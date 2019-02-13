package com.deimon.racecheck.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Scanner;


public class UtilsFile {

	public static void copyFile(File source, File dest) {
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
	
	public static void compareFile(File source, File dest) {
        String first = "", second = "";
		Scanner input1 = null;
		Scanner input2 = null;
		try {
			input1 = new Scanner(source);
			input2 = new Scanner(dest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
        while (input1.hasNextLine()) {
            first = input1.nextLine();
        }
        while (input2.hasNextLine()) {
            second = input2.nextLine();
        }

        if (!first.equals(second)) {
            System.out.println("Differences found: " + "\n" + first + '\n' + second);
        }else {
        	System.out.println("Son iguales");
        }
	}
	
	public static void cleanFile(File f) {
	}
}
