import java.util.*;
public class TestProg{

	StaffList SL = new StaffList();
	Scanner iScanner = new Scanner(System.in);

	public static void main(String[] args){
		TestProg TP = new TestProg();
		TP.startTest();
	}
	public void startTest(){
		SL.readFromFile();
		SL.printArray();
		/*for(int i=0; i<3; i++){
			addData();
		}*/
		SL.writeToFile();
	}
	public void addData(){
		iScanner.nextLine();
		Staff tempAdd = new Staff();
		System.out.println("firstName:");
		tempAdd.firstName = iScanner.nextLine();
		System.out.println("lastName: ");
		tempAdd.lastName = iScanner.nextLine();
		System.out.println("dob: ");
		tempAdd.dob = iScanner.nextLine();
		try{
		System.out.println("accessLevel: ");
		tempAdd.accessLevel = iScanner.nextInt();
		System.out.println("dailyhours: ");
		tempAdd.dailyHours = iScanner.nextInt();
		System.out.println("total hours: ");
		tempAdd.totalHours = iScanner.nextInt();
		System.out.println("staffID: ");
		tempAdd.staffID = iScanner.nextInt();
		} catch(NumberFormatException exc){ System.out.println("an error occurred");}
		System.out.println("No errors occurred");
		SL.addToList(tempAdd);

	}
	
}





// check and delete if inactive set to true after a month