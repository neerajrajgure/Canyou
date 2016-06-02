package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

/* FrameDemo.java requires no other files. */

public class Payment extends JDialog {
    private static CafeBill _cb;

    static FileWriter fout;
    static String FileName = CafeBill.getCurrentDate().toString();
    static String FilePath = "//bin//Docs//Log//HIVE-";
    final static int CASH_PAY = 51;  // Default payment if they do not select.
    final static int CC_DC_PAY = 52;
    public static int payCashOrCC;
    public static String transInfo;
    public String Total;
    public boolean cancelPressed = false;

    JRadioButton radioOptionCash = new JRadioButton("Cash");
    JRadioButton radioOptionCC = new JRadioButton("Credit/Debit Card");
    JTextField jTxtCash = new JTextField(10);
    final JLabel lblTotal1 = new JLabel("Return Amount");
    final JLabel lblamount = new JLabel();
    JTextField jTxtCreditCard = new JTextField(10);
    {
    }
    private final JButton btnCancel = new JButton("Cancel");

    public Payment(CafeBill cb) {
    	FileName=FilePath+FileName+".txt";
    	try {
    		fout= new FileWriter(FileName);
    	} catch (Exception e3) {
    		// TODO Auto-generated catch block
    		e3.printStackTrace();
    	}
    	try {
    		fout.write(CafeBill.getCurrentDate()+"-"+CafeBill.getCurrentTime()+"-"+"Payment Process Initiated.");
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        cancelPressed = false;
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setTitle("Payment Screen");
        _cb = cb;
        // setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setModal(true);
        createAndShowGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        radioOptionCash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTxtCash.setEnabled(true);
                jTxtCreditCard.setEnabled(false);
            }
        });
        //Create and set up the window.
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        radioOptionCash.setToolTipText("Payment by Cash");
        ButtonGroup btngroup=new ButtonGroup();
        btngroup.add(radioOptionCash);
        btngroup.add(radioOptionCC);
        jTxtCreditCard.setText("CC-");
        Total = _cb.lblTotal.getText();
        System.out.println("Total with tax in payment :"+Total);
        JButton btnsubmit = new JButton("Submit");
        btnsubmit.addActionListener(new ActionListener() {
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                if((radioOptionCash.isSelected() && jTxtCash.getText().length() >= 1 ) || radioOptionCC.isSelected()) {
                	try {
                		fout.write(CafeBill.getCurrentDate()+"-"+CafeBill.getCurrentTime()+"-"+"Payment Mode = Cash");
                	} catch (IOException e1) {
                		// TODO Auto-generated catch block
                		e1.printStackTrace();
                	}
                    calculatePay();
//                String msg = "Do you want to print? ";
//                int result = JOptionPane.showConfirmDialog(( java.awt.Component) null, (Object)msg, "Print", JOptionPane.YES_NO_OPTION);
//
//                if (result != JOptionPane.YES_OPTION) {
//                    return;
//                }
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Make a selection 'Cash' or 'Credit/Debit card. If 'Cash' selected enter amount in test box.'");
                   
                }
            }
        });
        System.out.println("amount due change ioutside btnsubmit:"+transInfo);

        JButton btncalculate = new JButton("Calculate");
        btncalculate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if((radioOptionCash.isSelected() && jTxtCash.getText().length() >= 1 ) || radioOptionCC.isSelected()) {
                	try {
                		fout.write(CafeBill.getCurrentDate()+"-"+CafeBill.getCurrentTime()+"-"+"Payment Mode = Credit Card");
                	} catch (IOException e1) {
                		// TODO Auto-generated catch block
                		e1.printStackTrace();
                	}
                    calculatePay();
                }
            }
        });
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(radioOptionCash);
        getContentPane().add(jTxtCash);
        radioOptionCC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTxtCash.setEnabled(false);
                jTxtCreditCard.setEnabled(true);
            }
        });
        getContentPane().add(radioOptionCC);
        getContentPane().add(jTxtCreditCard);
        getContentPane().add(lblTotal1);
        getContentPane().add(lblamount);
        getContentPane().add(btncalculate);
        getContentPane().add(btnsubmit);
        getContentPane().add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelPressed = true;
                dispose();
            }
        });
        this.dispose();
    }
    public void calculatePay()
    {
        if(radioOptionCash.isSelected()) {
            System.out.println("Payment::createAbdShowGUI - In  if ActionListener");
            payCashOrCC = CASH_PAY;
            transInfo = jTxtCash.getText();
            try {
            	fout.write(CafeBill.getCurrentDate()+"-"+CafeBill.getCurrentTime()+"-"+"Paied Amount = " + transInfo);
            } catch (IOException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            }
            System.out.println("transInfo on btnsubmit in if radiocash payment:"+transInfo);
            float diff = CafeBill.roundDecimal(new Float(new Float(transInfo) - new Float(Total)), 2);
            System.out.println("amount due change (diff):" + diff);
            lblamount.setText((new Float(diff)).toString());
        } else if(radioOptionCC.isSelected()) {
            System.out.println("Payment::createAbdShowGUI -In  else if ActionListener");
            payCashOrCC = CC_DC_PAY;
            transInfo =  jTxtCreditCard.getText();
            System.out.println("amount due change in else if transInfo:"+transInfo);

        } else {
            transInfo = "jTxtCash = '" + jTxtCash.getText() + "' jTxtCreditCard = '" + jTxtCreditCard.getText() + "'";
            System.out.println("amount due change in else  transInfo:"+transInfo);
        }
        try {
        	fout.write(CafeBill.getCurrentDate()+"-"+CafeBill.getCurrentTime()+"-"+lblamount.getText());
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        System.out.println("amount due change :"+lblamount.getText());
    }

}