import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Central implements Runnable{
	ServerSocket administration;
	Map<String, Boolean> online;
	PrintStream users;
	
	public Central(){
		try {
			users = new PrintStream(new FileOutputStream("users.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		init();
	}
	
	public void init(){
		try {
			online = new HashMap<>();
			administration = new ServerSocket(85);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			BufferedReader read = new BufferedReader(new FileReader("users.txt"));
			String entry = read.readLine();
			while(entry != null){
				online.put(entry, false);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean running = true;
		while(running){
			try {
				Socket ne = administration.accept();
				ObjectInputStream in = new ObjectInputStream(ne.getInputStream());
				boolean noName = true;
				while(noName){
					String name = (String)in.readObject();
					if(name != null){
						noName = false;
						if(!online.containsKey(name))
							users.println(name);
						online.put(name, true);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		Thread Server = new Thread(new Central());
		Server.start();
	}
	
}
