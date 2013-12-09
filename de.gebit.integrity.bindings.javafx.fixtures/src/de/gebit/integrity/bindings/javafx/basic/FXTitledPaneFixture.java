/*******************************************************************************
 * Copyright (c) 2013 GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.gebit.integrity.bindings.javafx.basic;

import javafx.scene.control.TitledPane;
import de.gebit.integrity.bindings.javafx.AbstractFXControlFixture;
import de.gebit.integrity.bindings.javafx.FXRunnable;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

/**
 * Used to control titled Panes.
 */
public class FXTitledPaneFixture extends AbstractFXControlFixture {

	@FixtureMethod
	public void setExpanded(@FixtureParameter(name = "id")String anId, @FixtureParameter(name = "expanded")final Boolean anExpanded) {
		final TitledPane control = (TitledPane) findComponentOfType(anId,
				TitledPane.class);

		doInFXThread(new FXRunnable() {

			@Override
			public void doInFXThread() {
				control.setExpanded(anExpanded);
			}
		});

	}

}
