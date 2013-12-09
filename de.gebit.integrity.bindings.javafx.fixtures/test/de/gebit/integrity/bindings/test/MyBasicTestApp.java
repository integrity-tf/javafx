/*******************************************************************************
 * Copyright (c) 2013 GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.gebit.integrity.bindings.test;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBoxBuilder;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * JavaFX Class to test FX Fixtures.
 * This includes of a lot of different java fx standard components.
 *
 */
public class MyBasicTestApp extends Application {

	public static Stage primaryStage;
	public static Scene scene;

	public class TableItem {
		private StringProperty itemName;
		private StringProperty itemType;
		private StringProperty itemOther;

		private TableItem(String pItemName, String pItemType, String pItemOther) {
			this.itemName = new SimpleStringProperty(pItemName);
			this.itemType = new SimpleStringProperty(pItemType);
			this.itemOther = new SimpleStringProperty(pItemOther);
		}

		public String getItemName() {
            return itemName.get();
        }

		public StringProperty itemNameProperty() {
			return itemName;
		}

		public String getItemType() {
            return itemType.get();
        }
		
		public StringProperty itemTypeProperty() {
			return itemType;
		}

		public String getItemOther() {
            return itemOther.get();
        }
		
		public StringProperty itemOtherProperty() {
			return itemOther;
		}
		
		/*@Override
		public String toString() {
			return itemNameProperty().getName() +""+ itemTypeProperty().getName() +""+ itemOtherProperty().getName();
		}*/
	}
	
//	public static TableItem createTableItem(){
//		return new TableItem(String pItemName, String pItemType, String pItemOther);		
//	}

