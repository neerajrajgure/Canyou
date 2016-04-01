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
	final String Discount_ = "SubTotal After Discount";
	final String total_wt ="Total with Tax";
	final String RsSymbol = "Rs";
	final String oid = "Order Id ";
	final String custid = "Customer Id ";
	final String blank = "  ";
	private String Subtotal;
	private String tax1Num;
	private String Tax2Num;
	private String Tax3Num;
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
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		Subtotal = _cb.lblSubtotal.getText();
		String tax1Lbl = _cb.lblTax1.getText();
		tax1Num = _cb.lblTax1.getText();
		Tax2Num = _cb.lblTax2.getText();
		Tax3Num = _cb.lblTax3.getText();
		//		System.out.println("tax1Num is in krp frame: "+tax1Num+ "is the value");
		//		System.out.println("Tax2Num is in krp frame: "+Tax2Num+ "is the value");
		//		System.out.println("Tax3Num is in krp frame: "+Tax3Num+ "is the value");
		Total = _cb.lblTotal.getText();
		Discount = _cb.lblDiscount.getText();
		//		System.out.println("Discount is in krp frame:"+Discount+"is the value");
		OrderId =_cb.currentOid; // Should change this to pass oid to the constructor instead of accessing the static variable
		CustomerId = _cb.cid; // Should change this to pass cid to the constructor instead of accessing the static variable

		int x_startpos = 5 ;
		int yPosition = 10;
		int xDateColumnPos = 100;
		int xTimeColumnPos = 100;
		int xoidpos = 65 ;
		int xcidpos = 65 ;
		int fields= 40 ;
		int qtypos = 120;
		int pricepos = 145;
		int totalpos = 180;


		g.drawString(oid, x_startpos, yPosition);
		g.drawString(custid, x_startpos, yPosition + 10);
		g.drawString(String.valueOf(OrderId), xoidpos, yPosition);
		g.drawString(String.valueOf(CustomerId), xcidpos, yPosition + 10);
		g.drawString(sdf.format(date1) , xDateColumnPos , yPosition);
		g.drawString(sdf1.format(date1) , xTimeColumnPos , yPosition + 10);

		g.drawString(item, leftMargin, fields);
		g.drawString(qty, qtypos, fields);
		g.drawString(price, pricepos, fields);
		g.drawString(total, totalpos, fields);

		String menuName,printQuantity,prinrtunitprice,prinrToatalprice;
		System.out.println(" value of y in krp after sting " + yPosition  );

		for (int row = 0; row <_cb.dataModel.getRowCount(); row++){

			menuName = _cb.dataModel.getValueAt(row ,1).toString();
			//            menuName =  _cb.dataModel.getValueAt(row ,1).toString().substring(0,26);
			printQuantity =  _cb.dataModel.getValueAt(row ,2).toString();
			prinrtunitprice =  _cb.dataModel.getValueAt(row ,3).toString();
			prinrToatalprice =  _cb.dataModel.getValueAt(row, 4) .toString();
			System.out.println( "Field = "+menuName+" "+printQuantity+" "+prinrtunitprice+" "+prinrToatalprice);
			//            g.drawString(printitem+printQuantity+prinrtunitprice+prinrToatalprice,5,y += newline);
			g.drawString(menuName, leftMargin, fields + yPosition +10);
			g.drawString(printQuantity, 120, fields + yPosition + 10);
			g.drawString(prinrtunitprice, 150, fields + yPosition + 10);
			g.drawString(prinrToatalprice, 180, fields + yPosition + 10);
			g.drawString(blank, 80,yPosition += newline+10);//This has to fix later
		}
		if(Discount.matches("0.0")){
			int yPosition1 = yPosition + 30;
			g.drawString(Sub_Total, leftMargin, yPosition1+= newline );

			if( _cb.lblTax1_1.getText().length() > 0 ) {
			    g.drawString(_cb.lblTax1_1.getText(), leftMargin, yPosition1 += newline );
			}
			if( _cb.lblTax2_1.getText().length() > 0 ) {
			    g.drawString(_cb.lblTax2_1.getText(), leftMargin, yPosition1 += newline );
			}
			if( _cb.lblTax3_1.getText().length() > 0 ) {
			    g.drawString(_cb.lblTax3_1.getText(), leftMargin, yPosition1 += newline );
			}

			g.drawString(total_wt, leftMargin, yPosition1 += newline);
			g.drawString(RsSymbol, 150, yPosition1);
			yPosition1 += newline;
			int x=165;
			g.drawString(Subtotal, x, yPosition1+= newline-60); //TODO: NEED TO REMOVE THE -60 and put it few lines before where it belong without subtracting

            if( _cb.lblTax1_1.getText().length() > 0 ) {
                g.drawString(tax1Num, x, yPosition1 += newline);
            }
            if( _cb.lblTax2_1.getText().length() > 0 ) {
                g.drawString(Tax2Num, x, yPosition1 += newline);
            }
            if( _cb.lblTax3_1.getText().length() > 0 ) {
                g.drawString(Tax3Num, x, yPosition1 += newline);
            }

			g.drawString(Total, x, yPosition1+=newline);
		}
		else{

			int yPosition1 = yPosition + 30;
			g.drawString(Sub_Total, leftMargin, yPosition1+= newline );

			if( _cb.lblTax1_1.getText().length() > 0 ) {
			    g.drawString(_cb.lblTax1_1.getText(), leftMargin, yPosition1 += newline );
			}
			if( _cb.lblTax2_1.getText().length() > 0 ) {
			    g.drawString(_cb.lblTax2_1.getText(), leftMargin, yPosition1 += newline );
			}
			if( _cb.lblTax3_1.getText().length() > 0 ) {
			    g.drawString(_cb.lblTax3_1.getText(), leftMargin, yPosition1 += newline );
			}

			g.drawString(Discount_, leftMargin, yPosition1 += newline);
			g.drawString(total_wt, leftMargin, yPosition1 += newline);
			g.drawString(RsSymbol, 150, yPosition1);
			yPosition1 += newline;
			int x=165;
			g.drawString(Subtotal, x, yPosition1+= newline-75); //TODO: NEED TO REMOVE THE -60 and put it few lines before where it belong without subtracting

            if( _cb.lblTax1_1.getText().length() > 0 ) {
                g.drawString(tax1Num, x, yPosition1 += newline);
            }
            if( _cb.lblTax2_1.getText().length() > 0 ) {
                g.drawString(Tax2Num, x, yPosition1 += newline);
            }
            if( _cb.lblTax3_1.getText().length() > 0 ) {
                g.drawString(Tax3Num, x, yPosition1 += newline);
            }

			g.drawString(Discount, x , yPosition1 +=newline);
			g.drawString(Total, x, yPosition1+=newline);
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
		test_cb.getContentPane().setMinimumSize(new Dimension(0, 0));
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


