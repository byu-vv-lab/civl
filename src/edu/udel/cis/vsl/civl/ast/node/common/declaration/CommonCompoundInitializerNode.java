package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.PairNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.CompoundInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonSequenceNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonCompoundInitializerNode extends
		CommonSequenceNode<PairNode<DesignationNode, InitializerNode>>
		implements CompoundInitializerNode {

	public CommonCompoundInitializerNode(Source source,
			List<PairNode<DesignationNode, InitializerNode>> childList) {
		super(source, "CompoundInitializer", childList);
	}

}
