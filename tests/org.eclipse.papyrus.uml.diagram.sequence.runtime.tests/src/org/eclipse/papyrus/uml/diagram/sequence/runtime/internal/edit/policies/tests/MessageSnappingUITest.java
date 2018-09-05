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

package org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.tests;

import static org.eclipse.papyrus.uml.diagram.sequence.figure.magnets.IMagnetManager.MODIFIER_NO_SNAPPING;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.tests.matchers.GEFMatchers.EditParts.runs;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.tests.rules.EditorFixture.at;
import static org.eclipse.papyrus.uml.diagram.sequence.runtime.tests.rules.EditorFixture.sized;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeThat;

import java.util.Arrays;
import java.util.function.Function;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.commands.wrappers.GMFtoGEFCommandWrapper;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.edit.policies.LifelineBodyGraphicalNodeEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.providers.SequenceElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.tests.rules.EditorFixture;
import org.eclipse.papyrus.uml.diagram.sequence.runtime.tests.rules.Maximized;
import org.eclipse.papyrus.uml.interaction.tests.rules.ModelResource;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.UMLPackage;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Integration test cases for the {@link LifelineBodyGraphicalNodeEditPolicy}
 * class's message re-connection behaviour.
 *
 * @author Christian W. Damus
 */
@SuppressWarnings("restriction")
@ModelResource("two-lifelines.di")
@Maximized
@RunWith(Parameterized.class)
public class MessageSnappingUITest extends AbstractGraphicalEditPolicyUITest {
	// Horizontal position of the first lifeline's body
	private static final int LL1_BODY_X = 121;

	// Horizontal position of the second lifeline's body
	private static final int LL2_BODY_X = 281;

	private static final boolean EXEC_START = true;
	private static final int EXEC_START_Y = 145;

	private static final boolean EXEC_FINISH = false;
	private static final int EXEC_HEIGHT = 60;
	private static final int EXEC_FINISH_Y = EXEC_START_Y + EXEC_HEIGHT;

	private final EditorFixture.Modifiers modifiers;
	private final Function<Matcher<?>, Matcher<?>> modifiersMatcherFunction;
	private ExecutionSpecification exec;

	/**
	 * Initializes me.
	 *
	 * @param withSnap
	 *            whether to allow snapping ({@code true}) or suppress it
	 *            ({@code false})
	 * @param snapString
	 *            a string representation of {@code withSnap}
	 */
	public MessageSnappingUITest(boolean withSnap, String snapString) {
		super();

		this.modifiers = withSnap ? editor.unmodified() : editor.modifierKey(MODIFIER_NO_SNAPPING);
		modifiersMatcherFunction = withSnap ? CoreMatchers::is : CoreMatchers::not;
	}

	@Test
	public void createSyncCallMessage() {
		EditPart messageEP = editor.with(modifiers,
				() -> createConnection(SequenceElementTypes.Sync_Message_Edge,
						at(LL1_BODY_X, withinMagnet(EXEC_START)),
						at(LL2_BODY_X, withinMagnet(EXEC_START))));

		// The receiving end snaps to the exec start and the sending end matches
		assertThat(messageEP, withModifiers(runs(LL1_BODY_X, EXEC_START_Y, LL2_BODY_X, EXEC_START_Y, 1)));

		// The message receive event starts the execution
		Message message = (Message) messageEP.getAdapter(EObject.class);
		assertThat(exec.getStart(), withModifiers(is(message.getReceiveEvent())));
	}

	@Test
	public void createAsyncCallMessage() {
		EditPart messageEP = editor.with(modifiers,
				() -> createConnection(SequenceElementTypes.Async_Message_Edge, at(LL1_BODY_X, 120),
						at(LL2_BODY_X, withinMagnet(EXEC_START))));

		// The receiving end snaps to the exec start. The sending end doesn't match
		assertThat(messageEP, withModifiers(runs(LL1_BODY_X, 120, LL2_BODY_X, EXEC_START_Y, 1)));

		// The message receive event starts the execution
		Message message = (Message) messageEP.getAdapter(EObject.class);
		assertThat(exec.getStart(), withModifiers(is(message.getReceiveEvent())));
	}

