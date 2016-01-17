package GUI;
import javax.swing.*;
import javax.swing.table.*;




import java.sql.*;
import java.awt.Dimension;
/**
 * This class create JTable from Database table.
 * User program needs to specify database connection and corresponding a table name.
 * @authorAmit 
 */

public class SalesHistory{
	//private String table;
	// private static CafeBill _cb;
	public SalesHistory(/*CafeBill cb*/){
		// _cb = cb;
	}
	private Connection con;
	public SalesHistory(Connection con){
		this.con=con;

	}
	/**
	 * This method return JTable object created from Database table having same data as a structure
	 * as in original table into database.
	 * @param table Name of the database table to be converted to JTable
	 * @return JTable object that consist of data and structure of Database table
	 * @throws java.lang.Exception Original object is different, e.i either SQLException or NullPointerException
	 */

	final static String db_name= "HMS";
	final static String username = "billing";
	final static String password = "hmsbilling";
	final static String hmsDbUrl ="jdbc:mysql://localhost/"+db_name+"?"+ "user=" + username + "&" + "password=" + password;
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	public JTable getTable(String table)throws Exception{
		JTable t1=new JTable();
		t1.setPreferredScrollableViewportSize(new Dimension(800, 600));
		t1.setEnabled(false);
		t1.setEditingRow(1);
		t1.setEditingColumn(1);
		t1.setCellSelectionEnabled(true);
		t1.setColumnSelectionAllowed(true);
		t1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t1.setRowSelectionAllowed(false);
		t1.setFillsViewportHeight(true);
		DefaultTableModel dm=new DefaultTableModel();
		Statement st=con.createStatement();   
		//ResultSet rs=st.executeQuery("select * from menu_order");
		ResultSet rs=st.executeQuery("SELECT * FROM menu_order where DATE(orderDate)=CURDATE()"); //for current date order
		//ResultSet rs=st.executeQuery("select sum(totalAmount) from menu_order;"); //for total
		

		ResultSetMetaData rsmd=rs.getMetaData();
		//Coding to get columns-
		int cols=rsmd.getColumnCount();
		String c[]=new String[cols];
		for(int i=0;i<cols;i++){
			c[i]=rsmd.getColumnName(i+1);
			dm.addColumn(c[i]);
		}
		//get data from rows
		Object row[]=new Object[cols];
		while(rs.next()){
			for(int i=0;i<cols;i++){
				row[i]=rs.getString(i+1);
			}
			dm.addRow(row);
		}
		t1.setModel(dm);
		con.close();
		return t1;
	}

	

	public JTable getTable(String table,String query)throws Exception{
		
		
		JTable t1=new JTable();
		DefaultTableModel dm=new DefaultTableModel();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		ResultSetMetaData rsmd=rs.getMetaData();
		//Coding to get columns-
		int cols=rsmd.getColumnCount();
		String c[]=new String[cols];
		for(int i=0;i<cols;i++){
			c[i]=rsmd.getColumnName(i+1);
			dm.addColumn(c[i]);
		}


		//get data from rows
		Object row[]=new Object[cols];
		while(rs.next()){
			for(int i=0;i<cols;i++){
				row[i]=rs.getString(i+1);
			}
			dm.addRow(row);
		}
		t1.setModel(dm);
		con.close();
		return t1;
	}


	public static void main(String ar[])throws Exception{
		JFrame frame=new JFrame("Today's orders");
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(SalesHistory.hmsDbUrl);
		GUI.SalesHistory obj = new GUI.SalesHistory(connect);
		// JScrollPane sp=new JScrollPane(obj.getTable("menu_order"));
		JScrollPane sp=new JScrollPane(obj.getTable(""));
		frame.getContentPane().add(sp);
		frame.setBounds(200,200,400,300);
		frame.setVisible(true);       
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

}
