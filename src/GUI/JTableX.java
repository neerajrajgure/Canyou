//http://www.javaworld.com/article/2077465/learn-java/java-tip-102--add-multiple-jtable-cell-editors-per-column.html 
//Seema 
package GUI;
import javax.swing.*;
import javax.swing.table.*;

import java.util.Vector;
//Seema: Remove this file code not used 19-0ct-2015
 public class JTableX extends JTable
 {
     protected RowEditorModel rm;

     public JTableX()
     {
         super();
         rm = null;
     }

     public JTableX(TableModel tm)
     {
         super(tm);
         rm = null;
     }

     public JTableX(TableModel tm, TableColumnModel cm)
     {
         super(tm,cm);
         rm = null;
     }

     public JTableX(TableModel tm, TableColumnModel cm,
      ListSelectionModel sm)
     {
         super(tm,cm,sm);
         rm = null;
     }

     public JTableX(int rows, int cols)
     {
         super(rows,cols);
         rm = null;
     }

     public JTableX(final Vector rowData, final Vector columnNames)
     {
         super(rowData, columnNames);
         rm = null;
     }

     public JTableX(final Object[][] rowData, final Object[] colNames)
     {
         super(rowData, colNames);
         rm = null;
     }

     // new constructor
     public JTableX(TableModel tm, RowEditorModel rm)
     {
         super(tm,null,null);
         this.rm = rm;
     }

     public void setRowEditorModel(RowEditorModel rm)
     {
         this.rm = rm;
     }

     public RowEditorModel getRowEditorModel()
     {
         return rm;
     }

     public TableCellEditor getCellEditor(int row, int col)
     {
    	 
    	 
         TableCellEditor tmpEditor = null;
         if (rm!=null)
         {
             //tmpEditor = rm.getEditor(row);
             // ££££
             if ((row < 8 ) && (col == 1))
             {
            	 tmpEditor = rm.getEditor(1);
             System.out.println(" getCellEditor : In the if condition " + "  Row=" + row +" col=" + col  + "   &&&&& " );
             }
             else 
             {
            	 tmpEditor = rm.getEditor(2);
             System.out.println(" getCellEditor : In the if condition " + "  Row=" + row +" col=" + col  + "   ===== " );
             }
            	 
            	 
         }
         
         
         if (tmpEditor!=null)
             return tmpEditor;
         System.out.println(" getCellEditor : shouldnt reach here ..  " + "  Row=" + row +" col=" + col  + "   +++++ " );
         return super.getCellEditor(row,col);
     }
 }