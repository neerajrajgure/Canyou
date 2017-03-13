package GUI;

import java.awt.Component;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.sun.prism.paint.Color;

public class Generator extends javax.swing.JDialog {
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
	java.sql.PreparedStatement ps;
	ResultSet rsCategory;
	Connection connect = null;
	String catId=null;
	
    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration 

	// public Generator() throws SQLException, ClassNotFoundException {
    public Generator() throws SQLException {
        setTitle("Customer Data");
        setModal(true);

        initComponents();
    }
    
    private void initComponents() throws SQLException {
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

		connect=ConnectionManager.getConnection();
		 
		String qryCategory = "SELECT categoryId FROM categories";
		//String qryItem="SELECT categoryId FROM item WHERE categoryId ="+categotyID;

		System.out.println("Category Query is = "+qryCategory);
        ps = connect.prepareStatement(qryCategory);
        rsCategory = ps.executeQuery();
		cbxCatId=new JComboBox();
		while (rsCategory.next()) {
			cbxCatId.addItem(rsCategory.getString("categoryId"));
		}
		cbxCatId.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Selected Number = "+cbxCatId.getSelectedItem());
				catId = cbxCatId.getSelectedItem().toString();
				//catId = Integer..toString();
				getMenuItemsForCat();
				try {
					initComponents();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnAddNewCategory = new JButton("Category");
		btnAddNewCategory.setBounds(10, 100, 80, 25);
		btnAddNewCategory.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("query generator btn");
//				queryGenerator();
				try {
					AddNewCategory ac = new AddNewCategory();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnAddNewCategory);
		
		btnAddNewItem = new JButton("Item");
		btnAddNewItem.setBounds(180, 100, 80, 25);
		panel.add(btnAddNewItem);
		btnAddNewItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddNewItem ai = new AddNewItem();
				ai.AddNewItem();
			}
		});
		
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();


        Vector colNames = new Vector();

        // String[] strColNames = new String []{ "cid-1", "Fname", "Lname", "Address", "phonenum", "phone", "emailid", "DOB", "flag"};

        String[] strColNames = new String []{ "itemId", "itemName", "categoryId", "price", "secCategoryId"};
        String str;
        for(int j = 0; j < strColNames.length; j++) {
            colNames.add(strColNames[j]);
        }
        // setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Vector<Vector> searchlist = getMenuItemsForCat();
        jTable1.setModel(new javax.swing.table.DefaultTableModel( searchlist, colNames));
        jScrollPane1.setViewportView(jTable1);

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(new ActionListener() {
        	long itemid;

        	public void actionPerformed(ActionEvent e) {
                for (int row : jTable1.getSelectedRows()) {
                    itemid = new Long(jTable1.getValueAt(row ,0).toString()).longValue();
                    GUI.CafeBill.itemid = itemid;
                    ItemInfo ii = new ItemInfo();
                    ii.setFlag(true);
                    System.out.println("item id on select in generator form : " +itemid  );
                    DefaultTableModel dm = (DefaultTableModel)jTable1.getModel();
                    while(dm.getRowCount() > 0)
                    {
                        dm.removeRow(0);
                    }
                    dispose();
                }
            }
        });


        JButton btnSelect2 = new JButton("Select2");
        btnSelect.addActionListener(new ActionListener() {
        	long cid;

        	public void actionPerformed(ActionEvent e) {
                for (int row : jTable1.getSelectedRows()) {
                    cid = new Long(jTable1.getValueAt(row ,0).toString()).longValue();
                    GUI.CafeBill.cid = cid;
                    System.out.println("Test ... 2 ......Customre ids on select in CustomeRetrivalform : " +cid  );
                    DefaultTableModel dm = (DefaultTableModel)jTable1.getModel();
                    while(dm.getRowCount() > 0)
                    {
                        dm.removeRow(0);
                    }
                    dispose();
                }
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 803, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(btnSelect)
                                        .addComponent(cbxCatId)
                                        .addComponent(btnAddNewCategory)
                                        .addComponent(btnAddNewItem)
                                        )
                                )
                        .addContainerGap(20, Short.MAX_VALUE))
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnSelect)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cbxCatId)
                        .addComponent(btnAddNewCategory)
                        .addComponent(btnAddNewItem)
                        )
                );

        getContentPane().setLayout(layout);

        pack();
        
	}


	 Vector<Vector> getMenuItemsForCat() {

		 Vector<Vector> searchlist = new Vector<Vector>();

         try
         {
             String querySelectPart1 = "SELECT * FROM item where categoryId ="+catId+";";
             String queryCustSearch = querySelectPart1;
             
             System.out.println("Query for cust search: " + queryCustSearch);
             ps = connect.prepareStatement(queryCustSearch);
             ResultSet rs = ps.executeQuery();
             //search_info si = new search_info();
             
             while(rs.next())
             {
                 ItemInfo ii = new ItemInfo();
                 ii.setItemId(rs.getString("itemId"));
                 ii.setItemName(rs.getString("itemName"));
                 ii.setCategoryId(rs.getString("categoryId"));
                 ii.setPrice(rs.getString("price"));
                 ii.setSecCategoryId(rs.getString("secCategoryId"));

                 Vector searchData = new Vector();
                 searchData.add(ii.getItemId());
                 searchData.add(ii.getItemName());
                 searchData.add(ii.getCategoryId());
                 searchData.add(ii.getPrice());
                 searchData.add(ii.getSecCategoryId());

                 System.out.println( "Recordset data: itemId = " + ii.getItemId() + "Item Name = " + ii.getItemName( ));
                 searchlist.add(searchData);
             }
         }
         catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

         System.out.println(" vector length is :" + searchlist.size());
         
         return searchlist;
	 }

}