package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class ContractTest {
	/* *************************** Static Fields *************************** */

	private static UserInterface ui = new UserInterface();

	private static File rootDir = new File(new File("examples"), "contracts");

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	@Test
	public void collective_assert() {
		assertFalse(ui.run(
				"verify -enablePrintf=false -input_mpi_nprocs=3 -mpiContract",
				filename("wildcard_coassert_bad.c")));
		assertTrue(ui
				.run("verify -enablePrintf=false -input_mpi_nprocs=4 -deadlock=potential -mpiContract",
						filename("wildcard_coassert_barrier.c")));
		assertTrue(ui
				.run("verify -enablePrintf=false -input_mpi_nprocs=4 -deadlock=potential -mpiContract",
						filename("reduce_coassert.c")));
	}

	@Test
	public void highErrorBoundCollective_assert() {
		assertFalse(ui
				.run("verify -enablePrintf=false -input_mpi_nprocs=4 -deadlock=potential -errorBound=10 -mpiContract",
						filename("wildcard_coassert_bad.c")));
	}
}
