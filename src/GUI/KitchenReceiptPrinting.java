package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KitchenReceiptPrinting implements Printable {
    private static CafeBill _cb;
    public KitchenReceiptPrinting(CafeBill cb){
        _cb = cb;
     }
//    public ReceiptPrinting()
//    {
//    }

    final String item = "ITEM";
    final String qty = " |QTY";
    final String price = "|PRICE";
    final String total = "|TOTAL";
    final String Sub_Total = "SubTotal";
    final String Discount_ = "Discount(%)";
    final String total_wt ="Total with Tax";
    final String Grettings = " Please visit again";
    final String blank=" ";
    private String Subtotal;
    private String tax1Num;
    private String Tax2Num;
    private String Tax3Num;
    private String Total;
    private String Discount;
 /*   
    private Paper receiptPaper;
    private double paperWidth = 0.2;
    private double paperHeight = 0.2;
    double leftMargin = 0.01;
    double rightMargin = 0.01;
    double topMargin = 0.01;
    double bottomMargin = 0.01;
   */
    
    private int leftMargin = 0;
    
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
    	System.out.println(" in print fun krp " );
        Dimension d = this.getPreferredSize();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

        int newline = g.getFont().getSize() + 5 ;
        System.out.println("Newline size :"+newline);

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        Date date1 = new Date( );
        SimpleDateFormat sdf =  new SimpleDateFormat ("E yyyy-MM-dd");
        SimpleDateFormat sdf1 =  new SimpleDateFormat ("hh:mm a");

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
       
//        receiptPaper = new Paper();
//        receiptPaper.setSize(paperWidth * 0.2, paperHeight * 0.2);
//        receiptPaper.setImageableArea(leftMargin * 0.02, topMargin * 0.02,
//                (paperWidth - leftMargin - rightMargin) * 0.02,
//                (paperHeight - topMargin - bottomMargin) * 0.02);
//        pf.setPaper(receiptPaper);
//
//        //PageFormat pf.setPaper(receiptPaper);
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        Subtotal = _cb.lblSubtotal.getText();
        String tax1Lbl = _cb.lblTax1.getText();
        tax1Num = _cb.lblTax1.getText();
        Tax2Num = _cb.lblTax2.getText();
        Tax3Num = _cb.lblTax3.getText();
        Total = _cb.lblTotal.getText();
        Discount = _cb.lblDiscount.getText();
        System.out.println("Discount is:"+Discount+"is the value");
        /* Now we perform our rendering */

        int xDateColumnPos = 130;
        int xTimeColumnPos = 150;

        final int Y_STARTING_POINT = 10;
        int yPosition = Y_STARTING_POINT;
        
        g.drawString(sdf.format(date1) , xDateColumnPos, yPosition);
        g.drawString(sdf1.format(date1) , xTimeColumnPos, yPosition += newline);
        yPosition += newline;
        final int X_ITEM_POS = leftMargin;
        final int X_QTY_POS = X_ITEM_POS + 100;
        final int X_PRICE_POS = X_QTY_POS + 25;  // 100 + 25 = 125
        final int X_TOTAL_POS = X_PRICE_POS + 35;  // 100 + 25 + 35 = 160
        final int X_MENU_QTY_POS_ADJUST = 20;
        final int X_MENU_PRICE_POS_ADJUST = 10;
        final int X_MENU_TOTAL_POS_ADJUST = 5;

        g.drawString(item, X_ITEM_POS, yPosition);
        g.drawString(qty, X_QTY_POS, yPosition);
        g.drawString(price, X_PRICE_POS, yPosition);
        g.drawString(total, X_TOTAL_POS, yPosition);
        yPosition += newline;

        String menuName,printQuantity,prinrtunitprice,prinrToatalprice;
        // int y = 100;
        for (int row = 0; row <_cb.dataModel.getRowCount(); row++){

            menuName = _cb.dataModel.getValueAt(row ,1).toString();
//            menuName =  _cb.dataModel.getValueAt(row ,1).toString().substring(0,26);
            printQuantity =  _cb.dataModel.getValueAt(row ,2).toString();
            prinrtunitprice =  _cb.dataModel.getValueAt(row ,3).toString();
            prinrToatalprice =  _cb.dataModel.getValueAt(row, 4) .toString();
            System.out.println( "Field = "+menuName+" "+printQuantity+" "+prinrtunitprice+" "+prinrToatalprice);
//            g.drawString(printitem+printQuantity+prinrtunitprice+prinrToatalprice,5,y += newline);
            g.drawString(menuName, X_ITEM_POS, yPosition);
            g.drawString(printQuantity, X_QTY_POS + X_MENU_QTY_POS_ADJUST, yPosition);
            g.drawString(prinrtunitprice, X_PRICE_POS + X_MENU_PRICE_POS_ADJUST, yPosition);
            g.drawString(prinrToatalprice, X_TOTAL_POS + X_MENU_TOTAL_POS_ADJUST, yPosition);
            yPosition += newline;
           // g.drawString(blank, X_TOTAL_POS + X_MENU_TOTAL_POS_ADJUST,yPosition += newline);

            //System.out.println("Value of Y :"+y);
        }
        yPosition += newline;
        final int X_POS_RIGHT_COL_POS = 165;
        final int Y_SUB_TOTAL_POS_ADJUST = 75;
            System.out.println("Am in if");

            g.drawString(Sub_Total, leftMargin, yPosition += newline);
            if((tax1Num.compareTo("") == 0) || (tax1Num.compareTo("0.0") == 0)) {
                g.drawString(tax1Lbl, leftMargin, yPosition += newline);
            }
            if (Discount.compareToIgnoreCase("0.0") != 0) {
                g.drawString(Discount_, leftMargin, yPosition += newline);
            }
            g.drawString(total_wt, leftMargin, yPosition += newline);
//            System.out.println("Value of Y :"+y);
           //numeric values from GUI
            g.drawString(Subtotal, X_POS_RIGHT_COL_POS, yPosition += newline - Y_SUB_TOTAL_POS_ADJUST);
            if((tax1Num.compareTo("") == 0) || (tax1Num.compareTo("0.0") == 0)) {
            	g.drawString(tax1Num, X_POS_RIGHT_COL_POS, yPosition += newline);
            }
            if((Tax2Num.compareTo("") == 0) || (Tax2Num.compareTo("0.0") == 0)) {
            g.drawString(Tax2Num, X_POS_RIGHT_COL_POS, yPosition += newline);
            }
            
            if((Tax3Num.compareTo("") == 0) || (Tax3Num.compareTo("0.0") == 0)) {
            g.drawString(Tax3Num, X_POS_RIGHT_COL_POS, yPosition +=newline);
            }
            
            g.drawString(Total, X_POS_RIGHT_COL_POS, yPosition +=newline);

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
              //The job did not successfully complete
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
/*
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PageFormat pf = job.pageDialog(aset);
        job.setPrintable(new ReceiptPrinting(), pf);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print(aset);
            } catch (PrinterException ex) {
             // The job did not successfully complete
            }
        }
*/
        CafeBill test_cb = new CafeBill();
        job.setPrintable(new KitchenReceiptPrinting(test_cb));
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
              //The job did not successfully complete
            }
        }
    }
}


