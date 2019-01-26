public class InsertionSort {

	public static void sort(ArrayBasedList<Product> plist) {

		//TODO: Implement stable in-place insertion sort
			
		// Insertion sort for sorting by average ratings
		for (int i = 1; i < plist.size(); i++) {
			Product insertElem = plist.get(i);
			int position = i;
			// Comparing by average rating
			while (position > 0 && insertElem.getAvgRat() > plist.get(position-1).getAvgRat()) {
				plist.set(position, plist.get(position-1));
				position--;
			}
			plist.set(position, insertElem);
		}
    }	
}
