package edu.udel.cis.vsl.civl.ast.node.IF;

import java.util.Iterator;

import org.antlr.runtime.TokenSource;

import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.token.IF.CToken;

/**
 * A pragma may be included in the AST wherever a statement or an external
 * definition may occur. The pragma is represented as an identifier (the first
 * token to follow the # pragma), followed by some sequence of tokens of length
 * (say) n. We will refer to that sequence as the "pragma body".
 * 
 * @author siegel
 * 
 */
public interface PragmaNode extends ExternalDefinitionNode, StatementNode {

	IdentifierNode getPragmaIdentifier();

	/**
	 * Returns n, the number of tokens following # pragma IDENTIFIER.
	 * 
	 * @return number of tokens in the pragma body
	 */
	int getNumTokens();

	/**
	 * Returns the index-th token in the pragma body.
	 * 
	 * @param index
	 * @return
	 */
	CToken getToken(int index);

	/**
	 * Returns an iterator over the tokens in the pragma body.
	 * 
	 * @return
	 */
	Iterator<CToken> getTokens();

	/**
	 * Returns the tokens of the pragma body as an ANTLR TokenSource, which can
	 * then be fed into an ANTLR parser for syntactic analysis.
	 * 
	 * @return
	 */
	TokenSource getTokenSource();
}
