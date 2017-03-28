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
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import javax.swing.table.TableColumn;
import javax.swing.JDialog;
import java.awt.Dialog;

import javax.swing.JOptionPane;

public class CafeBill extends JFrame {

	private JPanel contentPane;
	private JPanel categoryPane;
	private JPanel menuExpansionPane;
	private JPanel costPane;
	private JPanel frequentItemsPane;
	private JList list;
	private DefaultListModel<String> listModel;
	private ArrayList<String> categories;
	private ArrayList<String> imageIcons;
	private ArrayList<ArrayList<MenuItem>> menuItems;
	final JLabel lblSubtotal_1 = new JLabel("Subtotal");
	final JLabel lblSubtotal = new JLabel("");
	final JLabel lblTotal_1 = new JLabel("");
	final JLabel lblTotal = new JLabel("");
	final JLabel lblTax1_1 = new JLabel("");
	final JLabel lblTax1 = new JLabel("");
	final JLabel lblTax2_1 = new JLabel("");
	final JLabel lblTax2 = new JLabel("");
	final JLabel lblTax3_1 = new JLabel("");
	final JLabel lblTax3 = new JLabel("");
	final JLabel lblDiscountvalue = new JLabel();
	final JLabel lblDiscount_1 = new JLabel("Discount" + "( 0.0 % )" ); //new JLabellblDiscount_1.set("Discount" + "( " + lblDiscountvalue.getText() + " % )" );
	final JLabel lblDiscount = new JLabel("");
	final static String db_name= "HMS";
	final static String username = "billing";
	final static String password = "hmsbilling";
	final static String hmsDbUrl ="jdbc:mysql://localhost/"+db_name+"?"+ "user=" + username + "&" + "password=" + password;
	final int PAY_OPTION_CANCEL_CLICKED = -1;
	//    final JLabel username = new JLabel("username");
	String currEmpName;
	private static Connection connect = null;
	//private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private int index;
	private int index2;
	long currEmpID=0;
    public JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem ViewTotalMenuItem;
    private JMenuItem ViewSaleMenuItem;
    private JMenuItem CustomerLookUpMenuItem;
    private JMenuItem AboutMenuItem;

	//String ExtraChoiceCombo[] = { " 1  cheese", " 2 coffee  ", "3 extra ", " 4 Four",
	// " 5 Item Five" };
	private JComboBox<String> cExtraItem;
	//private ComboBoxEditor<String> cExtraItem1;

	DefaultTableModel dataModel;
	float totalTax=0;
	float taxCal=0;

