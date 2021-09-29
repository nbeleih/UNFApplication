import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import net.proteanit.sql.DbUtils;


public class ConnectToDB {

	protected static Connection connection = null;
   protected static Statement stmt = null;
   protected static Statement statement = null;

	public static String databaseName = "";
	public static String url = "jdbc:mysql://cisvm-winsrv-mysql1.unfcsd.unf.edu:3307/?user=n01406392/group3";
	 
	public static String username = "n01406392";
	public static String password = "Nonoshlol17";
	protected JTable table;
	

	protected int CustomerID;
	protected static int valueOfCusIDFromRs;
	protected static int InvoiceID;
	protected static int valueOfInvoiceIDFromRs;
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException , ClassNotFoundException , SQLException {

	}
	
public void Initialize() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url , username, password);
		
		
	}

	public boolean login(String User , String Password) throws SQLException {
		
		stmt = connection.createStatement();
		String sql = "SELECT COUNT(*) from group3.users WHERE UserName='" + User + "'" + "and Password='" + Password + "';";
		ResultSet result = stmt.executeQuery(sql);
		result.next();

		if(result != null && result.getInt(1) == 1 ) {
			
			String sqlQueryToGetCusID = "SELECT users.CustomerID FROM group3.users WHERE UserName = '"+ User +"'; ";
			statement = connection.createStatement();
			ResultSet resultForCusID = statement.executeQuery(sqlQueryToGetCusID);
			resultForCusID.next();
			valueOfCusIDFromRs =  ((Number) resultForCusID.getObject(1)).intValue();
			
			return result.getInt(1) > 0 ? true : false;
		}else {
			
			return false;

		}
		
		
	}
	

public boolean loginAdmin(String Admin , String Password) throws SQLException {
		
		stmt = connection.createStatement();
		String sql = "SELECT COUNT(*) from group3.admins WHERE Admin='" + Admin + "'" + "and Password='" + Password + "';";
		ResultSet result = stmt.executeQuery(sql);
		result.next();

		if(result != null && result.getInt(1) == 1 ) {
		
			return result.getInt(1) > 0 ? true : false;
		
		}else {
			
			return false;

		}
		
		
	}
	
public boolean Register(String User, String Password, String fName , String lName , String Email , String PhoneNum , String Street , String City , String State , String ZIP ) throws SQLException {
		
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT COUNT(*) from group3.users WHERE UserName='" + User + "'" + "and Password='" + Password + "';";
		ResultSet result = stmt.executeQuery(sql);
		result.next();

		if(result != null && result.getInt(1) == 0) {
			
				String sqlInsertInCustomer = "INSERT INTO group3.customer(CustomerFirstName , CustomerLastName , Email , PhoneNumber , Street , City , State , ZIP , OutStandingBalance) VALUES('"+ fName + "' , '"+ lName+"' , '"+ Email +"' , '"+ PhoneNum +"' , '"+ Street +"' , '"+ City +"' , '"+ State +"' , '"+ZIP +"' , '"+ 0.00 +"'); ";
				
								
				stmt.executeUpdate(sqlInsertInCustomer,Statement.RETURN_GENERATED_KEYS);

				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();
					
				 CustomerID = rs.getInt(1);
				
				
				String sqlInsert = "INSERT INTO group3.users(CustomerID , UserName , Password) VALUES('" + CustomerID + "','" + User + "','" + Password + "');";

				stmt.executeUpdate(sqlInsert);
				
				
				
				
				return true;
			}
		
		return false;
	}
	
public int getCustomerID() {
	return this.CustomerID;
}

public void setCustomerID(int customerID) {
	this.CustomerID = customerID;
}
	

public void showDataTableInUserSearch(JTable table1) throws SQLException {
		
		

		stmt = connection.createStatement();
		String sql = "select book.ISBN , book.BookTitle , author.AuthorLastName AS AuthorName , book_categories.BookCategory, book.Edition , book.BuyPrice , book.RentPrice\n" + 
				"from group3.book , group3.author , group3.book_categories\n" + 
				"WHERE book.AuthorID = author.AuthorID\n" + 
				"AND book_categories.BookCategoryCode = book.BookCategoryCode\n" + 
				"order by BookTitle; ";
		
		ResultSet result = stmt.executeQuery(sql);
		table1.setModel(DbUtils.resultSetToTableModel(result));
	}

