package server;
import java.util.Comparator;

public class JobComperator implements Comparator<Job>{
	
			
		@Override
		public int compare(Job j1, Job j2) {
			System.out.println("j1 size = " + j1.getBoardSize() + " j2 size = " + j2.getBoardSize());
			return  j1.getBoardSize()-j2.getBoardSize();
		}

}
