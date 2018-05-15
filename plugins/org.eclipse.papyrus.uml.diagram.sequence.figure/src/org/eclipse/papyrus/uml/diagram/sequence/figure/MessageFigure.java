/*****************************************************************************
 * Copyright (c) 2018 EclipseSource and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - Initial API and implementation
 *   
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.figure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.papyrus.uml.diagram.sequence.figure.anchors.MessageSourceAnchor;
import org.eclipse.papyrus.uml.diagram.sequence.figure.anchors.MessageTargetAnchor;

/**
 * Figure specific for messages (with a by default
 */
public class MessageFigure extends PolylineConnectionEx {

	private static final String ANCHOR_START_TERMINAL = "start"; //$NON-NLS-1$

	private static final String ANCHOR_END_TERMINAL = "end"; //$NON-NLS-1$

	@Override
	public ConnectionAnchor getConnectionAnchor(String terminal) {
		if (terminal == null || terminal.isEmpty()) {
			return super.getConnectionAnchor(terminal);
		}

		switch (terminal) {
			case ANCHOR_START_TERMINAL:
				return new MessageSourceAnchor(this);
			case ANCHOR_END_TERMINAL:
				return new MessageTargetAnchor(this);
		}

		return super.getConnectionAnchor(terminal);
	}

}
