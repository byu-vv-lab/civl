package edu.udel.cis.vsl.civl.dev;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import edu.udel.cis.vsl.abc.FrontEnd;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import edu.udel.cis.vsl.civl.transform.IF.TransformerFactory;
import edu.udel.cis.vsl.civl.transform.IF.Transforms;
import org.junit.experimental.categories.Category;

@Category(edu.udel.cis.vsl.civl.DevTests.class)
public class Cuda2CIVLTransformTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "cuda");

	private static UserInterface ui = new UserInterface();

	@SuppressWarnings("unused")
	private PrintStream out = System.out;

	private File root = new File(new File("examples"), "cuda");
	
	@SuppressWarnings("unused")
	private File cudaHelper = new File(root, "cuda-helper.cvh");
	

	// private static List<String> codes = Arrays.asList("prune", "sef");

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/**
	 * tests a Cuda program by applying the following transformers in
	 * sequence:
	 * <ol>
//	 * <li>Cuda to CIVL transformer</li>
	 * </ol>
	 * 
	 * @param filenameRoot
	 *            The file name of the Cuda program (without extension).
	 * @param debug
	 *            The flag to be set for printing.
	 * @throws ABCException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void check(String filenameRoot, boolean debug) throws ABCException,
			IOException {
		FrontEnd frontEnd = new FrontEnd();
		TransformerFactory transformerFactory = Transforms
				.newTransformerFactory(frontEnd.getASTFactory());
		Program program;
		File file = new File(root, filenameRoot + ".cu");


		program = frontEnd.compileAndLink(new File[] { file }, Language.CIVL_C);
//		if (debug) {
//			PrintStream before = new PrintStream("/tmp/before_simplify");
//			program.getAST().prettyPrint(before, true);
//			PrintStream beforeAST = new PrintStream("/tmp/before_AST");
//			frontEnd.printProgram(beforeAST, program, false, false);
//		}
		program.apply(transformerFactory.getCuda2CIVLTransformer());
		program.prettyPrint(System.out);
//		if (debug) {
//			PrintStream after = new PrintStream("/tmp/after_simplify");
//			program.getAST().prettyPrint(after, true);
//		}
//		program.apply(transformerFactory.getOpenMP2CIVLTransformer());
//		if (debug) {
//			out.println("======== After applying OpenMP Simplifier ========");
//			frontEnd.printProgram(out, program, true, false);
//		}
//		program.applyTransformer("prune");
//		if (debug) {
//			out.println("======== After applying Pruner ========");
//			frontEnd.printProgram(out, program, true, false);
//		}
//		program.applyTransformer("sef");
//		if (debug) {
//			out.println("======== After applying Side Effect Remover ========");
//			frontEnd.printProgram(out, program, true, false);
//		}
	}

	/* **************************** Test Methods *************************** */
	
	@Test
	public void sum() {
		assertTrue(ui.run("verify", "-inputN=8", "-inputNBLOCKS=4", filename("sum.cu")));
	}
	
	@Test
	public void dot() {
		assertTrue(ui.run("verify", "-inputN_B=6", "-inputthreadsPerBlock_B=4", filename("dot.cu")));
	}
	
	@Test
	public void matMult() {
		assertTrue(ui.run("verify", "-inputN=2", "-inputTILE_WIDTH=1", filename("matMult1.cu")));
	}
	
	@Test
	public void cudaOmp() {
		assertTrue(ui.run("verify", "-inputBLOCK_B=4", "-inputTHREADS_B=2", filename("cuda-omp.cu")));
	}
}
