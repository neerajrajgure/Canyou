package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class AddNewCategory {
	JFrame frame;
	JPanel panel;
	JLabel lblCatId,lblCatName,lblIconPath;
	JTextField txtCatId,txtCatName,txtPath;
	JButton btnAddItem,btnClear,btnBrowse;
	JFileChooser chooser;
	String fileID;

	public void AddNewCategory() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("Add New category");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		frame.add(panel);
		panel.setLayout(null);


		lblCatId = new JLabel("Category Id");
		lblCatId.setBounds(10, 10, 80, 25);
		panel.add(lblCatId);
		
		txtCatId = new JTextField();
		txtCatId.setBounds(155, 10, 150, 20);
		panel.add(txtCatId);
		
		lblCatName = new JLabel("Category Name");
		lblCatName.setBounds(10, 30, 80, 25);
		panel.add(lblCatName);
		
		txtCatName = new JTextField();
		txtCatName.setBounds(155, 30, 150, 20);
		panel.add(txtCatName);
		
		lblIconPath=new JLabel("Select Icon");
		lblIconPath.setBounds(10, 50, 80, 25);
		panel.add(lblIconPath);
		
		txtPath = new JTextField();
		txtPath.setBounds(155, 50, 150, 20);
		panel.add(txtPath);
		
		btnBrowse = new JButton("..");
		btnBrowse.setBounds(170,50,40,20);
		panel.add(btnBrowse);
		
		btnBrowse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads")); //Downloads Directory as default
		        chooser.setDialogTitle("Select Location");
		        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);

		        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		        { 
		            fileID = chooser.getSelectedFile().getPath();
		            txtPath.setText(fileID);
		        }
			}
		});
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(40, 120, 100, 30);
		panel.add(btnAddItem);
		
		btnClear  = new JButton("Clear");
		btnClear.setBounds(160, 120, 100, 30);
		panel.add(btnClear);
		
		frame.setVisible(true);

	}

}
