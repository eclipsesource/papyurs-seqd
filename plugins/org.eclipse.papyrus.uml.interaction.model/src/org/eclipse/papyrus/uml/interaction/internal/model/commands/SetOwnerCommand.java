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

package org.eclipse.papyrus.uml.interaction.internal.model.commands;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.papyrus.uml.interaction.internal.model.impl.MElementImpl;
import org.eclipse.papyrus.uml.interaction.model.MElement;
import org.eclipse.papyrus.uml.interaction.model.MExecution;
import org.eclipse.papyrus.uml.interaction.model.MLifeline;
import org.eclipse.papyrus.uml.interaction.model.MOccurrence;
import org.eclipse.papyrus.uml.interaction.model.util.Optionals;
import org.eclipse.papyrus.uml.interaction.model.util.SequenceDiagramSwitch;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Assignment of the owner of an element.
 */
public class SetOwnerCommand extends ModelCommandWithDependencies<MElementImpl<? extends Element>> {

	private final MElement<? extends Element> newOwner;

	private final OptionalInt yPosition;

	/**
	 * Initializes me.
	 *
	 * @param target
	 */
	public SetOwnerCommand(MElementImpl<? extends Element> element, MElement<? extends Element> newOwner,
			OptionalInt yPosition) {
		super(element);

		this.newOwner = newOwner;
		this.yPosition = yPosition;
	}

	protected boolean isChangingOwner() {
		MElement<?> owner = getTarget().getOwner();
		return owner.getElement() != newOwner.getElement();
	}

	protected boolean isChangingPosition() {
		return yPosition.isPresent() && !getTarget().getTop().equals(yPosition);
	}

	@Override
	protected Command doCreateCommand() {
		return new SequenceDiagramSwitch<Command>() {
			@Override
			public Command caseMExecution(MExecution execution) {
				return createCommand(execution, (MLifeline)newOwner);
			}

			@Override
			public Command defaultCase(EObject object) {
				return UnexecutableCommand.INSTANCE;
			}
		}.doSwitch((EObject)getTarget());
	}

	protected Command createCommand(MExecution execution, MLifeline lifeline) {
		InteractionFragment fragment = execution.getElement();

		Command result = isChangingOwner()
				? semanticHelper().set(fragment, UMLPackage.Literals.INTERACTION_FRAGMENT__COVERED,
						// Set the entire covereds list to be just this lifeline
						// (executions only cover one lifeline)
						singletonList(lifeline.getElement()))
				: IdentityCommand.INSTANCE;

		// Handle the notation before dependencies so that dependencies will see the notation update
		Optional<Shape> executionShape = execution.getDiagramView();
		Optional<Shape> lifelineHead = lifeline.getDiagramView();
		if (executionShape.isPresent() && lifelineHead.isPresent()) {
			Shape lifelineView = diagramHelper().getLifelineBodyShape(lifelineHead.get());
			int newYPosition = yPosition.orElseGet(() -> execution.getTop().getAsInt());

			if (isChangingOwner()) {
				// Move the execution shape
				result = chain(result, diagramHelper().reparentView(executionShape.get(), lifelineView));
			}

			if (isChangingPosition() || isChangingOwner()) {
				// Position it, as the relative position of the execution may now be different according to
				// the new lifeline's creation position
				result = chain(result, layoutHelper().setTop(executionShape.get(), newYPosition));
			}
		}

		// Handle dependent occurrences and other elements
		result = dependencies(execution, lifeline).map(chaining(result)).orElse(result);

		return result;
	}

	protected Optional<Command> dependencies(MExecution execution, MLifeline lifeline) {
		List<Command> result = new ArrayList<>();

		// Occurrences spanned by the execution, including its start and finish. They move
		// according to the execution, maintaining their relative position. Note that
		// nested executions will be handled implicitly by either their start or finish
		OptionalInt currentTop = execution.getTop();
		OptionalInt deltaY = currentTop.isPresent() && yPosition.isPresent()
				? OptionalInt.of(yPosition.getAsInt() - currentTop.getAsInt())
				: OptionalInt.empty();
		Function<MOccurrence<? extends Element>, OptionalInt> yMapping = deltaY.isPresent()
				? occ -> Optionals.map(occ.getTop(), y -> y + deltaY.getAsInt())
				: __ -> OptionalInt.empty();
		execution.getOccurrences().stream().map(occ -> occ.setCovered(lifeline, yMapping.apply(occ)))
				.filter(Objects::nonNull).forEach(result::add);

		return result.stream().reduce(chaining());
	}

}