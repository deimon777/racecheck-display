package com.deimon.racecheck.gui;

import java.io.File;
import java.net.URL;

//import com.deimon.racecheck.gui.menu.MyMenu;
import com.deimon.racecheck.utils.WatchFile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainGUI {
	
	String fileName = null;
	File filePath = null;
	Stage stage = new Stage();

	public MainGUI() {
		Display display = new Display();		

		BorderPane root = new BorderPane();
		stage.setTitle("Display - Punta Cronos");
		stage.setResizable(false);
		/*********************************/
		/**** ABRIR **********************/
		/*********************************/
		FlowPane abrir = new FlowPane();
		abrir.setOrientation(Orientation.HORIZONTAL);
		Button btnAbrir = new Button("Abrir");
		Text nombreArchivo = new Text("");

		btnAbrir.setOnAction(e -> {
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Racecheck", "*.racecheck");
			FileChooser.ExtensionFilter extTodo = new FileChooser.ExtensionFilter("Todo los archivos", "*.*");
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(extFilter,extTodo);
			fileChooser.setTitle("Abrir Archivo");
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				fileName = file.getName();
				filePath = file.getParentFile();
				System.out.println(fileName);
				System.out.println(filePath);
				// System.out.println(file.getPath());
				nombreArchivo.setText(fileName);
			}
		});

		abrir.getChildren().addAll(btnAbrir,nombreArchivo);
		/*********************************/
		/**** LOGO ***********************/
		/*********************************/

		/*********************************/
		/**** COLORES ********************/
		/*********************************/

		/*********************************/
		/**** BTN ************************/
		/*********************************/
		HBox comenzar = new HBox();
		comenzar.setPadding(new Insets(20));
		comenzar.setAlignment(Pos.CENTER);
		Button btnComenzar = new Button("Comenzar");
		Font fontComenzar = Font.font("Serif", 50);
		btnComenzar.setFont(fontComenzar);
		btnComenzar.setPadding(new Insets(20));
		EventHandler<ActionEvent> eventComenzar = new EventHandler<ActionEvent>() {
			boolean corriendo = false;
			WatchFile wf = null;
			public void handle(ActionEvent e) {
				if (fileName != null && filePath != null) {
					if(corriendo != true) {
						wf = new WatchFile(fileName, filePath.toString(),display);
						wf.start();

						btnComenzar.setText("Parar");
						display.start();
						// inicia la pantalla con los valores por default 
						display.setName("Corredor");
						display.setTime("00:00:00.000");
						corriendo = true;
						System.out.println("Corriendo!");
					}else {
						wf.interrupt();
						display.interrupt();
						btnComenzar.setText("Comenzar");
						corriendo = false;
					}
				} else {
					System.out.println("Seleccionar un archivo .racecheck");
				}
			}
		};
		btnComenzar.setOnAction(eventComenzar);
		comenzar.getChildren().add(btnComenzar);

		/*********************************/
		/**** MENU ***********************/
		/*********************************/
		// MyMenu myMenu = new MyMenu();
		// MenuBar menu = myMenu.getMenu();

		// root.setTop(menu);
		root.setCenter(abrir);
		root.setBottom(comenzar);
		Scene scene = new Scene(root, 800, 500);

		URL dark = this.getClass().getResource("resource/dark.css");
		URL css = this.getClass().getResource("resource/main.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().addAll(dark.toExternalForm(), css.toExternalForm());

		stage.setScene(scene);
	}

	public void show() {
		stage.show();
	}

}
