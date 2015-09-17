import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import java.awt.TextArea;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.Canvas;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextArea;


public class CafeBill extends JFrame {

	private JPanel contentPane;

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
	 */
	public CafeBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblNewLabel = new JLabel("Items");
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		
		
		JLabel lblSubtotal = new JLabel("00");
		
		/*
		 * This is the common action for every button click
		 * Add an entry to the billing list and recalculate the subtotal.
		 * 
		 * 
		 * */
		
		JButton btnCheeseBurger = new JButton("Cheese Burger");
		btnCheeseBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				textArea.append("Cheese Burger  Rs 150 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
				
			}
		});
		
		JButton btnSandwich = new JButton("Sandwich");
		btnSandwich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				textArea.append("Cheese Burger  Rs 150 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
			}
		});
		
		JButton btnTea = new JButton("Tea");
		btnTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textArea.append("Cheese Burger  Rs 150 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
			}
		});
		
		JButton btnVegBurger = new JButton("Veg Burger");
		btnVegBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textArea.append(" Veg Burger  Rs 150 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
				
			}
		});
		
		JButton btnColdDring = new JButton("Cold Drink");
		btnColdDring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textArea.append("Cold Drink Rs 15 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 150;
					lblSubtotal.setText(Float.toString(subTotal)    );
			}
		});
		
		JButton btnCoffee = new JButton("Coffee");
		btnCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tableList.
			//tableList.addRowSelectionInterval(1, 1);	
				
				textArea.append("Coffee Rs 50 \n");
				float subTotal;
				String tempStr;
				 
					tempStr = lblSubtotal.getText();
					subTotal = Float.parseFloat(tempStr);
					subTotal += 50;
					lblSubtotal.setText(Float.toString(subTotal)    );
			}
		});
		
		JLabel lblSubtotal_1 = new JLabel("Subtotal");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCheeseBurger, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnVegBurger, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSandwich, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(9)
									.addComponent(btnTea, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCoffee, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addComponent(btnColdDring, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 150, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSubtotal_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSubtotal, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1)
									.addGap(22))))
						.addComponent(textArea, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSubtotal_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSubtotal, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(158))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCheeseBurger, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVegBurger, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSandwich, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColdDring))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCoffee)
								.addComponent(btnTea)))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
