/*****************************************************************************
 * Copyright (c) 2018 Christian W. Damus and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies;

import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.getOriginalMouseLocation;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.getOriginalSourceLocation;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.getOriginalTargetLocation;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.setForce;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.setOriginalMouseLocation;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.setOriginalSourceLocation;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.PrivateRequestUtils.setOriginalTargetLocation;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.MessageFeedbackHelper.Mode;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.util.MessageUtil;
import org.eclipse.uml2.uml.Message;

/**
 * Endpoint edit-policy for management of message ends.
 */
public class MessageEndpointEditPolicy extends ConnectionEndpointEditPolicy {

	// Last known mouse location on the connection in case of move gesture
	private Point lastMouseLocation;

	// Keeping track of original anchors for move feed-back (which moves both anchors)
	private ConnectionAnchor originalSource;

	private ConnectionAnchor originalTarget;

	private MessageFeedbackHelper feedbackHelper;

	/**
	 * Initializes me.
	 */
	public MessageEndpointEditPolicy() {
		super();
	}

	@Override
	public Command getCommand(Request request) {
		if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			return getMoveConnectionCommand((BendpointRequest)request);
		} else {
			return super.getCommand(request);
		}
	}

	/**
	 * Constrain the alignment of ends as appropriate to the message sort.
	 */
	@Override
	protected FeedbackHelper getFeedbackHelper(ReconnectRequest request) {
		if (feedbackHelper == null) {
			Message message = (Message)((IGraphicalEditPart)getHost()).resolveSemanticElement();
			boolean synch = MessageUtil.isSynchronous(message.getMessageSort());
			boolean source = request.isMovingStartAnchor();

			feedbackHelper = new MessageFeedbackHelper(source ? Mode.MOVE_SOURCE : Mode.MOVE_TARGET, synch);
			feedbackHelper.setConnection(getConnection());
		}
		return feedbackHelper;
	}

	@Override
	public void showSourceFeedback(Request request) {
		if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			showConnectionMoveFeedback((BendpointRequest)request);
		} else {
			super.showSourceFeedback(request);
		}
	}

	@Override
	public void showTargetFeedback(Request request) {
		if (REQ_SELECTION.equals(request.getType()) || REQ_SELECTION_HOVER.equals(request.getType())) {
			// Capture the mouse location in case of initiation of a move by dragging
			lastMouseLocation = ((LocationRequest)request).getLocation().getCopy();
		}
		super.showTargetFeedback(request);
	}

	@Override
	protected void eraseConnectionMoveFeedback(ReconnectRequest request) {
		super.eraseConnectionMoveFeedback(request);
		lastMouseLocation = null;
		feedbackHelper = null;
	}

	protected void showConnectionMoveFeedback(BendpointRequest request) {
		if (originalSource == null) {
			originalSource = getConnection().getSourceAnchor();
			Point sourceLocation = MessageFeedbackHelper.getLocation(originalSource);
			setOriginalSourceLocation(request, sourceLocation);
		}
		if (originalTarget == null) {
			originalTarget = getConnection().getTargetAnchor();
			Point targetLocation = MessageFeedbackHelper.getLocation(originalTarget);
			setOriginalTargetLocation(request, targetLocation);
		}

		FeedbackHelper helper = getFeedbackHelper(request);
		helper.setMovingStartAnchor(true);
		helper.update(getConnection().getSourceAnchor(), request.getLocation());
		helper.setMovingStartAnchor(false);
		helper.update(getConnection().getTargetAnchor(), request.getLocation());
	}

	@Override
	public void eraseSourceFeedback(Request request) {
		if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			eraseConnectionMoveFeedback((BendpointRequest)request);
		} else {
			super.eraseSourceFeedback(request);
		}
	}

	/**
	 * @param request
	 */
	protected void eraseConnectionMoveFeedback(BendpointRequest request) {
		if (originalSource == null) {
			return;
		}

		getConnection().setSourceAnchor(originalSource);
		getConnection().setTargetAnchor(originalTarget);

		lastMouseLocation = null;
		originalSource = null;
		originalTarget = null;
		feedbackHelper = null;
	}

	protected FeedbackHelper getFeedbackHelper(BendpointRequest request) {
		if (feedbackHelper == null) {
			Message message = (Message)((IGraphicalEditPart)getHost()).resolveSemanticElement();
			boolean synch = MessageUtil.isSynchronous(message.getMessageSort());

			feedbackHelper = new MessageFeedbackHelper(Mode.MOVE_BOTH, synch);
			feedbackHelper.setConnection(getConnection());

			Point grabbedAt = lastMouseLocation;
			if (grabbedAt == null) {
				grabbedAt = request.getLocation();
			}

			feedbackHelper.setGrabbedAt(grabbedAt);

			// Record it also in the request for eventual retrieval for the move command
			setOriginalMouseLocation(request, grabbedAt);
		}
		return feedbackHelper;
	}

	/**
	 * Obtain a command to move the message connection according to the {@code request}ed location.
	 * 
	 * @param request
	 *            the bendpoint request (as we use the create bendpoint gesture to grab and move)
	 * @return the move command
	 */
	protected Command getMoveConnectionCommand(BendpointRequest request) {
		CompoundCommand result = new CompoundCommand();

		Point grabbedAt = getOriginalMouseLocation(request);
		int deltaY = grabbedAt == null ? 0 : request.getLocation().y() - grabbedAt.y();
		if (deltaY != 0) {
			ConnectionEditPart connection = (ConnectionEditPart)getHost();
			EditPart source = connection.getSource();
			EditPart target = connection.getTarget();

			// Don't modify the original because we get this command many times
			Point sourceLocation = getOriginalSourceLocation(request).getCopy();
			sourceLocation.translate(0, deltaY);
			ReconnectRequest sourceReq = new ReconnectRequest(REQ_RECONNECT_SOURCE);
			sourceReq.setTargetEditPart(source);
			sourceReq.setConnectionEditPart(connection);
			sourceReq.setLocation(sourceLocation);
			setForce(sourceReq, true);
			Command updateSource = source.getCommand(sourceReq);

			// Don't modify the original because we get this command many times
			Point targetLocation = getOriginalTargetLocation(request).getCopy();
			targetLocation.translate(0, deltaY);
			ReconnectRequest targetReq = new ReconnectRequest(REQ_RECONNECT_TARGET);
			targetReq.setTargetEditPart(target);
			targetReq.setConnectionEditPart(connection);
			targetReq.setLocation(targetLocation);
			setForce(targetReq, true);
			Command updateTarget = target.getCommand(targetReq);

			// Never update just one end
			if (updateSource != null && updateTarget != null) {
				result.add(updateSource);
				result.add(updateTarget);
			}
		}

		return result.unwrap();
	}
}