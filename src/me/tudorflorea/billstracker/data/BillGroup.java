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
public class BillGroup {
    private int mId;
    private String mDescription;
    
    public BillGroup(int id, String description)
    {
        this.mId = id;
        this.mDescription = description;
    }
    
    public int getId()
    {
        return this.mId;
    }
    
    public String getDescription()
    {
        return this.mDescription;
    }
    
}
