package NinCrypt;

public abstract class Crypt {
	protected String plainText;
	protected String cipherText;
	protected Integer key;
	
	public Crypt() {
		plainText = "";
		cipherText = "";
		key = null;
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
		key = intHashKey(k);
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
	
	//Unsecure int hash
	protected Integer intHashKey(String s) {
		Integer hash = 0;
		
		for (int i = 0;i<s.length();i++) {
			Character c = s.charAt(i);
			c = (char) (c%2 == 0 ? c : Character.reverseBytes(c)-c);
			hash = hash+c;
		}
		
		return hash;
	}
	
	protected boolean hasPlainText() {
		return !plainText.isEmpty();
	}
	
	protected boolean hasCipherText() {
		return !cipherText.isEmpty();
	}
	
	protected boolean hasKey() {
		return key != null;
	}

}
