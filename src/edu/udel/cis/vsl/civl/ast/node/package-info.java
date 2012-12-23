/**
 * This package deals with AST nodes.
 * 
 * <ul>
 * <li>
 * <code>ASTNode</code> (root)
 *   <ul>
 *   <li>Children: <code>ExternalDefinitionNode</code></li>
 *   </ul>
 * </li>
 * 
 * <li>ExternalDefinitionNode.
 *   Subtypes: OrdinaryDeclarationNode, PragmaNode,
 *     StaticAssertionNode, TypedefDeclarationNode.
 * </li>
 * 
 * <li>
 * DeclarationNode.
 *   Children: 0=IdentifierNode.
 * </li>
 * 
 * <li>
 * OrdinaryDeclarationNode.
 *   Inherited children: 0 (from DeclarationNode).
 *   Children: 1=TypeNode.
 *   Subtypes: FunctionDeclarationNode, VariableDeclarationNode.
 * </li>
 * 
 * <li>
 * FunctionDeclarationNode.
 *   Subtypes: FunctionDefinitionNode.
 * </li>
 * 
 * <li>
 * FunctionDefinitionNode.
 *   Inherited children: 0-1 (from OrdinaryDeclarationNode).
 *   Children: 2=CompoundStatementNode.
 * </li>
 * 
 * <li>
 * VariableDeclarationNode.
 *   Inherited children: 0-1 (from OrdinaryDeclarationNode).
 *   Children:
 *     2=InitializerNode (optional).
 *     3=SequenceNode&lt;ExpressionNode&gt; (optional, constant alignments).
 *     4=SequenceNode&lt;TypeNode&gt; (optional,type alignments).
 * </li>
 * 
 * <li>
 * InitializerNode.
 *   Subtypes: ExpressionNode, CompoundInitializerNode.
 * </li>
 * 
 * <li>
 * PragmaNode.
 *   Children: 0=IdentifierNode.
 * </li>
 * 
 * <li>
 * StaticAssertionNode.
 *   Children: 0=ExpressionNode, 1=StringLiteralNode.
 * </li>
 * 
 * <li>
 * TypedefDeclarationNode.
 *   Inherited children: 0 (DeclarationNode).
 * </li>
 * 
 * <li>
 * CompoundInitializerNode.
 *   Children: PairNode<DesignationNode,InitializerNode>.
 * </li>
 * 
 * <li>
 * DesignationNode.
 *   Children: DesignatorNode.
 * </li>
 * 
 * <li>
 * DesignatorNode.
 *   Subtypes: ArrayDesignatorNode, FieldDesignatorNode.
 * </li>
 * 
 * <li>
 * ArrayDesignatorNode.
 *   Children: 0=ExpressionNode (index).
 * </li>
 * 
 * <li>
 * FieldDesignatorNode.
 *   Children: 0=IdentifierNode (field).
 * </li>
 * 
 * <li>
 * TypeNode.
 *   Subtypes: BasicTypeNode, ArrayTypeNode, AtomicTypeNode, EnumerationTypeNode,
 *     FunctionTypeNode, PointerTypeNode, StructureOrUnionTypeNode,
 *     TypedefNameNode.
 * </li>
 * 
 * <li>
 * BasicTypeNode.
 * </li>
 * 
 * <li>
 * ArrayTypeNode.
 *   Children: 0=TypeNode (element type), 1=ExpressionNode (size).
 * </li>
 * 
 * <li>
 * AtomicTypeNode.
 *   Children: 0=TypeNode.
 * </li>
 * 
 * <li>
 * EnumerationTypeNode.
 *   Children: 0=IdentifierNode, 1=SequenceNode&lt;EnumeratorDeclarationNode&lt;.
 * </li>
 * 
 * <li>
 * FunctionTypeNode.
 *   Children: 0=TypeNode (return type),
 *     1=SequenceNode&lt;VariableDeclarationNode&gt; (parameter types).
 * </li>
 * 
 * <li>
 * PointerTypeNode.
 *   Children: 0=TypeNode (referenced type).
 * </li>
 * 
 * <li>
 * StructureOrUnionTypeNode.
 *   Children: 0=IdentifierNode (tag),
 *     1=SequenceNode&lt;FieldDeclarationNode&gt; (structDeclList).
 * </li>
 *  
 * <li> 
 * TypedefNameNode.
 *   Children: 0=IdentifierNode.
 * </li>
 * 
 * <li>
 * ExpressionNode.
 *   Subtypes: AlignOfNode, ArrowNode, CastNode, ConstantNode, CompoundLiteralNode,
 *     DotNode, FunctionCallNode, GenericSelectionNode, IdentifierExpressionNode,
 *     OperatorNode.
 *  </li>
 *  
 * <li>
 * AlignOfNode.
 *   Children: 0=TypeNode
 * </li>
 * 
 * <li>
 * ArrowNode.
 *   Children: 0=ExpressionNode (struct pointer), 1=IdentifierNode (field name).
 * </li>
 * 
 * <li>
 * CastNode.
 *   Children: 0=TypeNode, 1=ExpressionNode.
 *  </li>
 *  
 * <li> 
 * ConstantNode.
 *   Subtypes: CharacterConstantNode, EnumerationConstantNode, FloatingConstantNode,
 *     IntegerConstantNode, StringLiteralNode.  All have 0 children.
 * </li>
 * 
 * <li>
 * CompoundLiteralNode: TODO.
 * </li>
 * 
 * <li>
 * DotNode.
 *   Children: 0=ExpressionNode, 1=IdentifierNode.
 * </li>
 * 
 * <li>
 * FunctionCallNode.
 *   Children: 0=ExpressionNode (function),
 *     1=SequenceNode&lt;ExpressionNode&gt; (arguments)
 * </li>
 * 
 * <li>
 * GenericSelectionNode.
 *   Children: TODO
 * </li>
 * 
 * <li>
 * IdentifierExpressionNode.
 *   Children: 0=IdentifierNode
 * </li>
 * 
 * <li>
 * OperatorNode.
 *   Children: ExpressionNode.
 * </li>
 * 
 * </ul>
 * 
 */
package edu.udel.cis.vsl.civl.ast.node;

