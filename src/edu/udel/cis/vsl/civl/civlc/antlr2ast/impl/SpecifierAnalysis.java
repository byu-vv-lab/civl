package edu.udel.cis.vsl.civl.civlc.antlr2ast.impl;

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.civl.civlc.parse.IF.CParser;
import edu.udel.cis.vsl.civl.civlc.parse.common.CivlCParser;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

/**
 * This class is used to analyze the "declaration specifier" section of a C
 * declaration. The specifier information is complex and involves many different
 * elements of the language. It consists of type specifiers and qualifiers,
 * storage class specifiers, function specifiers, and alignment specifiers. See
 * C11 Sections 6.7.1 through 6.7.5 for details.
 * 
 * Sec. 6.7.2 of the C11 Standard covers "type specifiers". The set of type
 * specifiers must fall into one of the following categories: a "basic multiset"
 * (see class BasicMultiset for the enumeration of those sets); or one of the
 * singleton sets VOID; STRUCT; UNION; ENUM; TYPEDEF_NAME; or ATOMIC. (Note that
 * some of these are actually structured types, such as STRUCT, UNION, ENUM, and
 * ATOMIC.) Each of these categories is represented by an element of the
 * enumerated type TypeName.TypeNameKind. This class will determine which of the
 * categories the given set of type specifiers belongs to, and other
 * information.
 * 
 * Sec. 6.7.3 covers "type qualifiers": CONST, RESTRICT, VOLATILE, ATOMIC. A
 * qualifier can appear more than once---it is the same as appearing once. This
 * class records whether or not each qualifier occurs.
 * 
 * The storage class specifiers are TYPEDEF, EXTERN, STATIC, THREADLOCAL, AUTO,
 * and REGISTER.
 * 
 * The function specifiers are INLINE and NORETURN.
 * 
 * The alignment specifiers fall into two categories: ALIGNAS ( type ) and
 * ALIGNAS ( expression ). There can be any number of both.
 * 
 * There are a lot of restrictions on the allowable combintations of specifiers.
 * See the C11 Standard for details.
 */
public class SpecifierAnalysis {

	// the basic type specifier keywords and VOID...

	public final static int VOID = CivlCParser.VOID;
	public final static int CHAR = CivlCParser.CHAR;
	public final static int SHORT = CivlCParser.SHORT;
	public final static int INT = CivlCParser.INT;
	public final static int LONG = CivlCParser.LONG;
	public final static int FLOAT = CivlCParser.FLOAT;
	public final static int DOUBLE = CivlCParser.DOUBLE;
	public final static int SIGNED = CivlCParser.SIGNED;
	public final static int UNSIGNED = CivlCParser.UNSIGNED;
	public final static int BOOL = CivlCParser.BOOL;
	public final static int COMPLEX = CivlCParser.COMPLEX;

	// Instance variables...

	private CParser parser;

	/**
	 * The given tree node whose children are the declaration specifiers to be
	 * analyzed.
	 */
	CommonTree specifierListNode;

	/**
	 * The kind of type name represented by these specifiers. One of BASIC,
	 * VOID, STRUCTURE, UNION, ENUMERATION, TYPEDEF_NAME, or ATOMIC.
	 */
	TypeNodeKind typeNameKind = null;

	/**
	 * If the type name kind is NOT BASIC, this variable will hold a reference
	 * to the child of the specifierListNode that is the sole type specifier.
	 * 
	 * If the type name kind is BASIC, this will be null. (Reason: basic types
	 * require a set of type specifiers, which is why the multisets are needed.)
	 */
	CommonTree typeSpecifierNode = null;

	/**
	 * If the type name kind is BASIC, this will contain a list of the type
	 * specifiers.
	 */
	List<CommonTree> basicSpecifierNodes = null;

	/**
	 * If the typeNameKind is BASIC, this will hold the kind of BASIC type.
	 * Otherwise it will be null.
	 */
	BasicTypeKind basicTypeKind = null;

	// multiset specifiers:
	BasicMultiset set = new BasicMultiset();
	// other types:
	int voidTypeCount = 0;
	int atomicTypeCount = 0; // _Atomic(typeName): has one child
	int structTypeCount = 0;
	int unionTypeCount = 0;
	int enumTypeCount = 0;
	int typedefNameCount = 0;
	// qualifiers:
	boolean constQualifier = false;
	boolean restrictQualifier = false;
	boolean volatileQualifier = false;
	boolean atomicQualifier = false; // _Atomic: has 0 children
	// storage class specifiers
	int typedefCount = 0;
	int externCount = 0;
	int staticCount = 0;
	int threadLocalCount = 0;
	int autoCount = 0;
	int registerCount = 0;
	// function specifiers: may appear multiple times
	boolean inlineSpecifier = false;
	boolean noreturnSpecifier = false;
	// alignment specifiers
	List<CommonTree> alignmentTypeNodes = new LinkedList<CommonTree>();
	List<CommonTree> alignmentExpressionNodes = new LinkedList<CommonTree>();

