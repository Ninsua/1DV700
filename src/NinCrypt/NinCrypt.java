package NinCrypt;

/*
 * TODO
 * Key to fixed size, reversible algorithm
 * algo: c+intKey and reversebytes
 * de-algo: c = reversebytes
 * p = c-intkey
 * 
 */

import java.util.Scanner;
import java.lang.StringBuilder;

public class NinCrypt {
	
	public static String subEncrypt(String p,String key) {
		StringBuilder chipher = new StringBuilder();
		String encKey = key;
		int intKey = 0;
		
		//Pad
		if (encKey.length()%2 == 1) {
			encKey = encKey+"0";
		}
		
		for (int i = 0;i<encKey.length();i++) {
			Character c = encKey.charAt(i);
			c = Character.reverseBytes(c);
			intKey = intKey+c+encKey.length();
		}
		
		System.out.println(intKey);
		
		for (int i = 0;i<1;i++) {
			Character c = p.charAt(i);
			Character.reverseBytes();
			char a = (char)c+intKey;
			chipher.append(c);
			//System.out.print(Character.reverseBytes(c));
		}
		
		return "";
		
	}
	
	public static char subCharSwap() {
		return 'A';
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String toEncrypt;
		String key;
		
		//System.out.print("toEncrypt: ");
		//toEncrypt = input.nextLine();
		
		toEncrypt = "Testing";
		key = "secret";
		
		System.out.println(toEncrypt+" "+key);
		System.out.println(subEncrypt(toEncrypt,key));
	}

}
