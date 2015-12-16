import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

// DON'T USE FOR NOW

public class ClientSideServer implements Runnable {
	private Client parentClient;
	private DatagramSocket socket;
	private int port;
	private boolean serverRunning;
	
	public ClientSideServer(Client client) {
		this(client, 85);
		
	}
	
	public ClientSideServer(Client client, int port) {
		this.parentClient = client;
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		new Thread(this, "Server Thread").start();
	}
	
	public void run() {
		serverRunning = true;
		
		new Thread("Receive Packets") {
			public void run() {
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				try {
					socket.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Message message = null;
				try {
					message = Message.deserialize(packet.getData());
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		while(serverRunning) {
			
		}
	}
}
