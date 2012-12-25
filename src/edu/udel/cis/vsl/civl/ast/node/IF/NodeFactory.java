package edu.udel.cis.vsl.civl.ast.node.IF;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ArrayDesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.CompoundInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnsuresNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.RequiresNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.AlignOfNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ArrowNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CollectiveExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CompoundLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.DotNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.EnumerationConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.RemoteExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeableNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeofNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SpawnNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.AssertNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.AssumeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ChooseStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.GotoNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.JumpNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.WaitNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.WhenNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.AtomicTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.BasicTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.EnumerationTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypedefNameNode;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.civl.token.IF.CToken;
import edu.udel.cis.vsl.civl.token.IF.ExecutionCharacter;
import edu.udel.cis.vsl.civl.token.IF.Source;
import edu.udel.cis.vsl.civl.token.IF.StringLiteral;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

/**
 * The factory used to construct the nodes of the Abstract Syntax Tree of a
 * translation unit.
 * 
 * The user constructs the nodes of an AST using the methods in this class.
 * These nodes have the structure of a tree, the root node being the node
 * representing the translation unit; the chidren of the root node correspond to
 * the "external definitions" of the unit. Once these have been constructed, the
 * newTranslationUnit method is invoked on the root node to actually construct
 * the TranslationUnit object. This performs a number of analyses and stores
 * additional information about the translation unit. A number of errors can be
 * detected and reported at this stage. Among other things, this also computes
 * the abstract "type" of every variable, function, and expression. It also
 * computes the scope and linkage of all identifiers.
 * 
 * After the TranslationUnit is created, the unit (and all of its nodes) become
 * immutable. Every node has an "owner" (originally null), which is set to the
 * TranslationUnit object at this time. If you want to modify the tree, you must
 * first invoke the "release" method, which frees the nodes from ownership by
 * the TranslationUnit object, setting the "owner" fields again to null. They
 * can then be modified, and then "newTranslationUnit" called again to
 * re-analylze and re-build a translation unit. Alternatively, you can also
 * clone, if you want to keep the old translation unit around for some reason.
 * 
 * Finally, one or more translation unit can be combined to form a complete
 * "program" using the newProgram method. This corresponds to "linking" in the
 * usual compiler sense.
 * 
 * 
 * @author siegel
 * 
 */
public interface NodeFactory {

	/**
	 * Creates an attribute slot for all AST nodes. This is a mechanism for
	 * extending the functionality of nodes. Thi may be used to hang any kind of
	 * data onto nodes.
	 * 
	 * @param attributeName
	 *            a name for the new attribute, unique among all attribute names
	 * @param attributeClass
	 *            the class to which attribute values of the new kind will
	 *            belong
	 * @return a new attribute key which can be used to assign attribute values
	 *         to nodes
	 */
	AttributeKey newAttribute(String attributeName, Class<Object> attributeClass);

	/**
	 * Creates a new sequence node, i.e., a node which has some finite ordered
	 * sequence of children belonging to a particular class.
	 * 
	 * @param source
	 *            source information for the whole sequence
	 * @param name
	 *            a name to use when printing this sequence node
	 * @param nodes
	 *            a list of nodes that will form the children of the new
	 *            sequence node
	 * @return the new sequence node with the children set
	 */
	<T extends ASTNode> SequenceNode<T> newSequenceNode(Source source,
			String name, List<T> nodes);

	/**
	 * Creates a new ordered pair node, i.e., a node with exactly two children
	 * belonging to two specific classes.
	 * 
	 * @param node1
	 *            the first child node
	 * @param node2
	 *            the second child node
	 * @return the new pair node with the children set
	 */
	<S extends ASTNode, T extends ASTNode> PairNode<S, T> newPairNode(
			Source source, S node1, T node2);

	// Identifiers...

	IdentifierNode newIdentifierNode(Source source, String name);

	// Type Nodes ...

	BasicTypeNode newBasicTypeNode(Source source, BasicTypeKind kind);

	TypeNode newVoidTypeNode(Source source);

	TypeNode newProcessTypeNode(Source source);

	EnumerationTypeNode newEnumerationTypeNode(Source source,
			IdentifierNode tag,
			SequenceNode<EnumeratorDeclarationNode> enumerators);

	ArrayTypeNode newArrayTypeNode(Source source, TypeNode elementType,
			ExpressionNode extent);

	AtomicTypeNode newAtomicTypeNode(Source source, TypeNode baseType);

	PointerTypeNode newPointerTypeNode(Source source, TypeNode referencedType);

	StructureOrUnionTypeNode newStructOrUnionTypeNode(Source source,
			boolean isStruct, IdentifierNode tag,
			SequenceNode<FieldDeclarationNode> structDeclList);

	FunctionTypeNode newFunctionTypeNode(Source source, TypeNode returnType,
			SequenceNode<VariableDeclarationNode> formals,
			boolean hasIdentifierList);

	/**
	 * Source is same as that of the identifier name.
	 * 
	 * @param name
	 * @return
	 */
	TypedefNameNode newTypedefNameNode(IdentifierNode name);

	// Expressions...