	@Override
	public void start(Stage primaryStage) {

		MyBasicTestApp.primaryStage = primaryStage;

		VBox vBox = new VBox();
		vBox.setId("VBox");

		final Pane root = new Pane();

		// ----------------------------------------------------------------------------------------
		Label label = new Label();
		label.setId("Label");
		// ----------------------------------------------------------------------------------------
		CheckBox checkBox = CheckBoxBuilder.create().id("CheckBox").maxHeight(40).maxWidth(40).alignment(Pos.CENTER).build();
		// ----------------------------------------------------------------------------------------
		ToggleGroup toggleGroup = new ToggleGroup();
		VBox radioButtonVBox = new VBox();
		radioButtonVBox.setSpacing(5);
		RadioButton radioButton1 = new RadioButton("RadioButton1");
		radioButton1.setToggleGroup(toggleGroup);
		radioButton1.setId("RadioButton1");

		RadioButton radioButton2 = new RadioButton("RadioButton2");
		radioButton2.setToggleGroup(toggleGroup);
		radioButton2.setSelected(true);
		radioButton2.setId("RadioButton2");
		RadioButton radioButton3 = new RadioButton("RadioButton3");
		radioButton3.setToggleGroup(toggleGroup);
		radioButton3.setSelected(false);
		radioButton3.setDisable(true);
		radioButton3.setId("RadioButton3");
		radioButtonVBox.getChildren().add(radioButton1);
		radioButtonVBox.getChildren().add(radioButton2);
		radioButtonVBox.getChildren().add(radioButton3);
		// ----------------------------------------------------------------------------------------
		ComboBox<String> comboBoxEmpty = new ComboBox<String>();
		comboBoxEmpty.setMaxHeight(100);
		comboBoxEmpty.setMaxWidth(100);
		comboBoxEmpty.setId("ComboBoxEmpty");
		comboBoxEmpty.getSelectionModel().selectFirst();
		comboBoxEmpty.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldString, String newString) {
				System.out.println("ComboBoxEmpty");
			}
		});

		ComboBox<String> comboBoxFilled = new ComboBox<String>(FXCollections.observableArrayList("ComboBoxFilled1", "ComboBoxFilled2", "ComboBoxFilled3"));
		comboBoxFilled.setMaxHeight(100);
		comboBoxFilled.setMaxWidth(100);
		comboBoxFilled.setId("ComboBoxFilled");
		comboBoxFilled.getSelectionModel().selectFirst();
		comboBoxFilled.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldString, String newString) {
				System.out.println("ComboBoxFilled");
			}
		});

		// ----------------------------------------------------------------------------------------
		ChoiceBox<String> choiceBoxEmpty = new ChoiceBox<String>();
		choiceBoxEmpty.setMaxHeight(100);
		choiceBoxEmpty.setMaxWidth(100);
		choiceBoxEmpty.setId("ChoiceBoxEmpty");
		choiceBoxEmpty.getSelectionModel().selectFirst();
		choiceBoxEmpty.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldString, String newString) {
				System.out.println("ChoiceBoxEmpty");
			}
		});

		ChoiceBox<String> choiceBoxFilled = new ChoiceBox<String>(FXCollections.observableArrayList("ChoiceBoxFilled1", "ChoiceBoxFilled2", "ChoiceBoxFilled3"));
		choiceBoxFilled.setMaxHeight(100);
		choiceBoxFilled.setMaxWidth(100);
		choiceBoxFilled.setId("ChoiceBoxFilled");
		choiceBoxFilled.getSelectionModel().selectFirst();
		choiceBoxFilled.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldString, String newString) {
				System.out.println("ChoiceBoxFilled");
			}
		});

		// ----------------------------------------------------------------------------------------
		final Button button = ButtonBuilder.create().id("Button").text("Button").maxHeight(100).maxWidth(100).onAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Button");
			}
		}).build();
		// ----------------------------------------------------------------------------------------
		CheckMenuItem checkMenuItem = new CheckMenuItem("CheckMenuItem");
		checkMenuItem.setId("CheckMenuItem");
		checkMenuItem.setSelected(false);
		checkMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.out.println("CheckMenuItem");
			}
		});
		// ----------------------------------------------------------------------------------------
		TextArea textArea = TextAreaBuilder.create().id("TextArea").text("TextArea").maxHeight(100).maxWidth(100).build();
		// ----------------------------------------------------------------------------------------
		TextField textField = TextFieldBuilder.create().id("TextField").text("TextField").maxHeight(100).maxWidth(100).build();
		// ----------------------------------------------------------------------------------------
		ListView<String> listViewEmpty = new ListView<String>();
		ObservableList<String> itemsEmpty = FXCollections.observableArrayList();
		listViewEmpty.setMaxHeight(100);
		listViewEmpty.setMaxWidth(100);
		listViewEmpty.setId("ListViewEmpty");
		listViewEmpty.setItems(itemsEmpty);
		listViewEmpty.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				System.out.println("ListViewEmpty");
			}
		});
		ListView<String> listViewFilled = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList(FXCollections.observableArrayList("ListViewFilled1", "ListViewFilled2", "ListViewFilled3"));
		listViewFilled.setMaxHeight(100);
		listViewFilled.setMaxWidth(100);
		listViewFilled.setId("ListViewFilled");
		listViewFilled.setItems(items);
		listViewFilled.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				System.out.println("ListViewFilled");
			}
		});
		// ----------------------------------------------------------------------------------------
