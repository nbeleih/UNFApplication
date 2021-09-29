import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.JSpinner;

public class UserSearchFrame {

	private JFrame frame;
	private JComboBox comboBoxSelection;
	protected JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSearchFrame window = new UserSearchFrame();
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
	 * @throws ClassNotFoundException 
	 */
	Connection connection = null;
	private JTextField textFieldSearch;
	private JTable table;

	public UserSearchFrame() throws ClassNotFoundException, SQLException {
		initialize();
		showTableInUserSearch();
		//searchingJtable();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(175, 238, 238));
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBorder(new LineBorder(new Color(72, 209, 204), 12));
		panel.setBounds(28, 16, 569, 352);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setBounds(45, 77, 96, 16);
		panel.add(lblNewLabel);

		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {

				try {
					searchingJtable();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		textFieldSearch.setBounds(268, 73, 130, 26);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JButton btnNewButton_1 = new JButton("Add to Cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] options = {"Buy", "Rent"};
				int n = JOptionPane.showOptionDialog(frame,
						"Would you like to buy or rent the book?",
						"Buy or Rent ?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,    	 	//do not use a custom Icon
						options,  		//the titles of buttons
						options[0]); 	//default button title


				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int[] rows = table.getSelectedRows();
				ConnectToDB dbConn = new ConnectToDB();
				String Rent_Buy = null;
				int QtyInStock;

				if (rows.length == 1) {
					String ISBNToBeSelected = model.getValueAt(rows[0], 0).toString();
					if(ISBNToBeSelected != null && !ISBNToBeSelected.isEmpty()) {

						String QuantitySold = spinner.getValue().toString();

						int InvoiceID = 0;
						try {
							InvoiceID = dbConn.getInvoiceID();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}

						if(n == JOptionPane.YES_OPTION)  { //buy option




							//getting the # of books		

							if(InvoiceID > 0) {

								Rent_Buy = "B";
								try {

									double bookTotalBuyPrice = dbConn.getBuyTotalPrice(ISBNToBeSelected, QuantitySold);
									dbConn.insertInProductLineItem(ISBNToBeSelected, QuantitySold, Rent_Buy ,InvoiceID , bookTotalBuyPrice);
									dbConn.updateQtyOfBooksInInventory(ISBNToBeSelected, QuantitySold);
									JOptionPane.showMessageDialog (null, "You've successfully added book to cart!", "Added to cart", JOptionPane.INFORMATION_MESSAGE);

								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

							Rent_Buy = "B";

						}//added
						if(n == JOptionPane.NO_OPTION) { //rent option

							int QuantityRent = 1;
							Rent_Buy = "R";

							if(InvoiceID > 0) {

								try {
									double OutStandingBalance = dbConn.checkOutStandingBalance();
									int numOfRentedBooks = dbConn.gettingNumOfRentedBooks(ISBNToBeSelected, Rent_Buy);

									if(OutStandingBalance <= 10.00 && numOfRentedBooks <= 2) {
										double TotalAmount = dbConn.getRentTotalPrice(ISBNToBeSelected, QuantityRent);
										dbConn.insertInProductLineItem(ISBNToBeSelected, QuantityRent, Rent_Buy , InvoiceID ,TotalAmount );
										dbConn.updateQtyOfBooksInInventory(ISBNToBeSelected, QuantitySold);
										JOptionPane.showMessageDialog (null, "You've successfully added book to cart!", "Added to cart", JOptionPane.INFORMATION_MESSAGE);

									}else {
										JOptionPane.showMessageDialog (null, "It looks like you have more than 2 books rented", "Errors", JOptionPane.ERROR_MESSAGE);
										
									}
									
								} catch (SQLException e1) {

									e1.printStackTrace();
								}
							}

						}

					}

				}

			}
		});
		btnNewButton_1.setBounds(91, 282, 117, 29);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserAccountFrame UAFback = new UserAccountFrame();
				UAFback.NewScreen();
			}
		});
		btnNewButton_2.setBounds(384, 282, 117, 29);
		panel.add(btnNewButton_2);

		comboBoxSelection = new JComboBox();
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"BookTitle"}));
		comboBoxSelection.setBounds(115, 74, 125, 27);
		panel.add(comboBoxSelection);

		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 0, 9, 1);  
		spinner = new JSpinner(model1);
		spinner.setBounds(45, 282, 34, 26);

		panel.add(spinner);

		/*HERE
		 * 
		 * check here
		 */

		JLabel lblNewLabel_1 = new JLabel("Qty:");
		lblNewLabel_1.setBounds(18, 287, 34, 16);
		panel.add(lblNewLabel_1);


		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(72, 209, 204), 12));
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(990, 6, 420, 352);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Go to cart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
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
		btnNewButton.setBounds(6, 6, 408, 340);
		panel_1.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 427, 1382, 279);
		frame.getContentPane().add(scrollPane);

		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setBorder(new LineBorder(new Color(72, 209, 204), 12));
		panel_2.setBackground(new Color(175, 238, 238));
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 16, 1344, 242);
		panel_2.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	public void showTableInUserSearch() throws SQLException {

		ConnectToDB dbConn = new ConnectToDB();
		dbConn.showDataTableInUserSearch(table);

	}

	public void searchingJtable() throws SQLException {

		ConnectToDB conn = new ConnectToDB();
		String bookTitleSearch = textFieldSearch.getText();
		String selection = (String) comboBoxSelection.getSelectedItem();

		conn.searchingjTable(bookTitleSearch , table , selection);


	}

}
