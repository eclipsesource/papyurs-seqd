/*
 * generated by Xtext
 */
package org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.scoping;

import static org.eclipse.uml2.common.util.UML2Util.isEmpty;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.AssignmentTarget;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.MessageArgument;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.MessageReplyOutput;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.ReplyMessage;
import org.eclipse.papyrus.uml.diagram.lightweightsequence.message.xtext.lwMessage.RequestMessage;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.util.MessageUtil;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.util.NamedElementUtil;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.util.OperationUtil;
import org.eclipse.papyrus.uml.xtext.integration.core.ContextElementUtil;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

import com.google.common.base.Function;

/**
 * This class contains custom scoping description.
 *
 * See
 * https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
public class LwMessageScopeProvider extends AbstractDeclarativeScopeProvider {

	public IScope scope_RequestMessage_signal(RequestMessage requestMessage, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(requestMessage.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			List<Signal> available = MessageUtil.getReceivableSignals(message).collect(Collectors.toList());
			return Scopes.scopeFor(available);
		}
		return IScope.NULLSCOPE;
	}

	public IScope scope_RequestMessage_operation(RequestMessage requestMessage, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(requestMessage.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			List<Operation> available = MessageUtil.getCallableOperations(message)
					.collect(Collectors.toList());
			return Scopes.scopeFor(available);
		}
		return IScope.NULLSCOPE;
	}

	/**
	 * Xtext looks up the {@link MessageArgument#getProperty()
	 * MessageArgument::property} in the context of the containing
	 * {@link RequestMessage}.
	 *
	 * @param requestMessage
	 *            the contextual request message
	 * @param reference
	 *            the {@code MessageArgument::property} reference
	 *
	 * @return the scope of valid signal attributes to link to
	 */
	public IScope scope_MessageArgument_property(RequestMessage requestMessage, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(requestMessage.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			List<Signal> signals = MessageUtil.getReceivableSignals(message).collect(Collectors.toList());
			if (!signals.isEmpty()) {
				// Filter for partial matches
				String messageName = requestMessage.getName();
				if (!isEmpty(messageName)) {
					signals.removeIf(signal -> (signal.getName() != null)
							&& !signal.getName().startsWith(messageName));
				}
			}
			List<Property> available = signals.stream()
					.flatMap(signal -> signal.getAllAttributes().stream()).collect(Collectors.toList());
			return Scopes.scopeFor(available);
		}
		return IScope.NULLSCOPE;
	}

	/**
	 * Xtext looks up the {@link MessageArgument#getParameter()
	 * MessageArgument::parameter} in the context of the containing
	 * {@link RequestMessage}.
	 *
	 * @param requestMessage
	 *            the contextual request message
	 * @param reference
	 *            the {@code MessageArgument::parameter} reference
	 *
	 * @return the scope of valid operation parameters to link to
	 */
	public IScope scope_MessageArgument_parameter(RequestMessage requestMessage, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(requestMessage.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			List<Operation> operations = MessageUtil.getCallableOperations(message)
					.collect(Collectors.toList());
			if (!operations.isEmpty()) {
				String messageName = requestMessage.getName();
				if (!isEmpty(messageName)) {
					operations.removeIf(
							op -> (op.getName() != null) && !op.getName().startsWith(messageName));
				}
			}
			List<Parameter> available = operations.stream().flatMap(op -> op.getOwnedParameters().stream())
					.filter(OperationUtil::isInput).collect(Collectors.toList());
			return Scopes.scopeFor(available);
		}
		return IScope.NULLSCOPE;
	}

	public IScope scope_ReplyMessage_operation(RequestMessage requestMessage, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(requestMessage.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			List<Operation> available = MessageUtil.getRepliedOperations(message)
					.collect(Collectors.toList());
			return Scopes.scopeFor(available);
		}
		return IScope.NULLSCOPE;
	}

	public IScope scope_AssignmentTarget_target(AssignmentTarget assignmentTarget, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(assignmentTarget.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			List<ConnectableElement> available = MessageUtil.getAssignableTargets(message)
					.collect(Collectors.toList());
			return Scopes.scopeFor(available, nameQualifiedRelativeTo(message), IScope.NULLSCOPE);
		}

		return IScope.NULLSCOPE;
	}

	public IScope scope_MessageReplyOutput_parameter(MessageReplyOutput output, EReference reference) {
		EObject contextElement = ContextElementUtil.getContextElement(output.eResource());
		if (contextElement instanceof Message) {
			Message message = (Message) contextElement;
			ReplyMessage reply = EcoreUtil2.getContainerOfType(output, ReplyMessage.class);
			if (reply != null) {
				List<Parameter> available = MessageUtil.getAssignableOutputs(message, reply.getName())
						.collect(Collectors.toList());
				return Scopes.scopeFor(available);
			}
		}

		return IScope.NULLSCOPE;
	}

	/**
	 * Obtains a qualified name function that provides names qualified relative to
	 * some {@code referring} element.
	 *
	 * @param referring
	 *            the element relative to which names are to be qualified
	 * @return the relative qualified name computation
	 */
	protected Function<NamedElement, QualifiedName> nameQualifiedRelativeTo(NamedElement referring) {
		Pattern split = Pattern.compile(Pattern.quote(NamedElement.SEPARATOR));

		return element -> {
			String result = NamedElementUtil.getQualifiedName(element, referring);
			return isEmpty(result) ? QualifiedName.EMPTY : QualifiedName.create(split.split(result));
		};
	}
}