//		TableView<String> tableViewEmpty = new TableView<String>();
//		tableViewEmpty.setId("TableViewEmpty");
//		tableViewEmpty.setEditable(true);
//		final ObservableList<String> dataEmpty = FXCollections.observableArrayList();
//		tableViewEmpty.setItems(dataEmpty);
//		TableColumn<String, String> col1Empty = new TableColumn<String, String>("String");
//		col1Empty.setMinWidth(100);
//		col1Empty.setMaxWidth(100);
//		col1Empty.setCellValueFactory(new PropertyValueFactory<String, String>("String"));
//		tableViewEmpty.getColumns().addAll(col1Empty);
//		tableViewEmpty.setMinHeight(100);
//		tableViewEmpty.setMaxHeight(100);
		
		final ObservableList<TableItem> dataEmpty = FXCollections.observableArrayList(				);
		TableColumn<TableItem, String> colItemNameEmpty = new TableColumn<TableItem, String>();
		colItemNameEmpty.setMinWidth(100);
		colItemNameEmpty.setMaxWidth(100);
		colItemNameEmpty.setText("ItemName");
		colItemNameEmpty.setCellValueFactory(new PropertyValueFactory<TableItem, String>("itemName"));
		TableColumn<TableItem, String> colItemTypeEmpty = new TableColumn<TableItem, String>();
		colItemTypeEmpty.setMinWidth(100);
		colItemTypeEmpty.setMaxWidth(100);
		colItemTypeEmpty.setText("ItemType");
		colItemTypeEmpty.setCellValueFactory(new PropertyValueFactory<TableItem, String>("itemType"));
		TableColumn<TableItem, String> colItemOtherEmpty = new TableColumn<TableItem, String>();
		colItemOtherEmpty.setMinWidth(100);
		colItemOtherEmpty.setMaxWidth(100);
		colItemOtherEmpty.setText("ItemOther");
		colItemOtherEmpty.setCellValueFactory(new PropertyValueFactory<TableItem, String>("itemOther"));
		
		TableView<TableItem> tableViewEmpty = new TableView<TableItem>();
		tableViewEmpty.setId("TableViewEmpty");
		tableViewEmpty.setMinHeight(100);
		tableViewEmpty.setMaxHeight(100);
		tableViewEmpty.setEditable(true);
		tableViewEmpty.setItems(dataEmpty);
		tableViewEmpty.getColumns().addAll(colItemNameEmpty, colItemTypeEmpty, colItemOtherEmpty);
		

		final ObservableList<TableItem> data = FXCollections.observableArrayList(//
				new TableItem("TableViewFilled1.1", "1.2", "1.3"), //
				new TableItem("TableViewFilled2.1", "2.2", "2.3"), //
				new TableItem("TableViewFilled3.1", "3.2", "3.3"));
		TableColumn<TableItem, String> colItemName = new TableColumn<TableItem, String>();
		colItemName.setMinWidth(100);
		colItemName.setMaxWidth(100);
		colItemName.setText("ItemName");
		colItemName.setCellValueFactory(new PropertyValueFactory<TableItem, String>("itemName"));
		TableColumn<TableItem, String> colItemType = new TableColumn<TableItem, String>();
		colItemType.setMinWidth(100);
		colItemType.setMaxWidth(100);
		colItemType.setText("ItemType");
		colItemType.setCellValueFactory(new PropertyValueFactory<TableItem, String>("itemType"));
		TableColumn<TableItem, String> colItemOther = new TableColumn<TableItem, String>();
		colItemOther.setMinWidth(100);
		colItemOther.setMaxWidth(100);
		colItemOther.setText("ItemOther");
		colItemOther.setCellValueFactory(new PropertyValueFactory<TableItem, String>("itemOther"));
		
		TableView<TableItem> tableViewFilled = new TableView<TableItem>();
		tableViewFilled.setId("TableViewFilled");
		tableViewFilled.setMinHeight(100);
		tableViewFilled.setMaxHeight(100);
		tableViewFilled.setEditable(true);
		tableViewFilled.setItems(data);
		tableViewFilled.getColumns().addAll(colItemName, colItemType, colItemOther);
		// ----------------------------------------------------------------------------------------
		final TabPane tabPane = new TabPane();
		tabPane.setId("TabPane");
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabPane.setMaxHeight(100);

		final Tab tab1 = new Tab();
		tab1.setId("Tab1");
		tab1.setText("Tab1");
		final Tab tab2 = new Tab();
		tab2.setId("Tab2");
		tab2.setText("Tab2");
		tabPane.getTabs().addAll(tab1, tab2);
		// ----------------------------------------------------------------------------------------
		MenuBar menuBar = new MenuBar();
		menuBar.setId("MenuBar");
		Menu menu = new Menu("Menu");
		menu.setId("Menu");
		menu.getItems().addAll(checkMenuItem);
		menuBar.getMenus().addAll(menu);
		// ----------------------------------------------------------------------------------------
		Label label1 = new Label("");
		Label label2 = new Label("");
		vBox.getChildren().addAll(label1, label2, label, radioButtonVBox, checkBox, comboBoxEmpty, comboBoxFilled, choiceBoxEmpty, choiceBoxFilled, button, textArea, textField, listViewEmpty, listViewFilled,
				tableViewEmpty, tableViewFilled, tabPane);
		root.getChildren().add(vBox);
		root.getChildren().addAll(menuBar);
		// ----------------------------------------------------------------------------------------
		// ----------------------------------------------------------------------------------------
		scene = new Scene(root, 800, 800);
		scene.setFill(Color.OLDLACE);
		// ----------------------------------------------------------------------------------------

		primaryStage.setTitle("Basic Test");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.out.println("Close");
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}