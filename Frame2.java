import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Frame2 {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame2 window = new Frame2();
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
	public Frame2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName: ");
		lblNewLabel.setBounds(20, 76, 72, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setBounds(20, 132, 72, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(104, 71, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 127, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Log in");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					frame.dispose();

					login();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		
		btnNewButton.setBounds(20, 196, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewAccountFrame NAF = new NewAccountFrame();
				NAF.newScreen();
			}
		});
		btnNewButton_1.setBounds(291, 226, 130, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Not a user ? Create an account");
		lblNewLabel_2.setBounds(245, 201, 199, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(20, 237, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		
	}
	
	public void login() throws SQLException {
		
		String User = textField.getText();
		@SuppressWarnings("deprecation")
		String Password = passwordField.getText();
		
		ConnectToDB dbConnection = new ConnectToDB();
		
		if(!User.isBlank() && !Password.isBlank()) {
			
			boolean loginStatus = dbConnection.login(User , Password);

			if(loginStatus != false) {
			
			//open a new frame for user account if the info is true

			UserAccountFrame UAF = new UserAccountFrame();
			UAF.NewScreen();
			
		} else {
			
			//if it's false then display an error message
			
			JOptionPane.showMessageDialog(null, "Invalid log-in information" , "Log in Error" , JOptionPane.ERROR_MESSAGE);
			
		}
		
		return;
		}else {
			JOptionPane.showMessageDialog(null, "Empty Input" , "Log in Error" , JOptionPane.ERROR_MESSAGE);

		}
	}
}
