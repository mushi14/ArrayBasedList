import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

public class BucketSort {

	public static void sort(ArrayBasedList<Product> plist) {
        
		//TODO: Implement bucket sort. 
		// Creating 1 big ArrayBasedlist (buckets) that contains 5 ArrayBasedLists (list)
		ArrayBasedList<ArrayBasedList<Product>> buckets = new ArrayBasedList<ArrayBasedList<Product>>(5);
		// Creating 5 ArrayBasedLists, 1 for each bucket
		ArrayBasedList<Product> list0 = new ArrayBasedList<Product>(plist.size()); 
		ArrayBasedList<Product> list1 = new ArrayBasedList<Product>(plist.size());
		ArrayBasedList<Product> list2 = new ArrayBasedList<Product>(plist.size()); 
		ArrayBasedList<Product> list3 = new ArrayBasedList<Product>(plist.size()); 
		ArrayBasedList<Product> list4 = new ArrayBasedList<Product>(plist.size()); 

		// Setting each bucket to its assigned list
		buckets.set(0, list0);
		buckets.set(1, list1);
		buckets.set(2, list2);
		buckets.set(3, list3);
		buckets.set(4, list4);
		
		// Variables for setting the plist elements to lists
		int j = 0, k = 0, l = 0, m = 0, n = 0;
		
		// Calling insertion sort to sort the plist before actually going through it
		InsertionSort.sort(plist);
		// Going through the plist
		for (int i = 0; i < plist.size(); i++) {
			// Setting plist's product that matches the criteria of the first bucket
			if (plist.get(i).getAvgRat() >= 4.00) {
				list0.set(j, plist.get(i));
				// Incrementing j so the next index can be added to
				j++;
			} 
			// Setting plist's product that matches the criteria of the second bucket
			if (plist.get(i).getAvgRat() >= 3.00 && plist.get(i).getAvgRat() < 4.00) {
				list1.set(k, plist.get(i));
				// Incrementing k so the next index can be added to
				k++;
			}
			// Setting plist's product that matches the criteria of the third bucket
			if (plist.get(i).getAvgRat() >= 2.00 && plist.get(i).getAvgRat() < 3.00) {
				list2.set(l, plist.get(i));
				// Incrementing l so the next index can be added to
				l++;
			}
			// Setting plist's product that matches the criteria of the fourth bucket
			if (plist.get(i).getAvgRat() >= 1.00 && plist.get(i).getAvgRat() < 2.00) {
				list3.set(m, plist.get(i));
				// Incrementing m so the next index can be added to
				m++;
			}
			// Setting plist's product that matches the criteria of the fifth bucket
			if (plist.get(i).getAvgRat() >= 0.00 && plist.get(i).getAvgRat() < 1.00) {
				list4.set(n, plist.get(i));
				// Incrementing n so the next index can be added to
				n++;
			}
		}
	}
}
