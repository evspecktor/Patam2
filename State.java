package server;
import java.util.ArrayList;

public abstract class State {
	
	protected String state;
	protected int cost;
	protected State cameFrom;
	
	public boolean equals(State s)
	{
		return (state.equals(s.state));
	}
	
	public abstract Boolean isGoal();
	
	public void setCameFrom(State s)
	{
		this.cameFrom = s;
	}
	
	public void setCost(int s)
	{
		this.cost = s;
	}
	
	public State getCameFrom()
	{
		return this.cameFrom;
	}
	
	public int getCost()
	{
		return this.cost;
	}
	
	public abstract ArrayList<String> wayToSolution();

}
