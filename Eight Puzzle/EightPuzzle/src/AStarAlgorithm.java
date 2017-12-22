import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*This class is responsible for implementing the A* algorithm for 8 puzzle.
 * This class extends the super class Algorithm and implements the processPuzzle method.
 * 
 *  @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class AStarAlgorithm extends Algorithm {
	//priority queue is used for keeping the nodes in order.
	static Comparator<Node> nodeComparator = new NodeComparator();
	static PriorityQueue<Node> priorityQueue = new PriorityQueue<>(1, nodeComparator);
	
	//this list is used to store expanded nodes.
	static List<Node> nodes_explored = new ArrayList<Node>();

	@Override
	public Node processPuzzle(Puzzle puzzle) {
		
		int[][] initial = puzzle.getInitialState();
		int[][] goal = puzzle.getGoalState();
		
		//set initial node.
		Node init = new Node();
		init.setCurrentState(initial);
		init.setParent(null);
		init.calculateTotalCost(initial, goal);
		
		priorityQueue.add(init);
		while(true)
		{
			if(priorityQueue.isEmpty())
			{
				System.out.println("ERROR: Failure to process the puzzle.");
				return null;
			}
			else
			{
				Node currentNode = priorityQueue.poll();
				nodes_explored.add(currentNode);
				if(Arrays.deepEquals(currentNode.getCurrentState(), goal))
				{
					System.out.println("INFO: Successfully found the goal state.");
					puzzle.setNodes_expanded(nodes_explored.size());
					puzzle.setNodes_generated(nodes_explored.size() + priorityQueue.size());
					puzzle.setPath_cost(currentNode.getgCost());
					return currentNode;
				}
				else {
					generateChildStates(currentNode, goal);
				}
			}
			
		}	
	}

	//This method is used for generating the child states.
	private void generateChildStates(Node currentNode, int[][] goal) {
		
		//get possible directions.
		int i=0, j = 0, xPos=0, yPos = 0;
		int b[][] = currentNode.getCurrentState();
		
		//get zero position.
		for(i=0; i< 3; i++) {
			for(j = 0; j < 3; j++) {
				if(b[i][j] == 0) {
					xPos = i;
					yPos = j;
					break;
				}
			}
		}
		
		//get possible directions.
		List<Operator> directions = getPossibleDirections(xPos, yPos);
		
		//explore all the directions.
		for(Operator op : directions)
		{
			//If the tile can move in UP direction.
			if(op == Operator.UP)
			{
				int temp = 0;
				Node node_up = new Node();
				int ch1[][] = new int[3][3];
				for(i=0; i<3; i++) {
					for(j=0; j<3; j++) {
						ch1[i][j] = b[i][j];
					}
				}
				
				//swap the tiles.
				temp = ch1[xPos][yPos];
				ch1[xPos][yPos] = ch1[xPos - 1][yPos];
				ch1[xPos -1][yPos] = temp;
				
				//check if it exits.
				if(!isAlreadyExplored(ch1)) {
					node_up.setCurrentState(ch1);
					node_up.setParent(currentNode);
					node_up.calculateTotalCost(ch1, goal);
					
					for(Node n: priorityQueue) {
						if(Arrays.deepEquals(ch1, n.getCurrentState())) {
							if(node_up.getgCost() < n.getgCost()) {
								priorityQueue.remove(n);
								break;
							}
						}
					}
					
					priorityQueue.add(node_up);
					
				}
			}
			
			//if the tile can move in Down direction.
			if(op == Operator.DOWN)
			{
				int temp = 0;
				Node node_down = new Node();
				int ch2[][] = new int[3][3];
				for(i=0; i<3; i++) {
					for(j=0; j<3; j++) {
						ch2[i][j] = b[i][j];
					}
				}
				
				//swap the tiles.
				temp = ch2[xPos][yPos];
				ch2[xPos][yPos] = ch2[xPos + 1][yPos];
				ch2[xPos + 1][yPos] = temp;
				
				//check if it exits.
				if(!isAlreadyExplored(ch2)) {
					node_down.setCurrentState(ch2);
					node_down.setParent(currentNode);
					node_down.calculateTotalCost(ch2, goal);
					
					for(Node n: priorityQueue) {
						if(Arrays.deepEquals(ch2, n.getCurrentState())) {
							if(node_down.getgCost() < n.getgCost()) {
								priorityQueue.remove(n);
								break;
							}
						}
					}
					
					priorityQueue.add(node_down);
					
				}
			}
			
			//if the tile can move in left direction.
			if(op == Operator.LEFT)
			{
				int temp = 0;
				Node node_left = new Node();
				int ch3[][] = new int[3][3];
				for(i=0; i<3; i++) {
					for(j=0; j<3; j++) {
						ch3[i][j] = b[i][j];
					}
				}
				
				//swap the tiles.
				temp = ch3[xPos][yPos];
				ch3[xPos][yPos] = ch3[xPos][yPos - 1];
				ch3[xPos][yPos - 1] = temp;
				
				//check if it exits.
				if(!isAlreadyExplored(ch3)) {
					node_left.setCurrentState(ch3);
					node_left.setParent(currentNode);
					node_left.calculateTotalCost(ch3, goal);
					
					for(Node n: priorityQueue) {
						if(Arrays.deepEquals(ch3, n.getCurrentState())) {
							if(node_left.getgCost() < n.getgCost()) {
								priorityQueue.remove(n);
								break;
							}
						}
					}
					
					priorityQueue.add(node_left);
					
				}
			}
			
			//if the tile can move in right direction.
			if(op == Operator.RIGHT)
			{
				int temp = 0;
				Node node_right = new Node();
				int ch4[][] = new int[3][3];
				for(i=0; i<3; i++) {
					for(j=0; j<3; j++) {
						ch4[i][j] = b[i][j];
					}
				}
				
				//swap the tiles.
				temp = ch4[xPos][yPos];
				ch4[xPos][yPos] = ch4[xPos][yPos + 1];
				ch4[xPos][yPos + 1] = temp;
				
				//check if it exits.
				if(!isAlreadyExplored(ch4)) {
					node_right.setCurrentState(ch4);
					node_right.setParent(currentNode);
					node_right.calculateTotalCost(ch4, goal);
					
					for(Node n: priorityQueue) {
						if(Arrays.deepEquals(ch4, n.getCurrentState())) {
							if(node_right.getgCost() < n.getgCost()) {
								priorityQueue.remove(n);
								break;
							}
						}
					}
					
					priorityQueue.add(node_right);
					
				}
			}
			
			
			
		}
		
	}

	//This method checks if the state is already explored.
	private boolean isAlreadyExplored(int[][] state) {
		boolean isExists = false;
		for(Node n: nodes_explored)
		{
			if(Arrays.deepEquals(state, n.getCurrentState())) {
				isExists = true;
				break;
			}
		}
		
		return isExists;
	}

	//This method checks all the possible directions a tile can move.
	private List<Operator> getPossibleDirections(int xPos, int yPos) {
		List<Operator> directions = new ArrayList<Operator>();
		
		if((xPos -1) >=0 && (xPos -1) <=2 && yPos <=2 && yPos >= 0) {
			directions.add(Operator.UP);
		}
		if((xPos) >= 0 && (xPos) <=2 && (yPos - 1) >= 0 && (yPos -1) <=2) {
			directions.add(Operator.LEFT);
		}
		if((xPos + 1) >= 0 && (xPos + 1) <=2 && (yPos) >= 0 && (yPos) <= 2) {
			directions.add(Operator.DOWN);
		}
		if(xPos >= 0 && xPos <= 2 && (yPos + 1) >=0 && (yPos + 1)<=2) {
			directions.add(Operator.RIGHT);
		}
		
		return directions;
		
	}
	
}
