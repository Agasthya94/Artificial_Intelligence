/*
 * This class is POJO for storing the current state and totalcost, h cost and gcost.
 * @author : Agasthya Vidyanath Rao Peggerla.
 * */
public class Node {
	
	private int[][] currentState;
	private int totalCost;
	private int hCost;
	private int gCost;
	Node parent;

	public int[][] getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int[][] currentState) {
		this.currentState = currentState;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int gethCost() {
		return hCost;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}

	public int getgCost() {
		return gCost;
	}

	public void setgCost(int gCost) {
		this.gCost = gCost;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	// Manhatten distance calculation - heuristic cost.
	public void calculateHCost(int a[][], int go[][]) {
		int i, j, k, l;
		this.hCost = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (a[i][j] != 0) {
					for (k = 0; k < 3; k++) {
						for (l = 0; l < 3; l++) {
							if (a[i][j] == go[k][l]) {
								this.hCost = this.hCost + Math.abs((i - k))
										+ Math.abs((j - l));
							}
						}
					}
				}
			}
		}
	}
	
	//calculates path cost
	public void calculateGCost(){
		if(this.parent!=null) {
			this.gCost= 1 + this.parent.gCost;	
		}
		else {
			this.gCost=0;
		}
	}
	
	//calculates the total cost of the path.
	public void calculateTotalCost(int currentState[][], int goalState[][])
	{
		this.calculateHCost(currentState, goalState);
		this.calculateGCost();
		this.totalCost = this.getgCost() + this.gethCost();
	}
}
