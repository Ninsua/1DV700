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
	
	


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String toEncrypt;
		String key;
		SubEncryptor sub = new SubEncryptor();
		
		//System.out.print("toEncrypt: ");
		//toEncrypt = input.nextLine();
		
		//Ⲷ㺶䶶侶䖶䮶䖶ﾵ侶䞶ʶ垶䲶亶妶޶䶶垶䶶ಶ
		sub.setPlainText("Testing of this enc!");
		sub.setKey("S3c|2et k3y 1s s3c|23t!!1");
		sub.encrypt();
		
		//System.out.println(toEncrypt+" "+key);
		System.out.println(sub.getPlainText()); 
		System.out.println(sub.getCipherText());
	}

}
