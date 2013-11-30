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
import edu.udel.cis.vsl.gmc.GuidedTransitionChooser;
import edu.udel.cis.vsl.gmc.MisguidedExecutionException;
import edu.udel.cis.vsl.gmc.Replayer;
import edu.udel.cis.vsl.gmc.TransitionChooser;

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

	private TransitionChooser<State, Transition> chooser;

	private Replayer<State, Transition> replayer;

	TracePlayer(GMCConfiguration config, Model model, PrintStream out)
			throws CommandLineException {
		super(config, model, out);
		// turn the following off because they duplicate what
		// the Replayer prints:
		stateManager.setShowStates(false);
		stateManager.setShowSavedStates(false);
		stateManager.setShowTransitions(false);
		stateManager.setVerbose(false);
		log.setSearcher(null);
		replayer = new Replayer<State, Transition>(stateManager, out);
		replayer.setPrintAllStates(showStates || verbose || debug);
	}

	public TracePlayer(GMCConfiguration config, Model model,
			TransitionChooser<State, Transition> chooser, PrintStream out)
			throws CommandLineException {
		this(config, model, out);
		this.chooser = chooser;
	}

	public TracePlayer(GMCConfiguration config, Model model, File traceFile,
			PrintStream out) throws CommandLineException, IOException, MisguidedExecutionException {
		this(config, model, out);
		this.chooser = new GuidedTransitionChooser<State, Transition, TransitionSequence>(
				enabler, traceFile);
	}

	public boolean run() throws MisguidedExecutionException {
		State initialState = stateFactory.initialState(model);

		replayer.play(initialState, chooser);
		return true;
	}

	public void printStats() {
		out.print("   maxProcs            : ");
		out.println(stateManager.maxProcs());
	}

}
