package GUI;

/**
 * menuExpansionPane is the Panel where the buttons will go after selecting the category from the left side
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
// import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextArea;


public class CafeBill extends JFrame {

	private JPanel contentPane;
	private JPanel categoryPane;
	private JPanel menuExpansionPane;
	private JPanel costPane;
	private JPanel frequentItemsPane;
    private JList list;
    private DefaultListModel<String> listModel;
    private ArrayList<String> categories;
    private ArrayList<ArrayList<MenuItem>> menuItems;
    JLabel lblSubtotal_1 = new JLabel("Subtotal");
    final JLabel lblSubtotal = new JLabel("00");
    final JLabel lblTotal_1 = new JLabel("Total with tax ");
    final JLabel lblTotal = new JLabel("00");
    final JLabel lblTax1_1 = new JLabel("Tax 1 (0.01)");
    final JLabel lblTax1 = new JLabel("00");
    final JLabel lblTax2_1 = new JLabel("Tax 1 (0.02)");
    final JLabel lblTax2 = new JLabel("00");
    final JLabel lblTax3_1 = new JLabel("Tax 1 (0.05)");
    final JLabel lblTax3 = new JLabel("00");
    
    DefaultTableModel dataModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeBill frame = new CafeBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */	public CafeBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		categoryPane = new JPanel();
		categoryPane.setPreferredSize(new Dimension(200,800));
		categoryPane.setBorder(BorderFactory.createLineBorder(Color.black));
		menuExpansionPane = new JPanel();
		menuExpansionPane.setPreferredSize(new Dimension(200,800));
		menuExpansionPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		costPane = new JPanel();
		costPane.setPreferredSize(new Dimension(600,800));
		costPane.setBorder(BorderFactory.createLineBorder(Color.black));
		frequentItemsPane = new JPanel();
		frequentItemsPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(categoryPane, BorderLayout.WEST);
		contentPane.add(menuExpansionPane, BorderLayout.CENTER);
		contentPane.add(costPane, BorderLayout.EAST);
		contentPane.add(frequentItemsPane, BorderLayout.SOUTH);
		
		
		
		
		final JTextArea textArea = new JTextArea();
		
		listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        setCategoryPane();
        //list.addListSelectionListener((ListSelectionListener) this);

		//JLabel lblNewLabel_4 = new JLabel("Total Price");
		
		
		/*
		 * This is the common action for every button click
		 * Add an entry to the billing list and recalculate the subtotal.
		 * 
		 * 
		 * */


		
		//Change the index to number of categories in the database
		
		
		
		//GroupLayout gl_categoryPane = new GroupLayout(categoryPane);
		//GroupLayout gl_contentPane = new GroupLayout(contentPane);
		/*
		gl_categoryPane.setHorizontalGroup(gl_categoryPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnCtgCoffee)
				.addGap(18)
				.addComponent(btnCtgSandwich)
				);
		gl_categoryPane.setVerticalGroup(gl_categoryPane.createSequentialGroup()
				.addComponent(btnCtgCoffee,GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(btnCtgSandwich,GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				);
	*/
		//setMenuExpansionPane();
		setCostPane();
		
		
		//contentPane.setLayout(gl_contentPane);
	}
	 public void setCategoryPane(){
		 /*
		  * Set Layout
		  */
 			GridLayout categoryLayout = new GridLayout(4,1); // Change Hardcoded value
 			
 			categoryPane.setLayout(categoryLayout);
		 
			JButton btnCtgCoffee = new JButton();
	        ImageIcon img = new ImageIcon("./src/images/Coffee.png");
	        btnCtgCoffee.setIcon(img);

			
			btnCtgCoffee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					ImageIcon img1 = new ImageIcon("./src/images/chicken.png");
//			        btnCtgCoffee.setIcon(img1);
			        
					setMenuExpansionPaneForCoffee();
				/*
					textArea.append("Cheese Burger  Rs 150 \n");
					listModel.addElement("Cheese Burger 1 Rs.150 Rs.150\n");
					float subTotal;
					String tempStr;
					 
						tempStr = lblSubtotal.getText();
						subTotal = Float.parseFloat(tempStr);
						subTotal += 150;
						lblSubtotal.setText(Float.toString(subTotal) );
						*/
					
				}
			});
			
			JButton btnCtgSandwich = new JButton();
	        ImageIcon imgSandwitch = new ImageIcon("./src/images/Sandwich.png");
	        btnCtgSandwich.setIcon(imgSandwitch);
	        
			JButton btnCtgBurger = new JButton();
	        ImageIcon imgBurger = new ImageIcon("./src/images/Burger.png");
	        btnCtgBurger.setIcon(imgBurger);

			btnCtgSandwich.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
