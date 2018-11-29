package server;
import java.util.ArrayList;
import java.util.List;


public class Pipe {
	private PipeTypes.pipeEnum type;
	PipeTypes.pipeHLType hlType;
	private int x;
	private int y;	
	private int clicksCount = 0;
	private int clicksCountAlgo = 0;
	private int maxClicks = 0;
	private Boolean visited = false;
	private Boolean visitedAlgo = false;
	

	public Pipe(char p, int x, int y)  { 
		this.type = setClientCharToPipeType(p);
		this.x = x;
		this.y = y;
	}
	
	public Pipe(char p, int x, int y, int clicksCount)  { 
		this.type = setClientCharToPipeType(p);
		this.x = x;
		this.y = y;
		this.clicksCount = clicksCount;	
	}
	
	public Pipe(Pipe p)
	{
		this.clicksCount = p.clicksCount;
		this.maxClicks = p.maxClicks;
		this.type = p.type;
		this.hlType = p.hlType;
		this.x = p.x;
		this.y = p.y;
		this.visitedAlgo = p.visitedAlgo;
		//this.visited = p.visited;
	}
	
	public int getClicksCount()
	{
		return this.clicksCount;
	}
	
	public int getMaxClicksCount()
	{
		return this.maxClicks;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	
	public int getY()
	{
		return this.y;
	}
	
	public Boolean ClickPipe()
	{
		if (this.clicksCount < this.maxClicks)
		{
			this.clicksCount++;
			chageTypeByClicking();
			return true;
		}
		return false;
	}
	public void ClickPipeAlgo()
	{
		{
			this.clicksCount++;
			chageTypeByClicking();
		}
	}
	
	private void chageTypeByClicking()
	{
		switch (type) {
		case cornerUR:
			this.type = PipeTypes.pipeEnum.cornerDR;
			break;
		case cornerDR:
			this.type = PipeTypes.pipeEnum.cornerDL;
			break;
		case cornerDL:
			this.type = PipeTypes.pipeEnum.cornerUL;
			break;
		case cornerUL:
			this.type = PipeTypes.pipeEnum.cornerUR;
			break;
		case lineSide:
			this.type = PipeTypes.pipeEnum.lineUp;
			break;
		case lineUp:
			this.type = PipeTypes.pipeEnum.lineSide;
			break;
		default:
			break;
		}
	}
	
	@Override
	public String toString()
	{
		return (type.name());
	}

	public PipeTypes.pipeEnum getPipeType()
	{
		return this.type;
	}
	
	public PipeTypes.pipeHLType getHLType()
	{
		return this.hlType;
	}
	
	public void setVisited(Boolean b)
	{
		this.visited = true;
	}

	public Boolean getVisitedAlgo()
	{
		return this.visitedAlgo;
	}
	public void setVisitedAlgo()
	{
		this.visitedAlgo = true;
	}

	public Boolean getVisited()
	{
		return this.visited;
	}
	
	private PipeTypes.pipeEnum setClientCharToPipeType(char s) {
		PipeTypes.pipeEnum type = null;
		switch (s) {
		case '7':
			type = PipeTypes.pipeEnum.cornerUR;
			maxClicks = 3;
			hlType = PipeTypes.pipeHLType.corner;
			break;
		case 'F':
			type = PipeTypes.pipeEnum.cornerUL;
			maxClicks = 3;
			hlType = PipeTypes.pipeHLType.corner;
			break;
		case 'L':
			type = PipeTypes.pipeEnum.cornerDL;
			maxClicks = 3;
			hlType = PipeTypes.pipeHLType.corner;
			break;
		case 'J':
			type = PipeTypes.pipeEnum.cornerDR;
			maxClicks = 3;
			hlType = PipeTypes.pipeHLType.corner;
			break;
		case '-':
			type = PipeTypes.pipeEnum.lineSide;
			maxClicks = 1;
			hlType = PipeTypes.pipeHLType.line;
			break;
		case '|':
			type = PipeTypes.pipeEnum.lineUp;
			maxClicks = 1;
			hlType = PipeTypes.pipeHLType.line;
			break;
		case 's':
			type = PipeTypes.pipeEnum.start;
			maxClicks = 0;
			hlType = PipeTypes.pipeHLType.start;
			break;
		case 'g':
			type = PipeTypes.pipeEnum.goal;
			maxClicks = 0;
			hlType = PipeTypes.pipeHLType.goal;
			break;
		case ' ':
			type = PipeTypes.pipeEnum.empty;
			hlType = PipeTypes.pipeHLType.empty;
			break;
		}
		return type;		
	}
	
	public List<PipeTypes.pipeEnum> validRightPipeTypes()
	{
		List<PipeTypes.pipeEnum> ListToReturn = new ArrayList<>();
		
		if (this.type == PipeTypes.pipeEnum.cornerDL || this.type == PipeTypes.pipeEnum.cornerUL || 
				this.type == PipeTypes.pipeEnum.lineSide || this.type == PipeTypes.pipeEnum.start)
		{
			ListToReturn.add(PipeTypes.pipeEnum.lineSide);
			ListToReturn.add(PipeTypes.pipeEnum.cornerDR);
			ListToReturn.add(PipeTypes.pipeEnum.cornerUR);
			ListToReturn.add(PipeTypes.pipeEnum.goal);
		}

		return ListToReturn;
	}
	
	public List<PipeTypes.pipeEnum> validLeftPipeTypes()
	{
	List<PipeTypes.pipeEnum> ListToReturn = new ArrayList<>();
		
		if (this.type == PipeTypes.pipeEnum.cornerDR || this.type == PipeTypes.pipeEnum.cornerUR || 
				this.type == PipeTypes.pipeEnum.lineSide || this.type == PipeTypes.pipeEnum.start)
		{
			ListToReturn.add(PipeTypes.pipeEnum.lineSide);
			ListToReturn.add(PipeTypes.pipeEnum.cornerUL);
			ListToReturn.add(PipeTypes.pipeEnum.cornerDL);
			ListToReturn.add(PipeTypes.pipeEnum.goal);
		}

		return ListToReturn;
	}
	
	public List<PipeTypes.pipeEnum> validUpPipeTypes()
	{
		List<PipeTypes.pipeEnum> ListToReturn = new ArrayList<>();
		
		if (this.type == PipeTypes.pipeEnum.cornerDR || this.type == PipeTypes.pipeEnum.cornerDL || 
				this.type == PipeTypes.pipeEnum.lineUp || this.type == PipeTypes.pipeEnum.start)
		{
			ListToReturn.add(PipeTypes.pipeEnum.lineUp);
			ListToReturn.add(PipeTypes.pipeEnum.cornerUL);
			ListToReturn.add(PipeTypes.pipeEnum.cornerUR);
			ListToReturn.add(PipeTypes.pipeEnum.goal);
		}
		return ListToReturn;
	}
		
	public List<PipeTypes.pipeEnum> validDownPipeTypes()
	{
		List<PipeTypes.pipeEnum> ListToReturn = new ArrayList<>();
		
		if (this.type == PipeTypes.pipeEnum.cornerUR || this.type == PipeTypes.pipeEnum.cornerUL || 
				this.type == PipeTypes.pipeEnum.lineUp || this.type == PipeTypes.pipeEnum.start)
		{
			ListToReturn.add(PipeTypes.pipeEnum.lineUp);
			ListToReturn.add(PipeTypes.pipeEnum.cornerDL);
			ListToReturn.add(PipeTypes.pipeEnum.cornerDR);
			ListToReturn.add(PipeTypes.pipeEnum.goal);
		}
		return ListToReturn;
	}
}
