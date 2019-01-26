import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSort {

	public static void sort(ArrayBasedList<Product> plist) {
		
		//TODO: Implement in-place quick sort
		// Setting low to 0 and high to plist's size
		int low = 0;
		int high = plist.size();
		
		// Calling the quicksort static method
		QuickSort(plist, low, high - 1);	
		
		/* Following was used to test whether all the Products were getting sorted
		try {
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("data"));
			for (int i = 0; i < plist.size(); i++) {
				outputWriter.write(plist.get(i) + " ");
			}				
			outputWriter.flush();  
			outputWriter.close();
		} catch (IOException e) {
			System.out.println("Enter the sorted file name again.");
		} */
	}
	
	// Static recursive method for QuickSort
	public static void QuickSort(ArrayBasedList<Product> a,int low, int high) {		
		// Base Case
		if (low < high) {
			// Calling Partition method
			int mid = Partition(a, low, high);
			QuickSort(a, low, mid - 1);
			QuickSort(a, mid + 1, high);
		}
	}

	// Static Partition method for updating the pivot
	public static int Partition(ArrayBasedList<Product> a,int low, int high) {
		// Setting pivot to the rating of the last element in plist
		double pivot = a.get(high).getAvgRat();
		int i = low - 1;
		
		// Going through plist
		for (int j = low; j < high; j++) {
			// Comparing to the pivot and swapping accordingly
			if (a.get(j).getAvgRat() >= pivot) {
				i++;
                Product temp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, temp);
			}
		}
		// Swapping pivot
		Product temp = a.get(i+1);
		a.set(i+1, a.get(high));
		a.set(high, temp);
		
		// Returning new pivot
		return i + 1;
	}
}