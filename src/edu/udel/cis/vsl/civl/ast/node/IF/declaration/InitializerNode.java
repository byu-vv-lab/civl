package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;

/**
 * A marker interface for any node that can be used as an initializer. An
 * initializer is used in a declaration to give an initial value to an
 * identifier.
 * 
 * There are two kinds of initializers: a simple expression (used to initialize
 * scalar variables) and compound initializers (used to initialize arrays,
 * structs, and unions).
 * 
 * @author siegel
 * 
 */
public interface InitializerNode extends ASTNode {

}
