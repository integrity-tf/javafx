package de.gebit.integrity.bindings.javafx;

import javafx.stage.Stage;

/**
 * Checks whether a stage is a requested one.
 */
interface StageDecider {

	boolean isRequestedStage(Stage s);
}