package server;
import java.util.ArrayList;

public interface Searcher {
	ArrayList<State> search(Searchable sbl);
}
