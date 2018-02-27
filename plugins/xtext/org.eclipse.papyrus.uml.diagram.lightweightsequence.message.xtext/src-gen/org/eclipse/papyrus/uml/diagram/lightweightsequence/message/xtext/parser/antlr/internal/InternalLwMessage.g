/*
 * generated by Xtext
 */
grammar InternalLwMessage;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.services.LwMessageGrammarAccess;

}

@parser::members {

 	private LwMessageGrammarAccess grammarAccess;
 	
    public InternalLwMessageParser(TokenStream input, LwMessageGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "AbstractMessage";	
   	}
   	
   	@Override
   	protected LwMessageGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleAbstractMessage
entryRuleAbstractMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAbstractMessageRule()); }
	 iv_ruleAbstractMessage=ruleAbstractMessage 
	 { $current=$iv_ruleAbstractMessage.current; } 
	 EOF 
;

// Rule AbstractMessage
ruleAbstractMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAbstractMessageAccess().getAbstractRequestMessageParserRuleCall_0()); 
    }
    this_AbstractRequestMessage_0=ruleAbstractRequestMessage
    { 
        $current = $this_AbstractRequestMessage_0.current; 
        afterParserOrEnumRuleCall();
    }

    |((
(
		ruleQName
)
)=>
    { 
        newCompositeNode(grammarAccess.getAbstractMessageAccess().getReplyMessageParserRuleCall_1()); 
    }
    this_ReplyMessage_1=ruleReplyMessage
    { 
        $current = $this_ReplyMessage_1.current; 
        afterParserOrEnumRuleCall();
    }
))
;





// Entry rule entryRuleAbstractRequestMessage
entryRuleAbstractRequestMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAbstractRequestMessageRule()); }
	 iv_ruleAbstractRequestMessage=ruleAbstractRequestMessage 
	 { $current=$iv_ruleAbstractRequestMessage.current; } 
	 EOF 
;

// Rule AbstractRequestMessage
ruleAbstractRequestMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAbstractRequestMessageAccess().getAnyMessageParserRuleCall_0()); 
    }
    this_AnyMessage_0=ruleAnyMessage
    { 
        $current = $this_AnyMessage_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getAbstractRequestMessageAccess().getRequestMessageParserRuleCall_1()); 
    }
    this_RequestMessage_1=ruleRequestMessage
    { 
        $current = $this_RequestMessage_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleRequestMessage
entryRuleRequestMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRequestMessageRule()); }
	 iv_ruleRequestMessage=ruleRequestMessage 
	 { $current=$iv_ruleRequestMessage.current; } 
	 EOF 
;

// Rule RequestMessage
ruleRequestMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((((
(
ruleName
)
)=>
(
		{ 
	        newCompositeNode(grammarAccess.getRequestMessageAccess().getNameNameParserRuleCall_0_0_0()); 
	    }
		lv_name_0_0=ruleName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRequestMessageRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Name");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |((
(
	RULE_ID

)
)=>
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRequestMessageRule());
	        }
        }
	otherlv_1=RULE_ID
	{
		newLeafNode(otherlv_1, grammarAccess.getRequestMessageAccess().getSignalSignalCrossReference_0_1_0()); 
	}

)
)
    |((
(
	RULE_ID

)
)=>
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRequestMessageRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getRequestMessageAccess().getOperationOperationCrossReference_0_2_0()); 
	}

)
))(	otherlv_3='(' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getRequestMessageAccess().getLeftParenthesisKeyword_1_0());
    }
(
    { 
		if ($current==null) {
			$current = createModelElement(grammarAccess.getRequestMessageRule());
		}
        newCompositeNode(grammarAccess.getRequestMessageAccess().getMessageRequestArgumentsParserRuleCall_1_1()); 
    }
    this_MessageRequestArguments_4=ruleMessageRequestArguments[$current]
    { 
        $current = $this_MessageRequestArguments_4.current; 
        afterParserOrEnumRuleCall();
    }
)?	otherlv_5=')' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getRequestMessageAccess().getRightParenthesisKeyword_1_2());
    }
)?)
;





// Entry rule entryRuleAnyMessage
entryRuleAnyMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAnyMessageRule()); }
	 iv_ruleAnyMessage=ruleAnyMessage 
	 { $current=$iv_ruleAnyMessage.current; } 
	 EOF 
;

