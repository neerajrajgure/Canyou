//http://www.javaworld.com/article/2077465/learn-java/java-tip-102--add-multiple-jtable-cell-editors-per-column.html
// To have a different editor for a cell depending on if we need a combo box or no
// Here we choose to show a list if its an extra item other wise it should be a normal table cell
package GUI;
import javax.swing.table.*;
import java.util.*;
// Seema: Remove this file code not used 19-oct-2015
//Removing public keyword to avoid using a seperate file 
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