	//JTableX table ;
	JTable table ;
	ReceiptPrinting rp;
	KitchenReceiptPrinting krp;
	public long nextOid = 0;
	public static long currentOid = 0; // This need to change pass oid to the constructor of the classes that need it (instead of accessing the static variable)
	public static long cid = 0; // This need to change pass cid to the constructor of the classes that need it (instead of accessing the static variable)
	public float db_tax1=(float) 0.0;
	public float db_tax2 = (float)0.0;
	public float db_tax3 =(float) 0.0;
	public float db_totalTaxPerc = (float)0.0;
	/**
	 * Launch the application.
	 */
	/*
    public static void main(String[] args) {
    //    EventQueue.invokeLater(new Runnable() {
      //      public void run() {
                try {
                    CafeBill frame = new CafeBill();
                    frame.setVisible(true);
                    frame.pack();
                   try {
                        frame.oid = frame.getNextOid();
                        System.out.println(" Oid in frame is  : "+ frame.oid);
                    } catch(Exception e) {
                        // Call the Fall back method to use text files as the backups
                        e.printStackTrace();
                    }
                   frame.rp = new ReceiptPrinting(frame);
                   frame.getTax();

                   frame.showOpenLoginScreen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
          //  }
        //});
    }
	 */
	public void init() {
		try {
			// CafeBill frame = new CafeBill();
            setTitle("The Hive - " + currEmpName); // Title name with Employee name
			setVisible(true);
			pack();
			try {
				nextOid = getNextOid();
				currentOid = nextOid;
				// cid= getNextCid();
				System.out.println("Current Oid in frame is  : "+ currentOid);
				System.out.println("Cid in frame is  : "+ cid);
			} catch(Exception e) {
				// Call the Fall back method to use text files as the backups
				e.printStackTrace();
			}
			rp = new ReceiptPrinting(this);
			krp = new KitchenReceiptPrinting(this);
			getTax();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void showOpenLoginScreen()
	{
		this.dispose();
		Login loginScreen = new Login(this);
		loginScreen.pack();
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - loginScreen.getWidth()) / 2;
		final int y = (screenSize.height - loginScreen.getHeight()) / 2;
		loginScreen.setLocation(x, y);
		loginScreen.setSize(250, 110);
		loginScreen.setVisible(true);
	}

	int showOpenPayScreen()
	{
		CafeBill cfbl= new CafeBill();
		Payment objPay = new Payment(this);
		// objPay.createAndShowGUI();
		System.out.println("Cash or CC: " + Payment.payCashOrCC);
		objPay.pack();
		//		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		//		final Dimension screenSize = toolkit.getScreenSize();
		//		final int x = (screenSize.width - objPay.getWidth()) / 2;
		//		final int y = (screenSize.height - objPay.getHeight()) / 2;
		objPay.setLocation(750, 150);
		objPay.setSize(300, 250);
        System.out.println("Cancel button station at - 1 = " + objPay.cancelPressed);
		objPay.setVisible(true);
		System.out.println("Cancel button station at - 2 = " + objPay.cancelPressed);
		if(objPay.cancelPressed == true) {
		    return PAY_OPTION_CANCEL_CLICKED;
		}
		return 1;
	}

	
	public void connectDatabase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean getTax() {
		connectDatabase();
		try {
            preparedStatement = connect.prepareStatement("SELECT * FROM "+db_name+".tax where startDate <= CURDATE() and endDate >= CURDATE()");
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				/*
//              db_tax1 = rs.getFloat("serviceTax");
                String name_test = rs.getString("name");
                System.out.println("Name : " );
                System.out.println(name_test );
              db_tax2 = rs.getFloat("percentValue");
              System.out.println("db_tax2: " );
              System.out.println(db_tax2 );
//              db_tax3 = rs.getFloat("service Charge");
            System.out.println(("rsString = " + rs.getString("name")));
				 */
				//                                System.out.println("db_tax2: " );
				String db_tax_name = rs.getString("name");

				System.out.println("My Str = " + "'" +  db_tax_name + "'");
				//                 if (rs.getString("name").equals("serviceTax"))
				if (db_tax_name.compareTo("Service Tax")==0)
				{
					db_tax1 = rs.getFloat("percentValue");
					lblTax1_1.setText(db_tax_name+ "(" + db_tax1 + "%)");
					System.out.println("In tax if :"+ db_tax1);
				}
				else if (db_tax_name.compareTo("VAT")==0)
				{
					db_tax2 = rs.getFloat("percentValue");
					lblTax2_1.setText(db_tax_name + "(" + db_tax2 + "%)");
					System.out.println("In tax else 1 : "+ db_tax2 );
				}
				else if(db_tax_name.compareTo("Service Charge") == 0)
				{
					db_tax3 = rs.getFloat("percentValue");
					lblTax3_1.setText(db_tax_name+ "(" + db_tax3 + "%)");
					System.out.println("Ins tax else 2: "+ db_tax3 );
				}
			}
			//            System.out.println("db_tax1: " );
			//            System.out.println(db_tax1 );
			db_totalTaxPerc = db_tax1 + db_tax2 + db_tax3 ;
			lblTotal_1.setText("Total");
			System.out.println("totalTax:- " + db_totalTaxPerc );
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
		return true;
	}
	public long getNextOid() {
		//connectDatabase();
		long maxoid=0;
		try {
			preparedStatement = connect.prepareStatement("SELECT MAX(oid) FROM menu_order WHERE orderDate=CURRENT_DATE()");
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				maxoid=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ++maxoid;
	}

	public ResultSet readDBMS(String query){
		try {
			// This will load the MySQL driver, each DB has its own driver
			preparedStatement = connect.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();


			//displayResultSet(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;

	}

	/**
	 * Create the frame.
	 *
	 */
	public CafeBill() {
		CouponDiscount cd= new CouponDiscount(this);
		connectDatabase();
		setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/images/THE_HIVE_Logo.jpg"))); //Title logo
		categories = new ArrayList<String>();
		imageIcons = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		categoryPane = new JPanel();
		categoryPane.setPreferredSize(new Dimension(200,800));
		categoryPane.setBorder(BorderFactory.createLineBorder(Color.black));
		menuExpansionPane = new JPanel();
		menuExpansionPane.setPreferredSize(new Dimension(200,800));
		menuExpansionPane.setBorder(BorderFactory.createLineBorder(Color.black));
		costPane = new JPanel();
		costPane.setPreferredSize(new Dimension(800,800));
		costPane.setBorder(BorderFactory.createLineBorder(Color.black));
		frequentItemsPane = new JPanel();
		frequentItemsPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(categoryPane, BorderLayout.WEST);
		contentPane.add(menuExpansionPane, BorderLayout.CENTER);
		contentPane.add(costPane, BorderLayout.EAST);
		contentPane.add(frequentItemsPane, BorderLayout.SOUTH);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        ViewTotalMenuItem = new JMenuItem("View Total");
        ViewSaleMenuItem = new JMenuItem("View Sale");
        CustomerLookUpMenuItem = new JMenuItem("Customer LookUp");
        AboutMenuItem = new JMenuItem("About");
        fileMenu.add(ViewTotalMenuItem);
        fileMenu.add(ViewSaleMenuItem);
        fileMenu.add(CustomerLookUpMenuItem);
        fileMenu.add(AboutMenuItem);
        ViewTotalMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String query = ("select round(sum(totalAmount)) from menu_order where DATE(orderDate)=CURDATE()");
                    preparedStatement = connect.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    String sum = rs.getString(1);
                    System.out.println(" TOTAL SALE IS: " +sum);
                    JOptionPane.showMessageDialog ( null, "Total Sale of Today is Rs " + sum, "TOTAL SALE AMOUNT", JOptionPane.PLAIN_MESSAGE);
                }
                catch (ClassNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
        });
        CustomerLookUpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCust();
            }
        });

        ViewSaleMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewSaleFrame();
            }
        });

        AboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("./src/GUI/HiveCafe.properties");
                    FileInputStream fileInput = new FileInputStream(file);
                    Properties properties = new Properties();
                    properties.load(fileInput);
                    fileInput.close();
                    String majorVer = properties.getProperty("MajorVersion");
                    System.out.println("Data ==> " + ": " + majorVer);
                    String minorVer = properties.getProperty("MinorVersion");
                    System.out.println("Data ==> " + ": " + minorVer);
                    JOptionPane.showMessageDialog ( null, "The Hive - Billing System : " + majorVer + "." + minorVer, "HIVE CAFE", JOptionPane.PLAIN_MESSAGE);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuBar.add(fileMenu);
		/*
		 * Read Categories and Items from Database
		 */
		ResultSet r = readDBMS("Select categoryName, imageIcon from "+db_name+".categories");
		try {
			while (r.next()) {
				String categoryName = r.getString("categoryName");
				String imageIcon = r.getString("imageIcon");
				categories.add(categoryName);
				imageIcons.add(imageIcon);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			try {
				r.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
        categories.add("Coffee");
        categories.add("Sandwich");
        imageIcons.add("./src/images/Coffee.png");
        imageIcons.add("./src/images/sandwich.png");
		 */
		/*
		 * Read Items from Database
		 */
		menuItems = new ArrayList<ArrayList<MenuItem>>();
		for(int i = 0;i<categories.size();i++){
			ResultSet r1 = readDBMS("Select itemId, itemName, price from item I , categories C where I.categoryId = C.categoryId and categoryName = '"+categories.get(i)+"'");
			ArrayList<MenuItem> subcategories = new ArrayList<MenuItem>();
			try {
				while (r1.next()) {
					int itemNum = r1.getInt("itemId");
					String itemName = r1.getString("itemName");
					Float price = r1.getFloat("price");
					MenuItem m = new MenuItem();
					m.itemNum = itemNum;
					m.itemName = itemName;
					m.price = price;
					subcategories.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					r1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			menuItems.add(subcategories);
		}
		//final JTextArea textArea = new JTextArea();
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		setCategoryPane();
		setCostPane();
		//contentPane.setLayout(gl_contentPane);
	}
	public void setCategoryPane(){
		/*
		 * Set Layout
		 */
		GridLayout categoryLayout = new GridLayout(categories.size(),1);
		categoryPane.setLayout(categoryLayout);
		//Dynamically Add Buttons
		for(index=0;index<categories.size();index++){
			String b = categories.get(index);
			final JButton btn= new JButton();
			btn.setPreferredSize(new Dimension(40,40));
			btn.setName(b);
			ImageIcon img = new ImageIcon(imageIcons.get(index));
			btn.setIcon(img);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//setMenuExpansionPaneForCoffee();
					//System.out.println(menuItems.get(menuItems.indexOf(btn.getName())));
					System.out.println(btn.getName());
					ArrayList<MenuItem> expandedMenu = menuItems.get(categories.indexOf(btn.getName()));
					setMenuExpansionPane(expandedMenu);
				}
			});
			categoryPane.add(btn);

		}
	}
	public void setMenuExpansionPane(final ArrayList<MenuItem> expandedMenu ){
		/*
		 * Set Layout
		 */
		GridLayout subcategoryLayout = new GridLayout(expandedMenu.size(),1);
		menuExpansionPane.removeAll();
		menuExpansionPane.updateUI();
		menuExpansionPane.setLayout(subcategoryLayout);
		for(index2=0;index2<expandedMenu.size();index2++){
			final String item = expandedMenu.get(index2).itemName;
			final JButton btn= new JButton(item);
			btn.setText(item);
			btn.setPreferredSize(new Dimension(100,40));
			btn.setName(Integer.toString(index2));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//itemAddedToBill(item);
					String[] row = { Integer.toString(expandedMenu.get(Integer.parseInt(btn.getName())).itemNum),  item, "1", Float.toString(expandedMenu.get(Integer.parseInt(btn.getName())).price), Float.toString(expandedMenu.get(Integer.parseInt(btn.getName())) .price)};
					dataModel.addRow(row);
					calculateTotal();
				}
			});
			menuExpansionPane.add(btn);

		}
		//pack();
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
		table = new JTable(dataModel);

		// Reverting back as we dont want the whole column to have the same cell renderer
		//        JTable table = new JTable(dataModel);
		//        Trying something with the JtableX class to have cell editor for each column cell
		//       JTableX table = new JTable(dataModel);
		//       table = new JTableX(dataModel);
		//        addRowEditor(); //CHANGE
		JScrollPane scrollpane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(500,300));
		table.setFillsViewportHeight(true);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(75);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(90);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		@SuppressWarnings("serial")
		Action action = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedColumn() == 2) {
					TableCellListener tcl = (TableCellListener)e.getSource();
					float priceVal = new Float((table.getValueAt(tcl.getRow(), 3).toString())).floatValue();
					//float newTotalPrice =Float.parseFloat((String)tcl.getNewValue())* priceVal;
					float val = new Float((tcl.getNewValue().toString())).floatValue();
					float newTotalPrice = val * priceVal;
					dataModel.setValueAt(newTotalPrice, tcl.getRow(), 4);
					calculateTotal();
				}

			}
		};
		setUpquantyColumn(table, table.getColumnModel().getColumn(2));
		TableCellListener tcl = new TableCellListener(table, action);

		//Adding the table column renderor for each cell
		TableColumn cm = table.getColumnModel().getColumn(1); //hard code value
		cm.setCellEditor(new TextAreaCellRenderer());
		cm.setCellRenderer(new TextAreaCellRenderer());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		costPane.add(scrollpane,c);
		JButton btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.setSize(100, 100);
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					// remove selected row from the model
					System.out.println("Deleting row number #### " + table.getSelectedRowCount());
					dataModel.removeRow(table.getSelectedRow());
					calculateTotal();
				}
			}
		});
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.setSize(100, 100);
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = 0;
				selRow = table.getSelectedRow();
				if (selRow != -1) {
					Object [] data = {"","","1","20.0","20.0"};
					int col = 0;
					dataModel.insertRow(selRow + 1, data);
					TableColumn cm = table.getColumnModel().getColumn(1); //hard code value
					//This is cell Editor return appropriate combobox This need s to happen as soon as the table is created
					cm.setCellEditor(new TextAreaCellRenderer());
					cm.setCellRenderer(new TextAreaCellRenderer());
					calculateTotal();
				}
			}
		});
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		costPane.add(btnDeleteRow,c);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx =1;
		costPane.add(btnAddRow,c);
		c.gridx =1;
		c.gridy = 2;
		c.gridwidth = 1;
		costPane.add(lblSubtotal_1,c);
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		costPane.add(lblSubtotal,c);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		costPane.add(lblTax1_1,c);
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		costPane.add(lblTax1,c);
		c.gridx = 1;
		c.gridy = 4;
		// costPane.add(lblTax2_1,c);
		c.gridx = 3;
		c.gridy = 4;
		// costPane.add(lblTax2,c);
		c.gridx = 1;
		c.gridy = 5;
		//costPane.add(lblTax3_1,c);
		c.gridx = 3;
		c.gridy = 5;
		//costPane.add(lblTax3,c);
		c.gridx = 1;
		c.gridy = 7;
		costPane.add(lblTotal_1,c);
		c.gridx = 3;
		c.gridy = 7;
		costPane.add(lblTotal,c);
		c.gridx = 1;
		c.gridy = 6;
		costPane.add(lblDiscount_1,c);
