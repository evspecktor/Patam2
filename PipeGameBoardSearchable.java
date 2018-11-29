package server;
import java.util.ArrayList;
import java.util.List;

import server.PipeTypes.pipeEnum;
import server.PipeTypes.pipeHLType;

public class PipeGameBoardSearchable implements Searchable{
	
	//private ArrayList<PipeGameBoardState> graph = new ArrayList<>();
	private PipeGameBoardState InitState;
	PipeGameBoardState PipeGameBoardState; 

	
	public PipeGameBoardSearchable(PipeGameBoardState problem) {
		InitState = new PipeGameBoardState(problem);
		PipeGameBoardState = new PipeGameBoardState(problem);  
	}
	
	@Override
	public State GetInitState() {
		return this.InitState;
	}

	
	@Override
	public boolean isGoal() {
		return PipeGameBoardState.isGoal();
	}

	@Override
	public ArrayList<State> getAllStates(State s) {
		ArrayList<State> listToReturn= new ArrayList<>();
		PipeGameBoardState currentState = (PipeGameBoardState)s;
		Pipe pipeCurrentPosition = currentState.pipeGameBoard.getCurrentPosition();
		
		addRightPipeChildren(currentState, listToReturn, pipeCurrentPosition);
		addDownPipeChildren(currentState, listToReturn, pipeCurrentPosition);
		addLeftPipeChildren(currentState, listToReturn, pipeCurrentPosition);
		addUpPipeChildren(currentState, listToReturn, pipeCurrentPosition);
		
		return listToReturn;
	}
	
	
	private void addRightPipeChildren(PipeGameBoardState currentState, ArrayList<State> listToReturn, Pipe pipeCurrentPosition)
	{
		if (currentState.pipeGameBoard.isPickingAllowedRight(pipeCurrentPosition))
		{
			Pipe rightPipe = currentState.pipeGameBoard.getRightPipe(pipeCurrentPosition);
			
			if((rightPipe.getHLType() == pipeHLType.corner || rightPipe.getHLType() == pipeHLType.goal || rightPipe.getHLType() == pipeHLType.line) 
					&& (!rightPipe.getVisitedAlgo()) && !pipeCurrentPosition.getVisitedAlgo()) 
			{
					int numOfBoards = rightPipe.getMaxClicksCount();
					for (int i = 0; i <= numOfBoards ; i++)
					{
						PipeGameBoardState pgbs = new PipeGameBoardState(currentState);
						pgbs.setCameFrom(currentState);
						for (int j = i; j > 0; j--)
						{
							pgbs.clickPipeAlgo(rightPipe.getX(), rightPipe.getY());
						}
						Pipe newPipe =pgbs.pipeGameBoard.getPipeByLocation(rightPipe.getX(), rightPipe.getY()); 
						Pipe newCurrentPipe = pgbs.pipeGameBoard.getCurrentPosition();
						pgbs.setVisitedAlgo(newCurrentPipe.getX(), newCurrentPipe.getY());
						
						if (newCurrentPipe.validRightPipeTypes().contains(newPipe.getPipeType()))
						{
							
							pgbs.pipeGameBoard.setCurrentPositionByLocation(newPipe.getX(), newPipe.getY()); 
						}
						//set cost - goal - current position
						pgbs.setCost(Math.abs(pgbs.pipeGameBoard.getGoalPipe().getX() - 
								pgbs.pipeGameBoard.getCurrentPosition().getX()) + 
								Math.abs(pgbs.pipeGameBoard.getGoalPipe().getY() - 
										pgbs.pipeGameBoard.getCurrentPosition().getY()));
						
						listToReturn.add(pgbs);
						
					}
			}
		}
	}

	private void addLeftPipeChildren(PipeGameBoardState currentState, ArrayList<State> listToReturn, Pipe pipeCurrentPosition)
	{
		if (currentState.pipeGameBoard.isPickingAllowedLeft(pipeCurrentPosition))
		{
			Pipe leftPipe = currentState.pipeGameBoard.getLeftPipe(pipeCurrentPosition);
			
			if((leftPipe.getHLType() == pipeHLType.corner || leftPipe.getHLType() == pipeHLType.goal ||leftPipe.getHLType() == pipeHLType.line) 
					&& (! leftPipe.getVisitedAlgo()) && !pipeCurrentPosition.getVisitedAlgo()) 
			{
					int numOfBoards = leftPipe.getMaxClicksCount();
					for (int i = 0; i <= numOfBoards ; i++)
					{
						PipeGameBoardState pgbs = new PipeGameBoardState(currentState);
						pgbs.setCameFrom(currentState);
						for (int j = i; j >0; j--)
						{
							pgbs.clickPipeAlgo(leftPipe.getX(), leftPipe.getY());
						}
						Pipe newPipe =pgbs.pipeGameBoard.getPipeByLocation(leftPipe.getX(), leftPipe.getY()); 
						Pipe newCurrentPipe = pgbs.pipeGameBoard.getCurrentPosition();
						pgbs.setVisitedAlgo(newCurrentPipe.getX(), newCurrentPipe.getY());
						if (newCurrentPipe.validLeftPipeTypes().contains(newPipe.getPipeType()))
						{
							
							pgbs.pipeGameBoard.setCurrentPositionByLocation(newPipe.getX(), newPipe.getY()); 
						}
						
						//set cost - goal - current position
						pgbs.setCost(Math.abs(pgbs.pipeGameBoard.getGoalPipe().getX() - 
								pgbs.pipeGameBoard.getCurrentPosition().getX()) + 
								Math.abs(pgbs.pipeGameBoard.getGoalPipe().getY() - 
										pgbs.pipeGameBoard.getCurrentPosition().getY()));
						
						listToReturn.add(pgbs);
						
					}
			}
		}
	}

