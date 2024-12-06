package fr.istic.prg3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @version 1.0
 *
 */
public class HeapArray extends ArrayList<Integer> implements Heap {

	
//	protected int[] values;
//	protected int size;
	
	public HeapArray(int[] valuesArray) {
		// TODO
		super();
		for (int i : valuesArray) {
			add(i);
		}

		heapifyDown();

	}
	
	
	public void addValue(int newValue) {
		// TODO
		this.add(newValue);
		siftUp(); // Nouvelle valeure a la fin, donc on la siftUp pour bien la placé
	}
	
	
	public int getMax() {
		// TODO
		return get(0);
	}
	
	
	public int extractMax() {
		// TODO
		int max = getMax();
		swap(0,size()-1);
		this.remove(size()-1);
		siftDown();
		return max;
	}


	/*
	 * Passe a travers le tableau de droite a gauche et verifie que
	 * chaque representation de tas binaire sinon siftDown
	 */
	protected void heapifyDown() {
		// TODO

		for (int i = this.size(); i >=0 ; i--) {
			siftDown(i);
		}

	}


	
	/*
	 * creer un tas avec le tableau et recupere le max un par un
	 *
	 * @param unsortedValues un tableau de int
	 * @return une copie du tableau trié
	 */
	public static int[] heapsort(int[] unsortedValues) {
		// TODO
		HeapArray heap = new HeapArray(unsortedValues);
		int[] sortedVal = new int[unsortedValues.length];
		for (int i = unsortedValues.length - 1; i >= 0; i--) {
			sortedVal[i] = heap.extractMax();
		}
		return sortedVal;
	}
	
	
	/*
	 * Calcul l'index du fils gauche
	 *
	 * @param position, la postion du pere
	 * @return l'index du fils gauche
	 */
	protected int indexLeft(int position) {
		// TODO
		return 2*position+1;
	}


	/*
	 * Calcul l'index du fils droit (gauche+1)
	 *
	 * @param position, la postion du pere
	 * @return l'index du fils droit
	 */
	protected int indexRight(int position) {
		// TODO
		return indexLeft(position)+1;
	}


	/*
	 * Calcul l'index du pere
	 *
	 * @param position, la postion du fils
	 * @return l'index du pere
	 */
	protected int indexUp(int position) {
		// TODO
		return (position-1) / 2;
	}
	
	
	public void siftDown() {
		this.siftDown(0);
	}
	
	
	protected void siftDown(int position) {
		// TODO
		int left = indexLeft(position);
		int right = indexRight(position);
		if ( left < this.size()) {
			if (right >= this.size() || get(left) > get(right)) {
				swapIfLowerAndSiftDown(position, left);
			} else {
				swapIfLowerAndSiftDown(position, right);
			}
		}

			

	}
	
	
	public void siftUp() {
		this.siftUp(this.size() - 1);

	}
	
	
	protected void siftUp(int position) {
		// TODO
		int parent = indexUp(position);


		swapIfGreaterAndSiftUp(position, parent);


	}
	
	/*
	 * echange la valeur a 2 index
	 *
	 * @param index1 a echanger
	 * @param index2 a echanger
	 */
	protected void swap(int index1, int index2) {
		// TODO
		int value1 = get(index1);
		int value2 = get(index2);
		set(index1, value2);
		set(index2, value1);

	}
	
	
	protected void swapIfGreaterAndSiftUp(int index1, int index2) {
		// TODO
		// si l'index2 est bien dans le tableau et sa valeure inferieur a index 1 on swap
		if (index2 >= 0 && get(index1) > get(index2)) {
			swap(index1, index2);
			siftUp(index2); // on sift pour confirmer que le noeud est a la bonne place
		}
	}
	
	
	protected void swapIfLowerAndSiftDown(int index1, int index2) {
		// TODO
		if (index2 < this.size() && get(index1) < get(index2) ) {
			swap(index1, index2);
			siftDown(index2);
		}
	}
	
	
	public String toString() {
		// TODO
		String original = super.toString();
		return original + "\n" + toString("", 0);
	}
	
	
	public String toString(String offset, int index) {
		// TODO
		String offset2 = offset + "  ";
		String desc = get(index) + " (" + numOfDescendants(index,0) + ")\n";


		// appel recursivement les enfants gauches d'abords puis droit
		String lefty = "";
		String righty = "";
		if ( indexLeft(index) < size()) {
			lefty = toString(offset2, indexLeft(index));
		}
		if (indexRight(index) < size()) {
			righty = toString(offset2, indexRight(index));
		}


		return offset + desc + lefty + righty;
	}
	


	public int numOfDescendants(int index, int count) {
		count = 0;
		if (indexLeft(index) < size()) {
			count += 1 + numOfDescendants(indexLeft(index), count);
		}
		if (indexRight(index) < size()) {
			count += 1 + numOfDescendants(indexRight(index), count);
		}
		return count;
	}
	

	public static void main(String[] args) {
		// TODO
		int[] treeValues = {107, 111, 112, 103, 104, 110, 101, 106, 102, 108, 105};

		HeapArray myTree = new HeapArray(treeValues);
		System.out.println(myTree+"\n");
	}
}
