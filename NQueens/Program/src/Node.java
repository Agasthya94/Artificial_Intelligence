import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the current state, the neighbors of the queens and the heuristic.
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class Node implements Comparable<Node> {
	
	private Queen[] state;
	private List<Node> neighbors;
	private int hn;
	
	//Constructor.
	public Node() {
		state = new Queen[NQueens.numberOfQueens]; //Empty state.
		neighbors = new ArrayList<Node>(); // Empty neighbor list.
	}
	
	public Node(Node n) {
		state = new Queen[NQueens.numberOfQueens];
		neighbors = new ArrayList<Node>();
		
		for(int i=0; i < NQueens.numberOfQueens ; i++) {
			state[i] = new Queen(n.state[i].getRow(), n.state[i].getColumn());
		}
		hn = 0;
	}
	
	//Generating the neighbors of the current start state.
	public ArrayList<Node> generateNeighbors(Node startState) {
		int count = 0;
		if(startState == null)
			System.out.println("WARN: Null state.");
		
		for(int i=0; i < NQueens.numberOfQueens; i++) {
			for(int j=0; j < NQueens.numberOfQueens; j++) {
				neighbors.add(count, new Node(startState));
				neighbors.get(count).state[i].move(j);
				
				//compute its hn value.
				neighbors.get(count).computeHeuristic();
				count++;
			}
		}
		
		return (ArrayList<Node>) neighbors;
	}
	
	public int computeHeuristic() {
		
		for(int i=0; i < NQueens.numberOfQueens - 1; i++) {
			for(int j= i+1; j < NQueens.numberOfQueens; j++) {
				if(state[i].canAttack(state[j])) {
					hn++;
				}
			}
		}
		
		return hn;
	}
	
	public int getHeuristic() {
		return hn;
	}

	@Override
	public int compareTo(Node n) {
		if(this.hn < n.getHeuristic())
			return -1;
		else if(this.hn > n.getHeuristic())
			return 1;
		else
			return 0;
	}
	
	public void setState(Queen[] s) {
		for(int i=0; i < NQueens.numberOfQueens; i++) {
			state[i] = new Queen(s[i].getRow(), s[i].getColumn());
		}
	}
	
	public Queen[] getState() {
		return state;
	}
	
	public String toString() {
		String result = "";
		
		String[][] board = new String[NQueens.numberOfQueens][NQueens.numberOfQueens];
		
		//initialize board with X's to indicate emppty spaces.
		for(int i=0; i < NQueens.numberOfQueens; i++) {
			for(int j=0; j < NQueens.numberOfQueens; j++) {
				board[i][j] = "0 ";
			}
		}
		
		//place queens on the board.
		for(int i=0; i < NQueens.numberOfQueens; i++) {
			board[state[i].getRow()][state[i].getColumn()] = "Q ";
		}
		
		//feed values into the result string.
		for(int i=0; i < NQueens.numberOfQueens; i++) {
			for(int j=0; j < NQueens.numberOfQueens; j++) {
				result += board[i][j];
			}
			result += "\n";
		}
		return result;
	}

}