	/**
	 * If the expression can be evaluated statically to yield a constant value,
	 * this method returns that value, else it returns null.
	 * 
	 * Every "constant expression" will yield a (non-null) value, but other
	 * expressions not strictly considered "constant expressions" may also yield
	 * non-null constant values. Hence if method isConstantExpression() returns
	 * true, this method should return a non-null value; if
	 * isConstantExpression() returns false, this method may or may not return a
	 * non-null value.
	 * 
	 * @return the constant value obtained by evaluating this expression, or
	 *         null if the expression cannot be evaluated
	 */
	Value getConstantValue(ExpressionNode expression) throws SyntaxException;

	// Constant and literal expressions ...

	CharacterConstantNode newCharacterConstantNode(Source source,
			String representation, ExecutionCharacter character)
			throws SyntaxException;

	StringLiteralNode newStringLiteralNode(Source source,
			String representation, StringLiteral literal)
			throws SyntaxException;

	IntegerConstantNode newIntegerConstantNode(Source source,
			String representation) throws SyntaxException;

	FloatingConstantNode newFloatingConstantNode(Source source,
			String representation) throws SyntaxException;

	/**
	 * Source is same as that of identifier.
	 * 
	 * @param name
	 * @return
	 */
	EnumerationConstantNode newEnumerationConstantNode(IdentifierNode name);

	CompoundLiteralNode newCompoundLiteralNode(Source source,
			TypeNode typeNode, CompoundInitializerNode initializerList);

	/**
	 * "\true" or "\false".
	 * 
	 * @param source
	 * @param value
	 *            true for "\true", false for "\false"
	 * @return
	 */
	ConstantNode newBooleanConstantNode(Source source, boolean value);

	/**
	 * "\self"
	 * 
	 * @param source
	 * @return
	 */
	ExpressionNode newSelfNode(Source source);

	/**
	 * "\result"
	 * 
	 * @param source
	 * @return
	 */
	ExpressionNode newResultNode(Source source);

	// Other Expressions...

	/**
	 * Source is not necessarily same as identifier because you might want to
	 * include surrounding parentheses in the expression.
	 * 
	 * @param identifier
	 * @return
	 */
	IdentifierExpressionNode newIdentifierExpressionNode(Source source,
			IdentifierNode identifier);

	AlignOfNode newAlignOfNode(Source source, TypeNode type);

	CastNode newCastNode(Source source, TypeNode type, ExpressionNode argument);

	FunctionCallNode newFunctionCallNode(Source source,
			ExpressionNode function, List<ExpressionNode> arguments);

	DotNode newDotNode(Source source, ExpressionNode structure,
			IdentifierNode fieldName);

	ArrowNode newArrowNode(Source source, ExpressionNode structurePointer,
			IdentifierNode fieldName);

	OperatorNode newOperatorNode(Source source, Operator operator,
			List<ExpressionNode> arguments);

	SizeofNode newSizeofNode(Source source, SizeableNode argument);

	SpawnNode newSpawnNode(Source source, FunctionCallNode callNode);

	/**
	 * A remote expression node, representing an expression of the form
	 * "proc_expr@x". This refers to a variable in the process p referenced by
	 * the expression proc_expr. The static variable x can be determined
	 * statically now. Later it will be evaluated in a dynamic state in p's
	 * context.
	 */
	RemoteExpressionNode newRemoteExpressionNode(Source source,
			ExpressionNode left, IdentifierExpressionNode right);

	/**
	 * Creates a new collective expression node. This expression can be used in
	 * an assertion to form a collective assertion. It can also be used in an
	 * assume statement, a loop invariant, or a procedure contract.
	 * 
	 * The set of processes over which this collective expression spans is
	 * specified by an array whose elements have type \proc.
	 * 
	 * @param source
	 *            the source code elements
	 * @param processPointerExpression
	 *            a pointer to the first element of an array of process
	 *            references
	 * @param lengthExpression
	 *            the number of processes in the array
	 * @param body
	 *            the expression to be interpreted in the collective context
	 * @return the new collective expression node with given children
	 */
	CollectiveExpressionNode newCollectiveExpressionNode(Source source,
			ExpressionNode processPointerExpression,
			ExpressionNode lengthExpression, ExpressionNode body);

	// Declarations...

	/**
	 * Returns a new declaration of an "object" variable with no initializer.
	 * 
	 * @param source
	 * @param name
	 * @param type
	 * @return
	 */
	VariableDeclarationNode newVariableDeclarationNode(Source source,
			IdentifierNode name, TypeNode type);

	/**
	 * Returns a new declaration for an "object" variable with an initializer.
	 * 
	 * @param name
	 *            identifier being declared
	 * @param type
	 *            the type
	 * @param initializer
	 *            optional initializer (for variables only) or null
	 * @return a new declaration for an "ordinary identifier"
	 */
	VariableDeclarationNode newVariableDeclarationNode(Source source,
			IdentifierNode name, TypeNode type, InitializerNode initializer);

