/*******************************************************************************
 * Copyright (c) 2013 GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.gebit.integrity.bindings.javafx;

import javafx.stage.Stage;

/**
 * Checks whether a stage is a requested one.
 */
interface StageDecider {

	boolean isRequestedStage(Stage s);
}