import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		//TODO: Read .csv file from command line and parse into ArrayBasedList<Product>
		// You should not need to declare another ArrayBasedList<Product> in main
		
		// Variable for creating adjustable length of the ArrayBasedList
		int len = 100;
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>(len);
		
		// Counter for future use
		int count = 0;
		
		// Declaring ASIN and a String array of ratings. 
		String asin;
		
		// Variables for storing total sum of 1 product and its total number of ratings
		double sum = 0;
		int total_ratings = 0;
		double avg_Rating;

		try {
			// Reading file from the command line
			File file = new File(args[0]);
			
			// Creating scanner for reading purposes
			Scanner ratingsFile = new Scanner(file);
			
			// Reading the file line by line
			while (ratingsFile.hasNextLine()) {
				
				// Checking to see if ArrayBasedList is big enough for the contents of the file
				// If it is not, then calling dynamicSizing() from ArrayBasedList class (creates a larger array)
				if ((len - 1) - plist.size() == 0) {
					plist.dynamicSizing();
					// Updating the len variable
					len = len * 2;
				}
				
				// Creating a String array called product that stores ASIN and ratings
				String[] product = ratingsFile.nextLine().split(",");
				
				// Asin = the first thing that splits in the line by a comma 
				asin = product[0];
				
				// Going over the rest of the splits and updating the total sum and ratings of each line
				for (int i = 1; i < product.length; i ++) {
					sum += Integer.parseInt(product[i].replaceAll("\\D", ""));
					total_ratings ++;
				}
				
				// Finding the average rating
				avg_Rating = (double) (Math.round((sum / total_ratings) * 100) / 100.0);
				
				// Adding the product (ASIN, average rating, and index number) to plist
				plist.add(count, new Product(asin, avg_Rating, count));
				
				// Setting the variables to 0 for the next index
				sum = 0;
				total_ratings = 0;
				
				// Incrementing count (index)
				count++;
			}
			
			// This will test the certain edge case and accuracy of your sorts.
			// This is NOT comprehensive and your own tests need to done in 
			// addition to this test.
			//
			// TODO: To look the timing of your sorts, uncomment printTiming in the 
			// Test.java file. Large samples will take a long time to complete though.
			Test.test(plist, args);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException | IOException e) {
			System.out.println("Test could not run/finish.");
		}
		
				
		//TODO: Sort based on averageRating from highest to lowest.
		
		// Insertion Sort (the sort that copies ASINS to the result file)
		InsertionSort.sort(plist);
		
		// Bucket Sort (commented so only insertion sort can be ran)
		// BucketSort.sort(plist);

		// Quick Sort (commented so only insertion sort can be ran)
		// QuickSort.sort(plist);
		
		// Uncomment to print the list
		// System.out.println(plist.toString());
				
		//TODO: Write to a new .csv file named whatever the file being read is with "_sorted"
		//      added to it. So if the file being read is called ratings_Stuff.csv, your file
		//      will be called ratings_Stuff_sorted.csv. For this part, use any of your sorts.
		//   The file will have one ASIN per line.
		
		// Using BufferedWriter to store ASIN of the products sorted by average rating
		try {
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("ratings_Musical_Instruments_short_condensed_result.csv"));
			for (int i = 0; i < plist.size(); i++) {
			    outputWriter.write(plist.get(i).getAsin() + "  ");
			    /* This line was used when testing sorts 
			    outputWriter.write(plist.get(i).getAvgRat() + "  "); */
			    outputWriter.newLine();
			}
			outputWriter.flush();  
			outputWriter.close();
		
		} catch (IOException e) {
			System.out.println("Enter the sorted file name again.");
		}
		
		try {
			// Requires both result and sorted files to be in current directory
			Test.testFile(args);
		} catch (IOException e1) {
			System.out.println("----------------------------------------");
			System.out.println("!! File Test: Could not read file. Check file naming.");
		} catch (IndexOutOfBoundsException e2) {
			System.out.println("----------------------------------------");
			System.out.println("!! File Test: Missing command line argument");
		}
	}
}