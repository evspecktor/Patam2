package server;
import java.util.ArrayList;
import java.util.HashSet;

public class BFS extends CommonSearcher{

	@Override
	public ArrayList<State> search(Searchable s) {
		ArrayList<State> solution = new ArrayList<State>();
		
		openList.add(s.GetInitState());
		HashSet<State> closedSet = new HashSet<State>();
		
		while (openList.size()>0)
		{
			System.out.println(openList.size());
			State n = openList.poll();
			closedSet.add(n);
			
			if (n.isGoal())
			{
				solution.add(n);
				System.out.println("The solution is " + n.state);
				break;
			}	
			ArrayList<State> successors = s.getAllStates(n);
			for (State s1 : successors)
			{
				if(!closedSet.contains(s1))
				{
					s1.cameFrom = n;
					openList.add(s1);
				}
			}
		}
		return solution;
	}
}
