import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Frame1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ConnectToDB c1 = new ConnectToDB();
		c1.Initialize();
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
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
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//frame.dispose();
				Frame2 f2 = new Frame2();
				f2.NewScreen();
			}
		});
		btnUser.setBounds(144, 100, 164, 29);
		frame.getContentPane().add(btnUser);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Frame3 f3 = new Frame3();
			f3.NewScreen2();
				
			}
		});
		btnNewButton.setBounds(144, 160, 164, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Welcome to UNF Bookstore!");
		lblNewLabel.setBounds(134, 23, 194, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}