package server;

public interface Server {
	void start(ClientHandler ch);
	void stop();
}
