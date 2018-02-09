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
		PermEncryptor perm = new PermEncryptor();
		
		//System.out.print("toEncrypt: ");
		//toEncrypt = input.nextLine();
		
		//Ⲷ㺶䶶侶䖶䮶䖶ﾵ侶䞶ʶ垶䲶亶妶޶䶶垶䶶ಶ
		sub.setPlainText("Testing of this enc!");
		sub.setKey("S3c|2et k3y 1s s3c|23t!!1");
		sub.encrypt();
		//sub.setCipherText("Ⲷ㺶䶶侶䖶䮶䖶ﾵ侶䞶ʶ垶䲶亶妶޶䶶垶䶶ಶ");
		//sub.subDecrypt();
		
		//System.out.println(sub.getPlainText()); 
		//System.out.println(sub.getCipherText());
		
		perm.setPlainText("A more complex transposition cipher is to write\n" + 
				"the message in a rectangle, row by row, and\n" + 
				"read the message off, column by column but\n" + 
				"permute the order of the columns");
		perm.setKey("S3c|2et k3y 1s s3c|23t!!1");
		perm.encrypt();
		//perm.setCipherText("Ⲷ㺶䶶侶䖶䮶䖶ﾵ侶䞶ʶ垶䲶亶妶޶䶶垶䶶ಶ");
		//perm.subDecrypt();
		
		//System.out.println(perm.getPlainText()); 
		//System.out.println(perm.getCipherText());
		

	}

}
