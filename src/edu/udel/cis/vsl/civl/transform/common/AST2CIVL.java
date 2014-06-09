package edu.udel.cis.vsl.civl.transform.common;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ArrowNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.DotNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode.ExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AssumeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.BasicTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.EnumerationTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypedefNameNode;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;

public class AST2CIVL {
	public Map<String, StringBuffer> astToCIVL(AST ast) {
		Map<String, StringBuffer> results = new LinkedHashMap<>();
		Set<String> headers = new LinkedHashSet<>();
		ASTNode root = ast.getRootNode();
		String sourceFile = root.getSource().getFirstToken().getSourceFile()
				.getName();

		results.put(sourceFile, new StringBuffer());
		for (ASTNode child : root.children()) {
			this.externalDef2CIVL((ExternalDefinitionNode) child, results,
					headers);
		}
		return results;
	}

	private void externalDef2CIVL(ExternalDefinitionNode extern,
			Map<String, StringBuffer> results, Set<String> headers) {
		String sourceFile = extern.getSource().getFirstToken().getSourceFile()
				.getName();
		StringBuffer myBuffer;
		StringBuffer nodeBuffer = new StringBuffer();

		switch (sourceFile) {
		case "assert.h":
		case "civlc.h":
		case "civlc-common.h":
		case "civlc-omp.cvl":
		case "float.h":
		case "math.h":
		case "mpi-common.h":
		case "mpi.h":
		case "omp-common.h":
		case "omp.h":
		case "pthread.h":
		case "stdio-c.cvl":
		case "stdio.cvl":
		case "stdbool.h":
		case "stddef.h":
		case "stdio-common.h":
		case "stdio.h":
		case "stdlib.h":
		case "string-common.h":
		case "string.h":
			headers.add(sourceFile);
			return;
		default:
			if (!results.containsKey(sourceFile))
				results.put(sourceFile, new StringBuffer());
		}
		myBuffer = results.get(sourceFile);
		if (extern instanceof AssumeNode) {
			nodeBuffer.append(assume2CIVL((AssumeNode) extern));
		} else if (extern instanceof VariableDeclarationNode) {
			nodeBuffer
					.append(variableDeclaration2CIVL((VariableDeclarationNode) extern));
		}

	}

	private StringBuffer variableDeclaration2CIVL(
			VariableDeclarationNode variable) {
		StringBuffer result = new StringBuffer();
		InitializerNode init = variable.getInitializer();

		result.append(type2CIVL(variable.getTypeNode()));
		result.append(" ");
		result.append(variable.getName());
		if (init != null) {
			result.append(" = ");
			result.append(initializer2CIVL(init));
		}
		result.append(";");
		return result;
	}

	private StringBuffer initializer2CIVL(InitializerNode init) {
		StringBuffer result = new StringBuffer();

		// if(init )

		return result;
	}

	private StringBuffer assume2CIVL(AssumeNode assume) {
		StringBuffer result = new StringBuffer();

		result.append("$assume ");
		result.append(expression2CIVL(assume.getExpression()));
		result.append(";");
		return result;
	}

	private StringBuffer expression2CIVL(ExpressionNode expression) {
		StringBuffer result = new StringBuffer();
		ExpressionKind kind = expression.expressionKind();

		switch (kind) {
		case ARROW: {
			ArrowNode arrow = (ArrowNode) expression;

			result.append(expression2CIVL(arrow.getStructurePointer()));
			result.append("->");
			result.append(arrow.getFieldName().name());
			break;
		}
		case CAST: {
			CastNode cast = (CastNode) expression;

			result.append("(");
			result.append(type2CIVL(cast.getCastType()));
			result.append(")");
			result.append(expression2CIVL(cast.getArgument()));
			break;
		}
		case COMPOUND_LITERAL: {
			break;
		}
		case CONSTANT: {
			result.append(((ConstantNode) expression).getConstantValue());
			break;
		}
		case DOT: {
			DotNode dot = (DotNode) expression;

			result.append(expression2CIVL(dot.getStructure()));
			result.append(".");
			result.append(dot.getFieldName().name());
			break;
		}
		case FUNCTION_CALL: {
			FunctionCallNode call = (FunctionCallNode) expression;
			int argNum = call.getNumberOfArguments();

			result.append(expression2CIVL(call.getFunction()));
			result.append("(");
			for (int i = 0; i < argNum; i++) {
				if (i > 0)
					result.append(",");
				result.append(expression2CIVL(call.getArgument(i)));
			}
			result.append(")");
			break;
		}
		case IDENTIFIER_EXPRESSION:
			result.append(((IdentifierExpressionNode) expression)
					.getIdentifier().name());
			break;
		case OPERATOR:
			result = operator2CIVL((OperatorNode) expression);
		default:

		}

		return result;
	}

