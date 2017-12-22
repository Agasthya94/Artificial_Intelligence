import java.util.ArrayList;
import java.util.List;

//This is a POJO class of the board that analyzes every aspect of the game board.
/*
* @Author: Agasthya Vidyanath Rao Peggerla.
*/
public class Board {

	static List<Score> scores = new ArrayList<Score>();
	static int[][] board;
	
	void setBoardState(int state[][]) {
		Board.board = state;
	}
	
	public int analyzeCurrentBoardState() {    //Checking the entire board for X,O and blanks
        int score = 0;

        for (int i = 0; i < board.length; ++i) {    //Checking rows
            int blankPositions = 0;
            int X = 0;
            int O = 0;
            for (int j = 0; j < board.length; ++j) {
                if (board[i][j] == 0) {
                    blankPositions++;
                } else if (board[i][j] == 1) {
                    X++;
                } else {
                    O++;
                }

            } 
            score+=computeScore(X, O); 
        }

        for (int j = 0; j < board.length; ++j) {    //Checking columns
            int blankPositions = 0;
            int X = 0;
            int O = 0;
            for (int i = 0; i < board.length; ++i) {
                if (board[i][j] == 0) {
                    blankPositions++;
                } else if (board[i][j] == 1) {
                    X++;
                } else {
                    O++;
                } 
            }
            score+=computeScore(X, O);
        }

        int blankPositions = 0;
        int X = 0;
        int O = 0;

        for (int i = 0, j = 0; i < board.length; ++i, ++j) {    //Checking rows
            if (board[i][j] == 1) {
                X++;
            } else if (board[i][j] == 2) {
                O++;
            } else {
                blankPositions++;
            }
        }

        score+=computeScore(X, O);

        blankPositions = 0;
        X = 0;
        O = 0;

        for (int i = board.length-1, j = 0; i >= 0; --i, ++j) {     //Checking diagonal
            if (board[i][j] == 1) {
                X++;
            } else if (board[i][j] == 2) {
                O++;
            } else {
                blankPositions++;
            }
        }

        score+=computeScore(X, O);

        return score;
    }
    
	//Computing Score
    private int computeScore(int X, int O){   
        int tem;

        if(O==0){
            tem = 10*X;
        }
        else if(X==0){
            tem = -(10*O);
        }
        else
            tem=0;
        return tem;
    }
    
    

	
	
	
}
