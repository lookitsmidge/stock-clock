public class Staff extends Person{
	int accessLevel;// can have SU, admin, general 0=su 1=admin 2=staff
	int dailyHours;
	int totalHours;
	int staffID;
	String password;
	
	public String toString(){
		String temp = staffID + "," + firstName + "," + lastName + "," + dob + "," + accessLevel + "," + gender + "," + dailyHours + "," + totalHours + "," + password + "," + houseNameNumber + "," + addressLn1 + "," + addressLn2 + "," + townCity + "," + postCode;
		return temp;
	}
}
/*
0 - staffId
1-firstName
2-lastName
3-dob
4-accessLevel
5-gender
6-dailyHours
7-totalHours
8-password
9-houseNameNumber
10-addressLn1
11-addressLn2
12-townCity
13-postCode
*/