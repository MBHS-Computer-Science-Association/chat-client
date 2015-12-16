import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton btnSignIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(103, 128, 62, 16);
		frame.getContentPane().add(lblUsername);

		textField = new JTextField();
		textField.setBounds(177, 122, 150, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(106, 158, 59, 16);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(177, 152, 150, 28);
		frame.getContentPane().add(passwordField);

		btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
//				ClientWindow.openClient();
				new ClientWindow();
			}
		});
		btnSignIn.setBounds(210, 192, 117, 29);
		frame.getContentPane().add(btnSignIn);
	}
}
