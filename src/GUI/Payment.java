package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/* FrameDemo.java requires no other files. */

public class Payment extends JDialog {

    final static int CASH_PAY = 51;  // Default payment if they do not select.
    final static int CC_DC_PAY = 52;
    public static int payCashOrCC;
    public static String transInfo;


    public Payment() {
        setModal(true);
        // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createAndShowGUI();
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JRadioButton radioOptionCash = new JRadioButton("Cash",true);
        radioOptionCash.setToolTipText("Payment by Cash");
        JTextField jTxtCash = new JTextField(10);
        JRadioButton radioOptionCC = new JRadioButton("Credit/Debit Card");
        radioOptionCC.setToolTipText("Payment by Credit/Debit Card");
        ButtonGroup btngroup=new ButtonGroup();
        btngroup.add(radioOptionCash);
        btngroup.add(radioOptionCC);
        JTextField jTxtCreditCard = new JTextField(10);

        JButton btnsubmit = new JButton("Submit");
        btnsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(radioOptionCash.isSelected()) {
                    System.out.println("Payment::createAbdShowGUI - In  if ActionListener");
                    payCashOrCC = CASH_PAY;
                    transInfo = jTxtCash.getText();
                } else if(radioOptionCC.isSelected()) {
                    System.out.println("Payment::createAbdShowGUI -In  else if ActionListener");
                    payCashOrCC = CC_DC_PAY;
                    transInfo =  jTxtCreditCard.getText();

                } else {
                    transInfo = "jTxtCash = '" + jTxtCash + "' jTxtCreditCard = '" + jTxtCreditCard + "'";
                }
                dispose();
            }
        });

        setLayout(new FlowLayout());
        getContentPane().add(radioOptionCash);
        getContentPane().add(jTxtCash);
        getContentPane().add(radioOptionCC);
        getContentPane().add(jTxtCreditCard);
        getContentPane().add(btnsubmit);
        this.dispose();
    }

}