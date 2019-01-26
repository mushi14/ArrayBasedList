public class Product {
	
	//TODO: Declare/initialize instance members
	// Variables that will be needed
	private String asin;
	private int index;
	private double avg_Rating;
	
	//TODO: Create constructor(s)
	// Constructor for Product class
	public Product(String a, double avgRat, int i) {
		//TODO: Complete
		asin = a;
		avg_Rating = avgRat;
		index = i;
	}
	
	//TODO: Create instance methods
	// Returns ASIN
	public String getAsin() {
		return asin;
	}
	
	// Gets average rating for elements in plist
	public double getAvgRat() {
		return avg_Rating;
	}
	
	// Method for setting asin to a certain string
	public void setAsin(int index, String a) {
		asin = a;
	}
	
	@Override
	// toString() method that prints ASIN and their average ratings
	public String toString() {
		return "ASIN: " + asin + " AVERAGE RATINGS: " + avg_Rating + "\n";
	}
}
