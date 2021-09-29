import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CheckoutFrame {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutFrame window = new CheckoutFrame();
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
	public CheckoutFrame() throws SQLException {
		initialize();
		displayingCart();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 795, 348);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 103, 762, 200);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 750, 188);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Pay now");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConnectToDB dbConn = new ConnectToDB();
				try {
					dbConn.payNow();
					JOptionPane.showMessageDialog (null, "You've successfully bought the books!", "Transaction completed", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(165, 40, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				UserAccountFrame UAF = new UserAccountFrame();
				UAF.NewScreen();
			}
		});
		btnNewButton_1.setBounds(678, 16, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove Item");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					deleteItem();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(464, 40, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
	}

	public void displayingCart() throws SQLException {
		
		ConnectToDB dbConn = new ConnectToDB();
		dbConn.displayingCart(table);
	}
	
	public void deleteItem() throws SQLException {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int[] rows = table.getSelectedRows();
		ConnectToDB dbConn = new ConnectToDB();
		
		if (rows.length == 1) {
			String ISBNToBeRemoved = model.getValueAt(rows[0], 2).toString();
			System.out.println(ISBNToBeRemoved);
			dbConn.removeItemFromCart(table, ISBNToBeRemoved);
			JOptionPane.showMessageDialog (null, "You've successfully removed book from cart!", "Removed from cart", JOptionPane.INFORMATION_MESSAGE);

		
		}
		else {
			JOptionPane.showMessageDialog (null, "Somethng is wrong", "Error", JOptionPane.ERROR_MESSAGE);

			
		}
		
	}
}
