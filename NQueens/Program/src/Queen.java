/*
 * This class is responsible for maintaining the state of the queens in the current board.
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class Queen {
	private int row;
	private int column;
	
	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	//Checks whether a queen can attack other queens.
	public boolean canAttack(Queen q) {
		boolean isAttackPossible = false;
		
		//test columns and rows.
		if(row == q.getRow() || column == q.getColumn()) {
			isAttackPossible = true;
		}
		else if(Math.abs(column - q.getColumn()) == Math.abs(row - q.getRow())) {
			isAttackPossible = true;
		}
		return isAttackPossible;
	}
	
	//Moves the queen with the given number of positions.
	public void move(int numberOfPositions) {
		row += numberOfPositions;
		
		//Check bounds.
		if(row > (NQueens.numberOfQueens - 1) && (row % (NQueens.numberOfQueens - 1) != 0 )) {
			row = (row % (NQueens.numberOfQueens - 1)) - 1;
		}
		else if(row > (NQueens.numberOfQueens - 1) && (row % (NQueens.numberOfQueens - 1)== 0)) {
			row = (NQueens.numberOfQueens - 1);
		}
	}
}
