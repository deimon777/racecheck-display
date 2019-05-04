package com.deimon.racecheck.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UtilsFile {
	// public File source;
	// public File dest;

	//	public void copyFile(File source, File dest){
	//
	//		System.out.println("ARCHIVO TEMPORAL: "+dest);
	//		System.out.println("Arranco a copiar");
	//		try {
	//			Files.deleteIfExists(Paths.get(dest.toURI()));
	//			Files.copy(source.toPath(), dest.toPath(),StandardCopyOption.REPLACE_EXISTING);
	//		} catch (IOException e) {
	////			Alert alert = new Alert(AlertType.ERROR);
	////			alert.setTitle("Error - WatchFile (run)");
	////			alert.setHeaderText(null);
	////			alert.setContentText("Ooops!!! No se pudo copiar el archivo - WatchFile (run)");
	////			alert.showAndWait();
	//			e.printStackTrace();
	//		}
	//		System.out.println("Termino a copiar");
	//	}


	public void copyFile(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;			
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, String> compareFile(File source, File dest){
		Scanner input1 = null;
		Scanner input2 = null;
		
		Map<String, String> out = null;
		String first = "", second = "";
		try {
			input1 = new Scanner(source);
			input2 = new Scanner(dest);
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error - UF (compare)");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			e.printStackTrace();
			// System.out.println("Error (UF compare): " + e.toString());
		}

		while (input1.hasNextLine() || input2.hasNextLine()) {
			if(input1.hasNextLine()) {
				first = input1.nextLine();
			}
			if(input2.hasNextLine()) {
				second = input2.nextLine();
			}
			if (!first.equals(second)) {
				// System.out.println("Diferencias: " + "\nF:" + first + "\nS:" + second+ "\n-------");
				out = this.parseText(first);
				break;
			}
		}
		return out;
	}

	public static void cleanFile(File f) {
	}

	private Map<String, String> parseText(String txt) {
		//controlar por si llega un txt vacio o nulo
		String[] parts = txt.split("\\|");
		Map<String, String> out = new HashMap<String, String>();
		//;SEXO|NOMBRE|CHIP|DORSAL|MODALIDAD|CATEGORIA|TIEMPO|POSICION|POS.CAT.|RITMO
		try {
			String charsetTo = "ISO-8859-1";
			String charsetFrom = "windows-1252";
			out.put("sexo",parts[0]);
			out.put("nombre",new String(parts[1].getBytes(charsetTo), charsetFrom));			
			out.put("chip",parts[2]);			
			out.put("dorsal",parts[3]);			
			out.put("modalidad",parts[4]);			
			out.put("categoria",parts[5]);			
			out.put("tiempo",parts[6]);
			out.put("posicion",parts[7]);
			out.put("pos.cat",parts[8]);
			out.put("ritmo",parts[9]);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

}
