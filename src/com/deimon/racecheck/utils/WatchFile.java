package com.deimon.racecheck.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Map;

import com.deimon.racecheck.gui.Display;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WatchFile extends Thread {
	// private BlockingQueue<Message> queue;
	Display display;
	UtilsFile util;
	File source;
	File fileTmp;

	String name;
	Path path;
	WatchService watchService;
	boolean escuchando = true;

	//	public WatchFile() {
	//	}

	//	public WatchFile(String name, String path) {
	//		System.out.println("WatchFile");
	//		this.name = name;
	//		this.path = Paths.get(path);
	//
	//		display = new Display();
	//
	//		Alert alert = new Alert(AlertType.WARNING);
	//		alert.setTitle("Cuidado");
	//		alert.setHeaderText(null);
	//		try {
	//			watchService = this.path.getFileSystem().newWatchService();
	//			this.path.register(
	//					watchService, StandardWatchEventKinds.ENTRY_CREATE,
	//					StandardWatchEventKinds.ENTRY_DELETE,
	//					StandardWatchEventKinds.ENTRY_MODIFY);
	//		} catch (IOException e) {
	//			alert.setContentText("Ooops!!! Error de entrada/salida");
	//			alert.showAndWait();
	//			System.out.println("Error: " + e.toString());
	//		} catch (Exception e) {
	//			// e.printStackTrace();
	//			alert.setContentText("Ooops!!! Error sin solución");
	//			alert.showAndWait();
	//			System.out.println("Error: " + e.toString());
	//		}
	//	}

	public WatchFile(String name, String path, Display display) {
		System.out.println("WatchFile");
		this.name = name;
		this.path = Paths.get(path);

		this.display = display;

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Cuidado");
		alert.setHeaderText(null);
		try {
			watchService = this.path.getFileSystem().newWatchService();
			this.path.register(
					watchService, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException e) {
			alert.setContentText("Ooops!!! Error de entrada/salida");
			alert.showAndWait();
			System.out.println("Error: " + e.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			alert.setContentText("Ooops!!! Error sin solución");
			alert.showAndWait();
			System.out.println("Error: " + e.toString());
		}
	}

	@Override
	public synchronized void start() {
		System.out.println("WatchFile Start");
		util = new UtilsFile();

		escuchando = true;
		//display.start();
		source = new File(this.path+"/"+this.name);
		fileTmp = new File(System.getProperty("java.io.tmpdir")+"/"+this.name+"2");

		util.copyFile(source,fileTmp);
		super.start();
	}

	@Override
	public void run() {
		// super.run();
		System.out.println("WatchFile Run");
		// Message msg = new Message();

		while (escuchando) {
			WatchKey watchKey = null;
			List<WatchEvent<?>> events = null;
			try {
				watchKey = watchService.take();
				events = watchKey.pollEvents();
			} catch (InterruptedException e) {
				escuchando = false;
				// System.out.println("Error: " + e.toString());
			}
			Path changed;
			if(events != null) {
				for (WatchEvent<?> event : events) {
					changed = (Path) event.context();
					if (changed.toString().equals(this.name)) {
						// if (changed.endsWith(this.name)) {
						System.out.println("My file has changed");
						System.out.println("-----");

						Map<String, String> info = util.compareFile(source, fileTmp);

						Platform.runLater(new Runnable() {
							@Override public void run() {
								if(info != null) { //controlar aca
									display.setName(info.get("nombre").toString());
									display.setModality(info.get("modalidad").toString());
									display.setCategory(info.get("categoria").toString());
									display.setPosition(info.get("posicion").toString());
									display.setPositionCategory(info.get("pos.cat").toString());
									display.setTime(info.get("tiempo").toString());
								}
							}
						});
						util.copyFile(source, fileTmp);
					}
					// System.out.println("Modificado: "+changed);
				}
				boolean valid = watchKey.reset();
				if (!valid) {
					break;
				}
			}
		}
		System.out.println("WatchFile - Salio del while");
	}

	@Override
	public void interrupt() {
		System.out.println("WatchFile Interrupt");
		fileTmp.delete();
		escuchando = false;
		//display.interrupt();
		super.interrupt();
	}

	/*******************/
}
