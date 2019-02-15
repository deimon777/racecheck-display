package com.deimon.racecheck.gui;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display {
	Stage stage = new Stage();
	public Label textName;
	public Label textTime;

	public Display() {
		System.out.println("Display");
		VBox root = new VBox();
		stage.setTitle("Display - Punta Cronos");
		Scene scene = new Scene(root, 800, 500);
		// stage.setFullScreen(true);

		/*********************************/
		/**** HEAD ***********************/
		/*********************************/
		HBox top = new HBox();
		top.prefHeightProperty().bind(stage.heightProperty().multiply(0.15));
		top.setStyle("-fx-background-color: #222222;");
		HBox titulo = new HBox();
		titulo.setAlignment(Pos.CENTER);
		HBox.setHgrow(titulo, Priority.ALWAYS);
		Text textTitle = new Text();
		textTitle.setText("Punta Cronos");
		Font fontTitle = Font.font("Serif", 50);
		textTitle.setFont(fontTitle);
		textTitle.setFill(Color.BLACK);
		titulo.getChildren().add(textTitle);

		HBox logo = new HBox();
		logo.getChildren().add(new ImageView(new Image(this.getClass().getResourceAsStream("resource/logo.png"))));

		top.getChildren().addAll(titulo, logo);

		/*********************************/
		/**** NOMBRE *********************/
		/*********************************/
		HBox name = new HBox();
		name.prefHeightProperty().bind(stage.heightProperty().multiply(0.20));
		name.setAlignment(Pos.CENTER);
		textName = new Label();
		textName.setText("Nombre del corredor");
		textName.setTextFill(Color.ORANGERED);
		Font fontName = Font.font("Serif", 100);
		textName.setFont(fontName);
		name.getChildren().add(textName);
		/* Menu contextual */
		ContextMenu contextName= new ContextMenu();
		MenuItem achicarName = new MenuItem("Achicar");
		achicarName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(textName.getFont().getSize()>10) {
					textName.setFont(Font.font("Serif", FontWeight.BOLD, textName.getFont().getSize() - 10));
				}
			}
		});
		MenuItem agrandarName = new MenuItem("Agrandar");
		agrandarName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textName.setFont(Font.font("Serif", FontWeight.BOLD, textName.getFont().getSize() + 10));
			}
		});
		achicarName.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));
		agrandarName.setAccelerator(new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));

		contextName.getItems().addAll(achicarName, agrandarName);
		textName.setContextMenu(contextName);
		/* Fin Menu contextual */

		/*********************************/
		/**** TIME ***********************/
		/*********************************/
		HBox time = new HBox();
		time.prefHeightProperty().bind(stage.heightProperty().multiply(0.65));
		time.setAlignment(Pos.CENTER);
		textTime = new Label();
		textTime.setText("00:00:00.000");
		textTime.setTextFill(Color.LAWNGREEN);
		Font fontTime = Font.font("Serif", FontWeight.BOLD, 250);
		textTime.setFont(fontTime);
		time.getChildren().add(textTime);
		/* Menu contextual */
		ContextMenu contextTime = new ContextMenu();
		MenuItem achicarTime = new MenuItem("Achicar");
		achicarTime.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(textTime.getFont().getSize()>10) {
					textTime.setFont(Font.font("Serif", FontWeight.BOLD, textTime.getFont().getSize() - 10));
				}
			}
		});
		MenuItem agrandarTime = new MenuItem("Agrandar");
		agrandarTime.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textTime.setFont(Font.font("Serif", FontWeight.BOLD, textTime.getFont().getSize() + 10));
			}
		});
		achicarTime.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHORTCUT_DOWN));
		agrandarTime.setAccelerator(new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHORTCUT_DOWN));

		contextTime.getItems().addAll(achicarTime, agrandarTime);
		textTime.setContextMenu(contextTime);
		/* Fin Menu contextual */

		root.getChildren().addAll(top, name, time);
		stage.setScene(scene);
		URL dark = this.getClass().getResource("resource/modena_dark.css");
		URL css = this.getClass().getResource("resource/display.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().addAll(dark.toExternalForm(), css.toExternalForm());
	}

	public void start() {
		System.out.println("Display Start");
		// super.start();
		stage.show();

	}

	public void interrupt(){
		System.out.println("Display Interrupt");
		stage.close();
	}

	public void hide() {
		stage.hide();	
		System.out.println("Display Hide");
	}


	/**********/
	
	public void setName(String name) {
		textName.setText(name);
	}
	
	public void setTime(String time) {
		textTime.setText(time);
	}
	
}
