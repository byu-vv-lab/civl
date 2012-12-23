package edu.udel.cis.vsl.civl.ast.node.IF.expression;

/**
 * The most common type of expression is an expression that involves an operator
 * and some number of operands. The operands are the children of this AST node.
 * 
 * @author siegel
 * 
 */
public interface OperatorNode extends ExpressionNode {

	public enum Operator {
		ADDRESSOF, // & pointer to object
		ASSIGN, // = standard assignment operator
		BITAND, // & bit-wise and
		BITANDEQ, // &= bit-wise and assignment
		BITCOMPLEMENT, // ~ bit-wise complement
		BITOR, // | bit-wise inclusive or
		BITOREQ, // |= bit-wise inclusive or assignment
		BITXOR, // ^ bit-wise exclusive or
		BITXOREQ, // ^= bit-wise exclusive or assignment
		COMMA, // , the comma operator
		CONDITIONAL, // ?: the conditional operator
		DEREFERENCE, // * pointer dereference
		DIV, // / numerical division
		DIVEQ, // /= division assignment
		EQUALS, // == equality
		GT, // > greater than
		GTE, // >= greater than or equals
		LAND, // && logical and
		LOR, // || logical or
		LT, // < less than
		LTE, // <= less than or equals
		MINUS, // - binary subtraction (numbers and pointers)
		MINUSEQ, // -= subtraction assignment
		MOD, // % integer modulus
		MODEQ, // %= integer modulus assignment
		NEQ, // != not equals
		NOT, // ! logical not
		PLUS, // + binary addition, numeric or pointer
		PLUSEQ, // += addition assignment
		POSTDECREMENT, // -- decrement after expression
		POSTINCREMENT, // ++ increment after expression
		PREDECREMENT, // -- decrement before expression
		PREINCREMENT, // ++ increment before expression
		SHIFTLEFT, // << shift left
		SHIFTLEFTEQ, // <<= shift left assignment
		SHIFTRIGHT, // >> shift right
		SHIFTRIGHTEQ, // >>= shift right assignment
		SUBSCRIPT, // [] array subscript
		TIMES, // * numeric multiplication
		TIMESEQ, // *= multiplication assignment
		UNARYMINUS, // - numeric negative
		UNARYPLUS // + numeric no-op
	};

	Operator getOperator();

	void setOperator(Operator operator);

	/**
	 * Returns the number of arguments in this operator expression.
	 * 
	 * @return the number of arguments
	 */
	int getNumberOfArguments();

	/**
	 * Returns the index-th argument, indexed from 0. Beware: the argument index
	 * and the child index are off by one! So, argument 0 is child 1. That is
	 * because child 0 is the operator.
	 */
	ExpressionNode getArgument(int index);

	void setArgument(int index, ExpressionNode value);

}
