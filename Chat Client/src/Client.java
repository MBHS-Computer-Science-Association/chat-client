import java.util.PriorityQueue;
import java.util.Queue;

public class Client {
	private boolean isActive;
	private Queue<Message> messageQueue;
	private Thread displayThread;
	private ClientWindow window;
	
	public Client() {
		messageQueue = new PriorityQueue<>();
	}
	
	public void activateMessages() {
		displayThread  = new Thread() {
			public void run() {
				while (!messageQueue.isEmpty()) {
					Message message;
					if ((message = messageQueue.poll()) != null) {
//						window.display(message);
					}
				}
			}
		};
		displayThread.start();
	}
	
	public void inactivateMessages() {
		displayThread.interrupt();
	}
}
