package mlsdev.teewold;

public class PathNode extends TreeNode<Point>{

	
	@Override
	//TODO fix copy paste
	public PathNode add(Point data) {
		PathNode child = new PathNode();
		child.data = data;
		children.add(child);
		return child;
	}
	
}
