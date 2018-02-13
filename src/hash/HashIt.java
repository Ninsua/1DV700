package hash;

public class HashIt {
	
	private static Integer hash(byte[] msg) {
		Integer hash = 0xDECFBAD;
		for (int i = 0; i < msg.length; i++) {
			hash = ((hash << 5)^(hash >> 27)^msg[i]);
		}
		
		return (hash & 0x7FFFFFFF);
	}

	public static void main(String[] args) {
		String test = "Hash";
		
		for (byte b : test.getBytes()) {
			
			
			
			
			System.out.println(b);
		}
		
		Integer t = 0x7FFFFFFF;
		Integer i = 5;
		
		System.out.println((i << 1));
		
		
		/*
		for (Integer i = 0;i<1;i++) {
			Integer testHash = hash(i.toString().getBytes());
			System.out.println(testHash);
		}
		*/
		
		//System.out.println(testHash);

	}

}
