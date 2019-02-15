package com.deimon.racecheck.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.regex.Pattern;


public class UtilsFile {
	public File source;
	public File dest;

	public void copyFile(File source, File dest) {
		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (FileAlreadyExistsException e) {
			dest.delete();
			try {
				Files.copy(source.toPath(), dest.toPath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
			System.out.println("No se peude copiar");
		}
	}

	public String[] compareFile(File source, File dest) {
		String[] out = null;
		String first = "", second = "";
		Scanner input1 = null;
		Scanner input2 = null;
		try {
			input1 = new Scanner(source);
			input2 = new Scanner(dest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (input1.hasNextLine() || input2.hasNextLine()) {
			if(input1.hasNextLine()) {
				first = input1.nextLine();
			}
			if(input2.hasNextLine()) {
				second = input2.nextLine();
			}
			if (!first.equals(second)) {
				//				System.out.println("Differences found: " + "\nF:" + first + "\nS:" + second);
				out = this.parseText(first);
				break;
			}
		}
		return out;
	}

	public static void cleanFile(File f) {
	}

	private String[] parseText(String txt) {
		//controlar por si llega un txt vacio o nulo
		String[] parts = txt.split("\\|");
		System.out.println(parts.length);
//		for (int i = 0; i < parts.length; i++) {
//			System.out.println(i+") "+parts[i]);
//		}
		/*el 1 y 2l 6 me sirven*/
		return new String[] {parts[1],parts[6]};
	}

}
