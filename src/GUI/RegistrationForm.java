package GUI;



import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class RegistrationForm extends Frame implements ActionListener
{
    String msg;
    Button b1=new Button("Submit");
    Button b2=new Button("Clear");
    Label l11=new Label("Customer Details",Label.CENTER);
    Label l1=new Label("First Name:",Label.LEFT);
    Label l2=new Label("Last Name:",Label.LEFT);
    Label l3=new Label("Phone no:",Label.LEFT);
    Label l4=new Label("DOB:",Label.LEFT);
    Label l5=new Label("Email Id:",Label.LEFT);

    TextField t1=new TextField();
    TextField t2=new TextField();
    TextField t3=new TextField();
    TextField t4=new TextField();
    TextField t5=new TextField();

    public RegistrationForm()
    {
        addWindowListener(new myWindowAdapter());
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.black);
        setLayout(null);
        add(l11);
        add(l1);
        add(t1);

        add(l2);
        add(t2);

        add(l3);
        add(t3);

        add(l4);
        add(t4);

        add(l5);
        add(t5);

        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);

        l1.setBounds(25,65,90,20);
        l2.setBounds(25,90,90,20);
        l3.setBounds(25,120,90,20);
        l4.setBounds(25,150,90,20);
        l5.setBounds(25,180,90,20);

        l11.setBounds(10,40,280,20);
        t1.setBounds(120,65,170,20);
        t2.setBounds(120,90,170,20);
        t3.setBounds(120,120,170,20);
        t4.setBounds(120,150,170,20);
        t5.setBounds(120,180,170,20);
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
            msg="Customer details saved!";
            System.out.println("Text Submit" );
        }
        else 
            if(ae.getActionCommand().equals("Clear"))
            {
                t1.setText(" ");
                t2.setText(" ");
                t3.setText(" ");
                t4.setText(" ");
                t5.setText(" ");
                System.out.println("Text Cleared" );
            }
    }


    public static void main(String g[])
    {
        RegistrationForm stu=new RegistrationForm();
        stu.setSize(new Dimension(500,500));
        stu.setTitle("student registration");
        stu.setVisible(true);
    }
}

class myWindowAdapter extends WindowAdapter
{
    public void windowClosing(WindowEvent we)
    {
        System.exit(0);
    }
}