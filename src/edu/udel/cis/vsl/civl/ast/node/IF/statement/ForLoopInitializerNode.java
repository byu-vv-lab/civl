package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;

/**
 * A marker interface indicating this construct can be used as the first clause
 * in a "for" loop. This clause can be either an expression or a declaration.
 * 
 * 
 * From C11 Sec. 6.8.5.3: "The declaration part of a for statement shall only
 * declare identifiers for objects having storage class auto or register."
 * 
 * @author siegel
 * 
 */
public interface ForLoopInitializerNode extends ASTNode {

}
