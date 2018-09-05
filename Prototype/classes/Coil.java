public class Coil extends Product{
	type = "Coil";
	public String toString(){
		String temp = productID + "," + name + "," + manufacturer + "," + price + "," + amountOfProduct + "," + supplierId;
		return temp;
	}
}

// when build list class for this, make sure to add it as type coil