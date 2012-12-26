package edu.udel.cis.vsl.civl.analysis.entity;

import java.util.Collection;
import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity;
import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.LinkageKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.entity.IF.Label;
import edu.udel.cis.vsl.civl.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.civl.ast.entity.IF.Variable;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.PairNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ArrayDesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.CompoundInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnsuresNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.OrdinaryDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.RequiresNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.GotoNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

public class DeclarationAnalyzer {

	// ***************************** Fields *******************************

	private EntityAnalyzer entityAnalyzer;

	/**
	 * Typedefs which name types in this set will be ignored in file scope.
	 */
	private Collection<String> ignoredTypes = null;

	// ************************** Constructors ****************************

	DeclarationAnalyzer(EntityAnalyzer entityAnalyzer) {
		this.entityAnalyzer = entityAnalyzer;
	}

	// ************************* Exported Methods *************************

	void setIgnoredTypes(Collection<String> ignoredTypes) {
		this.ignoredTypes = ignoredTypes;
	}

	void processTypedefDeclaration(TypedefDeclarationNode node)
			throws SyntaxException {
		String name = node.getIdentifier().name();
		Scope scope = node.getScope();

		if (scope.getScopeKind() == ScopeKind.FILE && ignoredTypes != null
				&& ignoredTypes.contains(name))
			return;
		else {
			TypeNode typeNode = ((TypedefDeclarationNode) node).getTypeNode();
			Type type = entityAnalyzer.typeAnalyzer.processTypeNode(typeNode);
			OrdinaryEntity entity = scope.getOrdinaryEntity(name);
			Typedef typedef;

			if (entity != null) {
				Type oldType;

				if (!(entity instanceof Typedef))
					throw entityAnalyzer.error("Typedef name already used at "
							+ entity.getDefinition().getSource(), node);
				typedef = (Typedef) entity;
				oldType = typedef.getType();
				if (!type.equals(oldType))
					throw entityAnalyzer
							.error("Redefiniton of typedef name with different type.  "
									+ "Original definition was at "
									+ typedef.getDefinition().getSource(), node);
			} else {
				typedef = entityAnalyzer.entityFactory.newTypedef(name, type);
				try {
					scope.add(typedef);
				} catch (UnsourcedException e) {
					throw entityAnalyzer.error(e, node);
				}
				typedef.setDefinition((TypedefDeclarationNode) node);
			}
			typedef.addDeclaration((TypedefDeclarationNode) node);
			node.setEntity(typedef);
		}
	}

	Variable processVariableDeclaration(VariableDeclarationNode node)
			throws SyntaxException {
		return processVariableDeclaration(node, false);
	}
	
	// TODO: problem is contract uses variables x declared
	// as formal parameters but scope is outside of that scope.
	// function scope: contract, type

	Function processFunctionDeclaration(FunctionDeclarationNode node)
			throws SyntaxException {
		Function result = (Function) processOrdinaryDeclaration(node);
		SequenceNode<ContractNode> contract = node.getContract();

		addDeclarationToFunction(result, node);
		if (node instanceof FunctionDefinitionNode) {
			CompoundStatementNode body = ((FunctionDefinitionNode) node)
					.getBody();

			entityAnalyzer.statementAnalyzer.processCompoundStatement(body);
			processGotos(body);
		}
		if (contract != null) {
			Iterator<ContractNode> contractIter = contract.childIterator();

			while (contractIter.hasNext()) {
				ContractNode clause = contractIter.next();

				if (clause instanceof RequiresNode) {
					ExpressionNode expression = ((RequiresNode) clause)
							.getExpression();

					entityAnalyzer.expressionAnalyzer
							.processExpression(expression);
					result.addPrecondition(expression);
				} else if (clause instanceof EnsuresNode) {
					ExpressionNode expression = ((EnsuresNode) clause)
							.getExpression();

					entityAnalyzer.expressionAnalyzer
							.processExpression(expression);
					result.addPostcondition(expression);
				} else {
					throw error("Unknown kind of contract clause", clause);
				}
			}
		}
		return result;
	}

