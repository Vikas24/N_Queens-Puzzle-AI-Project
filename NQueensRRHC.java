import java.util.Random;
import java.util.Arrays;

public class NQueensRRHC{
	
	int[] current = new int[0];
	int numOfNonAtt = 0;
	int numOfPairs = 0;
	int cNum = 0;
	int numOfRestarts = 0;
	
	// Constructer sets the current configuration to the start configuration.
	public NQueensRRHC (int[] start){
		current = start;
		numOfPairs = ((current.length) * ((current.length) - 1)) / 2;
	}
	public int getNumOfRestarts(){
		return numOfRestarts;
	}
	public int[] nQueensSolverRRHC(){
		int count = 0;
		cNum = ConflictsNumber(current);
		numOfNonAtt = numOfPairs - cNum;
		if(numOfNonAtt == numOfPairs){
			return current;
		}
		while(count < 25){
			// have not hit a Local Minimum.
			boolean localMax = false;
			while(localMax == false){
				int[][] pM = possibleMovements(current);
				int[] tempConfig = bestConfig(pM,current);
				int cNumOfTempConfig = ConflictsNumber(tempConfig);
				int tempNumOfNonAtt = numOfPairs - cNumOfTempConfig;
				if(numOfNonAtt >= tempNumOfNonAtt){
					// hit Local Minimum so conduct Random Restart.
					localMax = true;
					count += 1;
					numOfRestarts += 1;
					current = randRestart();
					cNum = ConflictsNumber(current);
					numOfNonAtt = numOfPairs - cNum;
				} else {
					current = tempConfig;
					cNum = cNumOfTempConfig;
					numOfNonAtt = numOfPairs - cNum;
					if(numOfNonAtt == numOfPairs){
						return current;
					}
				}	
			}
		}
		
		return current;		
	}
	
	private int ConflictsNumber(int[] array){
		Conflicts s = new Conflicts(array);
		return s.numOfConflicts();
	}
	
	private int[][] possibleMovements(int[] array){
		int size = array.length;
		int[][] children = new int[size][size];
		int[] tempArray = Arrays.copyOf(array,size);
		
		for(int col = 0; col < size; col++){
			for(int row = 0; row < size; row++){
				if(row != array[col]){
					tempArray[col] = row;
					int tempCNum = ConflictsNumber(tempArray);
					children[row][col] = tempCNum;
				} else {
					// -1 indicates current placement of queen in the column.
					children[row][col] = -1;
				}
			}
			tempArray[col] = array[col];
		}
		
		return children;
	}
	
	private int[] bestConfig(int[][] pMArray, int[] array){
		int bestCNum = 100000;
		int bestRow = 0;
		int bestCol = 0;
		int size = array.length;
		int[] tempArray = Arrays.copyOf(array,size);
		
		for(int col = 0; col < size; col++){
			for(int row = 0; row < size; row++){
				if(pMArray[row][col] != -1){
					if(pMArray[row][col] < bestCNum){
						bestCNum = pMArray[row][col];
						bestRow = row;
						bestCol = col;
					}
				}
			}
		}
		tempArray[bestCol] = bestRow;
		return tempArray;
	}
	
	private int[] randRestart(){
		int[] array = new int[current.length];
		for(int i = 0; i < array.length; i++){
			Random r = new Random();
			array[i] = Math.abs(r.nextInt() % (current.length));
		}
		return array;
	}
	
}