public void searchingjTable(String bookTitleSearch , JTable tablePassed , String selection) throws SQLException {
	
	
	stmt = connection.createStatement();
	
	String sqlSearch = "select book.ISBN ,  book.BookTitle , author.AuthorLastName AS AuthorName , book_categories.BookCategory, book.Edition , book.BuyPrice , book.RentPrice\n" + 
			"from group3.book , group3.author , group3.book_categories\n" + 
			"WHERE book.AuthorID = author.AuthorID\n" + 
			"AND book_categories.BookCategoryCode = book.BookCategoryCode\n" +
			"AND "+ selection +" LIKE '"+bookTitleSearch+"%' ORDER BY book.BookTitle;";

	//selection = BookTitle or AuthorID from combobox
	//bookTitleSearch is what the user will enter
	
	
	
	ResultSet result = stmt.executeQuery(sqlSearch);
	tablePassed.setModel(DbUtils.resultSetToTableModel(result));
	
}

public void showTableInAdminSearch(JTable table) throws SQLException {
	
	stmt = connection.createStatement();
	String sql = "SELECT inventory.ISBN , book.Edition , book.BookTitle , book.Format , inventory.BookShelfNum\n" + 
			"FROM group3.book , group3.inventory\n" + 
			"WHERE  inventory.ISBN = book.ISBN\n" + 
			"AND book.Format IN ('Hardcover' ,'Paperback')\n" + 
			"ORDER BY BookTitle;";
	
	ResultSet result = stmt.executeQuery(sql);
	table.setModel(DbUtils.resultSetToTableModel(result));
}

public void searchingTableInAdminSearch(JTable table1 , String BookTitle) throws SQLException {
	
	stmt = connection.createStatement();
	String sql = "SELECT inventory.ISBN , book.Edition , book.BookTitle , book.Format , inventory.BookShelfNum\n" + 
			"FROM group3.book , group3.inventory\n" + 
			"WHERE  inventory.ISBN = book.ISBN\n" + 
			"AND book.Format IN ('Hardcover' ,'Paperback')\n" + 
			"AND BookTitle LIKE '%"+ BookTitle +" %' " +
			"ORDER BY BookTitle;";
	
	ResultSet result = stmt.executeQuery(sql);
	table1.setModel(DbUtils.resultSetToTableModel(result));
}
public void searchingjTableFromBookTitle(JTable table , String bookTitleSearch) throws SQLException {
	
	stmt = connection.createStatement();
	String sqlSearch = "SELECT * FROM group3.book WHERE BookTitle LIKE '"+bookTitleSearch+"%'; ";
	ResultSet result = stmt.executeQuery(sqlSearch);
	table.setModel(DbUtils.resultSetToTableModel(result));

	
}
public void searchingjTableFromAuthor(JTable table , String AuthorID) throws SQLException {
	
	stmt = connection.createStatement();
	String sqlSearch = "SELECT * FROM group3.book WHERE AuthorID LIKE '"+AuthorID+"%'; ";
	ResultSet result = stmt.executeQuery(sqlSearch);
	table.setModel(DbUtils.resultSetToTableModel(result));

	
}

public boolean searchingjTableButton(JTable table , String ISBN , String AuthorID , String BookTitle , String BookCategoryCode) throws SQLException {
	
	stmt = connection.createStatement();
	String SqlSearch = "SELECT * FROM group3.book Where ";
	boolean doesReturn = false;
	boolean addAndClause = false;
	//use resultset as well for query above?
	//have multiple ifs instead of else if? so the cursor could check each one
	
	if (ISBN != null && !ISBN.isEmpty()) {
		SqlSearch = SqlSearch.concat("ISBN LIKE '%" + ISBN + "%'");
		doesReturn = true;
		addAndClause = true;
	}
	if(AuthorID != null && !AuthorID.isEmpty()) {
		if (addAndClause){ SqlSearch = SqlSearch.concat(" AND "); }
		SqlSearch = SqlSearch.concat("AuthorID LIKE '%" + AuthorID + "%'");
		doesReturn = true;
		addAndClause = true;
	}
	 if(BookTitle != null && !BookTitle.isEmpty()) {
		if (addAndClause){ SqlSearch = SqlSearch.concat(" AND "); }
		SqlSearch = SqlSearch.concat("BookTitle LIKE '%" + BookTitle + "%'");
		doesReturn = true;	
		addAndClause = true;
	}
	 if(BookCategoryCode != null && !BookCategoryCode.isEmpty()) {
		if (addAndClause){ SqlSearch = SqlSearch.concat(" AND "); }
		SqlSearch = SqlSearch.concat("BookCategoryCode LIKE '%" + BookCategoryCode + "%'");
		doesReturn = true;
		addAndClause = true;
	}
	
	if (doesReturn) {
		ResultSet result = stmt.executeQuery(SqlSearch);
		table.setModel(DbUtils.resultSetToTableModel(result));
		return true;
	}
	else { return false; }
}
	
