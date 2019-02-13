package com.deimon.racecheck.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchFile extends Thread {

	String name;
	Path path;
	WatchService watchService;
	boolean escuchando = true;

	public WatchFile() {
	}

	public WatchFile(String name, String path) {
		this.name = name;
		this.path = Paths.get(path);

		try {
			watchService = this.path.getFileSystem().newWatchService();
			this.path.register(
					watchService, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.toString());
		}
	}

	@Override
	public synchronized void start() {
		escuchando = true;
		System.out.println("Start");
		super.start();
	}

	@Override
	public void run() {
		System.out.println("Run");
		while (escuchando) {
			WatchKey watchKey = null;
			try {
				watchKey = watchService.take();
			} catch (InterruptedException e) {
				escuchando = false;
				e.printStackTrace();
			}
			List<WatchEvent<?>> events = watchKey.pollEvents();
			Path changed;
			for (WatchEvent<?> event : events) {
				changed = (Path) event.context();
				if (changed.toString().equals(this.name)) {
					// if (changed.endsWith(this.name)) {
					System.out.println("My file has changed");
					System.out.println("-----");
				}
			}
			boolean valid = watchKey.reset();
			if (!valid) {
				break;
			}
		}
		System.out.println("Salio del while");
	}

	@Override
	public void interrupt() {
		System.out.println("Interrupt");
		escuchando = false;
		super.interrupt();
	}
}
