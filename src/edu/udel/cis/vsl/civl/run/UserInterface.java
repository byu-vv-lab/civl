package edu.udel.cis.vsl.civl.run;

import static edu.udel.cis.vsl.gmc.Option.OptionType.BOOLEAN;
import static edu.udel.cis.vsl.gmc.Option.OptionType.INTEGER;
import static edu.udel.cis.vsl.gmc.Option.OptionType.STRING;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import edu.udel.cis.vsl.abc.ABC;
import edu.udel.cis.vsl.abc.ABCException;
import edu.udel.cis.vsl.abc.Activator;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.civl.CIVL;
import edu.udel.cis.vsl.civl.model.Models;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelBuilder;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.CommandLineParser;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.gmc.MisguidedExecutionException;
import edu.udel.cis.vsl.gmc.Option;
import edu.udel.cis.vsl.sarl.SARL;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public class UserInterface {

	// Static fields...

	/**
	 * A string printed before and after titles of sections of output to make
	 * them stand out among the clutter.
	 */
	public final static String bar = "===================";

	// Instance fields...

	/**
	 * The time at which this instance of UserInterface was created.
	 */
	public final double startTime = System.currentTimeMillis();

	/**
	 * The symbolic universe used in this session.
	 */
	SymbolicUniverse universe = SARL.newStandardUniverse();

	public final static Option errorBoundO = Option.newScalarOption(
			"errorBound", INTEGER, "stop after finding this many errors", 1);

	public final static Option showModelO = Option.newScalarOption("showModel",
			BOOLEAN, "print the model", false);

	public final static Option verboseO = Option.newScalarOption("verbose",
			BOOLEAN, "verbose mode", false);

	public final static Option randomO = Option.newScalarOption("random",
			BOOLEAN, "select enabled transitions randomly; default for run,\n"
					+ "    ignored for all other commands", null);

	public final static Option guidedO = Option.newScalarOption("guided",
			BOOLEAN, "user guided simulation; applies only to run, ignored\n"
					+ "    for all other commands", null);

	public final static Option seedO = Option.newScalarOption("seed", STRING,
			"set the random seed; applies only to run", null);

	public final static Option debugO = Option.newScalarOption("debug",
			BOOLEAN, "debug mode: print very detailed information", false);

	public final static Option userIncludePathO = Option.newScalarOption(
			"userIncludePath", STRING, "set the user include path", null);

	public final static Option sysIncludePathO = Option.newScalarOption(
			"sysIncludePath", STRING, "set the user include path", null);

	public final static Option showTransitionsO = Option.newScalarOption(
			"showTransitions", BOOLEAN, "print transitions", false);

	public final static Option showStatesO = Option.newScalarOption(
			"showStates", BOOLEAN, "print all states", false);

	public final static Option showSavedStatesO = Option.newScalarOption(
			"showSavedStates", BOOLEAN, "print saved states only", false);

	public final static Option showQueriesO = Option.newScalarOption(
			"showQueries", BOOLEAN, "print all queries", false);

	public final static Option showProverQueriesO = Option.newScalarOption(
			"showProverQueries", BOOLEAN, "print theorem prover queries only",
			false);

	public final static Option inputO = Option.newMapOption("input",
			"initialize input variable KEY to VALUE");

	/**
	 * The parser from the Generic Model Checking package used to parse the
	 * command line.
	 */
	private CommandLineParser parser;

	public UserInterface() {
		Collection<Option> options = Arrays.asList(errorBoundO, showModelO,
				verboseO, randomO, guidedO, seedO, debugO, userIncludePathO,
				sysIncludePathO, showTransitionsO, showStatesO,
				showSavedStatesO, showQueriesO, showProverQueriesO, inputO);

		parser = new CommandLineParser(options);
	}

	private void setToDefault(GMCConfiguration config, Option option) {
		config.setScalarValue(option, option.defaultValue());
	}

	private void setToDefault(GMCConfiguration config,
			Collection<Option> options) {
		for (Option option : options)
			setToDefault(config, option);
	}

	/**
	 * Prints usage information to the given stream and flushes the stream.
	 * 
	 * @param out
	 *            stream to which to print
	 */
	private void printUsage(PrintStream out) {
		out.println("Usage: civl <command> <options> file0 ...");
		out.println("Commands:");
		out.println("  verify : verify program file0");
		out.println("  run : run program file0");
		out.println("  help : print this message");
		out.println("  replay : replay trace for program file0 using trace file file1");
		out.println("  parse : show result of preprocessing and parsing file0");
		out.println("  preprocess : show result of preprocessing file0");
		out.println("Options:");
		parser.printUsage(out);
		out.flush();
	}

	/**
	 * Extracts from a string the "core" part of a filename by removing any
	 * directory prefixes and removing any file suffix. For example, invoking on
	 * "users/siegel/gcd/gcd1.cvl" will return "gcd1". This is the name used to
	 * name the model and other structures; it is used in the log, to name
	 * generated files, and for error reporting.
	 * 
	 * @param filename
	 *            a filename
	 * @return the core part of that filename
	 */
	private static String coreName(String filename) {
		String result = filename;
		char sep = File.separatorChar;
		int lastSep = filename.lastIndexOf(sep);
		if (lastSep >= 0)
			result = result.substring(lastSep + 1);
		int lastDot = result.lastIndexOf('.');
		if (lastDot >= 0)
			result = result.substring(0, lastDot);

		return result;
	}

	/**
	 * Instantiates, initializes, and returns a new compiler front end (an
	 * instance of ABC's Activator class) from the ABC compiler. The user and
	 * system include paths, if specified in the config, are used to instantiate
	 * the front end. The front end can then be used preprocess, parse, and
	 * transform the input file.
	 * 
	 * @param filename
	 *            the name of the file to be parsed
	 * @param config
	 *            the configuration parameters for this session
	 * @return the ABC Activator that can be used to parse and process the file
	 */
	private Activator getFrontEnd(String filename, GMCConfiguration config) {
		File file = new File(filename);
		File[] userIncludes = extractPaths((String) config
				.getValue(userIncludePathO));
		File[] sysIncludes = extractPaths((String) config
				.getValue(sysIncludePathO));
		Activator frontEnd = ABC.activator(file, sysIncludes, userIncludes);

		return frontEnd;
	}

	/**
	 * Applies the ABC preprocessor to the specified file, printing the result
	 * of preprocessing to the given stream.
	 * 
	 * @param out
	 *            the stream to which to print the result of preprocessing
	 * @param config
	 *            the configuration object specifying options and arguments for
	 *            this session
	 * @param filename
	 *            the name of the file to preprocess
	 * @throws PreprocessorException
	 *             if the file does not conform to the preprocessor grammar
	 */
	private void preprocess(PrintStream out, GMCConfiguration config,
			String filename) throws PreprocessorException {
		getFrontEnd(filename, config).preprocess(out);
	}

	/**
	 * Given a colon-separated list of filenames as a single string, this splits
	 * it up and returns an array of File objects, one for each name.
	 * 
	 * @param string
	 *            null or colon-separated list of filenames
	 * @return array of File
	 */
	private File[] extractPaths(String string) {
		if (string == null)
			return new File[0];
		else {
			String[] pieces = string.split(":");
			int numPieces = pieces.length;
			File[] result = new File[numPieces];

			for (int i = 0; i < numPieces; i++)
				result[i] = new File(pieces[i]);
			return result;
		}
	}

	private Model extractModel(PrintStream out, GMCConfiguration config,
			String filename) throws ABCException, IOException {
		boolean parse = "parse".equals(config.getFreeArg(0));
		boolean debug = config.isTrue(debugO);
		boolean verbose = config.isTrue(verboseO);
		boolean showModel = config.isTrue(showModelO);
		ModelBuilder modelBuilder = Models.newModelBuilder(universe);
		Activator frontEnd = getFrontEnd(filename, config);
		Program program;
		Model model;

		if (parse || debug) {
			// shows absolutely everything
			program = frontEnd.showTranslation(out);
		} else {
			if (verbose)
				out.println("Parsing program...");
			program = frontEnd.getProgram();
			if (verbose)
				out.println("Pruning unreachable nodes from AST...");
			program.prune();
			if (verbose)
				out.println("Removing side effects from expressions...");
			program.removeSideEffects();
			if (verbose) {
				out.println(bar + " Side-effect-free Pruned Program " + bar
						+ "\n");
				program.print(out);
				out.println();
			}
		}
		if (verbose || debug)
			out.println("Extracting CIVL model...");
		model = modelBuilder.buildModel(program);
		model.setName(coreName(filename));
		if (showModel || verbose || debug || parse) {
			out.println(bar + " Model " + bar + "\n");
			model.print(out);
		}
		return model;
	}

	/**
	 * Checks that number of filenames (the free arguments in the command line
	 * after the command itself) is as expected.
	 * 
	 * @param numExpected
	 *            the number of filenames expected
	 * @param config
	 *            the configuration object which specifies the free arguments
	 * @throws CommandLineException
	 *             if the number of free arguments is not equal to one plus the
	 *             number of expected filenames
	 */
	private void checkFilenames(int numExpected, GMCConfiguration config)
			throws CommandLineException {
		int numSeen = config.getNumFreeArgs() - 1;

		if (numSeen < numExpected)
			throw new CommandLineException(
					"Missing filename(s) in command line");
		if (numSeen > numExpected)
			throw new CommandLineException("Unexpected command line argument "
					+ config.getFreeArg(numExpected + 1));
	}

	/**
	 * Prints statistics after a run. The end time is marked and compared to the
	 * start time to compute total elapsed time. Other statistics are taken from
	 * the symbolic universe created in this class. The remaining statistics are
	 * provided as parameters to this method.
	 * 
	 * @param out
	 *            the stream to which to print
	 * @param maxProcs
	 *            the maximum number of processes that existed in any state
	 *            encountered
	 * @param statesSeen
	 *            the number of states seen in the run
	 * @param statesMatched
	 *            the number of states encountered which were determined to have
	 *            been seen before
	 * @param transitions
	 *            the number of transitions executed in the course of the run
	 */
	public void printStats(PrintStream out) {
		// round up time to nearest 1/100th of second...
		double time = Math
				.ceil((System.currentTimeMillis() - startTime) / 10.0) / 100.0;
		long numValidCalls = universe.numValidCalls();
		long numProverCalls = universe.numProverValidCalls();
		long memory = Runtime.getRuntime().totalMemory();

		out.println(bar + " Stats " + bar);
		out.print("   validCalls          : ");
		out.println(numValidCalls);
		out.print("   proverCalls         : ");
		out.println(numProverCalls);
		out.print("   memory (bytes)      : ");
		out.println(memory);
		out.print("   time (s)            : ");
		out.println(time);
	}

	/**
	 * Parses command line arguments and runs the CIVL tool(s) as specified by
	 * those arguments.
	 * 
	 * @param args
	 *            the command line arguments, e.g., {"verify", "-verbose",
	 *            "foo.c"}. This is an array of strings of length at least 1;
	 *            element 0 should be the name of the command
	 * @return true iff everything succeeded and no errors discovered
	 * @throws CommandLineException
	 *             if the args are not properly formatted commandline arguments
	 */
	public boolean runWork(String[] args) throws CommandLineException {
		PrintStream out = System.out, err = System.err;
		GMCConfiguration config = parser.parse(Arrays.asList(args));
		int numFree = config.getNumFreeArgs();
		String command;
		boolean result;

		out.println("CIVL v" + CIVL.version + " of " + CIVL.date
				+ " -- http://vsl.cis.udel.edu/civl");
		out.flush();
		if (numFree == 0)
			throw new CommandLineException("Missing command");
		command = config.getFreeArg(0);

		try {
			switch (command) {
			case "help":
				printUsage(out);
				return true;
			case "verify":
				checkFilenames(1, config);
				{
					String filename = config.getFreeArg(1);
					Model model = extractModel(out, config, filename);
					Verifier verifier = new Verifier(config, model, out);

					result = verifier.run();
					printStats(out);
					verifier.printStats();
					out.println();
					verifier.printResult();
					out.flush();
					break;
				}
			case "replay":
				checkFilenames(2, config);
				{
					String sourceFilename = config.getFreeArg(1);
					String traceFilename = config.getFreeArg(2);
					File traceFile = new File(traceFilename);
					GMCConfiguration newConfig = parser.newConfig();
					Model model;
					TracePlayer replayer;

					// need to get the original trace and overwrite
					// it with new options...
					parser.parse(newConfig, traceFile);
					setToDefault(newConfig, Arrays.asList(showModelO, verboseO,
							debugO, showTransitionsO, showStatesO,
							showSavedStatesO, showQueriesO, showProverQueriesO));
					parser.parse(newConfig, Arrays.asList(args));
					model = extractModel(out, newConfig, sourceFilename);
					replayer = new TracePlayer(newConfig, model, traceFile, out);
					result = replayer.run();
					break;
				}
			case "run":
				checkFilenames(1, config);
				{
					String filename = config.getFreeArg(1);
					Model model = extractModel(out, config, filename);
					// Player player = new Player(config, model);
					//
					// result = player.run();
					throw new UnsupportedOperationException(
							"run not yet implemented");
					// break;
				}
			case "parse": // run ABC, but get options right first
				checkFilenames(1, config);
				extractModel(out, config, config.getFreeArg(1));
				result = true;
				break;
			case "preprocess": // ditto
				checkFilenames(1, config);
				preprocess(out, config, config.getFreeArg(1));
				result = true;
				break;
			default:
				throw new CommandLineException("Unknown command: " + command);
			}
		} catch (ABCException e) {
			err.println(e);
			err.flush();
			result = false;
		} catch (IOException e) {
			err.println(e);
			err.flush();
			result = false;
		} catch (MisguidedExecutionException e) {
			err.println(e);
			err.flush();
			result = false;
		}
		return result;
	}

	/**
	 * Runs the appropriate CIVL tools based on the command line arguments.
	 * 
	 * @param args
	 *            command line arguments
	 * @return true iff everything succeeeded and no errors were found
	 */
	public boolean run(String[] args) {
		try {
			return runWork(args);
		} catch (CommandLineException e) {
			System.err.println(e.getMessage());
			System.err.println("Type \"civl help\" for command line syntax.");
			System.err.flush();
			// printUsage(System.out);
			// System.out.flush();
		}
		return false;
	}
}
