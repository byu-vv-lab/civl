package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class HybridTest {
	/* *************************** Static Fields *************************** */

	private static File rootDir = new File("examples");

	private static UserInterface ui = new UserInterface();

	private static final String mpiPthread = "mpi-pthread";
	private static final String mpiOmp = "mpi-omp";

	// private static final String cudaOmp = "cuda-omp";

	/* *************************** Helper Methods *************************** */

	private static String filename(String parent, String name) {
		return new File(new File(rootDir, parent), name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void mpipthreads_both() throws ABCException {
		// assertTrue(ui.run("verify",
		// filename("mpi-pthread/mpithreads_both.c"),
		// "-input__NPROCS=3", "-showInputs", "-enablePrintf=false"));
		ui.run("show", "-showProgram",
				filename("mpi-pthread", "mpithreads_both.c"));
		ui.run("verify", "-input_NPROCS=3", "-showInputs",
				"-enablePrintf=true", filename(mpiPthread, "mpithreads_both.c"));
	}

	@Test
	public void mpi_pthreads_pie_collective() throws ABCException {
		assertTrue(ui.run("verify", "-input_NPROCS=2", "-enablePrintf=false",
				filename(mpiPthread, "mpi-pthreads-pie-collective.c")));
	}

	@Test
	public void helloWorld() throws ABCException {
		// ui.run("run", "-input_NPROCS=3", "-showTransitions=false",
		// filename("helloWorld.c"));
		// ui.run("show", "-showProgram", filename("helloWorld.c"));
		assertTrue(ui.run("verify", "-input_NPROCS=2",
				filename(mpiPthread, "helloWorld.c")));
	}

	@Test
	public void inform_blkstp() throws ABCException {
		assertTrue(ui.run("verify -input_NPROCS=2 -inputTHREAD_MAX=2 ",
				filename(mpiOmp, "mpi-omp-mat-infnorm-blkstp.c")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
