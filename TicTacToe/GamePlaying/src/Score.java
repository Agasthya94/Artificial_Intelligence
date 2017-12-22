//This class is the abstraction of Position and its corrosponding score.
/*
* @Author: Agasthya Vidyanath Rao Peggerla.
*/

public class Score {
	
	private int score;
	private Position position;
	
	public Score(int score, Position position) {
		this.score = score;
		this.position = position;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
