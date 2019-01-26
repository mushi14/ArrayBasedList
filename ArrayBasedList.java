import java.util.AbstractList;
import java.util.Arrays;

/**
 * TODO: Implement your own array based list. For full credit,
 *       do not import ArrayList.
 *
 */
public class ArrayBasedList<E> extends AbstractList<E> {
	
	//TODO: Create instance members
	protected E array[];

	//TODO: Create constructor(s)
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int n) {
		// TODO: Complete
		array = (E[])new Object[n];
	}

	// Method that creates a new array and doubles the size of existing array. Then copies the
	// array elements to the new array. 
	public void dynamicSizing() {
		E array2[] = (E[])new Object[array.length * 2];
		System.arraycopy(array, 0, array2, 0, array.length);
		array = array2;
	}
	
	//TODO: Create and complete instance methods
	@Override
	public E get(int index) {
		// TODO: Complete
		return array[index];
	}

	@Override
	public int size() {
		// TODO: Complete
		int size = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				size ++; 
			}
		}
		return size;
	}
	
	@Override
	public boolean add(E e) {
		// TODO: Complete
		boolean space = false;
		int size = this.size();
		if (array.length - size == 0) {
			array[size + 1] = e;
			space = true;
		} else {
			space = false;
		}
		return space;
	}
	
	@Override
	public void add(int index, E element) {
		// TODO: Complete
		int size = this.size() + 1;
		for (int i = size; i > index; i --) {
			if ((array.length - 1) - size == 0) {
				this.dynamicSizing();
			}
			array[i] = array[i - 1];
		}
		array[index] = element;
	}

	
	@Override
	public E set(int index, E element) {
		// TODO: Complete
		array[index] = element;
		return element;
	}
	
	@Override
	public E remove(int index) {
		//TODO: Complete
		for (int i = 0; i < array.length; i++) {
			if (i == index) {
				array[i] = null;
			}
		}
		return null;
	}
}
