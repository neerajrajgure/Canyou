package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menubar  {
    private static CafeBill _cb;
    public Menubar(CafeBill cb) {
        
    }
    private JFrame frame;
    public JMenuBar menuBar; 
    private JMenu fileMenu;
    private JMenuItem ViewTotalMenuItem;
    private JMenuItem ViewSaleMenuItem;
    private JMenuItem CustomerLookUpMenuItem;
    private JMenuItem AboutMenuItem;

    public void menus()
    {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        ViewTotalMenuItem = new JMenuItem("View Total");
        ViewSaleMenuItem = new JMenuItem("View Sale");
        CustomerLookUpMenuItem = new JMenuItem("Customer LookUp");
        AboutMenuItem = new JMenuItem("About");
        fileMenu.add(ViewTotalMenuItem);
        fileMenu.add(ViewSaleMenuItem);
        fileMenu.add(ViewSaleMenuItem);  
        fileMenu.add(AboutMenuItem);
        ViewTotalMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //custRetrieval.setVisible(true);
            }
        });
        menuBar.add(fileMenu);
    }

}

