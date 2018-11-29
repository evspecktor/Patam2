package server;
import java.util.Comparator;

public class MyComparator implements Comparator<State> {

	@Override
	public int compare(State a, State b) {
		return (int) (a.cost - b.cost);
	}
	
	
}