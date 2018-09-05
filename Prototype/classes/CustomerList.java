import java.io.*;
import java.util.*;
public class CustomerList{
	String filename = "customerDetails.txt";
	Customer[] customerArray = new Customer[300];
	int nextCustomerLocaton = 0;
	int resultOfCustomerSearch = -1;
	int[] searchLocations = new int[100];
	int nextSearchLocation = 0;

	public void addToList(Customer tempCustomer){
		System.out.println("[CList] adding customer to customer array");
		customerArray[nextCustomerLocation] = tempCustomer;
		nextCustomerLocation++;
	}
	public void readFromFile(){
		System.out.println("[CList] reading from file");
		nextCustomerLocation = 0;
        int tempCount = 0;
         try{
        	System.out.println("[CLIST] starting Read");
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            System.out.println("[CLIST] reading first Line");
            String sReadLine = br.readLine();
            System.out.println("[CLIST] initialising Read Stage 2");
            while(sReadLine != null){
            	System.out.println("[CLIST] Reading Next Line");
                String[] splitData = (Etest.Decrypt(sReadLine)).split(",");
                Staff tempRead = new Customer();
                tempRead.firstName = splitData[0];
                tempRead.lastName = splitData[1];
                tempRead.dob = splitData[2];
                //tempRead.accessLevel = Integer.parseInt(splitData[3]);
                //tempRead.dailyHours = Integer.parseInt(splitData[4]);
                //tempRead.totalHours = Integer.parseInt(splitData[5]);
                //tempRead.staffID = Integer.parseInt(splitData[6]);
                //tempRead.password = splitData[7];
                System.out.println("[CLIST] Finished reading index: " + tempCount);
                System.out.println("[CLIST] Staff Found:\n"+tempRead.toString());
                addToList(tempRead);

                sReadLine = br.readLine();
                
            }
            
            fr.close();
        } catch(Exception exc){
            System.out.println("[CLIST] [ERROR] there was an error reading the Staff from the file at: " + tempCount + "   "  + exc);
        }
        printArray();
	}
	public void printArray(){
      for(int i=0; i< nextCustomerLocation; i++){
        System.out.println("\n\n\n\n[Print]:");
        System.out.println(customerArray[i].toString());
      }
    }
    public void writeToFile(){
        System.out.println("[CLIST] Running writeListToFile");
         try{
             FileWriter fw = new FileWriter(filename);
             System.out.println("[CLIST] FileWriter created");

             for(int i=0;i<nextCustomerLocation;i++){
                 
                 String currentPosData = Etest.Encrypt(customerArray[i].toString());
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
    public void removeCustomer(int iD){
        System.out.println("[SList] starting to delete the staff at given Id");
        searchForStaff(iD+"", 3);
        if(resultOfCustomerSearch == -1){
            System.out.println("[SList] [ERROR] deletion cannot be carried out. either customer id doesn`t exist or there is an error in the program");
        } else{
            System.out.println("[SList] [DEL] found the customer to be deleted");
            Customer[] tempCustomer = new Customer[100];
            int tempCustomerNextLocation = 0;
            for(int i=0; i <nextCustomerLocation; i++){
                if(i == resultOfCustomerSearch){
                //skips record
                } else{
                    //copies the data over
                    tempCustomer[tempCustomerNextLocation] = customerArray[i];
                    tempCustomerNextLocation++;
                }
            }

            System.out.println("[SList] [DEL] copying over tempArray");
            nextCustomerLocation = 0;
            for(int i = 0; i< tempCustomerNextLocation; i++){
                cusotmerArray[nextCustomerLocation] = tempCustomer[i];
                nextCustomerLocation++;
            }
            System.out.println("[SList] [DEL] finished deletion process");
        }
    }
    public void searchForCustomer(String searchParameter, int searchType){
      resultOfCustomerSearch=-1; //reset results
      System.out.println("[LIST] Starting search");
        if(searchType == 0){
        System.out.println("[SList] Search type 0 detected - first name");
        for(int i = 0; i < nextCustomerLocation; i++){
            System.out.println("[SLIST] [SEARCH] Searching at: " + i);
            if(customerArray[i].firstName.equals(searchParameter)==true){
                System.out.println("[SLIST] [SEARCH] Found Customer at index: " + i + ". Breaking search.");
                resultOfCustomerSearch = i;
                System.out.println("[SLIST] set Customer searchLocation");
                break;
            } else {
                System.out.println("[SLIST] Not Found - search Conntinue");
            }
        }
      } else if(searchType == 1){ // search by last name
        System.out.println("[SList] Search type 1 detected - search by last name");
        for(int i = 0; i < nextCustomerLocation; i++){
            System.out.println("[SLIST] [SEARCH] Searching at: " + i);
            if(customerArray[i].lastName.equals(searchParameter)==true){
                System.out.println("[SLIST] [SEARCH] Found Customer at index: " + i + ". Breaking search.");
                resultOfCustmerSearch = i;
                System.out.println("[SLIST] set Customer searchLocation");
                break;
            } else {
                System.out.println("[SLIST] Not Found - search Conntinue");
            }
        }
      } else if(searchType == 2){ // search by access level
            System.out.println("[LIST] this is not a valid search type");
      } else if(searchType == 3){ // search by Customer ID
        System.out.println("[SList] search Type 3 detected - search by staff id");
        for(int i = 0; i < nextCustomerLocation; i++){
            System.out.println("[SLIST] [SEARCH] Searching at: " + i);
            if(customerArray[i].customerID == Integer.parseInt(searchParameter)){
                System.out.println("[SLIST] [SEARCH] Found Customer at index: " + i + ". Breaking search.");
                resultOfCustomerSearch = i;
                System.out.println("[SLIST] set Customer searchLocation");
                break;
            } else {
                System.out.println("[SLIST] Not Found - search Conntinue");
            }
        }
      } else{
        System.out.println("[SList] Search type not detected, please input a relavant search type");
      }
    }
    public void sort(int mode){//0 = id, 1= name, 2=
      
      //Mode 1
      if(mode == 0){
        int n;
        int temp;
        boolean swapped = true;

        n = nextCustomerLocation;
        while(swapped == true){
          swapped = false;
          for(int i = 0; i<n; i++){
            if(customerArray[i].customerID < customerArray[i+1].customerID){
              //finish this
            }
          }
        }
      } else if(mode == 2){} else {System.out.println("that is not a sort mode");}
    }
}