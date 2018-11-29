package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import server.MyCHandler;
public class MyServer implements Server {
	
	private int port;
	private ClientHandler ch;
	private volatile boolean stop;
	
	public MyServer(int port) {
		this.port=port;
		stop=false;
		ch = new MyCHandler();
		}
	
	
	@Override
//	public void start(ClientHandler ch) {
//		System.out.println("start***");
//		System.out.println("runServer()");
//		try {
//			runServer();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		
	public void start(ClientHandler ch) {
		new Thread(()->{
			
		try {
			System.out.println("----------Run Server----------");
			runServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}).start();
}
	
	
	@Override
	public void stop() {
		stop=true;
	}
	
	private void runServer() throws Exception {
		System.out.println("inside run srver");
		ServerSocket server=new ServerSocket(port);
		System.out.println("1");
		server.setSoTimeout(1000000000);
		System.out.println("2");
		int poolSize = 6;
		int queueSize = 3;
		PriorityJobScheduler pjs = new PriorityJobScheduler(poolSize, queueSize);
		System.out.println("3");
		System.out.println("after PriorityJobScheduler");
		System.out.println("stop" + stop);
		while(!stop){
			try{
				System.out.println("4");
				Socket aClient=server.accept();// blocking call
				System.out.println("5");				
				Job myJob = new Job(ch,aClient.getInputStream(),aClient.getOutputStream());				
				System.out.println("6");
				pjs.scheduleJob(myJob);
				System.out.println("7");

		}	
			catch(IOException e) {e.printStackTrace();}
			System.out.println("8");
		}
		System.out.println("9");
		server.close();
	}
}
	
//	private void runServer() throws Exception {
//		System.out.println("inside run srver");
//		ServerSocket server=new ServerSocket(port);
//		System.out.println("ServerSocket");
//		server.setSoTimeout(1000000000);
//		//priority schedueler
//		int poolSize = 1;
//		int queueSize = 10;
//		System.out.println("before PriorityJobScheduler");
//		PriorityJobScheduler pjs = new PriorityJobScheduler(poolSize, queueSize);
//		System.out.println("after PriorityJobScheduler");
//		while(!stop){
//			try{
//				Socket aClient=server.accept();// blocking call
//				System.out.println("server accept");
//				Job myJob = new Job(ch,aClient.getInputStream(),aClient.getOutputStream());				
//				System.out.println("!!before schedule job!!");
//				pjs.scheduleJob(myJob);
////				try {
//////					ch.handle(aClient.getInputStream(), aClient.getOutputStream());
////					
////				} catch (IOException e) {e.printStackTrace();}
//			}catch(IOException e) {e.printStackTrace();}
//		}
//		server.close();
//	}
//}

