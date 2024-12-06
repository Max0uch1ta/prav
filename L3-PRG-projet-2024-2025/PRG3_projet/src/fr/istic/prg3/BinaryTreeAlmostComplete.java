/**
 * 
 */
package fr.istic.prg3;

import java.util.Objects;



/**
 * @version 1.0
 *
 */
public class BinaryTreeAlmostComplete {
	
	protected int rootValue;
	protected BinaryTreeAlmostComplete left;
	protected BinaryTreeAlmostComplete right;
	protected BinaryTreeAlmostComplete up;
	protected int nbDescendants;
	
	
	public BinaryTreeAlmostComplete(int value) {
		this(value, null);
	}
	
	
	public BinaryTreeAlmostComplete(int[] values) {
		this(values[0], null); // utilise le premier int pour creer l'arbre
		boolean first = true;
		for (int number: values) {
			if (first) { // saute le premier element pour le addValue
				first = false;
			} else {
				addValue(number);				
			}
		}
	}
	
	
	public BinaryTreeAlmostComplete(int value, BinaryTreeAlmostComplete parent) {
		this.rootValue = value;
		this.left = null;
		this.right = null;
		this.up = parent;
		this.updateNumberOfDescendants();
	}
	
	
	
	
	
	
	public void addValue(int value) {
		if (Objects.isNull(this.left)) {
			this.left = new BinaryTreeAlmostComplete(value, this);
			this.updateNumberOfDescendants();
		}
		else {
			if (Objects.isNull(this.right)) {
				this.right = new BinaryTreeAlmostComplete(value, this);
				this.updateNumberOfDescendants();
			}
			else {
				// both left and right exist
				int nbDescLeft = this.left.nbDescendants;
				if (getLevels(nbDescLeft) == getLevels(nbDescLeft + 1)) {
					// the lowest level of left child is not full
					this.left.addValue(value);
				}
				else {
					// the lowest level of left child is full
					int nbDescRight = this.right.nbDescendants;
					if (nbDescLeft > nbDescRight) {
						// the lowest level of left child is full, AND the lowest level of right child is not full
						this.right.addValue(value);
					}
					else {
						// both left and right child are full and have the same level
						this.left.addValue(value);
					}
				}
			}
		}
	}
	
	
	
	protected static int getLevels(int n) {
		return (int)(Math.log(n + 1) / Math.log(2));
	}
	
	
	protected BinaryTreeAlmostComplete getRightmostLowestNode() {
		if (isLeaf()) {
			return this;
		}
		if (!left.isComplete()) {
			return left.getRightmostLowestNode();
		}
		if (right == null || getLevels(right.nbDescendants) < getLevels(left.nbDescendants)) {
			return left.getRightmostLowestNode();
		}
		return right.getRightmostLowestNode();
	}
	
	protected boolean isLeaf() {
		if (left == null) { // si left est null right est null
			return true;
		}
		return false;
	}

	/*
	 * Compare le niveau du descendants le plus bas et compare au niveau hypothetique du prochain
	 *
	 * @return true si complet faux sinon
	 *
	 */
	protected boolean isComplete() {
//		int levels = getLevels(nbDescendants);
//		int maxDes = 1 << levels; // 2 à la puissance nbr de niveau
//		
//		return maxDes == nbDescendants;
		return getLevels(nbDescendants) != getLevels(nbDescendants + 1);
	}
	
	
	/*
	 * Elle permute successivement ce nœud avec un de ses fils tant que la valeur du nœud est 
	 * inférieure à celle d’un de ses fils. Si sa valeur est inférieure à celles de ses deux fils,
	 * on permute avec le fils ayant la plus grande valeur.
	 */
	public void siftDown() {


		BinaryTreeAlmostComplete lefty = this.left;
		BinaryTreeAlmostComplete righty = this.right;
		//int buffer = this.rootValue;
		if (lefty != null) {
			if (righty != null && this.rootValue < lefty.rootValue && this.rootValue < righty.rootValue) {
				if (lefty.rootValue < righty.rootValue) {
					swap(this, righty);
					// Appel recursif a droite
					righty.siftDown();
				// Si le fils gauche est plus grand on swap et on appel recursif sur gauche
				} else {
					swap(this, lefty);
					lefty.siftDown();
				}

			} else if (this.rootValue < lefty.rootValue) {
				swap(this, lefty);
				lefty.siftDown();


			} else if (righty != null && this.rootValue < righty.rootValue) {
				swap(this, righty);
				righty.siftDown();
			}
		}
	}
	
	/*
	 * Swap la rootValue de deux root
	 * 
	 * @param tree1 a swapper
	 * @param tree2 a swapper
	 * 
	 * @pre les tree ne sont pas null
	 */
	protected void swap(BinaryTreeAlmostComplete tree1, BinaryTreeAlmostComplete tree2) {
		int buffer = tree1.rootValue;
		tree1.rootValue = tree2.rootValue;
		tree2.rootValue = buffer;
	}
	
	/*
	 * Swap this avec son père tant que son pere (recurs) a un value < this.value
	 */
	public void siftUp() {
		
		BinaryTreeAlmostComplete current = this;
		BinaryTreeAlmostComplete parent = this.up;
		
		if (parent != null && current.rootValue > parent.rootValue) {
			swap(parent, current);
			parent.siftUp(); // appel recusif sur le parent
		}
	}
	
	
	
	public String toString() {
		return this.toString("");
	}
	
	
	public String toString(String offset) {
		
		String offset2 = offset + "  "; // Rajoute un offset pour les enfants
		String desc = rootValue + " (" + this.nbDescendants + ")\n"; 
		
		// appel recursivement les enfants gauches d'abords puis droit
		String lefty = "";
		String righty = "";
		
		if (left != null) {
			lefty = this.left.toString(offset2);
		}
		
		if (right != null) {
			righty = this.right.toString(offset2);
		}
		return offset + desc + lefty + righty; 
	}
	
	
	
	protected void updateNumberOfDescendants() {
		this.nbDescendants = 0;
		if (Objects.nonNull(this.left)) {
			this.nbDescendants += 1 + this.left.nbDescendants;
		}
		if (Objects.nonNull(this.right)) {
			this.nbDescendants += 1 + this.right.nbDescendants;
		}
		if (Objects.nonNull(this.up)) {
			up.updateNumberOfDescendants();
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] treeValues = {107, 111, 112, 103, 104, 110, 101, 106, 102, 108, 105};
		
		BinaryTreeAlmostComplete myTree = new BinaryTreeAlmostComplete(109);
		System.out.println(myTree+"\n");
		
		for (int number: treeValues) {
			myTree.addValue(number);
			System.out.println(myTree+"\n");
		}
		
		BinaryTreeAlmostComplete tree2 = new BinaryTreeAlmostComplete(treeValues);
		System.out.println(tree2);
	}

}
