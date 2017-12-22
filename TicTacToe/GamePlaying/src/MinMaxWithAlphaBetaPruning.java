import java.util.List;

//This class contains the implementation of the Min Max and alpha beta pruning
//algorithm, and also verifies when to end the game.
/*
* @Author: Agasthya Vidyanath Rao Peggerla.
*/

public class MinMaxWithAlphaBetaPruning {

	private Moves moves = new Moves();
	private Board boardState = new Board(); 
	
    //Minimax Algorithm with Alpha Beta pruning
    public int minMax(int alpha, int beta, int depth, String currentPlayer){

        int maxDepth;
        List<Position> possibleMoves = moves.returnPossibleMoves();
        int max_val = Integer.MIN_VALUE, min_val = Integer.MAX_VALUE;
        
        if (Board.board.length==3) {
        	maxDepth=-1;
        }else {
        	maxDepth=4;       	
        }
            
        if(beta <= alpha){ 
            if(currentPlayer.equals("User")) {
            	return Integer.MAX_VALUE;
            }else {
            	return Integer.MIN_VALUE;
            }
        }
        
        if(depth == maxDepth || checkGameEnd()) {
            return boardState.analyzeCurrentBoardState();
        }

        if(possibleMoves.isEmpty()) {
    		return 0;
        }

        if(depth==0) {
        	Board.scores.clear();
        }
      
        for(int i=0;i<possibleMoves.size(); ++i){
            Position pos = possibleMoves.get(i);
            int current_score = 0;
            
            if(currentPlayer.equals("User")){
                moves.makeMove(pos, 1); 
                current_score = minMax(alpha, beta, depth+1, "Computer");
                max_val = Math.max(max_val, current_score); 
                
              //Calculation of alpha.
                alpha = Math.max(current_score, alpha);  
                
                if(depth == 0)
                    Board.scores.add(new Score(current_score, pos));
            }
            else if(currentPlayer.equals("Computer")){
                moves.makeMove(pos, 2);
                current_score = minMax(alpha, beta, depth+1, "User"); 
                min_val = Math.min(min_val, current_score);
                
                //Calculation of beta.
                beta = Math.min(current_score, beta);
            }
            
            Board.board[pos.getxPosition()][pos.getyPosition()] = 0;
            
          //If a pruning was performed, we dont evaluate the rest of the siblings
            if(current_score == Integer.MAX_VALUE || current_score == Integer.MIN_VALUE) {
            	break;
            }
        }
        
        if (currentPlayer.equals("User")) {
        	return max_val;
        }else {
        	return min_val;
        }
    }  
    
  //returns true if either of the player won or if the match is a draws
    public boolean checkGameEnd() {
        return (isWonByPlayerX() || isWonByPlayerO() || moves.returnPossibleMoves().isEmpty());
    }
    
    //Checking if Player O has won.
    public boolean isWonByPlayerO() {
        if (Board.board.length==3)
        {
            if ((Board.board[0][0] == Board.board[1][1] && Board.board[0][0] == Board.board[2][2] && Board.board[0][0] == 2) || (Board.board[0][2] == Board.board[1][1] && Board.board[0][2] == Board.board[2][0] && Board.board[0][2] == 2)) {
            	return true;
            }
            
            for (int i = 0; i < 3; ++i) {
            	if ((Board.board[i][0] == Board.board[i][1] && Board.board[i][0] == Board.board[i][2] && Board.board[i][0] == 2)
            			|| (Board.board[0][i] == Board.board[1][i] && Board.board[0][i] == Board.board[2][i] && Board.board[0][i] == 2)) {
            		return true;
            	}
            }

            return false;
        }else {
        	int diagonol=0;
        	for (int i = 0, j = 0; i < Board.board.length; ++i, ++j) {
        		if (Board.board[i][j] == 2)
        		{
        			diagonol++;
        		}
        	}
        
        	if (diagonol==Board.board.length) {
        		return true;
        	}
        	
        	diagonol=0;
        	for (int i = Board.board.length-1, j = 0; i >=0; --i, ++j) {
        		if (Board.board[i][j] == 2) {
        			diagonol++;
        		}
        	}
        	
        	if (diagonol==Board.board.length) {
        		return true;
        	}

        	int row,column;
        	for (int i=0; i<Board.board.length; i++) {   
        		row=0;
        		for (int j=0; j<Board.board.length; j++)
        		{
        			if (Board.board[i][j]==1) {
        				row++;
        			}
        		}
        		if (row== Board.board.length) {
        			return true;
        		}		
        	}
        
        	for (int i=0; i< Board.board.length; i++) {   
        		column=0;
        		for (int j=0; j< Board.board.length; j++)
        		{
        			if(Board.board[j][i]==1) {
        				column++;
        			}
        		}
        		
        		if (column==Board.board.length) {
        			return true;	
        		}
        	}
        	return false;
        }
    }
    
    
    
	// checking if Player X has won.
	public boolean isWonByPlayerX() {

		if (Board.board.length == 3) {
			if ((Board.board[0][0] == Board.board[1][1] && Board.board[0][0] == Board.board[2][2] && Board.board[0][0] == 1)
					|| (Board.board[0][2] == Board.board[1][1]
							&& Board.board[0][2] == Board.board[2][0] && Board.board[0][2] == 1)) {
				return true;
			}

			for (int i = 0; i < 3; ++i) {
				if (((Board.board[i][0] == Board.board[i][1] && Board.board[i][0] == Board.board[i][2] && Board.board[i][0] == 1) || (Board.board[0][i] == Board.board[1][i]
						&& Board.board[0][i] == Board.board[2][i] && Board.board[0][i] == 1))) {
					return true;
				}
			}

			return false;
		} else {
			int diagonol = 0;
			int row, column;
			for (int i = 0, j = 0; i < Board.board.length; ++i, ++j) {
				if (Board.board[i][j] == 1) {
					diagonol++;
				}
			}
			if (diagonol == Board.board.length) {
				return true;
			}

			diagonol = 0;

			for (int i = Board.board.length - 1, j = 0; i >= 0; --i, ++j) {
				if (Board.board[i][j] == 1) {
					diagonol++;
				}
			}

			if (diagonol == Board.board.length) {
				return true;
			}

			for (int i = 0; i < Board.board.length; i++) {
				row = 0;
				for (int j = 0; j < Board.board.length; j++) {
					if (Board.board[i][j] == 1)
						row++;
				}
				if (row == Board.board.length) {
					return true;
				}
			}

			for (int i = 0; i < Board.board.length; i++) {
				column = 0;
				for (int j = 0; j < Board.board.length; j++) {
					if (Board.board[j][i] == 1) {
						column++;
					}
				}
				if (column == Board.board.length) {
					return true;
				}
			}
			return false;
		}
	}
}
