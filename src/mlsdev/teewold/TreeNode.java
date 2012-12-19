package mlsdev.teewold;

import java.util.ArrayList;

public class TreeNode<T> {
	T data;
	TreeNode<T> parent;
	ArrayList<TreeNode<T>> children;
	
	public TreeNode() {
		this(null);
	}
	public TreeNode(TreeNode<T> parent) {
		this.parent = parent;
		children = new ArrayList<TreeNode<T>>();
	}
	
	public TreeNode<T> add(T data) {
		TreeNode<T> child = new TreeNode<T>();
		child.data = data;
		children.add(child);
		return child;
	}
	
	public T getData() {
		return data;
	}
}