public boolean addingBooks(String ISBN , String BookCategoryCode , String AuthorID , String Condition , String Publisher , String DateIssued , String Edition , String BookTitle , String BuyPrice , String RentPrice , String Format ,String BookScore) throws SQLException {
	
	stmt = connection.createStatement();


	
	
	
	String sqlInsert = "INSERT INTO group3.book VALUES" +
					   "('"+ISBN+"','"+BookCategoryCode+"','"+AuthorID+"','" + Condition +"','" + Publisher +"','"+ DateIssued +"','"+Edition+"','"+BookTitle+"'"
					   				+ ",'"+BuyPrice+"','"+RentPrice+"','"+Format+"','"+BookScore+"');";
	
	stmt.executeUpdate(sqlInsert);
	
	/*String sql = "SELECT * FROM group3.book;";
	ResultSet resultForTable = stmt.executeQuery(sql);
	table.setModel(DbUtils.resultSetToTableModel(resultForTable));*/

	
		//show * FROM book table after inserting
	
	return false;
}

public void DeleteBookRecord(String ISBN) throws SQLException {
	stmt = connection.createStatement();
	String sqlDelete = "DELETE FROM group3.book WHERE ISBN = '" + ISBN + "';";
	System.out.println(sqlDelete);
	stmt.executeUpdate(sqlDelete);
}

public double getBuyTotalPrice(String ISBNToBePurchased , String QuantitySold) throws SQLException {
	
	
	stmt = connection.createStatement();
	
    
    String sqlSelectBuy = "SELECT book.BuyPrice FROM group3.book WHERE ISBN = '"+ ISBNToBePurchased +"'; ";
    ResultSet rs = stmt.executeQuery(sqlSelectBuy);
    rs.next();
    
    double BuyPrice = rs.getDouble("BuyPrice");
    
    int Quantity = Integer.parseInt(QuantitySold); //converts string type QuantitySold into int
    double TotalAmount = Quantity * BuyPrice; 
  
    
    return TotalAmount;

  
   
}

public double getRentTotalPrice(String ISBNToBeRented , int QuantityRent ) throws SQLException {


	stmt = connection.createStatement();
	
	
    
    String sqlSelectRent = "SELECT book.RentPrice FROM group3.book WHERE ISBN = '"+ ISBNToBeRented +"'; ";
    ResultSet rs1 = stmt.executeQuery(sqlSelectRent);
    rs1.next();
    double RentPrice = rs1.getDouble("RentPrice");
    
    double TotalAmount = QuantityRent * RentPrice; 

   return TotalAmount;
    
}

public int getInvoiceID() throws SQLException {
	
	
	stmt = connection.createStatement();
	
	int CustomerID = valueOfCusIDFromRs;
	String CartStatus = null;
	int InvoiceID = 0;
	
	String sqlQueryToGetStatus = "SELECT  product_invoice.InvoiceID , product_invoice.CartStatus"
			+ " FROM group3.product_invoice "
			+ "WHERE CustomerID = '"+ CustomerID +"' "
					+ "ORDER BY InvoiceCreatedDate DESC LIMIT 1; ";
	
	
	ResultSet resultForCartStatus = stmt.executeQuery(sqlQueryToGetStatus);
	boolean empty = true;
	if(resultForCartStatus.next()){ 
		
		
		CartStatus = resultForCartStatus.getString("CartStatus");
		InvoiceID = resultForCartStatus.getInt("InvoiceID");
		
		//System.out.println(CartStatus);
		//System.out.println(InvoiceID);

		
		if(CartStatus.equals("Purchased")) {
			
			empty = true;
		}
		else {
			
			empty = false;
		}
	}

	 if(empty) {
		
		return CreateCart(); //returns invoiceID after the new cart was created
		 
	}
	 else {
		 return InvoiceID; //returns currents cart InvoiceID
	 }
	
}

public void insertInProductLineItem(String ISBNToBeSelected , String QuantitySold , String Rent_Buy , int InvoiceID , double TotalAmount) throws SQLException {
	
	stmt = connection.createStatement();
	
	int Quantity = Integer.parseInt(QuantitySold);
	int CustomerID = valueOfCusIDFromRs;

	
	
	
	String sqlInsertInLineItem = "INSERT INTO group3.product_line_item(InvoiceID , CustomerID , ISBN , QuantitySold , Rent_Buy , TotalAmount)" +
								 "VALUES('"+ InvoiceID +"' , '"+ CustomerID +"' , '"+ ISBNToBeSelected +"' , '"+ Quantity +"' , '"+ Rent_Buy +"','"+ TotalAmount +"'); ";

	stmt.executeUpdate(sqlInsertInLineItem);
}

public void insertInProductLineItem(String ISBNToBeSelected, int QuantityRent, String Rent_Buy , int InvoiceID , double TotalAmount) throws SQLException {
	
	stmt = connection.createStatement();
	
	int CustomerID = valueOfCusIDFromRs;
	 
	
	String sqlInsertInLineItem = "INSERT INTO group3.product_line_item(InvoiceID , CustomerID , ISBN , QuantitySold , Rent_Buy , TotalAmount)" +
								 "VALUES('"+ InvoiceID +"' , '"+ CustomerID +"' , '"+ ISBNToBeSelected +"' , '"+ QuantityRent +"' , '"+ Rent_Buy +"','"+ TotalAmount +"'); ";

	stmt.executeUpdate(sqlInsertInLineItem);
}

