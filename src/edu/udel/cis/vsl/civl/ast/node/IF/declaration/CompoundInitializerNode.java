package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.PairNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;

/**
 * A compound initializer (written with curly braces in C) is used initialize an
 * array, struct, or union. It is specified as a sequence of
 * designation-initializer pairs.
 * 
 * @author siegel
 * 
 */
public interface CompoundInitializerNode extends InitializerNode,
		SequenceNode<PairNode<DesignationNode, InitializerNode>> {

}
