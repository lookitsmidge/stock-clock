public class E-Cig extends Product{
	String coilSupport;
	int batteryAmount;
	public String toString(){
		String temp =  productID + "," + name + "," + manufacturer + "," + price + "," + amountOfProduct + "," + coilSupport + "," + batteryAmount + ",";
		return temp;
	}
}