	private StringBuffer operator2CIVL(OperatorNode operator) {
		StringBuffer result = new StringBuffer();
		Operator op = operator.getOperator();
		StringBuffer arg0 = expression2CIVL(operator.getArgument(0));
		StringBuffer arg1 = operator.getNumberOfArguments() > 1 ? expression2CIVL(operator
				.getArgument(1)) : null;

		switch (op) {
		case ADDRESSOF:
			result.append("&");
			result.append(arg0);
			break;
		case ASSIGN:
			result.append(arg0);
			result.append(" = ");
			result.append(arg1);
			break;
		case BIG_O:
			result.append("$O(");
			result.append(arg0);
			result.append(")");
			break;
		case BITAND:
			result.append(arg0);
			result.append(" & ");
			result.append(arg1);
			break;
		case BITCOMPLEMENT:
			result.append("~");
			result.append(arg0);
			break;
		case BITOR:
			result.append(arg0);
			result.append(" | ");
			result.append(arg1);
			break;
		case BITXOR:
			result.append(arg0);
			result.append(" ^ ");
			result.append(arg1);
			break;
		case DEREFERENCE:
			result.append("*");
			result.append(arg0);
			break;
		case DIV:
			result.append(arg0);
			result.append(" / ");
			result.append(arg1);
			break;
		case EQUALS:
			result.append(arg0);
			result.append(" == ");
			result.append(arg1);
			break;
		case GT:
			result.append(arg0);
			result.append(" > ");
			result.append(arg1);
			break;
		case GTE:
			result.append(arg0);
			result.append(" >= ");
			result.append(arg1);
			break;
		case IMPLIES:
			result.append(arg0);
			result.append(" => ");
			result.append(arg1);
			break;
		case LAND:
			result.append(arg0);
			result.append(" && ");
			result.append(arg1);
			break;
		case LOR:
			result.append(arg0);
			result.append(" || ");
			result.append(arg1);
			break;
		case LT:
			result.append(arg0);
			result.append(" < ");
			result.append(arg1);
			break;
		case LTE:
			result.append(arg0);
			result.append(" <= ");
			result.append(arg1);
			break;
		case MINUS:
			result.append(arg0);
			result.append(" － ");
			result.append(arg1);
			break;
		case MOD:
			result.append(arg0);
			result.append(" % ");
			result.append(arg1);
			break;
		case NEQ:
			result.append(arg0);
			result.append(" != ");
			result.append(arg1);
			break;
		case PLUS:
			result.append(arg0);
			result.append(" + ");
			result.append(arg1);
			break;
		case SHIFTLEFT:
			result.append(arg0);
			result.append(" << ");
			result.append(arg1);
			break;
		case SHIFTRIGHT:
			result.append(arg0);
			result.append(" >> ");
			result.append(arg1);
			break;
		case SUBSCRIPT:
			result.append(arg0);
			result.append("[");
			result.append(arg1);
			result.append("]");
			break;
		case TIMES:
			result.append(arg0);
			result.append(" * ");
			result.append(arg1);
			break;
		case UNARYMINUS:
			result.append("-");
			result.append(arg0);
			break;
		case UNARYPLUS:
			result.append("+");
			result.append(arg0);
			break;
		default:
			throw new CIVLUnimplementedFeatureException(
					"translating expression node of " + op
							+ " kind into CIVL code", operator.getSource());
		}

		return result;
	}

	private StringBuffer type2CIVL(TypeNode type) {
		StringBuffer result = new StringBuffer();
		TypeNodeKind kind = type.kind();

		switch (kind) {
		case VOID:
			result.append("void");
			break;
		case BASIC:
			return basicType2CIVL((BasicTypeNode) type);
		case ENUMERATION:
			result.append(((EnumerationTypeNode) type).getIdentifier().name());
			break;
		case STRUCTURE_OR_UNION:
			result.append(((StructureOrUnionTypeNode) type).getName());
			break;
		case POINTER:
			result.append("*");
			result.append(type2CIVL(((PointerTypeNode) type).referencedType()));
			break;
		case TYPEDEF_NAME:
			result.append(((TypedefNameNode) type).getName().name());
			break;
		case SCOPE:
			result.append("$scope");
			break;
		default:
			throw new CIVLUnimplementedFeatureException(
					"translating type node of " + kind + " kind into CIVL code",
					type.getSource());
		}
		return result;
	}

	private StringBuffer basicType2CIVL(BasicTypeNode type) {
		StringBuffer result = new StringBuffer();
		BasicTypeKind basicKind = type.getBasicTypeKind();

		switch (basicKind) {
		case BOOL:
			result.append("_Bool");
			break;
		case CHAR:
			result.append("char");
			break;
		case DOUBLE:
			result.append("double");
			break;
		case DOUBLE_COMPLEX:
			result.append("double _Complex");
			break;
		case FLOAT:
			result.append("float");
			break;
		case FLOAT_COMPLEX:
			result.append("float _Complex");
			break;
		case INT:
			result.append("int");
			break;
		case LONG:
			result.append("long");
			break;
		case LONG_DOUBLE:
			result.append("long double");
			break;
		case LONG_DOUBLE_COMPLEX:
			result.append("long double _Complex");
			break;
		case LONG_LONG:
			result.append("long long");
			break;
		case REAL:
			result.append("real");
			break;
		case SHORT:
			result.append("short");
			break;
		case SIGNED_CHAR:
			result.append("signed char");
			break;
		case UNSIGNED:
			result.append("unsigned");
			break;
		case UNSIGNED_CHAR:
			result.append("unsigned char");
			break;
		case UNSIGNED_LONG:
			result.append("unsigned long");
			break;
		case UNSIGNED_LONG_LONG:
			result.append("unsigned long long");
			break;
		case UNSIGNED_SHORT:
			result.append("unsigned short");
			break;
		default:

		}
		return result;
	}

//	private void astNode2CIVL(String prefix, ASTNode node,
//			Map<String, StringBuffer> results, Set<String> headers) {
//
//	}
}
