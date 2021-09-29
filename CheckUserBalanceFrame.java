import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckUserBalanceFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckUserBalanceFrame window = new CheckUserBalanceFrame();
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
	public CheckUserBalanceFrame() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 824, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(17, 118, 788, 338);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 776, 326);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Customer ID:");
		lblNewLabel.setBounds(35, 54, 90, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(137, 49, 66, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					CheckingUserBalance();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					//JOptionPane.showMessageDialog (null, "Invalid format or customer doesn't exist!", "Search by Customer ID", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		btnNewButton.setBounds(243, 49, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				AdminAccountFrame AAF = new AdminAccountFrame();
				AAF.newScreen();
			}
		});
		btnNewButton_1.setBounds(649, 49, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public void CheckingUserBalance() throws SQLException {
		
		ConnectToDB dbConn = new ConnectToDB();
		String CusID = textField.getText();
		try { 
		
			Integer.parseInt(CusID);

		}catch(NumberFormatException e) {
			
			JOptionPane.showMessageDialog (null, "Invalid format or customer doesn't exist!", "Search by Customer ID", JOptionPane.INFORMATION_MESSAGE);
		
		}
		dbConn.CheckingUserBalance(CusID , table);
	}
}
