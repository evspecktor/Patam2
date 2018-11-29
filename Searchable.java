package server;
import java.util.ArrayList;

public interface Searchable {
	
	State GetInitState();
	//State GetState();
	boolean isGoal();
	void setCameFrom(State s);
	void setCost(int c);
	ArrayList<State> getAllStates(State s);
}
