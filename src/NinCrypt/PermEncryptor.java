package NinCrypt;

import java.util.Arrays;

/*
 *algo: 
 * "hash" key to int
 * use key length to determine block size
 * use key to order the blocks
 * shift odd number letters in block, depending on key (maybe reversed for where to shift)
 * 
 * decrypt: 420 reverse it brotendo
 * 
 */

public class PermEncryptor extends Crypt {
	
	public PermEncryptor() {
		super();
	}
	
	public void encrypt() throws IllegalStateException {
		if (!hasPlainText() || !hasKey()) {
			throw new IllegalStateException("The encryptor is missing a property!");
		}
		permEncrypt();
	}
	
	private void permEncrypt() {
		StringBuilder deconstructor = new StringBuilder(plainText);
		StringBuilder cipherBuilder = new StringBuilder(plainText);
		int columns,rows; //rows aka blockSize
		rows = key.toString().length();
		columns = plainText.length()/rows+1;
		
		char[][] plainChart = new char[columns][rows];
		char[][] cipherChart = new char[columns][rows];
		
		//Building array
		int i = 0;
		while (deconstructor.length()>0) {
			plainChart[i/rows][i%rows] = deconstructor.charAt(0);
			deconstructor.deleteCharAt(0);
			i++;
		}
		
		
		//Move plainChart rows to cipherChart depending on KEY
		int toRow = key;
		for (int r = 0;r<columns;r++)  {
			toRow = toRow;
			System.out.print(plainChart[r]);
			cipherChart[r] = plainChart[r];
		}
		
		
		
		/*
		//write out
		for (int j = 0;j<(columns*rows);j++) {
			System.out.print(plainChart[j/rows][j%rows]);
			
		}
		*/
		System.out.print("\n");
		
		System.out.println();

		
		
		deconstructor.append("slusk");
		System.out.println("pt length: "+plainText.length()+" blocksize: "+rows);
		
		cipherText = cipherBuilder.toString();//"wananananananana";
	}

}
