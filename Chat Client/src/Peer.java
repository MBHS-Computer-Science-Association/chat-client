import java.net.InetAddress;

public class Peer {
	public final InetAddress address;
	public final int port;
	
	public Peer(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public boolean equals(Object o) {
		Peer peer;
		try {
			peer = (Peer) o;
		} catch (ClassCastException e) {
			return false;
		}
		return this.address.getHostAddress().equals(peer.address.getHostAddress()) && 
				this.port == peer.port;
	}
}
