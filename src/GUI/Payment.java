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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createAndShowGUI();
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.

        JRadioButton radioOptionCash = new JRadioButton("Cash");
        radioOptionCash.setToolTipText("Payment by Cash");
        JTextField jTxtCash = new JTextField(10);
        JRadioButton radioOptionCC = new JRadioButton("Credit/Debit Card");
        radioOptionCC.setToolTipText("Payment by Credit/Debit Card");
        JTextField jTxtCreditCard = new JTextField(10);
       
        JButton btnsubmit = new JButton("Submit");
        btnsubmit.setSize(100, 100);
        btnsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(radioOptionCash.isSelected()) {
                    payCashOrCC = CASH_PAY;
                } else if(radioOptionCash.isSelected()) {
                    payCashOrCC = CC_DC_PAY;
                } else {
                    transInfo = "jTxtCash='" + jTxtCash + "' jTxtCreditCard='" + jTxtCreditCard + "'";
                }
            }
        });
        setLayout(new FlowLayout());
        getContentPane().add(radioOptionCash);
        getContentPane().add(jTxtCash);
        getContentPane().add(radioOptionCC);
        getContentPane().add(jTxtCreditCard);
        getContentPane().add(btnsubmit);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
        Payment pay = new Payment();
        pay.createAndShowGUI();
        //    }
        // });
    }
}