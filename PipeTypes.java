package server;

final class PipeTypes {
	
	private pipeEnum type;
	
	public PipeTypes(pipeEnum p)
	{
		this.type = p;
	}
	public static enum pipeEnum {
		lineUp,
		lineSide,
		cornerUL,
		cornerUR,
		cornerDL,
		cornerDR,
		goal,
		start,
		empty
	};
	
	public static enum pipeHLType {
		line,
		corner,
		goal,
		start,
		empty
	};
	
	
}
