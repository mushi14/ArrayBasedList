import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * This is used to test Project 2. This is only preliminary
 * and does not cover all cases.
 *
 */
public class Test {
	
	/**
	 * Tests all sorts. This is NOT a comprehensive test and further tests should be
	 * done to ensure validity. 
	 * 
	 * @param args command line arguments
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void test(ArrayBasedList<Product> plist, String[] args) throws IOException, NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (testExceptions()) {
			//TODO: If using a relatively small sample try uncommenting printTiming
			//      to look at the difference in time between sorts. However, due to
			//      the nature of this method, big samples will take a very long
			//      time.
			printTiming(plist);
			testAccuracy();
		}
	}
	
	/**
	 * Prints the timing of all sorts in nanoseconds. Does not count the overhead
	 * of rewriting the unsorted list each time.
	 * 
	 * @param orig
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private static void printTiming(ArrayBasedList<Product> orig) throws 
	NoSuchMethodException, SecurityException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException {
		System.out.println("----------------------------------------");
		System.out.println("Timing (Does NOT check for accuracy): ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
			for (Product p: orig) {
				plist.add(p);
			}
			long start = System.nanoTime();
			method.invoke(sortName,plist);
			System.out.println(sortName.getClass().getName()+": "+(System.nanoTime()-start)+" nano seconds");
		}
	}

	/**
	 * Set up for small accuracy test
	 * 
	 * @return filled list
	 */
	private static ArrayBasedList<Product> setUp1() {
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
		plist.add(0, new Product("123", 5, 0));
		plist.add(1, new Product("345", 2.67, 1));
		plist.add(2,new Product("4", 4, 2));
		plist.add(3, new Product("2AC", 5, 3));
		plist.add(4, new Product("BC", 4.9, 4));
		plist.add(5, new Product("CD", 5, 5));
		return plist;
	}
	
	/**
	 * Set up medium accuracy test
	 * 
	 * @return filled list
	 */
	private static ArrayBasedList<Product> setUp2() {
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
		plist.add(0, new Product("F", 1.2, 0));
		plist.add(1, new Product("AL", 2, 1));
		plist.add(2, new Product("PI", 3.2, 2));
		plist.add(3, new Product("S", 5, 3));
		plist.add(4, new Product("A", 4, 4));
		plist.add(5, new Product("IS", 4.1, 5));
		plist.add(6, new Product("ID", 2, 6));
		plist.add(7, new Product("U", 3, 7));
		plist.add(8, new Product("IL", 5, 8));
		plist.add(9, new Product("T",5, 9));
		plist.add(10, new Product("OUS",1, 10));
		plist.add(11, new Product("I",3.9, 11));
		plist.add(12, new Product("P",4.9, 12));
		plist.add(13, new Product("CI",1.5, 13));
		plist.add(14, new Product("EX",2.7, 14));
		plist.add(15, new Product("C",5, 15));
		plist.add(16, new Product("O",2, 16));
		plist.add(17, new Product("E",4.5, 17));
		plist.add(18, new Product("G",3, 18));
		plist.add(19, new Product("R",5, 19));
		plist.add(20, new Product("IC",3, 20));
		plist.add(21, new Product("L",4, 21));
		plist.add(22, new Product("RA",3.9, 22));
		return plist;
	}
	
