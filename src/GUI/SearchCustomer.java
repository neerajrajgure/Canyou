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
public class SearchCustomer extends JDialog implements ActionListener{
    private static CafeBill _cb;
    public SearchCustomer(CafeBill cb){
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

    // ArrayList<search_info> searchlist = new ArrayList<search_info>();
    // Vector<search_info> searchlist = new Vector<search_info>();
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
    TextField dobyyyy=new TextField();
    TextField dobmm=new TextField();
    TextField dobdd=new TextField();
    TextField email=new TextField();

    public SearchCustomer()
    {
        setTitle("Search Customer");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createAndShowGUI();
        addWindowListener(new myWindowAdapter1());
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
        getContentPane().add(dobyyyy);
        getContentPane().add(dobmm);
        getContentPane().add(dobdd);

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
        dobyyyy.setBounds(120,150,50,20);
        dobmm.setBounds(180,150,50,20);
        dobdd.setBounds(250,150,50,20);
        email.setBounds(120,180,170,20);
        btnSearch.setBounds(120,210,70,20);
        btnClear.setBounds(220,210,70,20);
        //getContentPane().setDefaultButton(btnSearch);
    }
    /*  public void paint(Graphics g)
    {
       //g.drawString(msg,200,450s);
    }*/
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("Search"))
        {
            String fname = firstname.getText();
            String lname = lastname.getText();
            String phno = phoneno.getText();
            String doby = dobyyyy.getText();
            String dobm = dobmm.getText();
            String dobd = dobdd.getText();
            String emal = email.getText();
            System.out.println(" firstname from Search customer gui : "+fname);
            System.out.println(" LastName from Search customer gui : "+lname);
            System.out.println(" PHONENO from Search customer gui :"+phno);
            System.out.println(" DOB year from Search customer gui : "+doby);
            System.out.println(" DOB nonth from Search customer gui : "+dobm);
            System.out.println(" DOB date from Search customer gui :"+dobd);
            System.out.println(" Email from Search customer gui : "+emal);
            String dobStr1 = doby + "-" + dobm + "-" + dobd;
            System.out.println("DOB in (YYYY-MM-DD) format: " + dobStr1 );
            String dobStr2 = dobd + "-" + dobm + "-" + doby;
            System.out.println("DOB in reverse format: " + dobStr2 );

            //			if (fname.length() > 0) {
            //				something1 = CONSTANT.TABLE_COL_NAME;
            //				something2 = fname;
            //			}
            
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
                else if (dobStr2.length() > 0) //DOB
                {
                    queryParamName = COLUMN_DOB;
                    queryParamVal = dobStr2;
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
                //search_info si = new search_info();
                
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

            System.out.println(" vector length is :" + searchlist.size());
            dispose();
            CustomerRetrievalForm custRetrieval = new CustomerRetrievalForm(searchlist);
            custRetrieval.setVisible(true);

            /*
	            // list iterate
                Iterator<search_info> iter=searchlist.iterator();
                search_info sn = new search_info();
                while(iter.hasNext())
                {
                search_info s = iter.next();
                   if((fname.equals(si.getFName())) || (lname.equals(si.getLName())) || (phno.equals(si.getPhonenum())) || (doby.equals(si.getDOB())) || ( dobm.equals(si.getDOB()))  ||  ( dobd.equals(si.getDOB())) || ( emal.equals(si.getEmailid())))
                   {
                       sn.FName = si.FName;
                       sn.LName = si.LName;
                       sn.Phonenum = si.Phonenum;
                       sn.DOB =si .DOB;
                       sn.emailid = si.emailid;
                       searchlist1.add(sn);
                       checksearchResult=true;
                   }
                }
                if(!checksearchResult)
                {
                JOptionPane.showMessageDialog(null, "No results found", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                Iterator<search_info> iter1=searchlist1.iterator();
                //iterate and add to Jtable
                while(iter1.hasNext()){
                search_info sa = iter.next();
                System.out.println( iter1.next() );
                }
               }
            }
            catch (SQLException e) {
           // TODO Auto-generated catch block
            e.printStackTrace();
            }

			//new CustomerReterivalForm().setVisible(true);
		}
             */
        }
        else
            if(ae.getActionCommand().equals("Clear"))
            {
                firstname.setText(" ");
                lastname.setText(" ");
                phoneno.setText(" ");
                dobyyyy.setText(" ");
                dobmm.setText(" ");
                dobdd.setText(" ");
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