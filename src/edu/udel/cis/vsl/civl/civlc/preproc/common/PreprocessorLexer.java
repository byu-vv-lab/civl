// $ANTLR 3.4 PreprocessorLexer.g 2012-12-22 16:00:58

package edu.udel.cis.vsl.civl.civlc.preproc.common;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PreprocessorLexer extends Lexer {
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


    public boolean inInclude = false; // are we inside a #include directive?
    public boolean inCondition = false; // are we inside a #if condition?
    public boolean atLineStart = true; // are we at start of line + possible WS?

    @Override
    public void emitErrorMessage(String msg) { // don't try to recover!
        throw new RuntimeException(msg);
    }



    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public PreprocessorLexer() {} 
    public PreprocessorLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PreprocessorLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "PreprocessorLexer.g"; }

    // $ANTLR start "NotLineStart"
    public final void mNotLineStart() throws RecognitionException {
        try {
            // PreprocessorLexer.g:37:14: ()
            // PreprocessorLexer.g:37:16: 
            {
            if ( state.backtracking==0 ) {atLineStart = false;}

            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NotLineStart"

    // $ANTLR start "PDEFINE"
    public final void mPDEFINE() throws RecognitionException {
        try {
            int _type = PDEFINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:39:10: ({...}? => ( WS )* '#' ( WS )* 'define' NotLineStart )
            // PreprocessorLexer.g:39:12: {...}? => ( WS )* '#' ( WS )* 'define' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PDEFINE", "atLineStart");
            }

            // PreprocessorLexer.g:39:28: ( WS )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='\t'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // PreprocessorLexer.g:39:28: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:39:36: ( WS )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\t'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // PreprocessorLexer.g:39:36: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            match("define"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PDEFINE"

    // $ANTLR start "PINCLUDE"
    public final void mPINCLUDE() throws RecognitionException {
        try {
            int _type = PINCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:40:10: ({...}? => ( WS )* '#' ( WS )* 'include' )
            // PreprocessorLexer.g:40:12: {...}? => ( WS )* '#' ( WS )* 'include'
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PINCLUDE", "atLineStart");
            }

            // PreprocessorLexer.g:40:28: ( WS )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\t'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // PreprocessorLexer.g:40:28: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:40:36: ( WS )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\t'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // PreprocessorLexer.g:40:36: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            match("include"); if (state.failed) return ;



            if ( state.backtracking==0 ) {inInclude = true; atLineStart=false;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PINCLUDE"

    // $ANTLR start "PIFDEF"
    public final void mPIFDEF() throws RecognitionException {
        try {
            int _type = PIFDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:43:9: ({...}? => ( WS )* '#' ( WS )* 'ifdef' NotLineStart )
            // PreprocessorLexer.g:43:11: {...}? => ( WS )* '#' ( WS )* 'ifdef' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PIFDEF", "atLineStart");
            }

            // PreprocessorLexer.g:43:27: ( WS )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\t'||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // PreprocessorLexer.g:43:27: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:43:35: ( WS )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\t'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // PreprocessorLexer.g:43:35: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            match("ifdef"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIFDEF"

    // $ANTLR start "PIFNDEF"
    public final void mPIFNDEF() throws RecognitionException {
        try {
            int _type = PIFNDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:44:10: ({...}? => ( WS )* '#' ( WS )* 'ifndef' NotLineStart )
            // PreprocessorLexer.g:44:12: {...}? => ( WS )* '#' ( WS )* 'ifndef' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PIFNDEF", "atLineStart");
            }

            // PreprocessorLexer.g:44:28: ( WS )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\t'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // PreprocessorLexer.g:44:28: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:44:36: ( WS )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\t'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // PreprocessorLexer.g:44:36: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match("ifndef"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIFNDEF"

    // $ANTLR start "PIF"
    public final void mPIF() throws RecognitionException {
        try {
            int _type = PIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:45:6: ({...}? => ( WS )* '#' ( WS )* 'if' )
            // PreprocessorLexer.g:45:8: {...}? => ( WS )* '#' ( WS )* 'if'
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PIF", "atLineStart");
            }

            // PreprocessorLexer.g:45:24: ( WS )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\t'||LA9_0==' ') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // PreprocessorLexer.g:45:24: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:45:32: ( WS )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='\t'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // PreprocessorLexer.g:45:32: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            match("if"); if (state.failed) return ;



            if ( state.backtracking==0 ) {inCondition = true; atLineStart = false;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIF"

    // $ANTLR start "PENDIF"
    public final void mPENDIF() throws RecognitionException {
        try {
            int _type = PENDIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:48:9: ({...}? => ( WS )* '#' ( WS )* 'endif' NotLineStart )
            // PreprocessorLexer.g:48:11: {...}? => ( WS )* '#' ( WS )* 'endif' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PENDIF", "atLineStart");
            }

            // PreprocessorLexer.g:48:27: ( WS )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0=='\t'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // PreprocessorLexer.g:48:27: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:48:35: ( WS )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='\t'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // PreprocessorLexer.g:48:35: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            match("endif"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PENDIF"

    // $ANTLR start "PELIF"
    public final void mPELIF() throws RecognitionException {
        try {
            int _type = PELIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:49:8: ({...}? => ( WS )* '#' ( WS )* 'elif' )
            // PreprocessorLexer.g:49:10: {...}? => ( WS )* '#' ( WS )* 'elif'
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PELIF", "atLineStart");
            }

            // PreprocessorLexer.g:49:26: ( WS )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='\t'||LA13_0==' ') ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // PreprocessorLexer.g:49:26: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:49:34: ( WS )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0=='\t'||LA14_0==' ') ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // PreprocessorLexer.g:49:34: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            match("elif"); if (state.failed) return ;



            if ( state.backtracking==0 ) {inCondition = true; atLineStart = false;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PELIF"

    // $ANTLR start "PELSE"
    public final void mPELSE() throws RecognitionException {
        try {
            int _type = PELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:52:8: ({...}? => ( WS )* '#' ( WS )* 'else' NotLineStart )
            // PreprocessorLexer.g:52:10: {...}? => ( WS )* '#' ( WS )* 'else' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PELSE", "atLineStart");
            }

            // PreprocessorLexer.g:52:26: ( WS )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0=='\t'||LA15_0==' ') ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // PreprocessorLexer.g:52:26: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:52:34: ( WS )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='\t'||LA16_0==' ') ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // PreprocessorLexer.g:52:34: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            match("else"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PELSE"

    // $ANTLR start "PRAGMA"
    public final void mPRAGMA() throws RecognitionException {
        try {
            int _type = PRAGMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:53:9: ({...}? => ( WS )* '#' ( WS )* 'pragma' NotLineStart )
            // PreprocessorLexer.g:53:11: {...}? => ( WS )* '#' ( WS )* 'pragma' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PRAGMA", "atLineStart");
            }

            // PreprocessorLexer.g:53:27: ( WS )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0=='\t'||LA17_0==' ') ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // PreprocessorLexer.g:53:27: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:53:35: ( WS )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='\t'||LA18_0==' ') ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // PreprocessorLexer.g:53:35: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            match("pragma"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PRAGMA"

    // $ANTLR start "PERROR"
    public final void mPERROR() throws RecognitionException {
        try {
            int _type = PERROR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:54:9: ({...}? => ( WS )* '#' ( WS )* 'error' NotLineStart )
            // PreprocessorLexer.g:54:11: {...}? => ( WS )* '#' ( WS )* 'error' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PERROR", "atLineStart");
            }

            // PreprocessorLexer.g:54:27: ( WS )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='\t'||LA19_0==' ') ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // PreprocessorLexer.g:54:27: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:54:35: ( WS )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0=='\t'||LA20_0==' ') ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // PreprocessorLexer.g:54:35: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            match("error"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PERROR"

    // $ANTLR start "PUNDEF"
    public final void mPUNDEF() throws RecognitionException {
        try {
            int _type = PUNDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:55:9: ({...}? => ( WS )* '#' ( WS )* 'undef' NotLineStart )
            // PreprocessorLexer.g:55:11: {...}? => ( WS )* '#' ( WS )* 'undef' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PUNDEF", "atLineStart");
            }

            // PreprocessorLexer.g:55:27: ( WS )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0=='\t'||LA21_0==' ') ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // PreprocessorLexer.g:55:27: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:55:35: ( WS )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\t'||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // PreprocessorLexer.g:55:35: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            match("undef"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PUNDEF"

    // $ANTLR start "PLINE"
    public final void mPLINE() throws RecognitionException {
        try {
            int _type = PLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:56:8: ({...}? => ( WS )* '#' ( WS )* 'line' NotLineStart )
            // PreprocessorLexer.g:56:10: {...}? => ( WS )* '#' ( WS )* 'line' NotLineStart
            {
            if ( !((atLineStart)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "PLINE", "atLineStart");
            }

            // PreprocessorLexer.g:56:26: ( WS )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0=='\t'||LA23_0==' ') ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // PreprocessorLexer.g:56:26: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:56:34: ( WS )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='\t'||LA24_0==' ') ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // PreprocessorLexer.g:56:34: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            match("line"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLINE"

    // $ANTLR start "HASH"
    public final void mHASH() throws RecognitionException {
        try {
            int _type = HASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:58:7: ( ( WS )* '#' ( WS )* )
            // PreprocessorLexer.g:58:9: ( WS )* '#' ( WS )*
            {
            // PreprocessorLexer.g:58:9: ( WS )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='\t'||LA25_0==' ') ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // PreprocessorLexer.g:58:9: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            match('#'); if (state.failed) return ;

            // PreprocessorLexer.g:58:17: ( WS )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='\t'||LA26_0==' ') ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // PreprocessorLexer.g:58:17: WS
            	    {
            	    mWS(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HASH"

    // $ANTLR start "DEFINED"
    public final void mDEFINED() throws RecognitionException {
        try {
            int _type = DEFINED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:59:10: ({...}? => 'defined' NotLineStart )
            // PreprocessorLexer.g:59:12: {...}? => 'defined' NotLineStart
            {
            if ( !((inCondition)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "DEFINED", "inCondition");
            }

            match("defined"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEFINED"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:63:10: ( NewLine )
            // PreprocessorLexer.g:63:12: NewLine
            {
            mNewLine(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "NewLine"
    public final void mNewLine() throws RecognitionException {
        try {
            // PreprocessorLexer.g:66:10: ( ( '\\r' )? '\\n' )
            // PreprocessorLexer.g:66:12: ( '\\r' )? '\\n'
            {
            // PreprocessorLexer.g:66:12: ( '\\r' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0=='\r') ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // PreprocessorLexer.g:66:12: '\\r'
                    {
                    match('\r'); if (state.failed) return ;

                    }
                    break;

            }


            match('\n'); if (state.failed) return ;

            if ( state.backtracking==0 ) {inCondition=false; atLineStart=true;}

            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NewLine"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:69:5: ( ( ' ' | '\\t' )+ )
            // PreprocessorLexer.g:69:7: ( ' ' | '\\t' )+
            {
            // PreprocessorLexer.g:69:7: ( ' ' | '\\t' )+
            int cnt28=0;
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0=='\t'||LA28_0==' ') ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt28 >= 1 ) break loop28;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(28, input);
                        throw eee;
                }
                cnt28++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "AUTO"
    public final void mAUTO() throws RecognitionException {
        try {
            int _type = AUTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:74:7: ( 'auto' )
            // PreprocessorLexer.g:74:9: 'auto'
            {
            match("auto"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AUTO"

    // $ANTLR start "BREAK"
    public final void mBREAK() throws RecognitionException {
        try {
            int _type = BREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:75:8: ( 'break' )
            // PreprocessorLexer.g:75:10: 'break'
            {
            match("break"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BREAK"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:76:7: ( 'case' )
            // PreprocessorLexer.g:76:9: 'case'
            {
            match("case"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:77:7: ( 'char' )
            // PreprocessorLexer.g:77:9: 'char'
            {
            match("char"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "CONST"
    public final void mCONST() throws RecognitionException {
        try {
            int _type = CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:78:8: ( 'const' )
            // PreprocessorLexer.g:78:10: 'const'
            {
            match("const"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONST"

    // $ANTLR start "CONTINUE"
    public final void mCONTINUE() throws RecognitionException {
        try {
            int _type = CONTINUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:79:10: ( 'continue' )
            // PreprocessorLexer.g:79:12: 'continue'
            {
            match("continue"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTINUE"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:80:10: ( 'default' )
            // PreprocessorLexer.g:80:12: 'default'
            {
            match("default"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:81:5: ( 'do' )
            // PreprocessorLexer.g:81:7: 'do'
            {
            match("do"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "DOUBLE"
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:82:9: ( 'double' )
            // PreprocessorLexer.g:82:11: 'double'
            {
            match("double"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOUBLE"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:83:7: ( 'else' )
            // PreprocessorLexer.g:83:9: 'else'
            {
            match("else"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "ENUM"
    public final void mENUM() throws RecognitionException {
        try {
            int _type = ENUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:84:7: ( 'enum' )
            // PreprocessorLexer.g:84:9: 'enum'
            {
            match("enum"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENUM"

    // $ANTLR start "EXTERN"
    public final void mEXTERN() throws RecognitionException {
        try {
            int _type = EXTERN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:85:9: ( 'extern' )
            // PreprocessorLexer.g:85:11: 'extern'
            {
            match("extern"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXTERN"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:86:8: ( 'float' )
            // PreprocessorLexer.g:86:10: 'float'
            {
            match("float"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:87:6: ( 'for' )
            // PreprocessorLexer.g:87:8: 'for'
            {
            match("for"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "GOTO"
    public final void mGOTO() throws RecognitionException {
        try {
            int _type = GOTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:88:7: ( 'goto' )
            // PreprocessorLexer.g:88:9: 'goto'
            {
            match("goto"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GOTO"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:89:5: ( 'if' )
            // PreprocessorLexer.g:89:7: 'if'
            {
            match("if"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "INLINE"
    public final void mINLINE() throws RecognitionException {
        try {
            int _type = INLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:90:9: ( 'inline' )
            // PreprocessorLexer.g:90:11: 'inline'
            {
            match("inline"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INLINE"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:91:6: ( 'int' )
            // PreprocessorLexer.g:91:8: 'int'
            {
            match("int"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "LONG"
    public final void mLONG() throws RecognitionException {
        try {
            int _type = LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:92:7: ( 'long' )
            // PreprocessorLexer.g:92:9: 'long'
            {
            match("long"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LONG"

    // $ANTLR start "REGISTER"
    public final void mREGISTER() throws RecognitionException {
        try {
            int _type = REGISTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:93:10: ( 'register' )
            // PreprocessorLexer.g:93:12: 'register'
            {
            match("register"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REGISTER"

    // $ANTLR start "RESTRICT"
    public final void mRESTRICT() throws RecognitionException {
        try {
            int _type = RESTRICT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:94:10: ( 'restrict' )
            // PreprocessorLexer.g:94:12: 'restrict'
            {
            match("restrict"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RESTRICT"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:95:9: ( 'return' )
            // PreprocessorLexer.g:95:11: 'return'
            {
            match("return"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "SHORT"
    public final void mSHORT() throws RecognitionException {
        try {
            int _type = SHORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:96:8: ( 'short' )
            // PreprocessorLexer.g:96:10: 'short'
            {
            match("short"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHORT"

    // $ANTLR start "SIGNED"
    public final void mSIGNED() throws RecognitionException {
        try {
            int _type = SIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:97:9: ( 'signed' )
            // PreprocessorLexer.g:97:11: 'signed'
            {
            match("signed"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIGNED"

    // $ANTLR start "SIZEOF"
    public final void mSIZEOF() throws RecognitionException {
        try {
            int _type = SIZEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:98:9: ( 'sizeof' )
            // PreprocessorLexer.g:98:11: 'sizeof'
            {
            match("sizeof"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIZEOF"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:99:9: ( 'static' )
            // PreprocessorLexer.g:99:11: 'static'
            {
            match("static"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "STRUCT"
    public final void mSTRUCT() throws RecognitionException {
        try {
            int _type = STRUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:100:9: ( 'struct' )
            // PreprocessorLexer.g:100:11: 'struct'
            {
            match("struct"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRUCT"

    // $ANTLR start "SWITCH"
    public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:101:9: ( 'switch' )
            // PreprocessorLexer.g:101:11: 'switch'
            {
            match("switch"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SWITCH"

    // $ANTLR start "TYPEDEF"
    public final void mTYPEDEF() throws RecognitionException {
        try {
            int _type = TYPEDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:102:10: ( 'typedef' )
            // PreprocessorLexer.g:102:12: 'typedef'
            {
            match("typedef"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPEDEF"

    // $ANTLR start "UNION"
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:103:8: ( 'union' )
            // PreprocessorLexer.g:103:10: 'union'
            {
            match("union"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNION"

    // $ANTLR start "UNSIGNED"
    public final void mUNSIGNED() throws RecognitionException {
        try {
            int _type = UNSIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:104:10: ( 'unsigned' )
            // PreprocessorLexer.g:104:12: 'unsigned'
            {
            match("unsigned"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNSIGNED"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:105:7: ( 'void' )
            // PreprocessorLexer.g:105:9: 'void'
            {
            match("void"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "VOLATILE"
    public final void mVOLATILE() throws RecognitionException {
        try {
            int _type = VOLATILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:106:10: ( 'volatile' )
            // PreprocessorLexer.g:106:12: 'volatile'
            {
            match("volatile"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VOLATILE"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:107:8: ( 'while' )
            // PreprocessorLexer.g:107:10: 'while'
            {
            match("while"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "ALIGNAS"
    public final void mALIGNAS() throws RecognitionException {
        try {
            int _type = ALIGNAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:108:10: ( '_Alignas' )
            // PreprocessorLexer.g:108:12: '_Alignas'
            {
            match("_Alignas"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALIGNAS"

    // $ANTLR start "ALIGNOF"
    public final void mALIGNOF() throws RecognitionException {
        try {
            int _type = ALIGNOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:109:10: ( '_Alignof' )
            // PreprocessorLexer.g:109:12: '_Alignof'
            {
            match("_Alignof"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALIGNOF"

    // $ANTLR start "ATOMIC"
    public final void mATOMIC() throws RecognitionException {
        try {
            int _type = ATOMIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:110:9: ( '_Atomic' )
            // PreprocessorLexer.g:110:11: '_Atomic'
            {
            match("_Atomic"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ATOMIC"

    // $ANTLR start "BOOL"
    public final void mBOOL() throws RecognitionException {
        try {
            int _type = BOOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:111:7: ( '_Bool' )
            // PreprocessorLexer.g:111:9: '_Bool'
            {
            match("_Bool"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOL"

    // $ANTLR start "COMPLEX"
    public final void mCOMPLEX() throws RecognitionException {
        try {
            int _type = COMPLEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:112:10: ( '_Complex' )
            // PreprocessorLexer.g:112:12: '_Complex'
            {
            match("_Complex"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMPLEX"

    // $ANTLR start "GENERIC"
    public final void mGENERIC() throws RecognitionException {
        try {
            int _type = GENERIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:113:10: ( '_Generic' )
            // PreprocessorLexer.g:113:12: '_Generic'
            {
            match("_Generic"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GENERIC"

    // $ANTLR start "IMAGINARY"
    public final void mIMAGINARY() throws RecognitionException {
        try {
            int _type = IMAGINARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:114:11: ( '_Imaginary' )
            // PreprocessorLexer.g:114:13: '_Imaginary'
            {
            match("_Imaginary"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMAGINARY"

    // $ANTLR start "NORETURN"
    public final void mNORETURN() throws RecognitionException {
        try {
            int _type = NORETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:115:10: ( '_Noreturn' )
            // PreprocessorLexer.g:115:12: '_Noreturn'
            {
            match("_Noreturn"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NORETURN"

    // $ANTLR start "STATICASSERT"
    public final void mSTATICASSERT() throws RecognitionException {
        try {
            int _type = STATICASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:116:14: ( '_Static_assert' )
            // PreprocessorLexer.g:116:16: '_Static_assert'
            {
            match("_Static_assert"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STATICASSERT"

    // $ANTLR start "THREADLOCAL"
    public final void mTHREADLOCAL() throws RecognitionException {
        try {
            int _type = THREADLOCAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:117:13: ( '_Thread_local' )
            // PreprocessorLexer.g:117:15: '_Thread_local'
            {
            match("_Thread_local"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "THREADLOCAL"

    // $ANTLR start "ASSERT"
    public final void mASSERT() throws RecognitionException {
        try {
            int _type = ASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:122:9: ( '_assert' )
            // PreprocessorLexer.g:122:11: '_assert'
            {
            match("_assert"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSERT"

    // $ANTLR start "ASSUME"
    public final void mASSUME() throws RecognitionException {
        try {
            int _type = ASSUME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:123:9: ( '_assume' )
            // PreprocessorLexer.g:123:11: '_assume'
            {
            match("_assume"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSUME"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:124:5: ( '@' )
            // PreprocessorLexer.g:124:7: '@'
            {
            match('@'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "CHOOSE"
    public final void mCHOOSE() throws RecognitionException {
        try {
            int _type = CHOOSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:125:9: ( '_choose' )
            // PreprocessorLexer.g:125:11: '_choose'
            {
            match("_choose"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHOOSE"

    // $ANTLR start "COLLECTIVE"
    public final void mCOLLECTIVE() throws RecognitionException {
        try {
            int _type = COLLECTIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:126:12: ( '\\\\collective' )
            // PreprocessorLexer.g:126:14: '\\\\collective'
            {
            match("\\collective"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLLECTIVE"

    // $ANTLR start "INPUT"
    public final void mINPUT() throws RecognitionException {
        try {
            int _type = INPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:127:8: ( '_input' )
            // PreprocessorLexer.g:127:10: '_input'
            {
            match("_input"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INPUT"

    // $ANTLR start "INVARIANT"
    public final void mINVARIANT() throws RecognitionException {
        try {
            int _type = INVARIANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:128:11: ( '_invariant' )
            // PreprocessorLexer.g:128:13: '_invariant'
            {
            match("_invariant"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INVARIANT"

    // $ANTLR start "OUTPUT"
    public final void mOUTPUT() throws RecognitionException {
        try {
            int _type = OUTPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:129:9: ( '_output' )
            // PreprocessorLexer.g:129:11: '_output'
            {
            match("_output"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OUTPUT"

    // $ANTLR start "PROC"
    public final void mPROC() throws RecognitionException {
        try {
            int _type = PROC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:130:7: ( '_proc' )
            // PreprocessorLexer.g:130:9: '_proc'
            {
            match("_proc"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PROC"

    // $ANTLR start "SPAWN"
    public final void mSPAWN() throws RecognitionException {
        try {
            int _type = SPAWN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:131:8: ( '_spawn' )
            // PreprocessorLexer.g:131:10: '_spawn'
            {
            match("_spawn"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SPAWN"

    // $ANTLR start "WAIT"
    public final void mWAIT() throws RecognitionException {
        try {
            int _type = WAIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:132:7: ( '_wait' )
            // PreprocessorLexer.g:132:9: '_wait'
            {
            match("_wait"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WAIT"

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:133:7: ( '_when' )
            // PreprocessorLexer.g:133:9: '_when'
            {
            match("_when"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHEN"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:139:12: ( IdentifierNonDigit ( IdentifierNonDigit | Digit )* NotLineStart )
            // PreprocessorLexer.g:139:14: IdentifierNonDigit ( IdentifierNonDigit | Digit )* NotLineStart
            {
            mIdentifierNonDigit(); if (state.failed) return ;


            // PreprocessorLexer.g:140:4: ( IdentifierNonDigit | Digit )*
            loop29:
            do {
                int alt29=3;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0 >= 'A' && LA29_0 <= 'Z')||LA29_0=='\\'||LA29_0=='_'||(LA29_0 >= 'a' && LA29_0 <= 'z')) ) {
                    alt29=1;
                }
                else if ( ((LA29_0 >= '0' && LA29_0 <= '9')) ) {
                    alt29=2;
                }


                switch (alt29) {
            	case 1 :
            	    // PreprocessorLexer.g:140:5: IdentifierNonDigit
            	    {
            	    mIdentifierNonDigit(); if (state.failed) return ;


            	    }
            	    break;
            	case 2 :
            	    // PreprocessorLexer.g:140:26: Digit
            	    {
            	    mDigit(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "IdentifierNonDigit"
    public final void mIdentifierNonDigit() throws RecognitionException {
        try {
            // PreprocessorLexer.g:145:3: ( NonDigit | UniversalCharacterName )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0 >= 'A' && LA30_0 <= 'Z')||LA30_0=='_'||(LA30_0 >= 'a' && LA30_0 <= 'z')) ) {
                alt30=1;
            }
            else if ( (LA30_0=='\\') ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }
            switch (alt30) {
                case 1 :
                    // PreprocessorLexer.g:145:5: NonDigit
                    {
                    mNonDigit(); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:145:16: UniversalCharacterName
                    {
                    mUniversalCharacterName(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IdentifierNonDigit"

    // $ANTLR start "Zero"
    public final void mZero() throws RecognitionException {
        try {
            // PreprocessorLexer.g:148:7: ( '0' )
            // PreprocessorLexer.g:148:9: '0'
            {
            match('0'); if (state.failed) return ;

            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Zero"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // PreprocessorLexer.g:151:8: ( Zero | NonZeroDigit )
            // PreprocessorLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "NonZeroDigit"
    public final void mNonZeroDigit() throws RecognitionException {
        try {
            // PreprocessorLexer.g:154:14: ( '1' .. '9' )
            // PreprocessorLexer.g:
            {
            if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NonZeroDigit"

    // $ANTLR start "NonDigit"
    public final void mNonDigit() throws RecognitionException {
        try {
            // PreprocessorLexer.g:157:10: ( 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // PreprocessorLexer.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NonDigit"

    // $ANTLR start "UniversalCharacterName"
    public final void mUniversalCharacterName() throws RecognitionException {
        try {
            // PreprocessorLexer.g:161:3: ( '\\\\' 'u' HexQuad | '\\\\' 'U' HexQuad HexQuad )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0=='\\') ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1=='u') ) {
                    alt31=1;
                }
                else if ( (LA31_1=='U') ) {
                    alt31=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // PreprocessorLexer.g:161:5: '\\\\' 'u' HexQuad
                    {
                    match('\\'); if (state.failed) return ;

                    match('u'); if (state.failed) return ;

                    mHexQuad(); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:162:5: '\\\\' 'U' HexQuad HexQuad
                    {
                    match('\\'); if (state.failed) return ;

                    match('U'); if (state.failed) return ;

                    mHexQuad(); if (state.failed) return ;


                    mHexQuad(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UniversalCharacterName"

    // $ANTLR start "HexQuad"
    public final void mHexQuad() throws RecognitionException {
        try {
            // PreprocessorLexer.g:166:10: ( HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit )
            // PreprocessorLexer.g:166:12: HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
            {
            mHexadecimalDigit(); if (state.failed) return ;


            mHexadecimalDigit(); if (state.failed) return ;


            mHexadecimalDigit(); if (state.failed) return ;


            mHexadecimalDigit(); if (state.failed) return ;


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexQuad"

    // $ANTLR start "HexadecimalDigit"
    public final void mHexadecimalDigit() throws RecognitionException {
        try {
            // PreprocessorLexer.g:170:3: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            // PreprocessorLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexadecimalDigit"

    // $ANTLR start "INTEGER_CONSTANT"
    public final void mINTEGER_CONSTANT() throws RecognitionException {
        try {
            int _type = INTEGER_CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:175:3: ( DecimalConstant ( IntegerSuffix )? | OctalConstant ( IntegerSuffix )? | HexadecimalConstant ( IntegerSuffix )? )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0 >= '1' && LA35_0 <= '9')) ) {
                alt35=1;
            }
            else if ( (LA35_0=='0') ) {
                int LA35_2 = input.LA(2);

                if ( (LA35_2=='X'||LA35_2=='x') ) {
                    alt35=3;
                }
                else {
                    alt35=2;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // PreprocessorLexer.g:175:5: DecimalConstant ( IntegerSuffix )?
                    {
                    mDecimalConstant(); if (state.failed) return ;


                    // PreprocessorLexer.g:175:21: ( IntegerSuffix )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0=='L'||LA32_0=='U'||LA32_0=='l'||LA32_0=='u') ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // PreprocessorLexer.g:175:21: IntegerSuffix
                            {
                            mIntegerSuffix(); if (state.failed) return ;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:176:5: OctalConstant ( IntegerSuffix )?
                    {
                    mOctalConstant(); if (state.failed) return ;


                    // PreprocessorLexer.g:176:19: ( IntegerSuffix )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0=='L'||LA33_0=='U'||LA33_0=='l'||LA33_0=='u') ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // PreprocessorLexer.g:176:19: IntegerSuffix
                            {
                            mIntegerSuffix(); if (state.failed) return ;


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // PreprocessorLexer.g:177:5: HexadecimalConstant ( IntegerSuffix )?
                    {
                    mHexadecimalConstant(); if (state.failed) return ;


                    // PreprocessorLexer.g:177:25: ( IntegerSuffix )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0=='L'||LA34_0=='U'||LA34_0=='l'||LA34_0=='u') ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // PreprocessorLexer.g:177:25: IntegerSuffix
                            {
                            mIntegerSuffix(); if (state.failed) return ;


                            }
                            break;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER_CONSTANT"

    // $ANTLR start "DecimalConstant"
    public final void mDecimalConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:181:17: ( NonZeroDigit ( Digit )* )
            // PreprocessorLexer.g:181:19: NonZeroDigit ( Digit )*
            {
            mNonZeroDigit(); if (state.failed) return ;


            // PreprocessorLexer.g:181:32: ( Digit )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0 >= '0' && LA36_0 <= '9')) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DecimalConstant"

    // $ANTLR start "IntegerSuffix"
    public final void mIntegerSuffix() throws RecognitionException {
        try {
            // PreprocessorLexer.g:185:15: ( UnsignedSuffix ( LongSuffix )? | UnsignedSuffix LongLongSuffix | LongSuffix ( UnsignedSuffix )? | LongLongSuffix ( UnsignedSuffix )? )
            int alt40=4;
            switch ( input.LA(1) ) {
            case 'U':
            case 'u':
                {
                switch ( input.LA(2) ) {
                case 'l':
                    {
                    int LA40_5 = input.LA(3);

                    if ( (LA40_5=='l') ) {
                        alt40=2;
                    }
                    else {
                        alt40=1;
                    }
                    }
                    break;
                case 'L':
                    {
                    int LA40_6 = input.LA(3);

                    if ( (LA40_6=='L') ) {
                        alt40=2;
                    }
                    else {
                        alt40=1;
                    }
                    }
                    break;
                default:
                    alt40=1;
                }

                }
                break;
            case 'l':
                {
                int LA40_2 = input.LA(2);

                if ( (LA40_2=='l') ) {
                    alt40=4;
                }
                else {
                    alt40=3;
                }
                }
                break;
            case 'L':
                {
                int LA40_3 = input.LA(2);

                if ( (LA40_3=='L') ) {
                    alt40=4;
                }
                else {
                    alt40=3;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }

            switch (alt40) {
                case 1 :
                    // PreprocessorLexer.g:185:17: UnsignedSuffix ( LongSuffix )?
                    {
                    mUnsignedSuffix(); if (state.failed) return ;


                    // PreprocessorLexer.g:185:32: ( LongSuffix )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0=='L'||LA37_0=='l') ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:186:5: UnsignedSuffix LongLongSuffix
                    {
                    mUnsignedSuffix(); if (state.failed) return ;


                    mLongLongSuffix(); if (state.failed) return ;


                    }
                    break;
                case 3 :
                    // PreprocessorLexer.g:187:5: LongSuffix ( UnsignedSuffix )?
                    {
                    mLongSuffix(); if (state.failed) return ;


                    // PreprocessorLexer.g:187:16: ( UnsignedSuffix )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0=='U'||LA38_0=='u') ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // PreprocessorLexer.g:188:5: LongLongSuffix ( UnsignedSuffix )?
                    {
                    mLongLongSuffix(); if (state.failed) return ;


                    // PreprocessorLexer.g:188:20: ( UnsignedSuffix )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0=='U'||LA39_0=='u') ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IntegerSuffix"

    // $ANTLR start "UnsignedSuffix"
    public final void mUnsignedSuffix() throws RecognitionException {
        try {
            // PreprocessorLexer.g:192:16: ( 'u' | 'U' )
            // PreprocessorLexer.g:
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UnsignedSuffix"

    // $ANTLR start "LongSuffix"
    public final void mLongSuffix() throws RecognitionException {
        try {
            // PreprocessorLexer.g:195:12: ( 'l' | 'L' )
            // PreprocessorLexer.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LongSuffix"

    // $ANTLR start "LongLongSuffix"
    public final void mLongLongSuffix() throws RecognitionException {
        try {
            // PreprocessorLexer.g:198:16: ( 'll' | 'LL' )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0=='l') ) {
                alt41=1;
            }
            else if ( (LA41_0=='L') ) {
                alt41=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // PreprocessorLexer.g:198:18: 'll'
                    {
                    match("ll"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:198:25: 'LL'
                    {
                    match("LL"); if (state.failed) return ;



                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LongLongSuffix"

    // $ANTLR start "OctalConstant"
    public final void mOctalConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:201:15: ( Zero ( OctalDigit )* ( IntegerSuffix )? NotLineStart )
            // PreprocessorLexer.g:201:17: Zero ( OctalDigit )* ( IntegerSuffix )? NotLineStart
            {
            mZero(); if (state.failed) return ;


            // PreprocessorLexer.g:201:22: ( OctalDigit )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0 >= '0' && LA42_0 <= '7')) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            // PreprocessorLexer.g:201:34: ( IntegerSuffix )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0=='L'||LA43_0=='U'||LA43_0=='l'||LA43_0=='u') ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // PreprocessorLexer.g:201:34: IntegerSuffix
                    {
                    mIntegerSuffix(); if (state.failed) return ;


                    }
                    break;

            }


            mNotLineStart(); if (state.failed) return ;


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalConstant"

    // $ANTLR start "HexadecimalConstant"
    public final void mHexadecimalConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:205:3: ( HexPrefix ( HexadecimalDigit )+ ( IntegerSuffix )? NotLineStart )
            // PreprocessorLexer.g:205:5: HexPrefix ( HexadecimalDigit )+ ( IntegerSuffix )? NotLineStart
            {
            mHexPrefix(); if (state.failed) return ;


            // PreprocessorLexer.g:205:15: ( HexadecimalDigit )+
            int cnt44=0;
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0 >= '0' && LA44_0 <= '9')||(LA44_0 >= 'A' && LA44_0 <= 'F')||(LA44_0 >= 'a' && LA44_0 <= 'f')) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt44 >= 1 ) break loop44;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(44, input);
                        throw eee;
                }
                cnt44++;
            } while (true);


            // PreprocessorLexer.g:205:33: ( IntegerSuffix )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0=='L'||LA45_0=='U'||LA45_0=='l'||LA45_0=='u') ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // PreprocessorLexer.g:205:33: IntegerSuffix
                    {
                    mIntegerSuffix(); if (state.failed) return ;


                    }
                    break;

            }


            mNotLineStart(); if (state.failed) return ;


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexadecimalConstant"

    // $ANTLR start "HexPrefix"
    public final void mHexPrefix() throws RecognitionException {
        try {
            // PreprocessorLexer.g:208:11: ( Zero ( 'x' | 'X' ) )
            // PreprocessorLexer.g:208:13: Zero ( 'x' | 'X' )
            {
            mZero(); if (state.failed) return ;


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexPrefix"

    // $ANTLR start "FLOATING_CONSTANT"
    public final void mFLOATING_CONSTANT() throws RecognitionException {
        try {
            int _type = FLOATING_CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:213:3: ( DecimalFloatingConstant | HexadecimalFloatingConstant )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0=='0') ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1=='.'||(LA46_1 >= '0' && LA46_1 <= '9')||LA46_1=='E'||LA46_1=='e') ) {
                    alt46=1;
                }
                else if ( (LA46_1=='X'||LA46_1=='x') ) {
                    alt46=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA46_0=='.'||(LA46_0 >= '1' && LA46_0 <= '9')) ) {
                alt46=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // PreprocessorLexer.g:213:5: DecimalFloatingConstant
                    {
                    mDecimalFloatingConstant(); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:214:5: HexadecimalFloatingConstant
                    {
                    mHexadecimalFloatingConstant(); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOATING_CONSTANT"

    // $ANTLR start "DecimalFloatingConstant"
    public final void mDecimalFloatingConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:219:3: ( FractionalConstant ( ExponentPart )? ( FloatingSuffix )? | ( Digit )+ ExponentPart ( FloatingSuffix )? )
            int alt51=2;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // PreprocessorLexer.g:219:5: FractionalConstant ( ExponentPart )? ( FloatingSuffix )?
                    {
                    mFractionalConstant(); if (state.failed) return ;


                    // PreprocessorLexer.g:219:24: ( ExponentPart )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0=='E'||LA47_0=='e') ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // PreprocessorLexer.g:219:24: ExponentPart
                            {
                            mExponentPart(); if (state.failed) return ;


                            }
                            break;

                    }


                    // PreprocessorLexer.g:219:38: ( FloatingSuffix )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0=='F'||LA48_0=='L'||LA48_0=='f'||LA48_0=='l') ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:220:5: ( Digit )+ ExponentPart ( FloatingSuffix )?
                    {
                    // PreprocessorLexer.g:220:5: ( Digit )+
                    int cnt49=0;
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( ((LA49_0 >= '0' && LA49_0 <= '9')) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt49 >= 1 ) break loop49;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(49, input);
                                throw eee;
                        }
                        cnt49++;
                    } while (true);


                    mExponentPart(); if (state.failed) return ;


                    // PreprocessorLexer.g:220:25: ( FloatingSuffix )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0=='F'||LA50_0=='L'||LA50_0=='f'||LA50_0=='l') ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DecimalFloatingConstant"

    // $ANTLR start "FractionalConstant"
    public final void mFractionalConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:225:3: ( ( Digit )* '.' ( Digit )+ | ( Digit )+ '.' )
            int alt55=2;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // PreprocessorLexer.g:225:5: ( Digit )* '.' ( Digit )+
                    {
                    // PreprocessorLexer.g:225:5: ( Digit )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( ((LA52_0 >= '0' && LA52_0 <= '9')) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    match('.'); if (state.failed) return ;

                    // PreprocessorLexer.g:225:16: ( Digit )+
                    int cnt53=0;
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( ((LA53_0 >= '0' && LA53_0 <= '9')) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt53 >= 1 ) break loop53;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(53, input);
                                throw eee;
                        }
                        cnt53++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:226:5: ( Digit )+ '.'
                    {
                    // PreprocessorLexer.g:226:5: ( Digit )+
                    int cnt54=0;
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( ((LA54_0 >= '0' && LA54_0 <= '9')) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt54 >= 1 ) break loop54;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(54, input);
                                throw eee;
                        }
                        cnt54++;
                    } while (true);


                    match('.'); if (state.failed) return ;

                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FractionalConstant"

    // $ANTLR start "ExponentPart"
    public final void mExponentPart() throws RecognitionException {
        try {
            // PreprocessorLexer.g:230:14: ( ( 'e' | 'E' ) ( '+' | '-' )? ( Digit )+ )
            // PreprocessorLexer.g:230:16: ( 'e' | 'E' ) ( '+' | '-' )? ( Digit )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // PreprocessorLexer.g:230:28: ( '+' | '-' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0=='+'||LA56_0=='-') ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // PreprocessorLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // PreprocessorLexer.g:230:41: ( Digit )+
            int cnt57=0;
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( ((LA57_0 >= '0' && LA57_0 <= '9')) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt57 >= 1 ) break loop57;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(57, input);
                        throw eee;
                }
                cnt57++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ExponentPart"

    // $ANTLR start "FloatingSuffix"
    public final void mFloatingSuffix() throws RecognitionException {
        try {
            // PreprocessorLexer.g:233:16: ( 'f' | 'l' | 'F' | 'L' )
            // PreprocessorLexer.g:
            {
            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FloatingSuffix"

    // $ANTLR start "HexadecimalFloatingConstant"
    public final void mHexadecimalFloatingConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:237:3: ( HexPrefix HexFractionalConstant BinaryExponentPart ( FloatingSuffix )? | HexPrefix ( HexadecimalDigit )+ BinaryExponentPart ( FloatingSuffix )? )
            int alt61=2;
            alt61 = dfa61.predict(input);
            switch (alt61) {
                case 1 :
                    // PreprocessorLexer.g:237:5: HexPrefix HexFractionalConstant BinaryExponentPart ( FloatingSuffix )?
                    {
                    mHexPrefix(); if (state.failed) return ;


                    mHexFractionalConstant(); if (state.failed) return ;


                    mBinaryExponentPart(); if (state.failed) return ;


                    // PreprocessorLexer.g:238:4: ( FloatingSuffix )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0=='F'||LA58_0=='L'||LA58_0=='f'||LA58_0=='l') ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:239:5: HexPrefix ( HexadecimalDigit )+ BinaryExponentPart ( FloatingSuffix )?
                    {
                    mHexPrefix(); if (state.failed) return ;


                    // PreprocessorLexer.g:239:15: ( HexadecimalDigit )+
                    int cnt59=0;
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( ((LA59_0 >= '0' && LA59_0 <= '9')||(LA59_0 >= 'A' && LA59_0 <= 'F')||(LA59_0 >= 'a' && LA59_0 <= 'f')) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt59 >= 1 ) break loop59;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(59, input);
                                throw eee;
                        }
                        cnt59++;
                    } while (true);


                    mBinaryExponentPart(); if (state.failed) return ;


                    // PreprocessorLexer.g:240:4: ( FloatingSuffix )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0=='F'||LA60_0=='L'||LA60_0=='f'||LA60_0=='l') ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexadecimalFloatingConstant"

    // $ANTLR start "HexFractionalConstant"
    public final void mHexFractionalConstant() throws RecognitionException {
        try {
            // PreprocessorLexer.g:245:3: ( ( HexadecimalDigit )* '.' ( Digit )+ | ( HexadecimalDigit )+ '.' )
            int alt65=2;
            alt65 = dfa65.predict(input);
            switch (alt65) {
                case 1 :
                    // PreprocessorLexer.g:245:5: ( HexadecimalDigit )* '.' ( Digit )+
                    {
                    // PreprocessorLexer.g:245:5: ( HexadecimalDigit )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( ((LA62_0 >= '0' && LA62_0 <= '9')||(LA62_0 >= 'A' && LA62_0 <= 'F')||(LA62_0 >= 'a' && LA62_0 <= 'f')) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);


                    match('.'); if (state.failed) return ;

                    // PreprocessorLexer.g:245:27: ( Digit )+
                    int cnt63=0;
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( ((LA63_0 >= '0' && LA63_0 <= '9')) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt63 >= 1 ) break loop63;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(63, input);
                                throw eee;
                        }
                        cnt63++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:246:5: ( HexadecimalDigit )+ '.'
                    {
                    // PreprocessorLexer.g:246:5: ( HexadecimalDigit )+
                    int cnt64=0;
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( ((LA64_0 >= '0' && LA64_0 <= '9')||(LA64_0 >= 'A' && LA64_0 <= 'F')||(LA64_0 >= 'a' && LA64_0 <= 'f')) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt64 >= 1 ) break loop64;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(64, input);
                                throw eee;
                        }
                        cnt64++;
                    } while (true);


                    match('.'); if (state.failed) return ;

                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexFractionalConstant"

    // $ANTLR start "BinaryExponentPart"
    public final void mBinaryExponentPart() throws RecognitionException {
        try {
            // PreprocessorLexer.g:251:3: ( ( 'p' | 'P' ) ( '+' | '-' )? ( Digit )+ )
            // PreprocessorLexer.g:251:5: ( 'p' | 'P' ) ( '+' | '-' )? ( Digit )+
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // PreprocessorLexer.g:251:17: ( '+' | '-' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0=='+'||LA66_0=='-') ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // PreprocessorLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // PreprocessorLexer.g:251:30: ( Digit )+
            int cnt67=0;
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( ((LA67_0 >= '0' && LA67_0 <= '9')) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt67 >= 1 ) break loop67;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(67, input);
                        throw eee;
                }
                cnt67++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BinaryExponentPart"

    // $ANTLR start "PP_NUMBER"
    public final void mPP_NUMBER() throws RecognitionException {
        try {
            int _type = PP_NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:259:11: ( ( '.' )? Digit ( '.' | IdentifierNonDigit | Digit | ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' ) )* NotLineStart )
            // PreprocessorLexer.g:259:13: ( '.' )? Digit ( '.' | IdentifierNonDigit | Digit | ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' ) )* NotLineStart
            {
            // PreprocessorLexer.g:259:13: ( '.' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0=='.') ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // PreprocessorLexer.g:259:13: '.'
                    {
                    match('.'); if (state.failed) return ;

                    }
                    break;

            }


            mDigit(); if (state.failed) return ;


            // PreprocessorLexer.g:260:4: ( '.' | IdentifierNonDigit | Digit | ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' ) )*
            loop69:
            do {
                int alt69=5;
                switch ( input.LA(1) ) {
                case '.':
                    {
                    alt69=1;
                    }
                    break;
                case 'E':
                case 'P':
                case 'e':
                case 'p':
                    {
                    int LA69_3 = input.LA(2);

                    if ( (LA69_3=='+'||LA69_3=='-') ) {
                        alt69=4;
                    }

                    else {
                        alt69=2;
                    }


                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '\\':
                case '_':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt69=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt69=3;
                    }
                    break;

                }

                switch (alt69) {
            	case 1 :
            	    // PreprocessorLexer.g:260:6: '.'
            	    {
            	    match('.'); if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // PreprocessorLexer.g:261:6: IdentifierNonDigit
            	    {
            	    mIdentifierNonDigit(); if (state.failed) return ;


            	    }
            	    break;
            	case 3 :
            	    // PreprocessorLexer.g:262:6: Digit
            	    {
            	    mDigit(); if (state.failed) return ;


            	    }
            	    break;
            	case 4 :
            	    // PreprocessorLexer.g:263:6: ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' )
            	    {
            	    if ( input.LA(1)=='E'||input.LA(1)=='P'||input.LA(1)=='e'||input.LA(1)=='p' ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);


            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PP_NUMBER"

    // $ANTLR start "CHARACTER_CONSTANT"
    public final void mCHARACTER_CONSTANT() throws RecognitionException {
        try {
            int _type = CHARACTER_CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:272:3: ( ( 'L' | 'U' | 'u' )? '\\'' ( CChar )+ '\\'' NotLineStart )
            // PreprocessorLexer.g:272:5: ( 'L' | 'U' | 'u' )? '\\'' ( CChar )+ '\\'' NotLineStart
            {
            // PreprocessorLexer.g:272:5: ( 'L' | 'U' | 'u' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0=='L'||LA70_0=='U'||LA70_0=='u') ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // PreprocessorLexer.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='U'||input.LA(1)=='u' ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            match('\''); if (state.failed) return ;

            // PreprocessorLexer.g:272:29: ( CChar )+
            int cnt71=0;
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( ((LA71_0 >= '\u0000' && LA71_0 <= '\t')||(LA71_0 >= '\u000B' && LA71_0 <= '&')||(LA71_0 >= '(' && LA71_0 <= '\uFFFF')) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // PreprocessorLexer.g:272:29: CChar
            	    {
            	    mCChar(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    if ( cnt71 >= 1 ) break loop71;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(71, input);
                        throw eee;
                }
                cnt71++;
            } while (true);


            match('\''); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHARACTER_CONSTANT"

    // $ANTLR start "CChar"
    public final void mCChar() throws RecognitionException {
        try {
            // PreprocessorLexer.g:275:8: (~ ( '\\'' | '\\\\' | '\\n' ) | EscapeSequence )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( ((LA72_0 >= '\u0000' && LA72_0 <= '\t')||(LA72_0 >= '\u000B' && LA72_0 <= '&')||(LA72_0 >= '(' && LA72_0 <= '[')||(LA72_0 >= ']' && LA72_0 <= '\uFFFF')) ) {
                alt72=1;
            }
            else if ( (LA72_0=='\\') ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;

            }
            switch (alt72) {
                case 1 :
                    // PreprocessorLexer.g:275:10: ~ ( '\\'' | '\\\\' | '\\n' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:275:34: EscapeSequence
                    {
                    mEscapeSequence(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CChar"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // PreprocessorLexer.g:278:16: ( '\\\\' ( '\\'' | '\"' | '\\?' | '\\\\' | 'a' | 'b' | 'f' | 'n' | 'r' | 't' | 'v' ) | OctalEscape | HexEscape )
            int alt73=3;
            int LA73_0 = input.LA(1);

            if ( (LA73_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '?':
                case '\\':
                case 'a':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                case 'v':
                    {
                    alt73=1;
                    }
                    break;
                case 'x':
                    {
                    alt73=3;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt73=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 73, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;

            }
            switch (alt73) {
                case 1 :
                    // PreprocessorLexer.g:278:18: '\\\\' ( '\\'' | '\"' | '\\?' | '\\\\' | 'a' | 'b' | 'f' | 'n' | 'r' | 't' | 'v' )
                    {
                    match('\\'); if (state.failed) return ;

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='?'||input.LA(1)=='\\'||(input.LA(1) >= 'a' && input.LA(1) <= 'b')||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t'||input.LA(1)=='v' ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:281:5: OctalEscape
                    {
                    mOctalEscape(); if (state.failed) return ;


                    }
                    break;
                case 3 :
                    // PreprocessorLexer.g:282:5: HexEscape
                    {
                    mHexEscape(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // PreprocessorLexer.g:285:13: ( '\\\\' OctalDigit ( OctalDigit ( OctalDigit )? )? )
            // PreprocessorLexer.g:285:15: '\\\\' OctalDigit ( OctalDigit ( OctalDigit )? )?
            {
            match('\\'); if (state.failed) return ;

            mOctalDigit(); if (state.failed) return ;


            // PreprocessorLexer.g:285:31: ( OctalDigit ( OctalDigit )? )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( ((LA75_0 >= '0' && LA75_0 <= '7')) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // PreprocessorLexer.g:285:32: OctalDigit ( OctalDigit )?
                    {
                    mOctalDigit(); if (state.failed) return ;


                    // PreprocessorLexer.g:285:43: ( OctalDigit )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( ((LA74_0 >= '0' && LA74_0 <= '7')) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // PreprocessorLexer.g:
                            {
                            if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                                input.consume();
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "OctalDigit"
    public final void mOctalDigit() throws RecognitionException {
        try {
            // PreprocessorLexer.g:288:12: ( '0' .. '7' )
            // PreprocessorLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalDigit"

    // $ANTLR start "HexEscape"
    public final void mHexEscape() throws RecognitionException {
        try {
            // PreprocessorLexer.g:291:11: ( '\\\\' 'x' ( HexadecimalDigit )+ )
            // PreprocessorLexer.g:291:13: '\\\\' 'x' ( HexadecimalDigit )+
            {
            match('\\'); if (state.failed) return ;

            match('x'); if (state.failed) return ;

            // PreprocessorLexer.g:291:22: ( HexadecimalDigit )+
            int cnt76=0;
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( ((LA76_0 >= '0' && LA76_0 <= '9')||(LA76_0 >= 'A' && LA76_0 <= 'F')||(LA76_0 >= 'a' && LA76_0 <= 'f')) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // PreprocessorLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt76 >= 1 ) break loop76;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(76, input);
                        throw eee;
                }
                cnt76++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexEscape"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:297:17: ( ( 'u8' | 'u' | 'U' | 'L' )? '\"' ( SChar )* '\"' NotLineStart )
            // PreprocessorLexer.g:297:19: ( 'u8' | 'u' | 'U' | 'L' )? '\"' ( SChar )* '\"' NotLineStart
            {
            // PreprocessorLexer.g:297:19: ( 'u8' | 'u' | 'U' | 'L' )?
            int alt77=5;
            switch ( input.LA(1) ) {
                case 'u':
                    {
                    int LA77_1 = input.LA(2);

                    if ( (LA77_1=='8') ) {
                        alt77=1;
                    }
                    else if ( (LA77_1=='\"') ) {
                        alt77=2;
                    }
                    }
                    break;
                case 'U':
                    {
                    alt77=3;
                    }
                    break;
                case 'L':
                    {
                    alt77=4;
                    }
                    break;
            }

            switch (alt77) {
                case 1 :
                    // PreprocessorLexer.g:297:20: 'u8'
                    {
                    match("u8"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:297:27: 'u'
                    {
                    match('u'); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // PreprocessorLexer.g:297:33: 'U'
                    {
                    match('U'); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // PreprocessorLexer.g:297:39: 'L'
                    {
                    match('L'); if (state.failed) return ;

                    }
                    break;

            }


            match('\"'); if (state.failed) return ;

            // PreprocessorLexer.g:297:49: ( SChar )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( ((LA78_0 >= '\u0000' && LA78_0 <= '\t')||(LA78_0 >= '\u000B' && LA78_0 <= '!')||(LA78_0 >= '#' && LA78_0 <= '\uFFFF')) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // PreprocessorLexer.g:297:49: SChar
            	    {
            	    mSChar(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);


            match('\"'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "SChar"
    public final void mSChar() throws RecognitionException {
        try {
            // PreprocessorLexer.g:302:8: (~ ( '\"' | '\\\\' | '\\n' ) | EscapeSequence )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( ((LA79_0 >= '\u0000' && LA79_0 <= '\t')||(LA79_0 >= '\u000B' && LA79_0 <= '!')||(LA79_0 >= '#' && LA79_0 <= '[')||(LA79_0 >= ']' && LA79_0 <= '\uFFFF')) ) {
                alt79=1;
            }
            else if ( (LA79_0=='\\') ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;

            }
            switch (alt79) {
                case 1 :
                    // PreprocessorLexer.g:302:10: ~ ( '\"' | '\\\\' | '\\n' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:302:33: EscapeSequence
                    {
                    mEscapeSequence(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SChar"

    // $ANTLR start "ELLIPSIS"
    public final void mELLIPSIS() throws RecognitionException {
        try {
            // PreprocessorLexer.g:307:19: ()
            // PreprocessorLexer.g:307:20: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELLIPSIS"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:309:6: ( '.' ( ( '..' )=> '..' |) NotLineStart )
            // PreprocessorLexer.g:309:8: '.' ( ( '..' )=> '..' |) NotLineStart
            {
            match('.'); if (state.failed) return ;

            // PreprocessorLexer.g:310:4: ( ( '..' )=> '..' |)
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0=='.') && (synpred1_PreprocessorLexer())) {
                alt80=1;
            }
            else {
                alt80=2;
            }
            switch (alt80) {
                case 1 :
                    // PreprocessorLexer.g:310:8: ( '..' )=> '..'
                    {
                    match(".."); if (state.failed) return ;



                    if ( state.backtracking==0 ) { _type  = ELLIPSIS; }

                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:312:4: 
                    {
                    }
                    break;

            }


            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:316:11: ( '&' NotLineStart )
            // PreprocessorLexer.g:316:13: '&' NotLineStart
            {
            match('&'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:317:6: ( '&&' NotLineStart )
            // PreprocessorLexer.g:317:8: '&&' NotLineStart
            {
            match("&&"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "ARROW"
    public final void mARROW() throws RecognitionException {
        try {
            int _type = ARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:318:8: ( '->' NotLineStart )
            // PreprocessorLexer.g:318:10: '->' NotLineStart
            {
            match("->"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ARROW"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:319:9: ( '=' NotLineStart )
            // PreprocessorLexer.g:319:11: '=' NotLineStart
            {
            match('='); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "BITANDEQ"
    public final void mBITANDEQ() throws RecognitionException {
        try {
            int _type = BITANDEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:320:10: ( '&=' NotLineStart )
            // PreprocessorLexer.g:320:12: '&=' NotLineStart
            {
            match("&="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITANDEQ"

    // $ANTLR start "BITOR"
    public final void mBITOR() throws RecognitionException {
        try {
            int _type = BITOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:321:8: ( '|' NotLineStart )
            // PreprocessorLexer.g:321:10: '|' NotLineStart
            {
            match('|'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITOR"

    // $ANTLR start "BITOREQ"
    public final void mBITOREQ() throws RecognitionException {
        try {
            int _type = BITOREQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:322:10: ( '|=' NotLineStart )
            // PreprocessorLexer.g:322:12: '|=' NotLineStart
            {
            match("|="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITOREQ"

    // $ANTLR start "BITXOR"
    public final void mBITXOR() throws RecognitionException {
        try {
            int _type = BITXOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:323:9: ( '^' NotLineStart )
            // PreprocessorLexer.g:323:11: '^' NotLineStart
            {
            match('^'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITXOR"

    // $ANTLR start "BITXOREQ"
    public final void mBITXOREQ() throws RecognitionException {
        try {
            int _type = BITXOREQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:324:10: ( '^=' NotLineStart )
            // PreprocessorLexer.g:324:12: '^=' NotLineStart
            {
            match("^="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITXOREQ"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:325:8: ( ':' NotLineStart )
            // PreprocessorLexer.g:325:10: ':' NotLineStart
            {
            match(':'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:326:8: ( ',' NotLineStart )
            // PreprocessorLexer.g:326:10: ',' NotLineStart
            {
            match(','); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:327:6: ( '/' NotLineStart )
            // PreprocessorLexer.g:327:8: '/' NotLineStart
            {
            match('/'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "DIVEQ"
    public final void mDIVEQ() throws RecognitionException {
        try {
            int _type = DIVEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:328:8: ( '/=' NotLineStart )
            // PreprocessorLexer.g:328:10: '/=' NotLineStart
            {
            match("/="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIVEQ"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:329:9: ( '==' NotLineStart )
            // PreprocessorLexer.g:329:11: '==' NotLineStart
            {
            match("=="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:330:5: ( '>' NotLineStart )
            // PreprocessorLexer.g:330:7: '>' NotLineStart
            {
            match('>'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "GTE"
    public final void mGTE() throws RecognitionException {
        try {
            int _type = GTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:331:6: ( '>=' NotLineStart )
            // PreprocessorLexer.g:331:8: '>=' NotLineStart
            {
            match(">="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GTE"

    // $ANTLR start "HASHHASH"
    public final void mHASHHASH() throws RecognitionException {
        try {
            int _type = HASHHASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:333:10: ( '##' | '%:%:' NotLineStart )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0=='#') ) {
                alt81=1;
            }
            else if ( (LA81_0=='%') ) {
                alt81=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;

            }
            switch (alt81) {
                case 1 :
                    // PreprocessorLexer.g:333:12: '##'
                    {
                    match("##"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:333:19: '%:%:' NotLineStart
                    {
                    match("%:%:"); if (state.failed) return ;



                    mNotLineStart(); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HASHHASH"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:334:9: ( '{' | '<%' NotLineStart )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0=='{') ) {
                alt82=1;
            }
            else if ( (LA82_0=='<') ) {
                alt82=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;

            }
            switch (alt82) {
                case 1 :
                    // PreprocessorLexer.g:334:11: '{'
                    {
                    match('{'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:334:17: '<%' NotLineStart
                    {
                    match("<%"); if (state.failed) return ;



                    mNotLineStart(); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:335:9: ( '(' NotLineStart )
            // PreprocessorLexer.g:335:11: '(' NotLineStart
            {
            match('('); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "LSQUARE"
    public final void mLSQUARE() throws RecognitionException {
        try {
            int _type = LSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:336:10: ( '[' | '<:' NotLineStart )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0=='[') ) {
                alt83=1;
            }
            else if ( (LA83_0=='<') ) {
                alt83=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;

            }
            switch (alt83) {
                case 1 :
                    // PreprocessorLexer.g:336:12: '['
                    {
                    match('['); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:336:18: '<:' NotLineStart
                    {
                    match("<:"); if (state.failed) return ;



                    mNotLineStart(); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LSQUARE"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:337:5: ( '<' NotLineStart )
            // PreprocessorLexer.g:337:7: '<' NotLineStart
            {
            match('<'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LTE"
    public final void mLTE() throws RecognitionException {
        try {
            int _type = LTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:338:6: ( '<=' NotLineStart )
            // PreprocessorLexer.g:338:8: '<=' NotLineStart
            {
            match("<="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LTE"

    // $ANTLR start "MINUSMINUS"
    public final void mMINUSMINUS() throws RecognitionException {
        try {
            int _type = MINUSMINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:339:12: ( '--' NotLineStart )
            // PreprocessorLexer.g:339:14: '--' NotLineStart
            {
            match("--"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUSMINUS"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:340:6: ( '%' NotLineStart )
            // PreprocessorLexer.g:340:8: '%' NotLineStart
            {
            match('%'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "MODEQ"
    public final void mMODEQ() throws RecognitionException {
        try {
            int _type = MODEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:341:8: ( '%=' NotLineStart )
            // PreprocessorLexer.g:341:10: '%=' NotLineStart
            {
            match("%="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MODEQ"

    // $ANTLR start "NEQ"
    public final void mNEQ() throws RecognitionException {
        try {
            int _type = NEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:342:6: ( '!=' NotLineStart )
            // PreprocessorLexer.g:342:8: '!=' NotLineStart
            {
            match("!="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEQ"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:343:6: ( '!' NotLineStart )
            // PreprocessorLexer.g:343:8: '!' NotLineStart
            {
            match('!'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:344:5: ( '||' NotLineStart )
            // PreprocessorLexer.g:344:7: '||' NotLineStart
            {
            match("||"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:345:7: ( '+' NotLineStart )
            // PreprocessorLexer.g:345:9: '+' NotLineStart
            {
            match('+'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "PLUSEQ"
    public final void mPLUSEQ() throws RecognitionException {
        try {
            int _type = PLUSEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:346:9: ( '+=' NotLineStart )
            // PreprocessorLexer.g:346:11: '+=' NotLineStart
            {
            match("+="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUSEQ"

    // $ANTLR start "PLUSPLUS"
    public final void mPLUSPLUS() throws RecognitionException {
        try {
            int _type = PLUSPLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:347:10: ( '++' NotLineStart )
            // PreprocessorLexer.g:347:12: '++' NotLineStart
            {
            match("++"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUSPLUS"

    // $ANTLR start "QMARK"
    public final void mQMARK() throws RecognitionException {
        try {
            int _type = QMARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:348:8: ( '?' NotLineStart )
            // PreprocessorLexer.g:348:10: '?' NotLineStart
            {
            match('?'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QMARK"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:349:9: ( '}' | '%>' NotLineStart )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0=='}') ) {
                alt84=1;
            }
            else if ( (LA84_0=='%') ) {
                alt84=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }
            switch (alt84) {
                case 1 :
                    // PreprocessorLexer.g:349:11: '}'
                    {
                    match('}'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:349:17: '%>' NotLineStart
                    {
                    match("%>"); if (state.failed) return ;



                    mNotLineStart(); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:350:9: ( ')' NotLineStart )
            // PreprocessorLexer.g:350:11: ')' NotLineStart
            {
            match(')'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "RSQUARE"
    public final void mRSQUARE() throws RecognitionException {
        try {
            int _type = RSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:351:10: ( ']' | ':>' NotLineStart )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==']') ) {
                alt85=1;
            }
            else if ( (LA85_0==':') ) {
                alt85=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }
            switch (alt85) {
                case 1 :
                    // PreprocessorLexer.g:351:12: ']'
                    {
                    match(']'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:351:18: ':>' NotLineStart
                    {
                    match(":>"); if (state.failed) return ;



                    mNotLineStart(); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RSQUARE"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:352:7: ( ';' NotLineStart )
            // PreprocessorLexer.g:352:9: ';' NotLineStart
            {
            match(';'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "SHIFTLEFT"
    public final void mSHIFTLEFT() throws RecognitionException {
        try {
            int _type = SHIFTLEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:353:11: ( '<<' NotLineStart )
            // PreprocessorLexer.g:353:13: '<<' NotLineStart
            {
            match("<<"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHIFTLEFT"

    // $ANTLR start "SHIFTLEFTEQ"
    public final void mSHIFTLEFTEQ() throws RecognitionException {
        try {
            int _type = SHIFTLEFTEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:354:13: ( '<<=' NotLineStart )
            // PreprocessorLexer.g:354:15: '<<=' NotLineStart
            {
            match("<<="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHIFTLEFTEQ"

    // $ANTLR start "SHIFTRIGHT"
    public final void mSHIFTRIGHT() throws RecognitionException {
        try {
            int _type = SHIFTRIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:355:12: ( '>>' NotLineStart )
            // PreprocessorLexer.g:355:14: '>>' NotLineStart
            {
            match(">>"); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHIFTRIGHT"

    // $ANTLR start "SHIFTRIGHTEQ"
    public final void mSHIFTRIGHTEQ() throws RecognitionException {
        try {
            int _type = SHIFTRIGHTEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:356:14: ( '>>=' NotLineStart )
            // PreprocessorLexer.g:356:16: '>>=' NotLineStart
            {
            match(">>="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHIFTRIGHTEQ"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:357:7: ( '*' NotLineStart )
            // PreprocessorLexer.g:357:9: '*' NotLineStart
            {
            match('*'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "STAREQ"
    public final void mSTAREQ() throws RecognitionException {
        try {
            int _type = STAREQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:358:9: ( '*=' NotLineStart )
            // PreprocessorLexer.g:358:11: '*=' NotLineStart
            {
            match("*="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAREQ"

    // $ANTLR start "SUB"
    public final void mSUB() throws RecognitionException {
        try {
            int _type = SUB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:359:6: ( '-' NotLineStart )
            // PreprocessorLexer.g:359:8: '-' NotLineStart
            {
            match('-'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SUB"

    // $ANTLR start "SUBEQ"
    public final void mSUBEQ() throws RecognitionException {
        try {
            int _type = SUBEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:360:8: ( '-=' NotLineStart )
            // PreprocessorLexer.g:360:10: '-=' NotLineStart
            {
            match("-="); if (state.failed) return ;



            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SUBEQ"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:361:8: ( '~' NotLineStart )
            // PreprocessorLexer.g:361:10: '~' NotLineStart
            {
            match('~'); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "HEADER_NAME"
    public final void mHEADER_NAME() throws RecognitionException {
        try {
            int _type = HEADER_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:365:13: ({...}? => ( '\"' (~ ( '\\n' | '\"' ) )+ '\"' | '<' (~ ( '\\n' | '>' ) )+ '>' ) )
            // PreprocessorLexer.g:365:15: {...}? => ( '\"' (~ ( '\\n' | '\"' ) )+ '\"' | '<' (~ ( '\\n' | '>' ) )+ '>' )
            {
            if ( !((inInclude)) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "HEADER_NAME", "inInclude");
            }

            // PreprocessorLexer.g:366:4: ( '\"' (~ ( '\\n' | '\"' ) )+ '\"' | '<' (~ ( '\\n' | '>' ) )+ '>' )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0=='\"') ) {
                alt88=1;
            }
            else if ( (LA88_0=='<') ) {
                alt88=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;

            }
            switch (alt88) {
                case 1 :
                    // PreprocessorLexer.g:366:6: '\"' (~ ( '\\n' | '\"' ) )+ '\"'
                    {
                    match('\"'); if (state.failed) return ;

                    // PreprocessorLexer.g:366:10: (~ ( '\\n' | '\"' ) )+
                    int cnt86=0;
                    loop86:
                    do {
                        int alt86=2;
                        int LA86_0 = input.LA(1);

                        if ( ((LA86_0 >= '\u0000' && LA86_0 <= '\t')||(LA86_0 >= '\u000B' && LA86_0 <= '!')||(LA86_0 >= '#' && LA86_0 <= '\uFFFF')) ) {
                            alt86=1;
                        }


                        switch (alt86) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt86 >= 1 ) break loop86;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(86, input);
                                throw eee;
                        }
                        cnt86++;
                    } while (true);


                    match('\"'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:367:6: '<' (~ ( '\\n' | '>' ) )+ '>'
                    {
                    match('<'); if (state.failed) return ;

                    // PreprocessorLexer.g:367:10: (~ ( '\\n' | '>' ) )+
                    int cnt87=0;
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( ((LA87_0 >= '\u0000' && LA87_0 <= '\t')||(LA87_0 >= '\u000B' && LA87_0 <= '=')||(LA87_0 >= '?' && LA87_0 <= '\uFFFF')) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // PreprocessorLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '=')||(input.LA(1) >= '?' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt87 >= 1 ) break loop87;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(87, input);
                                throw eee;
                        }
                        cnt87++;
                    } while (true);


                    match('>'); if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {inInclude=false; atLineStart=false;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEADER_NAME"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:375:10: ( '//' ( options {greedy=true; } :~ ( '\\n' | '\\r' ) )* | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0=='/') ) {
                int LA91_1 = input.LA(2);

                if ( (LA91_1=='/') ) {
                    alt91=1;
                }
                else if ( (LA91_1=='*') ) {
                    alt91=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 91, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;

            }
            switch (alt91) {
                case 1 :
                    // PreprocessorLexer.g:375:12: '//' ( options {greedy=true; } :~ ( '\\n' | '\\r' ) )*
                    {
                    match("//"); if (state.failed) return ;



                    // PreprocessorLexer.g:375:17: ( options {greedy=true; } :~ ( '\\n' | '\\r' ) )*
                    loop89:
                    do {
                        int alt89=2;
                        int LA89_0 = input.LA(1);

                        if ( ((LA89_0 >= '\u0000' && LA89_0 <= '\t')||(LA89_0 >= '\u000B' && LA89_0 <= '\f')||(LA89_0 >= '\u000E' && LA89_0 <= '\uFFFF')) ) {
                            alt89=1;
                        }


                        switch (alt89) {
                    	case 1 :
                    	    // PreprocessorLexer.g:375:44: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // PreprocessorLexer.g:376:5: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); if (state.failed) return ;



                    // PreprocessorLexer.g:376:10: ( options {greedy=false; } : . )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0=='*') ) {
                            int LA90_1 = input.LA(2);

                            if ( (LA90_1=='/') ) {
                                alt90=2;
                            }
                            else if ( ((LA90_1 >= '\u0000' && LA90_1 <= '.')||(LA90_1 >= '0' && LA90_1 <= '\uFFFF')) ) {
                                alt90=1;
                            }


                        }
                        else if ( ((LA90_0 >= '\u0000' && LA90_0 <= ')')||(LA90_0 >= '+' && LA90_0 <= '\uFFFF')) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // PreprocessorLexer.g:376:38: .
                    	    {
                    	    matchAny(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);


                    match("*/"); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "OTHER"
    public final void mOTHER() throws RecognitionException {
        try {
            int _type = OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PreprocessorLexer.g:381:8: ( . NotLineStart )
            // PreprocessorLexer.g:381:10: . NotLineStart
            {
            matchAny(); if (state.failed) return ;

            mNotLineStart(); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OTHER"

    public void mTokens() throws RecognitionException {
        // PreprocessorLexer.g:1:8: ( PDEFINE | PINCLUDE | PIFDEF | PIFNDEF | PIF | PENDIF | PELIF | PELSE | PRAGMA | PERROR | PUNDEF | PLINE | HASH | DEFINED | NEWLINE | WS | AUTO | BREAK | CASE | CHAR | CONST | CONTINUE | DEFAULT | DO | DOUBLE | ELSE | ENUM | EXTERN | FLOAT | FOR | GOTO | IF | INLINE | INT | LONG | REGISTER | RESTRICT | RETURN | SHORT | SIGNED | SIZEOF | STATIC | STRUCT | SWITCH | TYPEDEF | UNION | UNSIGNED | VOID | VOLATILE | WHILE | ALIGNAS | ALIGNOF | ATOMIC | BOOL | COMPLEX | GENERIC | IMAGINARY | NORETURN | STATICASSERT | THREADLOCAL | ASSERT | ASSUME | AT | CHOOSE | COLLECTIVE | INPUT | INVARIANT | OUTPUT | PROC | SPAWN | WAIT | WHEN | IDENTIFIER | INTEGER_CONSTANT | FLOATING_CONSTANT | PP_NUMBER | CHARACTER_CONSTANT | STRING_LITERAL | DOT | AMPERSAND | AND | ARROW | ASSIGN | BITANDEQ | BITOR | BITOREQ | BITXOR | BITXOREQ | COLON | COMMA | DIV | DIVEQ | EQUALS | GT | GTE | HASHHASH | LCURLY | LPAREN | LSQUARE | LT | LTE | MINUSMINUS | MOD | MODEQ | NEQ | NOT | OR | PLUS | PLUSEQ | PLUSPLUS | QMARK | RCURLY | RPAREN | RSQUARE | SEMI | SHIFTLEFT | SHIFTLEFTEQ | SHIFTRIGHT | SHIFTRIGHTEQ | STAR | STAREQ | SUB | SUBEQ | TILDE | HEADER_NAME | COMMENT | OTHER )
        int alt92=127;
        alt92 = dfa92.predict(input);
        switch (alt92) {
            case 1 :
                // PreprocessorLexer.g:1:10: PDEFINE
                {
                mPDEFINE(); if (state.failed) return ;


                }
                break;
            case 2 :
                // PreprocessorLexer.g:1:18: PINCLUDE
                {
                mPINCLUDE(); if (state.failed) return ;


                }
                break;
            case 3 :
                // PreprocessorLexer.g:1:27: PIFDEF
                {
                mPIFDEF(); if (state.failed) return ;


                }
                break;
            case 4 :
                // PreprocessorLexer.g:1:34: PIFNDEF
                {
                mPIFNDEF(); if (state.failed) return ;


                }
                break;
            case 5 :
                // PreprocessorLexer.g:1:42: PIF
                {
                mPIF(); if (state.failed) return ;


                }
                break;
            case 6 :
                // PreprocessorLexer.g:1:46: PENDIF
                {
                mPENDIF(); if (state.failed) return ;


                }
                break;
            case 7 :
                // PreprocessorLexer.g:1:53: PELIF
                {
                mPELIF(); if (state.failed) return ;


                }
                break;
            case 8 :
                // PreprocessorLexer.g:1:59: PELSE
                {
                mPELSE(); if (state.failed) return ;


                }
                break;
            case 9 :
                // PreprocessorLexer.g:1:65: PRAGMA
                {
                mPRAGMA(); if (state.failed) return ;


                }
                break;
            case 10 :
                // PreprocessorLexer.g:1:72: PERROR
                {
                mPERROR(); if (state.failed) return ;


                }
                break;
            case 11 :
                // PreprocessorLexer.g:1:79: PUNDEF
                {
                mPUNDEF(); if (state.failed) return ;


                }
                break;
            case 12 :
                // PreprocessorLexer.g:1:86: PLINE
                {
                mPLINE(); if (state.failed) return ;


                }
                break;
            case 13 :
                // PreprocessorLexer.g:1:92: HASH
                {
                mHASH(); if (state.failed) return ;


                }
                break;
            case 14 :
                // PreprocessorLexer.g:1:97: DEFINED
                {
                mDEFINED(); if (state.failed) return ;


                }
                break;
            case 15 :
                // PreprocessorLexer.g:1:105: NEWLINE
                {
                mNEWLINE(); if (state.failed) return ;


                }
                break;
            case 16 :
                // PreprocessorLexer.g:1:113: WS
                {
                mWS(); if (state.failed) return ;


                }
                break;
            case 17 :
                // PreprocessorLexer.g:1:116: AUTO
                {
                mAUTO(); if (state.failed) return ;


                }
                break;
            case 18 :
                // PreprocessorLexer.g:1:121: BREAK
                {
                mBREAK(); if (state.failed) return ;


                }
                break;
            case 19 :
                // PreprocessorLexer.g:1:127: CASE
                {
                mCASE(); if (state.failed) return ;


                }
                break;
            case 20 :
                // PreprocessorLexer.g:1:132: CHAR
                {
                mCHAR(); if (state.failed) return ;


                }
                break;
            case 21 :
                // PreprocessorLexer.g:1:137: CONST
                {
                mCONST(); if (state.failed) return ;


                }
                break;
            case 22 :
                // PreprocessorLexer.g:1:143: CONTINUE
                {
                mCONTINUE(); if (state.failed) return ;


                }
                break;
            case 23 :
                // PreprocessorLexer.g:1:152: DEFAULT
                {
                mDEFAULT(); if (state.failed) return ;


                }
                break;
            case 24 :
                // PreprocessorLexer.g:1:160: DO
                {
                mDO(); if (state.failed) return ;


                }
                break;
            case 25 :
                // PreprocessorLexer.g:1:163: DOUBLE
                {
                mDOUBLE(); if (state.failed) return ;


                }
                break;
            case 26 :
                // PreprocessorLexer.g:1:170: ELSE
                {
                mELSE(); if (state.failed) return ;


                }
                break;
            case 27 :
                // PreprocessorLexer.g:1:175: ENUM
                {
                mENUM(); if (state.failed) return ;


                }
                break;
            case 28 :
                // PreprocessorLexer.g:1:180: EXTERN
                {
                mEXTERN(); if (state.failed) return ;


                }
                break;
            case 29 :
                // PreprocessorLexer.g:1:187: FLOAT
                {
                mFLOAT(); if (state.failed) return ;


                }
                break;
            case 30 :
                // PreprocessorLexer.g:1:193: FOR
                {
                mFOR(); if (state.failed) return ;


                }
                break;
            case 31 :
                // PreprocessorLexer.g:1:197: GOTO
                {
                mGOTO(); if (state.failed) return ;


                }
                break;
            case 32 :
                // PreprocessorLexer.g:1:202: IF
                {
                mIF(); if (state.failed) return ;


                }
                break;
            case 33 :
                // PreprocessorLexer.g:1:205: INLINE
                {
                mINLINE(); if (state.failed) return ;


                }
                break;
            case 34 :
                // PreprocessorLexer.g:1:212: INT
                {
                mINT(); if (state.failed) return ;


                }
                break;
            case 35 :
                // PreprocessorLexer.g:1:216: LONG
                {
                mLONG(); if (state.failed) return ;


                }
                break;
            case 36 :
                // PreprocessorLexer.g:1:221: REGISTER
                {
                mREGISTER(); if (state.failed) return ;


                }
                break;
            case 37 :
                // PreprocessorLexer.g:1:230: RESTRICT
                {
                mRESTRICT(); if (state.failed) return ;


                }
                break;
            case 38 :
                // PreprocessorLexer.g:1:239: RETURN
                {
                mRETURN(); if (state.failed) return ;


                }
                break;
            case 39 :
                // PreprocessorLexer.g:1:246: SHORT
                {
                mSHORT(); if (state.failed) return ;


                }
                break;
            case 40 :
                // PreprocessorLexer.g:1:252: SIGNED
                {
                mSIGNED(); if (state.failed) return ;


                }
                break;
            case 41 :
                // PreprocessorLexer.g:1:259: SIZEOF
                {
                mSIZEOF(); if (state.failed) return ;


                }
                break;
            case 42 :
                // PreprocessorLexer.g:1:266: STATIC
                {
                mSTATIC(); if (state.failed) return ;


                }
                break;
            case 43 :
                // PreprocessorLexer.g:1:273: STRUCT
                {
                mSTRUCT(); if (state.failed) return ;


                }
                break;
            case 44 :
                // PreprocessorLexer.g:1:280: SWITCH
                {
                mSWITCH(); if (state.failed) return ;


                }
                break;
            case 45 :
                // PreprocessorLexer.g:1:287: TYPEDEF
                {
                mTYPEDEF(); if (state.failed) return ;


                }
                break;
            case 46 :
                // PreprocessorLexer.g:1:295: UNION
                {
                mUNION(); if (state.failed) return ;


                }
                break;
            case 47 :
                // PreprocessorLexer.g:1:301: UNSIGNED
                {
                mUNSIGNED(); if (state.failed) return ;


                }
                break;
            case 48 :
                // PreprocessorLexer.g:1:310: VOID
                {
                mVOID(); if (state.failed) return ;


                }
                break;
            case 49 :
                // PreprocessorLexer.g:1:315: VOLATILE
                {
                mVOLATILE(); if (state.failed) return ;


                }
                break;
            case 50 :
                // PreprocessorLexer.g:1:324: WHILE
                {
                mWHILE(); if (state.failed) return ;


                }
                break;
            case 51 :
                // PreprocessorLexer.g:1:330: ALIGNAS
                {
                mALIGNAS(); if (state.failed) return ;


                }
                break;
            case 52 :
                // PreprocessorLexer.g:1:338: ALIGNOF
                {
                mALIGNOF(); if (state.failed) return ;


                }
                break;
            case 53 :
                // PreprocessorLexer.g:1:346: ATOMIC
                {
                mATOMIC(); if (state.failed) return ;


                }
                break;
            case 54 :
                // PreprocessorLexer.g:1:353: BOOL
                {
                mBOOL(); if (state.failed) return ;


                }
                break;
            case 55 :
                // PreprocessorLexer.g:1:358: COMPLEX
                {
                mCOMPLEX(); if (state.failed) return ;


                }
                break;
            case 56 :
                // PreprocessorLexer.g:1:366: GENERIC
                {
                mGENERIC(); if (state.failed) return ;


                }
                break;
            case 57 :
                // PreprocessorLexer.g:1:374: IMAGINARY
                {
                mIMAGINARY(); if (state.failed) return ;


                }
                break;
            case 58 :
                // PreprocessorLexer.g:1:384: NORETURN
                {
                mNORETURN(); if (state.failed) return ;


                }
                break;
            case 59 :
                // PreprocessorLexer.g:1:393: STATICASSERT
                {
                mSTATICASSERT(); if (state.failed) return ;


                }
                break;
            case 60 :
                // PreprocessorLexer.g:1:406: THREADLOCAL
                {
                mTHREADLOCAL(); if (state.failed) return ;


                }
                break;
            case 61 :
                // PreprocessorLexer.g:1:418: ASSERT
                {
                mASSERT(); if (state.failed) return ;


                }
                break;
            case 62 :
                // PreprocessorLexer.g:1:425: ASSUME
                {
                mASSUME(); if (state.failed) return ;


                }
                break;
            case 63 :
                // PreprocessorLexer.g:1:432: AT
                {
                mAT(); if (state.failed) return ;


                }
                break;
            case 64 :
                // PreprocessorLexer.g:1:435: CHOOSE
                {
                mCHOOSE(); if (state.failed) return ;


                }
                break;
            case 65 :
                // PreprocessorLexer.g:1:442: COLLECTIVE
                {
                mCOLLECTIVE(); if (state.failed) return ;


                }
                break;
            case 66 :
                // PreprocessorLexer.g:1:453: INPUT
                {
                mINPUT(); if (state.failed) return ;


                }
                break;
            case 67 :
                // PreprocessorLexer.g:1:459: INVARIANT
                {
                mINVARIANT(); if (state.failed) return ;


                }
                break;
            case 68 :
                // PreprocessorLexer.g:1:469: OUTPUT
                {
                mOUTPUT(); if (state.failed) return ;


                }
                break;
            case 69 :
                // PreprocessorLexer.g:1:476: PROC
                {
                mPROC(); if (state.failed) return ;


                }
                break;
            case 70 :
                // PreprocessorLexer.g:1:481: SPAWN
                {
                mSPAWN(); if (state.failed) return ;


                }
                break;
            case 71 :
                // PreprocessorLexer.g:1:487: WAIT
                {
                mWAIT(); if (state.failed) return ;


                }
                break;
            case 72 :
                // PreprocessorLexer.g:1:492: WHEN
                {
                mWHEN(); if (state.failed) return ;


                }
                break;
            case 73 :
                // PreprocessorLexer.g:1:497: IDENTIFIER
                {
                mIDENTIFIER(); if (state.failed) return ;


                }
                break;
            case 74 :
                // PreprocessorLexer.g:1:508: INTEGER_CONSTANT
                {
                mINTEGER_CONSTANT(); if (state.failed) return ;


                }
                break;
            case 75 :
                // PreprocessorLexer.g:1:525: FLOATING_CONSTANT
                {
                mFLOATING_CONSTANT(); if (state.failed) return ;


                }
                break;
            case 76 :
                // PreprocessorLexer.g:1:543: PP_NUMBER
                {
                mPP_NUMBER(); if (state.failed) return ;


                }
                break;
            case 77 :
                // PreprocessorLexer.g:1:553: CHARACTER_CONSTANT
                {
                mCHARACTER_CONSTANT(); if (state.failed) return ;


                }
                break;
            case 78 :
                // PreprocessorLexer.g:1:572: STRING_LITERAL
                {
                mSTRING_LITERAL(); if (state.failed) return ;


                }
                break;
            case 79 :
                // PreprocessorLexer.g:1:587: DOT
                {
                mDOT(); if (state.failed) return ;


                }
                break;
            case 80 :
                // PreprocessorLexer.g:1:591: AMPERSAND
                {
                mAMPERSAND(); if (state.failed) return ;


                }
                break;
            case 81 :
                // PreprocessorLexer.g:1:601: AND
                {
                mAND(); if (state.failed) return ;


                }
                break;
            case 82 :
                // PreprocessorLexer.g:1:605: ARROW
                {
                mARROW(); if (state.failed) return ;


                }
                break;
            case 83 :
                // PreprocessorLexer.g:1:611: ASSIGN
                {
                mASSIGN(); if (state.failed) return ;


                }
                break;
            case 84 :
                // PreprocessorLexer.g:1:618: BITANDEQ
                {
                mBITANDEQ(); if (state.failed) return ;


                }
                break;
            case 85 :
                // PreprocessorLexer.g:1:627: BITOR
                {
                mBITOR(); if (state.failed) return ;


                }
                break;
            case 86 :
                // PreprocessorLexer.g:1:633: BITOREQ
                {
                mBITOREQ(); if (state.failed) return ;


                }
                break;
            case 87 :
                // PreprocessorLexer.g:1:641: BITXOR
                {
                mBITXOR(); if (state.failed) return ;


                }
                break;
            case 88 :
                // PreprocessorLexer.g:1:648: BITXOREQ
                {
                mBITXOREQ(); if (state.failed) return ;


                }
                break;
            case 89 :
                // PreprocessorLexer.g:1:657: COLON
                {
                mCOLON(); if (state.failed) return ;


                }
                break;
            case 90 :
                // PreprocessorLexer.g:1:663: COMMA
                {
                mCOMMA(); if (state.failed) return ;


                }
                break;
            case 91 :
                // PreprocessorLexer.g:1:669: DIV
                {
                mDIV(); if (state.failed) return ;


                }
                break;
            case 92 :
                // PreprocessorLexer.g:1:673: DIVEQ
                {
                mDIVEQ(); if (state.failed) return ;


                }
                break;
            case 93 :
                // PreprocessorLexer.g:1:679: EQUALS
                {
                mEQUALS(); if (state.failed) return ;


                }
                break;
            case 94 :
                // PreprocessorLexer.g:1:686: GT
                {
                mGT(); if (state.failed) return ;


                }
                break;
            case 95 :
                // PreprocessorLexer.g:1:689: GTE
                {
                mGTE(); if (state.failed) return ;


                }
                break;
            case 96 :
                // PreprocessorLexer.g:1:693: HASHHASH
                {
                mHASHHASH(); if (state.failed) return ;


                }
                break;
            case 97 :
                // PreprocessorLexer.g:1:702: LCURLY
                {
                mLCURLY(); if (state.failed) return ;


                }
                break;
            case 98 :
                // PreprocessorLexer.g:1:709: LPAREN
                {
                mLPAREN(); if (state.failed) return ;


                }
                break;
            case 99 :
                // PreprocessorLexer.g:1:716: LSQUARE
                {
                mLSQUARE(); if (state.failed) return ;


                }
                break;
            case 100 :
                // PreprocessorLexer.g:1:724: LT
                {
                mLT(); if (state.failed) return ;


                }
                break;
            case 101 :
                // PreprocessorLexer.g:1:727: LTE
                {
                mLTE(); if (state.failed) return ;


                }
                break;
            case 102 :
                // PreprocessorLexer.g:1:731: MINUSMINUS
                {
                mMINUSMINUS(); if (state.failed) return ;


                }
                break;
            case 103 :
                // PreprocessorLexer.g:1:742: MOD
                {
                mMOD(); if (state.failed) return ;


                }
                break;
            case 104 :
                // PreprocessorLexer.g:1:746: MODEQ
                {
                mMODEQ(); if (state.failed) return ;


                }
                break;
            case 105 :
                // PreprocessorLexer.g:1:752: NEQ
                {
                mNEQ(); if (state.failed) return ;


                }
                break;
            case 106 :
                // PreprocessorLexer.g:1:756: NOT
                {
                mNOT(); if (state.failed) return ;


                }
                break;
            case 107 :
                // PreprocessorLexer.g:1:760: OR
                {
                mOR(); if (state.failed) return ;


                }
                break;
            case 108 :
                // PreprocessorLexer.g:1:763: PLUS
                {
                mPLUS(); if (state.failed) return ;


                }
                break;
            case 109 :
                // PreprocessorLexer.g:1:768: PLUSEQ
                {
                mPLUSEQ(); if (state.failed) return ;


                }
                break;
            case 110 :
                // PreprocessorLexer.g:1:775: PLUSPLUS
                {
                mPLUSPLUS(); if (state.failed) return ;


                }
                break;
            case 111 :
                // PreprocessorLexer.g:1:784: QMARK
                {
                mQMARK(); if (state.failed) return ;


                }
                break;
            case 112 :
                // PreprocessorLexer.g:1:790: RCURLY
                {
                mRCURLY(); if (state.failed) return ;


                }
                break;
            case 113 :
                // PreprocessorLexer.g:1:797: RPAREN
                {
                mRPAREN(); if (state.failed) return ;


                }
                break;
            case 114 :
                // PreprocessorLexer.g:1:804: RSQUARE
                {
                mRSQUARE(); if (state.failed) return ;


                }
                break;
            case 115 :
                // PreprocessorLexer.g:1:812: SEMI
                {
                mSEMI(); if (state.failed) return ;


                }
                break;
            case 116 :
                // PreprocessorLexer.g:1:817: SHIFTLEFT
                {
                mSHIFTLEFT(); if (state.failed) return ;


                }
                break;
            case 117 :
                // PreprocessorLexer.g:1:827: SHIFTLEFTEQ
                {
                mSHIFTLEFTEQ(); if (state.failed) return ;


                }
                break;
            case 118 :
                // PreprocessorLexer.g:1:839: SHIFTRIGHT
                {
                mSHIFTRIGHT(); if (state.failed) return ;


                }
                break;
            case 119 :
                // PreprocessorLexer.g:1:850: SHIFTRIGHTEQ
                {
                mSHIFTRIGHTEQ(); if (state.failed) return ;


                }
                break;
            case 120 :
                // PreprocessorLexer.g:1:863: STAR
                {
                mSTAR(); if (state.failed) return ;


                }
                break;
            case 121 :
                // PreprocessorLexer.g:1:868: STAREQ
                {
                mSTAREQ(); if (state.failed) return ;


                }
                break;
            case 122 :
                // PreprocessorLexer.g:1:875: SUB
                {
                mSUB(); if (state.failed) return ;


                }
                break;
            case 123 :
                // PreprocessorLexer.g:1:879: SUBEQ
                {
                mSUBEQ(); if (state.failed) return ;


                }
                break;
            case 124 :
                // PreprocessorLexer.g:1:885: TILDE
                {
                mTILDE(); if (state.failed) return ;


                }
                break;
            case 125 :
                // PreprocessorLexer.g:1:891: HEADER_NAME
                {
                mHEADER_NAME(); if (state.failed) return ;


                }
                break;
            case 126 :
                // PreprocessorLexer.g:1:903: COMMENT
                {
                mCOMMENT(); if (state.failed) return ;


                }
                break;
            case 127 :
                // PreprocessorLexer.g:1:911: OTHER
                {
                mOTHER(); if (state.failed) return ;


                }
                break;

        }

    }

    // $ANTLR start synpred1_PreprocessorLexer
    public final void synpred1_PreprocessorLexer_fragment() throws RecognitionException {
        // PreprocessorLexer.g:310:8: ( '..' )
        // PreprocessorLexer.g:310:9: '..'
        {
        match(".."); if (state.failed) return ;



        }

    }
    // $ANTLR end synpred1_PreprocessorLexer

    public final boolean synpred1_PreprocessorLexer() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_PreprocessorLexer_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA51 dfa51 = new DFA51(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA61 dfa61 = new DFA61(this);
    protected DFA65 dfa65 = new DFA65(this);
    protected DFA92 dfa92 = new DFA92(this);
    static final String DFA51_eotS =
        "\4\uffff";
    static final String DFA51_eofS =
        "\4\uffff";
    static final String DFA51_minS =
        "\2\56\2\uffff";
    static final String DFA51_maxS =
        "\1\71\1\145\2\uffff";
    static final String DFA51_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA51_specialS =
        "\4\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA51_eot = DFA.unpackEncodedString(DFA51_eotS);
    static final short[] DFA51_eof = DFA.unpackEncodedString(DFA51_eofS);
    static final char[] DFA51_min = DFA.unpackEncodedStringToUnsignedChars(DFA51_minS);
    static final char[] DFA51_max = DFA.unpackEncodedStringToUnsignedChars(DFA51_maxS);
    static final short[] DFA51_accept = DFA.unpackEncodedString(DFA51_acceptS);
    static final short[] DFA51_special = DFA.unpackEncodedString(DFA51_specialS);
    static final short[][] DFA51_transition;

    static {
        int numStates = DFA51_transitionS.length;
        DFA51_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA51_transition[i] = DFA.unpackEncodedString(DFA51_transitionS[i]);
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = DFA51_eot;
            this.eof = DFA51_eof;
            this.min = DFA51_min;
            this.max = DFA51_max;
            this.accept = DFA51_accept;
            this.special = DFA51_special;
            this.transition = DFA51_transition;
        }
        public String getDescription() {
            return "218:1: fragment DecimalFloatingConstant : ( FractionalConstant ( ExponentPart )? ( FloatingSuffix )? | ( Digit )+ ExponentPart ( FloatingSuffix )? );";
        }
    }
    static final String DFA55_eotS =
        "\3\uffff\1\4\1\uffff";
    static final String DFA55_eofS =
        "\5\uffff";
    static final String DFA55_minS =
        "\2\56\1\uffff\1\60\1\uffff";
    static final String DFA55_maxS =
        "\2\71\1\uffff\1\71\1\uffff";
    static final String DFA55_acceptS =
        "\2\uffff\1\1\1\uffff\1\2";
    static final String DFA55_specialS =
        "\5\uffff}>";
    static final String[] DFA55_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1",
            "",
            "\12\2",
            ""
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
            return "224:1: fragment FractionalConstant : ( ( Digit )* '.' ( Digit )+ | ( Digit )+ '.' );";
        }
    }
    static final String DFA61_eotS =
        "\6\uffff";
    static final String DFA61_eofS =
        "\6\uffff";
    static final String DFA61_minS =
        "\1\60\1\130\2\56\2\uffff";
    static final String DFA61_maxS =
        "\1\60\1\170\1\146\1\160\2\uffff";
    static final String DFA61_acceptS =
        "\4\uffff\1\1\1\2";
    static final String DFA61_specialS =
        "\6\uffff}>";
    static final String[] DFA61_transitionS = {
            "\1\1",
            "\1\2\37\uffff\1\2",
            "\1\4\1\uffff\12\3\7\uffff\6\3\32\uffff\6\3",
            "\1\4\1\uffff\12\3\7\uffff\6\3\11\uffff\1\5\20\uffff\6\3\11"+
            "\uffff\1\5",
            "",
            ""
    };

    static final short[] DFA61_eot = DFA.unpackEncodedString(DFA61_eotS);
    static final short[] DFA61_eof = DFA.unpackEncodedString(DFA61_eofS);
    static final char[] DFA61_min = DFA.unpackEncodedStringToUnsignedChars(DFA61_minS);
    static final char[] DFA61_max = DFA.unpackEncodedStringToUnsignedChars(DFA61_maxS);
    static final short[] DFA61_accept = DFA.unpackEncodedString(DFA61_acceptS);
    static final short[] DFA61_special = DFA.unpackEncodedString(DFA61_specialS);
    static final short[][] DFA61_transition;

    static {
        int numStates = DFA61_transitionS.length;
        DFA61_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA61_transition[i] = DFA.unpackEncodedString(DFA61_transitionS[i]);
        }
    }

    class DFA61 extends DFA {

        public DFA61(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 61;
            this.eot = DFA61_eot;
            this.eof = DFA61_eof;
            this.min = DFA61_min;
            this.max = DFA61_max;
            this.accept = DFA61_accept;
            this.special = DFA61_special;
            this.transition = DFA61_transition;
        }
        public String getDescription() {
            return "236:1: fragment HexadecimalFloatingConstant : ( HexPrefix HexFractionalConstant BinaryExponentPart ( FloatingSuffix )? | HexPrefix ( HexadecimalDigit )+ BinaryExponentPart ( FloatingSuffix )? );";
        }
    }
    static final String DFA65_eotS =
        "\3\uffff\1\4\1\uffff";
    static final String DFA65_eofS =
        "\5\uffff";
    static final String DFA65_minS =
        "\2\56\1\uffff\1\60\1\uffff";
    static final String DFA65_maxS =
        "\2\146\1\uffff\1\71\1\uffff";
    static final String DFA65_acceptS =
        "\2\uffff\1\1\1\uffff\1\2";
    static final String DFA65_specialS =
        "\5\uffff}>";
    static final String[] DFA65_transitionS = {
            "\1\2\1\uffff\12\1\7\uffff\6\1\32\uffff\6\1",
            "\1\3\1\uffff\12\1\7\uffff\6\1\32\uffff\6\1",
            "",
            "\12\2",
            ""
    };

    static final short[] DFA65_eot = DFA.unpackEncodedString(DFA65_eotS);
    static final short[] DFA65_eof = DFA.unpackEncodedString(DFA65_eofS);
    static final char[] DFA65_min = DFA.unpackEncodedStringToUnsignedChars(DFA65_minS);
    static final char[] DFA65_max = DFA.unpackEncodedStringToUnsignedChars(DFA65_maxS);
    static final short[] DFA65_accept = DFA.unpackEncodedString(DFA65_acceptS);
    static final short[] DFA65_special = DFA.unpackEncodedString(DFA65_specialS);
    static final short[][] DFA65_transition;

    static {
        int numStates = DFA65_transitionS.length;
        DFA65_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA65_transition[i] = DFA.unpackEncodedString(DFA65_transitionS[i]);
        }
    }

    class DFA65 extends DFA {

        public DFA65(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 65;
            this.eot = DFA65_eot;
            this.eof = DFA65_eof;
            this.min = DFA65_min;
            this.max = DFA65_max;
            this.accept = DFA65_accept;
            this.special = DFA65_special;
            this.transition = DFA65_transition;
        }
        public String getDescription() {
            return "244:1: fragment HexFractionalConstant : ( ( HexadecimalDigit )* '.' ( Digit )+ | ( HexadecimalDigit )+ '.' );";
        }
    }
    static final String DFA92_eotS =
        "\1\uffff\1\67\1\102\1\105\1\66\1\uffff\17\105\1\uffff\1\66\1\105"+
        "\2\162\1\u0081\1\uffff\1\66\1\105\1\66\1\u0086\1\u008a\1\u008c\1"+
        "\u008f\1\u0091\1\u0093\1\uffff\1\u0097\1\u009a\1\u009d\1\uffff\1"+
        "\u00a3\2\uffff\1\u00a8\1\u00ab\5\uffff\1\u00b0\3\uffff\1\102\1\67"+
        "\1\uffff\1\102\7\uffff\1\105\1\u00b9\2\uffff\13\105\1\u00c5\12\105"+
        "\2\uffff\21\105\3\uffff\4\162\1\u00ef\1\171\1\uffff\4\162\2\171"+
        "\1\u00ef\30\uffff\1\u0106\5\uffff\1\u009e\1\u00a6\1\u0107\1\u0109"+
        "\20\uffff\1\u010c\3\uffff\2\105\1\uffff\11\105\1\u011c\1\105\1\uffff"+
        "\1\105\1\u011f\41\105\5\162\1\u00ef\1\uffff\1\171\1\u00ef\1\171"+
        "\1\u00ef\11\162\1\171\2\162\1\u015b\1\u00a4\6\uffff\1\u015e\6\uffff"+
        "\3\105\1\u0162\1\105\1\u0164\1\u0165\2\105\1\u0168\1\u0169\2\105"+
        "\1\uffff\1\u016c\1\105\1\uffff\1\u016e\14\105\1\u017b\24\105\3\162"+
        "\1\171\2\u00ef\20\162\3\171\4\uffff\3\105\1\uffff\1\u01a8\2\uffff"+
        "\1\u01a9\1\105\2\uffff\1\105\1\u01ac\1\uffff\1\105\1\uffff\3\105"+
        "\1\u01b1\6\105\1\u01b8\1\105\1\uffff\1\105\1\u01bb\2\105\1\u01be"+
        "\14\105\1\u01cb\1\105\1\u01cd\1\u01ce\17\162\2\171\1\u00ef\2\uffff"+
        "\2\105\1\u01e1\2\uffff\1\105\1\u01e3\1\uffff\1\u01e4\2\105\1\u01e7"+
        "\1\uffff\1\u01e8\1\u01e9\1\u01ea\1\u01eb\1\u01ec\1\105\1\uffff\2"+
        "\105\1\uffff\2\105\1\uffff\11\105\1\u01fc\2\105\1\uffff\1\u01ff"+
        "\2\uffff\15\162\1\171\2\u00ef\1\u0208\1\u0209\1\uffff\1\105\2\uffff"+
        "\2\105\6\uffff\1\u020d\4\105\1\u0212\6\105\1\u0219\1\u021a\1\u021b"+
        "\1\uffff\1\105\1\u021d\1\uffff\7\162\1\u00ef\2\uffff\1\u021f\1\u0220"+
        "\1\u0221\1\uffff\1\u0222\1\u0223\1\u0224\1\u0225\1\uffff\1\u0226"+
        "\1\u0227\4\105\3\uffff\1\105\13\uffff\1\105\1\u022e\3\105\1\u0232"+
        "\1\uffff\2\105\1\u0235\1\uffff\2\105\1\uffff\3\105\1\u023b\1\u023c"+
        "\2\uffff";
    static final String DFA92_eofS =
        "\u023d\uffff";
    static final String DFA92_minS =
        "\1\0\2\11\1\145\1\12\1\uffff\1\165\1\162\1\141\2\154\1\157\1\146"+
        "\1\157\1\145\1\150\1\171\1\42\1\157\1\150\1\101\1\uffff\1\125\1"+
        "\42\2\56\1\60\1\uffff\1\0\1\42\1\0\1\46\1\55\3\75\1\76\1\uffff\1"+
        "\52\1\75\1\72\1\uffff\1\0\2\uffff\1\75\1\53\5\uffff\1\75\3\uffff"+
        "\2\11\1\uffff\1\11\1\uffff\1\146\1\154\4\uffff\1\146\1\60\2\uffff"+
        "\1\164\1\145\1\163\1\141\1\156\1\163\1\165\1\164\1\157\1\162\1\164"+
        "\1\60\1\154\1\156\1\147\1\157\1\147\1\141\1\151\1\160\1\151\1\42"+
        "\2\uffff\2\151\1\154\2\157\1\145\1\155\1\157\1\164\1\150\1\163\1"+
        "\150\1\156\1\165\1\162\1\160\1\141\3\uffff\5\56\1\53\1\uffff\7\56"+
        "\1\uffff\2\0\25\uffff\1\75\5\uffff\4\0\20\uffff\1\144\1\uffff\1"+
        "\151\1\uffff\1\141\1\142\1\uffff\1\157\1\141\1\145\1\162\1\163\1"+
        "\145\1\155\1\145\1\141\1\60\1\157\1\uffff\1\151\1\60\1\147\1\151"+
        "\1\164\1\165\1\162\1\156\1\145\1\164\1\165\1\164\1\145\1\157\1\151"+
        "\1\144\1\141\1\154\1\151\2\157\1\155\1\156\1\141\1\162\1\141\1\162"+
        "\1\163\1\157\1\160\1\164\1\157\1\141\1\151\1\145\6\56\1\uffff\1"+
        "\53\1\56\1\60\11\56\1\53\1\60\2\56\5\0\3\uffff\1\0\6\uffff\1\156"+
        "\1\165\1\154\1\60\1\153\2\60\1\164\1\151\2\60\1\162\1\164\1\uffff"+
        "\1\60\1\156\1\uffff\1\60\1\163\2\162\1\164\1\145\1\157\1\151\2\143"+
        "\1\144\1\156\1\147\1\60\1\164\1\145\1\147\1\155\1\154\1\160\1\145"+
        "\1\147\1\145\1\164\2\145\1\157\1\165\1\141\1\160\1\143\1\167\1\164"+
        "\1\156\3\56\1\60\22\56\1\60\1\53\1\60\3\0\1\uffff\1\145\1\154\1"+
        "\145\1\uffff\1\60\2\uffff\1\60\1\156\2\uffff\1\156\1\60\1\uffff"+
        "\1\145\1\uffff\1\164\1\151\1\156\1\60\1\144\1\146\1\143\1\164\1"+
        "\150\1\145\1\60\1\156\1\uffff\1\151\1\60\1\156\1\151\1\60\1\154"+
        "\1\162\1\151\1\164\1\151\1\141\1\162\1\155\1\163\1\164\1\162\1\165"+
        "\1\60\1\156\2\60\17\56\1\53\1\60\1\56\2\0\1\144\1\164\1\60\2\uffff"+
        "\1\165\1\60\1\uffff\1\60\1\145\1\143\1\60\1\uffff\5\60\1\146\1\uffff"+
        "\1\145\1\154\1\uffff\1\141\1\143\1\uffff\1\145\1\151\1\156\1\165"+
        "\1\143\1\144\1\164\2\145\1\60\1\151\1\164\1\uffff\1\60\2\uffff\15"+
        "\56\1\60\2\56\2\60\1\uffff\1\145\2\uffff\1\162\1\164\6\uffff\1\60"+
        "\1\144\1\145\1\163\1\146\1\60\1\170\1\143\1\141\1\162\2\137\3\60"+
        "\1\uffff\1\141\1\60\1\uffff\10\56\1\0\1\uffff\3\60\1\uffff\4\60"+
        "\1\uffff\2\60\1\162\1\156\1\141\1\154\3\uffff\1\156\13\uffff\1\171"+
        "\1\60\1\163\1\157\1\164\1\60\1\uffff\1\163\1\143\1\60\1\uffff\1"+
        "\145\1\141\1\uffff\1\162\1\154\1\164\2\60\2\uffff";
    static final String DFA92_maxS =
        "\1\uffff\1\43\1\165\1\157\1\12\1\uffff\1\165\1\162\1\157\1\170\2"+
        "\157\1\156\1\157\1\145\1\167\1\171\1\156\1\157\1\150\1\167\1\uffff"+
        "\1\165\1\47\2\172\1\71\1\uffff\1\uffff\1\47\1\uffff\1\75\1\76\1"+
        "\75\1\174\1\75\1\76\1\uffff\1\75\2\76\1\uffff\1\uffff\2\uffff\2"+
        "\75\5\uffff\1\75\3\uffff\1\165\1\43\1\uffff\1\165\1\uffff\1\156"+
        "\1\162\4\uffff\1\146\1\172\2\uffff\1\164\1\145\1\163\1\141\1\156"+
        "\1\163\1\165\1\164\1\157\1\162\1\164\1\172\1\164\1\156\1\164\1\157"+
        "\1\172\1\162\1\151\1\160\1\163\1\42\2\uffff\1\154\1\151\1\164\2"+
        "\157\1\145\1\155\1\157\1\164\1\150\1\163\1\150\1\156\1\165\1\162"+
        "\1\160\1\150\3\uffff\5\172\1\71\1\uffff\4\172\1\146\1\145\1\172"+
        "\1\uffff\2\uffff\25\uffff\1\75\5\uffff\4\uffff\20\uffff\1\156\1"+
        "\uffff\1\163\1\uffff\1\151\1\142\1\uffff\1\157\1\141\1\145\1\162"+
        "\1\164\1\145\1\155\1\145\1\141\1\172\1\157\1\uffff\1\151\1\172\1"+
        "\147\1\151\1\164\1\165\1\162\1\156\1\145\1\164\1\165\1\164\1\145"+
        "\1\157\1\151\1\144\1\141\1\154\1\151\2\157\1\155\1\156\1\141\1\162"+
        "\1\141\1\162\1\163\1\157\1\166\1\164\1\157\1\141\1\151\1\145\6\172"+
        "\1\uffff\1\71\1\172\1\71\12\172\1\71\2\172\1\0\4\uffff\3\uffff\1"+
        "\uffff\6\uffff\1\156\1\165\1\154\1\172\1\153\2\172\1\164\1\151\2"+
        "\172\1\162\1\164\1\uffff\1\172\1\156\1\uffff\1\172\1\163\2\162\1"+
        "\164\1\145\1\157\1\151\2\143\1\144\1\156\1\147\1\172\1\164\1\145"+
        "\1\147\1\155\1\154\1\160\1\145\1\147\1\145\1\164\1\145\1\165\1\157"+
        "\1\165\1\141\1\160\1\143\1\167\1\164\1\156\3\172\1\71\22\172\1\160"+
        "\1\71\1\160\1\0\2\uffff\1\uffff\1\145\1\154\1\145\1\uffff\1\172"+
        "\2\uffff\1\172\1\156\2\uffff\1\156\1\172\1\uffff\1\145\1\uffff\1"+
        "\164\1\151\1\156\1\172\1\144\1\146\1\143\1\164\1\150\1\145\1\172"+
        "\1\156\1\uffff\1\151\1\172\1\156\1\151\1\172\1\154\1\162\1\151\1"+
        "\164\1\151\1\141\1\162\1\155\1\163\1\164\1\162\1\165\1\172\1\156"+
        "\21\172\2\71\1\172\2\uffff\1\144\1\164\1\172\2\uffff\1\165\1\172"+
        "\1\uffff\1\172\1\145\1\143\1\172\1\uffff\5\172\1\146\1\uffff\1\145"+
        "\1\154\1\uffff\1\157\1\143\1\uffff\1\145\1\151\1\156\1\165\1\143"+
        "\1\144\1\164\2\145\1\172\1\151\1\164\1\uffff\1\172\2\uffff\15\172"+
        "\1\71\4\172\1\uffff\1\145\2\uffff\1\162\1\164\6\uffff\1\172\1\144"+
        "\1\145\1\163\1\146\1\172\1\170\1\143\1\141\1\162\2\137\3\172\1\uffff"+
        "\1\141\1\172\1\uffff\10\172\1\0\1\uffff\3\172\1\uffff\4\172\1\uffff"+
        "\2\172\1\162\1\156\1\141\1\154\3\uffff\1\156\13\uffff\1\171\1\172"+
        "\1\163\1\157\1\164\1\172\1\uffff\1\163\1\143\1\172\1\uffff\1\145"+
        "\1\141\1\uffff\1\162\1\154\1\164\2\172\2\uffff";
    static final String DFA92_acceptS =
        "\5\uffff\1\17\17\uffff\1\77\5\uffff\1\111\11\uffff\1\132\3\uffff"+
        "\1\141\1\uffff\1\142\1\143\2\uffff\1\157\1\160\1\161\1\162\1\163"+
        "\1\uffff\1\174\1\177\1\20\2\uffff\1\140\1\uffff\1\1\2\uffff\1\11"+
        "\1\13\1\14\1\15\2\uffff\1\111\1\17\26\uffff\1\115\1\116\21\uffff"+
        "\1\77\1\101\1\112\6\uffff\1\114\7\uffff\1\117\2\uffff\1\121\1\124"+
        "\1\120\1\122\1\146\1\173\1\172\1\135\1\123\1\126\1\153\1\125\1\130"+
        "\1\127\1\162\1\131\1\132\1\134\1\176\1\133\1\137\1\uffff\1\136\1"+
        "\150\1\160\1\147\1\141\4\uffff\1\144\1\175\1\142\1\143\1\151\1\152"+
        "\1\155\1\156\1\154\1\157\1\161\1\163\1\171\1\170\1\174\1\2\1\uffff"+
        "\1\6\1\uffff\1\12\2\uffff\1\30\13\uffff\1\40\51\uffff\1\113\25\uffff"+
        "\1\167\1\166\1\145\1\uffff\1\164\1\3\1\4\1\5\1\7\1\10\15\uffff\1"+
        "\36\2\uffff\1\42\76\uffff\1\165\3\uffff\1\21\1\uffff\1\23\1\24\2"+
        "\uffff\1\32\1\33\2\uffff\1\37\1\uffff\1\43\14\uffff\1\60\54\uffff"+
        "\1\22\1\25\2\uffff\1\35\4\uffff\1\47\6\uffff\1\56\2\uffff\1\62\2"+
        "\uffff\1\66\14\uffff\1\105\1\uffff\1\107\1\110\22\uffff\1\31\1\uffff"+
        "\1\34\1\41\2\uffff\1\46\1\50\1\51\1\52\1\53\1\54\17\uffff\1\102"+
        "\2\uffff\1\106\11\uffff\1\27\3\uffff\1\55\4\uffff\1\65\6\uffff\1"+
        "\75\1\76\1\100\1\uffff\1\104\1\16\1\26\1\44\1\45\1\57\1\61\1\63"+
        "\1\64\1\67\1\70\6\uffff\1\72\3\uffff\1\71\2\uffff\1\103\5\uffff"+
        "\1\74\1\73";
    static final String DFA92_specialS =
        "\1\14\1\uffff\1\27\31\uffff\1\16\1\uffff\1\33\13\uffff\1\6\15\uffff"+
        "\1\30\2\uffff\1\10\1\uffff\1\20\1\5\103\uffff\1\0\1\1\33\uffff\1"+
        "\25\1\32\1\4\1\7\20\uffff\1\17\1\uffff\1\3\113\uffff\1\2\1\13\1"+
        "\15\1\24\3\uffff\1\23\122\uffff\1\12\1\22\1\31\105\uffff\1\11\1"+
        "\21\143\uffff\1\26\64\uffff}>";
    static final String[] DFA92_transitionS = {
            "\11\66\1\1\1\5\2\66\1\4\22\66\1\1\1\55\1\36\1\2\1\66\1\50\1"+
            "\37\1\34\1\53\1\61\1\64\1\56\1\45\1\40\1\32\1\46\1\31\11\30"+
            "\1\44\1\63\1\52\1\41\1\47\1\57\1\25\13\33\1\35\10\33\1\27\5"+
            "\33\1\54\1\26\1\62\1\43\1\24\1\66\1\6\1\7\1\10\1\3\1\11\1\12"+
            "\1\13\1\33\1\14\2\33\1\15\5\33\1\16\1\17\1\20\1\21\1\22\1\23"+
            "\3\33\1\51\1\42\1\60\1\65\uff81\66",
            "\1\71\26\uffff\1\71\2\uffff\1\70",
            "\1\73\26\uffff\1\73\2\uffff\1\72\100\uffff\1\74\1\76\3\uffff"+
            "\1\75\2\uffff\1\101\3\uffff\1\77\4\uffff\1\100",
            "\1\103\11\uffff\1\104",
            "\1\106",
            "",
            "\1\107",
            "\1\110",
            "\1\111\6\uffff\1\112\6\uffff\1\113",
            "\1\114\1\uffff\1\115\11\uffff\1\116",
            "\1\117\2\uffff\1\120",
            "\1\121",
            "\1\122\7\uffff\1\123",
            "\1\124",
            "\1\125",
            "\1\126\1\127\12\uffff\1\130\2\uffff\1\131",
            "\1\132",
            "\1\136\4\uffff\1\135\20\uffff\1\134\65\uffff\1\133",
            "\1\137",
            "\1\140",
            "\1\141\1\142\1\143\3\uffff\1\144\1\uffff\1\145\4\uffff\1\146"+
            "\4\uffff\1\147\1\150\14\uffff\1\151\1\uffff\1\152\5\uffff\1"+
            "\153\5\uffff\1\154\1\155\2\uffff\1\156\3\uffff\1\157",
            "",
            "\1\105\15\uffff\1\161\21\uffff\1\105",
            "\1\136\4\uffff\1\135",
            "\1\167\1\uffff\12\163\7\uffff\4\171\1\170\6\171\1\166\10\171"+
            "\1\164\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\171\1\170"+
            "\6\171\1\165\10\171\1\164\5\171",
            "\1\167\1\uffff\10\172\2\177\7\uffff\4\171\1\170\6\171\1\175"+
            "\10\171\1\173\2\171\1\176\2\171\1\uffff\1\171\2\uffff\1\171"+
            "\1\uffff\4\171\1\170\6\171\1\174\10\171\1\173\2\171\1\176\2"+
            "\171",
            "\12\u0080",
            "",
            "\12\135\1\uffff\34\135\1\uffff\uffd8\135",
            "\1\136\4\uffff\1\135",
            "\12\u0082\1\uffff\27\u0082\1\136\71\u0082\1\u0083\uffa3\u0082",
            "\1\u0084\26\uffff\1\u0085",
            "\1\u0088\17\uffff\1\u0089\1\u0087",
            "\1\u008b",
            "\1\u008d\76\uffff\1\u008e",
            "\1\u0090",
            "\1\u0092",
            "",
            "\1\u0096\4\uffff\1\u0096\15\uffff\1\u0095",
            "\1\u0098\1\u0099",
            "\1\72\2\uffff\1\u009b\1\u009c",
            "",
            "\12\u00a4\1\uffff\32\u00a4\1\u009f\24\u00a4\1\u00a0\1\u00a4"+
            "\1\u00a2\1\u00a1\1\uffff\uffc1\u00a4",
            "",
            "",
            "\1\u00a7",
            "\1\u00aa\21\uffff\1\u00a9",
            "",
            "",
            "",
            "",
            "",
            "\1\u00af",
            "",
            "",
            "",
            "\1\73\26\uffff\1\73\103\uffff\1\74\1\76\3\uffff\1\75\2\uffff"+
            "\1\101\3\uffff\1\77\4\uffff\1\100",
            "\1\71\26\uffff\1\71\2\uffff\1\70",
            "",
            "\1\73\26\uffff\1\73\103\uffff\1\74\1\76\3\uffff\1\75\2\uffff"+
            "\1\101\3\uffff\1\77\4\uffff\1\100",
            "",
            "\1\u00b3\7\uffff\1\u00b2",
            "\1\u00b5\1\uffff\1\u00b4\3\uffff\1\u00b6",
            "",
            "",
            "",
            "",
            "\1\u00b7",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\24\105\1\u00b8\5\105",
            "",
            "",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u00c6\7\uffff\1\u00c7",
            "\1\u00c8",
            "\1\u00c9\13\uffff\1\u00ca\1\u00cb",
            "\1\u00cc",
            "\1\u00cd\22\uffff\1\u00ce",
            "\1\u00cf\20\uffff\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3\11\uffff\1\u00d4",
            "\1\136",
            "",
            "",
            "\1\u00d5\2\uffff\1\u00d6",
            "\1\u00d7",
            "\1\u00d8\7\uffff\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7\6\uffff\1\u00e8",
            "",
            "",
            "",
            "\1\167\1\uffff\12\163\7\uffff\4\171\1\170\6\171\1\166\10\171"+
            "\1\164\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\171\1\170"+
            "\6\171\1\165\10\171\1\164\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00ea\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u00e9\16\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u00ec\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u00eb\10\171\1\u00ec\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00ed\10\171\1\u00ec"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u00ec\5"+
            "\171",
            "\1\171\1\uffff\12\u00ee\7\uffff\4\171\1\u00f0\1\u00f1\5\171"+
            "\1\u00f1\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\171\1"+
            "\u00f0\1\u00f1\5\171\1\u00f1\16\171",
            "\1\u00f2\1\uffff\1\u00f2\2\uffff\12\u00f3",
            "",
            "\1\167\1\uffff\10\172\2\177\7\uffff\4\171\1\170\6\171\1\175"+
            "\10\171\1\173\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\171"+
            "\1\170\6\171\1\174\10\171\1\173\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00f6\10\171\1\u00f5"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u00f4\10"+
            "\171\1\u00f5\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00f9\10\171\1\u00f8"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u00f7\10"+
            "\171\1\u00f8\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00fa\10\171\1\u00f8"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u00fb\10"+
            "\171\1\u00f8\5\171",
            "\1\u00fd\1\uffff\12\u00fe\7\uffff\4\u00ff\1\u00fc\1\u00ff\32"+
            "\uffff\4\u00ff\1\u00fc\1\u00ff",
            "\1\167\1\uffff\12\177\13\uffff\1\170\37\uffff\1\170",
            "\1\171\1\uffff\12\u00ee\7\uffff\4\171\1\u00f0\1\u00f1\5\171"+
            "\1\u00f1\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\171\1"+
            "\u00f0\1\u00f1\5\171\1\u00f1\16\171",
            "",
            "\12\u0082\1\uffff\27\u0082\1\u0100\71\u0082\1\u0083\uffa3\u0082",
            "\12\u00a4\1\uffff\27\u00a4\1\u0101\4\u00a4\1\u0104\10\u00a4"+
            "\10\u0103\7\u00a4\1\u0104\34\u00a4\1\u0104\4\u00a4\2\u0104\3"+
            "\u00a4\1\u0104\7\u00a4\1\u0104\3\u00a4\1\u0104\1\u00a4\1\u0104"+
            "\1\u00a4\1\u0104\1\u00a4\1\u0102\uff87\u00a4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0105",
            "",
            "",
            "",
            "",
            "",
            "\12\u00a4\1\uffff\ufff5\u00a4",
            "\12\u00a4\1\uffff\ufff5\u00a4",
            "\12\u00a4\1\uffff\ufff5\u00a4",
            "\12\u00a4\1\uffff\62\u00a4\1\u0108\uffc2\u00a4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u010a\11\uffff\1\u010b",
            "",
            "\1\u010d\11\uffff\1\u010e",
            "",
            "\1\u0110\7\uffff\1\u010f",
            "\1\u0111",
            "",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u011d",
            "",
            "\1\u011e",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b\5\uffff\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\13\171\1\u0142\16\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0143\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0144\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0144\5\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0144\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0144\5\171",
            "\1\171\1\uffff\12\u00ee\7\uffff\4\171\1\u00f0\1\u00f1\5\171"+
            "\1\u00f1\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\171\1"+
            "\u00f0\1\u00f1\5\171\1\u00f1\16\171",
            "",
            "\1\u0145\1\uffff\1\u0145\2\uffff\12\u0146",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\12\u00f3",
            "\1\171\1\uffff\12\u00f3\7\uffff\5\171\1\u0147\5\171\1\u0147"+
            "\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\5\171\1\u0147\5"+
            "\171\1\u0147\16\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00f9\10\171\1\u0149"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u0148\10"+
            "\171\1\u0149\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u014b\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u014a\16\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u014c\10\171\1\u0149"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u00fb\10"+
            "\171\1\u0149\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00f9\10\171\1\u014e"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u014d\10"+
            "\171\1\u014e\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0150\10\171\1\u00f5"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u014f\10"+
            "\171\1\u00f5\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0151\10\171\1\u0152"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u0152\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0153\10\171\1\u014e"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u00fb\10"+
            "\171\1\u014e\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0152\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u0154\10\171\1\u0152\5"+
            "\171",
            "\1\171\1\uffff\1\171\1\u0158\1\uffff\12\u00fe\7\uffff\4\u00ff"+
            "\1\u00fc\1\u00ff\5\171\1\u0157\3\171\1\u0159\4\171\1\u0155\5"+
            "\171\1\uffff\1\171\2\uffff\1\171\1\uffff\4\u00ff\1\u00fc\1\u00ff"+
            "\5\171\1\u0156\3\171\1\u0159\4\171\1\u0155\5\171",
            "\12\u015a",
            "\1\u0158\1\uffff\12\u00fe\7\uffff\4\u00ff\1\u00fc\1\u00ff\5"+
            "\171\1\u0157\3\171\1\u0159\4\171\1\u0155\5\171\1\uffff\1\171"+
            "\2\uffff\1\171\1\uffff\4\u00ff\1\u00fc\1\u00ff\5\171\1\u0156"+
            "\3\171\1\u0159\4\171\1\u0155\5\171",
            "\1\u0158\1\uffff\12\u00fe\7\uffff\4\u00ff\1\u00fc\1\u00ff\5"+
            "\171\1\u0157\3\171\1\u0159\4\171\1\u0155\5\171\1\uffff\1\171"+
            "\2\uffff\1\171\1\uffff\4\u00ff\1\u00fc\1\u00ff\5\171\1\u0156"+
            "\3\171\1\u0159\4\171\1\u0155\5\171",
            "\1\uffff",
            "\12\136\1\uffff\ufff5\136",
            "\12\u00a4\1\uffff\45\u00a4\12\u015c\7\u00a4\6\u015c\32\u00a4"+
            "\6\u015c\uff99\u00a4",
            "\12\u0082\1\uffff\27\u0082\1\u0100\15\u0082\10\u015d\44\u0082"+
            "\1\u0083\uffa3\u0082",
            "\12\u0082\1\uffff\27\u0082\1\u0100\71\u0082\1\u0083\uffa3\u0082",
            "",
            "",
            "",
            "\12\u00a4\1\uffff\ufff5\u00a4",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u015f",
            "\1\u0160",
            "\1\u0161",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u0163",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u0166",
            "\1\u0167",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u016a",
            "\1\u016b",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u016d",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\u0176",
            "\1\u0177",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180",
            "\1\u0181",
            "\1\u0182",
            "\1\u0183",
            "\1\u0184",
            "\1\u0185",
            "\1\u0186",
            "\1\u0187\17\uffff\1\u0188",
            "\1\u0189",
            "\1\u018a",
            "\1\u018b",
            "\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\12\u0146",
            "\1\171\1\uffff\12\u0146\7\uffff\5\171\1\u00f1\5\171\1\u00f1"+
            "\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\5\171\1\u00f1\5"+
            "\171\1\u00f1\16\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u00f9\10\171\1\u0191"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u014d\10"+
            "\171\1\u0191\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u014b\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u014a\16\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\13\171\1\u0192\16\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0193\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0153\10\171\1\u0191"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u00fb\10"+
            "\171\1\u0191\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0194\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u0154\10\171\1\u0194\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0150\10\171\1\u00f5"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u014f\10"+
            "\171\1\u00f5\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0152\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u0195\10\171\1\u0152\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0196\10\171\1\u0152"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u0152\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0197\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0197\5\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0151\10\171\1\u0194"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u0194\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0197\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0197\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u019a\10\171\1\u0199"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u0198\10"+
            "\171\1\u0199\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u019d\10\171\1\u019c"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u019b\10"+
            "\171\1\u019c\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u019e\10\171\1\u019c"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u019f\10"+
            "\171\1\u019c\5\171",
            "\12\u015a\26\uffff\1\u01a0\37\uffff\1\u01a0",
            "\1\u01a1\1\uffff\1\u01a1\2\uffff\12\u01a2",
            "\12\u015a\26\uffff\1\u01a0\37\uffff\1\u01a0",
            "\1\uffff",
            "\12\u0082\1\uffff\27\u0082\1\u0100\15\u0082\12\u01a3\7\u0082"+
            "\6\u01a3\25\u0082\1\u0083\4\u0082\6\u01a3\uff99\u0082",
            "\12\u0082\1\uffff\27\u0082\1\u0100\15\u0082\10\u01a4\44\u0082"+
            "\1\u0083\uffa3\u0082",
            "",
            "\1\u01a5",
            "\1\u01a6",
            "\1\u01a7",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01aa",
            "",
            "",
            "\1\u01ab",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\1\u01ad",
            "",
            "\1\u01ae",
            "\1\u01af",
            "\1\u01b0",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01b2",
            "\1\u01b3",
            "\1\u01b4",
            "\1\u01b5",
            "\1\u01b6",
            "\1\u01b7",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01b9",
            "",
            "\1\u01ba",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01bc",
            "\1\u01bd",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\u01c4",
            "\1\u01c5",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u01c8",
            "\1\u01c9",
            "\1\u01ca",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01cc",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u014b\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u014a\16\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0197\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0197\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0197\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0197\5\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u019d\10\171\1\u01d0"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u01cf\10"+
            "\171\1\u01d0\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d2\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d1\16\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d3\10\171\1\u01d0"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u019f\10"+
            "\171\1\u01d0\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u019d\10\171\1\u01d5"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d4\10"+
            "\171\1\u01d5\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d7\10\171\1\u0199"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d6\10"+
            "\171\1\u0199\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d8\10\171\1\u01d9"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u01d9\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01da\10\171\1\u01d5"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u019f\10"+
            "\171\1\u01d5\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u01d9\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u01db\10\171\1\u01d9\5"+
            "\171",
            "\1\u01dc\1\uffff\1\u01dc\2\uffff\12\u01dd",
            "\12\u01a2",
            "\1\171\1\uffff\12\u01a2\7\uffff\5\171\1\u01de\5\171\1\u01de"+
            "\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\5\171\1\u01de\5"+
            "\171\1\u01de\16\171",
            "\12\u0082\1\uffff\27\u0082\1\u0100\15\u0082\12\u01a3\7\u0082"+
            "\6\u01a3\25\u0082\1\u0083\4\u0082\6\u01a3\uff99\u0082",
            "\12\u0082\1\uffff\27\u0082\1\u0100\71\u0082\1\u0083\uffa3\u0082",
            "\1\u01df",
            "\1\u01e0",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "",
            "\1\u01e2",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01e5",
            "\1\u01e6",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01ed",
            "",
            "\1\u01ee",
            "\1\u01ef",
            "",
            "\1\u01f0\15\uffff\1\u01f1",
            "\1\u01f2",
            "",
            "\1\u01f3",
            "\1\u01f4",
            "\1\u01f5",
            "\1\u01f6",
            "\1\u01f7",
            "\1\u01f8",
            "\1\u01f9",
            "\1\u01fa",
            "\1\u01fb",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u01fd",
            "\1\u01fe",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u019d\10\171\1\u0200"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d4\10"+
            "\171\1\u0200\5\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d2\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d1\16\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\13\171\1\u0201\16\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0202\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01da\10\171\1\u0200"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u019f\10"+
            "\171\1\u0200\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0203\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u01db\10\171\1\u0203\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d7\10\171\1\u0199"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d6\10"+
            "\171\1\u0199\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u01d9\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u0204\10\171\1\u01d9\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u0205\10\171\1\u01d9"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u01d9\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0206\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0206\5\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d8\10\171\1\u0203"+
            "\5\171\1\uffff\1\171\2\uffff\1\171\1\uffff\24\171\1\u0203\5"+
            "\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0206\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0206\5\171",
            "\12\u01dd",
            "\1\171\1\uffff\12\u01dd\7\uffff\5\171\1\u0207\5\171\1\u0207"+
            "\16\171\1\uffff\1\171\2\uffff\1\171\1\uffff\5\171\1\u0207\5"+
            "\171\1\u0207\16\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\1\u020a",
            "",
            "",
            "\1\u020b",
            "\1\u020c",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u020e",
            "\1\u020f",
            "\1\u0210",
            "\1\u0211",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u0213",
            "\1\u0214",
            "\1\u0215",
            "\1\u0216",
            "\1\u0217",
            "\1\u0218",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\1\u021c",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\1\171\1\uffff\12\171\7\uffff\13\171\1\u01d2\16\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\13\171\1\u01d1\16\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0206\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0206\5\171",
            "\1\171\1\uffff\12\171\7\uffff\24\171\1\u0206\5\171\1\uffff"+
            "\1\171\2\uffff\1\171\1\uffff\24\171\1\u0206\5\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\171\1\uffff\12\171\7\uffff\32\171\1\uffff\1\171\2\uffff"+
            "\1\171\1\uffff\32\171",
            "\1\uffff",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u0228",
            "\1\u0229",
            "\1\u022a",
            "\1\u022b",
            "",
            "",
            "",
            "\1\u022c",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u022d",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\1\u022f",
            "\1\u0230",
            "\1\u0231",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\1\u0233",
            "\1\u0234",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            "\1\u0236",
            "\1\u0237",
            "",
            "\1\u0238",
            "\1\u0239",
            "\1\u023a",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "\12\105\7\uffff\32\105\1\uffff\1\105\2\uffff\1\105\1\uffff"+
            "\32\105",
            "",
            ""
    };

    static final short[] DFA92_eot = DFA.unpackEncodedString(DFA92_eotS);
    static final short[] DFA92_eof = DFA.unpackEncodedString(DFA92_eofS);
    static final char[] DFA92_min = DFA.unpackEncodedStringToUnsignedChars(DFA92_minS);
    static final char[] DFA92_max = DFA.unpackEncodedStringToUnsignedChars(DFA92_maxS);
    static final short[] DFA92_accept = DFA.unpackEncodedString(DFA92_acceptS);
    static final short[] DFA92_special = DFA.unpackEncodedString(DFA92_specialS);
    static final short[][] DFA92_transition;

    static {
        int numStates = DFA92_transitionS.length;
        DFA92_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA92_transition[i] = DFA.unpackEncodedString(DFA92_transitionS[i]);
        }
    }

    class DFA92 extends DFA {

        public DFA92(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 92;
            this.eot = DFA92_eot;
            this.eof = DFA92_eof;
            this.min = DFA92_min;
            this.max = DFA92_max;
            this.accept = DFA92_accept;
            this.special = DFA92_special;
            this.transition = DFA92_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( PDEFINE | PINCLUDE | PIFDEF | PIFNDEF | PIF | PENDIF | PELIF | PELSE | PRAGMA | PERROR | PUNDEF | PLINE | HASH | DEFINED | NEWLINE | WS | AUTO | BREAK | CASE | CHAR | CONST | CONTINUE | DEFAULT | DO | DOUBLE | ELSE | ENUM | EXTERN | FLOAT | FOR | GOTO | IF | INLINE | INT | LONG | REGISTER | RESTRICT | RETURN | SHORT | SIGNED | SIZEOF | STATIC | STRUCT | SWITCH | TYPEDEF | UNION | UNSIGNED | VOID | VOLATILE | WHILE | ALIGNAS | ALIGNOF | ATOMIC | BOOL | COMPLEX | GENERIC | IMAGINARY | NORETURN | STATICASSERT | THREADLOCAL | ASSERT | ASSUME | AT | CHOOSE | COLLECTIVE | INPUT | INVARIANT | OUTPUT | PROC | SPAWN | WAIT | WHEN | IDENTIFIER | INTEGER_CONSTANT | FLOATING_CONSTANT | PP_NUMBER | CHARACTER_CONSTANT | STRING_LITERAL | DOT | AMPERSAND | AND | ARROW | ASSIGN | BITANDEQ | BITOR | BITOREQ | BITXOR | BITXOREQ | COLON | COMMA | DIV | DIVEQ | EQUALS | GT | GTE | HASHHASH | LCURLY | LPAREN | LSQUARE | LT | LTE | MINUSMINUS | MOD | MODEQ | NEQ | NOT | OR | PLUS | PLUSEQ | PLUSPLUS | QMARK | RCURLY | RPAREN | RSQUARE | SEMI | SHIFTLEFT | SHIFTLEFTEQ | SHIFTRIGHT | SHIFTRIGHTEQ | STAR | STAREQ | SUB | SUBEQ | TILDE | HEADER_NAME | COMMENT | OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA92_130 = input.LA(1);

                        s = -1;
                        if ( (LA92_130=='\"') ) {s = 256;}

                        else if ( ((LA92_130 >= '\u0000' && LA92_130 <= '\t')||(LA92_130 >= '\u000B' && LA92_130 <= '!')||(LA92_130 >= '#' && LA92_130 <= '[')||(LA92_130 >= ']' && LA92_130 <= '\uFFFF')) ) {s = 130;}

                        else if ( (LA92_130=='\\') ) {s = 131;}

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA92_131 = input.LA(1);

                         
                        int index92_131 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_131=='\"') ) {s = 257;}

                        else if ( (LA92_131=='x') ) {s = 258;}

                        else if ( ((LA92_131 >= '0' && LA92_131 <= '7')) ) {s = 259;}

                        else if ( (LA92_131=='\''||LA92_131=='?'||LA92_131=='\\'||(LA92_131 >= 'a' && LA92_131 <= 'b')||LA92_131=='f'||LA92_131=='n'||LA92_131=='r'||LA92_131=='t'||LA92_131=='v') ) {s = 260;}

                        else if ( ((LA92_131 >= '\u0000' && LA92_131 <= '\t')||(LA92_131 >= '\u000B' && LA92_131 <= '!')||(LA92_131 >= '#' && LA92_131 <= '&')||(LA92_131 >= '(' && LA92_131 <= '/')||(LA92_131 >= '8' && LA92_131 <= '>')||(LA92_131 >= '@' && LA92_131 <= '[')||(LA92_131 >= ']' && LA92_131 <= '`')||(LA92_131 >= 'c' && LA92_131 <= 'e')||(LA92_131 >= 'g' && LA92_131 <= 'm')||(LA92_131 >= 'o' && LA92_131 <= 'q')||LA92_131=='s'||LA92_131=='u'||LA92_131=='w'||(LA92_131 >= 'y' && LA92_131 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                         
                        input.seek(index92_131);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA92_257 = input.LA(1);

                         
                        int index92_257 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA92_257 >= '\u0000' && LA92_257 <= '\t')||(LA92_257 >= '\u000B' && LA92_257 <= '\uFFFF')) ) {s = 94;}

                        else s = 164;

                         
                        input.seek(index92_257);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA92_181 = input.LA(1);

                         
                        int index92_181 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_181=='i') && ((atLineStart))) {s = 269;}

                        else if ( (LA92_181=='s') && ((atLineStart))) {s = 270;}

                         
                        input.seek(index92_181);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA92_161 = input.LA(1);

                         
                        int index92_161 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA92_161 >= '\u0000' && LA92_161 <= '\t')||(LA92_161 >= '\u000B' && LA92_161 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                        else s = 263;

                         
                        input.seek(index92_161);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA92_62 = input.LA(1);

                         
                        int index92_62 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_62=='n') && ((atLineStart))) {s = 180;}

                        else if ( (LA92_62=='l') && ((atLineStart))) {s = 181;}

                        else if ( (LA92_62=='r') && ((atLineStart))) {s = 182;}

                         
                        input.seek(index92_62);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA92_42 = input.LA(1);

                         
                        int index92_42 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_42=='%') ) {s = 159;}

                        else if ( (LA92_42==':') ) {s = 160;}

                        else if ( (LA92_42=='=') ) {s = 161;}

                        else if ( (LA92_42=='<') ) {s = 162;}

                        else if ( ((LA92_42 >= '\u0000' && LA92_42 <= '\t')||(LA92_42 >= '\u000B' && LA92_42 <= '$')||(LA92_42 >= '&' && LA92_42 <= '9')||LA92_42==';'||(LA92_42 >= '?' && LA92_42 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                        else s = 163;

                         
                        input.seek(index92_42);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA92_162 = input.LA(1);

                         
                        int index92_162 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_162=='=') ) {s = 264;}

                        else if ( ((LA92_162 >= '\u0000' && LA92_162 <= '\t')||(LA92_162 >= '\u000B' && LA92_162 <= '<')||(LA92_162 >= '>' && LA92_162 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                        else s = 265;

                         
                        input.seek(index92_162);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA92_59 = input.LA(1);

                         
                        int index92_59 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_59=='d') && ((atLineStart))) {s = 60;}

                        else if ( (LA92_59=='\t'||LA92_59==' ') ) {s = 59;}

                        else if ( (LA92_59=='i') && ((atLineStart))) {s = 61;}

                        else if ( (LA92_59=='e') && ((atLineStart))) {s = 62;}

                        else if ( (LA92_59=='p') && ((atLineStart))) {s = 63;}

                        else if ( (LA92_59=='u') && ((atLineStart))) {s = 64;}

                        else if ( (LA92_59=='l') && ((atLineStart))) {s = 65;}

                        else s = 66;

                         
                        input.seek(index92_59);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA92_419 = input.LA(1);

                        s = -1;
                        if ( (LA92_419=='\"') ) {s = 256;}

                        else if ( ((LA92_419 >= '0' && LA92_419 <= '9')||(LA92_419 >= 'A' && LA92_419 <= 'F')||(LA92_419 >= 'a' && LA92_419 <= 'f')) ) {s = 419;}

                        else if ( (LA92_419=='\\') ) {s = 131;}

                        else if ( ((LA92_419 >= '\u0000' && LA92_419 <= '\t')||(LA92_419 >= '\u000B' && LA92_419 <= '!')||(LA92_419 >= '#' && LA92_419 <= '/')||(LA92_419 >= ':' && LA92_419 <= '@')||(LA92_419 >= 'G' && LA92_419 <= '[')||(LA92_419 >= ']' && LA92_419 <= '`')||(LA92_419 >= 'g' && LA92_419 <= '\uFFFF')) ) {s = 130;}

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA92_347 = input.LA(1);

                         
                        int index92_347 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((inInclude)))) ) {s = 94;}

                        else if ( ((inInclude)) ) {s = 164;}

                         
                        input.seek(index92_347);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA92_258 = input.LA(1);

                         
                        int index92_258 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA92_258 >= '0' && LA92_258 <= '9')||(LA92_258 >= 'A' && LA92_258 <= 'F')||(LA92_258 >= 'a' && LA92_258 <= 'f')) ) {s = 348;}

                        else if ( ((LA92_258 >= '\u0000' && LA92_258 <= '\t')||(LA92_258 >= '\u000B' && LA92_258 <= '/')||(LA92_258 >= ':' && LA92_258 <= '@')||(LA92_258 >= 'G' && LA92_258 <= '`')||(LA92_258 >= 'g' && LA92_258 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                         
                        input.seek(index92_258);

                        if ( s>=0 ) return s;
                        break;

                    case 12 : 
                        int LA92_0 = input.LA(1);

                        s = -1;
                        if ( (LA92_0=='\t'||LA92_0==' ') ) {s = 1;}

                        else if ( (LA92_0=='#') ) {s = 2;}

                        else if ( (LA92_0=='d') ) {s = 3;}

                        else if ( (LA92_0=='\r') ) {s = 4;}

                        else if ( (LA92_0=='\n') ) {s = 5;}

                        else if ( (LA92_0=='a') ) {s = 6;}

                        else if ( (LA92_0=='b') ) {s = 7;}

                        else if ( (LA92_0=='c') ) {s = 8;}

                        else if ( (LA92_0=='e') ) {s = 9;}

                        else if ( (LA92_0=='f') ) {s = 10;}

                        else if ( (LA92_0=='g') ) {s = 11;}

                        else if ( (LA92_0=='i') ) {s = 12;}

                        else if ( (LA92_0=='l') ) {s = 13;}

                        else if ( (LA92_0=='r') ) {s = 14;}

                        else if ( (LA92_0=='s') ) {s = 15;}

                        else if ( (LA92_0=='t') ) {s = 16;}

                        else if ( (LA92_0=='u') ) {s = 17;}

                        else if ( (LA92_0=='v') ) {s = 18;}

                        else if ( (LA92_0=='w') ) {s = 19;}

                        else if ( (LA92_0=='_') ) {s = 20;}

                        else if ( (LA92_0=='@') ) {s = 21;}

                        else if ( (LA92_0=='\\') ) {s = 22;}

                        else if ( (LA92_0=='U') ) {s = 23;}

                        else if ( ((LA92_0 >= '1' && LA92_0 <= '9')) ) {s = 24;}

                        else if ( (LA92_0=='0') ) {s = 25;}

                        else if ( (LA92_0=='.') ) {s = 26;}

                        else if ( ((LA92_0 >= 'A' && LA92_0 <= 'K')||(LA92_0 >= 'M' && LA92_0 <= 'T')||(LA92_0 >= 'V' && LA92_0 <= 'Z')||LA92_0=='h'||(LA92_0 >= 'j' && LA92_0 <= 'k')||(LA92_0 >= 'm' && LA92_0 <= 'q')||(LA92_0 >= 'x' && LA92_0 <= 'z')) ) {s = 27;}

                        else if ( (LA92_0=='\'') ) {s = 28;}

                        else if ( (LA92_0=='L') ) {s = 29;}

                        else if ( (LA92_0=='\"') ) {s = 30;}

                        else if ( (LA92_0=='&') ) {s = 31;}

                        else if ( (LA92_0=='-') ) {s = 32;}

                        else if ( (LA92_0=='=') ) {s = 33;}

                        else if ( (LA92_0=='|') ) {s = 34;}

                        else if ( (LA92_0=='^') ) {s = 35;}

                        else if ( (LA92_0==':') ) {s = 36;}

                        else if ( (LA92_0==',') ) {s = 37;}

                        else if ( (LA92_0=='/') ) {s = 38;}

                        else if ( (LA92_0=='>') ) {s = 39;}

                        else if ( (LA92_0=='%') ) {s = 40;}

                        else if ( (LA92_0=='{') ) {s = 41;}

                        else if ( (LA92_0=='<') ) {s = 42;}

                        else if ( (LA92_0=='(') ) {s = 43;}

                        else if ( (LA92_0=='[') ) {s = 44;}

                        else if ( (LA92_0=='!') ) {s = 45;}

                        else if ( (LA92_0=='+') ) {s = 46;}

                        else if ( (LA92_0=='?') ) {s = 47;}

                        else if ( (LA92_0=='}') ) {s = 48;}

                        else if ( (LA92_0==')') ) {s = 49;}

                        else if ( (LA92_0==']') ) {s = 50;}

                        else if ( (LA92_0==';') ) {s = 51;}

                        else if ( (LA92_0=='*') ) {s = 52;}

                        else if ( (LA92_0=='~') ) {s = 53;}

                        else if ( ((LA92_0 >= '\u0000' && LA92_0 <= '\b')||(LA92_0 >= '\u000B' && LA92_0 <= '\f')||(LA92_0 >= '\u000E' && LA92_0 <= '\u001F')||LA92_0=='$'||LA92_0=='`'||(LA92_0 >= '\u007F' && LA92_0 <= '\uFFFF')) ) {s = 54;}

                        if ( s>=0 ) return s;
                        break;

                    case 13 : 
                        int LA92_259 = input.LA(1);

                        s = -1;
                        if ( ((LA92_259 >= '0' && LA92_259 <= '7')) ) {s = 349;}

                        else if ( (LA92_259=='\"') ) {s = 256;}

                        else if ( ((LA92_259 >= '\u0000' && LA92_259 <= '\t')||(LA92_259 >= '\u000B' && LA92_259 <= '!')||(LA92_259 >= '#' && LA92_259 <= '/')||(LA92_259 >= '8' && LA92_259 <= '[')||(LA92_259 >= ']' && LA92_259 <= '\uFFFF')) ) {s = 130;}

                        else if ( (LA92_259=='\\') ) {s = 131;}

                        if ( s>=0 ) return s;
                        break;

                    case 14 : 
                        int LA92_28 = input.LA(1);

                        s = -1;
                        if ( ((LA92_28 >= '\u0000' && LA92_28 <= '\t')||(LA92_28 >= '\u000B' && LA92_28 <= '&')||(LA92_28 >= '(' && LA92_28 <= '\uFFFF')) ) {s = 93;}

                        else s = 54;

                        if ( s>=0 ) return s;
                        break;

                    case 15 : 
                        int LA92_179 = input.LA(1);

                         
                        int index92_179 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_179=='d') && ((atLineStart))) {s = 266;}

                        else if ( (LA92_179=='n') && ((atLineStart))) {s = 267;}

                        else s = 268;

                         
                        input.seek(index92_179);

                        if ( s>=0 ) return s;
                        break;

                    case 16 : 
                        int LA92_61 = input.LA(1);

                         
                        int index92_61 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_61=='n') && ((atLineStart))) {s = 178;}

                        else if ( (LA92_61=='f') && ((atLineStart))) {s = 179;}

                         
                        input.seek(index92_61);

                        if ( s>=0 ) return s;
                        break;

                    case 17 : 
                        int LA92_420 = input.LA(1);

                        s = -1;
                        if ( (LA92_420=='\"') ) {s = 256;}

                        else if ( ((LA92_420 >= '\u0000' && LA92_420 <= '\t')||(LA92_420 >= '\u000B' && LA92_420 <= '!')||(LA92_420 >= '#' && LA92_420 <= '[')||(LA92_420 >= ']' && LA92_420 <= '\uFFFF')) ) {s = 130;}

                        else if ( (LA92_420=='\\') ) {s = 131;}

                        if ( s>=0 ) return s;
                        break;

                    case 18 : 
                        int LA92_348 = input.LA(1);

                        s = -1;
                        if ( (LA92_348=='\"') ) {s = 256;}

                        else if ( ((LA92_348 >= '0' && LA92_348 <= '9')||(LA92_348 >= 'A' && LA92_348 <= 'F')||(LA92_348 >= 'a' && LA92_348 <= 'f')) ) {s = 419;}

                        else if ( (LA92_348=='\\') ) {s = 131;}

                        else if ( ((LA92_348 >= '\u0000' && LA92_348 <= '\t')||(LA92_348 >= '\u000B' && LA92_348 <= '!')||(LA92_348 >= '#' && LA92_348 <= '/')||(LA92_348 >= ':' && LA92_348 <= '@')||(LA92_348 >= 'G' && LA92_348 <= '[')||(LA92_348 >= ']' && LA92_348 <= '`')||(LA92_348 >= 'g' && LA92_348 <= '\uFFFF')) ) {s = 130;}

                        if ( s>=0 ) return s;
                        break;

                    case 19 : 
                        int LA92_264 = input.LA(1);

                         
                        int index92_264 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA92_264 >= '\u0000' && LA92_264 <= '\t')||(LA92_264 >= '\u000B' && LA92_264 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                        else s = 350;

                         
                        input.seek(index92_264);

                        if ( s>=0 ) return s;
                        break;

                    case 20 : 
                        int LA92_260 = input.LA(1);

                        s = -1;
                        if ( (LA92_260=='\"') ) {s = 256;}

                        else if ( ((LA92_260 >= '\u0000' && LA92_260 <= '\t')||(LA92_260 >= '\u000B' && LA92_260 <= '!')||(LA92_260 >= '#' && LA92_260 <= '[')||(LA92_260 >= ']' && LA92_260 <= '\uFFFF')) ) {s = 130;}

                        else if ( (LA92_260=='\\') ) {s = 131;}

                        if ( s>=0 ) return s;
                        break;

                    case 21 : 
                        int LA92_159 = input.LA(1);

                         
                        int index92_159 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA92_159 >= '\u0000' && LA92_159 <= '\t')||(LA92_159 >= '\u000B' && LA92_159 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                        else s = 158;

                         
                        input.seek(index92_159);

                        if ( s>=0 ) return s;
                        break;

                    case 22 : 
                        int LA92_520 = input.LA(1);

                         
                        int index92_520 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((inCondition)) ) {s = 542;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index92_520);

                        if ( s>=0 ) return s;
                        break;

                    case 23 : 
                        int LA92_2 = input.LA(1);

                         
                        int index92_2 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_2=='#') ) {s = 58;}

                        else if ( (LA92_2=='\t'||LA92_2==' ') ) {s = 59;}

                        else if ( (LA92_2=='d') && ((atLineStart))) {s = 60;}

                        else if ( (LA92_2=='i') && ((atLineStart))) {s = 61;}

                        else if ( (LA92_2=='e') && ((atLineStart))) {s = 62;}

                        else if ( (LA92_2=='p') && ((atLineStart))) {s = 63;}

                        else if ( (LA92_2=='u') && ((atLineStart))) {s = 64;}

                        else if ( (LA92_2=='l') && ((atLineStart))) {s = 65;}

                        else s = 66;

                         
                        input.seek(index92_2);

                        if ( s>=0 ) return s;
                        break;

                    case 24 : 
                        int LA92_56 = input.LA(1);

                         
                        int index92_56 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA92_56=='\t'||LA92_56==' ') ) {s = 59;}

                        else if ( (LA92_56=='d') && ((atLineStart))) {s = 60;}

                        else if ( (LA92_56=='i') && ((atLineStart))) {s = 61;}

                        else if ( (LA92_56=='e') && ((atLineStart))) {s = 62;}

                        else if ( (LA92_56=='p') && ((atLineStart))) {s = 63;}

                        else if ( (LA92_56=='u') && ((atLineStart))) {s = 64;}

                        else if ( (LA92_56=='l') && ((atLineStart))) {s = 65;}

                        else s = 66;

                         
                        input.seek(index92_56);

                        if ( s>=0 ) return s;
                        break;

                    case 25 : 
                        int LA92_349 = input.LA(1);

                        s = -1;
                        if ( ((LA92_349 >= '0' && LA92_349 <= '7')) ) {s = 420;}

                        else if ( (LA92_349=='\"') ) {s = 256;}

                        else if ( ((LA92_349 >= '\u0000' && LA92_349 <= '\t')||(LA92_349 >= '\u000B' && LA92_349 <= '!')||(LA92_349 >= '#' && LA92_349 <= '/')||(LA92_349 >= '8' && LA92_349 <= '[')||(LA92_349 >= ']' && LA92_349 <= '\uFFFF')) ) {s = 130;}

                        else if ( (LA92_349=='\\') ) {s = 131;}

                        if ( s>=0 ) return s;
                        break;

                    case 26 : 
                        int LA92_160 = input.LA(1);

                         
                        int index92_160 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA92_160 >= '\u0000' && LA92_160 <= '\t')||(LA92_160 >= '\u000B' && LA92_160 <= '\uFFFF')) && ((inInclude))) {s = 164;}

                        else s = 166;

                         
                        input.seek(index92_160);

                        if ( s>=0 ) return s;
                        break;

                    case 27 : 
                        int LA92_30 = input.LA(1);

                        s = -1;
                        if ( ((LA92_30 >= '\u0000' && LA92_30 <= '\t')||(LA92_30 >= '\u000B' && LA92_30 <= '!')||(LA92_30 >= '#' && LA92_30 <= '[')||(LA92_30 >= ']' && LA92_30 <= '\uFFFF')) ) {s = 130;}

                        else if ( (LA92_30=='\\') ) {s = 131;}

                        else if ( (LA92_30=='\"') ) {s = 94;}

                        else s = 54;

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 92, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}