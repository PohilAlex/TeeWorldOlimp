package mlsdev.teewold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PathNode {

	Point data;
	HashSet<PathNode> parents;
	HashSet<PathNode> children;
	HashMap<Point, PathNode> allNode;
	
	public PathNode(Point data) {
		this.data = data;
		parents = null;
		children = new HashSet<PathNode>();
		allNode = new HashMap<Point, PathNode>();
		allNode.put(data, this);
	}
	
	private PathNode() {
		parents = null;
		children = new HashSet<PathNode>();
	}
	
	/*
	public PathNode(PathNode parent) {
		this.parent = parent;
		children = new HashSet<PathNode>();
	} */
	
	
	@Override
	public int hashCode() {
		return data.hashCode();
		/*
		final int prime = 131;
		int result = 3;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((parent != null && parent.data != null) ? parent.data.hashCode() : 0);
		for (PathNode child : children) {
			result = prime * result + ((child != null && child.data != null) ? child.data.hashCode() : 0);
		}
		return result;*/
	}
	
	public PathNode add(Point data) {
		PathNode child = new PathNode();
		child.data = data;
		if (allNode.containsKey(data)) {
			child = allNode.get(data);
		} else {
			child.parents = new HashSet<PathNode>();
			allNode.put(data, child);
		}
		child.parents.add(this);
		child.allNode = allNode;
		children.add(child);
		return child;
	}
	
	public Point getData() {
		return data;
	}
	
	public void setData(Point data) {
		this.data = data;
	}
	
	public HashSet<PathNode> getParents() {
		return parents;
	}

	public HashSet<PathNode> getChildren() {
		return children;
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
		return data.equals(other);
		/*
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
			
			//boolean res = true;
			//for (int i = 0; i < children.size(); i++) {
			//	res = res && children.get(i).data.equals(other.children.get(i).data);
			//}
			//if (!res) {
			//	return false;
			//}
		}
		return true;
		*/
	}
	
	public void print() {
		ArrayList<PathNode> currPath = new ArrayList<PathNode>();
		ArrayList<PathNode> nextPath = new ArrayList<PathNode>();
		currPath.add(this);
		while (currPath.size() > 0) {
			for (PathNode node : currPath) {
				//System.out.print(node.data.y + " " + node.data.x + "\t");
				if (node.children != null) {
					nextPath.addAll(node.children);
				}
			}
			currPath = nextPath;
			nextPath = new ArrayList<PathNode>();
			System.out.println();
		}
	}
}
