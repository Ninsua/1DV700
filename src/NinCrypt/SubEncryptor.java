package NinCrypt;

public class SubEncryptor extends Crypt {
	
	/*
	 * TODO
	 * Implement a more secure algorithm when time allows it
	 */
	
	public SubEncryptor() {
		super();
	}
	
	@Override
	public void encrypt() throws IllegalStateException {
		if (!hasPlainText() || !hasKey()) {
			throw new IllegalStateException("The encryptor is missing a property!");
		}
		subEncrypt();
	}
	
	@Override
	public void decrypt() throws IllegalStateException {
		if (!hasCipherText() || !hasKey()) {
			throw new IllegalStateException("Error: The decryptor is missing a property!");
		}
		subDecrypt();
	}
	
	
	private void subEncrypt() {
		StringBuilder cipherBuilder = new StringBuilder();
		String toEnc = plainText;
		int toEncLength = toEnc.length();
		int keyLength = key.length();

		int[] keyIndex = keyIndex();
		
		for (int i = 0;i<toEncLength;i++) {
			cipherBuilder.append(subCharSwap(toEnc.charAt(i),keyIndex[i%keyLength]));
		}
	
		cipherText = cipherBuilder.toString();
	}
	
	public void subDecrypt() {
		StringBuilder plainTextBuilder = new StringBuilder();
		String toDec = cipherText;
		int toDecLength = toDec.length();
		int keyLength = key.length();

		int[] keyIndex = keyIndex();
		
		if (keyLength == 1 && Character.isDigit(key.charAt(0))) {
			int simpleKey = Integer.parseInt(key);
			for (int i = 0;i<toDecLength;i++) {
				plainTextBuilder.append(subCharSwap(toDec.charAt(i),-simpleKey));
			}
		}
		
		else {
			for (int i = 0;i<toDecLength;i++) {
				plainTextBuilder.append(subCharSwap(toDec.charAt(i),-keyIndex[i%keyLength]));
			}
		}

		
		plainText = plainTextBuilder.toString();
	}
	
	//Swaps
	private char subCharSwap(char c,int key) {
		final int LETTERS;
		int base,offset;
		LETTERS = 26;
		base = 0;
		
		if ('A' <= c && c <= 'Z') {
			base = 'A';
		}
		else if ('a' <= c && c <= 'z') {
			base = 'a';
		}
		else {
			return c;
		}
		
		offset = c-base+key;
		
		if (offset > LETTERS) {
			offset = offset-LETTERS;
		}
		else if (offset < 0 ) {
			offset = offset+LETTERS;
		}
		
		return (char) (base+offset); 
	}
}