	@Test
	public void createReplyMessage() {
		EditPart messageEP = editor.with(modifiers,
				() -> createConnection(SequenceElementTypes.Reply_Message_Edge,
						at(LL2_BODY_X, withinMagnet(EXEC_FINISH)),
						at(LL1_BODY_X, withinMagnet(EXEC_FINISH))));

		// The sending end snaps to the exec start and the receiving end matches
		assertThat(messageEP, withModifiers(runs(LL2_BODY_X, EXEC_FINISH_Y, LL1_BODY_X, EXEC_FINISH_Y, 1)));

		// The message send event finishes the execution
		Message message = (Message) messageEP.getAdapter(EObject.class);
		assertThat(exec.getFinish(), withModifiers(is(message.getSendEvent())));
	}

	@Test
	public void moveSyncCallMessage() {
		EditPart messageEP = createConnection(SequenceElementTypes.Sync_Message_Edge, at(LL1_BODY_X, 120),
				at(LL2_BODY_X, 120));
		assumeThat(messageEP, runs(LL1_BODY_X, 120, LL2_BODY_X, 120));

		ensureMessageEndsFirst(messageEP);

		// The receiving end snaps to the exec start and the sending end matches
		final int midMessage = (LL1_BODY_X + LL2_BODY_X) / 2;
		editor.with(modifiers,
				() -> editor.moveSelection(at(midMessage, 120), at(midMessage, withinMagnet(EXEC_START))));
		assertThat(messageEP, withModifiers(runs(LL1_BODY_X, EXEC_START_Y, LL2_BODY_X, EXEC_START_Y, 1)));
	}

	@Ignore("Logical model not calculating bottom of execution correctly.")
	@Test
	public void moveReplyMessage() {
		EditPart messageEP = createConnection(SequenceElementTypes.Reply_Message_Edge, at(LL2_BODY_X, 240),
				at(LL1_BODY_X, 240));
		assumeThat(messageEP, runs(LL2_BODY_X, 240, LL1_BODY_X, 240));

		ensureMessageEndsLast(messageEP);

		// The sending end snaps to the exec start and the receiving end matches
		final int midMessage = (LL1_BODY_X + LL2_BODY_X) / 2;
		editor.with(modifiers,
				() -> editor.moveSelection(at(midMessage, 240), at(midMessage, withinMagnet(EXEC_FINISH))));
		assertThat(messageEP, withModifiers(runs(LL2_BODY_X, EXEC_FINISH_Y, LL1_BODY_X, EXEC_FINISH_Y, 1)));
	}

	/**
	 * Verify that magnets are updated when the size of an execution occurrence
	 * changes.
	 */
	@Test
	public void magnetUpdatesOnSize() {
		assumeThat("Only makes sense with snapping enabled", "magnets suppressed",
				withModifiers(is("magnets suppressed")));

		// First, extend the bottom edge of the execution specification
		int newBottomY = EXEC_FINISH_Y + 100;
		editor.moveSelection(at(LL2_BODY_X, EXEC_FINISH_Y), at(LL2_BODY_X, newBottomY));

		EditPart messageEP = editor.with(modifiers,
				() -> createConnection(SequenceElementTypes.Reply_Message_Edge, //
						at(LL2_BODY_X, withinMagnet(newBottomY, EXEC_FINISH)),
						at(LL1_BODY_X, withinMagnet(newBottomY, EXEC_FINISH))));
		assertThat("No snap: infer that magnet not moved", messageEP,
				runs(LL2_BODY_X, newBottomY, LL1_BODY_X, newBottomY, 1));
	}

	/**
	 * Verify that magnets are updated when the location of an execution occurrence
	 * changes.
	 */
	@Test
	public void magnetUpdatesOnLocation() {
		assumeThat("Only makes sense with snapping enabled", "magnets suppressed",
				withModifiers(is("magnets suppressed")));

		// First, move the execution specification down. The edit-part doesn't (yet)
		// have a policy for moving it (only for resize) so be direct about it instead
		// of automating the selection tool
		EditPart execEP = getLastCreatedEditPart();
		Node execView = (Node) execEP.getModel();
		Location location = (Location) execView.getLayoutConstraint();
		SetBoundsCommand command = new SetBoundsCommand(editor.getDiagramEditPart().getEditingDomain(),
				"Move execution down", new EObjectAdapter(execView),
				at(location.getX(), location.getY() + 100));
		editor.getDiagramEditPart().getDiagramEditDomain().getDiagramCommandStack()
				.execute(GMFtoGEFCommandWrapper.wrap(command));

		int newTopY = EXEC_START_Y + 100;

		EditPart messageEP = editor.with(modifiers,
				() -> createConnection(SequenceElementTypes.Reply_Message_Edge, //
						at(LL2_BODY_X, withinMagnet(newTopY, EXEC_START)),
						at(LL1_BODY_X, withinMagnet(newTopY, EXEC_START))));
		assertThat("No snap: infer that magnet not moved", messageEP,
				runs(LL2_BODY_X, newTopY, LL1_BODY_X, newTopY, 1));
	}