//					ImageIcon img1 = new ImageIcon("./src/images/chicken.png");
//			        btnCtgSandwich.setIcon(img1);
			        
					setMenuExpansionPaneForSandwich();
					
				/*
					textArea.append("Cheese Burger  Rs 150 \n");
					listModel.addElement("Cheese Burger 1 Rs.150 Rs.150\n");
					float subTotal;
					String tempStr;
					 
						tempStr = lblSubtotal.getText();
						subTotal = Float.parseFloat(tempStr);
						subTotal += 150;
						lblSubtotal.setText(Float.toString(subTotal) );
						*/
					
				}
			});
			
			JButton btnCtgSoftDrink = new JButton();
	        ImageIcon imgSoftDrink = new ImageIcon("./src/images/SoftDrink.png");
	        btnCtgSoftDrink.setIcon(imgSoftDrink);
			categoryPane.add(btnCtgCoffee);
			categoryPane.add(btnCtgSoftDrink);
			categoryPane.add(btnCtgSandwich);
			categoryPane.add(btnCtgBurger);

	 }
	public void setCostPane(){
		/*
		 * Set Layout
		 */
		//BoxLayout gl_costPane = new BoxLayout(costPane,BoxLayout.PAGE_AXIS);

		GridBagLayout gl_costPane = new GridBagLayout();
		costPane.setLayout(gl_costPane);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		final String[] columnNames = {"Sr_No","Item", "Quantity","Unit Price","Total Price"};
	      dataModel = new DefaultTableModel(0,0){
	    	  
	    	    @Override
	    	       public boolean isCellEditable(int row, int col) {
	    	            //Note that the data/cell address is constant,
	    	            //no matter where the cell appears onscreen.
	    	            if (col == 2 || col==1) {
	    	                return true;
	    	            } else {
	    	                return false;
	    	            }
	    	       }

	    	    
    	  
	      };
	      
	     dataModel.setColumnIdentifiers(columnNames);
	     
	      JTable table = new JTable(dataModel);
	      JScrollPane scrollpane = new JScrollPane(table);
	      table.setFillsViewportHeight(true);
	      @SuppressWarnings("serial")
		Action action = new AbstractAction()
	      {
	    	    public void actionPerformed(ActionEvent e)
	    	    {
	    	    	if (table.getSelectedColumn() == 2) {
	    	        TableCellListener tcl = (TableCellListener)e.getSource();
					Float priceVal =(Float) table.getValueAt(tcl.getRow(), 3);
					Float newTotalPrice =Float.parseFloat((String)tcl.getNewValue())* priceVal;
					dataModel.setValueAt(newTotalPrice, tcl.getRow(), 4);
	    	    	}

	    	    }
	    	};

    	TableCellListener tcl = new TableCellListener(table, action);
    	c.gridx = 0;
    	c.gridy = 0;
		costPane.add(scrollpane,c);
		JButton btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        if (table.getSelectedRow() != -1) {
			            // remove selected row from the model
		        	dataModel.removeRow(table.getSelectedRow());
		        }
				
				
			}
		});
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 if (table.getSelectedRow() != -1) {
						Object [] data = {"","","","0","",""};
					//dataModel.setValueAt(100, 1, 1);
					//dataModel.addRow(data);
					//dataModel.insertRow(table.getSelectedRow(), data);
						dataModel.insertRow(table.getSelectedRow()+1, data);
					 }
					
					
				}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		costPane.add(btnDeleteRow,c);
		c.gridx = 1;
		c.gridy = 1;
		costPane.add(btnAddRow,c);
		c.gridx = 0;
		c.gridy = 2;
		costPane.add(lblSubtotal_1,c);
		c.gridx = 1;
		c.gridy = 2;
		costPane.add(lblSubtotal,c);

		c.gridx = 0;
		c.gridy = 3;
		costPane.add(lblTax1_1,c);
		c.gridx = 1;
		c.gridy = 3;
		costPane.add(lblTax1,c);
		
		c.gridx = 0;
		c.gridy = 4;
		costPane.add(lblTax2_1,c);
		c.gridx = 1;
		c.gridy = 4;
		costPane.add(lblTax2,c);
		
		c.gridx = 0;
		c.gridy = 5;
		costPane.add(lblTax3_1,c);
		c.gridx = 1;
		c.gridy = 5;
		costPane.add(lblTax3,c);
		
		c.gridx = 0;
		c.gridy = 6;
		costPane.add(lblTotal_1,c);
		c.gridx = 1;
		c.gridy = 6;
		costPane.add(lblTotal,c);

		JButton btnPrintBill = new JButton("Submit Order");
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	/*			
				textArea.append("Cheese Burger  Rs 150 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
	*/				
			}
		});
		c.gridx = 0;
		c.gridy = 7;
		costPane.add(btnPrintBill,c);
	
	
	}
	
	/*
	 *  This function will update the billing pannel depending on the item selected
	 *  The input passed it the name
	 *  Will need updating if the number of items are increased ..prices changes etc
	 */
	
	
	public void itemAddedToBill(String item1)
	{
		
		//if (item1 ) // Make sure nul pointer is not passed
		int id = dataModel.getColumnCount();
		id = dataModel.getRowCount();
		int cost = 0;
		
		id++;
								
		String s1 = new String(Integer.toString(id));
		
		
			switch (item1)
			{
			case "veg sandwich":
				cost = 150 ;
				
				break;
			case "coffee1":
				cost = 100;
				
				break;
			case "coffee2" :
				cost =200;
				break;
			case "chicken sandwich":
				cost = 175;
				break;
			}	
			
			String[] socrates = { s1,  item1, "1", Integer.toString(cost), Float.toString(cost * 1) };
			dataModel.addRow(socrates);
			
		calculateTotal();
		
	}
	/*
	 *  Calculating the total with tax
	 *  The amount is .05
	 * 
	 */
	
	public void calculateTotal()
	{
		float totalAmt = 0 ,ftax1 = 0,ftax2 = 0 ,ftax3 = 0, tot =0 , totAmtWithTax =0 ; 
		int iRowCnt, iQty, iNumber, j,k;
		iRowCnt = dataModel.getRowCount();
		Object obj;
		
		//j = dataModel.findColumn("Quantity");
		//k = dataModel.findColumn("Unit Price");
		j  = dataModel.findColumn("Total Price");
		
		for (int i=0; i< iRowCnt; i++)
		{
		 obj     = 	 dataModel.getValueAt(i, j) ;
		 tot = Float.parseFloat(obj.toString());
		 
		 totalAmt =  totalAmt + tot;  
		}
		
		ftax1 = (float) (totalAmt * 0.01);
		ftax2 = (float) (totalAmt * 0.02);
		ftax3 = (float) (totalAmt * 0.05);
		totAmtWithTax =  (float) (totalAmt + (totalAmt * 0.5));
		
		lblSubtotal.setText(Float.toString(totalAmt));
		
		lblTax1.setText(Float.toString(ftax1));
		lblTax2.setText(Float.toString(ftax2));
		lblTax3.setText(Float.toString(ftax3));		
		lblTotal.setText (Float.toString(totAmtWithTax));
	}
	
	/*
	 * Button choice
	 * Update accordingly
	 * 
	 * 
	 * */
	public void setMenuExpansionPaneForSandwich(){
		 /*
		  * Set Layout
		  */
		//ImageIcon img1 = new ImageIcon("./src/images/Coffee.png");
        //btnCtgSandwich.setIcon(img1);
        
			GridLayout categoryLayout = new GridLayout(4,2); // Change Hardcoded value
			menuExpansionPane.removeAll();
			menuExpansionPane.updateUI();
			menuExpansionPane.setLayout(categoryLayout);
		 
			JButton btnExpVegSandwich = new JButton();
	        //ImageIcon imgExpVegSandwich = new ImageIcon("./src/images/veg.png");
	        //btnExpVegSandwich.setIcon(imgExpVegSandwich);
				  btnExpVegSandwich.setText("Veg Sandwich");
			
	        btnExpVegSandwich.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					itemAddedToBill("veg sandwich");
				}
			});
			
	        JButton btnExpGrillVegSandwich = new JButton();
	        //ImageIcon imgExpGrillVegSandwich = new ImageIcon("./src/images/grillVeg.png");
	        //btnExpGrillVegSandwich.setIcon(imgExpGrillVegSandwich);
				  btnExpGrillVegSandwich.setText("Grill Veg Sandwich");;

			
	        btnExpGrillVegSandwich.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					itemAddedToBill("Chicken Sandwich");
				}
			});

	        JButton btnExpChkSandwich = new JButton();
	        //ImageIcon imgExpChkSandwich = new ImageIcon("./src/images/chicken.png");
	        //btnExpChkSandwich.setIcon(imgExpChkSandwich);
			  btnExpChkSandwich.setText("Chicken Sandwich");;

			
	        btnExpChkSandwich.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					itemAddedToBill("coffee2");
				
				}
			});

	        JButton btnExpGrillChkSandwich = new JButton();
	        //ImageIcon imgExpGrillChkSandwich = new ImageIcon("./src/images/Coffee.png");
	        //btnExpGrillChkSandwich.setIcon(imgExpGrillChkSandwich);
				

			
	        btnExpGrillChkSandwich.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					itemAddedToBill("Grill Chicken");
				}
			});

	        menuExpansionPane.add(btnExpVegSandwich);
	        menuExpansionPane.add(btnExpGrillVegSandwich);
	        menuExpansionPane.add(btnExpChkSandwich);
	        

	 }


	public void setMenuExpansionPaneForCoffee(){
		 /*
		  * Set Layout
		  */
		//ImageIcon img1 = new ImageIcon("./src/images/Coffee.png");
       //btnCtgSandwich.setIcon(img1);
       
			GridLayout categoryLayout1 = new GridLayout(4,2); // Change Hardcoded value
			menuExpansionPane.removeAll();
			menuExpansionPane.updateUI();
			menuExpansionPane.setLayout(categoryLayout1);
		 
			JButton btnExpCoffee1 = new JButton();
	      //  ImageIcon imgExpVegSandwich1 = new ImageIcon("./src/images/coffee.png");
	      //  btnExpCoffee1.setIcon(imgExpVegSandwich1);
			  btnExpCoffee1.setText("Coffee1");
	        //btnExpCoffee1.setLabel("Coffee1"); 

			
	        btnExpCoffee1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Object [] data = {(int)100,(String)"Latte",(int)1,(float)150.0,(float)150.0};
					//dataModel.setValueAt(100, 1, 1);
	itemAddedToBill("coffee1");
					//dataModel.addRow(data);
					
				}
			});

			JButton btnExpCoffee2 = new JButton();
	    //    ImageIcon imgExpVegSandwich2 = new ImageIcon("./src/images/coffee.png");
	    //    btnExpCoffee1.setIcon(imgExpVegSandwich2);
	        btnExpCoffee2.setText("Coffee2");
	        //btnExpCoffee1.setLabel("Coffee2"); 

			
	        btnExpCoffee2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					itemAddedToBill("coffee2");
				}
			});

	        
	        menuExpansionPane.add(btnExpCoffee2);
	        menuExpansionPane.add(btnExpCoffee1);
	        

	 }

}