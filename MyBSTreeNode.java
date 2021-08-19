public class MyBSTreeNode<K extends Comparable<K>,T> implements MyBSTreeNodeInterface<K,T>  {

	private K key;
	private T payload;
	private MyBSTreeNode<K,T> left;
	private MyBSTreeNode<K,T> right;
	private MyBSTreeNode<K,T> parent;
	
	public MyBSTreeNode() {
		this.key = null;
		this.payload = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public MyBSTreeNode(K key, T payload) {
		this.key = key;
		this.payload = payload;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public void setLeft(MyBSTreeNode<K,T> leftNode) {
		this.left = leftNode;
	}
	
	public void setRight(MyBSTreeNode<K,T> rightNode) {
		this.right = rightNode;
	}
	
	public void setParent(MyBSTreeNode<K,T> parentNode) {
		this.parent = parentNode;
	}
	
	public void setKey(K newKey) {
		this.key = newKey;
	}
	
	public void setPayload(T newPayload) {
		this.payload = newPayload;
	}
	
	public MyBSTreeNode<K,T> getLeft(){
		return this.left;
	}
	
	public MyBSTreeNode<K,T> getRight(){
		return this.right;
	}
	
	public MyBSTreeNode<K,T> getParent(){
		return this.parent;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public T getPayload() {
		return this.payload;
	}
	
	public String toString() {
		//[Key: …. | Payload: …]
		
		String s = "[Key: " + this.getKey() + ". | Payload: " + this.getPayload() + "]";
		return s;
	}
	
	
	
}