	/**
	 * Test small and medium size for accuracy
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracy() throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringWriter sw = new StringWriter();
		sw.write("----------------------------------------\n");
		sw.write("Accuracy Test: \n");
		testAccuracyTwo(sw);
		testAccuracySmall(sw);
		testAccuracyMedium(sw);
		System.out.println(sw.toString());
	}
	
	/**
	 * Tests sample size of 2
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracyTwo(StringWriter sw) throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sw.write("-----------\n");
		sw.write("Two Test\n");
		sw.write("-----------\n");
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
		plist.add(0, new Product("CD345", 5, 0));
		plist.add(1, new Product("AB123", 1, 1));
		if (testAccuracyFor(plist,new String[] {"AB123","CD345"},sw)) {
			sw.write("Passes two sorting!\n");
		}
	}
	
	/**
	 * Tests small sample size
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracySmall(StringWriter sw) throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sw.write("-----------\n");
		sw.write("Small Test\n");
		sw.write("-----------\n");
		if (testAccuracyFor(setUp1(),new String[] {"CD","BC","4","345","123","2AC"},sw)) {
			sw.write("Passes small sorting!\n");
		}
	}
	
	/**
	 * Tests medium sample size
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracyMedium(StringWriter sw) throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sw.write("-----------\n");
		sw.write("Medium Test\n");
		sw.write("-----------\n");
		if (testAccuracyFor(setUp2(),new String[] {"S","U","P","E","R","C","A","L","I","F","RA","G",
				"IL","IS","T","IC","EX","PI","AL","ID","O","CI","OUS"},sw)) {
			sw.write("Passed medium sorting!\n");
		} 
	}
	
	/**
	 * Helper for accuracy tests. Does not count overhead to rewrite the unsorted list
	 * for each sort.
	 * 
	 * @param orig
	 * @param answer
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static boolean testAccuracyFor(ArrayBasedList<Product> orig, String[] answer, StringWriter sw) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean success = true;
		Object[] sortNames = new Object[] { new InsertionSort(),
				new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
			for (Product p: orig) {
				plist.add(p);
			}
			long start = System.nanoTime();
			method.invoke(sortName,plist);
			sw.write(sortName.getClass().getName()+": "+(System.nanoTime()-start)+" nano seconds\n");
			for (int i=0; i<answer.length; i++) {
				try {
					if (!plist.get(i).toString().equals(answer[i])) {
						sw.write("!!! "+sortName.getClass().getName()+".sort does not work\n");
						success = false;
						break;
					}
				} catch (NullPointerException | IndexOutOfBoundsException e) {
					sw.write("!!! "+sortName.getClass().getName()+" test throws an error\n");
					success = false;
					break;
				}
			}
		}
		return success;
	}
	
	/**
	 * Check if sorted and results files are the same. This will 
	 * only work for stable sorts.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void testFile(String[] args) throws IOException {
		String f = args[0];
		System.out.println("----------------------------------------");
		System.out.println("File Test:");
		int index = f.lastIndexOf('.');
		Path result = Paths.get(f.substring(0, index)+"_result"+f.substring(index));
		Path answer = Paths.get(f.substring(0, index)+"_sorted"+f.substring(index));
		
		if (Arrays.equals(Files.readAllBytes(answer), Files.readAllBytes(result))) {
			System.out.println("Result and sorted files match!");
		} else {
			System.out.println("Result and sorted files do NOT match");
			System.out.println("Ignore if using unstable sort.");
		}
	}
	
	/**
	 * Check for Exceptions in specific edge cases
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testExceptions() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		System.out.println("----------------------------------------");
		System.out.println("Exception Test:");
		success &= testEmpty();
		success &= testEmptyRating();
		success &= testOne();
		return success;
	}
	
	/**
	 * Check when there is only one Product in the list
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testOne() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		StringWriter fail = new StringWriter();
		System.out.println("-------------");
		System.out.println("Single Test: ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
			plist.add(1, new Product("abc", 4.5, 0));
			try {
				method.invoke(sortName,plist);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				fail.write(sortName.getClass().getName()+", ");
				success = false;
			}
		}
		if (success) {
			System.out.println("Passes one element test!");
		} else {
			System.out.println("Fail: "+fail.toString());
		}
		return success;
	}
	
	/**
	 * Check when the list is empty
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testEmpty() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		StringWriter fail = new StringWriter();
		System.out.println("-------------");
		System.out.println("Empty Product list Test: ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
			try {
				method.invoke(sortName,plist);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				fail.write(sortName.getClass().getName()+", ");
				success = false;
			}
		}
		if (success) {
			System.out.println("Passes empty Product list test!");
		} else {
			System.out.println("Fail: "+fail.toString());
		}
		return success;
	}
	
	/**
	 * Check if there are Products in list but no ratings
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testEmptyRating() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		StringWriter fail = new StringWriter();
		System.out.println("-------------");
		System.out.println("Empty ratings Test: ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>(5000);
			plist.add(0, new Product("123", 3.78, 0));
			plist.add(1, new Product("234", 3.9, 1));
			plist.add(2, new Product("345", 5, 2));
			try {
				method.invoke(sortName,plist);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				fail.write(sortName.getClass().getName()+", ");
				success = false;
			}
		}
		if (success) {
			System.out.println("Passes empty ratings test!");
		} else {
			System.out.println("Fail: "+fail.toString());
		}
		return success;
	}
	
}