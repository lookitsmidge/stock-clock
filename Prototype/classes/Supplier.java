public class Supplier{
	Int id;
	String name;
	String type;
	String address_Ln1;
	String address_Ln2;
	String town;
	String county;
	String postCode;
	// add supplier id under product
	public String toString(){
		String temp = id + "," + name + "," + type + "," + address_Ln1 + "," + address_Ln2 + "," + town + "," + county + "," + postCode;
		return temp;
	}

}