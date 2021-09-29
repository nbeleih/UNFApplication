import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserAccountFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAccountFrame window = new UserAccountFrame();
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
	public UserAccountFrame() {
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
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel.setBounds(182, 6, 112, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserSearchFrame USF = null;
				
					try {
						USF = new UserSearchFrame();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				USF.newScreen();
			}
		});
		btnNewButton.setBounds(165, 55, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Checkout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CheckoutFrame CF = null;
				try {
					CF = new CheckoutFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CF.newScreen();
			}
		});
		btnNewButton_1.setBounds(165, 103, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame2 f2 = new Frame2();
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?" , "Exit" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);;
				
				if(result == JOptionPane.YES_OPTION) {
					f2.NewScreen();

				}
				else  {
					
				}
			}
		});
		btnNewButton_2.setBounds(165, 224, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Check/pay balance, and late fees");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				CheckLateFeesFrame CLFF = null;
				try {
					CLFF = new CheckLateFeesFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CLFF.newScreen();
			}
		});
		btnNewButton_3.setBounds(106, 144, 238, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Return Book");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				ReturnBookFrame RBF = null;
				try {
					RBF = new ReturnBookFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				RBF.newScreen();
			}
		});
		btnNewButton_4.setBounds(165, 185, 117, 29);
		frame.getContentPane().add(btnNewButton_4);
	}

}
