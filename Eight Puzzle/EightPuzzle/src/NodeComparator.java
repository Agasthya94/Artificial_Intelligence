import java.util.Comparator;

/*
 * This is a comparator class used for PriorityQueue.
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.getTotalCost() < o2.getTotalCost()) {
			return -1;
		}
		else if(o1.getTotalCost() > o2.getTotalCost()) {
			return 1;
		}
		return 0;
	}
}
