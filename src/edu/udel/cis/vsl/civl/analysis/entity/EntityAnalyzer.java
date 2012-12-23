package edu.udel.cis.vsl.civl.analysis.entity;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.analysis.IF.Analyzer;
import edu.udel.cis.vsl.civl.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.civl.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.Variable;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.civl.ast.node.IF.StaticAssertionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.EnumerationTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.civl.config.IF.Configuration;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

/**
 * Performs standard analysis of a translation unit, creating the following
 * information which is attached to the AST:
 * 
 * <ul>
 * <li>entities: an entity is any thing that can be represented by an
 * identifier. An IdentifierNode has a method to get and set the Entity
 * associated to the identifier. This Analyzer creates the Entity object and
 * sets it in each identifier.</li>
 * <li>types: every TypeNode and ExpressionNode will have an associated Type
 * object associated to it</li>
 * <li>linkage: each entity has a kind of linkage which is determined and set</li>
 * </ul>
 * 
 * @author siegel
 * 
 */
public class EntityAnalyzer implements Analyzer {

	// Exported Fields...

	DeclarationAnalyzer declarationAnalyzer;

	ExpressionAnalyzer expressionAnalyzer;

	StatementAnalyzer statementAnalyzer;

	TypeAnalyzer typeAnalyzer;

	EntityFactory entityFactory;

	TypeFactory typeFactory;

	NodeFactory nodeFactory;

	ValueFactory valueFactory;

	StandardTypes standardTypes;

	// Private fields...

	private Configuration configuration;

	private TokenFactory sourceFactory;

	// Constructors...

	public EntityAnalyzer(EntityFactory entityFactory, NodeFactory nodeFactory,
			TokenFactory sourceFactory, ConversionFactory conversionFactory) {
		this.nodeFactory = nodeFactory;
		this.typeFactory = nodeFactory.getTypeFactory();
		this.valueFactory = nodeFactory.getValueFactory();
		this.sourceFactory = sourceFactory;
		this.entityFactory = entityFactory;
		this.standardTypes = new StandardTypes(entityFactory, typeFactory);
		this.declarationAnalyzer = new DeclarationAnalyzer(this);
		declarationAnalyzer.setIgnoredTypes(standardTypes
				.getStandardTypeNames());
		this.expressionAnalyzer = new ExpressionAnalyzer(this,
				conversionFactory, typeFactory);
		this.statementAnalyzer = new StatementAnalyzer(this, expressionAnalyzer);
		this.typeAnalyzer = new TypeAnalyzer(this, typeFactory, entityFactory);
	}

	// Public methods...

	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void analyze(TranslationUnit unit) throws SyntaxException {
		ASTNode root = unit.getRootNode();
		Iterator<ASTNode> children = root.children();

		try {
			standardTypes.addToScope(root.getScope());
		} catch (UnsourcedException e) {
			throw error(e, root);
		}
		while (children.hasNext()) {
			processExternalDefinitions((ExternalDefinitionNode) children.next());
		}
		findTentativeDefinitions(root.getScope());
	}

	// Package private methods...

	SyntaxException error(String message, ASTNode node) {
		return sourceFactory.newSyntaxException(message, node.getSource());
	}

	SyntaxException error(UnsourcedException e, ASTNode node) {
		return new SyntaxException(e, node.getSource());
	}

	Value valueOf(ExpressionNode expression) throws SyntaxException {
		return nodeFactory.getConstantValue(expression);
	}

	void processStaticAssertion(StaticAssertionNode node)
			throws SyntaxException {
		ExpressionNode expression = node.getExpression();
		Value value;

		value = valueOf(expression);
		if (value == null)
			throw error("Expression in static assertion not constant",
					expression);
		switch (valueFactory.isZero(value)) {
		case YES:
			throw error("Static assertion violation: "
					+ node.getMessage().getConstantValue(), node);
		case MAYBE:
			throw error("Possible static assertion violation: "
					+ node.getMessage().getConstantValue(), node);
		default:
		}
	}

	// Private methods...

	/**
	 * Process an ExternalDefinitionNode.
	 * 
	 */
	private void processExternalDefinitions(ExternalDefinitionNode node)
			throws SyntaxException {
		if (node instanceof VariableDeclarationNode) {
			declarationAnalyzer
					.processVariableDeclaration((VariableDeclarationNode) node);
		} else if (node instanceof FunctionDeclarationNode) {
			declarationAnalyzer
					.processFunctionDeclaration((FunctionDeclarationNode) node);
		} else if (node instanceof TypedefDeclarationNode) {
			declarationAnalyzer
					.processTypedefDeclaration((TypedefDeclarationNode) node);
		} else if (node instanceof PragmaNode) {
			processPragma((PragmaNode) node);
		} else if (node instanceof StaticAssertionNode) {
			processStaticAssertion((StaticAssertionNode) node);
		} else if (node instanceof StructureOrUnionTypeNode) {
			typeAnalyzer
					.processStructureOrUnionType((StructureOrUnionTypeNode) node);
		} else if (node instanceof EnumerationTypeNode) {
			typeAnalyzer.processEnumerationType((EnumerationTypeNode) node);
		} else {
			throw new RuntimeException("Unreachable");
		}
	}

	private void processPragma(PragmaNode node) throws SyntaxException {
		// TODO: insert pragma handlers?
	}

	/**
	 * For objects that don't have definitions, see if they have a tentative
	 * definition. Choose the first one and make it the definition. From C11
	 * Sec. 6.9.2:
	 * 
	 * <blockquote> A declaration of an identifier for an object that has file
	 * scope without an initializer, and without a storage-class specifier or
	 * with the storage-class specifier static, constitutes a tentative
	 * definition. If a translation unit contains one or more tentative
	 * definitions for an identifier, and the translation unit contains no
	 * external definition for that identifier, then the behavior is exactly as
	 * if the translation unit contains a file scope declaration of that
	 * identifier, with the composite type as of the end of the translation
	 * unit, with an initializer equal to 0. </blockquote>
	 * 
	 * @param scope
	 */
	private void findTentativeDefinitions(Scope scope) {
		if (scope.getScopeKind() != ScopeKind.FILE)
			throw new IllegalArgumentException(
					"Tentative definition only exist at file scope");

		Iterator<Variable> variableIter = scope.getVariables();

		while (variableIter.hasNext()) {
			Variable variable = variableIter.next();
			VariableDeclarationNode declaration = variable.getDefinition();

			if (declaration == null) {
				Iterator<DeclarationNode> declIter = variable.getDeclarations();

				while (declIter.hasNext()) {
					declaration = (VariableDeclarationNode) declIter.next();

					if (declaration.getInitializer() == null
							&& !(declaration.hasAutoStorage()
									|| declaration.hasRegisterStorage()
									|| declaration.hasThreadLocalStorage() || declaration
										.hasExternStorage())) {
						variable.setDefinition(declaration);
						declaration.setIsDefinition(true);
						break;
					}
				}
			}
		}
	}
}
