import java.util.ArrayList;
import java.util.List;

//This class contains the move (actions) that are present in the game.
/*
* @Author: Agasthya Vidyanath Rao Peggerla.
*/

public class Moves {

    //return all the available states
    public List<Position> returnPossibleMoves() {
        List<Position> freePositions = new ArrayList<>();
        for (int i = 0; i < Board.board.length; ++i) {
            for (int j = 0; j < Board.board.length; ++j) {
                if (Board.board[i][j] == 0) {
                    freePositions.add(new Position(i, j));
                }
            }
        }
        return freePositions;
    }
    
    //Returns best possible move 
    public Position getBestMove() {

        int maximum = -100000;
        int best = -1;

        for (int i = 0; i < Board.scores.size(); ++i) {
            if (maximum < Board.scores.get(i).getScore()) {
                maximum = Board.scores.get(i).getScore();
                best = i;
            }
        }
        return Board.scores.get(best).getPosition();
    }
    
    //Make move.
    public void makeMove(Position pos, int player) {
        Board.board[pos.getxPosition()][pos.getyPosition()] = player;
    }
}
