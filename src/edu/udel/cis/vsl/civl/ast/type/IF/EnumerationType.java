package edu.udel.cis.vsl.civl.ast.type.IF;

import java.util.Iterator;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.entity.IF.Enumeration;
import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;

/**
 * An enumeration type. An enumeration type consists of a tag and a sequence of
 * enumerators. Each enumerator consists of an identifier and an optional
 * constant expression.
 * 
 * @author siegel
 */
public interface EnumerationType extends IntegerType {

	/**
	 * Returns the tag of this enumeration type. This is the string used in the
	 * declaration of the type, i.e., in "enum foo {...}", "foo" is the tag.
	 * 
	 * @return the tag of this enumeration type
	 */
	String getTag();

	/**
	 * Returns the number of enumerators specified in this enumeration type.
	 * 
	 * @exception RuntimeException
	 *                if the type is not complete, i.e., the enumerators have
	 *                not yet been specified
	 * 
	 * @return the number of enumerators in the type
	 */
	int getNumEnumerators();

	/**
	 * Returns the index-th enumerator defined in the type.
	 * 
	 * @param index
	 *            an integer between 0 and the number of enumerators minus 1,
	 *            inclusive
	 * @return the index-th enumerator
	 * 
	 * @exception RuntimeException
	 *                if the type is not complete
	 */
	Enumerator getEnumerator(int index);

	/**
	 * Returns the sequence of enumerators for this enumerated type. Each
	 * enumerator consists of a name and optional constant expression. If the
	 * optional constant expression is absent, it will be null.
	 * 
	 * This will return null if the type is incomplete, i.e., the enumerators
	 * have not yet been specified
	 * 
	 * @return the sequence node for the enumerators of this type, or null
	 */
	Iterator<Enumerator> getEnumerators();

	/**
	 * Completes this enumeration type by specifying the contents of the type,
	 * i.e., the list or enumerator constants.
	 * 
	 * @param enumerators
	 *            an ordered list of enumerators which comprise the type
	 * 
	 * @exception RuntimeException
	 *                if the type is already complete
	 */
	void complete(List<Enumerator> enumerators);

	void setEntity(Enumeration enumeration);
	
	Enumeration getEntity();

}