public void updateAmountOwed(String ISBNToBePurchased , String QuantitySold) throws SQLException {
	
	
	stmt = connection.createStatement();
	
	  String sqlSelectBuy = "SELECT book.BuyPrice FROM group3.book WHERE ISBN = '"+ ISBNToBePurchased +"'; ";
	    ResultSet rs = stmt.executeQuery(sqlSelectBuy);
	    rs.next();
	    
	    int Quantity = Integer.parseInt(QuantitySold);
	    
	    double BuyPrice = rs.getDouble("BuyPrice");
	    double AmountPaid = Quantity * BuyPrice; 
	    
	    String sqlUpdateAmountOwed = "UPDATE group3.product_invoice SET AmountPaid = AmountPaid + '"+ AmountPaid +"' WHERE InvoiceID = '"+ getInvoiceID() +"' ; ";
	    
	    stmt.executeUpdate(sqlUpdateAmountOwed);
	    
	    String sqlUpdateOutStandingBalance = "UPDATE group3.customer "
	    		+ "SET OutStandingBalance = OutStandingBalance + '"+ AmountPaid +"'"
	    				+ " WHERE CustomerID = '"+ CustomerID +"'; ";
	    
	    statement.executeUpdate(sqlUpdateOutStandingBalance);
	    
}
public void updateQtyOfBooksInInventory(String ISBNToBeSelected , String QuantitySold) throws SQLException {
	
	stmt = connection.createStatement();
	
	    
	    int Quantity = Integer.parseInt(QuantitySold);
	    
	   String sqlUpdateQty = "UPDATE group3.inventory SET QuantityInStock = QuantityInStock - '"+ Quantity +"' WHERE ISBN = '"+ ISBNToBeSelected +"'; ";
	   
	   stmt.executeUpdate(sqlUpdateQty);
}

public void updateQtyOfBooksInInventory(String ISBNToBeSelected, int QuantityRent) throws SQLException {
	// TODO Auto-generated method stub
	stmt = connection.createStatement();
	 
	String sqlUpdateQty = "UPDATE group3.inventory SET QuantityInStock = QuantityInStock - '"+ QuantityRent +"' WHERE ISBN = '"+ ISBNToBeSelected +"'; ";
	stmt.executeUpdate(sqlUpdateQty);
	
}

public int getQuantityInStock(String ISBNToBeSelected) throws SQLException {
	
	stmt = connection.createStatement();
	
	String sqlCheckingQty = "SELECT inventory.QuantityInStock FROM inventory WHERE ISBN = '"+ ISBNToBeSelected +"'; ";
	
	
	ResultSet rs = 	stmt.executeQuery(sqlCheckingQty);
	rs.next();
	int QtyInStock = rs.getInt("QuantityInStock");

	return QtyInStock;
}

public void updateAmountOwed(String ISBNToBeSelected, int QuantityRent) throws SQLException {
	// TODO Auto-generated method stub
	

	stmt = connection.createStatement();
	
	  String sqlSelectBuy = "SELECT book.RentPrice FROM group3.book WHERE ISBN = '"+ ISBNToBeSelected +"'; ";
	    ResultSet rs = stmt.executeQuery(sqlSelectBuy);
	    rs.next();
	    
	    
	    
	    double RentPrice = rs.getDouble("RentPrice");
	    double AmountPaid = QuantityRent * RentPrice; 
	    
	    String sqlUpdateAmountOwed = "UPDATE group3.product_invoice SET AmountPaid = AmountPaid + '"+ AmountPaid +"' WHERE InvoiceID = '"+ valueOfInvoiceIDFromRs +"' ; ";
	    
	    stmt.executeUpdate(sqlUpdateAmountOwed);
	    
	    String sqlUpdateOutStandingBalance = "UPDATE group3.customer "
	    		+ "SET OutStandingBalance = OutStandingBalance + '"+ AmountPaid +"'"
	    				+ " WHERE CustomerID = '"+ CustomerID +"'; ";
	    
	    statement.executeUpdate(sqlUpdateOutStandingBalance);
	    
}

public double checkOutStandingBalance() throws SQLException {
	
	stmt = connection.createStatement();
	int CustomerID = valueOfCusIDFromRs;
	
	String sqlSelectOutstandingBalance = "SELECT customer.OutStandingBalance FROM group3.customer WHERE CustomerID = '"+ CustomerID +"'; ";
	ResultSet result = stmt.executeQuery(sqlSelectOutstandingBalance);
	result.next();
	double OutStandingBalance = result.getDouble("OutStandingBalance");
	
	return OutStandingBalance;
}

