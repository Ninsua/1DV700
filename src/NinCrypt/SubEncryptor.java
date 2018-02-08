package NinCrypt;

public class SubEncryptor extends Crypt {
	private Integer key;
	
	public SubEncryptor() {
		super();
	}
	
	public void encrypt() throws IllegalStateException {
		if (!hasPlainText() || !hasKey()) {
			throw new IllegalStateException("The encryptor is missing a property!");
		}
		subEncrypt();
	}
	
	public void setKey(String k) throws IllegalArgumentException {
		if (k.isEmpty()) {
			throw new IllegalArgumentException("Key cannot be empty!");
		}
		key = intHashKey(k);
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
		return Character.reverseBytes((char) (c+(key-seed)));
	}
	
	private char subDecCharSwap(char c,int key,int seed) {
		return (char) (Character.reverseBytes(c)-key+seed);
	}
	
	private boolean hasKey() {
		return key != null;
	}
	
}
