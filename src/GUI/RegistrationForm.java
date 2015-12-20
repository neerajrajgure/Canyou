package GUI;



import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class RegistrationForm extends Frame implements ActionListener{
    private static CafeBill _cb;
    public RegistrationForm(CafeBill cb){
        _cb = cb;
     }
    String msg;
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

    public RegistrationForm()
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
            msg="Customer details saved!";
            System.out.println("Text Submit" );
        }
        else 
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
        RegistrationForm crf=new RegistrationForm();
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