public int gettingNumOfRentedBooks(String ISBNToBeSelected , String Rent_Buy) throws SQLException {
	
	stmt = connection.createStatement();
	int CustomerID = valueOfCusIDFromRs;
	int InvoiceID = getInvoiceID();
	
	String sqlCount = "SELECT COUNT(Rent_Buy) AS NumOfRentedBooks FROM group3.product_line_item WHERE InvoiceID = '"+ InvoiceID +"' AND Rent_Buy = '"+ Rent_Buy +"'; ";
	
	ResultSet rs = stmt.executeQuery(sqlCount);
	rs.next();
	int NumOfRentedBooks = rs.getInt("NumOfRentedBooks");
	
	return NumOfRentedBooks;
}

public int CreateCart() throws SQLException {
	
	stmt = connection.createStatement();
	
	int CustomerID = valueOfCusIDFromRs;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();
    String InvoiceCreatedDate = dtf.format(localDate);
    String CartStatus = "Pending";
	
	String sqlCreate = "INSERT INTO group3.product_invoice(CustomerID , InvoiceCreatedDate , CartStatus) "
			+ "VALUES('"+ CustomerID +"','"+ InvoiceCreatedDate +"','"+ CartStatus +"');";
	
	//System.out.println(sqlCreate);

	
	stmt.executeUpdate(sqlCreate , Statement.RETURN_GENERATED_KEYS); 
	   
	   ResultSet result = stmt.getGeneratedKeys();
	   result.next();
	   InvoiceID = result.getInt(1);

	   return InvoiceID;
}

public void displayingCart(JTable table) throws SQLException {
	
	stmt = connection.createStatement();
	
	int CustomerID = valueOfCusIDFromRs;
	int InvoiceID = getInvoiceID();
	String sqlCart = "select *"
			+ " from group3.product_line_item pli\n" + 
			"join group3.product_invoice pi on pli.InvoiceID = '"+ InvoiceID +"' " //change pi.InvoiceID?
			+ " and pi.CartStatus = 'Pending' "
			+ "and InvoiceCreatedDate = (select InvoiceCreatedDate "
			+ "from group3.product_invoice "
			+ "where CustomerID = '"+ CustomerID +"' order by InvoiceCreatedDate desc limit 1)";
	
	ResultSet result = stmt.executeQuery(sqlCart);
	table.setModel(DbUtils.resultSetToTableModel(result));
	
}

public void CheckingUserBalance(String CusID , JTable table) throws SQLException {
	
	
	stmt = connection.createStatement();
	
	int CustomerID = Integer.parseInt(CusID);
	//String sqlCount = "SELECT COUNT(CustomerID) FROM group3.customer WHERE CustomerID = '"+  +"' "
	String sqlSelect = "SELECT * FROM group3.customer WHERE CustomerID = '"+ CustomerID +"'; ";
	
	ResultSet rs = stmt.executeQuery(sqlSelect);
	table.setModel(DbUtils.resultSetToTableModel(rs));
}
public void generateRevenuePerBook(JTable table , String BookTitle) throws SQLException {
	
	stmt = connection.createStatement();
	//int InvoiceID = getInvoiceID();
	
	
	String sqlBookRevenue = "select PLI.ISBN , BK.BookTitle, Sum(PLI.TotalAmount) AS Revenue " + 
			"from group3.product_line_item PLI " + 
			"join group3.product_invoice PI ON PLI.InvoiceID = PI.InvoiceID " + 
			"join group3.book BK ON BK.ISBN = PLI.ISBN " + 
			"AND PI.CartStatus = 'Purchased'" + 
			"AND BookTitle LIKE '%"+ BookTitle +"%' " +
			" group by PLI.ISBN , BK.BookTitle" + 
			" order by PLI.ISBN;";
	
	ResultSet rs = stmt.executeQuery(sqlBookRevenue);
	table.setModel(DbUtils.resultSetToTableModel(rs));

}