	//
	// Test framework
	//

	@Parameters(name = "{1}")
	public static Iterable<Object[]> parameters() {
		return Arrays.asList(new Object[][] { //
				{ true, "snap to magnet" }, //
				{ false, "suppress snapping" }, //
		});
	}

	@Before
	public void createExecutionSpecification() {
		EditPart exec = createShape(SequenceElementTypes.Behavior_Execution_Shape,
				at(LL2_BODY_X, EXEC_START_Y), sized(0, EXEC_HEIGHT));
		assumeThat("Execution specification not created", exec, notNullValue());

		this.exec = (ExecutionSpecification) exec.getAdapter(EObject.class);
	}

	@SuppressWarnings("unchecked")
	<T> Matcher<T> withModifiers(Matcher<T> matcher) {
		return (Matcher<T>) modifiersMatcherFunction.apply(matcher);
	}

	void ensureMessageEndsFirst(EditPart connectionEP) {
		Command adjust = null;

		EditingDomain domain = editor.getEditingDomain();
		Message message = (Message) connectionEP.getAdapter(EObject.class);
		InteractionFragment send = (InteractionFragment) message.getSendEvent();
		InteractionFragment recv = (InteractionFragment) message.getReceiveEvent();
		Interaction interaction = message.getInteraction();

		int sendIndex = interaction.getFragments().indexOf(send);
		if (sendIndex != 0) {
			Command move = MoveCommand.create(domain, interaction,
					UMLPackage.Literals.INTERACTION__FRAGMENT, send, 0);
			adjust = move;
		}
		int recvIndex = interaction.getFragments().indexOf(recv);
		if (recvIndex != 1) {
			Command move = MoveCommand.create(domain, interaction,
					UMLPackage.Literals.INTERACTION__FRAGMENT, recv, 1);
			if (adjust == null) {
				adjust = move;
			} else {
				adjust = adjust.chain(move);
			}
		}
		if (adjust != null) {
			domain.getCommandStack().execute(adjust);
		}
	}

	void ensureMessageEndsLast(EditPart connectionEP) {
		Command adjust = null;

		EditingDomain domain = editor.getEditingDomain();
		Message message = (Message) connectionEP.getAdapter(EObject.class);
		InteractionFragment send = (InteractionFragment) message.getSendEvent();
		InteractionFragment recv = (InteractionFragment) message.getReceiveEvent();
		Interaction interaction = message.getInteraction();
		int secondLast = interaction.getFragments().size() - 2;
		int last = interaction.getFragments().size() - 1;

		int sendIndex = interaction.getFragments().indexOf(send);
		if (sendIndex != secondLast) {
			Command move = MoveCommand.create(domain, interaction,
					UMLPackage.Literals.INTERACTION__FRAGMENT, send, secondLast);
			adjust = move;
		}
		int recvIndex = interaction.getFragments().indexOf(recv);
		if (recvIndex != last) {
			Command move = MoveCommand.create(domain, interaction,
					UMLPackage.Literals.INTERACTION__FRAGMENT, recv, last);
			if (adjust == null) {
				adjust = move;
			} else {
				adjust = adjust.chain(move);
			}
		}
		if (adjust != null) {
			domain.getCommandStack().execute(adjust);
			editor.flushDisplayEvents();
		}
	}

	static int withinMagnet(boolean execStart) {
		return withinMagnet(execStart ? EXEC_START_Y : EXEC_FINISH_Y, execStart);
	}

	static int withinMagnet(int y, boolean execStart) {
		return execStart ? y - 9 : y + 9;
	}
}