	/**
	 * Returns a new function declaration with no body (so it is not a function
	 * "definition").
	 * 
	 * @param source
	 * @param name
	 * @param type
	 * @return
	 */
	FunctionDeclarationNode newFunctionDeclarationNode(Source source,
			IdentifierNode name, TypeNode type,
			SequenceNode<ContractNode> contract);

	EnumeratorDeclarationNode newEnumeratorDeclarationNode(Source source,
			IdentifierNode name, ExpressionNode value);

	FieldDeclarationNode newFieldDeclarationNode(Source source,
			IdentifierNode name, TypeNode type);

	FieldDeclarationNode newFieldDeclarationNode(Source source,
			IdentifierNode name, TypeNode type, ExpressionNode bitFieldWidth);

	OrdinaryLabelNode newStandardLabelDeclarationNode(Source source,
			IdentifierNode name, StatementNode statement);

	SwitchLabelNode newCaseLabelDeclarationNode(Source source,
			ExpressionNode constantExpression, StatementNode statement);

	SwitchLabelNode newDefaultLabelDeclarationNode(Source source,
			StatementNode statement);

	TypedefDeclarationNode newTypedefDeclarationNode(Source source,
			IdentifierNode name, TypeNode type);

	CompoundInitializerNode newCompoundInitializerNode(Source source,
			List<PairNode<DesignationNode, InitializerNode>> initList);

	DesignationNode newDesignationNode(Source source,
			List<DesignatorNode> designators);

	FieldDesignatorNode newFieldDesignatorNode(Source source,
			IdentifierNode name);

	ArrayDesignatorNode newArrayDesignatorNode(Source source,
			ExpressionNode index);

	// Statements...

	CompoundStatementNode newCompoundStatementNode(Source source,
			List<BlockItemNode> items);

	/**
	 * Source is same as that of the expression.
	 * 
	 * @param expression
	 * @return
	 */
	ExpressionStatementNode newExpressionStatementNode(ExpressionNode expression);

	StatementNode newNullStatementNode(Source source);

	/**
	 * 
	 * @param initializer
	 *            an Expression or another instance of ForLoopInitializerNode,
	 *            such as one produced from a list of delcarations; may be null
	 * @param condition
	 * @param incrementer
	 * @param body
	 * @param invariant
	 *            loop invariant: may be null
	 * @return
	 */
	ForLoopNode newForLoopNode(Source source,
			ForLoopInitializerNode initializer, ExpressionNode condition,
			ExpressionNode incrementer, StatementNode body,
			ExpressionNode invariant);

	ForLoopInitializerNode newForLoopInitializerNode(Source source,
			List<VariableDeclarationNode> declarations);

	LoopNode newWhileLoopNode(Source source, ExpressionNode condition,
			StatementNode body, ExpressionNode invariant);

	LoopNode newDoLoopNode(Source source, ExpressionNode condition,
			StatementNode body, ExpressionNode invariant);

	GotoNode newGotoNode(Source source, IdentifierNode label);

	/**
	 * Creates new if statement when there is no false ("else") branch.
	 * 
	 * @param source
	 * @param condition
	 * @param trueBranch
	 * @return
	 */
	IfNode newIfNode(Source source, ExpressionNode condition,
			StatementNode trueBranch);

	/**
	 * False branch may be null if there is no "else" clause.
	 * 
	 * @param condition
	 * @param trueBranch
	 * @param falseBranch
	 * @return
	 */
	IfNode newIfNode(Source source, ExpressionNode condition,
			StatementNode trueBranch, StatementNode falseBranch);

	JumpNode newContinueNode(Source source);

	JumpNode newBreakNode(Source source);

	/**
	 * Argument may be null.
	 * 
	 * @param argument
	 * @return
	 */
	ReturnNode newReturnNode(Source source, ExpressionNode argument);

	LabeledStatementNode newLabeledStatementNode(Source source,
			LabelNode label, StatementNode statement);

	SwitchNode newSwitchNode(Source source, ExpressionNode condition,
			StatementNode body);

	WaitNode newWaitNode(Source source, ExpressionNode expression);

	AssertNode newAssertNode(Source source, ExpressionNode expression);

	AssumeNode newAssumeNode(Source source, ExpressionNode expression);

	WhenNode newWhenNode(Source source, ExpressionNode guard, StatementNode body);

	ChooseStatementNode newChooseStatementNode(Source source,
			List<StatementNode> statements);

	// misc. nodes ...

	StaticAssertionNode newStaticAssertionNode(Source source,
			ExpressionNode expression, StringLiteralNode message);

	PragmaNode newPragmaNode(Source source, IdentifierNode identifier,
			List<CToken> body, CToken newlineToken);

	RequiresNode newRequiresNode(Source source, ExpressionNode expression);

	EnsuresNode newEnsuresNode(Source source, ExpressionNode expression);

	// external definitions...

	FunctionDefinitionNode newFunctionDefinitionNode(Source source,
			IdentifierNode name, TypeNode type,
			SequenceNode<ContractNode> contract, CompoundStatementNode body);

	ASTNode newTranslationUnitNode(Source source,
			List<ExternalDefinitionNode> definitions);

	ValueFactory getValueFactory();

	TypeFactory getTypeFactory();

}
