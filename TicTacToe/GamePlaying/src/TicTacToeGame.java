/*
* @Author: Agasthya Vidyanath Rao Peggerla.
*/

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//This class contains the main class for implementing the UI for the game.
public class TicTacToeGame extends JFrame {
	
	private JLabel selectTurnLabel;
	private JLabel selectSizeLabel;
	private String turn[];
	
	private JComboBox comboBox;
	private JPanel buttons;
	private JButton buttonArray[][];
	private JLabel note;
	
	private int positionX;
	private int positionY;
	private int player_turn;
	
	private MinMaxWithAlphaBetaPruning minMax;
	private Board board;
	private Moves moves;
	private Random random;
	private Position best_position;
	private JComboBox boardSize;
	private int entered_board_size = 3;
	private int c = 0;
	
	private JButton restartButton;
	private JPanel bp;
	private JPanel combo;
	
	public TicTacToeGame() {
		restartButton = new JButton("Restart Game");
		board = new Board();
		random = new Random();
		minMax = new MinMaxWithAlphaBetaPruning();
		moves = new Moves();
		
		buttons = new JPanel();
		selectTurnLabel = new JLabel("Select the turn.");
		note = new JLabel(" ");
		
		String turn[] = {"User", "Computer"};
		String bSize[] = {"Select", "3", "5", "7", "9", "11", "13", "15", "17", "19" };
		
		comboBox = new JComboBox(turn);
		boardSize = new JComboBox(bSize);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		selectSizeLabel = new JLabel("Select the board size: ");
		combo = new JPanel();
		setLayout(new FlowLayout());
		combo.setLayout(new FlowLayout());
		combo.add(boardSize);
		combo.add(selectTurnLabel);
		combo.add(comboBox);
		combo.add(note);
		add(combo);
		
		//Set the action handlers. 
		boardSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("INFO: Creating board");
						String val = boardSize.getSelectedItem().toString();
						if(val.equals("Select")) {
							System.out.println("ERROR: Please select a proper value.");
							System.exit(0);
						}
						entered_board_size = Integer.parseInt(boardSize.getSelectedItem().toString());
						board.setBoardState(new int[entered_board_size][entered_board_size]);
						buttonArray = new JButton[entered_board_size][entered_board_size];
						
						//Enter the grid.
						GridLayout grid_layout = new GridLayout(entered_board_size, entered_board_size);
						buttons.setLayout(grid_layout);
						add(buttons);
						buttons.setVisible(true);
						
						//Invoke action listener for each of the buttons.
						for(int i=0; i < entered_board_size; i++) {
							for(int j=0; j < entered_board_size; j++) {
								buttonArray[i][j] = new JButton("-");
								buttons.add(buttonArray[i][j]);
								
								final int k = i;
								final int l = j;
								buttonArray[k][l].addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(final ActionEvent ae1) {
										SwingUtilities.invokeLater(new Runnable() {
											@Override
											public void run() {
												if(ae1.getSource() == buttonArray[k][l]) {
													buttonArray[k][l].setEnabled(false);
													positionX = k;
													positionY = l;
													buttonArray[k][l].setText("O");
													c++;
													
													int count = 1;
													while(!minMax.checkGameEnd() && count == 1) {
														Position user_position = new Position(k, l);
														//2 for user.
														moves.makeMove(user_position, 2);
														
														if(minMax.checkGameEnd()) {
															break;
														}
														
														minMax.minMax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0,"User");
														best_position = moves.getBestMove();
														moves.makeMove(best_position, 1);
														buttonArray[best_position.getxPosition()][best_position.getyPosition()].setText("X");
														buttonArray[best_position.getxPosition()][best_position.getyPosition()].setEnabled(false);
														c++;
														count++;
													}
												}
												
												if((c == entered_board_size * entered_board_size) || minMax.isWonByPlayerX() || minMax.isWonByPlayerO()) {
													if(minMax.isWonByPlayerX()) {
														String x_won = "You Lost.";
														note.setText(x_won);
														disableAllButtons(buttonArray);
														System.out
																.println("RESULT: You have lost the game.");
													} else if(minMax.isWonByPlayerO()) {
														String o_own = "You won.";
														note.setText(o_own);
														disableAllButtons(buttonArray);
														System.out
																.println("RESULT: You have wont the game.");
													} else {
														String draw = "Draw";
														note.setText(draw);
														System.out
																.println("RESULT: The game is a draw.");
													}
												}
												
											}
										});
										
									}
								});
								
								validate();
								repaint();
							}
						}
						
						boardSize.setEnabled(false);
					}
				});
				
			}
		});
		
		bp = new JPanel();
		bp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bp.add(restartButton);
		add(bp, BorderLayout.SOUTH);
		
		//Restart button action listener.
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("INFO: Game will be restarted.");
						dispose();
						new TicTacToeGame();
					}
				});
				
			}
		});
		
		//Combo box action listener.
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						if(comboBox.getSelectedItem().equals("User")) {
							note.setText("User goes first.");
							System.out.println("INFO: User goes first.");
							player_turn = 1;
						}else {
							note.setText("Computer goes first");
							System.out.println("INFO: Computer goes first.");
							player_turn = 2;
							Position p = new Position(random.nextInt(entered_board_size), random.nextInt(entered_board_size));
							moves.makeMove(p, 1);
							buttonArray[p.getxPosition()][p.getyPosition()].setText("X");
							buttonArray[p.getxPosition()][p.getyPosition()].setEnabled(false);
							c++;
						}
						comboBox.setEnabled(false);
					}
				});
				
			}
		});
		
		setVisible(true);
		setSize(400, 400);
	}
	
	//Disable all the buttons after the game.
	private void disableAllButtons(
			JButton[][] buttonArray) {
		
		for(int i=0; i < buttonArray.length; i++) {
			for(int j=0; j < buttonArray[0].length; j++) {
				this.buttonArray[i][j].setEnabled(false);
			}
		}
		
	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
	}
}