// Rule AnyMessage
ruleAnyMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getAnyMessageAccess().getAnyMessageAction_0(),
            $current);
    }
)	otherlv_1='*' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getAnyMessageAccess().getAsteriskKeyword_1());
    }
)
;






// Rule MessageRequestArguments
ruleMessageRequestArguments [EObject in_current] returns [EObject current=in_current] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
(
		{ 
	        newCompositeNode(grammarAccess.getMessageRequestArgumentsAccess().getArgumentsMessageRequestArgumentParserRuleCall_0_0_0()); 
	    }
		lv_arguments_0_0=ruleMessageRequestArgument		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageRequestArgumentsRule());
	        }
       		add(
       			$current, 
       			"arguments",
        		lv_arguments_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.MessageRequestArgument");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_1=',' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getMessageRequestArgumentsAccess().getCommaKeyword_0_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMessageRequestArgumentsAccess().getArgumentsMessageRequestArgumentParserRuleCall_0_1_1_0()); 
	    }
		lv_arguments_2_0=ruleMessageRequestArgument		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageRequestArgumentsRule());
	        }
       		add(
       			$current, 
       			"arguments",
        		lv_arguments_2_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.MessageRequestArgument");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
    |((
(
		{ 
	        newCompositeNode(grammarAccess.getMessageRequestArgumentsAccess().getArgumentsMessageRequestArgumentWithNameParserRuleCall_1_0_0()); 
	    }
		lv_arguments_3_0=ruleMessageRequestArgumentWithName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageRequestArgumentsRule());
	        }
       		add(
       			$current, 
       			"arguments",
        		lv_arguments_3_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.MessageRequestArgumentWithName");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_4=',' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getMessageRequestArgumentsAccess().getCommaKeyword_1_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMessageRequestArgumentsAccess().getArgumentsMessageRequestArgumentWithNameParserRuleCall_1_1_1_0()); 
	    }
		lv_arguments_5_0=ruleMessageRequestArgumentWithName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageRequestArgumentsRule());
	        }
       		add(
       			$current, 
       			"arguments",
        		lv_arguments_5_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.MessageRequestArgumentWithName");
	        afterParserOrEnumRuleCall();
	    }

)
))*))
;





// Entry rule entryRuleMessageRequestArgument
entryRuleMessageRequestArgument returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMessageRequestArgumentRule()); }
	 iv_ruleMessageRequestArgument=ruleMessageRequestArgument 
	 { $current=$iv_ruleMessageRequestArgument.current; } 
	 EOF 
;

// Rule MessageRequestArgument
ruleMessageRequestArgument returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
    {
        $current = forceCreateModelElement(
            grammarAccess.getMessageRequestArgumentAccess().getWildcardMessageArgumentAction_0_0(),
            $current);
    }
)((	'-' 
)=>	otherlv_1='-' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getMessageRequestArgumentAccess().getHyphenMinusKeyword_0_1());
    }
))
    |
    { 
		if ($current==null) {
			$current = createModelElement(grammarAccess.getMessageRequestArgumentRule());
		}
        newCompositeNode(grammarAccess.getMessageRequestArgumentAccess().getMessageRequestValueParserRuleCall_1()); 
    }
    this_MessageRequestValue_2=ruleMessageRequestValue[$current]
    { 
        $current = $this_MessageRequestValue_2.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleMessageRequestArgumentWithName
entryRuleMessageRequestArgumentWithName returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMessageRequestArgumentWithNameRule()); }
	 iv_ruleMessageRequestArgumentWithName=ruleMessageRequestArgumentWithName 
	 { $current=$iv_ruleMessageRequestArgumentWithName.current; } 
	 EOF 
;

// Rule MessageRequestArgumentWithName
ruleMessageRequestArgumentWithName returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:

    { 
        newCompositeNode(grammarAccess.getMessageRequestArgumentWithNameAccess().getMessageRequestNameAndValueParserRuleCall()); 
    }
    this_MessageRequestNameAndValue_0=ruleMessageRequestNameAndValue
    { 
        $current = $this_MessageRequestNameAndValue_0.current; 
        afterParserOrEnumRuleCall();
    }

;





// Entry rule entryRuleMessageRequestNameAndValue
entryRuleMessageRequestNameAndValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMessageRequestNameAndValueRule()); }
	 iv_ruleMessageRequestNameAndValue=ruleMessageRequestNameAndValue 
	 { $current=$iv_ruleMessageRequestNameAndValue.current; } 
	 EOF 
