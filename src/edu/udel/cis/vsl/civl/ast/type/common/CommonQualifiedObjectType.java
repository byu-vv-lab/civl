package edu.udel.cis.vsl.civl.ast.type.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.type.IF.UnqualifiedObjectType;

public class CommonQualifiedObjectType extends CommonObjectType implements
		QualifiedObjectType {

	private UnqualifiedObjectType baseType;

	private boolean constQualified = false;

	private boolean volatileQualified = false;

	private boolean restrictQualified = false;

	public CommonQualifiedObjectType(UnqualifiedObjectType baseType,
			boolean constQualified, boolean volatileQualified,
			boolean restrictQualified) {
		super(TypeKind.QUALIFIED);
		if (!constQualified && !volatileQualified && !restrictQualified)
			throw new RuntimeException("No qualifiers used in qualified type: "
					+ baseType);
		this.baseType = baseType;
		this.constQualified = constQualified;
		this.volatileQualified = volatileQualified;
		this.restrictQualified = restrictQualified;
	}

	@Override
	public boolean isConstQualified() {
		return constQualified;
	}

	@Override
	public boolean isVolatileQualified() {
		return this.volatileQualified;
	}

	@Override
	public boolean isRestrictQualified() {
		return this.restrictQualified;
	}

	@Override
	public boolean compatibleWith(Type type) {
		if (type instanceof QualifiedObjectType) {
			QualifiedObjectType that = (QualifiedObjectType) type;

			return constQualified == that.isConstQualified()
					&& volatileQualified == that.isVolatileQualified()
					&& restrictQualified == that.isRestrictQualified()
					&& baseType.compatibleWith(that.getBaseType());
		}
		return false;
	}

	@Override
	public UnqualifiedObjectType getBaseType() {
		return baseType;
	}

	@Override
	public boolean isComplete() {
		return baseType.isComplete();
	}

	@Override
	public boolean isVariablyModified() {
		return baseType.isVariablyModified();
	}

	@Override
	public int hashCode() {
		int result = 1024 * baseType.hashCode();

		if (constQualified)
			result += 3;
		if (volatileQualified)
			result += 5;
		if (restrictQualified)
			result += 7;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof QualifiedObjectType) {
			QualifiedObjectType that = (QualifiedObjectType) object;

			return constQualified == that.isConstQualified()
					&& volatileQualified == that.isVolatileQualified()
					&& restrictQualified == that.isRestrictQualified()
					&& baseType.equals(that.getBaseType());
		}
		return false;
	}

	@Override
	public void print(String prefix, PrintStream out, boolean abbrv) {
		String seperator = "";

		out.print("QualifiedType[");
		if (constQualified) {
			out.print(seperator + "const");
			seperator = ", ";
		}
		if (restrictQualified) {
			out.print(seperator + "restrict");
			seperator = ", ";
		}
		if (volatileQualified) {
			out.print(seperator + "volatile");
			seperator = ", ";
		}
		out.println("]");
		out.print(prefix + "| ");
		baseType.print(prefix + "| ", out, true);
	}

	@Override
	public boolean isScalar() {
		return baseType.isScalar();
	}

}
