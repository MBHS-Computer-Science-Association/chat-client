import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class ClientWindow {

	private JFrame frame;
	private JTextField textField;
	private JButton btnSend;
	private JSeparator separator;
	private JTextArea draftMessage;
	private JLabel newMessage;

	/**
	 * Launch the application.
	 */
	public static void openClient() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	//set JLabel to display single message
	public void displayMessage(String message) {
		newMessage.setText(message);
	}

	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);

		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(200, 6, 12, 655);
		frame.getContentPane().add(separator);

		draftMessage = new JTextArea();
		draftMessage.setBounds(580, 560, 350, 80);
		frame.getContentPane().add(draftMessage);

		btnSend = new JButton("Send");
		btnSend.setBounds(942, 585, 75, 29);
		frame.getContentPane().add(btnSend);

		newMessage = new JLabel("");
		newMessage.setBounds(580, 430, 350, 118);
		frame.getContentPane().add(newMessage);
	}
}
