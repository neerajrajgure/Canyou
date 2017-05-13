package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import javax.swing.*;

public class AddNewCategory {
	JFrame frame;
	JPanel panel;
	JLabel lblCatId, lblCatName, lblIconPath;
	JTextField txtCatId, txtCatName, txtPath;
	JTextArea txtFianlQry;
	JButton btnAddItem, btnClear, btnBrowse;
	JFileChooser chooser;
	String fileID, strCatId, strCatName, strPath, strQry;
	int id;
	int newCatId = 0;

	java.sql.Connection con;
	ResultSet rs;
	PreparedStatement ps;

	public AddNewCategory() throws SQLException {
		// TODO Auto-generated constructor stub
		frame = new JFrame("Add New category");
		frame.setSize(350, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();

		frame.add(panel);
		panel.setLayout(null);
		ItemInfo ii = new ItemInfo();
		if(ii.flag==true){
			System.out.println("Edit Mode");
		}
		else{
			System.out.println("Add new mode");
		}

		//Database Connection -->
		con=ConnectionManager.getConnection();
		String qry = "SELECT MAX(categoryId) FROM categories";
		ps = con.prepareStatement(qry);
		rs=ps.executeQuery(qry);

		while(rs.next()){
			strCatId = rs.getString(1);
			try {
				newCatId = new Integer(strCatId).intValue();
				newCatId++;
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println(strCatId);
		}

		lblCatId = new JLabel("Category Id");
		lblCatId.setBounds(10, 10, 80, 25);
		panel.add(lblCatId);

		txtCatId = new JTextField();
		txtCatId.setBounds(155, 10, 150, 20);
		txtCatId.setEditable(false);
		panel.add(txtCatId);

		lblCatName = new JLabel("Category Name");
		lblCatName.setBounds(10, 30, 80, 25);
		panel.add(lblCatName);

		txtCatName = new JTextField();
		txtCatName.setBounds(155, 30, 150, 20);
		panel.add(txtCatName);

		lblIconPath=new JLabel("Select Icon");
		lblIconPath.setBounds(10, 50, 80, 25);
		panel.add(lblIconPath);

		txtPath = new JTextField();
		txtPath.setBounds(155, 50, 150, 20);
		panel.add(txtPath);

		btnBrowse = new JButton("..here");
		btnBrowse.setBounds(200,50,40,20);
		panel.add(btnBrowse);

		btnBrowse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads")); //Downloads Directory as default
		        chooser.setDialogTitle("Select Location");
		        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);

		        if (chooser.showSaveDialog(btnBrowse) == JFileChooser.APPROVE_OPTION)
		        { 
		            fileID = chooser.getSelectedFile().getPath();
		            txtPath.setText(fileID);
		        }
			}
		});

		btnAddItem = new JButton("Add Category");
		btnAddItem.setBounds(40, 120, 100, 30);
		panel.add(btnAddItem);
		btnAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				strCatId = txtCatId.getText();
				strCatName = txtCatName.getText();
				strPath = txtPath.getText();
				strQry = "INSERT INTO categories (categoryId,categoryName,imageIcon) VALUES("+newCatId+", '"+strCatName+"', '"+strPath+"')";
				System.out.println("Add Category ="+strQry);
			}
		});

		btnClear  = new JButton("Clear");
		btnClear.setBounds(160, 120, 100, 30);
		panel.add(btnClear);
		
		txtFianlQry  = new JTextArea("");
		txtFianlQry.setBounds(0, 180, 150, 150);
		panel.add(txtFianlQry);
		txtFianlQry.setText(strQry);

		frame.setVisible(true);

	}

}
