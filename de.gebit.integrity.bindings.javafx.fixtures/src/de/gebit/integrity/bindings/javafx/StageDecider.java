package de.gebit.integrity.bindings.javafx;

import javafx.stage.Stage;

interface StageDecider {

	boolean isRequestedStage(Stage s);
}