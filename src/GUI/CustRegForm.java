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

public class CustRegForm extends JDialog {
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
    TextField dobdd=new TextField();
    TextField dobmm=new TextField();
    TextField dobyyyy=new TextField();
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
        long currentMaxCid=0;
        try {
            connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
            preparedStatement = connect.prepareStatement("SELECT MAX(cid) FROM customer");
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next())
            {
                currentMaxCid=rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ++currentMaxCid;
    }

    /*  public void paint(Graphics g)
    {
       //g.drawString(msg,200,450);
    }*/

    public boolean addCustomer()
    {
        try {
            long newCid = getNextCid();
            if(firstname.getText().trim().length() <= 0) {
                return false;
            }

            String dobd = dobdd.getText();
            String dobm = dobmm.getText();
            String doby = dobyyyy.getText();

            if(doby.isEmpty() && dobm.isEmpty() && dobd.isEmpty()) {
                dobd = "1";
                dobm = "1";
                doby = "1901";
            }
            else if(doby.isEmpty() || dobm.isEmpty() || dobd.isEmpty()) {
                JOptionPane.showMessageDialog ( null, "Date format error. Either all date fields have to be present or none.", "Date format", JOptionPane.ERROR_MESSAGE);

                return false;
            }

            String dobStr = doby + "-" + dobm + "-" + dobd;
            System.out.println("DOB in (YYYY-MM-DD) format: " + dobStr);
            // Setup the connection with the DB
            connect = DriverManager.getConnection(CafeBill.hmsDbUrl);
            String query="Insert into customer (cid,FName,LName,Address,phonenum,phone,emailid,DOB,flag) values (?,?,?,?,?,?,?,?,?)";
            preparedStatement=connect.prepareStatement(query);
            System.out.println("Customer will get this Cid: " + newCid );
            preparedStatement.setLong(1, newCid);
            preparedStatement.setString(2, firstname.getText());
            preparedStatement.setString(3, lastname.getText());
            preparedStatement.setString(4, "Address - 1"); // No address asked yet
            preparedStatement.setString(5,  phoneno.getText());
            try {
                if(phoneno.getText().trim().isEmpty()) {
                    preparedStatement.setLong(6, 1234567890);
                }
                else {
                    preparedStatement.setLong(6, new Long(phoneno.getText()).longValue());
                }
            }
            catch ( NumberFormatException nfe ) {
                preparedStatement.setLong(6, 1234567890);
                nfe.printStackTrace();
            }
            preparedStatement.setString(7, email.getText());
            preparedStatement.setString(8,  dobStr);
            preparedStatement.setString (9, "C"); // C - Create, D - Deleted
            preparedStatement.executeUpdate();
        }
        catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();

            return false;
        }
        clearFields();

        return true;
    }

    public void clearFields() {
        firstname.setText("");
        lastname.setText("");
        phoneno.setText("");
        dobyyyy.setText("");
        dobmm.setText("");
        dobdd.setText("");
        email.setText("");
        System.out.println("Text Cleared" );
    }

    public static void main(String g[])
    {
        new  CustRegForm().setVisible(true);
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
        getContentPane().add(dobdd);
        getContentPane().add(dobmm);
        getContentPane().add(dobyyyy);

        getContentPane().add(email_id);
        getContentPane().add(email);

        getContentPane().add(b1);
        getContentPane().add(b2);
        // b1.addActionListener(this);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(addCustomer() == true) {
                    System.out.println("Customer sucessfully added." );
                    JOptionPane.showMessageDialog(null, "Customer Data Saved!");
                    dispose();
               }
                else {
                    System.out.println("Error: Failed to save customer info." );
                    JOptionPane.showMessageDialog(null, "Error: Unable to save customer data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // b2.addActionListener(this);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        first_name.setBounds(25,65,90,20);
        last_name.setBounds(25,90,90,20);
        phone_no.setBounds(25,120,90,20);
        d_o_b.setBounds(25,150,109,20);
        email_id.setBounds(25,180,90,20);

        l11.setBounds(10,40,280,20);
        firstname.setBounds(140,65,170,20);
        lastname.setBounds(140,90,170,20);
        phoneno.setBounds(140,120,170,20);
        dobdd.setBounds(140,150,50,20);
        dobmm.setBounds(196,150,50,20);
        dobyyyy.setBounds(252,150,50,20);
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