	Variable processVariableDeclaration(VariableDeclarationNode node,
			boolean isParameter) throws SyntaxException {
		Variable result = (Variable) processOrdinaryDeclaration(node,
				isParameter);
		// when do you process type?
		InitializerNode initializer = node.getInitializer();

		if (result != null) {
			ObjectType type;

			addDeclarationToVariable(result, node);
			type = result.getType();
			if (initializer != null)
				processInitializer(initializer, type);
		}
		return result;
	}

	public void processInitializer(InitializerNode initializer,
			ObjectType currentType) throws SyntaxException {
		assert currentType != null;
		if (initializer instanceof ExpressionNode) {
			ExpressionNode rhs = (ExpressionNode) initializer;

			entityAnalyzer.expressionAnalyzer
					.processExpression((ExpressionNode) initializer);
			try {
				entityAnalyzer.expressionAnalyzer.processAssignment(
						currentType, rhs);
			} catch (UnsourcedException e) {
				throw error(e, initializer);
			}
		} else if (initializer instanceof CompoundInitializerNode) {
			Iterator<PairNode<DesignationNode, InitializerNode>> childIter = ((CompoundInitializerNode) initializer)
					.childIterator();

			while (childIter.hasNext()) {
				PairNode<DesignationNode, InitializerNode> pair = childIter
						.next();
				DesignationNode designation = pair.getLeft();
				InitializerNode subInitializer = pair.getRight();

				// the designation determines a type by starting from
				// the current type and navigating within it.
				// call this the designated type.

				// designation may be null. in that case, the implicit
				// designation is either the first element of the
				// current type or the next element after the previous
				// one.

				// the subInitializer is either an object of the
				// designated type, or an object of some sub-type
				// of the designated type. This is because the
				// curly braces are optional. Look to see if
				// the

				ObjectType subType = processDesignation(designation,
						currentType);

				processInitializer(subInitializer, subType);
			}
		}
	}

	private ObjectType processDesignation(DesignationNode designation,
			ObjectType currentType) throws SyntaxException {
		Iterator<DesignatorNode> childIter = designation.childIterator();

		while (childIter.hasNext()) {
			DesignatorNode designator = childIter.next();

			if (designator instanceof ArrayDesignatorNode) {
				ExpressionNode index = ((ArrayDesignatorNode) designator)
						.getIndex();

				entityAnalyzer.expressionAnalyzer.processExpression(index);
			} else if (designator instanceof FieldDesignatorNode) {
				IdentifierNode identifier = ((FieldDesignatorNode) designator)
						.getField();

				// TODO find the field. What is the structure or union type?

			} else {
				throw new RuntimeException("Unexpected kind of designator: "
						+ designator);
			}
		}
		// TODO TODO!
		return null;

	}

	// ************************* Private Methods **************************

