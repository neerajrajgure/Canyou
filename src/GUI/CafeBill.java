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
import javax.swing.table.DefaultTableModel;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.JDialog;
import java.awt.Dialog;


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
    JLabel lblSubtotal_1 = new JLabel("Subtotal");
    final JLabel lblSubtotal = new JLabel("00");
    final JLabel lblTotal_1 = new JLabel("Total with Tax ");
    final JLabel lblTotal = new JLabel("00");
    final JLabel lblTax1_1 = new JLabel("VAT (12.50)");
    final JLabel lblTax1 = new JLabel("00");
    final JLabel lblTax2_1 = new JLabel("ServiceTax (14.50)");
    final JLabel lblTax2 = new JLabel("00");
    final JLabel lblTax3_1 = new JLabel("Service Charge (5)");
    final JLabel lblTax3 = new JLabel("00");
    final JLabel lblDiscount_1 = new JLabel("Discount");
    final JLabel lblDiscount = new JLabel("00");
    private static Connection connect = null;
    //private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private int index;
    private int index2;
    //String ExtraChoiceCombo[] = { " 1  cheese", " 2 coffee  ", "3 extra ", " 4 Four",
    // " 5 Item Five" };
    private JComboBox<String> cExtraItem;
    //private ComboBoxEditor<String> cExtraItem1;

    DefaultTableModel dataModel;

    //JTableX table ;
    JTable table ;
    ReceiptPrinting rp;
    public long oid = 0;
    public float db_tax1;
    public float db_tax2;
    public float db_tax3;
    public float totalTax;
    private java.sql.Time getCurrentTime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Time(today.getTime());
    }
    private Timestamp getCurrentTimeStamp() {
        // TODO Auto-generated method stub
        return null;
    }
    private java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
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
            }
        });
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

    public void connectDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/HMS?"+ "user=billing&password=hmsbilling");
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
            preparedStatement = connect.prepareStatement("SELECT * FROM HMS.tax");
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
                if (rs.getString("name").equals("Service Tax"))
                {
                    db_tax1 = rs.getFloat("percentValue");
                    System.out.println("In tax if :"+ db_tax1);
                }
                else if (rs.getString("name").equals("VAT"))
                {
                    db_tax2 = rs.getFloat("percentValue");
                    System.out.println("In tax else 1 : "+ db_tax2 );
                }
                else if(db_tax_name.compareTo("Service Charge") == 0)
                {
                    db_tax3 = rs.getFloat("percentValue");
                    System.out.println("Ins tax else 2: "+ db_tax3 );
                }
            }
            //            System.out.println("db_tax1: " );
            //            System.out.println(db_tax1 );
            totalTax = db_tax1 + db_tax2 + db_tax3 ;
            System.out.println("totalTax:- " + totalTax );
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return false;
        }
        return true;
    }
    public long getNextOid() {
        connectDatabase();
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
        setTitle("The Hive -Coffee & Fun Serverd Togethers");//Title name
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
        /*
         * Read Categories and Items from Database
         */
        ResultSet r = readDBMS("Select categoryName, imageIcon from HMS.categories");
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
                    Float priceVal =Float.parseFloat((String) table.getValueAt(tcl.getRow(), 3));
                    Float newTotalPrice =Float.parseFloat((String)tcl.getNewValue())* priceVal;
                    dataModel.setValueAt(newTotalPrice, tcl.getRow(), 4);
                    calculateTotal();
                }

            }
        };

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
        /*JButton btnAddRow = new JButton("Add Row");
        btnAddRow.setSize(100, 100);
        btnAddRow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selRow = 0;
                    selRow = table.getSelectedRow();
                     if (selRow != -1) {
                        Object [] data = {"","","1","20.0","20.0"};
                        int col=0;
                        dataModel.insertRow(selRow+1, data);
                        //TableColumn cm = table.getColumnModel().getColumn(1); //hard code value
         * //This is cell Editor return appropriate combobox This need s to happen as soon as the table is created
                        //cm.setCellEditor(new TextAreaCellRenderer());
                        //cm.setCellRenderer(new TextAreaCellRenderer());
                        calculateTotal();
                     }
                     }
        });*/
        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        costPane.add(btnDeleteRow,c);
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        //c.weightx =1;
        //costPane.add(btnAddRow,c);
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
        costPane.add(lblTax2_1,c);
        c.gridx = 3;
        c.gridy = 4;
        costPane.add(lblTax2,c);
        c.gridx = 1;
        c.gridy = 5;
        costPane.add(lblTax3_1,c);
        c.gridx = 3;
        c.gridy = 5;
        costPane.add(lblTax3,c);
        c.gridx = 1;
        c.gridy = 7;
        costPane.add(lblTotal_1,c);
        c.gridx = 3;
        c.gridy = 7;
        costPane.add(lblTotal,c);
        c.gridx = 1;
        c.gridy = 6;
        costPane.add(lblDiscount_1,c);
        c.gridx = 3;
        c.gridy = 6;
        costPane.add(lblDiscount,c);

        JButton btnPrintBill = new JButton("Submit Order");
        btnPrintBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int count=table.getRowCount();
                //ResultSet resultset=null;
                try {
      Class.forName("com.mysql.jdbc.Driver");
     // Setup the connection with the DB
    connect = DriverManager.getConnection("jdbc:mysql://localhost/HMS?"+ "user=billing&password=hmsbilling");
    String query="Insert into menu_order(oid,cid,orderDate,transtypeid,transtypeinfo,empid) values (?,?,?,?,?,?)";
    preparedStatement=connect.prepareStatement(query);
    //    for(int i=0;i<count;i++)
        {
//             int OId= (int) table.getValueAt(i, 1);
//             int CId= (int) table.getValueAt(i, 2);
//             String orderDate=(String) table.getValueAt(i, 3);
//             int Transtypeid=(int) table.getValueAt(i, 4);
//             String TrantypeInfo=(String) table.getValueAt(i, 5);
             //preparedStatement.setInt(1, (int)getNextOid());
             System.out.println("New Oid: " );
             preparedStatement.setLong(1, oid);
             System.out.println(" Oid in menu_order is  : "+ oid);
             preparedStatement.setInt(2, 1);
             preparedStatement.setDate(3, getCurrentDate());
             // preparedStatement.setString(3,"2015-12-10");
             preparedStatement.setInt(4, 51);
             preparedStatement.setString(5, "cc-1234");
             preparedStatement.setInt(6, 31);
             preparedStatement.executeUpdate();
             preparedStatement.close();
             }
        System.out.println("menu order updated " );

        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            }
                 try {
      Class.forName("com.mysql.jdbc.Driver");
     // Setup the connection with the DB
    connect = DriverManager.getConnection("jdbc:mysql://localhost/HMS?"+ "user=billing&password=hmsbilling");
    String query="Insert into billing_info(oid,itemid,item_instruction) values (?,?,?)";
    preparedStatement=connect.prepareStatement(query);
    //    for(int i=0;i<count;i++)
        {
//             int OId= (int) table.getValueAt(i, 1);
//             int CId= (int) table.getValueAt(i, 2);
//             String orderDate=(String) table.getValueAt(i, 3);
//             int Transtypeid=(int) table.getValueAt(i, 4);
//             String TrantypeInfo=(String) table.getValueAt(i, 5);
             preparedStatement.setLong(1, oid);
             System.out.println(" Oid in billing_info is  : "+ oid);
             preparedStatement.setInt(2, 1001);
             preparedStatement.setString(3, "Test");
             preparedStatement.executeUpdate();
             preparedStatement.close();
             }

        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
                System.out.println("incremet oid is" +oid);
                oid++;

                //JOptionPane.showConfirmDialog(null, "Order is Placed", "Printing", JOptionPane.DEFAULT_OPTION);

                //new ReceiptPrinting().setVisible(true);
                // ReceiptPrinting rp = new ReceiptPrinting();

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
            }
        });
        c.gridx = 1;
        c.gridy = 9;
        costPane.add(btnPrintBill,c);

        JButton btnClear = new JButton("Clear Order");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        c.gridy = 8;
        costPane.add(btnClear,c);
        JButton btnac = new JButton("Apply Coupon & Discount");
        btnac.setSize(100, 100);
        btnac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CouponDiscount().setVisible(true);
