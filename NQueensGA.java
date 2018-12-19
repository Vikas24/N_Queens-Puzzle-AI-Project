import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class NQueensGA{
	
	int n = 0;
	int popSize = 0;
	double mutationChance = 0;
	int numOfGenerations = 0;
	int numOfNonAtt = 0;
	int numOfPairs = 0;
	int cNum = 0;
	double selectionFactor = 0;
	int genNum = 0;
	int numOfMut = 0;
	ArrayList<NQueens> population = new ArrayList<NQueens>();
	
	
	public NQueensGA(int bSize, int initPopSize, double mChance, int numOfGen, double sFactor){
		n = bSize;
		popSize = initPopSize;
		mutationChance = mChance;
		numOfGenerations = numOfGen;
		selectionFactor = sFactor;
		numOfPairs = (n * (n-1))/2;
	}
	
	public NQueens nQueensSolverGA(){
		initPopulation();
		int count = 0;
		boolean solFound = false;
		while(count < numOfGenerations){
			sortPopulation();
			ArrayList<NQueens> newPopulation = new ArrayList<NQueens>();
			int sum = 0;
			for(int i = 0; i < popSize; i++){
				int[] x = randomSelectParent();
				int[] y = randomSelectParent();
				
				Random r = new Random();
				int crossoverPoint = (int) (Math.random() * (n-1)) + 1;
				
				// stuff for child 1
				int[] c1 = reproduce(x,y,crossoverPoint);
				Conflicts s1 = new Conflicts(c1);
				cNum = s1.numOfConflicts();
				numOfNonAtt = numOfPairs - cNum;
				sum += numOfNonAtt;
				NQueens child1 = new NQueens(c1,numOfNonAtt,0);
				if(numOfNonAtt == numOfPairs){
					return child1;
				}
				newPopulation.add(child1);
				
				// stuff for child 2
				int[] c2 = reproduce(y,x,crossoverPoint);
				Conflicts s2 = new Conflicts(c2);
				cNum = s2.numOfConflicts();
				numOfNonAtt = numOfPairs - cNum;
				sum += numOfNonAtt;
				NQueens child2 = new NQueens(c2,numOfNonAtt,0);
				if(numOfNonAtt == numOfPairs){
					return child2;
				}
				newPopulation.add(child2);
			}
			fitnessFn(sum);
			cNum = 0;
			count += 1;
			genNum += 1;
			population = newPopulation;	
		}
		sortPopulation();
		NQueens bestSol = population.get(0);
		return bestSol;
	}
	
	public int getGenNum(){
		return genNum;
	}
	
	public int getNumOfMutations(){
		return numOfMut;
	}
	
	private int[] reproduce(int[] x, int[] y, int cPoint){
		int[] child = new int[n];
		for(int i = 0; i < x.length; i++){
			if(i < cPoint){
				child[i] = x[i];
			} else{
				child[i] = y[i];
			}
			double r = Math.random();
			if(r <= mutationChance){
				numOfMut += 1;
				Random rand = new Random();
				child[i] = Math.abs(rand.nextInt() % n);
			}
		}
		return child;
	}
		
	private int[] randomSelectParent(){
		double r = Math.random();
		int randIndex = (int) (popSize * Math.pow(r, selectionFactor));
		NQueens p = population.get(randIndex);
		int[] parent = p.getNQueen();
		return parent;
	}
	
	private void initPopulation(){
		int sum = 0;
		for(int i = 0; i < popSize; i++){
			int[] array = new int[n];
			for(int j = 0; j < array.length; j++){
				Random r = new Random();
				array[j] = Math.abs(r.nextInt() % n);
			}
			Conflicts s = new Conflicts(array);
			cNum = s.numOfConflicts();
			numOfNonAtt = numOfPairs - cNum;
			sum += numOfNonAtt;
			NQueens nQueensInd = new NQueens(array,numOfNonAtt,0);
			population.add(nQueensInd);
		}
		fitnessFn(sum);
	}
	
	private void fitnessFn(int val){
		for(int i = 0; i < population.size(); i++){
			NQueens nQueensInd = population.get(i);
			double nonAtt = (double) nQueensInd.getNonAttPairs();
			double fitnessVal = (nonAtt/val);
			nQueensInd.setFitnessVal(fitnessVal);
		}
	}
	
	private void sortPopulation(){
		for(int i = 0; i < popSize-1; i++){
			int index = i;
			for(int j = i+1; j < popSize; j++){
				
				NQueens nQueensInd1 = population.get(index);
				int val1 = nQueensInd1.getNonAttPairs();
				
				NQueens nQueensInd2 = population.get(j);
				int val2 = nQueensInd2.getNonAttPairs();
				
				if(val2 > val1){
					index = j;
				}
			}
			NQueens larger = population.get(index);
			population.remove(index);
			population.add(i,larger);
		}
	}
	
}