/*
		c.gridx = 2;
		c.gridy = 6;
		costPane.add(lblDiscountvalue,c);

*/		c.gridx = 3;
		c.gridy = 6;
		costPane.add(lblDiscount,c);

		c.gridx = 12;
		c.gridy = 0;
		JButton btnPrintBill = new JButton("Submit Order");
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				PageFormat pf = job.defaultPage();
				Paper paper = new Paper();
				double margin = 8; // half inch
				System.out.println("width = " + paper.getWidth());
				System.out.println("Calc width = " + (paper.getWidth() - margin * 2));
				paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
				pf.setPaper(paper);
				int count=table.getRowCount();

				//ResultSet resultset=null;

				// showOpenPayScreen();

				//ResultSet resultset=null;    


				/*                
                Customer cust = new Customer();
                //c.DOB = null;
                cust.cid = cid;
                cust.FName ="Bill";
                cust.LName ="Gates";
                cust.phoneNum = 9873211111L;
                cust.address = "Test addr";
                cust.flag = 'N';
                cust.emailid = "abcd";
                cust.phone = "9873211111";
                cust.DOB = getCurrentDate();
                setCustomerInfo_old(cust);

				 */ 
				if(showOpenPayScreen() == PAY_OPTION_CANCEL_CLICKED) {
				    return;
				}
				//TODO: Should substring the transInfo so that the data in the db does not overflow.
				System.out.println("CafeBill::Submit_order - Payment type: " + Payment.payCashOrCC);
				System.out.println("CafeBill::Submit_order - Payment info: " + Payment.transInfo);
				System.out.println("CafeBill::Submit_order - before krp print " );
				job.setPrintable(krp, pf);
				if (job.printDialog()) {
					try {
						System.out.println(" inside try krp " );
						job.print();
					} catch (PrinterException printException) {
						System.out.println(printException);
					}
				}
				// Change Values customerId, transID, transInfo from the Credit Cash Dialog
				setMenuOrder(CafeBill.cid, Payment.payCashOrCC, Payment.transInfo, Float.parseFloat(lblDiscount.getText()), (float)CouponDiscount.couponValue, (String)CouponDiscount.DISCOUNTDEC, Float.parseFloat(lblSubtotal.getText()), db_totalTaxPerc, Float.parseFloat(lblTotal.getText()));
				int iRowCnt = dataModel.getRowCount();
				
				Connection connect = null;
				Connection con = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = null;
				String sql=null;

				int ToV = 0;
				int NoV = 0;

				try{
					// first get the total no. of visits in current table;
					sql = "SELECT totalNoVisits FROM customer WHERE cid = "+cid;
					preparedStatement = connect.prepareStatement(sql);
					System.out.println(sql);
					resultSet = preparedStatement.executeQuery();
					System.out.println("before visits ="+resultSet.getFetchSize());

					while(resultSet.next()){
						ToV = resultSet.getInt(1);
					}

					//update customer table TotalNoVisits = TotalNoVisits + 1
					sql = "UPDATE customer SET totalNoVisits = "+ToV+" + 1, lastVisit = now() where cid = "+cid;
					preparedStatement = connect.prepareStatement(sql);
					System.out.println(sql);
					preparedStatement.executeUpdate();
				}
				catch(Exception e1){
					e1.printStackTrace();
				}


				Object obj;
				Object objQty;
				String objString;
				int itemId;
				int j= dataModel.findColumn("Sr_No");
				int q = dataModel.findColumn("Quantity");
				for (int i=0; i< iRowCnt; i++)
				{
					obj = dataModel.getValueAt(i, j);
					objString =  obj.toString();
					if(objString.equals("") ){
						continue;
					}
					itemId = Integer.parseInt(obj.toString());

					objQty = dataModel.getValueAt(i, q);
					int quantity = Integer.parseInt(objQty.toString());
					for(int k = 0; k < quantity; k++)
					{
						setBillingInfo(itemId, "Test");
					}

				}
				nextOid++; // Do not change. Do not delete this line.
                System.out.println("Current oid is: " + currentOid);
				currentOid = nextOid;
                System.out.println("New oid for next transaction will be: " + nextOid);
                System.out.println("Current oid for next transaction will be: " + currentOid);

				/*                for(int i=table.getModel().getRowCount()-1;i>=0;i--)
                {
                    System.out.println(dataModel.getRowCount());
                    dataModel.removeRow(i);
                    calculateTotal();
                    menuExpansionPane.removeAll();
                    menuExpansionPane.updateUI();
                }*/

				//Code for testing Customer Relations
				//boolean flag = customerLookupExact(0, "Sam", "Smith", 0, null , null);
				//System.out.println("Cusomer exists" +flag);
				/*
                ArrayList<Customer> cArr = customerLookup(0, "Sam", "Smith", 0, null , null);
                for(int i=0;i<cArr.size();i++){
                    System.out.println(cArr.get(i).FName+" "+cArr.get(i).LName);
                }
				 */

				CouponDiscount.couponValue=0.0;

				//JOptionPane.showConfirmDialog(null, "Order is Placed", "Printing", JOptionPane.DEFAULT_OPTION);

				//new ReceiptPrinting().setVisible(true);
				// ReceiptPrinting rp = new ReceiptPrinting();
				/*
                PrinterJob job = PrinterJob.getPrinterJob();
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                PageFormat pf = job.pageDialog(aset);
                job.setPrintable(rp , pf);
                boolean ok = job.printDialog(aset);
                if (ok) {
                    try {
                        job.print(aset);
                    } catch (PrinterException ex) {
                        // The job did not successfully complete
                    }
                }
				 */

				//                PrinterJob job = PrinterJob.getPrinterJob();
				//                PageFormat pf = job.defaultPage();
				//                Paper paper = new Paper();
				//                double margin = 8; // half inch

				System.out.println("width = " + paper.getWidth());
				System.out.println("Calc width = " + (paper.getWidth() - margin * 2));
				paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
				pf.setPaper(paper);

				job.setPrintable(rp, pf);
				if (job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException printException) {
						System.out.println(printException);
					}
				}

				for(int i=table.getModel().getRowCount()-1;i>=0;i--)
				{
					System.out.println(dataModel.getRowCount());
					dataModel.removeRow(i);
					calculateTotal();
					menuExpansionPane.removeAll();
					menuExpansionPane.updateUI();
				}
				CouponDiscount.couponValue=0.0;
				new Demography().setVisible(true);
				/*				 
	                String msg = "Change Printer Name to adobe pdf And save";
	                int result = JOptionPane.showConfirmDialog(( java.awt.Component) null, (Object)msg, "Print", JOptionPane.OK_OPTION);
                 // FOR RE PRINTING RECIET,CHANGE PRINTER TO ADOBE PDF AND SAVE
				 paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
				 pf.setPaper(paper);

				 job.setPrintable(rp, pf);
				 if (job.printDialog()) {
					 try {
						 job.print();
					 } catch (PrinterException printException) {
						 System.out.println(printException);
					 }
				 }

				 */			}
		});
		c.gridx = 1;
		c.gridy = 10;
		costPane.add(btnPrintBill,c);

		JButton btnClear = new JButton("Clear Order");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Do you want to clear the screen? ";
				int result = JOptionPane.showConfirmDialog(( java.awt.Component) null, (Object)msg, "alert", JOptionPane.YES_NO_OPTION);

				if (result != JOptionPane.YES_OPTION) {
					return;
				}
				for(int i=table.getModel().getRowCount()-1;i>=0;i--)
				{
					System.out.println(dataModel.getRowCount());
					dataModel.removeRow(i);
				}
				CouponDiscount.couponValue=0.0;
				calculateTotal();
				menuExpansionPane.removeAll();
				menuExpansionPane.updateUI();
			}
		});
		c.gridx = 1;
		c.gridy = 11;
		costPane.add(btnClear,c);
		JButton btnac = new JButton("Apply Coupon & Discount");
		btnac.setSize(100, 100);
		btnac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CouponDiscount().setVisible(true);
				/*	            
				      try {
                    Class.forName("com.mysql.jdbc.Driver");
                    // Setup the connection with the DB
                    connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
                    String query="Insert into coupon(couponId,couponName,startDate,startTime,endDate,endTime,itemId,percentage) values (?,?,?,?,?,?,?,?)";
                    preparedStatement=connect.prepareStatement(query);
                    preparedStatement.setInt(1, 101);
                    preparedStatement.setString(2, "HIVE10");
                    preparedStatement.setDate(3, getCurrentDate());
                    preparedStatement.setTime(4, getCurrentTime());
                    preparedStatement.setDate(5, getCurrentDate());
                    preparedStatement.setTime(6, getCurrentTime());
                    preparedStatement.setInt(7, 1001);
                    preparedStatement.setInt(8, 100);
                    preparedStatement.executeUpdate();
                }
                catch (ClassNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
				 */
			}

			//}
		});
		c.gridx = 1;
		c.gridy = 9;
		costPane.add(btnac,c);
		JButton btncanelOrder = new JButton("Logout");
		btncanelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//code for cancel the order
				//             new CafeBill().setVisible(true);
				//System.exit(0);

				String msg = "Are you sure about Logout action? ";
				int result = JOptionPane.showConfirmDialog(( java.awt.Component) null, (Object)msg, "alert", JOptionPane.YES_NO_OPTION);

				if (result != JOptionPane.YES_OPTION) {
					return;
				}
				for(int i=table.getModel().getRowCount()-1;i>=0;i--)
				{
					System.out.println(dataModel.getRowCount());
					dataModel.removeRow(i);
				}
				CouponDiscount.couponValue=0.0;
				calculateTotal();
				menuExpansionPane.removeAll();
				menuExpansionPane.updateUI();
				// new CafeBill().disable();
				showOpenLoginScreen();
			}
		});
		c.gridx = 1;
		c.gridy = 12;
		costPane.add(btncanelOrder,c);
		JButton btnCR = new JButton("Customer Registration");
		btnCR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new  CustRegForm().setVisible(true);
			}
		});
		c.gridx = 1;
		c.gridy = 8;
		costPane.add(btnCR,c);

		JButton btnCl = new JButton("Customer Lookup");
		btnCl.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new SearchCustomer ().setVisible(true);
		    }
		});
		c.gridx = 2;
		c.gridy = 8;
		costPane.add(btnCl,c);

		JButton btndemography = new JButton("Demography");
		btndemography.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Demography().setVisible(true);
			}
		});
		c.gridx = 1;
		c.gridy = 14;
		//costPane.add(btndemography,c);
