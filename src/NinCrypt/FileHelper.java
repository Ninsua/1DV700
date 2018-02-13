package NinCrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileHelper {
	
	//Throws error if no file is selected
	public static void fileCheck(File f) throws IllegalStateException {
		if (f == null) {
			throw new IllegalStateException("No file selected!");
		}
	}
	
	public static String readFile(File f) throws IllegalStateException {
		BufferedReader fileReader;
		StringBuilder input = new StringBuilder();
		
		try {
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));	
			
			if (!f.canRead()) {
				fileReader.close();
				throw new IllegalStateException("Error: Cannot read file!");
			}
			
			
			while (fileReader.ready()) {
				input.append((char) fileReader.read());
			}
			
			fileReader.close();
		}
		catch (IOException e) {
			throw new IllegalStateException("Error: File not found!");
		}
		
		return input.toString();
	}
	
	public static void writeFile(File f,String input) throws IllegalStateException  {
		OutputStreamWriter writer;
		
		if (!f.canWrite()) {
			throw new IllegalStateException("Error: Cannot write to file!");
		}
		
		try {
			writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			
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
