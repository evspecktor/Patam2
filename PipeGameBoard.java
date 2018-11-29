package server;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class PipeGameBoard {
		
	private List<Pipe> PipesList = new ArrayList<>();
	private Pipe[][] Pipes; 
	private int x;
	private int y;
	private Pipe startPipe;
	private Pipe goalPipe;
	private Pipe currentPosition;
	
	

	public PipeGameBoard(PipeGameBoard PGB)
	{
		PipesList = CopyPipesList(PGB.PipesList);
		Pipes = CopyPipesArrayandSetStartAndGoalPipe(PGB.Pipes, PGB.x,PGB.y);
		x = PGB.x;
		y = PGB.y;
		this.setCurrentPositionByLocation(PGB.getCurrentPosition().getX(), PGB.getCurrentPosition().getY());
		goalPipe = PGB.goalPipe;
	}
	
	public PipeGameBoard(Pipe[][] pipes) {
		this.x = pipes.length;
		this.y = pipes[0].length;
		this.Pipes = CopyPipesArrayandSetStartAndGoalPipe(pipes,this.x, this.y);
		this.PipesList = setListPipeFrom2DArray(this.Pipes, this.x, this.y);
		System.out.println("");
		currentPosition = startPipe;
	}
	
	public void setStartPipe(Pipe p) {
		this.startPipe = p;
	}
	public void setGoalPipe(Pipe p)
	{
		this.goalPipe = p;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getBoardSize()
	{
		return getX()*getY();
	}
	public Pipe getGoalPipe()
	{
		return this.goalPipe;
	}
	
	public Pipe getPipeByLocation (int x, int y)
	{
		return Pipes[x][y];
	}
	public Pipe getUpPipe(Pipe p)
	{
		return (Pipes[p.getX()][p.getY()-1]);
	}
	
	public Pipe getDownPipe(Pipe p)
	{
		return Pipes[p.getX()][p.getY()+1];
	}
	public Pipe getRightPipe(Pipe p)
	{
		return Pipes[p.getX()+1][p.getY()];
	}
	public Pipe getLeftPipe(Pipe p)
	{
		return Pipes[p.getX()-1][p.getY()];
	}
	
	public void setCurrentPositionByLocation(int x, int y)
	{
		this.currentPosition = Pipes[x][y];
	}
	
	public void setVisitedAlgo(int x, int y)
	{
		this.Pipes[x][y].setVisitedAlgo(); 
	}
	
	public Pipe getCurrentPosition()
	{
		return this.currentPosition;
	}

	public Pipe[][] getPipesArray()
	{
		return this.Pipes;
	}
	
	public ArrayList<String> locationAndClicksCount()
	{
		ArrayList<String> solution = new ArrayList<>();
		for (int j = 0; j < this.y; j++)
			for(int i = 0; i < this.x ; i++)
			{
				if (Pipes[i][j].getClicksCount() > 0)
				{
					String str = Pipes[i][j].getY() + "," + Pipes[i][j].getX() + "," + Pipes[i][j].getClicksCount();
					
					solution.add(str);
				}
			}
		return solution;
	}
		
	public int getNumberOfPipes()
	{
		return PipesList.size();
	}
	
	public void removePipeFromPipesList(int i)
	{
		PipesList.remove(i);
	}
	
	public Boolean clickPipe(int x, int y)
	{
		return Pipes[x][y].ClickPipe();
	}
	
	public void clickPipeAlgo(int x, int y)
	{
		Pipes[x][y].ClickPipeAlgo();
	}
	
	public Pipe getPipeFromPipesList(int i)
	{
		Pipe p = new Pipe(PipesList.get(i));
		return p;
	}
	
	public Boolean isGoal()
	{
		if (currentPosition.getPipeType() == PipeTypes.pipeEnum.goal)
			{
			System.out.println("---Is Gaol? True---");
			return true;
			}
		return false;
		
		
		//Pipe currentPipe = getSatrtPipe();
		//Boolean isBlocked = false;
		//int startCounter = 1;
		
	}
		
//		while (currentPipe.getPipeType() != PipeTypes.pipeEnum.goal && (isBlocked == false) && (startCounter <= 4))
//		{ 
//			System.out.println("---2 isGaol ---");
//			if (isPickingAllowedUp(currentPipe))
//			{
//				Pipe upPipe = Pipes[currentPipe.getX()][currentPipe.getY()-1];
//				System.out.println("---3 isGaol before move---");
//				if (currentPipe.validUpPipeTypes().contains(upPipe.getPipeType()) && !upPipe.getVisited())
//				{
//					
//					//move 
//					currentPipe = upPipe;
//					currentPipe.setVisited(true);
//					isBlocked = false;
//					continue;
//				}
//				else
//				{
//					isBlocked = true;
//				}
//					
//			}
//			if (isPickingAllowedDown(currentPipe))
//			{
//				Pipe downPipe = Pipes[currentPipe.getX()][currentPipe.getY()+1];
//				if (currentPipe.validDownPipeTypes().contains(downPipe.getPipeType()) && !downPipe.getVisited())
//				{
//					//move
//					currentPipe = downPipe;
//					currentPipe.setVisited(true);
//					isBlocked = false;
//					continue;
//				}
//				else
//				{
//					isBlocked = true;
//				}
//			}
//			if (isPickingAllowedRight(currentPipe))
//			{
//				Pipe rightPipe = Pipes[currentPipe.getX()+1][currentPipe.getY()];
//				if (currentPipe.validRightPipeTypes().contains(rightPipe.getPipeType()) && !rightPipe.getVisited())
//				{
//					//move
//					currentPipe = rightPipe;
//					currentPipe.setVisited(true);
//					isBlocked = false;
//					continue;
//				}
//				else
//				{
//					isBlocked = true;
//				}
//			}
//			if (isPickingAllowedLeft(currentPipe))
//			{
//				Pipe leftPipe = Pipes[currentPipe.getX()-1][currentPipe.getY()];
//				if (currentPipe.validLeftPipeTypes().contains(leftPipe.getPipeType()) && !leftPipe.getVisited())
//				{
//					//move
//					currentPipe = leftPipe;
//					currentPipe.setVisited(true);
//					isBlocked = false;
//					continue;
//				}
//				else
//				{
//					isBlocked = true;
//				}
//			}
//			if (isBlocked == true)
//			{
//				currentPipe = getSatrtPipe();
//				startCounter++;
//			}
//		}
//			
//		if (currentPipe.getPipeType() == PipeTypes.pipeEnum.goal)
//		{
//			return true;
//		}
//		else
//			return false;
//	}
//	
//	private Pipe getSatrtPipe()
//	{
//		if (this.startPipe == null)
//			System.out.println("start pipe wasnt found..will exit");
//		return this.startPipe;
//	}
//	
	public Boolean isPickingAllowedUp(Pipe p)
	{
		if (p.getY() == 0)
			return false;
		return true;		
	}
	public Boolean isPickingAllowedDown(Pipe p)
	{
		if (p.getY() == y-1)
			return false;
		return true;		
	}
	public Boolean isPickingAllowedLeft(Pipe p)
	{
		if (p.getX() == 0)
			return false;
		return true;		
	}
	public Boolean isPickingAllowedRight(Pipe p)
	{
		if (p.getX() == x-1)
			return false;
		return true;		
	}
	
	@Override
	public String toString()
	{
		String strtoReturn = "";
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				strtoReturn += Pipes[j][i].toString();
			}
			strtoReturn += "/n";
		}
		return strtoReturn;
	}
	
	//the string which will represent the state
	public String toStringStateID()
	{
		String strtoReturn = "";
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				strtoReturn += Pipes[j][i].toString();
			}
		}
		return strtoReturn;
	}

	//gameToString returns a unified string for all Games level, by the HLtype & place  
	public String SpecificGameToStringGeneralGame()
	{
		String strtoReturn = "";
		for (int i = 0; i < this.y; i++)
		{
			for (int j = 0; j < this.x; j++)
			{
				strtoReturn += Pipes[j][i].getHLType().name();
			}
			strtoReturn += "\n";
		}
		System.out.println(strtoReturn);
		return strtoReturn;
	}

	private List<Pipe> setListPipeFrom2DArray(Pipe[][] pipes, int x, int y)
	{
		List<Pipe> newList = new ArrayList<>();
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				PipeTypes.pipeHLType type = pipes[j][i].getHLType();
				if ( type.equals(PipeTypes.pipeHLType.corner) || type.equals(PipeTypes.pipeHLType.line))
					newList.add(pipes[j][i]);
			}
		}
		return newList;
	}
	
	private Pipe[][] CopyPipesArrayandSetStartAndGoalPipe(Pipe[][] pipes, int x, int y)
	{
		Pipe[][] newPipeArr = new Pipe[x][y];
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				newPipeArr[j][i] = new Pipe(pipes[j][i]);
				if (newPipeArr[j][i].getPipeType() == PipeTypes.pipeEnum.start) {
					setStartPipe(newPipeArr[j][i]);
				}
				if (newPipeArr[j][i].getPipeType() == PipeTypes.pipeEnum.goal) {
					setGoalPipe(newPipeArr[j][i]);
				}
			}
		}
		return newPipeArr;
	}
	
	private List<Pipe> CopyPipesList(List<Pipe> lPipes)
	{
		List<Pipe> newList = new ArrayList<>(lPipes);
		return newList;
	}

}
