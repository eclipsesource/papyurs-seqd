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
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.diagram.model.internal.layout;

import static org.eclipse.papyrus.uml.diagram.sequence.diagram.model.internal.layout.LayoutModelTestUtils.getAllTenPrefs;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class LayoutModelWithFragmentsAutoLayoutTest {

	ModelWithFragmentsFixture fixture;

	@Before
	public void initFixture() {
		fixture = new ModelWithFragmentsFixture();
	}

	@Test
	public void testThatAutomaticPositionYDoesNotViolateSemanticOrder() {
		fixture.interaction.using(getAllTenPrefs());
		fixture.interaction.applyAutoLayout();

		assertTrue(fixture.interaction.areAllYRangesValid());
		assertTrue(fixture.interaction.isLayoutFreeOfGaps());
	}

	@Test
	public void testPrintPositionsAfterAutoLayoutOrder() {
		fixture.interaction.using(getAllTenPrefs());
		fixture.interaction.applyAutoLayout();
		sysoutFixture();
	}

	public void sysoutFixture() {

		System.out.println("LayoutModelWithFragmentsAutoLayoutTest");
		System.out.println("======================================");

		for (MLifeline line : Arrays.asList(fixture.user, fixture.webBrowser, fixture.applicationServer)) {
			System.out.println(line.getPosition());
		}

		System.out.println();

		for (MInteractionElement element : Arrays.asList(fixture.obtainResource, fixture.requestAccess,
				fixture.grantAccess, fixture.redirectRequest, fixture.alternative)) {
			System.out.println(element.getPosition());
		}

		System.out.println();
	}

}
