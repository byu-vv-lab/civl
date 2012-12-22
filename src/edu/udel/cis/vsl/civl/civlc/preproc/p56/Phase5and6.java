package edu.udel.cis.vsl.civl.civlc.preproc.p56;

/**
 * The purpose of this class is to perform Translation Phases 5 and 6. Phase 5
 * begins just after all preprocessing directives have been executed. From C11
 * Sec. 5.1.1.2:
 * 
 * <blockquote>
 * <ul>
 * <li>5. Each source character set member and escape sequence in character
 * constants and string literals is converted to the corresponding member of the
 * execution character set; if there is no corresponding member, it is converted
 * to an implementation- defined member other than the null (wide) character.8)</li>
 * <li>6. Adjacent string literal tokens are concatenated.</li>
 * </ul>
 * </blockquote>
 * 
 * Need special character token and string token.  Special structure.
 * Basically what is in value.
 * 
 * Also need to append '\0' character to string literals in Phase 7.
 * 
 * make this its own component
 * 
 * ExecutionCharacter
 * ExecutionString
 * 
 * StringCToken extends CToken
 *   ExecutionCharacter[] getExecutionCharacters();
 * 
 * CharacterCToken extends CToken
 *   ExecutionCharacter getExecutionCharacter();
 *   
 *   CTokenSource transformCharacters(CTokenSource stream);
 * 
 * old stream becomes invalid.
 * 
 *  * 
 * @author siegel
 * 
 */
public class Phase5and6 {

}
