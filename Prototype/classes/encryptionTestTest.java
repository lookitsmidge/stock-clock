
public class encryptionTestTest {

	public encryptionTestTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		encryptionTestTest ET = new encryptionTestTest();
		ET.phoneHome();

	} // sub all spaces for ALT 0196
	public void phoneHome() {
		
		String encryptMessage = Encrypt("Does this work properly. this is an english essay in computer science");
		System.out.println(encryptMessage);
		System.out.println(Decrypt(encryptMessage));
	}
	
	public String Encrypt(String parameter) {
		
		//parameter = parameter.replace(" ", "%");
		System.out.println(parameter);
		//split into half
		//ecrypt each half
		//intertwine each of them
		//char into int and then bubble sort (1 iteration)
		System.out.println("[ENC] encrypting");
		String sentO = "";
		String sent1 = "";
		String sent2 = "";
		int length = parameter.length();
		
	//split the message
		String[] parts =new String[2];
		System.out.println("[ENC] splitting");
		if(length%2 == 0){
			parts[0] = parameter.substring(0, length/2);
			parts[1] = parameter.substring(length/2);
		} else{
			parts[0] = parameter.substring(0, (length/2));
			parts[1] = parameter.substring((length/2), length);
		}
		sent1 = parts[0];
		sent2 = parts[1];
		

		System.out.println("\n\n\n"+sent1+"\n"+sent2+"\n\n\n");
		/*for(int i=0;i<(length);i++) {
			if(i < (length/2)) {
				sent1 = sent1 + parameter.charAt(i);
			} else {
				sent2 = sent2 + parameter.charAt(i);
			}
		}
		if(length%2 == 1) {
			sent2 = sent2 + parameter.charAt(length-1);
		}*/
		System.out.println("[ENC] adding together");
		for(int i=0;i <length/2; i++) {
			sentO = sentO + sent1.charAt(i) + "" + sent2.charAt(i);
		}
		
		if(length%2 == 1){
			sentO = sentO + sent2.charAt(sent2.length()-1);
		} else{

		}
		/*if(length%2 == 1) {
			sentO = sentO + sent2.charAt((length/2));
		}*/
		System.out.println("[ENC] Encrypting Data");
		String Lock_message = "";//this is the temporary message

		int j =0;
		for(int i=0; i<sentO.length();i++) {//this loops through the message and encrypts it
			if(j<21){
				Lock_message = Lock_message + (""+  (char)(   (int)sentO.charAt(i)+j+1)  );// this converts the charachter currently selected to be a integer and then adds 3 to it and then converts it back to a char then to a string and then adds it to the message
			} else{
				j=0;
				Lock_message = Lock_message + (""+  (char)(   (int)sentO.charAt(i)+j+1)  );// this converts the charachter currently selected to be a integer and then adds 3 to it and then converts it back to a char then to a string and then adds it to the message

			}
			j++;
		}
		sentO = Lock_message;
		System.out.println("\n\nSentO: "+sentO);
		
		System.out.println("\n\n\n\n[ENC] Encryption complete\n\n\n\n");
		
		
		return sentO;
	}
	public String Decrypt(String parameter) {
		System.out.println("[DEC] decrypting");
		String Lock_message = "";//this is the temporary message
		
		int j = 0;
		for(int i=0; i<parameter.length();i++) {//this loops through the message and encrypts it
			System.out.println("Iteration"+i);
			if(j < 21){
				Lock_message = Lock_message + (""+(char)( (int)(parameter.charAt(i)-j-1)));// this converts the charachter currently selected to be a integer and then adds 3 to it and then converts it back to a char then to a string and then adds it to the message
			} else{
				j=0;
				Lock_message = Lock_message + (""+(char)( (int)(parameter.charAt(i)-j-1)));// this converts the charachter currently selected to be a integer and then adds 3 to it and then converts it back to a char then to a string and then adds it to the message
			}
			
			System.out.println("	[DEC]LockMessage: "+Lock_message);
			j++;
		}
		System.out.println(Lock_message);
		String sent1 = "";
		String sent2 = "";
		String sentO = "";
		sentO = Lock_message;
		boolean temp = false;
		System.out.println("[DEC] parting parameter");
		if(parameter.length()%2 == 0){
			for(int i=0;i <sentO.length(); i++) {
				System.out.println("[DEC] - 1 iteration "+i);
				if(temp == false) {
					temp = true;
					sent1 = sent1 + sentO.charAt(i);
				} else {
					temp = false;
					sent2 = sent2 + sentO.charAt(i);
					
				}
				System.out.println(sent1);
				System.out.println(sent2);
				System.out.println(sentO);
				
			}
		} else{
			for(int i=0;i <sentO.length()-1; i++) {
				System.out.println("[DEC] - 2 iteration "+i);
				if(temp == false) {
					temp = true;
					sent1 = sent1 + sentO.charAt(i);
				} else {
					temp = false;
					sent2 = sent2 + sentO.charAt(i);
					
				}
				System.out.println(sent1);
				System.out.println(sent2);
				System.out.println(sentO);
				
			}
			sent2 = sent2 + sentO.charAt(sentO.length()-1);
		}
		System.out.println("[DEC] concatanating");
		System.out.println("[DEC] finished decryption");
		sentO = sent1 + sent2;
		//sentO = sentO.replace("%", " ");
		return sentO;
	}

}
