package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ToFile implements CacheManager {
	String fileName = "";
	Map<String, String> HM = new HashMap<String, String>();
	
	public ToFile() {
		fileName = "C:\\Talia\\cache.txt";
	}

	@Override
	public Boolean save(String problemString, String solution) throws IOException {
		//check if exist already
		if (HM.containsKey(problemString) == true)
		{
			System.out.println("");
			System.out.println("-----Cach Manager: solution already exists in cache. nothing new to save-----");
			return false;
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(String.valueOf(problemString));
		writer.write("\t");
		writer.write(solution);
		writer.newLine();
		writer.close();
		HM.put(problemString, solution);
		System.out.println("");
		System.out.println("-----Cach Manager: saving the solution in cache... -----");
		return true;
	}

	@Override
	//load should get the gameToString value, so the hash will be the same for all the levels of the game
	public String load(String problemString) throws IOException {		
		String solution = " ";
		String line = "";
		
		if (HM.containsKey(problemString) == false)
		{
			System.out.println("-----Cache Manager: solution doesn't exist in cache, nothing to load-----");
			return solution;
		}
		BufferedReader in=new BufferedReader(new FileReader(fileName));
		while((line=in.readLine())!=null)
		{
			String[] arr = line.split("\t");
			if(arr[0].equals(problemString))
			{
				solution = arr[1];
			}
		}
		in.close();

		System.out.println("-----Cache Manager: solution exists in cache, loading-----");
		return solution;		
	}
}
