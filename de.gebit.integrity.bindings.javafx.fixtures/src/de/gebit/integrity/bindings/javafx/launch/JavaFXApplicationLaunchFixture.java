/*******************************************************************************
 * Copyright (c) 2013 GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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