;

// Rule MessageRequestNameAndValue
ruleMessageRequestNameAndValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((((
(
ruleName
)
)=>
(
		{ 
	        newCompositeNode(grammarAccess.getMessageRequestNameAndValueAccess().getNameNameParserRuleCall_0_0_0()); 
	    }
		lv_name_0_0=ruleName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageRequestNameAndValueRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Name");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |((
(
	RULE_ID

)
)=>
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMessageRequestNameAndValueRule());
	        }
        }
	otherlv_1=RULE_ID
	{
		newLeafNode(otherlv_1, grammarAccess.getMessageRequestNameAndValueAccess().getPropertyPropertyCrossReference_0_1_0()); 
	}

)
)
    |((
(
	RULE_ID

)
)=>
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMessageRequestNameAndValueRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getMessageRequestNameAndValueAccess().getParameterParameterCrossReference_0_2_0()); 
	}

)
))	otherlv_3='=' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getMessageRequestNameAndValueAccess().getEqualsSignKeyword_1());
    }

    { 
		if ($current==null) {
			$current = createModelElement(grammarAccess.getMessageRequestNameAndValueRule());
		}
        newCompositeNode(grammarAccess.getMessageRequestNameAndValueAccess().getMessageRequestValueParserRuleCall_2()); 
    }
    this_MessageRequestValue_4=ruleMessageRequestValue[$current]
    { 
        $current = $this_MessageRequestValue_4.current; 
        afterParserOrEnumRuleCall();
    }
)
;






// Rule MessageRequestValue
ruleMessageRequestValue [EObject in_current] returns [EObject current=in_current] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getMessageRequestValueAccess().getValueValueParserRuleCall_0()); 
	    }
		lv_value_0_0=ruleValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageRequestValueRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Value");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleReplyMessage
entryRuleReplyMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getReplyMessageRule()); }
	 iv_ruleReplyMessage=ruleReplyMessage 
	 { $current=$iv_ruleReplyMessage.current; } 
	 EOF 
;

// Rule ReplyMessage
ruleReplyMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
(
		ruleQName
)
)=>
    { 
		if ($current==null) {
			$current = createModelElement(grammarAccess.getReplyMessageRule());
		}
        newCompositeNode(grammarAccess.getReplyMessageAccess().getAssignmentTargetParserRuleCall_0()); 
    }
    this_AssignmentTarget_0=ruleAssignmentTarget[$current]
    { 
        $current = $this_AssignmentTarget_0.current; 
        afterParserOrEnumRuleCall();
    }
)?(((
(
ruleName
)
)=>
(
		{ 
	        newCompositeNode(grammarAccess.getReplyMessageAccess().getNameNameParserRuleCall_1_0_0()); 
	    }
		lv_name_1_0=ruleName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getReplyMessageRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Name");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |((
(
	RULE_ID

)
)=>
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getReplyMessageRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getReplyMessageAccess().getOperationOperationCrossReference_1_1_0()); 
	}

)
))(	otherlv_3='(' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getReplyMessageAccess().getLeftParenthesisKeyword_2_0());
    }
(
    { 
		if ($current==null) {
			$current = createModelElement(grammarAccess.getReplyMessageRule());
		}
        newCompositeNode(grammarAccess.getReplyMessageAccess().getMessageReplyOutputsParserRuleCall_2_1()); 
    }
    this_MessageReplyOutputs_4=ruleMessageReplyOutputs[$current]
    { 
        $current = $this_MessageReplyOutputs_4.current; 
        afterParserOrEnumRuleCall();
    }
)?	otherlv_5=')' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getReplyMessageAccess().getRightParenthesisKeyword_2_2());
    }
)?(
(
		{ 
	        newCompositeNode(grammarAccess.getReplyMessageAccess().getValueOutputValueParserRuleCall_3_0()); 
	    }
		lv_value_6_0=ruleOutputValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getReplyMessageRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_6_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.OutputValue");
	        afterParserOrEnumRuleCall();
	    }

)
)?)
;






// Rule AssignmentTarget
ruleAssignmentTarget [EObject in_current] returns [EObject current=in_current] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
(
		ruleQName
)
)=>
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getAssignmentTargetRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getAssignmentTargetAccess().getTargetConnectableElementCrossReference_0_0()); 
	    }
		ruleQName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_1='=' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getAssignmentTargetAccess().getEqualsSignKeyword_1());
    }
)
;






