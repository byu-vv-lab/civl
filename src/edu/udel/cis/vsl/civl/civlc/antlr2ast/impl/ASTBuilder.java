package edu.udel.cis.vsl.civl.civlc.antlr2ast.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.node.IF.PairNode;
import edu.udel.cis.vsl.civl.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.StaticAssertionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.CompoundInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CompoundLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeableNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeofNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ChooseStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.AtomicTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.EnumerationTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypedefNameNode;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.ast.unit.IF.UnitFactory;
import edu.udel.cis.vsl.civl.civlc.parse.IF.CParser;
import edu.udel.cis.vsl.civl.civlc.parse.common.CivlCParser;
import edu.udel.cis.vsl.civl.token.IF.CToken;
import edu.udel.cis.vsl.civl.token.IF.CharacterToken;
import edu.udel.cis.vsl.civl.token.IF.Source;
import edu.udel.cis.vsl.civl.token.IF.StringToken;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;

/**
 * Builds an AST from an ANTLR tree.
 * 
 * TODO: standardize token names across grammar and classes.
 * 
 * @author siegel
 * 
 */
public class ASTBuilder {

	// Convenient constants...

	static final int ALIGNAS = CivlCParser.ALIGNAS;
	static final int ALIGNOF = CivlCParser.ALIGNOF;
	static final int AMPERSAND = CivlCParser.AMPERSAND;
	static final int AND = CivlCParser.AND;
	static final int ARROW = CivlCParser.ARROW;
	static final int ASSIGN = CivlCParser.ASSIGN;
	static final int ATOMIC = CivlCParser.ATOMIC;
	static final int AUTO = CivlCParser.AUTO;
	static final int BITANDEQ = CivlCParser.BITANDEQ;
	static final int BITOR = CivlCParser.BITOR;
	static final int BITOREQ = CivlCParser.BITOREQ;
	static final int BITXOR = CivlCParser.BITXOR;
	static final int BITXOREQ = CivlCParser.BITXOREQ;
	static final int BOOL = CivlCParser.BOOL;
	static final int BREAK = CivlCParser.BREAK;
	static final int CASE = CivlCParser.CASE;
	static final int CHAR = CivlCParser.CHAR;
	static final int CHARACTER_CONSTANT = CivlCParser.CHARACTER_CONSTANT;
	static final int COLON = CivlCParser.COLON;
	static final int COMMA = CivlCParser.COMMA;
	static final int COMMENT = CivlCParser.COMMENT;
	static final int COMPLEX = CivlCParser.COMPLEX;
	static final int CONST = CivlCParser.CONST;
	static final int CONTINUE = CivlCParser.CONTINUE;
	static final int DEFAULT = CivlCParser.DEFAULT;
	static final int DIV = CivlCParser.DIV;
	static final int DIVEQ = CivlCParser.DIVEQ;
	static final int DO = CivlCParser.DO;
	static final int DOT = CivlCParser.DOT;
	static final int DOUBLE = CivlCParser.DOUBLE;
	static final int ELLIPSIS = CivlCParser.ELLIPSIS;
	static final int ELSE = CivlCParser.ELSE;
	static final int ENUM = CivlCParser.ENUM;
	static final int EQUALS = CivlCParser.EQUALS;
	static final int EXTERN = CivlCParser.EXTERN;
	static final int FLOAT = CivlCParser.FLOAT;
	static final int FLOATING_CONSTANT = CivlCParser.FLOATING_CONSTANT;
	static final int FOR = CivlCParser.FOR;
	static final int GENERIC = CivlCParser.GENERIC;
	static final int GOTO = CivlCParser.GOTO;
	static final int GT = CivlCParser.GT;
	static final int GTE = CivlCParser.GTE;
	static final int IDENTIFIER = CivlCParser.IDENTIFIER;
	static final int IF = CivlCParser.IF;
	static final int IMAGINARY = CivlCParser.IMAGINARY;
	static final int INLINE = CivlCParser.INLINE;
	static final int INT = CivlCParser.INT;
	static final int INTEGER_CONSTANT = CivlCParser.INTEGER_CONSTANT;
	static final int LCURLY = CivlCParser.LCURLY;
	static final int LONG = CivlCParser.LONG;
	static final int LPAREN = CivlCParser.LPAREN;
	static final int LSQUARE = CivlCParser.LSQUARE;
	static final int LT = CivlCParser.LT;
	static final int LTE = CivlCParser.LTE;
	static final int MINUSMINUS = CivlCParser.MINUSMINUS;
	static final int MOD = CivlCParser.MOD;
	static final int MODEQ = CivlCParser.MODEQ;
	static final int NEQ = CivlCParser.NEQ;
	static final int NEWLINE = CivlCParser.NEWLINE;
	static final int NORETURN = CivlCParser.NORETURN;
	static final int NOT = CivlCParser.NOT;
	static final int OR = CivlCParser.OR;
	static final int OTHER = CivlCParser.OTHER;
	static final int PARENTHESIZED_EXPRESSION = CivlCParser.PARENTHESIZED_EXPRESSION;
	static final int PLUS = CivlCParser.PLUS;
	static final int PLUSEQ = CivlCParser.PLUSEQ;
	static final int PLUSPLUS = CivlCParser.PLUSPLUS;
	static final int POST_DECREMENT = CivlCParser.POST_DECREMENT;
	static final int POST_INCREMENT = CivlCParser.POST_INCREMENT;
	static final int PRE_DECREMENT = CivlCParser.PRE_DECREMENT;
	static final int PRE_INCREMENT = CivlCParser.PRE_INCREMENT;
	static final int PP_NUMBER = CivlCParser.PP_NUMBER;
	static final int PRAGMA = CivlCParser.PRAGMA;
	static final int QMARK = CivlCParser.QMARK;
	static final int RCURLY = CivlCParser.RCURLY;
	static final int REGISTER = CivlCParser.REGISTER;
	static final int RESTRICT = CivlCParser.RESTRICT;
	static final int RETURN = CivlCParser.RETURN;
	static final int RPAREN = CivlCParser.RPAREN;
	static final int RSQUARE = CivlCParser.RSQUARE;
	static final int SEMI = CivlCParser.SEMI;
	static final int SHIFTLEFT = CivlCParser.SHIFTLEFT;
	static final int SHIFTLEFTEQ = CivlCParser.SHIFTLEFTEQ;
	static final int SHIFTRIGHT = CivlCParser.SHIFTRIGHT;
	static final int SHIFTRIGHTEQ = CivlCParser.SHIFTRIGHTEQ;
	static final int SHORT = CivlCParser.SHORT;
	static final int SIGNED = CivlCParser.SIGNED;
	static final int SIZEOF = CivlCParser.SIZEOF;
	static final int STAR = CivlCParser.STAR;
	static final int STAREQ = CivlCParser.STAREQ;
	static final int STATIC = CivlCParser.STATIC;
	static final int STATICASSERT = CivlCParser.STATICASSERT;
	static final int STRING_LITERAL = CivlCParser.STRING_LITERAL;
	static final int STRUCT = CivlCParser.STRUCT;
	static final int SUB = CivlCParser.SUB;
	static final int SUBEQ = CivlCParser.SUBEQ;
	static final int SWITCH = CivlCParser.SWITCH;
	static final int THREADLOCAL = CivlCParser.THREADLOCAL;
	static final int TILDE = CivlCParser.TILDE;
	static final int TYPEDEF = CivlCParser.TYPEDEF;
	static final int UNION = CivlCParser.UNION;
	static final int UNSIGNED = CivlCParser.UNSIGNED;
	static final int VOID = CivlCParser.VOID;
	static final int VOLATILE = CivlCParser.VOLATILE;
	static final int WHILE = CivlCParser.WHILE;
	static final int WS = CivlCParser.WS;
	static final int ABSENT = CivlCParser.ABSENT;
	static final int ABSTRACT_DECLARATOR = CivlCParser.ABSTRACT_DECLARATOR;
	static final int ARGUMENT_LIST = CivlCParser.ARGUMENT_LIST;
	static final int ARRAY_ELEMENT_DESIGNATOR = CivlCParser.ARRAY_ELEMENT_DESIGNATOR;
	static final int ARRAY_SUFFIX = CivlCParser.ARRAY_SUFFIX;
	static final int COMPOUND_STATEMENT = CivlCParser.COMPOUND_STATEMENT;
	static final int BLOCK_ITEM_LIST = CivlCParser.BLOCK_ITEM_LIST;
	static final int CALL = CivlCParser.CALL;
	static final int CASE_LABELED_STATEMENT = CivlCParser.CASE_LABELED_STATEMENT;
	static final int CAST = CivlCParser.CAST;
	static final int COMPOUND_LITERAL = CivlCParser.COMPOUND_LITERAL;
	static final int DECLARATION = CivlCParser.DECLARATION;
	static final int DECLARATION_LIST = CivlCParser.DECLARATION_LIST;
	static final int DECLARATION_SPECIFIERS = CivlCParser.DECLARATION_SPECIFIERS;
	static final int DECLARATOR = CivlCParser.DECLARATOR;
	static final int DEFAULT_LABELED_STATEMENT = CivlCParser.DEFAULT_LABELED_STATEMENT;
	static final int DESIGNATED_INITIALIZER = CivlCParser.DESIGNATED_INITIALIZER;
	static final int DESIGNATION = CivlCParser.DESIGNATION;
	static final int DIRECT_ABSTRACT_DECLARATOR = CivlCParser.DIRECT_ABSTRACT_DECLARATOR;
	static final int DIRECT_DECLARATOR = CivlCParser.DIRECT_DECLARATOR;
	static final int ENUMERATION_CONSTANT = CivlCParser.ENUMERATION_CONSTANT;
	static final int ENUMERATOR = CivlCParser.ENUMERATOR;
	static final int ENUMERATOR_LIST = CivlCParser.ENUMERATOR_LIST;
	static final int EXPR = CivlCParser.EXPR;
	static final int EXPRESSION_STATEMENT = CivlCParser.EXPRESSION_STATEMENT;
	static final int FIELD_DESIGNATOR = CivlCParser.FIELD_DESIGNATOR;
	static final int FUNCTION_DEFINITION = CivlCParser.FUNCTION_DEFINITION;
	static final int FUNCTION_SUFFIX = CivlCParser.FUNCTION_SUFFIX;
	static final int GENERIC_ASSOCIATION = CivlCParser.GENERIC_ASSOCIATION;
	static final int GENERIC_ASSOC_LIST = CivlCParser.GENERIC_ASSOC_LIST;
	static final int IDENTIFIER_LABELED_STATEMENT = CivlCParser.IDENTIFIER_LABELED_STATEMENT;
	static final int IDENTIFIER_LIST = CivlCParser.IDENTIFIER_LIST;
	static final int INDEX = CivlCParser.INDEX;
	static final int INITIALIZER_LIST = CivlCParser.INITIALIZER_LIST;
	static final int INIT_DECLARATOR = CivlCParser.INIT_DECLARATOR;
	static final int INIT_DECLARATOR_LIST = CivlCParser.INIT_DECLARATOR_LIST;
	static final int OPERATOR = CivlCParser.OPERATOR;
	static final int PARAMETER_DECLARATION = CivlCParser.PARAMETER_DECLARATION;
	static final int PARAMETER_LIST = CivlCParser.PARAMETER_LIST;
	static final int PARAMETER_TYPE_LIST = CivlCParser.PARAMETER_TYPE_LIST;
	static final int POINTER = CivlCParser.POINTER;
	static final int SCALAR_INITIALIZER = CivlCParser.SCALAR_INITIALIZER;
	static final int SPECIFIER_QUALIFIER_LIST = CivlCParser.SPECIFIER_QUALIFIER_LIST;
	static final int STRUCT_DECLARATION = CivlCParser.STRUCT_DECLARATION;
	static final int STRUCT_DECLARATION_LIST = CivlCParser.STRUCT_DECLARATION_LIST;
	static final int STRUCT_DECLARATOR = CivlCParser.STRUCT_DECLARATOR;
	static final int STRUCT_DECLARATOR_LIST = CivlCParser.STRUCT_DECLARATOR;
	static final int TRANSLATION_UNIT = CivlCParser.TRANSLATION_UNIT;
	static final int TYPE = CivlCParser.TYPE;
	static final int TYPEDEF_NAME = CivlCParser.TYPEDEF_NAME;
	static final int TYPE_NAME = CivlCParser.TYPE_NAME;
	static final int TYPE_QUALIFIER_LIST = CivlCParser.TYPE_QUALIFIER_LIST;

