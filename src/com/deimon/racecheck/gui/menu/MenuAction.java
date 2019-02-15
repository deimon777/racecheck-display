package com.deimon.racecheck.gui.menu;

import java.io.File;

import com.deimon.racecheck.gui.Display;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class MenuAction {
	public <T> EventHandler<ActionEvent> setAction() {
		EventHandler<ActionEvent> action = changeView();
		return action;
	}

	public <T> EventHandler<ActionEvent> changeView() {
		return new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
				String side = mItem.getText();
				switch (side) {

				case TextGUI.ABRIR:
					FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Racecheck", "*.racecheck");
					FileChooser.ExtensionFilter extTodo = new FileChooser.ExtensionFilter("Todo los archivos", "*.*");
					FileChooser fileChooser = new FileChooser();
					fileChooser.getExtensionFilters().addAll(extFilter,extTodo);
					fileChooser.setTitle("Abrir Archivo");
					File file = fileChooser.showOpenDialog(null);
					if (file != null) {
						System.out.println(file.getName());
						System.out.println(file.getParentFile());
						System.out.println(file.getPath());
					}
					break;
				case TextGUI.SALIR:
					Platform.exit();
					break;

				case TextGUI.MOSTRAR:
					Display display = new Display();
					display.start();
					break;

				default:
					break;
				}
			}
		};
	}

}
