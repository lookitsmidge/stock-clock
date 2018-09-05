public class Liquid extends Product{
	type = "liquid";
	int strength;
	String flavour;
	public String toString(){
		String temp = productID + "," + name + "," + manufacturer + "," + price + "," + amountOfProduct + "," + supplierId + "," + strength + "," + flavour;
		return temp;
	}
}