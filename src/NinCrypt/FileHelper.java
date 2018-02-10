package NinCrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {
	
	//Throws error if no file is selected
	public static void fileCheck(File f) throws IllegalStateException {
		if (f == null) {
			throw new IllegalStateException("No file selected!");
		}
	}
	
	public static String readFile(File f) throws IllegalStateException {
		Scanner fileReader;
		StringBuilder input = new StringBuilder();
		
		try {
			fileReader = new Scanner(f);	
		}
		catch (FileNotFoundException e) {
			throw new IllegalStateException("Error: File not found!");
		}
		
		if (!f.canRead()) {
			fileReader.close();
			throw new IllegalStateException("Error: Cannot read file!");
		}
		
		fileReader.useDelimiter("");
		while (fileReader.hasNext()) {
			input.append(fileReader.next());
		}
		
		fileReader.close();
		
		return input.toString();
	}
	
	public static void writeFile(File f,String input) throws IllegalStateException  {
		FileWriter writer;
		
		if (!f.canWrite()) {
			throw new IllegalStateException("Error: Cannot write to file!");
		}
		
		try {
			writer = new FileWriter(f);
			
			for (int i = 0;i<input.length();i++) {
				writer.write(input.charAt(i));
			}
			
			writer.close();
		}
		
		catch (IOException e) {
			throw new IllegalStateException("Error while writing to file!");
		}
	}
}
