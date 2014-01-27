package edu.udel.cis.vsl.civl.model.IF.type;

import java.util.Collection;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.Identifier;

public interface CIVLUnionType extends CIVLType {
	/**
	 * Is this union type complete?
	 * 
	 * @return true iff this is complete
	 */
	boolean isComplete();

	/**
	 * Returns the number of members in this union type
	 * 
	 * @throws CIVLInternalException
	 *             if the type is not complete
	 * @return the number of fields
	 */
	int numberOfMembers();

	/**
	 * Returns the index-th field of this union type.
	 * 
	 * @param index
	 *            nonnegative integer in range [0,numFields-1].
	 * 
	 * @return the index-th field
	 * @throws CIVLInternalException
	 *             if this type is not complete
	 */
	UnionMember getMember(int index);

	/**
	 * Returns an iterable object over all the members of this union type, in
	 * ascending order.
	 * 
	 * @return A list of the member types in this union.
	 * @throws CIVLInternalException
	 *             if this type is not complete
	 */
	Iterable<UnionMember> members();

	/**
	 * Returns the name of this union type.
	 * 
	 * @return The name of this union.
	 */
	Identifier name();

	/**
	 * Completes this union type by specifying the fields as a collection.
	 * 
	 * @param members
	 *            the members
	 * @throws CIVLInternalException
	 *             if this struct type is already complete
	 */
	void complete(Collection<UnionMember> members);

	/**
	 * Completes this struct type by specifying the fields as an array. The
	 * array is copied.
	 * 
	 * @param fields
	 *            the fields
	 * @throws CIVLInternalException
	 *             if this struct type is already complete
	 */
	void complete(UnionMember[] members);
}
