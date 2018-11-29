package server;
import java.io.IOException;

public interface CacheManager {
	
	Boolean save(String problemString, String solution) throws IOException;
	
	String load(String problemString) throws IOException;

}
