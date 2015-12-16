import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ClientWindow {

	private Client parentClient;

	private JFrame frame;
	private JButton btnSend;
	private JTextField draftMessage;
	
	private WindowListener windowListener;
	private boolean isActive;
	private boolean isReady;

	private JTextField displayMessage;

	/**
	 * Create the application.
	 */
	public ClientWindow(Client client) {
		isReady = false;
		parentClient = client;
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public ClientWindow openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ClientWindow window = new ClientWindow();
					// window.frame.setVisible(true);
					frame.setVisible(true);
					isReady = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return this;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void send() {
		parentClient.send(new Message(draftMessage.getText()));
		draftMessage.setText("");
	}

	// set JLabel to display single message
	public void displayMessage(Message message) {
		System.out.println("Message is being displayed...");
		isReady = false;
		Timer timer = new Timer();
		displayMessage.setText(message.receiveMessage());

		TimerTask task = new TimerTask() {
			public void run() {
				displayMessage.setText("");
				isReady = true;
			}
		};

		// delay
		timer.schedule(task, message.getExpiry());
	}

	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame("Chat Client");
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(152, 181, 214));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 25, 275, 25, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 25, 100, 25, 25, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		displayMessage = new JTextField();
		displayMessage.setEditable(false);
		GridBagConstraints gbc_displayMessage = new GridBagConstraints();
		gbc_displayMessage.insets = new Insets(0, 0, 5, 5);
		gbc_displayMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_displayMessage.gridx = 1;
		gbc_displayMessage.gridy = 0;
		frame.getContentPane().add(displayMessage, gbc_displayMessage);
		displayMessage.setColumns(10);

		draftMessage = new JTextField();
		GridBagConstraints gbc_draftMessage = new GridBagConstraints();
		gbc_draftMessage.fill = GridBagConstraints.BOTH;
		gbc_draftMessage.insets = new Insets(0, 0, 5, 5);
		gbc_draftMessage.gridx = 1;
		gbc_draftMessage.gridy = 2;
		draftMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					send();
			}
		});
		frame.getContentPane().add(draftMessage, gbc_draftMessage);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 5;
		frame.getContentPane().add(btnSend, gbc_btnSend);

		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {isActive = true; displayMessage.setText("");}
			public void windowDeactivated(WindowEvent e) {isActive = false;}
		});
	}
	
	public boolean isActive() {
		if (!isActive) displayMessage.setText("MUST BE ACTIVE TO SEE NEW MESSAGES");
		return isActive;
	}
	
	public boolean isReady() {
		return isReady;
	}
}
