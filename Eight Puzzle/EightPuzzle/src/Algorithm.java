/*
 * This class is the base class for the entire algorithm.
 * It can be extended to further algorithms.
 * 
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public abstract class Algorithm {

	/* This method initializes the puzzle by setting the given initial state
	 * and goal state from the user.
	 */
	public Puzzle initializePuzzle(int initial[][], int goalState[][])
	{
		Puzzle puzzle = new Puzzle();
		puzzle.setInitialState(initial);
		puzzle.setGoalState(goalState);
		puzzle.setNodes_expanded(0);
		puzzle.setNodes_generated(0);
		puzzle.setPath_cost(0);
		return puzzle;
	}
	
	/*This is an abstract method which can be extended and processed by various algorithms.*/
	public abstract Node processPuzzle(Puzzle puzzle);
	
}
