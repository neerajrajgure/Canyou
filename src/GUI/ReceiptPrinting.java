package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.print.*;

//public class ReceiptPrinting implements Printable, ActionListener {
public class ReceiptPrinting implements Printable {
    private static CafeBill _cb;
    public ReceiptPrinting(CafeBill cb){
        _cb =cb;
     }
    public ReceiptPrinting()
    {
    }
    final String companyName = "The Hive";
    final String addrLine1 = "Shop no 27 RADHEY HEIGHTS";
    final String addrLine2 = "Sector no 29 at village Ravet";
    final String addrLine3 = "Taluka Haveli ,District Pune 412101";
    final String tfield1 = "ITEM";
    final String tfield2 = " |QTY";
    final String tfield3 = "|PRICE";
    final String tfield4 = "|TOTAL";
    final String Sub_Total = "SubTotal";
    final String Discount_ = "Discount";
    final String S_Tax ="Service Tax(14.50)";
    final String Vat_ ="VAT(12.50)";
    final String total_wt ="Total with Tax";
    final String S_Charge ="Service Charge(5.0)";
    final String blank=" ";
    private String Subtotal;
    private String Tax1;
    private String Tax2;
    private String Tax3;
    private String Total;
    private String Discount;
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        Dimension d = this.getPreferredSize();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        Subtotal = _cb.lblSubtotal.getText();
        Tax1 = _cb.lblTax1.getText();
        Tax2=_cb.lblTax2.getText();
        Tax3=_cb.lblTax3.getText();
        Total=_cb.lblTotal.getText();
        Discount=_cb.lblDiscount.getText();
        /* Now we perform our rendering */
        g.drawString(companyName, 5, 10);
        g.drawString(addrLine1, 5, 20);
        g.drawString(addrLine2, 5, 30);
        g.drawString(addrLine3, 5, 40);
        g.drawString(tfield1, 5, 50);
        g.drawString(tfield2, 105, 50);
        g.drawString(tfield3, 130, 50);
        g.drawString(tfield4, 165, 50);
        int newline = g.getFont().getSize() + 5 ;
        System.out.println("Newline size :"+newline);
        String menuName,printQuantity,prinrtunitprice,prinrToatalprice;
        int y = 65;
        for (int row = 0; row <_cb.dataModel.getRowCount(); row++){

            menuName = _cb.dataModel.getValueAt(row ,1).toString();
            // menuName =  _cb.dataModel.getValueAt(row ,1).substring(0,15) +" ";
            printQuantity =  _cb.dataModel.getValueAt(row ,2).toString();
            prinrtunitprice =  _cb.dataModel.getValueAt(row ,3).toString();
            prinrToatalprice =  _cb.dataModel.getValueAt(row, 4) .toString();
            System.out.println( "Field = "+menuName+" "+printQuantity+" "+prinrtunitprice+" "+prinrToatalprice);
            //             g.drawString(printitem+printQuantity+prinrtunitprice+prinrToatalprice,5,y += newline);



            g.drawString(menuName, 5, y);
            g.drawString(printQuantity, 115, y);
            g.drawString(prinrtunitprice, 140, y);
            g.drawString(prinrToatalprice, 170, y);
            g.drawString(blank, 180,y += newline);//This has to fix later

            System.out.println("Value of Y :"+y);
        }
            g.drawString(Sub_Total, 5, y += newline);
            g.drawString(Vat_, 5, y += newline);
            g.drawString(S_Charge, 5, y += newline);
            g.drawString(S_Tax, 5, y += newline);
            g.drawString(Discount_, 5,y += newline);
            g.drawString(total_wt, 5, y += newline);
//            System.out.println("Value of Y :"+y);
           //numeric values from GUI
            int x=170;
            g.drawString(Subtotal, x, y+= newline-90);
            g.drawString(Tax1, x, y+= newline);
            g.drawString(Tax2, x, y += newline);
            g.drawString(Tax3, x, y+=newline);
            g.drawString(Discount, x, y+=newline);
            g.drawString(Total, x, y+=newline);
            /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    private String substring(int i, int j) {
        // TODO Auto-generated method stub
        return null;
    }
    private Dimension getPreferredSize() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        }

    public ReceiptPrinting getSource() {
        // TODO Auto-generated method stub
        return null;
    }
    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                // The job did not successfully complete
            }
        }
    }

    public static void main(String args[]) {
 try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
        }
        PrinterJob job = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PageFormat pf = job.pageDialog(aset);
        job.setPrintable(new ReceiptPrinting(), pf);
        boolean ok = job.printDialog(aset);
        if (ok) {
            try {
                 job.print(aset);
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
        }
    }
   }