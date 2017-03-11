package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewItem {
	JFrame frame;
	JPanel panel;
	JLabel lblItemId,lblItemName,lblItemPrice,lblCatId,lblSecCatId;
	JTextField txtItemId,txtItemName,txtItemPrice,txtCatId,txtSecCatId;
	JButton btnAddItem,btnClear;

	public void AddNewItem() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("Add New Menu Item");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		frame.add(panel);
		panel.setLayout(null);


		lblItemId = new JLabel("Item Id");
		lblItemId.setBounds(10, 10, 80, 25);
		panel.add(lblItemId);
		
		txtItemId = new JTextField();
		txtItemId.setBounds(155, 10, 150, 20);
		panel.add(txtItemId);
		
		lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(10, 30, 80, 25);
		panel.add(lblItemName);
		
		txtItemName = new JTextField();
		txtItemName.setBounds(155, 30, 150, 20);
		panel.add(txtItemName);
		
		lblItemPrice=new JLabel("Item Price");
		lblItemPrice.setBounds(10, 50, 80, 25);
		panel.add(lblItemPrice);
		
		txtItemPrice = new JTextField();
		txtItemPrice.setBounds(155, 50, 150, 20);
		panel.add(txtItemPrice);
		
		lblCatId=new JLabel("Category Id");
		lblCatId.setBounds(10, 70, 80, 25);
		panel.add(lblCatId);
		
		txtCatId = new JTextField();
		txtCatId.setBounds(155, 70, 150, 20);
		panel.add(txtCatId);
		
		lblSecCatId=new JLabel("Secondry Category Id");
		lblSecCatId.setBounds(10, 90, 150, 25);
		panel.add(lblSecCatId);
		
		txtSecCatId = new JTextField();
		txtSecCatId.setBounds(155, 90, 150, 20);
		panel.add(txtSecCatId);
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(40, 120, 100, 30);
		panel.add(btnAddItem);
		
		btnClear  = new JButton("Clear");
		btnClear.setBounds(160, 120, 100, 30);
		panel.add(btnClear);
		
		frame.setVisible(true);

	}

}
