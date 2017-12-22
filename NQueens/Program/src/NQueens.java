import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/*
 * This class is the main class that generates a random initial state for the 
 * NQueens problem and tries to solve using Hill Climbing, Random Restart and 
 * Min conflicts algorithm.
 * 
 * @author: Agasthya Vidyanath Rao Peggerla.
 * */
public class NQueens {
	
	public static int numberOfQueens;
	
	public static void main(String[] args) {
		System.out.println("Enter the number of Queens: ");
		Scanner sc = new Scanner(System.in);
		numberOfQueens = sc.nextInt();
		sc.close();
		
		//Initialize board.
		NQueens board = new NQueens();
		Queen[] startBoard = board.generateBoard();
		
		//Creates hill climbing instance.
		HillClimbing hillClimber = new HillClimbing(startBoard);
		RandomRestart randomRestart = new RandomRestart(startBoard);
		MinimumConflicts minConflicts = new MinimumConflicts(startBoard);
		
		System.out.println("RESULT:");
		
		//Hill Climbing.
		System.out.println("--------------- Hill Climbing ----------------");
		long time1= new Date().getTime();
		Node hillSolved = hillClimber.hillClimbing();
		
		if(hillSolved.getHeuristic() == 0) {
			System.out.println("INFO:  Hill Climbing algorithm has solved the problem.");
		}
		else {
			System.out.println("INFO: Hill climbing failed to find the solution.");
		}
		
		System.out.println(hillSolved.toString());
		System.out.println("Total Steps climbed: " + hillClimber.getTotalStepsClimbed());
		System.out.println("Total time taken to execute: " + (new Date().getTime() - time1));
		
		//Hill Climbing with random restart.
		System.out.println("---------------- Hill Climbing with random restart ----------");
		long time2 = new Date().getTime();
		Node randomSolved = randomRestart.randomRestart();
		
		if(randomSolved.getHeuristic() == 0) {
			System.out.println("INFO: Random restart solved problem.");
		}
		else {
			System.out.println("INFO: Random restart failed to solve problem.");
		}
		
		System.out.println("Solution:");
		System.out.println(randomSolved.toString());
		System.out.println("Total number of restarts: "+ randomRestart.getNumberOfRestarts());
		System.out.println("Total number of steps climbed are: " + randomRestart.getTotalStepsClimbed());
		System.out.println("Total time to execute algorithm: " + (new Date().getTime() - time2));
		
		//Minimum conflicts algorithm.
		System.out.println("------------------ Min Conflicts Algorithm ---------------------");
		long time3 = new Date().getTime();
		Node minimumSolved = minConflicts.minimumConflicts();
		
		if(minimumSolved.getHeuristic() == 0) {
			System.out.println("INFO: Min conflicts has solved problem.");
		}
		else {
			System.out.println("INFO: Min conflicts has failed to solve problem.");
		}
		
		System.out.println("Solution:");
		System.out.println(minimumSolved.toString());
		System.out.println("Total number of restarts : " + minConflicts.getNumberOfRestarts());
		System.out.println("Total number of steps climbed : " + minConflicts.getTotalStepsClimbed());
		System.out.println("Total time taken to execute the algorithm: " + (new Date().getTime() - time3));
	}
	
	//Generate a random initial board for a given number of queens.
	public Queen[] generateBoard() {
		Queen[] start = new Queen[numberOfQueens];
		
		Random randomGen = new Random();
		for(int i=0; i < numberOfQueens; i++) {
			start[i] = new Queen(randomGen.nextInt(numberOfQueens),i);
		}
		return start;
	}
}
