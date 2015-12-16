import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

public class Client {
	private boolean isActive;
	private Queue<Message> messageQueue;
	// UDP Socket - DatagramSocket
	private DatagramSocket socket;
	private InetAddress inetAddress;

	private ClientWindow window;
<<<<<<< HEAD
	private boolean isRunning = true;
=======
>>>>>>> 96a209f4f055b2f9e526318805a35b2d6b533fa7

	public Client() {
		messageQueue = new LinkedList<>();
		//
		// messageQueue = new PriorityQueue<>();
		window = new ClientWindow(this);
		window.openClient();
		window.displayMessage(new Message("Hello"));
	}

<<<<<<< HEAD
	public void openConnection() {
		try {
			socket = new DatagramSocket();
			// change to actual IP address
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		System.out.println(inetAddress.getHostAddress());
	}

=======
>>>>>>> 96a209f4f055b2f9e526318805a35b2d6b533fa7
	public void send(Message message) {
		final byte[] data;
		try {
			data = Message.serialize(message);
			new Thread("Sending Message") {
				public void run() {
					DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, 85);
					try {
						socket.send(packet);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DatagramPacket receivePacket() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);

		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return packet;
	}

<<<<<<< HEAD
	public void displayMessages() {
		new Thread("Display Messages") {
=======
	public void activateMessages() {
		displayThread = new Thread() {
>>>>>>> 96a209f4f055b2f9e526318805a35b2d6b533fa7
			public void run() {
				while (isRunning) {
					if (!window.isActive())
						continue;

					Message message;
					if ((message = messageQueue.poll()) != null) {
						window.displayMessage(message);
					}
				}
			}
		}.start();
	}

<<<<<<< HEAD
	public void listen() {
		new Thread("Listen") {
			public void run() {
				while (isRunning) {
					DatagramPacket packet = receivePacket();
					try {
						messageQueue.add((Message) Message.deserialize(packet.getData()));
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
=======
	public void deactivateMessages() {
		displayThread.interrupt();
>>>>>>> 96a209f4f055b2f9e526318805a35b2d6b533fa7
	}

	public Queue<Message> getMessageQueue() {
		return messageQueue;
	}

	public static void main(String[] args) {
		Client testClient = new Client();
	}
}
