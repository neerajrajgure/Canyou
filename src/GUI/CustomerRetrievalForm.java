package GUI;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CustomerRetrievalForm extends javax.swing.JDialog {
    private static CafeBill _cb;
    public CustomerRetrievalForm(CafeBill cb){
        _cb = cb;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration   

    /**
     * Creates new form CustomerReterivalForm
     * @throws SQLException 
     * @wbp.parser.constructor
     */

    public CustomerRetrievalForm(Vector searchlist) throws SQLException {
        setTitle("Customer Data");
        setModal(true);

        initComponents(searchlist);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws SQLException 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(Vector searchList) throws SQLException {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();


        Vector colNames = new Vector();

        String[] strColNames = new String []{ "cid-1", "Fname", "Lname", "Address", "phonenum", "phone", "emailid", "DOB", "flag"};

        String str;
        for(int i = 0; i < strColNames.length; i++) {
            colNames.add(strColNames[i]);
        }

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel( searchList, colNames)
                /*
            new Object [][] {
                {1, 2, 3, 4, 5, null, null, null, null},-
              },
            new String [] {
                "cid", "Fname", "Lname", "Address", "phonenum", "phone", "emailid", "DOB", "flag"
            }
                 */
                );
        jScrollPane1.setViewportView(jTable1);

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(new ActionListener() {
        	long cid;

        	public void actionPerformed(ActionEvent e) {
                for (int row : jTable1.getSelectedRows()) {
                    cid = new Long(jTable1.getValueAt(row ,0).toString()).longValue();
                    GUI.CafeBill.cid = cid;
                    System.out.println("Customre ids on select in CustomeRetrivalform : " +cid  );
                    DefaultTableModel dm = (DefaultTableModel)jTable1.getModel();
                    while(dm.getRowCount() > 0)
                    {
                        dm.removeRow(0);
                    }
                    dispose();
                }
                try {
					RewardDialogue rd = new RewardDialogue(cid);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
           
 
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 803, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(btnSelect)))
                        .addContainerGap(20, Short.MAX_VALUE))
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnSelect)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        System.out.println(" in customer retival form - Start");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerRetrievalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerRetrievalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerRetrievalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerRetrievalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new CustomerRetrievalForm().setVisible(true);
            }
        });
    }
}
