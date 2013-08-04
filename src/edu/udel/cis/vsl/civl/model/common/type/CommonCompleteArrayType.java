/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLCompleteArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

/**
 * @author zirkel
 * 
 */
public class CommonCompleteArrayType extends CommonArrayType implements
		CIVLCompleteArrayType {

	private Expression extent;

	/**
	 * @param baseType
	 */
	public CommonCompleteArrayType(CIVLType baseType, Expression extent) {
		super(baseType);
		this.extent = extent;
	}

	@Override
	public Expression extent() {
		return extent;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

}
