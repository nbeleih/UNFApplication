import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GenerateReportsFrame {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReportsFrame window = new GenerateReportsFrame();
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
	public GenerateReportsFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 834, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Title:");
		lblNewLabel.setBounds(19, 89, 77, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(94, 84, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					generateRevenuePerBook();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(236, 84, 82, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Yearly Revenue");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					generateRevenueYearly();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(19, 176, 142, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Monthly Revenue");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					generateRevenueMonthly();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(19, 258, 142, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Weekly Revenue");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					generateRevenueWeekly();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(19, 336, 142, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Go back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				AdminAccountFrame AAF = new AdminAccountFrame();
				AAF.newScreen();
			}
		});
		btnNewButton_4.setBounds(19, 410, 142, 29);
		frame.getContentPane().add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		panel.setBounds(349, 25, 468, 487);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 456, 475);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
public void generateRevenuePerBook() throws SQLException {
	
	String BookTitle = textField.getText();
	
	if(BookTitle != null && !BookTitle.isEmpty()) {
		
		ConnectToDB dbConn = new ConnectToDB();
		dbConn.generateRevenuePerBook(table, BookTitle);
	}else {
		JOptionPane.showMessageDialog (null, "Invalid Input", "Error", JOptionPane.INFORMATION_MESSAGE);

	}
}
public void generateRevenueMonthly() throws SQLException {
	
	ConnectToDB dbConn = new ConnectToDB();
	dbConn.generateRevenueMonthly(table);
	
}
public void generateRevenueYearly() throws SQLException {
	
	ConnectToDB dbConn = new ConnectToDB();
	dbConn.generateRevenueYearly(table);
	
}
public void generateRevenueWeekly() throws SQLException {
	
	ConnectToDB dbConn = new ConnectToDB();
	dbConn.generateRevenueWeekly(table);
}
}
