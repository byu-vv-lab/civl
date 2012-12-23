package edu.udel.cis.vsl.civl.analysis;

import edu.udel.cis.vsl.civl.analysis.IF.Analyzer;
import edu.udel.cis.vsl.civl.analysis.common.StandardAnalyzer;
import edu.udel.cis.vsl.civl.ast.conversion.Conversions;
import edu.udel.cis.vsl.civl.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.civl.ast.entity.Entities;
import edu.udel.cis.vsl.civl.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.civl.ast.node.Nodes;
import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.ast.unit.IF.UnitFactory;
import edu.udel.cis.vsl.civl.ast.value.Values;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;

public class Analysis {

	public static Analyzer newStandardAnalyzer(EntityFactory entityFactory,
			NodeFactory nodeFactory, TokenFactory sourceFactory,
			ConversionFactory conversionFactory) {
		return new StandardAnalyzer(entityFactory, nodeFactory, sourceFactory,
				conversionFactory);
	}

	public static void performStandardAnalysis(TranslationUnit unit)
			throws SyntaxException {
		EntityFactory entityFactory = Entities.newEntityFactory();
		UnitFactory unitFactory = unit.getUnitFactory();
		TypeFactory typeFactory = unitFactory.getTypeFactory();
		ConversionFactory conversionFactory = Conversions
				.newConversionFactory(typeFactory);
		TokenFactory sourceFactory = unitFactory.getTokenFactory();
		ValueFactory valueFactory = Values.newValueFactory(typeFactory);
		NodeFactory nodeFactory = Nodes.newNodeFactory(typeFactory,
				valueFactory);
		Analyzer analyzer = newStandardAnalyzer(entityFactory, nodeFactory,
				sourceFactory, conversionFactory);

		analyzer.analyze(unit);
	}

}
