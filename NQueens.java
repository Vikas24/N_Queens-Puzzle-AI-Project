import java.util.Arrays;

public class NQueens{
	
	int[] nQueen = new int[0];
	int nonAttPairs = 0;
	double fitnessVal = 0;
			
	public NQueens(int[] array,int x,double y){
		nQueen = array;
		nonAttPairs = x;
		fitnessVal = y;
	}
	
	public int[] getNQueen(){
		return nQueen;
	}
	
	public int getNonAttPairs(){
		return nonAttPairs;
	}
	
	public double getFitnessVal(){
		return fitnessVal;
	}
	
	public void setFitnessVal(double val){
		fitnessVal = val;
	}
	
	public String toString(){
		String a = Arrays.toString(nQueen);
		String b = "Number of NonAttacking Pairs: "+ nonAttPairs;
		String c = "Fitness Value of Individual: "+ fitnessVal;
		
		return (a + "\n" + b + "\n" + c);
	}
}