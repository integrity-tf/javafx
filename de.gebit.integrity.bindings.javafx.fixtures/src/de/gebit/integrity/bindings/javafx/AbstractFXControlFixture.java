package de.gebit.integrity.bindings.javafx;

import java.util.Iterator;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.Window;

/*******************************************************************************
 * Copyright (c) 2013 GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
public class AbstractFXControlFixture {

    /**
     * Synchronizes between java fx thread and the current test thread.
     * @param runn
     */
	protected void doInFXThread(final FXRunnable runn) {
		final Synchronizer sync = new Synchronizer();

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				runn.doInFXThread();
				sync.fired = true;
			}
		});

		// wait until fired
		while (!sync.fired) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException exc) {
				// ignore
			}
		}
	}

	/**
	 * Finds a component of the given class type.
	 * @param anId
	 * @param aClass
	 * @return
	 */
	protected Object findComponentOfType(String anId, @SuppressWarnings("rawtypes") Class aClass) {
		Parent root = findPrimaryRoot();
		return findNode(anId, root, aClass);
	}
	
	/**
	 * Finds a component of the given class type, returns an exception if it could not find the component.
	 * @param anId
	 * @param aClass
	 * @return
	 */
	protected Object findComponentOfTypeWithException(String anId, @SuppressWarnings("rawtypes") Class aClass) {
	    Object control = findComponentOfType(anId, aClass);
	    if (control == null) {
            throw new IllegalArgumentException("Control with id " + anId + " not found!");
        }
	    return control;
	}

	protected Parent findPrimaryRoot() {
		Stage primStage = findPrimaryStageByOpenedWindow();

		if (primStage == null || primStage.getScene() == null
				|| primStage.getScene().getRoot() == null) {
			return null;
		}
		return primStage.getScene().getRoot();
	}

	protected Object findComponent(String anId) {
		return findComponentOfType(anId, null);
	}

	/**
	 * Finds the primary stage by checking the windows title.
	 * @param aTitle
	 * @return
	 */
	protected Stage findPrimaryStageByTitle(final String aTitle) {
		return findPrimaryStage(new StageDecider() {

			@Override
			public boolean isRequestedStage(Stage s) {
				return aTitle.equals(s.getTitle());
			}
		});
	}

	/**
	 * Finds the primary stage by checking whether a window is currently shown.
	 * @return
	 */
	protected Stage findPrimaryStageByOpenedWindow() {
		return findPrimaryStage(new StageDecider() {

			@Override
			public boolean isRequestedStage(Stage s) {
				return s.isShowing();
			}
		});
	}

	/**
	 * Finds the primary stage of this JavaFX container.
	 * @param sd a decider that checks whether a stage is a primary stage.
	 * @return
	 */
	protected Stage findPrimaryStage(StageDecider sd) {
		Iterator<Window> windows = Window.impl_getWindows();
		while (windows.hasNext()) {
			javafx.stage.Window nextWin = windows.next();

			// nextWin.isShowing()

			if (nextWin instanceof Stage) {
				Stage st = (Stage) nextWin;
				if (sd.isRequestedStage(st)) {
					return st;
				}
			}
		}
		return null;
	}

	/**
	 * Will find a node for a given id.
	 * 
	 * @param anId
	 * @param aParent
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
    protected Node findNode(String anId, Parent aParent, Class aClass) {
		for (Node n : aParent.getChildrenUnmodifiable()) {

			if (anId.equals(n.getId())) {
				if (aClass == null) { // basic case if called with aClass = null
					return n;
				} else { // if a special class is required
					if (aClass.isAssignableFrom(n.getClass())) {
						return n;
					}
				}
			}

			if (n instanceof Parent) {
				Node found = findNode(anId, (Parent) n, aClass);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}

}
