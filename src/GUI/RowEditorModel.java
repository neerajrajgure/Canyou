//http://www.javaworld.com/article/2077465/learn-java/java-tip-102--add-multiple-jtable-cell-editors-per-column.html
// To have a different editor for a cell depending on if we need a combo box or no
// Here we choos to show a list if its an extra item other wise it should be a normal table cell
package GUI;
import javax.swing.table.*;
import java.util.*;

//Removing public keywork to avoid usign a seperate file 
//public 
class RowEditorModel
 {
   private Hashtable data;
      public RowEditorModel()
      {
          data = new Hashtable();
      }
     public void addEditorForRow(int row, TableCellEditor e )
     {
         data.put(new Integer(row), e);
     }
     public void removeEditorForRow(int row)
     {
         data.remove(new Integer(row));
     }
     public TableCellEditor getEditor(int row)
     {
         return (TableCellEditor)data.get(new Integer(row));
     }
 }



