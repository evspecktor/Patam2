package server;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class CommonSearcher implements Searcher{

	Comparator<State> comparator = new MyComparator();
	PriorityQueue<State> openList;
	
	
	public CommonSearcher() {
		openList= new PriorityQueue<State>(comparator);
	}

	@Override
	public abstract ArrayList<State> search(Searchable sbl);
}