	private void addUpPipeChildren(PipeGameBoardState currentState, ArrayList<State> listToReturn, Pipe pipeCurrentPosition)
	{
		if (currentState.pipeGameBoard.isPickingAllowedUp(pipeCurrentPosition))
		{
			Pipe upPipe = currentState.pipeGameBoard.getUpPipe(pipeCurrentPosition);
			
			if((upPipe.getHLType() == pipeHLType.corner || upPipe.getHLType() == pipeHLType.goal ||upPipe.getHLType() == pipeHLType.line) 
					&& (!upPipe.getVisitedAlgo())&& !pipeCurrentPosition.getVisitedAlgo()) 
			{
					int numOfBoards = upPipe.getMaxClicksCount();
					for (int i = 0; i <= numOfBoards ; i++)
					{
						PipeGameBoardState pgbs = new PipeGameBoardState(currentState);
						pgbs.setCameFrom(currentState);
						for (int j = i; j >0; j--)
						{
							pgbs.clickPipeAlgo(upPipe.getX(), upPipe.getY());
						}
						Pipe newPipe =pgbs.pipeGameBoard.getPipeByLocation(upPipe.getX(), upPipe.getY()); 
						Pipe newCurrentPipe = pgbs.pipeGameBoard.getCurrentPosition();
						pgbs.setVisitedAlgo(newCurrentPipe.getX(), newCurrentPipe.getY());
						if (newCurrentPipe.validUpPipeTypes().contains(newPipe.getPipeType()))
						{
							
							pgbs.pipeGameBoard.setCurrentPositionByLocation(newPipe.getX(), newPipe.getY()); 
						}
						//set cost - goal - current position
						pgbs.setCost(Math.abs(pgbs.pipeGameBoard.getGoalPipe().getX() - 
								pgbs.pipeGameBoard.getCurrentPosition().getX()) + 
								Math.abs(pgbs.pipeGameBoard.getGoalPipe().getY() - 
										pgbs.pipeGameBoard.getCurrentPosition().getY()));
						
						listToReturn.add(pgbs);
						
					}
			}
		}
	}
	
	private void addDownPipeChildren(PipeGameBoardState currentState, ArrayList<State> listToReturn, Pipe pipeCurrentPosition)
	{
		if (currentState.pipeGameBoard.isPickingAllowedDown(pipeCurrentPosition))
		{
			Pipe downPipe = currentState.pipeGameBoard.getDownPipe(pipeCurrentPosition);
			
			if((downPipe.getHLType() == pipeHLType.corner || downPipe.getHLType() == pipeHLType.goal ||downPipe.getHLType() == pipeHLType.line) 
					&& (!downPipe.getVisitedAlgo()) && !pipeCurrentPosition.getVisitedAlgo())  
			{
					int numOfBoards = downPipe.getMaxClicksCount();
					for (int i = 0; i <= numOfBoards ; i++)
					{
						PipeGameBoardState pgbs = new PipeGameBoardState(currentState);
						pgbs.setCameFrom(currentState);
						for (int j = i; j >0; j--)
						{
							pgbs.clickPipeAlgo(downPipe.getX(), downPipe.getY());
						}
						Pipe newPipe =pgbs.pipeGameBoard.getPipeByLocation(downPipe.getX(), downPipe.getY()); 
						Pipe newCurrentPipe = pgbs.pipeGameBoard.getCurrentPosition();
						pgbs.setVisitedAlgo(newCurrentPipe.getX(), newCurrentPipe.getY());
						if (newCurrentPipe.validDownPipeTypes().contains(newPipe.getPipeType()))
						{
							
							pgbs.pipeGameBoard.setCurrentPositionByLocation(newPipe.getX(), newPipe.getY()); 
						}
						//set cost - goal - current position
						pgbs.setCost(Math.abs(pgbs.pipeGameBoard.getGoalPipe().getX() - 
								pgbs.pipeGameBoard.getCurrentPosition().getX()) + 
								Math.abs(pgbs.pipeGameBoard.getGoalPipe().getY() - 
										pgbs.pipeGameBoard.getCurrentPosition().getY()));
						
						listToReturn.add(pgbs);
						
					}
			}
		}
	}
	@Override
	public void setCameFrom(State s) {
		PipeGameBoardState.cameFrom = s;
		
	}

	@Override
	public void setCost(int c) {
		PipeGameBoardState.cost = c;
	}
}