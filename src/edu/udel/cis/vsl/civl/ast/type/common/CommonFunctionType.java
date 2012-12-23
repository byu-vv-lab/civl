package edu.udel.cis.vsl.civl.ast.type.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

/**
 * 
 * @author siegel
 * 
 */
public class CommonFunctionType extends CommonType implements FunctionType {

	private static int classCode = CommonFunctionType.class.hashCode();

	private ObjectType returnType;

	private ArrayList<ObjectType> parameterTypes;

	private boolean hasVariableArgs;

	private boolean fromIdentifierList;

	/**
	 * Constructs a new instance in the case where no information is known about
	 * the parameters. In this case, fromIdentifierList must be true.
	 * 
	 * @param returnType
	 *            the type returned by a call to the function
	 */
	public CommonFunctionType(ObjectType returnType) {
		super(TypeKind.FUNCTION);
		this.returnType = returnType;
		this.fromIdentifierList = true;
		this.parameterTypes = null;
		this.hasVariableArgs = false;
	}

	/**
	 * Constructs a new instance in the case where the information on the
	 * parameter types is known.
	 * 
	 * @param returnType
	 *            the type returned by a call to the function
	 * @param fromIdentifierList
	 *            was this type generated from a declaration with an identifier
	 *            list (as opposed to a parameter-type list)?
	 * @param parameterTypes
	 *            the type of each parameter
	 * @param hasVariableArgs
	 *            does the function have a variable number of arguments
	 *            (indicated by a "..." in the parameter type list)?
	 */
	public CommonFunctionType(ObjectType returnType,
			boolean fromIdentifierList, List<ObjectType> parameterTypes,
			boolean hasVariableArgs) {
		super(TypeKind.FUNCTION);
		this.returnType = returnType;
		this.fromIdentifierList = fromIdentifierList;
		this.parameterTypes = new ArrayList<ObjectType>(parameterTypes);
		this.hasVariableArgs = hasVariableArgs;
	}

	@Override
	public ObjectType getReturnType() {
		return returnType;
	}

	@Override
	public boolean hasVariableArgs() {
		return hasVariableArgs;
	}

	@Override
	public int getNumParameters() {
		return parameterTypes.size();
	}

	@Override
	public ObjectType getParameterType(int index) {
		return parameterTypes.get(index);
	}

	@Override
	public Iterator<ObjectType> getParameterTypes() {
		return parameterTypes.iterator();
	}

	@Override
	public boolean isVariablyModified() {
		return returnType.isVariablyModified();
	}

	@Override
	public int hashCode() {
		int result = classCode + returnType.hashCode();

		if (parameterTypes != null) {
			for (ObjectType type : parameterTypes)
				if (type != null)
					result += type.hashCode();
		}
		if (hasVariableArgs)
			result += 128;
		if (fromIdentifierList)
			result += 256;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof CommonFunctionType) {
			CommonFunctionType that = (CommonFunctionType) object;

			if (!returnType.equals(that.returnType))
				return false;
			if (parameterTypes == null) {
				if (that.parameterTypes != null)
					return false;
			} else {
				if (!parameterTypes.equals(that.parameterTypes))
					return false;
			}
			return hasVariableArgs == that.hasVariableArgs
					&& fromIdentifierList == that.fromIdentifierList;
		}
		return false;
	}

	/**
	 * See C11 Sec. 6.7.6.3(15).
	 * 
	 * Note: "In the determination of type compatibility and of a composite
	 * type, each parameter declared with function or array type is taken as
	 * having the adjusted type and each parameter declared with qualified type
	 * is taken as having the unqualified version of its declared type."
	 * 
	 * 6.5.2.2(6): "If the expression that denotes the called function has a
	 * type that does not include a prototype, the integer promotions are
	 * performed on each argument, and arguments that have type float are
	 * promoted to double. These are called the default argument promotions."
	 */
	@Override
	public boolean compatibleWith(Type type) {
		if (type instanceof CommonFunctionType) {
			CommonFunctionType that = (CommonFunctionType) type;

			if (!returnType.compatibleWith(that.returnType))
				// "For two function types to be compatible, both shall specify
				// compatible return types."
				return false;
			if (!this.fromIdentifierList && !that.fromIdentifierList) {
				// "the parameter type lists, if both are present, shall agree
				// in the number of parameters and in use of the ellipsis
				// terminator; corresponding parameters shall have compatible
				// types."
				int numParameters = getNumParameters();

				if (numParameters != that.getNumParameters())
					return false;
				for (int i = 0; i < numParameters; i++) {
					ObjectType parameterType1 = this.getParameterType(i);
					ObjectType parameterType2 = that.getParameterType(i);

					if (!parameterType1.compatibleWith(parameterType2))
						return false;
				}
				if (this.hasVariableArgs != that.hasVariableArgs)
					return false;
				return true;
			} else if (!this.fromIdentifierList && !that.parametersKnown()) {
				// "If one type has a parameter type list and the other type is
				// specified by a function declarator that is not part of a
				// function definition and that contains an empty identifier
				// list, the parameter list shall not have an ellipsis
				// terminator and the type of each parameter shall be compatible
				// with the type that results from the application of the
				// default argument promotions." The application of the default
				// argument promotions to what??? Is this referring to
				// what happens when the function is called?
				if (this.hasVariableArgs)
					return false;
			} else if (!that.fromIdentifierList && !this.parametersKnown()) {
				// symmetric situation
				return that.compatibleWith(this);
			} else if (!this.fromIdentifierList
					&& (that.fromIdentifierList && that.parametersKnown())) {
				// "If one type has a parameter type list and the other type is
				// specified by a function definition that contains a (possibly
				// empty) identifier list, both shall agree in the number of
				// parameters, and the type of each prototype parameter shall be
				// compatible with the type that results from the application of
				// the default argument promotions to the type of the
				// corresponding identifier."  In other words, the "real" type
				// of the parameter is the one in the parameter-type list; the
				// type specified in the function definition has to be
				// compatible with that after it is promoted.
				int numParameters = getNumParameters();

				if (numParameters != that.getNumParameters())
					return false;
				for (int i = 0; i < numParameters; i++) {
					ObjectType parameterType1 = this.getParameterType(i);
					ObjectType parameterType2 = that.getParameterType(i);

					// TODO: perform default argument promotion to
					// parameterType2
					if (!parameterType1.compatibleWith(parameterType2))
						return false;
				}
				if (this.hasVariableArgs != that.hasVariableArgs)
					return false;
				return true;
			} else if (!that.fromIdentifierList
					&& (this.fromIdentifierList && this.parametersKnown())) {
				// symmetric situation
				return that.compatibleWith(this);
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean fromIdentifierList() {
		return fromIdentifierList;
	}

	@Override
	public boolean parametersKnown() {
		return parameterTypes != null;
	}

	@Override
	public void print(String prefix, PrintStream out, boolean abbrv) {
		out.println("Function");
		out.print(prefix + "| returnType = ");
		returnType.print(prefix + "| | ", out, true);
		if (parameterTypes != null) {
			out.println();
			out.print(prefix + "| parameterTypes");
			for (Type type : parameterTypes) {
				out.println();
				out.print(prefix + "| | ");
				type.print(prefix + "| | ", out, true);
			}
		}
	}

	@Override
	public boolean isScalar() {
		return true;
	}

}
