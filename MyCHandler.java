package server;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MyCHandler implements ClientHandler {
	
	CacheManager cm;
	Solver s;
	
	public MyCHandler() {
		cm = new ToFile();
	}

	
	@Override
	//handle gets the input from the MyServer, than check the cache for solution and if not exists ask the solver 
	public void handle(InputStream inFromClient, OutputStream outToClient, PipeGameBoard p) throws IOException {
		//convert the input into game	
		System.out.println("");
		System.out.println("----------Handle----------");
		PipeGameBoard pipeGame =  p;
//		PipeGameBoard pipeGame =  readInputStramIntoGame(inFromClient);
		PrintWriter out=new PrintWriter(outToClient);
		
		if (pipeGame != null)
		{
			PipeGameBoardState pipeGameState = new PipeGameBoardState(pipeGame);
			ArrayList<String> solution = new ArrayList<>();
			
			//check if game exits in cache 
			String result = load(pipeGameState.state);
			
			//if not exists in cache
			if (result == " ")
			{
				System.out.println("");
				System.out.println("---Solve Me---");
				solution = solve(pipeGameState);
				if (solution != null) {
				//save to cache
					String solutionForCache = "";
					for (String s : solution)
					{
						solutionForCache += s;
						solutionForCache += " ";
					}	
					save(pipeGameState.state, solutionForCache);
				}
			}
			else
			{
				//load from cache
				String[] stringFromCache = result.split(" ");
				for (String s : stringFromCache)
				{
					solution.add(s);
				}	
			}

			//send the solution to the client
			System.out.println("---Send solution to client---");
			if (solution != null)
			{
				for (String s: solution) {
					out.println(s);
					System.out.println("solution " + s);
					
				}
			}
			
		}
		out.println("done");
		out.flush();
		
	}

	
	private PipeGameBoard readInputStramIntoGame(InputStream inFromClient) throws IOException
	{
		BufferedReader reader=new BufferedReader(new InputStreamReader(inFromClient));
		
		PipeGameBoard game = null;
		Pipe[][] pipes2dArray = setPipesIntoArray(reader); 
		if (pipes2dArray != null)
		{
			
			game = new PipeGameBoard(pipes2dArray);
			
		}
				
		return game;
	}
	
	
	public Pipe[][] setPipesIntoArray(BufferedReader reader) {
		System.out.println("---set Pipes Into Array---");
		ArrayList<String> listOfString = new ArrayList<String>();
		int numOfLines = 0;			
		
		try {
			String line = null;
			while (( line = reader.readLine()) != null)
			{
					
				numOfLines++;
				System.out.println("Filling the list of string");
				listOfString.add(line);
				if (line.equals("done")) {break;}
			}	 
		} catch (IOException e) {
			System.out.println("exception?");
			e.printStackTrace();
			
		}
		System.out.println("!");
		Pipe[][] pipes = null;
		int lengthOfLine = 0;
		if (listOfString != null)
		{
			if (listOfString.isEmpty())
			{
				System.out.println("client sent empty game");
			}
			else
			{
				lengthOfLine = listOfString.get(0).length();
				System.out.println("New array size is: x is :" + lengthOfLine + ", y is: " + (numOfLines-1)); //numOfLines-1 since done is also a line
				if (lengthOfLine > 0 && numOfLines-1 > 0)
				{
					pipes = new Pipe[lengthOfLine][numOfLines-1];
					listOfString.remove(listOfString.size()-1);
					int lineNumber = 0;
					for (String line:listOfString) {
						for ( int i = 0; i < line.length(); i++)
						{
							int x = i; 
							int y = lineNumber;
							char p = line.charAt(i);
							Pipe pipe = new Pipe(p, x,y);
							System.out.println("X : " + x + ", Y : " + y + ", Pipe type: " + p);
							pipes[x][y] = pipe;
						}
						lineNumber++;
					}
				
				}
					System.out.println("---set Pipes Into Array Done---");
			
			}
		}
		return pipes;
	}

	
	public Boolean save(String problemString, String solution) throws IOException {
		return (cm.save(problemString, solution));
	}


	public ArrayList<String> solve(PipeGameBoardState problem) {
		PipeGameBoardSolver pgbs = new PipeGameBoardSolver();
		
		return (pgbs.solve(problem));	
	}

	
	public String load(String problemString) throws IOException {
		return (cm.load(problemString));
	}

	

}
