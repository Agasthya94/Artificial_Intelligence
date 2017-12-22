/*
 * This class is a POJO for initializing the 8 puzzle and for reporting purposes.
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class Puzzle {
	
	private int initialState[][]; //initial state of the puzzle.
	private int goalState[][];  //goal state of the puzzle.
	private int nodes_expanded; // num of nodes expanded.
	private int nodes_generated; // num of nodes generated
	private int path_cost; // path cost of the optimal solution.
	
	public int[][] getInitialState() {
		return initialState;
	}
	public void setInitialState(int[][] initialState) {
		this.initialState = initialState;
	}
	public int[][] getGoalState() {
		return goalState;
	}
	public void setGoalState(int[][] goalState) {
		this.goalState = goalState;
	}
	public int getNodes_expanded() {
		return nodes_expanded;
	}
	public void setNodes_expanded(int nodes_expanded) {
		this.nodes_expanded = nodes_expanded;
	}
	public int getNodes_generated() {
		return nodes_generated;
	}
	public void setNodes_generated(int nodes_generated) {
		this.nodes_generated = nodes_generated;
	}
	public int getPath_cost() {
		return path_cost;
	}
	public void setPath_cost(int path_cost) {
		this.path_cost = path_cost;
	}
	
}
