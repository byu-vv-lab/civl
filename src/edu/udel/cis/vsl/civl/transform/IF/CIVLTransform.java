package edu.udel.cis.vsl.civl.transform.IF;

import java.util.List;

import edu.udel.cis.vsl.abc.antlr2ast.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.IF.Transform;
import edu.udel.cis.vsl.abc.transform.IF.TransformRecord;
import edu.udel.cis.vsl.abc.transform.IF.Transformer;
import edu.udel.cis.vsl.civl.transform.common.CIVLBaseTransformer;
import edu.udel.cis.vsl.civl.transform.common.GeneralTransformer;
import edu.udel.cis.vsl.civl.transform.common.IOTransformer;
import edu.udel.cis.vsl.civl.transform.common.MPI2CIVLTransformer;
import edu.udel.cis.vsl.civl.transform.common.OmpPragmaTransformer;
import edu.udel.cis.vsl.civl.transform.common.OpenMPTransformer;

public class CIVLTransform {
	public static String GENERAL = GeneralTransformer.CODE;
	public static String IO = IOTransformer.CODE;
	public static String OMP_PRAGMA = OmpPragmaTransformer.CODE;
	public static String OMP = OpenMPTransformer.CODE;
	public static String MPI = MPI2CIVLTransformer.CODE;

	static {
		if (!Transform.getCodes().contains(GeneralTransformer.CODE))
			Transform.addTransform(new TransformRecord(GeneralTransformer.CODE,
					GeneralTransformer.LONG_NAME,
					GeneralTransformer.SHORT_DESCRIPTION) {
				@Override
				public Transformer create(ASTFactory astFactory) {
					return new GeneralTransformer(astFactory);
				}
			});
		if (!Transform.getCodes().contains(IOTransformer.CODE))
			Transform.addTransform(new TransformRecord(IOTransformer.CODE,
					IOTransformer.LONG_NAME, IOTransformer.SHORT_DESCRIPTION) {
				@Override
				public Transformer create(ASTFactory astFactory) {
					return new IOTransformer(astFactory);
				}
			});
		if (!Transform.getCodes().contains(OpenMPTransformer.CODE))
			Transform.addTransform(new TransformRecord(OpenMPTransformer.CODE,
					OpenMPTransformer.LONG_NAME,
					OpenMPTransformer.SHORT_DESCRIPTION) {
				@Override
				public Transformer create(ASTFactory astFactory) {
					return new OpenMPTransformer(astFactory);
				}
			});
		if (!Transform.getCodes().contains(OmpPragmaTransformer.CODE))
			Transform.addTransform(new TransformRecord(
					OmpPragmaTransformer.CODE, OmpPragmaTransformer.LONG_NAME,
					OmpPragmaTransformer.SHORT_DESCRIPTION) {
				@Override
				public Transformer create(ASTFactory astFactory) {
					return new OmpPragmaTransformer(astFactory);
				}
			});
		if (!Transform.getCodes().contains(MPI2CIVLTransformer.CODE))
			Transform.addTransform(new TransformRecord(
					MPI2CIVLTransformer.CODE, MPI2CIVLTransformer.LONG_NAME,
					MPI2CIVLTransformer.SHORT_DESCRIPTION) {
				@Override
				public Transformer create(ASTFactory astFactory) {
					return new MPI2CIVLTransformer(astFactory);
				}
			});
	}

	/**
	 * Applies a transformer to a program.
	 * 
	 * @param program
	 *            The program to be transformed.
	 * @param code
	 *            The code of a transformer, should be one of the following:<br>
	 *            <ul>
	 *            <li>"general": general transformer</li>
	 *            <li>"io": IO transformer</li>
	 *            <li>"mpi": MPI-to-CIVL transformer</li>
	 *            <li>"_omp_": OpenMP pragma transformer</li>
	 *            <li>"omp": OpenMP-to-CIVL transformer</li>
	 *            </ul>
	 * @throws SyntaxException
	 */
	public static void applyTransformer(Program program, String code,
			List<String> inputVars, ASTBuilder astBuilder)
			throws SyntaxException {
		if (code.equals(CIVLTransform.GENERAL) || code.equals(CIVLTransform.IO)
				|| code.equals(CIVLTransform.MPI)
				|| code.equals(CIVLTransform.OMP)
				|| code.equals(CIVLTransform.OMP_PRAGMA)) {
			CIVLBaseTransformer transformer = (CIVLBaseTransformer) Transform
					.newTransformer(code, program.getAST().getASTFactory());

			transformer.setASTBuilder(astBuilder);
			transformer.setInputVars(inputVars);
			program.apply(transformer);
		} else {
			program.applyTransformer(code);
		}
	}
}
