package com.deimon.racecheck.gui.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class MyMenu {
	MenuBar menu = new MenuBar();
	
	public MyMenu() {
		EventHandler<ActionEvent> action = new MenuAction().setAction();
		/*
		 * Creando las secciones del menú
		 */
		Menu archivo = new Menu("_Archivo");
		MenuItem itmAbrir = new MenuItem(TextGUI.ABRIR);
		MenuItem itmSalir = new MenuItem(TextGUI.SALIR);
		

		Menu display= new Menu("_Display");
		MenuItem itmConfigurar = new MenuItem(TextGUI.CONFIGURAR);
		MenuItem itmMostrar = new MenuItem(TextGUI.MOSTRAR);
		
		Menu ayuda = new Menu("A_yuda");
		MenuItem itmAcerca = new MenuItem("Acerca de");
//		MenuItem itmAtajos = new MenuItem("Atajos de teclado");
		MenuItem itmAyuda = new MenuItem("Ayuda");
		
		/*
		 * Creando los Submenus
		 */
		archivo.getItems().addAll(itmAbrir,itmSalir);
		display.getItems().addAll(itmConfigurar,itmMostrar);
		ayuda.getItems().addAll(itmAcerca,itmAyuda);

		/*
		 * Agregando los Atajos de teclado
		 */
		itmAbrir.setAccelerator(new KeyCodeCombination(KeyCode.O,KeyCombination.SHORTCUT_DOWN));
		itmSalir.setAccelerator(new KeyCodeCombination(KeyCode.Q,KeyCombination.SHORTCUT_DOWN));
		itmMostrar.setAccelerator(new KeyCodeCombination(KeyCode.ENTER,KeyCombination.SHORTCUT_DOWN));
		itmAyuda.setAccelerator(new KeyCodeCombination(KeyCode.F1));
		
		/*
		 * Agregando los menus
		 */
		menu.getMenus().addAll(archivo,display,ayuda);
		
		/*
		 * Agregando las acciones
		 */
		itmAbrir.setOnAction(action);
		itmSalir.setOnAction(action);		
		itmMostrar.setOnAction(action);
	}
	
	public MenuBar getMenu() {
		return this.menu;
	}
}