	// added for CIVL-C...

	static final int PROC = CivlCParser.PROC;
	static final int SELF = CivlCParser.SELF;
	static final int INPUT = CivlCParser.INPUT;
	static final int OUTPUT = CivlCParser.OUTPUT;
	static final int SPAWN = CivlCParser.SPAWN;
	static final int WAIT = CivlCParser.WAIT;
	static final int ASSERT = CivlCParser.ASSERT;
	static final int TRUE = CivlCParser.TRUE;
	static final int FALSE = CivlCParser.FALSE;
	static final int ASSUME = CivlCParser.ASSUME;
	static final int WHEN = CivlCParser.WHEN;
	static final int CHOOSE = CivlCParser.CHOOSE;
	static final int INVARIANT = CivlCParser.INVARIANT;
	static final int REQUIRES = CivlCParser.REQUIRES;
	static final int ENSURES = CivlCParser.ENSURES;
	static final int RESULT = CivlCParser.RESULT;
	static final int AT = CivlCParser.AT;
	static final int COLLECTIVE = CivlCParser.COLLECTIVE;

	// Instance fields...

	private CParser parser;

	private NodeFactory nodeFactory;

	private TokenFactory sourceFactory;

	private UnitFactory unitFactory;

	private CommonTree rootTree;

	// Constructors...

	/**
	 * Constructs a new ASTBuilder for the given ANTLR tree.
	 * 
	 * @param factory
	 *            an ASTFactory to use
	 * @param rootTree
	 *            the root of the ANTLR tree
	 * @param tokenSource
	 *            the CTokenSource used to produce the ANTLR tree
	 * 
	 */
	public ASTBuilder(CParser parser, UnitFactory unitFactory,
			CommonTree rootTree) {
		this.parser = parser;
		this.unitFactory = unitFactory;
		this.nodeFactory = unitFactory.getNodeFactory();
		this.sourceFactory = unitFactory.getTokenFactory();
		this.rootTree = rootTree;
	}

	// The "main" instance method...

	/**
	 * The main method: given an ANTLR tree, produces a TranslationUnit.
	 * 
	 * @param tree
	 *            an ANTLR syntax tree
	 * @return a TranslationUnit representing the given syntax tree
	 * @throws SyntaxException
	 *             if there is something in the tree that does not conform to
	 *             the C11 standard
	 */
	public TranslationUnit getTranslationUnit() throws SyntaxException {
		ASTNode root = translateTranslationUnit(rootTree);

		return unitFactory.newTranslationUnit(root);
	}

	// Supporting methods...

	private SyntaxException error(String message, CommonTree tree) {
		return new SyntaxException(message, newSource(tree));
	}

	private SyntaxException error(String message, ASTNode node) {
		return new SyntaxException(message, node.getSource());
	}

	private Source newSource(CommonTree tree) {
		return parser.source(tree);
	}

	private SpecifierAnalysis newSpecifierAnalysis(CommonTree specifiers)
			throws SyntaxException {
		return new SpecifierAnalysis(specifiers, parser);
	}

	private boolean isFunction(TypeNode type, SimpleScope scope)
			throws SyntaxException {
		return isFunction(type, scope, new HashSet<String>());
	}

	private boolean isFunction(TypeNode type, SimpleScope scope,
			Set<String> seenNames) throws SyntaxException {
		TypeNodeKind kind = type.kind();

		switch (kind) {
		case FUNCTION:
			return true;
		case TYPEDEF_NAME: {
			String typeName = ((TypedefNameNode) type).getName().name();
			TypeNode referencedNode = scope.getReferencedType(typeName);

			if (seenNames.contains(typeName))
				throw error("Cycle in typedefs", type);
			while (referencedNode == null) {
				scope = scope.getParent();
				if (scope == null)
					throw error("Could not resolve typedef name", type);
				referencedNode = scope.getReferencedType(typeName);
			}
			seenNames.add(typeName);
			return isFunction(referencedNode, scope, seenNames);
		}
		default:
			return false;
		}
	}

	private IdentifierNode translateIdentifier(CommonTree identifier) {
		CToken token = (CToken) identifier.getToken();
		Source source = sourceFactory.newSource(token);

		return nodeFactory.newIdentifierNode(source, token.getText());
	}

	private IntegerConstantNode translateIntegerConstant(Source source,
			CommonTree integerConstant) throws SyntaxException {
		return nodeFactory.newIntegerConstantNode(source,
				integerConstant.getText());
	}

	private FloatingConstantNode translateFloatingConstant(Source source,
			CommonTree floatingConstant) throws SyntaxException {
		return nodeFactory.newFloatingConstantNode(source,
				floatingConstant.getText());
	}

	private CharacterConstantNode translateCharacterConstant(Source source,
			CommonTree characterConstant) throws SyntaxException {
		CharacterToken token = (CharacterToken) characterConstant.getToken();

		return nodeFactory.newCharacterConstantNode(source,
				characterConstant.getText(), token.getExecutionCharacter());
	}

	private ConstantNode translateTrue(Source source) {
		return nodeFactory.newBooleanConstantNode(source, true);
	}

	private ConstantNode translateFalse(Source source) {
		return nodeFactory.newBooleanConstantNode(source, false);
	}

	private StringLiteralNode translateStringLiteral(Source source,
			CommonTree stringLiteral) throws SyntaxException {
		StringToken token = (StringToken) stringLiteral.getToken();

		return nodeFactory.newStringLiteralNode(source,
				stringLiteral.getText(), token.getStringLiteral());
	}

	private ExpressionNode translateExpression(CommonTree expressionTree,
			SimpleScope scope) throws SyntaxException {
		int kind = expressionTree.getType();

		if (kind == ABSENT)
			return null;
		return translateExpression(newSource(expressionTree), expressionTree,
				scope);
	}