	private SyntaxException error(String message, ASTNode node) {
		return entityAnalyzer.error(message, node);
	}

	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return entityAnalyzer.error(e, node);
	}

	private LinkageKind computeLinkage(OrdinaryDeclarationNode node) {
		boolean isFunction = node instanceof FunctionDeclarationNode;
		IdentifierNode identifier = node.getIdentifier();
		Scope scope = node.getScope();
		boolean isFileScope = scope.getScopeKind() == ScopeKind.FILE;
		String name;
		boolean hasNoStorageClass;

		if (identifier == null)
			return LinkageKind.NONE;
		name = identifier.name();
		if (isFileScope && node.hasStaticStorage()) {
			return LinkageKind.INTERNAL;
		}
		hasNoStorageClass = hasNoStorageClass(node);
		if (node.hasExternStorage() || (isFunction && hasNoStorageClass)) {
			Entity previous = scope.getLexicalOrdinaryEntity(name);

			if (previous == null) {
				return LinkageKind.EXTERNAL;
			} else {
				LinkageKind previousLinkage = previous.getLinkage();

				if (previousLinkage == LinkageKind.INTERNAL
						|| previousLinkage == LinkageKind.EXTERNAL)
					return previousLinkage;
				else
					return LinkageKind.EXTERNAL;
			}
		}
		if (isFileScope) {
			if (!isFunction && hasNoStorageClass)
				return LinkageKind.EXTERNAL;
			if (node.hasStaticStorage())
				return LinkageKind.INTERNAL;
		}
		return LinkageKind.NONE;
	}

	private OrdinaryEntity processOrdinaryDeclaration(
			OrdinaryDeclarationNode node) throws SyntaxException {
		return processOrdinaryDeclaration(node, false);
	}

	/**
	 * Processes an ordinary declaration, i.e., one which declares a variable or
	 * function. (And not a structure/union member, enumerator, or typedef.) If
	 * the declared entity has not yet been encountered, and Entity object is
	 * created and added to the appropriate scope.
	 * 
	 * This method does not do everything needed to process an ordinary
	 * declaration. It just does the stuff that is common to both an object and
	 * function declaration.
	 * 
	 * @param node
	 *            the declaration node
	 * @param isParameter
	 *            is the declaration the delcaration of a function parameter?
	 * @throws SyntaxException
	 */
	private OrdinaryEntity processOrdinaryDeclaration(
			OrdinaryDeclarationNode node, boolean isParameter)
			throws SyntaxException {
		TranslationUnit unit = node.getOwner();
		IdentifierNode identifier = node.getIdentifier();
		TypeNode typeNode = node.getTypeNode();
		Type type = entityAnalyzer.typeAnalyzer.processTypeNode(typeNode,
				isParameter);
		Scope scope;
		boolean isFunction;
		LinkageKind linkage;
		String name;
		OrdinaryEntity entity;

		if (identifier == null)
			return null;
		scope = node.getScope();
		// if node is function definition, then scope is function scope.
		// want to add entity to parent scope. Otherwise fine.
		if (scope.getScopeKind() == ScopeKind.FUNCTION)
			scope = scope.getParentScope();
		isFunction = node instanceof FunctionDeclarationNode;
		linkage = computeLinkage(node);
		name = identifier.name();
		entity = scope.getOrdinaryEntity(name);
		if (entity != null) {
			if (linkage == LinkageKind.NONE) {
				throw error("Redeclaration of identifier with no linkage. "
						+ "Original declaration was at "
						+ entity.getDeclaration(0).getSource(), identifier);
			} else if (entity.getLinkage() != linkage)
				throw error(
						"Disagreement on internal/external linkage between two declarations",
						node);
		} else {
			entity = isFunction ? entityAnalyzer.entityFactory.newFunction(
					name, linkage, type) : entityAnalyzer.entityFactory
					.newVariable(name, linkage, type);
			try {
				scope.add(entity);
			} catch (UnsourcedException e) {
				throw error(e, identifier);
			}
			if (linkage != LinkageKind.NONE)
				unit.add(entity);
		}
		node.setEntity(entity);
		return entity;
	}

	private void addTypeToVariableOrFunction(TypeNode typeNode,
			OrdinaryEntity entity) throws SyntaxException {
		if (typeNode != null) {
			Type type = typeNode.getType();
			Type oldType = entity.getType();

			if (type == null)
				throw error("Internal error: type not processed", typeNode);
			if (oldType == null)
				entity.setType(type);
			else
				entity.setType(entityAnalyzer.typeFactory.compositeType(
						oldType, type));
		}
	}

	// precondition: type has already been set in decl and
	// linkage has been computed.
	private void addDeclarationToVariable(Variable variable,
			VariableDeclarationNode declaration) throws SyntaxException {
		TypeNode typeNode = declaration.getTypeNode();
		InitializerNode initializer = declaration.getInitializer();
		SequenceNode<TypeNode> typeAlignmentSpecifiers = declaration
				.typeAlignmentSpecifiers();
		SequenceNode<ExpressionNode> constantAlignmentSpecifiers = declaration
				.constantAlignmentSpecifiers();

		addTypeToVariableOrFunction(typeNode, variable);
		if (initializer != null) {
			InitializerNode oldInitializer = variable.getInitializer();

			if (oldInitializer != null)
				throw error(
						"Re-initialization of variable " + variable.getName()
								+ ". First was at" + oldInitializer.getSource()
								+ ".", initializer);
			variable.setInitializer(initializer);
			variable.setDefinition(declaration);
			declaration.setIsDefinition(true);
		} else if (variable.getLinkage() == LinkageKind.NONE) {
			variable.setDefinition(declaration);
			declaration.setIsDefinition(true);
		}
		if (typeAlignmentSpecifiers != null) {
			Iterator<TypeNode> typeIter = typeAlignmentSpecifiers
					.childIterator();

			while (typeIter.hasNext())
				variable.addTypeAlignment(entityAnalyzer.typeAnalyzer
						.processTypeNode(typeIter.next()));
		}
		if (constantAlignmentSpecifiers != null) {
			Iterator<ExpressionNode> expressionIter = constantAlignmentSpecifiers
					.childIterator();

			while (expressionIter.hasNext()) {
				ExpressionNode expression = expressionIter.next();
				Value constant = entityAnalyzer.valueOf(expression);

				if (constant == null)
					throw error("Value for enumerator must be constant",
							expression);
				variable.addConstantAlignment(constant);
			}
		}
		// TODO: set storage duration. See C11 Sec. 6.2.4.
		variable.addDeclaration(declaration);
	}

	private void addDeclarationToFunction(Function function,
			FunctionDeclarationNode declaration) throws SyntaxException {
		TypeNode typeNode = declaration.getTypeNode();
		OrdinaryDeclarationNode previousDeclaration;
		Iterator<DeclarationNode> declarationIter = function.getDeclarations();

		if (declarationIter.hasNext())
			previousDeclaration = (OrdinaryDeclarationNode) declarationIter
					.next();
		else
			previousDeclaration = null;
		addTypeToVariableOrFunction(typeNode, function);
		if (previousDeclaration == null) {
			if (declaration.hasInlineFunctionSpecifier())
				function.setIsInlined(true);
			if (declaration.hasNoreturnFunctionSpecifier())
				function.setDoesNotReturn(true);
		} else {
			if (declaration.hasInlineFunctionSpecifier() != function
					.isInlined())
				throw error(
						"Disagreement on inline function specifier at function declaration."
								+ "  Previous declaration was at "
								+ previousDeclaration.getSource(), declaration);
			if (declaration.hasNoreturnFunctionSpecifier() != function
					.doesNotReturn())
				throw error(
						"Disagreement on Noreturn function specifier at function declaration."
								+ "  Previous declaration was at "
								+ previousDeclaration.getSource(), declaration);
		}
		if (declaration instanceof FunctionDefinitionNode) {
			FunctionDefinitionNode previousDefinition = function
					.getDefinition();

			if (previousDefinition != null)
				throw error(
						"Redefinition of function.  Previous definition was at "
								+ previousDefinition.getSource(), declaration);
			function.setDefinition(declaration);
		}
		function.addDeclaration(declaration);
	}

	private boolean hasNoStorageClass(OrdinaryDeclarationNode node) {
		if (node.hasExternStorage() || node.hasStaticStorage())
			return false;
		if (node instanceof VariableDeclarationNode)
			return !(((VariableDeclarationNode) node).hasRegisterStorage() || ((VariableDeclarationNode) node)
					.hasAutoStorage());
		return true;
	}

	private void processGotos(ASTNode node) throws SyntaxException {
		Iterator<ASTNode> childIter = node.children();

		if (node instanceof GotoNode) {
			IdentifierNode identifier = ((GotoNode) node).getLabel();
			String name = identifier.name();
			Scope scope = node.getScope();
			Label label = scope.getLexicalLabel(name);

			if (label == null)
				throw error("Goto statement refers to non-existent label",
						identifier);
			identifier.setEntity(label);
		}
		while (childIter.hasNext()) {
			ASTNode child = childIter.next();

			if (child != null)
				processGotos(child);
		}
	}

}
