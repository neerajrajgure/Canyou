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
    final String tfields ="  ITEM    | QTY | PRICE | TOTAL ";
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        Dimension d = this.getPreferredSize();
        //int fontSize = 8;

        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering */
//        g.drawString(companyName,5,10);
//        g.drawString(addrLine1,  5, 20);
//        g.drawString(addrLine2, 5, 30);
//        g.drawString(addrLine3, 5, 40);                                                                                                                                                                                                                                                                                                                                                          
          g.drawString(tfields,5, 50);
        int newline = g.getFont().getSize() + 5 ;
         String   printid,printitem,printQuantity,prinrtunitprice,prinrToatalprice;
         int y=50;
            for (int row = 0; row <_cb.dataModel.getRowCount(); row++){
            printitem = (String ) _cb.dataModel.getValueAt(row ,1) +" ";
            //printitem = ((String) _cb.dataModel.getValueAt(row ,1)).substring(0,15) +" ";
            printQuantity = (String) _cb.dataModel.getValueAt(row ,2) + "   ";
            prinrtunitprice = (String) _cb.dataModel.getValueAt(row ,3) + "        ";
            prinrToatalprice = (float) _cb.dataModel.getValueAt(row ,4) + "        ";
            System.out.println( "Field = "+printitem+" "+printQuantity+" "+prinrtunitprice+" "+prinrToatalprice);
            g.drawString(printitem+printQuantity+prinrtunitprice+prinrToatalprice,5,y += newline);
        }
            System.out.println(g2d);
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