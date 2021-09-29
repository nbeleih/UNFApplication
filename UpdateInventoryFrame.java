import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateInventoryFrame {

	private JFrame frame;
	private JTextField textFieldISBN;
	private JTextField textFieldBookCode;
	private JTextField textFieldAuthorID;
	private JTextField textFieldCondition;
	private JTextField textFieldPublisher;
	private JTextField textFieldDateIssued;
	private JTextField textFieldEdition;
	private JTextField textFieldBookTitle;
	private JTextField textFieldBuyPrice;
	private JTextField textFieldRentPrice;
	private JTextField textFieldFormat;
	private JTextField textFieldBookScore;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateInventoryFrame window = new UpdateInventoryFrame();
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
	public UpdateInventoryFrame() throws SQLException {
		initialize();
		showTableInAdminSearch();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 961, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 17, 928, 224);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISBN:");
		lblNewLabel.setBounds(16, 22, 44, 16);
		panel.add(lblNewLabel);
		
		textFieldISBN = new JTextField();
	
		textFieldISBN.setBounds(71, 17, 130, 26);
		panel.add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("BookCategory Code:");
		lblNewLabel_1.setBounds(16, 71, 130, 16);
		panel.add(lblNewLabel_1);
		
		textFieldBookCode = new JTextField();
		textFieldBookCode.setBounds(158, 66, 61, 26);
		panel.add(textFieldBookCode);
		textFieldBookCode.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Author ID");
		lblNewLabel_2.setBounds(16, 121, 69, 16);
		panel.add(lblNewLabel_2);
		
		textFieldAuthorID = new JTextField();
		textFieldAuthorID.setBounds(97, 116, 61, 26);
		panel.add(textFieldAuthorID);
		textFieldAuthorID.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Condition:");
		lblNewLabel_3.setBounds(16, 165, 69, 16);
		panel.add(lblNewLabel_3);
		
		textFieldCondition = new JTextField();
		textFieldCondition.setBounds(89, 160, 130, 26);
		panel.add(textFieldCondition);
		textFieldCondition.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Publisher:");
		lblNewLabel_4.setBounds(295, 22, 69, 16);
		panel.add(lblNewLabel_4);
		
		textFieldPublisher = new JTextField();
		textFieldPublisher.setBounds(360, 17, 111, 26);
		panel.add(textFieldPublisher);
		textFieldPublisher.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date Issued:");
		lblNewLabel_5.setBounds(295, 71, 78, 16);
		panel.add(lblNewLabel_5);
		
		textFieldDateIssued = new JTextField();
		textFieldDateIssued.setBounds(385, 66, 86, 26);
		panel.add(textFieldDateIssued);
		textFieldDateIssued.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Edition:");
		lblNewLabel_6.setBounds(295, 121, 54, 16);
		panel.add(lblNewLabel_6);
		
		textFieldEdition = new JTextField();
		textFieldEdition.setBounds(361, 116, 37, 26);
		panel.add(textFieldEdition);
		textFieldEdition.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Book Title:");
		lblNewLabel_7.setBounds(295, 165, 69, 16);
		panel.add(lblNewLabel_7);
		
		textFieldBookTitle = new JTextField();
		textFieldBookTitle.setBounds(371, 160, 130, 26);
		panel.add(textFieldBookTitle);
		textFieldBookTitle.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Buy Price:");
		lblNewLabel_8.setBounds(540, 22, 69, 16);
		panel.add(lblNewLabel_8);
		
		textFieldBuyPrice = new JTextField();
		textFieldBuyPrice.setBounds(610, 17, 69, 26);
		panel.add(textFieldBuyPrice);
		textFieldBuyPrice.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Rent Price: ");
		lblNewLabel_9.setBounds(540, 71, 78, 16);
		panel.add(lblNewLabel_9);
		
		textFieldRentPrice = new JTextField();
		textFieldRentPrice.setBounds(610, 66, 69, 26);
		panel.add(textFieldRentPrice);
		textFieldRentPrice.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Format: ");
		lblNewLabel_10.setBounds(540, 121, 54, 16);
		panel.add(lblNewLabel_10);
		
		textFieldFormat = new JTextField();
		textFieldFormat.setBounds(610, 116, 69, 26);
		panel.add(textFieldFormat);
		textFieldFormat.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Book Score:");
		lblNewLabel_11.setBounds(540, 165, 78, 16);
		panel.add(lblNewLabel_11);
		
		textFieldBookScore = new JTextField();
		textFieldBookScore.setBounds(620, 160, 59, 26);
		panel.add(textFieldBookScore);
		textFieldBookScore.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					searchingjTableButton();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(785, 17, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add/Insert");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					addingBooks();
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "Enter all the missing information in the correct format"
							, "Missing Field" , JOptionPane.ERROR_MESSAGE);	
					//e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(785, 66, 117, 29);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(785, 116, 117, 29);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int[] rows = table.getSelectedRows();
				if (rows.length == 1) {
					String ISBNToBeDeleted = model.getValueAt(rows[0], 0).toString();
					if (ISBNToBeDeleted != null && !ISBNToBeDeleted.isEmpty()) {
						ConnectToDB dbConn = new ConnectToDB();
						try {
							int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete?" , "Delete" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);;		
							if(result == JOptionPane.YES_OPTION) {
								dbConn.DeleteBookRecord(ISBNToBeDeleted);
								model.removeRow(rows[0]);
							}
							else  {
								//
							}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				}
				else {
					// Print User Error Message (Please select one record to delete)
				}
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Go back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();

				AdminAccountFrame AAF = new AdminAccountFrame();
				AAF.newScreen();
			}
		});
		btnNewButton_3.setBounds(785, 165, 117, 29);
		panel.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 253, 928, 263);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 916, 251);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
