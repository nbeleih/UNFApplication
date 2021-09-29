import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReturnBookFrame {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBookFrame window = new ReturnBookFrame();
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
	public ReturnBookFrame() throws SQLException {
		initialize();
		displayRentedBooks();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 791, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(223, 23, 551, 391);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 539, 379);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Return selected book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					returnBook();
					JOptionPane.showMessageDialog (null, "You've successfully returned book, check your balance for any late fees", "returned book", JOptionPane.INFORMATION_MESSAGE);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(36, 202, 160, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				UserAccountFrame UAF = new UserAccountFrame();
				UAF.NewScreen();
			}
		});
		btnNewButton_1.setBounds(51, 385, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void displayRentedBooks() throws SQLException {
		
		ConnectToDB dbConn = new ConnectToDB();
		dbConn.displayRentedBooksTable(table);
	}
	public void returnBook() throws SQLException { //stopped here
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int[] rows = table.getSelectedRows();
		ConnectToDB dbConn = new ConnectToDB();
		
		if(rows.length == 1) {
			
			String InvoiceIDSelected = model.getValueAt(rows[0], 0).toString();
			String ISBNSelected = model.getValueAt(rows[0], 2).toString();
			String RentOrBuy = model.getValueAt(rows[0], 4).toString();

			if(InvoiceIDSelected != null && !InvoiceIDSelected.isEmpty()) {
			
			dbConn.returnBooks(InvoiceIDSelected , ISBNSelected , RentOrBuy);

			}
		}
		else {
			
			JOptionPane.showMessageDialog (null, "Something is wrong", "Error", JOptionPane.ERROR_MESSAGE);

		}
		
	}
}
