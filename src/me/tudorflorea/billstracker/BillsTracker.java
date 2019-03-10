/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tudorflorea.billstracker;

import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import me.tudorflorea.billstracker.data.Bill;
import me.tudorflorea.billstracker.data.BillGroup;
import me.tudorflorea.billstracker.data.BillType;
/**
 *
 * @author Tudor
 */
public class BillsTracker extends javax.swing.JFrame {

    /**
     * Creates new form BillsTracker
     */
    //String header[] = new String[] { "Description", "Due Date", "Paid", "Amount", "Paid date", "Bill Type", "Bill Group" };
    //String header[] = new String[] { DESCRIPTION_COLUMN, DUE_DATE_COLUMN, PAID_COLUMN, AMOUNT_COLUMN, PAID_DATE_COLUMN, BILL_TYPE_COLUMN, BILL_GROUP_COLUMN };
    private static final String ID_COLUMN = "id";
    private static final String DESCRIPTION_COLUMN = "Description";
    private static final String DUE_DATE_COLUMN = "Due Date";
    private static final String PAID_COLUMN = "Paid";
    private static final String AMOUNT_COLUMN = "Amount";
    private static final String PAID_DATE_COLUMN = "Paid date";
    private static final String BILL_TYPE_COLUMN = "Bill Type";
    private static final String BILL_GROUP_COLUMN = "Bill Group";
    
    private BillGroupsFrame mBillGroupsFrame;
    private BillTypesFrame mBillTypesFrame;
    private SQLiteHelper mSQLiteHelper;
    private int mCurrentSelectionDbId = 0;
    private ArrayList<Bill> mBills;
    
