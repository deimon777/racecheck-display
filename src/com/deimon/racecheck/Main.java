package com.deimon.racecheck;

import javafx.application.Application;
import javafx.stage.Stage;

import com.deimon.racecheck.gui.MainGUI;

public class Main<T> extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {		
		MainGUI gui = new MainGUI();
	    // System.out.println("El directorio temporal del sistema es "+ System.getProperty("java.io.tmpdir"));
		gui.show();
	}

	public static void main(String[] args) {
		launch(args);		
	}
}

