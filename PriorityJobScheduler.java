package server;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import server.JobComperator;

public class PriorityJobScheduler{
	
    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler 
      = Executors.newSingleThreadExecutor();
    //private PriorityBlockingQueue<Job> priorityQueue;
    private PriorityBlockingQueue<Job> priorityQueue;
    
    JobComperator jc = new JobComperator();
    
 
    public PriorityJobScheduler(Integer poolSize, Integer queueSize) {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<Job>(queueSize, jc);
        System.out.println("!!priorityQueue size = " + priorityQueue.size());
        priorityJobScheduler.execute(() -> {
            while (true) {
            	if (priorityQueue.size() != 0)
            	{
            		try {
            			Thread.sleep(1500);
						priorityJobPoolExecutor.execute(priorityQueue.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		System.out.println("running thread");
            	}
				
            }
        });
    }
 
    public void scheduleJob(Job job) {
        System.out.println("queue size before" + priorityQueue.size());
    	priorityQueue.add(job);
    	System.out.println("queue size after" + priorityQueue.size());
    }
}