// Rule MessageReplyOutputs
ruleMessageReplyOutputs [EObject in_current] returns [EObject current=in_current] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getMessageReplyOutputsAccess().getOutputsMessageReplyOutputParserRuleCall_0_0()); 
	    }
		lv_outputs_0_0=ruleMessageReplyOutput		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageReplyOutputsRule());
	        }
       		add(
       			$current, 
       			"outputs",
        		lv_outputs_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.MessageReplyOutput");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_1=',' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getMessageReplyOutputsAccess().getCommaKeyword_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMessageReplyOutputsAccess().getOutputsMessageReplyOutputParserRuleCall_1_1_0()); 
	    }
		lv_outputs_2_0=ruleMessageReplyOutput		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageReplyOutputsRule());
	        }
       		add(
       			$current, 
       			"outputs",
        		lv_outputs_2_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.MessageReplyOutput");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleMessageReplyOutput
entryRuleMessageReplyOutput returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMessageReplyOutputRule()); }
	 iv_ruleMessageReplyOutput=ruleMessageReplyOutput 
	 { $current=$iv_ruleMessageReplyOutput.current; } 
	 EOF 
;

// Rule MessageReplyOutput
ruleMessageReplyOutput returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((((
(
		ruleQName
)
)=>
    { 
		if ($current==null) {
			$current = createModelElement(grammarAccess.getMessageReplyOutputRule());
		}
        newCompositeNode(grammarAccess.getMessageReplyOutputAccess().getAssignmentTargetParserRuleCall_0_0()); 
    }
    this_AssignmentTarget_0=ruleAssignmentTarget[$current]
    { 
        $current = $this_AssignmentTarget_0.current; 
        afterParserOrEnumRuleCall();
    }
)(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMessageReplyOutputRule());
	        }
        }
	otherlv_1=RULE_ID
	{
		newLeafNode(otherlv_1, grammarAccess.getMessageReplyOutputAccess().getParameterParameterCrossReference_0_1_0()); 
	}

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getMessageReplyOutputAccess().getValueOutputValueParserRuleCall_0_2_0()); 
	    }
		lv_value_2_0=ruleOutputValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageReplyOutputRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_2_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.OutputValue");
	        afterParserOrEnumRuleCall();
	    }

)
)?)
    |((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMessageReplyOutputRule());
	        }
        }
	otherlv_3=RULE_ID
	{
		newLeafNode(otherlv_3, grammarAccess.getMessageReplyOutputAccess().getParameterParameterCrossReference_1_0_0()); 
	}

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getMessageReplyOutputAccess().getValueOutputValueParserRuleCall_1_1_0()); 
	    }
		lv_value_4_0=ruleOutputValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMessageReplyOutputRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_4_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.OutputValue");
	        afterParserOrEnumRuleCall();
	    }

)
)))
;





// Entry rule entryRuleOutputValue
entryRuleOutputValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getOutputValueRule()); }
	 iv_ruleOutputValue=ruleOutputValue 
	 { $current=$iv_ruleOutputValue.current; } 
	 EOF 
;

// Rule OutputValue
ruleOutputValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0=':' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getOutputValueAccess().getColonKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getOutputValueAccess().getValueValueParserRuleCall_1_0()); 
	    }
		lv_value_1_0=ruleValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getOutputValueRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_1_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Value");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleValue
entryRuleValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getValueRule()); }
	 iv_ruleValue=ruleValue 
	 { $current=$iv_ruleValue.current; } 
	 EOF 
;

