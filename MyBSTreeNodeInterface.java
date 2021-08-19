public interface MyBSTreeNodeInterface<K extends Comparable<K>,T> {
	public abstract void setLeft(MyBSTreeNode<K,T> leftNode);
	public abstract void setRight(MyBSTreeNode<K,T> rightNode);
	public abstract void setParent(MyBSTreeNode<K,T> parentNode);
	public abstract void setKey(K newKey);
	public abstract void setPayload(T newPayload);
	public abstract MyBSTreeNode<K,T> getLeft();
	public abstract MyBSTreeNode<K,T> getRight();
	public abstract MyBSTreeNode<K,T> getParent();	
	public abstract K getKey();
	public abstract T getPayload();	
}