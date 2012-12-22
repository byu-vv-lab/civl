// $ANTLR 3.4 CivlCParser.g 2012-12-22 16:01:06

package edu.udel.cis.vsl.civl.civlc.parse.common;

import java.util.Set;
import java.util.HashSet;
import edu.udel.cis.vsl.civl.civlc.parse.IF.RuntimeParseException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class CivlCParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALIGNAS", "ALIGNOF", "AMPERSAND", "AND", "ARROW", "ASSERT", "ASSIGN", "ASSUME", "AT", "ATOMIC", "AUTO", "BITANDEQ", "BITOR", "BITOREQ", "BITXOR", "BITXOREQ", "BOOL", "BREAK", "BinaryExponentPart", "CASE", "CChar", "CHAR", "CHARACTER_CONSTANT", "CHOOSE", "COLLECTIVE", "COLON", "COMMA", "COMMENT", "COMPLEX", "CONST", "CONTINUE", "DEFAULT", "DEFINED", "DIV", "DIVEQ", "DO", "DOT", "DOUBLE", "DecimalConstant", "DecimalFloatingConstant", "Digit", "ELLIPSIS", "ELSE", "ENUM", "EQUALS", "EXTERN", "EscapeSequence", "ExponentPart", "FLOAT", "FLOATING_CONSTANT", "FOR", "FloatingSuffix", "FractionalConstant", "GENERIC", "GOTO", "GT", "GTE", "HASH", "HASHHASH", "HEADER_NAME", "HexEscape", "HexFractionalConstant", "HexPrefix", "HexQuad", "HexadecimalConstant", "HexadecimalDigit", "HexadecimalFloatingConstant", "IDENTIFIER", "IF", "IMAGINARY", "INLINE", "INPUT", "INT", "INTEGER_CONSTANT", "INVARIANT", "IdentifierNonDigit", "IntegerSuffix", "LCURLY", "LONG", "LPAREN", "LSQUARE", "LT", "LTE", "LongLongSuffix", "LongSuffix", "MINUSMINUS", "MOD", "MODEQ", "NEQ", "NEWLINE", "NORETURN", "NOT", "NewLine", "NonDigit", "NonZeroDigit", "NotLineStart", "OR", "OTHER", "OUTPUT", "OctalConstant", "OctalDigit", "OctalEscape", "PDEFINE", "PELIF", "PELSE", "PENDIF", "PERROR", "PIF", "PIFDEF", "PIFNDEF", "PINCLUDE", "PLINE", "PLUS", "PLUSEQ", "PLUSPLUS", "PP_NUMBER", "PRAGMA", "PROC", "PUNDEF", "QMARK", "RCURLY", "REGISTER", "RESTRICT", "RETURN", "RPAREN", "RSQUARE", "SChar", "SEMI", "SHIFTLEFT", "SHIFTLEFTEQ", "SHIFTRIGHT", "SHIFTRIGHTEQ", "SHORT", "SIGNED", "SIZEOF", "SPAWN", "STAR", "STAREQ", "STATIC", "STATICASSERT", "STRING_LITERAL", "STRUCT", "SUB", "SUBEQ", "SWITCH", "THREADLOCAL", "TILDE", "TYPEDEF", "UNION", "UNSIGNED", "UniversalCharacterName", "UnsignedSuffix", "VOID", "VOLATILE", "WAIT", "WHEN", "WHILE", "WS", "Zero", "BODY", "EXPR", "FILE", "PARAMLIST", "SEQUENCE", "TEXT_BLOCK", "ABSENT", "ABSTRACT_DECLARATOR", "ARGUMENT_LIST", "ARRAY_ELEMENT_DESIGNATOR", "ARRAY_SUFFIX", "BLOCK_ITEM_LIST", "CALL", "CASE_LABELED_STATEMENT", "CAST", "COMPOUND_LITERAL", "COMPOUND_STATEMENT", "DECLARATION", "DECLARATION_LIST", "DECLARATION_SPECIFIERS", "DECLARATOR", "DEFAULT_LABELED_STATEMENT", "DESIGNATED_INITIALIZER", "DESIGNATION", "DIRECT_ABSTRACT_DECLARATOR", "DIRECT_DECLARATOR", "ENUMERATION_CONSTANT", "ENUMERATOR", "ENUMERATOR_LIST", "EXPRESSION_STATEMENT", "FIELD_DESIGNATOR", "FUNCTION_DEFINITION", "FUNCTION_SUFFIX", "GENERIC_ASSOCIATION", "GENERIC_ASSOC_LIST", "IDENTIFIER_LABELED_STATEMENT", "IDENTIFIER_LIST", "INDEX", "INITIALIZER_LIST", "INIT_DECLARATOR", "INIT_DECLARATOR_LIST", "OPERATOR", "PARAMETER_DECLARATION", "PARAMETER_LIST", "PARAMETER_TYPE_LIST", "PARENTHESIZED_EXPRESSION", "POINTER", "POST_DECREMENT", "POST_INCREMENT", "PRE_DECREMENT", "PRE_INCREMENT", "SCALAR_INITIALIZER", "SPECIFIER_QUALIFIER_LIST", "STRUCT_DECLARATION", "STRUCT_DECLARATION_LIST", "STRUCT_DECLARATOR", "STRUCT_DECLARATOR_LIST", "TOKEN_LIST", "TRANSLATION_UNIT", "TYPE", "TYPEDEF_NAME", "TYPE_NAME", "TYPE_QUALIFIER_LIST"
    };

    public static final int EOF=-1;
    public static final int ALIGNAS=4;
    public static final int ALIGNOF=5;
    public static final int AMPERSAND=6;
    public static final int AND=7;
    public static final int ARROW=8;
    public static final int ASSERT=9;
    public static final int ASSIGN=10;
    public static final int ASSUME=11;
    public static final int AT=12;
    public static final int ATOMIC=13;
    public static final int AUTO=14;
    public static final int BITANDEQ=15;
    public static final int BITOR=16;
    public static final int BITOREQ=17;
    public static final int BITXOR=18;
    public static final int BITXOREQ=19;
    public static final int BOOL=20;
    public static final int BREAK=21;
    public static final int BinaryExponentPart=22;
    public static final int CASE=23;
    public static final int CChar=24;
    public static final int CHAR=25;
    public static final int CHARACTER_CONSTANT=26;
    public static final int CHOOSE=27;
    public static final int COLLECTIVE=28;
    public static final int COLON=29;
    public static final int COMMA=30;
    public static final int COMMENT=31;
    public static final int COMPLEX=32;
    public static final int CONST=33;
    public static final int CONTINUE=34;
    public static final int DEFAULT=35;
    public static final int DEFINED=36;
    public static final int DIV=37;
    public static final int DIVEQ=38;
    public static final int DO=39;
    public static final int DOT=40;
    public static final int DOUBLE=41;
    public static final int DecimalConstant=42;
    public static final int DecimalFloatingConstant=43;
    public static final int Digit=44;
    public static final int ELLIPSIS=45;
    public static final int ELSE=46;
    public static final int ENUM=47;
    public static final int EQUALS=48;
    public static final int EXTERN=49;
    public static final int EscapeSequence=50;
    public static final int ExponentPart=51;
    public static final int FLOAT=52;
    public static final int FLOATING_CONSTANT=53;
    public static final int FOR=54;
    public static final int FloatingSuffix=55;
    public static final int FractionalConstant=56;
    public static final int GENERIC=57;
    public static final int GOTO=58;
    public static final int GT=59;
    public static final int GTE=60;
    public static final int HASH=61;
    public static final int HASHHASH=62;
    public static final int HEADER_NAME=63;
    public static final int HexEscape=64;
    public static final int HexFractionalConstant=65;
    public static final int HexPrefix=66;
    public static final int HexQuad=67;
    public static final int HexadecimalConstant=68;
    public static final int HexadecimalDigit=69;
    public static final int HexadecimalFloatingConstant=70;
    public static final int IDENTIFIER=71;
    public static final int IF=72;
    public static final int IMAGINARY=73;
    public static final int INLINE=74;
    public static final int INPUT=75;
    public static final int INT=76;
    public static final int INTEGER_CONSTANT=77;
    public static final int INVARIANT=78;
    public static final int IdentifierNonDigit=79;
    public static final int IntegerSuffix=80;
    public static final int LCURLY=81;
    public static final int LONG=82;
    public static final int LPAREN=83;
    public static final int LSQUARE=84;
    public static final int LT=85;
    public static final int LTE=86;
    public static final int LongLongSuffix=87;
    public static final int LongSuffix=88;
    public static final int MINUSMINUS=89;
    public static final int MOD=90;
    public static final int MODEQ=91;
    public static final int NEQ=92;
    public static final int NEWLINE=93;
    public static final int NORETURN=94;
    public static final int NOT=95;
    public static final int NewLine=96;
    public static final int NonDigit=97;
    public static final int NonZeroDigit=98;
    public static final int NotLineStart=99;
    public static final int OR=100;
    public static final int OTHER=101;
    public static final int OUTPUT=102;
    public static final int OctalConstant=103;
    public static final int OctalDigit=104;
    public static final int OctalEscape=105;
    public static final int PDEFINE=106;
    public static final int PELIF=107;
    public static final int PELSE=108;
    public static final int PENDIF=109;
    public static final int PERROR=110;
    public static final int PIF=111;
    public static final int PIFDEF=112;
    public static final int PIFNDEF=113;
    public static final int PINCLUDE=114;
    public static final int PLINE=115;
    public static final int PLUS=116;
    public static final int PLUSEQ=117;
    public static final int PLUSPLUS=118;
    public static final int PP_NUMBER=119;
    public static final int PRAGMA=120;
    public static final int PROC=121;
    public static final int PUNDEF=122;
    public static final int QMARK=123;
    public static final int RCURLY=124;
    public static final int REGISTER=125;
    public static final int RESTRICT=126;
    public static final int RETURN=127;
    public static final int RPAREN=128;
    public static final int RSQUARE=129;
    public static final int SChar=130;
    public static final int SEMI=131;
    public static final int SHIFTLEFT=132;
    public static final int SHIFTLEFTEQ=133;
    public static final int SHIFTRIGHT=134;
    public static final int SHIFTRIGHTEQ=135;
    public static final int SHORT=136;
    public static final int SIGNED=137;
    public static final int SIZEOF=138;
    public static final int SPAWN=139;
    public static final int STAR=140;
    public static final int STAREQ=141;
    public static final int STATIC=142;
    public static final int STATICASSERT=143;
    public static final int STRING_LITERAL=144;
    public static final int STRUCT=145;
    public static final int SUB=146;
    public static final int SUBEQ=147;
    public static final int SWITCH=148;
    public static final int THREADLOCAL=149;
    public static final int TILDE=150;
    public static final int TYPEDEF=151;
    public static final int UNION=152;
    public static final int UNSIGNED=153;
    public static final int UniversalCharacterName=154;
    public static final int UnsignedSuffix=155;
    public static final int VOID=156;
    public static final int VOLATILE=157;
    public static final int WAIT=158;
    public static final int WHEN=159;
    public static final int WHILE=160;
    public static final int WS=161;
    public static final int Zero=162;
    public static final int BODY=163;
    public static final int EXPR=164;
    public static final int FILE=165;
    public static final int PARAMLIST=166;
    public static final int SEQUENCE=167;
    public static final int TEXT_BLOCK=168;
    public static final int ABSENT=169;
    public static final int ABSTRACT_DECLARATOR=170;
    public static final int ARGUMENT_LIST=171;
    public static final int ARRAY_ELEMENT_DESIGNATOR=172;
    public static final int ARRAY_SUFFIX=173;
    public static final int BLOCK_ITEM_LIST=174;
    public static final int CALL=175;
    public static final int CASE_LABELED_STATEMENT=176;
    public static final int CAST=177;
    public static final int COMPOUND_LITERAL=178;
    public static final int COMPOUND_STATEMENT=179;
    public static final int DECLARATION=180;
    public static final int DECLARATION_LIST=181;
    public static final int DECLARATION_SPECIFIERS=182;
    public static final int DECLARATOR=183;
    public static final int DEFAULT_LABELED_STATEMENT=184;
    public static final int DESIGNATED_INITIALIZER=185;
    public static final int DESIGNATION=186;
    public static final int DIRECT_ABSTRACT_DECLARATOR=187;
    public static final int DIRECT_DECLARATOR=188;
    public static final int ENUMERATION_CONSTANT=189;
    public static final int ENUMERATOR=190;
    public static final int ENUMERATOR_LIST=191;
    public static final int EXPRESSION_STATEMENT=192;
    public static final int FIELD_DESIGNATOR=193;
    public static final int FUNCTION_DEFINITION=194;
    public static final int FUNCTION_SUFFIX=195;
    public static final int GENERIC_ASSOCIATION=196;
    public static final int GENERIC_ASSOC_LIST=197;
    public static final int IDENTIFIER_LABELED_STATEMENT=198;
    public static final int IDENTIFIER_LIST=199;
    public static final int INDEX=200;
    public static final int INITIALIZER_LIST=201;
    public static final int INIT_DECLARATOR=202;
    public static final int INIT_DECLARATOR_LIST=203;
    public static final int OPERATOR=204;
    public static final int PARAMETER_DECLARATION=205;
    public static final int PARAMETER_LIST=206;
    public static final int PARAMETER_TYPE_LIST=207;
    public static final int PARENTHESIZED_EXPRESSION=208;
    public static final int POINTER=209;
    public static final int POST_DECREMENT=210;
    public static final int POST_INCREMENT=211;
    public static final int PRE_DECREMENT=212;
    public static final int PRE_INCREMENT=213;
    public static final int SCALAR_INITIALIZER=214;
    public static final int SPECIFIER_QUALIFIER_LIST=215;
    public static final int STRUCT_DECLARATION=216;
    public static final int STRUCT_DECLARATION_LIST=217;
    public static final int STRUCT_DECLARATOR=218;
    public static final int STRUCT_DECLARATOR_LIST=219;
    public static final int TOKEN_LIST=220;
    public static final int TRANSLATION_UNIT=221;
    public static final int TYPE=222;
    public static final int TYPEDEF_NAME=223;
    public static final int TYPE_NAME=224;
    public static final int TYPE_QUALIFIER_LIST=225;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators

    protected static class Symbols_scope {
        Set<String> types;
        Set<String> enumerationConstants;
        boolean isFunctionDefinition;
    }
    protected Stack Symbols_stack = new Stack();


    protected static class DeclarationScope_scope {
        boolean isTypedef;
    }
    protected Stack DeclarationScope_stack = new Stack();



    public CivlCParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public CivlCParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return CivlCParser.tokenNames; }
    public String getGrammarFileName() { return "CivlCParser.g"; }


    	@Override
    	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
    		String hdr = getErrorHeader(e);
    		String msg = getErrorMessage(e, tokenNames);
    		
    		throw new RuntimeParseException(hdr+" "+msg, e.token);
    	}

    	@Override
    	public void emitErrorMessage(String msg) { // don't try to recover!
    	    throw new RuntimeParseException(msg);
    	}

    	boolean isTypeName(String name) {
    		for (Object scope : Symbols_stack)
    			if (((Symbols_scope)scope).types.contains(name)) return true;
    		return false;
    	}
    	
    	boolean isEnumerationConstant(String name) {
    		boolean answer = false;
    		
    		// System.err.print("Is "+name+" an enumeration constant: ");
    		for (Object scope : Symbols_stack) {
    			if (((Symbols_scope)scope).enumerationConstants.contains(name)) {
    				answer=true;
    				break;
    			}
    		}
    		// System.err.println(answer);
    		// System.err.flush();
    		return answer;
    	}	


    public static class constant_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constant"
    // CivlCParser.g:147:1: constant : ( enumerationConstant | INTEGER_CONSTANT | FLOATING_CONSTANT | CHARACTER_CONSTANT );
    public final CivlCParser.constant_return constant() throws RecognitionException {
        CivlCParser.constant_return retval = new CivlCParser.constant_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token INTEGER_CONSTANT2=null;
        Token FLOATING_CONSTANT3=null;
        Token CHARACTER_CONSTANT4=null;
        CivlCParser.enumerationConstant_return enumerationConstant1 =null;


        Object INTEGER_CONSTANT2_tree=null;
        Object FLOATING_CONSTANT3_tree=null;
        Object CHARACTER_CONSTANT4_tree=null;

        try {
            // CivlCParser.g:148:2: ( enumerationConstant | INTEGER_CONSTANT | FLOATING_CONSTANT | CHARACTER_CONSTANT )
            int alt1=4;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt1=1;
                }
                break;
            case INTEGER_CONSTANT:
                {
                alt1=2;
                }
                break;
            case FLOATING_CONSTANT:
                {
                alt1=3;
                }
                break;
            case CHARACTER_CONSTANT:
                {
                alt1=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // CivlCParser.g:148:4: enumerationConstant
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_enumerationConstant_in_constant584);
                    enumerationConstant1=enumerationConstant();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumerationConstant1.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:149:4: INTEGER_CONSTANT
                    {
                    root_0 = (Object)adaptor.nil();


                    INTEGER_CONSTANT2=(Token)match(input,INTEGER_CONSTANT,FOLLOW_INTEGER_CONSTANT_in_constant589); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTEGER_CONSTANT2_tree = 
                    (Object)adaptor.create(INTEGER_CONSTANT2)
                    ;
                    adaptor.addChild(root_0, INTEGER_CONSTANT2_tree);
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:150:4: FLOATING_CONSTANT
                    {
                    root_0 = (Object)adaptor.nil();


                    FLOATING_CONSTANT3=(Token)match(input,FLOATING_CONSTANT,FOLLOW_FLOATING_CONSTANT_in_constant594); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOATING_CONSTANT3_tree = 
                    (Object)adaptor.create(FLOATING_CONSTANT3)
                    ;
                    adaptor.addChild(root_0, FLOATING_CONSTANT3_tree);
                    }

                    }
                    break;
                case 4 :
                    // CivlCParser.g:151:4: CHARACTER_CONSTANT
                    {
                    root_0 = (Object)adaptor.nil();


                    CHARACTER_CONSTANT4=(Token)match(input,CHARACTER_CONSTANT,FOLLOW_CHARACTER_CONSTANT_in_constant599); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHARACTER_CONSTANT4_tree = 
                    (Object)adaptor.create(CHARACTER_CONSTANT4)
                    ;
                    adaptor.addChild(root_0, CHARACTER_CONSTANT4_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "constant"


    public static class enumerationConstant_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumerationConstant"
    // CivlCParser.g:154:1: enumerationConstant :{...}? IDENTIFIER -> ^( ENUMERATION_CONSTANT IDENTIFIER ) ;
    public final CivlCParser.enumerationConstant_return enumerationConstant() throws RecognitionException {
        CivlCParser.enumerationConstant_return retval = new CivlCParser.enumerationConstant_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER5=null;

        Object IDENTIFIER5_tree=null;
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // CivlCParser.g:155:2: ({...}? IDENTIFIER -> ^( ENUMERATION_CONSTANT IDENTIFIER ) )
            // CivlCParser.g:155:4: {...}? IDENTIFIER
            {
            if ( !((isEnumerationConstant(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "enumerationConstant", "isEnumerationConstant(input.LT(1).getText())");
            }

            IDENTIFIER5=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumerationConstant612); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER5);


            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 155:63: -> ^( ENUMERATION_CONSTANT IDENTIFIER )
            {
                // CivlCParser.g:156:4: ^( ENUMERATION_CONSTANT IDENTIFIER )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ENUMERATION_CONSTANT, "ENUMERATION_CONSTANT")
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENTIFIER.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "enumerationConstant"


    public static class primaryExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primaryExpression"
    // CivlCParser.g:160:1: primaryExpression : ( constant | IDENTIFIER | STRING_LITERAL | LPAREN expression RPAREN -> ^( PARENTHESIZED_EXPRESSION LPAREN expression RPAREN ) | genericSelection );
    public final CivlCParser.primaryExpression_return primaryExpression() throws RecognitionException {
        CivlCParser.primaryExpression_return retval = new CivlCParser.primaryExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER7=null;
        Token STRING_LITERAL8=null;
        Token LPAREN9=null;
        Token RPAREN11=null;
        CivlCParser.constant_return constant6 =null;

        CivlCParser.expression_return expression10 =null;

        CivlCParser.genericSelection_return genericSelection12 =null;


        Object IDENTIFIER7_tree=null;
        Object STRING_LITERAL8_tree=null;
        Object LPAREN9_tree=null;
        Object RPAREN11_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // CivlCParser.g:161:2: ( constant | IDENTIFIER | STRING_LITERAL | LPAREN expression RPAREN -> ^( PARENTHESIZED_EXPRESSION LPAREN expression RPAREN ) | genericSelection )
            int alt2=5;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA2_1 = input.LA(2);

                if ( ((isEnumerationConstant(input.LT(1).getText()))) ) {
                    alt2=1;
                }
                else if ( (true) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;

                }
                }
                break;
            case CHARACTER_CONSTANT:
            case FLOATING_CONSTANT:
            case INTEGER_CONSTANT:
                {
                alt2=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt2=3;
                }
                break;
            case LPAREN:
                {
                alt2=4;
                }
                break;
            case GENERIC:
                {
                alt2=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // CivlCParser.g:161:4: constant
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_constant_in_primaryExpression636);
                    constant6=constant();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constant6.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:163:4: IDENTIFIER
                    {
                    root_0 = (Object)adaptor.nil();


                    IDENTIFIER7=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_primaryExpression645); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER7_tree = 
                    (Object)adaptor.create(IDENTIFIER7)
                    ;
                    adaptor.addChild(root_0, IDENTIFIER7_tree);
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:164:4: STRING_LITERAL
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING_LITERAL8=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_primaryExpression650); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL8_tree = 
                    (Object)adaptor.create(STRING_LITERAL8)
                    ;
                    adaptor.addChild(root_0, STRING_LITERAL8_tree);
                    }

                    }
                    break;
                case 4 :
                    // CivlCParser.g:165:4: LPAREN expression RPAREN
                    {
                    LPAREN9=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_primaryExpression655); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN9);


                    pushFollow(FOLLOW_expression_in_primaryExpression657);
                    expression10=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression10.getTree());

                    RPAREN11=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_primaryExpression659); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN11);


                    // AST REWRITE
                    // elements: LPAREN, expression, RPAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 166:4: -> ^( PARENTHESIZED_EXPRESSION LPAREN expression RPAREN )
                    {
                        // CivlCParser.g:166:7: ^( PARENTHESIZED_EXPRESSION LPAREN expression RPAREN )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARENTHESIZED_EXPRESSION, "PARENTHESIZED_EXPRESSION")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LPAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RPAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // CivlCParser.g:167:4: genericSelection
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_genericSelection_in_primaryExpression680);
                    genericSelection12=genericSelection();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, genericSelection12.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "primaryExpression"


    public static class genericSelection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericSelection"
    // CivlCParser.g:172:1: genericSelection : GENERIC LPAREN assignmentExpression COMMA genericAssocList RPAREN -> ^( GENERIC assignmentExpression genericAssocList ) ;
    public final CivlCParser.genericSelection_return genericSelection() throws RecognitionException {
        CivlCParser.genericSelection_return retval = new CivlCParser.genericSelection_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token GENERIC13=null;
        Token LPAREN14=null;
        Token COMMA16=null;
        Token RPAREN18=null;
        CivlCParser.assignmentExpression_return assignmentExpression15 =null;

        CivlCParser.genericAssocList_return genericAssocList17 =null;


        Object GENERIC13_tree=null;
        Object LPAREN14_tree=null;
        Object COMMA16_tree=null;
        Object RPAREN18_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_GENERIC=new RewriteRuleTokenStream(adaptor,"token GENERIC");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_genericAssocList=new RewriteRuleSubtreeStream(adaptor,"rule genericAssocList");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        try {
            // CivlCParser.g:173:2: ( GENERIC LPAREN assignmentExpression COMMA genericAssocList RPAREN -> ^( GENERIC assignmentExpression genericAssocList ) )
            // CivlCParser.g:173:4: GENERIC LPAREN assignmentExpression COMMA genericAssocList RPAREN
            {
            GENERIC13=(Token)match(input,GENERIC,FOLLOW_GENERIC_in_genericSelection694); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_GENERIC.add(GENERIC13);


            LPAREN14=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_genericSelection696); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN14);


            pushFollow(FOLLOW_assignmentExpression_in_genericSelection698);
            assignmentExpression15=assignmentExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression15.getTree());

            COMMA16=(Token)match(input,COMMA,FOLLOW_COMMA_in_genericSelection700); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COMMA.add(COMMA16);


            pushFollow(FOLLOW_genericAssocList_in_genericSelection702);
            genericAssocList17=genericAssocList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_genericAssocList.add(genericAssocList17.getTree());

            RPAREN18=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_genericSelection707); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN18);


            // AST REWRITE
            // elements: assignmentExpression, GENERIC, genericAssocList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 175:4: -> ^( GENERIC assignmentExpression genericAssocList )
            {
                // CivlCParser.g:175:7: ^( GENERIC assignmentExpression genericAssocList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_GENERIC.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                adaptor.addChild(root_1, stream_genericAssocList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "genericSelection"


    public static class genericAssocList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericAssocList"
    // CivlCParser.g:179:1: genericAssocList : genericAssociation ( COMMA genericAssociation )* -> ^( GENERIC_ASSOC_LIST ( genericAssociation )+ ) ;
    public final CivlCParser.genericAssocList_return genericAssocList() throws RecognitionException {
        CivlCParser.genericAssocList_return retval = new CivlCParser.genericAssocList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA20=null;
        CivlCParser.genericAssociation_return genericAssociation19 =null;

        CivlCParser.genericAssociation_return genericAssociation21 =null;


        Object COMMA20_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_genericAssociation=new RewriteRuleSubtreeStream(adaptor,"rule genericAssociation");
        try {
            // CivlCParser.g:180:2: ( genericAssociation ( COMMA genericAssociation )* -> ^( GENERIC_ASSOC_LIST ( genericAssociation )+ ) )
            // CivlCParser.g:180:4: genericAssociation ( COMMA genericAssociation )*
            {
            pushFollow(FOLLOW_genericAssociation_in_genericAssocList733);
            genericAssociation19=genericAssociation();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_genericAssociation.add(genericAssociation19.getTree());

            // CivlCParser.g:180:23: ( COMMA genericAssociation )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // CivlCParser.g:180:24: COMMA genericAssociation
            	    {
            	    COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_genericAssocList736); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA20);


            	    pushFollow(FOLLOW_genericAssociation_in_genericAssocList738);
            	    genericAssociation21=genericAssociation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_genericAssociation.add(genericAssociation21.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // AST REWRITE
            // elements: genericAssociation
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 181:4: -> ^( GENERIC_ASSOC_LIST ( genericAssociation )+ )
            {
                // CivlCParser.g:181:7: ^( GENERIC_ASSOC_LIST ( genericAssociation )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(GENERIC_ASSOC_LIST, "GENERIC_ASSOC_LIST")
                , root_1);

                if ( !(stream_genericAssociation.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_genericAssociation.hasNext() ) {
                    adaptor.addChild(root_1, stream_genericAssociation.nextTree());

                }
                stream_genericAssociation.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "genericAssocList"


    public static class genericAssociation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericAssociation"
    // CivlCParser.g:185:1: genericAssociation : ( typeName COLON assignmentExpression -> ^( GENERIC_ASSOCIATION typeName assignmentExpression ) | DEFAULT COLON assignmentExpression -> ^( GENERIC_ASSOCIATION DEFAULT assignmentExpression ) );
    public final CivlCParser.genericAssociation_return genericAssociation() throws RecognitionException {
        CivlCParser.genericAssociation_return retval = new CivlCParser.genericAssociation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COLON23=null;
        Token DEFAULT25=null;
        Token COLON26=null;
        CivlCParser.typeName_return typeName22 =null;

        CivlCParser.assignmentExpression_return assignmentExpression24 =null;

        CivlCParser.assignmentExpression_return assignmentExpression27 =null;


        Object COLON23_tree=null;
        Object DEFAULT25_tree=null;
        Object COLON26_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_DEFAULT=new RewriteRuleTokenStream(adaptor,"token DEFAULT");
        RewriteRuleSubtreeStream stream_typeName=new RewriteRuleSubtreeStream(adaptor,"rule typeName");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        try {
            // CivlCParser.g:186:2: ( typeName COLON assignmentExpression -> ^( GENERIC_ASSOCIATION typeName assignmentExpression ) | DEFAULT COLON assignmentExpression -> ^( GENERIC_ASSOCIATION DEFAULT assignmentExpression ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ATOMIC||LA4_0==BOOL||LA4_0==CHAR||(LA4_0 >= COMPLEX && LA4_0 <= CONST)||LA4_0==DOUBLE||LA4_0==ENUM||LA4_0==FLOAT||LA4_0==IDENTIFIER||(LA4_0 >= INPUT && LA4_0 <= INT)||LA4_0==LONG||LA4_0==OUTPUT||LA4_0==PROC||LA4_0==RESTRICT||(LA4_0 >= SHORT && LA4_0 <= SIGNED)||LA4_0==STRUCT||(LA4_0 >= UNION && LA4_0 <= UNSIGNED)||(LA4_0 >= VOID && LA4_0 <= VOLATILE)) ) {
                alt4=1;
            }
            else if ( (LA4_0==DEFAULT) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // CivlCParser.g:186:4: typeName COLON assignmentExpression
                    {
                    pushFollow(FOLLOW_typeName_in_genericAssociation765);
                    typeName22=typeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeName.add(typeName22.getTree());

                    COLON23=(Token)match(input,COLON,FOLLOW_COLON_in_genericAssociation767); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON23);


                    pushFollow(FOLLOW_assignmentExpression_in_genericAssociation769);
                    assignmentExpression24=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression24.getTree());

                    // AST REWRITE
                    // elements: assignmentExpression, typeName
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 187:4: -> ^( GENERIC_ASSOCIATION typeName assignmentExpression )
                    {
                        // CivlCParser.g:187:7: ^( GENERIC_ASSOCIATION typeName assignmentExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(GENERIC_ASSOCIATION, "GENERIC_ASSOCIATION")
                        , root_1);

                        adaptor.addChild(root_1, stream_typeName.nextTree());

                        adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:188:4: DEFAULT COLON assignmentExpression
                    {
                    DEFAULT25=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_genericAssociation787); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DEFAULT.add(DEFAULT25);


                    COLON26=(Token)match(input,COLON,FOLLOW_COLON_in_genericAssociation789); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON26);


                    pushFollow(FOLLOW_assignmentExpression_in_genericAssociation791);
                    assignmentExpression27=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression27.getTree());

                    // AST REWRITE
                    // elements: DEFAULT, assignmentExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 189:4: -> ^( GENERIC_ASSOCIATION DEFAULT assignmentExpression )
                    {
                        // CivlCParser.g:189:7: ^( GENERIC_ASSOCIATION DEFAULT assignmentExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(GENERIC_ASSOCIATION, "GENERIC_ASSOCIATION")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_DEFAULT.nextNode()
                        );

                        adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "genericAssociation"


    public static class postfixExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "postfixExpression"
    // CivlCParser.g:193:1: postfixExpression : ( postfixExpressionRoot -> postfixExpressionRoot ) (l= LSQUARE expression RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression expression ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( CALL LPAREN $postfixExpression argumentExpressionList RPAREN ) | DOT IDENTIFIER -> ^( DOT $postfixExpression IDENTIFIER ) | ARROW IDENTIFIER -> ^( ARROW $postfixExpression IDENTIFIER ) |p= PLUSPLUS -> ^( OPERATOR POST_INCREMENT[$p] ^( ARGUMENT_LIST $postfixExpression) ) | AT IDENTIFIER -> ^( AT $postfixExpression IDENTIFIER ) |m= MINUSMINUS -> ^( OPERATOR POST_DECREMENT[$m] ^( ARGUMENT_LIST $postfixExpression) ) )* ;
    public final CivlCParser.postfixExpression_return postfixExpression() throws RecognitionException {
        CivlCParser.postfixExpression_return retval = new CivlCParser.postfixExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token l=null;
        Token p=null;
        Token m=null;
        Token RSQUARE30=null;
        Token LPAREN31=null;
        Token RPAREN33=null;
        Token DOT34=null;
        Token IDENTIFIER35=null;
        Token ARROW36=null;
        Token IDENTIFIER37=null;
        Token AT38=null;
        Token IDENTIFIER39=null;
        CivlCParser.postfixExpressionRoot_return postfixExpressionRoot28 =null;

        CivlCParser.expression_return expression29 =null;

        CivlCParser.argumentExpressionList_return argumentExpressionList32 =null;


        Object l_tree=null;
        Object p_tree=null;
        Object m_tree=null;
        Object RSQUARE30_tree=null;
        Object LPAREN31_tree=null;
        Object RPAREN33_tree=null;
        Object DOT34_tree=null;
        Object IDENTIFIER35_tree=null;
        Object ARROW36_tree=null;
        Object IDENTIFIER37_tree=null;
        Object AT38_tree=null;
        Object IDENTIFIER39_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
        RewriteRuleTokenStream stream_MINUSMINUS=new RewriteRuleTokenStream(adaptor,"token MINUSMINUS");
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
        RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_PLUSPLUS=new RewriteRuleTokenStream(adaptor,"token PLUSPLUS");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_postfixExpressionRoot=new RewriteRuleSubtreeStream(adaptor,"rule postfixExpressionRoot");
        RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");
        try {
            // CivlCParser.g:194:2: ( ( postfixExpressionRoot -> postfixExpressionRoot ) (l= LSQUARE expression RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression expression ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( CALL LPAREN $postfixExpression argumentExpressionList RPAREN ) | DOT IDENTIFIER -> ^( DOT $postfixExpression IDENTIFIER ) | ARROW IDENTIFIER -> ^( ARROW $postfixExpression IDENTIFIER ) |p= PLUSPLUS -> ^( OPERATOR POST_INCREMENT[$p] ^( ARGUMENT_LIST $postfixExpression) ) | AT IDENTIFIER -> ^( AT $postfixExpression IDENTIFIER ) |m= MINUSMINUS -> ^( OPERATOR POST_DECREMENT[$m] ^( ARGUMENT_LIST $postfixExpression) ) )* )
            // CivlCParser.g:194:4: ( postfixExpressionRoot -> postfixExpressionRoot ) (l= LSQUARE expression RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression expression ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( CALL LPAREN $postfixExpression argumentExpressionList RPAREN ) | DOT IDENTIFIER -> ^( DOT $postfixExpression IDENTIFIER ) | ARROW IDENTIFIER -> ^( ARROW $postfixExpression IDENTIFIER ) |p= PLUSPLUS -> ^( OPERATOR POST_INCREMENT[$p] ^( ARGUMENT_LIST $postfixExpression) ) | AT IDENTIFIER -> ^( AT $postfixExpression IDENTIFIER ) |m= MINUSMINUS -> ^( OPERATOR POST_DECREMENT[$m] ^( ARGUMENT_LIST $postfixExpression) ) )*
            {
            // CivlCParser.g:194:4: ( postfixExpressionRoot -> postfixExpressionRoot )
            // CivlCParser.g:194:5: postfixExpressionRoot
            {
            pushFollow(FOLLOW_postfixExpressionRoot_in_postfixExpression818);
            postfixExpressionRoot28=postfixExpressionRoot();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_postfixExpressionRoot.add(postfixExpressionRoot28.getTree());

            // AST REWRITE
            // elements: postfixExpressionRoot
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 194:27: -> postfixExpressionRoot
            {
                adaptor.addChild(root_0, stream_postfixExpressionRoot.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:195:4: (l= LSQUARE expression RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression expression ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( CALL LPAREN $postfixExpression argumentExpressionList RPAREN ) | DOT IDENTIFIER -> ^( DOT $postfixExpression IDENTIFIER ) | ARROW IDENTIFIER -> ^( ARROW $postfixExpression IDENTIFIER ) |p= PLUSPLUS -> ^( OPERATOR POST_INCREMENT[$p] ^( ARGUMENT_LIST $postfixExpression) ) | AT IDENTIFIER -> ^( AT $postfixExpression IDENTIFIER ) |m= MINUSMINUS -> ^( OPERATOR POST_DECREMENT[$m] ^( ARGUMENT_LIST $postfixExpression) ) )*
            loop5:
            do {
                int alt5=8;
                switch ( input.LA(1) ) {
                case LSQUARE:
                    {
                    alt5=1;
                    }
                    break;
                case LPAREN:
                    {
                    alt5=2;
                    }
                    break;
                case DOT:
                    {
                    alt5=3;
                    }
                    break;
                case ARROW:
                    {
                    alt5=4;
                    }
                    break;
                case PLUSPLUS:
                    {
                    alt5=5;
                    }
                    break;
                case AT:
                    {
                    alt5=6;
                    }
                    break;
                case MINUSMINUS:
                    {
                    alt5=7;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // CivlCParser.g:195:6: l= LSQUARE expression RSQUARE
            	    {
            	    l=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_postfixExpression832); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_LSQUARE.add(l);


            	    pushFollow(FOLLOW_expression_in_postfixExpression834);
            	    expression29=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression29.getTree());

            	    RSQUARE30=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_postfixExpression836); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE30);


            	    // AST REWRITE
            	    // elements: expression, RSQUARE, postfixExpression
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 196:6: -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression expression ) RSQUARE )
            	    {
            	        // CivlCParser.g:196:9: ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression expression ) RSQUARE )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        (Object)adaptor.create(INDEX, l)
            	        );

            	        // CivlCParser.g:198:13: ^( ARGUMENT_LIST $postfixExpression expression )
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_expression.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_1, 
            	        stream_RSQUARE.nextNode()
            	        );

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 2 :
            	    // CivlCParser.g:200:6: LPAREN argumentExpressionList RPAREN
            	    {
            	    LPAREN31=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_postfixExpression904); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN31);


            	    pushFollow(FOLLOW_argumentExpressionList_in_postfixExpression906);
            	    argumentExpressionList32=argumentExpressionList();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList32.getTree());

            	    RPAREN33=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_postfixExpression908); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN33);


            	    // AST REWRITE
            	    // elements: argumentExpressionList, RPAREN, LPAREN, postfixExpression
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 201:6: -> ^( CALL LPAREN $postfixExpression argumentExpressionList RPAREN )
            	    {
            	        // CivlCParser.g:201:9: ^( CALL LPAREN $postfixExpression argumentExpressionList RPAREN )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(CALL, "CALL")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_LPAREN.nextNode()
            	        );

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());

            	        adaptor.addChild(root_1, 
            	        stream_RPAREN.nextNode()
            	        );

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 3 :
            	    // CivlCParser.g:202:6: DOT IDENTIFIER
            	    {
            	    DOT34=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression935); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(DOT34);


            	    IDENTIFIER35=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_postfixExpression937); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER35);


            	    // AST REWRITE
            	    // elements: IDENTIFIER, postfixExpression, DOT
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 203:6: -> ^( DOT $postfixExpression IDENTIFIER )
            	    {
            	        // CivlCParser.g:203:9: ^( DOT $postfixExpression IDENTIFIER )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        stream_DOT.nextNode()
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, 
            	        stream_IDENTIFIER.nextNode()
            	        );

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 4 :
            	    // CivlCParser.g:204:6: ARROW IDENTIFIER
            	    {
            	    ARROW36=(Token)match(input,ARROW,FOLLOW_ARROW_in_postfixExpression960); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ARROW.add(ARROW36);


            	    IDENTIFIER37=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_postfixExpression962); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER37);


            	    // AST REWRITE
            	    // elements: IDENTIFIER, postfixExpression, ARROW
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 205:6: -> ^( ARROW $postfixExpression IDENTIFIER )
            	    {
            	        // CivlCParser.g:205:9: ^( ARROW $postfixExpression IDENTIFIER )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        stream_ARROW.nextNode()
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, 
            	        stream_IDENTIFIER.nextNode()
            	        );

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 5 :
            	    // CivlCParser.g:206:6: p= PLUSPLUS
            	    {
            	    p=(Token)match(input,PLUSPLUS,FOLLOW_PLUSPLUS_in_postfixExpression987); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_PLUSPLUS.add(p);


            	    // AST REWRITE
            	    // elements: postfixExpression
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 207:6: -> ^( OPERATOR POST_INCREMENT[$p] ^( ARGUMENT_LIST $postfixExpression) )
            	    {
            	        // CivlCParser.g:207:9: ^( OPERATOR POST_INCREMENT[$p] ^( ARGUMENT_LIST $postfixExpression) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        (Object)adaptor.create(POST_INCREMENT, p)
            	        );

            	        // CivlCParser.g:208:11: ^( ARGUMENT_LIST $postfixExpression)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 6 :
            	    // CivlCParser.g:209:6: AT IDENTIFIER
            	    {
            	    AT38=(Token)match(input,AT,FOLLOW_AT_in_postfixExpression1025); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_AT.add(AT38);


            	    IDENTIFIER39=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_postfixExpression1027); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER39);


            	    // AST REWRITE
            	    // elements: postfixExpression, IDENTIFIER, AT
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 210:6: -> ^( AT $postfixExpression IDENTIFIER )
            	    {
            	        // CivlCParser.g:210:9: ^( AT $postfixExpression IDENTIFIER )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        stream_AT.nextNode()
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, 
            	        stream_IDENTIFIER.nextNode()
            	        );

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 7 :
            	    // CivlCParser.g:211:6: m= MINUSMINUS
            	    {
            	    m=(Token)match(input,MINUSMINUS,FOLLOW_MINUSMINUS_in_postfixExpression1052); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_MINUSMINUS.add(m);


            	    // AST REWRITE
            	    // elements: postfixExpression
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 212:6: -> ^( OPERATOR POST_DECREMENT[$m] ^( ARGUMENT_LIST $postfixExpression) )
            	    {
            	        // CivlCParser.g:212:9: ^( OPERATOR POST_DECREMENT[$m] ^( ARGUMENT_LIST $postfixExpression) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        (Object)adaptor.create(POST_DECREMENT, m)
            	        );

            	        // CivlCParser.g:213:11: ^( ARGUMENT_LIST $postfixExpression)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "postfixExpression"


    public static class postfixExpressionRoot_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "postfixExpressionRoot"
    // CivlCParser.g:230:1: postfixExpressionRoot : ( ( LPAREN typeName RPAREN LCURLY )=> LPAREN typeName RPAREN LCURLY initializerList ( RCURLY | COMMA RCURLY ) -> ^( COMPOUND_LITERAL LPAREN typeName initializerList RCURLY ) | primaryExpression );
    public final CivlCParser.postfixExpressionRoot_return postfixExpressionRoot() throws RecognitionException {
        CivlCParser.postfixExpressionRoot_return retval = new CivlCParser.postfixExpressionRoot_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LPAREN40=null;
        Token RPAREN42=null;
        Token LCURLY43=null;
        Token RCURLY45=null;
        Token COMMA46=null;
        Token RCURLY47=null;
        CivlCParser.typeName_return typeName41 =null;

        CivlCParser.initializerList_return initializerList44 =null;

        CivlCParser.primaryExpression_return primaryExpression48 =null;


        Object LPAREN40_tree=null;
        Object RPAREN42_tree=null;
        Object LCURLY43_tree=null;
        Object RCURLY45_tree=null;
        Object COMMA46_tree=null;
        Object RCURLY47_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_typeName=new RewriteRuleSubtreeStream(adaptor,"rule typeName");
        RewriteRuleSubtreeStream stream_initializerList=new RewriteRuleSubtreeStream(adaptor,"rule initializerList");
        try {
            // CivlCParser.g:231:2: ( ( LPAREN typeName RPAREN LCURLY )=> LPAREN typeName RPAREN LCURLY initializerList ( RCURLY | COMMA RCURLY ) -> ^( COMPOUND_LITERAL LPAREN typeName initializerList RCURLY ) | primaryExpression )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==LPAREN) ) {
                int LA7_1 = input.LA(2);

                if ( (synpred1_CivlCParser()) ) {
                    alt7=1;
                }
                else if ( (true) ) {
                    alt7=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA7_0==CHARACTER_CONSTANT||LA7_0==FLOATING_CONSTANT||LA7_0==GENERIC||LA7_0==IDENTIFIER||LA7_0==INTEGER_CONSTANT||LA7_0==STRING_LITERAL) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // CivlCParser.g:231:4: ( LPAREN typeName RPAREN LCURLY )=> LPAREN typeName RPAREN LCURLY initializerList ( RCURLY | COMMA RCURLY )
                    {
                    LPAREN40=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_postfixExpressionRoot1116); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN40);


                    pushFollow(FOLLOW_typeName_in_postfixExpressionRoot1118);
                    typeName41=typeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeName.add(typeName41.getTree());

                    RPAREN42=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_postfixExpressionRoot1120); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN42);


                    LCURLY43=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_postfixExpressionRoot1122); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY43);


                    pushFollow(FOLLOW_initializerList_in_postfixExpressionRoot1124);
                    initializerList44=initializerList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_initializerList.add(initializerList44.getTree());

                    // CivlCParser.g:233:3: ( RCURLY | COMMA RCURLY )
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==RCURLY) ) {
                        alt6=1;
                    }
                    else if ( (LA6_0==COMMA) ) {
                        alt6=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;

                    }
                    switch (alt6) {
                        case 1 :
                            // CivlCParser.g:233:5: RCURLY
                            {
                            RCURLY45=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_postfixExpressionRoot1131); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY45);


                            }
                            break;
                        case 2 :
                            // CivlCParser.g:234:5: COMMA RCURLY
                            {
                            COMMA46=(Token)match(input,COMMA,FOLLOW_COMMA_in_postfixExpressionRoot1137); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA46);


                            RCURLY47=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_postfixExpressionRoot1139); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY47);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: LPAREN, typeName, RCURLY, initializerList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 236:4: -> ^( COMPOUND_LITERAL LPAREN typeName initializerList RCURLY )
                    {
                        // CivlCParser.g:236:7: ^( COMPOUND_LITERAL LPAREN typeName initializerList RCURLY )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(COMPOUND_LITERAL, "COMPOUND_LITERAL")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LPAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_typeName.nextTree());

                        adaptor.addChild(root_1, stream_initializerList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RCURLY.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:237:4: primaryExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_primaryExpression_in_postfixExpressionRoot1165);
                    primaryExpression48=primaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primaryExpression48.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "postfixExpressionRoot"


    public static class argumentExpressionList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "argumentExpressionList"
    // CivlCParser.g:241:1: argumentExpressionList : ( -> ^( ARGUMENT_LIST ) | assignmentExpression ( COMMA assignmentExpression )* -> ^( ARGUMENT_LIST ( assignmentExpression )+ ) );
    public final CivlCParser.argumentExpressionList_return argumentExpressionList() throws RecognitionException {
        CivlCParser.argumentExpressionList_return retval = new CivlCParser.argumentExpressionList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA50=null;
        CivlCParser.assignmentExpression_return assignmentExpression49 =null;

        CivlCParser.assignmentExpression_return assignmentExpression51 =null;


        Object COMMA50_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        try {
            // CivlCParser.g:242:2: ( -> ^( ARGUMENT_LIST ) | assignmentExpression ( COMMA assignmentExpression )* -> ^( ARGUMENT_LIST ( assignmentExpression )+ ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RPAREN) ) {
                alt9=1;
            }
            else if ( ((LA9_0 >= ALIGNOF && LA9_0 <= AMPERSAND)||LA9_0==CHARACTER_CONSTANT||LA9_0==FLOATING_CONSTANT||LA9_0==GENERIC||LA9_0==IDENTIFIER||LA9_0==INTEGER_CONSTANT||LA9_0==LPAREN||LA9_0==MINUSMINUS||LA9_0==NOT||LA9_0==PLUS||LA9_0==PLUSPLUS||(LA9_0 >= SIZEOF && LA9_0 <= STAR)||LA9_0==STRING_LITERAL||LA9_0==SUB||LA9_0==TILDE) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // CivlCParser.g:242:4: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 242:4: -> ^( ARGUMENT_LIST )
                    {
                        // CivlCParser.g:242:7: ^( ARGUMENT_LIST )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:243:4: assignmentExpression ( COMMA assignmentExpression )*
                    {
                    pushFollow(FOLLOW_assignmentExpression_in_argumentExpressionList1187);
                    assignmentExpression49=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression49.getTree());

                    // CivlCParser.g:243:25: ( COMMA assignmentExpression )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // CivlCParser.g:243:26: COMMA assignmentExpression
                    	    {
                    	    COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_argumentExpressionList1190); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA50);


                    	    pushFollow(FOLLOW_assignmentExpression_in_argumentExpressionList1192);
                    	    assignmentExpression51=assignmentExpression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression51.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: assignmentExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 244:4: -> ^( ARGUMENT_LIST ( assignmentExpression )+ )
                    {
                        // CivlCParser.g:244:7: ^( ARGUMENT_LIST ( assignmentExpression )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_1);

                        if ( !(stream_assignmentExpression.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_assignmentExpression.hasNext() ) {
                            adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                        }
                        stream_assignmentExpression.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "argumentExpressionList"


    public static class unaryExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpression"
    // CivlCParser.g:248:1: unaryExpression : ( postfixExpression |p= PLUSPLUS unaryExpression -> ^( OPERATOR PRE_INCREMENT[$p] ^( ARGUMENT_LIST unaryExpression ) ) |m= MINUSMINUS unaryExpression -> ^( OPERATOR PRE_DECREMENT[$m] ^( ARGUMENT_LIST unaryExpression ) ) | unaryOperator castExpression -> ^( OPERATOR unaryOperator ^( ARGUMENT_LIST castExpression ) ) | ( SIZEOF LPAREN typeName )=> SIZEOF LPAREN typeName RPAREN -> ^( SIZEOF TYPE typeName ) | SIZEOF unaryExpression -> ^( SIZEOF EXPR unaryExpression ) | ALIGNOF LPAREN typeName RPAREN -> ^( ALIGNOF typeName ) | spawnExpression );
    public final CivlCParser.unaryExpression_return unaryExpression() throws RecognitionException {
        CivlCParser.unaryExpression_return retval = new CivlCParser.unaryExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token p=null;
        Token m=null;
        Token SIZEOF57=null;
        Token LPAREN58=null;
        Token RPAREN60=null;
        Token SIZEOF61=null;
        Token ALIGNOF63=null;
        Token LPAREN64=null;
        Token RPAREN66=null;
        CivlCParser.postfixExpression_return postfixExpression52 =null;

        CivlCParser.unaryExpression_return unaryExpression53 =null;

        CivlCParser.unaryExpression_return unaryExpression54 =null;

        CivlCParser.unaryOperator_return unaryOperator55 =null;

        CivlCParser.castExpression_return castExpression56 =null;

        CivlCParser.typeName_return typeName59 =null;

        CivlCParser.unaryExpression_return unaryExpression62 =null;

        CivlCParser.typeName_return typeName65 =null;

        CivlCParser.spawnExpression_return spawnExpression67 =null;


        Object p_tree=null;
        Object m_tree=null;
        Object SIZEOF57_tree=null;
        Object LPAREN58_tree=null;
        Object RPAREN60_tree=null;
        Object SIZEOF61_tree=null;
        Object ALIGNOF63_tree=null;
        Object LPAREN64_tree=null;
        Object RPAREN66_tree=null;
        RewriteRuleTokenStream stream_SIZEOF=new RewriteRuleTokenStream(adaptor,"token SIZEOF");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_MINUSMINUS=new RewriteRuleTokenStream(adaptor,"token MINUSMINUS");
        RewriteRuleTokenStream stream_ALIGNOF=new RewriteRuleTokenStream(adaptor,"token ALIGNOF");
        RewriteRuleTokenStream stream_PLUSPLUS=new RewriteRuleTokenStream(adaptor,"token PLUSPLUS");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typeName=new RewriteRuleSubtreeStream(adaptor,"rule typeName");
        RewriteRuleSubtreeStream stream_unaryOperator=new RewriteRuleSubtreeStream(adaptor,"rule unaryOperator");
        RewriteRuleSubtreeStream stream_castExpression=new RewriteRuleSubtreeStream(adaptor,"rule castExpression");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        try {
            // CivlCParser.g:249:2: ( postfixExpression |p= PLUSPLUS unaryExpression -> ^( OPERATOR PRE_INCREMENT[$p] ^( ARGUMENT_LIST unaryExpression ) ) |m= MINUSMINUS unaryExpression -> ^( OPERATOR PRE_DECREMENT[$m] ^( ARGUMENT_LIST unaryExpression ) ) | unaryOperator castExpression -> ^( OPERATOR unaryOperator ^( ARGUMENT_LIST castExpression ) ) | ( SIZEOF LPAREN typeName )=> SIZEOF LPAREN typeName RPAREN -> ^( SIZEOF TYPE typeName ) | SIZEOF unaryExpression -> ^( SIZEOF EXPR unaryExpression ) | ALIGNOF LPAREN typeName RPAREN -> ^( ALIGNOF typeName ) | spawnExpression )
            int alt10=8;
            switch ( input.LA(1) ) {
            case CHARACTER_CONSTANT:
            case FLOATING_CONSTANT:
            case GENERIC:
            case IDENTIFIER:
            case INTEGER_CONSTANT:
            case LPAREN:
            case STRING_LITERAL:
                {
                alt10=1;
                }
                break;
            case PLUSPLUS:
                {
                alt10=2;
                }
                break;
            case MINUSMINUS:
                {
                alt10=3;
                }
                break;
            case AMPERSAND:
            case NOT:
            case PLUS:
            case STAR:
            case SUB:
            case TILDE:
                {
                alt10=4;
                }
                break;
            case SIZEOF:
                {
                int LA10_11 = input.LA(2);

                if ( (synpred2_CivlCParser()) ) {
                    alt10=5;
                }
                else if ( (true) ) {
                    alt10=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 11, input);

                    throw nvae;

                }
                }
                break;
            case ALIGNOF:
                {
                alt10=7;
                }
                break;
            case SPAWN:
                {
                alt10=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // CivlCParser.g:249:4: postfixExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_postfixExpression_in_unaryExpression1219);
                    postfixExpression52=postfixExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpression52.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:250:4: p= PLUSPLUS unaryExpression
                    {
                    p=(Token)match(input,PLUSPLUS,FOLLOW_PLUSPLUS_in_unaryExpression1226); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PLUSPLUS.add(p);


                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1228);
                    unaryExpression53=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression53.getTree());

                    // AST REWRITE
                    // elements: unaryExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 251:4: -> ^( OPERATOR PRE_INCREMENT[$p] ^( ARGUMENT_LIST unaryExpression ) )
                    {
                        // CivlCParser.g:251:7: ^( OPERATOR PRE_INCREMENT[$p] ^( ARGUMENT_LIST unaryExpression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OPERATOR, "OPERATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(PRE_INCREMENT, p)
                        );

                        // CivlCParser.g:252:9: ^( ARGUMENT_LIST unaryExpression )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_2);

                        adaptor.addChild(root_2, stream_unaryExpression.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:253:4: m= MINUSMINUS unaryExpression
                    {
                    m=(Token)match(input,MINUSMINUS,FOLLOW_MINUSMINUS_in_unaryExpression1261); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUSMINUS.add(m);


                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1263);
                    unaryExpression54=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression54.getTree());

                    // AST REWRITE
                    // elements: unaryExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 254:4: -> ^( OPERATOR PRE_DECREMENT[$m] ^( ARGUMENT_LIST unaryExpression ) )
                    {
                        // CivlCParser.g:254:7: ^( OPERATOR PRE_DECREMENT[$m] ^( ARGUMENT_LIST unaryExpression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OPERATOR, "OPERATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(PRE_DECREMENT, m)
                        );

                        // CivlCParser.g:255:9: ^( ARGUMENT_LIST unaryExpression )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_2);

                        adaptor.addChild(root_2, stream_unaryExpression.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // CivlCParser.g:256:4: unaryOperator castExpression
                    {
                    pushFollow(FOLLOW_unaryOperator_in_unaryExpression1294);
                    unaryOperator55=unaryOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryOperator.add(unaryOperator55.getTree());

                    pushFollow(FOLLOW_castExpression_in_unaryExpression1296);
                    castExpression56=castExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_castExpression.add(castExpression56.getTree());

                    // AST REWRITE
                    // elements: castExpression, unaryOperator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 257:4: -> ^( OPERATOR unaryOperator ^( ARGUMENT_LIST castExpression ) )
                    {
                        // CivlCParser.g:257:7: ^( OPERATOR unaryOperator ^( ARGUMENT_LIST castExpression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OPERATOR, "OPERATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_unaryOperator.nextTree());

                        // CivlCParser.g:257:32: ^( ARGUMENT_LIST castExpression )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_2);

                        adaptor.addChild(root_2, stream_castExpression.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // CivlCParser.g:258:4: ( SIZEOF LPAREN typeName )=> SIZEOF LPAREN typeName RPAREN
                    {
                    SIZEOF57=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_unaryExpression1327); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SIZEOF.add(SIZEOF57);


                    LPAREN58=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpression1329); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN58);


                    pushFollow(FOLLOW_typeName_in_unaryExpression1331);
                    typeName59=typeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeName.add(typeName59.getTree());

                    RPAREN60=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpression1333); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN60);


                    // AST REWRITE
                    // elements: typeName, SIZEOF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 259:4: -> ^( SIZEOF TYPE typeName )
                    {
                        // CivlCParser.g:259:7: ^( SIZEOF TYPE typeName )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_SIZEOF.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(TYPE, "TYPE")
                        );

                        adaptor.addChild(root_1, stream_typeName.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // CivlCParser.g:260:4: SIZEOF unaryExpression
                    {
                    SIZEOF61=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_unaryExpression1351); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SIZEOF.add(SIZEOF61);


                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1353);
                    unaryExpression62=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression62.getTree());

                    // AST REWRITE
                    // elements: SIZEOF, unaryExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 261:4: -> ^( SIZEOF EXPR unaryExpression )
                    {
                        // CivlCParser.g:261:7: ^( SIZEOF EXPR unaryExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_SIZEOF.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(EXPR, "EXPR")
                        );

                        adaptor.addChild(root_1, stream_unaryExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    // CivlCParser.g:262:4: ALIGNOF LPAREN typeName RPAREN
                    {
                    ALIGNOF63=(Token)match(input,ALIGNOF,FOLLOW_ALIGNOF_in_unaryExpression1371); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ALIGNOF.add(ALIGNOF63);


                    LPAREN64=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpression1373); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN64);


                    pushFollow(FOLLOW_typeName_in_unaryExpression1375);
                    typeName65=typeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeName.add(typeName65.getTree());

                    RPAREN66=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpression1377); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN66);


                    // AST REWRITE
                    // elements: typeName, ALIGNOF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 263:4: -> ^( ALIGNOF typeName )
                    {
                        // CivlCParser.g:263:7: ^( ALIGNOF typeName )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_ALIGNOF.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_typeName.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 8 :
                    // CivlCParser.g:264:4: spawnExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_spawnExpression_in_unaryExpression1393);
                    spawnExpression67=spawnExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, spawnExpression67.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"


    public static class spawnExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "spawnExpression"
    // CivlCParser.g:268:1: spawnExpression : SPAWN postfixExpressionRoot LPAREN argumentExpressionList RPAREN -> ^( SPAWN postfixExpressionRoot argumentExpressionList ) ;
    public final CivlCParser.spawnExpression_return spawnExpression() throws RecognitionException {
        CivlCParser.spawnExpression_return retval = new CivlCParser.spawnExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SPAWN68=null;
        Token LPAREN70=null;
        Token RPAREN72=null;
        CivlCParser.postfixExpressionRoot_return postfixExpressionRoot69 =null;

        CivlCParser.argumentExpressionList_return argumentExpressionList71 =null;


        Object SPAWN68_tree=null;
        Object LPAREN70_tree=null;
        Object RPAREN72_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_SPAWN=new RewriteRuleTokenStream(adaptor,"token SPAWN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_postfixExpressionRoot=new RewriteRuleSubtreeStream(adaptor,"rule postfixExpressionRoot");
        RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");
        try {
            // CivlCParser.g:269:2: ( SPAWN postfixExpressionRoot LPAREN argumentExpressionList RPAREN -> ^( SPAWN postfixExpressionRoot argumentExpressionList ) )
            // CivlCParser.g:269:4: SPAWN postfixExpressionRoot LPAREN argumentExpressionList RPAREN
            {
            SPAWN68=(Token)match(input,SPAWN,FOLLOW_SPAWN_in_spawnExpression1405); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SPAWN.add(SPAWN68);


            pushFollow(FOLLOW_postfixExpressionRoot_in_spawnExpression1407);
            postfixExpressionRoot69=postfixExpressionRoot();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_postfixExpressionRoot.add(postfixExpressionRoot69.getTree());

            LPAREN70=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_spawnExpression1409); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN70);


            pushFollow(FOLLOW_argumentExpressionList_in_spawnExpression1415);
            argumentExpressionList71=argumentExpressionList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList71.getTree());

            RPAREN72=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_spawnExpression1417); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN72);


            // AST REWRITE
            // elements: postfixExpressionRoot, argumentExpressionList, SPAWN
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 271:4: -> ^( SPAWN postfixExpressionRoot argumentExpressionList )
            {
                // CivlCParser.g:271:7: ^( SPAWN postfixExpressionRoot argumentExpressionList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_SPAWN.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_postfixExpressionRoot.nextTree());

                adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "spawnExpression"


    public static class unaryOperator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryOperator"
    // CivlCParser.g:276:1: unaryOperator : ( AMPERSAND | STAR | PLUS | SUB | TILDE | NOT );
    public final CivlCParser.unaryOperator_return unaryOperator() throws RecognitionException {
        CivlCParser.unaryOperator_return retval = new CivlCParser.unaryOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set73=null;

        Object set73_tree=null;

        try {
            // CivlCParser.g:277:2: ( AMPERSAND | STAR | PLUS | SUB | TILDE | NOT )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set73=(Token)input.LT(1);

            if ( input.LA(1)==AMPERSAND||input.LA(1)==NOT||input.LA(1)==PLUS||input.LA(1)==STAR||input.LA(1)==SUB||input.LA(1)==TILDE ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set73)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryOperator"


    public static class castExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "castExpression"
    // CivlCParser.g:283:1: castExpression : ( ( LPAREN typeName RPAREN )=>l= LPAREN typeName RPAREN castExpression -> ^( CAST typeName castExpression $l) | unaryExpression );
    public final CivlCParser.castExpression_return castExpression() throws RecognitionException {
        CivlCParser.castExpression_return retval = new CivlCParser.castExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token l=null;
        Token RPAREN75=null;
        CivlCParser.typeName_return typeName74 =null;

        CivlCParser.castExpression_return castExpression76 =null;

        CivlCParser.unaryExpression_return unaryExpression77 =null;


        Object l_tree=null;
        Object RPAREN75_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typeName=new RewriteRuleSubtreeStream(adaptor,"rule typeName");
        RewriteRuleSubtreeStream stream_castExpression=new RewriteRuleSubtreeStream(adaptor,"rule castExpression");
        try {
            // CivlCParser.g:284:2: ( ( LPAREN typeName RPAREN )=>l= LPAREN typeName RPAREN castExpression -> ^( CAST typeName castExpression $l) | unaryExpression )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LPAREN) ) {
                int LA11_1 = input.LA(2);

                if ( (synpred3_CivlCParser()) ) {
                    alt11=1;
                }
                else if ( (true) ) {
                    alt11=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA11_0 >= ALIGNOF && LA11_0 <= AMPERSAND)||LA11_0==CHARACTER_CONSTANT||LA11_0==FLOATING_CONSTANT||LA11_0==GENERIC||LA11_0==IDENTIFIER||LA11_0==INTEGER_CONSTANT||LA11_0==MINUSMINUS||LA11_0==NOT||LA11_0==PLUS||LA11_0==PLUSPLUS||(LA11_0 >= SIZEOF && LA11_0 <= STAR)||LA11_0==STRING_LITERAL||LA11_0==SUB||LA11_0==TILDE) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // CivlCParser.g:284:4: ( LPAREN typeName RPAREN )=>l= LPAREN typeName RPAREN castExpression
                    {
                    l=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_castExpression1490); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(l);


                    pushFollow(FOLLOW_typeName_in_castExpression1492);
                    typeName74=typeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeName.add(typeName74.getTree());

                    RPAREN75=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_castExpression1494); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN75);


                    pushFollow(FOLLOW_castExpression_in_castExpression1496);
                    castExpression76=castExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_castExpression.add(castExpression76.getTree());

                    // AST REWRITE
                    // elements: castExpression, typeName, l
                    // token labels: l
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_l=new RewriteRuleTokenStream(adaptor,"token l",l);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 285:4: -> ^( CAST typeName castExpression $l)
                    {
                        // CivlCParser.g:285:7: ^( CAST typeName castExpression $l)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(CAST, "CAST")
                        , root_1);

                        adaptor.addChild(root_1, stream_typeName.nextTree());

                        adaptor.addChild(root_1, stream_castExpression.nextTree());

                        adaptor.addChild(root_1, stream_l.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:286:4: unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_unaryExpression_in_castExpression1517);
                    unaryExpression77=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression77.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "castExpression"


    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multiplicativeExpression"
    // CivlCParser.g:290:1: multiplicativeExpression : ( castExpression -> castExpression ) ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )* ;
    public final CivlCParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        CivlCParser.multiplicativeExpression_return retval = new CivlCParser.multiplicativeExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token STAR79=null;
        Token DIV80=null;
        Token MOD81=null;
        CivlCParser.castExpression_return y =null;

        CivlCParser.castExpression_return castExpression78 =null;


        Object STAR79_tree=null;
        Object DIV80_tree=null;
        Object MOD81_tree=null;
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_DIV=new RewriteRuleTokenStream(adaptor,"token DIV");
        RewriteRuleTokenStream stream_MOD=new RewriteRuleTokenStream(adaptor,"token MOD");
        RewriteRuleSubtreeStream stream_castExpression=new RewriteRuleSubtreeStream(adaptor,"rule castExpression");
        try {
            // CivlCParser.g:291:2: ( ( castExpression -> castExpression ) ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )* )
            // CivlCParser.g:291:4: ( castExpression -> castExpression ) ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )*
            {
            // CivlCParser.g:291:4: ( castExpression -> castExpression )
            // CivlCParser.g:291:5: castExpression
            {
            pushFollow(FOLLOW_castExpression_in_multiplicativeExpression1531);
            castExpression78=castExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_castExpression.add(castExpression78.getTree());

            // AST REWRITE
            // elements: castExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 291:20: -> castExpression
            {
                adaptor.addChild(root_0, stream_castExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:292:2: ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )*
            loop12:
            do {
                int alt12=4;
                switch ( input.LA(1) ) {
                case STAR:
                    {
                    alt12=1;
                    }
                    break;
                case DIV:
                    {
                    alt12=2;
                    }
                    break;
                case MOD:
                    {
                    alt12=3;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // CivlCParser.g:292:4: STAR y= castExpression
            	    {
            	    STAR79=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression1541); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_STAR.add(STAR79);


            	    pushFollow(FOLLOW_castExpression_in_multiplicativeExpression1545);
            	    y=castExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_castExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: STAR, multiplicativeExpression, y
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 293:4: -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) )
            	    {
            	        // CivlCParser.g:293:7: ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_STAR.nextNode()
            	        );

            	        // CivlCParser.g:293:23: ^( ARGUMENT_LIST $multiplicativeExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 2 :
            	    // CivlCParser.g:294:4: DIV y= castExpression
            	    {
            	    DIV80=(Token)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression1571); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DIV.add(DIV80);


            	    pushFollow(FOLLOW_castExpression_in_multiplicativeExpression1575);
            	    y=castExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_castExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: DIV, y, multiplicativeExpression
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 295:4: -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) )
            	    {
            	        // CivlCParser.g:295:7: ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_DIV.nextNode()
            	        );

            	        // CivlCParser.g:295:22: ^( ARGUMENT_LIST $multiplicativeExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 3 :
            	    // CivlCParser.g:296:11: MOD y= castExpression
            	    {
            	    MOD81=(Token)match(input,MOD,FOLLOW_MOD_in_multiplicativeExpression1608); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_MOD.add(MOD81);


            	    pushFollow(FOLLOW_castExpression_in_multiplicativeExpression1612);
            	    y=castExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_castExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: y, MOD, multiplicativeExpression
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 297:4: -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) )
            	    {
            	        // CivlCParser.g:297:7: ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_MOD.nextNode()
            	        );

            	        // CivlCParser.g:297:22: ^( ARGUMENT_LIST $multiplicativeExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multiplicativeExpression"


    public static class additiveExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "additiveExpression"
    // CivlCParser.g:302:1: additiveExpression : ( multiplicativeExpression -> multiplicativeExpression ) ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )* ;
    public final CivlCParser.additiveExpression_return additiveExpression() throws RecognitionException {
        CivlCParser.additiveExpression_return retval = new CivlCParser.additiveExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS83=null;
        Token SUB84=null;
        CivlCParser.multiplicativeExpression_return y =null;

        CivlCParser.multiplicativeExpression_return multiplicativeExpression82 =null;


        Object PLUS83_tree=null;
        Object SUB84_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_SUB=new RewriteRuleTokenStream(adaptor,"token SUB");
        RewriteRuleSubtreeStream stream_multiplicativeExpression=new RewriteRuleSubtreeStream(adaptor,"rule multiplicativeExpression");
        try {
            // CivlCParser.g:303:2: ( ( multiplicativeExpression -> multiplicativeExpression ) ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )* )
            // CivlCParser.g:303:4: ( multiplicativeExpression -> multiplicativeExpression ) ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )*
            {
            // CivlCParser.g:303:4: ( multiplicativeExpression -> multiplicativeExpression )
            // CivlCParser.g:303:5: multiplicativeExpression
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1658);
            multiplicativeExpression82=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_multiplicativeExpression.add(multiplicativeExpression82.getTree());

            // AST REWRITE
            // elements: multiplicativeExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 303:30: -> multiplicativeExpression
            {
                adaptor.addChild(root_0, stream_multiplicativeExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:304:9: ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )*
            loop13:
            do {
                int alt13=3;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==PLUS) ) {
                    alt13=1;
                }
                else if ( (LA13_0==SUB) ) {
                    alt13=2;
                }


                switch (alt13) {
            	case 1 :
            	    // CivlCParser.g:304:11: PLUS y= multiplicativeExpression
            	    {
            	    PLUS83=(Token)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression1675); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_PLUS.add(PLUS83);


            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1679);
            	    y=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_multiplicativeExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: additiveExpression, y, PLUS
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 305:11: -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) )
            	    {
            	        // CivlCParser.g:305:14: ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_PLUS.nextNode()
            	        );

            	        // CivlCParser.g:305:30: ^( ARGUMENT_LIST $additiveExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 2 :
            	    // CivlCParser.g:306:11: SUB y= multiplicativeExpression
            	    {
            	    SUB84=(Token)match(input,SUB,FOLLOW_SUB_in_additiveExpression1719); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SUB.add(SUB84);


            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1723);
            	    y=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_multiplicativeExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: y, SUB, additiveExpression
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 307:11: -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) )
            	    {
            	        // CivlCParser.g:307:14: ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_SUB.nextNode()
            	        );

            	        // CivlCParser.g:307:29: ^( ARGUMENT_LIST $additiveExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "additiveExpression"


    public static class shiftExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "shiftExpression"
    // CivlCParser.g:312:1: shiftExpression : ( additiveExpression -> additiveExpression ) ( SHIFTLEFT y= additiveExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= additiveExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )* ;
    public final CivlCParser.shiftExpression_return shiftExpression() throws RecognitionException {
        CivlCParser.shiftExpression_return retval = new CivlCParser.shiftExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SHIFTLEFT86=null;
        Token SHIFTRIGHT87=null;
        CivlCParser.additiveExpression_return y =null;

        CivlCParser.additiveExpression_return additiveExpression85 =null;


        Object SHIFTLEFT86_tree=null;
        Object SHIFTRIGHT87_tree=null;
        RewriteRuleTokenStream stream_SHIFTLEFT=new RewriteRuleTokenStream(adaptor,"token SHIFTLEFT");
        RewriteRuleTokenStream stream_SHIFTRIGHT=new RewriteRuleTokenStream(adaptor,"token SHIFTRIGHT");
        RewriteRuleSubtreeStream stream_additiveExpression=new RewriteRuleSubtreeStream(adaptor,"rule additiveExpression");
        try {
            // CivlCParser.g:313:2: ( ( additiveExpression -> additiveExpression ) ( SHIFTLEFT y= additiveExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= additiveExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )* )
            // CivlCParser.g:313:4: ( additiveExpression -> additiveExpression ) ( SHIFTLEFT y= additiveExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= additiveExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )*
            {
            // CivlCParser.g:313:4: ( additiveExpression -> additiveExpression )
            // CivlCParser.g:313:5: additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_shiftExpression1776);
            additiveExpression85=additiveExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_additiveExpression.add(additiveExpression85.getTree());

            // AST REWRITE
            // elements: additiveExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 313:24: -> additiveExpression
            {
                adaptor.addChild(root_0, stream_additiveExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:314:9: ( SHIFTLEFT y= additiveExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= additiveExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )*
            loop14:
            do {
                int alt14=3;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==SHIFTLEFT) ) {
                    alt14=1;
                }
                else if ( (LA14_0==SHIFTRIGHT) ) {
                    alt14=2;
                }


                switch (alt14) {
            	case 1 :
            	    // CivlCParser.g:314:11: SHIFTLEFT y= additiveExpression
            	    {
            	    SHIFTLEFT86=(Token)match(input,SHIFTLEFT,FOLLOW_SHIFTLEFT_in_shiftExpression1793); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SHIFTLEFT.add(SHIFTLEFT86);


            	    pushFollow(FOLLOW_additiveExpression_in_shiftExpression1797);
            	    y=additiveExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_additiveExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: SHIFTLEFT, shiftExpression, y
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 315:11: -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) )
            	    {
            	        // CivlCParser.g:315:14: ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_SHIFTLEFT.nextNode()
            	        );

            	        // CivlCParser.g:315:35: ^( ARGUMENT_LIST $shiftExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;
            	case 2 :
            	    // CivlCParser.g:316:11: SHIFTRIGHT y= additiveExpression
            	    {
            	    SHIFTRIGHT87=(Token)match(input,SHIFTRIGHT,FOLLOW_SHIFTRIGHT_in_shiftExpression1837); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SHIFTRIGHT.add(SHIFTRIGHT87);


            	    pushFollow(FOLLOW_additiveExpression_in_shiftExpression1841);
            	    y=additiveExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_additiveExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: y, shiftExpression, SHIFTRIGHT
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 317:11: -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) )
            	    {
            	        // CivlCParser.g:317:14: ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_SHIFTRIGHT.nextNode()
            	        );

            	        // CivlCParser.g:317:36: ^( ARGUMENT_LIST $shiftExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "shiftExpression"


    public static class relationalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relationalExpression"
    // CivlCParser.g:322:1: relationalExpression : ( shiftExpression -> shiftExpression ) ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )* ;
    public final CivlCParser.relationalExpression_return relationalExpression() throws RecognitionException {
        CivlCParser.relationalExpression_return retval = new CivlCParser.relationalExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.shiftExpression_return y =null;

        CivlCParser.shiftExpression_return shiftExpression88 =null;

        CivlCParser.relationalOperator_return relationalOperator89 =null;


        RewriteRuleSubtreeStream stream_relationalOperator=new RewriteRuleSubtreeStream(adaptor,"rule relationalOperator");
        RewriteRuleSubtreeStream stream_shiftExpression=new RewriteRuleSubtreeStream(adaptor,"rule shiftExpression");
        try {
            // CivlCParser.g:323:2: ( ( shiftExpression -> shiftExpression ) ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )* )
            // CivlCParser.g:323:4: ( shiftExpression -> shiftExpression ) ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )*
            {
            // CivlCParser.g:323:4: ( shiftExpression -> shiftExpression )
            // CivlCParser.g:323:6: shiftExpression
            {
            pushFollow(FOLLOW_shiftExpression_in_relationalExpression1895);
            shiftExpression88=shiftExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_shiftExpression.add(shiftExpression88.getTree());

            // AST REWRITE
            // elements: shiftExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 323:22: -> shiftExpression
            {
                adaptor.addChild(root_0, stream_shiftExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:324:4: ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0 >= GT && LA15_0 <= GTE)||(LA15_0 >= LT && LA15_0 <= LTE)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // CivlCParser.g:324:6: relationalOperator y= shiftExpression
            	    {
            	    pushFollow(FOLLOW_relationalOperator_in_relationalExpression1908);
            	    relationalOperator89=relationalOperator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_relationalOperator.add(relationalOperator89.getTree());

            	    pushFollow(FOLLOW_shiftExpression_in_relationalExpression1912);
            	    y=shiftExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_shiftExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: relationalExpression, y, relationalOperator
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 325:6: -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) )
            	    {
            	        // CivlCParser.g:325:9: ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, stream_relationalOperator.nextTree());

            	        // CivlCParser.g:325:39: ^( ARGUMENT_LIST $relationalExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "relationalExpression"


    public static class relationalOperator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relationalOperator"
    // CivlCParser.g:329:1: relationalOperator : ( LT | GT | LTE | GTE );
    public final CivlCParser.relationalOperator_return relationalOperator() throws RecognitionException {
        CivlCParser.relationalOperator_return retval = new CivlCParser.relationalOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set90=null;

        Object set90_tree=null;

        try {
            // CivlCParser.g:330:2: ( LT | GT | LTE | GTE )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set90=(Token)input.LT(1);

            if ( (input.LA(1) >= GT && input.LA(1) <= GTE)||(input.LA(1) >= LT && input.LA(1) <= LTE) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set90)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "relationalOperator"


    public static class equalityExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "equalityExpression"
    // CivlCParser.g:334:1: equalityExpression : ( relationalExpression -> relationalExpression ) ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )* ;
    public final CivlCParser.equalityExpression_return equalityExpression() throws RecognitionException {
        CivlCParser.equalityExpression_return retval = new CivlCParser.equalityExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.relationalExpression_return y =null;

        CivlCParser.relationalExpression_return relationalExpression91 =null;

        CivlCParser.equalityOperator_return equalityOperator92 =null;


        RewriteRuleSubtreeStream stream_relationalExpression=new RewriteRuleSubtreeStream(adaptor,"rule relationalExpression");
        RewriteRuleSubtreeStream stream_equalityOperator=new RewriteRuleSubtreeStream(adaptor,"rule equalityOperator");
        try {
            // CivlCParser.g:335:2: ( ( relationalExpression -> relationalExpression ) ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )* )
            // CivlCParser.g:335:4: ( relationalExpression -> relationalExpression ) ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )*
            {
            // CivlCParser.g:335:4: ( relationalExpression -> relationalExpression )
            // CivlCParser.g:335:6: relationalExpression
            {
            pushFollow(FOLLOW_relationalExpression_in_equalityExpression1979);
            relationalExpression91=relationalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_relationalExpression.add(relationalExpression91.getTree());

            // AST REWRITE
            // elements: relationalExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 335:27: -> relationalExpression
            {
                adaptor.addChild(root_0, stream_relationalExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:336:4: ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==EQUALS||LA16_0==NEQ) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // CivlCParser.g:336:6: equalityOperator y= relationalExpression
            	    {
            	    pushFollow(FOLLOW_equalityOperator_in_equalityExpression1992);
            	    equalityOperator92=equalityOperator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_equalityOperator.add(equalityOperator92.getTree());

            	    pushFollow(FOLLOW_relationalExpression_in_equalityExpression1996);
            	    y=relationalExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_relationalExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: y, equalityExpression, equalityOperator
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 337:6: -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) )
            	    {
            	        // CivlCParser.g:337:9: ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, stream_equalityOperator.nextTree());

            	        // CivlCParser.g:337:37: ^( ARGUMENT_LIST $equalityExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "equalityExpression"


    public static class equalityOperator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "equalityOperator"
    // CivlCParser.g:341:1: equalityOperator : ( EQUALS | NEQ );
    public final CivlCParser.equalityOperator_return equalityOperator() throws RecognitionException {
        CivlCParser.equalityOperator_return retval = new CivlCParser.equalityOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set93=null;

        Object set93_tree=null;

        try {
            // CivlCParser.g:342:2: ( EQUALS | NEQ )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set93=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||input.LA(1)==NEQ ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set93)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "equalityOperator"


    public static class andExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andExpression"
    // CivlCParser.g:346:1: andExpression : ( equalityExpression -> equalityExpression ) ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )* ;
    public final CivlCParser.andExpression_return andExpression() throws RecognitionException {
        CivlCParser.andExpression_return retval = new CivlCParser.andExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token AMPERSAND95=null;
        CivlCParser.equalityExpression_return y =null;

        CivlCParser.equalityExpression_return equalityExpression94 =null;


        Object AMPERSAND95_tree=null;
        RewriteRuleTokenStream stream_AMPERSAND=new RewriteRuleTokenStream(adaptor,"token AMPERSAND");
        RewriteRuleSubtreeStream stream_equalityExpression=new RewriteRuleSubtreeStream(adaptor,"rule equalityExpression");
        try {
            // CivlCParser.g:347:2: ( ( equalityExpression -> equalityExpression ) ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )* )
            // CivlCParser.g:347:4: ( equalityExpression -> equalityExpression ) ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )*
            {
            // CivlCParser.g:347:4: ( equalityExpression -> equalityExpression )
            // CivlCParser.g:347:6: equalityExpression
            {
            pushFollow(FOLLOW_equalityExpression_in_andExpression2055);
            equalityExpression94=equalityExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_equalityExpression.add(equalityExpression94.getTree());

            // AST REWRITE
            // elements: equalityExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 347:25: -> equalityExpression
            {
                adaptor.addChild(root_0, stream_equalityExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:348:4: ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==AMPERSAND) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // CivlCParser.g:348:6: AMPERSAND y= equalityExpression
            	    {
            	    AMPERSAND95=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_andExpression2068); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_AMPERSAND.add(AMPERSAND95);


            	    pushFollow(FOLLOW_equalityExpression_in_andExpression2072);
            	    y=equalityExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_equalityExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: y, andExpression, AMPERSAND
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 349:6: -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) )
            	    {
            	        // CivlCParser.g:349:9: ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_AMPERSAND.nextNode()
            	        );

            	        // CivlCParser.g:349:30: ^( ARGUMENT_LIST $andExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "andExpression"


    public static class exclusiveOrExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exclusiveOrExpression"
    // CivlCParser.g:354:1: exclusiveOrExpression : ( andExpression -> andExpression ) ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )* ;
    public final CivlCParser.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException {
        CivlCParser.exclusiveOrExpression_return retval = new CivlCParser.exclusiveOrExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token BITXOR97=null;
        CivlCParser.andExpression_return y =null;

        CivlCParser.andExpression_return andExpression96 =null;


        Object BITXOR97_tree=null;
        RewriteRuleTokenStream stream_BITXOR=new RewriteRuleTokenStream(adaptor,"token BITXOR");
        RewriteRuleSubtreeStream stream_andExpression=new RewriteRuleSubtreeStream(adaptor,"rule andExpression");
        try {
            // CivlCParser.g:355:2: ( ( andExpression -> andExpression ) ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )* )
            // CivlCParser.g:355:4: ( andExpression -> andExpression ) ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )*
            {
            // CivlCParser.g:355:4: ( andExpression -> andExpression )
            // CivlCParser.g:355:6: andExpression
            {
            pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression2116);
            andExpression96=andExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_andExpression.add(andExpression96.getTree());

            // AST REWRITE
            // elements: andExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 355:20: -> andExpression
            {
                adaptor.addChild(root_0, stream_andExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:356:4: ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==BITXOR) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // CivlCParser.g:356:6: BITXOR y= andExpression
            	    {
            	    BITXOR97=(Token)match(input,BITXOR,FOLLOW_BITXOR_in_exclusiveOrExpression2129); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_BITXOR.add(BITXOR97);


            	    pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression2133);
            	    y=andExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_andExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: y, exclusiveOrExpression, BITXOR
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 357:6: -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) )
            	    {
            	        // CivlCParser.g:357:9: ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_BITXOR.nextNode()
            	        );

            	        // CivlCParser.g:357:27: ^( ARGUMENT_LIST $exclusiveOrExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "exclusiveOrExpression"


    public static class inclusiveOrExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "inclusiveOrExpression"
    // CivlCParser.g:362:1: inclusiveOrExpression : ( exclusiveOrExpression -> exclusiveOrExpression ) ( BITOR y= exclusiveOrExpression -> ^( OPERATOR BITOR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )* ;
    public final CivlCParser.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException {
        CivlCParser.inclusiveOrExpression_return retval = new CivlCParser.inclusiveOrExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token BITOR99=null;
        CivlCParser.exclusiveOrExpression_return y =null;

        CivlCParser.exclusiveOrExpression_return exclusiveOrExpression98 =null;


        Object BITOR99_tree=null;
        RewriteRuleTokenStream stream_BITOR=new RewriteRuleTokenStream(adaptor,"token BITOR");
        RewriteRuleSubtreeStream stream_exclusiveOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule exclusiveOrExpression");
        try {
            // CivlCParser.g:363:2: ( ( exclusiveOrExpression -> exclusiveOrExpression ) ( BITOR y= exclusiveOrExpression -> ^( OPERATOR BITOR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )* )
            // CivlCParser.g:363:4: ( exclusiveOrExpression -> exclusiveOrExpression ) ( BITOR y= exclusiveOrExpression -> ^( OPERATOR BITOR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )*
            {
            // CivlCParser.g:363:4: ( exclusiveOrExpression -> exclusiveOrExpression )
            // CivlCParser.g:363:6: exclusiveOrExpression
            {
            pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2177);
            exclusiveOrExpression98=exclusiveOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exclusiveOrExpression.add(exclusiveOrExpression98.getTree());

            // AST REWRITE
            // elements: exclusiveOrExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 363:28: -> exclusiveOrExpression
            {
                adaptor.addChild(root_0, stream_exclusiveOrExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:364:4: ( BITOR y= exclusiveOrExpression -> ^( OPERATOR BITOR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==BITOR) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // CivlCParser.g:364:6: BITOR y= exclusiveOrExpression
            	    {
            	    BITOR99=(Token)match(input,BITOR,FOLLOW_BITOR_in_inclusiveOrExpression2190); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_BITOR.add(BITOR99);


            	    pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2194);
            	    y=exclusiveOrExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exclusiveOrExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: inclusiveOrExpression, y, BITOR
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 365:6: -> ^( OPERATOR BITOR ^( ARGUMENT_LIST $inclusiveOrExpression $y) )
            	    {
            	        // CivlCParser.g:365:9: ^( OPERATOR BITOR ^( ARGUMENT_LIST $inclusiveOrExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_BITOR.nextNode()
            	        );

            	        // CivlCParser.g:365:26: ^( ARGUMENT_LIST $inclusiveOrExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "inclusiveOrExpression"


    public static class logicalAndExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "logicalAndExpression"
    // CivlCParser.g:370:1: logicalAndExpression : ( inclusiveOrExpression -> inclusiveOrExpression ) ( AND y= inclusiveOrExpression -> ^( OPERATOR AND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )* ;
    public final CivlCParser.logicalAndExpression_return logicalAndExpression() throws RecognitionException {
        CivlCParser.logicalAndExpression_return retval = new CivlCParser.logicalAndExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token AND101=null;
        CivlCParser.inclusiveOrExpression_return y =null;

        CivlCParser.inclusiveOrExpression_return inclusiveOrExpression100 =null;


        Object AND101_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleSubtreeStream stream_inclusiveOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule inclusiveOrExpression");
        try {
            // CivlCParser.g:371:2: ( ( inclusiveOrExpression -> inclusiveOrExpression ) ( AND y= inclusiveOrExpression -> ^( OPERATOR AND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )* )
            // CivlCParser.g:371:4: ( inclusiveOrExpression -> inclusiveOrExpression ) ( AND y= inclusiveOrExpression -> ^( OPERATOR AND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )*
            {
            // CivlCParser.g:371:4: ( inclusiveOrExpression -> inclusiveOrExpression )
            // CivlCParser.g:371:6: inclusiveOrExpression
            {
            pushFollow(FOLLOW_inclusiveOrExpression_in_logicalAndExpression2238);
            inclusiveOrExpression100=inclusiveOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_inclusiveOrExpression.add(inclusiveOrExpression100.getTree());

            // AST REWRITE
            // elements: inclusiveOrExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 371:28: -> inclusiveOrExpression
            {
                adaptor.addChild(root_0, stream_inclusiveOrExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:372:4: ( AND y= inclusiveOrExpression -> ^( OPERATOR AND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==AND) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // CivlCParser.g:372:6: AND y= inclusiveOrExpression
            	    {
            	    AND101=(Token)match(input,AND,FOLLOW_AND_in_logicalAndExpression2251); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_AND.add(AND101);


            	    pushFollow(FOLLOW_inclusiveOrExpression_in_logicalAndExpression2255);
            	    y=inclusiveOrExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_inclusiveOrExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: AND, logicalAndExpression, y
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 373:6: -> ^( OPERATOR AND ^( ARGUMENT_LIST $logicalAndExpression $y) )
            	    {
            	        // CivlCParser.g:373:9: ^( OPERATOR AND ^( ARGUMENT_LIST $logicalAndExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_AND.nextNode()
            	        );

            	        // CivlCParser.g:373:24: ^( ARGUMENT_LIST $logicalAndExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "logicalAndExpression"


    public static class logicalOrExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "logicalOrExpression"
    // CivlCParser.g:378:1: logicalOrExpression : ( logicalAndExpression -> logicalAndExpression ) ( OR y= logicalAndExpression -> ^( OPERATOR OR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )* ;
    public final CivlCParser.logicalOrExpression_return logicalOrExpression() throws RecognitionException {
        CivlCParser.logicalOrExpression_return retval = new CivlCParser.logicalOrExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token OR103=null;
        CivlCParser.logicalAndExpression_return y =null;

        CivlCParser.logicalAndExpression_return logicalAndExpression102 =null;


        Object OR103_tree=null;
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_logicalAndExpression=new RewriteRuleSubtreeStream(adaptor,"rule logicalAndExpression");
        try {
            // CivlCParser.g:379:2: ( ( logicalAndExpression -> logicalAndExpression ) ( OR y= logicalAndExpression -> ^( OPERATOR OR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )* )
            // CivlCParser.g:379:4: ( logicalAndExpression -> logicalAndExpression ) ( OR y= logicalAndExpression -> ^( OPERATOR OR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )*
            {
            // CivlCParser.g:379:4: ( logicalAndExpression -> logicalAndExpression )
            // CivlCParser.g:379:6: logicalAndExpression
            {
            pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression2299);
            logicalAndExpression102=logicalAndExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_logicalAndExpression.add(logicalAndExpression102.getTree());

            // AST REWRITE
            // elements: logicalAndExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 379:27: -> logicalAndExpression
            {
                adaptor.addChild(root_0, stream_logicalAndExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:380:4: ( OR y= logicalAndExpression -> ^( OPERATOR OR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==OR) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // CivlCParser.g:380:6: OR y= logicalAndExpression
            	    {
            	    OR103=(Token)match(input,OR,FOLLOW_OR_in_logicalOrExpression2312); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_OR.add(OR103);


            	    pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression2316);
            	    y=logicalAndExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_logicalAndExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: OR, y, logicalOrExpression
            	    // token labels: 
            	    // rule labels: retval, y
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 381:6: -> ^( OPERATOR OR ^( ARGUMENT_LIST $logicalOrExpression $y) )
            	    {
            	        // CivlCParser.g:381:9: ^( OPERATOR OR ^( ARGUMENT_LIST $logicalOrExpression $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_OR.nextNode()
            	        );

            	        // CivlCParser.g:381:23: ^( ARGUMENT_LIST $logicalOrExpression $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_retval.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "logicalOrExpression"


    public static class conditionalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditionalExpression"
    // CivlCParser.g:387:1: conditionalExpression : logicalOrExpression ( -> logicalOrExpression | QMARK expression COLON conditionalExpression -> ^( OPERATOR QMARK ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression ) ) ) ;
    public final CivlCParser.conditionalExpression_return conditionalExpression() throws RecognitionException {
        CivlCParser.conditionalExpression_return retval = new CivlCParser.conditionalExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token QMARK105=null;
        Token COLON107=null;
        CivlCParser.logicalOrExpression_return logicalOrExpression104 =null;

        CivlCParser.expression_return expression106 =null;

        CivlCParser.conditionalExpression_return conditionalExpression108 =null;


        Object QMARK105_tree=null;
        Object COLON107_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_QMARK=new RewriteRuleTokenStream(adaptor,"token QMARK");
        RewriteRuleSubtreeStream stream_logicalOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule logicalOrExpression");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_conditionalExpression=new RewriteRuleSubtreeStream(adaptor,"rule conditionalExpression");
        try {
            // CivlCParser.g:388:2: ( logicalOrExpression ( -> logicalOrExpression | QMARK expression COLON conditionalExpression -> ^( OPERATOR QMARK ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression ) ) ) )
            // CivlCParser.g:388:4: logicalOrExpression ( -> logicalOrExpression | QMARK expression COLON conditionalExpression -> ^( OPERATOR QMARK ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression ) ) )
            {
            pushFollow(FOLLOW_logicalOrExpression_in_conditionalExpression2360);
            logicalOrExpression104=logicalOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_logicalOrExpression.add(logicalOrExpression104.getTree());

            // CivlCParser.g:389:2: ( -> logicalOrExpression | QMARK expression COLON conditionalExpression -> ^( OPERATOR QMARK ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression ) ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0 >= COLON && LA22_0 <= COMMA)||LA22_0==RCURLY||(LA22_0 >= RPAREN && LA22_0 <= RSQUARE)||LA22_0==SEMI) ) {
                alt22=1;
            }
            else if ( (LA22_0==QMARK) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // CivlCParser.g:389:4: 
                    {
                    // AST REWRITE
                    // elements: logicalOrExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 389:4: -> logicalOrExpression
                    {
                        adaptor.addChild(root_0, stream_logicalOrExpression.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:390:8: QMARK expression COLON conditionalExpression
                    {
                    QMARK105=(Token)match(input,QMARK,FOLLOW_QMARK_in_conditionalExpression2376); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMARK.add(QMARK105);


                    pushFollow(FOLLOW_expression_in_conditionalExpression2378);
                    expression106=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression106.getTree());

                    COLON107=(Token)match(input,COLON,FOLLOW_COLON_in_conditionalExpression2380); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON107);


                    pushFollow(FOLLOW_conditionalExpression_in_conditionalExpression2382);
                    conditionalExpression108=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_conditionalExpression.add(conditionalExpression108.getTree());

                    // AST REWRITE
                    // elements: expression, logicalOrExpression, QMARK, conditionalExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 391:8: -> ^( OPERATOR QMARK ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression ) )
                    {
                        // CivlCParser.g:391:11: ^( OPERATOR QMARK ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OPERATOR, "OPERATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_QMARK.nextNode()
                        );

                        // CivlCParser.g:392:13: ^( ARGUMENT_LIST logicalOrExpression expression conditionalExpression )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_2);

                        adaptor.addChild(root_2, stream_logicalOrExpression.nextTree());

                        adaptor.addChild(root_2, stream_expression.nextTree());

                        adaptor.addChild(root_2, stream_conditionalExpression.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "conditionalExpression"


    public static class assignmentExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignmentExpression"
    // CivlCParser.g:407:1: assignmentExpression : ( ( unaryExpression assignmentOperator )=> unaryExpression assignmentOperator assignmentExpression -> ^( OPERATOR assignmentOperator ^( ARGUMENT_LIST unaryExpression assignmentExpression ) ) | conditionalExpression );
    public final CivlCParser.assignmentExpression_return assignmentExpression() throws RecognitionException {
        CivlCParser.assignmentExpression_return retval = new CivlCParser.assignmentExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.unaryExpression_return unaryExpression109 =null;

        CivlCParser.assignmentOperator_return assignmentOperator110 =null;

        CivlCParser.assignmentExpression_return assignmentExpression111 =null;

        CivlCParser.conditionalExpression_return conditionalExpression112 =null;


        RewriteRuleSubtreeStream stream_assignmentOperator=new RewriteRuleSubtreeStream(adaptor,"rule assignmentOperator");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        try {
            // CivlCParser.g:408:2: ( ( unaryExpression assignmentOperator )=> unaryExpression assignmentOperator assignmentExpression -> ^( OPERATOR assignmentOperator ^( ARGUMENT_LIST unaryExpression assignmentExpression ) ) | conditionalExpression )
            int alt23=2;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                int LA23_1 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;

                }
                }
                break;
            case IDENTIFIER:
                {
                int LA23_2 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 2, input);

                    throw nvae;

                }
                }
                break;
            case INTEGER_CONSTANT:
                {
                int LA23_3 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 3, input);

                    throw nvae;

                }
                }
                break;
            case FLOATING_CONSTANT:
                {
                int LA23_4 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 4, input);

                    throw nvae;

                }
                }
                break;
            case CHARACTER_CONSTANT:
                {
                int LA23_5 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 5, input);

                    throw nvae;

                }
                }
                break;
            case STRING_LITERAL:
                {
                int LA23_6 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 6, input);

                    throw nvae;

                }
                }
                break;
            case GENERIC:
                {
                int LA23_7 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 7, input);

                    throw nvae;

                }
                }
                break;
            case PLUSPLUS:
                {
                int LA23_8 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 8, input);

                    throw nvae;

                }
                }
                break;
            case MINUSMINUS:
                {
                int LA23_9 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 9, input);

                    throw nvae;

                }
                }
                break;
            case AMPERSAND:
            case NOT:
            case PLUS:
            case STAR:
            case SUB:
            case TILDE:
                {
                int LA23_10 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 10, input);

                    throw nvae;

                }
                }
                break;
            case SIZEOF:
                {
                int LA23_11 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 11, input);

                    throw nvae;

                }
                }
                break;
            case ALIGNOF:
                {
                int LA23_12 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 12, input);

                    throw nvae;

                }
                }
                break;
            case SPAWN:
                {
                int LA23_13 = input.LA(2);

                if ( (synpred4_CivlCParser()) ) {
                    alt23=1;
                }
                else if ( (true) ) {
                    alt23=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 13, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
                case 1 :
                    // CivlCParser.g:408:4: ( unaryExpression assignmentOperator )=> unaryExpression assignmentOperator assignmentExpression
                    {
                    pushFollow(FOLLOW_unaryExpression_in_assignmentExpression2491);
                    unaryExpression109=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression109.getTree());

                    pushFollow(FOLLOW_assignmentOperator_in_assignmentExpression2493);
                    assignmentOperator110=assignmentOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentOperator.add(assignmentOperator110.getTree());

                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression2495);
                    assignmentExpression111=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression111.getTree());

                    // AST REWRITE
                    // elements: unaryExpression, assignmentExpression, assignmentOperator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 410:4: -> ^( OPERATOR assignmentOperator ^( ARGUMENT_LIST unaryExpression assignmentExpression ) )
                    {
                        // CivlCParser.g:410:7: ^( OPERATOR assignmentOperator ^( ARGUMENT_LIST unaryExpression assignmentExpression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OPERATOR, "OPERATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_assignmentOperator.nextTree());

                        // CivlCParser.g:411:9: ^( ARGUMENT_LIST unaryExpression assignmentExpression )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_2);

                        adaptor.addChild(root_2, stream_unaryExpression.nextTree());

                        adaptor.addChild(root_2, stream_assignmentExpression.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:412:4: conditionalExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_conditionalExpression_in_assignmentExpression2527);
                    conditionalExpression112=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression112.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignmentExpression"


    public static class assignmentOperator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignmentOperator"
    // CivlCParser.g:416:1: assignmentOperator : ( ASSIGN | STAREQ | DIVEQ | MODEQ | PLUSEQ | SUBEQ | SHIFTLEFTEQ | SHIFTRIGHTEQ | BITANDEQ | BITXOREQ | BITOREQ );
    public final CivlCParser.assignmentOperator_return assignmentOperator() throws RecognitionException {
        CivlCParser.assignmentOperator_return retval = new CivlCParser.assignmentOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set113=null;

        Object set113_tree=null;

        try {
            // CivlCParser.g:417:2: ( ASSIGN | STAREQ | DIVEQ | MODEQ | PLUSEQ | SUBEQ | SHIFTLEFTEQ | SHIFTRIGHTEQ | BITANDEQ | BITXOREQ | BITOREQ )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set113=(Token)input.LT(1);

            if ( input.LA(1)==ASSIGN||input.LA(1)==BITANDEQ||input.LA(1)==BITOREQ||input.LA(1)==BITXOREQ||input.LA(1)==DIVEQ||input.LA(1)==MODEQ||input.LA(1)==PLUSEQ||input.LA(1)==SHIFTLEFTEQ||input.LA(1)==SHIFTRIGHTEQ||input.LA(1)==STAREQ||input.LA(1)==SUBEQ ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set113)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignmentOperator"


    public static class commaExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "commaExpression"
    // CivlCParser.g:429:1: commaExpression : (x= assignmentExpression -> assignmentExpression ) ( COMMA y= assignmentExpression -> ^( OPERATOR COMMA ^( ARGUMENT_LIST $x $y) ) )* ;
    public final CivlCParser.commaExpression_return commaExpression() throws RecognitionException {
        CivlCParser.commaExpression_return retval = new CivlCParser.commaExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA114=null;
        CivlCParser.assignmentExpression_return x =null;

        CivlCParser.assignmentExpression_return y =null;


        Object COMMA114_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        try {
            // CivlCParser.g:430:2: ( (x= assignmentExpression -> assignmentExpression ) ( COMMA y= assignmentExpression -> ^( OPERATOR COMMA ^( ARGUMENT_LIST $x $y) ) )* )
            // CivlCParser.g:430:4: (x= assignmentExpression -> assignmentExpression ) ( COMMA y= assignmentExpression -> ^( OPERATOR COMMA ^( ARGUMENT_LIST $x $y) ) )*
            {
            // CivlCParser.g:430:4: (x= assignmentExpression -> assignmentExpression )
            // CivlCParser.g:430:6: x= assignmentExpression
            {
            pushFollow(FOLLOW_assignmentExpression_in_commaExpression2598);
            x=assignmentExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assignmentExpression.add(x.getTree());

            // AST REWRITE
            // elements: assignmentExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 430:29: -> assignmentExpression
            {
                adaptor.addChild(root_0, stream_assignmentExpression.nextTree());

            }


            retval.tree = root_0;
            }

            }


            // CivlCParser.g:431:4: ( COMMA y= assignmentExpression -> ^( OPERATOR COMMA ^( ARGUMENT_LIST $x $y) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // CivlCParser.g:431:6: COMMA y= assignmentExpression
            	    {
            	    COMMA114=(Token)match(input,COMMA,FOLLOW_COMMA_in_commaExpression2610); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA114);


            	    pushFollow(FOLLOW_assignmentExpression_in_commaExpression2614);
            	    y=assignmentExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assignmentExpression.add(y.getTree());

            	    // AST REWRITE
            	    // elements: x, y, COMMA
            	    // token labels: 
            	    // rule labels: retval, y, x
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.tree:null);
            	    RewriteRuleSubtreeStream stream_x=new RewriteRuleSubtreeStream(adaptor,"rule x",x!=null?x.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 432:6: -> ^( OPERATOR COMMA ^( ARGUMENT_LIST $x $y) )
            	    {
            	        // CivlCParser.g:432:9: ^( OPERATOR COMMA ^( ARGUMENT_LIST $x $y) )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(OPERATOR, "OPERATOR")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_COMMA.nextNode()
            	        );

            	        // CivlCParser.g:432:26: ^( ARGUMENT_LIST $x $y)
            	        {
            	        Object root_2 = (Object)adaptor.nil();
            	        root_2 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
            	        , root_2);

            	        adaptor.addChild(root_2, stream_x.nextTree());

            	        adaptor.addChild(root_2, stream_y.nextTree());

            	        adaptor.addChild(root_1, root_2);
            	        }

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "commaExpression"


    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // CivlCParser.g:436:1: expression : ( COLLECTIVE LPAREN commaExpression RPAREN commaExpression -> ^( COLLECTIVE commaExpression commaExpression ) | commaExpression );
    public final CivlCParser.expression_return expression() throws RecognitionException {
        CivlCParser.expression_return retval = new CivlCParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COLLECTIVE115=null;
        Token LPAREN116=null;
        Token RPAREN118=null;
        CivlCParser.commaExpression_return commaExpression117 =null;

        CivlCParser.commaExpression_return commaExpression119 =null;

        CivlCParser.commaExpression_return commaExpression120 =null;


        Object COLLECTIVE115_tree=null;
        Object LPAREN116_tree=null;
        Object RPAREN118_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COLLECTIVE=new RewriteRuleTokenStream(adaptor,"token COLLECTIVE");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_commaExpression=new RewriteRuleSubtreeStream(adaptor,"rule commaExpression");
        try {
            // CivlCParser.g:437:2: ( COLLECTIVE LPAREN commaExpression RPAREN commaExpression -> ^( COLLECTIVE commaExpression commaExpression ) | commaExpression )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==COLLECTIVE) ) {
                alt25=1;
            }
            else if ( ((LA25_0 >= ALIGNOF && LA25_0 <= AMPERSAND)||LA25_0==CHARACTER_CONSTANT||LA25_0==FLOATING_CONSTANT||LA25_0==GENERIC||LA25_0==IDENTIFIER||LA25_0==INTEGER_CONSTANT||LA25_0==LPAREN||LA25_0==MINUSMINUS||LA25_0==NOT||LA25_0==PLUS||LA25_0==PLUSPLUS||(LA25_0 >= SIZEOF && LA25_0 <= STAR)||LA25_0==STRING_LITERAL||LA25_0==SUB||LA25_0==TILDE) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // CivlCParser.g:437:4: COLLECTIVE LPAREN commaExpression RPAREN commaExpression
                    {
                    COLLECTIVE115=(Token)match(input,COLLECTIVE,FOLLOW_COLLECTIVE_in_expression2654); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLLECTIVE.add(COLLECTIVE115);


                    LPAREN116=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_expression2656); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN116);


                    pushFollow(FOLLOW_commaExpression_in_expression2658);
                    commaExpression117=commaExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_commaExpression.add(commaExpression117.getTree());

                    RPAREN118=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_expression2660); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN118);


                    pushFollow(FOLLOW_commaExpression_in_expression2662);
                    commaExpression119=commaExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_commaExpression.add(commaExpression119.getTree());

                    // AST REWRITE
                    // elements: COLLECTIVE, commaExpression, commaExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 438:4: -> ^( COLLECTIVE commaExpression commaExpression )
                    {
                        // CivlCParser.g:438:7: ^( COLLECTIVE commaExpression commaExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_COLLECTIVE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_commaExpression.nextTree());

                        adaptor.addChild(root_1, stream_commaExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:439:4: commaExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_commaExpression_in_expression2680);
                    commaExpression120=commaExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, commaExpression120.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class constantExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantExpression"
    // CivlCParser.g:443:1: constantExpression : conditionalExpression ;
    public final CivlCParser.constantExpression_return constantExpression() throws RecognitionException {
        CivlCParser.constantExpression_return retval = new CivlCParser.constantExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.conditionalExpression_return conditionalExpression121 =null;



        try {
            // CivlCParser.g:444:2: ( conditionalExpression )
            // CivlCParser.g:444:4: conditionalExpression
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_conditionalExpression_in_constantExpression2693);
            conditionalExpression121=conditionalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression121.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "constantExpression"


    public static class declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declaration"
    // CivlCParser.g:470:1: declaration : (d= declarationSpecifiers (i= initDeclaratorList -> ^( DECLARATION $d $i) | -> ^( DECLARATION $d ABSENT ) ) SEMI | staticAssertDeclaration );
    public final CivlCParser.declaration_return declaration() throws RecognitionException {
        DeclarationScope_stack.push(new DeclarationScope_scope());

        CivlCParser.declaration_return retval = new CivlCParser.declaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMI122=null;
        CivlCParser.declarationSpecifiers_return d =null;

        CivlCParser.initDeclaratorList_return i =null;

        CivlCParser.staticAssertDeclaration_return staticAssertDeclaration123 =null;


        Object SEMI122_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");
        RewriteRuleSubtreeStream stream_initDeclaratorList=new RewriteRuleSubtreeStream(adaptor,"rule initDeclaratorList");

          ((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef = false;

        try {
            // CivlCParser.g:475:2: (d= declarationSpecifiers (i= initDeclaratorList -> ^( DECLARATION $d $i) | -> ^( DECLARATION $d ABSENT ) ) SEMI | staticAssertDeclaration )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ALIGNAS||(LA27_0 >= ATOMIC && LA27_0 <= AUTO)||LA27_0==BOOL||LA27_0==CHAR||(LA27_0 >= COMPLEX && LA27_0 <= CONST)||LA27_0==DOUBLE||LA27_0==ENUM||LA27_0==EXTERN||LA27_0==FLOAT||LA27_0==IDENTIFIER||(LA27_0 >= INLINE && LA27_0 <= INT)||LA27_0==LONG||LA27_0==NORETURN||LA27_0==OUTPUT||LA27_0==PROC||(LA27_0 >= REGISTER && LA27_0 <= RESTRICT)||(LA27_0 >= SHORT && LA27_0 <= SIGNED)||LA27_0==STATIC||LA27_0==STRUCT||LA27_0==THREADLOCAL||(LA27_0 >= TYPEDEF && LA27_0 <= UNSIGNED)||(LA27_0 >= VOID && LA27_0 <= VOLATILE)) ) {
                alt27=1;
            }
            else if ( (LA27_0==STATICASSERT) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;

            }
            switch (alt27) {
                case 1 :
                    // CivlCParser.g:475:4: d= declarationSpecifiers (i= initDeclaratorList -> ^( DECLARATION $d $i) | -> ^( DECLARATION $d ABSENT ) ) SEMI
                    {
                    pushFollow(FOLLOW_declarationSpecifiers_in_declaration2721);
                    d=declarationSpecifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declarationSpecifiers.add(d.getTree());

                    // CivlCParser.g:476:4: (i= initDeclaratorList -> ^( DECLARATION $d $i) | -> ^( DECLARATION $d ABSENT ) )
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==IDENTIFIER||LA26_0==LPAREN||LA26_0==STAR) ) {
                        alt26=1;
                    }
                    else if ( (LA26_0==SEMI) ) {
                        alt26=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 0, input);

                        throw nvae;

                    }
                    switch (alt26) {
                        case 1 :
                            // CivlCParser.g:477:6: i= initDeclaratorList
                            {
                            pushFollow(FOLLOW_initDeclaratorList_in_declaration2736);
                            i=initDeclaratorList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_initDeclaratorList.add(i.getTree());

                            // AST REWRITE
                            // elements: i, d
                            // token labels: 
                            // rule labels: retval, d, i
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
                            RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 478:6: -> ^( DECLARATION $d $i)
                            {
                                // CivlCParser.g:478:9: ^( DECLARATION $d $i)
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(DECLARATION, "DECLARATION")
                                , root_1);

                                adaptor.addChild(root_1, stream_d.nextTree());

                                adaptor.addChild(root_1, stream_i.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:479:6: 
                            {
                            // AST REWRITE
                            // elements: d
                            // token labels: 
                            // rule labels: retval, d
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 479:6: -> ^( DECLARATION $d ABSENT )
                            {
                                // CivlCParser.g:479:9: ^( DECLARATION $d ABSENT )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(DECLARATION, "DECLARATION")
                                , root_1);

                                adaptor.addChild(root_1, stream_d.nextTree());

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    SEMI122=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration2779); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI122);


                    }
                    break;
                case 2 :
                    // CivlCParser.g:482:4: staticAssertDeclaration
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_staticAssertDeclaration_in_declaration2784);
                    staticAssertDeclaration123=staticAssertDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, staticAssertDeclaration123.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            DeclarationScope_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "declaration"


    public static class declarationSpecifiers_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarationSpecifiers"
    // CivlCParser.g:489:1: declarationSpecifiers : l= declarationSpecifierList -> ^( DECLARATION_SPECIFIERS declarationSpecifierList ) ;
    public final CivlCParser.declarationSpecifiers_return declarationSpecifiers() throws RecognitionException {
        CivlCParser.declarationSpecifiers_return retval = new CivlCParser.declarationSpecifiers_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.declarationSpecifierList_return l =null;


        RewriteRuleSubtreeStream stream_declarationSpecifierList=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifierList");
        try {
            // CivlCParser.g:490:2: (l= declarationSpecifierList -> ^( DECLARATION_SPECIFIERS declarationSpecifierList ) )
            // CivlCParser.g:490:4: l= declarationSpecifierList
            {
            pushFollow(FOLLOW_declarationSpecifierList_in_declarationSpecifiers2799);
            l=declarationSpecifierList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarationSpecifierList.add(l.getTree());

            // AST REWRITE
            // elements: declarationSpecifierList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 491:4: -> ^( DECLARATION_SPECIFIERS declarationSpecifierList )
            {
                // CivlCParser.g:491:7: ^( DECLARATION_SPECIFIERS declarationSpecifierList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DECLARATION_SPECIFIERS, "DECLARATION_SPECIFIERS")
                , root_1);

                adaptor.addChild(root_1, stream_declarationSpecifierList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "declarationSpecifiers"


    public static class declarationSpecifierList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarationSpecifierList"
    // CivlCParser.g:496:1: declarationSpecifierList : ({...}?s= declarationSpecifier )+ ;
    public final CivlCParser.declarationSpecifierList_return declarationSpecifierList() throws RecognitionException {
        CivlCParser.declarationSpecifierList_return retval = new CivlCParser.declarationSpecifierList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.declarationSpecifier_return s =null;



        try {
            // CivlCParser.g:497:2: ( ({...}?s= declarationSpecifier )+ )
            // CivlCParser.g:497:4: ({...}?s= declarationSpecifier )+
            {
            root_0 = (Object)adaptor.nil();


            // CivlCParser.g:497:4: ({...}?s= declarationSpecifier )+
            int cnt28=0;
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==IDENTIFIER) ) {
                    int LA28_1 = input.LA(2);

                    if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                        alt28=1;
                    }


                }
                else if ( (LA28_0==ALIGNAS||(LA28_0 >= ATOMIC && LA28_0 <= AUTO)||LA28_0==BOOL||LA28_0==CHAR||(LA28_0 >= COMPLEX && LA28_0 <= CONST)||LA28_0==DOUBLE||LA28_0==ENUM||LA28_0==EXTERN||LA28_0==FLOAT||(LA28_0 >= INLINE && LA28_0 <= INT)||LA28_0==LONG||LA28_0==NORETURN||LA28_0==OUTPUT||LA28_0==PROC||(LA28_0 >= REGISTER && LA28_0 <= RESTRICT)||(LA28_0 >= SHORT && LA28_0 <= SIGNED)||LA28_0==STATIC||LA28_0==STRUCT||LA28_0==THREADLOCAL||(LA28_0 >= TYPEDEF && LA28_0 <= UNSIGNED)||(LA28_0 >= VOID && LA28_0 <= VOLATILE)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // CivlCParser.g:498:6: {...}?s= declarationSpecifier
            	    {
            	    if ( !((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        throw new FailedPredicateException(input, "declarationSpecifierList", "!$DeclarationScope::isTypedef || input.LT(2).getType() != SEMI ");
            	    }

            	    pushFollow(FOLLOW_declarationSpecifier_in_declarationSpecifierList2839);
            	    s=declarationSpecifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt28 >= 1 ) break loop28;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(28, input);
                        throw eee;
                }
                cnt28++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "declarationSpecifierList"


    public static class declarationSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarationSpecifier"
    // CivlCParser.g:503:1: declarationSpecifier : (s= storageClassSpecifier | typeSpecifierOrQualifier | functionSpecifier | alignmentSpecifier );
    public final CivlCParser.declarationSpecifier_return declarationSpecifier() throws RecognitionException {
        CivlCParser.declarationSpecifier_return retval = new CivlCParser.declarationSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.storageClassSpecifier_return s =null;

        CivlCParser.typeSpecifierOrQualifier_return typeSpecifierOrQualifier124 =null;

        CivlCParser.functionSpecifier_return functionSpecifier125 =null;

        CivlCParser.alignmentSpecifier_return alignmentSpecifier126 =null;



        try {
            // CivlCParser.g:504:2: (s= storageClassSpecifier | typeSpecifierOrQualifier | functionSpecifier | alignmentSpecifier )
            int alt29=4;
            switch ( input.LA(1) ) {
            case AUTO:
            case EXTERN:
            case REGISTER:
            case STATIC:
            case THREADLOCAL:
            case TYPEDEF:
                {
                alt29=1;
                }
                break;
            case ATOMIC:
            case BOOL:
            case CHAR:
            case COMPLEX:
            case CONST:
            case DOUBLE:
            case ENUM:
            case FLOAT:
            case IDENTIFIER:
            case INPUT:
            case INT:
            case LONG:
            case OUTPUT:
            case PROC:
            case RESTRICT:
            case SHORT:
            case SIGNED:
            case STRUCT:
            case UNION:
            case UNSIGNED:
            case VOID:
            case VOLATILE:
                {
                alt29=2;
                }
                break;
            case INLINE:
            case NORETURN:
                {
                alt29=3;
                }
                break;
            case ALIGNAS:
                {
                alt29=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // CivlCParser.g:504:4: s= storageClassSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_storageClassSpecifier_in_declarationSpecifier2858);
                    s=storageClassSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:505:4: typeSpecifierOrQualifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeSpecifierOrQualifier_in_declarationSpecifier2863);
                    typeSpecifierOrQualifier124=typeSpecifierOrQualifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSpecifierOrQualifier124.getTree());

                    }
                    break;
                case 3 :
                    // CivlCParser.g:506:4: functionSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionSpecifier_in_declarationSpecifier2868);
                    functionSpecifier125=functionSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionSpecifier125.getTree());

                    }
                    break;
                case 4 :
                    // CivlCParser.g:507:4: alignmentSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_alignmentSpecifier_in_declarationSpecifier2873);
                    alignmentSpecifier126=alignmentSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, alignmentSpecifier126.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "declarationSpecifier"


    public static class typeSpecifierOrQualifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeSpecifierOrQualifier"
    // CivlCParser.g:518:1: typeSpecifierOrQualifier : ( ( typeSpecifier )=> typeSpecifier | typeQualifier );
    public final CivlCParser.typeSpecifierOrQualifier_return typeSpecifierOrQualifier() throws RecognitionException {
        CivlCParser.typeSpecifierOrQualifier_return retval = new CivlCParser.typeSpecifierOrQualifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.typeSpecifier_return typeSpecifier127 =null;

        CivlCParser.typeQualifier_return typeQualifier128 =null;



        try {
            // CivlCParser.g:519:2: ( ( typeSpecifier )=> typeSpecifier | typeQualifier )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==VOID) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==CHAR) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==SHORT) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==INT) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==LONG) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==FLOAT) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==DOUBLE) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==SIGNED) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==UNSIGNED) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==BOOL) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==COMPLEX) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==PROC) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==ATOMIC) ) {
                int LA30_13 = input.LA(2);

                if ( (synpred5_CivlCParser()) ) {
                    alt30=1;
                }
                else if ( (true) ) {
                    alt30=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 13, input);

                    throw nvae;

                }
            }
            else if ( (LA30_0==STRUCT||LA30_0==UNION) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==ENUM) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==IDENTIFIER) && (synpred5_CivlCParser())) {
                alt30=1;
            }
            else if ( (LA30_0==CONST||LA30_0==INPUT||LA30_0==OUTPUT||LA30_0==RESTRICT||LA30_0==VOLATILE) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }
            switch (alt30) {
                case 1 :
                    // CivlCParser.g:519:4: ( typeSpecifier )=> typeSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeSpecifier_in_typeSpecifierOrQualifier2891);
                    typeSpecifier127=typeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSpecifier127.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:520:11: typeQualifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeQualifier_in_typeSpecifierOrQualifier2903);
                    typeQualifier128=typeQualifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeQualifier128.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typeSpecifierOrQualifier"


    public static class initDeclaratorList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "initDeclaratorList"
    // CivlCParser.g:527:1: initDeclaratorList :i+= initDeclarator ( COMMA i+= initDeclarator )* -> ^( INIT_DECLARATOR_LIST ( $i)+ ) ;
    public final CivlCParser.initDeclaratorList_return initDeclaratorList() throws RecognitionException {
        CivlCParser.initDeclaratorList_return retval = new CivlCParser.initDeclaratorList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA129=null;
        List list_i=null;
        RuleReturnScope i = null;
        Object COMMA129_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_initDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule initDeclarator");
        try {
            // CivlCParser.g:528:2: (i+= initDeclarator ( COMMA i+= initDeclarator )* -> ^( INIT_DECLARATOR_LIST ( $i)+ ) )
            // CivlCParser.g:528:4: i+= initDeclarator ( COMMA i+= initDeclarator )*
            {
            pushFollow(FOLLOW_initDeclarator_in_initDeclaratorList2918);
            i=initDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_initDeclarator.add(i.getTree());
            if (list_i==null) list_i=new ArrayList();
            list_i.add(i.getTree());


            // CivlCParser.g:528:22: ( COMMA i+= initDeclarator )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==COMMA) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // CivlCParser.g:528:23: COMMA i+= initDeclarator
            	    {
            	    COMMA129=(Token)match(input,COMMA,FOLLOW_COMMA_in_initDeclaratorList2921); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA129);


            	    pushFollow(FOLLOW_initDeclarator_in_initDeclaratorList2925);
            	    i=initDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_initDeclarator.add(i.getTree());
            	    if (list_i==null) list_i=new ArrayList();
            	    list_i.add(i.getTree());


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            // AST REWRITE
            // elements: i
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: i
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"token i",list_i);
            root_0 = (Object)adaptor.nil();
            // 529:4: -> ^( INIT_DECLARATOR_LIST ( $i)+ )
            {
                // CivlCParser.g:529:7: ^( INIT_DECLARATOR_LIST ( $i)+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(INIT_DECLARATOR_LIST, "INIT_DECLARATOR_LIST")
                , root_1);

                if ( !(stream_i.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_i.hasNext() ) {
                    adaptor.addChild(root_1, stream_i.nextTree());

                }
                stream_i.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "initDeclaratorList"


    public static class initDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "initDeclarator"
    // CivlCParser.g:537:1: initDeclarator : d= declarator ( -> ^( INIT_DECLARATOR $d ABSENT ) | ( ASSIGN i= initializer ) -> ^( INIT_DECLARATOR $d $i) ) ;
    public final CivlCParser.initDeclarator_return initDeclarator() throws RecognitionException {
        CivlCParser.initDeclarator_return retval = new CivlCParser.initDeclarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ASSIGN130=null;
        CivlCParser.declarator_return d =null;

        CivlCParser.initializer_return i =null;


        Object ASSIGN130_tree=null;
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_declarator=new RewriteRuleSubtreeStream(adaptor,"rule declarator");
        RewriteRuleSubtreeStream stream_initializer=new RewriteRuleSubtreeStream(adaptor,"rule initializer");
        try {
            // CivlCParser.g:538:2: (d= declarator ( -> ^( INIT_DECLARATOR $d ABSENT ) | ( ASSIGN i= initializer ) -> ^( INIT_DECLARATOR $d $i) ) )
            // CivlCParser.g:538:4: d= declarator ( -> ^( INIT_DECLARATOR $d ABSENT ) | ( ASSIGN i= initializer ) -> ^( INIT_DECLARATOR $d $i) )
            {
            pushFollow(FOLLOW_declarator_in_initDeclarator2955);
            d=declarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarator.add(d.getTree());

            // CivlCParser.g:539:4: ( -> ^( INIT_DECLARATOR $d ABSENT ) | ( ASSIGN i= initializer ) -> ^( INIT_DECLARATOR $d $i) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==COMMA||LA32_0==SEMI) ) {
                alt32=1;
            }
            else if ( (LA32_0==ASSIGN) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // CivlCParser.g:539:7: 
                    {
                    // AST REWRITE
                    // elements: d
                    // token labels: 
                    // rule labels: retval, d
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 539:7: -> ^( INIT_DECLARATOR $d ABSENT )
                    {
                        // CivlCParser.g:539:10: ^( INIT_DECLARATOR $d ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(INIT_DECLARATOR, "INIT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_d.nextTree());

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:540:6: ( ASSIGN i= initializer )
                    {
                    // CivlCParser.g:540:6: ( ASSIGN i= initializer )
                    // CivlCParser.g:540:7: ASSIGN i= initializer
                    {
                    ASSIGN130=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_initDeclarator2980); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN130);


                    pushFollow(FOLLOW_initializer_in_initDeclarator2984);
                    i=initializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_initializer.add(i.getTree());

                    }


                    // AST REWRITE
                    // elements: i, d
                    // token labels: 
                    // rule labels: retval, d, i
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
                    RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 540:29: -> ^( INIT_DECLARATOR $d $i)
                    {
                        // CivlCParser.g:540:32: ^( INIT_DECLARATOR $d $i)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(INIT_DECLARATOR, "INIT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_d.nextTree());

                        adaptor.addChild(root_1, stream_i.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "initDeclarator"


    public static class storageClassSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "storageClassSpecifier"
    // CivlCParser.g:545:1: storageClassSpecifier : ( TYPEDEF | ( EXTERN | STATIC | THREADLOCAL | AUTO | REGISTER ) );
    public final CivlCParser.storageClassSpecifier_return storageClassSpecifier() throws RecognitionException {
        CivlCParser.storageClassSpecifier_return retval = new CivlCParser.storageClassSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token TYPEDEF131=null;
        Token set132=null;

        Object TYPEDEF131_tree=null;
        Object set132_tree=null;

        try {
            // CivlCParser.g:546:2: ( TYPEDEF | ( EXTERN | STATIC | THREADLOCAL | AUTO | REGISTER ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==TYPEDEF) ) {
                alt33=1;
            }
            else if ( (LA33_0==AUTO||LA33_0==EXTERN||LA33_0==REGISTER||LA33_0==STATIC||LA33_0==THREADLOCAL) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // CivlCParser.g:546:4: TYPEDEF
                    {
                    root_0 = (Object)adaptor.nil();


                    TYPEDEF131=(Token)match(input,TYPEDEF,FOLLOW_TYPEDEF_in_storageClassSpecifier3015); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TYPEDEF131_tree = 
                    (Object)adaptor.create(TYPEDEF131)
                    ;
                    adaptor.addChild(root_0, TYPEDEF131_tree);
                    }

                    if ( state.backtracking==0 ) {((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef = true;}

                    }
                    break;
                case 2 :
                    // CivlCParser.g:547:4: ( EXTERN | STATIC | THREADLOCAL | AUTO | REGISTER )
                    {
                    root_0 = (Object)adaptor.nil();


                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==AUTO||input.LA(1)==EXTERN||input.LA(1)==REGISTER||input.LA(1)==STATIC||input.LA(1)==THREADLOCAL ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (Object)adaptor.create(set132)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "storageClassSpecifier"


    public static class typeSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeSpecifier"
    // CivlCParser.g:551:1: typeSpecifier : ( VOID | CHAR | SHORT | INT | LONG | FLOAT | DOUBLE | SIGNED | UNSIGNED | BOOL | COMPLEX | PROC | atomicTypeSpecifier | structOrUnionSpecifier | enumSpecifier | typedefName );
    public final CivlCParser.typeSpecifier_return typeSpecifier() throws RecognitionException {
        CivlCParser.typeSpecifier_return retval = new CivlCParser.typeSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token VOID133=null;
        Token CHAR134=null;
        Token SHORT135=null;
        Token INT136=null;
        Token LONG137=null;
        Token FLOAT138=null;
        Token DOUBLE139=null;
        Token SIGNED140=null;
        Token UNSIGNED141=null;
        Token BOOL142=null;
        Token COMPLEX143=null;
        Token PROC144=null;
        CivlCParser.atomicTypeSpecifier_return atomicTypeSpecifier145 =null;

        CivlCParser.structOrUnionSpecifier_return structOrUnionSpecifier146 =null;

        CivlCParser.enumSpecifier_return enumSpecifier147 =null;

        CivlCParser.typedefName_return typedefName148 =null;


        Object VOID133_tree=null;
        Object CHAR134_tree=null;
        Object SHORT135_tree=null;
        Object INT136_tree=null;
        Object LONG137_tree=null;
        Object FLOAT138_tree=null;
        Object DOUBLE139_tree=null;
        Object SIGNED140_tree=null;
        Object UNSIGNED141_tree=null;
        Object BOOL142_tree=null;
        Object COMPLEX143_tree=null;
        Object PROC144_tree=null;

        try {
            // CivlCParser.g:552:2: ( VOID | CHAR | SHORT | INT | LONG | FLOAT | DOUBLE | SIGNED | UNSIGNED | BOOL | COMPLEX | PROC | atomicTypeSpecifier | structOrUnionSpecifier | enumSpecifier | typedefName )
            int alt34=16;
            switch ( input.LA(1) ) {
            case VOID:
                {
                alt34=1;
                }
                break;
            case CHAR:
                {
                alt34=2;
                }
                break;
            case SHORT:
                {
                alt34=3;
                }
                break;
            case INT:
                {
                alt34=4;
                }
                break;
            case LONG:
                {
                alt34=5;
                }
                break;
            case FLOAT:
                {
                alt34=6;
                }
                break;
            case DOUBLE:
                {
                alt34=7;
                }
                break;
            case SIGNED:
                {
                alt34=8;
                }
                break;
            case UNSIGNED:
                {
                alt34=9;
                }
                break;
            case BOOL:
                {
                alt34=10;
                }
                break;
            case COMPLEX:
                {
                alt34=11;
                }
                break;
            case PROC:
                {
                alt34=12;
                }
                break;
            case ATOMIC:
                {
                alt34=13;
                }
                break;
            case STRUCT:
            case UNION:
                {
                alt34=14;
                }
                break;
            case ENUM:
                {
                alt34=15;
                }
                break;
            case IDENTIFIER:
                {
                alt34=16;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }

            switch (alt34) {
                case 1 :
                    // CivlCParser.g:552:4: VOID
                    {
                    root_0 = (Object)adaptor.nil();


                    VOID133=(Token)match(input,VOID,FOLLOW_VOID_in_typeSpecifier3053); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VOID133_tree = 
                    (Object)adaptor.create(VOID133)
                    ;
                    adaptor.addChild(root_0, VOID133_tree);
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:552:11: CHAR
                    {
                    root_0 = (Object)adaptor.nil();


                    CHAR134=(Token)match(input,CHAR,FOLLOW_CHAR_in_typeSpecifier3057); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHAR134_tree = 
                    (Object)adaptor.create(CHAR134)
                    ;
                    adaptor.addChild(root_0, CHAR134_tree);
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:552:18: SHORT
                    {
                    root_0 = (Object)adaptor.nil();


                    SHORT135=(Token)match(input,SHORT,FOLLOW_SHORT_in_typeSpecifier3061); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SHORT135_tree = 
                    (Object)adaptor.create(SHORT135)
                    ;
                    adaptor.addChild(root_0, SHORT135_tree);
                    }

                    }
                    break;
                case 4 :
                    // CivlCParser.g:552:26: INT
                    {
                    root_0 = (Object)adaptor.nil();


                    INT136=(Token)match(input,INT,FOLLOW_INT_in_typeSpecifier3065); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT136_tree = 
                    (Object)adaptor.create(INT136)
                    ;
                    adaptor.addChild(root_0, INT136_tree);
                    }

                    }
                    break;
                case 5 :
                    // CivlCParser.g:552:32: LONG
                    {
                    root_0 = (Object)adaptor.nil();


                    LONG137=(Token)match(input,LONG,FOLLOW_LONG_in_typeSpecifier3069); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONG137_tree = 
                    (Object)adaptor.create(LONG137)
                    ;
                    adaptor.addChild(root_0, LONG137_tree);
                    }

                    }
                    break;
                case 6 :
                    // CivlCParser.g:552:39: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    FLOAT138=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_typeSpecifier3073); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT138_tree = 
                    (Object)adaptor.create(FLOAT138)
                    ;
                    adaptor.addChild(root_0, FLOAT138_tree);
                    }

                    }
                    break;
                case 7 :
                    // CivlCParser.g:552:47: DOUBLE
                    {
                    root_0 = (Object)adaptor.nil();


                    DOUBLE139=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_typeSpecifier3077); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLE139_tree = 
                    (Object)adaptor.create(DOUBLE139)
                    ;
                    adaptor.addChild(root_0, DOUBLE139_tree);
                    }

                    }
                    break;
                case 8 :
                    // CivlCParser.g:553:4: SIGNED
                    {
                    root_0 = (Object)adaptor.nil();


                    SIGNED140=(Token)match(input,SIGNED,FOLLOW_SIGNED_in_typeSpecifier3082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SIGNED140_tree = 
                    (Object)adaptor.create(SIGNED140)
                    ;
                    adaptor.addChild(root_0, SIGNED140_tree);
                    }

                    }
                    break;
                case 9 :
                    // CivlCParser.g:553:13: UNSIGNED
                    {
                    root_0 = (Object)adaptor.nil();


                    UNSIGNED141=(Token)match(input,UNSIGNED,FOLLOW_UNSIGNED_in_typeSpecifier3086); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    UNSIGNED141_tree = 
                    (Object)adaptor.create(UNSIGNED141)
                    ;
                    adaptor.addChild(root_0, UNSIGNED141_tree);
                    }

                    }
                    break;
                case 10 :
                    // CivlCParser.g:553:24: BOOL
                    {
                    root_0 = (Object)adaptor.nil();


                    BOOL142=(Token)match(input,BOOL,FOLLOW_BOOL_in_typeSpecifier3090); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL142_tree = 
                    (Object)adaptor.create(BOOL142)
                    ;
                    adaptor.addChild(root_0, BOOL142_tree);
                    }

                    }
                    break;
                case 11 :
                    // CivlCParser.g:553:31: COMPLEX
                    {
                    root_0 = (Object)adaptor.nil();


                    COMPLEX143=(Token)match(input,COMPLEX,FOLLOW_COMPLEX_in_typeSpecifier3094); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMPLEX143_tree = 
                    (Object)adaptor.create(COMPLEX143)
                    ;
                    adaptor.addChild(root_0, COMPLEX143_tree);
                    }

                    }
                    break;
                case 12 :
                    // CivlCParser.g:554:4: PROC
                    {
                    root_0 = (Object)adaptor.nil();


                    PROC144=(Token)match(input,PROC,FOLLOW_PROC_in_typeSpecifier3099); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PROC144_tree = 
                    (Object)adaptor.create(PROC144)
                    ;
                    adaptor.addChild(root_0, PROC144_tree);
                    }

                    }
                    break;
                case 13 :
                    // CivlCParser.g:555:4: atomicTypeSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicTypeSpecifier_in_typeSpecifier3104);
                    atomicTypeSpecifier145=atomicTypeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTypeSpecifier145.getTree());

                    }
                    break;
                case 14 :
                    // CivlCParser.g:556:4: structOrUnionSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_structOrUnionSpecifier_in_typeSpecifier3109);
                    structOrUnionSpecifier146=structOrUnionSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, structOrUnionSpecifier146.getTree());

                    }
                    break;
                case 15 :
                    // CivlCParser.g:557:4: enumSpecifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_enumSpecifier_in_typeSpecifier3114);
                    enumSpecifier147=enumSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumSpecifier147.getTree());

                    }
                    break;
                case 16 :
                    // CivlCParser.g:558:4: typedefName
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typedefName_in_typeSpecifier3119);
                    typedefName148=typedefName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typedefName148.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typeSpecifier"


    public static class structOrUnionSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structOrUnionSpecifier"
    // CivlCParser.g:566:1: structOrUnionSpecifier : structOrUnion ( IDENTIFIER LCURLY structDeclarationList RCURLY -> ^( structOrUnion IDENTIFIER structDeclarationList RCURLY ) | LCURLY structDeclarationList RCURLY -> ^( structOrUnion ABSENT structDeclarationList RCURLY ) | IDENTIFIER -> ^( structOrUnion IDENTIFIER ABSENT ) ) ;
    public final CivlCParser.structOrUnionSpecifier_return structOrUnionSpecifier() throws RecognitionException {
        CivlCParser.structOrUnionSpecifier_return retval = new CivlCParser.structOrUnionSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER150=null;
        Token LCURLY151=null;
        Token RCURLY153=null;
        Token LCURLY154=null;
        Token RCURLY156=null;
        Token IDENTIFIER157=null;
        CivlCParser.structOrUnion_return structOrUnion149 =null;

        CivlCParser.structDeclarationList_return structDeclarationList152 =null;

        CivlCParser.structDeclarationList_return structDeclarationList155 =null;


        Object IDENTIFIER150_tree=null;
        Object LCURLY151_tree=null;
        Object RCURLY153_tree=null;
        Object LCURLY154_tree=null;
        Object RCURLY156_tree=null;
        Object IDENTIFIER157_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_structDeclarationList=new RewriteRuleSubtreeStream(adaptor,"rule structDeclarationList");
        RewriteRuleSubtreeStream stream_structOrUnion=new RewriteRuleSubtreeStream(adaptor,"rule structOrUnion");
        try {
            // CivlCParser.g:567:2: ( structOrUnion ( IDENTIFIER LCURLY structDeclarationList RCURLY -> ^( structOrUnion IDENTIFIER structDeclarationList RCURLY ) | LCURLY structDeclarationList RCURLY -> ^( structOrUnion ABSENT structDeclarationList RCURLY ) | IDENTIFIER -> ^( structOrUnion IDENTIFIER ABSENT ) ) )
            // CivlCParser.g:567:4: structOrUnion ( IDENTIFIER LCURLY structDeclarationList RCURLY -> ^( structOrUnion IDENTIFIER structDeclarationList RCURLY ) | LCURLY structDeclarationList RCURLY -> ^( structOrUnion ABSENT structDeclarationList RCURLY ) | IDENTIFIER -> ^( structOrUnion IDENTIFIER ABSENT ) )
            {
            pushFollow(FOLLOW_structOrUnion_in_structOrUnionSpecifier3132);
            structOrUnion149=structOrUnion();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_structOrUnion.add(structOrUnion149.getTree());

            // CivlCParser.g:568:6: ( IDENTIFIER LCURLY structDeclarationList RCURLY -> ^( structOrUnion IDENTIFIER structDeclarationList RCURLY ) | LCURLY structDeclarationList RCURLY -> ^( structOrUnion ABSENT structDeclarationList RCURLY ) | IDENTIFIER -> ^( structOrUnion IDENTIFIER ABSENT ) )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==IDENTIFIER) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==LCURLY) ) {
                    alt35=1;
                }
                else if ( (LA35_1==EOF||LA35_1==ALIGNAS||(LA35_1 >= ATOMIC && LA35_1 <= AUTO)||LA35_1==BOOL||LA35_1==CHAR||(LA35_1 >= COLON && LA35_1 <= COMMA)||(LA35_1 >= COMPLEX && LA35_1 <= CONST)||LA35_1==DOUBLE||LA35_1==ENUM||LA35_1==EXTERN||LA35_1==FLOAT||LA35_1==IDENTIFIER||(LA35_1 >= INLINE && LA35_1 <= INT)||(LA35_1 >= LONG && LA35_1 <= LSQUARE)||LA35_1==NORETURN||LA35_1==OUTPUT||LA35_1==PROC||(LA35_1 >= REGISTER && LA35_1 <= RESTRICT)||LA35_1==RPAREN||LA35_1==SEMI||(LA35_1 >= SHORT && LA35_1 <= SIGNED)||LA35_1==STAR||LA35_1==STATIC||LA35_1==STRUCT||LA35_1==THREADLOCAL||(LA35_1 >= TYPEDEF && LA35_1 <= UNSIGNED)||(LA35_1 >= VOID && LA35_1 <= VOLATILE)) ) {
                    alt35=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA35_0==LCURLY) ) {
                alt35=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // CivlCParser.g:568:8: IDENTIFIER LCURLY structDeclarationList RCURLY
                    {
                    IDENTIFIER150=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_structOrUnionSpecifier3141); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER150);


                    LCURLY151=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_structOrUnionSpecifier3143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY151);


                    pushFollow(FOLLOW_structDeclarationList_in_structOrUnionSpecifier3145);
                    structDeclarationList152=structDeclarationList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_structDeclarationList.add(structDeclarationList152.getTree());

                    RCURLY153=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_structOrUnionSpecifier3147); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY153);


                    // AST REWRITE
                    // elements: structOrUnion, RCURLY, IDENTIFIER, structDeclarationList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 569:8: -> ^( structOrUnion IDENTIFIER structDeclarationList RCURLY )
                    {
                        // CivlCParser.g:569:11: ^( structOrUnion IDENTIFIER structDeclarationList RCURLY )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_structOrUnion.nextNode(), root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, stream_structDeclarationList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RCURLY.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:570:8: LCURLY structDeclarationList RCURLY
                    {
                    LCURLY154=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_structOrUnionSpecifier3175); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY154);


                    pushFollow(FOLLOW_structDeclarationList_in_structOrUnionSpecifier3177);
                    structDeclarationList155=structDeclarationList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_structDeclarationList.add(structDeclarationList155.getTree());

                    RCURLY156=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_structOrUnionSpecifier3179); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY156);


                    // AST REWRITE
                    // elements: RCURLY, structDeclarationList, structOrUnion
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 571:8: -> ^( structOrUnion ABSENT structDeclarationList RCURLY )
                    {
                        // CivlCParser.g:571:11: ^( structOrUnion ABSENT structDeclarationList RCURLY )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_structOrUnion.nextNode(), root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_structDeclarationList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RCURLY.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:572:8: IDENTIFIER
                    {
                    IDENTIFIER157=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_structOrUnionSpecifier3207); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER157);


                    // AST REWRITE
                    // elements: structOrUnion, IDENTIFIER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 573:8: -> ^( structOrUnion IDENTIFIER ABSENT )
                    {
                        // CivlCParser.g:573:11: ^( structOrUnion IDENTIFIER ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_structOrUnion.nextNode(), root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structOrUnionSpecifier"


    public static class structOrUnion_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structOrUnion"
    // CivlCParser.g:578:1: structOrUnion : ( STRUCT | UNION );
    public final CivlCParser.structOrUnion_return structOrUnion() throws RecognitionException {
        CivlCParser.structOrUnion_return retval = new CivlCParser.structOrUnion_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set158=null;

        Object set158_tree=null;

        try {
            // CivlCParser.g:579:2: ( STRUCT | UNION )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set158=(Token)input.LT(1);

            if ( input.LA(1)==STRUCT||input.LA(1)==UNION ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set158)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structOrUnion"


    public static class structDeclarationList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structDeclarationList"
    // CivlCParser.g:586:1: structDeclarationList : ( structDeclaration )+ -> ^( STRUCT_DECLARATION_LIST ( structDeclaration )+ ) ;
    public final CivlCParser.structDeclarationList_return structDeclarationList() throws RecognitionException {
        CivlCParser.structDeclarationList_return retval = new CivlCParser.structDeclarationList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.structDeclaration_return structDeclaration159 =null;


        RewriteRuleSubtreeStream stream_structDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule structDeclaration");
        try {
            // CivlCParser.g:587:2: ( ( structDeclaration )+ -> ^( STRUCT_DECLARATION_LIST ( structDeclaration )+ ) )
            // CivlCParser.g:587:4: ( structDeclaration )+
            {
            // CivlCParser.g:587:4: ( structDeclaration )+
            int cnt36=0;
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==ATOMIC||LA36_0==BOOL||LA36_0==CHAR||(LA36_0 >= COMPLEX && LA36_0 <= CONST)||LA36_0==DOUBLE||LA36_0==ENUM||LA36_0==FLOAT||LA36_0==IDENTIFIER||(LA36_0 >= INPUT && LA36_0 <= INT)||LA36_0==LONG||LA36_0==OUTPUT||LA36_0==PROC||LA36_0==RESTRICT||(LA36_0 >= SHORT && LA36_0 <= SIGNED)||LA36_0==STATICASSERT||LA36_0==STRUCT||(LA36_0 >= UNION && LA36_0 <= UNSIGNED)||(LA36_0 >= VOID && LA36_0 <= VOLATILE)) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // CivlCParser.g:587:4: structDeclaration
            	    {
            	    pushFollow(FOLLOW_structDeclaration_in_structDeclarationList3261);
            	    structDeclaration159=structDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_structDeclaration.add(structDeclaration159.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt36 >= 1 ) break loop36;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(36, input);
                        throw eee;
                }
                cnt36++;
            } while (true);


            // AST REWRITE
            // elements: structDeclaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 588:4: -> ^( STRUCT_DECLARATION_LIST ( structDeclaration )+ )
            {
                // CivlCParser.g:588:7: ^( STRUCT_DECLARATION_LIST ( structDeclaration )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(STRUCT_DECLARATION_LIST, "STRUCT_DECLARATION_LIST")
                , root_1);

                if ( !(stream_structDeclaration.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_structDeclaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_structDeclaration.nextTree());

                }
                stream_structDeclaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structDeclarationList"


    public static class structDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structDeclaration"
    // CivlCParser.g:602:1: structDeclaration : (s= specifierQualifierList ( -> ^( STRUCT_DECLARATION $s ABSENT ) | structDeclaratorList -> ^( STRUCT_DECLARATION $s structDeclaratorList ) ) SEMI | staticAssertDeclaration );
    public final CivlCParser.structDeclaration_return structDeclaration() throws RecognitionException {
        DeclarationScope_stack.push(new DeclarationScope_scope());

        CivlCParser.structDeclaration_return retval = new CivlCParser.structDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMI161=null;
        CivlCParser.specifierQualifierList_return s =null;

        CivlCParser.structDeclaratorList_return structDeclaratorList160 =null;

        CivlCParser.staticAssertDeclaration_return staticAssertDeclaration162 =null;


        Object SEMI161_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_structDeclaratorList=new RewriteRuleSubtreeStream(adaptor,"rule structDeclaratorList");
        RewriteRuleSubtreeStream stream_specifierQualifierList=new RewriteRuleSubtreeStream(adaptor,"rule specifierQualifierList");

          ((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef = false;

        try {
            // CivlCParser.g:607:5: (s= specifierQualifierList ( -> ^( STRUCT_DECLARATION $s ABSENT ) | structDeclaratorList -> ^( STRUCT_DECLARATION $s structDeclaratorList ) ) SEMI | staticAssertDeclaration )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==ATOMIC||LA38_0==BOOL||LA38_0==CHAR||(LA38_0 >= COMPLEX && LA38_0 <= CONST)||LA38_0==DOUBLE||LA38_0==ENUM||LA38_0==FLOAT||LA38_0==IDENTIFIER||(LA38_0 >= INPUT && LA38_0 <= INT)||LA38_0==LONG||LA38_0==OUTPUT||LA38_0==PROC||LA38_0==RESTRICT||(LA38_0 >= SHORT && LA38_0 <= SIGNED)||LA38_0==STRUCT||(LA38_0 >= UNION && LA38_0 <= UNSIGNED)||(LA38_0 >= VOID && LA38_0 <= VOLATILE)) ) {
                alt38=1;
            }
            else if ( (LA38_0==STATICASSERT) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // CivlCParser.g:607:7: s= specifierQualifierList ( -> ^( STRUCT_DECLARATION $s ABSENT ) | structDeclaratorList -> ^( STRUCT_DECLARATION $s structDeclaratorList ) ) SEMI
                    {
                    pushFollow(FOLLOW_specifierQualifierList_in_structDeclaration3302);
                    s=specifierQualifierList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_specifierQualifierList.add(s.getTree());

                    // CivlCParser.g:608:7: ( -> ^( STRUCT_DECLARATION $s ABSENT ) | structDeclaratorList -> ^( STRUCT_DECLARATION $s structDeclaratorList ) )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==SEMI) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==COLON||LA37_0==IDENTIFIER||LA37_0==LPAREN||LA37_0==STAR) ) {
                        alt37=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;

                    }
                    switch (alt37) {
                        case 1 :
                            // CivlCParser.g:608:9: 
                            {
                            // AST REWRITE
                            // elements: s
                            // token labels: 
                            // rule labels: retval, s
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 608:9: -> ^( STRUCT_DECLARATION $s ABSENT )
                            {
                                // CivlCParser.g:608:12: ^( STRUCT_DECLARATION $s ABSENT )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(STRUCT_DECLARATION, "STRUCT_DECLARATION")
                                , root_1);

                                adaptor.addChild(root_1, stream_s.nextTree());

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:609:9: structDeclaratorList
                            {
                            pushFollow(FOLLOW_structDeclaratorList_in_structDeclaration3331);
                            structDeclaratorList160=structDeclaratorList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_structDeclaratorList.add(structDeclaratorList160.getTree());

                            // AST REWRITE
                            // elements: structDeclaratorList, s
                            // token labels: 
                            // rule labels: retval, s
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 610:9: -> ^( STRUCT_DECLARATION $s structDeclaratorList )
                            {
                                // CivlCParser.g:610:12: ^( STRUCT_DECLARATION $s structDeclaratorList )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(STRUCT_DECLARATION, "STRUCT_DECLARATION")
                                , root_1);

                                adaptor.addChild(root_1, stream_s.nextTree());

                                adaptor.addChild(root_1, stream_structDeclaratorList.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    SEMI161=(Token)match(input,SEMI,FOLLOW_SEMI_in_structDeclaration3366); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI161);


                    }
                    break;
                case 2 :
                    // CivlCParser.g:613:7: staticAssertDeclaration
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_staticAssertDeclaration_in_structDeclaration3374);
                    staticAssertDeclaration162=staticAssertDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, staticAssertDeclaration162.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            DeclarationScope_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "structDeclaration"


    public static class specifierQualifierList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "specifierQualifierList"
    // CivlCParser.g:620:1: specifierQualifierList : ( typeSpecifierOrQualifier )+ -> ^( SPECIFIER_QUALIFIER_LIST ( typeSpecifierOrQualifier )+ ) ;
    public final CivlCParser.specifierQualifierList_return specifierQualifierList() throws RecognitionException {
        CivlCParser.specifierQualifierList_return retval = new CivlCParser.specifierQualifierList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.typeSpecifierOrQualifier_return typeSpecifierOrQualifier163 =null;


        RewriteRuleSubtreeStream stream_typeSpecifierOrQualifier=new RewriteRuleSubtreeStream(adaptor,"rule typeSpecifierOrQualifier");
        try {
            // CivlCParser.g:621:5: ( ( typeSpecifierOrQualifier )+ -> ^( SPECIFIER_QUALIFIER_LIST ( typeSpecifierOrQualifier )+ ) )
            // CivlCParser.g:621:7: ( typeSpecifierOrQualifier )+
            {
            // CivlCParser.g:621:7: ( typeSpecifierOrQualifier )+
            int cnt39=0;
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==IDENTIFIER) ) {
                    int LA39_2 = input.LA(2);

                    if ( ((isTypeName(input.LT(1).getText()))) ) {
                        alt39=1;
                    }


                }
                else if ( (LA39_0==ATOMIC||LA39_0==BOOL||LA39_0==CHAR||(LA39_0 >= COMPLEX && LA39_0 <= CONST)||LA39_0==DOUBLE||LA39_0==ENUM||LA39_0==FLOAT||(LA39_0 >= INPUT && LA39_0 <= INT)||LA39_0==LONG||LA39_0==OUTPUT||LA39_0==PROC||LA39_0==RESTRICT||(LA39_0 >= SHORT && LA39_0 <= SIGNED)||LA39_0==STRUCT||(LA39_0 >= UNION && LA39_0 <= UNSIGNED)||(LA39_0 >= VOID && LA39_0 <= VOLATILE)) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // CivlCParser.g:621:7: typeSpecifierOrQualifier
            	    {
            	    pushFollow(FOLLOW_typeSpecifierOrQualifier_in_specifierQualifierList3393);
            	    typeSpecifierOrQualifier163=typeSpecifierOrQualifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeSpecifierOrQualifier.add(typeSpecifierOrQualifier163.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt39 >= 1 ) break loop39;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(39, input);
                        throw eee;
                }
                cnt39++;
            } while (true);


            // AST REWRITE
            // elements: typeSpecifierOrQualifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 622:7: -> ^( SPECIFIER_QUALIFIER_LIST ( typeSpecifierOrQualifier )+ )
            {
                // CivlCParser.g:622:10: ^( SPECIFIER_QUALIFIER_LIST ( typeSpecifierOrQualifier )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(SPECIFIER_QUALIFIER_LIST, "SPECIFIER_QUALIFIER_LIST")
                , root_1);

                if ( !(stream_typeSpecifierOrQualifier.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_typeSpecifierOrQualifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeSpecifierOrQualifier.nextTree());

                }
                stream_typeSpecifierOrQualifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "specifierQualifierList"


    public static class structDeclaratorList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structDeclaratorList"
    // CivlCParser.g:629:1: structDeclaratorList :s+= structDeclarator ( COMMA s+= structDeclarator )* -> ^( STRUCT_DECLARATOR_LIST ( $s)+ ) ;
    public final CivlCParser.structDeclaratorList_return structDeclaratorList() throws RecognitionException {
        CivlCParser.structDeclaratorList_return retval = new CivlCParser.structDeclaratorList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA164=null;
        List list_s=null;
        RuleReturnScope s = null;
        Object COMMA164_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_structDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule structDeclarator");
        try {
            // CivlCParser.g:630:5: (s+= structDeclarator ( COMMA s+= structDeclarator )* -> ^( STRUCT_DECLARATOR_LIST ( $s)+ ) )
            // CivlCParser.g:630:7: s+= structDeclarator ( COMMA s+= structDeclarator )*
            {
            pushFollow(FOLLOW_structDeclarator_in_structDeclaratorList3430);
            s=structDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_structDeclarator.add(s.getTree());
            if (list_s==null) list_s=new ArrayList();
            list_s.add(s.getTree());


            // CivlCParser.g:630:27: ( COMMA s+= structDeclarator )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==COMMA) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // CivlCParser.g:630:28: COMMA s+= structDeclarator
            	    {
            	    COMMA164=(Token)match(input,COMMA,FOLLOW_COMMA_in_structDeclaratorList3433); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA164);


            	    pushFollow(FOLLOW_structDeclarator_in_structDeclaratorList3437);
            	    s=structDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_structDeclarator.add(s.getTree());
            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTree());


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            // AST REWRITE
            // elements: s
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: s
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"token s",list_s);
            root_0 = (Object)adaptor.nil();
            // 631:7: -> ^( STRUCT_DECLARATOR_LIST ( $s)+ )
            {
                // CivlCParser.g:631:10: ^( STRUCT_DECLARATOR_LIST ( $s)+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(STRUCT_DECLARATOR_LIST, "STRUCT_DECLARATOR_LIST")
                , root_1);

                if ( !(stream_s.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_s.hasNext() ) {
                    adaptor.addChild(root_1, stream_s.nextTree());

                }
                stream_s.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structDeclaratorList"


    public static class structDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structDeclarator"
    // CivlCParser.g:639:1: structDeclarator : ( declarator ( -> ^( STRUCT_DECLARATOR declarator ABSENT ) | COLON constantExpression -> ^( STRUCT_DECLARATOR declarator constantExpression ) ) | COLON constantExpression -> ^( STRUCT_DECLARATOR ABSENT constantExpression ) );
    public final CivlCParser.structDeclarator_return structDeclarator() throws RecognitionException {
        CivlCParser.structDeclarator_return retval = new CivlCParser.structDeclarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COLON166=null;
        Token COLON168=null;
        CivlCParser.declarator_return declarator165 =null;

        CivlCParser.constantExpression_return constantExpression167 =null;

        CivlCParser.constantExpression_return constantExpression169 =null;


        Object COLON166_tree=null;
        Object COLON168_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_declarator=new RewriteRuleSubtreeStream(adaptor,"rule declarator");
        RewriteRuleSubtreeStream stream_constantExpression=new RewriteRuleSubtreeStream(adaptor,"rule constantExpression");
        try {
            // CivlCParser.g:640:5: ( declarator ( -> ^( STRUCT_DECLARATOR declarator ABSENT ) | COLON constantExpression -> ^( STRUCT_DECLARATOR declarator constantExpression ) ) | COLON constantExpression -> ^( STRUCT_DECLARATOR ABSENT constantExpression ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==IDENTIFIER||LA42_0==LPAREN||LA42_0==STAR) ) {
                alt42=1;
            }
            else if ( (LA42_0==COLON) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // CivlCParser.g:640:7: declarator ( -> ^( STRUCT_DECLARATOR declarator ABSENT ) | COLON constantExpression -> ^( STRUCT_DECLARATOR declarator constantExpression ) )
                    {
                    pushFollow(FOLLOW_declarator_in_structDeclarator3474);
                    declarator165=declarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declarator.add(declarator165.getTree());

                    // CivlCParser.g:641:7: ( -> ^( STRUCT_DECLARATOR declarator ABSENT ) | COLON constantExpression -> ^( STRUCT_DECLARATOR declarator constantExpression ) )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==COMMA||LA41_0==SEMI) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==COLON) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;

                    }
                    switch (alt41) {
                        case 1 :
                            // CivlCParser.g:641:10: 
                            {
                            // AST REWRITE
                            // elements: declarator
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 641:10: -> ^( STRUCT_DECLARATOR declarator ABSENT )
                            {
                                // CivlCParser.g:641:13: ^( STRUCT_DECLARATOR declarator ABSENT )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(STRUCT_DECLARATOR, "STRUCT_DECLARATOR")
                                , root_1);

                                adaptor.addChild(root_1, stream_declarator.nextTree());

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:642:9: COLON constantExpression
                            {
                            COLON166=(Token)match(input,COLON,FOLLOW_COLON_in_structDeclarator3503); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COLON.add(COLON166);


                            pushFollow(FOLLOW_constantExpression_in_structDeclarator3505);
                            constantExpression167=constantExpression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression167.getTree());

                            // AST REWRITE
                            // elements: declarator, constantExpression
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 643:10: -> ^( STRUCT_DECLARATOR declarator constantExpression )
                            {
                                // CivlCParser.g:643:13: ^( STRUCT_DECLARATOR declarator constantExpression )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(STRUCT_DECLARATOR, "STRUCT_DECLARATOR")
                                , root_1);

                                adaptor.addChild(root_1, stream_declarator.nextTree());

                                adaptor.addChild(root_1, stream_constantExpression.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // CivlCParser.g:645:7: COLON constantExpression
                    {
                    COLON168=(Token)match(input,COLON,FOLLOW_COLON_in_structDeclarator3540); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON168);


                    pushFollow(FOLLOW_constantExpression_in_structDeclarator3542);
                    constantExpression169=constantExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression169.getTree());

                    // AST REWRITE
                    // elements: constantExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 646:7: -> ^( STRUCT_DECLARATOR ABSENT constantExpression )
                    {
                        // CivlCParser.g:646:10: ^( STRUCT_DECLARATOR ABSENT constantExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(STRUCT_DECLARATOR, "STRUCT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_constantExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structDeclarator"


    public static class enumSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumSpecifier"
    // CivlCParser.g:654:1: enumSpecifier : ENUM ( IDENTIFIER -> ^( ENUM IDENTIFIER ABSENT ) | IDENTIFIER LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM IDENTIFIER enumeratorList ) | LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM ABSENT enumeratorList ) ) ;
    public final CivlCParser.enumSpecifier_return enumSpecifier() throws RecognitionException {
        CivlCParser.enumSpecifier_return retval = new CivlCParser.enumSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ENUM170=null;
        Token IDENTIFIER171=null;
        Token IDENTIFIER172=null;
        Token LCURLY173=null;
        Token COMMA175=null;
        Token RCURLY176=null;
        Token LCURLY177=null;
        Token COMMA179=null;
        Token RCURLY180=null;
        CivlCParser.enumeratorList_return enumeratorList174 =null;

        CivlCParser.enumeratorList_return enumeratorList178 =null;


        Object ENUM170_tree=null;
        Object IDENTIFIER171_tree=null;
        Object IDENTIFIER172_tree=null;
        Object LCURLY173_tree=null;
        Object COMMA175_tree=null;
        Object RCURLY176_tree=null;
        Object LCURLY177_tree=null;
        Object COMMA179_tree=null;
        Object RCURLY180_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_ENUM=new RewriteRuleTokenStream(adaptor,"token ENUM");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_enumeratorList=new RewriteRuleSubtreeStream(adaptor,"rule enumeratorList");
        try {
            // CivlCParser.g:655:5: ( ENUM ( IDENTIFIER -> ^( ENUM IDENTIFIER ABSENT ) | IDENTIFIER LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM IDENTIFIER enumeratorList ) | LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM ABSENT enumeratorList ) ) )
            // CivlCParser.g:655:7: ENUM ( IDENTIFIER -> ^( ENUM IDENTIFIER ABSENT ) | IDENTIFIER LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM IDENTIFIER enumeratorList ) | LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM ABSENT enumeratorList ) )
            {
            ENUM170=(Token)match(input,ENUM,FOLLOW_ENUM_in_enumSpecifier3577); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ENUM.add(ENUM170);


            // CivlCParser.g:656:9: ( IDENTIFIER -> ^( ENUM IDENTIFIER ABSENT ) | IDENTIFIER LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM IDENTIFIER enumeratorList ) | LCURLY enumeratorList ( COMMA )? RCURLY -> ^( ENUM ABSENT enumeratorList ) )
            int alt45=3;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==IDENTIFIER) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==LCURLY) ) {
                    alt45=2;
                }
                else if ( (LA45_1==EOF||LA45_1==ALIGNAS||(LA45_1 >= ATOMIC && LA45_1 <= AUTO)||LA45_1==BOOL||LA45_1==CHAR||(LA45_1 >= COLON && LA45_1 <= COMMA)||(LA45_1 >= COMPLEX && LA45_1 <= CONST)||LA45_1==DOUBLE||LA45_1==ENUM||LA45_1==EXTERN||LA45_1==FLOAT||LA45_1==IDENTIFIER||(LA45_1 >= INLINE && LA45_1 <= INT)||(LA45_1 >= LONG && LA45_1 <= LSQUARE)||LA45_1==NORETURN||LA45_1==OUTPUT||LA45_1==PROC||(LA45_1 >= REGISTER && LA45_1 <= RESTRICT)||LA45_1==RPAREN||LA45_1==SEMI||(LA45_1 >= SHORT && LA45_1 <= SIGNED)||LA45_1==STAR||LA45_1==STATIC||LA45_1==STRUCT||LA45_1==THREADLOCAL||(LA45_1 >= TYPEDEF && LA45_1 <= UNSIGNED)||(LA45_1 >= VOID && LA45_1 <= VOLATILE)) ) {
                    alt45=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA45_0==LCURLY) ) {
                alt45=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // CivlCParser.g:656:11: IDENTIFIER
                    {
                    IDENTIFIER171=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumSpecifier3590); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER171);


                    // AST REWRITE
                    // elements: IDENTIFIER, ENUM
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 657:11: -> ^( ENUM IDENTIFIER ABSENT )
                    {
                        // CivlCParser.g:657:14: ^( ENUM IDENTIFIER ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_ENUM.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:658:11: IDENTIFIER LCURLY enumeratorList ( COMMA )? RCURLY
                    {
                    IDENTIFIER172=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumSpecifier3623); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER172);


                    LCURLY173=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_enumSpecifier3625); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY173);


                    pushFollow(FOLLOW_enumeratorList_in_enumSpecifier3627);
                    enumeratorList174=enumeratorList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumeratorList.add(enumeratorList174.getTree());

                    // CivlCParser.g:658:44: ( COMMA )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==COMMA) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // CivlCParser.g:658:44: COMMA
                            {
                            COMMA175=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumSpecifier3629); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA175);


                            }
                            break;

                    }


                    RCURLY176=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_enumSpecifier3632); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY176);


                    // AST REWRITE
                    // elements: enumeratorList, IDENTIFIER, ENUM
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 659:11: -> ^( ENUM IDENTIFIER enumeratorList )
                    {
                        // CivlCParser.g:659:14: ^( ENUM IDENTIFIER enumeratorList )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_ENUM.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, stream_enumeratorList.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:660:11: LCURLY enumeratorList ( COMMA )? RCURLY
                    {
                    LCURLY177=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_enumSpecifier3664); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY177);


                    pushFollow(FOLLOW_enumeratorList_in_enumSpecifier3666);
                    enumeratorList178=enumeratorList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumeratorList.add(enumeratorList178.getTree());

                    // CivlCParser.g:660:33: ( COMMA )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==COMMA) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // CivlCParser.g:660:33: COMMA
                            {
                            COMMA179=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumSpecifier3668); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA179);


                            }
                            break;

                    }


                    RCURLY180=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_enumSpecifier3671); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY180);


                    // AST REWRITE
                    // elements: enumeratorList, ENUM
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 661:11: -> ^( ENUM ABSENT enumeratorList )
                    {
                        // CivlCParser.g:661:14: ^( ENUM ABSENT enumeratorList )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_ENUM.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_enumeratorList.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "enumSpecifier"


    public static class enumeratorList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumeratorList"
    // CivlCParser.g:669:1: enumeratorList : enumerator ( COMMA enumerator )* -> ^( ENUMERATOR_LIST ( enumerator )+ ) ;
    public final CivlCParser.enumeratorList_return enumeratorList() throws RecognitionException {
        CivlCParser.enumeratorList_return retval = new CivlCParser.enumeratorList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA182=null;
        CivlCParser.enumerator_return enumerator181 =null;

        CivlCParser.enumerator_return enumerator183 =null;


        Object COMMA182_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_enumerator=new RewriteRuleSubtreeStream(adaptor,"rule enumerator");
        try {
            // CivlCParser.g:670:5: ( enumerator ( COMMA enumerator )* -> ^( ENUMERATOR_LIST ( enumerator )+ ) )
            // CivlCParser.g:670:7: enumerator ( COMMA enumerator )*
            {
            pushFollow(FOLLOW_enumerator_in_enumeratorList3720);
            enumerator181=enumerator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_enumerator.add(enumerator181.getTree());

            // CivlCParser.g:670:18: ( COMMA enumerator )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==COMMA) ) {
                    int LA46_1 = input.LA(2);

                    if ( (LA46_1==IDENTIFIER) ) {
                        alt46=1;
                    }


                }


                switch (alt46) {
            	case 1 :
            	    // CivlCParser.g:670:19: COMMA enumerator
            	    {
            	    COMMA182=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumeratorList3723); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA182);


            	    pushFollow(FOLLOW_enumerator_in_enumeratorList3725);
            	    enumerator183=enumerator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_enumerator.add(enumerator183.getTree());

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            // AST REWRITE
            // elements: enumerator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 671:7: -> ^( ENUMERATOR_LIST ( enumerator )+ )
            {
                // CivlCParser.g:671:10: ^( ENUMERATOR_LIST ( enumerator )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ENUMERATOR_LIST, "ENUMERATOR_LIST")
                , root_1);

                if ( !(stream_enumerator.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_enumerator.hasNext() ) {
                    adaptor.addChild(root_1, stream_enumerator.nextTree());

                }
                stream_enumerator.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "enumeratorList"


    public static class enumerator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumerator"
    // CivlCParser.g:679:1: enumerator : IDENTIFIER ( -> ^( ENUMERATOR IDENTIFIER ABSENT ) | ( ASSIGN constantExpression ) -> ^( ENUMERATOR IDENTIFIER constantExpression ) ) ;
    public final CivlCParser.enumerator_return enumerator() throws RecognitionException {
        CivlCParser.enumerator_return retval = new CivlCParser.enumerator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER184=null;
        Token ASSIGN185=null;
        CivlCParser.constantExpression_return constantExpression186 =null;


        Object IDENTIFIER184_tree=null;
        Object ASSIGN185_tree=null;
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_constantExpression=new RewriteRuleSubtreeStream(adaptor,"rule constantExpression");
        try {
            // CivlCParser.g:680:2: ( IDENTIFIER ( -> ^( ENUMERATOR IDENTIFIER ABSENT ) | ( ASSIGN constantExpression ) -> ^( ENUMERATOR IDENTIFIER constantExpression ) ) )
            // CivlCParser.g:680:4: IDENTIFIER ( -> ^( ENUMERATOR IDENTIFIER ABSENT ) | ( ASSIGN constantExpression ) -> ^( ENUMERATOR IDENTIFIER constantExpression ) )
            {
            IDENTIFIER184=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumerator3758); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER184);


            if ( state.backtracking==0 ) {
                		((Symbols_scope)Symbols_stack.peek()).enumerationConstants.add((IDENTIFIER184!=null?IDENTIFIER184.getText():null));
            		// System.err.println("define enum constant "+(IDENTIFIER184!=null?IDENTIFIER184.getText():null));	
                	  }

            // CivlCParser.g:685:8: ( -> ^( ENUMERATOR IDENTIFIER ABSENT ) | ( ASSIGN constantExpression ) -> ^( ENUMERATOR IDENTIFIER constantExpression ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==COMMA||LA47_0==RCURLY) ) {
                alt47=1;
            }
            else if ( (LA47_0==ASSIGN) ) {
                alt47=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;

            }
            switch (alt47) {
                case 1 :
                    // CivlCParser.g:685:11: 
                    {
                    // AST REWRITE
                    // elements: IDENTIFIER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 685:11: -> ^( ENUMERATOR IDENTIFIER ABSENT )
                    {
                        // CivlCParser.g:685:14: ^( ENUMERATOR IDENTIFIER ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ENUMERATOR, "ENUMERATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:686:10: ( ASSIGN constantExpression )
                    {
                    // CivlCParser.g:686:10: ( ASSIGN constantExpression )
                    // CivlCParser.g:686:11: ASSIGN constantExpression
                    {
                    ASSIGN185=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_enumerator3799); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN185);


                    pushFollow(FOLLOW_constantExpression_in_enumerator3801);
                    constantExpression186=constantExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression186.getTree());

                    }


                    // AST REWRITE
                    // elements: constantExpression, IDENTIFIER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 687:11: -> ^( ENUMERATOR IDENTIFIER constantExpression )
                    {
                        // CivlCParser.g:687:14: ^( ENUMERATOR IDENTIFIER constantExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ENUMERATOR, "ENUMERATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, stream_constantExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "enumerator"


    public static class atomicTypeSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicTypeSpecifier"
    // CivlCParser.g:692:1: atomicTypeSpecifier : ATOMIC LPAREN typeName RPAREN -> ^( ATOMIC typeName ) ;
    public final CivlCParser.atomicTypeSpecifier_return atomicTypeSpecifier() throws RecognitionException {
        CivlCParser.atomicTypeSpecifier_return retval = new CivlCParser.atomicTypeSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ATOMIC187=null;
        Token LPAREN188=null;
        Token RPAREN190=null;
        CivlCParser.typeName_return typeName189 =null;


        Object ATOMIC187_tree=null;
        Object LPAREN188_tree=null;
        Object RPAREN190_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ATOMIC=new RewriteRuleTokenStream(adaptor,"token ATOMIC");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typeName=new RewriteRuleSubtreeStream(adaptor,"rule typeName");
        try {
            // CivlCParser.g:693:5: ( ATOMIC LPAREN typeName RPAREN -> ^( ATOMIC typeName ) )
            // CivlCParser.g:693:7: ATOMIC LPAREN typeName RPAREN
            {
            ATOMIC187=(Token)match(input,ATOMIC,FOLLOW_ATOMIC_in_atomicTypeSpecifier3847); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ATOMIC.add(ATOMIC187);


            LPAREN188=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_atomicTypeSpecifier3849); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN188);


            pushFollow(FOLLOW_typeName_in_atomicTypeSpecifier3851);
            typeName189=typeName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeName.add(typeName189.getTree());

            RPAREN190=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_atomicTypeSpecifier3853); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN190);


            // AST REWRITE
            // elements: typeName, ATOMIC
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 694:7: -> ^( ATOMIC typeName )
            {
                // CivlCParser.g:694:10: ^( ATOMIC typeName )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_ATOMIC.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_typeName.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicTypeSpecifier"


    public static class typeQualifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeQualifier"
    // CivlCParser.g:698:1: typeQualifier : ( CONST | RESTRICT | VOLATILE | ATOMIC | INPUT | OUTPUT );
    public final CivlCParser.typeQualifier_return typeQualifier() throws RecognitionException {
        CivlCParser.typeQualifier_return retval = new CivlCParser.typeQualifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set191=null;

        Object set191_tree=null;

        try {
            // CivlCParser.g:699:5: ( CONST | RESTRICT | VOLATILE | ATOMIC | INPUT | OUTPUT )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set191=(Token)input.LT(1);

            if ( input.LA(1)==ATOMIC||input.LA(1)==CONST||input.LA(1)==INPUT||input.LA(1)==OUTPUT||input.LA(1)==RESTRICT||input.LA(1)==VOLATILE ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set191)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typeQualifier"


    public static class functionSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionSpecifier"
    // CivlCParser.g:704:1: functionSpecifier : ( INLINE | NORETURN );
    public final CivlCParser.functionSpecifier_return functionSpecifier() throws RecognitionException {
        CivlCParser.functionSpecifier_return retval = new CivlCParser.functionSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set192=null;

        Object set192_tree=null;

        try {
            // CivlCParser.g:705:5: ( INLINE | NORETURN )
            // CivlCParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set192=(Token)input.LT(1);

            if ( input.LA(1)==INLINE||input.LA(1)==NORETURN ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set192)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionSpecifier"


    public static class alignmentSpecifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "alignmentSpecifier"
    // CivlCParser.g:714:1: alignmentSpecifier : ALIGNAS LPAREN ( typeName RPAREN -> ^( ALIGNAS TYPE typeName ) | constantExpression RPAREN -> ^( ALIGNAS EXPR constantExpression ) ) ;
    public final CivlCParser.alignmentSpecifier_return alignmentSpecifier() throws RecognitionException {
        CivlCParser.alignmentSpecifier_return retval = new CivlCParser.alignmentSpecifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ALIGNAS193=null;
        Token LPAREN194=null;
        Token RPAREN196=null;
        Token RPAREN198=null;
        CivlCParser.typeName_return typeName195 =null;

        CivlCParser.constantExpression_return constantExpression197 =null;


        Object ALIGNAS193_tree=null;
        Object LPAREN194_tree=null;
        Object RPAREN196_tree=null;
        Object RPAREN198_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ALIGNAS=new RewriteRuleTokenStream(adaptor,"token ALIGNAS");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typeName=new RewriteRuleSubtreeStream(adaptor,"rule typeName");
        RewriteRuleSubtreeStream stream_constantExpression=new RewriteRuleSubtreeStream(adaptor,"rule constantExpression");
        try {
            // CivlCParser.g:715:5: ( ALIGNAS LPAREN ( typeName RPAREN -> ^( ALIGNAS TYPE typeName ) | constantExpression RPAREN -> ^( ALIGNAS EXPR constantExpression ) ) )
            // CivlCParser.g:715:7: ALIGNAS LPAREN ( typeName RPAREN -> ^( ALIGNAS TYPE typeName ) | constantExpression RPAREN -> ^( ALIGNAS EXPR constantExpression ) )
            {
            ALIGNAS193=(Token)match(input,ALIGNAS,FOLLOW_ALIGNAS_in_alignmentSpecifier3952); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ALIGNAS.add(ALIGNAS193);


            LPAREN194=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_alignmentSpecifier3954); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN194);


            // CivlCParser.g:716:9: ( typeName RPAREN -> ^( ALIGNAS TYPE typeName ) | constantExpression RPAREN -> ^( ALIGNAS EXPR constantExpression ) )
            int alt48=2;
            switch ( input.LA(1) ) {
            case ATOMIC:
            case BOOL:
            case CHAR:
            case COMPLEX:
            case CONST:
            case DOUBLE:
            case ENUM:
            case FLOAT:
            case INPUT:
            case INT:
            case LONG:
            case OUTPUT:
            case PROC:
            case RESTRICT:
            case SHORT:
            case SIGNED:
            case STRUCT:
            case UNION:
            case UNSIGNED:
            case VOID:
            case VOLATILE:
                {
                alt48=1;
                }
                break;
            case IDENTIFIER:
                {
                int LA48_16 = input.LA(2);

                if ( ((isTypeName(input.LT(1).getText()))) ) {
                    alt48=1;
                }
                else if ( (true) ) {
                    alt48=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 48, 16, input);

                    throw nvae;

                }
                }
                break;
            case ALIGNOF:
            case AMPERSAND:
            case CHARACTER_CONSTANT:
            case FLOATING_CONSTANT:
            case GENERIC:
            case INTEGER_CONSTANT:
            case LPAREN:
            case MINUSMINUS:
            case NOT:
            case PLUS:
            case PLUSPLUS:
            case SIZEOF:
            case SPAWN:
            case STAR:
            case STRING_LITERAL:
            case SUB:
            case TILDE:
                {
                alt48=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;

            }

            switch (alt48) {
                case 1 :
                    // CivlCParser.g:716:11: typeName RPAREN
                    {
                    pushFollow(FOLLOW_typeName_in_alignmentSpecifier3967);
                    typeName195=typeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeName.add(typeName195.getTree());

                    RPAREN196=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_alignmentSpecifier3969); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN196);


                    // AST REWRITE
                    // elements: ALIGNAS, typeName
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 717:11: -> ^( ALIGNAS TYPE typeName )
                    {
                        // CivlCParser.g:717:14: ^( ALIGNAS TYPE typeName )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_ALIGNAS.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(TYPE, "TYPE")
                        );

                        adaptor.addChild(root_1, stream_typeName.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:718:11: constantExpression RPAREN
                    {
                    pushFollow(FOLLOW_constantExpression_in_alignmentSpecifier4001);
                    constantExpression197=constantExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression197.getTree());

                    RPAREN198=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_alignmentSpecifier4003); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN198);


                    // AST REWRITE
                    // elements: ALIGNAS, constantExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 719:11: -> ^( ALIGNAS EXPR constantExpression )
                    {
                        // CivlCParser.g:719:14: ^( ALIGNAS EXPR constantExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_ALIGNAS.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(EXPR, "EXPR")
                        );

                        adaptor.addChild(root_1, stream_constantExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "alignmentSpecifier"


    public static class declarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarator"
    // CivlCParser.g:728:1: declarator : (d= directDeclarator -> ^( DECLARATOR ABSENT $d) | pointer d= directDeclarator -> ^( DECLARATOR pointer $d) );
    public final CivlCParser.declarator_return declarator() throws RecognitionException {
        CivlCParser.declarator_return retval = new CivlCParser.declarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.directDeclarator_return d =null;

        CivlCParser.pointer_return pointer199 =null;


        RewriteRuleSubtreeStream stream_directDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule directDeclarator");
        RewriteRuleSubtreeStream stream_pointer=new RewriteRuleSubtreeStream(adaptor,"rule pointer");
        try {
            // CivlCParser.g:729:2: (d= directDeclarator -> ^( DECLARATOR ABSENT $d) | pointer d= directDeclarator -> ^( DECLARATOR pointer $d) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==IDENTIFIER||LA49_0==LPAREN) ) {
                alt49=1;
            }
            else if ( (LA49_0==STAR) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }
            switch (alt49) {
                case 1 :
                    // CivlCParser.g:729:4: d= directDeclarator
                    {
                    pushFollow(FOLLOW_directDeclarator_in_declarator4051);
                    d=directDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_directDeclarator.add(d.getTree());

                    // AST REWRITE
                    // elements: d
                    // token labels: 
                    // rule labels: retval, d
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 730:4: -> ^( DECLARATOR ABSENT $d)
                    {
                        // CivlCParser.g:730:7: ^( DECLARATOR ABSENT $d)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DECLARATOR, "DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_d.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:731:4: pointer d= directDeclarator
                    {
                    pushFollow(FOLLOW_pointer_in_declarator4070);
                    pointer199=pointer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pointer.add(pointer199.getTree());

                    pushFollow(FOLLOW_directDeclarator_in_declarator4074);
                    d=directDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_directDeclarator.add(d.getTree());

                    // AST REWRITE
                    // elements: d, pointer
                    // token labels: 
                    // rule labels: retval, d
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 732:4: -> ^( DECLARATOR pointer $d)
                    {
                        // CivlCParser.g:732:7: ^( DECLARATOR pointer $d)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DECLARATOR, "DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_pointer.nextTree());

                        adaptor.addChild(root_1, stream_d.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "declarator"


    public static class directDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directDeclarator"
    // CivlCParser.g:740:1: directDeclarator : p= directDeclaratorPrefix ( -> ^( DIRECT_DECLARATOR $p) | (s+= directDeclaratorSuffix )+ -> ^( DIRECT_DECLARATOR $p ( $s)+ ) ) ;
    public final CivlCParser.directDeclarator_return directDeclarator() throws RecognitionException {
        CivlCParser.directDeclarator_return retval = new CivlCParser.directDeclarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        List list_s=null;
        CivlCParser.directDeclaratorPrefix_return p =null;

        RuleReturnScope s = null;
        RewriteRuleSubtreeStream stream_directDeclaratorSuffix=new RewriteRuleSubtreeStream(adaptor,"rule directDeclaratorSuffix");
        RewriteRuleSubtreeStream stream_directDeclaratorPrefix=new RewriteRuleSubtreeStream(adaptor,"rule directDeclaratorPrefix");
        try {
            // CivlCParser.g:741:2: (p= directDeclaratorPrefix ( -> ^( DIRECT_DECLARATOR $p) | (s+= directDeclaratorSuffix )+ -> ^( DIRECT_DECLARATOR $p ( $s)+ ) ) )
            // CivlCParser.g:741:4: p= directDeclaratorPrefix ( -> ^( DIRECT_DECLARATOR $p) | (s+= directDeclaratorSuffix )+ -> ^( DIRECT_DECLARATOR $p ( $s)+ ) )
            {
            pushFollow(FOLLOW_directDeclaratorPrefix_in_directDeclarator4103);
            p=directDeclaratorPrefix();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_directDeclaratorPrefix.add(p.getTree());

            // CivlCParser.g:742:4: ( -> ^( DIRECT_DECLARATOR $p) | (s+= directDeclaratorSuffix )+ -> ^( DIRECT_DECLARATOR $p ( $s)+ ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==EOF||LA51_0==ALIGNAS||LA51_0==ASSIGN||(LA51_0 >= ATOMIC && LA51_0 <= AUTO)||LA51_0==BOOL||LA51_0==CHAR||(LA51_0 >= COLON && LA51_0 <= COMMA)||(LA51_0 >= COMPLEX && LA51_0 <= CONST)||LA51_0==DOUBLE||LA51_0==ENUM||LA51_0==EXTERN||LA51_0==FLOAT||LA51_0==IDENTIFIER||(LA51_0 >= INLINE && LA51_0 <= INT)||(LA51_0 >= LCURLY && LA51_0 <= LONG)||LA51_0==NORETURN||LA51_0==OUTPUT||LA51_0==PROC||(LA51_0 >= REGISTER && LA51_0 <= RESTRICT)||LA51_0==RPAREN||LA51_0==SEMI||(LA51_0 >= SHORT && LA51_0 <= SIGNED)||(LA51_0 >= STATIC && LA51_0 <= STATICASSERT)||LA51_0==STRUCT||LA51_0==THREADLOCAL||(LA51_0 >= TYPEDEF && LA51_0 <= UNSIGNED)||(LA51_0 >= VOID && LA51_0 <= VOLATILE)) ) {
                alt51=1;
            }
            else if ( ((LA51_0 >= LPAREN && LA51_0 <= LSQUARE)) ) {
                alt51=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }
            switch (alt51) {
                case 1 :
                    // CivlCParser.g:742:6: 
                    {
                    // AST REWRITE
                    // elements: p
                    // token labels: 
                    // rule labels: retval, p
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 742:6: -> ^( DIRECT_DECLARATOR $p)
                    {
                        // CivlCParser.g:742:9: ^( DIRECT_DECLARATOR $p)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DIRECT_DECLARATOR, "DIRECT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_p.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:743:6: (s+= directDeclaratorSuffix )+
                    {
                    // CivlCParser.g:743:7: (s+= directDeclaratorSuffix )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( ((LA50_0 >= LPAREN && LA50_0 <= LSQUARE)) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // CivlCParser.g:743:7: s+= directDeclaratorSuffix
                    	    {
                    	    pushFollow(FOLLOW_directDeclaratorSuffix_in_directDeclarator4126);
                    	    s=directDeclaratorSuffix();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_directDeclaratorSuffix.add(s.getTree());
                    	    if (list_s==null) list_s=new ArrayList();
                    	    list_s.add(s.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt50 >= 1 ) break loop50;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(50, input);
                                throw eee;
                        }
                        cnt50++;
                    } while (true);


                    // AST REWRITE
                    // elements: s, p
                    // token labels: 
                    // rule labels: retval, p
                    // token list labels: 
                    // rule list labels: s
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"token s",list_s);
                    root_0 = (Object)adaptor.nil();
                    // 743:33: -> ^( DIRECT_DECLARATOR $p ( $s)+ )
                    {
                        // CivlCParser.g:743:35: ^( DIRECT_DECLARATOR $p ( $s)+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DIRECT_DECLARATOR, "DIRECT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_p.nextTree());

                        if ( !(stream_s.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_s.hasNext() ) {
                            adaptor.addChild(root_1, stream_s.nextTree());

                        }
                        stream_s.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directDeclarator"


    public static class directDeclaratorPrefix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directDeclaratorPrefix"
    // CivlCParser.g:750:1: directDeclaratorPrefix : ( IDENTIFIER | LPAREN ! declarator RPAREN !);
    public final CivlCParser.directDeclaratorPrefix_return directDeclaratorPrefix() throws RecognitionException {
        CivlCParser.directDeclaratorPrefix_return retval = new CivlCParser.directDeclaratorPrefix_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER200=null;
        Token LPAREN201=null;
        Token RPAREN203=null;
        CivlCParser.declarator_return declarator202 =null;


        Object IDENTIFIER200_tree=null;
        Object LPAREN201_tree=null;
        Object RPAREN203_tree=null;

        try {
            // CivlCParser.g:751:2: ( IDENTIFIER | LPAREN ! declarator RPAREN !)
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==IDENTIFIER) ) {
                alt52=1;
            }
            else if ( (LA52_0==LPAREN) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }
            switch (alt52) {
                case 1 :
                    // CivlCParser.g:751:4: IDENTIFIER
                    {
                    root_0 = (Object)adaptor.nil();


                    IDENTIFIER200=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_directDeclaratorPrefix4157); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER200_tree = 
                    (Object)adaptor.create(IDENTIFIER200)
                    ;
                    adaptor.addChild(root_0, IDENTIFIER200_tree);
                    }

                    if ( state.backtracking==0 ) {
                    			if (((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef) {
                    				((Symbols_scope)Symbols_stack.peek()).types.add((IDENTIFIER200!=null?IDENTIFIER200.getText():null));
                    				//System.err.println("define type "+(IDENTIFIER200!=null?IDENTIFIER200.getText():null));
                    			}
                    		}

                    }
                    break;
                case 2 :
                    // CivlCParser.g:758:4: LPAREN ! declarator RPAREN !
                    {
                    root_0 = (Object)adaptor.nil();


                    LPAREN201=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_directDeclaratorPrefix4167); if (state.failed) return retval;

                    pushFollow(FOLLOW_declarator_in_directDeclaratorPrefix4170);
                    declarator202=declarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, declarator202.getTree());

                    RPAREN203=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directDeclaratorPrefix4172); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directDeclaratorPrefix"


    public static class directDeclaratorSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directDeclaratorSuffix"
    // CivlCParser.g:762:1: directDeclaratorSuffix : ( directDeclaratorArraySuffix | directDeclaratorFunctionSuffix );
    public final CivlCParser.directDeclaratorSuffix_return directDeclaratorSuffix() throws RecognitionException {
        CivlCParser.directDeclaratorSuffix_return retval = new CivlCParser.directDeclaratorSuffix_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.directDeclaratorArraySuffix_return directDeclaratorArraySuffix204 =null;

        CivlCParser.directDeclaratorFunctionSuffix_return directDeclaratorFunctionSuffix205 =null;



        try {
            // CivlCParser.g:763:2: ( directDeclaratorArraySuffix | directDeclaratorFunctionSuffix )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LSQUARE) ) {
                alt53=1;
            }
            else if ( (LA53_0==LPAREN) ) {
                alt53=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }
            switch (alt53) {
                case 1 :
                    // CivlCParser.g:763:4: directDeclaratorArraySuffix
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_directDeclaratorArraySuffix_in_directDeclaratorSuffix4185);
                    directDeclaratorArraySuffix204=directDeclaratorArraySuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, directDeclaratorArraySuffix204.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:764:4: directDeclaratorFunctionSuffix
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_directDeclaratorFunctionSuffix_in_directDeclaratorSuffix4190);
                    directDeclaratorFunctionSuffix205=directDeclaratorFunctionSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, directDeclaratorFunctionSuffix205.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directDeclaratorSuffix"


    public static class directDeclaratorArraySuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directDeclaratorArraySuffix"
    // CivlCParser.g:776:1: directDeclaratorArraySuffix : LSQUARE ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE ) | typeQualifierList_opt STAR RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE ) ) ;
    public final CivlCParser.directDeclaratorArraySuffix_return directDeclaratorArraySuffix() throws RecognitionException {
        CivlCParser.directDeclaratorArraySuffix_return retval = new CivlCParser.directDeclaratorArraySuffix_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LSQUARE206=null;
        Token RSQUARE209=null;
        Token STATIC210=null;
        Token RSQUARE213=null;
        Token STATIC215=null;
        Token RSQUARE217=null;
        Token STAR219=null;
        Token RSQUARE220=null;
        CivlCParser.typeQualifierList_opt_return typeQualifierList_opt207 =null;

        CivlCParser.assignmentExpression_opt_return assignmentExpression_opt208 =null;

        CivlCParser.typeQualifierList_opt_return typeQualifierList_opt211 =null;

        CivlCParser.assignmentExpression_return assignmentExpression212 =null;

        CivlCParser.typeQualifierList_return typeQualifierList214 =null;

        CivlCParser.assignmentExpression_return assignmentExpression216 =null;

        CivlCParser.typeQualifierList_opt_return typeQualifierList_opt218 =null;


        Object LSQUARE206_tree=null;
        Object RSQUARE209_tree=null;
        Object STATIC210_tree=null;
        Object RSQUARE213_tree=null;
        Object STATIC215_tree=null;
        Object RSQUARE217_tree=null;
        Object STAR219_tree=null;
        Object RSQUARE220_tree=null;
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
        RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
        RewriteRuleTokenStream stream_STATIC=new RewriteRuleTokenStream(adaptor,"token STATIC");
        RewriteRuleSubtreeStream stream_typeQualifierList=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifierList");
        RewriteRuleSubtreeStream stream_assignmentExpression_opt=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression_opt");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        RewriteRuleSubtreeStream stream_typeQualifierList_opt=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifierList_opt");
        try {
            // CivlCParser.g:777:2: ( LSQUARE ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE ) | typeQualifierList_opt STAR RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE ) ) )
            // CivlCParser.g:777:4: LSQUARE ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE ) | typeQualifierList_opt STAR RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE ) )
            {
            LSQUARE206=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_directDeclaratorArraySuffix4203); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE206);


            // CivlCParser.g:778:4: ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE ) | typeQualifierList_opt STAR RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE ) )
            int alt54=4;
            alt54 = dfa54.predict(input);
            switch (alt54) {
                case 1 :
                    // CivlCParser.g:778:6: typeQualifierList_opt assignmentExpression_opt RSQUARE
                    {
                    pushFollow(FOLLOW_typeQualifierList_opt_in_directDeclaratorArraySuffix4210);
                    typeQualifierList_opt207=typeQualifierList_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeQualifierList_opt.add(typeQualifierList_opt207.getTree());

                    pushFollow(FOLLOW_assignmentExpression_opt_in_directDeclaratorArraySuffix4212);
                    assignmentExpression_opt208=assignmentExpression_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression_opt.add(assignmentExpression_opt208.getTree());

                    RSQUARE209=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4214); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE209);


                    // AST REWRITE
                    // elements: LSQUARE, assignmentExpression_opt, typeQualifierList_opt, RSQUARE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 779:6: -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE )
                    {
                        // CivlCParser.g:779:9: ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LSQUARE.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_typeQualifierList_opt.nextTree());

                        adaptor.addChild(root_1, stream_assignmentExpression_opt.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RSQUARE.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:781:6: STATIC typeQualifierList_opt assignmentExpression RSQUARE
                    {
                    STATIC210=(Token)match(input,STATIC,FOLLOW_STATIC_in_directDeclaratorArraySuffix4252); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STATIC.add(STATIC210);


                    pushFollow(FOLLOW_typeQualifierList_opt_in_directDeclaratorArraySuffix4254);
                    typeQualifierList_opt211=typeQualifierList_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeQualifierList_opt.add(typeQualifierList_opt211.getTree());

                    pushFollow(FOLLOW_assignmentExpression_in_directDeclaratorArraySuffix4256);
                    assignmentExpression212=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression212.getTree());

                    RSQUARE213=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4258); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE213);


                    // AST REWRITE
                    // elements: RSQUARE, assignmentExpression, STATIC, typeQualifierList_opt, LSQUARE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 782:6: -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE )
                    {
                        // CivlCParser.g:782:9: ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LSQUARE.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_STATIC.nextNode()
                        );

                        adaptor.addChild(root_1, stream_typeQualifierList_opt.nextTree());

                        adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RSQUARE.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:784:8: typeQualifierList STATIC assignmentExpression RSQUARE
                    {
                    pushFollow(FOLLOW_typeQualifierList_in_directDeclaratorArraySuffix4298);
                    typeQualifierList214=typeQualifierList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeQualifierList.add(typeQualifierList214.getTree());

                    STATIC215=(Token)match(input,STATIC,FOLLOW_STATIC_in_directDeclaratorArraySuffix4300); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STATIC.add(STATIC215);


                    pushFollow(FOLLOW_assignmentExpression_in_directDeclaratorArraySuffix4302);
                    assignmentExpression216=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression216.getTree());

                    RSQUARE217=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4304); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE217);


                    // AST REWRITE
                    // elements: RSQUARE, assignmentExpression, STATIC, LSQUARE, typeQualifierList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 785:6: -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE )
                    {
                        // CivlCParser.g:785:9: ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LSQUARE.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_STATIC.nextNode()
                        );

                        adaptor.addChild(root_1, stream_typeQualifierList.nextTree());

                        adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RSQUARE.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // CivlCParser.g:787:8: typeQualifierList_opt STAR RSQUARE
                    {
                    pushFollow(FOLLOW_typeQualifierList_opt_in_directDeclaratorArraySuffix4344);
                    typeQualifierList_opt218=typeQualifierList_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeQualifierList_opt.add(typeQualifierList_opt218.getTree());

                    STAR219=(Token)match(input,STAR,FOLLOW_STAR_in_directDeclaratorArraySuffix4346); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STAR.add(STAR219);


                    RSQUARE220=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4348); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE220);


                    // AST REWRITE
                    // elements: LSQUARE, typeQualifierList_opt, RSQUARE, STAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 788:6: -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE )
                    {
                        // CivlCParser.g:788:9: ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LSQUARE.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_typeQualifierList_opt.nextTree());

                        adaptor.addChild(root_1, 
                        stream_STAR.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_RSQUARE.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directDeclaratorArraySuffix"


    public static class directDeclaratorFunctionSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directDeclaratorFunctionSuffix"
    // CivlCParser.g:799:1: directDeclaratorFunctionSuffix : LPAREN ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN ) | identifierList RPAREN -> ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN ) | RPAREN -> ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN ) ) ;
    public final CivlCParser.directDeclaratorFunctionSuffix_return directDeclaratorFunctionSuffix() throws RecognitionException {
        CivlCParser.directDeclaratorFunctionSuffix_return retval = new CivlCParser.directDeclaratorFunctionSuffix_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LPAREN221=null;
        Token RPAREN223=null;
        Token RPAREN225=null;
        Token RPAREN226=null;
        CivlCParser.parameterTypeList_return parameterTypeList222 =null;

        CivlCParser.identifierList_return identifierList224 =null;


        Object LPAREN221_tree=null;
        Object RPAREN223_tree=null;
        Object RPAREN225_tree=null;
        Object RPAREN226_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_identifierList=new RewriteRuleSubtreeStream(adaptor,"rule identifierList");
        RewriteRuleSubtreeStream stream_parameterTypeList=new RewriteRuleSubtreeStream(adaptor,"rule parameterTypeList");
        try {
            // CivlCParser.g:800:2: ( LPAREN ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN ) | identifierList RPAREN -> ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN ) | RPAREN -> ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN ) ) )
            // CivlCParser.g:800:4: LPAREN ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN ) | identifierList RPAREN -> ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN ) | RPAREN -> ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN ) )
            {
            LPAREN221=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_directDeclaratorFunctionSuffix4397); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN221);


            // CivlCParser.g:801:4: ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN ) | identifierList RPAREN -> ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN ) | RPAREN -> ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN ) )
            int alt55=3;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // CivlCParser.g:801:6: parameterTypeList RPAREN
                    {
                    pushFollow(FOLLOW_parameterTypeList_in_directDeclaratorFunctionSuffix4404);
                    parameterTypeList222=parameterTypeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_parameterTypeList.add(parameterTypeList222.getTree());

                    RPAREN223=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directDeclaratorFunctionSuffix4406); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN223);


                    // AST REWRITE
                    // elements: parameterTypeList, RPAREN, LPAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 802:6: -> ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN )
                    {
                        // CivlCParser.g:802:9: ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNCTION_SUFFIX, "FUNCTION_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LPAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_parameterTypeList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RPAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:803:6: identifierList RPAREN
                    {
                    pushFollow(FOLLOW_identifierList_in_directDeclaratorFunctionSuffix4432);
                    identifierList224=identifierList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_identifierList.add(identifierList224.getTree());

                    RPAREN225=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directDeclaratorFunctionSuffix4434); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN225);


                    // AST REWRITE
                    // elements: identifierList, LPAREN, RPAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 804:6: -> ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN )
                    {
                        // CivlCParser.g:804:9: ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNCTION_SUFFIX, "FUNCTION_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LPAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_identifierList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RPAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:805:6: RPAREN
                    {
                    RPAREN226=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directDeclaratorFunctionSuffix4458); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN226);


                    // AST REWRITE
                    // elements: RPAREN, LPAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 805:13: -> ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN )
                    {
                        // CivlCParser.g:805:16: ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNCTION_SUFFIX, "FUNCTION_SUFFIX")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LPAREN.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, 
                        stream_RPAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directDeclaratorFunctionSuffix"


    public static class typeQualifierList_opt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeQualifierList_opt"
    // CivlCParser.g:813:1: typeQualifierList_opt : ( typeQualifier )* -> ^( TYPE_QUALIFIER_LIST ( typeQualifier )* ) ;
    public final CivlCParser.typeQualifierList_opt_return typeQualifierList_opt() throws RecognitionException {
        CivlCParser.typeQualifierList_opt_return retval = new CivlCParser.typeQualifierList_opt_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.typeQualifier_return typeQualifier227 =null;


        RewriteRuleSubtreeStream stream_typeQualifier=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifier");
        try {
            // CivlCParser.g:814:2: ( ( typeQualifier )* -> ^( TYPE_QUALIFIER_LIST ( typeQualifier )* ) )
            // CivlCParser.g:814:4: ( typeQualifier )*
            {
            // CivlCParser.g:814:4: ( typeQualifier )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==ATOMIC||LA56_0==CONST||LA56_0==INPUT||LA56_0==OUTPUT||LA56_0==RESTRICT||LA56_0==VOLATILE) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // CivlCParser.g:814:4: typeQualifier
            	    {
            	    pushFollow(FOLLOW_typeQualifier_in_typeQualifierList_opt4488);
            	    typeQualifier227=typeQualifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeQualifier.add(typeQualifier227.getTree());

            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);


            // AST REWRITE
            // elements: typeQualifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 814:19: -> ^( TYPE_QUALIFIER_LIST ( typeQualifier )* )
            {
                // CivlCParser.g:814:22: ^( TYPE_QUALIFIER_LIST ( typeQualifier )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TYPE_QUALIFIER_LIST, "TYPE_QUALIFIER_LIST")
                , root_1);

                // CivlCParser.g:814:44: ( typeQualifier )*
                while ( stream_typeQualifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeQualifier.nextTree());

                }
                stream_typeQualifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typeQualifierList_opt"


    public static class assignmentExpression_opt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignmentExpression_opt"
    // CivlCParser.g:820:1: assignmentExpression_opt : ( -> ABSENT | assignmentExpression );
    public final CivlCParser.assignmentExpression_opt_return assignmentExpression_opt() throws RecognitionException {
        CivlCParser.assignmentExpression_opt_return retval = new CivlCParser.assignmentExpression_opt_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.assignmentExpression_return assignmentExpression228 =null;



        try {
            // CivlCParser.g:821:2: ( -> ABSENT | assignmentExpression )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==RSQUARE) ) {
                alt57=1;
            }
            else if ( ((LA57_0 >= ALIGNOF && LA57_0 <= AMPERSAND)||LA57_0==CHARACTER_CONSTANT||LA57_0==FLOATING_CONSTANT||LA57_0==GENERIC||LA57_0==IDENTIFIER||LA57_0==INTEGER_CONSTANT||LA57_0==LPAREN||LA57_0==MINUSMINUS||LA57_0==NOT||LA57_0==PLUS||LA57_0==PLUSPLUS||(LA57_0 >= SIZEOF && LA57_0 <= STAR)||LA57_0==STRING_LITERAL||LA57_0==SUB||LA57_0==TILDE) ) {
                alt57=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;

            }
            switch (alt57) {
                case 1 :
                    // CivlCParser.g:821:5: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 821:5: -> ABSENT
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:822:4: assignmentExpression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression_opt4519);
                    assignmentExpression228=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression228.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignmentExpression_opt"


    public static class pointer_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointer"
    // CivlCParser.g:829:1: pointer : ( pointer_part )+ -> ^( POINTER ( pointer_part )+ ) ;
    public final CivlCParser.pointer_return pointer() throws RecognitionException {
        CivlCParser.pointer_return retval = new CivlCParser.pointer_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.pointer_part_return pointer_part229 =null;


        RewriteRuleSubtreeStream stream_pointer_part=new RewriteRuleSubtreeStream(adaptor,"rule pointer_part");
        try {
            // CivlCParser.g:830:5: ( ( pointer_part )+ -> ^( POINTER ( pointer_part )+ ) )
            // CivlCParser.g:830:7: ( pointer_part )+
            {
            // CivlCParser.g:830:7: ( pointer_part )+
            int cnt58=0;
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==STAR) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // CivlCParser.g:830:7: pointer_part
            	    {
            	    pushFollow(FOLLOW_pointer_part_in_pointer4535);
            	    pointer_part229=pointer_part();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_pointer_part.add(pointer_part229.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt58 >= 1 ) break loop58;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(58, input);
                        throw eee;
                }
                cnt58++;
            } while (true);


            // AST REWRITE
            // elements: pointer_part
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 830:21: -> ^( POINTER ( pointer_part )+ )
            {
                // CivlCParser.g:830:24: ^( POINTER ( pointer_part )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(POINTER, "POINTER")
                , root_1);

                if ( !(stream_pointer_part.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_pointer_part.hasNext() ) {
                    adaptor.addChild(root_1, stream_pointer_part.nextTree());

                }
                stream_pointer_part.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pointer"


    public static class pointer_part_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointer_part"
    // CivlCParser.g:837:1: pointer_part : STAR typeQualifierList_opt -> ^( STAR typeQualifierList_opt ) ;
    public final CivlCParser.pointer_part_return pointer_part() throws RecognitionException {
        CivlCParser.pointer_part_return retval = new CivlCParser.pointer_part_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token STAR230=null;
        CivlCParser.typeQualifierList_opt_return typeQualifierList_opt231 =null;


        Object STAR230_tree=null;
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleSubtreeStream stream_typeQualifierList_opt=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifierList_opt");
        try {
            // CivlCParser.g:838:2: ( STAR typeQualifierList_opt -> ^( STAR typeQualifierList_opt ) )
            // CivlCParser.g:838:4: STAR typeQualifierList_opt
            {
            STAR230=(Token)match(input,STAR,FOLLOW_STAR_in_pointer_part4561); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STAR.add(STAR230);


            pushFollow(FOLLOW_typeQualifierList_opt_in_pointer_part4563);
            typeQualifierList_opt231=typeQualifierList_opt();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeQualifierList_opt.add(typeQualifierList_opt231.getTree());

            // AST REWRITE
            // elements: typeQualifierList_opt, STAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 838:31: -> ^( STAR typeQualifierList_opt )
            {
                // CivlCParser.g:838:34: ^( STAR typeQualifierList_opt )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_STAR.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_typeQualifierList_opt.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pointer_part"


    public static class typeQualifierList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeQualifierList"
    // CivlCParser.g:845:1: typeQualifierList : ( typeQualifier )+ -> ^( TYPE_QUALIFIER_LIST ( typeQualifier )+ ) ;
    public final CivlCParser.typeQualifierList_return typeQualifierList() throws RecognitionException {
        CivlCParser.typeQualifierList_return retval = new CivlCParser.typeQualifierList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.typeQualifier_return typeQualifier232 =null;


        RewriteRuleSubtreeStream stream_typeQualifier=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifier");
        try {
            // CivlCParser.g:846:5: ( ( typeQualifier )+ -> ^( TYPE_QUALIFIER_LIST ( typeQualifier )+ ) )
            // CivlCParser.g:846:7: ( typeQualifier )+
            {
            // CivlCParser.g:846:7: ( typeQualifier )+
            int cnt59=0;
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==ATOMIC||LA59_0==CONST||LA59_0==INPUT||LA59_0==OUTPUT||LA59_0==RESTRICT||LA59_0==VOLATILE) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // CivlCParser.g:846:7: typeQualifier
            	    {
            	    pushFollow(FOLLOW_typeQualifier_in_typeQualifierList4587);
            	    typeQualifier232=typeQualifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeQualifier.add(typeQualifier232.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt59 >= 1 ) break loop59;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(59, input);
                        throw eee;
                }
                cnt59++;
            } while (true);


            // AST REWRITE
            // elements: typeQualifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 846:22: -> ^( TYPE_QUALIFIER_LIST ( typeQualifier )+ )
            {
                // CivlCParser.g:846:25: ^( TYPE_QUALIFIER_LIST ( typeQualifier )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TYPE_QUALIFIER_LIST, "TYPE_QUALIFIER_LIST")
                , root_1);

                if ( !(stream_typeQualifier.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_typeQualifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeQualifier.nextTree());

                }
                stream_typeQualifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typeQualifierList"


    public static class parameterTypeList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameterTypeList"
    // CivlCParser.g:860:1: parameterTypeList : ({...}? parameterTypeListWithoutScope | parameterTypeListWithScope );
    public final CivlCParser.parameterTypeList_return parameterTypeList() throws RecognitionException {
        CivlCParser.parameterTypeList_return retval = new CivlCParser.parameterTypeList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.parameterTypeListWithoutScope_return parameterTypeListWithoutScope233 =null;

        CivlCParser.parameterTypeListWithScope_return parameterTypeListWithScope234 =null;



        try {
            // CivlCParser.g:861:2: ({...}? parameterTypeListWithoutScope | parameterTypeListWithScope )
            int alt60=2;
            switch ( input.LA(1) ) {
            case TYPEDEF:
                {
                int LA60_1 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 1, input);

                    throw nvae;

                }
                }
                break;
            case AUTO:
            case EXTERN:
            case REGISTER:
            case STATIC:
            case THREADLOCAL:
                {
                int LA60_2 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 2, input);

                    throw nvae;

                }
                }
                break;
            case VOID:
                {
                int LA60_3 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 3, input);

                    throw nvae;

                }
                }
                break;
            case CHAR:
                {
                int LA60_4 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 4, input);

                    throw nvae;

                }
                }
                break;
            case SHORT:
                {
                int LA60_5 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 5, input);

                    throw nvae;

                }
                }
                break;
            case INT:
                {
                int LA60_6 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 6, input);

                    throw nvae;

                }
                }
                break;
            case LONG:
                {
                int LA60_7 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 7, input);

                    throw nvae;

                }
                }
                break;
            case FLOAT:
                {
                int LA60_8 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 8, input);

                    throw nvae;

                }
                }
                break;
            case DOUBLE:
                {
                int LA60_9 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 9, input);

                    throw nvae;

                }
                }
                break;
            case SIGNED:
                {
                int LA60_10 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 10, input);

                    throw nvae;

                }
                }
                break;
            case UNSIGNED:
                {
                int LA60_11 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 11, input);

                    throw nvae;

                }
                }
                break;
            case BOOL:
                {
                int LA60_12 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 12, input);

                    throw nvae;

                }
                }
                break;
            case COMPLEX:
                {
                int LA60_13 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 13, input);

                    throw nvae;

                }
                }
                break;
            case PROC:
                {
                int LA60_14 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 14, input);

                    throw nvae;

                }
                }
                break;
            case ATOMIC:
                {
                int LA60_15 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 15, input);

                    throw nvae;

                }
                }
                break;
            case STRUCT:
            case UNION:
                {
                int LA60_16 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 16, input);

                    throw nvae;

                }
                }
                break;
            case ENUM:
                {
                int LA60_17 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 17, input);

                    throw nvae;

                }
                }
                break;
            case IDENTIFIER:
                {
                int LA60_18 = input.LA(2);

                if ( (((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 18, input);

                    throw nvae;

                }
                }
                break;
            case CONST:
            case INPUT:
            case OUTPUT:
            case RESTRICT:
            case VOLATILE:
                {
                int LA60_19 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 19, input);

                    throw nvae;

                }
                }
                break;
            case INLINE:
            case NORETURN:
                {
                int LA60_20 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 20, input);

                    throw nvae;

                }
                }
                break;
            case ALIGNAS:
                {
                int LA60_21 = input.LA(2);

                if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition))) ) {
                    alt60=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 21, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;

            }

            switch (alt60) {
                case 1 :
                    // CivlCParser.g:861:4: {...}? parameterTypeListWithoutScope
                    {
                    root_0 = (Object)adaptor.nil();


                    if ( !((((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition)) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "parameterTypeList", "$Symbols::isFunctionDefinition");
                    }

                    pushFollow(FOLLOW_parameterTypeListWithoutScope_in_parameterTypeList4617);
                    parameterTypeListWithoutScope233=parameterTypeListWithoutScope();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameterTypeListWithoutScope233.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:862:4: parameterTypeListWithScope
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_parameterTypeListWithScope_in_parameterTypeList4622);
                    parameterTypeListWithScope234=parameterTypeListWithScope();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameterTypeListWithScope234.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameterTypeList"


    public static class parameterTypeListWithScope_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameterTypeListWithScope"
    // CivlCParser.g:865:1: parameterTypeListWithScope : parameterTypeListWithoutScope ;
    public final CivlCParser.parameterTypeListWithScope_return parameterTypeListWithScope() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CivlCParser.parameterTypeListWithScope_return retval = new CivlCParser.parameterTypeListWithScope_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.parameterTypeListWithoutScope_return parameterTypeListWithoutScope235 =null;




        	((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
        	((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
        	((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = false;

        try {
            // CivlCParser.g:872:2: ( parameterTypeListWithoutScope )
            // CivlCParser.g:872:4: parameterTypeListWithoutScope
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_parameterTypeListWithoutScope_in_parameterTypeListWithScope4643);
            parameterTypeListWithoutScope235=parameterTypeListWithoutScope();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parameterTypeListWithoutScope235.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "parameterTypeListWithScope"


    public static class parameterTypeListWithoutScope_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameterTypeListWithoutScope"
    // CivlCParser.g:875:1: parameterTypeListWithoutScope : parameterList ( -> ^( PARAMETER_TYPE_LIST parameterList ABSENT ) | COMMA ELLIPSIS -> ^( PARAMETER_TYPE_LIST parameterList ELLIPSIS ) ) ;
    public final CivlCParser.parameterTypeListWithoutScope_return parameterTypeListWithoutScope() throws RecognitionException {
        CivlCParser.parameterTypeListWithoutScope_return retval = new CivlCParser.parameterTypeListWithoutScope_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA237=null;
        Token ELLIPSIS238=null;
        CivlCParser.parameterList_return parameterList236 =null;


        Object COMMA237_tree=null;
        Object ELLIPSIS238_tree=null;
        RewriteRuleTokenStream stream_ELLIPSIS=new RewriteRuleTokenStream(adaptor,"token ELLIPSIS");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_parameterList=new RewriteRuleSubtreeStream(adaptor,"rule parameterList");
        try {
            // CivlCParser.g:876:5: ( parameterList ( -> ^( PARAMETER_TYPE_LIST parameterList ABSENT ) | COMMA ELLIPSIS -> ^( PARAMETER_TYPE_LIST parameterList ELLIPSIS ) ) )
            // CivlCParser.g:876:7: parameterList ( -> ^( PARAMETER_TYPE_LIST parameterList ABSENT ) | COMMA ELLIPSIS -> ^( PARAMETER_TYPE_LIST parameterList ELLIPSIS ) )
            {
            pushFollow(FOLLOW_parameterList_in_parameterTypeListWithoutScope4657);
            parameterList236=parameterList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_parameterList.add(parameterList236.getTree());

            // CivlCParser.g:877:7: ( -> ^( PARAMETER_TYPE_LIST parameterList ABSENT ) | COMMA ELLIPSIS -> ^( PARAMETER_TYPE_LIST parameterList ELLIPSIS ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==RPAREN) ) {
                alt61=1;
            }
            else if ( (LA61_0==COMMA) ) {
                alt61=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }
            switch (alt61) {
                case 1 :
                    // CivlCParser.g:877:9: 
                    {
                    // AST REWRITE
                    // elements: parameterList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 877:9: -> ^( PARAMETER_TYPE_LIST parameterList ABSENT )
                    {
                        // CivlCParser.g:877:12: ^( PARAMETER_TYPE_LIST parameterList ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARAMETER_TYPE_LIST, "PARAMETER_TYPE_LIST")
                        , root_1);

                        adaptor.addChild(root_1, stream_parameterList.nextTree());

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:878:9: COMMA ELLIPSIS
                    {
                    COMMA237=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameterTypeListWithoutScope4685); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA237);


                    ELLIPSIS238=(Token)match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_parameterTypeListWithoutScope4687); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELLIPSIS.add(ELLIPSIS238);


                    // AST REWRITE
                    // elements: ELLIPSIS, parameterList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 879:9: -> ^( PARAMETER_TYPE_LIST parameterList ELLIPSIS )
                    {
                        // CivlCParser.g:879:12: ^( PARAMETER_TYPE_LIST parameterList ELLIPSIS )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARAMETER_TYPE_LIST, "PARAMETER_TYPE_LIST")
                        , root_1);

                        adaptor.addChild(root_1, stream_parameterList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_ELLIPSIS.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameterTypeListWithoutScope"


    public static class parameterList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameterList"
    // CivlCParser.g:887:1: parameterList : parameterDeclaration ( COMMA parameterDeclaration )* -> ^( PARAMETER_LIST ( parameterDeclaration )+ ) ;
    public final CivlCParser.parameterList_return parameterList() throws RecognitionException {
        CivlCParser.parameterList_return retval = new CivlCParser.parameterList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA240=null;
        CivlCParser.parameterDeclaration_return parameterDeclaration239 =null;

        CivlCParser.parameterDeclaration_return parameterDeclaration241 =null;


        Object COMMA240_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_parameterDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule parameterDeclaration");
        try {
            // CivlCParser.g:888:5: ( parameterDeclaration ( COMMA parameterDeclaration )* -> ^( PARAMETER_LIST ( parameterDeclaration )+ ) )
            // CivlCParser.g:888:7: parameterDeclaration ( COMMA parameterDeclaration )*
            {
            pushFollow(FOLLOW_parameterDeclaration_in_parameterList4732);
            parameterDeclaration239=parameterDeclaration();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_parameterDeclaration.add(parameterDeclaration239.getTree());

            // CivlCParser.g:888:28: ( COMMA parameterDeclaration )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==COMMA) ) {
                    int LA62_2 = input.LA(2);

                    if ( (LA62_2==ALIGNAS||(LA62_2 >= ATOMIC && LA62_2 <= AUTO)||LA62_2==BOOL||LA62_2==CHAR||(LA62_2 >= COMPLEX && LA62_2 <= CONST)||LA62_2==DOUBLE||LA62_2==ENUM||LA62_2==EXTERN||LA62_2==FLOAT||LA62_2==IDENTIFIER||(LA62_2 >= INLINE && LA62_2 <= INT)||LA62_2==LONG||LA62_2==NORETURN||LA62_2==OUTPUT||LA62_2==PROC||(LA62_2 >= REGISTER && LA62_2 <= RESTRICT)||(LA62_2 >= SHORT && LA62_2 <= SIGNED)||LA62_2==STATIC||LA62_2==STRUCT||LA62_2==THREADLOCAL||(LA62_2 >= TYPEDEF && LA62_2 <= UNSIGNED)||(LA62_2 >= VOID && LA62_2 <= VOLATILE)) ) {
                        alt62=1;
                    }


                }


                switch (alt62) {
            	case 1 :
            	    // CivlCParser.g:888:29: COMMA parameterDeclaration
            	    {
            	    COMMA240=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameterList4735); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA240);


            	    pushFollow(FOLLOW_parameterDeclaration_in_parameterList4737);
            	    parameterDeclaration241=parameterDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_parameterDeclaration.add(parameterDeclaration241.getTree());

            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            // AST REWRITE
            // elements: parameterDeclaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 889:7: -> ^( PARAMETER_LIST ( parameterDeclaration )+ )
            {
                // CivlCParser.g:889:10: ^( PARAMETER_LIST ( parameterDeclaration )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PARAMETER_LIST, "PARAMETER_LIST")
                , root_1);

                if ( !(stream_parameterDeclaration.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_parameterDeclaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameterDeclaration.nextTree());

                }
                stream_parameterDeclaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameterList"


    public static class parameterDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameterDeclaration"
    // CivlCParser.g:897:1: parameterDeclaration : declarationSpecifiers ( -> ^( PARAMETER_DECLARATION declarationSpecifiers ABSENT ) | declaratorOrAbstractDeclarator -> ^( PARAMETER_DECLARATION declarationSpecifiers declaratorOrAbstractDeclarator ) ) ;
    public final CivlCParser.parameterDeclaration_return parameterDeclaration() throws RecognitionException {
        DeclarationScope_stack.push(new DeclarationScope_scope());

        CivlCParser.parameterDeclaration_return retval = new CivlCParser.parameterDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.declarationSpecifiers_return declarationSpecifiers242 =null;

        CivlCParser.declaratorOrAbstractDeclarator_return declaratorOrAbstractDeclarator243 =null;


        RewriteRuleSubtreeStream stream_declaratorOrAbstractDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule declaratorOrAbstractDeclarator");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");

        	((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef = false;

        try {
            // CivlCParser.g:902:5: ( declarationSpecifiers ( -> ^( PARAMETER_DECLARATION declarationSpecifiers ABSENT ) | declaratorOrAbstractDeclarator -> ^( PARAMETER_DECLARATION declarationSpecifiers declaratorOrAbstractDeclarator ) ) )
            // CivlCParser.g:902:7: declarationSpecifiers ( -> ^( PARAMETER_DECLARATION declarationSpecifiers ABSENT ) | declaratorOrAbstractDeclarator -> ^( PARAMETER_DECLARATION declarationSpecifiers declaratorOrAbstractDeclarator ) )
            {
            pushFollow(FOLLOW_declarationSpecifiers_in_parameterDeclaration4783);
            declarationSpecifiers242=declarationSpecifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarationSpecifiers.add(declarationSpecifiers242.getTree());

            // CivlCParser.g:903:7: ( -> ^( PARAMETER_DECLARATION declarationSpecifiers ABSENT ) | declaratorOrAbstractDeclarator -> ^( PARAMETER_DECLARATION declarationSpecifiers declaratorOrAbstractDeclarator ) )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==COMMA||LA63_0==RPAREN) ) {
                alt63=1;
            }
            else if ( (LA63_0==IDENTIFIER||(LA63_0 >= LPAREN && LA63_0 <= LSQUARE)||LA63_0==STAR) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // CivlCParser.g:903:9: 
                    {
                    // AST REWRITE
                    // elements: declarationSpecifiers
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 903:9: -> ^( PARAMETER_DECLARATION declarationSpecifiers ABSENT )
                    {
                        // CivlCParser.g:903:12: ^( PARAMETER_DECLARATION declarationSpecifiers ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARAMETER_DECLARATION, "PARAMETER_DECLARATION")
                        , root_1);

                        adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:904:9: declaratorOrAbstractDeclarator
                    {
                    pushFollow(FOLLOW_declaratorOrAbstractDeclarator_in_parameterDeclaration4811);
                    declaratorOrAbstractDeclarator243=declaratorOrAbstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declaratorOrAbstractDeclarator.add(declaratorOrAbstractDeclarator243.getTree());

                    // AST REWRITE
                    // elements: declaratorOrAbstractDeclarator, declarationSpecifiers
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 905:9: -> ^( PARAMETER_DECLARATION declarationSpecifiers declaratorOrAbstractDeclarator )
                    {
                        // CivlCParser.g:905:12: ^( PARAMETER_DECLARATION declarationSpecifiers declaratorOrAbstractDeclarator )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARAMETER_DECLARATION, "PARAMETER_DECLARATION")
                        , root_1);

                        adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());

                        adaptor.addChild(root_1, stream_declaratorOrAbstractDeclarator.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            DeclarationScope_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "parameterDeclaration"


    public static class declaratorOrAbstractDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declaratorOrAbstractDeclarator"
    // CivlCParser.g:914:1: declaratorOrAbstractDeclarator : ( ( declarator )=> declarator | abstractDeclarator );
    public final CivlCParser.declaratorOrAbstractDeclarator_return declaratorOrAbstractDeclarator() throws RecognitionException {
        CivlCParser.declaratorOrAbstractDeclarator_return retval = new CivlCParser.declaratorOrAbstractDeclarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.declarator_return declarator244 =null;

        CivlCParser.abstractDeclarator_return abstractDeclarator245 =null;



        try {
            // CivlCParser.g:915:2: ( ( declarator )=> declarator | abstractDeclarator )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==IDENTIFIER) && (synpred6_CivlCParser())) {
                alt64=1;
            }
            else if ( (LA64_0==LPAREN) ) {
                int LA64_2 = input.LA(2);

                if ( (synpred6_CivlCParser()) ) {
                    alt64=1;
                }
                else if ( (true) ) {
                    alt64=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 64, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA64_0==STAR) ) {
                int LA64_3 = input.LA(2);

                if ( (synpred6_CivlCParser()) ) {
                    alt64=1;
                }
                else if ( (true) ) {
                    alt64=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 64, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA64_0==LSQUARE) ) {
                alt64=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;

            }
            switch (alt64) {
                case 1 :
                    // CivlCParser.g:915:4: ( declarator )=> declarator
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarator_in_declaratorOrAbstractDeclarator4873);
                    declarator244=declarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, declarator244.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:916:4: abstractDeclarator
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_abstractDeclarator_in_declaratorOrAbstractDeclarator4878);
                    abstractDeclarator245=abstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, abstractDeclarator245.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "declaratorOrAbstractDeclarator"


    public static class identifierList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "identifierList"
    // CivlCParser.g:924:1: identifierList : IDENTIFIER ( COMMA IDENTIFIER )* -> ^( IDENTIFIER_LIST ( IDENTIFIER )+ ) ;
    public final CivlCParser.identifierList_return identifierList() throws RecognitionException {
        CivlCParser.identifierList_return retval = new CivlCParser.identifierList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER246=null;
        Token COMMA247=null;
        Token IDENTIFIER248=null;

        Object IDENTIFIER246_tree=null;
        Object COMMA247_tree=null;
        Object IDENTIFIER248_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // CivlCParser.g:925:5: ( IDENTIFIER ( COMMA IDENTIFIER )* -> ^( IDENTIFIER_LIST ( IDENTIFIER )+ ) )
            // CivlCParser.g:925:7: IDENTIFIER ( COMMA IDENTIFIER )*
            {
            IDENTIFIER246=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierList4896); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER246);


            // CivlCParser.g:925:18: ( COMMA IDENTIFIER )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==COMMA) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // CivlCParser.g:925:20: COMMA IDENTIFIER
            	    {
            	    COMMA247=(Token)match(input,COMMA,FOLLOW_COMMA_in_identifierList4900); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA247);


            	    IDENTIFIER248=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierList4902); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER248);


            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);


            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 926:7: -> ^( IDENTIFIER_LIST ( IDENTIFIER )+ )
            {
                // CivlCParser.g:926:10: ^( IDENTIFIER_LIST ( IDENTIFIER )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(IDENTIFIER_LIST, "IDENTIFIER_LIST")
                , root_1);

                if ( !(stream_IDENTIFIER.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_IDENTIFIER.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_IDENTIFIER.nextNode()
                    );

                }
                stream_IDENTIFIER.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "identifierList"


    public static class typeName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeName"
    // CivlCParser.g:935:1: typeName : specifierQualifierList ( -> ^( TYPE_NAME specifierQualifierList ABSENT ) | abstractDeclarator -> ^( TYPE_NAME specifierQualifierList abstractDeclarator ) ) ;
    public final CivlCParser.typeName_return typeName() throws RecognitionException {
        CivlCParser.typeName_return retval = new CivlCParser.typeName_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.specifierQualifierList_return specifierQualifierList249 =null;

        CivlCParser.abstractDeclarator_return abstractDeclarator250 =null;


        RewriteRuleSubtreeStream stream_specifierQualifierList=new RewriteRuleSubtreeStream(adaptor,"rule specifierQualifierList");
        RewriteRuleSubtreeStream stream_abstractDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule abstractDeclarator");
        try {
            // CivlCParser.g:936:5: ( specifierQualifierList ( -> ^( TYPE_NAME specifierQualifierList ABSENT ) | abstractDeclarator -> ^( TYPE_NAME specifierQualifierList abstractDeclarator ) ) )
            // CivlCParser.g:936:7: specifierQualifierList ( -> ^( TYPE_NAME specifierQualifierList ABSENT ) | abstractDeclarator -> ^( TYPE_NAME specifierQualifierList abstractDeclarator ) )
            {
            pushFollow(FOLLOW_specifierQualifierList_in_typeName4939);
            specifierQualifierList249=specifierQualifierList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_specifierQualifierList.add(specifierQualifierList249.getTree());

            // CivlCParser.g:937:7: ( -> ^( TYPE_NAME specifierQualifierList ABSENT ) | abstractDeclarator -> ^( TYPE_NAME specifierQualifierList abstractDeclarator ) )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==EOF||LA66_0==COLON||LA66_0==RPAREN) ) {
                alt66=1;
            }
            else if ( ((LA66_0 >= LPAREN && LA66_0 <= LSQUARE)||LA66_0==STAR) ) {
                alt66=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // CivlCParser.g:937:9: 
                    {
                    // AST REWRITE
                    // elements: specifierQualifierList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 937:9: -> ^( TYPE_NAME specifierQualifierList ABSENT )
                    {
                        // CivlCParser.g:937:12: ^( TYPE_NAME specifierQualifierList ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(TYPE_NAME, "TYPE_NAME")
                        , root_1);

                        adaptor.addChild(root_1, stream_specifierQualifierList.nextTree());

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:938:9: abstractDeclarator
                    {
                    pushFollow(FOLLOW_abstractDeclarator_in_typeName4967);
                    abstractDeclarator250=abstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_abstractDeclarator.add(abstractDeclarator250.getTree());

                    // AST REWRITE
                    // elements: specifierQualifierList, abstractDeclarator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 939:9: -> ^( TYPE_NAME specifierQualifierList abstractDeclarator )
                    {
                        // CivlCParser.g:939:12: ^( TYPE_NAME specifierQualifierList abstractDeclarator )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(TYPE_NAME, "TYPE_NAME")
                        , root_1);

                        adaptor.addChild(root_1, stream_specifierQualifierList.nextTree());

                        adaptor.addChild(root_1, stream_abstractDeclarator.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typeName"


    public static class abstractDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "abstractDeclarator"
    // CivlCParser.g:951:1: abstractDeclarator : ( pointer -> ^( ABSTRACT_DECLARATOR pointer ABSENT ) | directAbstractDeclarator -> ^( ABSTRACT_DECLARATOR ABSENT directAbstractDeclarator ) | pointer directAbstractDeclarator -> ^( ABSTRACT_DECLARATOR pointer directAbstractDeclarator ) );
    public final CivlCParser.abstractDeclarator_return abstractDeclarator() throws RecognitionException {
        CivlCParser.abstractDeclarator_return retval = new CivlCParser.abstractDeclarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.pointer_return pointer251 =null;

        CivlCParser.directAbstractDeclarator_return directAbstractDeclarator252 =null;

        CivlCParser.pointer_return pointer253 =null;

        CivlCParser.directAbstractDeclarator_return directAbstractDeclarator254 =null;


        RewriteRuleSubtreeStream stream_pointer=new RewriteRuleSubtreeStream(adaptor,"rule pointer");
        RewriteRuleSubtreeStream stream_directAbstractDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule directAbstractDeclarator");
        try {
            // CivlCParser.g:952:5: ( pointer -> ^( ABSTRACT_DECLARATOR pointer ABSENT ) | directAbstractDeclarator -> ^( ABSTRACT_DECLARATOR ABSENT directAbstractDeclarator ) | pointer directAbstractDeclarator -> ^( ABSTRACT_DECLARATOR pointer directAbstractDeclarator ) )
            int alt67=3;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // CivlCParser.g:952:7: pointer
                    {
                    pushFollow(FOLLOW_pointer_in_abstractDeclarator5012);
                    pointer251=pointer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pointer.add(pointer251.getTree());

                    // AST REWRITE
                    // elements: pointer
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 953:7: -> ^( ABSTRACT_DECLARATOR pointer ABSENT )
                    {
                        // CivlCParser.g:953:10: ^( ABSTRACT_DECLARATOR pointer ABSENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ABSTRACT_DECLARATOR, "ABSTRACT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_pointer.nextTree());

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:954:7: directAbstractDeclarator
                    {
                    pushFollow(FOLLOW_directAbstractDeclarator_in_abstractDeclarator5036);
                    directAbstractDeclarator252=directAbstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_directAbstractDeclarator.add(directAbstractDeclarator252.getTree());

                    // AST REWRITE
                    // elements: directAbstractDeclarator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 955:7: -> ^( ABSTRACT_DECLARATOR ABSENT directAbstractDeclarator )
                    {
                        // CivlCParser.g:955:10: ^( ABSTRACT_DECLARATOR ABSENT directAbstractDeclarator )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ABSTRACT_DECLARATOR, "ABSTRACT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_directAbstractDeclarator.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:956:7: pointer directAbstractDeclarator
                    {
                    pushFollow(FOLLOW_pointer_in_abstractDeclarator5060);
                    pointer253=pointer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pointer.add(pointer253.getTree());

                    pushFollow(FOLLOW_directAbstractDeclarator_in_abstractDeclarator5062);
                    directAbstractDeclarator254=directAbstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_directAbstractDeclarator.add(directAbstractDeclarator254.getTree());

                    // AST REWRITE
                    // elements: pointer, directAbstractDeclarator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 957:7: -> ^( ABSTRACT_DECLARATOR pointer directAbstractDeclarator )
                    {
                        // CivlCParser.g:957:10: ^( ABSTRACT_DECLARATOR pointer directAbstractDeclarator )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ABSTRACT_DECLARATOR, "ABSTRACT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_pointer.nextTree());

                        adaptor.addChild(root_1, stream_directAbstractDeclarator.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "abstractDeclarator"


    public static class directAbstractDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directAbstractDeclarator"
    // CivlCParser.g:970:1: directAbstractDeclarator : ( LPAREN abstractDeclarator RPAREN ( directAbstractDeclaratorSuffix )* -> ^( DIRECT_ABSTRACT_DECLARATOR abstractDeclarator ( directAbstractDeclaratorSuffix )* ) | ( directAbstractDeclaratorSuffix )+ -> ^( DIRECT_ABSTRACT_DECLARATOR ABSENT ( directAbstractDeclaratorSuffix )+ ) );
    public final CivlCParser.directAbstractDeclarator_return directAbstractDeclarator() throws RecognitionException {
        CivlCParser.directAbstractDeclarator_return retval = new CivlCParser.directAbstractDeclarator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LPAREN255=null;
        Token RPAREN257=null;
        CivlCParser.abstractDeclarator_return abstractDeclarator256 =null;

        CivlCParser.directAbstractDeclaratorSuffix_return directAbstractDeclaratorSuffix258 =null;

        CivlCParser.directAbstractDeclaratorSuffix_return directAbstractDeclaratorSuffix259 =null;


        Object LPAREN255_tree=null;
        Object RPAREN257_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_directAbstractDeclaratorSuffix=new RewriteRuleSubtreeStream(adaptor,"rule directAbstractDeclaratorSuffix");
        RewriteRuleSubtreeStream stream_abstractDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule abstractDeclarator");
        try {
            // CivlCParser.g:971:5: ( LPAREN abstractDeclarator RPAREN ( directAbstractDeclaratorSuffix )* -> ^( DIRECT_ABSTRACT_DECLARATOR abstractDeclarator ( directAbstractDeclaratorSuffix )* ) | ( directAbstractDeclaratorSuffix )+ -> ^( DIRECT_ABSTRACT_DECLARATOR ABSENT ( directAbstractDeclaratorSuffix )+ ) )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==LPAREN) ) {
                int LA70_1 = input.LA(2);

                if ( ((LA70_1 >= LPAREN && LA70_1 <= LSQUARE)||LA70_1==STAR) ) {
                    alt70=1;
                }
                else if ( (LA70_1==ALIGNAS||(LA70_1 >= ATOMIC && LA70_1 <= AUTO)||LA70_1==BOOL||LA70_1==CHAR||(LA70_1 >= COMPLEX && LA70_1 <= CONST)||LA70_1==DOUBLE||LA70_1==ENUM||LA70_1==EXTERN||LA70_1==FLOAT||LA70_1==IDENTIFIER||(LA70_1 >= INLINE && LA70_1 <= INT)||LA70_1==LONG||LA70_1==NORETURN||LA70_1==OUTPUT||LA70_1==PROC||(LA70_1 >= REGISTER && LA70_1 <= RESTRICT)||LA70_1==RPAREN||(LA70_1 >= SHORT && LA70_1 <= SIGNED)||LA70_1==STATIC||LA70_1==STRUCT||LA70_1==THREADLOCAL||(LA70_1 >= TYPEDEF && LA70_1 <= UNSIGNED)||(LA70_1 >= VOID && LA70_1 <= VOLATILE)) ) {
                    alt70=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA70_0==LSQUARE) ) {
                alt70=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }
            switch (alt70) {
                case 1 :
                    // CivlCParser.g:971:7: LPAREN abstractDeclarator RPAREN ( directAbstractDeclaratorSuffix )*
                    {
                    LPAREN255=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_directAbstractDeclarator5097); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN255);


                    pushFollow(FOLLOW_abstractDeclarator_in_directAbstractDeclarator5099);
                    abstractDeclarator256=abstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_abstractDeclarator.add(abstractDeclarator256.getTree());

                    RPAREN257=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directAbstractDeclarator5101); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN257);


                    // CivlCParser.g:971:40: ( directAbstractDeclaratorSuffix )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( ((LA68_0 >= LPAREN && LA68_0 <= LSQUARE)) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // CivlCParser.g:971:40: directAbstractDeclaratorSuffix
                    	    {
                    	    pushFollow(FOLLOW_directAbstractDeclaratorSuffix_in_directAbstractDeclarator5103);
                    	    directAbstractDeclaratorSuffix258=directAbstractDeclaratorSuffix();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_directAbstractDeclaratorSuffix.add(directAbstractDeclaratorSuffix258.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: abstractDeclarator, directAbstractDeclaratorSuffix
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 972:7: -> ^( DIRECT_ABSTRACT_DECLARATOR abstractDeclarator ( directAbstractDeclaratorSuffix )* )
                    {
                        // CivlCParser.g:972:10: ^( DIRECT_ABSTRACT_DECLARATOR abstractDeclarator ( directAbstractDeclaratorSuffix )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DIRECT_ABSTRACT_DECLARATOR, "DIRECT_ABSTRACT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_abstractDeclarator.nextTree());

                        // CivlCParser.g:973:12: ( directAbstractDeclaratorSuffix )*
                        while ( stream_directAbstractDeclaratorSuffix.hasNext() ) {
                            adaptor.addChild(root_1, stream_directAbstractDeclaratorSuffix.nextTree());

                        }
                        stream_directAbstractDeclaratorSuffix.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:974:7: ( directAbstractDeclaratorSuffix )+
                    {
                    // CivlCParser.g:974:7: ( directAbstractDeclaratorSuffix )+
                    int cnt69=0;
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( ((LA69_0 >= LPAREN && LA69_0 <= LSQUARE)) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // CivlCParser.g:974:7: directAbstractDeclaratorSuffix
                    	    {
                    	    pushFollow(FOLLOW_directAbstractDeclaratorSuffix_in_directAbstractDeclarator5140);
                    	    directAbstractDeclaratorSuffix259=directAbstractDeclaratorSuffix();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_directAbstractDeclaratorSuffix.add(directAbstractDeclaratorSuffix259.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt69 >= 1 ) break loop69;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(69, input);
                                throw eee;
                        }
                        cnt69++;
                    } while (true);


                    // AST REWRITE
                    // elements: directAbstractDeclaratorSuffix
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 975:7: -> ^( DIRECT_ABSTRACT_DECLARATOR ABSENT ( directAbstractDeclaratorSuffix )+ )
                    {
                        // CivlCParser.g:975:10: ^( DIRECT_ABSTRACT_DECLARATOR ABSENT ( directAbstractDeclaratorSuffix )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DIRECT_ABSTRACT_DECLARATOR, "DIRECT_ABSTRACT_DECLARATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        if ( !(stream_directAbstractDeclaratorSuffix.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_directAbstractDeclaratorSuffix.hasNext() ) {
                            adaptor.addChild(root_1, stream_directAbstractDeclaratorSuffix.nextTree());

                        }
                        stream_directAbstractDeclaratorSuffix.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directAbstractDeclarator"


    public static class typedefName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typedefName"
    // CivlCParser.g:999:1: typedefName :{...}? IDENTIFIER -> ^( TYPEDEF_NAME IDENTIFIER ) ;
    public final CivlCParser.typedefName_return typedefName() throws RecognitionException {
        CivlCParser.typedefName_return retval = new CivlCParser.typedefName_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER260=null;

        Object IDENTIFIER260_tree=null;
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // CivlCParser.g:1000:5: ({...}? IDENTIFIER -> ^( TYPEDEF_NAME IDENTIFIER ) )
            // CivlCParser.g:1000:7: {...}? IDENTIFIER
            {
            if ( !((isTypeName(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "typedefName", "isTypeName(input.LT(1).getText())");
            }

            IDENTIFIER260=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_typedefName5180); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER260);


            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1001:7: -> ^( TYPEDEF_NAME IDENTIFIER )
            {
                // CivlCParser.g:1001:10: ^( TYPEDEF_NAME IDENTIFIER )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TYPEDEF_NAME, "TYPEDEF_NAME")
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENTIFIER.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typedefName"


    public static class directAbstractDeclaratorSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directAbstractDeclaratorSuffix"
    // CivlCParser.g:1015:1: directAbstractDeclaratorSuffix : ( LSQUARE ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression ) | STAR RSQUARE -> ^( ARRAY_SUFFIX ABSENT ABSENT STAR ) ) | LPAREN ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX parameterTypeList ) | RPAREN -> ^( FUNCTION_SUFFIX ABSENT ) ) );
    public final CivlCParser.directAbstractDeclaratorSuffix_return directAbstractDeclaratorSuffix() throws RecognitionException {
        CivlCParser.directAbstractDeclaratorSuffix_return retval = new CivlCParser.directAbstractDeclaratorSuffix_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LSQUARE261=null;
        Token RSQUARE264=null;
        Token STATIC265=null;
        Token RSQUARE268=null;
        Token STATIC270=null;
        Token RSQUARE272=null;
        Token STAR273=null;
        Token RSQUARE274=null;
        Token LPAREN275=null;
        Token RPAREN277=null;
        Token RPAREN278=null;
        CivlCParser.typeQualifierList_opt_return typeQualifierList_opt262 =null;

        CivlCParser.assignmentExpression_opt_return assignmentExpression_opt263 =null;

        CivlCParser.typeQualifierList_opt_return typeQualifierList_opt266 =null;

        CivlCParser.assignmentExpression_return assignmentExpression267 =null;

        CivlCParser.typeQualifierList_return typeQualifierList269 =null;

        CivlCParser.assignmentExpression_return assignmentExpression271 =null;

        CivlCParser.parameterTypeList_return parameterTypeList276 =null;


        Object LSQUARE261_tree=null;
        Object RSQUARE264_tree=null;
        Object STATIC265_tree=null;
        Object RSQUARE268_tree=null;
        Object STATIC270_tree=null;
        Object RSQUARE272_tree=null;
        Object STAR273_tree=null;
        Object RSQUARE274_tree=null;
        Object LPAREN275_tree=null;
        Object RPAREN277_tree=null;
        Object RPAREN278_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
        RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
        RewriteRuleTokenStream stream_STATIC=new RewriteRuleTokenStream(adaptor,"token STATIC");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typeQualifierList=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifierList");
        RewriteRuleSubtreeStream stream_assignmentExpression_opt=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression_opt");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        RewriteRuleSubtreeStream stream_typeQualifierList_opt=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifierList_opt");
        RewriteRuleSubtreeStream stream_parameterTypeList=new RewriteRuleSubtreeStream(adaptor,"rule parameterTypeList");
        try {
            // CivlCParser.g:1016:5: ( LSQUARE ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression ) | STAR RSQUARE -> ^( ARRAY_SUFFIX ABSENT ABSENT STAR ) ) | LPAREN ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX parameterTypeList ) | RPAREN -> ^( FUNCTION_SUFFIX ABSENT ) ) )
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==LSQUARE) ) {
                alt73=1;
            }
            else if ( (LA73_0==LPAREN) ) {
                alt73=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;

            }
            switch (alt73) {
                case 1 :
                    // CivlCParser.g:1016:7: LSQUARE ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression ) | STAR RSQUARE -> ^( ARRAY_SUFFIX ABSENT ABSENT STAR ) )
                    {
                    LSQUARE261=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_directAbstractDeclaratorSuffix5213); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE261);


                    // CivlCParser.g:1017:7: ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression ) | STAR RSQUARE -> ^( ARRAY_SUFFIX ABSENT ABSENT STAR ) )
                    int alt71=4;
                    alt71 = dfa71.predict(input);
                    switch (alt71) {
                        case 1 :
                            // CivlCParser.g:1017:9: typeQualifierList_opt assignmentExpression_opt RSQUARE
                            {
                            pushFollow(FOLLOW_typeQualifierList_opt_in_directAbstractDeclaratorSuffix5223);
                            typeQualifierList_opt262=typeQualifierList_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_typeQualifierList_opt.add(typeQualifierList_opt262.getTree());

                            pushFollow(FOLLOW_assignmentExpression_opt_in_directAbstractDeclaratorSuffix5225);
                            assignmentExpression_opt263=assignmentExpression_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignmentExpression_opt.add(assignmentExpression_opt263.getTree());

                            RSQUARE264=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5227); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE264);


                            // AST REWRITE
                            // elements: typeQualifierList_opt, assignmentExpression_opt
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1018:9: -> ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt )
                            {
                                // CivlCParser.g:1018:12: ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                                , root_1);

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_1, stream_typeQualifierList_opt.nextTree());

                                adaptor.addChild(root_1, stream_assignmentExpression_opt.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:1020:9: STATIC typeQualifierList_opt assignmentExpression RSQUARE
                            {
                            STATIC265=(Token)match(input,STATIC,FOLLOW_STATIC_in_directAbstractDeclaratorSuffix5270); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STATIC.add(STATIC265);


                            pushFollow(FOLLOW_typeQualifierList_opt_in_directAbstractDeclaratorSuffix5272);
                            typeQualifierList_opt266=typeQualifierList_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_typeQualifierList_opt.add(typeQualifierList_opt266.getTree());

                            pushFollow(FOLLOW_assignmentExpression_in_directAbstractDeclaratorSuffix5274);
                            assignmentExpression267=assignmentExpression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression267.getTree());

                            RSQUARE268=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5276); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE268);


                            // AST REWRITE
                            // elements: typeQualifierList_opt, STATIC, assignmentExpression
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1021:9: -> ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression )
                            {
                                // CivlCParser.g:1021:12: ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                                , root_1);

                                adaptor.addChild(root_1, 
                                stream_STATIC.nextNode()
                                );

                                adaptor.addChild(root_1, stream_typeQualifierList_opt.nextTree());

                                adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 3 :
                            // CivlCParser.g:1023:9: typeQualifierList STATIC assignmentExpression RSQUARE
                            {
                            pushFollow(FOLLOW_typeQualifierList_in_directAbstractDeclaratorSuffix5319);
                            typeQualifierList269=typeQualifierList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_typeQualifierList.add(typeQualifierList269.getTree());

                            STATIC270=(Token)match(input,STATIC,FOLLOW_STATIC_in_directAbstractDeclaratorSuffix5321); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STATIC.add(STATIC270);


                            pushFollow(FOLLOW_assignmentExpression_in_directAbstractDeclaratorSuffix5323);
                            assignmentExpression271=assignmentExpression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression271.getTree());

                            RSQUARE272=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5325); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE272);


                            // AST REWRITE
                            // elements: typeQualifierList, STATIC, assignmentExpression
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1024:9: -> ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression )
                            {
                                // CivlCParser.g:1024:12: ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                                , root_1);

                                adaptor.addChild(root_1, 
                                stream_STATIC.nextNode()
                                );

                                adaptor.addChild(root_1, stream_typeQualifierList.nextTree());

                                adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 4 :
                            // CivlCParser.g:1025:9: STAR RSQUARE
                            {
                            STAR273=(Token)match(input,STAR,FOLLOW_STAR_in_directAbstractDeclaratorSuffix5355); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STAR.add(STAR273);


                            RSQUARE274=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5357); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE274);


                            // AST REWRITE
                            // elements: STAR
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1026:9: -> ^( ARRAY_SUFFIX ABSENT ABSENT STAR )
                            {
                                // CivlCParser.g:1026:12: ^( ARRAY_SUFFIX ABSENT ABSENT STAR )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(ARRAY_SUFFIX, "ARRAY_SUFFIX")
                                , root_1);

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_1, 
                                stream_STAR.nextNode()
                                );

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // CivlCParser.g:1028:7: LPAREN ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX parameterTypeList ) | RPAREN -> ^( FUNCTION_SUFFIX ABSENT ) )
                    {
                    LPAREN275=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_directAbstractDeclaratorSuffix5393); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN275);


                    // CivlCParser.g:1029:7: ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX parameterTypeList ) | RPAREN -> ^( FUNCTION_SUFFIX ABSENT ) )
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==ALIGNAS||(LA72_0 >= ATOMIC && LA72_0 <= AUTO)||LA72_0==BOOL||LA72_0==CHAR||(LA72_0 >= COMPLEX && LA72_0 <= CONST)||LA72_0==DOUBLE||LA72_0==ENUM||LA72_0==EXTERN||LA72_0==FLOAT||LA72_0==IDENTIFIER||(LA72_0 >= INLINE && LA72_0 <= INT)||LA72_0==LONG||LA72_0==NORETURN||LA72_0==OUTPUT||LA72_0==PROC||(LA72_0 >= REGISTER && LA72_0 <= RESTRICT)||(LA72_0 >= SHORT && LA72_0 <= SIGNED)||LA72_0==STATIC||LA72_0==STRUCT||LA72_0==THREADLOCAL||(LA72_0 >= TYPEDEF && LA72_0 <= UNSIGNED)||(LA72_0 >= VOID && LA72_0 <= VOLATILE)) ) {
                        alt72=1;
                    }
                    else if ( (LA72_0==RPAREN) ) {
                        alt72=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 72, 0, input);

                        throw nvae;

                    }
                    switch (alt72) {
                        case 1 :
                            // CivlCParser.g:1029:9: parameterTypeList RPAREN
                            {
                            pushFollow(FOLLOW_parameterTypeList_in_directAbstractDeclaratorSuffix5403);
                            parameterTypeList276=parameterTypeList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_parameterTypeList.add(parameterTypeList276.getTree());

                            RPAREN277=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directAbstractDeclaratorSuffix5405); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN277);


                            // AST REWRITE
                            // elements: parameterTypeList
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1030:9: -> ^( FUNCTION_SUFFIX parameterTypeList )
                            {
                                // CivlCParser.g:1030:12: ^( FUNCTION_SUFFIX parameterTypeList )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(FUNCTION_SUFFIX, "FUNCTION_SUFFIX")
                                , root_1);

                                adaptor.addChild(root_1, stream_parameterTypeList.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:1031:9: RPAREN
                            {
                            RPAREN278=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directAbstractDeclaratorSuffix5431); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN278);


                            // AST REWRITE
                            // elements: 
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1032:9: -> ^( FUNCTION_SUFFIX ABSENT )
                            {
                                // CivlCParser.g:1032:12: ^( FUNCTION_SUFFIX ABSENT )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                (Object)adaptor.create(FUNCTION_SUFFIX, "FUNCTION_SUFFIX")
                                , root_1);

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "directAbstractDeclaratorSuffix"


    public static class initializer_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "initializer"
    // CivlCParser.g:1037:1: initializer : ( assignmentExpression -> ^( SCALAR_INITIALIZER assignmentExpression ) | LCURLY initializerList ( RCURLY | COMMA RCURLY ) -> initializerList );
    public final CivlCParser.initializer_return initializer() throws RecognitionException {
        CivlCParser.initializer_return retval = new CivlCParser.initializer_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LCURLY280=null;
        Token RCURLY282=null;
        Token COMMA283=null;
        Token RCURLY284=null;
        CivlCParser.assignmentExpression_return assignmentExpression279 =null;

        CivlCParser.initializerList_return initializerList281 =null;


        Object LCURLY280_tree=null;
        Object RCURLY282_tree=null;
        Object COMMA283_tree=null;
        Object RCURLY284_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        RewriteRuleSubtreeStream stream_initializerList=new RewriteRuleSubtreeStream(adaptor,"rule initializerList");
        try {
            // CivlCParser.g:1038:5: ( assignmentExpression -> ^( SCALAR_INITIALIZER assignmentExpression ) | LCURLY initializerList ( RCURLY | COMMA RCURLY ) -> initializerList )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( ((LA75_0 >= ALIGNOF && LA75_0 <= AMPERSAND)||LA75_0==CHARACTER_CONSTANT||LA75_0==FLOATING_CONSTANT||LA75_0==GENERIC||LA75_0==IDENTIFIER||LA75_0==INTEGER_CONSTANT||LA75_0==LPAREN||LA75_0==MINUSMINUS||LA75_0==NOT||LA75_0==PLUS||LA75_0==PLUSPLUS||(LA75_0 >= SIZEOF && LA75_0 <= STAR)||LA75_0==STRING_LITERAL||LA75_0==SUB||LA75_0==TILDE) ) {
                alt75=1;
            }
            else if ( (LA75_0==LCURLY) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;

            }
            switch (alt75) {
                case 1 :
                    // CivlCParser.g:1038:7: assignmentExpression
                    {
                    pushFollow(FOLLOW_assignmentExpression_in_initializer5474);
                    assignmentExpression279=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression279.getTree());

                    // AST REWRITE
                    // elements: assignmentExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1038:28: -> ^( SCALAR_INITIALIZER assignmentExpression )
                    {
                        // CivlCParser.g:1038:31: ^( SCALAR_INITIALIZER assignmentExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(SCALAR_INITIALIZER, "SCALAR_INITIALIZER")
                        , root_1);

                        adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1039:7: LCURLY initializerList ( RCURLY | COMMA RCURLY )
                    {
                    LCURLY280=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_initializer5490); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY280);


                    pushFollow(FOLLOW_initializerList_in_initializer5492);
                    initializerList281=initializerList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_initializerList.add(initializerList281.getTree());

                    // CivlCParser.g:1040:9: ( RCURLY | COMMA RCURLY )
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==RCURLY) ) {
                        alt74=1;
                    }
                    else if ( (LA74_0==COMMA) ) {
                        alt74=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 74, 0, input);

                        throw nvae;

                    }
                    switch (alt74) {
                        case 1 :
                            // CivlCParser.g:1040:13: RCURLY
                            {
                            RCURLY282=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_initializer5506); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY282);


                            }
                            break;
                        case 2 :
                            // CivlCParser.g:1041:13: COMMA RCURLY
                            {
                            COMMA283=(Token)match(input,COMMA,FOLLOW_COMMA_in_initializer5520); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA283);


                            RCURLY284=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_initializer5522); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY284);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: initializerList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1043:7: -> initializerList
                    {
                        adaptor.addChild(root_0, stream_initializerList.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "initializer"


    public static class initializerList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "initializerList"
    // CivlCParser.g:1047:1: initializerList : designatedInitializer ( COMMA designatedInitializer )* -> ^( INITIALIZER_LIST ( designatedInitializer )+ ) ;
    public final CivlCParser.initializerList_return initializerList() throws RecognitionException {
        CivlCParser.initializerList_return retval = new CivlCParser.initializerList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA286=null;
        CivlCParser.designatedInitializer_return designatedInitializer285 =null;

        CivlCParser.designatedInitializer_return designatedInitializer287 =null;


        Object COMMA286_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_designatedInitializer=new RewriteRuleSubtreeStream(adaptor,"rule designatedInitializer");
        try {
            // CivlCParser.g:1048:5: ( designatedInitializer ( COMMA designatedInitializer )* -> ^( INITIALIZER_LIST ( designatedInitializer )+ ) )
            // CivlCParser.g:1048:7: designatedInitializer ( COMMA designatedInitializer )*
            {
            pushFollow(FOLLOW_designatedInitializer_in_initializerList5561);
            designatedInitializer285=designatedInitializer();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_designatedInitializer.add(designatedInitializer285.getTree());

            // CivlCParser.g:1048:29: ( COMMA designatedInitializer )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==COMMA) ) {
                    int LA76_2 = input.LA(2);

                    if ( ((LA76_2 >= ALIGNOF && LA76_2 <= AMPERSAND)||LA76_2==CHARACTER_CONSTANT||LA76_2==DOT||LA76_2==FLOATING_CONSTANT||LA76_2==GENERIC||LA76_2==IDENTIFIER||LA76_2==INTEGER_CONSTANT||LA76_2==LCURLY||(LA76_2 >= LPAREN && LA76_2 <= LSQUARE)||LA76_2==MINUSMINUS||LA76_2==NOT||LA76_2==PLUS||LA76_2==PLUSPLUS||(LA76_2 >= SIZEOF && LA76_2 <= STAR)||LA76_2==STRING_LITERAL||LA76_2==SUB||LA76_2==TILDE) ) {
                        alt76=1;
                    }


                }


                switch (alt76) {
            	case 1 :
            	    // CivlCParser.g:1048:30: COMMA designatedInitializer
            	    {
            	    COMMA286=(Token)match(input,COMMA,FOLLOW_COMMA_in_initializerList5564); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA286);


            	    pushFollow(FOLLOW_designatedInitializer_in_initializerList5566);
            	    designatedInitializer287=designatedInitializer();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_designatedInitializer.add(designatedInitializer287.getTree());

            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);


            // AST REWRITE
            // elements: designatedInitializer
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1049:7: -> ^( INITIALIZER_LIST ( designatedInitializer )+ )
            {
                // CivlCParser.g:1049:10: ^( INITIALIZER_LIST ( designatedInitializer )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(INITIALIZER_LIST, "INITIALIZER_LIST")
                , root_1);

                if ( !(stream_designatedInitializer.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_designatedInitializer.hasNext() ) {
                    adaptor.addChild(root_1, stream_designatedInitializer.nextTree());

                }
                stream_designatedInitializer.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "initializerList"


    public static class designatedInitializer_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "designatedInitializer"
    // CivlCParser.g:1052:1: designatedInitializer : ( initializer -> ^( DESIGNATED_INITIALIZER ABSENT initializer ) | designation initializer -> ^( DESIGNATED_INITIALIZER designation initializer ) );
    public final CivlCParser.designatedInitializer_return designatedInitializer() throws RecognitionException {
        CivlCParser.designatedInitializer_return retval = new CivlCParser.designatedInitializer_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.initializer_return initializer288 =null;

        CivlCParser.designation_return designation289 =null;

        CivlCParser.initializer_return initializer290 =null;


        RewriteRuleSubtreeStream stream_designation=new RewriteRuleSubtreeStream(adaptor,"rule designation");
        RewriteRuleSubtreeStream stream_initializer=new RewriteRuleSubtreeStream(adaptor,"rule initializer");
        try {
            // CivlCParser.g:1053:2: ( initializer -> ^( DESIGNATED_INITIALIZER ABSENT initializer ) | designation initializer -> ^( DESIGNATED_INITIALIZER designation initializer ) )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( ((LA77_0 >= ALIGNOF && LA77_0 <= AMPERSAND)||LA77_0==CHARACTER_CONSTANT||LA77_0==FLOATING_CONSTANT||LA77_0==GENERIC||LA77_0==IDENTIFIER||LA77_0==INTEGER_CONSTANT||LA77_0==LCURLY||LA77_0==LPAREN||LA77_0==MINUSMINUS||LA77_0==NOT||LA77_0==PLUS||LA77_0==PLUSPLUS||(LA77_0 >= SIZEOF && LA77_0 <= STAR)||LA77_0==STRING_LITERAL||LA77_0==SUB||LA77_0==TILDE) ) {
                alt77=1;
            }
            else if ( (LA77_0==DOT||LA77_0==LSQUARE) ) {
                alt77=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;

            }
            switch (alt77) {
                case 1 :
                    // CivlCParser.g:1053:4: initializer
                    {
                    pushFollow(FOLLOW_initializer_in_designatedInitializer5597);
                    initializer288=initializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_initializer.add(initializer288.getTree());

                    // AST REWRITE
                    // elements: initializer
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1054:4: -> ^( DESIGNATED_INITIALIZER ABSENT initializer )
                    {
                        // CivlCParser.g:1054:7: ^( DESIGNATED_INITIALIZER ABSENT initializer )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DESIGNATED_INITIALIZER, "DESIGNATED_INITIALIZER")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, stream_initializer.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1055:4: designation initializer
                    {
                    pushFollow(FOLLOW_designation_in_designatedInitializer5615);
                    designation289=designation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_designation.add(designation289.getTree());

                    pushFollow(FOLLOW_initializer_in_designatedInitializer5617);
                    initializer290=initializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_initializer.add(initializer290.getTree());

                    // AST REWRITE
                    // elements: designation, initializer
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1056:4: -> ^( DESIGNATED_INITIALIZER designation initializer )
                    {
                        // CivlCParser.g:1056:7: ^( DESIGNATED_INITIALIZER designation initializer )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DESIGNATED_INITIALIZER, "DESIGNATED_INITIALIZER")
                        , root_1);

                        adaptor.addChild(root_1, stream_designation.nextTree());

                        adaptor.addChild(root_1, stream_initializer.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "designatedInitializer"


    public static class designation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "designation"
    // CivlCParser.g:1060:1: designation : designatorList ASSIGN -> ^( DESIGNATION designatorList ) ;
    public final CivlCParser.designation_return designation() throws RecognitionException {
        CivlCParser.designation_return retval = new CivlCParser.designation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ASSIGN292=null;
        CivlCParser.designatorList_return designatorList291 =null;


        Object ASSIGN292_tree=null;
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_designatorList=new RewriteRuleSubtreeStream(adaptor,"rule designatorList");
        try {
            // CivlCParser.g:1061:5: ( designatorList ASSIGN -> ^( DESIGNATION designatorList ) )
            // CivlCParser.g:1061:7: designatorList ASSIGN
            {
            pushFollow(FOLLOW_designatorList_in_designation5646);
            designatorList291=designatorList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_designatorList.add(designatorList291.getTree());

            ASSIGN292=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_designation5648); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN292);


            // AST REWRITE
            // elements: designatorList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1061:29: -> ^( DESIGNATION designatorList )
            {
                // CivlCParser.g:1061:32: ^( DESIGNATION designatorList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DESIGNATION, "DESIGNATION")
                , root_1);

                adaptor.addChild(root_1, stream_designatorList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "designation"


    public static class designatorList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "designatorList"
    // CivlCParser.g:1065:1: designatorList : ( designator )+ ;
    public final CivlCParser.designatorList_return designatorList() throws RecognitionException {
        CivlCParser.designatorList_return retval = new CivlCParser.designatorList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.designator_return designator293 =null;



        try {
            // CivlCParser.g:1066:5: ( ( designator )+ )
            // CivlCParser.g:1066:7: ( designator )+
            {
            root_0 = (Object)adaptor.nil();


            // CivlCParser.g:1066:7: ( designator )+
            int cnt78=0;
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==DOT||LA78_0==LSQUARE) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // CivlCParser.g:1066:7: designator
            	    {
            	    pushFollow(FOLLOW_designator_in_designatorList5675);
            	    designator293=designator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, designator293.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt78 >= 1 ) break loop78;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(78, input);
                        throw eee;
                }
                cnt78++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "designatorList"


    public static class designator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "designator"
    // CivlCParser.g:1070:1: designator : ( LSQUARE constantExpression RSQUARE -> ^( ARRAY_ELEMENT_DESIGNATOR constantExpression ) | DOT IDENTIFIER -> ^( FIELD_DESIGNATOR IDENTIFIER ) );
    public final CivlCParser.designator_return designator() throws RecognitionException {
        CivlCParser.designator_return retval = new CivlCParser.designator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LSQUARE294=null;
        Token RSQUARE296=null;
        Token DOT297=null;
        Token IDENTIFIER298=null;
        CivlCParser.constantExpression_return constantExpression295 =null;


        Object LSQUARE294_tree=null;
        Object RSQUARE296_tree=null;
        Object DOT297_tree=null;
        Object IDENTIFIER298_tree=null;
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
        RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleSubtreeStream stream_constantExpression=new RewriteRuleSubtreeStream(adaptor,"rule constantExpression");
        try {
            // CivlCParser.g:1071:5: ( LSQUARE constantExpression RSQUARE -> ^( ARRAY_ELEMENT_DESIGNATOR constantExpression ) | DOT IDENTIFIER -> ^( FIELD_DESIGNATOR IDENTIFIER ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==LSQUARE) ) {
                alt79=1;
            }
            else if ( (LA79_0==DOT) ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;

            }
            switch (alt79) {
                case 1 :
                    // CivlCParser.g:1071:7: LSQUARE constantExpression RSQUARE
                    {
                    LSQUARE294=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_designator5695); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE294);


                    pushFollow(FOLLOW_constantExpression_in_designator5697);
                    constantExpression295=constantExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression295.getTree());

                    RSQUARE296=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_designator5699); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE296);


                    // AST REWRITE
                    // elements: constantExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1072:7: -> ^( ARRAY_ELEMENT_DESIGNATOR constantExpression )
                    {
                        // CivlCParser.g:1072:10: ^( ARRAY_ELEMENT_DESIGNATOR constantExpression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARRAY_ELEMENT_DESIGNATOR, "ARRAY_ELEMENT_DESIGNATOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_constantExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1073:7: DOT IDENTIFIER
                    {
                    DOT297=(Token)match(input,DOT,FOLLOW_DOT_in_designator5721); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT.add(DOT297);


                    IDENTIFIER298=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_designator5723); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER298);


                    // AST REWRITE
                    // elements: IDENTIFIER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1074:7: -> ^( FIELD_DESIGNATOR IDENTIFIER )
                    {
                        // CivlCParser.g:1074:10: ^( FIELD_DESIGNATOR IDENTIFIER )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FIELD_DESIGNATOR, "FIELD_DESIGNATOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "designator"


    public static class staticAssertDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "staticAssertDeclaration"
    // CivlCParser.g:1078:1: staticAssertDeclaration : STATICASSERT LPAREN constantExpression COMMA STRING_LITERAL RPAREN SEMI -> ^( STATICASSERT constantExpression STRING_LITERAL ) ;
    public final CivlCParser.staticAssertDeclaration_return staticAssertDeclaration() throws RecognitionException {
        CivlCParser.staticAssertDeclaration_return retval = new CivlCParser.staticAssertDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token STATICASSERT299=null;
        Token LPAREN300=null;
        Token COMMA302=null;
        Token STRING_LITERAL303=null;
        Token RPAREN304=null;
        Token SEMI305=null;
        CivlCParser.constantExpression_return constantExpression301 =null;


        Object STATICASSERT299_tree=null;
        Object LPAREN300_tree=null;
        Object COMMA302_tree=null;
        Object STRING_LITERAL303_tree=null;
        Object RPAREN304_tree=null;
        Object SEMI305_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_STATICASSERT=new RewriteRuleTokenStream(adaptor,"token STATICASSERT");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_constantExpression=new RewriteRuleSubtreeStream(adaptor,"rule constantExpression");
        try {
            // CivlCParser.g:1079:5: ( STATICASSERT LPAREN constantExpression COMMA STRING_LITERAL RPAREN SEMI -> ^( STATICASSERT constantExpression STRING_LITERAL ) )
            // CivlCParser.g:1079:7: STATICASSERT LPAREN constantExpression COMMA STRING_LITERAL RPAREN SEMI
            {
            STATICASSERT299=(Token)match(input,STATICASSERT,FOLLOW_STATICASSERT_in_staticAssertDeclaration5756); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STATICASSERT.add(STATICASSERT299);


            LPAREN300=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_staticAssertDeclaration5758); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN300);


            pushFollow(FOLLOW_constantExpression_in_staticAssertDeclaration5760);
            constantExpression301=constantExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression301.getTree());

            COMMA302=(Token)match(input,COMMA,FOLLOW_COMMA_in_staticAssertDeclaration5762); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COMMA.add(COMMA302);


            STRING_LITERAL303=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_staticAssertDeclaration5764); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL303);


            RPAREN304=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_staticAssertDeclaration5772); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN304);


            SEMI305=(Token)match(input,SEMI,FOLLOW_SEMI_in_staticAssertDeclaration5774); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI305);


            // AST REWRITE
            // elements: STATICASSERT, STRING_LITERAL, constantExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1081:7: -> ^( STATICASSERT constantExpression STRING_LITERAL )
            {
                // CivlCParser.g:1081:10: ^( STATICASSERT constantExpression STRING_LITERAL )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_STATICASSERT.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_constantExpression.nextTree());

                adaptor.addChild(root_1, 
                stream_STRING_LITERAL.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "staticAssertDeclaration"


    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // CivlCParser.g:1088:1: statement : ( labeledStatement | compoundStatement | expressionStatement | selectionStatement | iterationStatement | jumpStatement | pragma | assertStatement | assumeStatement | waitStatement | whenStatement | chooseStatement );
    public final CivlCParser.statement_return statement() throws RecognitionException {
        CivlCParser.statement_return retval = new CivlCParser.statement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.labeledStatement_return labeledStatement306 =null;

        CivlCParser.compoundStatement_return compoundStatement307 =null;

        CivlCParser.expressionStatement_return expressionStatement308 =null;

        CivlCParser.selectionStatement_return selectionStatement309 =null;

        CivlCParser.iterationStatement_return iterationStatement310 =null;

        CivlCParser.jumpStatement_return jumpStatement311 =null;

        CivlCParser.pragma_return pragma312 =null;

        CivlCParser.assertStatement_return assertStatement313 =null;

        CivlCParser.assumeStatement_return assumeStatement314 =null;

        CivlCParser.waitStatement_return waitStatement315 =null;

        CivlCParser.whenStatement_return whenStatement316 =null;

        CivlCParser.chooseStatement_return chooseStatement317 =null;



        try {
            // CivlCParser.g:1089:5: ( labeledStatement | compoundStatement | expressionStatement | selectionStatement | iterationStatement | jumpStatement | pragma | assertStatement | assumeStatement | waitStatement | whenStatement | chooseStatement )
            int alt80=12;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA80_1 = input.LA(2);

                if ( (LA80_1==COLON) ) {
                    alt80=1;
                }
                else if ( ((LA80_1 >= AMPERSAND && LA80_1 <= ARROW)||LA80_1==ASSIGN||LA80_1==AT||(LA80_1 >= BITANDEQ && LA80_1 <= BITXOREQ)||LA80_1==COMMA||(LA80_1 >= DIV && LA80_1 <= DIVEQ)||LA80_1==DOT||LA80_1==EQUALS||(LA80_1 >= GT && LA80_1 <= GTE)||(LA80_1 >= LPAREN && LA80_1 <= LTE)||(LA80_1 >= MINUSMINUS && LA80_1 <= NEQ)||LA80_1==OR||(LA80_1 >= PLUS && LA80_1 <= PLUSPLUS)||LA80_1==QMARK||(LA80_1 >= SEMI && LA80_1 <= SHIFTRIGHTEQ)||(LA80_1 >= STAR && LA80_1 <= STAREQ)||(LA80_1 >= SUB && LA80_1 <= SUBEQ)) ) {
                    alt80=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 80, 1, input);

                    throw nvae;

                }
                }
                break;
            case CASE:
            case DEFAULT:
                {
                alt80=1;
                }
                break;
            case LCURLY:
                {
                alt80=2;
                }
                break;
            case ALIGNOF:
            case AMPERSAND:
            case CHARACTER_CONSTANT:
            case COLLECTIVE:
            case FLOATING_CONSTANT:
            case GENERIC:
            case INTEGER_CONSTANT:
            case LPAREN:
            case MINUSMINUS:
            case NOT:
            case PLUS:
            case PLUSPLUS:
            case SEMI:
            case SIZEOF:
            case SPAWN:
            case STAR:
            case STRING_LITERAL:
            case SUB:
            case TILDE:
                {
                alt80=3;
                }
                break;
            case IF:
            case SWITCH:
                {
                alt80=4;
                }
                break;
            case DO:
            case FOR:
            case WHILE:
                {
                alt80=5;
                }
                break;
            case BREAK:
            case CONTINUE:
            case GOTO:
            case RETURN:
                {
                alt80=6;
                }
                break;
            case PRAGMA:
                {
                alt80=7;
                }
                break;
            case ASSERT:
                {
                alt80=8;
                }
                break;
            case ASSUME:
                {
                alt80=9;
                }
                break;
            case WAIT:
                {
                alt80=10;
                }
                break;
            case WHEN:
                {
                alt80=11;
                }
                break;
            case CHOOSE:
                {
                alt80=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;

            }

            switch (alt80) {
                case 1 :
                    // CivlCParser.g:1089:7: labeledStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_labeledStatement_in_statement5813);
                    labeledStatement306=labeledStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, labeledStatement306.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1090:7: compoundStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_compoundStatement_in_statement5821);
                    compoundStatement307=compoundStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compoundStatement307.getTree());

                    }
                    break;
                case 3 :
                    // CivlCParser.g:1091:7: expressionStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionStatement_in_statement5829);
                    expressionStatement308=expressionStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionStatement308.getTree());

                    }
                    break;
                case 4 :
                    // CivlCParser.g:1092:7: selectionStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_selectionStatement_in_statement5837);
                    selectionStatement309=selectionStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selectionStatement309.getTree());

                    }
                    break;
                case 5 :
                    // CivlCParser.g:1093:7: iterationStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_iterationStatement_in_statement5845);
                    iterationStatement310=iterationStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, iterationStatement310.getTree());

                    }
                    break;
                case 6 :
                    // CivlCParser.g:1094:7: jumpStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_jumpStatement_in_statement5853);
                    jumpStatement311=jumpStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, jumpStatement311.getTree());

                    }
                    break;
                case 7 :
                    // CivlCParser.g:1095:7: pragma
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pragma_in_statement5861);
                    pragma312=pragma();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pragma312.getTree());

                    }
                    break;
                case 8 :
                    // CivlCParser.g:1096:7: assertStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assertStatement_in_statement5869);
                    assertStatement313=assertStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assertStatement313.getTree());

                    }
                    break;
                case 9 :
                    // CivlCParser.g:1097:7: assumeStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assumeStatement_in_statement5877);
                    assumeStatement314=assumeStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assumeStatement314.getTree());

                    }
                    break;
                case 10 :
                    // CivlCParser.g:1098:7: waitStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_waitStatement_in_statement5885);
                    waitStatement315=waitStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, waitStatement315.getTree());

                    }
                    break;
                case 11 :
                    // CivlCParser.g:1099:7: whenStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_whenStatement_in_statement5893);
                    whenStatement316=whenStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whenStatement316.getTree());

                    }
                    break;
                case 12 :
                    // CivlCParser.g:1100:7: chooseStatement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_chooseStatement_in_statement5901);
                    chooseStatement317=chooseStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, chooseStatement317.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class statementWithScope_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statementWithScope"
    // CivlCParser.g:1103:1: statementWithScope : statement ;
    public final CivlCParser.statementWithScope_return statementWithScope() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CivlCParser.statementWithScope_return retval = new CivlCParser.statementWithScope_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.statement_return statement318 =null;




        	((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
        	((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
                ((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = false;

        try {
            // CivlCParser.g:1110:2: ( statement )
            // CivlCParser.g:1110:4: statement
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_statement_in_statementWithScope5925);
            statement318=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement318.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "statementWithScope"


    public static class labeledStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "labeledStatement"
    // CivlCParser.g:1129:1: labeledStatement : ( IDENTIFIER COLON statement -> ^( IDENTIFIER_LABELED_STATEMENT IDENTIFIER statement ) | CASE constantExpression COLON statement -> ^( CASE_LABELED_STATEMENT CASE constantExpression statement ) | DEFAULT COLON statement -> ^( DEFAULT_LABELED_STATEMENT DEFAULT statement ) );
    public final CivlCParser.labeledStatement_return labeledStatement() throws RecognitionException {
        CivlCParser.labeledStatement_return retval = new CivlCParser.labeledStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER319=null;
        Token COLON320=null;
        Token CASE322=null;
        Token COLON324=null;
        Token DEFAULT326=null;
        Token COLON327=null;
        CivlCParser.statement_return statement321 =null;

        CivlCParser.constantExpression_return constantExpression323 =null;

        CivlCParser.statement_return statement325 =null;

        CivlCParser.statement_return statement328 =null;


        Object IDENTIFIER319_tree=null;
        Object COLON320_tree=null;
        Object CASE322_tree=null;
        Object COLON324_tree=null;
        Object DEFAULT326_tree=null;
        Object COLON327_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_DEFAULT=new RewriteRuleTokenStream(adaptor,"token DEFAULT");
        RewriteRuleTokenStream stream_CASE=new RewriteRuleTokenStream(adaptor,"token CASE");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        RewriteRuleSubtreeStream stream_constantExpression=new RewriteRuleSubtreeStream(adaptor,"rule constantExpression");
        try {
            // CivlCParser.g:1130:5: ( IDENTIFIER COLON statement -> ^( IDENTIFIER_LABELED_STATEMENT IDENTIFIER statement ) | CASE constantExpression COLON statement -> ^( CASE_LABELED_STATEMENT CASE constantExpression statement ) | DEFAULT COLON statement -> ^( DEFAULT_LABELED_STATEMENT DEFAULT statement ) )
            int alt81=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt81=1;
                }
                break;
            case CASE:
                {
                alt81=2;
                }
                break;
            case DEFAULT:
                {
                alt81=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;

            }

            switch (alt81) {
                case 1 :
                    // CivlCParser.g:1130:7: IDENTIFIER COLON statement
                    {
                    IDENTIFIER319=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_labeledStatement5941); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER319);


                    COLON320=(Token)match(input,COLON,FOLLOW_COLON_in_labeledStatement5943); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON320);


                    pushFollow(FOLLOW_statement_in_labeledStatement5945);
                    statement321=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement321.getTree());

                    // AST REWRITE
                    // elements: IDENTIFIER, statement
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1131:7: -> ^( IDENTIFIER_LABELED_STATEMENT IDENTIFIER statement )
                    {
                        // CivlCParser.g:1131:10: ^( IDENTIFIER_LABELED_STATEMENT IDENTIFIER statement )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(IDENTIFIER_LABELED_STATEMENT, "IDENTIFIER_LABELED_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, stream_statement.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1132:7: CASE constantExpression COLON statement
                    {
                    CASE322=(Token)match(input,CASE,FOLLOW_CASE_in_labeledStatement5969); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CASE.add(CASE322);


                    pushFollow(FOLLOW_constantExpression_in_labeledStatement5971);
                    constantExpression323=constantExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantExpression.add(constantExpression323.getTree());

                    COLON324=(Token)match(input,COLON,FOLLOW_COLON_in_labeledStatement5973); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON324);


                    pushFollow(FOLLOW_statement_in_labeledStatement5975);
                    statement325=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement325.getTree());

                    // AST REWRITE
                    // elements: statement, constantExpression, CASE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1133:7: -> ^( CASE_LABELED_STATEMENT CASE constantExpression statement )
                    {
                        // CivlCParser.g:1133:10: ^( CASE_LABELED_STATEMENT CASE constantExpression statement )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(CASE_LABELED_STATEMENT, "CASE_LABELED_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_CASE.nextNode()
                        );

                        adaptor.addChild(root_1, stream_constantExpression.nextTree());

                        adaptor.addChild(root_1, stream_statement.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:1134:7: DEFAULT COLON statement
                    {
                    DEFAULT326=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_labeledStatement6001); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DEFAULT.add(DEFAULT326);


                    COLON327=(Token)match(input,COLON,FOLLOW_COLON_in_labeledStatement6003); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON327);


                    pushFollow(FOLLOW_statement_in_labeledStatement6005);
                    statement328=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement328.getTree());

                    // AST REWRITE
                    // elements: DEFAULT, statement
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1135:7: -> ^( DEFAULT_LABELED_STATEMENT DEFAULT statement )
                    {
                        // CivlCParser.g:1135:10: ^( DEFAULT_LABELED_STATEMENT DEFAULT statement )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(DEFAULT_LABELED_STATEMENT, "DEFAULT_LABELED_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_DEFAULT.nextNode()
                        );

                        adaptor.addChild(root_1, stream_statement.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "labeledStatement"


    public static class compoundStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "compoundStatement"
    // CivlCParser.g:1144:1: compoundStatement : LCURLY ( RCURLY -> ^( COMPOUND_STATEMENT LCURLY ABSENT RCURLY ) | blockItemList RCURLY -> ^( COMPOUND_STATEMENT LCURLY blockItemList RCURLY ) ) ;
    public final CivlCParser.compoundStatement_return compoundStatement() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CivlCParser.compoundStatement_return retval = new CivlCParser.compoundStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LCURLY329=null;
        Token RCURLY330=null;
        Token RCURLY332=null;
        CivlCParser.blockItemList_return blockItemList331 =null;


        Object LCURLY329_tree=null;
        Object RCURLY330_tree=null;
        Object RCURLY332_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_blockItemList=new RewriteRuleSubtreeStream(adaptor,"rule blockItemList");

        	((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
        	((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
                ((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = false;

        try {
            // CivlCParser.g:1151:5: ( LCURLY ( RCURLY -> ^( COMPOUND_STATEMENT LCURLY ABSENT RCURLY ) | blockItemList RCURLY -> ^( COMPOUND_STATEMENT LCURLY blockItemList RCURLY ) ) )
            // CivlCParser.g:1151:7: LCURLY ( RCURLY -> ^( COMPOUND_STATEMENT LCURLY ABSENT RCURLY ) | blockItemList RCURLY -> ^( COMPOUND_STATEMENT LCURLY blockItemList RCURLY ) )
            {
            LCURLY329=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_compoundStatement6050); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY329);


            // CivlCParser.g:1152:7: ( RCURLY -> ^( COMPOUND_STATEMENT LCURLY ABSENT RCURLY ) | blockItemList RCURLY -> ^( COMPOUND_STATEMENT LCURLY blockItemList RCURLY ) )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==RCURLY) ) {
                alt82=1;
            }
            else if ( ((LA82_0 >= ALIGNAS && LA82_0 <= AMPERSAND)||LA82_0==ASSERT||LA82_0==ASSUME||(LA82_0 >= ATOMIC && LA82_0 <= AUTO)||(LA82_0 >= BOOL && LA82_0 <= BREAK)||LA82_0==CASE||(LA82_0 >= CHAR && LA82_0 <= COLLECTIVE)||(LA82_0 >= COMPLEX && LA82_0 <= DEFAULT)||LA82_0==DO||LA82_0==DOUBLE||LA82_0==ENUM||LA82_0==EXTERN||(LA82_0 >= FLOAT && LA82_0 <= FOR)||(LA82_0 >= GENERIC && LA82_0 <= GOTO)||(LA82_0 >= IDENTIFIER && LA82_0 <= IF)||(LA82_0 >= INLINE && LA82_0 <= INTEGER_CONSTANT)||(LA82_0 >= LCURLY && LA82_0 <= LPAREN)||LA82_0==MINUSMINUS||(LA82_0 >= NORETURN && LA82_0 <= NOT)||LA82_0==OUTPUT||LA82_0==PLUS||LA82_0==PLUSPLUS||(LA82_0 >= PRAGMA && LA82_0 <= PROC)||(LA82_0 >= REGISTER && LA82_0 <= RETURN)||LA82_0==SEMI||(LA82_0 >= SHORT && LA82_0 <= STAR)||(LA82_0 >= STATIC && LA82_0 <= SUB)||(LA82_0 >= SWITCH && LA82_0 <= UNSIGNED)||(LA82_0 >= VOID && LA82_0 <= WHILE)) ) {
                alt82=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;

            }
            switch (alt82) {
                case 1 :
                    // CivlCParser.g:1152:9: RCURLY
                    {
                    RCURLY330=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_compoundStatement6060); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY330);


                    // AST REWRITE
                    // elements: RCURLY, LCURLY
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1153:9: -> ^( COMPOUND_STATEMENT LCURLY ABSENT RCURLY )
                    {
                        // CivlCParser.g:1153:12: ^( COMPOUND_STATEMENT LCURLY ABSENT RCURLY )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(COMPOUND_STATEMENT, "COMPOUND_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LCURLY.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, 
                        stream_RCURLY.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1154:9: blockItemList RCURLY
                    {
                    pushFollow(FOLLOW_blockItemList_in_compoundStatement6090);
                    blockItemList331=blockItemList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_blockItemList.add(blockItemList331.getTree());

                    RCURLY332=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_compoundStatement6092); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY332);


                    // AST REWRITE
                    // elements: RCURLY, blockItemList, LCURLY
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1155:9: -> ^( COMPOUND_STATEMENT LCURLY blockItemList RCURLY )
                    {
                        // CivlCParser.g:1155:12: ^( COMPOUND_STATEMENT LCURLY blockItemList RCURLY )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(COMPOUND_STATEMENT, "COMPOUND_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_LCURLY.nextNode()
                        );

                        adaptor.addChild(root_1, stream_blockItemList.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RCURLY.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "compoundStatement"


    public static class blockItemList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "blockItemList"
    // CivlCParser.g:1160:1: blockItemList : ( blockItem )+ -> ^( BLOCK_ITEM_LIST ( blockItem )+ ) ;
    public final CivlCParser.blockItemList_return blockItemList() throws RecognitionException {
        CivlCParser.blockItemList_return retval = new CivlCParser.blockItemList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.blockItem_return blockItem333 =null;


        RewriteRuleSubtreeStream stream_blockItem=new RewriteRuleSubtreeStream(adaptor,"rule blockItem");
        try {
            // CivlCParser.g:1161:5: ( ( blockItem )+ -> ^( BLOCK_ITEM_LIST ( blockItem )+ ) )
            // CivlCParser.g:1161:7: ( blockItem )+
            {
            // CivlCParser.g:1161:7: ( blockItem )+
            int cnt83=0;
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( ((LA83_0 >= ALIGNAS && LA83_0 <= AMPERSAND)||LA83_0==ASSERT||LA83_0==ASSUME||(LA83_0 >= ATOMIC && LA83_0 <= AUTO)||(LA83_0 >= BOOL && LA83_0 <= BREAK)||LA83_0==CASE||(LA83_0 >= CHAR && LA83_0 <= COLLECTIVE)||(LA83_0 >= COMPLEX && LA83_0 <= DEFAULT)||LA83_0==DO||LA83_0==DOUBLE||LA83_0==ENUM||LA83_0==EXTERN||(LA83_0 >= FLOAT && LA83_0 <= FOR)||(LA83_0 >= GENERIC && LA83_0 <= GOTO)||(LA83_0 >= IDENTIFIER && LA83_0 <= IF)||(LA83_0 >= INLINE && LA83_0 <= INTEGER_CONSTANT)||(LA83_0 >= LCURLY && LA83_0 <= LPAREN)||LA83_0==MINUSMINUS||(LA83_0 >= NORETURN && LA83_0 <= NOT)||LA83_0==OUTPUT||LA83_0==PLUS||LA83_0==PLUSPLUS||(LA83_0 >= PRAGMA && LA83_0 <= PROC)||(LA83_0 >= REGISTER && LA83_0 <= RETURN)||LA83_0==SEMI||(LA83_0 >= SHORT && LA83_0 <= STAR)||(LA83_0 >= STATIC && LA83_0 <= SUB)||(LA83_0 >= SWITCH && LA83_0 <= UNSIGNED)||(LA83_0 >= VOID && LA83_0 <= WHILE)) ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // CivlCParser.g:1161:7: blockItem
            	    {
            	    pushFollow(FOLLOW_blockItem_in_blockItemList6139);
            	    blockItem333=blockItem();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_blockItem.add(blockItem333.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt83 >= 1 ) break loop83;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(83, input);
                        throw eee;
                }
                cnt83++;
            } while (true);


            // AST REWRITE
            // elements: blockItem
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1161:18: -> ^( BLOCK_ITEM_LIST ( blockItem )+ )
            {
                // CivlCParser.g:1161:21: ^( BLOCK_ITEM_LIST ( blockItem )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(BLOCK_ITEM_LIST, "BLOCK_ITEM_LIST")
                , root_1);

                if ( !(stream_blockItem.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_blockItem.hasNext() ) {
                    adaptor.addChild(root_1, stream_blockItem.nextTree());

                }
                stream_blockItem.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "blockItemList"


    public static class blockItem_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "blockItem"
    // CivlCParser.g:1165:1: blockItem : ( ( declarationSpecifiers declarator declarationList_opt LCURLY )=> functionDefinition | declaration | statement );
    public final CivlCParser.blockItem_return blockItem() throws RecognitionException {
        CivlCParser.blockItem_return retval = new CivlCParser.blockItem_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.functionDefinition_return functionDefinition334 =null;

        CivlCParser.declaration_return declaration335 =null;

        CivlCParser.statement_return statement336 =null;



        try {
            // CivlCParser.g:1166:5: ( ( declarationSpecifiers declarator declarationList_opt LCURLY )=> functionDefinition | declaration | statement )
            int alt84=3;
            switch ( input.LA(1) ) {
            case TYPEDEF:
                {
                int LA84_1 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 1, input);

                    throw nvae;

                }
                }
                break;
            case AUTO:
            case EXTERN:
            case REGISTER:
            case STATIC:
            case THREADLOCAL:
                {
                int LA84_2 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 2, input);

                    throw nvae;

                }
                }
                break;
            case VOID:
                {
                int LA84_3 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 3, input);

                    throw nvae;

                }
                }
                break;
            case CHAR:
                {
                int LA84_4 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 4, input);

                    throw nvae;

                }
                }
                break;
            case SHORT:
                {
                int LA84_5 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 5, input);

                    throw nvae;

                }
                }
                break;
            case INT:
                {
                int LA84_6 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 6, input);

                    throw nvae;

                }
                }
                break;
            case LONG:
                {
                int LA84_7 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 7, input);

                    throw nvae;

                }
                }
                break;
            case FLOAT:
                {
                int LA84_8 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 8, input);

                    throw nvae;

                }
                }
                break;
            case DOUBLE:
                {
                int LA84_9 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 9, input);

                    throw nvae;

                }
                }
                break;
            case SIGNED:
                {
                int LA84_10 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 10, input);

                    throw nvae;

                }
                }
                break;
            case UNSIGNED:
                {
                int LA84_11 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 11, input);

                    throw nvae;

                }
                }
                break;
            case BOOL:
                {
                int LA84_12 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 12, input);

                    throw nvae;

                }
                }
                break;
            case COMPLEX:
                {
                int LA84_13 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 13, input);

                    throw nvae;

                }
                }
                break;
            case PROC:
                {
                int LA84_14 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 14, input);

                    throw nvae;

                }
                }
                break;
            case ATOMIC:
                {
                int LA84_15 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 15, input);

                    throw nvae;

                }
                }
                break;
            case STRUCT:
            case UNION:
                {
                int LA84_16 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 16, input);

                    throw nvae;

                }
                }
                break;
            case ENUM:
                {
                int LA84_17 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 17, input);

                    throw nvae;

                }
                }
                break;
            case IDENTIFIER:
                {
                int LA84_18 = input.LA(2);

                if ( ((((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                    alt84=1;
                }
                else if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                    alt84=2;
                }
                else if ( (true) ) {
                    alt84=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 18, input);

                    throw nvae;

                }
                }
                break;
            case CONST:
            case INPUT:
            case OUTPUT:
            case RESTRICT:
            case VOLATILE:
                {
                int LA84_19 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 19, input);

                    throw nvae;

                }
                }
                break;
            case INLINE:
            case NORETURN:
                {
                int LA84_20 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 20, input);

                    throw nvae;

                }
                }
                break;
            case ALIGNAS:
                {
                int LA84_21 = input.LA(2);

                if ( (((synpred7_CivlCParser()&&synpred7_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt84=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 21, input);

                    throw nvae;

                }
                }
                break;
            case STATICASSERT:
                {
                alt84=2;
                }
                break;
            case ALIGNOF:
            case AMPERSAND:
            case ASSERT:
            case ASSUME:
            case BREAK:
            case CASE:
            case CHARACTER_CONSTANT:
            case CHOOSE:
            case COLLECTIVE:
            case CONTINUE:
            case DEFAULT:
            case DO:
            case FLOATING_CONSTANT:
            case FOR:
            case GENERIC:
            case GOTO:
            case IF:
            case INTEGER_CONSTANT:
            case LCURLY:
            case LPAREN:
            case MINUSMINUS:
            case NOT:
            case PLUS:
            case PLUSPLUS:
            case PRAGMA:
            case RETURN:
            case SEMI:
            case SIZEOF:
            case SPAWN:
            case STAR:
            case STRING_LITERAL:
            case SUB:
            case SWITCH:
            case TILDE:
            case WAIT:
            case WHEN:
            case WHILE:
                {
                alt84=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }

            switch (alt84) {
                case 1 :
                    // CivlCParser.g:1166:7: ( declarationSpecifiers declarator declarationList_opt LCURLY )=> functionDefinition
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionDefinition_in_blockItem6185);
                    functionDefinition334=functionDefinition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionDefinition334.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1168:7: declaration
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declaration_in_blockItem6193);
                    declaration335=declaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, declaration335.getTree());

                    }
                    break;
                case 3 :
                    // CivlCParser.g:1169:7: statement
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_statement_in_blockItem6201);
                    statement336=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement336.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "blockItem"


    public static class expressionStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expressionStatement"
    // CivlCParser.g:1177:1: expressionStatement : ( expression SEMI -> ^( EXPRESSION_STATEMENT expression SEMI ) | SEMI -> ^( EXPRESSION_STATEMENT ABSENT SEMI ) );
    public final CivlCParser.expressionStatement_return expressionStatement() throws RecognitionException {
        CivlCParser.expressionStatement_return retval = new CivlCParser.expressionStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMI338=null;
        Token SEMI339=null;
        CivlCParser.expression_return expression337 =null;


        Object SEMI338_tree=null;
        Object SEMI339_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // CivlCParser.g:1178:5: ( expression SEMI -> ^( EXPRESSION_STATEMENT expression SEMI ) | SEMI -> ^( EXPRESSION_STATEMENT ABSENT SEMI ) )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( ((LA85_0 >= ALIGNOF && LA85_0 <= AMPERSAND)||LA85_0==CHARACTER_CONSTANT||LA85_0==COLLECTIVE||LA85_0==FLOATING_CONSTANT||LA85_0==GENERIC||LA85_0==IDENTIFIER||LA85_0==INTEGER_CONSTANT||LA85_0==LPAREN||LA85_0==MINUSMINUS||LA85_0==NOT||LA85_0==PLUS||LA85_0==PLUSPLUS||(LA85_0 >= SIZEOF && LA85_0 <= STAR)||LA85_0==STRING_LITERAL||LA85_0==SUB||LA85_0==TILDE) ) {
                alt85=1;
            }
            else if ( (LA85_0==SEMI) ) {
                alt85=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }
            switch (alt85) {
                case 1 :
                    // CivlCParser.g:1178:7: expression SEMI
                    {
                    pushFollow(FOLLOW_expression_in_expressionStatement6220);
                    expression337=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression337.getTree());

                    SEMI338=(Token)match(input,SEMI,FOLLOW_SEMI_in_expressionStatement6222); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI338);


                    // AST REWRITE
                    // elements: SEMI, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1178:23: -> ^( EXPRESSION_STATEMENT expression SEMI )
                    {
                        // CivlCParser.g:1178:26: ^( EXPRESSION_STATEMENT expression SEMI )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(EXPRESSION_STATEMENT, "EXPRESSION_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_1, 
                        stream_SEMI.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1179:7: SEMI
                    {
                    SEMI339=(Token)match(input,SEMI,FOLLOW_SEMI_in_expressionStatement6240); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI339);


                    // AST REWRITE
                    // elements: SEMI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1179:12: -> ^( EXPRESSION_STATEMENT ABSENT SEMI )
                    {
                        // CivlCParser.g:1179:15: ^( EXPRESSION_STATEMENT ABSENT SEMI )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(EXPRESSION_STATEMENT, "EXPRESSION_STATEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                        adaptor.addChild(root_1, 
                        stream_SEMI.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expressionStatement"


    public static class selectionStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectionStatement"
    // CivlCParser.g:1194:1: selectionStatement : ( IF LPAREN expression RPAREN s1= statementWithScope ( ( ELSE )=> ELSE s2= statementWithScope -> ^( IF expression $s1 $s2) | -> ^( IF expression $s1 ABSENT ) ) | SWITCH LPAREN expression RPAREN s= statementWithScope -> ^( SWITCH expression $s) );
    public final CivlCParser.selectionStatement_return selectionStatement() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CivlCParser.selectionStatement_return retval = new CivlCParser.selectionStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IF340=null;
        Token LPAREN341=null;
        Token RPAREN343=null;
        Token ELSE344=null;
        Token SWITCH345=null;
        Token LPAREN346=null;
        Token RPAREN348=null;
        CivlCParser.statementWithScope_return s1 =null;

        CivlCParser.statementWithScope_return s2 =null;

        CivlCParser.statementWithScope_return s =null;

        CivlCParser.expression_return expression342 =null;

        CivlCParser.expression_return expression347 =null;


        Object IF340_tree=null;
        Object LPAREN341_tree=null;
        Object RPAREN343_tree=null;
        Object ELSE344_tree=null;
        Object SWITCH345_tree=null;
        Object LPAREN346_tree=null;
        Object RPAREN348_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_SWITCH=new RewriteRuleTokenStream(adaptor,"token SWITCH");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleTokenStream stream_ELSE=new RewriteRuleTokenStream(adaptor,"token ELSE");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_statementWithScope=new RewriteRuleSubtreeStream(adaptor,"rule statementWithScope");

        	((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
        	((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
                ((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = false;

        try {
            // CivlCParser.g:1201:5: ( IF LPAREN expression RPAREN s1= statementWithScope ( ( ELSE )=> ELSE s2= statementWithScope -> ^( IF expression $s1 $s2) | -> ^( IF expression $s1 ABSENT ) ) | SWITCH LPAREN expression RPAREN s= statementWithScope -> ^( SWITCH expression $s) )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==IF) ) {
                alt87=1;
            }
            else if ( (LA87_0==SWITCH) ) {
                alt87=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;

            }
            switch (alt87) {
                case 1 :
                    // CivlCParser.g:1201:7: IF LPAREN expression RPAREN s1= statementWithScope ( ( ELSE )=> ELSE s2= statementWithScope -> ^( IF expression $s1 $s2) | -> ^( IF expression $s1 ABSENT ) )
                    {
                    IF340=(Token)match(input,IF,FOLLOW_IF_in_selectionStatement6279); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF340);


                    LPAREN341=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectionStatement6281); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN341);


                    pushFollow(FOLLOW_expression_in_selectionStatement6283);
                    expression342=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression342.getTree());

                    RPAREN343=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectionStatement6285); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN343);


                    pushFollow(FOLLOW_statementWithScope_in_selectionStatement6289);
                    s1=statementWithScope();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statementWithScope.add(s1.getTree());

                    // CivlCParser.g:1202:9: ( ( ELSE )=> ELSE s2= statementWithScope -> ^( IF expression $s1 $s2) | -> ^( IF expression $s1 ABSENT ) )
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==ELSE) ) {
                        int LA86_1 = input.LA(2);

                        if ( (synpred8_CivlCParser()) ) {
                            alt86=1;
                        }
                        else if ( (true) ) {
                            alt86=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 86, 1, input);

                            throw nvae;

                        }
                    }
                    else if ( ((LA86_0 >= ALIGNAS && LA86_0 <= AMPERSAND)||LA86_0==ASSERT||LA86_0==ASSUME||(LA86_0 >= ATOMIC && LA86_0 <= AUTO)||(LA86_0 >= BOOL && LA86_0 <= BREAK)||LA86_0==CASE||(LA86_0 >= CHAR && LA86_0 <= COLLECTIVE)||(LA86_0 >= COMPLEX && LA86_0 <= DEFAULT)||LA86_0==DO||LA86_0==DOUBLE||LA86_0==ENUM||LA86_0==EXTERN||(LA86_0 >= FLOAT && LA86_0 <= FOR)||(LA86_0 >= GENERIC && LA86_0 <= GOTO)||(LA86_0 >= IDENTIFIER && LA86_0 <= IF)||(LA86_0 >= INLINE && LA86_0 <= INTEGER_CONSTANT)||(LA86_0 >= LCURLY && LA86_0 <= LPAREN)||LA86_0==MINUSMINUS||(LA86_0 >= NORETURN && LA86_0 <= NOT)||LA86_0==OUTPUT||LA86_0==PLUS||LA86_0==PLUSPLUS||(LA86_0 >= PRAGMA && LA86_0 <= PROC)||(LA86_0 >= RCURLY && LA86_0 <= RETURN)||LA86_0==SEMI||(LA86_0 >= SHORT && LA86_0 <= STAR)||(LA86_0 >= STATIC && LA86_0 <= SUB)||(LA86_0 >= SWITCH && LA86_0 <= UNSIGNED)||(LA86_0 >= VOID && LA86_0 <= WHILE)) ) {
                        alt86=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 86, 0, input);

                        throw nvae;

                    }
                    switch (alt86) {
                        case 1 :
                            // CivlCParser.g:1202:11: ( ELSE )=> ELSE s2= statementWithScope
                            {
                            ELSE344=(Token)match(input,ELSE,FOLLOW_ELSE_in_selectionStatement6306); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ELSE.add(ELSE344);


                            pushFollow(FOLLOW_statementWithScope_in_selectionStatement6310);
                            s2=statementWithScope();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_statementWithScope.add(s2.getTree());

                            // AST REWRITE
                            // elements: expression, IF, s2, s1
                            // token labels: 
                            // rule labels: retval, s2, s1
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_s2=new RewriteRuleSubtreeStream(adaptor,"rule s2",s2!=null?s2.tree:null);
                            RewriteRuleSubtreeStream stream_s1=new RewriteRuleSubtreeStream(adaptor,"rule s1",s1!=null?s1.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1203:11: -> ^( IF expression $s1 $s2)
                            {
                                // CivlCParser.g:1203:14: ^( IF expression $s1 $s2)
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                stream_IF.nextNode()
                                , root_1);

                                adaptor.addChild(root_1, stream_expression.nextTree());

                                adaptor.addChild(root_1, stream_s1.nextTree());

                                adaptor.addChild(root_1, stream_s2.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:1204:11: 
                            {
                            // AST REWRITE
                            // elements: expression, s1, IF
                            // token labels: 
                            // rule labels: retval, s1
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_s1=new RewriteRuleSubtreeStream(adaptor,"rule s1",s1!=null?s1.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1204:11: -> ^( IF expression $s1 ABSENT )
                            {
                                // CivlCParser.g:1204:14: ^( IF expression $s1 ABSENT )
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                stream_IF.nextNode()
                                , root_1);

                                adaptor.addChild(root_1, stream_expression.nextTree());

                                adaptor.addChild(root_1, stream_s1.nextTree());

                                adaptor.addChild(root_1, 
                                (Object)adaptor.create(ABSENT, "ABSENT")
                                );

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // CivlCParser.g:1206:7: SWITCH LPAREN expression RPAREN s= statementWithScope
                    {
                    SWITCH345=(Token)match(input,SWITCH,FOLLOW_SWITCH_in_selectionStatement6375); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SWITCH.add(SWITCH345);


                    LPAREN346=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectionStatement6377); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN346);


                    pushFollow(FOLLOW_expression_in_selectionStatement6379);
                    expression347=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression347.getTree());

                    RPAREN348=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectionStatement6381); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN348);


                    pushFollow(FOLLOW_statementWithScope_in_selectionStatement6385);
                    s=statementWithScope();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statementWithScope.add(s.getTree());

                    // AST REWRITE
                    // elements: s, expression, SWITCH
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1207:7: -> ^( SWITCH expression $s)
                    {
                        // CivlCParser.g:1207:10: ^( SWITCH expression $s)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_SWITCH.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "selectionStatement"


    public static class iterationStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "iterationStatement"
    // CivlCParser.g:1229:1: iterationStatement : ( WHILE LPAREN expression RPAREN invariant_opt s= statementWithScope -> ^( WHILE expression $s invariant_opt ) | DO s= statementWithScope WHILE LPAREN expression RPAREN invariant_opt SEMI -> ^( DO $s expression invariant_opt ) | FOR LPAREN (d= declaration e1= expression_opt SEMI e2= expression_opt RPAREN i= invariant_opt s= statementWithScope -> ^( FOR $d $e1 $e2 $s $i) |e0= expression_opt SEMI e1= expression_opt SEMI e2= expression_opt RPAREN s= statementWithScope -> ^( FOR $e0 $e1 $e2 $s $i) ) );
    public final CivlCParser.iterationStatement_return iterationStatement() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CivlCParser.iterationStatement_return retval = new CivlCParser.iterationStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token WHILE349=null;
        Token LPAREN350=null;
        Token RPAREN352=null;
        Token DO354=null;
        Token WHILE355=null;
        Token LPAREN356=null;
        Token RPAREN358=null;
        Token SEMI360=null;
        Token FOR361=null;
        Token LPAREN362=null;
        Token SEMI363=null;
        Token RPAREN364=null;
        Token SEMI365=null;
        Token SEMI366=null;
        Token RPAREN367=null;
        CivlCParser.statementWithScope_return s =null;

        CivlCParser.declaration_return d =null;

        CivlCParser.expression_opt_return e1 =null;

        CivlCParser.expression_opt_return e2 =null;

        CivlCParser.invariant_opt_return i =null;

        CivlCParser.expression_opt_return e0 =null;

        CivlCParser.expression_return expression351 =null;

        CivlCParser.invariant_opt_return invariant_opt353 =null;

        CivlCParser.expression_return expression357 =null;

        CivlCParser.invariant_opt_return invariant_opt359 =null;


        Object WHILE349_tree=null;
        Object LPAREN350_tree=null;
        Object RPAREN352_tree=null;
        Object DO354_tree=null;
        Object WHILE355_tree=null;
        Object LPAREN356_tree=null;
        Object RPAREN358_tree=null;
        Object SEMI360_tree=null;
        Object FOR361_tree=null;
        Object LPAREN362_tree=null;
        Object SEMI363_tree=null;
        Object RPAREN364_tree=null;
        Object SEMI365_tree=null;
        Object SEMI366_tree=null;
        Object RPAREN367_tree=null;
        RewriteRuleTokenStream stream_FOR=new RewriteRuleTokenStream(adaptor,"token FOR");
        RewriteRuleTokenStream stream_DO=new RewriteRuleTokenStream(adaptor,"token DO");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        RewriteRuleSubtreeStream stream_expression_opt=new RewriteRuleSubtreeStream(adaptor,"rule expression_opt");
        RewriteRuleSubtreeStream stream_invariant_opt=new RewriteRuleSubtreeStream(adaptor,"rule invariant_opt");
        RewriteRuleSubtreeStream stream_statementWithScope=new RewriteRuleSubtreeStream(adaptor,"rule statementWithScope");

        	((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
        	((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
                ((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = false;

        try {
            // CivlCParser.g:1236:2: ( WHILE LPAREN expression RPAREN invariant_opt s= statementWithScope -> ^( WHILE expression $s invariant_opt ) | DO s= statementWithScope WHILE LPAREN expression RPAREN invariant_opt SEMI -> ^( DO $s expression invariant_opt ) | FOR LPAREN (d= declaration e1= expression_opt SEMI e2= expression_opt RPAREN i= invariant_opt s= statementWithScope -> ^( FOR $d $e1 $e2 $s $i) |e0= expression_opt SEMI e1= expression_opt SEMI e2= expression_opt RPAREN s= statementWithScope -> ^( FOR $e0 $e1 $e2 $s $i) ) )
            int alt89=3;
            switch ( input.LA(1) ) {
            case WHILE:
                {
                alt89=1;
                }
                break;
            case DO:
                {
                alt89=2;
                }
                break;
            case FOR:
                {
                alt89=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;

            }

            switch (alt89) {
                case 1 :
                    // CivlCParser.g:1236:4: WHILE LPAREN expression RPAREN invariant_opt s= statementWithScope
                    {
                    WHILE349=(Token)match(input,WHILE,FOLLOW_WHILE_in_iterationStatement6428); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_WHILE.add(WHILE349);


                    LPAREN350=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_iterationStatement6430); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN350);


                    pushFollow(FOLLOW_expression_in_iterationStatement6432);
                    expression351=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression351.getTree());

                    RPAREN352=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_iterationStatement6434); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN352);


                    pushFollow(FOLLOW_invariant_opt_in_iterationStatement6436);
                    invariant_opt353=invariant_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_invariant_opt.add(invariant_opt353.getTree());

                    pushFollow(FOLLOW_statementWithScope_in_iterationStatement6444);
                    s=statementWithScope();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statementWithScope.add(s.getTree());

                    // AST REWRITE
                    // elements: invariant_opt, s, WHILE, expression
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1238:4: -> ^( WHILE expression $s invariant_opt )
                    {
                        // CivlCParser.g:1238:7: ^( WHILE expression $s invariant_opt )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_WHILE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_1, stream_invariant_opt.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1239:4: DO s= statementWithScope WHILE LPAREN expression RPAREN invariant_opt SEMI
                    {
                    DO354=(Token)match(input,DO,FOLLOW_DO_in_iterationStatement6465); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DO.add(DO354);


                    pushFollow(FOLLOW_statementWithScope_in_iterationStatement6469);
                    s=statementWithScope();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statementWithScope.add(s.getTree());

                    WHILE355=(Token)match(input,WHILE,FOLLOW_WHILE_in_iterationStatement6471); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_WHILE.add(WHILE355);


                    LPAREN356=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_iterationStatement6473); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN356);


                    pushFollow(FOLLOW_expression_in_iterationStatement6475);
                    expression357=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression357.getTree());

                    RPAREN358=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_iterationStatement6477); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN358);


                    pushFollow(FOLLOW_invariant_opt_in_iterationStatement6483);
                    invariant_opt359=invariant_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_invariant_opt.add(invariant_opt359.getTree());

                    SEMI360=(Token)match(input,SEMI,FOLLOW_SEMI_in_iterationStatement6485); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI360);


                    // AST REWRITE
                    // elements: invariant_opt, s, DO, expression
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1241:4: -> ^( DO $s expression invariant_opt )
                    {
                        // CivlCParser.g:1241:7: ^( DO $s expression invariant_opt )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_DO.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_1, stream_invariant_opt.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:1242:4: FOR LPAREN (d= declaration e1= expression_opt SEMI e2= expression_opt RPAREN i= invariant_opt s= statementWithScope -> ^( FOR $d $e1 $e2 $s $i) |e0= expression_opt SEMI e1= expression_opt SEMI e2= expression_opt RPAREN s= statementWithScope -> ^( FOR $e0 $e1 $e2 $s $i) )
                    {
                    FOR361=(Token)match(input,FOR,FOLLOW_FOR_in_iterationStatement6506); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FOR.add(FOR361);


                    LPAREN362=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_iterationStatement6508); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN362);


                    // CivlCParser.g:1243:4: (d= declaration e1= expression_opt SEMI e2= expression_opt RPAREN i= invariant_opt s= statementWithScope -> ^( FOR $d $e1 $e2 $s $i) |e0= expression_opt SEMI e1= expression_opt SEMI e2= expression_opt RPAREN s= statementWithScope -> ^( FOR $e0 $e1 $e2 $s $i) )
                    int alt88=2;
                    switch ( input.LA(1) ) {
                    case ALIGNAS:
                    case ATOMIC:
                    case AUTO:
                    case BOOL:
                    case CHAR:
                    case COMPLEX:
                    case CONST:
                    case DOUBLE:
                    case ENUM:
                    case EXTERN:
                    case FLOAT:
                    case INLINE:
                    case INPUT:
                    case INT:
                    case LONG:
                    case NORETURN:
                    case OUTPUT:
                    case PROC:
                    case REGISTER:
                    case RESTRICT:
                    case SHORT:
                    case SIGNED:
                    case STATIC:
                    case STATICASSERT:
                    case STRUCT:
                    case THREADLOCAL:
                    case TYPEDEF:
                    case UNION:
                    case UNSIGNED:
                    case VOID:
                    case VOLATILE:
                        {
                        alt88=1;
                        }
                        break;
                    case IDENTIFIER:
                        {
                        int LA88_18 = input.LA(2);

                        if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 88, 18, input);

                            throw nvae;

                        }
                        }
                        break;
                    case ALIGNOF:
                    case AMPERSAND:
                    case CHARACTER_CONSTANT:
                    case COLLECTIVE:
                    case FLOATING_CONSTANT:
                    case GENERIC:
                    case INTEGER_CONSTANT:
                    case LPAREN:
                    case MINUSMINUS:
                    case NOT:
                    case PLUS:
                    case PLUSPLUS:
                    case SEMI:
                    case SIZEOF:
                    case SPAWN:
                    case STAR:
                    case STRING_LITERAL:
                    case SUB:
                    case TILDE:
                        {
                        alt88=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 88, 0, input);

                        throw nvae;

                    }

                    switch (alt88) {
                        case 1 :
                            // CivlCParser.g:1243:6: d= declaration e1= expression_opt SEMI e2= expression_opt RPAREN i= invariant_opt s= statementWithScope
                            {
                            pushFollow(FOLLOW_declaration_in_iterationStatement6518);
                            d=declaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_declaration.add(d.getTree());

                            pushFollow(FOLLOW_expression_opt_in_iterationStatement6522);
                            e1=expression_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression_opt.add(e1.getTree());

                            SEMI363=(Token)match(input,SEMI,FOLLOW_SEMI_in_iterationStatement6524); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_SEMI.add(SEMI363);


                            pushFollow(FOLLOW_expression_opt_in_iterationStatement6528);
                            e2=expression_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression_opt.add(e2.getTree());

                            RPAREN364=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_iterationStatement6535); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN364);


                            pushFollow(FOLLOW_invariant_opt_in_iterationStatement6539);
                            i=invariant_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_invariant_opt.add(i.getTree());

                            pushFollow(FOLLOW_statementWithScope_in_iterationStatement6543);
                            s=statementWithScope();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_statementWithScope.add(s.getTree());

                            // AST REWRITE
                            // elements: s, e1, FOR, d, i, e2
                            // token labels: 
                            // rule labels: retval, d, e1, e2, s, i
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
                            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
                            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);
                            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);
                            RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1245:6: -> ^( FOR $d $e1 $e2 $s $i)
                            {
                                // CivlCParser.g:1245:9: ^( FOR $d $e1 $e2 $s $i)
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                stream_FOR.nextNode()
                                , root_1);

                                adaptor.addChild(root_1, stream_d.nextTree());

                                adaptor.addChild(root_1, stream_e1.nextTree());

                                adaptor.addChild(root_1, stream_e2.nextTree());

                                adaptor.addChild(root_1, stream_s.nextTree());

                                adaptor.addChild(root_1, stream_i.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // CivlCParser.g:1246:6: e0= expression_opt SEMI e1= expression_opt SEMI e2= expression_opt RPAREN s= statementWithScope
                            {
                            pushFollow(FOLLOW_expression_opt_in_iterationStatement6578);
                            e0=expression_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression_opt.add(e0.getTree());

                            SEMI365=(Token)match(input,SEMI,FOLLOW_SEMI_in_iterationStatement6580); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_SEMI.add(SEMI365);


                            pushFollow(FOLLOW_expression_opt_in_iterationStatement6584);
                            e1=expression_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression_opt.add(e1.getTree());

                            SEMI366=(Token)match(input,SEMI,FOLLOW_SEMI_in_iterationStatement6586); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_SEMI.add(SEMI366);


                            pushFollow(FOLLOW_expression_opt_in_iterationStatement6595);
                            e2=expression_opt();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression_opt.add(e2.getTree());

                            RPAREN367=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_iterationStatement6597); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN367);


                            pushFollow(FOLLOW_statementWithScope_in_iterationStatement6601);
                            s=statementWithScope();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_statementWithScope.add(s.getTree());

                            // AST REWRITE
                            // elements: e0, s, e1, FOR, e2, i
                            // token labels: 
                            // rule labels: retval, e1, e2, s, e0, i
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
                            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);
                            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);
                            RewriteRuleSubtreeStream stream_e0=new RewriteRuleSubtreeStream(adaptor,"rule e0",e0!=null?e0.tree:null);
                            RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                            root_0 = (Object)adaptor.nil();
                            // 1248:6: -> ^( FOR $e0 $e1 $e2 $s $i)
                            {
                                // CivlCParser.g:1248:9: ^( FOR $e0 $e1 $e2 $s $i)
                                {
                                Object root_1 = (Object)adaptor.nil();
                                root_1 = (Object)adaptor.becomeRoot(
                                stream_FOR.nextNode()
                                , root_1);

                                adaptor.addChild(root_1, stream_e0.nextTree());

                                adaptor.addChild(root_1, stream_e1.nextTree());

                                adaptor.addChild(root_1, stream_e2.nextTree());

                                adaptor.addChild(root_1, stream_s.nextTree());

                                adaptor.addChild(root_1, stream_i.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "iterationStatement"


    public static class expression_opt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression_opt"
    // CivlCParser.g:1252:1: expression_opt : ( expression | -> ABSENT );
    public final CivlCParser.expression_opt_return expression_opt() throws RecognitionException {
        CivlCParser.expression_opt_return retval = new CivlCParser.expression_opt_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.expression_return expression368 =null;



        try {
            // CivlCParser.g:1253:2: ( expression | -> ABSENT )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( ((LA90_0 >= ALIGNOF && LA90_0 <= AMPERSAND)||LA90_0==CHARACTER_CONSTANT||LA90_0==COLLECTIVE||LA90_0==FLOATING_CONSTANT||LA90_0==GENERIC||LA90_0==IDENTIFIER||LA90_0==INTEGER_CONSTANT||LA90_0==LPAREN||LA90_0==MINUSMINUS||LA90_0==NOT||LA90_0==PLUS||LA90_0==PLUSPLUS||(LA90_0 >= SIZEOF && LA90_0 <= STAR)||LA90_0==STRING_LITERAL||LA90_0==SUB||LA90_0==TILDE) ) {
                alt90=1;
            }
            else if ( (LA90_0==RPAREN||LA90_0==SEMI) ) {
                alt90=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;

            }
            switch (alt90) {
                case 1 :
                    // CivlCParser.g:1253:4: expression
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expression_in_expression_opt6643);
                    expression368=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression368.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1254:4: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1254:4: -> ABSENT
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression_opt"


    public static class invariant_opt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "invariant_opt"
    // CivlCParser.g:1257:1: invariant_opt : ( -> ABSENT | INVARIANT LPAREN expression RPAREN -> ^( INVARIANT expression ) );
    public final CivlCParser.invariant_opt_return invariant_opt() throws RecognitionException {
        CivlCParser.invariant_opt_return retval = new CivlCParser.invariant_opt_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token INVARIANT369=null;
        Token LPAREN370=null;
        Token RPAREN372=null;
        CivlCParser.expression_return expression371 =null;


        Object INVARIANT369_tree=null;
        Object LPAREN370_tree=null;
        Object RPAREN372_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_INVARIANT=new RewriteRuleTokenStream(adaptor,"token INVARIANT");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // CivlCParser.g:1258:2: ( -> ABSENT | INVARIANT LPAREN expression RPAREN -> ^( INVARIANT expression ) )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( ((LA91_0 >= ALIGNOF && LA91_0 <= AMPERSAND)||LA91_0==ASSERT||LA91_0==ASSUME||LA91_0==BREAK||LA91_0==CASE||(LA91_0 >= CHARACTER_CONSTANT && LA91_0 <= COLLECTIVE)||(LA91_0 >= CONTINUE && LA91_0 <= DEFAULT)||LA91_0==DO||(LA91_0 >= FLOATING_CONSTANT && LA91_0 <= FOR)||(LA91_0 >= GENERIC && LA91_0 <= GOTO)||(LA91_0 >= IDENTIFIER && LA91_0 <= IF)||LA91_0==INTEGER_CONSTANT||LA91_0==LCURLY||LA91_0==LPAREN||LA91_0==MINUSMINUS||LA91_0==NOT||LA91_0==PLUS||LA91_0==PLUSPLUS||LA91_0==PRAGMA||LA91_0==RETURN||LA91_0==SEMI||(LA91_0 >= SIZEOF && LA91_0 <= STAR)||LA91_0==STRING_LITERAL||LA91_0==SUB||LA91_0==SWITCH||LA91_0==TILDE||(LA91_0 >= WAIT && LA91_0 <= WHILE)) ) {
                alt91=1;
            }
            else if ( (LA91_0==INVARIANT) ) {
                alt91=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;

            }
            switch (alt91) {
                case 1 :
                    // CivlCParser.g:1258:4: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1258:4: -> ABSENT
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(ABSENT, "ABSENT")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1259:4: INVARIANT LPAREN expression RPAREN
                    {
                    INVARIANT369=(Token)match(input,INVARIANT,FOLLOW_INVARIANT_in_invariant_opt6668); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INVARIANT.add(INVARIANT369);


                    LPAREN370=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_invariant_opt6670); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN370);


                    pushFollow(FOLLOW_expression_in_invariant_opt6672);
                    expression371=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression371.getTree());

                    RPAREN372=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_invariant_opt6674); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN372);


                    // AST REWRITE
                    // elements: expression, INVARIANT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1260:3: -> ^( INVARIANT expression )
                    {
                        // CivlCParser.g:1260:6: ^( INVARIANT expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_INVARIANT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "invariant_opt"


    public static class jumpStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "jumpStatement"
    // CivlCParser.g:1281:1: jumpStatement : ( GOTO IDENTIFIER SEMI -> ^( GOTO IDENTIFIER SEMI ) | CONTINUE SEMI -> ^( CONTINUE SEMI ) | BREAK SEMI -> ^( BREAK SEMI ) | RETURN expression_opt SEMI -> ^( RETURN expression_opt SEMI ) );
    public final CivlCParser.jumpStatement_return jumpStatement() throws RecognitionException {
        CivlCParser.jumpStatement_return retval = new CivlCParser.jumpStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token GOTO373=null;
        Token IDENTIFIER374=null;
        Token SEMI375=null;
        Token CONTINUE376=null;
        Token SEMI377=null;
        Token BREAK378=null;
        Token SEMI379=null;
        Token RETURN380=null;
        Token SEMI382=null;
        CivlCParser.expression_opt_return expression_opt381 =null;


        Object GOTO373_tree=null;
        Object IDENTIFIER374_tree=null;
        Object SEMI375_tree=null;
        Object CONTINUE376_tree=null;
        Object SEMI377_tree=null;
        Object BREAK378_tree=null;
        Object SEMI379_tree=null;
        Object RETURN380_tree=null;
        Object SEMI382_tree=null;
        RewriteRuleTokenStream stream_GOTO=new RewriteRuleTokenStream(adaptor,"token GOTO");
        RewriteRuleTokenStream stream_CONTINUE=new RewriteRuleTokenStream(adaptor,"token CONTINUE");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_BREAK=new RewriteRuleTokenStream(adaptor,"token BREAK");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_RETURN=new RewriteRuleTokenStream(adaptor,"token RETURN");
        RewriteRuleSubtreeStream stream_expression_opt=new RewriteRuleSubtreeStream(adaptor,"rule expression_opt");
        try {
            // CivlCParser.g:1282:5: ( GOTO IDENTIFIER SEMI -> ^( GOTO IDENTIFIER SEMI ) | CONTINUE SEMI -> ^( CONTINUE SEMI ) | BREAK SEMI -> ^( BREAK SEMI ) | RETURN expression_opt SEMI -> ^( RETURN expression_opt SEMI ) )
            int alt92=4;
            switch ( input.LA(1) ) {
            case GOTO:
                {
                alt92=1;
                }
                break;
            case CONTINUE:
                {
                alt92=2;
                }
                break;
            case BREAK:
                {
                alt92=3;
                }
                break;
            case RETURN:
                {
                alt92=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;

            }

            switch (alt92) {
                case 1 :
                    // CivlCParser.g:1282:7: GOTO IDENTIFIER SEMI
                    {
                    GOTO373=(Token)match(input,GOTO,FOLLOW_GOTO_in_jumpStatement6701); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GOTO.add(GOTO373);


                    IDENTIFIER374=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_jumpStatement6703); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER374);


                    SEMI375=(Token)match(input,SEMI,FOLLOW_SEMI_in_jumpStatement6705); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI375);


                    // AST REWRITE
                    // elements: IDENTIFIER, SEMI, GOTO
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1282:28: -> ^( GOTO IDENTIFIER SEMI )
                    {
                        // CivlCParser.g:1282:31: ^( GOTO IDENTIFIER SEMI )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_GOTO.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IDENTIFIER.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_SEMI.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1283:7: CONTINUE SEMI
                    {
                    CONTINUE376=(Token)match(input,CONTINUE,FOLLOW_CONTINUE_in_jumpStatement6723); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CONTINUE.add(CONTINUE376);


                    SEMI377=(Token)match(input,SEMI,FOLLOW_SEMI_in_jumpStatement6725); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI377);


                    // AST REWRITE
                    // elements: CONTINUE, SEMI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1283:21: -> ^( CONTINUE SEMI )
                    {
                        // CivlCParser.g:1283:24: ^( CONTINUE SEMI )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CONTINUE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_SEMI.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // CivlCParser.g:1284:7: BREAK SEMI
                    {
                    BREAK378=(Token)match(input,BREAK,FOLLOW_BREAK_in_jumpStatement6741); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BREAK.add(BREAK378);


                    SEMI379=(Token)match(input,SEMI,FOLLOW_SEMI_in_jumpStatement6743); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI379);


                    // AST REWRITE
                    // elements: BREAK, SEMI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1284:18: -> ^( BREAK SEMI )
                    {
                        // CivlCParser.g:1284:21: ^( BREAK SEMI )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_BREAK.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_SEMI.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // CivlCParser.g:1285:7: RETURN expression_opt SEMI
                    {
                    RETURN380=(Token)match(input,RETURN,FOLLOW_RETURN_in_jumpStatement6759); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RETURN.add(RETURN380);


                    pushFollow(FOLLOW_expression_opt_in_jumpStatement6761);
                    expression_opt381=expression_opt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression_opt.add(expression_opt381.getTree());

                    SEMI382=(Token)match(input,SEMI,FOLLOW_SEMI_in_jumpStatement6763); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI382);


                    // AST REWRITE
                    // elements: SEMI, expression_opt, RETURN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1285:34: -> ^( RETURN expression_opt SEMI )
                    {
                        // CivlCParser.g:1285:37: ^( RETURN expression_opt SEMI )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_RETURN.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expression_opt.nextTree());

                        adaptor.addChild(root_1, 
                        stream_SEMI.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "jumpStatement"


    public static class pragma_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pragma"
    // CivlCParser.g:1294:1: pragma : PRAGMA IDENTIFIER pragmaBody NEWLINE -> ^( PRAGMA IDENTIFIER ^( TOKEN_LIST pragmaBody ) NEWLINE ) ;
    public final CivlCParser.pragma_return pragma() throws RecognitionException {
        CivlCParser.pragma_return retval = new CivlCParser.pragma_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PRAGMA383=null;
        Token IDENTIFIER384=null;
        Token NEWLINE386=null;
        CivlCParser.pragmaBody_return pragmaBody385 =null;


        Object PRAGMA383_tree=null;
        Object IDENTIFIER384_tree=null;
        Object NEWLINE386_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleTokenStream stream_PRAGMA=new RewriteRuleTokenStream(adaptor,"token PRAGMA");
        RewriteRuleSubtreeStream stream_pragmaBody=new RewriteRuleSubtreeStream(adaptor,"rule pragmaBody");
        try {
            // CivlCParser.g:1294:8: ( PRAGMA IDENTIFIER pragmaBody NEWLINE -> ^( PRAGMA IDENTIFIER ^( TOKEN_LIST pragmaBody ) NEWLINE ) )
            // CivlCParser.g:1294:10: PRAGMA IDENTIFIER pragmaBody NEWLINE
            {
            PRAGMA383=(Token)match(input,PRAGMA,FOLLOW_PRAGMA_in_pragma6788); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_PRAGMA.add(PRAGMA383);


            IDENTIFIER384=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_pragma6790); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER384);


            pushFollow(FOLLOW_pragmaBody_in_pragma6792);
            pragmaBody385=pragmaBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pragmaBody.add(pragmaBody385.getTree());

            NEWLINE386=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_pragma6794); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE386);


            // AST REWRITE
            // elements: NEWLINE, PRAGMA, IDENTIFIER, pragmaBody
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1295:3: -> ^( PRAGMA IDENTIFIER ^( TOKEN_LIST pragmaBody ) NEWLINE )
            {
                // CivlCParser.g:1295:6: ^( PRAGMA IDENTIFIER ^( TOKEN_LIST pragmaBody ) NEWLINE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PRAGMA.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENTIFIER.nextNode()
                );

                // CivlCParser.g:1295:26: ^( TOKEN_LIST pragmaBody )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TOKEN_LIST, "TOKEN_LIST")
                , root_2);

                adaptor.addChild(root_2, stream_pragmaBody.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_1, 
                stream_NEWLINE.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pragma"


    public static class pragmaBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pragmaBody"
    // CivlCParser.g:1298:1: pragmaBody : (~ NEWLINE )* ;
    public final CivlCParser.pragmaBody_return pragmaBody() throws RecognitionException {
        CivlCParser.pragmaBody_return retval = new CivlCParser.pragmaBody_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set387=null;

        Object set387_tree=null;

        try {
            // CivlCParser.g:1299:2: ( (~ NEWLINE )* )
            // CivlCParser.g:1299:4: (~ NEWLINE )*
            {
            root_0 = (Object)adaptor.nil();


            // CivlCParser.g:1299:4: (~ NEWLINE )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( ((LA93_0 >= ALIGNAS && LA93_0 <= NEQ)||(LA93_0 >= NORETURN && LA93_0 <= TYPE_QUALIFIER_LIST)) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // CivlCParser.g:
            	    {
            	    set387=(Token)input.LT(1);

            	    if ( (input.LA(1) >= ALIGNAS && input.LA(1) <= NEQ)||(input.LA(1) >= NORETURN && input.LA(1) <= TYPE_QUALIFIER_LIST) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set387)
            	        );
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop93;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pragmaBody"


    public static class assertStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assertStatement"
    // CivlCParser.g:1302:1: assertStatement : ASSERT expression SEMI -> ^( ASSERT expression ) ;
    public final CivlCParser.assertStatement_return assertStatement() throws RecognitionException {
        CivlCParser.assertStatement_return retval = new CivlCParser.assertStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ASSERT388=null;
        Token SEMI390=null;
        CivlCParser.expression_return expression389 =null;


        Object ASSERT388_tree=null;
        Object SEMI390_tree=null;
        RewriteRuleTokenStream stream_ASSERT=new RewriteRuleTokenStream(adaptor,"token ASSERT");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // CivlCParser.g:1303:2: ( ASSERT expression SEMI -> ^( ASSERT expression ) )
            // CivlCParser.g:1303:4: ASSERT expression SEMI
            {
            ASSERT388=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_assertStatement6839); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSERT.add(ASSERT388);


            pushFollow(FOLLOW_expression_in_assertStatement6841);
            expression389=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression389.getTree());

            SEMI390=(Token)match(input,SEMI,FOLLOW_SEMI_in_assertStatement6843); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI390);


            // AST REWRITE
            // elements: ASSERT, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1303:27: -> ^( ASSERT expression )
            {
                // CivlCParser.g:1303:30: ^( ASSERT expression )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_ASSERT.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assertStatement"


    public static class assumeStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assumeStatement"
    // CivlCParser.g:1306:1: assumeStatement : ASSUME expression SEMI -> ^( ASSUME expression ) ;
    public final CivlCParser.assumeStatement_return assumeStatement() throws RecognitionException {
        CivlCParser.assumeStatement_return retval = new CivlCParser.assumeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ASSUME391=null;
        Token SEMI393=null;
        CivlCParser.expression_return expression392 =null;


        Object ASSUME391_tree=null;
        Object SEMI393_tree=null;
        RewriteRuleTokenStream stream_ASSUME=new RewriteRuleTokenStream(adaptor,"token ASSUME");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // CivlCParser.g:1307:2: ( ASSUME expression SEMI -> ^( ASSUME expression ) )
            // CivlCParser.g:1307:4: ASSUME expression SEMI
            {
            ASSUME391=(Token)match(input,ASSUME,FOLLOW_ASSUME_in_assumeStatement6862); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSUME.add(ASSUME391);


            pushFollow(FOLLOW_expression_in_assumeStatement6864);
            expression392=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression392.getTree());

            SEMI393=(Token)match(input,SEMI,FOLLOW_SEMI_in_assumeStatement6866); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI393);


            // AST REWRITE
            // elements: ASSUME, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1307:27: -> ^( ASSUME expression )
            {
                // CivlCParser.g:1307:30: ^( ASSUME expression )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_ASSUME.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assumeStatement"


    public static class waitStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "waitStatement"
    // CivlCParser.g:1310:1: waitStatement : WAIT expression SEMI -> ^( WAIT expression ) ;
    public final CivlCParser.waitStatement_return waitStatement() throws RecognitionException {
        CivlCParser.waitStatement_return retval = new CivlCParser.waitStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token WAIT394=null;
        Token SEMI396=null;
        CivlCParser.expression_return expression395 =null;


        Object WAIT394_tree=null;
        Object SEMI396_tree=null;
        RewriteRuleTokenStream stream_WAIT=new RewriteRuleTokenStream(adaptor,"token WAIT");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // CivlCParser.g:1311:2: ( WAIT expression SEMI -> ^( WAIT expression ) )
            // CivlCParser.g:1311:4: WAIT expression SEMI
            {
            WAIT394=(Token)match(input,WAIT,FOLLOW_WAIT_in_waitStatement6885); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_WAIT.add(WAIT394);


            pushFollow(FOLLOW_expression_in_waitStatement6887);
            expression395=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression395.getTree());

            SEMI396=(Token)match(input,SEMI,FOLLOW_SEMI_in_waitStatement6889); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI396);


            // AST REWRITE
            // elements: expression, WAIT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1311:25: -> ^( WAIT expression )
            {
                // CivlCParser.g:1311:28: ^( WAIT expression )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_WAIT.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "waitStatement"


    public static class whenStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "whenStatement"
    // CivlCParser.g:1314:1: whenStatement : WHEN LPAREN expression RPAREN statement -> ^( WHEN expression statement ) ;
    public final CivlCParser.whenStatement_return whenStatement() throws RecognitionException {
        CivlCParser.whenStatement_return retval = new CivlCParser.whenStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token WHEN397=null;
        Token LPAREN398=null;
        Token RPAREN400=null;
        CivlCParser.expression_return expression399 =null;

        CivlCParser.statement_return statement401 =null;


        Object WHEN397_tree=null;
        Object LPAREN398_tree=null;
        Object RPAREN400_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_WHEN=new RewriteRuleTokenStream(adaptor,"token WHEN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // CivlCParser.g:1315:2: ( WHEN LPAREN expression RPAREN statement -> ^( WHEN expression statement ) )
            // CivlCParser.g:1315:4: WHEN LPAREN expression RPAREN statement
            {
            WHEN397=(Token)match(input,WHEN,FOLLOW_WHEN_in_whenStatement6908); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_WHEN.add(WHEN397);


            LPAREN398=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_whenStatement6910); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN398);


            pushFollow(FOLLOW_expression_in_whenStatement6912);
            expression399=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression399.getTree());

            RPAREN400=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_whenStatement6914); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN400);


            pushFollow(FOLLOW_statement_in_whenStatement6916);
            statement401=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statement.add(statement401.getTree());

            // AST REWRITE
            // elements: WHEN, statement, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1316:3: -> ^( WHEN expression statement )
            {
                // CivlCParser.g:1316:6: ^( WHEN expression statement )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_WHEN.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_1, stream_statement.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "whenStatement"


    public static class chooseStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "chooseStatement"
    // CivlCParser.g:1319:1: chooseStatement : CHOOSE LCURLY ( statement )+ RCURLY -> ^( CHOOSE ( statement )+ ) ;
    public final CivlCParser.chooseStatement_return chooseStatement() throws RecognitionException {
        CivlCParser.chooseStatement_return retval = new CivlCParser.chooseStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token CHOOSE402=null;
        Token LCURLY403=null;
        Token RCURLY405=null;
        CivlCParser.statement_return statement404 =null;


        Object CHOOSE402_tree=null;
        Object LCURLY403_tree=null;
        Object RCURLY405_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_CHOOSE=new RewriteRuleTokenStream(adaptor,"token CHOOSE");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // CivlCParser.g:1320:2: ( CHOOSE LCURLY ( statement )+ RCURLY -> ^( CHOOSE ( statement )+ ) )
            // CivlCParser.g:1320:4: CHOOSE LCURLY ( statement )+ RCURLY
            {
            CHOOSE402=(Token)match(input,CHOOSE,FOLLOW_CHOOSE_in_chooseStatement6939); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CHOOSE.add(CHOOSE402);


            LCURLY403=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_chooseStatement6941); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY403);


            // CivlCParser.g:1320:18: ( statement )+
            int cnt94=0;
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( ((LA94_0 >= ALIGNOF && LA94_0 <= AMPERSAND)||LA94_0==ASSERT||LA94_0==ASSUME||LA94_0==BREAK||LA94_0==CASE||(LA94_0 >= CHARACTER_CONSTANT && LA94_0 <= COLLECTIVE)||(LA94_0 >= CONTINUE && LA94_0 <= DEFAULT)||LA94_0==DO||(LA94_0 >= FLOATING_CONSTANT && LA94_0 <= FOR)||(LA94_0 >= GENERIC && LA94_0 <= GOTO)||(LA94_0 >= IDENTIFIER && LA94_0 <= IF)||LA94_0==INTEGER_CONSTANT||LA94_0==LCURLY||LA94_0==LPAREN||LA94_0==MINUSMINUS||LA94_0==NOT||LA94_0==PLUS||LA94_0==PLUSPLUS||LA94_0==PRAGMA||LA94_0==RETURN||LA94_0==SEMI||(LA94_0 >= SIZEOF && LA94_0 <= STAR)||LA94_0==STRING_LITERAL||LA94_0==SUB||LA94_0==SWITCH||LA94_0==TILDE||(LA94_0 >= WAIT && LA94_0 <= WHILE)) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // CivlCParser.g:1320:18: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_chooseStatement6943);
            	    statement404=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement404.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt94 >= 1 ) break loop94;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(94, input);
                        throw eee;
                }
                cnt94++;
            } while (true);


            RCURLY405=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_chooseStatement6946); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY405);


            // AST REWRITE
            // elements: CHOOSE, statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1321:3: -> ^( CHOOSE ( statement )+ )
            {
                // CivlCParser.g:1321:6: ^( CHOOSE ( statement )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_CHOOSE.nextNode()
                , root_1);

                if ( !(stream_statement.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "chooseStatement"


    public static class translationUnit_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "translationUnit"
    // CivlCParser.g:1330:1: translationUnit : ( externalDeclaration )* EOF -> ^( TRANSLATION_UNIT ( externalDeclaration )* ) ;
    public final CivlCParser.translationUnit_return translationUnit() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());
        DeclarationScope_stack.push(new DeclarationScope_scope());

        CivlCParser.translationUnit_return retval = new CivlCParser.translationUnit_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF407=null;
        CivlCParser.externalDeclaration_return externalDeclaration406 =null;


        Object EOF407_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_externalDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule externalDeclaration");

            ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
            ((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
            ((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = false;
            ((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef = false;

        try {
            // CivlCParser.g:1339:2: ( ( externalDeclaration )* EOF -> ^( TRANSLATION_UNIT ( externalDeclaration )* ) )
            // CivlCParser.g:1339:4: ( externalDeclaration )* EOF
            {
            // CivlCParser.g:1339:4: ( externalDeclaration )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==ALIGNAS||(LA95_0 >= ATOMIC && LA95_0 <= AUTO)||LA95_0==BOOL||LA95_0==CHAR||(LA95_0 >= COMPLEX && LA95_0 <= CONST)||LA95_0==DOUBLE||LA95_0==ENUM||LA95_0==EXTERN||LA95_0==FLOAT||LA95_0==IDENTIFIER||(LA95_0 >= INLINE && LA95_0 <= INT)||LA95_0==LONG||LA95_0==NORETURN||LA95_0==OUTPUT||(LA95_0 >= PRAGMA && LA95_0 <= PROC)||(LA95_0 >= REGISTER && LA95_0 <= RESTRICT)||(LA95_0 >= SHORT && LA95_0 <= SIGNED)||(LA95_0 >= STATIC && LA95_0 <= STATICASSERT)||LA95_0==STRUCT||LA95_0==THREADLOCAL||(LA95_0 >= TYPEDEF && LA95_0 <= UNSIGNED)||(LA95_0 >= VOID && LA95_0 <= VOLATILE)) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // CivlCParser.g:1339:4: externalDeclaration
            	    {
            	    pushFollow(FOLLOW_externalDeclaration_in_translationUnit6991);
            	    externalDeclaration406=externalDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_externalDeclaration.add(externalDeclaration406.getTree());

            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);


            EOF407=(Token)match(input,EOF,FOLLOW_EOF_in_translationUnit6994); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF407);


            // AST REWRITE
            // elements: externalDeclaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1340:3: -> ^( TRANSLATION_UNIT ( externalDeclaration )* )
            {
                // CivlCParser.g:1340:6: ^( TRANSLATION_UNIT ( externalDeclaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TRANSLATION_UNIT, "TRANSLATION_UNIT")
                , root_1);

                // CivlCParser.g:1340:25: ( externalDeclaration )*
                while ( stream_externalDeclaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_externalDeclaration.nextTree());

                }
                stream_externalDeclaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();
            DeclarationScope_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "translationUnit"


    public static class externalDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "externalDeclaration"
    // CivlCParser.g:1348:1: externalDeclaration : ( ( declarationSpecifiers declarator declarationList_opt LCURLY )=> functionDefinition | declaration | pragma );
    public final CivlCParser.externalDeclaration_return externalDeclaration() throws RecognitionException {
        CivlCParser.externalDeclaration_return retval = new CivlCParser.externalDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.functionDefinition_return functionDefinition408 =null;

        CivlCParser.declaration_return declaration409 =null;

        CivlCParser.pragma_return pragma410 =null;



        try {
            // CivlCParser.g:1349:5: ( ( declarationSpecifiers declarator declarationList_opt LCURLY )=> functionDefinition | declaration | pragma )
            int alt96=3;
            switch ( input.LA(1) ) {
            case TYPEDEF:
                {
                int LA96_1 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 1, input);

                    throw nvae;

                }
                }
                break;
            case AUTO:
            case EXTERN:
            case REGISTER:
            case STATIC:
            case THREADLOCAL:
                {
                int LA96_2 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 2, input);

                    throw nvae;

                }
                }
                break;
            case VOID:
                {
                int LA96_3 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 3, input);

                    throw nvae;

                }
                }
                break;
            case CHAR:
                {
                int LA96_4 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 4, input);

                    throw nvae;

                }
                }
                break;
            case SHORT:
                {
                int LA96_5 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 5, input);

                    throw nvae;

                }
                }
                break;
            case INT:
                {
                int LA96_6 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 6, input);

                    throw nvae;

                }
                }
                break;
            case LONG:
                {
                int LA96_7 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 7, input);

                    throw nvae;

                }
                }
                break;
            case FLOAT:
                {
                int LA96_8 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 8, input);

                    throw nvae;

                }
                }
                break;
            case DOUBLE:
                {
                int LA96_9 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 9, input);

                    throw nvae;

                }
                }
                break;
            case SIGNED:
                {
                int LA96_10 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 10, input);

                    throw nvae;

                }
                }
                break;
            case UNSIGNED:
                {
                int LA96_11 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 11, input);

                    throw nvae;

                }
                }
                break;
            case BOOL:
                {
                int LA96_12 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 12, input);

                    throw nvae;

                }
                }
                break;
            case COMPLEX:
                {
                int LA96_13 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 13, input);

                    throw nvae;

                }
                }
                break;
            case PROC:
                {
                int LA96_14 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 14, input);

                    throw nvae;

                }
                }
                break;
            case ATOMIC:
                {
                int LA96_15 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 15, input);

                    throw nvae;

                }
                }
                break;
            case STRUCT:
            case UNION:
                {
                int LA96_16 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 16, input);

                    throw nvae;

                }
                }
                break;
            case ENUM:
                {
                int LA96_17 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 17, input);

                    throw nvae;

                }
                }
                break;
            case IDENTIFIER:
                {
                int LA96_18 = input.LA(2);

                if ( ((((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                    alt96=1;
                }
                else if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 18, input);

                    throw nvae;

                }
                }
                break;
            case CONST:
            case INPUT:
            case OUTPUT:
            case RESTRICT:
            case VOLATILE:
                {
                int LA96_19 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 19, input);

                    throw nvae;

                }
                }
                break;
            case INLINE:
            case NORETURN:
                {
                int LA96_20 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 20, input);

                    throw nvae;

                }
                }
                break;
            case ALIGNAS:
                {
                int LA96_21 = input.LA(2);

                if ( (((synpred9_CivlCParser()&&synpred9_CivlCParser())&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))) ) {
                    alt96=1;
                }
                else if ( ((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )) ) {
                    alt96=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 21, input);

                    throw nvae;

                }
                }
                break;
            case STATICASSERT:
                {
                alt96=2;
                }
                break;
            case PRAGMA:
                {
                alt96=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;

            }

            switch (alt96) {
                case 1 :
                    // CivlCParser.g:1349:7: ( declarationSpecifiers declarator declarationList_opt LCURLY )=> functionDefinition
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionDefinition_in_externalDeclaration7038);
                    functionDefinition408=functionDefinition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionDefinition408.getTree());

                    }
                    break;
                case 2 :
                    // CivlCParser.g:1351:7: declaration
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declaration_in_externalDeclaration7046);
                    declaration409=declaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, declaration409.getTree());

                    }
                    break;
                case 3 :
                    // CivlCParser.g:1352:7: pragma
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pragma_in_externalDeclaration7054);
                    pragma410=pragma();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pragma410.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "externalDeclaration"


    public static class functionDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionDefinition"
    // CivlCParser.g:1356:1: functionDefinition : declarationSpecifiers declarator declarationList_opt compoundStatement -> ^( FUNCTION_DEFINITION declarationSpecifiers declarator declarationList_opt compoundStatement ) ;
    public final CivlCParser.functionDefinition_return functionDefinition() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CivlCParser.functionDefinition_return retval = new CivlCParser.functionDefinition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.declarationSpecifiers_return declarationSpecifiers411 =null;

        CivlCParser.declarator_return declarator412 =null;

        CivlCParser.declarationList_opt_return declarationList_opt413 =null;

        CivlCParser.compoundStatement_return compoundStatement414 =null;


        RewriteRuleSubtreeStream stream_declarator=new RewriteRuleSubtreeStream(adaptor,"rule declarator");
        RewriteRuleSubtreeStream stream_compoundStatement=new RewriteRuleSubtreeStream(adaptor,"rule compoundStatement");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");
        RewriteRuleSubtreeStream stream_declarationList_opt=new RewriteRuleSubtreeStream(adaptor,"rule declarationList_opt");

            ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();
            ((Symbols_scope)Symbols_stack.peek()).enumerationConstants = new HashSet<String>();
            ((Symbols_scope)Symbols_stack.peek()).isFunctionDefinition = true;

        try {
            // CivlCParser.g:1363:2: ( declarationSpecifiers declarator declarationList_opt compoundStatement -> ^( FUNCTION_DEFINITION declarationSpecifiers declarator declarationList_opt compoundStatement ) )
            // CivlCParser.g:1363:4: declarationSpecifiers declarator declarationList_opt compoundStatement
            {
            pushFollow(FOLLOW_declarationSpecifiers_in_functionDefinition7081);
            declarationSpecifiers411=declarationSpecifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarationSpecifiers.add(declarationSpecifiers411.getTree());

            pushFollow(FOLLOW_declarator_in_functionDefinition7083);
            declarator412=declarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarator.add(declarator412.getTree());

            pushFollow(FOLLOW_declarationList_opt_in_functionDefinition7088);
            declarationList_opt413=declarationList_opt();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarationList_opt.add(declarationList_opt413.getTree());

            pushFollow(FOLLOW_compoundStatement_in_functionDefinition7090);
            compoundStatement414=compoundStatement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_compoundStatement.add(compoundStatement414.getTree());

            // AST REWRITE
            // elements: declarator, compoundStatement, declarationList_opt, declarationSpecifiers
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1365:4: -> ^( FUNCTION_DEFINITION declarationSpecifiers declarator declarationList_opt compoundStatement )
            {
                // CivlCParser.g:1365:7: ^( FUNCTION_DEFINITION declarationSpecifiers declarator declarationList_opt compoundStatement )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(FUNCTION_DEFINITION, "FUNCTION_DEFINITION")
                , root_1);

                adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());

                adaptor.addChild(root_1, stream_declarator.nextTree());

                adaptor.addChild(root_1, stream_declarationList_opt.nextTree());

                adaptor.addChild(root_1, stream_compoundStatement.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "functionDefinition"


    public static class declarationList_opt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarationList_opt"
    // CivlCParser.g:1374:1: declarationList_opt : ( declaration )* -> ^( DECLARATION_LIST ( declaration )* ) ;
    public final CivlCParser.declarationList_opt_return declarationList_opt() throws RecognitionException {
        CivlCParser.declarationList_opt_return retval = new CivlCParser.declarationList_opt_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        CivlCParser.declaration_return declaration415 =null;


        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // CivlCParser.g:1375:2: ( ( declaration )* -> ^( DECLARATION_LIST ( declaration )* ) )
            // CivlCParser.g:1375:4: ( declaration )*
            {
            // CivlCParser.g:1375:4: ( declaration )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==ALIGNAS||(LA97_0 >= ATOMIC && LA97_0 <= AUTO)||LA97_0==BOOL||LA97_0==CHAR||(LA97_0 >= COMPLEX && LA97_0 <= CONST)||LA97_0==DOUBLE||LA97_0==ENUM||LA97_0==EXTERN||LA97_0==FLOAT||LA97_0==IDENTIFIER||(LA97_0 >= INLINE && LA97_0 <= INT)||LA97_0==LONG||LA97_0==NORETURN||LA97_0==OUTPUT||LA97_0==PROC||(LA97_0 >= REGISTER && LA97_0 <= RESTRICT)||(LA97_0 >= SHORT && LA97_0 <= SIGNED)||(LA97_0 >= STATIC && LA97_0 <= STATICASSERT)||LA97_0==STRUCT||LA97_0==THREADLOCAL||(LA97_0 >= TYPEDEF && LA97_0 <= UNSIGNED)||(LA97_0 >= VOID && LA97_0 <= VOLATILE)) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // CivlCParser.g:1375:4: declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_declarationList_opt7129);
            	    declaration415=declaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_declaration.add(declaration415.getTree());

            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);


            // AST REWRITE
            // elements: declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1375:17: -> ^( DECLARATION_LIST ( declaration )* )
            {
                // CivlCParser.g:1375:20: ^( DECLARATION_LIST ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DECLARATION_LIST, "DECLARATION_LIST")
                , root_1);

                // CivlCParser.g:1375:39: ( declaration )*
                while ( stream_declaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_declaration.nextTree());

                }
                stream_declaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "declarationList_opt"

    // $ANTLR start synpred1_CivlCParser
    public final void synpred1_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:231:4: ( LPAREN typeName RPAREN LCURLY )
        // CivlCParser.g:231:5: LPAREN typeName RPAREN LCURLY
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred1_CivlCParser1103); if (state.failed) return ;

        pushFollow(FOLLOW_typeName_in_synpred1_CivlCParser1105);
        typeName();

        state._fsp--;
        if (state.failed) return ;

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred1_CivlCParser1107); if (state.failed) return ;

        match(input,LCURLY,FOLLOW_LCURLY_in_synpred1_CivlCParser1109); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_CivlCParser

    // $ANTLR start synpred2_CivlCParser
    public final void synpred2_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:258:4: ( SIZEOF LPAREN typeName )
        // CivlCParser.g:258:5: SIZEOF LPAREN typeName
        {
        match(input,SIZEOF,FOLLOW_SIZEOF_in_synpred2_CivlCParser1319); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred2_CivlCParser1321); if (state.failed) return ;

        pushFollow(FOLLOW_typeName_in_synpred2_CivlCParser1323);
        typeName();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_CivlCParser

    // $ANTLR start synpred3_CivlCParser
    public final void synpred3_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:284:4: ( LPAREN typeName RPAREN )
        // CivlCParser.g:284:5: LPAREN typeName RPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred3_CivlCParser1480); if (state.failed) return ;

        pushFollow(FOLLOW_typeName_in_synpred3_CivlCParser1482);
        typeName();

        state._fsp--;
        if (state.failed) return ;

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred3_CivlCParser1484); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred3_CivlCParser

    // $ANTLR start synpred4_CivlCParser
    public final void synpred4_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:408:4: ( unaryExpression assignmentOperator )
        // CivlCParser.g:408:5: unaryExpression assignmentOperator
        {
        pushFollow(FOLLOW_unaryExpression_in_synpred4_CivlCParser2482);
        unaryExpression();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_assignmentOperator_in_synpred4_CivlCParser2484);
        assignmentOperator();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_CivlCParser

    // $ANTLR start synpred5_CivlCParser
    public final void synpred5_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:519:4: ( typeSpecifier )
        // CivlCParser.g:519:5: typeSpecifier
        {
        pushFollow(FOLLOW_typeSpecifier_in_synpred5_CivlCParser2887);
        typeSpecifier();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_CivlCParser

    // $ANTLR start synpred6_CivlCParser
    public final void synpred6_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:915:4: ( declarator )
        // CivlCParser.g:915:5: declarator
        {
        pushFollow(FOLLOW_declarator_in_synpred6_CivlCParser4869);
        declarator();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred6_CivlCParser

    // $ANTLR start synpred7_CivlCParser
    public final void synpred7_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:1166:7: ( declarationSpecifiers declarator declarationList_opt LCURLY )
        // CivlCParser.g:1166:8: declarationSpecifiers declarator declarationList_opt LCURLY
        {
        pushFollow(FOLLOW_declarationSpecifiers_in_synpred7_CivlCParser6169);
        declarationSpecifiers();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_declarator_in_synpred7_CivlCParser6171);
        declarator();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_declarationList_opt_in_synpred7_CivlCParser6173);
        declarationList_opt();

        state._fsp--;
        if (state.failed) return ;

        match(input,LCURLY,FOLLOW_LCURLY_in_synpred7_CivlCParser6175); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_CivlCParser

    // $ANTLR start synpred8_CivlCParser
    public final void synpred8_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:1202:11: ( ELSE )
        // CivlCParser.g:1202:12: ELSE
        {
        match(input,ELSE,FOLLOW_ELSE_in_synpred8_CivlCParser6302); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_CivlCParser

    // $ANTLR start synpred9_CivlCParser
    public final void synpred9_CivlCParser_fragment() throws RecognitionException {
        // CivlCParser.g:1349:7: ( declarationSpecifiers declarator declarationList_opt LCURLY )
        // CivlCParser.g:1349:8: declarationSpecifiers declarator declarationList_opt LCURLY
        {
        pushFollow(FOLLOW_declarationSpecifiers_in_synpred9_CivlCParser7022);
        declarationSpecifiers();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_declarator_in_synpred9_CivlCParser7024);
        declarator();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_declarationList_opt_in_synpred9_CivlCParser7026);
        declarationList_opt();

        state._fsp--;
        if (state.failed) return ;

        match(input,LCURLY,FOLLOW_LCURLY_in_synpred9_CivlCParser7028); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_CivlCParser

    // Delegated rules

    public final boolean synpred3_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_CivlCParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_CivlCParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA54 dfa54 = new DFA54(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA71 dfa71 = new DFA71(this);
    static final String DFA54_eotS =
        "\7\uffff";
    static final String DFA54_eofS =
        "\7\uffff";
    static final String DFA54_minS =
        "\2\5\1\uffff\1\5\3\uffff";
    static final String DFA54_maxS =
        "\2\u009d\1\uffff\1\u0096\3\uffff";
    static final String DFA54_acceptS =
        "\2\uffff\1\1\1\uffff\1\2\1\3\1\4";
    static final String DFA54_specialS =
        "\7\uffff}>";
    static final String[] DFA54_transitionS = {
            "\2\2\6\uffff\1\1\14\uffff\1\2\6\uffff\1\1\23\uffff\1\2\3\uffff"+
            "\1\2\15\uffff\1\2\3\uffff\1\1\1\uffff\1\2\5\uffff\1\2\5\uffff"+
            "\1\2\5\uffff\1\2\6\uffff\1\1\15\uffff\1\2\1\uffff\1\2\7\uffff"+
            "\1\1\2\uffff\1\2\10\uffff\2\2\1\3\1\uffff\1\4\1\uffff\1\2\1"+
            "\uffff\1\2\3\uffff\1\2\6\uffff\1\1",
            "\2\2\6\uffff\1\1\14\uffff\1\2\6\uffff\1\1\23\uffff\1\2\3\uffff"+
            "\1\2\15\uffff\1\2\3\uffff\1\1\1\uffff\1\2\5\uffff\1\2\5\uffff"+
            "\1\2\5\uffff\1\2\6\uffff\1\1\15\uffff\1\2\1\uffff\1\2\7\uffff"+
            "\1\1\2\uffff\1\2\10\uffff\2\2\1\3\1\uffff\1\5\1\uffff\1\2\1"+
            "\uffff\1\2\3\uffff\1\2\6\uffff\1\1",
            "",
            "\2\2\23\uffff\1\2\32\uffff\1\2\3\uffff\1\2\15\uffff\1\2\5\uffff"+
            "\1\2\5\uffff\1\2\5\uffff\1\2\5\uffff\1\2\24\uffff\1\2\1\uffff"+
            "\1\2\12\uffff\1\6\10\uffff\3\2\3\uffff\1\2\1\uffff\1\2\3\uffff"+
            "\1\2",
            "",
            "",
            ""
    };

    static final short[] DFA54_eot = DFA.unpackEncodedString(DFA54_eotS);
    static final short[] DFA54_eof = DFA.unpackEncodedString(DFA54_eofS);
    static final char[] DFA54_min = DFA.unpackEncodedStringToUnsignedChars(DFA54_minS);
    static final char[] DFA54_max = DFA.unpackEncodedStringToUnsignedChars(DFA54_maxS);
    static final short[] DFA54_accept = DFA.unpackEncodedString(DFA54_acceptS);
    static final short[] DFA54_special = DFA.unpackEncodedString(DFA54_specialS);
    static final short[][] DFA54_transition;

    static {
        int numStates = DFA54_transitionS.length;
        DFA54_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA54_transition[i] = DFA.unpackEncodedString(DFA54_transitionS[i]);
        }
    }

    class DFA54 extends DFA {

        public DFA54(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 54;
            this.eot = DFA54_eot;
            this.eof = DFA54_eof;
            this.min = DFA54_min;
            this.max = DFA54_max;
            this.accept = DFA54_accept;
            this.special = DFA54_special;
            this.transition = DFA54_transition;
        }
        public String getDescription() {
            return "778:4: ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt assignmentExpression_opt RSQUARE ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList_opt assignmentExpression RSQUARE ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX LSQUARE STATIC typeQualifierList assignmentExpression RSQUARE ) | typeQualifierList_opt STAR RSQUARE -> ^( ARRAY_SUFFIX LSQUARE ABSENT typeQualifierList_opt STAR RSQUARE ) )";
        }
    }
    static final String DFA55_eotS =
        "\11\uffff";
    static final String DFA55_eofS =
        "\11\uffff";
    static final String DFA55_minS =
        "\1\4\1\uffff\1\4\1\uffff\1\4\1\0\1\4\1\uffff\1\0";
    static final String DFA55_maxS =
        "\1\u009d\1\uffff\1\u009d\1\uffff\1\u009d\1\0\1\u009d\1\uffff\1\0";
    static final String DFA55_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\3\uffff\1\2\1\uffff";
    static final String DFA55_specialS =
        "\5\uffff\1\1\2\uffff\1\0}>";
    static final String[] DFA55_transitionS = {
            "\1\1\10\uffff\2\1\5\uffff\1\1\4\uffff\1\1\6\uffff\2\1\7\uffff"+
            "\1\1\5\uffff\1\1\1\uffff\1\1\2\uffff\1\1\22\uffff\1\2\2\uffff"+
            "\3\1\5\uffff\1\1\13\uffff\1\1\7\uffff\1\1\22\uffff\1\1\3\uffff"+
            "\2\1\1\uffff\1\3\7\uffff\2\1\4\uffff\1\1\2\uffff\1\1\3\uffff"+
            "\1\1\1\uffff\3\1\2\uffff\2\1",
            "",
            "\1\1\10\uffff\2\1\5\uffff\1\1\4\uffff\1\1\4\uffff\1\4\1\uffff"+
            "\2\1\7\uffff\1\1\5\uffff\1\1\1\uffff\1\1\2\uffff\1\1\22\uffff"+
            "\1\1\2\uffff\3\1\5\uffff\3\1\11\uffff\1\1\7\uffff\1\1\22\uffff"+
            "\1\1\3\uffff\2\1\1\uffff\1\5\7\uffff\2\1\2\uffff\1\1\1\uffff"+
            "\1\1\2\uffff\1\1\3\uffff\1\1\1\uffff\3\1\2\uffff\2\1",
            "",
            "\1\1\10\uffff\2\1\5\uffff\1\1\4\uffff\1\1\6\uffff\2\1\7\uffff"+
            "\1\1\3\uffff\1\1\1\uffff\1\1\1\uffff\1\1\2\uffff\1\1\22\uffff"+
            "\1\6\2\uffff\3\1\5\uffff\1\1\13\uffff\1\1\7\uffff\1\1\22\uffff"+
            "\1\1\3\uffff\2\1\11\uffff\2\1\4\uffff\1\1\2\uffff\1\1\3\uffff"+
            "\1\1\1\uffff\3\1\2\uffff\2\1",
            "\1\uffff",
            "\1\1\10\uffff\2\1\5\uffff\1\1\4\uffff\1\1\4\uffff\1\4\1\uffff"+
            "\2\1\7\uffff\1\1\5\uffff\1\1\1\uffff\1\1\2\uffff\1\1\22\uffff"+
            "\1\1\2\uffff\3\1\5\uffff\3\1\11\uffff\1\1\7\uffff\1\1\22\uffff"+
            "\1\1\3\uffff\2\1\1\uffff\1\10\7\uffff\2\1\2\uffff\1\1\1\uffff"+
            "\1\1\2\uffff\1\1\3\uffff\1\1\1\uffff\3\1\2\uffff\2\1",
            "",
            "\1\uffff"
    };

    static final short[] DFA55_eot = DFA.unpackEncodedString(DFA55_eotS);
    static final short[] DFA55_eof = DFA.unpackEncodedString(DFA55_eofS);
    static final char[] DFA55_min = DFA.unpackEncodedStringToUnsignedChars(DFA55_minS);
    static final char[] DFA55_max = DFA.unpackEncodedStringToUnsignedChars(DFA55_maxS);
    static final short[] DFA55_accept = DFA.unpackEncodedString(DFA55_acceptS);
    static final short[] DFA55_special = DFA.unpackEncodedString(DFA55_specialS);
    static final short[][] DFA55_transition;

    static {
        int numStates = DFA55_transitionS.length;
        DFA55_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA55_transition[i] = DFA.unpackEncodedString(DFA55_transitionS[i]);
        }
    }

    class DFA55 extends DFA {

        public DFA55(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 55;
            this.eot = DFA55_eot;
            this.eof = DFA55_eof;
            this.min = DFA55_min;
            this.max = DFA55_max;
            this.accept = DFA55_accept;
            this.special = DFA55_special;
            this.transition = DFA55_transition;
        }
        public String getDescription() {
            return "801:4: ( parameterTypeList RPAREN -> ^( FUNCTION_SUFFIX LPAREN parameterTypeList RPAREN ) | identifierList RPAREN -> ^( FUNCTION_SUFFIX LPAREN identifierList RPAREN ) | RPAREN -> ^( FUNCTION_SUFFIX LPAREN ABSENT RPAREN ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA55_8 = input.LA(1);

                         
                        int index55_8 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index55_8);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA55_5 = input.LA(1);

                         
                        int index55_5 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((((!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI )&&(!((DeclarationScope_scope)DeclarationScope_stack.peek()).isTypedef || input.LT(2).getType() != SEMI ))&&(isTypeName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index55_5);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 55, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA67_eotS =
        "\6\uffff";
    static final String DFA67_eofS =
        "\1\uffff\1\4\1\uffff\1\4\2\uffff";
    static final String DFA67_minS =
        "\1\123\1\15\1\uffff\1\15\2\uffff";
    static final String DFA67_maxS =
        "\1\u008c\1\u009d\1\uffff\1\u009d\2\uffff";
    static final String DFA67_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\1\3";
    static final String DFA67_specialS =
        "\6\uffff}>";
    static final String[] DFA67_transitionS = {
            "\2\2\67\uffff\1\1",
            "\1\3\17\uffff\2\4\2\uffff\1\3\51\uffff\1\3\7\uffff\2\5\21\uffff"+
            "\1\3\27\uffff\1\3\1\uffff\1\4\13\uffff\1\1\20\uffff\1\3",
            "",
            "\1\3\17\uffff\2\4\2\uffff\1\3\51\uffff\1\3\7\uffff\2\5\21\uffff"+
            "\1\3\27\uffff\1\3\1\uffff\1\4\13\uffff\1\1\20\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "951:1: abstractDeclarator : ( pointer -> ^( ABSTRACT_DECLARATOR pointer ABSENT ) | directAbstractDeclarator -> ^( ABSTRACT_DECLARATOR ABSENT directAbstractDeclarator ) | pointer directAbstractDeclarator -> ^( ABSTRACT_DECLARATOR pointer directAbstractDeclarator ) );";
        }
    }
    static final String DFA71_eotS =
        "\7\uffff";
    static final String DFA71_eofS =
        "\7\uffff";
    static final String DFA71_minS =
        "\2\5\1\uffff\1\5\3\uffff";
    static final String DFA71_maxS =
        "\2\u009d\1\uffff\1\u0096\3\uffff";
    static final String DFA71_acceptS =
        "\2\uffff\1\1\1\uffff\1\2\1\3\1\4";
    static final String DFA71_specialS =
        "\7\uffff}>";
    static final String[] DFA71_transitionS = {
            "\2\2\6\uffff\1\1\14\uffff\1\2\6\uffff\1\1\23\uffff\1\2\3\uffff"+
            "\1\2\15\uffff\1\2\3\uffff\1\1\1\uffff\1\2\5\uffff\1\2\5\uffff"+
            "\1\2\5\uffff\1\2\6\uffff\1\1\15\uffff\1\2\1\uffff\1\2\7\uffff"+
            "\1\1\2\uffff\1\2\10\uffff\2\2\1\3\1\uffff\1\4\1\uffff\1\2\1"+
            "\uffff\1\2\3\uffff\1\2\6\uffff\1\1",
            "\2\2\6\uffff\1\1\14\uffff\1\2\6\uffff\1\1\23\uffff\1\2\3\uffff"+
            "\1\2\15\uffff\1\2\3\uffff\1\1\1\uffff\1\2\5\uffff\1\2\5\uffff"+
            "\1\2\5\uffff\1\2\6\uffff\1\1\15\uffff\1\2\1\uffff\1\2\7\uffff"+
            "\1\1\2\uffff\1\2\10\uffff\3\2\1\uffff\1\5\1\uffff\1\2\1\uffff"+
            "\1\2\3\uffff\1\2\6\uffff\1\1",
            "",
            "\2\2\23\uffff\1\2\32\uffff\1\2\3\uffff\1\2\15\uffff\1\2\5\uffff"+
            "\1\2\5\uffff\1\2\5\uffff\1\2\5\uffff\1\2\24\uffff\1\2\1\uffff"+
            "\1\2\12\uffff\1\6\10\uffff\3\2\3\uffff\1\2\1\uffff\1\2\3\uffff"+
            "\1\2",
            "",
            "",
            ""
    };

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "1017:7: ( typeQualifierList_opt assignmentExpression_opt RSQUARE -> ^( ARRAY_SUFFIX ABSENT typeQualifierList_opt assignmentExpression_opt ) | STATIC typeQualifierList_opt assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList_opt assignmentExpression ) | typeQualifierList STATIC assignmentExpression RSQUARE -> ^( ARRAY_SUFFIX STATIC typeQualifierList assignmentExpression ) | STAR RSQUARE -> ^( ARRAY_SUFFIX ABSENT ABSENT STAR ) )";
        }
    }
 

    public static final BitSet FOLLOW_enumerationConstant_in_constant584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_constant589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATING_CONSTANT_in_constant594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_CONSTANT_in_constant599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumerationConstant612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_primaryExpression636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_primaryExpression645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_primaryExpression650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primaryExpression655 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_primaryExpression657 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_primaryExpression659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genericSelection_in_primaryExpression680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENERIC_in_genericSelection694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_genericSelection696 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_genericSelection698 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_genericSelection700 = new BitSet(new long[]{0x0010820B02102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_genericAssocList_in_genericSelection702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_genericSelection707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genericAssociation_in_genericAssocList733 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_genericAssocList736 = new BitSet(new long[]{0x0010820B02102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_genericAssociation_in_genericAssocList738 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_typeName_in_genericAssociation765 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_COLON_in_genericAssociation767 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_genericAssociation769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_genericAssociation787 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_COLON_in_genericAssociation789 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_genericAssociation791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixExpressionRoot_in_postfixExpression818 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_LSQUARE_in_postfixExpression832 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_postfixExpression834 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_postfixExpression836 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_LPAREN_in_postfixExpression904 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C01L});
    public static final BitSet FOLLOW_argumentExpressionList_in_postfixExpression906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_postfixExpression908 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_DOT_in_postfixExpression935 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_postfixExpression937 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_ARROW_in_postfixExpression960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_postfixExpression962 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_PLUSPLUS_in_postfixExpression987 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_AT_in_postfixExpression1025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_postfixExpression1027 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_MINUSMINUS_in_postfixExpression1052 = new BitSet(new long[]{0x0000010000001102L,0x0040000002180000L});
    public static final BitSet FOLLOW_LPAREN_in_postfixExpressionRoot1116 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_postfixExpressionRoot1118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_postfixExpressionRoot1120 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_postfixExpressionRoot1122 = new BitSet(new long[]{0x0220010004000060L,0x00500000821A2080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_initializerList_in_postfixExpressionRoot1124 = new BitSet(new long[]{0x0000000040000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_postfixExpressionRoot1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_postfixExpressionRoot1137 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_postfixExpressionRoot1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_postfixExpressionRoot1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentExpression_in_argumentExpressionList1187 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_argumentExpressionList1190 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_argumentExpressionList1192 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_postfixExpression_in_unaryExpression1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSPLUS_in_unaryExpression1226 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUSMINUS_in_unaryExpression1261 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryOperator_in_unaryExpression1294 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_castExpression_in_unaryExpression1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZEOF_in_unaryExpression1327 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_unaryExpression1329 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_unaryExpression1331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_unaryExpression1333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZEOF_in_unaryExpression1351 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALIGNOF_in_unaryExpression1371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_unaryExpression1373 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_unaryExpression1375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_unaryExpression1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spawnExpression_in_unaryExpression1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPAWN_in_spawnExpression1405 = new BitSet(new long[]{0x0220000004000000L,0x0000000000082080L,0x0000000000010000L});
    public static final BitSet FOLLOW_postfixExpressionRoot_in_spawnExpression1407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_spawnExpression1409 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C01L});
    public static final BitSet FOLLOW_argumentExpressionList_in_spawnExpression1415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_spawnExpression1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_castExpression1490 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_castExpression1492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_castExpression1494 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_castExpression_in_castExpression1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_castExpression1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression1531 = new BitSet(new long[]{0x0000002000000002L,0x0000000004000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression1541 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression1545 = new BitSet(new long[]{0x0000002000000002L,0x0000000004000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression1571 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression1575 = new BitSet(new long[]{0x0000002000000002L,0x0000000004000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_MOD_in_multiplicativeExpression1608 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression1612 = new BitSet(new long[]{0x0000002000000002L,0x0000000004000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1658 = new BitSet(new long[]{0x0000000000000002L,0x0010000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_PLUS_in_additiveExpression1675 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1679 = new BitSet(new long[]{0x0000000000000002L,0x0010000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SUB_in_additiveExpression1719 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1723 = new BitSet(new long[]{0x0000000000000002L,0x0010000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression1776 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000050L});
    public static final BitSet FOLLOW_SHIFTLEFT_in_shiftExpression1793 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression1797 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000050L});
    public static final BitSet FOLLOW_SHIFTRIGHT_in_shiftExpression1837 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression1841 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000050L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression1895 = new BitSet(new long[]{0x1800000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_relationalOperator_in_relationalExpression1908 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression1912 = new BitSet(new long[]{0x1800000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1979 = new BitSet(new long[]{0x0001000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_equalityOperator_in_equalityExpression1992 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1996 = new BitSet(new long[]{0x0001000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression2055 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_AMPERSAND_in_andExpression2068 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression2072 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression2116 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_BITXOR_in_exclusiveOrExpression2129 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression2133 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2177 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_BITOR_in_inclusiveOrExpression2190 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2194 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression2238 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_AND_in_logicalAndExpression2251 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression2255 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression2299 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_OR_in_logicalOrExpression2312 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression2316 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_logicalOrExpression_in_conditionalExpression2360 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_QMARK_in_conditionalExpression2376 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_conditionalExpression2378 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_COLON_in_conditionalExpression2380 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_conditionalExpression_in_conditionalExpression2382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_assignmentExpression2491 = new BitSet(new long[]{0x00000040000A8400L,0x0020000008000000L,0x00000000000820A0L});
    public static final BitSet FOLLOW_assignmentOperator_in_assignmentExpression2493 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression2495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_assignmentExpression2527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentExpression_in_commaExpression2598 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_commaExpression2610 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_commaExpression2614 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLLECTIVE_in_expression2654 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_expression2656 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_commaExpression_in_expression2658 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_expression2660 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_commaExpression_in_expression2662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_commaExpression_in_expression2680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_constantExpression2693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_declaration2721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L,0x0000000000001008L});
    public static final BitSet FOLLOW_initDeclaratorList_in_declaration2736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_declaration2779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_staticAssertDeclaration_in_declaration2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifierList_in_declarationSpecifiers2799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifier_in_declarationSpecifierList2839 = new BitSet(new long[]{0x0012820302106012L,0x6200004040041C80L,0x0000000033A24300L});
    public static final BitSet FOLLOW_storageClassSpecifier_in_declarationSpecifier2858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeSpecifierOrQualifier_in_declarationSpecifier2863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionSpecifier_in_declarationSpecifier2868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alignmentSpecifier_in_declarationSpecifier2873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeSpecifier_in_typeSpecifierOrQualifier2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifier_in_typeSpecifierOrQualifier2903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_initDeclarator_in_initDeclaratorList2918 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_initDeclaratorList2921 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L,0x0000000000001000L});
    public static final BitSet FOLLOW_initDeclarator_in_initDeclaratorList2925 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_declarator_in_initDeclarator2955 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ASSIGN_in_initDeclarator2980 = new BitSet(new long[]{0x0220000004000060L,0x00500000820A2080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_initializer_in_initDeclarator2984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPEDEF_in_storageClassSpecifier3015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_storageClassSpecifier3022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_typeSpecifier3053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_typeSpecifier3057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_typeSpecifier3061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_typeSpecifier3065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_typeSpecifier3069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_typeSpecifier3073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_typeSpecifier3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIGNED_in_typeSpecifier3082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNSIGNED_in_typeSpecifier3086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_typeSpecifier3090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPLEX_in_typeSpecifier3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROC_in_typeSpecifier3099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicTypeSpecifier_in_typeSpecifier3104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structOrUnionSpecifier_in_typeSpecifier3109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumSpecifier_in_typeSpecifier3114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedefName_in_typeSpecifier3119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structOrUnion_in_structOrUnionSpecifier3132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_structOrUnionSpecifier3141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_structOrUnionSpecifier3143 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033028300L});
    public static final BitSet FOLLOW_structDeclarationList_in_structOrUnionSpecifier3145 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_structOrUnionSpecifier3147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_structOrUnionSpecifier3175 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033028300L});
    public static final BitSet FOLLOW_structDeclarationList_in_structOrUnionSpecifier3177 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_structOrUnionSpecifier3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_structOrUnionSpecifier3207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structDeclaration_in_structDeclarationList3261 = new BitSet(new long[]{0x0010820302102002L,0x4200004000041880L,0x0000000033028300L});
    public static final BitSet FOLLOW_specifierQualifierList_in_structDeclaration3302 = new BitSet(new long[]{0x0000000020000000L,0x0000000000080080L,0x0000000000001008L});
    public static final BitSet FOLLOW_structDeclaratorList_in_structDeclaration3331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_structDeclaration3366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_staticAssertDeclaration_in_structDeclaration3374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeSpecifierOrQualifier_in_specifierQualifierList3393 = new BitSet(new long[]{0x0010820302102002L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_structDeclarator_in_structDeclaratorList3430 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_structDeclaratorList3433 = new BitSet(new long[]{0x0000000020000000L,0x0000000000080080L,0x0000000000001000L});
    public static final BitSet FOLLOW_structDeclarator_in_structDeclaratorList3437 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_declarator_in_structDeclarator3474 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_structDeclarator3503 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_constantExpression_in_structDeclarator3505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_structDeclarator3540 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_constantExpression_in_structDeclarator3542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enumSpecifier3577 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumSpecifier3590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumSpecifier3623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_enumSpecifier3625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_enumeratorList_in_enumSpecifier3627 = new BitSet(new long[]{0x0000000040000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_enumSpecifier3629 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_enumSpecifier3632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_enumSpecifier3664 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_enumeratorList_in_enumSpecifier3666 = new BitSet(new long[]{0x0000000040000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_enumSpecifier3668 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_enumSpecifier3671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumerator_in_enumeratorList3720 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_enumeratorList3723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_enumerator_in_enumeratorList3725 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumerator3758 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ASSIGN_in_enumerator3799 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_constantExpression_in_enumerator3801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATOMIC_in_atomicTypeSpecifier3847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_atomicTypeSpecifier3849 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_atomicTypeSpecifier3851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_atomicTypeSpecifier3853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALIGNAS_in_alignmentSpecifier3952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_alignmentSpecifier3954 = new BitSet(new long[]{0x0230820306102060L,0x42500040820C3880L,0x0000000033471F00L});
    public static final BitSet FOLLOW_typeName_in_alignmentSpecifier3967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_alignmentSpecifier3969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constantExpression_in_alignmentSpecifier4001 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_alignmentSpecifier4003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directDeclarator_in_declarator4051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_declarator4070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L});
    public static final BitSet FOLLOW_directDeclarator_in_declarator4074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directDeclaratorPrefix_in_directDeclarator4103 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_directDeclaratorSuffix_in_directDeclarator4126 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_directDeclaratorPrefix4157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_directDeclaratorPrefix4167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L,0x0000000000001000L});
    public static final BitSet FOLLOW_declarator_in_directDeclaratorPrefix4170 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_directDeclaratorPrefix4172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directDeclaratorArraySuffix_in_directDeclaratorSuffix4185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directDeclaratorFunctionSuffix_in_directDeclaratorSuffix4190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LSQUARE_in_directDeclaratorArraySuffix4203 = new BitSet(new long[]{0x0220000204002060L,0x4050004082082880L,0x0000000020455C02L});
    public static final BitSet FOLLOW_typeQualifierList_opt_in_directDeclaratorArraySuffix4210 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C02L});
    public static final BitSet FOLLOW_assignmentExpression_opt_in_directDeclaratorArraySuffix4212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_directDeclaratorArraySuffix4252 = new BitSet(new long[]{0x0220000204002060L,0x4050004082082880L,0x0000000020451C00L});
    public static final BitSet FOLLOW_typeQualifierList_opt_in_directDeclaratorArraySuffix4254 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_directDeclaratorArraySuffix4256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifierList_in_directDeclaratorArraySuffix4298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_STATIC_in_directDeclaratorArraySuffix4300 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_directDeclaratorArraySuffix4302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifierList_opt_in_directDeclaratorArraySuffix4344 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_STAR_in_directDeclaratorArraySuffix4346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directDeclaratorArraySuffix4348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_directDeclaratorFunctionSuffix4397 = new BitSet(new long[]{0x0012820302106010L,0x6200004040041C80L,0x0000000033A24301L});
    public static final BitSet FOLLOW_parameterTypeList_in_directDeclaratorFunctionSuffix4404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_directDeclaratorFunctionSuffix4406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierList_in_directDeclaratorFunctionSuffix4432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_directDeclaratorFunctionSuffix4434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_directDeclaratorFunctionSuffix4458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifier_in_typeQualifierList_opt4488 = new BitSet(new long[]{0x0000000200002002L,0x4000004000000800L,0x0000000020000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression_opt4519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_part_in_pointer4535 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_STAR_in_pointer_part4561 = new BitSet(new long[]{0x0000000200002000L,0x4000004000000800L,0x0000000020000000L});
    public static final BitSet FOLLOW_typeQualifierList_opt_in_pointer_part4563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifier_in_typeQualifierList4587 = new BitSet(new long[]{0x0000000200002002L,0x4000004000000800L,0x0000000020000000L});
    public static final BitSet FOLLOW_parameterTypeListWithoutScope_in_parameterTypeList4617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameterTypeListWithScope_in_parameterTypeList4622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameterTypeListWithoutScope_in_parameterTypeListWithScope4643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameterList_in_parameterTypeListWithoutScope4657 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_parameterTypeListWithoutScope4685 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ELLIPSIS_in_parameterTypeListWithoutScope4687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameterDeclaration_in_parameterList4732 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_parameterList4735 = new BitSet(new long[]{0x0012820302106010L,0x6200004040041C80L,0x0000000033A24300L});
    public static final BitSet FOLLOW_parameterDeclaration_in_parameterList4737 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_parameterDeclaration4783 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180080L,0x0000000000001000L});
    public static final BitSet FOLLOW_declaratorOrAbstractDeclarator_in_parameterDeclaration4811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarator_in_declaratorOrAbstractDeclarator4873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractDeclarator_in_declaratorOrAbstractDeclarator4878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierList4896 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_identifierList4900 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierList4902 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_specifierQualifierList_in_typeName4939 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L,0x0000000000001000L});
    public static final BitSet FOLLOW_abstractDeclarator_in_typeName4967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_abstractDeclarator5012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directAbstractDeclarator_in_abstractDeclarator5036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_abstractDeclarator5060 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_directAbstractDeclarator_in_abstractDeclarator5062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_directAbstractDeclarator5097 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L,0x0000000000001000L});
    public static final BitSet FOLLOW_abstractDeclarator_in_directAbstractDeclarator5099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_directAbstractDeclarator5101 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_directAbstractDeclaratorSuffix_in_directAbstractDeclarator5103 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_directAbstractDeclaratorSuffix_in_directAbstractDeclarator5140 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_typedefName5180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LSQUARE_in_directAbstractDeclaratorSuffix5213 = new BitSet(new long[]{0x0220000204002060L,0x4050004082082880L,0x0000000020455C02L});
    public static final BitSet FOLLOW_typeQualifierList_opt_in_directAbstractDeclaratorSuffix5223 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C02L});
    public static final BitSet FOLLOW_assignmentExpression_opt_in_directAbstractDeclaratorSuffix5225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_directAbstractDeclaratorSuffix5270 = new BitSet(new long[]{0x0220000204002060L,0x4050004082082880L,0x0000000020451C00L});
    public static final BitSet FOLLOW_typeQualifierList_opt_in_directAbstractDeclaratorSuffix5272 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_directAbstractDeclaratorSuffix5274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifierList_in_directAbstractDeclaratorSuffix5319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_STATIC_in_directAbstractDeclaratorSuffix5321 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_assignmentExpression_in_directAbstractDeclaratorSuffix5323 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_directAbstractDeclaratorSuffix5355 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_directAbstractDeclaratorSuffix5357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_directAbstractDeclaratorSuffix5393 = new BitSet(new long[]{0x0012820302106010L,0x6200004040041C80L,0x0000000033A24301L});
    public static final BitSet FOLLOW_parameterTypeList_in_directAbstractDeclaratorSuffix5403 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_directAbstractDeclaratorSuffix5405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_directAbstractDeclaratorSuffix5431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentExpression_in_initializer5474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_initializer5490 = new BitSet(new long[]{0x0220010004000060L,0x00500000821A2080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_initializerList_in_initializer5492 = new BitSet(new long[]{0x0000000040000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_initializer5506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_initializer5520 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_initializer5522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_designatedInitializer_in_initializerList5561 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_initializerList5564 = new BitSet(new long[]{0x0220010004000060L,0x00500000821A2080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_designatedInitializer_in_initializerList5566 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_initializer_in_designatedInitializer5597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_designation_in_designatedInitializer5615 = new BitSet(new long[]{0x0220000004000060L,0x00500000820A2080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_initializer_in_designatedInitializer5617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_designatorList_in_designation5646 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ASSIGN_in_designation5648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_designator_in_designatorList5675 = new BitSet(new long[]{0x0000010000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_LSQUARE_in_designator5695 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_constantExpression_in_designator5697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_RSQUARE_in_designator5699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_designator5721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_designator5723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATICASSERT_in_staticAssertDeclaration5756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_staticAssertDeclaration5758 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_constantExpression_in_staticAssertDeclaration5760 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_staticAssertDeclaration5762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_staticAssertDeclaration5764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_staticAssertDeclaration5772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_staticAssertDeclaration5774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_labeledStatement_in_statement5813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compoundStatement_in_statement5821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionStatement_in_statement5829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectionStatement_in_statement5837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iterationStatement_in_statement5845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jumpStatement_in_statement5853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pragma_in_statement5861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assertStatement_in_statement5869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assumeStatement_in_statement5877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_waitStatement_in_statement5885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whenStatement_in_statement5893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_chooseStatement_in_statement5901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statementWithScope5925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_labeledStatement5941 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_COLON_in_labeledStatement5943 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statement_in_labeledStatement5945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_labeledStatement5969 = new BitSet(new long[]{0x0220000004000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_constantExpression_in_labeledStatement5971 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_COLON_in_labeledStatement5973 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statement_in_labeledStatement5975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_labeledStatement6001 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_COLON_in_labeledStatement6003 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statement_in_labeledStatement6005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_compoundStatement6050 = new BitSet(new long[]{0x0672828F1EB06A70L,0xF3500040C20E3D80L,0x00000001F3F7DF08L});
    public static final BitSet FOLLOW_RCURLY_in_compoundStatement6060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockItemList_in_compoundStatement6090 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compoundStatement6092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockItem_in_blockItemList6139 = new BitSet(new long[]{0x0672828F1EB06A72L,0xE3500040C20E3D80L,0x00000001F3F7DF08L});
    public static final BitSet FOLLOW_functionDefinition_in_blockItem6185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_blockItem6193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_blockItem6201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionStatement6220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_expressionStatement6222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_expressionStatement6240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_selectionStatement6279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_selectionStatement6281 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_selectionStatement6283 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_selectionStatement6285 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_selectionStatement6289 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ELSE_in_selectionStatement6306 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_selectionStatement6310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_selectionStatement6375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_selectionStatement6377 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_selectionStatement6379 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_selectionStatement6381 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_selectionStatement6385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_iterationStatement6428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_iterationStatement6430 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_iterationStatement6432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_iterationStatement6434 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A6180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_invariant_opt_in_iterationStatement6436 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_iterationStatement6444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_iterationStatement6465 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_iterationStatement6469 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_WHILE_in_iterationStatement6471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_iterationStatement6473 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_iterationStatement6475 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_iterationStatement6477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L,0x0000000000000008L});
    public static final BitSet FOLLOW_invariant_opt_in_iterationStatement6483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_iterationStatement6485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_iterationStatement6506 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_iterationStatement6508 = new BitSet(new long[]{0x0232820316106070L,0x62500040C20C3C80L,0x0000000033E7DF08L});
    public static final BitSet FOLLOW_declaration_in_iterationStatement6518 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C08L});
    public static final BitSet FOLLOW_expression_opt_in_iterationStatement6522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_iterationStatement6524 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C01L});
    public static final BitSet FOLLOW_expression_opt_in_iterationStatement6528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_iterationStatement6535 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A6180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_invariant_opt_in_iterationStatement6539 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_iterationStatement6543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_opt_in_iterationStatement6578 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_iterationStatement6580 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C08L});
    public static final BitSet FOLLOW_expression_opt_in_iterationStatement6584 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_iterationStatement6586 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C01L});
    public static final BitSet FOLLOW_expression_opt_in_iterationStatement6595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_iterationStatement6597 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statementWithScope_in_iterationStatement6601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expression_opt6643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVARIANT_in_invariant_opt6668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_invariant_opt6670 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_invariant_opt6672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_invariant_opt6674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GOTO_in_jumpStatement6701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_jumpStatement6703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_jumpStatement6705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_jumpStatement6723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_jumpStatement6725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_jumpStatement6741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_jumpStatement6743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_jumpStatement6759 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C08L});
    public static final BitSet FOLLOW_expression_opt_in_jumpStatement6761 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_jumpStatement6763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRAGMA_in_pragma6788 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_IDENTIFIER_in_pragma6790 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0xFFFFFFFFFFFFFFFFL,0xFFFFFFFFFFFFFFFFL,0x00000003FFFFFFFFL});
    public static final BitSet FOLLOW_pragmaBody_in_pragma6792 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_NEWLINE_in_pragma6794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_assertStatement6839 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_assertStatement6841 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_assertStatement6843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSUME_in_assumeStatement6862 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_assumeStatement6864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_assumeStatement6866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WAIT_in_waitStatement6885 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_waitStatement6887 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_SEMI_in_waitStatement6889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHEN_in_whenStatement6908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_whenStatement6910 = new BitSet(new long[]{0x0220000014000060L,0x0050000082082080L,0x0000000000451C00L});
    public static final BitSet FOLLOW_expression_in_whenStatement6912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_whenStatement6914 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statement_in_whenStatement6916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHOOSE_in_chooseStatement6939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_chooseStatement6941 = new BitSet(new long[]{0x0660008C1CA00A60L,0x81500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_statement_in_chooseStatement6943 = new BitSet(new long[]{0x0660008C1CA00A60L,0x91500000820A2180L,0x00000001C0551C08L});
    public static final BitSet FOLLOW_RCURLY_in_chooseStatement6946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalDeclaration_in_translationUnit6991 = new BitSet(new long[]{0x0012820302106010L,0x6300004040041C80L,0x0000000033A2C300L});
    public static final BitSet FOLLOW_EOF_in_translationUnit6994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDefinition_in_externalDeclaration7038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_externalDeclaration7046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pragma_in_externalDeclaration7054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_functionDefinition7081 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L,0x0000000000001000L});
    public static final BitSet FOLLOW_declarator_in_functionDefinition7083 = new BitSet(new long[]{0x0012820302106010L,0x6200004040061C80L,0x0000000033A2C300L});
    public static final BitSet FOLLOW_declarationList_opt_in_functionDefinition7088 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_compoundStatement_in_functionDefinition7090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarationList_opt7129 = new BitSet(new long[]{0x0012820302106012L,0x6200004040041C80L,0x0000000033A2C300L});
    public static final BitSet FOLLOW_LPAREN_in_synpred1_CivlCParser1103 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_synpred1_CivlCParser1105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_synpred1_CivlCParser1107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_synpred1_CivlCParser1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZEOF_in_synpred2_CivlCParser1319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred2_CivlCParser1321 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_synpred2_CivlCParser1323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred3_CivlCParser1480 = new BitSet(new long[]{0x0010820302102000L,0x4200004000041880L,0x0000000033020300L});
    public static final BitSet FOLLOW_typeName_in_synpred3_CivlCParser1482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_synpred3_CivlCParser1484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_synpred4_CivlCParser2482 = new BitSet(new long[]{0x00000040000A8400L,0x0020000008000000L,0x00000000000820A0L});
    public static final BitSet FOLLOW_assignmentOperator_in_synpred4_CivlCParser2484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeSpecifier_in_synpred5_CivlCParser2887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarator_in_synpred6_CivlCParser4869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_synpred7_CivlCParser6169 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L,0x0000000000001000L});
    public static final BitSet FOLLOW_declarator_in_synpred7_CivlCParser6171 = new BitSet(new long[]{0x0012820302106010L,0x6200004040061C80L,0x0000000033A2C300L});
    public static final BitSet FOLLOW_declarationList_opt_in_synpred7_CivlCParser6173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_synpred7_CivlCParser6175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_synpred8_CivlCParser6302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_synpred9_CivlCParser7022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080080L,0x0000000000001000L});
    public static final BitSet FOLLOW_declarator_in_synpred9_CivlCParser7024 = new BitSet(new long[]{0x0012820302106010L,0x6200004040061C80L,0x0000000033A2C300L});
    public static final BitSet FOLLOW_declarationList_opt_in_synpred9_CivlCParser7026 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_synpred9_CivlCParser7028 = new BitSet(new long[]{0x0000000000000002L});

}