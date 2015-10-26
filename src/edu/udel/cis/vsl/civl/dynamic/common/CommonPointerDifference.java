package edu.udel.cis.vsl.civl.dynamic.common;

import edu.udel.cis.vsl.civl.dynamic.IF.PointerDifference;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.SymbolicObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public class CommonPointerDifference implements PointerDifference,
		NumericExpression {

	private SymbolicExpression minuendPtr;

	private SymbolicExpression subtrachendPtr;

	private SymbolicObject[] arguments;

	private SymbolicType type;

	private int id = -1;

	public CommonPointerDifference(SymbolicExpression minuendPtr,
			SymbolicExpression subtrachendPtr, SymbolicType type) {
		this.minuendPtr = minuendPtr;
		this.subtrachendPtr = subtrachendPtr;
		this.arguments = new SymbolicObject[2];
		arguments[0] = minuendPtr;
		arguments[1] = subtrachendPtr;
		this.type = type;
	}

	@Override
	public SymbolicObject argument(int index) {
		return arguments[index];
	}

	@Override
	public SymbolicObject[] arguments() {
		return arguments;
	}

	@Override
	public String atomString() {
		return this.toStringBuffer(true).toString();
	}

	@Override
	public boolean isFalse() {
		return false;
	}

	@Override
	public boolean isNull() {
		return minuendPtr.isNull() && subtrachendPtr.isNull();
	}

	@Override
	public boolean isNumeric() {
		return true;
	}

	@Override
	public boolean isOne() {
		return false;
	}

	@Override
	public boolean isTrue() {
		return false;
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public int numArguments() {
		return arguments.length;
	}

	@Override
	public SymbolicOperator operator() {
		return SymbolicOperator.SUBTRACT;
	}

	@Override
	public SymbolicType type() {
		return this.type;
	}

	@Override
	public SymbolicObjectKind symbolicObjectKind() {
		return SymbolicObjectKind.INT;
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean isCanonic() {
		return id >= 0;
	}

	@Override
	public StringBuffer toStringBuffer(boolean atomize) {
		StringBuffer result = new StringBuffer();

		result.append(this.minuendPtr.toStringBuffer(atomize) + " - "
				+ this.subtrachendPtr.toStringBuffer(atomize));
		if (atomize) {
			StringBuffer parenthesised = new StringBuffer();

			parenthesised.append("(");
			parenthesised.append(result);
			parenthesised.append(")");
			result = parenthesised;
		}
		return result;
	}

	@Override
	public SymbolicExpression getSubtrachend() {
		return subtrachendPtr;
	}

	@Override
	public SymbolicExpression getMinuend() {
		return minuendPtr;
	}

	@Override
	public StringBuffer toStringBufferLong() {
		StringBuffer buffer = new StringBuffer(getClass().getSimpleName());

		buffer.append("[");
		buffer.append(SymbolicOperator.SUBTRACT.toString());
		buffer.append("; ");
		buffer.append(type != null ? type.toString() : "no type");
		buffer.append("; ");
		buffer.append(toStringBufferLong(arguments));
		buffer.append("]");
		return buffer;
	}

	private StringBuffer toStringBufferLong(SymbolicObject[] objects) {
		StringBuffer buffer = new StringBuffer("{");
		boolean first = true;

		for (SymbolicObject object : objects) {
			if (first)
				first = false;
			else
				buffer.append(",");
			if (object == null)
				buffer.append("null");
			else
				buffer.append(object.toStringBufferLong());
		}
		buffer.append("}");
		return buffer;
	}

	@Override
	public String toString() {
		return this.toStringBuffer(false).toString();
	}
}
