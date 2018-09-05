public class Encrypter{
	public String Encrypt(String parameter){
		System.out.println("[LIST] Encrypting Data");
		String Lock_message = "";//this is the temporary message
		for(int i=0; i<parameter.length();i++) {//this loops through the message and encrypts it
			Lock_message = Lock_message + (""+(char)((int)parameter.charAt(i)+3));// this converts the charachter currently selected to be a integer and then adds 3 to it and then converts it back to a char then to a string and then adds it to the message
		}
		System.out.println("[LIST] Finished Encryption");
		return Lock_message;//returns the message
	}
	public String Decrypt(String parameter){
		System.out.println("[LIST] Decrypting Data");
		String Lock_message = "";//this is the temporary message
		for(int i=0; i<parameter.length();i++) {//this loops through the message and encrypts it
			Lock_message = Lock_message + (""+(char)((int)parameter.charAt(i)-3));// this converts the charachter currently selected to be a integer and then subtracts 3 to it and then converts it back to a char then to a string and then adds it to the message
		}
		System.out.println("[LIST] Decryption complete");
		return Lock_message;//returns the message
	}
}