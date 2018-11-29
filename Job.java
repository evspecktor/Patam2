package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
import java.util.ArrayList;
import server.MyCHandler;
import server.PipeGameBoard;

public class Job implements Runnable {
    private JobPriority jobPriority;
    private ClientHandler ch;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int boardSize = 0;
    private PipeGameBoard pgb;
    
//    PipeGameBoard p1 = readInputStramIntoGame(this.inputStream);
    
    public Job(ClientHandler ch,InputStream inFromClient,OutputStream outToClient) throws IOException {
		super();
		this.ch = ch;
		this.inputStream = inFromClient;
		this.outputStream = outToClient;
		this.pgb = readInputStramIntoGame(this.inputStream);
		this.boardSize = this.pgb.getBoardSize();
	}

    public int getBoardSize()
    {
    	return this.boardSize;
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
    

	@Override
    public void run() {
		try {
			System.out.println("game =" + pgb.toString());
			System.out.println("this thread runs:" + Thread.currentThread().toString());
			System.out.println("im in the run");
			ch.handle(this.inputStream, this.outputStream, this.pgb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    // standard setters and getters


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
}
