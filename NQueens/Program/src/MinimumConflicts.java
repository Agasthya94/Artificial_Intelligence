/*
 * This class implements the Min conflicts algorithm for the given initial state.
 * @Author: Agasthya Vidyanath Rao Peggerla.
 * */
public class MinimumConflicts {

	private HillClimbing hillClimber;
	private int numberOfRestarts;
	private int totalStepsClimbed;
	
	public MinimumConflicts(Queen[] startBoard) {
		hillClimber = new HillClimbing(startBoard);
		numberOfRestarts = 0;
		totalStepsClimbed = 0;
	}
	
	public Node minimumConflicts() {
		
		Node currentNode = hillClimber.getStartNode();
		int heuristic = currentNode.getHeuristic();
		boolean stuck = false;
		
		while(heuristic != 0 && !stuck) {
			for(int i = 0; i < NQueens.numberOfQueens ; i++) {
				for(int j=1; j < NQueens.numberOfQueens; j++) {
					heuristic = currentNode.computeHeuristic();
					
					if(heuristic == 0) {
						break;
					}
					
					currentNode.getState()[i].move(j);
					
					if(currentNode.computeHeuristic() < heuristic) {
						stuck = false;
					}
					else {
						stuck = true;
					}
				}
				
				if(heuristic == 0) {
					break;
				}
				
				if(stuck) {
					numberOfRestarts++;
					stuck = false;
					totalStepsClimbed += hillClimber.getTotalStepsClimbed();
					hillClimber = new HillClimbing();
					currentNode = hillClimber.hillClimbing();
					totalStepsClimbed += hillClimber.getTotalStepsClimbed();
				}
			}
		}
		
		return currentNode;
	}
	
	public int getNumberOfRestarts() {
		return numberOfRestarts;
	}
	
	public int getTotalStepsClimbed() {
		return totalStepsClimbed;
	}
}
