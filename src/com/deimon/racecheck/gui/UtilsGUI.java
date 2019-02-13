package com.deimon.racecheck.gui;

import java.net.URL;

import javafx.scene.Scene;

public class UtilsGUI {

	public UtilsGUI() {
	}

	public void addCSS(Scene scene, String nombre) {
		URL url = this.getClass().getResource("../../../gui/resource/"+nombre);
		String css = url.toExternalForm();
	    scene.getStylesheets().add(css);
	}
	
	public void cleanCSS(Scene scene) {
	    scene.getStylesheets().clear();;
	}
	
}
