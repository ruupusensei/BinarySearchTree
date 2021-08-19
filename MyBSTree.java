import java.util.*;

public class MyBSTree<K extends Comparable<K>,T>{

	protected MyBSTreeNode<K,T> root;

	public MyBSTree(){
		this.root = null;
	}

	public void insert(K newKey, T newPayload){

		MyBSTreeNode<K,T> newNode = new MyBSTreeNode<K,T>(newKey, newPayload);

		if (this.root == null) {
			this.root = newNode;
			return;
		}

		else {
			insertRecursive(this.root, newNode);
		}
	}

	private void insertRecursive(MyBSTreeNode<K,T> current, MyBSTreeNode<K,T> newNode) {

		if (newNode.getKey().compareTo(current.getKey()) < 0 ) {//if new node key is less than current node key, go left
			if (current.getLeft() == null) {
				current.setLeft(newNode);
				newNode.setParent(current);
				return;
			}
			else {
				insertRecursive(current.getLeft(), newNode);
			}
		}

		else if (newNode.getKey().compareTo(current.getKey()) > 0) {//if new node key is greater than current node key, go right
			if (current.getRight() == null) {
				current.setRight(newNode);
				newNode.setParent(current);
				return;
			}
			else {
				insertRecursive(current.getRight(), newNode);
			}
		}

		else { //this is the case where the key already exists in the tree. duplicates are not allowed.
			return;
		}

	}

	public void inOrder() { //left, parent, right
		inOrderRecursive(this.root);
		System.out.println();
	}

	private void inOrderRecursive(MyBSTreeNode<K,T> node) {
		if(node != null) {
			inOrderRecursive(node.getLeft());
			System.out.print(node.getKey() + " ");
			inOrderRecursive(node.getRight());
		}
		else return;
	}

	public void preOrder() { // parent, left, right
		preOrderRecursive(this.root);
		System.out.println();
	}

	private void preOrderRecursive(MyBSTreeNode<K,T> node) {
		if(node != null) {
			System.out.print(node.getKey() + " ");
			preOrderRecursive(node.getLeft());
			preOrderRecursive(node.getRight());
		}
		else return;
	}

	public void postOrder() { // left, right parent
		postOrderRecursive(this.root);
		System.out.println();
	}

	private void postOrderRecursive(MyBSTreeNode<K,T> node) {
		if(node != null) {
			postOrderRecursive(node.getLeft());
			postOrderRecursive(node.getRight());
			System.out.print(node.getKey() + " ");
		}
		else return;
	}

	public void levelOrder() {
		levelOrderRecursive(this.root);
		System.out.println();
	}

	private void levelOrderRecursive(MyBSTreeNode<K,T> node) {
		if (node != null) {

			Queue<MyBSTreeNode<K,T>> q = new LinkedList<MyBSTreeNode<K,T>>();

			q.add(node);

			while(q.isEmpty() == false) {
				MyBSTreeNode<K,T> current = q.remove();
				if (current != null) {
					System.out.print(current.getKey() + " ");
					q.add(current.getLeft());
					q.add(current.getRight());
				}
			}
		}
	}

	public MyBSTreeNode<K,T> getMinKeyNode(){

		if (this.root == null) {
			return null;
		}

		else {
			return this.getMinKeyNode(this.root);
		}
	}

	public MyBSTreeNode<K,T> getMaxKeyNode(){

		if (this.root == null) {
			return null;
		}

		else {
			return this.getMaxKeyNode(this.root);
		}
	}

	private MyBSTreeNode<K,T> getMinKeyNode(MyBSTreeNode<K,T> treeRoot){

		if (treeRoot.getLeft() == null && treeRoot.getRight() == null) {
			return null;
		}

		else {
			MyBSTreeNode<K,T> current = treeRoot;
			while (current.getLeft() != null) {
				current = current.getLeft();
			}
			return current;
		}
	}

	private MyBSTreeNode<K,T> getMaxKeyNode(MyBSTreeNode<K,T> treeRoot){

		if (treeRoot.getLeft() == null && treeRoot.getRight() == null) {
			return null;
		}

		else {
			MyBSTreeNode<K,T> current = treeRoot;
			while (current.getRight() != null) {
				current = current.getRight();
			}
			return current;
		}
	}

	public MyBSTreeNode<K,T> findNodeWithKey(K keyToFind){

		if (this.root == null) {
			return null;
		}

		else {
			return this.findNodeWithKeyRecursive(root, keyToFind);
		}
	}

