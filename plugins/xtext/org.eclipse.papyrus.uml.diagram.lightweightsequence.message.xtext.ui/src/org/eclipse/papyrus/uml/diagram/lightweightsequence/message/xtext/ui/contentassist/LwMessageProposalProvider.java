/*****************************************************************************
 * Copyright (c) 2016, 2018 Ericsson AB and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ericsson AB (Antonio Campesino) - Initial API and implementation
 *   Christian W. Damus - adapted for lightweight sequence diagram
 *
 *****************************************************************************/

/*
 * generated by Xtext
 */
package org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.ui.contentassist;

import static java.util.Arrays.asList;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.util.MessageUtil.canHaveSignature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.ReplyMessage;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.RequestMessage;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.util.MessageUtil;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.util.NamedElementUtil;
import org.eclipse.papyrus.uml.tools.utils.ICustomAppearance;
import org.eclipse.papyrus.uml.tools.utils.OperationUtil;
import org.eclipse.papyrus.uml.tools.utils.ParameterUtil;
import org.eclipse.papyrus.uml.tools.utils.PropertyUtil;
import org.eclipse.papyrus.uml.xtext.integration.CompletionProposalUtils;
import org.eclipse.papyrus.uml.xtext.integration.core.ContextElementUtil;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

/**
 * See
 * https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
public class LwMessageProposalProvider extends
		org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.ui.contentassist.AbstractLwMessageProposalProvider {

	private static final List<String> MASK_VALUES__OPERATION = asList(ICustomAppearance.DISP_NAME,
			ICustomAppearance.DISP_RT_TYPE, ICustomAppearance.DISP_PARAMETER_DIRECTION,
			ICustomAppearance.DISP_PARAMETER_NAME, ICustomAppearance.DISP_PARAMETER_TYPE,
			ICustomAppearance.DISP_PARAMETER_DEFAULT);

	private static final List<String> MASK_VALUES__ATTRIBUTE = Arrays.asList(ICustomAppearance.DISP_NAME,
			ICustomAppearance.DISP_TYPE);

	private static final List<String> MASK_VALUES__PARAMETER = Arrays.asList(
			ICustomAppearance.DISP_PARAMETER_DIRECTION, ICustomAppearance.DISP_PARAMETER_NAME,
			ICustomAppearance.DISP_PARAMETER_TYPE);

	@Override
	public void completeRequestMessage_Signal(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if (!canHaveSignature(message)) {
				return;
			}

			if ((message.getMessageSort() == MessageSort.ASYNCH_SIGNAL_LITERAL)
					|| (message.getSignature() == null)) {

				Stream<Signal> signals = MessageUtil.getReceivableSignals(message);
				signals.filter(isPrefixed(prefix)).map(propose(context)).forEach(acceptor::accept);
			}
		}
	}

	@Override
	public void completeRequestMessage_Operation(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if (!canHaveSignature(message)) {
				return;
			}

			if ((message.getMessageSort() == MessageSort.ASYNCH_CALL_LITERAL)
					|| (message.getMessageSort() == MessageSort.SYNCH_CALL_LITERAL)
					|| (message.getSignature() == null)) {

				Stream<Operation> operations = MessageUtil.getCallableOperations(message);
				operations.filter(isPrefixed(prefix)).map(propose(context)).forEach(acceptor::accept);
			}
		}
	}

	@Override
	public void completeMessageRequestNameAndValue_Property(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if ((message.getSignature() != null)
					&& (message.getMessageSort() != MessageSort.ASYNCH_SIGNAL_LITERAL)) {
				return;
			}

			RequestMessage requestMessage = EcoreUtil2.getContainerOfType(context.getCurrentModel(),
					RequestMessage.class);
			if (requestMessage != null) {
				Optional<Signal> maybeSignal = getSignal(message, requestMessage);
				maybeSignal.ifPresent(signal -> {
					Stream<Property> attributes = signal.getAllAttributes().stream();
					attributes.filter(isPrefixed(prefix)).map(propose(context)).forEach(acceptor::accept);
				});
			}
		}
	}

	@Override
	public void completeMessageRequestNameAndValue_Parameter(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if ((message.getSignature() != null)
					&& (message.getMessageSort() != MessageSort.SYNCH_CALL_LITERAL)
					&& (message.getMessageSort() != MessageSort.ASYNCH_CALL_LITERAL)) {
				return;
			}

			RequestMessage requestMessage = EcoreUtil2.getContainerOfType(context.getCurrentModel(),
					RequestMessage.class);
			if (requestMessage != null) {
				Optional<Operation> maybeOperation = getOperation(message, requestMessage);
				maybeOperation.ifPresent(operation -> {
					Stream<Parameter> parameters = operation.getOwnedParameters().stream().filter(
							org.eclipse.papyrus.uml.diagram.sequence.runtime.util.OperationUtil::isInput);
					parameters.filter(isPrefixed(prefix)).map(propose(context)).forEach(acceptor::accept);
				});
			}
		}
	}

	@Override
	public void completeReplyMessage_Operation(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if (message.getMessageSort() != MessageSort.REPLY_LITERAL) {
				return;
			}

			Stream<Operation> operations = MessageUtil.getRepliedOperations(message);
			operations.filter(isPrefixed(prefix)).map(propose(context)).forEach(acceptor::accept);
		}
	}

	@Override
	public void completeAssignmentTarget_Target(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if ((message.getSignature() instanceof Operation)
					&& (message.getMessageSort() != MessageSort.REPLY_LITERAL)) {
				return;
			}

			ReplyMessage replyMessage = EcoreUtil2.getContainerOfType(context.getCurrentModel(),
					ReplyMessage.class);
			if (replyMessage != null) {
				Stream<ConnectableElement> targets = MessageUtil.getAssignableTargets(message);
				targets.filter(isPrefixed(prefix)).map(proposeQualified(message, context))
						.forEach(acceptor::accept);
			}
		}
	}

	@Override
	public void completeMessageReplyOutput_Parameter(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

		String prefix = context.getPrefix();
		EObject obj = ContextElementUtil.getContextElement(context.getResource());
		if (obj instanceof Message) {
			Message message = (Message) obj;
			if ((message.getSignature() instanceof Operation)
					&& (message.getMessageSort() != MessageSort.REPLY_LITERAL)) {
				return;
			}

			ReplyMessage replyMessage = EcoreUtil2.getContainerOfType(context.getCurrentModel(),
					ReplyMessage.class);
			if (replyMessage != null) {
				Stream<Parameter> parameters = MessageUtil.getAssignableOutputs(message,
						replyMessage.getName());
				parameters.filter(isPrefixed(prefix)).map(propose(context)).forEach(acceptor::accept);
			}
		}
	}

	private Optional<Signal> getSignal(Message message, RequestMessage requestMessage) {
		Optional<Signal> result = Optional.ofNullable(requestMessage.getSignal());

		if (!result.isPresent()) {
			result = Optional.of(message).map(Message::getSignature).filter(Signal.class::isInstance)
					.map(Signal.class::cast)
					.filter(s -> (s.getName() != null) && s.getName().equals(requestMessage.getName()));
		}

		if (!result.isPresent()) {
			result = CompletionUtil.findSignal(message, requestMessage.getName(), Collections.emptyList());
		}

		return result;
	}

	private Optional<Operation> getOperation(Message message, RequestMessage requestMessage) {
		Optional<Operation> result = Optional.ofNullable(requestMessage.getOperation());

		if (!result.isPresent()) {
			result = Optional.of(message).map(Message::getSignature).filter(Operation.class::isInstance)
					.map(Operation.class::cast)
					.filter(s -> (s.getName() != null) && s.getName().equals(requestMessage.getName()));
		}

		if (!result.isPresent()) {
			result = CompletionUtil.findOperation(message, requestMessage.getName(), requestMessage);
		}

		return result;
	}

	private static Function<NamedElement, ICompletionProposal> propose(ContentAssistContext context) {
		return element -> CompletionProposalUtils.createCompletionProposalWithReplacementOfPrefix(element,
				element.getName(), label(element), context);
	}

	private static Function<NamedElement, ICompletionProposal> proposeQualified(NamedElement relativeTo,
			ContentAssistContext context) {
		return element -> CompletionProposalUtils.createCompletionProposalWithReplacementOfPrefix(element,
				NamedElementUtil.getQualifiedName(element, relativeTo), label(element), context);
	}

	private static Predicate<NamedElement> isPrefixed(String prefix) {
		if (UML2Util.isEmpty(prefix)) {
			return __ -> true;
		}
		return element -> (element.getName() != null) && element.getName().startsWith(prefix);
	}

	private static String label(NamedElement element) {
		String result = new UMLSwitch<String>() {
			@Override
			public String caseOperation(Operation operation) {
				return OperationUtil.getCustomLabel(operation, MASK_VALUES__OPERATION);
			}

			@Override
			public String caseSignal(Signal signal) {
				return signal.getName();
			}

			@Override
			public String caseProperty(Property property) {
				return PropertyUtil.getCustomLabel(property, MASK_VALUES__ATTRIBUTE);
			}

			@Override
			public String caseParameter(Parameter parameter) {
				return ParameterUtil.getCustomLabel(parameter, MASK_VALUES__PARAMETER);
			}

			@Override
			public String caseNamedElement(NamedElement element) {
				return element.getName();
			}
		}.doSwitch(element);

		if (result != null) {
			// The mask-label utilities prepend whitespace that we don't want
			result = result.trim();
		}

		return result;
	}
}