// Rule Value
ruleValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_0()); 
    }
    this_BooleanValue_0=ruleBooleanValue
    { 
        $current = $this_BooleanValue_0.current; 
        afterParserOrEnumRuleCall();
    }

    |((	ruleIntegerValue)=>
    { 
        newCompositeNode(grammarAccess.getValueAccess().getIntegerValueParserRuleCall_1()); 
    }
    this_IntegerValue_1=ruleIntegerValue
    { 
        $current = $this_IntegerValue_1.current; 
        afterParserOrEnumRuleCall();
    }
)
    |((	ruleUnlimitedNaturalValue)=>
    { 
        newCompositeNode(grammarAccess.getValueAccess().getUnlimitedNaturalValueParserRuleCall_2()); 
    }
    this_UnlimitedNaturalValue_2=ruleUnlimitedNaturalValue
    { 
        $current = $this_UnlimitedNaturalValue_2.current; 
        afterParserOrEnumRuleCall();
    }
)
    |((	ruleRealValue)=>
    { 
        newCompositeNode(grammarAccess.getValueAccess().getRealValueParserRuleCall_3()); 
    }
    this_RealValue_3=ruleRealValue
    { 
        $current = $this_RealValue_3.current; 
        afterParserOrEnumRuleCall();
    }
)
    |
    { 
        newCompositeNode(grammarAccess.getValueAccess().getNullValueParserRuleCall_4()); 
    }
    this_NullValue_4=ruleNullValue
    { 
        $current = $this_NullValue_4.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getValueAccess().getStringValueParserRuleCall_5()); 
    }
    this_StringValue_5=ruleStringValue
    { 
        $current = $this_StringValue_5.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleBooleanValue
entryRuleBooleanValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getBooleanValueRule()); }
	 iv_ruleBooleanValue=ruleBooleanValue 
	 { $current=$iv_ruleBooleanValue.current; } 
	 EOF 
;

// Rule BooleanValue
ruleBooleanValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getBooleanValueAccess().getValueBooleanParserRuleCall_0()); 
	    }
		lv_value_0_0=ruleBoolean		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getBooleanValueRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Boolean");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleIntegerValue
entryRuleIntegerValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getIntegerValueRule()); }
	 iv_ruleIntegerValue=ruleIntegerValue 
	 { $current=$iv_ruleIntegerValue.current; } 
	 EOF 
;

// Rule IntegerValue
ruleIntegerValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_INT
		{
			newLeafNode(lv_value_0_0, grammarAccess.getIntegerValueAccess().getValueINTTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getIntegerValueRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.INT");
	    }

)
)
;





// Entry rule entryRuleUnlimitedNaturalValue
entryRuleUnlimitedNaturalValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getUnlimitedNaturalValueRule()); }
	 iv_ruleUnlimitedNaturalValue=ruleUnlimitedNaturalValue 
	 { $current=$iv_ruleUnlimitedNaturalValue.current; } 
	 EOF 
;

// Rule UnlimitedNaturalValue
ruleUnlimitedNaturalValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getUnlimitedNaturalValueAccess().getValueUnlimitedNaturalParserRuleCall_0()); 
	    }
		lv_value_0_0=ruleUnlimitedNatural		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getUnlimitedNaturalValueRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.UnlimitedNatural");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleRealValue
entryRuleRealValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRealValueRule()); }
	 iv_ruleRealValue=ruleRealValue 
	 { $current=$iv_ruleRealValue.current; } 
	 EOF 
;

// Rule RealValue
ruleRealValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getRealValueAccess().getValueDoubleParserRuleCall_0()); 
	    }
		lv_value_0_0=ruleDouble		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRealValueRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.LwMessage.Double");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleNullValue
entryRuleNullValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getNullValueRule()); }
	 iv_ruleNullValue=ruleNullValue 
	 { $current=$iv_ruleNullValue.current; } 
	 EOF 
;

// Rule NullValue
ruleNullValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getNullValueAccess().getNullValueAction_0(),
            $current);
    }
)	otherlv_1='null' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getNullValueAccess().getNullKeyword_1());
    }
)
;





// Entry rule entryRuleStringValue
entryRuleStringValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStringValueRule()); }
	 iv_ruleStringValue=ruleStringValue 
	 { $current=$iv_ruleStringValue.current; } 
	 EOF 
;

// Rule StringValue
ruleStringValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_STRING
		{
			newLeafNode(lv_value_0_0, grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStringValueRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.papyrus.uml.alf.Common.STRING");
	    }

)
)
;







// Entry rule entryRuleDouble
entryRuleDouble returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getDoubleRule()); } 
	 iv_ruleDouble=ruleDouble 
	 { $current=$iv_ruleDouble.current.getText(); }  
	 EOF 
;

// Rule Double
ruleDouble returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_INT_0=RULE_INT    {
		$current.merge(this_INT_0);
    }

    { 
    newLeafNode(this_INT_0, grammarAccess.getDoubleAccess().getINTTerminalRuleCall_0()); 
    }

    |    this_REAL_1=RULE_REAL    {
		$current.merge(this_REAL_1);
    }

    { 
    newLeafNode(this_REAL_1, grammarAccess.getDoubleAccess().getREALTerminalRuleCall_1()); 
    }
)
    ;





