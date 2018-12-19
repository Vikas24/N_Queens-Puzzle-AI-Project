public class Conflicts{
	
	int[] current = new int[0];
	
	public Conflicts(int[] array){
		current = array;
	}
	
	public int numOfConflicts(){
		int cNum = 0;
		
		for(int i = 0; i < current.length; i++){
			for(int j = i+1; j < current.length; j ++){
				int diff = current[i] - current[j];
				int posDiff = j-i;
				int negDiff = i-j;
				if(diff == 0){
					cNum += 1;
				} else if((diff == posDiff)||(diff == negDiff)){
					cNum += 1;
				}
			}
		}
		return cNum;
	}
}