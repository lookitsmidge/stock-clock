import java.io.*;
import java.util.*;
public class StaffList{
	String filename = "staffDetails.txt";
	Staff[] staffArray = new Staff[30];
	int nextStaffLocation = 0;
	int resultOfStaffSearch = -1;
	int[] searchLocations = new int[100];
	int nextSearchLocation = 0;
  int currentUserLocation;
	Encrypter E = new Encrypter();


  encryptionTestTest Etest = new encryptionTestTest();
		
	public void addToList(Staff tempStaff){
		System.out.println("[SList] adding Staff to staff array");
		staffArray[nextStaffLocation] = tempStaff;
		nextStaffLocation++;
    System.out.println(nextStaffLocation);
    System.out.println("[SList] Finished adding item to staff array");
	}
	public void readFromFile(){
		System.out.println("[SList] reading from file");
		nextStaffLocation = 0;
        int tempCount = 0;
         try{
        	System.out.println("[SLIST] starting Read");
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            System.out.println("[SLIST] reading first Line");
            String sReadLine = br.readLine();
            System.out.println("[SLIST] initialising Read Stage 2");
            while(sReadLine != null){
            	System.out.println("[SLIST] Reading Next Line");
                String[] splitData = (E.Decrypt(sReadLine)).split(",");
                
                Staff tempRead = new Staff();
                tempRead.staffID = Integer.parseInt(splitData[0]);
                tempRead.firstName = splitData[1];
                tempRead.lastName = splitData[2];
                tempRead.dob = splitData[3];
                tempRead.accessLevel = Integer.parseInt(splitData[4]);
                tempRead.gender = splitData[5].charAt(0);
                tempRead.dailyHours = Integer.parseInt(splitData[6]);
                tempRead.totalHours = Integer.parseInt(splitData[7]);
                tempRead.password = splitData[8];
                tempRead.houseNameNumber = splitData[9];
                tempRead.addressLn1 = splitData[10];
                tempRead.addressLn2 = splitData[11];
                tempRead.townCity = splitData[12];
                tempRead.postCode = splitData[13];
                System.out.println("[SLIST] Finished reading index: " + tempCount);
                System.out.println("[SLIST] Staff Found:\n"+tempRead.toString());
                addToList(tempRead);

                sReadLine = br.readLine();
                
            }
            
            fr.close();
        } catch(Exception exc){
            System.out.println("[SLIST] [ERROR] there was an error reading the Staff from the file at: " + tempCount + "   "  + exc);
        }
        printArray();
	}
	public void writeToFile(){
		System.out.println("[SLIST] Running writeListToFile");
         try{
             FileWriter fw = new FileWriter(filename);
             System.out.println("[LIST] FileWriter created");

             for(int i=0;i<nextStaffLocation;i++){
            	 
                 String currentPosData = E.Encrypt(staffArray[i].toString());//here is where you would encrypt
                 fw.write(currentPosData);
                 fw.write("\r\n");
                 System.out.println("[LIST] written " + i);
             }
             fw.close();
             System.out.println("[LIST] write complete");
         } catch(Exception exc){
             System.out.println("[LIST] [ERROR] there was an error writing to file "+exc);
    	}
	}
	public void searchForStaff(String searchParameter, int searchType){
      resultOfStaffSearch=-1; //reset results
      System.out.println("[LIST] Starting search");
   		if(searchType == 0){
        System.out.println("[SList] Search type 0 detected - first name");
        for(int i = 0; i < nextStaffLocation; i++){
   			System.out.println("[SLIST] [SEARCH] Searching at: " + i);
   			if(staffArray[i].firstName.equals(searchParameter)==true){
   				System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
   				resultOfStaffSearch = i;
   				System.out.println("[SLIST] set Staff searchLocation");
   				break;
   			} else {
   				System.out.println("[SLIST] Not Found - search Conntinue");
   			}
   		}
      } else if(searchType == 1){ // search by last name
        System.out.println("[SList] Search type 1 detected - search by last name");
        for(int i = 0; i < nextStaffLocation; i++){
   			System.out.println("[SLIST] [SEARCH] Searching at: " + i);
   			if(staffArray[i].lastName.equals(searchParameter)==true){
   				System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
   				resultOfStaffSearch = i;
   				System.out.println("[SLIST] set Staff searchLocation");
   				break;
   			} else {
   				System.out.println("[SLIST] Not Found - search Conntinue");
   			}
   		}
      } else if(searchType == 2){ // search by access level
        System.out.println("[SList] search type 2 detected - search by access level");
        for(int i = 0; i < nextStaffLocation; i++){
   			System.out.println("[SLIST] [SEARCH] Searching at: " + i);
   			if(staffArray[i].accessLevel == Integer.parseInt(searchParameter)){
   				System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
   				resultOfStaffSearch = i;
   				System.out.println("[SLIST] set Staff searchLocation");
   				break;
   			} else {
   				System.out.println("[SLIST] Not Found - search Conntinue");
   			}
   		}
      } else if(searchType == 3){ // search by staff ID
        System.out.println("[SList] search Type 3 detected - search by staff id");
        for(int i = 0; i < nextStaffLocation; i++){
   			System.out.println("[SLIST] [SEARCH] Searching at: " + i);
   			if(staffArray[i].staffID == Integer.parseInt(searchParameter)){
   				System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
   				resultOfStaffSearch = i;
   				System.out.println("[SLIST] set Staff searchLocation");
   				break;
   			} else {
   				System.out.println("[SLIST] Not Found - search Conntinue");
   			}
   		}
      } else{
        System.out.println("[SList] Search type not detected, please input a relavant search type");
      }
    }
    public void searchStaff(String searchParameter, int searchType){
    	nextSearchLocation = 0;
      resultOfStaffSearch=-1; //reset results
      System.out.println("[LIST] Starting search");
      if(searchType == 0){
        System.out.println("[SList] Search type 0 detected - first name");
        for(int i = 0; i < nextStaffLocation; i++){

   			  System.out.println("[SLIST] [SEARCH] Searching at: " + i);
   			  if(staffArray[i].firstName.equals(searchParameter)==true){
            System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
            searchLocations[nextSearchLocation] = i;
            nextSearchLocation++;
   				  System.out.println("[SLIST] set Staff searchLocation");
   				  break;
   			  } else {
   			  	System.out.println("[SLIST] Not Found - search Continue");
   		 	  }
   		 }
      } else if(searchType == 1){ // search by last name
        System.out.println("[SList] Search type 1 detected - search by last name");
        for(int i = 0; i < nextStaffLocation; i++){

          System.out.println("[SLIST] [SEARCH] Searching at: " + i);
          if(staffArray[i].lastName.equals(searchParameter)==true){
            System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
            searchLocations[nextSearchLocation] = i;
            nextSearchLocation++;
            System.out.println("[SLIST] set Staff searchLocation");
            break;
          } else {
            System.out.println("[SLIST] Not Found - search Continue");
          }
       }
      } else if(searchType == 2){ // search by access level
        System.out.println("[SList] search type 2 detected - search by access level");
        for(int i = 0; i < nextStaffLocation; i++){

          System.out.println("[SLIST] [SEARCH] Searching at: " + i);
          if(staffArray[i].accessLevel == Integer.parseInt(searchParameter)){
            System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
            searchLocations[nextSearchLocation] = i;
            nextSearchLocation++;
            System.out.println("[SLIST] set Staff searchLocation");
            break;
          } else {
            System.out.println("[SLIST] Not Found - search Continue");
          }
       }
      } else if(searchType == 3){ // search by staff ID
        System.out.println("[SList] search Type 3 detected - search by staff id");
        for(int i = 0; i < nextStaffLocation; i++){

          System.out.println("[SLIST] [SEARCH] Searching at: " + i);
          if(staffArray[i].staffID == Integer.parseInt(searchParameter)){
            System.out.println("[SLIST] [SEARCH] Found Staff at index: " + i + ". Breaking search.");
            searchLocations[nextSearchLocation] = i;
            nextSearchLocation++;
            System.out.println("[SLIST] set Staff searchLocation");
            break;
          } else {
            System.out.println("[SLIST] Not Found - search Continue");
          }
       }
      } else{
        System.out.println("[SList] Search type not detected, please input a relavant search type");
      }
    }
    public void removeStaff(int iD){
    	System.out.println("[SList] starting to delete the staff at given Id");
    	searchForStaff(iD+"", 3);
    	if(resultOfStaffSearch == -1){
    		System.out.println("[SList] [ERROR] deletion cannot be carried out. either staff id doesn`t exist or there is an error in the program");
    	} else{
    		System.out.println("[SList] [DEL] found the staff to be deleted");
    		Staff[] tempStaff = new Staff[100];
    		int tempStaffNextLocation = 0;
      		for(int i=0; i <nextStaffLocation; i++){
      			if(i == resultOfStaffSearch){
      			//skips record
      			} else{
      				//copies the data over
      				tempStaff[tempStaffNextLocation] = staffArray[i];
      				tempStaffNextLocation++;
      			}
      		}

      		System.out.println("[SList] [DEL] copying over tempArray");
      		nextStaffLocation = 0;
      		for(int i = 0; i< tempStaffNextLocation; i++){
      			staffArray[nextStaffLocation] = tempStaff[i];
      			nextStaffLocation++;
      		}
      		System.out.println("[SList] [DEL] finished deletion process");
      	}
    }
    public void printArray(){
      for(int i=0; i< nextStaffLocation; i++){
        System.out.println("\n\n\n\n[Print]:");
        System.out.println(staffArray[i].toString());
      }
    }
    public void sort(int mode){//0 = id, 1= name, 2=
      
      //Mode 1
      if(mode == 0){
        int n;
        int temp;
        boolean swapped = true;

        n = nextStaffLocation;
        while(swapped == true){
          swapped = false;
          for(int i = 0; i<n; i++){
            if(staffArray[i].staffID < staffArray[i+1].staffID){
              //finish this
            }
          }
        }
      } else if(mode == 2){} else {System.out.println("that is not a sort mode");}
    }
}