	private MyBSTreeNode<K,T> findNodeWithKeyRecursive(MyBSTreeNode<K,T> node, K keyToFind){

		if (keyToFind.compareTo(node.getKey()) == 0) {
			return node;
		}

		else if (keyToFind.compareTo(node.getKey()) < 0) { //go left
			if (node.getLeft() != null) {
				return this.findNodeWithKeyRecursive(node.getLeft(), keyToFind);
			}
			else {
				return null;
			}
		}

		else if (keyToFind.compareTo(node.getKey()) > 0) { //go right
			if (node.getRight() != null) {
				return this.findNodeWithKeyRecursive(node.getRight(), keyToFind);
			}
			else {
				return null;
			}
		}
		return null;
	}

	private MyBSTreeNode<K,T> inOrderSuccesor(MyBSTreeNode<K,T> node){


		if (node.getRight().getLeft() == null) {
			return node.getRight();
		}

		else {
			 return this.getMinKeyNode(node.getRight());
		}

	}

	public boolean delete(K keyToDelete) {

		if (this.findNodeWithKey(keyToDelete) == null) { // key is not in the tree
			return false;
		}

		MyBSTreeNode<K,T> node = this.findNodeWithKey(keyToDelete);
		MyBSTreeNode<K,T> tempParent = node.getParent();

		if (node.getLeft() == null && node.getRight() == null) { //node has no children

			if(node == this.root) {
				this.root = null;
				return true;
			}

			else if (tempParent.getLeft() != null) {
				if(tempParent.getLeft().getKey().compareTo(node.getKey()) == 0) {
					tempParent.setLeft(null);
					node.setParent(null);
					return true;
				}
				else {
					tempParent.setRight(null);
					node.setParent(null);
					return true;
				}
			}

			else {
				if (tempParent.getRight().getKey().compareTo(node.getKey()) == 0) {
					tempParent.setRight(null);
					node.setParent(null);
					return true;
					}
				else {
					tempParent.setLeft(null);
					node.setParent(null);
					return true;
				}
			}
		}

		else if (node.getLeft() == null && node.getRight() != null)  { //node has right child only

			if (node == this.root) {// is right child of root
				this.root = node.getRight();
				node.setRight(null);
				this.root.setParent(null);
				return true;
			}

			else if (tempParent.getKey().compareTo(node.getRight().getKey()) < 0 ) { //new child key is greater than new parent key, goes to right
				tempParent.setRight(node.getRight());
				tempParent.getRight().setParent(tempParent);
				node.setParent(null);
				node.setRight(null);
				return true;
			}
			else { //new child key is less than new parent key, goes to left
				tempParent.setLeft(node.getRight());
				tempParent.getLeft().setParent(tempParent);
				node.setParent(null);
				node.setRight(null);
				return true;
			}
		}

		else if (node.getLeft() != null && node.getRight() == null) { //node has left child only

			if (node == this.root) {// is left child of root
				this.root = node.getLeft();
				node.setLeft(null);
				this.root.setParent(null);
				return true;
			}

			else if (tempParent.getKey().compareTo(node.getLeft().getKey()) < 0 ) { //new child key is greater than new parent key, goes to right
				tempParent.setRight(node.getLeft());
				tempParent.getRight().setParent(tempParent);
				node.setParent(null);
				node.setRight(null);
				return true;
			}
			else { //new child key is less than new parent key, goes to left
				tempParent.setLeft(node.getLeft());
				tempParent.getLeft().setParent(tempParent);
				node.setParent(null);
				node.setLeft(null);
				return true;
			}
		}

		else { //node has 2 children

			MyBSTreeNode<K,T> successor = this.inOrderSuccesor(node);

			if(successor.getKey().compareTo(node.getRight().getKey()) == 0) {//successor is right child of node to be deleted

				if(node == this.root) { //node to be deleted is tree root
					successor.setLeft(node.getLeft());
					successor.getLeft().setParent(successor);
					this.root = successor;
					successor.setParent(null);
					node.setLeft(null);
					node.setRight(null);
					return true;
				}

				else {
				successor.setLeft(node.getLeft());
				tempParent.setRight(successor);
				successor.setParent(tempParent);
				node.setParent(null);
				node.setLeft(null);
				node.setRight(null);
				return true;
				}
			}

			else { // successor is left descendant of right child
				if(node == this.root) { //node to be deleted is tree root

					successor.getParent().setLeft(successor.getRight());
					successor.setLeft(node.getLeft());
					successor.getLeft().setParent(successor);
					successor.setRight(node.getRight());
					successor.getRight().setParent(successor);
					this.root = successor;
					successor.setParent(null);
					node.setLeft(null);
					node.setRight(null);
					return true;

				}
				else {
					successor.getParent().setLeft(successor.getRight());
					successor.setRight(node.getRight());
					tempParent.setRight(successor);
					successor.setLeft(node.getLeft());
					successor.setParent(tempParent);
					node.setParent(null);
					node.setLeft(null);
					node.setRight(null);
					return true;
				}
		}
	}

	}
}
