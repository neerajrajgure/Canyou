package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

//Seema : http://www.codejava.net/java-se/swing/how-to-create-jcombobox-cell-editor-for-jtable
/*
public class ExtraChoiceTableCellRenderer implements TableCellRenderer {
	


 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
 {
	 if (isSelected) {
		 //setBackground(table.getSelectionBackground());
	 } else {
		 //setBackground(table.getSelectionForeground());
	 }


 return this;
 }
}*/

/// Seema : not using these classes now 19-oct 2015
class MyComboBoxEditor extends DefaultCellEditor {
	  public MyComboBoxEditor(String[] items) {
	    super(new JComboBox(items));
	    System.out.println("value in combobox constructor");
	    System.out.println(items[0]);
	  }
	}
// Seema : not using these classes now 19-oct 2015
class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
	  public MyComboBoxRenderer(String[] items) {
	    super(items);
	    System.out.println("value in combobox " + items[3]);
	   
	  }

	  
	  // http://www.java2s.com/Code/Java/Swing-JFC/UsingaJComboBoxinaCellinaJTableComponent.htm
	  // Reference for the code from this link Seema
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		  // Check its the extra row added 
		  // only then add the combo otherwise do nothing
		  // 
	    if (isSelected && table.getValueAt(row, 0).equals("")) {
	      setForeground(table.getSelectionForeground());
	      super.setBackground(table.getSelectionBackground());	      
	      System.out.println(" getTableCellRenderedComponent : In the if condition " + value.toString() + "  Row=" + row +" col=" + column  + "   #### " +  table.getValueAt(row, column).toString());
		    
	    } else {
	     setForeground(table.getForeground());
	     setBackground(table.getBackground());
	    }
	    
	    setSelectedItem(value);
		  System.out.println("getTableCellRenderedComponent : value in table " + value.toString() + "  Row=" + row +" col=" + column  + "   $$$$  " + table.getValueAt(row, column).toString());
	    return this;
	  }
	}

/*
 *  The TextAreaCellRenderer will return appropriate cell rendered according to the column selected
 *  We need a combo box and otherwise we need a cell editor
 */


//public 
class TextAreaCellRenderer extends AbstractCellEditor implements
TableCellRenderer,TableCellEditor {
private JTextPane textPane;
private JScrollPane scroll;
private String ExtraChoiceCombo[] = { " 1  cheese", " 2 coffee  ", "3 extra ", " 4 Four",
" 5 Item Five" };
//private JComboBox<String> cExtraItem;


public TextAreaCellRenderer() {
    textPane = new JTextPane();
  scroll = new JScrollPane(textPane);
}
public JTextPane getTextArea(){
      return textPane;
}

public Object getCellEditorValue() {
  return textPane.getText();
}
//this is what makes it work for the for the column


public Component getTableCellRendererComponent(final JTable table,
     Object value, boolean isSelected, boolean hasFocus, final int row, int column){
		//Seema : Cehck if the row is the newly added row.
	    // if yes it has to be a combo box else its editable
	    //As we do not need any scroll value
       // textPane.setText((value != null) ? value.toString() : "");
      //return scroll; 
	  Object obj1 = table.getValueAt(row, column);
	  
	  if (obj1.toString().equals("") )
	  {
		  JComboBox<String> cb = new JComboBox(ExtraChoiceCombo);
		  //Create an action listenenr for the combobox component
		  cb.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                //
	                // Get the source of the component, which is our combo
	                // box.
	                //
	            	//
	            	System.out.println("++++ combobox : Actionlistener");
	            	table.setValueAt("1", row, 3);
	            	table.setValueAt("40", row, 4);
	            	table.setValueAt("40.0", row, 5);	            

	            }
	        });
		  System.out.println("***combobox : value in table " + value.toString() + "  Row=" + row +" col=" + column  + "   $$$$  " + table.getValueAt(row, column).toString());
		  return cb;  
	  }
	  else
	  {
		     textPane.setText((value != null) ? value.toString() : "");
		     System.out.println("***TextAreaCellRenderer : value in table " + value.toString() + "  Row=" + row +" col=" + column  + "   $$$$  " + table.getValueAt(row, column).toString());
		     return textPane;
	  }
       // return textPane;
    }

//this is what makes it work for the cell
  public Component getTableCellEditorComponent(JTable table, 
          Object value, boolean isSelected, int row, int column) {
      //textPane.setText((value != null) ? value.toString() : "");
      //textPane.setEditable(false); //this is what makes it uneditable
      //return scroll; //Seema : As we do not want it scrollable
    //  return textPane;
      
      Object obj1 = table.getValueAt(row, 0);//hardcoding value for 1st column
	  
	  if (obj1.toString().equals("") )
	  {
		  JComboBox<String> cb = new JComboBox(ExtraChoiceCombo);
		  System.out.println("000Combobox :getTableCellEditorComponent value in table " + value.toString() + "  Row=" + row +" col=" + column  + "   $$$$  " + table.getValueAt(row, column).toString());
		  return cb;  
	  }
	  else
	  {
		     textPane.setText((value != null) ? value.toString() : "");
		     System.out.println("111Text getTableCellEditorComponent" + value.toString() + "  Row=" + row +" col=" + column  + "   $$$$  " + table.getValueAt(row, column).toString());
		     //The text field is uneditable
		     if (row != 1)
		     {
		    	 System.out.println("222Text getTableCellEditorComponent" + value.toString() + "  Row=" + row +" col=" + column  + "   $$$$  " + table.getValueAt(row, column).toString());
		    	 textPane.setEditable(false); //this is what makes it uneditable
		     }
		     return textPane;
	  }
  }
}

//Seema Reference : http://stackoverflow.com/questions/16453197/checkbox-in-some-cells-but-not-all-in-a-particular-column-jtable?rq=1
