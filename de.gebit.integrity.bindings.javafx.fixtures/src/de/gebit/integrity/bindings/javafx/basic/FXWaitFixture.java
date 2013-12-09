package de.gebit.integrity.bindings.javafx.basic;

import java.util.concurrent.TimeUnit;

import javafx.scene.Node;
import de.gebit.integrity.bindings.javafx.AbstractFXControlFixture;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

public class FXWaitFixture  extends AbstractFXControlFixture {
	
	@FixtureMethod
	public void simpleWait(@FixtureParameter(name = "seconds")Long someSeconds) {
		try {
			TimeUnit.SECONDS.sleep(someSeconds);
		} catch (InterruptedException e) {
			// ignore
		}
	}
	
	@FixtureMethod
	public void waitForNodeFocused(@FixtureParameter(name = "id")String anId, @FixtureParameter(name = "timeout")Long aTimeout) {
		
		long nanoTime = System.nanoTime();
		long doneTime = nanoTime + (aTimeout * 1000000L);

		Node found;
		do {
			found = (Node) findComponent(anId);
			if (found != null) {
				if (found.isFocusTraversable()) {
					System.out.println("focused");
					return;
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// ignore
			}
		} while (doneTime - System.nanoTime() > 0);
		System.out.println("done waiting");
	}
	
	@FixtureMethod
	public void waitForStage(@FixtureParameter(name = "timeout")Long aTimeout) {
		long nanoTime = System.nanoTime();
		long doneTime = nanoTime + (aTimeout * 1000000L);

		do {
			
			if (findPrimaryRoot() != null) {
				System.out.println("found");
				return;
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// ignore
			}
		} while (doneTime - System.nanoTime() > 0);
		System.out.println("done waiting");
		
		
	}

}
