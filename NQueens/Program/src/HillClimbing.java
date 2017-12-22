import java.util.ArrayList;
import java.util.Random;

/*
 * This class is responsible for the implementation of the hill climbing
 * algorithm.
 * @Author: Agasthya Vidyanath Rao Peggerla.
 * */
public class HillClimbing {
	
	private Queen[] startState;
	private Node start;
	private int totalStepsClimbed;
	
	public HillClimbing()
	{
		start = new Node();
		startState = new Queen[NQueens.numberOfQueens];
		generateStartState();
		totalStepsClimbed = 0;
	}
	
	public HillClimbing(Queen[] s) {
		start = new Node();
		startState = new Queen[NQueens.numberOfQueens];
		for(int i=0; i < s.length; i++) {
			startState[i]  = new Queen(s[i].getRow(), s[i].getColumn());
		}
		
		start.setState(startState);
		start.computeHeuristic();
	}
	
	public void generateStartState() {
		Random randomGen = new Random();
		for(int i=0; i < NQueens.numberOfQueens; i++) {
			startState[i] = new Queen(randomGen.nextInt(NQueens.numberOfQueens), i);
		}
		
		start.setState(startState);
		start.computeHeuristic();
	}
	
	public Node hillClimbing() {
		Node currentNode = start;
		while(true) {
			ArrayList<Node> successors = currentNode.generateNeighbors(currentNode);
			Node nextNode = null;
			for(int i=0; i < successors.size(); i++) {
				if(successors.get(i).compareTo(currentNode) < 0) {
					nextNode = successors.get(i);
				}
			}
			
			if(nextNode == null) {
				return currentNode;
			}
			currentNode = nextNode;
			totalStepsClimbed++;
		}
	}
	
	public Node getStartNode() {
		return start;
	}
	
	public int getTotalStepsClimbed() {
		return totalStepsClimbed;
	}
}
