package edu.udel.cis.vsl.civl.ast.type.IF;

/**
 * An atomic type, specified by "_Atomic ( type-name )" or by using the _Atomic
 * type qualifier. See C11 Sec. 6.7.2.4.
 * 
 * The base type cannot be array, function, atomic, or qualified.
 * 
 * @author siegel
 * 
 */
public interface AtomicType extends UnqualifiedObjectType {

	UnqualifiedObjectType getBaseType();

}
