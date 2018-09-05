public class clock{
	//per day
	//String clockID;
	int staffID;
	String startClock1;
	String endClock1;
	String startClock2;
	String endClock2;
	public String toString(){
		String info = staffID + startClock1 + "," + endClock1 + "," + startClock2 + "," + endClock2;
		return info;
	}

}



//for clock if staff dont log in it will break, so on  calculation i will need to check that the item is not null, if it is then it will break as the 'int' will be null. also need to check that staff sign out
//user could sign in and then go and be auto signed out at the end of the day




// ln 590