public void showTableInAdminSearch() throws SQLException {
		
		ConnectToDB dbConn = new ConnectToDB();
		dbConn.showTableInAdminSearch(table);
	}

public void searchingjTableButton() throws SQLException { 
	
	String BookTitle = textFieldBookTitle.getText();
	String ISBN = textFieldISBN.getText();
	String AuthorID = textFieldAuthorID.getText();
	String BookCategoryCode = textFieldBookCode.getText();

	
	ConnectToDB dbConn = new ConnectToDB(); //include parameters below when you have dbConn.
	boolean searchStatus = dbConn.searchingjTableButton(table, ISBN, AuthorID, BookTitle, BookCategoryCode);
	
	if(searchStatus == false) {
		
		JOptionPane.showMessageDialog(null, "Error. Make sure you Fill in ISBN, BookTitle, Author ID or Book Category Code " , "Invalid Search", JOptionPane.ERROR_MESSAGE);

	}

	
}
public void addingBooks() throws SQLException {
	
	String BookTitle = textFieldBookTitle.getText();
	String ISBN = textFieldISBN.getText();
	String AuthorID = textFieldAuthorID.getText();
	String BookCategoryCode = textFieldBookCode.getText();
	String Publisher = textFieldPublisher.getText();
	String Condition = textFieldCondition.getText();
	String DateIssued = textFieldDateIssued.getText();
	String Edition = textFieldEdition.getText();
	String BuyPrice = textFieldBuyPrice.getText();
	String RentPrice = textFieldRentPrice.getText();
	String Format = textFieldFormat.getText();
	String BookScore = textFieldBookScore.getText();



	if(!ISBN.isBlank() == false|| !BookTitle.isBlank()== false || !AuthorID.isBlank()== false 
			|| BookCategoryCode.isBlank() == false || Publisher.isBlank() == false
			|| Condition.isBlank() == false || DateIssued.isBlank() == false 
			|| Edition.isBlank() == false || BuyPrice.isBlank() == false 
			|| RentPrice.isBlank() == false || Format.isBlank() == false 
			|| BookScore.isBlank() == false ) {
		
		
		ConnectToDB dbConn = new ConnectToDB();
		dbConn.addingBooks(ISBN, BookCategoryCode, AuthorID, Condition, Publisher, DateIssued
							, Edition, BookTitle, BuyPrice, RentPrice, Format, BookScore);
		
		JOptionPane.showMessageDialog(null, "You've successfully inserted!"
				, "Successful transaction" , JOptionPane.YES_OPTION);	
	
	} else {
		
	//	JOptionPane.showMessageDialog(null, "Enter all the missing information in the correct format"
		//								, "Missing Field" , JOptionPane.ERROR_MESSAGE);	
		}
	
	
}
}
