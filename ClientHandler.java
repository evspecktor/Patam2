package server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface ClientHandler {
	
	void handle(InputStream inFromClient,OutputStream outToClient, PipeGameBoard p) throws IOException;
}