public void generateRevenueMonthly(JTable table) throws SQLException {
	
	stmt = connection.createStatement();
	
	String sql = "select Month(PI.PurchaseDate) AS Month , YEAR(PI.PurchaseDate) AS Year , Sum(PLI.TotalAmount) AS Revenue\n" + 
			"from group3.product_line_item PLI\n" + 
			"join group3.product_invoice PI ON PLI.InvoiceID = PI.InvoiceID\n" + 
			"AND PI.CartStatus = 'Purchased'\n" + 
			"group by Month(PI.PurchaseDate) , YEAR(PI.PurchaseDate) \n" + 
			"order by Month(PI.PurchaseDate) , YEAR(PI.PurchaseDate);";
	
	ResultSet rs = stmt.executeQuery(sql);
	table.setModel(DbUtils.resultSetToTableModel(rs));

}
public void generateRevenueYearly(JTable table) throws SQLException {
	
	stmt = connection.createStatement();
	
	String sqlYearlyRevenue = " SELECT YEAR(PI.PurchaseDate) AS Year , Sum(PLI.TotalAmount) AS Revenue\n" + 
			"from group3.product_line_item PLI\n" + 
			"join group3.product_invoice PI ON PLI.InvoiceID = PI.InvoiceID\n" + 
			"AND PI.CartStatus = 'Purchased'\n" + 
			"group by YEAR(PI.PurchaseDate) \n" + 
			"order by  YEAR(PI.PurchaseDate);";
	
	ResultSet rs = stmt.executeQuery(sqlYearlyRevenue);
	table.setModel(DbUtils.resultSetToTableModel(rs));

}
public void generateRevenueWeekly(JTable table) throws SQLException {
	
	stmt = connection.createStatement();
	
	String sqlWeeklyRevenue = "select WEEK(PI.PurchaseDate) AS Week , YEAR(PI.PurchaseDate) AS Year, Sum(PLI.TotalAmount) AS Revenue\n" + 
			"from group3.product_line_item PLI\n" + 
			"join group3.product_invoice PI ON PLI.InvoiceID = PI.InvoiceID\n" + 
			"AND PI.CartStatus = 'Purchased'\n" + 
			"group by WEEK(PI.PurchaseDate) , YEAR(PI.PurchaseDate) \n" + 
			"order by WEEK(PI.PurchaseDate) , YEAR(PI.PurchaseDate);";
	
	ResultSet rs = stmt.executeQuery(sqlWeeklyRevenue);
	table.setModel(DbUtils.resultSetToTableModel(rs));
}
public void payNow() throws SQLException {
	
	stmt = connection.createStatement();
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();
    String PurchaseDate = dtf.format(localDate);
    String CartStatus = "Purchased";
    int InvoiceID = getInvoiceID();
    double TotalAmount;
    
    String sqlGetTotalAmount = "SELECT SUM(TotalAmount) AS TotalAmount\n" + 
    		"FROM group3.product_line_item\n" + 
    		"WHERE product_line_item.InvoiceID = '"+ InvoiceID +"' ;";
    
    ResultSet result = stmt.executeQuery(sqlGetTotalAmount);
    result.next();
    
    TotalAmount = result.getInt("TotalAmount");
	
	String sqlUpdate = "UPDATE group3.product_invoice"
			+ " SET PurchaseDate = '"+ PurchaseDate +"',"
					+ " AmountPaid = '"+ TotalAmount +"',"
							+ " CartStatus =  '"+ CartStatus +"'"
									+ " WHERE InvoiceID = '"+ InvoiceID +"'; ";
	
	stmt.executeUpdate(sqlUpdate);
}
public void checkLateFees(JTable table2) throws SQLException {
	
	
	stmt = connection.createStatement();
	int CustomerID = valueOfCusIDFromRs;
	
	String sqlQuery = "SELECT customer.OutStandingBalance , customer.CustomerFirstName , customer.CustomerLastName "
			+ "FROM group3.customer WHERE CustomerID = '"+ CustomerID +"'; ";
	
	ResultSet rs = stmt.executeQuery(sqlQuery);
	//rs.next();
	table2.setModel(DbUtils.resultSetToTableModel(rs));
	
}
public void payLateFees() throws SQLException {
	
	stmt = connection.createStatement();
	int CustomerID = valueOfCusIDFromRs;
	
	String sqlUpdate = "UPDATE group3.customer SET OutStandingBalance = 0.00 WHERE CustomerID = '"+ CustomerID +"'; ";
	stmt.executeUpdate(sqlUpdate);
	
}

public void ApplyLateFees(double LateFees) throws SQLException {
	
	stmt = connection.createStatement();
	int CustomerID = valueOfCusIDFromRs;
	
	String sqlUpdate = "UPDATE group3.customer SET OutStandingBalance = '"+ LateFees +"' WHERE CustomerID = '"+ CustomerID +"'; ";
	stmt.executeUpdate(sqlUpdate);
	
}
public void displayRentedBooksTable(JTable table1) throws SQLException {
	
	stmt = connection.createStatement();
	
	int InvoiceID = getInvoiceID();
	String Rent_Buy = "R";
	int CustomerID = valueOfCusIDFromRs;
	
	String sqlCount = "SELECT * FROM group3.product_line_item WHERE CustomerID = '"+ CustomerID +"' AND Rent_Buy = '"+ Rent_Buy +"'; ";
	
	
	ResultSet result = stmt.executeQuery(sqlCount);
	table1.setModel(DbUtils.resultSetToTableModel(result));
	
}

