package NinCrypt;

public class SubEncryptor extends Crypt {
	
	/*
	 * TODO
	 * match subDecCharSwap to potentially fix the OS font issues
	 */
	
	public SubEncryptor() {
		super();
	}
	
	public void encrypt() throws IllegalStateException {
		if (!hasPlainText() || !hasKey()) {
			throw new IllegalStateException("The encryptor is missing a property!");
		}
		subEncrypt();
	}
	
	private void subEncrypt() {
		StringBuilder cipherBuilder = new StringBuilder();
		
		for (int i = 0;i<plainText.length();i++) {
			Character c = plainText.charAt(i);
			char e = subEncCharSwap(c,key,plainText.length()-i);
			cipherBuilder.append(e);
		}
	
		cipherText = cipherBuilder.toString();
	}
	
	public void subDecrypt() {
		StringBuilder plainTextBuilder = new StringBuilder();
		
		for (int i = 0;i<cipherText.length();i++) {
			Character c = cipherText.charAt(i);
			char d = subDecCharSwap(c,key,cipherText.length()-i);
			plainTextBuilder.append(d);
		}
		
		plainText = plainTextBuilder.toString();
	}
	
	private char subEncCharSwap(char c,int key,int seed) {
		return (char) (c+(key%26-seed)); // Windows is bork? maybe
		//return Character.reverseBytes((char) (c+(key-seed)));
	}
	
	private char subDecCharSwap(char c,int key,int seed) {
		return (char) (Character.reverseBytes(c)-key+seed);
	}
	
}