	/**
	 * Translates an expression.
	 * 
	 * @param expressionTree
	 *            any CommonTree node representing an expression
	 * @return an ExpressionNode
	 * @throws SyntaxException
	 */
	private ExpressionNode translateExpression(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		int kind = expressionTree.getType();

		switch (kind) {
		case INTEGER_CONSTANT:
			return translateIntegerConstant(source, expressionTree);
		case FLOATING_CONSTANT:
			return translateFloatingConstant(source, expressionTree);
		case ENUMERATION_CONSTANT:
			return nodeFactory
					.newEnumerationConstantNode(translateIdentifier((CommonTree) expressionTree
							.getChild(0)));
		case CHARACTER_CONSTANT:
			return translateCharacterConstant(source, expressionTree);
		case STRING_LITERAL:
			return translateStringLiteral(source, expressionTree);
		case IDENTIFIER:
			return nodeFactory.newIdentifierExpressionNode(source,
					translateIdentifier(expressionTree));
		case PARENTHESIZED_EXPRESSION:
			return translateExpression(source,
					(CommonTree) expressionTree.getChild(1), scope);
		case GENERIC: // TODO: genericSelection
			throw new UnsupportedOperationException(
					"Generic selections not yet implemented");
		case CALL:
			return translateCall(source, expressionTree, scope);
		case DOT:
		case ARROW:
			return translateDotOrArrow(source, expressionTree, scope);
		case COMPOUND_LITERAL:
			return translateCompoundLiteral(source, expressionTree, scope);
		case OPERATOR:
			return translateOperatorExpression(source, expressionTree, scope);
		case SIZEOF:
			return translateSizeOf(source, expressionTree, scope);
		case ALIGNOF:
			return nodeFactory.newAlignOfNode(
					source,
					translateTypeName((CommonTree) expressionTree.getChild(0),
							scope));
		case CAST:
			return nodeFactory.newCastNode(
					source,
					translateTypeName((CommonTree) expressionTree.getChild(0),
							scope),
					translateExpression(
							(CommonTree) expressionTree.getChild(1), scope));
		case SELF:
			return nodeFactory.newSelfNode(source);
		case SPAWN: {
			return nodeFactory.newSpawnNode(source,
					translateCall(source, expressionTree, scope));
		}
		case TRUE:
			return translateTrue(source);
		case FALSE:
			return translateFalse(source);
		case RESULT:
			return nodeFactory.newResultNode(source);
		case AT: {
			CommonTree procExprTree = (CommonTree) expressionTree.getChild(0);
			CommonTree identifierTree = (CommonTree) expressionTree.getChild(1);
			ExpressionNode procExpr = translateExpression(procExprTree, scope);
			IdentifierNode identifierNode = translateIdentifier(identifierTree);

			return nodeFactory.newRemoteExpressionNode(source, procExpr,
					nodeFactory.newIdentifierExpressionNode(
							newSource(identifierTree), identifierNode));
		}
		case COLLECTIVE:
			return nodeFactory.newCollectiveExpressionNode(
					source,
					translateExpression(
							(CommonTree) expressionTree.getChild(0), scope),
					translateExpression(
							(CommonTree) expressionTree.getChild(1), scope),
					translateExpression(
							(CommonTree) expressionTree.getChild(2), scope));
		default:
			throw error("Unknown expression kind", expressionTree);
		}
	}

	/**
	 * Translates a function call expression.
	 * 
	 * @param callTree
	 *            CommonTree node of type CALL, representing a function call
	 * @return a FunctionCallNode corresponding to the ANTLR tree
	 * @throws SyntaxException
	 */
	private FunctionCallNode translateCall(Source source, CommonTree callTree,
			SimpleScope scope) throws SyntaxException {
		CommonTree functionTree = (CommonTree) callTree.getChild(1);
		CommonTree argumentListTree = (CommonTree) callTree.getChild(2);
		ExpressionNode functionNode = translateExpression(functionTree, scope);
		int numArgs = argumentListTree.getChildCount();
		List<ExpressionNode> argumentList = new LinkedList<ExpressionNode>();

		for (int i = 0; i < numArgs; i++) {
			CommonTree argumentTree = (CommonTree) argumentListTree.getChild(i);
			ExpressionNode argumentNode = translateExpression(argumentTree,
					scope);

			argumentList.add(argumentNode);
		}
		return nodeFactory.newFunctionCallNode(source, functionNode,
				argumentList);
	}

	/**
	 * 
	 * @param compoundLiteralTree
	 * @return
	 * @throws SyntaxException
	 */
	private CompoundLiteralNode translateCompoundLiteral(Source source,
			CommonTree compoundLiteralTree, SimpleScope scope)
			throws SyntaxException {
		// tree has form:
		// ^(COMPOUND_LITERAL LPAREN typeName initializerList RCURLY)
		TypeNode typeNode = translateTypeName(
				(CommonTree) compoundLiteralTree.getChild(1), scope);
		CompoundInitializerNode initializerList = (CompoundInitializerNode) translateInitializer(
				(CommonTree) compoundLiteralTree.getChild(2), scope);

		return nodeFactory.newCompoundLiteralNode(source, typeNode,
				initializerList);
	}

	/**
	 * 
	 * @param expressionTree
	 * @return
	 * @throws SyntaxException
	 */
	private ExpressionNode translateDotOrArrow(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		int kind = expressionTree.getType();
		CommonTree argumentNode = (CommonTree) expressionTree.getChild(0);
		CommonTree fieldNode = (CommonTree) expressionTree.getChild(1);
		ExpressionNode argument = translateExpression(argumentNode, scope);
		IdentifierNode fieldName = translateIdentifier(fieldNode);

		if (kind == DOT)
			return nodeFactory.newDotNode(source, argument, fieldName);
		else
			return nodeFactory.newArrowNode(source, argument, fieldName);
	}

	/**
	 * 
	 * @param expressionTree
	 * @return
	 * @throws SyntaxException
	 */
	private OperatorNode translateOperatorExpression(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		CommonTree operatorTree = (CommonTree) expressionTree.getChild(0);
		int operatorKind = operatorTree.getType();
		CommonTree argumentList = (CommonTree) expressionTree.getChild(1);
		int numArgs = argumentList.getChildCount();
		List<ExpressionNode> arguments = new LinkedList<ExpressionNode>();
		Operator operator;

		for (int i = 0; i < numArgs; i++) {
			ExpressionNode argument = translateExpression(
					(CommonTree) argumentList.getChild(i), scope);

			arguments.add(argument);
		}
		switch (operatorKind) {
		case AMPERSAND:
			operator = numArgs == 1 ? Operator.ADDRESSOF : Operator.BITAND;
			break;
		case ASSIGN:
			operator = Operator.ASSIGN;
			break;
		case BITANDEQ:
			operator = Operator.BITANDEQ;
			break;
		case TILDE:
			operator = Operator.BITCOMPLEMENT;
			break;
		case BITOR:
			operator = Operator.BITOR;
			break;
		case BITOREQ:
			operator = Operator.BITOREQ;
			break;
		case BITXOR:
			operator = Operator.BITXOR;
			break;
		case BITXOREQ:
			operator = Operator.BITXOREQ;
			break;
		case COMMA:
			operator = Operator.COMMA;
			break;
		case QMARK:
			operator = Operator.CONDITIONAL;
			break;
		case STAR:
			operator = numArgs == 1 ? Operator.DEREFERENCE : Operator.TIMES;
			break;
		case DIV:
			operator = Operator.DIV;
			break;
		case DIVEQ:
			operator = Operator.DIVEQ;
			break;
		case EQUALS:
			operator = Operator.EQUALS;
			break;
		case GT:
			operator = Operator.GT;
			break;
		case GTE:
			operator = Operator.GTE;
			break;
		case AND:
			operator = Operator.LAND;
			break;
		case OR:
			operator = Operator.LOR;
			break;
		case LT:
			operator = Operator.LT;
			break;
		case LTE:
			operator = Operator.LTE;
			break;
		case SUB:
			operator = numArgs == 1 ? Operator.UNARYMINUS : Operator.MINUS;
			break;
		case SUBEQ:
			operator = Operator.MINUSEQ;
			break;
		case MOD:
			operator = Operator.MOD;
			break;
		case MODEQ:
			operator = Operator.MODEQ;
			break;
		case NEQ:
			operator = Operator.NEQ;
			break;
		case NOT:
			operator = Operator.NOT;
			break;
		case PLUS:
			operator = numArgs == 1 ? Operator.UNARYPLUS : Operator.PLUS;
			break;
		case PLUSEQ:
			operator = Operator.PLUSEQ;
			break;
		case POST_DECREMENT:
			operator = Operator.POSTDECREMENT;
			break;
		case POST_INCREMENT:
			operator = Operator.POSTINCREMENT;
			break;
		case PRE_DECREMENT:
			operator = Operator.PREDECREMENT;
			break;
		case PRE_INCREMENT:
			operator = Operator.PREINCREMENT;
			break;
		case SHIFTLEFT:
			operator = Operator.SHIFTLEFT;
			break;
		case SHIFTLEFTEQ:
			operator = Operator.SHIFTLEFTEQ;
			break;
		case SHIFTRIGHT:
			operator = Operator.SHIFTRIGHT;
			break;
		case SHIFTRIGHTEQ:
			operator = Operator.SHIFTRIGHTEQ;
			break;
		case INDEX:
			operator = Operator.SUBSCRIPT;
			break;
		case STAREQ:
			operator = Operator.TIMESEQ;
			break;
		default:
			throw error("Unknown operator :", operatorTree);
		}
		return nodeFactory.newOperatorNode(source, operator, arguments);
	}

	/**
	 * 
	 * @param expressionTree
	 * @return
	 * @throws SyntaxException
	 */
	private SizeofNode translateSizeOf(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		int kind = expressionTree.getChild(0).getType();
		CommonTree child = (CommonTree) expressionTree.getChild(1);
		SizeableNode sizeable;

		if (kind == EXPR)
			sizeable = translateExpression(child, scope);
		else if (kind == TYPE)
			sizeable = translateTypeName(child, scope);
		else
			throw error("Unexpected argument to sizeof", expressionTree);
		return nodeFactory.newSizeofNode(source, sizeable);
	}

