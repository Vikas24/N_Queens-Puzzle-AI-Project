import java.util.Random;
import java.util.Arrays;

public class N_Queen_Driver{
	public static void main(String[] args){
		// size of the gameboard
		int n = 21;
		System.out.println("N: " + n);
		System.out.print("");
		int[] nQueens = new int[n];
//-----------------------------------------------------------------------------------------------------------------//
		//For 1 iteration of Random Restart Hill Climb and Genetic Algorithm
//-----------------------------------------------------------------------------------------------------------------//
	
		System.out.println("Random Restart Hill Climb 1 iteration");
		// Randomly populates the gameboard array for 21-Queens Problem.
		for(int i = 0; i < nQueens.length; i++){
			Random r = new Random();
			nQueens[i] = Math.abs(r.nextInt() % (nQueens.length));
		}
		// nQueensSolverRRHC() uses random restart hill climbing to solve the problem.
		NQueensRRHC s = new NQueensRRHC(nQueens);
		Conflicts c = new Conflicts(nQueens);
		
		int cNum = c.numOfConflicts();
		int numOfPairs = ((nQueens.length) * ((nQueens.length) - 1)) / 2;
		System.out.println("Initial Configuration:");
		System.out.println(Arrays.toString(nQueens));
		System.out.println("Number of Non Attacking Pairs of Queens: " + (numOfPairs-cNum));
		int[] solution = s.nQueensSolverRRHC();
		
		// prints the final configuration if one was found otherwise it prints the best configuration found.
		c = new Conflicts(solution);
		cNum = c.numOfConflicts(); 
		System.out.println("Number of Restarts: "+s.getNumOfRestarts());
		System.out.println("Final Configuration:");
		System.out.println(Arrays.toString(solution));
		System.out.println("Number of Non Attacking Pairs of Queens: " + (numOfPairs-cNum));
		
		System.out.println("");
		
		
		// nQueensSolverGA() uses the Genetic Algorithm to solve the problem.
		// size of the board
		System.out.println("Genetic Algorithm for 1 iteration");
		
		// initial population size
		int initPopSize = 100;
		System.out.println("initial Population Size: " + initPopSize);
		// mutation chance of the child
		double mutationChance = (double) 5/100;
		System.out.println("Chance of Mutation: " + mutationChance);
		// max number of generations for GA Algorithm to run
		int numOfGenerations = 10000;
		System.out.println("Max number of Generations allowed: " + numOfGenerations);
		// influences which parents will be chosen.
		double selectionFactor = (double) 5;
		
		NQueensGA s1 = new NQueensGA(n,initPopSize,mutationChance,numOfGenerations,selectionFactor);
		NQueens solution1 = s1.nQueensSolverGA();
		System.out.println("Final Configuration:");
		System.out.println(Arrays.toString(solution1.getNQueen()));
		System.out.println("Number of Non Attacking Pairs of Queens: " + solution1.getNonAttPairs());
		System.out.println("Number of Generations Produced Before Finding Solution: " + s1.getGenNum());
		System.out.println("Number of Childern Mutated: " + s1.getNumOfMutations());
		
	
//-----------------------------------------------------------------------------------------------------------------//
//For 1000 iterations Random Restart Hill Climb and Genetic Algorithm
//-----------------------------------------------------------------------------------------------------------------//
	
		// nQueensSolverRRHC() uses random restart hill climbing to solve the problem for 1000 nQueen random problems.
		// outputs the percent of solved i.e. percent of the total where there were no pairs of attacking queens.
/*		
		System.out.println("Random Restart Hill Climb 1000 iterations");
		int sum = 0;
		long sumTime = 0;	
		for(int j = 0; j < 1000; j++){
			for(int i = 0; i < nQueens.length; i++){
				Random r = new Random();
				nQueens[i] = r.nextInt(n);
			}
			long sTime = System.nanoTime();
			NQueensRRHC s = new NQueensRRHC(nQueens);
			//Conflicts c = new Conflicts(nQueens);
	
			//int cNum = c.numOfConflicts();
			//System.out.println(Arrays.toString(nQueens) + cNum);
			int[] solution = s.nQueensSolverRRHC();
			
			Conflicts c = new Conflicts(solution);
			int cNum = c.numOfConflicts();
			int numOfPairs = ((n) * ((n) - 1)) / 2;
			int numOfNonAttackPairs = numOfPairs - cNum; 
			//System.out.println(s.getNumOfRestarts());
			//System.out.println(Arrays.toString(solution) + cNum);
			if(numOfNonAttackPairs == numOfPairs){
				sum += 1;
			}
			
			long eTime = System.nanoTime();
			long totalTime = eTime - sTime;
			sumTime += totalTime;
		}
		System.out.println("Total Solved: " + sum);
		System.out.println("Total Times Ran: " + 1000);
		double percent = (double)sum / 1000;
		double avgTime = (double)sumTime / 1000;
		System.out.println("Percent that were solved under 25 Restarts: " + (percent*100));
		System.out.println("Average Time Taken (nanoSeconds): " + avgTime);
		
		
		System.out.println("");
		System.out.println("Genetic Algorithm for 1000 iterations");
		sum = 0;
		sumTime = 0;	
		for(int j = 0; j < 1000; j++){
			// initial population size
			int initPopSize = 100;
			// mutation chance of the child
			double mutationChance = (double) 5/100;
			// max number of generations for GA Algorithm to run
			int numOfGenerations = 10000;
			// influences which parents will be chosen.
			double selectionFactor = 5;
			
			long sTime = System.nanoTime();
			NQueensGA s = new NQueensGA(n,initPopSize,mutationChance,numOfGenerations,selectionFactor);
			NQueens solution = s.nQueensSolverGA();
			
			int numOfPairs = ((nQueens.length) * ((nQueens.length) - 1)) / 2;
			if(solution.getNonAttPairs() == numOfPairs){
				sum += 1;
			}
			long eTime = System.nanoTime();
			long totalTime = eTime - sTime;
			sumTime += totalTime;
		}
		System.out.println("Total Solved: " + sum);
		System.out.println("Total Times Ran: " + 1000);
		percent = (double)sum / 1000;
		avgTime = (double)sumTime / 1000;
		System.out.println("Percent that were solved: " + (percent*100));
		System.out.println("Average Time Taken (nanoSeconds): " + avgTime);
	*/	
	}
}