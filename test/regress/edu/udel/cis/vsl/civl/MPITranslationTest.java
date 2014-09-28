package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class MPITranslationTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"),
			"translation/mpi");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void ring1() {
		assertTrue(ui.run("verify", filename("ring1.c"), "-input__NPROCS=2"));
	}

	@Test
	public void reduce() {
		assertTrue(ui.run("verify", filename("reduce.c"), "-input__NPROCS=2",
				"-enablePrintf=false"));
	}

	@Test
	public void mpithreads_mpi() {
		assertTrue(ui.run("verify", filename("mpithreads_mpi.c"),
				"-input__NPROCS=2", "-enablePrintf=false"));
	}

	@Test
	public void adder_par() {
		assertTrue(ui.run("verify", filename("adder_par.c"),
				"-input__NPROCS=2", "-inputNB=4", "-enablePrintf=false"));
	}

	@Test
	public void adder_comp() {
		assertTrue(ui.run("compare", "-enablePrintf=false", "-input__NPROCS=2",
				"-inputNB=4", filename("seq/adder_spec.c"),
				filename("adder_par.c")));
	}

	@Ignore
	@Test
	public void mpi_pi_send() {
		assertTrue(ui.run("verify", filename("mpi_pi_send.c"),
				"-input__NPROCS=3", "-enablePrintf=false"));
	}

	@Ignore
	@Test
	public void pi_comp() {
		assertTrue(ui.run("compare", "-input__NPROCS=3",
				filename("seq/ser_pi_calc.c"), filename("mpi_pi_send.c")));
	}

	@Test
	public void mpi_scatter() throws ABCException {
		assertTrue(ui.run("verify", filename("Gather_Scatter/mpi_scatter.c"),
				"-input__NPROCS=4", "-enablePrintf=false"));
	}

	@Test
	public void mpi_gather() throws ABCException {
		assertTrue(ui.run("verify", filename("Gather_Scatter/mpi_gather.c"),
				"-input__NPROCS=4", "-enablePrintf=false"));
	}

	@Test
	public void mpi_gatherv() throws ABCException {
		assertTrue(ui.run("verify",
				filename("Gather_Scatter/mpi_gather_inPlace.c"),
				"-input__NPROCS=4", "-enablePrintf=false"));
	}

	@Test
	public void mpi_scatterv() throws ABCException {
		assertTrue(ui.run("verify",
				filename("Gather_Scatter/mpi_scatter_inPlace.c"),
				"-input__NPROCS=4", "-enablePrintf=false"));
	}
}