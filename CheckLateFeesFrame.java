import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CheckLateFeesFrame {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckLateFeesFrame window = new CheckLateFeesFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public CheckLateFeesFrame() throws SQLException {
		initialize();
		checkLateFees();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(144, 24, 428, 466);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 416, 454);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Pay Late fees");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConnectToDB dbConn = new ConnectToDB();
				try {
					dbConn.payLateFees();
					JOptionPane.showMessageDialog (null, "You've successfully paid your outstanding balance!", "Outstanding balance clear", JOptionPane.INFORMATION_MESSAGE);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(6, 205, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				UserAccountFrame UAF = new UserAccountFrame();
				UAF.NewScreen();
			}
		});
		btnNewButton_1.setBounds(6, 353, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public void checkLateFees() throws SQLException {
		
		ConnectToDB dbConn = new ConnectToDB();
		dbConn.checkLateFees(table);
	}
}
