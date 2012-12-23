package edu.udel.cis.vsl.civl.token.common;

import java.io.File;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.civl.token.IF.Macro;

/**
 * Root class for representing preprocessor macros. This is an abstract class.
 * It has concrete subclasses CommonObjectMacro (for object-like macros) and
 * CommonFunctionMacro (for function-like macros).
 * 
 * @author siegel
 * 
 */
public abstract class CommonMacro implements Macro {

	protected Tree definitionNode;

	protected File file;

	protected CommonMacro(Tree definitionNode, File file) {
		this.definitionNode = definitionNode;
		this.file = file;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public Tree getDefinitionNode() {
		return definitionNode;
	}

	@Override
	public int getNumReplacementTokens() {
		return getBodyNode().getChildCount();
	}

	@Override
	public Token getReplacementToken(int i) {
		return ((CommonTree) getBodyNode().getChild(i)).getToken();
	}

	@Override
	public String getName() {
		return definitionNode.getChild(0).getText();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CommonMacro) {
			CommonMacro that = (CommonMacro) object;

			if (!getName().equals(that.getName()))
				return false;

			int numTokens = getNumReplacementTokens();

			if (numTokens != that.getNumReplacementTokens())
				return false;
			for (int i = 0; i < numTokens; i++) {
				Token t1 = getReplacementToken(i);
				Token t2 = that.getReplacementToken(i);

				if (t1.getType() != t2.getType()
						|| !t1.getText().equals(t2.getText()))
					return false;
			}
			return true;
		}
		return false;
	}
}
