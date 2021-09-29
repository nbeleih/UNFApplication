import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class NewAccountFrame {

	private JFrame frame;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	private JTextField textFieldFname;
	private JTextField textFieldLname;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNum;
	private JTextField textFieldStreet;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JTextField textFieldZip;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccountFrame window = new NewAccountFrame();
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
	public NewAccountFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign up for free!");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 18));
		lblNewLabel.setBounds(281, 6, 155, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter a UserName:");
		lblNewLabel_1.setBounds(379, 130, 126, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(517, 125, 130, 26);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter a Password:");
		lblNewLabel_2.setBounds(379, 203, 126, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(517, 198, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 55, 338, 372);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("First Name:");
		lblNewLabel_3.setBounds(6, 27, 77, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name:");
		lblNewLabel_4.setBounds(6, 70, 77, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setBounds(6, 112, 77, 16);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Phone Number:");
		lblNewLabel_6.setBounds(6, 154, 106, 16);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Street:");
		lblNewLabel_7.setBounds(6, 200, 61, 16);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("City:");
		lblNewLabel_8.setBounds(6, 238, 61, 16);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("State: ");
		lblNewLabel_9.setBounds(6, 272, 48, 16);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("ZIP: ");
		lblNewLabel_10.setBounds(6, 309, 40, 16);
		panel.add(lblNewLabel_10);
		
		textFieldFname = new JTextField();
		textFieldFname.setBounds(95, 22, 130, 26);
		panel.add(textFieldFname);
		textFieldFname.setColumns(10);
		
		textFieldLname = new JTextField();
		textFieldLname.setBounds(95, 65, 130, 26);
		panel.add(textFieldLname);
		textFieldLname.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(95, 107, 130, 26);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldPhoneNum = new JTextField();
		textFieldPhoneNum.setBounds(105, 149, 130, 26);
		panel.add(textFieldPhoneNum);
		textFieldPhoneNum.setColumns(10);
		
		textFieldStreet = new JTextField();
		textFieldStreet.setBounds(95, 195, 130, 26);
		panel.add(textFieldStreet);
		textFieldStreet.setColumns(10);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(95, 233, 130, 26);
		panel.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		textFieldState = new JTextField();
		textFieldState.setBounds(95, 267, 130, 26);
		panel.add(textFieldState);
		textFieldState.setColumns(10);
		
		textFieldZip = new JTextField();
		textFieldZip.setBounds(95, 304, 130, 26);
		panel.add(textFieldZip);
		textFieldZip.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(375, 55, 344, 372);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Create account now");
		btnNewButton.setBounds(170, 337, 168, 29);
		panel_1.add(btnNewButton);
		

		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserAccountFrame UAFback = new UserAccountFrame();
				UAFback.NewScreen();
			}
		});
		btnNewButton_1.setBounds(6, 337, 135, 29);
		panel_1.add(btnNewButton_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					register();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void register() throws SQLException {
		
		ConnectToDB dbConnection = new ConnectToDB();
		String User = textFieldUserName.getText();
		@SuppressWarnings("deprecation")
		String Password = passwordField.getText();
		String fName = textFieldFname.getText();
		String lName = textFieldLname.getText();
		String Email = textFieldEmail.getText();
		String PhoneNum = textFieldPhoneNum.getText();
		String Street = textFieldStreet.getText();
		String City = textFieldCity.getText();
		String State = textFieldState.getText();
		String ZIP = textFieldZip.getText();



		
		boolean registerStatus = dbConnection.Register(User, Password, fName, lName, Email, PhoneNum, Street, City, State, ZIP);
		
		if(registerStatus != false) {
			
			JOptionPane.showMessageDialog(null, "Account has been successfully created");
			Frame2 f2 = new Frame2();
			f2.NewScreen();
		}
		else {
			
			JOptionPane.showMessageDialog(null, "Error. An account with the same Username exists" , "Username exits", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