	/**
	 * Returns a list consiting of the following kinds of external definitions:
	 * 
	 * <ul>
	 * <li>VariableDeclarationNode</li>
	 * <li>FunctionDeclarationNode</li> (includes FunctionDefinitionNode)
	 * <li>StructureOrUnionTypeNode</li>
	 * <li>EnumerationTypeNode</li>
	 * <li>TypedefDeclarationNode</li>
	 * </ul>
	 * 
	 * @param declarationTree
	 *            CommonTree node of type DECLARATION (not static assertions)
	 * @return list of external definitions
	 * @throws SyntaxException
	 *             if the declaration does not conform to the C11 Standard
	 */
	private List<ExternalDefinitionNode> translateDeclaration(
			CommonTree declarationTree, SimpleScope scope)
			throws SyntaxException {
		CommonTree declarationSpecifiers = (CommonTree) declarationTree
				.getChild(0);
		CommonTree initDeclaratorList = (CommonTree) declarationTree
				.getChild(1);
		CommonTree contractTree = (CommonTree) declarationTree.getChild(2);
		SequenceNode<ContractNode> contract = getContract(contractTree, scope);
		SpecifierAnalysis analysis = newSpecifierAnalysis(declarationSpecifiers);
		int numDeclarators = initDeclaratorList.getChildCount();
		ArrayList<ExternalDefinitionNode> definitionList = new ArrayList<ExternalDefinitionNode>();
		Source source = newSource(declarationTree);

		if (numDeclarators == 0) {
			// C11 Sec. 6.7 Constraint 2:
			// "A declaration other than a static_assert declaration shall
			// declare at least a declarator (other than the parameters of a
			// function or the members of a structure or union), a tag, or the
			// members of an enumeration."
			TypeNode baseType = newSpecifierType(analysis, scope);
			ExternalDefinitionNode definition;

			if (baseType instanceof EnumerationTypeNode)
				definition = (EnumerationTypeNode) baseType;
			else if (baseType instanceof StructureOrUnionTypeNode)
				definition = (StructureOrUnionTypeNode) baseType;
			else
				throw error("Declaration missing declarator", declarationTree);
			definitionList.add(definition);
			return definitionList;
		}
		for (int i = 0; i < numDeclarators; i++) {
			CommonTree initDeclarator = (CommonTree) initDeclaratorList
					.getChild(i);
			CommonTree declaratorTree = (CommonTree) initDeclarator.getChild(0);
			CommonTree initializerTree = (CommonTree) initDeclarator
					.getChild(1);
			InitializerNode initializer = translateInitializer(initializerTree,
					scope);
			TypeNode baseType = newSpecifierType(analysis, scope);
			DeclaratorData data = processDeclarator(declaratorTree, baseType,
					scope);
			ExternalDefinitionNode definition;

			if (analysis.typedefCount > 0) {
				String name;

				definition = nodeFactory.newTypedefDeclarationNode(source,
						data.identifier, data.type);
				if (data.identifier == null)
					throw error("Missing identifier in typedef", declaratorTree);
				name = data.identifier.name();
				scope.putMapping(name, data.type);
			} else if (isFunction(data.type, scope)) {
				FunctionDeclarationNode declaration = nodeFactory
						.newFunctionDeclarationNode(source, data.identifier,
								data.type, contract);

				setFunctionSpecifiers(declaration, analysis);
				setStorageSpecifiers(declaration, analysis, scope);
				if (initializer != null)
					throw error("Initializer used in function declaration",
							initializerTree);
				checkAlignmentSpecifiers(declaration, analysis);
				definition = declaration;
			} else {
				VariableDeclarationNode declaration = nodeFactory
						.newVariableDeclarationNode(source, data.identifier,
								data.type);

				if (initializer != null)
					declaration.setInitializer(initializer);
				setStorageSpecifiers(declaration, analysis, scope);
				setAlignmentSpecifiers(declaration, analysis, scope);
				checkFunctionSpecifiers(declaration, analysis);
				definition = declaration;
			}
			definitionList.add(definition);
		}
		return definitionList;
	}

	private TypeNode newSpecifierType(SpecifierAnalysis analysis,
			SimpleScope scope) throws SyntaxException {
		TypeNode result;

		switch (analysis.typeNameKind) {
		case VOID:
			result = nodeFactory
					.newVoidTypeNode(newSource(analysis.typeSpecifierNode));
			break;
		case BASIC:
			result = nodeFactory.newBasicTypeNode(
					newSource(analysis.specifierListNode),
					analysis.basicTypeKind);
			break;
		case TYPEDEF_NAME:
			result = nodeFactory
					.newTypedefNameNode(translateIdentifier((CommonTree) analysis.typeSpecifierNode
							.getChild(0)));
			break;
		case STRUCTURE_OR_UNION:
			result = translateStructOrUnionType(analysis.typeSpecifierNode,
					scope);
			break;
		case ENUMERATION:
			result = translateEnumerationType(analysis.typeSpecifierNode, scope);
			break;
		case ATOMIC:
			result = translateAtomicType(analysis.typeSpecifierNode, scope);
			break;
		case PROCESS:
			result = nodeFactory
					.newProcessTypeNode(newSource(analysis.typeSpecifierNode));
			break;
		default:
			throw new RuntimeException("Should not happen.");
		}
		if (analysis.constQualifier)
			result.setConstQualified(true);
		if (analysis.volatileQualifier)
			result.setVolatileQualified(true);
		if (analysis.restrictQualifier)
			result.setRestrictQualified(true);
		if (analysis.atomicQualifier)
			result.setAtomicQualified(true);
		if (analysis.inputQualifier)
			result.setInputQualified(true);
		if (analysis.outputQualifier)
			result.setOutputQualified(true);
		return result;
	}

	/**
	 * 
	 * @param structTree
	 *            CommonTree of type STRUCT or UNION
	 * @return
	 * @throws SyntaxException
	 */
	private StructureOrUnionTypeNode translateStructOrUnionType(
			CommonTree structTree, SimpleScope scope) throws SyntaxException {
		int kind = structTree.getType();
		boolean isStruct = kind == STRUCT;
		CommonTree tagTree = (CommonTree) structTree.getChild(0);
		CommonTree declListTree = (CommonTree) structTree.getChild(1);
		IdentifierNode tag;
		SequenceNode<FieldDeclarationNode> structDeclList;

		if (tagTree.getType() == ABSENT) {
			tag = null;
		} else {
			tag = translateIdentifier(tagTree);
		}
		if (declListTree.getType() == ABSENT) {
			structDeclList = null;
		} else {
			int numFields = declListTree.getChildCount();
			List<FieldDeclarationNode> fieldDecls = new LinkedList<FieldDeclarationNode>();

			for (int i = 0; i < numFields; i++) {
				CommonTree declTree = (CommonTree) declListTree.getChild(i);
				List<FieldDeclarationNode> fieldDeclarations = translateFieldDeclaration(
						declTree, scope);

				fieldDecls.addAll(fieldDeclarations);
			}
			structDeclList = nodeFactory.newSequenceNode(
					newSource(declListTree), "FieldDeclarations", fieldDecls);
		}
		return nodeFactory.newStructOrUnionTypeNode(newSource(structTree),
				isStruct, tag, structDeclList);
	}

