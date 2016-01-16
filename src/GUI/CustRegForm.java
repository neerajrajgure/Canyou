package GUI;



import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustRegForm extends Frame implements ActionListener{
    private static CafeBill _cb;
    public CustRegForm(CafeBill cb){
        _cb = cb;
    }
    // final String db_name = "HMS";
    // final String username = "billing";
    // final String password = "hmsbilling";
    // final String db_url ="jdbc:mysql://localhost/"+CafeBill.db_name+"?"+ "user=" + username + "&" + "password=" + password;
    String msg;
    private static Connection connect = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    Button b1=new Button("Submit");
    Button b2=new Button("Clear");
    Label l11=new Label("Customer Details",Label.CENTER);
    Label first_name=new Label("First Name:",Label.LEFT);
    Label last_name=new Label("Last Name:",Label.LEFT);
    Label phone_no=new Label("Phone no:",Label.LEFT);
    Label d_o_b=new Label("DOB:",Label.LEFT);
    Label email_id=new Label("Email Id:",Label.LEFT);

    TextField firstname=new TextField();
    TextField lastname=new TextField();
    TextField phoneno=new TextField();
    TextField dob=new TextField();
    TextField email=new TextField();

    public CustRegForm()
    {
        addWindowListener(new myWindowAdapter());
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.black);
        setLayout(null);
        add(l11);
        add(first_name);
        add(firstname);

        add(last_name);
        add(lastname);

        add(phone_no);
        add(phoneno);

        add(d_o_b);
        add(dob);

        add(email_id);
        add(email);

        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);

        first_name.setBounds(25,65,90,20);
        last_name.setBounds(25,90,90,20);
        phone_no.setBounds(25,120,90,20);
        d_o_b.setBounds(25,150,90,20);
        email_id.setBounds(25,180,90,20);

        l11.setBounds(10,40,280,20);
        firstname.setBounds(120,65,170,20);
        lastname.setBounds(120,90,170,20);
        phoneno.setBounds(120,120,170,20);
        dob.setBounds(120,150,170,20);
        email.setBounds(120,180,170,20);
        b1.setBounds(120,210,70,20);
        b2.setBounds(220,210,70,20);
    }

    /*  public void paint(Graphics g)
    {
       //g.drawString(msg,200,450);
    }*/

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("Submit"))
        {
            try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
            String query="Insert into customer (cid,FName,LName,Address,phonenum,phone,emailid,DOB,flag) values (?,?,?,?,?,?,?,?,?)";
            preparedStatement=connect.prepareStatement(query);
            preparedStatement.setString(1, firstname.getText() );
            preparedStatement.setString(2, lastname.getText() );
            preparedStatement.setString(3, phoneno.getText() );
            preparedStatement.setString(4, dob.getText() );
            preparedStatement.setString(5, email.getText() );

        }
        catch (ClassNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
            msg="Customer details saved!";
            System.out.println("Text Submit" );
        }
        if(ae.getActionCommand().equals("Clear"))
            {
                firstname.setText(" ");
                lastname.setText(" ");
                phoneno.setText(" ");
                dob.setText(" ");
                email.setText(" ");
                System.out.println("Text Cleared" );
            }
    }


    public static void main(String g[])
    {
        CustRegForm crf=new CustRegForm();
        crf.setSize(new Dimension(500,500));
        crf.setTitle("Customer Registration");
        crf.setVisible(true);

    }

    private void createAndShowGUI() {
        // TODO Auto-generated method stub 
    }
}

class myWindowAdapter extends WindowAdapter
{
    public void windowClosing(WindowEvent we)
    {
        System.exit(0);
    }
}