package de.gebit.integrity.bindings.javafx.basic;

import javafx.scene.control.TitledPane;
import de.gebit.integrity.bindings.javafx.AbstractFXControlFixture;
import de.gebit.integrity.bindings.javafx.FXRunnable;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

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
