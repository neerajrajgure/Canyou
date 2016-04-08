package GUI;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.Vector;
import javax.swing.JButton;
public class SearchCustomer extends JDialog implements ActionListener
{
    private static CafeBill _cb;
    public SearchCustomer(CafeBill cb)
    {
        _cb = cb;
    }
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    final static String COLUMN_F_NAME = "FNAME";
    final static String COLUMN_L_NAME = "LNAME";
    final static String COLUMN_DOBY = "DOBY";
    final static String COLUMN_DOB_M = "DOBD";
    final static String COLUMN_DOB_D = "DOBD";
    //final static String COLUMN_DOB = "COLUMN_DOBY + COLUMN_DOB_M + COLUMN_DOB_D";
    final static String COLUMN_DOB = "DOB";
    final static String COLUMN_PHONENUM = "PHONENUM";
    final static String COLUMN_EMAIL = "EMAILID";
    final static String COLUMN_CID = "CID";
    final static String COLUMN_ADDRESS = "ADDRESS";
    String quryPart1;
    
    Vector<Vector> searchlist = new Vector<Vector>();
    ArrayList<CustInfo> searchlist1 = new ArrayList<CustInfo>();
    Vector<CustInfo> searchData = new Vector<CustInfo>();
    
    String msg;
    Button btnSearch=new Button("Search");
    Button btnClear=new Button("Clear");
    Label l11=new Label("Search Customer Details",Label.CENTER);
    Label first_name=new Label("First Name:",Label.LEFT);
    Label last_name=new Label("Last Name:",Label.LEFT);
    Label phone_no=new Label("Phone no:",Label.LEFT);
    Label d_o_b=new Label("DOB (DD-MM-YY)",Label.LEFT);
    Label email_id=new Label("Email Id:",Label.LEFT);

    TextField firstname=new TextField();
    TextField lastname=new TextField();
    TextField phoneno=new TextField();
	TextField dobTxt=new TextField();
	TextField email=new TextField();
    private final Button btnNewButton = new Button("Close");

    public SearchCustomer()
    {
        setModal(true);
        setSize(464,300);
        setLocation(500, 200);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setTitle("Search Customer");
        createAndShowGUI();
        addWindowListener(new WindowListener());
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.black);
        getContentPane().setLayout(null);
        getContentPane().add(l11);
        getContentPane().add(first_name);
        getContentPane().add(firstname);

        getContentPane().add(last_name);
        getContentPane().add(lastname);

        getContentPane().add(phone_no);
        getContentPane().add(phoneno);

        getContentPane().add(d_o_b);
		getContentPane().add(dobTxt);
		getContentPane().add(email_id);
        getContentPane().add(email);

        getContentPane().add(btnSearch);
        getContentPane().add(btnClear);
        btnSearch.addActionListener(this);
        btnClear.addActionListener(this);

        first_name.setBounds(25,65,90,20);
        last_name.setBounds(25,90,90,20);
        phone_no.setBounds(25,120,90,20);
        d_o_b.setBounds(10,150,105,20);
        email_id.setBounds(25,180,90,20);

        l11.setBounds(10,40,280,20);
        firstname.setBounds(120,65,170,20);
        lastname.setBounds(120,90,170,20);
        phoneno.setBounds(120,120,170,20);
        dobTxt.setBounds(120,150,170,20);
        email.setBounds(120,180,170,20);
        btnSearch.setBounds(120,210,70,20);
        btnClear.setBounds(207,210,70,20);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton.setBounds(292, 207, 89, 23);
        getContentPane().add(btnNewButton);
        //getContentPane().setDefaultButton(btnSearch);
    }
    @SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("Search"))
        {
            String fname = firstname.getText();
            String lname = lastname.getText();
            String phno = phoneno.getText();
            String dob = dobTxt.getText();

            String emal = email.getText();
            System.out.println(" firstname from Search customer gui : "+fname);
            System.out.println(" LastName from Search customer gui : "+lname);
            System.out.println(" PHONENO from Search customer gui :"+phno);
            System.out.println(" Date of birth from Search customer gui : "+dob);
            System.out.println(" Email from Search customer gui : "+emal);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
            }
            catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            boolean checksearchResult=false;
            try
            {
                String querySelectPart1 = "select * from customer where ";
                String queryParamName = null;
                String queryParamVal = null;
                if (fname.length() > 0 ) {
                    queryParamName = COLUMN_F_NAME;
                    queryParamVal = firstname.getText();
                }
                else if (lname.length() > 0 ) // lname
                {
                    queryParamName = COLUMN_L_NAME;
                    queryParamVal = lastname.getText();
                }
                else if( phno.length() > 0 )
                {
                    queryParamName = COLUMN_PHONENUM;
                    queryParamVal = phoneno.getText();
                }
                String queryCustSearch = querySelectPart1  + " " + queryParamName + " = '" + queryParamVal + "';";
                System.out.println("Query for cust search: " + queryCustSearch);
                preparedStatement = connect.prepareStatement(queryCustSearch);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next())
                {
                    // "cid", "Fname", "Lname", "Address", "phonenum", "phone", "emailid", "DOB", "flag"
                    CustInfo si = new CustInfo();
                    //	            	si.setCid(rs.getString((int) CafeBill.cid));
                    si.setCid(rs.getString(COLUMN_CID));
                    

                    si.setFName(rs.getString(COLUMN_F_NAME));
                    si.setLName(rs.getString(COLUMN_L_NAME));
                    si.setPhonenum(rs.getString(COLUMN_PHONENUM));
                    si.setDOB(rs.getString(COLUMN_DOB));
                    //si.setDOB(rs.getString(dobm));
                    //si.setDOB(rs.getString(dobd));
                    si.setEmailid(rs.getString(COLUMN_EMAIL));
                    // searchlist.add(si);
                    // searchlist.size();
                    @SuppressWarnings("rawtypes")
					Vector searchData = new Vector();
                    searchData.add(si.getCid());
                    searchData.add(si.getFName());
                    searchData.add(si.getLName());
                    searchData.add(si.getAddress());
                    searchData.add(si.getPhonenum());
                    searchData.add(si.getPhone());
                    searchData.add(si.getEmailid());
                    searchData.add(si.getDOB());
                    searchData.add(si.getFlag());
                    System.out.println( "Recordset data: CID = " + si.getCid() + " Name = " + si.getFName( ));
                    searchlist.add(searchData);
                }
            }
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Clear"))
        {
        	firstname.setText(" ");
        	lastname.setText(" ");
        	phoneno.setText(" ");
        	dobTxt.setText(" ");
        	email.setText(" ");
        	System.out.println("Text Cleared" );
        }
    }
    public static void main(String g[])
    {
        SearchCustomer scf=new SearchCustomer();
        scf.setSize(new Dimension(300,300));
        scf.setTitle("Search Customer");
        scf.setVisible(true);

    }

    private void createAndShowGUI() {
        // TODO Auto-generated method stub
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

    class myWindowAdapter1 extends WindowAdapter
    {
        public void windowClosing(WindowEvent we1)
        {
            //		System.exit(0);

        }
    }
}