	private List<FieldDeclarationNode> translateFieldDeclaration(
			CommonTree declarationTree, SimpleScope scope)
			throws SyntaxException {
		CommonTree declarationSpecifiers = (CommonTree) declarationTree
				.getChild(0); // may be ABSENT
		CommonTree structDeclaratorList = (CommonTree) declarationTree
				.getChild(1); // may be ABSENT
		SpecifierAnalysis analysis = newSpecifierAnalysis(declarationSpecifiers);
		TypeNode baseType = newSpecifierType(analysis, scope);
		int numDeclarators = structDeclaratorList.getChildCount();
		List<FieldDeclarationNode> result = new LinkedList<FieldDeclarationNode>();
		Source source = newSource(declarationTree);

		if (numDeclarators == 0) {
			// this can happen if the specifier is an anonymous struct or union
			result.add(nodeFactory.newFieldDeclarationNode(source, null,
					baseType, null));
		} else {
			for (int i = 0; i < numDeclarators; i++) {
				CommonTree structDeclarator = (CommonTree) structDeclaratorList
						.getChild(i);
				CommonTree declaratorTree = (CommonTree) structDeclarator
						.getChild(0); // could be ABSENT
				CommonTree bitFieldTree = (CommonTree) structDeclarator
						.getChild(1); // could be ABSENT
				ExpressionNode bitFieldWidth = translateExpression(
						bitFieldTree, scope);
				DeclaratorData data = processDeclarator(declaratorTree,
						baseType, scope);
				FieldDeclarationNode declaration;

				if (bitFieldWidth == null)
					declaration = nodeFactory.newFieldDeclarationNode(source,
							data.identifier, data.type);
				else
					declaration = nodeFactory.newFieldDeclarationNode(source,
							data.identifier, data.type, bitFieldWidth);

				result.add(declaration);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param enumerationTree
	 * @return
	 * @throws SyntaxException
	 */
	private EnumerationTypeNode translateEnumerationType(
			CommonTree enumerationTree, SimpleScope scope)
			throws SyntaxException {
		// tagTree may be ABSENT:
		CommonTree tagTree = (CommonTree) enumerationTree.getChild(0);
		// enumeratorListTree may be ABSENT:
		CommonTree enumeratorListTree = (CommonTree) enumerationTree
				.getChild(1);
		IdentifierNode tag;
		SequenceNode<EnumeratorDeclarationNode> enumerators;

		if (tagTree.getType() == ABSENT)
			tag = null;
		else
			tag = translateIdentifier(tagTree);
		if (enumeratorListTree.getType() == ABSENT) {
			enumerators = null;
		} else {
			int numEnumerators = enumeratorListTree.getChildCount();
			List<EnumeratorDeclarationNode> enumeratorList = new LinkedList<EnumeratorDeclarationNode>();

			for (int i = 0; i < numEnumerators; i++) {
				CommonTree enumeratorTree = (CommonTree) enumeratorListTree
						.getChild(i);
				CommonTree enumeratorNameTree = (CommonTree) enumeratorTree
						.getChild(0);
				IdentifierNode enumeratorName = translateIdentifier(enumeratorNameTree);
				CommonTree constantTree = (CommonTree) enumeratorTree
						.getChild(1);
				ExpressionNode constant = translateExpression(constantTree,
						scope);
				EnumeratorDeclarationNode decl = nodeFactory
						.newEnumeratorDeclarationNode(
								newSource(enumeratorTree), enumeratorName,
								constant);

				enumeratorList.add(decl);
			}
			enumerators = nodeFactory.newSequenceNode(
					newSource(enumeratorListTree), "EnumeratorList",
					enumeratorList);
		}
		return nodeFactory.newEnumerationTypeNode(newSource(enumerationTree),
				tag, enumerators);
	}

	/**
	 * 
	 * @param atomicTree
	 * @return
	 * @throws SyntaxException
	 */
	private AtomicTypeNode translateAtomicType(CommonTree atomicTree,
			SimpleScope scope) throws SyntaxException {
		TypeNode type = translateTypeName((CommonTree) atomicTree.getChild(0),
				scope);

		return nodeFactory.newAtomicTypeNode(newSource(atomicTree), type);
	}

	/**
	 * 
	 * @param declaration
	 * @param analysis
	 */
	private void setFunctionSpecifiers(FunctionDeclarationNode declaration,
			SpecifierAnalysis analysis) {
		if (analysis.inlineSpecifier)
			declaration.setInlineFunctionSpecifier(true);
		if (analysis.noreturnSpecifier)
			declaration.setNoreturnFunctionSpecifier(true);
	}

	private void checkFunctionSpecifiers(VariableDeclarationNode declaration,
			SpecifierAnalysis analysis) throws SyntaxException {
		if (analysis.inlineSpecifier)
			throw error("Use of inline specifier in object delcaration",
					declaration);
		if (analysis.noreturnSpecifier)
			throw error("Use of _Noreturn specifier in object delcaration",
					declaration);
	}

	/**
	 * 
	 * @param declaration
	 * @param analysis
	 * @throws SyntaxException
	 */
	private void setAlignmentSpecifiers(VariableDeclarationNode declaration,
			SpecifierAnalysis analysis, SimpleScope scope)
			throws SyntaxException {
		if (!analysis.alignmentTypeNodes.isEmpty()) {
			List<TypeNode> typeAlignmentSpecifiers = new ArrayList<TypeNode>();

			for (CommonTree node : analysis.alignmentTypeNodes)
				typeAlignmentSpecifiers.add(translateTypeName(node, scope));
			declaration.setTypeAlignmentSpecifiers(nodeFactory.newSequenceNode(
					newSource(analysis.specifierListNode),
					"TypeAlignmentSpecifiers", typeAlignmentSpecifiers));
		}
		if (!analysis.alignmentExpressionNodes.isEmpty()) {
			List<ExpressionNode> constantAlignmentSpecifiers = new ArrayList<ExpressionNode>();

			for (CommonTree node : analysis.alignmentExpressionNodes)
				constantAlignmentSpecifiers
						.add(translateExpression(node, scope));

			declaration.setConstantAlignmentSpecifiers(nodeFactory
					.newSequenceNode(newSource(analysis.specifierListNode),
							"ConstantAlignmentSpecifiers",
							constantAlignmentSpecifiers));
		}
	}

	private void checkAlignmentSpecifiers(FunctionDeclarationNode declaration,
			SpecifierAnalysis analysis) throws SyntaxException {
		if (!analysis.alignmentTypeNodes.isEmpty()
				|| !analysis.alignmentExpressionNodes.isEmpty())
			throw error("Use of alignment specifiers in function declaration",
					declaration);
	}

	/**
	 * 
	 * @param declaration
	 * @param analysis
	 */
	private void setStorageSpecifiers(VariableDeclarationNode declaration,
			SpecifierAnalysis analysis, SimpleScope scope) {

		if (analysis.externCount > 0)
			declaration.setExternStorage(true);
		if (analysis.staticCount > 0)
			declaration.setStaticStorage(true);
		if (analysis.threadLocalCount > 0)
			declaration.setThreadLocalStorage(true);
		if (analysis.autoCount > 0)
			declaration.setAutoStorage(true);
		if (analysis.registerCount > 0)
			declaration.setRegisterStorage(true);
	}

	private void setStorageSpecifiers(FunctionDeclarationNode declaration,
			SpecifierAnalysis analysis, SimpleScope scope)
			throws SyntaxException {

		if (analysis.externCount > 0)
			declaration.setExternStorage(true);
		if (analysis.staticCount > 0)
			declaration.setStaticStorage(true);
		if (analysis.threadLocalCount > 0)
			throw new SyntaxException(
					"Use of _Thread_local in function declaration",
					declaration.getSource());
		if (analysis.autoCount > 0)
			throw new SyntaxException("Use of auto in function declaration",
					declaration.getSource());
		if (analysis.registerCount > 0)
			throw new SyntaxException(
					"Use of register in function declaration",
					declaration.getSource());
	}

	/**
	 * Creates new DeclaratorData based on given declarator tree node and base
	 * type. The declarator may be abstract. The data gives the new type formed
	 * by applying the type derivation operations of the declarator to the base
	 * type. The data also gives the identifier being declared, though this may
	 * be null in the case of an abstract declarator.
	 * 
	 * @param declarator
	 *            CommonTree node of type DECLARATOR, ABSTRACT_DECLARATOR, or
	 *            ABSENT
	 * @param type
	 *            the start type before applying declarator operations
	 * 
	 * @return new DeclaratorData with type derived from given type and
	 *         identifier
	 * 
	 * @throws SyntaxException
	 */
	private DeclaratorData processDeclarator(CommonTree declarator,
			TypeNode type, SimpleScope scope) throws SyntaxException {
		if (declarator.getType() == ABSENT) {
			return new DeclaratorData(type, null);
		} else {
			CommonTree pointerTree = (CommonTree) declarator.getChild(0);
			CommonTree directDeclarator = (CommonTree) declarator.getChild(1);
			type = translatePointers(pointerTree, type, scope);

			return processDirectDeclarator(directDeclarator, type, scope);
		}
	}

	/**
	 * Creates a new DeclaratorData based on given direct declarator tree node
	 * and base type. The direct declarator may be abstract.
	 * 
	 * @param directDeclarator
	 *            CommonTree node of type DIRECT_DECLARATOR,
	 *            DIRECT_ABSTRACT_DECLARATOR, or ABSENT
	 * @param type
	 *            base type
	 * @return new DeclaratorData with derived type and identifier
	 * @throws SyntaxException
	 */
	private DeclaratorData processDirectDeclarator(CommonTree directDeclarator,
			TypeNode type, SimpleScope scope) throws SyntaxException {
		if (directDeclarator.getType() == ABSENT) {
			return new DeclaratorData(type, null);
		} else {
			int numChildren = directDeclarator.getChildCount();
			CommonTree prefix = (CommonTree) directDeclarator.getChild(0);

			// need to peel off right-most suffix first. Example:
			// T prefix [](); : (array of function returning T) prefix;
			for (int i = numChildren - 1; i >= 1; i--)
				type = translateDeclaratorSuffix(
						(CommonTree) directDeclarator.getChild(i), type, scope);
			switch (prefix.getType()) {
			case IDENTIFIER:
				return new DeclaratorData(type, translateIdentifier(prefix));
			case DECLARATOR:
			case ABSTRACT_DECLARATOR:
				return processDeclarator(prefix, type, scope);
			case ABSENT:
				return new DeclaratorData(type, null);
			default:
				throw error("Unexpected node for direct declarator prefix",
						prefix);
			}
		}
	}

	/**
	 * 
	 * @param initializerTree
	 * @return
	 * @throws SyntaxException
	 */
	private InitializerNode translateInitializer(CommonTree initializerTree,
			SimpleScope scope) throws SyntaxException {
		int kind = initializerTree.getType();

		if (kind == ABSENT)
			return null;
		if (kind == SCALAR_INITIALIZER) {
			return translateExpression(
					(CommonTree) initializerTree.getChild(0), scope);
		}
		if (kind == INITIALIZER_LIST) {
			int numInits = initializerTree.getChildCount();
			List<PairNode<DesignationNode, InitializerNode>> initList = new LinkedList<PairNode<DesignationNode, InitializerNode>>();

			for (int i = 0; i < numInits; i++) {
				CommonTree designatedInitializer = (CommonTree) initializerTree
						.getChild(i);
				CommonTree designation = (CommonTree) designatedInitializer
						.getChild(0);
				CommonTree initializer = (CommonTree) designatedInitializer
						.getChild(1);
				InitializerNode initializerNode = translateInitializer(
						initializer, scope);
				DesignationNode designationNode;

				if (designation.getType() == ABSENT) {
					designationNode = null;
				} else {
					int numDesignators = designation.getChildCount();
					List<DesignatorNode> designators = new LinkedList<DesignatorNode>();

					for (int j = 0; j < numDesignators; j++) {
						CommonTree designator = (CommonTree) designation
								.getChild(j);
						CommonTree designatorChild = (CommonTree) designator
								.getChild(0);
						int designatorKind = designator.getType();
						DesignatorNode designatorNode;
						Source designatorSource = newSource(designator);

						if (designatorKind == ARRAY_ELEMENT_DESIGNATOR) {
							designatorNode = nodeFactory
									.newArrayDesignatorNode(
											designatorSource,
											translateExpression(
													designatorChild, scope));
						} else if (designatorKind == FIELD_DESIGNATOR) {
							designatorNode = nodeFactory
									.newFieldDesignatorNode(
											designatorSource,
											translateIdentifier(designatorChild));
						} else {
							throw error("Unknown kind of designator",
									designator);
						}
						designators.add(designatorNode);
					}
					designationNode = nodeFactory.newDesignationNode(
							newSource(designation), designators);
				}
				initList.add(nodeFactory.newPairNode(
						newSource(designatedInitializer), designationNode,
						initializerNode));
			}
			return nodeFactory.newCompoundInitializerNode(
					newSource(initializerTree), initList);
		} else {
			throw error("Unrecognized kind of initializer", initializerTree);
		}
	}

	/**
	 * Returns the new type obtained by taking the given type and applying the
	 * pointer operations to it. For example, if the old type is "int" and the
	 * pointerTree is "*", the result is the type "pointer to int".
	 * 
	 * @param pointerTree
	 *            CommonTree node of type POINTER or ABSENT
	 * @param type
	 *            base type
	 * @return modified type
	 * @throws SyntaxException
	 *             if an unknown kind of type qualifier appears
	 */
	private TypeNode translatePointers(CommonTree pointerTree, TypeNode type,
			SimpleScope scope) throws SyntaxException {
		int numChildren = pointerTree.getChildCount();
		Source source = type.getSource();

		for (int i = 0; i < numChildren; i++) {
			CommonTree starNode = (CommonTree) pointerTree.getChild(i);
			CommonTree qualifiers = (CommonTree) starNode.getChild(0);

			source = sourceFactory.join(source, newSource(pointerTree));
			type = nodeFactory.newPointerTypeNode(source, type);
			applyQualifiers(qualifiers, type);
		}
		return type;
	}

	/**
	 * Given a base type and a declarator suffix, returns the new derived type.
	 * Example: base type is "int", suffix is "[10]", returns the type
	 * "array of int of length 10".
	 * 
	 * @param suffix
	 *            a CommonTree node of type ARRAY_SUFFIX or FUNCTION_SUFFIX
	 * @param type
	 * @return new type
	 * @throws SyntaxException
	 *             if the kind of suffix is not function or array
	 */
	private TypeNode translateDeclaratorSuffix(CommonTree suffix,
			TypeNode baseType, SimpleScope scope) throws SyntaxException {
		int kind = suffix.getType();

		if (kind == ARRAY_SUFFIX)
			return translateArraySuffix(suffix, baseType, scope);
		else if (kind == FUNCTION_SUFFIX)
			return translateFunctionSuffix(suffix, baseType, scope);
		else
			throw error("Unknown declarator suffix", suffix);
	}

	/**
	 * Applies the qualifires in the given qualifier list to the given type.
	 * Modifes the type accordingly.
	 * 
	 * @param qualifierList
	 *            CommonTree node which is root of list of qualifier nodes, or
	 *            ABSENT
	 * @param type
	 *            the type to modify by applying qualifiers
	 * @throws SyntaxException
	 *             if a childe of the qualifierList is not a type qualifier
	 */
	private void applyQualifiers(CommonTree qualifierList, TypeNode type)
			throws SyntaxException {
		int numQualifiers = qualifierList.getChildCount();

		for (int i = 0; i < numQualifiers; i++) {
			CommonTree qualifier = (CommonTree) qualifierList.getChild(i);

			switch (qualifier.getType()) {
			case CONST:
				type.setConstQualified(true);
				break;
			case VOLATILE:
				type.setVolatileQualified(true);
				break;
			case RESTRICT:
				type.setRestrictQualified(true);
				break;
			case ATOMIC:
				type.setAtomicQualified(true);
				break;
			default:
				throw error("Unknown type qualifier", qualifier);
			}
		}
	}

	private void applyArrayQualifiers(CommonTree qualifierList,
			ArrayTypeNode type) throws SyntaxException {
		int numQualifiers = qualifierList.getChildCount();

		for (int i = 0; i < numQualifiers; i++) {
			CommonTree qualifier = (CommonTree) qualifierList.getChild(i);

			switch (qualifier.getType()) {
			case CONST:
				type.setConstInBrackets(true);
				break;
			case VOLATILE:
				type.setVolatileInBrackets(true);
				break;
			case RESTRICT:
				type.setRestrictInBrackets(true);
				break;
			case ATOMIC:
				type.setAtomicInBrackets(true);
				break;
			default:
				throw error("Unknown type qualifier", qualifier);
			}
		}
	}

	/**
	 * 
	 * @param suffix
	 * @param baseType
	 * @return
	 * @throws SyntaxException
	 */
	private ArrayTypeNode translateArraySuffix(CommonTree suffix,
			TypeNode baseType, SimpleScope scope) throws SyntaxException {
		CommonTree staticNode = (CommonTree) suffix.getChild(1);
		CommonTree qualifiers = (CommonTree) suffix.getChild(2);
		CommonTree extentNode = (CommonTree) suffix.getChild(3);
		int extentNodeType = extentNode.getType();
		boolean unspecifiedVariableLength = false;
		ExpressionNode extent = null;
		ArrayTypeNode result;
		Source source = sourceFactory.join(baseType.getSource(),
				newSource(suffix));

		switch (extentNodeType) {
		case ABSENT:
			break;
		case STAR:
			unspecifiedVariableLength = true;
			break;
		default:
			extent = translateExpression(extentNode, scope);
		}
		result = nodeFactory.newArrayTypeNode(source, baseType, extent);
		if (unspecifiedVariableLength)
			result.setUnspecifiedVariableLength(true);
		if (staticNode.getType() == STATIC)
			result.setStaticExtent(true);
		applyArrayQualifiers(qualifiers, result);
		return result;
	}

	/**
	 * 
	 * @param suffix
	 * @param baseType
	 * @return
	 * @throws SyntaxException
	 */
	private FunctionTypeNode translateFunctionSuffix(CommonTree suffix,
			TypeNode baseType, SimpleScope scope) throws SyntaxException {
		CommonTree child = (CommonTree) suffix.getChild(1);
		int childKind = child.getType();
		FunctionTypeNode result;
		Source source = sourceFactory.join(baseType.getSource(),
				newSource(suffix));

		if (!scope.isFunctionScope()) {
			// this is not a function definition.
			// need a "function prototype" scope...
			scope = new SimpleScope(scope);
		}
		if (childKind == PARAMETER_TYPE_LIST) {
			CommonTree parameterListNode = (CommonTree) child.getChild(0);
			CommonTree ellipsisNode = (CommonTree) child.getChild(1);
			int numParameters = parameterListNode.getChildCount();
			List<VariableDeclarationNode> parameterDeclarations = new ArrayList<VariableDeclarationNode>(
					numParameters);

			for (int i = 0; i < numParameters; i++) {
				CommonTree parameterDeclarationNode = (CommonTree) parameterListNode
						.getChild(i);
				Source parameterDeclarationSource = newSource(parameterDeclarationNode);
				CommonTree specifiers = (CommonTree) parameterDeclarationNode
						.getChild(0);
				SpecifierAnalysis analysis = newSpecifierAnalysis(specifiers);
				TypeNode parameterBaseType = newSpecifierType(analysis, scope);
				CommonTree declarator = (CommonTree) parameterDeclarationNode
						.getChild(1);
				int declaratorKind = declarator.getType();
				VariableDeclarationNode declaration;
				// TODO: do adjustments here?

				if (declaratorKind == ABSENT) {
					declaration = nodeFactory
							.newVariableDeclarationNode(
									parameterDeclarationSource, null,
									parameterBaseType);
				} else if (declaratorKind == DECLARATOR
						|| declaratorKind == ABSTRACT_DECLARATOR) {
					DeclaratorData data = processDeclarator(declarator,
							parameterBaseType, scope);

					declaration = nodeFactory.newVariableDeclarationNode(
							parameterDeclarationSource, data.identifier,
							data.type);
				} else {
					throw error("Unknown kind of declarator", declarator);
				}
				// TODO: C11 6.7.6.3(2):
				// "The only storage-class specifier that shall occur in a
				// parameter declaration is register."
				// setFunctionSpecifiers(declaration, analysis);
				setAlignmentSpecifiers(declaration, analysis, scope);
				setStorageSpecifiers(declaration, analysis, scope);
				parameterDeclarations.add(declaration);
			}
			result = nodeFactory.newFunctionTypeNode(source, baseType,
					nodeFactory.newSequenceNode(newSource(parameterListNode),
							"FormalParameterList", parameterDeclarations),
					false);
			if (ellipsisNode.getType() == ELLIPSIS)
				result.setVariableArgs(true);
		} else if (childKind == IDENTIFIER_LIST || childKind == ABSENT) {
			int numParameters = child.getChildCount();
			List<VariableDeclarationNode> parameterDeclarations = new ArrayList<VariableDeclarationNode>(
					numParameters);

			for (int i = 0; i < numParameters; i++) {
				CommonTree identifierNode = (CommonTree) child.getChild(i);
				IdentifierNode identifier = translateIdentifier(identifierNode);
				Source identifierSource = newSource(identifierNode);
				VariableDeclarationNode declaration = nodeFactory
						.newVariableDeclarationNode(identifierSource,
								identifier, null);

				parameterDeclarations.add(declaration);
			}
			result = nodeFactory.newFunctionTypeNode(source, baseType,
					nodeFactory.newSequenceNode(source, "FormalParameterList",
							parameterDeclarations), true);
		} else {
			throw error("Unexpected kind of function suffix", child);
		}
		return result;
	}

	/**
	 * 
	 * @param typeNameTree
	 * @return
	 * @throws SyntaxException
	 */
	private TypeNode translateTypeName(CommonTree typeNameTree,
			SimpleScope scope) throws SyntaxException {
		CommonTree specifiers = (CommonTree) typeNameTree.getChild(0);
		CommonTree abstractDeclarator = (CommonTree) typeNameTree.getChild(1);
		SpecifierAnalysis analysis = newSpecifierAnalysis(specifiers);
		TypeNode baseType = newSpecifierType(analysis, scope);
		DeclaratorData data = processDeclarator(abstractDeclarator, baseType,
				scope);

		return data.type;
	}

	/**
	 * 
	 * @param statementTree
	 * @return
	 * @throws SyntaxException
	 */
	private StatementNode translateStatement(CommonTree statementTree,
			SimpleScope scope) throws SyntaxException {
		int kind = statementTree.getType();

		if (kind == ABSENT)
			return null;

		Source statementSource = newSource(statementTree);

		switch (kind) {
		case IDENTIFIER_LABELED_STATEMENT: {
			IdentifierNode labelName = translateIdentifier((CommonTree) statementTree
					.getChild(0));
			StatementNode statement = translateStatement(
					(CommonTree) statementTree.getChild(1), scope);
			OrdinaryLabelNode labelDecl = nodeFactory
					.newStandardLabelDeclarationNode(labelName.getSource(),
							labelName, statement);

			return nodeFactory.newLabeledStatementNode(statementSource,
					labelDecl, statement);
		}
		case CASE_LABELED_STATEMENT: {
			CToken caseToken = (CToken) ((CommonTree) statementTree.getChild(0))
					.getToken();
			CommonTree expression = (CommonTree) statementTree.getChild(1);
			ExpressionNode expressionNode = translateExpression(expression,
					scope);
			StatementNode statement = translateStatement(
					(CommonTree) statementTree.getChild(2), scope);
			Source expressionSource = newSource(expression);
			Source labelSource = sourceFactory
					.join(expressionSource, caseToken);
			SwitchLabelNode labelDecl = nodeFactory
					.newCaseLabelDeclarationNode(labelSource, expressionNode,
							statement);

			return nodeFactory.newLabeledStatementNode(statementSource,
					labelDecl, statement);
		}
		case DEFAULT_LABELED_STATEMENT: {
			CToken defaultToken = (CToken) ((CommonTree) statementTree
					.getChild(0)).getToken();
			Source labelSource = sourceFactory.newSource(defaultToken);
			StatementNode statement = translateStatement(
					(CommonTree) statementTree.getChild(1), scope);
			SwitchLabelNode labelDecl = nodeFactory
					.newDefaultLabelDeclarationNode(labelSource, statement);

			return nodeFactory.newLabeledStatementNode(statementSource,
					labelDecl, statement);
		}
		case COMPOUND_STATEMENT:
			return translateCompoundStatement(statementTree, scope);
		case EXPRESSION_STATEMENT: {
			CommonTree expression = (CommonTree) statementTree.getChild(0);
			ExpressionNode expressionNode = translateExpression(expression,
					scope);

			if (expressionNode == null)
				return nodeFactory.newNullStatementNode(statementSource);
			else
				return nodeFactory.newExpressionStatementNode(expressionNode);
		}
		case IF: {
			SimpleScope ifScope = new SimpleScope(scope);
			ExpressionNode condition = translateExpression(
					(CommonTree) statementTree.getChild(0), ifScope);
			StatementNode trueBranch = translateStatement(
					(CommonTree) statementTree.getChild(1), new SimpleScope(
							ifScope));
			StatementNode falseBranch = translateStatement(
					(CommonTree) statementTree.getChild(2), new SimpleScope(
							ifScope));

			if (falseBranch == null)
				return nodeFactory.newIfNode(statementSource, condition,
						trueBranch);
			else
				return nodeFactory.newIfNode(statementSource, condition,
						trueBranch, falseBranch);
		}
		case SWITCH: {
			CommonTree expressionTree = (CommonTree) statementTree.getChild(0);
			CommonTree bodyTree = (CommonTree) statementTree.getChild(1);
			SimpleScope switchScope = new SimpleScope(scope);
			SimpleScope bodyScope = new SimpleScope(switchScope);
			ExpressionNode expressionNode = translateExpression(expressionTree,
					switchScope);
			StatementNode statementNode = translateStatement(bodyTree,
					bodyScope);
			SwitchNode switchNode = nodeFactory.newSwitchNode(statementSource,
					expressionNode, statementNode);

			return switchNode;
		}
		case WHILE: {
			SimpleScope loopScope = new SimpleScope(scope);

			return nodeFactory.newWhileLoopNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(0),
							loopScope),
					translateStatement((CommonTree) statementTree.getChild(1),
							new SimpleScope(loopScope)),
					getInvariant((CommonTree) statementTree.getChild(2),
							loopScope));
		}
		case DO: {
			SimpleScope loopScope = new SimpleScope(scope);

			return nodeFactory.newDoLoopNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(1),
							loopScope),
					translateStatement((CommonTree) statementTree.getChild(0),
							new SimpleScope(loopScope)),
					getInvariant((CommonTree) statementTree.getChild(2),
							loopScope));
		}
		case FOR: {
			SimpleScope loopScope = new SimpleScope(scope);
			CommonTree initializerTree = (CommonTree) statementTree.getChild(0);
			ForLoopInitializerNode initializerNode;

			if (initializerTree.getType() == DECLARATION) {
				List<ExternalDefinitionNode> definitions = translateDeclaration(
						initializerTree, loopScope);
				List<VariableDeclarationNode> declarations = new LinkedList<VariableDeclarationNode>();

				for (ExternalDefinitionNode definition : definitions) {
					if (!(definition instanceof VariableDeclarationNode))
						throw error(
								"For-loop initializer declaration "
										+ "\"shall only declare identifiers for objects having storage class auto or register.\"",
								initializerTree);
					declarations.add((VariableDeclarationNode) definition);
				}
				initializerNode = nodeFactory.newForLoopInitializerNode(
						statementSource, declarations);
			} else {
				initializerNode = translateExpression(initializerTree,
						loopScope);
			}
			return nodeFactory.newForLoopNode(
					statementSource,
					initializerNode,
					translateExpression((CommonTree) statementTree.getChild(1),
							loopScope),
					translateExpression((CommonTree) statementTree.getChild(2),
							loopScope),
					translateStatement((CommonTree) statementTree.getChild(3),
							new SimpleScope(loopScope)),
					getInvariant((CommonTree) statementTree.getChild(4),
							loopScope));
		}
		case GOTO:
			return nodeFactory
					.newGotoNode(statementSource,
							translateIdentifier((CommonTree) statementTree
									.getChild(0)));
		case CONTINUE:
			return nodeFactory.newContinueNode(statementSource);
		case BREAK:
			return nodeFactory.newBreakNode(statementSource);
		case RETURN:
			return nodeFactory.newReturnNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(0),
							scope));
		case PRAGMA:
			return translatePragma(statementSource, statementTree, scope);
		case WAIT:
			return nodeFactory.newWaitNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(0),
							scope));
		case ASSERT:
			return nodeFactory.newAssertNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(0),
							scope));

		case ASSUME:
			return nodeFactory.newAssumeNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(0),
							scope));
		case WHEN:
			return nodeFactory.newWhenNode(
					statementSource,
					translateExpression((CommonTree) statementTree.getChild(0),
							scope),
					translateStatement((CommonTree) statementTree.getChild(1),
							scope));
		case CHOOSE:
			return translateChooseStatement(statementTree, scope);
		default:
			throw error("Unknown statement type", statementTree);
		}
	}

	private ExpressionNode getInvariant(CommonTree invariantTree,
			SimpleScope scope) throws SyntaxException {
		if (invariantTree == null)
			return null;
		if (invariantTree.getType() == ABSENT)
			return null;
		else {
			CommonTree exprTree = (CommonTree) invariantTree.getChild(0);

			return translateExpression(exprTree, scope);
		}
	}

	private PragmaNode translatePragma(Source source, CommonTree pragmaTree,
			SimpleScope scope) {
		CommonTree identifierTree = (CommonTree) pragmaTree.getChild(0);
		IdentifierNode identifier = translateIdentifier(identifierTree);
		CommonTree bodyTree = (CommonTree) pragmaTree.getChild(1);
		CommonTree newlineTree = (CommonTree) pragmaTree.getChild(2);
		int numTokens = bodyTree.getChildCount();
		List<CToken> body = new LinkedList<CToken>();
		CToken newlineToken = (CToken) newlineTree.getToken();

		for (int i = 0; i < numTokens; i++) {
			CToken token = (CToken) ((CommonTree) bodyTree.getChild(i))
					.getToken();
			body.add(token);
		}
		return nodeFactory
				.newPragmaNode(source, identifier, body, newlineToken);
	}

	/**
	 * 
	 * @param compoundStatementTree
	 * @return
	 * @throws SyntaxException
	 */
	private CompoundStatementNode translateCompoundStatement(
			CommonTree compoundStatementTree, SimpleScope scope)
			throws SyntaxException {
		SimpleScope newScope = new SimpleScope(scope);
		Source source = newSource(compoundStatementTree);
		CommonTree blockItems = (CommonTree) compoundStatementTree.getChild(1);
		int numChildren = blockItems.getChildCount();
		List<BlockItemNode> items = new LinkedList<BlockItemNode>();

		for (int i = 0; i < numChildren; i++) {
			CommonTree childTree = (CommonTree) blockItems.getChild(i);
			int kind = childTree.getType();

			if (kind == DECLARATION) {
				for (ExternalDefinitionNode declaration : translateDeclaration(
						childTree, newScope))
					items.add((BlockItemNode) declaration);
			} else if (kind == STATICASSERT) {
				items.add(translateStaticAssertion(childTree, newScope));
			} else if (kind == FUNCTION_DEFINITION) {
				items.add(translateFunctionDefinition(childTree, newScope));
			} else {
				items.add(translateStatement(childTree, newScope));
			}
		}
		return nodeFactory.newCompoundStatementNode(source, items);
	}

	private ChooseStatementNode translateChooseStatement(
			CommonTree chooseStatementTree, SimpleScope scope)
			throws SyntaxException {
		int numChildren = chooseStatementTree.getChildCount();
		List<StatementNode> statements = new LinkedList<StatementNode>();

		for (int i = 0; i < numChildren; i++) {
			CommonTree statementTree = (CommonTree) chooseStatementTree
					.getChild(i);
			StatementNode statement = translateStatement(statementTree, scope);

			statements.add(statement);
		}
		return nodeFactory.newChooseStatementNode(
				newSource(chooseStatementTree), statements);
	}

	/**
	 * 
	 * @param staticAssertTree
	 * @return
	 * @throws SyntaxException
	 */
	private StaticAssertionNode translateStaticAssertion(
			CommonTree staticAssertTree, SimpleScope scope)
			throws SyntaxException {
		CommonTree stringLiteral = (CommonTree) staticAssertTree.getChild(1);
		Source stringLiteralSource = newSource(stringLiteral);

		return nodeFactory.newStaticAssertionNode(
				newSource(staticAssertTree),
				translateExpression((CommonTree) staticAssertTree.getChild(0),
						scope),
				translateStringLiteral(stringLiteralSource,
						(CommonTree) staticAssertTree.getChild(1)));
	}

	/**
	 * 
	 * @param functionDefinitionTree
	 * @return
	 * @throws SyntaxException
	 */
	private FunctionDefinitionNode translateFunctionDefinition(
			CommonTree functionDefinitionTree, SimpleScope scope)
			throws SyntaxException {
		// two different ways of declaring parameters:
		// (1) parameter-type list and no declarations
		// (2) identifier list and declarations
		SimpleScope newScope = new SimpleScope(scope, true);
		CommonTree specifiers = (CommonTree) functionDefinitionTree.getChild(0);
		CommonTree declarator = (CommonTree) functionDefinitionTree.getChild(1);
		CommonTree declarationList = (CommonTree) functionDefinitionTree
				.getChild(2);
		CommonTree compoundStatementTree = (CommonTree) functionDefinitionTree
				.getChild(3);
		CommonTree contractTree = (CommonTree) functionDefinitionTree
				.getChild(4);
		SpecifierAnalysis analysis = newSpecifierAnalysis(specifiers);
		TypeNode baseType = newSpecifierType(analysis, newScope);
		DeclaratorData data = processDeclarator(declarator, baseType, newScope);
		FunctionTypeNode functionType = (FunctionTypeNode) data.type;
		CompoundStatementNode body;
		FunctionDefinitionNode result;

		if (functionType.hasIdentifierList()) {
			SequenceNode<VariableDeclarationNode> formalSequenceNode = functionType
					.getParameters();
			int numFormals = formalSequenceNode.numChildren();
			int numDeclarations = declarationList.getChildCount();

			if (numFormals == 0) {
				if (numDeclarations != 0)
					throw error(
							"Function with empty identifier list has parameter declarations",
							declarationList);
			} else {
				SequenceNode<VariableDeclarationNode> newFormalSequenceNode;
				List<VariableDeclarationNode> newFormalList = new LinkedList<VariableDeclarationNode>();
				Iterator<VariableDeclarationNode> formals = formalSequenceNode
						.childIterator();
				Map<String, VariableDeclarationNode> declMap = new HashMap<String, VariableDeclarationNode>();

				for (int i = 0; i < numDeclarations; i++) {
					CommonTree declarationTree = (CommonTree) declarationList
							.getChild(i);
					List<ExternalDefinitionNode> declNodes = translateDeclaration(
							declarationTree, newScope);

					for (ExternalDefinitionNode definition : declNodes) {
						String parameterName;
						VariableDeclarationNode oldDeclaration;

						if (!(definition instanceof VariableDeclarationNode))
							throw error("Illegal parameter declaration",
									declarationTree);
						parameterName = ((VariableDeclarationNode) definition)
								.getIdentifier().name();
						if (parameterName == null)
							throw error("Illegal parameter declaration",
									declarationTree);
						oldDeclaration = declMap.get(parameterName);
						if (oldDeclaration != null)
							throw error(
									"Re-declaration of parameter. Old declaration was at "
											+ oldDeclaration, declarationTree);
						declMap.put(parameterName,
								(VariableDeclarationNode) definition);
					}
				}
				while (formals.hasNext()) {
					VariableDeclarationNode formal = formals.next();
					String parameterName = formal.getIdentifier().name();
					VariableDeclarationNode newDeclaration;

					if (parameterName == null)
						throw error(
								"Formal parameter declaration missing name: "
										+ formal, declarator);
					newDeclaration = declMap.get(parameterName);
					if (newDeclaration == null)
						throw error("Missing declaration for parameter "
								+ parameterName, declarationList);
					newFormalList.add(newDeclaration);
					declMap.remove(parameterName);
				}
				if (!declMap.isEmpty())
					throw error(
							"Function contains declarations for variables that are not parameters",
							declarationList);
				newFormalSequenceNode = nodeFactory.newSequenceNode(
						newSource(declarationList),
						"FormalParameterDeclarations", newFormalList);
				functionType.setParameters(newFormalSequenceNode);
			}
		}
		body = translateCompoundStatement(compoundStatementTree, newScope);
		result = nodeFactory.newFunctionDefinitionNode(
				newSource(functionDefinitionTree), data.identifier,
				functionType, getContract(contractTree, newScope), body);
		return result;
	}

	private SequenceNode<ContractNode> getContract(CommonTree contractTree,
			SimpleScope scope) throws SyntaxException {
		SequenceNode<ContractNode> contract;

		if (contractTree == null)
			contract = null;
		else {
			int kind = contractTree.getType();

			if (kind == ABSENT)
				contract = null;
			else {
				int numItems = contractTree.getChildCount();
				List<ContractNode> items = new LinkedList<ContractNode>();

				if (numItems == 0) {
					contract = null;
				} else {
					for (int i = 0; i < numItems; i++) {
						CommonTree itemTree = (CommonTree) contractTree
								.getChild(i);
						int itemKind = itemTree.getType();
						CommonTree exprTree = (CommonTree) itemTree.getChild(0);
						ExpressionNode expr = translateExpression(exprTree,
								scope);
						ContractNode contractNode;
						Source source = newSource(itemTree);

						if (itemKind == ENSURES) {
							contractNode = nodeFactory.newEnsuresNode(source,
									expr);
						} else if (itemKind == REQUIRES) {
							contractNode = nodeFactory.newRequiresNode(source,
									expr);
						} else {
							throw error("Unknown kind of contract item: "
									+ itemTree, itemTree);
						}
						items.add(contractNode);
					}
					contract = nodeFactory.newSequenceNode(
							newSource(contractTree), "Contract", items);
				}
			}
		}
		return contract;
	}

	/**
	 * 
	 * @param translationUnit
	 * @return
	 * @throws SyntaxException
	 */
	private ASTNode translateTranslationUnit(CommonTree translationUnit)
			throws SyntaxException {
		int numChildren = translationUnit.getChildCount();
		ArrayList<ExternalDefinitionNode> definitions = new ArrayList<ExternalDefinitionNode>();
		SimpleScope scope = new SimpleScope(null);

		if (numChildren == 0) {
			throw error("Translation unit contains no definitions",
					translationUnit);
		}
		for (int i = 0; i < numChildren; i++) {
			CommonTree definitionTree = (CommonTree) translationUnit
					.getChild(i);
			int definitionType = definitionTree.getType();

			if (definitionType == DECLARATION)
				definitions.addAll(translateDeclaration(definitionTree, scope));
			else if (definitionType == FUNCTION_DEFINITION)
				definitions.add(translateFunctionDefinition(definitionTree,
						scope));
			else if (definitionType == STATICASSERT)
				definitions
						.add(translateStaticAssertion(definitionTree, scope));
			else if (definitionType == PRAGMA)
				definitions.add(translatePragma(newSource(definitionTree),
						definitionTree, scope));
			else
				throw error("Unknown type of external definition",
						definitionTree);
		}
		return nodeFactory.newTranslationUnitNode(newSource(translationUnit),
				definitions);
	}

}

/**
 * 
 * @author siegel
 * 
 */
class DeclaratorData {
	TypeNode type;
	IdentifierNode identifier;

	DeclaratorData(TypeNode type, IdentifierNode identifier) {
		this.type = type;
		this.identifier = identifier;
	}
}
