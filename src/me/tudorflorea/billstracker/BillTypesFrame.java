/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tudorflorea.billstracker;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import me.tudorflorea.billstracker.data.BillGroup;
import me.tudorflorea.billstracker.data.BillType;

/**
 *
 * @author Tudor
 */
public class BillTypesFrame extends javax.swing.JFrame {

    
    private SQLiteHelper mSQLiteHelper;
    private ArrayList<BillType> mBillTypes;
    private ArrayList<BillGroup> mBillGroups;
    private int mCurrentSelectionDbId = 0;
    
    /**
     * Creates new form BillTypes
     */
    public BillTypesFrame() {
        initComponents();
        mSQLiteHelper = new SQLiteHelper();
        fillBillTypesList();
        fillBillGroupsList();
    }
    
    private void fillBillTypesList()
    {
        mBillTypes = new ArrayList<>();
        DefaultListModel defaultListModel = new DefaultListModel();
        
        mBillTypes = mSQLiteHelper.selectBillTypesComplete();
        
        for(BillType billType: mBillTypes)
        {
            defaultListModel.addElement(billType.getDescription() + " - " + billType.getBillGroupDescription());
        }
        
        billTypesList.setModel(defaultListModel);
        
    }
    
    public ArrayList<BillType> getBillTypes()
    {
        return this.mBillTypes;
    }
    
    private void fillBillGroupsList()
    {
        mBillGroups = new ArrayList<>();
        DefaultComboBoxModel defaultListModel = new DefaultComboBoxModel();
        
        mBillGroups = mSQLiteHelper.selctBillGroups();
        
        for(BillGroup billGroup: mBillGroups)
        {
            defaultListModel.addElement(billGroup.getDescription());
        }
        
        currentBillGroupComboBox.setModel(defaultListModel);
        currentBillGroupComboBox.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        billTypesList = new javax.swing.JList<>();
        currentBillTypeTextField = new javax.swing.JTextField();
        currentBillGroupComboBox = new javax.swing.JComboBox<>();
        editBillTypeButton = new javax.swing.JButton();
        addBillTypeButton = new javax.swing.JButton();
        deleteBillTypeButton = new javax.swing.JButton();

        setAlwaysOnTop(true);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bill Types");

        billTypesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        billTypesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                billTypesListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(billTypesList);

        currentBillGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        editBillTypeButton.setText("Edit");
        editBillTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBillTypeButtonActionPerformed(evt);
            }
        });

        addBillTypeButton.setText("Add");
        addBillTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBillTypeButtonActionPerformed(evt);
            }
        });

        deleteBillTypeButton.setText("Delete");
        deleteBillTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBillTypeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(currentBillTypeTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentBillGroupComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addBillTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editBillTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBillTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(currentBillTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentBillGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBillTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBillTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBillTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void billTypesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_billTypesListValueChanged
        // TODO add your handling code here:
        if (billTypesList.getSelectedIndex() != -1) {
            BillType currentBillType = mBillTypes.get(billTypesList.getSelectedIndex());
            System.out.println("Selected id " + billTypesList.getSelectedIndex());
            mCurrentSelectionDbId = currentBillType.getId();
            System.out.println("DB ID: " + mCurrentSelectionDbId);
            currentBillTypeTextField.setText(currentBillType.getDescription());
            
            for(int i = 0 ; i < mBillGroups.size(); i++)
            {
                if(currentBillType.getBillGroupId() == mBillGroups.get(i).getId())
                {
                    currentBillGroupComboBox.setSelectedIndex(i);
                }
            }
            
        }
    }//GEN-LAST:event_billTypesListValueChanged

    private void addBillTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBillTypeButtonActionPerformed
        // TODO add your handling code here:
        if(!currentBillTypeTextField.getText().isEmpty())
        {
            mSQLiteHelper.insertBillType(currentBillTypeTextField.getText(), mBillGroups.get(currentBillGroupComboBox.getSelectedIndex()).getId());
            fillBillTypesList();
            fillBillGroupsList();
            currentBillTypeTextField.setText("");
        }
    }//GEN-LAST:event_addBillTypeButtonActionPerformed

    private void editBillTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBillTypeButtonActionPerformed
        // TODO add your handling code here:
        if(!currentBillTypeTextField.getText().isEmpty() && mCurrentSelectionDbId != 0)
        {
            mSQLiteHelper.updateBillType(mCurrentSelectionDbId, currentBillTypeTextField.getText(), mBillGroups.get(currentBillGroupComboBox.getSelectedIndex()).getId());
            fillBillTypesList();
            fillBillGroupsList();
            currentBillTypeTextField.setText("");
            mCurrentSelectionDbId = 0;
            currentBillGroupComboBox.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_editBillTypeButtonActionPerformed

    private void deleteBillTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBillTypeButtonActionPerformed
        // TODO add your handling code here:
        if(mCurrentSelectionDbId != 0)
        {
            mSQLiteHelper.deleteBillType(mCurrentSelectionDbId);
            fillBillTypesList();
            fillBillGroupsList();
            currentBillTypeTextField.setText("");
        }
    }//GEN-LAST:event_deleteBillTypeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillTypesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillTypesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillTypesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillTypesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillTypesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBillTypeButton;
    private javax.swing.JList<String> billTypesList;
    private javax.swing.JComboBox<String> currentBillGroupComboBox;
    private javax.swing.JTextField currentBillTypeTextField;
    private javax.swing.JButton deleteBillTypeButton;
    private javax.swing.JButton editBillTypeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}