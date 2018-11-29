package server;
import java.util.ArrayList;

public class PipeGameBoardSolver implements Solver {
	
	Searcher s;
	Searchable sable;
	
	@Override
	public ArrayList<String> solve(PipeGameBoardState problem) {
		
		ArrayList<State> solution;
		ArrayList<String> toReturn = null; 
		
		s = new BestFirstSearch();
		sable = new PipeGameBoardSearchable(problem);
		
		solution = s.search(sable);
		
		if (!(solution.size() < 1))
			return solution.get(0).wayToSolution();
		return toReturn;
			
		
		
	}
}
