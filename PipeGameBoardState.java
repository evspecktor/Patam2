package server;
import java.util.ArrayList;

public class PipeGameBoardState extends State {
	
	PipeGameBoard pipeGameBoard;

	public PipeGameBoardState(PipeGameBoard pgb) {
		pipeGameBoard = new PipeGameBoard(pgb);
		state = pipeGameBoard.toStringStateID();
		cost = 0;
		cameFrom = null;
	}
	
	public PipeGameBoardState(PipeGameBoard pgb, int cost, PipeGameBoardState cameFrom)
	{
		pipeGameBoard = new PipeGameBoard(pgb);
		state = pipeGameBoard.toStringStateID();
		this.cost = cost;
		this.cameFrom = cameFrom;
	}
	
	public PipeGameBoardState(PipeGameBoardState pgbs)
	{
		this.pipeGameBoard = new PipeGameBoard(pgbs.pipeGameBoard);
		this.state = pgbs.state;
		this.cost = pgbs.cost;
		this.cameFrom = pgbs.cameFrom;
	}
	
	public Boolean clickPipe(int x, int y)
	{
		Boolean returnBool = pipeGameBoard.clickPipe(x, y);
		this.state = pipeGameBoard.toStringStateID();
		return returnBool;
	}
	
	public void clickPipeAlgo(int x, int y)
	{
		pipeGameBoard.clickPipeAlgo(x,y);
		this.state = pipeGameBoard.toStringStateID();
	}
	
	public void setVisitedAlgo(int x, int y)
	{
		this.pipeGameBoard.setVisitedAlgo(x,y); 
	}
	
	public Boolean isGoal()
	{
		return pipeGameBoard.isGoal();
	}

	@Override
	public ArrayList<String> wayToSolution() {
		
		return pipeGameBoard.locationAndClicksCount();
	}

	
}
