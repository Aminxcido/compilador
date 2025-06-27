package org.xtext.example.mathlang.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mathlang.services.MathLangGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMathLangParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'$'", "'@'", "'='", "'+'", "'-'", "'*'", "'/'", "'^'", "'('", "')'", "'sin'", "'cos'", "'tan'", "'sqrt'", "'log'", "'ln'", "'frac'", "','"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalMathLangParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMathLangParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMathLangParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMathLang.g"; }



     	private MathLangGrammarAccess grammarAccess;

        public InternalMathLangParser(TokenStream input, MathLangGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected MathLangGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalMathLang.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalMathLang.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalMathLang.g:65:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMathLang.g:71:1: ruleModel returns [EObject current=null] : ( (lv_expressions_0_0= ruleDelimitedExpr ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_expressions_0_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:77:2: ( ( (lv_expressions_0_0= ruleDelimitedExpr ) )* )
            // InternalMathLang.g:78:2: ( (lv_expressions_0_0= ruleDelimitedExpr ) )*
            {
            // InternalMathLang.g:78:2: ( (lv_expressions_0_0= ruleDelimitedExpr ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMathLang.g:79:3: (lv_expressions_0_0= ruleDelimitedExpr )
            	    {
            	    // InternalMathLang.g:79:3: (lv_expressions_0_0= ruleDelimitedExpr )
            	    // InternalMathLang.g:80:4: lv_expressions_0_0= ruleDelimitedExpr
            	    {

            	    				newCompositeNode(grammarAccess.getModelAccess().getExpressionsDelimitedExprParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_expressions_0_0=ruleDelimitedExpr();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getModelRule());
            	    				}
            	    				add(
            	    					current,
            	    					"expressions",
            	    					lv_expressions_0_0,
            	    					"org.xtext.example.mathlang.MathLang.DelimitedExpr");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleDelimitedExpr"
    // InternalMathLang.g:100:1: entryRuleDelimitedExpr returns [EObject current=null] : iv_ruleDelimitedExpr= ruleDelimitedExpr EOF ;
    public final EObject entryRuleDelimitedExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDelimitedExpr = null;


        try {
            // InternalMathLang.g:100:54: (iv_ruleDelimitedExpr= ruleDelimitedExpr EOF )
            // InternalMathLang.g:101:2: iv_ruleDelimitedExpr= ruleDelimitedExpr EOF
            {
             newCompositeNode(grammarAccess.getDelimitedExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDelimitedExpr=ruleDelimitedExpr();

            state._fsp--;

             current =iv_ruleDelimitedExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDelimitedExpr"


    // $ANTLR start "ruleDelimitedExpr"
    // InternalMathLang.g:107:1: ruleDelimitedExpr returns [EObject current=null] : (otherlv_0= '$' ( (lv_expr_1_0= ruleExpression ) ) otherlv_2= '@' ) ;
    public final EObject ruleDelimitedExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_expr_1_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:113:2: ( (otherlv_0= '$' ( (lv_expr_1_0= ruleExpression ) ) otherlv_2= '@' ) )
            // InternalMathLang.g:114:2: (otherlv_0= '$' ( (lv_expr_1_0= ruleExpression ) ) otherlv_2= '@' )
            {
            // InternalMathLang.g:114:2: (otherlv_0= '$' ( (lv_expr_1_0= ruleExpression ) ) otherlv_2= '@' )
            // InternalMathLang.g:115:3: otherlv_0= '$' ( (lv_expr_1_0= ruleExpression ) ) otherlv_2= '@'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getDelimitedExprAccess().getDollarSignKeyword_0());
            		
            // InternalMathLang.g:119:3: ( (lv_expr_1_0= ruleExpression ) )
            // InternalMathLang.g:120:4: (lv_expr_1_0= ruleExpression )
            {
            // InternalMathLang.g:120:4: (lv_expr_1_0= ruleExpression )
            // InternalMathLang.g:121:5: lv_expr_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getDelimitedExprAccess().getExprExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_5);
            lv_expr_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDelimitedExprRule());
            					}
            					set(
            						current,
            						"expr",
            						lv_expr_1_0,
            						"org.xtext.example.mathlang.MathLang.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getDelimitedExprAccess().getCommercialAtKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDelimitedExpr"


    // $ANTLR start "entryRuleExpression"
    // InternalMathLang.g:146:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalMathLang.g:146:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalMathLang.g:147:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalMathLang.g:153:1: ruleExpression returns [EObject current=null] : (this_SimpleExpression_0= ruleSimpleExpression | this_Assignment_1= ruleAssignment ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleExpression_0 = null;

        EObject this_Assignment_1 = null;



        	enterRule();

        try {
            // InternalMathLang.g:159:2: ( (this_SimpleExpression_0= ruleSimpleExpression | this_Assignment_1= ruleAssignment ) )
            // InternalMathLang.g:160:2: (this_SimpleExpression_0= ruleSimpleExpression | this_Assignment_1= ruleAssignment )
            {
            // InternalMathLang.g:160:2: (this_SimpleExpression_0= ruleSimpleExpression | this_Assignment_1= ruleAssignment )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_INT||LA2_0==15||LA2_0==19||(LA2_0>=21 && LA2_0<=27)) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_ID) ) {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==13) ) {
                    alt2=2;
                }
                else if ( (LA2_2==EOF||LA2_2==12||(LA2_2>=14 && LA2_2<=18)||LA2_2==20||LA2_2==28) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalMathLang.g:161:3: this_SimpleExpression_0= ruleSimpleExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getSimpleExpressionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SimpleExpression_0=ruleSimpleExpression();

                    state._fsp--;


                    			current = this_SimpleExpression_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMathLang.g:170:3: this_Assignment_1= ruleAssignment
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getAssignmentParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Assignment_1=ruleAssignment();

                    state._fsp--;


                    			current = this_Assignment_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleAssignment"
    // InternalMathLang.g:182:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalMathLang.g:182:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalMathLang.g:183:2: iv_ruleAssignment= ruleAssignment EOF
            {
             newCompositeNode(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;

             current =iv_ruleAssignment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalMathLang.g:189:1: ruleAssignment returns [EObject current=null] : ( () ( (lv_left_1_0= ruleVariable ) ) otherlv_2= '=' ( (lv_right_3_0= ruleExpression ) ) ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:195:2: ( ( () ( (lv_left_1_0= ruleVariable ) ) otherlv_2= '=' ( (lv_right_3_0= ruleExpression ) ) ) )
            // InternalMathLang.g:196:2: ( () ( (lv_left_1_0= ruleVariable ) ) otherlv_2= '=' ( (lv_right_3_0= ruleExpression ) ) )
            {
            // InternalMathLang.g:196:2: ( () ( (lv_left_1_0= ruleVariable ) ) otherlv_2= '=' ( (lv_right_3_0= ruleExpression ) ) )
            // InternalMathLang.g:197:3: () ( (lv_left_1_0= ruleVariable ) ) otherlv_2= '=' ( (lv_right_3_0= ruleExpression ) )
            {
            // InternalMathLang.g:197:3: ()
            // InternalMathLang.g:198:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAssignmentAccess().getAssignmentAction_0(),
            					current);
            			

            }

            // InternalMathLang.g:204:3: ( (lv_left_1_0= ruleVariable ) )
            // InternalMathLang.g:205:4: (lv_left_1_0= ruleVariable )
            {
            // InternalMathLang.g:205:4: (lv_left_1_0= ruleVariable )
            // InternalMathLang.g:206:5: lv_left_1_0= ruleVariable
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getLeftVariableParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_6);
            lv_left_1_0=ruleVariable();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"left",
            						lv_left_1_0,
            						"org.xtext.example.mathlang.MathLang.Variable");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getAssignmentAccess().getEqualsSignKeyword_2());
            		
            // InternalMathLang.g:227:3: ( (lv_right_3_0= ruleExpression ) )
            // InternalMathLang.g:228:4: (lv_right_3_0= ruleExpression )
            {
            // InternalMathLang.g:228:4: (lv_right_3_0= ruleExpression )
            // InternalMathLang.g:229:5: lv_right_3_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getRightExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_right_3_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"org.xtext.example.mathlang.MathLang.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleSimpleExpression"
    // InternalMathLang.g:250:1: entryRuleSimpleExpression returns [EObject current=null] : iv_ruleSimpleExpression= ruleSimpleExpression EOF ;
    public final EObject entryRuleSimpleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleExpression = null;


        try {
            // InternalMathLang.g:250:57: (iv_ruleSimpleExpression= ruleSimpleExpression EOF )
            // InternalMathLang.g:251:2: iv_ruleSimpleExpression= ruleSimpleExpression EOF
            {
             newCompositeNode(grammarAccess.getSimpleExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleExpression=ruleSimpleExpression();

            state._fsp--;

             current =iv_ruleSimpleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleExpression"


    // $ANTLR start "ruleSimpleExpression"
    // InternalMathLang.g:257:1: ruleSimpleExpression returns [EObject current=null] : this_AddSub_0= ruleAddSub ;
    public final EObject ruleSimpleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_AddSub_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:263:2: (this_AddSub_0= ruleAddSub )
            // InternalMathLang.g:264:2: this_AddSub_0= ruleAddSub
            {

            		newCompositeNode(grammarAccess.getSimpleExpressionAccess().getAddSubParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_AddSub_0=ruleAddSub();

            state._fsp--;


            		current = this_AddSub_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleExpression"


    // $ANTLR start "entryRuleAddSub"
    // InternalMathLang.g:275:1: entryRuleAddSub returns [EObject current=null] : iv_ruleAddSub= ruleAddSub EOF ;
    public final EObject entryRuleAddSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddSub = null;


        try {
            // InternalMathLang.g:275:47: (iv_ruleAddSub= ruleAddSub EOF )
            // InternalMathLang.g:276:2: iv_ruleAddSub= ruleAddSub EOF
            {
             newCompositeNode(grammarAccess.getAddSubRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAddSub=ruleAddSub();

            state._fsp--;

             current =iv_ruleAddSub; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAddSub"


    // $ANTLR start "ruleAddSub"
    // InternalMathLang.g:282:1: ruleAddSub returns [EObject current=null] : (this_MulDiv_0= ruleMulDiv ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) ) )* ) ;
    public final EObject ruleAddSub() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_MulDiv_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:288:2: ( (this_MulDiv_0= ruleMulDiv ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) ) )* ) )
            // InternalMathLang.g:289:2: (this_MulDiv_0= ruleMulDiv ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) ) )* )
            {
            // InternalMathLang.g:289:2: (this_MulDiv_0= ruleMulDiv ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) ) )* )
            // InternalMathLang.g:290:3: this_MulDiv_0= ruleMulDiv ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) ) )*
            {

            			newCompositeNode(grammarAccess.getAddSubAccess().getMulDivParserRuleCall_0());
            		
            pushFollow(FOLLOW_7);
            this_MulDiv_0=ruleMulDiv();

            state._fsp--;


            			current = this_MulDiv_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMathLang.g:298:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=14 && LA4_0<=15)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalMathLang.g:299:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulDiv ) )
            	    {
            	    // InternalMathLang.g:299:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==14) ) {
            	        alt3=1;
            	    }
            	    else if ( (LA3_0==15) ) {
            	        alt3=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // InternalMathLang.g:300:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalMathLang.g:300:5: ( () otherlv_2= '+' )
            	            // InternalMathLang.g:301:6: () otherlv_2= '+'
            	            {
            	            // InternalMathLang.g:301:6: ()
            	            // InternalMathLang.g:302:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getAddSubAccess().getPlusLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,14,FOLLOW_4); 

            	            						newLeafNode(otherlv_2, grammarAccess.getAddSubAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalMathLang.g:314:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalMathLang.g:314:5: ( () otherlv_4= '-' )
            	            // InternalMathLang.g:315:6: () otherlv_4= '-'
            	            {
            	            // InternalMathLang.g:315:6: ()
            	            // InternalMathLang.g:316:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getAddSubAccess().getMinusLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,15,FOLLOW_4); 

            	            						newLeafNode(otherlv_4, grammarAccess.getAddSubAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalMathLang.g:328:4: ( (lv_right_5_0= ruleMulDiv ) )
            	    // InternalMathLang.g:329:5: (lv_right_5_0= ruleMulDiv )
            	    {
            	    // InternalMathLang.g:329:5: (lv_right_5_0= ruleMulDiv )
            	    // InternalMathLang.g:330:6: lv_right_5_0= ruleMulDiv
            	    {

            	    						newCompositeNode(grammarAccess.getAddSubAccess().getRightMulDivParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_7);
            	    lv_right_5_0=ruleMulDiv();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAddSubRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_5_0,
            	    							"org.xtext.example.mathlang.MathLang.MulDiv");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddSub"


    // $ANTLR start "entryRuleMulDiv"
    // InternalMathLang.g:352:1: entryRuleMulDiv returns [EObject current=null] : iv_ruleMulDiv= ruleMulDiv EOF ;
    public final EObject entryRuleMulDiv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMulDiv = null;


        try {
            // InternalMathLang.g:352:47: (iv_ruleMulDiv= ruleMulDiv EOF )
            // InternalMathLang.g:353:2: iv_ruleMulDiv= ruleMulDiv EOF
            {
             newCompositeNode(grammarAccess.getMulDivRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMulDiv=ruleMulDiv();

            state._fsp--;

             current =iv_ruleMulDiv; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMulDiv"


    // $ANTLR start "ruleMulDiv"
    // InternalMathLang.g:359:1: ruleMulDiv returns [EObject current=null] : (this_Pow_0= rulePow ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) ) )* ) ;
    public final EObject ruleMulDiv() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Pow_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:365:2: ( (this_Pow_0= rulePow ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) ) )* ) )
            // InternalMathLang.g:366:2: (this_Pow_0= rulePow ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) ) )* )
            {
            // InternalMathLang.g:366:2: (this_Pow_0= rulePow ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) ) )* )
            // InternalMathLang.g:367:3: this_Pow_0= rulePow ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) ) )*
            {

            			newCompositeNode(grammarAccess.getMulDivAccess().getPowParserRuleCall_0());
            		
            pushFollow(FOLLOW_8);
            this_Pow_0=rulePow();

            state._fsp--;


            			current = this_Pow_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMathLang.g:375:3: ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=16 && LA6_0<=17)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalMathLang.g:376:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= rulePow ) )
            	    {
            	    // InternalMathLang.g:376:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) )
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==16) ) {
            	        alt5=1;
            	    }
            	    else if ( (LA5_0==17) ) {
            	        alt5=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 5, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // InternalMathLang.g:377:5: ( () otherlv_2= '*' )
            	            {
            	            // InternalMathLang.g:377:5: ( () otherlv_2= '*' )
            	            // InternalMathLang.g:378:6: () otherlv_2= '*'
            	            {
            	            // InternalMathLang.g:378:6: ()
            	            // InternalMathLang.g:379:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMulDivAccess().getMulLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,16,FOLLOW_4); 

            	            						newLeafNode(otherlv_2, grammarAccess.getMulDivAccess().getAsteriskKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalMathLang.g:391:5: ( () otherlv_4= '/' )
            	            {
            	            // InternalMathLang.g:391:5: ( () otherlv_4= '/' )
            	            // InternalMathLang.g:392:6: () otherlv_4= '/'
            	            {
            	            // InternalMathLang.g:392:6: ()
            	            // InternalMathLang.g:393:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMulDivAccess().getDivLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,17,FOLLOW_4); 

            	            						newLeafNode(otherlv_4, grammarAccess.getMulDivAccess().getSolidusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalMathLang.g:405:4: ( (lv_right_5_0= rulePow ) )
            	    // InternalMathLang.g:406:5: (lv_right_5_0= rulePow )
            	    {
            	    // InternalMathLang.g:406:5: (lv_right_5_0= rulePow )
            	    // InternalMathLang.g:407:6: lv_right_5_0= rulePow
            	    {

            	    						newCompositeNode(grammarAccess.getMulDivAccess().getRightPowParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_8);
            	    lv_right_5_0=rulePow();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMulDivRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_5_0,
            	    							"org.xtext.example.mathlang.MathLang.Pow");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMulDiv"


    // $ANTLR start "entryRulePow"
    // InternalMathLang.g:429:1: entryRulePow returns [EObject current=null] : iv_rulePow= rulePow EOF ;
    public final EObject entryRulePow() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePow = null;


        try {
            // InternalMathLang.g:429:44: (iv_rulePow= rulePow EOF )
            // InternalMathLang.g:430:2: iv_rulePow= rulePow EOF
            {
             newCompositeNode(grammarAccess.getPowRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePow=rulePow();

            state._fsp--;

             current =iv_rulePow; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePow"


    // $ANTLR start "rulePow"
    // InternalMathLang.g:436:1: rulePow returns [EObject current=null] : (this_Unary_0= ruleUnary ( () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) ) )? ) ;
    public final EObject rulePow() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Unary_0 = null;

        EObject lv_exponent_3_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:442:2: ( (this_Unary_0= ruleUnary ( () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) ) )? ) )
            // InternalMathLang.g:443:2: (this_Unary_0= ruleUnary ( () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) ) )? )
            {
            // InternalMathLang.g:443:2: (this_Unary_0= ruleUnary ( () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) ) )? )
            // InternalMathLang.g:444:3: this_Unary_0= ruleUnary ( () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) ) )?
            {

            			newCompositeNode(grammarAccess.getPowAccess().getUnaryParserRuleCall_0());
            		
            pushFollow(FOLLOW_9);
            this_Unary_0=ruleUnary();

            state._fsp--;


            			current = this_Unary_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMathLang.g:452:3: ( () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalMathLang.g:453:4: () otherlv_2= '^' ( (lv_exponent_3_0= rulePow ) )
                    {
                    // InternalMathLang.g:453:4: ()
                    // InternalMathLang.g:454:5: 
                    {

                    					current = forceCreateModelElementAndSet(
                    						grammarAccess.getPowAccess().getPowBaseAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_2=(Token)match(input,18,FOLLOW_4); 

                    				newLeafNode(otherlv_2, grammarAccess.getPowAccess().getCircumflexAccentKeyword_1_1());
                    			
                    // InternalMathLang.g:464:4: ( (lv_exponent_3_0= rulePow ) )
                    // InternalMathLang.g:465:5: (lv_exponent_3_0= rulePow )
                    {
                    // InternalMathLang.g:465:5: (lv_exponent_3_0= rulePow )
                    // InternalMathLang.g:466:6: lv_exponent_3_0= rulePow
                    {

                    						newCompositeNode(grammarAccess.getPowAccess().getExponentPowParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_exponent_3_0=rulePow();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPowRule());
                    						}
                    						set(
                    							current,
                    							"exponent",
                    							lv_exponent_3_0,
                    							"org.xtext.example.mathlang.MathLang.Pow");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePow"


    // $ANTLR start "entryRuleUnary"
    // InternalMathLang.g:488:1: entryRuleUnary returns [EObject current=null] : iv_ruleUnary= ruleUnary EOF ;
    public final EObject entryRuleUnary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnary = null;


        try {
            // InternalMathLang.g:488:46: (iv_ruleUnary= ruleUnary EOF )
            // InternalMathLang.g:489:2: iv_ruleUnary= ruleUnary EOF
            {
             newCompositeNode(grammarAccess.getUnaryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnary=ruleUnary();

            state._fsp--;

             current =iv_ruleUnary; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnary"


    // $ANTLR start "ruleUnary"
    // InternalMathLang.g:495:1: ruleUnary returns [EObject current=null] : ( ( () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) ) ) | this_Primary_3= rulePrimary ) ;
    public final EObject ruleUnary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_operand_2_0 = null;

        EObject this_Primary_3 = null;



        	enterRule();

        try {
            // InternalMathLang.g:501:2: ( ( ( () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) ) ) | this_Primary_3= rulePrimary ) )
            // InternalMathLang.g:502:2: ( ( () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) ) ) | this_Primary_3= rulePrimary )
            {
            // InternalMathLang.g:502:2: ( ( () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) ) ) | this_Primary_3= rulePrimary )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==15) ) {
                alt8=1;
            }
            else if ( ((LA8_0>=RULE_ID && LA8_0<=RULE_INT)||LA8_0==19||(LA8_0>=21 && LA8_0<=27)) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalMathLang.g:503:3: ( () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) ) )
                    {
                    // InternalMathLang.g:503:3: ( () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) ) )
                    // InternalMathLang.g:504:4: () otherlv_1= '-' ( (lv_operand_2_0= ruleUnary ) )
                    {
                    // InternalMathLang.g:504:4: ()
                    // InternalMathLang.g:505:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getUnaryAccess().getNegativeAction_0_0(),
                    						current);
                    				

                    }

                    otherlv_1=(Token)match(input,15,FOLLOW_4); 

                    				newLeafNode(otherlv_1, grammarAccess.getUnaryAccess().getHyphenMinusKeyword_0_1());
                    			
                    // InternalMathLang.g:515:4: ( (lv_operand_2_0= ruleUnary ) )
                    // InternalMathLang.g:516:5: (lv_operand_2_0= ruleUnary )
                    {
                    // InternalMathLang.g:516:5: (lv_operand_2_0= ruleUnary )
                    // InternalMathLang.g:517:6: lv_operand_2_0= ruleUnary
                    {

                    						newCompositeNode(grammarAccess.getUnaryAccess().getOperandUnaryParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_operand_2_0=ruleUnary();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getUnaryRule());
                    						}
                    						set(
                    							current,
                    							"operand",
                    							lv_operand_2_0,
                    							"org.xtext.example.mathlang.MathLang.Unary");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMathLang.g:536:3: this_Primary_3= rulePrimary
                    {

                    			newCompositeNode(grammarAccess.getUnaryAccess().getPrimaryParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Primary_3=rulePrimary();

                    state._fsp--;


                    			current = this_Primary_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnary"


    // $ANTLR start "entryRulePrimary"
    // InternalMathLang.g:548:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // InternalMathLang.g:548:48: (iv_rulePrimary= rulePrimary EOF )
            // InternalMathLang.g:549:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // InternalMathLang.g:555:1: rulePrimary returns [EObject current=null] : (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')' ) ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject this_FunctionCall_0 = null;

        EObject this_Variable_1 = null;

        EObject this_NumberLiteral_2 = null;

        EObject this_Expression_4 = null;



        	enterRule();

        try {
            // InternalMathLang.g:561:2: ( (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')' ) ) )
            // InternalMathLang.g:562:2: (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')' ) )
            {
            // InternalMathLang.g:562:2: (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')' ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                {
                alt9=1;
                }
                break;
            case RULE_ID:
                {
                alt9=2;
                }
                break;
            case RULE_INT:
                {
                alt9=3;
                }
                break;
            case 19:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalMathLang.g:563:3: this_FunctionCall_0= ruleFunctionCall
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getFunctionCallParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_FunctionCall_0=ruleFunctionCall();

                    state._fsp--;


                    			current = this_FunctionCall_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMathLang.g:572:3: this_Variable_1= ruleVariable
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getVariableParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Variable_1=ruleVariable();

                    state._fsp--;


                    			current = this_Variable_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalMathLang.g:581:3: this_NumberLiteral_2= ruleNumberLiteral
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getNumberLiteralParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_NumberLiteral_2=ruleNumberLiteral();

                    state._fsp--;


                    			current = this_NumberLiteral_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalMathLang.g:590:3: (otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')' )
                    {
                    // InternalMathLang.g:590:3: (otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')' )
                    // InternalMathLang.g:591:4: otherlv_3= '(' this_Expression_4= ruleExpression otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,19,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_3_0());
                    			

                    				newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_3_1());
                    			
                    pushFollow(FOLLOW_10);
                    this_Expression_4=ruleExpression();

                    state._fsp--;


                    				current = this_Expression_4;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_5=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_3_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "entryRuleFunctionCall"
    // InternalMathLang.g:612:1: entryRuleFunctionCall returns [EObject current=null] : iv_ruleFunctionCall= ruleFunctionCall EOF ;
    public final EObject entryRuleFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionCall = null;


        try {
            // InternalMathLang.g:612:53: (iv_ruleFunctionCall= ruleFunctionCall EOF )
            // InternalMathLang.g:613:2: iv_ruleFunctionCall= ruleFunctionCall EOF
            {
             newCompositeNode(grammarAccess.getFunctionCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionCall=ruleFunctionCall();

            state._fsp--;

             current =iv_ruleFunctionCall; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionCall"


    // $ANTLR start "ruleFunctionCall"
    // InternalMathLang.g:619:1: ruleFunctionCall returns [EObject current=null] : ( () ( ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) ) ) otherlv_2= '(' ( ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )* )? otherlv_6= ')' ) ;
    public final EObject ruleFunctionCall() throws RecognitionException {
        EObject current = null;

        Token lv_func_1_1=null;
        Token lv_func_1_2=null;
        Token lv_func_1_3=null;
        Token lv_func_1_4=null;
        Token lv_func_1_5=null;
        Token lv_func_1_6=null;
        Token lv_func_1_7=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_args_3_0 = null;

        EObject lv_args_5_0 = null;



        	enterRule();

        try {
            // InternalMathLang.g:625:2: ( ( () ( ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) ) ) otherlv_2= '(' ( ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )* )? otherlv_6= ')' ) )
            // InternalMathLang.g:626:2: ( () ( ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) ) ) otherlv_2= '(' ( ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )* )? otherlv_6= ')' )
            {
            // InternalMathLang.g:626:2: ( () ( ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) ) ) otherlv_2= '(' ( ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )* )? otherlv_6= ')' )
            // InternalMathLang.g:627:3: () ( ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) ) ) otherlv_2= '(' ( ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )* )? otherlv_6= ')'
            {
            // InternalMathLang.g:627:3: ()
            // InternalMathLang.g:628:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFunctionCallAccess().getFunctionCallAction_0(),
            					current);
            			

            }

            // InternalMathLang.g:634:3: ( ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) ) )
            // InternalMathLang.g:635:4: ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) )
            {
            // InternalMathLang.g:635:4: ( (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' ) )
            // InternalMathLang.g:636:5: (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' )
            {
            // InternalMathLang.g:636:5: (lv_func_1_1= 'sin' | lv_func_1_2= 'cos' | lv_func_1_3= 'tan' | lv_func_1_4= 'sqrt' | lv_func_1_5= 'log' | lv_func_1_6= 'ln' | lv_func_1_7= 'frac' )
            int alt10=7;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt10=1;
                }
                break;
            case 22:
                {
                alt10=2;
                }
                break;
            case 23:
                {
                alt10=3;
                }
                break;
            case 24:
                {
                alt10=4;
                }
                break;
            case 25:
                {
                alt10=5;
                }
                break;
            case 26:
                {
                alt10=6;
                }
                break;
            case 27:
                {
                alt10=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalMathLang.g:637:6: lv_func_1_1= 'sin'
                    {
                    lv_func_1_1=(Token)match(input,21,FOLLOW_11); 

                    						newLeafNode(lv_func_1_1, grammarAccess.getFunctionCallAccess().getFuncSinKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalMathLang.g:648:6: lv_func_1_2= 'cos'
                    {
                    lv_func_1_2=(Token)match(input,22,FOLLOW_11); 

                    						newLeafNode(lv_func_1_2, grammarAccess.getFunctionCallAccess().getFuncCosKeyword_1_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalMathLang.g:659:6: lv_func_1_3= 'tan'
                    {
                    lv_func_1_3=(Token)match(input,23,FOLLOW_11); 

                    						newLeafNode(lv_func_1_3, grammarAccess.getFunctionCallAccess().getFuncTanKeyword_1_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalMathLang.g:670:6: lv_func_1_4= 'sqrt'
                    {
                    lv_func_1_4=(Token)match(input,24,FOLLOW_11); 

                    						newLeafNode(lv_func_1_4, grammarAccess.getFunctionCallAccess().getFuncSqrtKeyword_1_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_4, null);
                    					

                    }
                    break;
                case 5 :
                    // InternalMathLang.g:681:6: lv_func_1_5= 'log'
                    {
                    lv_func_1_5=(Token)match(input,25,FOLLOW_11); 

                    						newLeafNode(lv_func_1_5, grammarAccess.getFunctionCallAccess().getFuncLogKeyword_1_0_4());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_5, null);
                    					

                    }
                    break;
                case 6 :
                    // InternalMathLang.g:692:6: lv_func_1_6= 'ln'
                    {
                    lv_func_1_6=(Token)match(input,26,FOLLOW_11); 

                    						newLeafNode(lv_func_1_6, grammarAccess.getFunctionCallAccess().getFuncLnKeyword_1_0_5());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_6, null);
                    					

                    }
                    break;
                case 7 :
                    // InternalMathLang.g:703:6: lv_func_1_7= 'frac'
                    {
                    lv_func_1_7=(Token)match(input,27,FOLLOW_11); 

                    						newLeafNode(lv_func_1_7, grammarAccess.getFunctionCallAccess().getFuncFracKeyword_1_0_6());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFunctionCallRule());
                    						}
                    						setWithLastConsumed(current, "func", lv_func_1_7, null);
                    					

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getFunctionCallAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMathLang.g:720:3: ( ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )* )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_INT)||LA12_0==15||LA12_0==19||(LA12_0>=21 && LA12_0<=27)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalMathLang.g:721:4: ( (lv_args_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )*
                    {
                    // InternalMathLang.g:721:4: ( (lv_args_3_0= ruleExpression ) )
                    // InternalMathLang.g:722:5: (lv_args_3_0= ruleExpression )
                    {
                    // InternalMathLang.g:722:5: (lv_args_3_0= ruleExpression )
                    // InternalMathLang.g:723:6: lv_args_3_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getFunctionCallAccess().getArgsExpressionParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_args_3_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionCallRule());
                    						}
                    						add(
                    							current,
                    							"args",
                    							lv_args_3_0,
                    							"org.xtext.example.mathlang.MathLang.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMathLang.g:740:4: (otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==28) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalMathLang.g:741:5: otherlv_4= ',' ( (lv_args_5_0= ruleExpression ) )
                    	    {
                    	    otherlv_4=(Token)match(input,28,FOLLOW_4); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getFunctionCallAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalMathLang.g:745:5: ( (lv_args_5_0= ruleExpression ) )
                    	    // InternalMathLang.g:746:6: (lv_args_5_0= ruleExpression )
                    	    {
                    	    // InternalMathLang.g:746:6: (lv_args_5_0= ruleExpression )
                    	    // InternalMathLang.g:747:7: lv_args_5_0= ruleExpression
                    	    {

                    	    							newCompositeNode(grammarAccess.getFunctionCallAccess().getArgsExpressionParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_13);
                    	    lv_args_5_0=ruleExpression();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getFunctionCallRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"args",
                    	    								lv_args_5_0,
                    	    								"org.xtext.example.mathlang.MathLang.Expression");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,20,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionCall"


    // $ANTLR start "entryRuleVariable"
    // InternalMathLang.g:774:1: entryRuleVariable returns [EObject current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final EObject entryRuleVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariable = null;


        try {
            // InternalMathLang.g:774:49: (iv_ruleVariable= ruleVariable EOF )
            // InternalMathLang.g:775:2: iv_ruleVariable= ruleVariable EOF
            {
             newCompositeNode(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariable=ruleVariable();

            state._fsp--;

             current =iv_ruleVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // InternalMathLang.g:781:1: ruleVariable returns [EObject current=null] : ( () ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalMathLang.g:787:2: ( ( () ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalMathLang.g:788:2: ( () ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalMathLang.g:788:2: ( () ( (lv_name_1_0= RULE_ID ) ) )
            // InternalMathLang.g:789:3: () ( (lv_name_1_0= RULE_ID ) )
            {
            // InternalMathLang.g:789:3: ()
            // InternalMathLang.g:790:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableAccess().getVariableAction_0(),
            					current);
            			

            }

            // InternalMathLang.g:796:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMathLang.g:797:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMathLang.g:797:4: (lv_name_1_0= RULE_ID )
            // InternalMathLang.g:798:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVariableAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleNumberLiteral"
    // InternalMathLang.g:818:1: entryRuleNumberLiteral returns [EObject current=null] : iv_ruleNumberLiteral= ruleNumberLiteral EOF ;
    public final EObject entryRuleNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteral = null;


        try {
            // InternalMathLang.g:818:54: (iv_ruleNumberLiteral= ruleNumberLiteral EOF )
            // InternalMathLang.g:819:2: iv_ruleNumberLiteral= ruleNumberLiteral EOF
            {
             newCompositeNode(grammarAccess.getNumberLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumberLiteral=ruleNumberLiteral();

            state._fsp--;

             current =iv_ruleNumberLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNumberLiteral"


    // $ANTLR start "ruleNumberLiteral"
    // InternalMathLang.g:825:1: ruleNumberLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_INT ) ) ) ;
    public final EObject ruleNumberLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalMathLang.g:831:2: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) )
            // InternalMathLang.g:832:2: ( () ( (lv_value_1_0= RULE_INT ) ) )
            {
            // InternalMathLang.g:832:2: ( () ( (lv_value_1_0= RULE_INT ) ) )
            // InternalMathLang.g:833:3: () ( (lv_value_1_0= RULE_INT ) )
            {
            // InternalMathLang.g:833:3: ()
            // InternalMathLang.g:834:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0(),
            					current);
            			

            }

            // InternalMathLang.g:840:3: ( (lv_value_1_0= RULE_INT ) )
            // InternalMathLang.g:841:4: (lv_value_1_0= RULE_INT )
            {
            // InternalMathLang.g:841:4: (lv_value_1_0= RULE_INT )
            // InternalMathLang.g:842:5: lv_value_1_0= RULE_INT
            {
            lv_value_1_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            					newLeafNode(lv_value_1_0, grammarAccess.getNumberLiteralAccess().getValueINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getNumberLiteralRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumberLiteral"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000000000FE88030L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000000C002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x000000000FF88030L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000010100000L});

}