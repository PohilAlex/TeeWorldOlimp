package mlsdev.teewold;

import java.util.ArrayList;
import java.util.HashSet;

public class PathNode{

	Point data;
	PathNode parent;
	ArrayList<PathNode> children;
	
	public PathNode() {
		this(null);
	}
	
	public PathNode(PathNode parent) {
		this.parent = parent;
		children = new ArrayList<PathNode>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 131;
		int result = 3;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((parent != null && parent.data != null) ? parent.data.hashCode() : 0);
		for (PathNode child : children) {
			result = prime * result + ((child != null && child.data != null) ? child.data.hashCode() : 0);
		}
		return result;
	}
	
	public PathNode add(Point data) {
		PathNode child = new PathNode(this);
		child.data = data;
		children.add(child);
		return child;
	}
	
	public Point getData() {
		return data;
	}
	
	public void setData(Point data) {
		this.data = data;
	}

	public HashSet<PathNode> getLastLeyer() {
		HashSet<PathNode> lastLeyer = new HashSet<PathNode>();
		HashSet<PathNode> currLeyer = new HashSet<PathNode>();
		lastLeyer.add(this);

		do {
			currLeyer = lastLeyer;
			lastLeyer = new HashSet<PathNode>();

			for (PathNode node : currLeyer) {
				if (node.children != null) {
					lastLeyer.addAll(node.children);
				}
			}

		} while (lastLeyer.size() > 0);
		return currLeyer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		PathNode other = (PathNode) obj;

		if (data == null) {
			if (other.data != null) return false;
		} else if (!data.equals(other.data)) return false;

		if (parent == null) {
			if (other.parent != null) return false;
		} else if (parent.data == null) {
			if (other.parent.data != null) return false;
		} else if (!parent.data.equals(other.parent.data)) return false;
		
		if (children == null) {
			if (other.children != null) return false;
		} else {
			if (children.size() != other.children.size()) return false;
			boolean res = true;
			for (int i = 0; i < children.size(); i++) {
				res = res && children.get(i).data.equals(other.children.get(i).data);
			}
			if (!res) {
				return false;
			}
		}
		return true;
	}
	
}
