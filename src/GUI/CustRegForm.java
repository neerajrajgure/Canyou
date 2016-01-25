package GUI;



import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;

public class CustRegForm extends JDialog implements ActionListener{
    private static CafeBill _cb;
    public CustRegForm(CafeBill cb){
        _cb = cb;
        setModal(true);
        createAndShowGUI();

    }
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
    Label d_o_b=new Label("DOB (DD-MM-YYYY)  ",Label.LEFT);
    Label email_id=new Label("Email Id:",Label.LEFT);

    TextField firstname=new TextField();
    TextField lastname=new TextField();
    TextField phoneno=new TextField();
    TextField dobyyyy=new TextField();
    TextField dobmm=new TextField();
    TextField dobdd=new TextField();
    TextField email=new TextField();


    public CustRegForm()
    {
        setModal(true);
        createAndShowGUI();
        setTitle("Customer Registration");
        setSize(413,300);
        setLocation(500, 200);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    public long getNextCid() {
        //connectDatabase();
        long maxcid=0;
        try {
            preparedStatement = connect.prepareStatement("SELECT MAX(cid) FROM customer");
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next())
            {
                maxcid=rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ++maxcid;
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
                String doby = dobyyyy.getText();
                String dobm = dobmm.getText();
                String dobd = dobdd.getText();
                String dobStr1 = doby + "-" + dobm + "-" + dobd;
                System.out.println("DOB in (YYYY-MM-DD) format: " + dobStr1 );
                String dobStr2 = dobd + "-" + dobm + "-" + doby;
                System.out.println("DOB in reverse format: " + dobStr2 );
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
                String query="Insert into customer (cid,FName,LName,Address,phonenum,phone,emailid,DOB,flag) values (?,?,?,?,?,?,?,?,?)";
                preparedStatement=connect.prepareStatement(query);
                long newCid = getNextCid();
                System.out.println("Customer will get this Cid: " + newCid );
                preparedStatement.setLong(1, newCid);
                //                preparedStatement.setInt(2, customerId);
                preparedStatement.setString(2, firstname.getText());
                preparedStatement.setString(3, lastname.getText());
                preparedStatement.setString(4, "");
                preparedStatement.setString(5,  phoneno.getText());
                preparedStatement.setInt(6, 12345);
                preparedStatement.setString(7, email.getText());
                preparedStatement.setString(8,  dobStr2);
                preparedStatement.setString(9, "");
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("Customer Test updated " );
            }

            /*
            try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
            String query="Insert into customer (FName,LName,phone,emailid,DOB) values (?,?,?,?,?)";
            preparedStatement=connect.prepareStatement(query);
            preparedStatement.setString(1, firstname.getText() );
            preparedStatement.setString(2, lastname.getText() );
            preparedStatement.setString(3, phoneno.getText() );
            preparedStatement.setString(4, dob.getText() );
            preparedStatement.setString(5, email.getText() );
            }
             */
            catch (ClassNotFoundException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            catch (SQLException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Customer Data Saved!");
            System.out.println("Text Submit" );
            //this.dispose();
            firstname.setText(" ");
            lastname.setText(" ");
            phoneno.setText(" ");
            dobyyyy.setText(" ");
            dobmm.setText(" ");
            dobdd.setText(" ");
            email.setText(" ");
        }
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

    }

    private void createAndShowGUI() {
        // TODO Auto-generated method stub
        addWindowListener(new myWindowAdapter());
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

        getContentPane().add(b1);
        getContentPane().add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);

        first_name.setBounds(25,65,90,20);
        last_name.setBounds(25,90,90,20);
        phone_no.setBounds(25,120,90,20);
        d_o_b.setBounds(25,150,109,20);
        email_id.setBounds(25,180,90,20);

        l11.setBounds(10,40,280,20);
        firstname.setBounds(140,65,170,20);
        lastname.setBounds(140,90,170,20);
        phoneno.setBounds(140,120,170,20);
        dobyyyy.setBounds(140,150,50,20);
        dobmm.setBounds(196,150,50,20);
        dobdd.setBounds(252,150,50,20);
        email.setBounds(140,180,170,20);
        b1.setBounds(140,210,70,20);
        b2.setBounds(220,210,70,20);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnClose.setBounds(296, 207, 89, 23);
        getContentPane().add(btnClose);
    }
}

class myWindowAdapter extends WindowAdapter
{
    public void windowClosing(WindowEvent we)
    {
    }
}