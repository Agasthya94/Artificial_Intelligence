/*
 * This class is responsible for implementation of Random Restart Hill climbing
 * algorithm.
 * @Author: Agasthya Vidyanath Rao Peggerla.
 * */
public class RandomRestart {
	
	private HillClimbing hillClimber;
	private Node start;
	private static int numberOfRestarts;
	private static int totalStepsClimbed;
	
	public RandomRestart(Queen[] startBoard) {
		hillClimber = new HillClimbing(startBoard);
		numberOfRestarts = 0;
		totalStepsClimbed = 0;
	}
	
	public Node randomRestart() {
		Node currentNode = hillClimber.getStartNode();
		setStartNode(currentNode);
		
		int heuristic = currentNode.getHeuristic();
		
		while(heuristic != 0) {
			Node nextNode = hillClimber.hillClimbing();
			totalStepsClimbed += hillClimber.getTotalStepsClimbed();
			heuristic = nextNode.getHeuristic();
			
			if(heuristic != 0) {
				numberOfRestarts++;
				hillClimber = new HillClimbing();
			} else {
				currentNode = nextNode;
			}
		}
		
		return currentNode;
	}
	
	public void setStartNode(Node n) {
		start = n;
	}
	
	public Node getStartNode() {
		return start;
	}
	
	public int getNumberOfRestarts() {
		return numberOfRestarts;
	}
	
	public int getTotalStepsClimbed() {
		return totalStepsClimbed;
	}
}
