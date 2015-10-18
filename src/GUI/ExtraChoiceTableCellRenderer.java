package GUI;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ExtraChoiceTableCellRenderer implements TableCellRenderer {
	


 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
 {
//        if (value instanceof Country) {
//           Country country = (Country) value;
//          setText(country.getName());
//     }

	 if (isSelected) {
		 //setBackground(table.getSelectionBackground());
	 } else {
		 //setBackground(table.getSelectionForeground());
	 }


//	 Component c1 = new Component();
 return this;
//  }
 }
}

class MyComboBoxEditor extends DefaultCellEditor {
	  public MyComboBoxEditor(String[] items) {
	    super(new JComboBox(items));
	    System.out.println("value in combobox constructor");
	    System.out.println(items[0]);
	  }
	}

class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
	  public MyComboBoxRenderer(String[] items) {
	    super(items);
	    System.out.println("value in combobox " + items[3]);
	    //System.out.println(items[3]);
	  }

	  
	  // http://www.java2s.com/Code/Java/Swing-JFC/UsingaJComboBoxinaCellinaJTableComponent.htm
	  // Reference for the code from this link Seema
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		  // Check its the extra row added 
		  // only then add the combo otherwise do nothing
		  // 
	    if (isSelected && table.getValueAt(row, 1).equals("")) {
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


//public 
class TextAreaCellRenderer extends AbstractCellEditor implements
TableCellRenderer,TableCellEditor {
private JTextPane textPane;
private JScrollPane scroll;

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


public Component getTableCellRendererComponent(JTable table,
     Object value, boolean isSelected, boolean hasFocus, int row, int column){
        textPane.setText((value != null) ? value.toString() : "");
      return scroll;
    }

//this is what makes it work for the cell
  public Component getTableCellEditorComponent(JTable table, 
          Object value, boolean isSelected, int row, int column) {
      textPane.setText((value != null) ? value.toString() : "");
      textPane.setEditable(false); //this is what makes it uneditable
      return scroll;
  }
}
