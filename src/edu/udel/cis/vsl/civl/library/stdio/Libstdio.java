package edu.udel.cis.vsl.civl.library.stdio;

import java.io.PrintStream;
import java.util.Vector;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * Executor for stdio function calls.
 * 
 * @author Ziqing Luo (ziqing)
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class Libstdio implements LibraryExecutor {

	/* ************************** Instance Fields ************************** */

	/**
	 * Enable or disable printing. By default true, i.e., enable printing.
	 */
	private boolean enablePrintf;

	/**
	 * The unique evaluator used in the system.
	 */
	private Evaluator evaluator;

	/**
	 * The output stream to be used for printing.
	 */
	private PrintStream output = System.out;

	/**
	 * The unique state factory to obtain information of a certain state and
	 * generate new states.
	 */
	private StateFactory stateFactory;

	/**
	 * The SARL symbolic universe used by this system.
	 */
	private SymbolicUniverse universe;

	/* **************************** Constructors *************************** */

	/**
	 * Create a new instance of library executor for "stdio.h".
	 * 
	 * @param primaryExecutor
	 *            The main executor of the system.
	 * @param output
	 *            The output stream for printing.
	 * @param enablePrintf
	 *            True iff print is enabled, reflecting command line options.
	 */
	public Libstdio(Executor primaryExecutor, PrintStream output,
			boolean enablePrintf, ModelFactory modelFactory) {
		this.evaluator = primaryExecutor.evaluator();
		this.universe = evaluator.universe();
		this.stateFactory = evaluator.stateFactory();
		this.enablePrintf = enablePrintf;
		this.output = output;
	}

	/* ******************** Methods from LibraryExecutor ******************* */

	@Override
	public boolean containsFunction(String name) {
		switch (name) {
		case "printf":
			return true;
		case "fprintf":
			throw new CIVLUnimplementedFeatureException(name);
		default:
			throw new CIVLInternalException(name, (CIVLSource) null);
		}
	}

	@Override
	public State execute(State state, int pid, Statement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, (CallOrSpawnStatement) statement);
	}

	@Override
	public BooleanExpression getGuard(State state, int pid, Statement statement) {
		return universe.trueExpression();
	}

	@Override
	public State initialize(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		return "stdio";
	}

	@Override
	public State wrapUp(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Execute a function call statement for a certain process at a given state.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The Id of the process that the call statement belongs to.
	 * @param statement
	 *            The call statement to be executed.
	 * @return The new state after executing the call statement.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeWork(State state, int pid,
			CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		int numArgs;

		if (!(statement instanceof CallOrSpawnStatement)) {
			throw new CIVLInternalException("Unsupported statement for civlc",
					statement);
		}
		statement = (CallOrSpawnStatement) statement;
		numArgs = statement.arguments().size();
		name = statement.function().name();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = statement.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "printf":
			state = executePrintf(state, pid, argumentValues);
			state = stateFactory.setLocation(state, pid, statement.target());
			break;
		default:
			throw new CIVLUnimplementedFeatureException(name.name(), statement);

		}
		return state;
	}

	/**
	 * Execute Printf() function. Escape Characters can be supported and have
	 * been tested are: \n, \r, \b, \t, \", \', \\ Format specifiers can be
	 * supported and have been tested are: %d, %o, %x, %f, %e, %g, %a, %c, %s If
	 * users want to print addresses of pointers with arguments in the form of
	 * &a, please use %s as their format specifiers.
	 * 
	 * TODO CIVL currently dosen't support 'printf("%c" , c)'(where c is a char
	 * type variable)?
	 * 
	 * 
	 * @param state
	 * @param pid
	 * @param argumentValues
	 * @return State
	 */
	private State executePrintf(State state, int pid,
			SymbolicExpression[] argumentValues) {
		String stringOfSymbolicExpression = new String();
		String stringOutput = new String();
		Vector<Object> arguments = new Vector<Object>();
		CIVLSource source = state.getProcessState(pid).getLocation()
				.getSource();

		if (!this.enablePrintf)
			return state;
		// obtain printf() arguments
		stringOfSymbolicExpression += argumentValues[0];
		for (int i = 1; i < argumentValues.length; i++) {
			arguments.add(argumentValues[i]);
		}
		// convert the first argument from
		// a symbolic expression to a string can be printed
		stringOutput = this.abcArrayAnalyzer(stringOfSymbolicExpression, true,
				source);
		// convert a char array from a symbolic exrepssion to a string
		for (int i = 0; i < arguments.size(); i++) {
			SymbolicType.SymbolicTypeKind type = ((SymbolicExpression) arguments
					.get(i)).type().typeKind();
			// Type is char array
			if (type == SymbolicType.SymbolicTypeKind.ARRAY) {
				String arg_str = this.abcArrayAnalyzer(arguments.get(i)
						.toString(), false, source);
				// update
				arguments.remove(i);
				arguments.insertElementAt(arg_str, i);
			}
		}
		// Print
		output.printf(stringOutput, arguments.toArray());
		return state;
	}

	/**
	 * Extreact characters from symbolic expression
	 * 
	 * @param stringFromABC
	 * @param convertFormatSpecifier
	 * @return
	 */
	private String abcArrayAnalyzer(String stringFromABC,
			boolean convertFormatSpecifier, CIVLSource source) {
		Vector<String> individualChars = new Vector<String>();
		String stringOutput = new String();
		int eleNumInCharArray;
		char[] chars;

		// get the number of characters
		eleNumInCharArray = Integer.parseInt((((stringFromABC
				.split("\\u0028CHAR\\u005B"))[1]).split("]\\u0029<"))[0]);
		// Split the output stream into separate characters
		chars = stringFromABC.split("\\u0028CHAR\\u005B" + eleNumInCharArray
				+ "]\\u0029<")[1].toCharArray();
		// number check
		if (chars.length != (eleNumInCharArray * 2 + eleNumInCharArray - 1
				+ eleNumInCharArray + 1))
			return "Unknown Exception in character number checking in printf";
		if ((chars.length <= 4) || ((chars.length) % 4 != 0))
			return "Unknown Exception in character number checking in printf";
		// start at 4, end at charnum - 6: extract real useful character
		// step = 4: ,'char'
		for (int i = 4; i < chars.length - 5;) {
			if (chars[i] == '\'')
				if (chars[i + 2] == '\'')
					if (chars[i + 3] == ',')
						individualChars.add("" + chars[i + 1]);
			i += 4;
			if ((i == chars.length - 6) && (chars[i] == '\'')) // termination
				break;
			else if ((i == chars.length - 6) && (chars[i] != '\''))
				return ("Unknown Exception in characters extraction in printf");
		}
		// convert characters to String, replace '\'+'n' with "\n"
		for (int i = 0; i < individualChars.size(); i++) {
			if (individualChars.get(i).equals("\\")
					&& (i < individualChars.size() - 1)) {
				switch (individualChars.get(i + 1)) {
				case "n":
					stringOutput += "\n";
					i++;
					break;
				case "t":
					stringOutput += "\t";
					i++;
					break;
				case "r":
					stringOutput += "\r";
					i++;
					break;
				case "b":
					stringOutput += "\b";
					i++;
					break;
				case "f":
					stringOutput += "\f";
					i++;
					break;
				case "\"":
					stringOutput += "\"";
					i++;
					break;
				case "\'":
					stringOutput += "\'";
					i++;
					break;
				default:
					throw new CIVLUnimplementedFeatureException(
							individualChars.get(i + 1) + " in printf()", source);
				}
			} else {
				stringOutput += individualChars.get(i);
			}
		}
		/* replace format specifiers with %s */
		if (convertFormatSpecifier)
			stringOutput = stringOutput.replaceAll(
					"%[0-9]*[.]?[0-9]*[dfoxegac]", "%s");

		return stringOutput;
	}

}
