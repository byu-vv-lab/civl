package edu.udel.cis.vsl.civl.run;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.gmc.Replayer;

/**
 * A tool to replay a trace saved by a previous CIVL session.
 * 
 * NOTE: need to have the new options override the ones specified in the trace
 * file.
 * 
 * @author siegel
 * 
 */
public class TracePlayer extends Player {

	private Replayer<State, Transition, TransitionSequence> replayer;

	private File traceFile;

	/**
	 * 
	 * @param config
	 * @param args
	 * @param model
	 * @param traceFilename
	 * @param out
	 * @throws CommandLineException
	 */
	public TracePlayer(GMCConfiguration config, Model model, File traceFile,
			PrintStream out) throws CommandLineException {
		super(config, model, out);
		this.traceFile = traceFile;
		replayer = new Replayer<State, Transition, TransitionSequence>(enabler,
				stateManager, out);
		log.setSearcher(null);
		// turn the following off because they duplicate what
		// the Replayer prints:
		stateManager.setShowStates(false);
		stateManager.setShowSavedStates(false);
		stateManager.setShowTransitions(false);
		stateManager.setVerbose(false);
		replayer.setPrintAllStates(showStates || verbose || debug);
	}

	public boolean run() throws IOException {
		State initialState = stateFactory.initialState(model);

		replayer.play(initialState, traceFile);
		return false;
	}

	public void printStats() {
		out.print("   maxProcs            : ");
		out.println(stateManager.maxProcs());
	}

}
