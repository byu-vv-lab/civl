package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;

/**
 * An item in a block (compound statement): either a statement or a "declaration."
 * 
 * Every instance of BlockItemNode is also an instance of one of the following:
 * 
 * <ul>
 * <li>StatementNode</li> (includes PragmaNode)
 * <li>StructureOrUnionTypeNode</li>
 * <li>EnumerationTypeNode</li>
 * <li>StaticAssertionNode</li>
 * <li>VariableDeclarationNode</li>
 * <li>FunctionDeclarationNode</li> (but not a FunctionDefinitionNode)
 * <li>TypedefDeclarationNode</li>
 * <li> 
 * </ul>
 * 
 * @author siegel
 * 
 */
public interface BlockItemNode extends ASTNode {

}