/*
        JButton btnClockInClockOut = new JButton("Clock-in / Clock-out");
        btnClockInClockOut.setSize(100, 100);
        btnClockInClockOut.setVisible(false);
        btnClockInClockOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClockInClockOut objCICO = new ClockInClockOut();
                objCICO.setVisible(true);
            }
        });
        c.gridx = 1;
        c.gridy = 16;
        costPane.add(btnClockInClockOut,c);
*/
		}

	private void setUpquantyColumn(JTable table2, TableColumn Quantity) {
	    // TODO Auto-generated method stub
	    //Set up the editor for the sport cells.
	    JComboBox comboBox = new JComboBox();
	    final int number_to_add_max =10;
	    for (int i = 1; i <= number_to_add_max; i++)
	    {
	        comboBox.addItem(i);
	    }

	    Quantity.setCellEditor(new DefaultCellEditor(comboBox));

	    //Set up tool tips for the sport cells.
	    DefaultTableCellRenderer renderer =
	            new DefaultTableCellRenderer();
	    renderer.setToolTipText("Click for combo box");
	    Quantity.setCellRenderer(renderer);

	}
public  void searchCust() {
        //welcomeLabel. setText("Welcome user at - 1: " + cid );
        SearchCustomer objCustSearch = new SearchCustomer();
        objCustSearch.pack();
        objCustSearch.setLocation(400, 200);
        objCustSearch.setSize(500, 300);
        objCustSearch.setVisible(true);
        objCustSearch.setTitle("Search Customer");
        objCustSearch.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //welcomeLabel. setText("Welcome user at - 2 : " + cid );
    }

   private void viewSaleFrame() {
       // TODO Auto-generated method stub
       try {
           JFrame frame=new JFrame("Today orders");
           Class.forName("com.mysql.jdbc.Driver");
           connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
           GUI.SalesHistory obj = new GUI.SalesHistory(connect);
           JScrollPane sp=new JScrollPane(obj.getTable("menu_order"));
           frame.setSize(1000, 800);
           frame.getContentPane().add(sp);
           frame.setBounds(200,200,400,300);
           frame.setVisible(true);
       }
       catch (ClassNotFoundException e2) {
           // TODO Auto-generated catch block
           e2.printStackTrace();
       }
       catch (SQLException e2) {
           // TODO Auto-generated catch block
           e2.printStackTrace();
       } catch (Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
    }

    public void setBillingInfo( int itemId, String itemInstruction) {
        try {
            String query="Insert into billing_info(oid, orderDate, itemid,item_instruction) values (?,?,?,?)";
            preparedStatement=connect.prepareStatement(query);
            preparedStatement.setLong(1, currentOid);
            System.out.println(" Oid in billing_info is  : "+ currentOid);
            preparedStatement.setDate(2, DateTimeHelper.getCurrentDate());
            preparedStatement.setInt(3, itemId);
            preparedStatement.setString(4, itemInstruction);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

	public void setMenuOrder(long customerId, int transID, String transInfo, float discounAmount, float discountPercent, String DISCOUNTDEC, float subTotal, float totalTaxPercent, float totalAmount){
		try {
			String query="Insert into menu_order values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement=connect.prepareStatement(query);
			preparedStatement.setLong(1, currentOid);
			System.out.println(" Oid in menu_order is  : "+ currentOid);
			preparedStatement.setLong(2, customerId);
			preparedStatement.setDate(3, DateTimeHelper.getCurrentDate());
			preparedStatement.setTime(4, DateTimeHelper.getCurrentTime());
			preparedStatement.setInt(5, transID);
			preparedStatement.setString(6, transInfo);
			preparedStatement.setLong(7, currEmpID);
			preparedStatement.setFloat(8, discounAmount);
			preparedStatement.setFloat(9, discountPercent);
			preparedStatement.setString(10, DISCOUNTDEC);
			preparedStatement.setFloat(11, subTotal);
			preparedStatement.setFloat(12, totalTaxPercent);
			preparedStatement.setFloat(13, totalAmount);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			System.out.println("menu order updated " );

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
    public void setCustomerInfo_old(Customer c){
        try {
            //Check if Customer already exists
//            boolean flagCustEx = customerLookupExact(c.cid, c.FName, c.LName, c.phoneNum, c.emailid , c.DOB);
//            System.out.println(flagCustEx);
//            if (flagCustEx == false)
            {
//                String query = "Insert into customer(cid, Fname, Lname, Address, phonenum, phone, emailid, DOB, flag) values (?,?,?,?,?,?,?,?,?)";
                String query = "Insert into customer(cid, Fname, Lname) values (?,?,?)";
                preparedStatement=connect.prepareStatement(query);
                long custid = getNextCid();
                System.out.println("Cust ID: = " + custid);
                System.out.println("All flieds for insert: " + c.FName + "-->" + c.LName + "-->" + c.address + "-->" +
                        c.phone + "-->" + c.phoneNum + "-->" + c.emailid + "-->" + c.DOB + "-->" + c.flag);
                preparedStatement.setLong(1, 6);
                preparedStatement.setString(2, "asdf");
                preparedStatement.setString(3, "ertt");
//                preparedStatement.setString(4, c.address);
//                preparedStatement.setString(5, c.phone);
//                preparedStatement.setLong(6, c.phoneNum);
//                preparedStatement.setString(7, c.emailid);
//                preparedStatement.setDate(8, c.DOB);
//                // preparedStatement.setString(9, Character.toString(c.flag));
//                preparedStatement.setString(9,"c.flag");
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("Customer updated ");
            }
//            else {
//                System.out.println("Customer already Exists ");
//            }

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

    }

	 */  public ArrayList<Customer> customerLookup(long cid, String FName, String LName, long phoneNum, String emailid , Date DOB){
		 Customer c = null;
		 String query = "Select * FROM customer WHERE ";
		 ArrayList<String> parameterList=  new ArrayList<String>();
		 ArrayList<Customer> CustList = new ArrayList<Customer>();
		 if (cid!=0) {
			 query = query+"cid ="+cid;
		 } else {
			 if (FName != null) {
				 parameterList.add(" FName Like '"+FName+"%'");
			 }
			 if (LName != null) {
				 parameterList.add(" LName Like '"+LName+"%'");
			 }
			 if (phoneNum != 0) {
				 parameterList.add(" phone = "+phoneNum);
			 }
			 if (emailid != null) {
				 parameterList.add(" emailid Like '"+emailid+"%'");
			 }
			 if (DOB != null) {
				 parameterList.add(" DOB = '"+DOB+"'");
			 }
			 query = query+parameterList.get(0);
			 for (int i = 1; i<parameterList.size(); i++) {
				 query = query+" AND "+parameterList.get(i);
			 }
			 System.out.println(" Customer Query - 1: "+ "'" + query + "'");
		 }

		 try {
			 preparedStatement = connect.prepareStatement(query);
			 ResultSet rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				 c = new Customer();
				 c.cid=rs.getLong(1);
				 c.FName = rs.getString(2);
				 c.LName = rs.getString(3);
				 c.address = rs.getString(4);
				 c.phone = rs.getString(5);
				 c.phoneNum = rs.getLong(6);
				 c.emailid = rs.getString(7);
				 c.DOB = rs.getDate(8);
				 //c.flag = (rs.getString(9)).charAt(0);
				 CustList.add(c);
			 }
			 preparedStatement.close();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return CustList;

	 }
	 public boolean customerLookupExact(long cid, String FName, String LName, long phoneNum, String emailid , Date DOB){
		 Customer c = null;
		 String query = "Select * FROM customer WHERE ";
		 ArrayList<String> parameterList=  new ArrayList<String>();
		 if (cid!=0) {
			 query = query+"cid ="+cid;
		 } else {
			 if (FName != null) {
				 parameterList.add(" FName ='"+FName+"'");
			 }
			 if (LName != null) {
				 parameterList.add(" LName = '"+LName+"'");
			 }
			 if (phoneNum != 0) {
				 parameterList.add(" phone = "+phoneNum);
			 }
			 if (emailid != null) {
				 parameterList.add(" emailid = '"+emailid+"'");
			 }
			 if (DOB != null) {
				 parameterList.add(" DOB = '"+DOB+"'");
			 }
			 query = query+parameterList.get(0);
			 for (int i = 1; i<parameterList.size(); i++) {
				 query = query + " AND " + parameterList.get(i);
			 }
			 System.out.println(" Customer Query - 2: " + "'" + query + "'");
		 }

		 try {
			 preparedStatement = connect.prepareStatement(query);
			 ResultSet rs = preparedStatement.executeQuery();
			 if (!rs.next()) {
				 preparedStatement.close();
				 return false;
			 } else {
				 preparedStatement.close();
				 return true;
			 }
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return false;
	 }

	 /*
	  *  This function will update the billing pannel depending on the item selected
	  *  The input passed it the name
	  *  Will need updating if the number of items are increased ..prices changes etc
	  */
	 /*
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
	  */
	 public static float roundDecimal(float d, int decimalPlace) {
		 BigDecimal bd = new BigDecimal(Float.toString(d));
		 bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_DOWN);
		 return bd.floatValue();
	 }


	 /*
	  *  Calculating the total with tax
	  *  The amount is .05
	  *
	  */
	 public void calculateTotal()
	 {
		 float totalAmt = 0 ,ftax1 = 0,ftax2 = 0 ,ftax3 = 0, tot =0 , totAmtWithTax =0 ,price=0, totAmtAfterDiscount=0, discountValue=0;
		 int iRowCnt, iQty, iNumber, j,k,cQty,cUnit,ctotal;
		 iRowCnt = dataModel.getRowCount();
		 Object obj;
		 String quant ;
		 System.out.println("Row Count :"+iRowCnt);
		 System.out.println("Calculating total rowselected =" + table.getSelectedRowCount());

		 cQty = dataModel.findColumn("Quantity");
		 cUnit = dataModel.findColumn("Unit Price");
		 j  = dataModel.findColumn("Total Price");
		 for (int i=0; i< iRowCnt; i++)
		 {
			 obj = dataModel.getValueAt(i, cQty);
			 quant =  obj.toString();
			 if(quant.equals("") ){
				 continue;
			 }
			 iQty = Integer.parseInt(obj.toString());
			 obj = dataModel.getValueAt(i, cUnit);
			 quant =  obj.toString();
			 if(quant.equals("") ){
				 continue;
			 }
			 price = Float.parseFloat(obj.toString());
			 dataModel.setValueAt((price*iQty), i, j);
			 obj     =   dataModel.getValueAt(i, j) ;
			 quant =  obj.toString();
			 if(quant.equals("") ){
				 continue;
			 }
			 tot = Float.parseFloat(obj.toString());
			 totalAmt =  totalAmt + tot;
		 }
		 totalAmt = roundDecimal(totalAmt,2);
		 //        System.out.println("Discount Value :"+CouponDiscount.couponValue);
		 //        System.out.println("db_tax1: " );
		 //        System.out.println(db_tax1 );
		 if(CouponDiscount.couponValue!=0.0 && CouponDiscount.couponValue <=100)
		 {
			 discountValue=(float) (totalAmt*(CouponDiscount.couponValue/100));
		     totAmtAfterDiscount =  (float)( (totalAmt)- (totalAmt*(CouponDiscount.couponValue/100)));
			 // lblDiscount.setText(totAmtAfterDiscount+"   ("+Float.toString((float) CouponDiscount.couponValue)+"%"+")  ");
			 //lblDiscount.setText(new Float(totAmtAfterDiscount).toString());
			 lblDiscount.setText("-"+new Float(discountValue).toString());
			 lblDiscount_1.setText("Discount" + "( " +CouponDiscount.couponValue + " % )" );
		 }else{
			 totAmtAfterDiscount = totalAmt;
			 lblDiscount.setText("0.0");
			 lblDiscount_1.setText("Discount" + "( 0.0 % )" );
		 }
		 ftax1 = (float) ((totAmtAfterDiscount *  db_tax1)/100);
		 ftax1 = roundDecimal(ftax1,2);
		 ftax2 = (float) (totAmtAfterDiscount *  db_tax1/100);
		 ftax2 = roundDecimal(ftax2,2);
		 ftax3 = (float) (totAmtAfterDiscount *  db_tax1/100);
		 ftax3 = roundDecimal(ftax3,2);
		 System.out.println("Total Amt=" + totalAmt);
		 totalTax = ftax1 + ftax2 + ftax3;
		 System.out.println("Discount Amt="+totAmtAfterDiscount);
		 System.out.println("Tax 1=" + ftax1 + "Tax 2=" + ftax2 + "Tax 3=" + ftax3);
		 System.out.println("total tax="+ totalTax);
		 taxCal = ( totalTax + 100 ) / 100;
		 System.out.println("total tax="+ taxCal);
		 totAmtWithTax=  (float) (totAmtAfterDiscount + ftax1 + ftax2+ ftax3);
		 totAmtWithTax = roundDecimal(totAmtWithTax,2);
		 lblSubtotal.setText(Float.toString(totalAmt));
		 lblTax1.setText(Float.toString(ftax1));
		 lblTax2.setText(Float.toString(ftax2));
		 lblTax3.setText(Float.toString(ftax3));
		 lblTotal.setText (Float.toString(totAmtWithTax));
		 lblDiscountvalue.setText(Float.toString((float) CouponDiscount.couponValue));
		 System.out.println("lable Discount value in cafebill : " + lblDiscountvalue.getText());
	 }
	 /*
	  *
	  *Seema
	  *In this function we have set the column editor and renderor to a text editor or a combobox editor
	  *Right now by default for column 1 thi is in effect
	  *Also need to find when to add the combobox
	  * */
	 public void addRowEditor()
	 {
		 //   table = new JTableX(model);
		 // table.setRowSelectionAllowed(false);
		 // table.setColumnSelectionAllowed(false);
		 // create a RowEditorModel... this is used to hold the extra
		 // information that is needed to deal with row specific editors
		 // RowEditorModel rm = new RowEditorModel();
		 // tell the JTableX which RowEditorModel we are using
		 // table.setRowEditorModel(rm);
		 //    table.setCellEditor(new TextAreaCellRenderer());
		 //  table.setDefaultRenderer(table.getClass(), new TextAreaCellRenderer());
		 /*   // create a new JComboBox and DefaultCellEditor to use in the
    // JTableX column
    JComboBox<String> cb = new JComboBox(ExtraChoiceCombo);
    DefaultCellEditor ed = new DefaultCellEditor(cb);
    // tell the RowEditorModel to use ed for row 1
    rm.addEditorForRow(1,ed);
    // create a new JComboBox and editor for a different row
    //JTextPane tp = new JTextPane();
    //ed = (DefaultCellEditor)new TextAreaCellRenderer(tp);

    TextAreaCellRenderer txtedtr = new TextAreaCellRenderer();
    // inform the RowEditorMode of the situation
    rm.addEditorForRow(2,txtedtr);
		  */
	 }
	 /*
	  * Button choice
    =-0987654321
	  * Update accordingly
	  *
	  *
	  * */
	 /*
    public void setMenuExpansionPaneForSandwich(){
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
	  */
	 /*
    public void setMenuExpansionPaneForCoffee(){
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
	  */

}
