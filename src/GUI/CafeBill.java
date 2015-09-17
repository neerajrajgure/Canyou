package GUI;
/**
 * menuExpansionPane is the Panel where the buttons will go after selecting the category from the left side
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
// import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextArea;


public class CafeBill extends JFrame {

	private JPanel contentPane;
	private JPanel categoryPane;
	private JPanel menuExpansionPane;
	private JPanel costPane;
	private JPanel frequentItemsPane;
    private JList list;
    private DefaultListModel<String> listModel;
    JLabel lblSubtotal_1 = new JLabel("Subtotal");
    final JLabel lblSubtotal = new JLabel("00");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeBill frame = new CafeBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */	public CafeBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel(new BorderLayout());
		
		//categoryPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		categoryPane = new JPanel();
		categoryPane.setBorder(BorderFactory.createLineBorder(Color.black));
		menuExpansionPane = new JPanel();
		menuExpansionPane.setBorder(BorderFactory.createLineBorder(Color.black));
		costPane = new JPanel();
		costPane.setBorder(BorderFactory.createLineBorder(Color.black));
		frequentItemsPane = new JPanel();
		frequentItemsPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(categoryPane, BorderLayout.WEST);
		contentPane.add(menuExpansionPane, BorderLayout.CENTER);
		contentPane.add(costPane, BorderLayout.EAST);
		contentPane.add(frequentItemsPane, BorderLayout.SOUTH);
		
		
		
		
		final JTextArea textArea = new JTextArea();
		
		listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        setCategoryPane();
        //list.addListSelectionListener((ListSelectionListener) this);

		//JLabel lblNewLabel_4 = new JLabel("Total Price");
		
		
		/*
		 * This is the common action for every button click
		 * Add an entry to the billing list and recalculate the subtotal.
		 * 
		 * 
		 * */


		
		//Change the index to number of categories in the database
		
		
		
		//GroupLayout gl_categoryPane = new GroupLayout(categoryPane);
		//GroupLayout gl_contentPane = new GroupLayout(contentPane);
		/*
		gl_categoryPane.setHorizontalGroup(gl_categoryPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnCtgCoffee)
				.addGap(18)
				.addComponent(btnCtgSandwich)
				);
		gl_categoryPane.setVerticalGroup(gl_categoryPane.createSequentialGroup()
				.addComponent(btnCtgCoffee,GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(btnCtgSandwich,GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				);
	*/
		//setMenuExpansionPane();
		setCostPane();
		
		
		//contentPane.setLayout(gl_contentPane);
	}
	 public void setCategoryPane(){
		 /*
		  * Set Layout
		  */
 			GridLayout categoryLayout = new GridLayout(4,1); // Change Hardcoded value
 			
 			categoryPane.setLayout(categoryLayout);
		 
			JButton btnCtgCoffee = new JButton();
	        ImageIcon img = new ImageIcon("./src/images/Coffee.png");
	        btnCtgCoffee.setIcon(img);

			
			btnCtgCoffee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				/*
					textArea.append("Cheese Burger  Rs 150 \n");
					listModel.addElement("Cheese Burger 1 Rs.150 Rs.150\n");
					float subTotal;
					String tempStr;
					 
						tempStr = lblSubtotal.getText();
						subTotal = Float.parseFloat(tempStr);
						subTotal += 150;
						lblSubtotal.setText(Float.toString(subTotal) );
						*/
					
				}
			});
			
			JButton btnCtgSandwich = new JButton();
	        ImageIcon imgSandwitch = new ImageIcon("./src/images/Sandwich.png");
	        btnCtgSandwich.setIcon(imgSandwitch);
	        
			JButton btnCtgBurger = new JButton();
	        ImageIcon imgBurger = new ImageIcon("./src/images/Burger.png");
	        btnCtgBurger.setIcon(imgBurger);

			btnCtgSandwich.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				/*
					textArea.append("Cheese Burger  Rs 150 \n");
					listModel.addElement("Cheese Burger 1 Rs.150 Rs.150\n");
					float subTotal;
					String tempStr;
					 
						tempStr = lblSubtotal.getText();
						subTotal = Float.parseFloat(tempStr);
						subTotal += 150;
						lblSubtotal.setText(Float.toString(subTotal) );
						*/
					
				}
			});
			
			JButton btnCtgSoftDrink = new JButton();
	        ImageIcon imgSoftDrink = new ImageIcon("./src/images/SoftDrink.png");
	        btnCtgSoftDrink.setIcon(imgSoftDrink);
			categoryPane.add(btnCtgCoffee);
			categoryPane.add(btnCtgSoftDrink);
			categoryPane.add(btnCtgSandwich);
			categoryPane.add(btnCtgBurger);

	 }
	public void setCostPane(){
		/*
		 * Set Layout
		 */
		BoxLayout gl_costPane = new BoxLayout(costPane,BoxLayout.PAGE_AXIS);
		costPane.setLayout(gl_costPane);

		final String[] columnNames = {"Sr_No","Item", "Quantity","Unit Price","Total Price"};
	      TableModel dataModel = new AbstractTableModel() {
	    	    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
				public String getColumnName(int col) {
	    	        return columnNames[col].toString();
	    	    }

	          public int getColumnCount() { return 5; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return ""; }
	      };
	      JTable table = new JTable(dataModel);
	      JScrollPane scrollpane = new JScrollPane(table);
	      table.setFillsViewportHeight(true);
	      costPane.add(scrollpane);

		JButton btnPrintBill = new JButton("Submit Order");
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	/*			
				textArea.append("Cheese Burger  Rs 150 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
	*/				
			}
		});
		costPane.add(btnPrintBill);
		costPane.add(list);
	
	
	}
}
