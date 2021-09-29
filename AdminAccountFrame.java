import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminAccountFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAccountFrame window = new AdminAccountFrame();
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
	public AdminAccountFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 467, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Locate title");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocateTitleFrame LTF = null;
				try {
					LTF = new LocateTitleFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LTF.newScreen();
			}
		});
		btnNewButton.setBounds(158, 30, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update Inventory");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateInventoryFrame UIF = null;
				try {
					frame.dispose();

					UIF = new UpdateInventoryFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				UIF.newScreen();
			}
		});
		btnNewButton_1.setBounds(158, 82, 136, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Check user balance");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				CheckUserBalanceFrame CUBF = new CheckUserBalanceFrame();
				CUBF.newScreen();
			}
		});
		btnNewButton_2.setBounds(136, 134, 166, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Generate reports");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				GenerateReportsFrame GRF = new GenerateReportsFrame();
				GRF.newScreen();
			}
		});
		btnNewButton_3.setBounds(158, 188, 136, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Go back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Frame3 f3 = new Frame3();
				f3.NewScreen2();
			}
		});
		btnNewButton_4.setBounds(168, 237, 117, 29);
		frame.getContentPane().add(btnNewButton_4);
	}
}
