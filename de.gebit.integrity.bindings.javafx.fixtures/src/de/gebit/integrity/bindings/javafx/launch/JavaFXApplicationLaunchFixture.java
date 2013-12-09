package de.gebit.integrity.bindings.javafx.launch;

import javafx.application.Platform;
import de.gebit.integrity.runner.fixtures.JavaApplicationLaunchFixture;

/**
 * Launch and exits JavaFX applications.
 */
public class JavaFXApplicationLaunchFixture extends
		JavaApplicationLaunchFixture {
	
	@Override
	public boolean kill(ApplicationWrapper aWrapper) {
		Platform.exit();
		return super.kill(aWrapper);
	}

}
