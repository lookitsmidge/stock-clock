import java.io.*;
import java.util.*;
public class clockList{
	GetTime GT = new GetTime();
	String filename = GT.generateFilename();
	clock[] arrayClock = new clock[30];
	//int nextClockLocation = 0;
	boolean saveSuccess = false;
	public void fillArray() {
		for(int i=0; i <30; i++){
			clock tempClock = new clock();
			tempClock.staffID = i;
			tempClock.startClock1 = "Null";
			tempClock.endClock1 = "Null";
			tempClock.startClock2 = "Null";
			tempClock.endClock2 = "Null";
			arrayClock[i] = tempClock;
		}
	}
	public void recordTime(int staffID){
		saveSuccess = false;
		System.out.println("recording clock time");
		String time = GT.getTimeCurrent();
		System.out.println("got time");
		/*
		check for blanks in the clock array.
		if blank start
			set time to that
		else if blank second
			set time to that.
		*/

		
		//arrayClock[staffID].staffID = staffID;

		System.out.println("Set ID");
		saveSuccess = true;
		if(((arrayClock[staffID].startClock1).equals("Null"))==true){
			//set time to be first clock in
			System.out.println("got into if");
			arrayClock[staffID].startClock1 = time;
			System.out.println("[CLOCK] saved time to startClock1");
		} else if(((arrayClock[staffID].endClock1).equals("Null"))==true){
			//set time to be when first clock out
			System.out.println("got into if");
			arrayClock[staffID].endClock1 = time;
			System.out.println("[CLOCK] saved time to endClock1");
		} else if(((arrayClock[staffID].startClock2).equals("Null"))==true){
			//set time to be when staff clock back in after lunch
			System.out.println("got into if");
			arrayClock[staffID].startClock2 = time;
			System.out.println("[CLOCK] saved time to startClock2");

		}else if(((arrayClock[staffID].endClock2).equals("Null"))==true){
			//set time to be when staff clock out at the end of the day
			System.out.println("got into if");
			arrayClock[staffID].endClock2 = time;
			System.out.println("[CLOCK] saved time to endClock2");
		} else{
			//clock time cannot be recorded as all attributes are full
			saveSuccess = false;
			System.out.println("[CLOCK] [ERROR] unable to save clock time: all slots full");
		}
		System.out.println("successful");
		/*switch (1) {
			case boolToInt(arrayClock[staffID].startClock1.isEmpty()):
				//set the time for the staff at the start of the day.
				arrayClock[staffID].startClock1 = time;
				System.out.println("[CLOCK] saved time to startClock1");
				break;
			case boolToInt(arrayClock[staffID].endClock1.isEmpty()):
				//set the time for when the staff leave for lunch
				arrayClock[staffID].endClock1 = time;
				System.out.println("[CLOCK] saved time to endClock1");
				break;
			case boolToInt(arrayClock[staffID].startClock2.isEmpty()):
				//set time to be when staff clock out at the end of the day
				arrayClock[staffID].endClock2 = time;
				System.out.println("[CLOCK] saved time to endClock2");
			case boolToInt(arrayClock[staffID].endClock2.isEmpty()):
				//sets time to be when staff leave at the end of the day
				arrayClock[staffID].endClock2 = time;
				System.out.println("[CLOCK] saved time to endClock2");
			default:
				//clock time cannot be recorded as all attributes are full
				saveSuccess = false;
				System.out.println("[CLOCK] {ERROR] unable to save clock time: all slots full");
		}*/
	}
	// public int boolToInt(boolean bool){
	// 	int temp;
	// 	if (bool = true) {
	// 		temp = 1;
	// 	} else {
	// 		temp = 0;
	// 	}
	// 	return temp;
	//  }
	public double calculateTime(int staffID) {
		double total = 0;
		total = Double.parseDouble(arrayClock[staffID].endClock1) - Double.parseDouble(arrayClock[staffID].startClock1);
		total = total + (Double.parseDouble(arrayClock[staffID].endClock2) - Double.parseDouble(arrayClock[staffID].startClock2));
		return total;
	}
	public void addToList(clock item){
		System.out.println("[CLOCK] adding item to the array");
		int tempLocation = item.staffID;
		arrayClock[tempLocation] = item;
		System.out.println("[CLOCK] finsihed adding the item to the array");
	}
	public void readFromFile() {
		System.out.println("[CLOCK] reading from a file");
		try {
			System.out.println("[CLOCK] starting read...");
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			System.out.println("[CLOCK] reading first Line...");

			String readLine = br.readLine();
			System.out.println("[CLOCK] starting loop");

			while (readLine != null) {
				System.out.println("[CLOCK] Reading next line");

				String[] splitData = readLine.split(",");
				clock tempRead = new clock();

				tempRead.staffID = Integer.parseInt(splitData[0]);
				tempRead.startClock1 = splitData[1];
				tempRead.endClock1 = splitData[2];
				tempRead.startClock2 = splitData[3];
				tempRead.endClock2 = splitData[4];

				System.out.println("[CLOCK] finished reading,\nSaving beginning...");

				addToList(tempRead);
			}

			fr.close();
		} catch (Exception exc){
			//use logger to catch exception
			System.out.println("[CLOCK] [ERROR] there was an error reading from the file: " + exc);
		}
	}
	public void writeToFile() {
		System.out.println("[CLOCK] writing the clock details to the file");
		try {
			FileWriter fw = new FileWriter(filename);
			System.out.println("[CLOCK] Running write to file");

			for (clock i : arrayClock){ // iterates through array, missing empty spaces
				String currentData = i.toString();
				fw.write(currentData);
				fw.write("\r\n");
				System.out.println("List Written");
			}
			fw.close();
		} catch(Exception exc) {
			System.out.println("[ERROR] error in writing to file: " + exc);
		}
	}	
	// public void initialiseClocks() {
		
	// }
}