package GUI;

import java.awt.Component;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;
import com.sun.prism.paint.Color;

public class Generator implements ActionListener{
	static String stringMode,qryP2,stringTableName,stringWhr,stringWhrCons,qryP6;
	static String FinalQuery;
	JFrame frame;
	JPanel panel; 
	JLabel lblCatId;
	JButton btnAddNewCategory;
	//String country[];
	JComboBox cbxCatId;
	JButton btnAddNewItem;
	JTextArea txtFianlQuery;
	boolean flag = false;
	JTable tblCategory;

	public void Generator() throws SQLException, ClassNotFoundException {
		frame = new JFrame("Query Generator");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		frame.add(panel);
		panel.setLayout(null);


		lblCatId = new JLabel("Select Category Id");
		lblCatId.setBounds(10, 10, 80, 25);
		panel.add(lblCatId);

		int categotyID=0,itemID=0;
		java.sql.PreparedStatement ps;
		ResultSet rsCategory;
		Connection connect = null;
		connect=ConnectionManager.getConnection();
		 
		String qryCategory = "SELECT categoryId FROM categories";
		//String qryItem="SELECT categoryId FROM item WHERE categoryId ="+categotyID;

		System.out.println("Category Query is = "+qryCategory);
        ps = connect.prepareStatement(qryCategory);
        rsCategory = ps.executeQuery();


        int ids;
		/*Vector<String> vectorCategory = new Vector<String>();
		//ArrayList<String> arrayItem = new ArrayList<String>();
		Vector<String> columnNames = new Vector<String>();
        columnNames.add("categoryId");
        columnNames.add("categoryName");
        columnNames.add("imageIcon");
*/
		int i = 1;
		cbxCatId=new JComboBox();
		while (rsCategory.next()) {
			//ids = rsCategory.getInt(1);
			//vectorCategory.add(rsCategory.getString(i++));
			//vectorCategory.add(rsCategory.getInt(1), qryCategory);
			//vectorCategory.add(rsCategory.getString(2));
			//vectorCategory.add(rsCategory.getString(3));
			cbxCatId.addItem(rsCategory.getString("categoryId"));
		}
		cbxCatId.setBounds(110, 10, 160, 25);    
		panel.add(cbxCatId);        


		btnAddNewCategory = new JButton("Insert New Category");
		btnAddNewCategory.setBounds(10, 100, 80, 25);
		btnAddNewCategory.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("query generator btn");
//				queryGenerator();
				AddNewCategory ac = new AddNewCategory();
				try {
					ac.AddNewCategory();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnAddNewCategory);
		
		btnAddNewItem = new JButton("Add New Item");
		btnAddNewItem.setBounds(180, 100, 80, 25);
		panel.add(btnAddNewItem);
		btnAddNewItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddNewItem ai = new AddNewItem();
				ai.AddNewItem();
			}
		});
		
		//tblCategory.setBounds(null);
		panel.add(tblCategory);
		frame.setVisible(true);
	}

	private Component getInt(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}