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
public class BillType {
    private int mId;
    private String mDescription;
    private int mBillGroupId;
    
    public BillType(int id, String description, int billGroupId)
    {
        mId = id;
        mDescription = description;
        mBillGroupId = billGroupId;
    }
    
    public int getId()
    {
        return this.mId;
    }
    
    public String getDescription()
    {
        return this.mDescription;
    }
    
    public int getBillGroupId()
    {
        return this.mBillGroupId;
    }
    
}