public void returnBook(String InvoiceIDSelected , String ISBNSelected) throws SQLException {
	
	
	stmt = connection.createStatement();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();
    String TodayDate = dtf.format(localDate);
    //int InvoiceID = getInvoiceID();
    String Rent_Buy = "R";
    
    //getpurchaseDate
    //String sqlGetPurchase = "SELECT PurchaseDate FROM group3.product_line_item WHERE InvoiceID = '"+ InvoiceID +"';  ";
  
    
    String sqlUpdate = "UPDATE group3.product_line_item SET ReturnDate = '"+ TodayDate +"' WHERE InvoiceID = '"+ InvoiceIDSelected +"'"
    		+ " AND Rent_Buy = '"+ Rent_Buy +"' AND ISBN = '"+ ISBNSelected +"' ;";
    
    stmt.executeUpdate(sqlUpdate);
	
}

public void removeItemFromCart(JTable table , String ISBNToBeRemoved) throws SQLException {
	
	stmt = connection.createStatement();
	int InvoiceID = getInvoiceID();
	//ISBN get it from the jtable model and insert below in the where statement
	
	String sqlDelete = "DELETE FROM group3.product_line_item WHERE InvoiceID = '"+ InvoiceID +"' AND ISBN = '"+ ISBNToBeRemoved +"' ; ";

	stmt.executeUpdate(sqlDelete);
}




public boolean wasBooksNew(String isbn, Date purchaseDate) {//This function determines whether the book was a NEW RELEASE at the time of it's rent/purchase
	String query = 
			"SELECT ISBN, DateIssued\r\n" + 
			"FROM group3.book\r\n" + 
			"WHERE ISBN = '" + isbn + "';";
	Date newReleaseWindow = new Date(purchaseDate.getTime()-5256000000L);//The Long value is 2 months time in milliseconds
	Date date;
	
	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		rs.next();
		date = rs.getDate("DateIssued");
		if (date.after(newReleaseWindow))
			return true;
	}
	catch (Exception e) {
		//System.out.println(e);
	}
	return false;
}

public void cusBooks(int customer) {//Writes a table of the customer's purchase history with info that is relevant for book returns //dont need that i think
	String invoice;
	Date date;
	Date rentReturnWindow = new Date(0);
	String rb;
	String isbn;
	String title;
	
	String query = 
			"SELECT customer.CustomerID, product_invoice.InvoiceID, PurchaseDate, product_line_item.ISBN, Rent_Buy, BookTitle\r\n" + 
			"FROM group3.customer, group3.product_invoice, group3.product_line_item, book\r\n" + 
			"WHERE customer.CustomerID = product_invoice.CustomerID AND product_invoice.InvoiceID = product_line_item.InvoiceID AND product_line_item.ISBN = book.ISBN\r\n" +
			"AND customer.CustomerID = " + customer + ";";
	
	try {
		
		 stmt = connection.createStatement();
		ResultSet r = stmt.executeQuery(query);
		//System.out.println("InvoiceID, PurchaseDate, DaysLate, Rent or Buy, ISBN, Title");
		while (r.next()) {
			invoice = r.getString("InvoiceId");
			date = r.getDate("PurchaseDate");
			rb = r.getString("Rent_Buy");
			isbn = r.getString("ISBN");
			title = r.getString("BookTitle");
			//Calculates by how many days late a book is
			if (wasBooksNew(isbn, date))
				rentReturnWindow.setTime(System.currentTimeMillis()-345600000L);//4 Day rent period if new
			else
				rentReturnWindow.setTime(System.currentTimeMillis()-432000000L);//5 Day rent period otherwise
			long millisLate = rentReturnWindow.getTime() - date.getTime();
			long daysLate = millisLate / 86400000L;
					
			System.out.printf("%s, %s, %s, %s, %s, %s\n", invoice, date, daysLate, rb, isbn, title);
		}
	}
	catch (Exception e) {
		//System.out.println(e);
	}
}

