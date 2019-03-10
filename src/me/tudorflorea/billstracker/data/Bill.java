/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tudorflorea.billstracker.data;

/**
 *
 * @author Tudor
 */
public class Bill {
    private int mId;
    private String mDescription;
    private boolean mPaid;
    private double mAmout;
    private String mDueDate;
    private String mPaidDate;
    private int mBillTypeId;
    private int mBillGroupId;
    private String mBillTypeDescription;
    private String mBillGroupDescription;
    
    public Bill(int id, String description, boolean paid, double amoutnt, String dueDate, String paidDate, int billTypeId, int billGroupid, String billTypeDescription, String billGroupDescription)
    {
        mId = id;
        mDescription = description;
        mPaid = paid;
        mAmout = amoutnt;
        mDueDate = dueDate;
        mPaidDate = paidDate;
        mBillTypeId = billTypeId;
        mBillGroupId = billGroupid;
        mBillTypeDescription = billTypeDescription;
        mBillGroupDescription = billGroupDescription;
    }
    
    public String getDescription()
    {
        return this.mDescription;
    }
    
    public double getAmount()
    {
        return this.mAmout;
    }
    
    public String getDueDate()
    {
        return this.mDueDate;
    }
    
    public String getPaidDate()
    {
        return this.mPaidDate;
    }
    
    public int getId()
    {
        return this.mId;
    }
    
    public int getBillTypeId()
    {
        return this.mBillTypeId;
    }
    
    public int getBillGroupId()
    {
        return this.mBillGroupId;
    }
    
    public String getBillTypeDescription()
    {
        return this.mBillTypeDescription;
    }
    
    public String getBillGroupDescription()
    {
        return this.mBillGroupDescription;
    }
    
    public boolean isPaid()
    {
        return this.mPaid;
    }
    
    
    
    
    
    
}
