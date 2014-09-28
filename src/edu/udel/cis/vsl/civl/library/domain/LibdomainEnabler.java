package edu.udel.cis.vsl.civl.library.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicAnalyzer;

public class LibdomainEnabler extends BaseLibraryEnabler implements
		LibraryEnabler {

	public LibdomainEnabler(String name, Enabler primaryEnabler,
			Evaluator evaluator, ModelFactory modelFactory,
			SymbolicUtility symbolicUtil, SymbolicAnalyzer symbolicAnalyzer) {
		super(name, primaryEnabler, evaluator, modelFactory, symbolicUtil,
				symbolicAnalyzer);
	}

	/**
	 * The returned collection should have such structure: par1:{0:n1, 1:n2,
	 * 2:n2.........numEle:nx}; par2{...}; For every element, it should know
	 * which process owns itself.
	 * 
	 * @param numEle
	 * @param numPart
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<Map<Integer, List<Integer>>> getAllPartitions(int numEle,
			int numPart) {
		List<Map<Integer, List<Integer>>> result;
		List<Map<Integer, List<Integer>>> tmpResult = new LinkedList<>();

		result = this.getAllPartitionsWorker(0, numEle, numPart);
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).keySet().size() == numPart)
				tmpResult.add(result.get(i));
		}
		return tmpResult;
	}

	private List<Map<Integer, List<Integer>>> getAllPartitionsWorker(
			int element, int numEle, int numPart) {
		List<Map<Integer, List<Integer>>> result;
		Map<Integer, List<Integer>> singlePartition;

		if (element == numEle - 1) {
			result = new LinkedList<>();
			for (int i = 0; i < numPart; i++) {
				singlePartition = new HashMap<>();
				singlePartition.put(i, Arrays.asList(element));
				result.add(singlePartition);
			}
			return result;
		} else {
			List<Map<Integer, List<Integer>>> tmpResult;

			result = this.getAllPartitionsWorker(element + 1, numEle, numPart);
			tmpResult = new LinkedList<>();
			for (int i = 0; i < result.size(); i++) {
				singlePartition = result.get(i);
				for (int j = 0; j < numPart; j++) {
					Map<Integer, List<Integer>> tmpSinglePartition;
					List<Integer> tmpElements;

					tmpSinglePartition = new HashMap<>(singlePartition);
					if (singlePartition.containsKey(j)) {
						tmpElements = new LinkedList<>(
								tmpSinglePartition.get(j));
						tmpElements.add(element);
						tmpSinglePartition.put(j, tmpElements);
					} else
						tmpSinglePartition.put(j, Arrays.asList(element));
					tmpResult.add(tmpSinglePartition);
				}
			}
			return tmpResult;
		}
	}
}