public void returnBooks(String returningInvoice, String returningBook, String retType) {
	Date buyReturnWindow = new Date(System.currentTimeMillis()-2628000000L);//The Long value represents 1 month in milliseconds
	Date lateReturnWindow = new Date(System.currentTimeMillis()-1296000000L);//15 days
	Date rentReturnWindow = new Date(0);
	int change = 0;
	long daysLate;
	long millisLate;
	double lateFee = 0;
	
	String invoice;
	Date date;
	Date retDate;
	String rb;
	double price = 0;
	String isbn;
	String title;
	String format;
	int customerID = valueOfCusIDFromRs;
	
	String query = 
			"SELECT customer.CustomerID, product_invoice.InvoiceID, PurchaseDate, ReturnDate, product_line_item.ISBN, Rent_Buy, BuyPrice, BookTitle, Format\r\n" + 
			"FROM group3.customer, group3.product_invoice, group3.product_line_item, group3.book\r\n" + 
			"WHERE customer.CustomerID = product_invoice.CustomerID AND product_invoice.InvoiceID = product_line_item.InvoiceID AND product_line_item.ISBN = book.ISBN\r\n" +
			"AND customer.CustomerID = "+ customerID +" AND product_invoice.InvoiceID = "+ returningInvoice +" AND product_line_item.ISBN = '"+ returningBook +"';";
	System.out.println(query);
	
	String returnQuery;
	String lateReturnQuery;
	String tooLateReturnQuery;
	String buyReturnQuery;
	
	try {
		stmt = connection.createStatement();
		ResultSet r = stmt.executeQuery(query);
		
		r.next();
			invoice = r.getString("InvoiceID");
			date = r.getDate("PurchaseDate");
			retDate = r.getDate("ReturnDate");
			rb = r.getString("Rent_Buy");
			price = r.getDouble("BuyPrice");
			isbn = r.getString("ISBN");
			title = r.getString("BookTitle");
			format = r.getString("Format");
			if (retType.equals("R")) {//Checks whether a rented book is what's being returned
				if (wasBooksNew(isbn, date))
					rentReturnWindow.setTime(System.currentTimeMillis()-345600000L);//4 Day rent period if new
				else
					rentReturnWindow.setTime(System.currentTimeMillis()-432000000L);//5 Day rent period otherwise
				
				if (date.after(rentReturnWindow)) {//Checks if the book was rented within 4/5 days ago
					returnQuery = 
							"UPDATE group3.product_line_item, group3.inventory\r\n" + 
							"SET ReturnDate = CURDATE(), LateReturnFee = 0.00, QuantityInStock = QuantityInStock + 1\r\n" + 
							"WHERE CustomerID = "+ customerID +" AND InvoiceID = "+ returningInvoice +" AND product_line_item.ISBN = '"+ returningBook +"' AND inventory.ISBN = '"+ returningBook +"' AND Rent_Buy = 'R';";
					change = stmt.executeUpdate(returnQuery); 
				}
				else if (date.after(lateReturnWindow)){//Checks if the book was rented within the past 15 days
					millisLate = rentReturnWindow.getTime() - date.getTime();
					daysLate = millisLate / 86400000L;
					lateFee = daysLate * 2.00;
					
					lateReturnQuery = 
							"UPDATE group3.product_line_item, group3.inventory\r\n" + 
							"SET ReturnDate = CURDATE(), LateReturnFee = "+ lateFee +", TotalAmount = TotalAmount + "+ lateFee +", QuantityInStock = QuantityInStock + 1\r\n" + 
							"WHERE CustomerID = "+ customerID +" AND InvoiceID = "+ returningInvoice +" AND product_line_item.ISBN = '"+ returningBook +"' AND inventory.ISBN = '"+ returningBook +"' AND Rent_Buy = 'R';";
					change = stmt.executeUpdate(lateReturnQuery);
					ApplyLateFees(lateFee);
					
					
				}
				else {//If the book is returned later than 15 days, the full price of the books is paid for and the book is kept
					tooLateReturnQuery = 
							"UPDATE group3.product_line_item\r\n" + 
							"SET ReturnDate = CURDATE(), LateReturnFee = "+ price +" - TotalAmount, TotalAmount = "+ price +"\r\n" + 
							"WHERE CustomerID = "+ customerID +" AND InvoiceID = "+ returningInvoice +" AND product_line_item.ISBN = '"+ returningBook +"' AND Rent_Buy = 'R';";
					change = stmt.executeUpdate(tooLateReturnQuery);
					ApplyLateFees(price);
				}
			}
			else if (retType.equals("B")) {//Checks whether a bought book is what's being returned
				if (date.after(buyReturnWindow)) {//Checks whether the book was bought in the past 30 days
					if (format.equals("E-Book") != true) {//E-books cannot be returned if they're purchased
							buyReturnQuery = 
								"UPDATE group3.product_line_item, inventory\r\n" + 
								"SET ReturnDate = CURDATE(), LateReturnFee = 0, QuantityInStock = QuantityInStock + 1\r\n" + 
								"WHERE CustomerID = "+ customerID +" AND InvoiceID = "+ returningInvoice +" AND product_line_item.ISBN = '"+ returningBook +"' AND inventory.ISBN = '"+ returningBook +"' AND Rent_Buy = 'B';";
						change = stmt.executeUpdate(buyReturnQuery);
					}
					else
						JOptionPane.showMessageDialog (null, "Not allowed to return a purchased E-Book!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				JOptionPane.showMessageDialog (null, "1 month has passed since purchase, and you are no longer able to return this book", "Error", JOptionPane.ERROR_MESSAGE);

			}
			else
			JOptionPane.showMessageDialog (null, "You don't have this book or have already returned it.", "Error", JOptionPane.ERROR_MESSAGE);

	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
}