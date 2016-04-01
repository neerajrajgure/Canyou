package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class ReceiptPrinting implements Printable, ActionListener {
public class ReceiptPrinting implements Printable {
    private static CafeBill _cb;
    public ReceiptPrinting(CafeBill cb){
        _cb = cb;
    }
    //    public ReceiptPrinting()
    //    {
    //    }
    final String companyName = "Cafe The Hive";
    final String companyTag = "-Coffee & Fun Served Together-";
    final String addrLine1 = "Shop no 27 Radhey Heights,";
    final String addrLine2 = "Ravet Road, Bhondwe Chowk";
    final String addrLine3 = "Ravet, Pune 44";
    final String dash="----------------------------------------------------------------";
    final String tfield1 = "ITEM";
    final String tfield2 = " |QTY";
    final String tfield3 = "|PRICE";
    final String tfield4 = "|TOTAL";
    final String Sub_Total = "SubTotal";
    final String Discount_ = "SubTotal After Discount";

    //    final String S_Tax = "Service Tax(14.50)";
    //    final String Vat_ = "Service charge(12.50)";
    final String total_wt ="Total with Tax";

    final String S_Tax = "Service Tax(14.50 %)";
//    final String Vat_ = "VAT(5 %)";
//    final String total_wt ="Total with Tax";
    final String S_Charge = "Service Charge(5.0 %)";

    final String GREET_VISIT_AGAIN = " Please Visit Again!";
    final String website = "www.thehivecafe.com";
    final String blank = " ";
    final String RsSymbol = "Rs";
    final String GREET_THANKS = "Thank You!";
    final String oid = "Order Id ";
    final String custid = "Customer Id ";
    private String Subtotal;
    private String Tax1;
    private String Tax2;
    private String Tax3;
    private String Total;
    private String Discount;
    private long OrderId;
    private long CustomerId;
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
        Dimension d = this.getPreferredSize();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

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
        Tax1 = _cb.lblTax1.getText();
        Tax2 =_cb.lblTax2.getText();
        Tax3 =_cb.lblTax3.getText();
        Total =_cb.lblTotal.getText();
        Discount =_cb.lblDiscount.getText();
        OrderId =_cb.currentOid; // Should change this to pass oid to the constructor instead of accessing the static variable
        CustomerId = _cb.cid; // Should change this to pass cid to the constructor instead of accessing the static variable
        System.out.println("OrderId in recipt printing is: "+OrderId);
        System.out.println("CustomerId in recipt printing is: "+CustomerId);
        System.out.println("Discount is:"+Discount+"is the value");
        /* Now we perform our rendering */
        int y1 = 10;
        g2d.drawString(companyName, 75 , y1);
        g.drawString(companyTag, 33, 20);
        g.drawString(dash, leftMargin, 30);
        g.drawString(oid, leftMargin, 40);
        g.drawString(String.valueOf(OrderId), 75, 40);
        g.drawString(custid, leftMargin, 50);
        g.drawString(String.valueOf(CustomerId), 75, 50);
        g.drawString(dash, leftMargin, 60);
        g.drawString(sdf.format(date1) , 130, 40);
        g.drawString(sdf1.format(date1) , 152, 50);
        g.drawString(addrLine1, leftMargin, 70);
        g.drawString(addrLine2, leftMargin, 80);
        g.drawString(addrLine3, leftMargin, 90);
        g.drawString(dash, leftMargin, 100);
        g.drawString(tfield1, leftMargin, 110);
        g.drawString(tfield2, 100, 110);
        g.drawString(tfield3, 125, 110);
        g.drawString(tfield4, 160, 110);
        g.drawString(dash, leftMargin, 120);


        int newline = g.getFont().getSize() + 5 ;
        System.out.println("Newline size :"+newline);

        String menuName,printQuantity,prinrtunitprice,prinrToatalprice;
        int y = 130;
        for (int row = 0; row <_cb.dataModel.getRowCount(); row++){

            menuName = _cb.dataModel.getValueAt(row ,1).toString();
            //            menuName =  _cb.dataModel.getValueAt(row ,1).toString().substring(0,26);
            printQuantity =  _cb.dataModel.getValueAt(row ,2).toString();
            prinrtunitprice =  _cb.dataModel.getValueAt(row ,3).toString();
            prinrToatalprice =  _cb.dataModel.getValueAt(row, 4) .toString();
            System.out.println( "Field = "+menuName+" "+printQuantity+" "+prinrtunitprice+" "+prinrToatalprice);
            //            g.drawString(printitem+printQuantity+prinrtunitprice+prinrToatalprice,5,y += newline);
            g.drawString(menuName, leftMargin, y);
            g.drawString(printQuantity, 110, y);
            g.drawString(prinrtunitprice, 135, y);
            g.drawString(prinrToatalprice, 165, y);
            g.drawString(blank, 175,y += newline);//This has to fix later

            //System.out.println("Value of Y :"+y);
        }
        if(Discount.matches("0.0")){
            System.out.println("Am in if");
            g.drawString(dash, leftMargin, y += newline);
            g.drawString(Sub_Total, leftMargin, y += newline);

            if( _cb.lblTax1_1.getText().length() >= 0 ) {
                g.drawString(_cb.lblTax1.getText(), leftMargin, y += newline );
            }
            if( _cb.lblTax2_1.getText().length() >= 0 ) {
                g.drawString(_cb.lblTax2.getText(), leftMargin, y += newline );
            }
            if( _cb.lblTax3_1.getText().length() >= 0 ) {
                g.drawString(_cb.lblTax3.getText(), leftMargin, y += newline );
            }

            g.drawString(total_wt, leftMargin, y += newline);
            g.drawString(RsSymbol, leftMargin+150, y);

            //            g.drawString(S_Charge, leftMargin, y += newline);
            //            g.drawString(S_Tax, leftMargin, y += newline);
            //            g.drawString(Discount_, leftMargin,y += newline);
            //            g.drawString(total_wt, leftMargin, y += newline);
            System.out.println("Value of Y :"+y);
            //numeric values from GUI
            y += newline;
            int x=165;
            g.drawString(Subtotal, x, y+= newline-60); //TODO: NEED TO REMOVE THE -60 and put it few lines before where it belong without subtracting
            g.drawString(Tax2, x, y += newline);
            g.drawString(Total, x, y+=newline);
            g.drawString(GREET_VISIT_AGAIN, leftMargin+120, y+=newline);
            g.drawString(GREET_THANKS, leftMargin, y);
            g.drawString(website, leftMargin+55, y+=newline);
            //            g.drawString(Tax1, x, y+= newline);
            //            g.drawString(Tax2, x, y += newline);
            ////            g.drawString(Tax3, x, y+=newline);
            ////            g.drawString(Discount, x, y+=newline);
            //            g.drawString(Total, x, y+=newline);
        }
        else{
            System.out.println("Am in else");
            g.drawString(dash, leftMargin, y += newline);
            g.drawString(Sub_Total, leftMargin, y += newline);

            if( _cb.lblTax1_1.getText().length() >= 0 ) {
                g.drawString(_cb.lblTax1.getText(), leftMargin, y += newline );
            }
            if( _cb.lblTax2_1.getText().length() >= 0 ) {
                g.drawString(_cb.lblTax2.getText(), leftMargin, y += newline );
            }
            if( _cb.lblTax3_1.getText().length() >= 0 ) {
                g.drawString(_cb.lblTax3.getText(), leftMargin, y += newline );
            }

            g.drawString(Discount_, leftMargin, y += newline);
            g.drawString(total_wt, leftMargin, y += newline);
            g.drawString(RsSymbol, leftMargin+150, y);
            //            g.drawString(S_Charge, leftMargin, y += newline);
            //            g.drawString(S_Tax, leftMargin, y += newline);
            //            g.drawString(Discount_, leftMargin, y += newline);
            //            g.drawString(total_wt, leftMargin, y += newline);

            //            System.out.println("Value of Y :"+y);
            //numeric values from GUI
            int x=165;
            y += newline;
            y += newline;
            g.drawString(Subtotal, x , y += newline-89);
            //            g.drawString(Tax1, x, y+= newline);
            g.drawString(Tax2, x , y += newline);
            //            g.drawString(Tax3, x, y+=newline);
            g.drawString(Discount, x , y+=newline);
            g.drawString(Total, x , y+=newline);
            g.drawString(GREET_THANKS, x-165 , y+=newline);
            g.drawString(GREET_VISIT_AGAIN, x-50, y);
            g.drawString(website, x-110, y+=newline);
        }

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
        job.setPrintable(new ReceiptPrinting(test_cb));
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