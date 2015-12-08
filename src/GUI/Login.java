package GUI;
import javax.swing.*;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

class Login extends JFrame implements ActionListener
{
    JButton SUBMIT;
    JPanel panel;
    JLabel /*label1,*/label2;
    final JTextField  /*text1,*/text2;
    Login()
    {
        //   label1 = new JLabel();
        //   label1.setText("Username:");
        //   text1 = new JTextField(15);

        label2 = new JLabel();
        label2.setText("Password:");
        text2 = new JPasswordField(10);

        SUBMIT=new JButton("SUBMIT");
        SUBMIT.setLocation(50 , 40);
        SUBMIT.setBackground(new Color(59, 89, 182));
        SUBMIT.setForeground(Color.WHITE);
        SUBMIT.setFocusPainted(false);
        SUBMIT.setFont(new Font("Tahoma", Font.BOLD, 12));

        panel=new JPanel(new GridLayout(3,1));
        //   panel.add(label1);
        //   panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        panel.add(SUBMIT);
        add(panel,BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("LOGIN FORM");
    }
    public void actionPerformed(ActionEvent ae)
    {
        try {
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
            System.out.println(pass);
            while(rs.next()) {
                String password=rs.getString("Password");
                if ((pass.equals(password)))
                {
                    result = true; 
                    new CafeBill().setVisible(true);
                }
                
            }
            if(result==false) {
                JOptionPane.showMessageDialog(this, "Incorrect Password!");
            }
            
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }



    public static void main(String arg[])
    {
        try
        {
            Login frame=new Login();
            frame.setSize(200,100);
            frame.setLocation(500, 300);
            //   frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());}
    }
}