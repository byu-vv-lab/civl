package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class AMGTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File(new File("examples"), "mpi-omp"), "AMG2013");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void AMGUtils() {
		assertTrue(ui.run("verify", "-ompNoSimplify", "-input_NPROCS=1", "-inputTHREAD_MAX=1",
				"-DTIMER_USE_MPI", "-DHYPRE_USING_OPENMP", "-DHYPRE_TIMING",
				"-userIncludePath=examples/mpi-omp/AMG2013/utilities:examples/mpi-omp/AMG2013:examples/mpi-omp/AMG2013/parcsr_mv:examples/mpi-omp/AMG2013/seq_mv:examples/mpi-omp/AMG2013/sstruct_mv:examples/mpi-omp/AMG2013/struct_mv:examples/mpi-omp/AMG2013/IJ_mv:examples/mpi-omp/AMG2013/parcsr_ls:examples/mpi-omp/AMG2013/krylov",
				filename("test/amg2013.c"),filename("utilities/amg_linklist.c "),filename("utilities/binsearch.c"),
				filename("utilities/exchange_data.c "),filename("utilities/hypre_memory.c "),filename("utilities/hypre_qsort.c "),
				filename("utilities/memory_dmalloc.c "),filename("utilities/mpistubs.c "),filename("utilities/qsplit.c "),
				filename("utilities/thread_mpistubs.c "),filename("utilities/threading.c "),filename("utilities/timer.c "),
				filename("utilities/timing.c "),filename("utilities/umalloc_local.c ")));
		
	}

	

}