/*                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    // Setup the connection with the DB
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/HMS?"+ "user=billing&password=hmsbilling");
                    String query="Insert into coupon(couponId,couponName,startDate,startTime,endDate,endTime,itemId,percentage) values (?,?,?,?,?,?,?,?)";
                    preparedStatement=connect.prepareStatement(query);
                    preparedStatement.setString(1, "TEST10");
                    preparedStatement.setString(2, "HIVE100");
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
                }*/
            }

            //}
        });
        c.gridx = 1;
        c.gridy = 10;
        costPane.add(btnac,c);
        JButton btncanelOrder = new JButton("Logout");
        btncanelOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //code for cancel the order
   //             new CafeBill().setVisible(true);
                //System.exit(0);
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
        c.gridx = 6;
        c.gridy = 11;
        costPane.add(btncanelOrder,c);
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
    /*
     *  Calculating the total with tax
     *  The amount is .05
     *
     */
    public void calculateTotal()
    {
        float totalAmt = 0 ,ftax1 = 0,ftax2 = 0 ,ftax3 = 0, tot =0 , totAmtWithTax =0 ,price=0;
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
//        System.out.println("Discount Value :"+CouponDiscount.couponValue);
//        System.out.println("db_tax1: " );
//        System.out.println(db_tax1 );
        ftax1 = (float) ((totalAmt *  db_tax1)/100);
        ftax2 = (float) (totalAmt *  db_tax2/100);
        ftax3 = (float) (totalAmt *  db_tax3/100);
        if(CouponDiscount.couponValue!=0.0)
        {
            totAmtWithTax =  (float) (totalAmt + (totalAmt *  totalTax/100) - (totalAmt*CouponDiscount.couponValue/100));
        }
        else
        {
            totAmtWithTax =  (float) (totalAmt + (totalAmt *  totalTax/100));
        }
        lblSubtotal.setText(Float.toString(totalAmt));
        lblTax1.setText(Float.toString(ftax1));
        lblTax2.setText(Float.toString(ftax2));
        lblTax3.setText(Float.toString(ftax3));
        lblTotal.setText (Float.toString(totAmtWithTax));
        lblDiscount.setText(Float.toString((float) CouponDiscount.couponValue));
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
