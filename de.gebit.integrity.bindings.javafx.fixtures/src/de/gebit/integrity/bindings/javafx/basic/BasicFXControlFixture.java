package de.gebit.integrity.bindings.javafx.basic;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleButton;
import de.gebit.integrity.bindings.javafx.AbstractFXControlFixture;
import de.gebit.integrity.bindings.javafx.FXRunnable;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

public class BasicFXControlFixture extends AbstractFXControlFixture {

	/**
	 * Clickable - e.g. Button, CheckBox, ToggleButton=>RadioButton
	 */
	@FixtureMethod(description = "Click the Clickable-Control with the Id: $id$ (Button or CheckBox or RadioButton)")
	public void click(@FixtureParameter(name = "id") final String aControlId) {
		final ButtonBase control = (ButtonBase)findComponentOfTypeWithException(aControlId, ButtonBase.class);

		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				try {
					((ButtonBase) control).fire();
				} catch (ClassCastException ex) {
					throw new ClassCastException("Control with id " + aControlId + " is not clickable.");
				}
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------
	/**
	 * isSelected - z.B. CheckBox, ToggleButton=>RadioButton
	 */
	@FixtureMethod(description = "Is the Control with the Id: $id$ checked? (CheckBox or RadioButton)")
	public boolean isChecked(@FixtureParameter(name = "id") String anId) {
		final ButtonBase checkableButtonBase = (ButtonBase) findComponentOfTypeWithException(anId, ButtonBase.class);

		final Boolean result[] = new Boolean[1];
		result[0] = false;
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				if (checkableButtonBase instanceof CheckBox) {
					result[0] = ((CheckBox) checkableButtonBase).isSelected();
				} else if (checkableButtonBase instanceof RadioButton) {
					result[0] = ((ToggleButton) checkableButtonBase).isSelected();
				}
			}
		});
		return result[0].booleanValue();
	}

	// ------------------------------------------------------------------------------------------------------	
	/**
	 * select-Index-Item - z.B. ComboBox, ChoiceBox, TabPane, ListView, TableView
	 */
	@FixtureMethod(description = "Select the index: $index$ from the Control with the Id: $id$ (ComboBox, ChoiceBox, TabPane, ListView, TableView)")
	public void selectIndex(@FixtureParameter(name = "id") final String aConrtrolId, @FixtureParameter(name = "index") final Integer anIdx) {
		final Control controlObject = (Control)findComponentOfTypeWithException(aConrtrolId, Control.class);

		if (controlObject instanceof ComboBox) {
			selectComboBox(aConrtrolId, anIdx, (ComboBox<?>) controlObject);
		} else if (controlObject instanceof ChoiceBox) {
			selectChoiceBox(aConrtrolId, anIdx, (ChoiceBox<?>) controlObject);
		} else if (controlObject instanceof ListView<?>) {
			selectListView(aConrtrolId, anIdx, (ListView<?>) controlObject);
		} else if (controlObject instanceof TableView<?>) {
			selectTableView(aConrtrolId, anIdx, (TableView<?>) controlObject);
		} else if (controlObject instanceof TabPane) {
			selectTabPane(aConrtrolId, anIdx, (TabPane) controlObject);
		} else {
			throw new IllegalArgumentException("Cannot select an Index on the Control: " + aConrtrolId + " because it has a wrong type!");
		}

	}

	private void selectComboBox(final String aConrtrolId, final Integer anIdx, final ComboBox controlObject) {
		ObservableList<?> items = ((ComboBox<?>) controlObject).getItems();
		final Object objToSelect = items.get(anIdx);
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				controlObject.setValue(objToSelect);
			}
		});
	}

	private void selectChoiceBox(final String aConrtrolId, final Integer anIdx, final ChoiceBox controlObject) {
		ObservableList<?> items = ((ChoiceBox<?>) controlObject).getItems();
		final Object objToSelect = items.get(anIdx);
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				controlObject.setValue(objToSelect);
			}
		});
	}
	
	private void selectListView(final String aConrtrolId, final Integer anIdx, final ListView controlObject) {
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				controlObject.getSelectionModel().clearAndSelect(anIdx);
			}
		});
	}
	
	private void selectTableView(final String aConrtrolId, final Integer anIdx, final TableView controlObject) {
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				controlObject.getSelectionModel().clearAndSelect(anIdx);
			}
		});
	}

	private void selectTabPane(final String aConrtrolId, final Integer anIdx, final TabPane controlObject) {
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				if (((TabPane) controlObject).getSelectionModel().getSelectedItem().isDisabled()) {
					throw new RuntimeException("TabPane with id " + aConrtrolId + " is disabled.");
				} else {
					((TabPane) controlObject).getSelectionModel().select(anIdx);
				}
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------
	/**
	 * isEmpty - e.g. ComboBox, ChoiceBox, ListView, TableView
	 */
	@FixtureMethod(description = "is the Control with the Id: $id$ empty? (ComboBox or ChoiceBox or ListView or TableView)")
	public boolean isEmpty(@FixtureParameter(name = "id") final String aConrtrolId) {
		final Control controlObject = (Control)findComponentOfTypeWithException(aConrtrolId, Control.class);

		final Boolean result[] = new Boolean[1];
		result[0] = false;

		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				if (controlObject instanceof ComboBox) {
					result[0] = ((ComboBox<?>) controlObject).getItems().isEmpty();
				} else if (controlObject instanceof ChoiceBox) {
					result[0] = ((ChoiceBox<?>) controlObject).getItems().isEmpty();
				} else if (controlObject instanceof ListView<?>) {
					result[0] = ((ListView<?>) controlObject).getItems().isEmpty();
				} else if (controlObject instanceof TableView<?>) {
					result[0] = ((TableView<?>) controlObject).getItems().isEmpty();
				} else {
					throw new IllegalArgumentException("Control of type id " + aConrtrolId + " is not an not a ComboBox or ChoiceBox or ListView or TableView !");
				}
			}
		});
		return result[0].booleanValue();
	}

	// ------------------------------------------------------------------------------------------------------
	/**
	 * getSelectedItem - e.g. ComboBox, ChoiceBox, ListView, TableView
	 */
	@FixtureMethod(description = "get the Selected-Item from the Control with the Id: $id$ (ComboBox or ChoiceBox or ListView or TableView)")
		public Object getSelecteItem(@FixtureParameter(name = "id") final String aConrtrolId) {
			final Control controlObject = (Control)findComponentOfTypeWithException(aConrtrolId, Control.class);

			final Object selectedItem[] = new Object[1];

			doInFXThread(new FXRunnable() {

				@Override
				public void doInFXThread() {
					if (controlObject instanceof ComboBox) {
						selectedItem[0] = ((ComboBox<?>) controlObject).getSelectionModel().getSelectedItem();
					} else if (controlObject instanceof ChoiceBox) {
						selectedItem[0] = ((ChoiceBox<?>) controlObject).getSelectionModel().getSelectedItem();					
					} else if (controlObject instanceof ListView<?>) {
						selectedItem[0] = ((ListView<?>) controlObject).getSelectionModel().getSelectedItem();				
					} else if (controlObject instanceof TableView<?>) {
						TableView tv = ((TableView<?>) controlObject);
						TableViewSelectionModel selectionModel = tv.getSelectionModel();
						Object selectedItem2 = selectionModel.getSelectedItem();
						selectedItem[0] = selectedItem2;	
					} else {
						throw new IllegalArgumentException("Control with id " + aConrtrolId + " is not an not a ComboBox or ChoiceBox or ListView or TableView!");
					}
				}
			});

			return selectedItem[0];
		}
	// ------------------------------------------------------------------------------------------------------
		/**
		 * containsItem - e.g. ComboBox, ChoiceBox, ListView, TableView
		 */
		@FixtureMethod(description = "Does the Control with the Id: $id$ contains the Item: $aItem$ (ComboBox or ChoiceBox or ListView or TableView)")
			public boolean containsItem(@FixtureParameter(name = "id") final String aConrtrolId, @FixtureParameter(name = "aItem") final Object aItem) {
				final Control controlObject = (Control)findComponentOfTypeWithException(aConrtrolId, Control.class);

				final Boolean contains[] = new Boolean[1];
				contains[0]=false;

				doInFXThread(new FXRunnable() {

					@Override
					public void doInFXThread() {
						if (controlObject instanceof ComboBox) {
							ComboBox tv = ((ComboBox<?>) controlObject);
							ObservableList items = tv.getItems();
							boolean contains2 = items.contains(aItem);			
							contains[0] = ((ComboBox<?>) controlObject).getItems().contains(aItem);
						} else if (controlObject instanceof ChoiceBox) {
							contains[0] = ((ChoiceBox<?>) controlObject).getItems().contains(aItem);
						} else if (controlObject instanceof ListView<?>) {
							contains[0] = ((ListView<?>) controlObject).getItems().contains(aItem);		
						} else if (controlObject instanceof TableView<?>) {
							contains[0] = ((TableView<?>) controlObject).getItems().contains(aItem);	
						} else {
							throw new IllegalArgumentException("Control with id " + aConrtrolId + " is not an not a ComboBox or ChoiceBox or ListView or TableView!");
						}
					}
				});

				return contains[0].booleanValue();
			}
		
	// ------------------------------------------------------------------------------------------------------
	/**
	 * getText from Textable-Component - e.g. Textfield, TextArea, Label,
	 * Button, CheckBox, RadioButton (extends Labeled)
	 */
	@FixtureMethod(description = "Get Text from Textable-Component with the Id: $id$ (Textfield, TextArea, Label, Button)")
	public String getText(@FixtureParameter(name = "id") String aComponentId) {
		final Control component = (Control) findComponentOfTypeWithException(aComponentId, Control.class);
		final String value[] = new String[1];
		value[0] = null;
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				if (component instanceof TextInputControl) {
					value[0] = ((TextInputControl) component).getText();
				} else if (component instanceof Labeled) {
					value[0] = ((Labeled) component).getText();
				} else {
					throw new IllegalArgumentException("Component with id " + component + " is not textabled!");
				}
			}
		});
		return value[0];
	}

	// ------------------------------------------------------------------------------------------------------
	/**
	 * setText from Textable-Component - e.g. Textfield, TextArea, Label,
	 * Button, CheckBox, RadioButton (extends Labeled)
	 */
	@FixtureMethod(description = "Set Text: $text$ to Textable-Component with the Id: $id$  (Textfield, TextArea, Label, Button)")
	public void setText(@FixtureParameter(name = "id") String aComponentId, @FixtureParameter(name = "text") final String aText) {
		final Control component = (Control) findComponentOfTypeWithException(aComponentId, Control.class);
		doInFXThread(new FXRunnable() {
			@Override
			public void doInFXThread() {
				if (component instanceof TextInputControl) {
					((TextInputControl) component).setText(aText);
				} else if (component instanceof Labeled) {
					((Labeled) component).setText(aText);
				} else {
					throw new IllegalArgumentException("Component with id " + component + " is not textabled!");
				}
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------

}
