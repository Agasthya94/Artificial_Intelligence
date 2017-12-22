import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * This class is responsible for taking initial and goal states from user and displaying the final results.
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class TestClient {
	
	public static void main(String[] args) {
		
		//Initialize the initial states and goal states.
		int initialState[][] = new int[3][3];
		int goalState[][] = new int[3][3];
		int path[][] = new int[3][3];
		Scanner sc = new Scanner(System.in);
		List<Node> display = new ArrayList<Node>();
		
		//enter the initial state.
		System.out.println("Please enter the initial state board.");
		System.out.println("NOTE: Enter 0 for the blank state.");
		for(int i=0; i < 3; i++) {
			for(int j=0; j< 3; j++) {
				initialState[i][j] = sc.nextInt();
			}
		}
		
		//enter the goal state from user.
		System.out.println("Please enter the goal state board.");
		System.out.println("NOTE: Enter 0 for the blank state.");
		for(int i=0; i < 3; i++) {
			for(int j=0; j< 3; j++) {
				goalState[i][j] = sc.nextInt();
			}
		}
		
		//initialize puzzle.
		AStarAlgorithm astar = new AStarAlgorithm();
		Puzzle puzzle = astar.initializePuzzle(initialState, goalState);
		
		//process the puzzle.
		Node result = astar.processPuzzle(puzzle);
		
		//Display the result.
		if(result == null)
		{
			System.out.println("ERROR: Failed to reach the goal state.");
		}
		else
		{
			// If the goal state is reached.
			while(result != null)
			{
				display.add(result);
				result = result.getParent();
			}
			
			Collections.reverse(display);
			System.out.println("INFO: Printing Optimal Path.. ");
			for(Node n: display)
			{
				path = n.getCurrentState();
				for(int i=0; i < 3; i++) {
					for(int j=0; j<3; j++) {
						System.out.print(path[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println();
			}
		}
		
		//Generate a final report.
		System.out.println("Report : ");
		System.out.println("Number of nodes expanded : " +  puzzle.getNodes_expanded());
		System.out.println("Number of nodes generated : " + puzzle.getNodes_generated());
		System.out.println("Number of nodes in the optimal path : " + puzzle.getPath_cost());
	}
}
