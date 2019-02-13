package com.deimon.racecheck;

import javafx.application.Application;
import javafx.stage.Stage;

import com.deimon.racecheck.gui.MainGUI;

public class Main<T> extends Application {

//		//		Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"));
//		Path path = FileSystems.getDefault().getPath(Statics.ruta_archivo);
//		System.out.println(path.toString()); 
//
//
//		// busco mi archivo
//		//		File source = new File(Statics.nombre_archivo);
//		//		System.out.println(source.getPath().toString());
//		// creo el archivo temporal
//		//		File dest = new File(Statics.nombre_archivo_temp);
//		// copio la info del archivo en el temporal
//		//		UtilsFile.copyFile(source, dest);
//
//		//		UtilsFile.compareFile(source, dest);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainGUI mainGui = new MainGUI();
		mainGui.show();
	}
	
	public static void main(String[] args) {
		launch(args);		
	}
}