// Entry rule entryRuleUnlimitedNatural
entryRuleUnlimitedNatural returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getUnlimitedNaturalRule()); } 
	 iv_ruleUnlimitedNatural=ruleUnlimitedNatural 
	 { $current=$iv_ruleUnlimitedNatural.current.getText(); }  
	 EOF 
;

// Rule UnlimitedNatural
ruleUnlimitedNatural returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getUnlimitedNaturalAccess().getIntegerParserRuleCall_0()); 
    }
    this_Integer_0=ruleInteger    {
		$current.merge(this_Integer_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }

    |
	kw='*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getUnlimitedNaturalAccess().getAsteriskKeyword_1()); 
    }
)
    ;





// Entry rule entryRuleInteger
entryRuleInteger returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getIntegerRule()); } 
	 iv_ruleInteger=ruleInteger 
	 { $current=$iv_ruleInteger.current.getText(); }  
	 EOF 
;

// Rule Integer
ruleInteger returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_INT_0=RULE_INT    {
		$current.merge(this_INT_0);
    }

    { 
    newLeafNode(this_INT_0, grammarAccess.getIntegerAccess().getINTTerminalRuleCall_0()); 
    }

    |    this_NEG_INT_1=RULE_NEG_INT    {
		$current.merge(this_NEG_INT_1);
    }

    { 
    newLeafNode(this_NEG_INT_1, grammarAccess.getIntegerAccess().getNEG_INTTerminalRuleCall_1()); 
    }
)
    ;





// Entry rule entryRuleBoolean
entryRuleBoolean returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getBooleanRule()); } 
	 iv_ruleBoolean=ruleBoolean 
	 { $current=$iv_ruleBoolean.current.getText(); }  
	 EOF 
;

// Rule Boolean
ruleBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
	kw='true' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getBooleanAccess().getTrueKeyword_0()); 
    }

    |
	kw='false' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getBooleanAccess().getFalseKeyword_1()); 
    }
)
    ;





// Entry rule entryRuleQName
entryRuleQName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQNameRule()); } 
	 iv_ruleQName=ruleQName 
	 { $current=$iv_ruleQName.current.getText(); }  
	 EOF 
;

// Rule QName
ruleQName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getQNameAccess().getNameParserRuleCall_0()); 
    }
    this_Name_0=ruleName    {
		$current.merge(this_Name_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }
(
	kw='::' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQNameAccess().getColonColonKeyword_1_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getQNameAccess().getNameParserRuleCall_1_1()); 
    }
    this_Name_2=ruleName    {
		$current.merge(this_Name_2);
    }

    { 
        afterParserOrEnumRuleCall();
    }
)*)
    ;





// Entry rule entryRuleName
entryRuleName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getNameRule()); } 
	 iv_ruleName=ruleName 
	 { $current=$iv_ruleName.current.getText(); }  
	 EOF 
;

// Rule Name
ruleName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getNameAccess().getIDTerminalRuleCall()); 
    }

    ;





fragment RULE_DIGITS : RULE_DIGIT RULE_DIGIT0*;

fragment RULE_DIGIT : '1'..'9';

fragment RULE_DIGIT0 : ('0'|RULE_DIGIT);

fragment RULE_DECIMAL : ((RULE_INT|RULE_NEG_INT)|('-'|'+')? RULE_DIGIT0? '.' RULE_DIGITS);

RULE_INT : '+'? RULE_DIGITS;

RULE_NEG_INT : '-' RULE_DIGITS;

RULE_REAL : RULE_DECIMAL (('e'|'E') ('-'|'+')? RULE_DIGITS)?;

RULE_ID : (('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*|'\'' ( options {greedy=false;} : . )*'\'');

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_ML_COMMENT : '/*' ~('@') ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'|'@'))* ('\r'? '\n')?;

RULE_INTEGER_VALUE : (('0'|'1'..'9' ('_'? '0'..'9')*)|('0b'|'0B') '0'..'1' ('_'? '0'..'1')*|('0x'|'0X') ('0'..'9'|'a'..'f'|'A'..'F') ('_'? ('0'..'9'|'a'..'f'|'A'..'F'))*|'0' '_'? '0'..'7' ('_'? '0'..'7')*);

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


