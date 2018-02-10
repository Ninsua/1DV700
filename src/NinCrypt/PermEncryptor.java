package NinCrypt;

public class PermEncryptor extends Crypt {
	
	/*
	 * TODO
	 * Fix the padding issues
	 */
	
	public PermEncryptor()  {
		super();
	}
	
	@Override
	public void encrypt() throws IllegalStateException {
		if (!hasPlainText() || !hasKey()) {
			throw new IllegalStateException("Error: The encryptor is missing a property!");
		}
		permEncrypt();
	}
	
	@Override
	public void decrypt() throws IllegalStateException {
		if (!hasCipherText() || !hasKey()) {
			throw new IllegalStateException("Error: The decryptor is missing a property!");
		}
		permDecrypt();
	}
	
	private void permEncrypt() {
		StringBuilder cipherBuilder = new StringBuilder();
		String toEnc = plainText;
		int toEncLength = toEnc.length();
		int keyLength = key.length();
		
		//Check if padding is necessary
		if (toEncLength%keyLength != 0) {
			int charsToPad = toEncLength-(toEncLength%keyLength)+keyLength;
			toEnc = pad(toEnc,charsToPad);
			toEncLength = toEnc.length();
		}

		int columns = keyLength;
		Double d = Math.ceil(Double.valueOf(Double.valueOf(toEncLength)/Double.valueOf(columns))); //Round upwards too make sure everything fits in the array
		int rows = d.intValue();
		char[][] rowArray = new char[rows][columns];
		char[][] columnArray = new char[columns][rows];
		char[][] cipherArray = new char[columns][rows];
		int[] keyIndex = keyIndex();
		
		//Building the row based array;
		for (int i=0; i < toEncLength; i++) {
			rowArray[i/columns][i%columns] = toEnc.charAt(i);
		} 

		//Building the column based array;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				columnArray[j][i] = rowArray[i][j];
			}
		}
		
		//Building the cipherarray
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				cipherArray[keyIndex[i]][j] = columnArray[i][j];
			}
		}

		for (int i = 0; i < toEncLength; i++) {
			cipherBuilder.append(cipherArray[i/rows][i%rows]);
		}

		cipherText = cipherBuilder.toString();
	}
	
	public void permDecrypt() {
		StringBuilder plainBuilder = new StringBuilder();
		String toDec = cipherText;
		int toDecLength = toDec.length();
		int keyLength = key.length();
		
		Double d = Math.ceil(Double.valueOf(Double.valueOf(toDecLength)/Double.valueOf(keyLength))); //Round upwards too make sure everything fits in the array
		int columns = d.intValue();;
		int rows = keyLength;
		char[][] rowArray = new char[rows][columns];
		char[][] columnArray = new char[columns][rows];
		char[][] plainArray = new char[columns][rows];
		int[] keyIndex = keyIndex();
		
		//Building the row based array;
		for (int i = 0; i < toDecLength; i++) {
			rowArray[i/columns][i%columns] = toDec.charAt(i);
		}
		
		//Building the column based array;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				columnArray[j][i] = rowArray[i][j];
		
		//Building the plainArray
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < rows; j++)
				plainArray[i][j] = columnArray[i][keyIndex[j]];
		
		for (int i = 0; i < toDecLength; i++) {
			plainBuilder.append(plainArray[i/rows][i%rows]);
		}
		
		
		plainText = plainBuilder.toString();
	}
}