    public BillsTracker() {
        initComponents();
        mSQLiteHelper = new SQLiteHelper();
        mBillGroupsFrame = new BillGroupsFrame();
        mBillTypesFrame = new BillTypesFrame();
        fillBillGroups();
        fillBillTypes();
        fillCurrentBillTypes();
        fillBillsTable();
        billsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int cols = billsTable.getColumnCount();
                String currentColName = "";
                if(billsTable.getSelectedRow() == -1)
                    return;
                mCurrentSelectionDbId = mBills.get(billsTable.getSelectedRow()).getId();
                
                for(int i = 0; i < cols; i++)
                {
                    currentColName = billsTable.getColumnName(i);
                    
                    if(billsTable.getValueAt(billsTable.getSelectedRow(), i) == null) continue;
                    
                    switch(currentColName) {
                        case ID_COLUMN:
                            
                        
                        case DESCRIPTION_COLUMN:
                            billDescriptionTextField.setText(billsTable.getValueAt(billsTable.getSelectedRow(), i).toString());
                            break;
                        case DUE_DATE_COLUMN:
                            billDueDateTextField.setText(billsTable.getValueAt(billsTable.getSelectedRow(), i).toString());
                            break;
                        case PAID_COLUMN:
                            if(billsTable.getValueAt(billsTable.getSelectedRow(), i).toString().equals("YES")) {
                                billPaidCheckBox.setSelected(true);
                            } else {
                                billPaidCheckBox.setSelected(false);
                            }
                            break;
                        case AMOUNT_COLUMN:
                            billAmountTextField.setText(billsTable.getValueAt(billsTable.getSelectedRow(), i).toString());
                            break;
                        case PAID_DATE_COLUMN:
                            billPaidDateTextField.setText(billsTable.getValueAt(billsTable.getSelectedRow(), i).toString());
                            break;
                        case BILL_TYPE_COLUMN:
                            billTypeComboBox.getModel().setSelectedItem(billsTable.getValueAt(billsTable.getSelectedRow(), i).toString());
                            break;
                        case BILL_GROUP_COLUMN:

                            break;
                        default:
                            
                    }
                }
                
                System.out.println(cols);
                System.out.println(billsTable.getColumnName(0));
                System.out.println(billsTable.getValueAt(billsTable.getSelectedRow(), 0).toString());
            }
        });
        //add()
    }
    
    private void fillBillGroups()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        ArrayList<BillGroup> billGroups = this.mBillGroupsFrame.getBillGroups();
        model.addElement("All");
        for(int i = 1; i <= billGroups.size(); i++)
        {
            model.addElement(billGroups.get(i - 1).getDescription());
        }
  
        this.billGroupsComboBox.setModel(model);
 
    }
    
    private void fillBillTypes()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        ArrayList<BillType> billtypes = this.mBillTypesFrame.getBillTypes();
        model.addElement("All");
        for(int i = 1; i <= billtypes.size(); i++)
        {
            model.addElement(billtypes.get(i - 1).getDescription());
        }
  
        this.billTypesComboBox.setModel(model);
 
    }
    
    private void fillCurrentBillTypes()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        ArrayList<BillType> billtypes = this.mBillTypesFrame.getBillTypes();
        for(int i = 1; i <= billtypes.size(); i++)
        {
            model.addElement(billtypes.get(i - 1).getDescription());
        }
  
        this.billTypeComboBox.setModel(model);
        this.billTypeComboBox.setSelectedIndex(-1);
    }
    
    
    public void fillBillsTable()
    {
        
        mBills = mSQLiteHelper.selectBills();
        
         DefaultTableModel dtm = new DefaultTableModel(0, 0);

        // add header of the table
        String header[] = new String[] { ID_COLUMN, DESCRIPTION_COLUMN, DUE_DATE_COLUMN, PAID_COLUMN, AMOUNT_COLUMN, PAID_DATE_COLUMN, BILL_TYPE_COLUMN, BILL_GROUP_COLUMN };

        // add header in table model     
         dtm.setColumnIdentifiers(header);
            //set model into the table object
               billsTable.setModel(dtm);

             // add row dynamically into the table      
        for (int count = 0; count < mBills.size(); count++) {
            String billId = String.valueOf(mBills.get(count).getId());
            String billDescription =  mBills.get(count).getDescription();
            String billDueDate = mBills.get(count).getDueDate();
            String billPaid = mBills.get(count).isPaid() ? "YES" : "NO";
            String billAmount = String.valueOf(mBills.get(count).getAmount());
            String billPaidDate = mBills.get(count).getPaidDate();
            String billType = mBills.get(count).getBillTypeDescription();
            String billGroup = mBills.get(count).getBillGroupDescription();
            
            dtm.addRow(new Object[] { billId, billDescription, billDueDate, billPaid, billAmount, billPaidDate, billType, billGroup });
         }
    }
    
    
    private void clearFields()
    {
       billDescriptionTextField.setText("");
       billDueDateTextField.setText("");
       billPaidCheckBox.setSelected(false);
       billAmountTextField.setText("");
       billPaidDateTextField.setText("");
       billTypeComboBox.setSelectedIndex(-1);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        billGroupsComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        openBillGroupsButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        billTypesComboBox = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        billsTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        billDescriptionTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        billDueDateTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        billAmountTextField = new javax.swing.JTextField();
        billPaidDateTextField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        billPaidCheckBox = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        billTypeComboBox = new javax.swing.JComboBox<>();
        addBillButton = new javax.swing.JButton();
        editBillButton = new javax.swing.JButton();
        deleteBillButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bills Tracker");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bills Tracker");

        billGroupsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        billGroupsComboBox.setName(""); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bill Groups");

        openBillGroupsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/me/tudorflorea/billstracker/settings_icon.png"))); // NOI18N
        openBillGroupsButton.setToolTipText("");
        openBillGroupsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBillGroupsButtonActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bill TYpes");

        billTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        billTypesComboBox.setName(""); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/me/tudorflorea/billstracker/settings_icon.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        billsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(billsTable);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Description");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Due Date");

        jButton1.setText("jButton1");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Amount");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Paid Date");

        jButton3.setText("jButton1");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Paid");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Type");

        billTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addBillButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addBillButton.setText("Add");
        addBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBillButtonActionPerformed(evt);
            }
        });

        editBillButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        editBillButton.setText("Edit");
        editBillButton.setToolTipText("");
        editBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBillButtonActionPerformed(evt);
            }
        });

        deleteBillButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        deleteBillButton.setText("Delete");
        deleteBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBillButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 161, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(billGroupsComboBox, 0, 130, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openBillGroupsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(billTypesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(billPaidCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(billDescriptionTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(billDueDateTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(billAmountTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(billPaidDateTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(billTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(editBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(deleteBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {billGroupsComboBox, jLabel1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(billGroupsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(openBillGroupsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(billTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(billDescriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(billDueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(billAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(billPaidDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billPaidCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openBillGroupsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBillGroupsButtonActionPerformed
        // TODO add your handling code here:
        this.mBillGroupsFrame.setVisible(true);
    }//GEN-LAST:event_openBillGroupsButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mBillTypesFrame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void addBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBillButtonActionPerformed
        // TODO add your handling code here:
        String billDescription = billDescriptionTextField.getText();
        String dueDate = billDueDateTextField.getText();
        boolean paid = billPaidCheckBox.isSelected();
        double amount = Double.valueOf(billAmountTextField.getText());
        String paidDate = billPaidDateTextField.getText();
        int billTypeIndex = billTypeComboBox.getSelectedIndex();
        int billTypeId = mBillTypesFrame.getBillTypes().get(billTypeIndex).getId();
        
        mSQLiteHelper.insertBill(billDescription, dueDate, paid, amount, paidDate, billTypeId);
        fillBillsTable();
        clearFields();
    }//GEN-LAST:event_addBillButtonActionPerformed

    private void editBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBillButtonActionPerformed
        // TODO add your handling code here:
        String billDescription = billDescriptionTextField.getText();
        String dueDate = billDueDateTextField.getText();
        boolean paid = billPaidCheckBox.isSelected();
        double amount = Double.valueOf(billAmountTextField.getText());
        String paidDate = billPaidDateTextField.getText();
        int billTypeIndex = billTypeComboBox.getSelectedIndex();
        int billTypeId = mBillTypesFrame.getBillTypes().get(billTypeIndex).getId();
        
        mSQLiteHelper.updateBill(mCurrentSelectionDbId, billDescription, dueDate, paid, amount, paidDate, billTypeId);
        fillBillsTable();
        clearFields();
        mCurrentSelectionDbId = 0;
    }//GEN-LAST:event_editBillButtonActionPerformed

    private void deleteBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBillButtonActionPerformed
        // TODO add your handling code here:
        if(mCurrentSelectionDbId != 0)
            mSQLiteHelper.deleteBill(mCurrentSelectionDbId);
        fillBillsTable();
        clearFields();
        mCurrentSelectionDbId = 0;
    }//GEN-LAST:event_deleteBillButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void changeLabel()
    {

    }
    
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
            java.util.logging.Logger.getLogger(BillsTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillsTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillsTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillsTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BillsTracker tracker = new BillsTracker();
                tracker.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBillButton;
    private javax.swing.JTextField billAmountTextField;
    private javax.swing.JTextField billDescriptionTextField;
    private javax.swing.JTextField billDueDateTextField;
    private javax.swing.JComboBox<String> billGroupsComboBox;
    private javax.swing.JCheckBox billPaidCheckBox;
    private javax.swing.JTextField billPaidDateTextField;
    private javax.swing.JComboBox<String> billTypeComboBox;
    private javax.swing.JComboBox<String> billTypesComboBox;
    private javax.swing.JTable billsTable;
    private javax.swing.JButton deleteBillButton;
    private javax.swing.JButton editBillButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openBillGroupsButton;
    // End of variables declaration//GEN-END:variables
}
