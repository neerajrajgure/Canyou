package GUI;
import javax.swing.*;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

class Login extends JDialog implements ActionListener
{
    private CafeBill cb_;

    JButton SUBMIT;
    JPanel panel;
    JLabel label2;
    JTextField text2;
    String empName;
    long empID;

    public Login(CafeBill cb){
        cb_ = cb;

        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        label2 = new JLabel();
        label2.setText("Password:");
        text2 = new JPasswordField(10);

        SUBMIT=new JButton("LOGIN");

        SUBMIT.setLocation(50 , 40);
        SUBMIT.setBackground(new Color(59, 89, 182));
        SUBMIT.setForeground(Color.WHITE);
        SUBMIT.setFocusPainted(false);
        SUBMIT.setFont(new Font("Tahoma", Font.BOLD, 12));

        panel=new JPanel(new GridLayout(3,1));
        panel.add(label2);
        panel.add(text2);
        panel.add(SUBMIT);
        add(panel,BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("LOGIN FORM");
        getRootPane().setDefaultButton(SUBMIT);
    }

    public void actionPerformed(ActionEvent ae)
    {
        // System.out.println("Test-100");

        try {
            // System.out.println("Test-140");
            boolean result = false;

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = null;
            //private static Statement statement = null;
            PreparedStatement preparedStatement = null;
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/HMS?"+ "user=billing&password=hmsbilling");
            String query=("Select * from emp");
            preparedStatement = connect.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            String pass= text2.getText();
            // Never ever display password ... System.out.println(pass);
            while(rs.next()) {
               // System.out.println("Test-1100");
               String password=rs.getString("Password");

                if ((pass.equals(password)))
                {
                    result = true; 
                    empName = rs.getString(2);
                    System.out.println("employ name:-" + empName );
                    empID = Integer.parseInt(rs.getString(5));
                    cb_ = new CafeBill();
                    cb_.currEmpName = empName;
                    System.out.println("cb employ nama:-"+ cb_.currEmpName );
                    cb_.currEmpID = empID;
                    cb_.init();
                    cb_.setVisible(true);
                    this.dispose();
                    //text2.setText("");
                }

            }
            // System.out.println("Test-1200");

            if(result==false) {
                // System.out.println("Test-1240");

                JOptionPane.showMessageDialog(this, "Incorrect Password!");
                text2.setText("");
            }
            // System.out.println("Test-1400");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        // System.out.println("Test-1500");
    }

    public static void main(String arg[])
    {
        try
        {
            CafeBill cb = new CafeBill();
            Login frame=new Login(cb);
            frame.setSize(300,100);
            frame.setLocation(500, 300);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}