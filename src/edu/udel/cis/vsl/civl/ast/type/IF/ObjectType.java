package edu.udel.cis.vsl.civl.ast.type.IF;

public interface ObjectType extends Type {

	/**
	 * Is this type "complete"?
	 * 
	 * @return true iff this type is complete.
	 */
	boolean isComplete();

	/**
	 * Does this type have known constant size? An object type has
	 * "known constant size" iff it is not incomplete AND not a VLA (Variable
	 * Length Array) type.
	 * 
	 * @return true iff this is an object type of known constant size
	 */
	boolean hasKnownConstantSize();

}
