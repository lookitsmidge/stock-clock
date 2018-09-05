public class Customer extends Person{
    int customerID;

    public String toString(){
        String temp = customerID + "," + firstName + "," + lastName + "," + dob + "," + gender + "," + houseNameNumber + "," + addressLn1 + "," + addressLn2 + "," + townCity + "," + postCode;
        return temp;
    }

}