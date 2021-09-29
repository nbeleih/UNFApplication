import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Frame3 {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public void NewScreen2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame3 window = new Frame3();
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
	public Frame3() {
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
		
		JLabel lblNewLabel = new JLabel("Admin Name:");
		lblNewLabel.setBounds(19, 76, 124, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(50, 123, 73, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(128, 77, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					frame.dispose();

					login();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(6, 216, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 124, 120, 26);
		frame.getContentPane().add(passwordField);
		
		btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnNewButton_1.setBounds(307, 216, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public void login() throws SQLException {
		
		String Admin = textField.getText();
		@SuppressWarnings("deprecation")
		String Password = passwordField.getText();
		
		ConnectToDB dbConn = new ConnectToDB();
		
		if(!Admin.isBlank() && !Password.isBlank()) {

		boolean loginStatus = dbConn.loginAdmin(Admin, Password);
		
		if(loginStatus != false) {
			
			AdminAccountFrame AAF = new AdminAccountFrame();
			AAF.newScreen();
			
		}else {
			
			JOptionPane.showMessageDialog(null, "Error. Invalid Admin name or Password" ," Invaild Input" , JOptionPane.ERROR_MESSAGE);
		}
		}else {
			
			JOptionPane.showMessageDialog(null, "Error. Empty input" ," Invaild Input" , JOptionPane.ERROR_MESSAGE);

		}
	}

}