	/**
	 * Creates a new analysis object and conducts the analysis. The
	 * specifierListNode is the root of a tree which is a list of declaration
	 * specifiers. It may have type DECLARATION_SPECIFIERS or
	 * SPECIFIER_QUALIFIER_LIST.
	 * 
	 * @param specifierListNode
	 * @throws SyntaxException
	 */
	SpecifierAnalysis(CommonTree specifierListNode, CParser parser)
			throws SyntaxException {
		this.specifierListNode = specifierListNode;
		this.parser = parser;
		analyze();
	}

	private SyntaxException error(String message, CommonTree tree) {
		return parser.newSyntaxException(message, tree);
	}

	private void analyze() throws SyntaxException {
		int numChildren = specifierListNode.getChildCount();

		for (int i = 0; i < numChildren; i++) {
			CommonTree node = (CommonTree) specifierListNode.getChild(i);
			int kind = node.getType();

			switch (kind) {
			case CHAR:
			case SHORT:
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE:
			case SIGNED:
			case UNSIGNED:
			case BOOL:
			case COMPLEX:
				set.add(kind);
				setTypeNameKind(TypeNodeKind.BASIC);
				if (basicSpecifierNodes == null)
					basicSpecifierNodes = new LinkedList<CommonTree>();
				basicSpecifierNodes.add(node);
				break;
			case VOID:
				voidTypeCount++;
				setTypeNameKind(TypeNodeKind.VOID);
				setTypeSpecifierNode(node);
				break;
			case CivlCParser.ATOMIC:
				if (node.getChildCount() > 0) {
					atomicTypeCount++;
					setTypeNameKind(TypeNodeKind.ATOMIC);
					setTypeSpecifierNode(node);
				} else {
					atomicQualifier = true;
				}
				break;
			case CivlCParser.STRUCT:
				structTypeCount++;
				setTypeNameKind(TypeNodeKind.STRUCTURE_OR_UNION);
				setTypeSpecifierNode(node);
				break;
			case CivlCParser.UNION:
				unionTypeCount++;
				setTypeNameKind(TypeNodeKind.STRUCTURE_OR_UNION);
				setTypeSpecifierNode(node);
				break;
			case CivlCParser.ENUM:
				enumTypeCount++;
				setTypeNameKind(TypeNodeKind.ENUMERATION);
				setTypeSpecifierNode(node);
				break;
			case CivlCParser.TYPEDEF_NAME:
				typedefNameCount++;
				setTypeNameKind(TypeNodeKind.TYPEDEF_NAME);
				setTypeSpecifierNode(node);
				break;
			case CivlCParser.CONST:
				constQualifier = true;
				break;
			case CivlCParser.RESTRICT:
				restrictQualifier = true;
				break;
			case CivlCParser.VOLATILE:
				volatileQualifier = true;
				break;
			case CivlCParser.TYPEDEF:
				typedefCount++;
				break;
			case CivlCParser.EXTERN:
				externCount++;
				break;
			case CivlCParser.STATIC:
				staticCount++;
				break;
			case CivlCParser.THREADLOCAL:
				threadLocalCount++;
				break;
			case CivlCParser.AUTO:
				autoCount++;
				break;
			case CivlCParser.REGISTER:
				registerCount++;
				break;
			case CivlCParser.INLINE:
				inlineSpecifier = true;
				break;
			case CivlCParser.NORETURN:
				noreturnSpecifier = true;
				break;
			case CivlCParser.ALIGNAS: {
				int alignKind = ((CommonTree) node.getChild(0)).getType();
				CommonTree argument = (CommonTree) node.getChild(1);

				if (alignKind == CivlCParser.TYPE) {
					alignmentTypeNodes.add(argument);
				} else if (kind == CivlCParser.EXPR) {
					alignmentExpressionNodes.add(argument);
				} else {
					throw error("Unexpected kind of ALIGN_AS argument", node);
				}
				break;
			}
			default:
				throw error("Unknown declaration specifier", node);
			}
		}
		if (typeNameKind == TypeNodeKind.BASIC) {
			basicTypeKind = BasicMultiset.getBasicTypeKind(set);
			if (basicTypeKind == null)
				throw error("Illegal type specifiers", specifierListNode);
		}
	}

	public BasicTypeKind getBasicTypeKind() {
		return basicTypeKind;
	}

	private void setTypeNameKind(TypeNodeKind kind) throws SyntaxException {
		if (typeNameKind != null && typeNameKind != kind)
			throw error(
					"Two different kinds of types specified in declaration specifier list: "
							+ typeNameKind + " and " + kind, specifierListNode);
		typeNameKind = kind;
	}

	private void setTypeSpecifierNode(CommonTree node) throws SyntaxException {
		if (typeSpecifierNode != null)
			throw error(
					"Two type specifiers in declaration. Previous specifier was at "
							+ error("", typeSpecifierNode).getSource(), node);
		typeSpecifierNode = node;
	}

}
