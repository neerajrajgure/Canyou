package GUI;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.sun.prism.paint.Color;

public class Generator {
	static String qryP1,qryP2,qryP3,qryP4,qryP5,qryP6;

	public void Generator() {
		JFrame frame = new JFrame("Query Generator");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		frame.add(panel);
		panel.setLayout(null);


		JLabel lblMode = new JLabel("Select Mode");
		lblMode.setBounds(10, 10, 80, 25);
		panel.add(lblMode);

		String country[]={"Select","Insert","Update","Delete","Drop Table"};        
		JComboBox cbxMode=new JComboBox(country);    
		cbxMode.setBounds(110, 10, 160, 25);    
		panel.add(cbxMode);        

		JCheckBox chkIsWhr = new JCheckBox("Is Where Clause Present");
		chkIsWhr.setBounds(110, 40, 200, 25);
		panel.add(chkIsWhr);

		JLabel lblWhr = new JLabel("Where Condotion");
		lblWhr.setBounds(10, 70, 100, 25);
		panel.add(lblWhr);

		JTextField txtWhr = new JTextField();
		txtWhr.setBounds(110, 70, 160, 25);
		panel.add(txtWhr);

		JButton btnGenerate = new JButton("Generate Query");
		/*btnGenerate..addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Generate Btn clicked");
			}
		});*/
		btnGenerate.setBounds(10, 100, 80, 25);
		panel.add(btnGenerate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(180, 100, 80, 25);
		panel.add(btnClear);
		
		JTextArea txtFianlQuery = new JTextArea();
		txtFianlQuery.setBounds(10,140,250,100);
		panel.add(txtFianlQuery);
		
		frame.setVisible(true);
	}
}