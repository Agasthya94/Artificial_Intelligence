/*
 * This enum is the Operator for all the possible actions that a tile can move.
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public enum Operator {

	UP(1), DOWN(2), LEFT(3), RIGHT(4);

	private int type;

	Operator(int type) {
		this.type = type;
	}

	public int getOperator() {
		return type;
	}
}
