/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tudorflorea.billstracker;

import java.sql.*;
import java.util.ArrayList;
import me.tudorflorea.billstracker.data.BillGroup;
import me.tudorflorea.billstracker.data.BillType;

/**
 *
 * @author Tudor
 */
public class SQLiteHelper {
    
    private final String CONNECTION_STRING = "jdbc:sqlite:BillsTracker.db";
    
    private Connection mConnection;
    
    public SQLiteHelper()
    {
        this.mConnection = this.connect();
    }
    
        
    private Connection connect()
    {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
    
    public void SelecteazaToate()
    {
        String sql = "SELECT * FROM bill_groups";
        try
        {
            
            Statement stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {                
                System.out.println("Id: " + rs.getInt("id") + ", name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            
        }
    }
    
    public ArrayList<BillGroup> selctBillGroups()
    {
        String sql = "SELECT * FROM bill_groups";
        ArrayList<BillGroup> billGroups = new ArrayList<>();
        try
        {
            
            Statement stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int id;
            String description;
            while (rs.next()) {
                id = rs.getInt("id");
                description = rs.getString("description");
                billGroups.add(new BillGroup(id, description));
                System.out.println("Id: " + rs.getInt("id") + ", name: " + rs.getString("description"));
            }
            return billGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void insertBillGroup(String description)
    {
        String sql = "INSERT INTO bill_groups (description) values (?)";
        try 
        {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.executeUpdate();
            System.out.println("Valoare inserata");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteBillGroup(int id)
    {
        String sql = "DELETE FROM bill_groups WHERE id = ?";
        try {
            PreparedStatement pstmt = this.mConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        public void updateBillGroup(int id, String desctiption)
    {
        String sql = "UPDATE bill_groups SET description=? WHERE id = ?";
        try {
            PreparedStatement pstmt = this.mConnection.prepareStatement(sql);
            pstmt.setString(1, desctiption);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<BillType> selctBillTypes()
    {
        String sql = "SELECT * FROM bill_types";
        ArrayList<BillType> billTypes = new ArrayList<>();
        try
        {
            
            Statement stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int id;
            String description;
            int billGropuId;
            while (rs.next()) {
                id = rs.getInt("id");
                description = rs.getString("description");
                billGropuId =  rs.getInt("bill_group_id");
                billTypes.add(new BillType(id, description, billGropuId));
                System.out.println("Id: " + rs.getInt("id") + ", name: " + rs.getString("description") + " bill_group_id: " + rs.getInt("bill_group_id") );
            }
            return billTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
        
    public static void main(String[] args) {
        SQLiteHelper app = new SQLiteHelper();
        app.selctBillGroups();
        app.selctBillTypes();
    }
    
    
    
}
