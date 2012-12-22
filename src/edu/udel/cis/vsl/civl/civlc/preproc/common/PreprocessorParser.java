// $ANTLR 3.4 PreprocessorParser.g 2012-12-22 16:01:02

package edu.udel.cis.vsl.civl.civlc.preproc.common;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class PreprocessorParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALIGNAS", "ALIGNOF", "AMPERSAND", "AND", "ARROW", "ASSERT", "ASSIGN", "ASSUME", "AT", "ATOMIC", "AUTO", "BITANDEQ", "BITOR", "BITOREQ", "BITXOR", "BITXOREQ", "BOOL", "BREAK", "BinaryExponentPart", "CASE", "CChar", "CHAR", "CHARACTER_CONSTANT", "CHOOSE", "COLLECTIVE", "COLON", "COMMA", "COMMENT", "COMPLEX", "CONST", "CONTINUE", "DEFAULT", "DEFINED", "DIV", "DIVEQ", "DO", "DOT", "DOUBLE", "DecimalConstant", "DecimalFloatingConstant", "Digit", "ELLIPSIS", "ELSE", "ENUM", "EQUALS", "EXTERN", "EscapeSequence", "ExponentPart", "FLOAT", "FLOATING_CONSTANT", "FOR", "FloatingSuffix", "FractionalConstant", "GENERIC", "GOTO", "GT", "GTE", "HASH", "HASHHASH", "HEADER_NAME", "HexEscape", "HexFractionalConstant", "HexPrefix", "HexQuad", "HexadecimalConstant", "HexadecimalDigit", "HexadecimalFloatingConstant", "IDENTIFIER", "IF", "IMAGINARY", "INLINE", "INPUT", "INT", "INTEGER_CONSTANT", "INVARIANT", "IdentifierNonDigit", "IntegerSuffix", "LCURLY", "LONG", "LPAREN", "LSQUARE", "LT", "LTE", "LongLongSuffix", "LongSuffix", "MINUSMINUS", "MOD", "MODEQ", "NEQ", "NEWLINE", "NORETURN", "NOT", "NewLine", "NonDigit", "NonZeroDigit", "NotLineStart", "OR", "OTHER", "OUTPUT", "OctalConstant", "OctalDigit", "OctalEscape", "PDEFINE", "PELIF", "PELSE", "PENDIF", "PERROR", "PIF", "PIFDEF", "PIFNDEF", "PINCLUDE", "PLINE", "PLUS", "PLUSEQ", "PLUSPLUS", "PP_NUMBER", "PRAGMA", "PROC", "PUNDEF", "QMARK", "RCURLY", "REGISTER", "RESTRICT", "RETURN", "RPAREN", "RSQUARE", "SChar", "SEMI", "SHIFTLEFT", "SHIFTLEFTEQ", "SHIFTRIGHT", "SHIFTRIGHTEQ", "SHORT", "SIGNED", "SIZEOF", "SPAWN", "STAR", "STAREQ", "STATIC", "STATICASSERT", "STRING_LITERAL", "STRUCT", "SUB", "SUBEQ", "SWITCH", "THREADLOCAL", "TILDE", "TYPEDEF", "UNION", "UNSIGNED", "UniversalCharacterName", "UnsignedSuffix", "VOID", "VOLATILE", "WAIT", "WHEN", "WHILE", "WS", "Zero", "BODY", "EXPR", "FILE", "PARAMLIST", "SEQUENCE", "TEXT_BLOCK"
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

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public PreprocessorParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public PreprocessorParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return PreprocessorParser.tokenNames; }
    public String getGrammarFileName() { return "PreprocessorParser.g"; }


    @Override
    public void emitErrorMessage(String msg) { // don't try to recover!
        throw new RuntimeException(msg);
    }


    public static class file_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "file"
    // PreprocessorParser.g:45:1: file : block EOF -> ^( FILE ( block )? EOF ) ;
    public final PreprocessorParser.file_return file() throws RecognitionException {
        PreprocessorParser.file_return retval = new PreprocessorParser.file_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        PreprocessorParser.block_return block1 =null;


        Object EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // PreprocessorParser.g:45:7: ( block EOF -> ^( FILE ( block )? EOF ) )
            // PreprocessorParser.g:45:9: block EOF
            {
            pushFollow(FOLLOW_block_in_file120);
            block1=block();

            state._fsp--;

            stream_block.add(block1.getTree());

            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_file122);  
            stream_EOF.add(EOF2);


            // AST REWRITE
            // elements: EOF, block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 45:19: -> ^( FILE ( block )? EOF )
            {
                // PreprocessorParser.g:45:22: ^( FILE ( block )? EOF )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(FILE, "FILE")
                , root_1);

                // PreprocessorParser.g:45:29: ( block )?
                if ( stream_block.hasNext() ) {
                    adaptor.addChild(root_1, stream_block.nextTree());

                }
                stream_block.reset();

                adaptor.addChild(root_1, 
                stream_EOF.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "file"


    public static class block_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // PreprocessorParser.g:51:1: block : ( directive | textblock )* ;
    public final PreprocessorParser.block_return block() throws RecognitionException {
        PreprocessorParser.block_return retval = new PreprocessorParser.block_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.directive_return directive3 =null;

        PreprocessorParser.textblock_return textblock4 =null;



        try {
            // PreprocessorParser.g:51:8: ( ( directive | textblock )* )
            // PreprocessorParser.g:51:10: ( directive | textblock )*
            {
            root_0 = (Object)adaptor.nil();


            // PreprocessorParser.g:51:10: ( directive | textblock )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==HASH||LA1_0==PDEFINE||(LA1_0 >= PERROR && LA1_0 <= PLINE)||LA1_0==PRAGMA||LA1_0==PUNDEF) ) {
                    alt1=1;
                }
                else if ( ((LA1_0 >= ALIGNAS && LA1_0 <= BREAK)||LA1_0==CASE||(LA1_0 >= CHAR && LA1_0 <= DEFAULT)||(LA1_0 >= DIV && LA1_0 <= DOUBLE)||(LA1_0 >= ELLIPSIS && LA1_0 <= EXTERN)||(LA1_0 >= FLOAT && LA1_0 <= FOR)||(LA1_0 >= GENERIC && LA1_0 <= GTE)||(LA1_0 >= HASHHASH && LA1_0 <= HEADER_NAME)||(LA1_0 >= IDENTIFIER && LA1_0 <= INVARIANT)||(LA1_0 >= LCURLY && LA1_0 <= LTE)||(LA1_0 >= MINUSMINUS && LA1_0 <= NOT)||(LA1_0 >= OR && LA1_0 <= OUTPUT)||(LA1_0 >= PLUS && LA1_0 <= PP_NUMBER)||LA1_0==PROC||(LA1_0 >= QMARK && LA1_0 <= RSQUARE)||(LA1_0 >= SEMI && LA1_0 <= UNSIGNED)||(LA1_0 >= VOID && LA1_0 <= WS)) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // PreprocessorParser.g:51:11: directive
            	    {
            	    pushFollow(FOLLOW_directive_in_block148);
            	    directive3=directive();

            	    state._fsp--;

            	    adaptor.addChild(root_0, directive3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // PreprocessorParser.g:51:23: textblock
            	    {
            	    pushFollow(FOLLOW_textblock_in_block152);
            	    textblock4=textblock();

            	    state._fsp--;

            	    adaptor.addChild(root_0, textblock4.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "block"


    public static class directive_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "directive"
    // PreprocessorParser.g:62:1: directive : ( macrodef | macroundef | includeline | pragmaline | ifblock | ifdefblock | ifndefblock | errorline | lineline | nondirectiveline );
    public final PreprocessorParser.directive_return directive() throws RecognitionException {
        PreprocessorParser.directive_return retval = new PreprocessorParser.directive_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.macrodef_return macrodef5 =null;

        PreprocessorParser.macroundef_return macroundef6 =null;

        PreprocessorParser.includeline_return includeline7 =null;

        PreprocessorParser.pragmaline_return pragmaline8 =null;

        PreprocessorParser.ifblock_return ifblock9 =null;

        PreprocessorParser.ifdefblock_return ifdefblock10 =null;

        PreprocessorParser.ifndefblock_return ifndefblock11 =null;

        PreprocessorParser.errorline_return errorline12 =null;

        PreprocessorParser.lineline_return lineline13 =null;

        PreprocessorParser.nondirectiveline_return nondirectiveline14 =null;



        try {
            // PreprocessorParser.g:62:11: ( macrodef | macroundef | includeline | pragmaline | ifblock | ifdefblock | ifndefblock | errorline | lineline | nondirectiveline )
            int alt2=10;
            switch ( input.LA(1) ) {
            case PDEFINE:
                {
                alt2=1;
                }
                break;
            case PUNDEF:
                {
                alt2=2;
                }
                break;
            case PINCLUDE:
                {
                alt2=3;
                }
                break;
            case PRAGMA:
                {
                alt2=4;
                }
                break;
            case PIF:
                {
                alt2=5;
                }
                break;
            case PIFDEF:
                {
                alt2=6;
                }
                break;
            case PIFNDEF:
                {
                alt2=7;
                }
                break;
            case PERROR:
                {
                alt2=8;
                }
                break;
            case PLINE:
                {
                alt2=9;
                }
                break;
            case HASH:
                {
                alt2=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // PreprocessorParser.g:62:13: macrodef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_macrodef_in_directive165);
                    macrodef5=macrodef();

                    state._fsp--;

                    adaptor.addChild(root_0, macrodef5.getTree());

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:63:5: macroundef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_macroundef_in_directive171);
                    macroundef6=macroundef();

                    state._fsp--;

                    adaptor.addChild(root_0, macroundef6.getTree());

                    }
                    break;
                case 3 :
                    // PreprocessorParser.g:64:5: includeline
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeline_in_directive177);
                    includeline7=includeline();

                    state._fsp--;

                    adaptor.addChild(root_0, includeline7.getTree());

                    }
                    break;
                case 4 :
                    // PreprocessorParser.g:65:5: pragmaline
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pragmaline_in_directive183);
                    pragmaline8=pragmaline();

                    state._fsp--;

                    adaptor.addChild(root_0, pragmaline8.getTree());

                    }
                    break;
                case 5 :
                    // PreprocessorParser.g:66:5: ifblock
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifblock_in_directive189);
                    ifblock9=ifblock();

                    state._fsp--;

                    adaptor.addChild(root_0, ifblock9.getTree());

                    }
                    break;
                case 6 :
                    // PreprocessorParser.g:67:5: ifdefblock
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifdefblock_in_directive195);
                    ifdefblock10=ifdefblock();

                    state._fsp--;

                    adaptor.addChild(root_0, ifdefblock10.getTree());

                    }
                    break;
                case 7 :
                    // PreprocessorParser.g:68:5: ifndefblock
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifndefblock_in_directive201);
                    ifndefblock11=ifndefblock();

                    state._fsp--;

                    adaptor.addChild(root_0, ifndefblock11.getTree());

                    }
                    break;
                case 8 :
                    // PreprocessorParser.g:69:5: errorline
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_errorline_in_directive207);
                    errorline12=errorline();

                    state._fsp--;

                    adaptor.addChild(root_0, errorline12.getTree());

                    }
                    break;
                case 9 :
                    // PreprocessorParser.g:70:5: lineline
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_lineline_in_directive213);
                    lineline13=lineline();

                    state._fsp--;

                    adaptor.addChild(root_0, lineline13.getTree());

                    }
                    break;
                case 10 :
                    // PreprocessorParser.g:71:5: nondirectiveline
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_nondirectiveline_in_directive219);
                    nondirectiveline14=nondirectiveline();

                    state._fsp--;

                    adaptor.addChild(root_0, nondirectiveline14.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "directive"


    public static class textblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "textblock"
    // PreprocessorParser.g:80:1: textblock : ( options {greedy=true; } : textline )+ -> ^( TEXT_BLOCK ( textline )+ ) ;
    public final PreprocessorParser.textblock_return textblock() throws RecognitionException {
        PreprocessorParser.textblock_return retval = new PreprocessorParser.textblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.textline_return textline15 =null;


        RewriteRuleSubtreeStream stream_textline=new RewriteRuleSubtreeStream(adaptor,"rule textline");
        try {
            // PreprocessorParser.g:80:11: ( ( options {greedy=true; } : textline )+ -> ^( TEXT_BLOCK ( textline )+ ) )
            // PreprocessorParser.g:80:14: ( options {greedy=true; } : textline )+
            {
            // PreprocessorParser.g:80:14: ( options {greedy=true; } : textline )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                switch ( input.LA(1) ) {
                case COMMENT:
                case WS:
                    {
                    alt3=1;
                    }
                    break;
                case HEADER_NAME:
                    {
                    alt3=1;
                    }
                    break;
                case IDENTIFIER:
                    {
                    alt3=1;
                    }
                    break;
                case ALIGNAS:
                case ALIGNOF:
                case ASSERT:
                case ASSUME:
                case ATOMIC:
                case AUTO:
                case BOOL:
                case BREAK:
                case CASE:
                case CHAR:
                case CHOOSE:
                case COLLECTIVE:
                case COMPLEX:
                case CONST:
                case CONTINUE:
                case DEFAULT:
                case DO:
                case DOUBLE:
                case ELSE:
                case ENUM:
                case EXTERN:
                case FLOAT:
                case FOR:
                case GENERIC:
                case GOTO:
                case IF:
                case IMAGINARY:
                case INLINE:
                case INPUT:
                case INT:
                case INVARIANT:
                case LONG:
                case NORETURN:
                case OUTPUT:
                case PROC:
                case REGISTER:
                case RESTRICT:
                case RETURN:
                case SHORT:
                case SIGNED:
                case SIZEOF:
                case SPAWN:
                case STATIC:
                case STATICASSERT:
                case STRUCT:
                case SWITCH:
                case THREADLOCAL:
                case TYPEDEF:
                case UNION:
                case UNSIGNED:
                case VOID:
                case VOLATILE:
                case WAIT:
                case WHEN:
                case WHILE:
                    {
                    alt3=1;
                    }
                    break;
                case FLOATING_CONSTANT:
                case INTEGER_CONSTANT:
                case PP_NUMBER:
                    {
                    alt3=1;
                    }
                    break;
                case CHARACTER_CONSTANT:
                    {
                    alt3=1;
                    }
                    break;
                case STRING_LITERAL:
                    {
                    alt3=1;
                    }
                    break;
                case AMPERSAND:
                case AND:
                case ARROW:
                case ASSIGN:
                case AT:
                case BITANDEQ:
                case BITOR:
                case BITOREQ:
                case BITXOR:
                case BITXOREQ:
                case COLON:
                case COMMA:
                case DIV:
                case DIVEQ:
                case DOT:
                case ELLIPSIS:
                case EQUALS:
                case GT:
                case GTE:
                case HASHHASH:
                case LCURLY:
                case LPAREN:
                case LSQUARE:
                case LT:
                case LTE:
                case MINUSMINUS:
                case MOD:
                case MODEQ:
                case NEQ:
                case NOT:
                case OR:
                case PLUS:
                case PLUSEQ:
                case PLUSPLUS:
                case QMARK:
                case RCURLY:
                case RPAREN:
                case RSQUARE:
                case SEMI:
                case SHIFTLEFT:
                case SHIFTLEFTEQ:
                case SHIFTRIGHT:
                case SHIFTRIGHTEQ:
                case STAR:
                case STAREQ:
                case SUB:
                case SUBEQ:
                case TILDE:
                    {
                    alt3=1;
                    }
                    break;
                case OTHER:
                    {
                    alt3=1;
                    }
                    break;
                case NEWLINE:
                    {
                    alt3=1;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // PreprocessorParser.g:80:40: textline
            	    {
            	    pushFollow(FOLLOW_textline_in_textblock243);
            	    textline15=textline();

            	    state._fsp--;

            	    stream_textline.add(textline15.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            // AST REWRITE
            // elements: textline
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 81:5: -> ^( TEXT_BLOCK ( textline )+ )
            {
                // PreprocessorParser.g:81:8: ^( TEXT_BLOCK ( textline )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TEXT_BLOCK, "TEXT_BLOCK")
                , root_1);

                if ( !(stream_textline.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_textline.hasNext() ) {
                    adaptor.addChild(root_1, stream_textline.nextTree());

                }
                stream_textline.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "textblock"


    public static class textline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "textline"
    // PreprocessorParser.g:83:1: textline : ( white )* ( nonPoundPpToken ( wpptoken )* )? lineend ;
    public final PreprocessorParser.textline_return textline() throws RecognitionException {
        PreprocessorParser.textline_return retval = new PreprocessorParser.textline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.white_return white16 =null;

        PreprocessorParser.nonPoundPpToken_return nonPoundPpToken17 =null;

        PreprocessorParser.wpptoken_return wpptoken18 =null;

        PreprocessorParser.lineend_return lineend19 =null;



        try {
            // PreprocessorParser.g:83:10: ( ( white )* ( nonPoundPpToken ( wpptoken )* )? lineend )
            // PreprocessorParser.g:83:12: ( white )* ( nonPoundPpToken ( wpptoken )* )? lineend
            {
            root_0 = (Object)adaptor.nil();


            // PreprocessorParser.g:83:12: ( white )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==COMMENT||LA4_0==WS) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // PreprocessorParser.g:83:12: white
            	    {
            	    pushFollow(FOLLOW_white_in_textline267);
            	    white16=white();

            	    state._fsp--;

            	    adaptor.addChild(root_0, white16.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            // PreprocessorParser.g:83:19: ( nonPoundPpToken ( wpptoken )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0 >= ALIGNAS && LA6_0 <= BREAK)||LA6_0==CASE||(LA6_0 >= CHAR && LA6_0 <= COMMA)||(LA6_0 >= COMPLEX && LA6_0 <= DEFAULT)||(LA6_0 >= DIV && LA6_0 <= DOUBLE)||(LA6_0 >= ELLIPSIS && LA6_0 <= EXTERN)||(LA6_0 >= FLOAT && LA6_0 <= FOR)||(LA6_0 >= GENERIC && LA6_0 <= GTE)||(LA6_0 >= HASHHASH && LA6_0 <= HEADER_NAME)||(LA6_0 >= IDENTIFIER && LA6_0 <= INVARIANT)||(LA6_0 >= LCURLY && LA6_0 <= LTE)||(LA6_0 >= MINUSMINUS && LA6_0 <= NEQ)||(LA6_0 >= NORETURN && LA6_0 <= NOT)||(LA6_0 >= OR && LA6_0 <= OUTPUT)||(LA6_0 >= PLUS && LA6_0 <= PP_NUMBER)||LA6_0==PROC||(LA6_0 >= QMARK && LA6_0 <= RSQUARE)||(LA6_0 >= SEMI && LA6_0 <= UNSIGNED)||(LA6_0 >= VOID && LA6_0 <= WHILE)) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // PreprocessorParser.g:83:20: nonPoundPpToken ( wpptoken )*
                    {
                    pushFollow(FOLLOW_nonPoundPpToken_in_textline271);
                    nonPoundPpToken17=nonPoundPpToken();

                    state._fsp--;

                    adaptor.addChild(root_0, nonPoundPpToken17.getTree());

                    // PreprocessorParser.g:83:36: ( wpptoken )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0 >= ALIGNAS && LA5_0 <= BREAK)||LA5_0==CASE||(LA5_0 >= CHAR && LA5_0 <= DEFAULT)||(LA5_0 >= DIV && LA5_0 <= DOUBLE)||(LA5_0 >= ELLIPSIS && LA5_0 <= EXTERN)||(LA5_0 >= FLOAT && LA5_0 <= FOR)||(LA5_0 >= GENERIC && LA5_0 <= HEADER_NAME)||(LA5_0 >= IDENTIFIER && LA5_0 <= INVARIANT)||(LA5_0 >= LCURLY && LA5_0 <= LTE)||(LA5_0 >= MINUSMINUS && LA5_0 <= NEQ)||(LA5_0 >= NORETURN && LA5_0 <= NOT)||(LA5_0 >= OR && LA5_0 <= OUTPUT)||(LA5_0 >= PLUS && LA5_0 <= PP_NUMBER)||LA5_0==PROC||(LA5_0 >= QMARK && LA5_0 <= RSQUARE)||(LA5_0 >= SEMI && LA5_0 <= UNSIGNED)||(LA5_0 >= VOID && LA5_0 <= WS)) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // PreprocessorParser.g:83:36: wpptoken
                    	    {
                    	    pushFollow(FOLLOW_wpptoken_in_textline273);
                    	    wpptoken18=wpptoken();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, wpptoken18.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }


            pushFollow(FOLLOW_lineend_in_textline278);
            lineend19=lineend();

            state._fsp--;

            adaptor.addChild(root_0, lineend19.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "textline"


    public static class white_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "white"
    // PreprocessorParser.g:85:1: white : ( WS | COMMENT );
    public final PreprocessorParser.white_return white() throws RecognitionException {
        PreprocessorParser.white_return retval = new PreprocessorParser.white_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set20=null;

        Object set20_tree=null;

        try {
            // PreprocessorParser.g:85:8: ( WS | COMMENT )
            // PreprocessorParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set20=(Token)input.LT(1);

            if ( input.LA(1)==COMMENT||input.LA(1)==WS ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set20)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "white"


    public static class wpptoken_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "wpptoken"
    // PreprocessorParser.g:87:1: wpptoken : ( pptoken | white );
    public final PreprocessorParser.wpptoken_return wpptoken() throws RecognitionException {
        PreprocessorParser.wpptoken_return retval = new PreprocessorParser.wpptoken_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.pptoken_return pptoken21 =null;

        PreprocessorParser.white_return white22 =null;



        try {
            // PreprocessorParser.g:87:10: ( pptoken | white )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0 >= ALIGNAS && LA7_0 <= BREAK)||LA7_0==CASE||(LA7_0 >= CHAR && LA7_0 <= COMMA)||(LA7_0 >= COMPLEX && LA7_0 <= DEFAULT)||(LA7_0 >= DIV && LA7_0 <= DOUBLE)||(LA7_0 >= ELLIPSIS && LA7_0 <= EXTERN)||(LA7_0 >= FLOAT && LA7_0 <= FOR)||(LA7_0 >= GENERIC && LA7_0 <= HEADER_NAME)||(LA7_0 >= IDENTIFIER && LA7_0 <= INVARIANT)||(LA7_0 >= LCURLY && LA7_0 <= LTE)||(LA7_0 >= MINUSMINUS && LA7_0 <= NEQ)||(LA7_0 >= NORETURN && LA7_0 <= NOT)||(LA7_0 >= OR && LA7_0 <= OUTPUT)||(LA7_0 >= PLUS && LA7_0 <= PP_NUMBER)||LA7_0==PROC||(LA7_0 >= QMARK && LA7_0 <= RSQUARE)||(LA7_0 >= SEMI && LA7_0 <= UNSIGNED)||(LA7_0 >= VOID && LA7_0 <= WHILE)) ) {
                alt7=1;
            }
            else if ( (LA7_0==COMMENT||LA7_0==WS) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // PreprocessorParser.g:87:12: pptoken
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pptoken_in_wpptoken301);
                    pptoken21=pptoken();

                    state._fsp--;

                    adaptor.addChild(root_0, pptoken21.getTree());

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:87:22: white
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_white_in_wpptoken305);
                    white22=white();

                    state._fsp--;

                    adaptor.addChild(root_0, white22.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "wpptoken"


    public static class lineend_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lineend"
    // PreprocessorParser.g:89:1: lineend : NEWLINE ;
    public final PreprocessorParser.lineend_return lineend() throws RecognitionException {
        PreprocessorParser.lineend_return retval = new PreprocessorParser.lineend_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NEWLINE23=null;

        Object NEWLINE23_tree=null;

        try {
            // PreprocessorParser.g:89:10: ( NEWLINE )
            // PreprocessorParser.g:89:12: NEWLINE
            {
            root_0 = (Object)adaptor.nil();


            NEWLINE23=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_lineend315); 
            NEWLINE23_tree = 
            (Object)adaptor.create(NEWLINE23)
            ;
            adaptor.addChild(root_0, NEWLINE23_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "lineend"


    public static class macrodef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "macrodef"
    // PreprocessorParser.g:91:1: macrodef : PDEFINE ( white )+ i= identifier ( paramlist macrobody -> ^( PDEFINE $i paramlist macrobody ) | lineend -> ^( PDEFINE $i ^( BODY ) ) | white macrobody -> ^( PDEFINE $i macrobody ) ) ;
    public final PreprocessorParser.macrodef_return macrodef() throws RecognitionException {
        PreprocessorParser.macrodef_return retval = new PreprocessorParser.macrodef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PDEFINE24=null;
        PreprocessorParser.identifier_return i =null;

        PreprocessorParser.white_return white25 =null;

        PreprocessorParser.paramlist_return paramlist26 =null;

        PreprocessorParser.macrobody_return macrobody27 =null;

        PreprocessorParser.lineend_return lineend28 =null;

        PreprocessorParser.white_return white29 =null;

        PreprocessorParser.macrobody_return macrobody30 =null;


        Object PDEFINE24_tree=null;
        RewriteRuleTokenStream stream_PDEFINE=new RewriteRuleTokenStream(adaptor,"token PDEFINE");
        RewriteRuleSubtreeStream stream_macrobody=new RewriteRuleSubtreeStream(adaptor,"rule macrobody");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_paramlist=new RewriteRuleSubtreeStream(adaptor,"rule paramlist");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // PreprocessorParser.g:91:10: ( PDEFINE ( white )+ i= identifier ( paramlist macrobody -> ^( PDEFINE $i paramlist macrobody ) | lineend -> ^( PDEFINE $i ^( BODY ) ) | white macrobody -> ^( PDEFINE $i macrobody ) ) )
            // PreprocessorParser.g:91:12: PDEFINE ( white )+ i= identifier ( paramlist macrobody -> ^( PDEFINE $i paramlist macrobody ) | lineend -> ^( PDEFINE $i ^( BODY ) ) | white macrobody -> ^( PDEFINE $i macrobody ) )
            {
            PDEFINE24=(Token)match(input,PDEFINE,FOLLOW_PDEFINE_in_macrodef324);  
            stream_PDEFINE.add(PDEFINE24);


            // PreprocessorParser.g:91:20: ( white )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMENT||LA8_0==WS) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // PreprocessorParser.g:91:20: white
            	    {
            	    pushFollow(FOLLOW_white_in_macrodef326);
            	    white25=white();

            	    state._fsp--;

            	    stream_white.add(white25.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            pushFollow(FOLLOW_identifier_in_macrodef331);
            i=identifier();

            state._fsp--;

            stream_identifier.add(i.getTree());

            // PreprocessorParser.g:92:4: ( paramlist macrobody -> ^( PDEFINE $i paramlist macrobody ) | lineend -> ^( PDEFINE $i ^( BODY ) ) | white macrobody -> ^( PDEFINE $i macrobody ) )
            int alt9=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt9=1;
                }
                break;
            case NEWLINE:
                {
                alt9=2;
                }
                break;
            case COMMENT:
            case WS:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // PreprocessorParser.g:92:6: paramlist macrobody
                    {
                    pushFollow(FOLLOW_paramlist_in_macrodef338);
                    paramlist26=paramlist();

                    state._fsp--;

                    stream_paramlist.add(paramlist26.getTree());

                    pushFollow(FOLLOW_macrobody_in_macrodef340);
                    macrobody27=macrobody();

                    state._fsp--;

                    stream_macrobody.add(macrobody27.getTree());

                    // AST REWRITE
                    // elements: macrobody, paramlist, PDEFINE, i
                    // token labels: 
                    // rule labels: retval, i
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 92:26: -> ^( PDEFINE $i paramlist macrobody )
                    {
                        // PreprocessorParser.g:92:29: ^( PDEFINE $i paramlist macrobody )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PDEFINE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_i.nextTree());

                        adaptor.addChild(root_1, stream_paramlist.nextTree());

                        adaptor.addChild(root_1, stream_macrobody.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:93:6: lineend
                    {
                    pushFollow(FOLLOW_lineend_in_macrodef360);
                    lineend28=lineend();

                    state._fsp--;

                    stream_lineend.add(lineend28.getTree());

                    // AST REWRITE
                    // elements: PDEFINE, i
                    // token labels: 
                    // rule labels: retval, i
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 93:14: -> ^( PDEFINE $i ^( BODY ) )
                    {
                        // PreprocessorParser.g:93:17: ^( PDEFINE $i ^( BODY ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PDEFINE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_i.nextTree());

                        // PreprocessorParser.g:93:30: ^( BODY )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(BODY, "BODY")
                        , root_2);

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // PreprocessorParser.g:94:6: white macrobody
                    {
                    pushFollow(FOLLOW_white_in_macrodef380);
                    white29=white();

                    state._fsp--;

                    stream_white.add(white29.getTree());

                    pushFollow(FOLLOW_macrobody_in_macrodef382);
                    macrobody30=macrobody();

                    state._fsp--;

                    stream_macrobody.add(macrobody30.getTree());

                    // AST REWRITE
                    // elements: i, macrobody, PDEFINE
                    // token labels: 
                    // rule labels: retval, i
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_i=new RewriteRuleSubtreeStream(adaptor,"rule i",i!=null?i.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 94:22: -> ^( PDEFINE $i macrobody )
                    {
                        // PreprocessorParser.g:94:25: ^( PDEFINE $i macrobody )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PDEFINE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_i.nextTree());

                        adaptor.addChild(root_1, stream_macrobody.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "macrodef"


    public static class macrobody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "macrobody"
    // PreprocessorParser.g:98:1: macrobody : ( white )* (t+= pptoken ( (t+= wpptoken )* t+= pptoken )? ( white )* lineend -> ^( BODY ( $t)+ ) | lineend -> ^( BODY ) ) ;
    public final PreprocessorParser.macrobody_return macrobody() throws RecognitionException {
        PreprocessorParser.macrobody_return retval = new PreprocessorParser.macrobody_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        List list_t=null;
        PreprocessorParser.white_return white31 =null;

        PreprocessorParser.white_return white32 =null;

        PreprocessorParser.lineend_return lineend33 =null;

        PreprocessorParser.lineend_return lineend34 =null;

        RuleReturnScope t = null;
        RewriteRuleSubtreeStream stream_pptoken=new RewriteRuleSubtreeStream(adaptor,"rule pptoken");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_wpptoken=new RewriteRuleSubtreeStream(adaptor,"rule wpptoken");
        try {
            // PreprocessorParser.g:98:11: ( ( white )* (t+= pptoken ( (t+= wpptoken )* t+= pptoken )? ( white )* lineend -> ^( BODY ( $t)+ ) | lineend -> ^( BODY ) ) )
            // PreprocessorParser.g:98:13: ( white )* (t+= pptoken ( (t+= wpptoken )* t+= pptoken )? ( white )* lineend -> ^( BODY ( $t)+ ) | lineend -> ^( BODY ) )
            {
            // PreprocessorParser.g:98:13: ( white )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMMENT||LA10_0==WS) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // PreprocessorParser.g:98:13: white
            	    {
            	    pushFollow(FOLLOW_white_in_macrobody409);
            	    white31=white();

            	    state._fsp--;

            	    stream_white.add(white31.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            // PreprocessorParser.g:99:4: (t+= pptoken ( (t+= wpptoken )* t+= pptoken )? ( white )* lineend -> ^( BODY ( $t)+ ) | lineend -> ^( BODY ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0 >= ALIGNAS && LA14_0 <= BREAK)||LA14_0==CASE||(LA14_0 >= CHAR && LA14_0 <= COMMA)||(LA14_0 >= COMPLEX && LA14_0 <= DEFAULT)||(LA14_0 >= DIV && LA14_0 <= DOUBLE)||(LA14_0 >= ELLIPSIS && LA14_0 <= EXTERN)||(LA14_0 >= FLOAT && LA14_0 <= FOR)||(LA14_0 >= GENERIC && LA14_0 <= HEADER_NAME)||(LA14_0 >= IDENTIFIER && LA14_0 <= INVARIANT)||(LA14_0 >= LCURLY && LA14_0 <= LTE)||(LA14_0 >= MINUSMINUS && LA14_0 <= NEQ)||(LA14_0 >= NORETURN && LA14_0 <= NOT)||(LA14_0 >= OR && LA14_0 <= OUTPUT)||(LA14_0 >= PLUS && LA14_0 <= PP_NUMBER)||LA14_0==PROC||(LA14_0 >= QMARK && LA14_0 <= RSQUARE)||(LA14_0 >= SEMI && LA14_0 <= UNSIGNED)||(LA14_0 >= VOID && LA14_0 <= WHILE)) ) {
                alt14=1;
            }
            else if ( (LA14_0==NEWLINE) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // PreprocessorParser.g:99:6: t+= pptoken ( (t+= wpptoken )* t+= pptoken )? ( white )* lineend
                    {
                    pushFollow(FOLLOW_pptoken_in_macrobody420);
                    t=pptoken();

                    state._fsp--;

                    stream_pptoken.add(t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());


                    // PreprocessorParser.g:99:17: ( (t+= wpptoken )* t+= pptoken )?
                    int alt12=2;
                    alt12 = dfa12.predict(input);
                    switch (alt12) {
                        case 1 :
                            // PreprocessorParser.g:99:18: (t+= wpptoken )* t+= pptoken
                            {
                            // PreprocessorParser.g:99:19: (t+= wpptoken )*
                            loop11:
                            do {
                                int alt11=2;
                                alt11 = dfa11.predict(input);
                                switch (alt11) {
                            	case 1 :
                            	    // PreprocessorParser.g:99:19: t+= wpptoken
                            	    {
                            	    pushFollow(FOLLOW_wpptoken_in_macrobody425);
                            	    t=wpptoken();

                            	    state._fsp--;

                            	    stream_wpptoken.add(t.getTree());
                            	    if (list_t==null) list_t=new ArrayList();
                            	    list_t.add(t.getTree());


                            	    }
                            	    break;

                            	default :
                            	    break loop11;
                                }
                            } while (true);


                            pushFollow(FOLLOW_pptoken_in_macrobody430);
                            t=pptoken();

                            state._fsp--;

                            stream_pptoken.add(t.getTree());
                            if (list_t==null) list_t=new ArrayList();
                            list_t.add(t.getTree());


                            }
                            break;

                    }


                    // PreprocessorParser.g:99:44: ( white )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMENT||LA13_0==WS) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // PreprocessorParser.g:99:44: white
                    	    {
                    	    pushFollow(FOLLOW_white_in_macrobody434);
                    	    white32=white();

                    	    state._fsp--;

                    	    stream_white.add(white32.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    pushFollow(FOLLOW_lineend_in_macrobody437);
                    lineend33=lineend();

                    state._fsp--;

                    stream_lineend.add(lineend33.getTree());

                    // AST REWRITE
                    // elements: t
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: t
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"token t",list_t);
                    root_0 = (Object)adaptor.nil();
                    // 100:6: -> ^( BODY ( $t)+ )
                    {
                        // PreprocessorParser.g:100:9: ^( BODY ( $t)+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(BODY, "BODY")
                        , root_1);

                        if ( !(stream_t.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_t.hasNext() ) {
                            adaptor.addChild(root_1, stream_t.nextTree());

                        }
                        stream_t.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:101:6: lineend
                    {
                    pushFollow(FOLLOW_lineend_in_macrobody459);
                    lineend34=lineend();

                    state._fsp--;

                    stream_lineend.add(lineend34.getTree());

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 102:6: -> ^( BODY )
                    {
                        // PreprocessorParser.g:102:9: ^( BODY )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(BODY, "BODY")
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "macrobody"


    public static class paramlist_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "paramlist"
    // PreprocessorParser.g:106:1: paramlist : LPAREN ( white )* ( RPAREN -> ^( PARAMLIST ) | identifier ( ( white )* COMMA ( white )* identifier )* ( white )* RPAREN -> ^( PARAMLIST ( identifier )+ ) ) ;
    public final PreprocessorParser.paramlist_return paramlist() throws RecognitionException {
        PreprocessorParser.paramlist_return retval = new PreprocessorParser.paramlist_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LPAREN35=null;
        Token RPAREN37=null;
        Token COMMA40=null;
        Token RPAREN44=null;
        PreprocessorParser.white_return white36 =null;

        PreprocessorParser.identifier_return identifier38 =null;

        PreprocessorParser.white_return white39 =null;

        PreprocessorParser.white_return white41 =null;

        PreprocessorParser.identifier_return identifier42 =null;

        PreprocessorParser.white_return white43 =null;


        Object LPAREN35_tree=null;
        Object RPAREN37_tree=null;
        Object COMMA40_tree=null;
        Object RPAREN44_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // PreprocessorParser.g:106:11: ( LPAREN ( white )* ( RPAREN -> ^( PARAMLIST ) | identifier ( ( white )* COMMA ( white )* identifier )* ( white )* RPAREN -> ^( PARAMLIST ( identifier )+ ) ) )
            // PreprocessorParser.g:106:13: LPAREN ( white )* ( RPAREN -> ^( PARAMLIST ) | identifier ( ( white )* COMMA ( white )* identifier )* ( white )* RPAREN -> ^( PARAMLIST ( identifier )+ ) )
            {
            LPAREN35=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_paramlist486);  
            stream_LPAREN.add(LPAREN35);


            // PreprocessorParser.g:106:20: ( white )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==COMMENT||LA15_0==WS) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // PreprocessorParser.g:106:20: white
            	    {
            	    pushFollow(FOLLOW_white_in_paramlist488);
            	    white36=white();

            	    state._fsp--;

            	    stream_white.add(white36.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // PreprocessorParser.g:107:4: ( RPAREN -> ^( PARAMLIST ) | identifier ( ( white )* COMMA ( white )* identifier )* ( white )* RPAREN -> ^( PARAMLIST ( identifier )+ ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RPAREN) ) {
                alt20=1;
            }
            else if ( ((LA20_0 >= ALIGNAS && LA20_0 <= ALIGNOF)||LA20_0==ASSERT||LA20_0==ASSUME||(LA20_0 >= ATOMIC && LA20_0 <= AUTO)||(LA20_0 >= BOOL && LA20_0 <= BREAK)||LA20_0==CASE||LA20_0==CHAR||(LA20_0 >= CHOOSE && LA20_0 <= COLLECTIVE)||(LA20_0 >= COMPLEX && LA20_0 <= DEFAULT)||LA20_0==DO||LA20_0==DOUBLE||(LA20_0 >= ELSE && LA20_0 <= ENUM)||LA20_0==EXTERN||LA20_0==FLOAT||LA20_0==FOR||(LA20_0 >= GENERIC && LA20_0 <= GOTO)||(LA20_0 >= IDENTIFIER && LA20_0 <= INT)||LA20_0==INVARIANT||LA20_0==LONG||LA20_0==NORETURN||LA20_0==OUTPUT||LA20_0==PROC||(LA20_0 >= REGISTER && LA20_0 <= RETURN)||(LA20_0 >= SHORT && LA20_0 <= SPAWN)||(LA20_0 >= STATIC && LA20_0 <= STATICASSERT)||LA20_0==STRUCT||(LA20_0 >= SWITCH && LA20_0 <= THREADLOCAL)||(LA20_0 >= TYPEDEF && LA20_0 <= UNSIGNED)||(LA20_0 >= VOID && LA20_0 <= WHILE)) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // PreprocessorParser.g:107:6: RPAREN
                    {
                    RPAREN37=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_paramlist497);  
                    stream_RPAREN.add(RPAREN37);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 107:13: -> ^( PARAMLIST )
                    {
                        // PreprocessorParser.g:107:16: ^( PARAMLIST )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARAMLIST, "PARAMLIST")
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:108:6: identifier ( ( white )* COMMA ( white )* identifier )* ( white )* RPAREN
                    {
                    pushFollow(FOLLOW_identifier_in_paramlist510);
                    identifier38=identifier();

                    state._fsp--;

                    stream_identifier.add(identifier38.getTree());

                    // PreprocessorParser.g:108:17: ( ( white )* COMMA ( white )* identifier )*
                    loop18:
                    do {
                        int alt18=2;
                        alt18 = dfa18.predict(input);
                        switch (alt18) {
                    	case 1 :
                    	    // PreprocessorParser.g:108:18: ( white )* COMMA ( white )* identifier
                    	    {
                    	    // PreprocessorParser.g:108:18: ( white )*
                    	    loop16:
                    	    do {
                    	        int alt16=2;
                    	        int LA16_0 = input.LA(1);

                    	        if ( (LA16_0==COMMENT||LA16_0==WS) ) {
                    	            alt16=1;
                    	        }


                    	        switch (alt16) {
                    	    	case 1 :
                    	    	    // PreprocessorParser.g:108:18: white
                    	    	    {
                    	    	    pushFollow(FOLLOW_white_in_paramlist513);
                    	    	    white39=white();

                    	    	    state._fsp--;

                    	    	    stream_white.add(white39.getTree());

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop16;
                    	        }
                    	    } while (true);


                    	    COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_paramlist516);  
                    	    stream_COMMA.add(COMMA40);


                    	    // PreprocessorParser.g:108:31: ( white )*
                    	    loop17:
                    	    do {
                    	        int alt17=2;
                    	        int LA17_0 = input.LA(1);

                    	        if ( (LA17_0==COMMENT||LA17_0==WS) ) {
                    	            alt17=1;
                    	        }


                    	        switch (alt17) {
                    	    	case 1 :
                    	    	    // PreprocessorParser.g:108:31: white
                    	    	    {
                    	    	    pushFollow(FOLLOW_white_in_paramlist518);
                    	    	    white41=white();

                    	    	    state._fsp--;

                    	    	    stream_white.add(white41.getTree());

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop17;
                    	        }
                    	    } while (true);


                    	    pushFollow(FOLLOW_identifier_in_paramlist521);
                    	    identifier42=identifier();

                    	    state._fsp--;

                    	    stream_identifier.add(identifier42.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    // PreprocessorParser.g:108:51: ( white )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==COMMENT||LA19_0==WS) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // PreprocessorParser.g:108:51: white
                    	    {
                    	    pushFollow(FOLLOW_white_in_paramlist525);
                    	    white43=white();

                    	    state._fsp--;

                    	    stream_white.add(white43.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    RPAREN44=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_paramlist528);  
                    stream_RPAREN.add(RPAREN44);


                    // AST REWRITE
                    // elements: identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 109:6: -> ^( PARAMLIST ( identifier )+ )
                    {
                        // PreprocessorParser.g:109:9: ^( PARAMLIST ( identifier )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PARAMLIST, "PARAMLIST")
                        , root_1);

                        if ( !(stream_identifier.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_identifier.hasNext() ) {
                            adaptor.addChild(root_1, stream_identifier.nextTree());

                        }
                        stream_identifier.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "paramlist"


    public static class macroundef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "macroundef"
    // PreprocessorParser.g:113:1: macroundef : PUNDEF ( white )+ identifier ( white )* lineend -> ^( PUNDEF identifier ) ;
    public final PreprocessorParser.macroundef_return macroundef() throws RecognitionException {
        PreprocessorParser.macroundef_return retval = new PreprocessorParser.macroundef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PUNDEF45=null;
        PreprocessorParser.white_return white46 =null;

        PreprocessorParser.identifier_return identifier47 =null;

        PreprocessorParser.white_return white48 =null;

        PreprocessorParser.lineend_return lineend49 =null;


        Object PUNDEF45_tree=null;
        RewriteRuleTokenStream stream_PUNDEF=new RewriteRuleTokenStream(adaptor,"token PUNDEF");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // PreprocessorParser.g:113:12: ( PUNDEF ( white )+ identifier ( white )* lineend -> ^( PUNDEF identifier ) )
            // PreprocessorParser.g:113:14: PUNDEF ( white )+ identifier ( white )* lineend
            {
            PUNDEF45=(Token)match(input,PUNDEF,FOLLOW_PUNDEF_in_macroundef558);  
            stream_PUNDEF.add(PUNDEF45);


            // PreprocessorParser.g:113:21: ( white )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==COMMENT||LA21_0==WS) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // PreprocessorParser.g:113:21: white
            	    {
            	    pushFollow(FOLLOW_white_in_macroundef560);
            	    white46=white();

            	    state._fsp--;

            	    stream_white.add(white46.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            pushFollow(FOLLOW_identifier_in_macroundef563);
            identifier47=identifier();

            state._fsp--;

            stream_identifier.add(identifier47.getTree());

            // PreprocessorParser.g:113:39: ( white )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMENT||LA22_0==WS) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // PreprocessorParser.g:113:39: white
            	    {
            	    pushFollow(FOLLOW_white_in_macroundef565);
            	    white48=white();

            	    state._fsp--;

            	    stream_white.add(white48.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_macroundef568);
            lineend49=lineend();

            state._fsp--;

            stream_lineend.add(lineend49.getTree());

            // AST REWRITE
            // elements: PUNDEF, identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 114:4: -> ^( PUNDEF identifier )
            {
                // PreprocessorParser.g:114:7: ^( PUNDEF identifier )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PUNDEF.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_identifier.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "macroundef"


    public static class includeline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "includeline"
    // PreprocessorParser.g:117:1: includeline : PINCLUDE ( white )* HEADER_NAME ( white )* lineend -> ^( PINCLUDE HEADER_NAME ) ;
    public final PreprocessorParser.includeline_return includeline() throws RecognitionException {
        PreprocessorParser.includeline_return retval = new PreprocessorParser.includeline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PINCLUDE50=null;
        Token HEADER_NAME52=null;
        PreprocessorParser.white_return white51 =null;

        PreprocessorParser.white_return white53 =null;

        PreprocessorParser.lineend_return lineend54 =null;


        Object PINCLUDE50_tree=null;
        Object HEADER_NAME52_tree=null;
        RewriteRuleTokenStream stream_HEADER_NAME=new RewriteRuleTokenStream(adaptor,"token HEADER_NAME");
        RewriteRuleTokenStream stream_PINCLUDE=new RewriteRuleTokenStream(adaptor,"token PINCLUDE");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        try {
            // PreprocessorParser.g:117:13: ( PINCLUDE ( white )* HEADER_NAME ( white )* lineend -> ^( PINCLUDE HEADER_NAME ) )
            // PreprocessorParser.g:117:15: PINCLUDE ( white )* HEADER_NAME ( white )* lineend
            {
            PINCLUDE50=(Token)match(input,PINCLUDE,FOLLOW_PINCLUDE_in_includeline590);  
            stream_PINCLUDE.add(PINCLUDE50);


            // PreprocessorParser.g:117:24: ( white )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMENT||LA23_0==WS) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // PreprocessorParser.g:117:24: white
            	    {
            	    pushFollow(FOLLOW_white_in_includeline592);
            	    white51=white();

            	    state._fsp--;

            	    stream_white.add(white51.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            HEADER_NAME52=(Token)match(input,HEADER_NAME,FOLLOW_HEADER_NAME_in_includeline595);  
            stream_HEADER_NAME.add(HEADER_NAME52);


            // PreprocessorParser.g:117:43: ( white )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMENT||LA24_0==WS) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // PreprocessorParser.g:117:43: white
            	    {
            	    pushFollow(FOLLOW_white_in_includeline597);
            	    white53=white();

            	    state._fsp--;

            	    stream_white.add(white53.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_includeline600);
            lineend54=lineend();

            state._fsp--;

            stream_lineend.add(lineend54.getTree());

            // AST REWRITE
            // elements: PINCLUDE, HEADER_NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 118:4: -> ^( PINCLUDE HEADER_NAME )
            {
                // PreprocessorParser.g:118:7: ^( PINCLUDE HEADER_NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PINCLUDE.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_HEADER_NAME.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "includeline"


    public static class ifblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifblock"
    // PreprocessorParser.g:121:1: ifblock : PIF ( white )* expr lineend block ( elseblock )? endifline -> ^( PIF expr ^( SEQUENCE ( block )? ) ( elseblock )? ) ;
    public final PreprocessorParser.ifblock_return ifblock() throws RecognitionException {
        PreprocessorParser.ifblock_return retval = new PreprocessorParser.ifblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIF55=null;
        PreprocessorParser.white_return white56 =null;

        PreprocessorParser.expr_return expr57 =null;

        PreprocessorParser.lineend_return lineend58 =null;

        PreprocessorParser.block_return block59 =null;

        PreprocessorParser.elseblock_return elseblock60 =null;

        PreprocessorParser.endifline_return endifline61 =null;


        Object PIF55_tree=null;
        RewriteRuleTokenStream stream_PIF=new RewriteRuleTokenStream(adaptor,"token PIF");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_endifline=new RewriteRuleSubtreeStream(adaptor,"rule endifline");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_elseblock=new RewriteRuleSubtreeStream(adaptor,"rule elseblock");
        try {
            // PreprocessorParser.g:121:10: ( PIF ( white )* expr lineend block ( elseblock )? endifline -> ^( PIF expr ^( SEQUENCE ( block )? ) ( elseblock )? ) )
            // PreprocessorParser.g:121:13: PIF ( white )* expr lineend block ( elseblock )? endifline
            {
            PIF55=(Token)match(input,PIF,FOLLOW_PIF_in_ifblock624);  
            stream_PIF.add(PIF55);


            // PreprocessorParser.g:121:17: ( white )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==COMMENT||LA25_0==WS) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // PreprocessorParser.g:121:17: white
            	    {
            	    pushFollow(FOLLOW_white_in_ifblock626);
            	    white56=white();

            	    state._fsp--;

            	    stream_white.add(white56.getTree());

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            pushFollow(FOLLOW_expr_in_ifblock629);
            expr57=expr();

            state._fsp--;

            stream_expr.add(expr57.getTree());

            pushFollow(FOLLOW_lineend_in_ifblock631);
            lineend58=lineend();

            state._fsp--;

            stream_lineend.add(lineend58.getTree());

            pushFollow(FOLLOW_block_in_ifblock633);
            block59=block();

            state._fsp--;

            stream_block.add(block59.getTree());

            // PreprocessorParser.g:121:43: ( elseblock )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0 >= PELIF && LA26_0 <= PELSE)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // PreprocessorParser.g:121:43: elseblock
                    {
                    pushFollow(FOLLOW_elseblock_in_ifblock635);
                    elseblock60=elseblock();

                    state._fsp--;

                    stream_elseblock.add(elseblock60.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_endifline_in_ifblock638);
            endifline61=endifline();

            state._fsp--;

            stream_endifline.add(endifline61.getTree());

            // AST REWRITE
            // elements: PIF, elseblock, expr, block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 122:4: -> ^( PIF expr ^( SEQUENCE ( block )? ) ( elseblock )? )
            {
                // PreprocessorParser.g:122:7: ^( PIF expr ^( SEQUENCE ( block )? ) ( elseblock )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PIF.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                // PreprocessorParser.g:122:18: ^( SEQUENCE ( block )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(SEQUENCE, "SEQUENCE")
                , root_2);

                // PreprocessorParser.g:122:29: ( block )?
                if ( stream_block.hasNext() ) {
                    adaptor.addChild(root_2, stream_block.nextTree());

                }
                stream_block.reset();

                adaptor.addChild(root_1, root_2);
                }

                // PreprocessorParser.g:122:37: ( elseblock )?
                if ( stream_elseblock.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseblock.nextTree());

                }
                stream_elseblock.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "ifblock"


    public static class expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // PreprocessorParser.g:125:1: expr : ppdExpr ( ppdExpr | white )* -> ^( EXPR ( ppdExpr )+ ) ;
    public final PreprocessorParser.expr_return expr() throws RecognitionException {
        PreprocessorParser.expr_return retval = new PreprocessorParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.ppdExpr_return ppdExpr62 =null;

        PreprocessorParser.ppdExpr_return ppdExpr63 =null;

        PreprocessorParser.white_return white64 =null;


        RewriteRuleSubtreeStream stream_ppdExpr=new RewriteRuleSubtreeStream(adaptor,"rule ppdExpr");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        try {
            // PreprocessorParser.g:125:7: ( ppdExpr ( ppdExpr | white )* -> ^( EXPR ( ppdExpr )+ ) )
            // PreprocessorParser.g:125:9: ppdExpr ( ppdExpr | white )*
            {
            pushFollow(FOLLOW_ppdExpr_in_expr673);
            ppdExpr62=ppdExpr();

            state._fsp--;

            stream_ppdExpr.add(ppdExpr62.getTree());

            // PreprocessorParser.g:125:17: ( ppdExpr | white )*
            loop27:
            do {
                int alt27=3;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0 >= ALIGNAS && LA27_0 <= BREAK)||LA27_0==CASE||(LA27_0 >= CHAR && LA27_0 <= COMMA)||(LA27_0 >= COMPLEX && LA27_0 <= DOUBLE)||(LA27_0 >= ELLIPSIS && LA27_0 <= EXTERN)||(LA27_0 >= FLOAT && LA27_0 <= FOR)||(LA27_0 >= GENERIC && LA27_0 <= HEADER_NAME)||(LA27_0 >= IDENTIFIER && LA27_0 <= INVARIANT)||(LA27_0 >= LCURLY && LA27_0 <= LTE)||(LA27_0 >= MINUSMINUS && LA27_0 <= NEQ)||(LA27_0 >= NORETURN && LA27_0 <= NOT)||(LA27_0 >= OR && LA27_0 <= OUTPUT)||(LA27_0 >= PLUS && LA27_0 <= PP_NUMBER)||LA27_0==PROC||(LA27_0 >= QMARK && LA27_0 <= RSQUARE)||(LA27_0 >= SEMI && LA27_0 <= UNSIGNED)||(LA27_0 >= VOID && LA27_0 <= WHILE)) ) {
                    alt27=1;
                }
                else if ( (LA27_0==COMMENT||LA27_0==WS) ) {
                    alt27=2;
                }


                switch (alt27) {
            	case 1 :
            	    // PreprocessorParser.g:125:18: ppdExpr
            	    {
            	    pushFollow(FOLLOW_ppdExpr_in_expr676);
            	    ppdExpr63=ppdExpr();

            	    state._fsp--;

            	    stream_ppdExpr.add(ppdExpr63.getTree());

            	    }
            	    break;
            	case 2 :
            	    // PreprocessorParser.g:125:28: white
            	    {
            	    pushFollow(FOLLOW_white_in_expr680);
            	    white64=white();

            	    state._fsp--;

            	    stream_white.add(white64.getTree());

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            // AST REWRITE
            // elements: ppdExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 125:36: -> ^( EXPR ( ppdExpr )+ )
            {
                // PreprocessorParser.g:125:39: ^( EXPR ( ppdExpr )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(EXPR, "EXPR")
                , root_1);

                if ( !(stream_ppdExpr.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ppdExpr.hasNext() ) {
                    adaptor.addChild(root_1, stream_ppdExpr.nextTree());

                }
                stream_ppdExpr.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "expr"


    public static class definedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "definedExpr"
    // PreprocessorParser.g:127:1: definedExpr : DEFINED ( WS !)* ( identifier | LPAREN ! ( WS !)* identifier ( WS !)* RPAREN !) ;
    public final PreprocessorParser.definedExpr_return definedExpr() throws RecognitionException {
        PreprocessorParser.definedExpr_return retval = new PreprocessorParser.definedExpr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token DEFINED65=null;
        Token WS66=null;
        Token LPAREN68=null;
        Token WS69=null;
        Token WS71=null;
        Token RPAREN72=null;
        PreprocessorParser.identifier_return identifier67 =null;

        PreprocessorParser.identifier_return identifier70 =null;


        Object DEFINED65_tree=null;
        Object WS66_tree=null;
        Object LPAREN68_tree=null;
        Object WS69_tree=null;
        Object WS71_tree=null;
        Object RPAREN72_tree=null;

        try {
            // PreprocessorParser.g:127:13: ( DEFINED ( WS !)* ( identifier | LPAREN ! ( WS !)* identifier ( WS !)* RPAREN !) )
            // PreprocessorParser.g:127:15: DEFINED ( WS !)* ( identifier | LPAREN ! ( WS !)* identifier ( WS !)* RPAREN !)
            {
            root_0 = (Object)adaptor.nil();


            DEFINED65=(Token)match(input,DEFINED,FOLLOW_DEFINED_in_definedExpr700); 
            DEFINED65_tree = 
            (Object)adaptor.create(DEFINED65)
            ;
            adaptor.addChild(root_0, DEFINED65_tree);


            // PreprocessorParser.g:127:25: ( WS !)*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==WS) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // PreprocessorParser.g:127:25: WS !
            	    {
            	    WS66=(Token)match(input,WS,FOLLOW_WS_in_definedExpr702); 

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            // PreprocessorParser.g:128:4: ( identifier | LPAREN ! ( WS !)* identifier ( WS !)* RPAREN !)
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0 >= ALIGNAS && LA31_0 <= ALIGNOF)||LA31_0==ASSERT||LA31_0==ASSUME||(LA31_0 >= ATOMIC && LA31_0 <= AUTO)||(LA31_0 >= BOOL && LA31_0 <= BREAK)||LA31_0==CASE||LA31_0==CHAR||(LA31_0 >= CHOOSE && LA31_0 <= COLLECTIVE)||(LA31_0 >= COMPLEX && LA31_0 <= DEFAULT)||LA31_0==DO||LA31_0==DOUBLE||(LA31_0 >= ELSE && LA31_0 <= ENUM)||LA31_0==EXTERN||LA31_0==FLOAT||LA31_0==FOR||(LA31_0 >= GENERIC && LA31_0 <= GOTO)||(LA31_0 >= IDENTIFIER && LA31_0 <= INT)||LA31_0==INVARIANT||LA31_0==LONG||LA31_0==NORETURN||LA31_0==OUTPUT||LA31_0==PROC||(LA31_0 >= REGISTER && LA31_0 <= RETURN)||(LA31_0 >= SHORT && LA31_0 <= SPAWN)||(LA31_0 >= STATIC && LA31_0 <= STATICASSERT)||LA31_0==STRUCT||(LA31_0 >= SWITCH && LA31_0 <= THREADLOCAL)||(LA31_0 >= TYPEDEF && LA31_0 <= UNSIGNED)||(LA31_0 >= VOID && LA31_0 <= WHILE)) ) {
                alt31=1;
            }
            else if ( (LA31_0==LPAREN) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // PreprocessorParser.g:128:6: identifier
                    {
                    pushFollow(FOLLOW_identifier_in_definedExpr711);
                    identifier67=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier67.getTree());

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:129:6: LPAREN ! ( WS !)* identifier ( WS !)* RPAREN !
                    {
                    LPAREN68=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_definedExpr718); 

                    // PreprocessorParser.g:129:16: ( WS !)*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==WS) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // PreprocessorParser.g:129:16: WS !
                    	    {
                    	    WS69=(Token)match(input,WS,FOLLOW_WS_in_definedExpr721); 

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    pushFollow(FOLLOW_identifier_in_definedExpr725);
                    identifier70=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier70.getTree());

                    // PreprocessorParser.g:129:32: ( WS !)*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==WS) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // PreprocessorParser.g:129:32: WS !
                    	    {
                    	    WS71=(Token)match(input,WS,FOLLOW_WS_in_definedExpr727); 

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


                    RPAREN72=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_definedExpr731); 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "definedExpr"


    public static class ppdExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ppdExpr"
    // PreprocessorParser.g:133:1: ppdExpr : ( pptoken | definedExpr );
    public final PreprocessorParser.ppdExpr_return ppdExpr() throws RecognitionException {
        PreprocessorParser.ppdExpr_return retval = new PreprocessorParser.ppdExpr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.pptoken_return pptoken73 =null;

        PreprocessorParser.definedExpr_return definedExpr74 =null;



        try {
            // PreprocessorParser.g:133:10: ( pptoken | definedExpr )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0 >= ALIGNAS && LA32_0 <= BREAK)||LA32_0==CASE||(LA32_0 >= CHAR && LA32_0 <= COMMA)||(LA32_0 >= COMPLEX && LA32_0 <= DEFAULT)||(LA32_0 >= DIV && LA32_0 <= DOUBLE)||(LA32_0 >= ELLIPSIS && LA32_0 <= EXTERN)||(LA32_0 >= FLOAT && LA32_0 <= FOR)||(LA32_0 >= GENERIC && LA32_0 <= HEADER_NAME)||(LA32_0 >= IDENTIFIER && LA32_0 <= INVARIANT)||(LA32_0 >= LCURLY && LA32_0 <= LTE)||(LA32_0 >= MINUSMINUS && LA32_0 <= NEQ)||(LA32_0 >= NORETURN && LA32_0 <= NOT)||(LA32_0 >= OR && LA32_0 <= OUTPUT)||(LA32_0 >= PLUS && LA32_0 <= PP_NUMBER)||LA32_0==PROC||(LA32_0 >= QMARK && LA32_0 <= RSQUARE)||(LA32_0 >= SEMI && LA32_0 <= UNSIGNED)||(LA32_0 >= VOID && LA32_0 <= WHILE)) ) {
                alt32=1;
            }
            else if ( (LA32_0==DEFINED) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // PreprocessorParser.g:133:12: pptoken
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pptoken_in_ppdExpr752);
                    pptoken73=pptoken();

                    state._fsp--;

                    adaptor.addChild(root_0, pptoken73.getTree());

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:133:22: definedExpr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_definedExpr_in_ppdExpr756);
                    definedExpr74=definedExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, definedExpr74.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "ppdExpr"


    public static class elseblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elseblock"
    // PreprocessorParser.g:135:1: elseblock : ( simpleelseblock | elifblock );
    public final PreprocessorParser.elseblock_return elseblock() throws RecognitionException {
        PreprocessorParser.elseblock_return retval = new PreprocessorParser.elseblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        PreprocessorParser.simpleelseblock_return simpleelseblock75 =null;

        PreprocessorParser.elifblock_return elifblock76 =null;



        try {
            // PreprocessorParser.g:135:11: ( simpleelseblock | elifblock )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==PELSE) ) {
                alt33=1;
            }
            else if ( (LA33_0==PELIF) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // PreprocessorParser.g:135:13: simpleelseblock
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_simpleelseblock_in_elseblock765);
                    simpleelseblock75=simpleelseblock();

                    state._fsp--;

                    adaptor.addChild(root_0, simpleelseblock75.getTree());

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:135:31: elifblock
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_elifblock_in_elseblock769);
                    elifblock76=elifblock();

                    state._fsp--;

                    adaptor.addChild(root_0, elifblock76.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "elseblock"


    public static class simpleelseblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "simpleelseblock"
    // PreprocessorParser.g:137:1: simpleelseblock : PELSE ( white )* lineend block -> ^( PELSE ( block )? ) ;
    public final PreprocessorParser.simpleelseblock_return simpleelseblock() throws RecognitionException {
        PreprocessorParser.simpleelseblock_return retval = new PreprocessorParser.simpleelseblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PELSE77=null;
        PreprocessorParser.white_return white78 =null;

        PreprocessorParser.lineend_return lineend79 =null;

        PreprocessorParser.block_return block80 =null;


        Object PELSE77_tree=null;
        RewriteRuleTokenStream stream_PELSE=new RewriteRuleTokenStream(adaptor,"token PELSE");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // PreprocessorParser.g:137:17: ( PELSE ( white )* lineend block -> ^( PELSE ( block )? ) )
            // PreprocessorParser.g:137:19: PELSE ( white )* lineend block
            {
            PELSE77=(Token)match(input,PELSE,FOLLOW_PELSE_in_simpleelseblock778);  
            stream_PELSE.add(PELSE77);


            // PreprocessorParser.g:137:25: ( white )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMENT||LA34_0==WS) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // PreprocessorParser.g:137:25: white
            	    {
            	    pushFollow(FOLLOW_white_in_simpleelseblock780);
            	    white78=white();

            	    state._fsp--;

            	    stream_white.add(white78.getTree());

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_simpleelseblock783);
            lineend79=lineend();

            state._fsp--;

            stream_lineend.add(lineend79.getTree());

            pushFollow(FOLLOW_block_in_simpleelseblock785);
            block80=block();

            state._fsp--;

            stream_block.add(block80.getTree());

            // AST REWRITE
            // elements: block, PELSE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 137:46: -> ^( PELSE ( block )? )
            {
                // PreprocessorParser.g:137:49: ^( PELSE ( block )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PELSE.nextNode()
                , root_1);

                // PreprocessorParser.g:137:57: ( block )?
                if ( stream_block.hasNext() ) {
                    adaptor.addChild(root_1, stream_block.nextTree());

                }
                stream_block.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "simpleelseblock"


    public static class elifblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elifblock"
    // PreprocessorParser.g:139:1: elifblock : c= PELIF ( white )* expr lineend block ( elseblock )? -> ^( $c ^( $c expr ^( SEQUENCE ( block )? ) ( elseblock )? ) ) ;
    public final PreprocessorParser.elifblock_return elifblock() throws RecognitionException {
        PreprocessorParser.elifblock_return retval = new PreprocessorParser.elifblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token c=null;
        PreprocessorParser.white_return white81 =null;

        PreprocessorParser.expr_return expr82 =null;

        PreprocessorParser.lineend_return lineend83 =null;

        PreprocessorParser.block_return block84 =null;

        PreprocessorParser.elseblock_return elseblock85 =null;


        Object c_tree=null;
        RewriteRuleTokenStream stream_PELIF=new RewriteRuleTokenStream(adaptor,"token PELIF");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_elseblock=new RewriteRuleSubtreeStream(adaptor,"rule elseblock");
        try {
            // PreprocessorParser.g:139:11: (c= PELIF ( white )* expr lineend block ( elseblock )? -> ^( $c ^( $c expr ^( SEQUENCE ( block )? ) ( elseblock )? ) ) )
            // PreprocessorParser.g:139:13: c= PELIF ( white )* expr lineend block ( elseblock )?
            {
            c=(Token)match(input,PELIF,FOLLOW_PELIF_in_elifblock805);  
            stream_PELIF.add(c);


            // PreprocessorParser.g:139:21: ( white )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMENT||LA35_0==WS) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // PreprocessorParser.g:139:21: white
            	    {
            	    pushFollow(FOLLOW_white_in_elifblock807);
            	    white81=white();

            	    state._fsp--;

            	    stream_white.add(white81.getTree());

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            pushFollow(FOLLOW_expr_in_elifblock810);
            expr82=expr();

            state._fsp--;

            stream_expr.add(expr82.getTree());

            pushFollow(FOLLOW_lineend_in_elifblock812);
            lineend83=lineend();

            state._fsp--;

            stream_lineend.add(lineend83.getTree());

            pushFollow(FOLLOW_block_in_elifblock814);
            block84=block();

            state._fsp--;

            stream_block.add(block84.getTree());

            // PreprocessorParser.g:139:47: ( elseblock )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0 >= PELIF && LA36_0 <= PELSE)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // PreprocessorParser.g:139:47: elseblock
                    {
                    pushFollow(FOLLOW_elseblock_in_elifblock816);
                    elseblock85=elseblock();

                    state._fsp--;

                    stream_elseblock.add(elseblock85.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: c, elseblock, expr, block, c
            // token labels: c
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_c=new RewriteRuleTokenStream(adaptor,"token c",c);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 140:4: -> ^( $c ^( $c expr ^( SEQUENCE ( block )? ) ( elseblock )? ) )
            {
                // PreprocessorParser.g:141:4: ^( $c ^( $c expr ^( SEQUENCE ( block )? ) ( elseblock )? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_c.nextNode(), root_1);

                // PreprocessorParser.g:141:9: ^( $c expr ^( SEQUENCE ( block )? ) ( elseblock )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(stream_c.nextNode(), root_2);

                adaptor.addChild(root_2, stream_expr.nextTree());

                // PreprocessorParser.g:141:19: ^( SEQUENCE ( block )? )
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(SEQUENCE, "SEQUENCE")
                , root_3);

                // PreprocessorParser.g:141:30: ( block )?
                if ( stream_block.hasNext() ) {
                    adaptor.addChild(root_3, stream_block.nextTree());

                }
                stream_block.reset();

                adaptor.addChild(root_2, root_3);
                }

                // PreprocessorParser.g:141:38: ( elseblock )?
                if ( stream_elseblock.hasNext() ) {
                    adaptor.addChild(root_2, stream_elseblock.nextTree());

                }
                stream_elseblock.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "elifblock"


    public static class ifdefblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifdefblock"
    // PreprocessorParser.g:144:1: ifdefblock : PIFDEF ( white )* identifier ( white )* lineend block ( elseblock )? endifline -> ^( PIFDEF identifier ^( SEQUENCE ( block )? ) ( elseblock )? ) ;
    public final PreprocessorParser.ifdefblock_return ifdefblock() throws RecognitionException {
        PreprocessorParser.ifdefblock_return retval = new PreprocessorParser.ifdefblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIFDEF86=null;
        PreprocessorParser.white_return white87 =null;

        PreprocessorParser.identifier_return identifier88 =null;

        PreprocessorParser.white_return white89 =null;

        PreprocessorParser.lineend_return lineend90 =null;

        PreprocessorParser.block_return block91 =null;

        PreprocessorParser.elseblock_return elseblock92 =null;

        PreprocessorParser.endifline_return endifline93 =null;


        Object PIFDEF86_tree=null;
        RewriteRuleTokenStream stream_PIFDEF=new RewriteRuleTokenStream(adaptor,"token PIFDEF");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_endifline=new RewriteRuleSubtreeStream(adaptor,"rule endifline");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_elseblock=new RewriteRuleSubtreeStream(adaptor,"rule elseblock");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // PreprocessorParser.g:144:12: ( PIFDEF ( white )* identifier ( white )* lineend block ( elseblock )? endifline -> ^( PIFDEF identifier ^( SEQUENCE ( block )? ) ( elseblock )? ) )
            // PreprocessorParser.g:144:14: PIFDEF ( white )* identifier ( white )* lineend block ( elseblock )? endifline
            {
            PIFDEF86=(Token)match(input,PIFDEF,FOLLOW_PIFDEF_in_ifdefblock859);  
            stream_PIFDEF.add(PIFDEF86);


            // PreprocessorParser.g:144:21: ( white )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==COMMENT||LA37_0==WS) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // PreprocessorParser.g:144:21: white
            	    {
            	    pushFollow(FOLLOW_white_in_ifdefblock861);
            	    white87=white();

            	    state._fsp--;

            	    stream_white.add(white87.getTree());

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            pushFollow(FOLLOW_identifier_in_ifdefblock864);
            identifier88=identifier();

            state._fsp--;

            stream_identifier.add(identifier88.getTree());

            // PreprocessorParser.g:144:39: ( white )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==COMMENT||LA38_0==WS) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // PreprocessorParser.g:144:39: white
            	    {
            	    pushFollow(FOLLOW_white_in_ifdefblock866);
            	    white89=white();

            	    state._fsp--;

            	    stream_white.add(white89.getTree());

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_ifdefblock869);
            lineend90=lineend();

            state._fsp--;

            stream_lineend.add(lineend90.getTree());

            pushFollow(FOLLOW_block_in_ifdefblock874);
            block91=block();

            state._fsp--;

            stream_block.add(block91.getTree());

            // PreprocessorParser.g:145:10: ( elseblock )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0 >= PELIF && LA39_0 <= PELSE)) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // PreprocessorParser.g:145:10: elseblock
                    {
                    pushFollow(FOLLOW_elseblock_in_ifdefblock876);
                    elseblock92=elseblock();

                    state._fsp--;

                    stream_elseblock.add(elseblock92.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_endifline_in_ifdefblock879);
            endifline93=endifline();

            state._fsp--;

            stream_endifline.add(endifline93.getTree());

            // AST REWRITE
            // elements: identifier, elseblock, PIFDEF, block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 146:4: -> ^( PIFDEF identifier ^( SEQUENCE ( block )? ) ( elseblock )? )
            {
                // PreprocessorParser.g:146:7: ^( PIFDEF identifier ^( SEQUENCE ( block )? ) ( elseblock )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PIFDEF.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_identifier.nextTree());

                // PreprocessorParser.g:146:27: ^( SEQUENCE ( block )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(SEQUENCE, "SEQUENCE")
                , root_2);

                // PreprocessorParser.g:146:38: ( block )?
                if ( stream_block.hasNext() ) {
                    adaptor.addChild(root_2, stream_block.nextTree());

                }
                stream_block.reset();

                adaptor.addChild(root_1, root_2);
                }

                // PreprocessorParser.g:146:46: ( elseblock )?
                if ( stream_elseblock.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseblock.nextTree());

                }
                stream_elseblock.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "ifdefblock"


    public static class ifndefblock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifndefblock"
    // PreprocessorParser.g:149:1: ifndefblock : PIFNDEF ( white )* identifier ( white )* lineend block ( elseblock )? endifline -> ^( PIFNDEF identifier ^( SEQUENCE block ) ( elseblock )? ) ;
    public final PreprocessorParser.ifndefblock_return ifndefblock() throws RecognitionException {
        PreprocessorParser.ifndefblock_return retval = new PreprocessorParser.ifndefblock_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIFNDEF94=null;
        PreprocessorParser.white_return white95 =null;

        PreprocessorParser.identifier_return identifier96 =null;

        PreprocessorParser.white_return white97 =null;

        PreprocessorParser.lineend_return lineend98 =null;

        PreprocessorParser.block_return block99 =null;

        PreprocessorParser.elseblock_return elseblock100 =null;

        PreprocessorParser.endifline_return endifline101 =null;


        Object PIFNDEF94_tree=null;
        RewriteRuleTokenStream stream_PIFNDEF=new RewriteRuleTokenStream(adaptor,"token PIFNDEF");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_endifline=new RewriteRuleSubtreeStream(adaptor,"rule endifline");
        RewriteRuleSubtreeStream stream_white=new RewriteRuleSubtreeStream(adaptor,"rule white");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_elseblock=new RewriteRuleSubtreeStream(adaptor,"rule elseblock");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // PreprocessorParser.g:149:13: ( PIFNDEF ( white )* identifier ( white )* lineend block ( elseblock )? endifline -> ^( PIFNDEF identifier ^( SEQUENCE block ) ( elseblock )? ) )
            // PreprocessorParser.g:149:15: PIFNDEF ( white )* identifier ( white )* lineend block ( elseblock )? endifline
            {
            PIFNDEF94=(Token)match(input,PIFNDEF,FOLLOW_PIFNDEF_in_ifndefblock911);  
            stream_PIFNDEF.add(PIFNDEF94);


            // PreprocessorParser.g:149:23: ( white )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==COMMENT||LA40_0==WS) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // PreprocessorParser.g:149:23: white
            	    {
            	    pushFollow(FOLLOW_white_in_ifndefblock913);
            	    white95=white();

            	    state._fsp--;

            	    stream_white.add(white95.getTree());

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            pushFollow(FOLLOW_identifier_in_ifndefblock916);
            identifier96=identifier();

            state._fsp--;

            stream_identifier.add(identifier96.getTree());

            // PreprocessorParser.g:149:41: ( white )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==COMMENT||LA41_0==WS) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // PreprocessorParser.g:149:41: white
            	    {
            	    pushFollow(FOLLOW_white_in_ifndefblock918);
            	    white97=white();

            	    state._fsp--;

            	    stream_white.add(white97.getTree());

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_ifndefblock921);
            lineend98=lineend();

            state._fsp--;

            stream_lineend.add(lineend98.getTree());

            pushFollow(FOLLOW_block_in_ifndefblock926);
            block99=block();

            state._fsp--;

            stream_block.add(block99.getTree());

            // PreprocessorParser.g:150:10: ( elseblock )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( ((LA42_0 >= PELIF && LA42_0 <= PELSE)) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // PreprocessorParser.g:150:10: elseblock
                    {
                    pushFollow(FOLLOW_elseblock_in_ifndefblock928);
                    elseblock100=elseblock();

                    state._fsp--;

                    stream_elseblock.add(elseblock100.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_endifline_in_ifndefblock931);
            endifline101=endifline();

            state._fsp--;

            stream_endifline.add(endifline101.getTree());

            // AST REWRITE
            // elements: PIFNDEF, elseblock, identifier, block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 151:4: -> ^( PIFNDEF identifier ^( SEQUENCE block ) ( elseblock )? )
            {
                // PreprocessorParser.g:151:7: ^( PIFNDEF identifier ^( SEQUENCE block ) ( elseblock )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PIFNDEF.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_identifier.nextTree());

                // PreprocessorParser.g:151:28: ^( SEQUENCE block )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(SEQUENCE, "SEQUENCE")
                , root_2);

                adaptor.addChild(root_2, stream_block.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                // PreprocessorParser.g:151:46: ( elseblock )?
                if ( stream_elseblock.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseblock.nextTree());

                }
                stream_elseblock.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "ifndefblock"


    public static class endifline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "endifline"
    // PreprocessorParser.g:154:1: endifline : PENDIF ( white )* lineend ;
    public final PreprocessorParser.endifline_return endifline() throws RecognitionException {
        PreprocessorParser.endifline_return retval = new PreprocessorParser.endifline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PENDIF102=null;
        PreprocessorParser.white_return white103 =null;

        PreprocessorParser.lineend_return lineend104 =null;


        Object PENDIF102_tree=null;

        try {
            // PreprocessorParser.g:154:11: ( PENDIF ( white )* lineend )
            // PreprocessorParser.g:154:13: PENDIF ( white )* lineend
            {
            root_0 = (Object)adaptor.nil();


            PENDIF102=(Token)match(input,PENDIF,FOLLOW_PENDIF_in_endifline962); 
            PENDIF102_tree = 
            (Object)adaptor.create(PENDIF102)
            ;
            adaptor.addChild(root_0, PENDIF102_tree);


            // PreprocessorParser.g:154:20: ( white )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==COMMENT||LA43_0==WS) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // PreprocessorParser.g:154:20: white
            	    {
            	    pushFollow(FOLLOW_white_in_endifline964);
            	    white103=white();

            	    state._fsp--;

            	    adaptor.addChild(root_0, white103.getTree());

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_endifline967);
            lineend104=lineend();

            state._fsp--;

            adaptor.addChild(root_0, lineend104.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "endifline"


    public static class pragmaline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pragmaline"
    // PreprocessorParser.g:156:1: pragmaline : PRAGMA ( wpptoken )* lineend -> ^( PRAGMA ( wpptoken )* lineend ) ;
    public final PreprocessorParser.pragmaline_return pragmaline() throws RecognitionException {
        PreprocessorParser.pragmaline_return retval = new PreprocessorParser.pragmaline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PRAGMA105=null;
        PreprocessorParser.wpptoken_return wpptoken106 =null;

        PreprocessorParser.lineend_return lineend107 =null;


        Object PRAGMA105_tree=null;
        RewriteRuleTokenStream stream_PRAGMA=new RewriteRuleTokenStream(adaptor,"token PRAGMA");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_wpptoken=new RewriteRuleSubtreeStream(adaptor,"rule wpptoken");
        try {
            // PreprocessorParser.g:156:12: ( PRAGMA ( wpptoken )* lineend -> ^( PRAGMA ( wpptoken )* lineend ) )
            // PreprocessorParser.g:156:14: PRAGMA ( wpptoken )* lineend
            {
            PRAGMA105=(Token)match(input,PRAGMA,FOLLOW_PRAGMA_in_pragmaline976);  
            stream_PRAGMA.add(PRAGMA105);


            // PreprocessorParser.g:156:21: ( wpptoken )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0 >= ALIGNAS && LA44_0 <= BREAK)||LA44_0==CASE||(LA44_0 >= CHAR && LA44_0 <= DEFAULT)||(LA44_0 >= DIV && LA44_0 <= DOUBLE)||(LA44_0 >= ELLIPSIS && LA44_0 <= EXTERN)||(LA44_0 >= FLOAT && LA44_0 <= FOR)||(LA44_0 >= GENERIC && LA44_0 <= HEADER_NAME)||(LA44_0 >= IDENTIFIER && LA44_0 <= INVARIANT)||(LA44_0 >= LCURLY && LA44_0 <= LTE)||(LA44_0 >= MINUSMINUS && LA44_0 <= NEQ)||(LA44_0 >= NORETURN && LA44_0 <= NOT)||(LA44_0 >= OR && LA44_0 <= OUTPUT)||(LA44_0 >= PLUS && LA44_0 <= PP_NUMBER)||LA44_0==PROC||(LA44_0 >= QMARK && LA44_0 <= RSQUARE)||(LA44_0 >= SEMI && LA44_0 <= UNSIGNED)||(LA44_0 >= VOID && LA44_0 <= WS)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // PreprocessorParser.g:156:21: wpptoken
            	    {
            	    pushFollow(FOLLOW_wpptoken_in_pragmaline978);
            	    wpptoken106=wpptoken();

            	    state._fsp--;

            	    stream_wpptoken.add(wpptoken106.getTree());

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_pragmaline981);
            lineend107=lineend();

            state._fsp--;

            stream_lineend.add(lineend107.getTree());

            // AST REWRITE
            // elements: lineend, wpptoken, PRAGMA
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 156:39: -> ^( PRAGMA ( wpptoken )* lineend )
            {
                // PreprocessorParser.g:156:42: ^( PRAGMA ( wpptoken )* lineend )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PRAGMA.nextNode()
                , root_1);

                // PreprocessorParser.g:156:51: ( wpptoken )*
                while ( stream_wpptoken.hasNext() ) {
                    adaptor.addChild(root_1, stream_wpptoken.nextTree());

                }
                stream_wpptoken.reset();

                adaptor.addChild(root_1, stream_lineend.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "pragmaline"


    public static class errorline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "errorline"
    // PreprocessorParser.g:158:1: errorline : PERROR ( wpptoken )* lineend -> ^( PERROR ( wpptoken )* ) ;
    public final PreprocessorParser.errorline_return errorline() throws RecognitionException {
        PreprocessorParser.errorline_return retval = new PreprocessorParser.errorline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PERROR108=null;
        PreprocessorParser.wpptoken_return wpptoken109 =null;

        PreprocessorParser.lineend_return lineend110 =null;


        Object PERROR108_tree=null;
        RewriteRuleTokenStream stream_PERROR=new RewriteRuleTokenStream(adaptor,"token PERROR");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_wpptoken=new RewriteRuleSubtreeStream(adaptor,"rule wpptoken");
        try {
            // PreprocessorParser.g:158:11: ( PERROR ( wpptoken )* lineend -> ^( PERROR ( wpptoken )* ) )
            // PreprocessorParser.g:158:13: PERROR ( wpptoken )* lineend
            {
            PERROR108=(Token)match(input,PERROR,FOLLOW_PERROR_in_errorline1001);  
            stream_PERROR.add(PERROR108);


            // PreprocessorParser.g:158:20: ( wpptoken )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0 >= ALIGNAS && LA45_0 <= BREAK)||LA45_0==CASE||(LA45_0 >= CHAR && LA45_0 <= DEFAULT)||(LA45_0 >= DIV && LA45_0 <= DOUBLE)||(LA45_0 >= ELLIPSIS && LA45_0 <= EXTERN)||(LA45_0 >= FLOAT && LA45_0 <= FOR)||(LA45_0 >= GENERIC && LA45_0 <= HEADER_NAME)||(LA45_0 >= IDENTIFIER && LA45_0 <= INVARIANT)||(LA45_0 >= LCURLY && LA45_0 <= LTE)||(LA45_0 >= MINUSMINUS && LA45_0 <= NEQ)||(LA45_0 >= NORETURN && LA45_0 <= NOT)||(LA45_0 >= OR && LA45_0 <= OUTPUT)||(LA45_0 >= PLUS && LA45_0 <= PP_NUMBER)||LA45_0==PROC||(LA45_0 >= QMARK && LA45_0 <= RSQUARE)||(LA45_0 >= SEMI && LA45_0 <= UNSIGNED)||(LA45_0 >= VOID && LA45_0 <= WS)) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // PreprocessorParser.g:158:20: wpptoken
            	    {
            	    pushFollow(FOLLOW_wpptoken_in_errorline1003);
            	    wpptoken109=wpptoken();

            	    state._fsp--;

            	    stream_wpptoken.add(wpptoken109.getTree());

            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_errorline1006);
            lineend110=lineend();

            state._fsp--;

            stream_lineend.add(lineend110.getTree());

            // AST REWRITE
            // elements: wpptoken, PERROR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 158:38: -> ^( PERROR ( wpptoken )* )
            {
                // PreprocessorParser.g:158:41: ^( PERROR ( wpptoken )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PERROR.nextNode()
                , root_1);

                // PreprocessorParser.g:158:50: ( wpptoken )*
                while ( stream_wpptoken.hasNext() ) {
                    adaptor.addChild(root_1, stream_wpptoken.nextTree());

                }
                stream_wpptoken.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "errorline"


    public static class lineline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lineline"
    // PreprocessorParser.g:160:1: lineline : PLINE ( wpptoken )* lineend -> ^( PLINE ( wpptoken )* ) ;
    public final PreprocessorParser.lineline_return lineline() throws RecognitionException {
        PreprocessorParser.lineline_return retval = new PreprocessorParser.lineline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLINE111=null;
        PreprocessorParser.wpptoken_return wpptoken112 =null;

        PreprocessorParser.lineend_return lineend113 =null;


        Object PLINE111_tree=null;
        RewriteRuleTokenStream stream_PLINE=new RewriteRuleTokenStream(adaptor,"token PLINE");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_wpptoken=new RewriteRuleSubtreeStream(adaptor,"rule wpptoken");
        try {
            // PreprocessorParser.g:160:10: ( PLINE ( wpptoken )* lineend -> ^( PLINE ( wpptoken )* ) )
            // PreprocessorParser.g:160:12: PLINE ( wpptoken )* lineend
            {
            PLINE111=(Token)match(input,PLINE,FOLLOW_PLINE_in_lineline1024);  
            stream_PLINE.add(PLINE111);


            // PreprocessorParser.g:160:18: ( wpptoken )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0 >= ALIGNAS && LA46_0 <= BREAK)||LA46_0==CASE||(LA46_0 >= CHAR && LA46_0 <= DEFAULT)||(LA46_0 >= DIV && LA46_0 <= DOUBLE)||(LA46_0 >= ELLIPSIS && LA46_0 <= EXTERN)||(LA46_0 >= FLOAT && LA46_0 <= FOR)||(LA46_0 >= GENERIC && LA46_0 <= HEADER_NAME)||(LA46_0 >= IDENTIFIER && LA46_0 <= INVARIANT)||(LA46_0 >= LCURLY && LA46_0 <= LTE)||(LA46_0 >= MINUSMINUS && LA46_0 <= NEQ)||(LA46_0 >= NORETURN && LA46_0 <= NOT)||(LA46_0 >= OR && LA46_0 <= OUTPUT)||(LA46_0 >= PLUS && LA46_0 <= PP_NUMBER)||LA46_0==PROC||(LA46_0 >= QMARK && LA46_0 <= RSQUARE)||(LA46_0 >= SEMI && LA46_0 <= UNSIGNED)||(LA46_0 >= VOID && LA46_0 <= WS)) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // PreprocessorParser.g:160:18: wpptoken
            	    {
            	    pushFollow(FOLLOW_wpptoken_in_lineline1026);
            	    wpptoken112=wpptoken();

            	    state._fsp--;

            	    stream_wpptoken.add(wpptoken112.getTree());

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_lineline1029);
            lineend113=lineend();

            state._fsp--;

            stream_lineend.add(lineend113.getTree());

            // AST REWRITE
            // elements: PLINE, wpptoken
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 160:36: -> ^( PLINE ( wpptoken )* )
            {
                // PreprocessorParser.g:160:39: ^( PLINE ( wpptoken )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_PLINE.nextNode()
                , root_1);

                // PreprocessorParser.g:160:47: ( wpptoken )*
                while ( stream_wpptoken.hasNext() ) {
                    adaptor.addChild(root_1, stream_wpptoken.nextTree());

                }
                stream_wpptoken.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "lineline"


    public static class nondirectiveline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nondirectiveline"
    // PreprocessorParser.g:162:1: nondirectiveline : HASH ( wpptoken )* lineend -> ^( HASH ( wpptoken )* ) ;
    public final PreprocessorParser.nondirectiveline_return nondirectiveline() throws RecognitionException {
        PreprocessorParser.nondirectiveline_return retval = new PreprocessorParser.nondirectiveline_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token HASH114=null;
        PreprocessorParser.wpptoken_return wpptoken115 =null;

        PreprocessorParser.lineend_return lineend116 =null;


        Object HASH114_tree=null;
        RewriteRuleTokenStream stream_HASH=new RewriteRuleTokenStream(adaptor,"token HASH");
        RewriteRuleSubtreeStream stream_lineend=new RewriteRuleSubtreeStream(adaptor,"rule lineend");
        RewriteRuleSubtreeStream stream_wpptoken=new RewriteRuleSubtreeStream(adaptor,"rule wpptoken");
        try {
            // PreprocessorParser.g:163:3: ( HASH ( wpptoken )* lineend -> ^( HASH ( wpptoken )* ) )
            // PreprocessorParser.g:163:5: HASH ( wpptoken )* lineend
            {
            HASH114=(Token)match(input,HASH,FOLLOW_HASH_in_nondirectiveline1049);  
            stream_HASH.add(HASH114);


            // PreprocessorParser.g:163:10: ( wpptoken )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( ((LA47_0 >= ALIGNAS && LA47_0 <= BREAK)||LA47_0==CASE||(LA47_0 >= CHAR && LA47_0 <= DEFAULT)||(LA47_0 >= DIV && LA47_0 <= DOUBLE)||(LA47_0 >= ELLIPSIS && LA47_0 <= EXTERN)||(LA47_0 >= FLOAT && LA47_0 <= FOR)||(LA47_0 >= GENERIC && LA47_0 <= HEADER_NAME)||(LA47_0 >= IDENTIFIER && LA47_0 <= INVARIANT)||(LA47_0 >= LCURLY && LA47_0 <= LTE)||(LA47_0 >= MINUSMINUS && LA47_0 <= NEQ)||(LA47_0 >= NORETURN && LA47_0 <= NOT)||(LA47_0 >= OR && LA47_0 <= OUTPUT)||(LA47_0 >= PLUS && LA47_0 <= PP_NUMBER)||LA47_0==PROC||(LA47_0 >= QMARK && LA47_0 <= RSQUARE)||(LA47_0 >= SEMI && LA47_0 <= UNSIGNED)||(LA47_0 >= VOID && LA47_0 <= WS)) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // PreprocessorParser.g:163:10: wpptoken
            	    {
            	    pushFollow(FOLLOW_wpptoken_in_nondirectiveline1051);
            	    wpptoken115=wpptoken();

            	    state._fsp--;

            	    stream_wpptoken.add(wpptoken115.getTree());

            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            pushFollow(FOLLOW_lineend_in_nondirectiveline1054);
            lineend116=lineend();

            state._fsp--;

            stream_lineend.add(lineend116.getTree());

            // AST REWRITE
            // elements: HASH, wpptoken
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 163:28: -> ^( HASH ( wpptoken )* )
            {
                // PreprocessorParser.g:163:31: ^( HASH ( wpptoken )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_HASH.nextNode()
                , root_1);

                // PreprocessorParser.g:163:38: ( wpptoken )*
                while ( stream_wpptoken.hasNext() ) {
                    adaptor.addChild(root_1, stream_wpptoken.nextTree());

                }
                stream_wpptoken.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "nondirectiveline"


    public static class pptoken_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pptoken"
    // PreprocessorParser.g:166:1: pptoken : ( HEADER_NAME | identifier | pp_number | CHARACTER_CONSTANT | STRING_LITERAL | punctuator | OTHER );
    public final PreprocessorParser.pptoken_return pptoken() throws RecognitionException {
        PreprocessorParser.pptoken_return retval = new PreprocessorParser.pptoken_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token HEADER_NAME117=null;
        Token CHARACTER_CONSTANT120=null;
        Token STRING_LITERAL121=null;
        Token OTHER123=null;
        PreprocessorParser.identifier_return identifier118 =null;

        PreprocessorParser.pp_number_return pp_number119 =null;

        PreprocessorParser.punctuator_return punctuator122 =null;


        Object HEADER_NAME117_tree=null;
        Object CHARACTER_CONSTANT120_tree=null;
        Object STRING_LITERAL121_tree=null;
        Object OTHER123_tree=null;

        try {
            // PreprocessorParser.g:166:10: ( HEADER_NAME | identifier | pp_number | CHARACTER_CONSTANT | STRING_LITERAL | punctuator | OTHER )
            int alt48=7;
            switch ( input.LA(1) ) {
            case HEADER_NAME:
                {
                alt48=1;
                }
                break;
            case ALIGNAS:
            case ALIGNOF:
            case ASSERT:
            case ASSUME:
            case ATOMIC:
            case AUTO:
            case BOOL:
            case BREAK:
            case CASE:
            case CHAR:
            case CHOOSE:
            case COLLECTIVE:
            case COMPLEX:
            case CONST:
            case CONTINUE:
            case DEFAULT:
            case DO:
            case DOUBLE:
            case ELSE:
            case ENUM:
            case EXTERN:
            case FLOAT:
            case FOR:
            case GENERIC:
            case GOTO:
            case IDENTIFIER:
            case IF:
            case IMAGINARY:
            case INLINE:
            case INPUT:
            case INT:
            case INVARIANT:
            case LONG:
            case NORETURN:
            case OUTPUT:
            case PROC:
            case REGISTER:
            case RESTRICT:
            case RETURN:
            case SHORT:
            case SIGNED:
            case SIZEOF:
            case SPAWN:
            case STATIC:
            case STATICASSERT:
            case STRUCT:
            case SWITCH:
            case THREADLOCAL:
            case TYPEDEF:
            case UNION:
            case UNSIGNED:
            case VOID:
            case VOLATILE:
            case WAIT:
            case WHEN:
            case WHILE:
                {
                alt48=2;
                }
                break;
            case FLOATING_CONSTANT:
            case INTEGER_CONSTANT:
            case PP_NUMBER:
                {
                alt48=3;
                }
                break;
            case CHARACTER_CONSTANT:
                {
                alt48=4;
                }
                break;
            case STRING_LITERAL:
                {
                alt48=5;
                }
                break;
            case AMPERSAND:
            case AND:
            case ARROW:
            case ASSIGN:
            case AT:
            case BITANDEQ:
            case BITOR:
            case BITOREQ:
            case BITXOR:
            case BITXOREQ:
            case COLON:
            case COMMA:
            case DIV:
            case DIVEQ:
            case DOT:
            case ELLIPSIS:
            case EQUALS:
            case GT:
            case GTE:
            case HASH:
            case HASHHASH:
            case LCURLY:
            case LPAREN:
            case LSQUARE:
            case LT:
            case LTE:
            case MINUSMINUS:
            case MOD:
            case MODEQ:
            case NEQ:
            case NOT:
            case OR:
            case PLUS:
            case PLUSEQ:
            case PLUSPLUS:
            case QMARK:
            case RCURLY:
            case RPAREN:
            case RSQUARE:
            case SEMI:
            case SHIFTLEFT:
            case SHIFTLEFTEQ:
            case SHIFTRIGHT:
            case SHIFTRIGHTEQ:
            case STAR:
            case STAREQ:
            case SUB:
            case SUBEQ:
            case TILDE:
                {
                alt48=6;
                }
                break;
            case OTHER:
                {
                alt48=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;

            }

            switch (alt48) {
                case 1 :
                    // PreprocessorParser.g:166:12: HEADER_NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    HEADER_NAME117=(Token)match(input,HEADER_NAME,FOLLOW_HEADER_NAME_in_pptoken1075); 
                    HEADER_NAME117_tree = 
                    (Object)adaptor.create(HEADER_NAME117)
                    ;
                    adaptor.addChild(root_0, HEADER_NAME117_tree);


                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:167:5: identifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_identifier_in_pptoken1081);
                    identifier118=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier118.getTree());

                    }
                    break;
                case 3 :
                    // PreprocessorParser.g:168:5: pp_number
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pp_number_in_pptoken1087);
                    pp_number119=pp_number();

                    state._fsp--;

                    adaptor.addChild(root_0, pp_number119.getTree());

                    }
                    break;
                case 4 :
                    // PreprocessorParser.g:169:5: CHARACTER_CONSTANT
                    {
                    root_0 = (Object)adaptor.nil();


                    CHARACTER_CONSTANT120=(Token)match(input,CHARACTER_CONSTANT,FOLLOW_CHARACTER_CONSTANT_in_pptoken1093); 
                    CHARACTER_CONSTANT120_tree = 
                    (Object)adaptor.create(CHARACTER_CONSTANT120)
                    ;
                    adaptor.addChild(root_0, CHARACTER_CONSTANT120_tree);


                    }
                    break;
                case 5 :
                    // PreprocessorParser.g:170:5: STRING_LITERAL
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING_LITERAL121=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_pptoken1099); 
                    STRING_LITERAL121_tree = 
                    (Object)adaptor.create(STRING_LITERAL121)
                    ;
                    adaptor.addChild(root_0, STRING_LITERAL121_tree);


                    }
                    break;
                case 6 :
                    // PreprocessorParser.g:171:5: punctuator
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_punctuator_in_pptoken1105);
                    punctuator122=punctuator();

                    state._fsp--;

                    adaptor.addChild(root_0, punctuator122.getTree());

                    }
                    break;
                case 7 :
                    // PreprocessorParser.g:172:5: OTHER
                    {
                    root_0 = (Object)adaptor.nil();


                    OTHER123=(Token)match(input,OTHER,FOLLOW_OTHER_in_pptoken1111); 
                    OTHER123_tree = 
                    (Object)adaptor.create(OTHER123)
                    ;
                    adaptor.addChild(root_0, OTHER123_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "pptoken"


    public static class nonPoundPpToken_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nonPoundPpToken"
    // PreprocessorParser.g:176:1: nonPoundPpToken : ( HEADER_NAME | identifier | pp_number | CHARACTER_CONSTANT | STRING_LITERAL | nonPoundPunctuator | OTHER );
    public final PreprocessorParser.nonPoundPpToken_return nonPoundPpToken() throws RecognitionException {
        PreprocessorParser.nonPoundPpToken_return retval = new PreprocessorParser.nonPoundPpToken_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token HEADER_NAME124=null;
        Token CHARACTER_CONSTANT127=null;
        Token STRING_LITERAL128=null;
        Token OTHER130=null;
        PreprocessorParser.identifier_return identifier125 =null;

        PreprocessorParser.pp_number_return pp_number126 =null;

        PreprocessorParser.nonPoundPunctuator_return nonPoundPunctuator129 =null;


        Object HEADER_NAME124_tree=null;
        Object CHARACTER_CONSTANT127_tree=null;
        Object STRING_LITERAL128_tree=null;
        Object OTHER130_tree=null;

        try {
            // PreprocessorParser.g:176:17: ( HEADER_NAME | identifier | pp_number | CHARACTER_CONSTANT | STRING_LITERAL | nonPoundPunctuator | OTHER )
            int alt49=7;
            switch ( input.LA(1) ) {
            case HEADER_NAME:
                {
                alt49=1;
                }
                break;
            case ALIGNAS:
            case ALIGNOF:
            case ASSERT:
            case ASSUME:
            case ATOMIC:
            case AUTO:
            case BOOL:
            case BREAK:
            case CASE:
            case CHAR:
            case CHOOSE:
            case COLLECTIVE:
            case COMPLEX:
            case CONST:
            case CONTINUE:
            case DEFAULT:
            case DO:
            case DOUBLE:
            case ELSE:
            case ENUM:
            case EXTERN:
            case FLOAT:
            case FOR:
            case GENERIC:
            case GOTO:
            case IDENTIFIER:
            case IF:
            case IMAGINARY:
            case INLINE:
            case INPUT:
            case INT:
            case INVARIANT:
            case LONG:
            case NORETURN:
            case OUTPUT:
            case PROC:
            case REGISTER:
            case RESTRICT:
            case RETURN:
            case SHORT:
            case SIGNED:
            case SIZEOF:
            case SPAWN:
            case STATIC:
            case STATICASSERT:
            case STRUCT:
            case SWITCH:
            case THREADLOCAL:
            case TYPEDEF:
            case UNION:
            case UNSIGNED:
            case VOID:
            case VOLATILE:
            case WAIT:
            case WHEN:
            case WHILE:
                {
                alt49=2;
                }
                break;
            case FLOATING_CONSTANT:
            case INTEGER_CONSTANT:
            case PP_NUMBER:
                {
                alt49=3;
                }
                break;
            case CHARACTER_CONSTANT:
                {
                alt49=4;
                }
                break;
            case STRING_LITERAL:
                {
                alt49=5;
                }
                break;
            case AMPERSAND:
            case AND:
            case ARROW:
            case ASSIGN:
            case AT:
            case BITANDEQ:
            case BITOR:
            case BITOREQ:
            case BITXOR:
            case BITXOREQ:
            case COLON:
            case COMMA:
            case DIV:
            case DIVEQ:
            case DOT:
            case ELLIPSIS:
            case EQUALS:
            case GT:
            case GTE:
            case HASHHASH:
            case LCURLY:
            case LPAREN:
            case LSQUARE:
            case LT:
            case LTE:
            case MINUSMINUS:
            case MOD:
            case MODEQ:
            case NEQ:
            case NOT:
            case OR:
            case PLUS:
            case PLUSEQ:
            case PLUSPLUS:
            case QMARK:
            case RCURLY:
            case RPAREN:
            case RSQUARE:
            case SEMI:
            case SHIFTLEFT:
            case SHIFTLEFTEQ:
            case SHIFTRIGHT:
            case SHIFTRIGHTEQ:
            case STAR:
            case STAREQ:
            case SUB:
            case SUBEQ:
            case TILDE:
                {
                alt49=6;
                }
                break;
            case OTHER:
                {
                alt49=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }

            switch (alt49) {
                case 1 :
                    // PreprocessorParser.g:176:19: HEADER_NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    HEADER_NAME124=(Token)match(input,HEADER_NAME,FOLLOW_HEADER_NAME_in_nonPoundPpToken1124); 
                    HEADER_NAME124_tree = 
                    (Object)adaptor.create(HEADER_NAME124)
                    ;
                    adaptor.addChild(root_0, HEADER_NAME124_tree);


                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:177:5: identifier
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_identifier_in_nonPoundPpToken1130);
                    identifier125=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier125.getTree());

                    }
                    break;
                case 3 :
                    // PreprocessorParser.g:178:5: pp_number
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pp_number_in_nonPoundPpToken1136);
                    pp_number126=pp_number();

                    state._fsp--;

                    adaptor.addChild(root_0, pp_number126.getTree());

                    }
                    break;
                case 4 :
                    // PreprocessorParser.g:179:5: CHARACTER_CONSTANT
                    {
                    root_0 = (Object)adaptor.nil();


                    CHARACTER_CONSTANT127=(Token)match(input,CHARACTER_CONSTANT,FOLLOW_CHARACTER_CONSTANT_in_nonPoundPpToken1142); 
                    CHARACTER_CONSTANT127_tree = 
                    (Object)adaptor.create(CHARACTER_CONSTANT127)
                    ;
                    adaptor.addChild(root_0, CHARACTER_CONSTANT127_tree);


                    }
                    break;
                case 5 :
                    // PreprocessorParser.g:180:5: STRING_LITERAL
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING_LITERAL128=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_nonPoundPpToken1148); 
                    STRING_LITERAL128_tree = 
                    (Object)adaptor.create(STRING_LITERAL128)
                    ;
                    adaptor.addChild(root_0, STRING_LITERAL128_tree);


                    }
                    break;
                case 6 :
                    // PreprocessorParser.g:181:5: nonPoundPunctuator
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_nonPoundPunctuator_in_nonPoundPpToken1154);
                    nonPoundPunctuator129=nonPoundPunctuator();

                    state._fsp--;

                    adaptor.addChild(root_0, nonPoundPunctuator129.getTree());

                    }
                    break;
                case 7 :
                    // PreprocessorParser.g:182:5: OTHER
                    {
                    root_0 = (Object)adaptor.nil();


                    OTHER130=(Token)match(input,OTHER,FOLLOW_OTHER_in_nonPoundPpToken1160); 
                    OTHER130_tree = 
                    (Object)adaptor.create(OTHER130)
                    ;
                    adaptor.addChild(root_0, OTHER130_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "nonPoundPpToken"


    public static class identifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "identifier"
    // PreprocessorParser.g:188:1: identifier : ( IDENTIFIER | c_keyword );
    public final PreprocessorParser.identifier_return identifier() throws RecognitionException {
        PreprocessorParser.identifier_return retval = new PreprocessorParser.identifier_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IDENTIFIER131=null;
        PreprocessorParser.c_keyword_return c_keyword132 =null;


        Object IDENTIFIER131_tree=null;

        try {
            // PreprocessorParser.g:188:12: ( IDENTIFIER | c_keyword )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==IDENTIFIER) ) {
                alt50=1;
            }
            else if ( ((LA50_0 >= ALIGNAS && LA50_0 <= ALIGNOF)||LA50_0==ASSERT||LA50_0==ASSUME||(LA50_0 >= ATOMIC && LA50_0 <= AUTO)||(LA50_0 >= BOOL && LA50_0 <= BREAK)||LA50_0==CASE||LA50_0==CHAR||(LA50_0 >= CHOOSE && LA50_0 <= COLLECTIVE)||(LA50_0 >= COMPLEX && LA50_0 <= DEFAULT)||LA50_0==DO||LA50_0==DOUBLE||(LA50_0 >= ELSE && LA50_0 <= ENUM)||LA50_0==EXTERN||LA50_0==FLOAT||LA50_0==FOR||(LA50_0 >= GENERIC && LA50_0 <= GOTO)||(LA50_0 >= IF && LA50_0 <= INT)||LA50_0==INVARIANT||LA50_0==LONG||LA50_0==NORETURN||LA50_0==OUTPUT||LA50_0==PROC||(LA50_0 >= REGISTER && LA50_0 <= RETURN)||(LA50_0 >= SHORT && LA50_0 <= SPAWN)||(LA50_0 >= STATIC && LA50_0 <= STATICASSERT)||LA50_0==STRUCT||(LA50_0 >= SWITCH && LA50_0 <= THREADLOCAL)||(LA50_0 >= TYPEDEF && LA50_0 <= UNSIGNED)||(LA50_0 >= VOID && LA50_0 <= WHILE)) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }
            switch (alt50) {
                case 1 :
                    // PreprocessorParser.g:188:14: IDENTIFIER
                    {
                    root_0 = (Object)adaptor.nil();


                    IDENTIFIER131=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifier1178); 
                    IDENTIFIER131_tree = 
                    (Object)adaptor.create(IDENTIFIER131)
                    ;
                    adaptor.addChild(root_0, IDENTIFIER131_tree);


                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:188:27: c_keyword
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_c_keyword_in_identifier1182);
                    c_keyword132=c_keyword();

                    state._fsp--;

                    adaptor.addChild(root_0, c_keyword132.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "identifier"


    public static class c_keyword_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "c_keyword"
    // PreprocessorParser.g:190:1: c_keyword : ( AUTO | BREAK | CASE | CHAR | CONST | CONTINUE | DEFAULT | DO | DOUBLE | ELSE | ENUM | EXTERN | FLOAT | FOR | GOTO | IF | INLINE | INT | LONG | REGISTER | RESTRICT | RETURN | SHORT | SIGNED | SIZEOF | STATIC | STRUCT | SWITCH | TYPEDEF | UNION | UNSIGNED | VOID | VOLATILE | WHILE | ALIGNAS | ALIGNOF | ATOMIC | BOOL | COMPLEX | GENERIC | IMAGINARY | NORETURN | STATICASSERT | THREADLOCAL | ASSERT | ASSUME | CHOOSE | COLLECTIVE | INPUT | INVARIANT | OUTPUT | PROC | SPAWN | WAIT | WHEN );
    public final PreprocessorParser.c_keyword_return c_keyword() throws RecognitionException {
        PreprocessorParser.c_keyword_return retval = new PreprocessorParser.c_keyword_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set133=null;

        Object set133_tree=null;

        try {
            // PreprocessorParser.g:190:11: ( AUTO | BREAK | CASE | CHAR | CONST | CONTINUE | DEFAULT | DO | DOUBLE | ELSE | ENUM | EXTERN | FLOAT | FOR | GOTO | IF | INLINE | INT | LONG | REGISTER | RESTRICT | RETURN | SHORT | SIGNED | SIZEOF | STATIC | STRUCT | SWITCH | TYPEDEF | UNION | UNSIGNED | VOID | VOLATILE | WHILE | ALIGNAS | ALIGNOF | ATOMIC | BOOL | COMPLEX | GENERIC | IMAGINARY | NORETURN | STATICASSERT | THREADLOCAL | ASSERT | ASSUME | CHOOSE | COLLECTIVE | INPUT | INVARIANT | OUTPUT | PROC | SPAWN | WAIT | WHEN )
            // PreprocessorParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set133=(Token)input.LT(1);

            if ( (input.LA(1) >= ALIGNAS && input.LA(1) <= ALIGNOF)||input.LA(1)==ASSERT||input.LA(1)==ASSUME||(input.LA(1) >= ATOMIC && input.LA(1) <= AUTO)||(input.LA(1) >= BOOL && input.LA(1) <= BREAK)||input.LA(1)==CASE||input.LA(1)==CHAR||(input.LA(1) >= CHOOSE && input.LA(1) <= COLLECTIVE)||(input.LA(1) >= COMPLEX && input.LA(1) <= DEFAULT)||input.LA(1)==DO||input.LA(1)==DOUBLE||(input.LA(1) >= ELSE && input.LA(1) <= ENUM)||input.LA(1)==EXTERN||input.LA(1)==FLOAT||input.LA(1)==FOR||(input.LA(1) >= GENERIC && input.LA(1) <= GOTO)||(input.LA(1) >= IF && input.LA(1) <= INT)||input.LA(1)==INVARIANT||input.LA(1)==LONG||input.LA(1)==NORETURN||input.LA(1)==OUTPUT||input.LA(1)==PROC||(input.LA(1) >= REGISTER && input.LA(1) <= RETURN)||(input.LA(1) >= SHORT && input.LA(1) <= SPAWN)||(input.LA(1) >= STATIC && input.LA(1) <= STATICASSERT)||input.LA(1)==STRUCT||(input.LA(1) >= SWITCH && input.LA(1) <= THREADLOCAL)||(input.LA(1) >= TYPEDEF && input.LA(1) <= UNSIGNED)||(input.LA(1) >= VOID && input.LA(1) <= WHILE) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set133)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "c_keyword"


    public static class punctuator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "punctuator"
    // PreprocessorParser.g:201:1: punctuator : ( nonPoundPunctuator | HASH );
    public final PreprocessorParser.punctuator_return punctuator() throws RecognitionException {
        PreprocessorParser.punctuator_return retval = new PreprocessorParser.punctuator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token HASH135=null;
        PreprocessorParser.nonPoundPunctuator_return nonPoundPunctuator134 =null;


        Object HASH135_tree=null;

        try {
            // PreprocessorParser.g:201:12: ( nonPoundPunctuator | HASH )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( ((LA51_0 >= AMPERSAND && LA51_0 <= ARROW)||LA51_0==ASSIGN||LA51_0==AT||(LA51_0 >= BITANDEQ && LA51_0 <= BITXOREQ)||(LA51_0 >= COLON && LA51_0 <= COMMA)||(LA51_0 >= DIV && LA51_0 <= DIVEQ)||LA51_0==DOT||LA51_0==ELLIPSIS||LA51_0==EQUALS||(LA51_0 >= GT && LA51_0 <= GTE)||LA51_0==HASHHASH||LA51_0==LCURLY||(LA51_0 >= LPAREN && LA51_0 <= LTE)||(LA51_0 >= MINUSMINUS && LA51_0 <= NEQ)||LA51_0==NOT||LA51_0==OR||(LA51_0 >= PLUS && LA51_0 <= PLUSPLUS)||(LA51_0 >= QMARK && LA51_0 <= RCURLY)||(LA51_0 >= RPAREN && LA51_0 <= RSQUARE)||(LA51_0 >= SEMI && LA51_0 <= SHIFTRIGHTEQ)||(LA51_0 >= STAR && LA51_0 <= STAREQ)||(LA51_0 >= SUB && LA51_0 <= SUBEQ)||LA51_0==TILDE) ) {
                alt51=1;
            }
            else if ( (LA51_0==HASH) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }
            switch (alt51) {
                case 1 :
                    // PreprocessorParser.g:201:14: nonPoundPunctuator
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_nonPoundPunctuator_in_punctuator1435);
                    nonPoundPunctuator134=nonPoundPunctuator();

                    state._fsp--;

                    adaptor.addChild(root_0, nonPoundPunctuator134.getTree());

                    }
                    break;
                case 2 :
                    // PreprocessorParser.g:201:35: HASH
                    {
                    root_0 = (Object)adaptor.nil();


                    HASH135=(Token)match(input,HASH,FOLLOW_HASH_in_punctuator1439); 
                    HASH135_tree = 
                    (Object)adaptor.create(HASH135)
                    ;
                    adaptor.addChild(root_0, HASH135_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "punctuator"


    public static class pp_number_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pp_number"
    // PreprocessorParser.g:204:1: pp_number : ( INTEGER_CONSTANT | FLOATING_CONSTANT | PP_NUMBER );
    public final PreprocessorParser.pp_number_return pp_number() throws RecognitionException {
        PreprocessorParser.pp_number_return retval = new PreprocessorParser.pp_number_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set136=null;

        Object set136_tree=null;

        try {
            // PreprocessorParser.g:204:11: ( INTEGER_CONSTANT | FLOATING_CONSTANT | PP_NUMBER )
            // PreprocessorParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set136=(Token)input.LT(1);

            if ( input.LA(1)==FLOATING_CONSTANT||input.LA(1)==INTEGER_CONSTANT||input.LA(1)==PP_NUMBER ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set136)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "pp_number"


    public static class nonPoundPunctuator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nonPoundPunctuator"
    // PreprocessorParser.g:207:1: nonPoundPunctuator : ( AMPERSAND | AND | ARROW | ASSIGN | AT | BITANDEQ | BITOR | BITOREQ | BITXOR | BITXOREQ | COLON | COMMA | DIV | DIVEQ | DOT | ELLIPSIS | EQUALS | GT | GTE | HASHHASH | LCURLY | LPAREN | LSQUARE | LT | LTE | MINUSMINUS | MOD | MODEQ | NEQ | NOT | OR | PLUS | PLUSEQ | PLUSPLUS | QMARK | RCURLY | RPAREN | RSQUARE | SEMI | SHIFTLEFT | SHIFTLEFTEQ | SHIFTRIGHT | SHIFTRIGHTEQ | STAR | STAREQ | SUB | SUBEQ | TILDE );
    public final PreprocessorParser.nonPoundPunctuator_return nonPoundPunctuator() throws RecognitionException {
        PreprocessorParser.nonPoundPunctuator_return retval = new PreprocessorParser.nonPoundPunctuator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set137=null;

        Object set137_tree=null;

        try {
            // PreprocessorParser.g:208:3: ( AMPERSAND | AND | ARROW | ASSIGN | AT | BITANDEQ | BITOR | BITOREQ | BITXOR | BITXOREQ | COLON | COMMA | DIV | DIVEQ | DOT | ELLIPSIS | EQUALS | GT | GTE | HASHHASH | LCURLY | LPAREN | LSQUARE | LT | LTE | MINUSMINUS | MOD | MODEQ | NEQ | NOT | OR | PLUS | PLUSEQ | PLUSPLUS | QMARK | RCURLY | RPAREN | RSQUARE | SEMI | SHIFTLEFT | SHIFTLEFTEQ | SHIFTRIGHT | SHIFTRIGHTEQ | STAR | STAREQ | SUB | SUBEQ | TILDE )
            // PreprocessorParser.g:
            {
            root_0 = (Object)adaptor.nil();


            set137=(Token)input.LT(1);

            if ( (input.LA(1) >= AMPERSAND && input.LA(1) <= ARROW)||input.LA(1)==ASSIGN||input.LA(1)==AT||(input.LA(1) >= BITANDEQ && input.LA(1) <= BITXOREQ)||(input.LA(1) >= COLON && input.LA(1) <= COMMA)||(input.LA(1) >= DIV && input.LA(1) <= DIVEQ)||input.LA(1)==DOT||input.LA(1)==ELLIPSIS||input.LA(1)==EQUALS||(input.LA(1) >= GT && input.LA(1) <= GTE)||input.LA(1)==HASHHASH||input.LA(1)==LCURLY||(input.LA(1) >= LPAREN && input.LA(1) <= LTE)||(input.LA(1) >= MINUSMINUS && input.LA(1) <= NEQ)||input.LA(1)==NOT||input.LA(1)==OR||(input.LA(1) >= PLUS && input.LA(1) <= PLUSPLUS)||(input.LA(1) >= QMARK && input.LA(1) <= RCURLY)||(input.LA(1) >= RPAREN && input.LA(1) <= RSQUARE)||(input.LA(1) >= SEMI && input.LA(1) <= SHIFTRIGHTEQ)||(input.LA(1) >= STAR && input.LA(1) <= STAREQ)||(input.LA(1) >= SUB && input.LA(1) <= SUBEQ)||input.LA(1)==TILDE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set137)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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
    // $ANTLR end "nonPoundPunctuator"

    // Delegated rules


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA12_eotS =
        "\4\uffff";
    static final String DFA12_eofS =
        "\4\uffff";
    static final String DFA12_minS =
        "\1\4\1\uffff\1\4\1\uffff";
    static final String DFA12_maxS =
        "\1\u00a1\1\uffff\1\u00a1\1\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\1\1\uffff\1\2";
    static final String DFA12_specialS =
        "\4\uffff}>";
    static final String[] DFA12_transitionS = {
            "\22\1\1\uffff\1\1\1\uffff\6\1\1\2\4\1\1\uffff\5\1\3\uffff\5"+
            "\1\2\uffff\3\1\2\uffff\7\1\7\uffff\10\1\2\uffff\6\1\2\uffff"+
            "\4\1\1\3\2\1\4\uffff\3\1\15\uffff\4\1\1\uffff\1\1\1\uffff\7"+
            "\1\1\uffff\27\1\2\uffff\5\1\1\2",
            "",
            "\22\1\1\uffff\1\1\1\uffff\6\1\1\2\4\1\1\uffff\5\1\3\uffff\5"+
            "\1\2\uffff\3\1\2\uffff\7\1\7\uffff\10\1\2\uffff\6\1\2\uffff"+
            "\4\1\1\3\2\1\4\uffff\3\1\15\uffff\4\1\1\uffff\1\1\1\uffff\7"+
            "\1\1\uffff\27\1\2\uffff\5\1\1\2",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "99:17: ( (t+= wpptoken )* t+= pptoken )?";
        }
    }
    static final String DFA11_eotS =
        "\15\uffff";
    static final String DFA11_eofS =
        "\15\uffff";
    static final String DFA11_minS =
        "\12\4\1\uffff\1\4\1\uffff";
    static final String DFA11_maxS =
        "\12\u00a1\1\uffff\1\u00a1\1\uffff";
    static final String DFA11_acceptS =
        "\12\uffff\1\1\1\uffff\1\2";
    static final String DFA11_specialS =
        "\15\uffff}>";
    static final String[] DFA11_transitionS = {
            "\2\3\3\7\1\3\1\7\1\3\1\7\2\3\5\7\2\3\1\uffff\1\3\1\uffff\1\3"+
            "\1\5\2\3\2\7\1\12\4\3\1\uffff\2\7\1\3\1\7\1\3\3\uffff\1\7\2"+
            "\3\1\7\1\3\2\uffff\1\3\1\4\1\3\2\uffff\2\3\2\7\1\10\1\7\1\1"+
            "\7\uffff\1\2\5\3\1\4\1\3\2\uffff\1\7\1\3\4\7\2\uffff\4\7\1\uffff"+
            "\1\3\1\7\4\uffff\1\7\1\11\1\3\15\uffff\3\7\1\4\1\uffff\1\3\1"+
            "\uffff\2\7\3\3\2\7\1\uffff\5\7\4\3\2\7\2\3\1\6\1\3\2\7\2\3\1"+
            "\7\3\3\2\uffff\5\3\1\12",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            "",
            "\22\12\1\uffff\1\12\1\uffff\6\12\1\13\4\12\1\uffff\5\12\3\uffff"+
            "\5\12\2\uffff\3\12\2\uffff\7\12\7\uffff\10\12\2\uffff\6\12\2"+
            "\uffff\4\12\1\14\2\12\4\uffff\3\12\15\uffff\4\12\1\uffff\1\12"+
            "\1\uffff\7\12\1\uffff\27\12\2\uffff\5\12\1\13",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "()* loopback of 99:19: (t+= wpptoken )*";
        }
    }
    static final String DFA18_eotS =
        "\4\uffff";
    static final String DFA18_eofS =
        "\4\uffff";
    static final String DFA18_minS =
        "\2\36\2\uffff";
    static final String DFA18_maxS =
        "\2\u00a1\2\uffff";
    static final String DFA18_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA18_specialS =
        "\4\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\3\1\1\140\uffff\1\2\40\uffff\1\1",
            "\1\3\1\1\140\uffff\1\2\40\uffff\1\1",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "()* loopback of 108:17: ( ( white )* COMMA ( white )* identifier )*";
        }
    }
 

    public static final BitSet FOLLOW_block_in_file120 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directive_in_block148 = new BitSet(new long[]{0xFE73E3EFFEBFFFF2L,0xFFFFC470FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_textblock_in_block152 = new BitSet(new long[]{0xFE73E3EFFEBFFFF2L,0xFFFFC470FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_macrodef_in_directive165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_macroundef_in_directive171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeline_in_directive177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pragmaline_in_directive183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifblock_in_directive189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifdefblock_in_directive195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifndefblock_in_directive201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_errorline_in_directive207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lineline_in_directive213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nondirectiveline_in_directive219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textline_in_textblock243 = new BitSet(new long[]{0xDE73E3EFFEBFFFF2L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_white_in_textline267 = new BitSet(new long[]{0xDE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_nonPoundPpToken_in_textline271 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_wpptoken_in_textline273 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_lineend_in_textline278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pptoken_in_wpptoken301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_white_in_wpptoken305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_lineend315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDEFINE_in_macrodef324 = new BitSet(new long[]{0x0000000080000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_macrodef326 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_macrodef331 = new BitSet(new long[]{0x0000000080000000L,0x0000000020080000L,0x0000000200000000L});
    public static final BitSet FOLLOW_paramlist_in_macrodef338 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_macrobody_in_macrodef340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lineend_in_macrodef360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_white_in_macrodef380 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_macrobody_in_macrodef382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_white_in_macrobody409 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_pptoken_in_macrobody420 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_wpptoken_in_macrobody425 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_pptoken_in_macrobody430 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_macrobody434 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_macrobody437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lineend_in_macrobody459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_paramlist486 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF01L});
    public static final BitSet FOLLOW_white_in_paramlist488 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF01L});
    public static final BitSet FOLLOW_RPAREN_in_paramlist497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_paramlist510 = new BitSet(new long[]{0x00000000C0000000L,0x0000000000000000L,0x0000000200000001L});
    public static final BitSet FOLLOW_white_in_paramlist513 = new BitSet(new long[]{0x00000000C0000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_COMMA_in_paramlist516 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_white_in_paramlist518 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_paramlist521 = new BitSet(new long[]{0x00000000C0000000L,0x0000000000000000L,0x0000000200000001L});
    public static final BitSet FOLLOW_white_in_paramlist525 = new BitSet(new long[]{0x0000000080000000L,0x0000000000000000L,0x0000000200000001L});
    public static final BitSet FOLLOW_RPAREN_in_paramlist528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUNDEF_in_macroundef558 = new BitSet(new long[]{0x0000000080000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_macroundef560 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_macroundef563 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_macroundef565 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_macroundef568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PINCLUDE_in_includeline590 = new BitSet(new long[]{0x8000000080000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_includeline592 = new BitSet(new long[]{0x8000000080000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_HEADER_NAME_in_includeline595 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_includeline597 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_includeline600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIF_in_ifblock624 = new BitSet(new long[]{0xFE73E3FFFEBFFFF0L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_white_in_ifblock626 = new BitSet(new long[]{0xFE73E3FFFEBFFFF0L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_expr_in_ifblock629 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_lineend_in_ifblock631 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFFFFFC70FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_block_in_ifblock633 = new BitSet(new long[]{0x0000000000000000L,0x0000380000000000L});
    public static final BitSet FOLLOW_elseblock_in_ifblock635 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_endifline_in_ifblock638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ppdExpr_in_expr673 = new BitSet(new long[]{0xFE73E3FFFEBFFFF2L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_ppdExpr_in_expr676 = new BitSet(new long[]{0xFE73E3FFFEBFFFF2L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_white_in_expr680 = new BitSet(new long[]{0xFE73E3FFFEBFFFF2L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_DEFINED_in_definedExpr700 = new BitSet(new long[]{0x0652C28F1AB06A30L,0xE2000040400C5F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_WS_in_definedExpr702 = new BitSet(new long[]{0x0652C28F1AB06A30L,0xE2000040400C5F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_definedExpr711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_definedExpr718 = new BitSet(new long[]{0x0652C28F1AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_WS_in_definedExpr721 = new BitSet(new long[]{0x0652C28F1AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_definedExpr725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000200000001L});
    public static final BitSet FOLLOW_WS_in_definedExpr727 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000200000001L});
    public static final BitSet FOLLOW_RPAREN_in_definedExpr731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pptoken_in_ppdExpr752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_definedExpr_in_ppdExpr756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleelseblock_in_elseblock765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elifblock_in_elseblock769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PELSE_in_simpleelseblock778 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_simpleelseblock780 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_simpleelseblock783 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFFFFC470FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_block_in_simpleelseblock785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PELIF_in_elifblock805 = new BitSet(new long[]{0xFE73E3FFFEBFFFF0L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_white_in_elifblock807 = new BitSet(new long[]{0xFE73E3FFFEBFFFF0L,0xFAF00070DE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_expr_in_elifblock810 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_lineend_in_elifblock812 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFFFFDC70FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_block_in_elifblock814 = new BitSet(new long[]{0x0000000000000002L,0x0000180000000000L});
    public static final BitSet FOLLOW_elseblock_in_elifblock816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIFDEF_in_ifdefblock859 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_white_in_ifdefblock861 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_ifdefblock864 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_ifdefblock866 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_ifdefblock869 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFFFFFC70FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_block_in_ifdefblock874 = new BitSet(new long[]{0x0000000000000000L,0x0000380000000000L});
    public static final BitSet FOLLOW_elseblock_in_ifdefblock876 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_endifline_in_ifdefblock879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIFNDEF_in_ifndefblock911 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_white_in_ifndefblock913 = new BitSet(new long[]{0x0652C28F9AB06A30L,0xE200004040045F80L,0x00000003F3B2CF00L});
    public static final BitSet FOLLOW_identifier_in_ifndefblock916 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_ifndefblock918 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_ifndefblock921 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFFFFFC70FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_block_in_ifndefblock926 = new BitSet(new long[]{0x0000000000000000L,0x0000380000000000L});
    public static final BitSet FOLLOW_elseblock_in_ifndefblock928 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_endifline_in_ifndefblock931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PENDIF_in_endifline962 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_white_in_endifline964 = new BitSet(new long[]{0x0000000080000000L,0x0000000020000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_lineend_in_endifline967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRAGMA_in_pragmaline976 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_wpptoken_in_pragmaline978 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_lineend_in_pragmaline981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERROR_in_errorline1001 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_wpptoken_in_errorline1003 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_lineend_in_errorline1006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLINE_in_lineline1024 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_wpptoken_in_lineline1026 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_lineend_in_lineline1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_nondirectiveline1049 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_wpptoken_in_nondirectiveline1051 = new BitSet(new long[]{0xFE73E3EFFEBFFFF0L,0xFAF00070FE7E7F80L,0x00000003F3FFFFFBL});
    public static final BitSet FOLLOW_lineend_in_nondirectiveline1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEADER_NAME_in_pptoken1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_pptoken1081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pp_number_in_pptoken1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_CONSTANT_in_pptoken1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_pptoken1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_punctuator_in_pptoken1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OTHER_in_pptoken1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEADER_NAME_in_nonPoundPpToken1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_nonPoundPpToken1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pp_number_in_nonPoundPpToken1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_CONSTANT_in_nonPoundPpToken1142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_nonPoundPpToken1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonPoundPunctuator_in_nonPoundPpToken1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OTHER_in_nonPoundPpToken1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifier1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_keyword_in_identifier1182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonPoundPunctuator_in_punctuator1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_punctuator1439 = new BitSet(new long[]{0x0000000000000002L});

}