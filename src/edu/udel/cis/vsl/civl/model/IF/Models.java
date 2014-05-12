/**
 * 
 */
package edu.udel.cis.vsl.civl.model.IF;

import edu.udel.cis.vsl.civl.model.common.CommonModelBuilder;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

/**
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Models {

	public static ModelBuilder newModelBuilder(SymbolicUniverse universe) {
		return new CommonModelBuilder(universe);
	}

	public static ModelBuilder newModelBuilder(ModelFactory factory) {
		return new CommonModelBuilder(factory.universe(), factory);
	}

}
