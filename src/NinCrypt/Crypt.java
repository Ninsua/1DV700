package NinCrypt;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

public abstract class Crypt {
	protected String plainText;
	protected String cipherText;
	protected String key;
	
	public Crypt() {
		plainText = "";
		cipherText = "";
		key = "";
	}
	
	//For functionality only
	public void encrypt() throws IllegalStateException {
	}
	
	//For functionality only
	public void decrypt() throws IllegalStateException {
	}
	
	public void setPlainText(String p) {
		plainText = p;
	}
	
	public void setCipherText(String c) {
		cipherText = c;
	}
	
	public void setKey(String k) throws IllegalArgumentException {
		if (k.isEmpty()) {
			throw new IllegalArgumentException("Key cannot be empty!");
		}
		key = k;
	}
	
	public String getPlainText() {
		if (!hasPlainText()) {
			throw new IllegalStateException("The encryptor is missing a plainText!");
		}
		return plainText;
	}
	
	public String getCipherText() throws IllegalStateException {
		if (!hasCipherText()) {
			throw new IllegalStateException("The encryptor is missing a CipherText!");
		}
		return cipherText;
	}
	
	protected boolean hasPlainText() {
		return !plainText.isEmpty();
	}
	
	protected boolean hasCipherText() {
		return !cipherText.isEmpty();
	}
	
	protected boolean hasKey() {
		return !key.isEmpty();
	}
	
	//Pads with 0s
	protected String pad(String toPad,int charsToPad) {
		StringBuilder padded = new StringBuilder(toPad);
		
		for (int i = 0;i<charsToPad;i++) {
			padded.append(' ');
		}
		
		return padded.toString();
	}
	
	//Orders the key in reversed alphabetical order
	protected int[] keyIndex() {
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


}
