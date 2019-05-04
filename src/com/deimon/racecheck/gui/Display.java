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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display {
	Stage stage = new Stage();
	public Label textName;
	public Label textTime;
	public Label pos;
	public Label posCat;
	public Label modalidad;
	public Label categoria;

	public Display() {
		// System.out.println("Display");
		VBox root = new VBox();
		root.setId("display");
		stage.setTitle("Display - Punta Cronos");
		Scene scene = new Scene(root, 800, 500);
		// stage.setFullScreen(true);

		/*********************************/
		/**** HEAD ***********************/
		/*********************************/
		HBox headBox = new HBox();
		headBox.prefHeightProperty().bind(stage.heightProperty().multiply(0.15));
		headBox.setId("head-box");
		HBox titulo = new HBox();
		titulo.setAlignment(Pos.CENTER);
		HBox.setHgrow(titulo, Priority.ALWAYS);
		Text textTitle = new Text();
		textTitle.setText("Punta Cronos");
		textTitle.getStyleClass().add("title");
		Font fontTitle = Font.font("Serif", 70);
		textTitle.setFont(fontTitle);
		titulo.getChildren().add(textTitle);

		HBox logoPC = new HBox();
		ImageView imgLogoPC = new ImageView(
				new Image(this.getClass().getResourceAsStream("resource/logo.png"), 100, 100, true, false));
		logoPC.setStyle("-fx-padding: 20;");
		logoPC.getChildren().add(imgLogoPC);
		HBox logoOtro = new HBox();
		ImageView imgLogoOtro = new ImageView(
				new Image(this.getClass().getResourceAsStream("resource/logo.png"), 100, 100, true, false));
		logoOtro.setStyle("-fx-padding: 20;");
		logoOtro.getChildren().add(imgLogoOtro);
		headBox.getChildren().addAll(logoPC, titulo, logoOtro);

		/*********************************/
		/**** NAME ************************/
		/*********************************/
		HBox nameBox = new HBox();
		nameBox.setAlignment(Pos.CENTER);
		nameBox.prefHeightProperty().bind(stage.heightProperty().multiply(0.20));
		nameBox.setId("name-box");
		textName = new Label();
		textName.setText("Corredor");
		textName.setId("name-name");
		Font fontName = Font.font("Serif", 80);
		textName.setFont(fontName);
		nameBox.getChildren().add(textName);
		/* Menu contextual */
		ContextMenu contextName = new ContextMenu();
		MenuItem achicarName = new MenuItem("Achicar");
		achicarName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (textName.getFont().getSize() > 10) {
					textName.setFont(Font.font("Serif", textName.getFont().getSize() - 10));
				}
			}
		});
		MenuItem agrandarName = new MenuItem("Agrandar");
		agrandarName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textName.setFont(Font.font("Serif", textName.getFont().getSize() + 10));
			}
		});
		achicarName.setAccelerator(
				new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));
		agrandarName.setAccelerator(
				new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));

		contextName.getItems().addAll(achicarName, agrandarName);
		textName.setContextMenu(contextName);
		/* Fin Menu contextual */

		/*********************************/
		/**** INFO ***********************/
		/*********************************/
		HBox infoBox = new HBox();
		infoBox.setAlignment(Pos.CENTER);
		infoBox.prefHeightProperty().bind(stage.heightProperty().multiply(0.10));
		nameBox.setId("info-box");

		Font fontInfo = Font.font("Serif", 25);

		HBox positionBox = new HBox();
		Label posText = new Label("Pos: ");
		posText.getStyleClass().add("info-aux");
		posText.setFont(fontInfo);
		pos = new Label();
		pos.setText("000");
		pos.getStyleClass().add("info-info");
		pos.setFont(fontInfo);
		positionBox.getChildren().addAll(posText, pos);

		HBox positionCatBox = new HBox();
		Label posCatText = new Label("Pos Cat: ");
		posCatText.getStyleClass().add("info-aux");
		posCatText.setFont(fontInfo);
		posCat = new Label();
		posCat.setText("000");
		posCat.getStyleClass().add("info-info");
		posCat.setFont(fontInfo);
		positionCatBox.getChildren().addAll(posCatText, posCat);

		HBox distanciaBox = new HBox();
		Label distanciaText = new Label("Distancia: ");
		distanciaText.getStyleClass().add("info-aux");
		distanciaText.setFont(fontInfo);
		modalidad = new Label();
		modalidad.setText("0K");
		modalidad.getStyleClass().add("info-info");
		modalidad.setFont(fontInfo);
		distanciaBox.getChildren().addAll(distanciaText, modalidad);

		HBox categoriaBox = new HBox();
		Label categoriaText = new Label("Categoría: ");
		categoriaText.getStyleClass().add("info-aux");
		categoriaText.setFont(fontInfo);
		categoria = new Label();
		categoria.setText("16a100");
		categoria.getStyleClass().add("info-info");
		categoria.setFont(fontInfo);
		categoriaBox.getChildren().addAll(categoriaText, categoria);

		distanciaBox.setStyle("-fx-padding: 0 20 0 20;");
		positionBox.setStyle("-fx-padding: 0 20 0 20;");

		infoBox.getChildren().addAll(distanciaBox, categoriaBox, positionBox, positionCatBox);
		/* Menu contextual */
		ContextMenu contextPos = new ContextMenu();
		MenuItem achicarPos = new MenuItem("Achicar");
		achicarPos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (pos.getFont().getSize() > 10) {
					// ver si se puede agregar al font y no a cada texto
					posText.setFont(Font.font("Serif", posText.getFont().getSize() - 10));
					pos.setFont(Font.font("Serif", pos.getFont().getSize() - 10));
					posCatText.setFont(Font.font("Serif", posCatText.getFont().getSize() - 10));
					posCat.setFont(Font.font("Serif", posCat.getFont().getSize() - 10));
					distanciaText.setFont(Font.font("Serif", distanciaText.getFont().getSize() - 10));
					modalidad.setFont(Font.font("Serif", modalidad.getFont().getSize() - 10));
					categoriaText.setFont(Font.font("Serif", categoriaText.getFont().getSize() - 10));
					categoria.setFont(Font.font("Serif", categoria.getFont().getSize() - 10));
				}
			}
		});
		MenuItem agrandarPos = new MenuItem("Agrandar");
		agrandarPos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				posText.setFont(Font.font("Serif", posText.getFont().getSize() + 10));
				pos.setFont(Font.font("Serif", pos.getFont().getSize() + 10));
				posCatText.setFont(Font.font("Serif", posCatText.getFont().getSize() + 10));
				posCat.setFont(Font.font("Serif", posCat.getFont().getSize() + 10));
				distanciaText.setFont(Font.font("Serif", distanciaText.getFont().getSize() + 10));
				modalidad.setFont(Font.font("Serif", modalidad.getFont().getSize() + 10));
				categoriaText.setFont(Font.font("Serif", categoriaText.getFont().getSize() + 10));
				categoria.setFont(Font.font("Serif", categoria.getFont().getSize() + 10));
			}
		});
		achicarPos.setAccelerator(
				new KeyCodeCombination(KeyCode.MINUS, KeyCombination.ALT_DOWN, KeyCombination.SHORTCUT_DOWN));
		agrandarPos.setAccelerator(
				new KeyCodeCombination(KeyCode.PLUS, KeyCombination.ALT_DOWN, KeyCombination.SHORTCUT_DOWN));

		contextPos.getItems().addAll(achicarPos, agrandarPos);
		pos.setContextMenu(contextPos);
		/* Fin Menu contextual */

		/*********************************/
		/**** TIME ***********************/
		/*********************************/
		HBox timeBox = new HBox();
		timeBox.prefHeightProperty().bind(stage.heightProperty().multiply(0.65));
		timeBox.setId("time-box");
		timeBox.setAlignment(Pos.CENTER);
		textTime = new Label();
		textTime.setText("00:00:00.000");
		textTime.setId("time");
		Font fontTime = Font.font("Serif", 250);
		textTime.setFont(fontTime);
		timeBox.getChildren().add(textTime);
		/* Menu contextual */
		ContextMenu contextTime = new ContextMenu();
		MenuItem achicarTime = new MenuItem("Achicar");
		achicarTime.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (textTime.getFont().getSize() > 10) {
					textTime.setFont(Font.font("Serif", textTime.getFont().getSize() - 10));
				}
			}
		});
		MenuItem agrandarTime = new MenuItem("Agrandar");
		agrandarTime.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textTime.setFont(Font.font("Serif", textTime.getFont().getSize() + 10));
			}
		});
		achicarTime.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHORTCUT_DOWN));
		agrandarTime.setAccelerator(new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHORTCUT_DOWN));

		contextTime.getItems().addAll(achicarTime, agrandarTime);
		textTime.setContextMenu(contextTime);
		/* Fin Menu contextual */

		root.getChildren().addAll(headBox, nameBox, infoBox, timeBox);
		stage.setScene(scene);
		URL dark = this.getClass().getResource("resource/modena_dark.css");
		URL css = this.getClass().getResource("resource/display.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().addAll(dark.toExternalForm(), css.toExternalForm());
	}

	public void start() {
		// System.out.println("Display Start");
		// super.start();
		stage.show();

	}

	public void interrupt() {
		// System.out.println("Display Interrupt");
		stage.close();
	}

	public void hide() {
		stage.hide();
		// System.out.println("Display Hide");
	}

	/**********/
	public void setName(String name) {
		textName.setText(name);
	}

	public void setTime(String time) {
		textTime.setText(time);
	}

	public void setPosition(String position) {
		pos.setText(position);
	}

	public void setPositionCategory(String posCategory) {
		posCat.setText(posCategory);
	}

	public void setModality(String modality) {
		modalidad.setText(modality);
	}

	public void setCategory(String category) {
		categoria.setText(category);
	}

}
