package cryptoAnalysis;

import NinCrypt.Crypt;
import NinCrypt.SubEncryptor;
import NinCrypt.FileHelper;
import java.io.File;
import org.knowm.xchart.*;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;


public class CharHistogram {
	static int i = 0; //to make stream work, shitty I know
	
	//Make bar histogram
	public static CategoryChart getBar(int w, int h, String t, String x, String y) {
		return new CategoryChartBuilder().width(w).height(h).title(t).xAxisTitle(x).yAxisTitle(y).build();
	}
	
	public static double[] charOccurrence(String in,char[] charToCheck) {
		double[] occurrence = new double[charToCheck.length];
		
		i = 0;
		while (i<charToCheck.length) {
			IntStream charStream = in.chars();
			occurrence[i] = (double) charStream
					.filter(c -> c==charToCheck[i])
					.count();
			i++;
		}
		
		return occurrence;
	}
	
	private static int[] keyIndex(String key) {
		int keyLength = key.length();
		int[] indexes = new int[keyLength];
		
		ArrayList<SimpleEntry<Integer,Character>> keyList = new ArrayList<SimpleEntry<Integer,Character>>();

		//Add all maps to list
		for (int i = 0; i < keyLength;i++) {
			SimpleEntry<Integer,Character>mapToAdd = new SimpleEntry<Integer,Character>(i, key.charAt(i));
			keyList.add(mapToAdd);
		}
		
		//Sort list, reversed
		keyList.sort((SimpleEntry<Integer,Character> a,SimpleEntry<Integer,Character> b) -> b.getValue().compareTo(a.getValue()));
		
		for (int i = 0; i < keyLength; i++) {
			indexes[keyList.get(i).getKey()] = i;
		}

		return indexes;
	}
	
	public static void main(String[] args) {
		String inputStr;
		File inputFile;
		char[] charsToLookFor;
		String path;
		
		int[] key = keyIndex("Dialectial materialism");
		
		for (int i : key)
			System.out.println(i);
		
		inputStr = "";
		path = "G:\\java\\workspace_1\\1DV700\\src\\cryptoAnalysis\\toAnalyze\\MirzaDurakovic_substitution.txt";
		inputFile = new File(path);
		
		try {
			inputStr = FileHelper.readFile(inputFile);
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		//Make char array from input
		char[] inputAsArray = inputStr.toCharArray();
		Arrays.sort(inputAsArray);
		
		//Remove duplicates as list
		ArrayList<Character> charList = new ArrayList<Character>();
		Character prev = 0;
		for (char c : inputAsArray) {
			if (prev.compareTo(c) != 0) {
				charList.add(c);
			}
			prev = c;
		}
		
		//List to array
		charsToLookFor = new char[charList.size()];
		for (int i = 0;i<charList.size();i++) {
			char c = charList.get(i);
			charsToLookFor[i] = c;
		}
		
		//Calculate occurences
		double[] charOccurrence = charOccurrence(inputStr,charsToLookFor);
		
		List<Double> occurenceList = new ArrayList<Double>();
		for (int i = 0;i<charOccurrence.length;i++) {
			double d = charOccurrence[i];
			occurenceList.add(d);
		}
		
		List<String> barCharas = new ArrayList<String>();
		for (int i = 0;i<charsToLookFor.length;i++) {
			char c = charsToLookFor[i];
			barCharas.add(""+c);
		}
		
		
		//Make chart
		CategoryChart bar = getBar(800,600,"Histogram","Character","Quantity");
		
		bar.addSeries("Charas",barCharas,occurenceList);
		
		//Display
		new SwingWrapper<>(bar).displayChart();
		
		
		
		
		
		
	}

}
