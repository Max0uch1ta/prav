/**
 * 
 */
package fr.istic.prg3;

import java.util.Objects;
import java.util.Arrays;


/**
 * @version 1.0
 *
 */
public class HeapTree extends BinaryTreeAlmostComplete implements Heap {
	
	public HeapTree(int value) {
		super(value);
	}
	
	
	public HeapTree(int value, HeapTree parent) {
		super(value, parent);
		this.siftUp(); // Sift up pour qu'il reprenne sa place
	}
	
	
	public HeapTree(int[] tab) {
		super(tab); // utilise une boucle avec addValue (donc sera semi-trié)
	}
	
	
	
	public void addValue(int value) {
		super.addValue(value);
		// Nouvelle valeure est le rightMostLowest
		BinaryTreeAlmostComplete newTree = getRightmostLowestNode();
		newTree.siftUp(); // On replace le nouvelle arbre pour garder la propriete partout
		
////////////////// Debugging, prints all of the tree ///////////////////////////////////////////////////////
//		BinaryTreeAlmostComplete current = this;
//		while (current.up != null) {
//			current = current.up;
//		}
//		System.out.println(current);
	}

	/**
	 * Extrait et retourne la plus haute valeur de l'arbre
	 * @ret valeur max de l'arbre
	 */
	public int extractMax() {

		int max = this.rootValue;

		// Si feuille on ne supprime pas
		if (isLeaf()) {
			return max;
		}

		BinaryTreeAlmostComplete last = getRightmostLowestNode();
		swap(this, last);
		BinaryTreeAlmostComplete parent = last.up;
		// On supprime le max (verif si a gauche ou a droite
		if (parent.right == last) {
			parent.right = null;
		} else {
			parent.left = null;
		}
		parent.updateNumberOfDescendants(); // Le nombre de descendant doit être mis  jour pour les calculs

		siftDown(); // il faut maintenant replacer le noeud ajouté au bon endroit

		return max;
	}
	
	
	public int getMax() {
		return rootValue;
	}

	/**
	 * Tri un tableau d'entier.
	 * @param unsortedValues un array de int
	 * @return un array de int trié
	 */
	public static int[] heapsort(int[] unsortedValues) {
		// TODO
		int[] arrayCopy = Arrays.copyOf(unsortedValues, unsortedValues.length);
		HeapTree tree = new HeapTree(unsortedValues);
		for (int i = unsortedValues.length - 1; i >= 0; i--) {
			arrayCopy[i] = tree.extractMax();
		}
		return arrayCopy;
	}






	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("CONSTRUCTION");
		int[] treeValues = {109, 107, 111, 112, 103, 104, 110, 101, 106, 102, 108, 105};
		HeapTree myTree = new HeapTree(treeValues);
		System.out.println(myTree);
		System.out.println("\n");
		int[] newArr = HeapTree.heapsort(treeValues);
		System.out.println(Arrays.toString(newArr));
		
		
		
	}

}
