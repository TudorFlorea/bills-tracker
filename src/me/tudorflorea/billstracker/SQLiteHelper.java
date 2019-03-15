/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tudorflorea.billstracker;

import java.sql.*;
import java.util.ArrayList;
import me.tudorflorea.billstracker.data.Bill;
import me.tudorflorea.billstracker.data.BillGroup;
import me.tudorflorea.billstracker.data.BillType;

/**
 *
 * @author Tudor
 */
public class SQLiteHelper {
    
    private final String CONNECTION_STRING = "jdbc:sqlite:BillsTracker.db";
    
    private Connection mConnection;
    private String mLastQuery = null;
    private int[] mLastParams = null;
    
    public SQLiteHelper()
    {
        this.mConnection = this.connect();
        this.initDatabase();
    }
    
    private void initDatabase()
    {
        String createBillGroupsTable = "CREATE TABLE IF NOT EXISTS bill_groups ( " +
            "id INTEGER PRIMARY KEY " +
            "UNIQUE " +
            "NOT NULL, " +
            "description VARCHAR (100) NOT NULL " +
            ");";
        
        String createBillTypesTable = "CREATE TABLE IF NOT EXISTS bill_types ( " +
                    "id 		INTEGER PRIMARY KEY " +
                                        "UNIQUE " +
                                        "NOT NULL," +
                    "description 	VARCHAR (100) NOT NULL, " +
                    "bill_group_id 	INTEGER REFERENCES bill_groups (id) ON DELETE CASCADE " +
                                        "ON UPDATE CASCADE " +
                                        "NOT NULL " +
                    ");";
        
        String createBillsTable = "CREATE TABLE IF NOT EXISTS bills ( " +
                    "id 		INTEGER PRIMARY KEY " +
                                        "UNIQUE " +
                                        "NOT NULL, " +
                    "description 	TEXT NOT NULL, " +
                    "due_date           DATE, " +
                    "paid 		BOOLEAN NOT NULL, " +
                    "amount             REAL, " +
                    "paid_date          DATE, " +
                    "bill_type_id 	INTEGER REFERENCES bill_types (id) ON DELETE CASCADE " +
                                        "ON UPDATE CASCADE " +
                                        "NOT NULL " +
                    ");";
        try {
            Statement stmt = mConnection.createStatement();
            stmt.execute(createBillGroupsTable);
            stmt.execute(createBillTypesTable);
            stmt.execute(createBillsTable);
        } catch (Exception e) {
        }
        
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
    
    public ArrayList<BillType> selectBillTypesComplete()
    {
        String sql = "SELECT bill_types.id, bill_types.description AS bill_type_description, bill_types.bill_group_id, bill_groups.description AS bill_group_description " +
                     "FROM bill_types, bill_groups " +
                     "WHERE bill_types.bill_group_id = bill_groups.id";
        ArrayList<BillType> billTypes = new ArrayList<>();
        
        try
        {
            Statement stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int id;
            String billTypedDescription;
            String billGroupDescription;
            int billGropuId;
            while (rs.next()) {
                id = rs.getInt("id");
                billTypedDescription = rs.getString("bill_type_description");
                billGroupDescription = rs.getString("bill_group_description");
                billGropuId =  rs.getInt("bill_group_id");
                billTypes.add(new BillType(id, billTypedDescription, billGropuId, billGroupDescription));
                System.out.println("Id: " + id + ", name: " + billTypedDescription + " bill_group_id: " + billGropuId );
            }
            return billTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
   
    public void insertBillType(String description, int billGroupId)
    {
        String sql = "INSERT INTO bill_types (description, bill_group_id) values (?, ?)";
        try 
        {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.setInt(2, billGroupId);
            pstmt.executeUpdate();
            System.out.println("Valoare inserata");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateBillType(int id, String desctiption, int billGroupId)
    {
        String sql = "UPDATE bill_types SET description=?, bill_group_id=? WHERE id = ?";
        try {
            PreparedStatement pstmt = this.mConnection.prepareStatement(sql);
            pstmt.setString(1, desctiption);
            pstmt.setInt(2, billGroupId);
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteBillType(int id)
    {
        String sql = "DELETE FROM bill_types WHERE id = ?";
        try {
            PreparedStatement pstmt = this.mConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Bill> reQueryBills()
    {
        System.out.println(mLastQuery);
        if(mLastQuery != null || !mLastQuery.isEmpty()) {
            if(mLastParams != null && mLastParams.length > 0) {
                System.out.println("Prepared query");
                return selectBills( preparedQuery(mLastQuery, mLastParams) );
            } else {
                System.out.println("simple query");
                return selectBills( simpleQuery(mLastQuery) );
            }
        } else {
            return selectAllBills();
        }
    }
    
    public ArrayList<Bill> selectAllBills()
    {
        String sql =    "SELECT bills.id, bills.description, bills.paid, bills.amount, bills.due_date, bills.paid_date, bills.bill_type_id, bill_types.description AS bill_type_description, bill_groups.id AS bill_group_id, bill_groups.description AS bill_group_description " +
                "FROM bills, bill_types, bill_groups " +
                "WHERE bills.bill_type_id = bill_types.id AND bill_groups.id = bill_types.bill_group_id";
        mLastQuery = sql;
        mLastParams = null;
        return selectBills( simpleQuery(sql) );
    }
    
    public ArrayList<Bill> selectBillsByBillGroup(int billGroupId)
    {
        String sql =    "SELECT bills.id, bills.description, bills.paid, bills.amount, bills.due_date, bills.paid_date, bills.bill_type_id, bill_types.description AS bill_type_description, bill_groups.id AS bill_group_id, bill_groups.description AS bill_group_description " +
            "FROM bills, bill_types, bill_groups " +
            "WHERE bills.bill_type_id = bill_types.id AND bill_groups.id = bill_types.bill_group_id AND bill_groups.id=?";
        int[] params = new int[] {billGroupId};

        mLastQuery = sql;
        mLastParams = params;

        return selectBills( preparedQuery(sql, params) );
    }
    
    public ArrayList<Bill> selectBillsByBillType(int billTypeId)
    {
        String sql =    "SELECT bills.id, bills.description, bills.paid, bills.amount, bills.due_date, bills.paid_date, bills.bill_type_id, bill_types.description AS bill_type_description, bill_groups.id AS bill_group_id, bill_groups.description AS bill_group_description " +
            "FROM bills, bill_types, bill_groups " +
            "WHERE bills.bill_type_id = bill_types.id AND bill_groups.id = bill_types.bill_group_id AND bill_types.id=?";
        int[] params = new int[] {billTypeId};
            
        mLastQuery = sql;
        mLastParams = params;
            
        return selectBills( preparedQuery(sql, params) );
    }
    
    public ArrayList<Bill> selectBillsByBillGroupAndType(int billGroupId, int billTypeId)
    {
        String sql =    "SELECT bills.id, bills.description, bills.paid, bills.amount, bills.due_date, bills.paid_date, bills.bill_type_id, bill_types.description AS bill_type_description, bill_groups.id AS bill_group_id, bill_groups.description AS bill_group_description " +
            "FROM bills, bill_types, bill_groups " +
            "WHERE bills.bill_type_id = bill_types.id AND bill_groups.id = bill_types.bill_group_id AND bill_groups.id=? AND bill_types.id=? ";
        int[] params = new int[] {billGroupId, billTypeId};
            
        mLastQuery = sql;
        mLastParams = params;
            
        return selectBills( preparedQuery(sql, params) );
    }    
    
    private ResultSet simpleQuery(String sql)
    {
        try {
            Statement stmt = mConnection.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    private ResultSet preparedQuery(String sql, int[] nums) {
        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            
            for(int i = 0; i < nums.length; i++)
            {
                pstmt.setInt(i + 1, nums[i]);
            }
            return pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Bill> selectBills(ResultSet rs)
    {
        ArrayList<Bill> bills = new ArrayList<>();
        
        try
        {
            
            int id;
            String billDescription;
            double billAmount;
            String billDueDate;
            String billPaidDate;
            boolean billPaied;
            int billTypeId;
            int billGroupId;
            String billTypedDescription;
            String billGroupDescription;
            while (rs.next()) {
                id = rs.getInt("id");
                billDescription = rs.getString("description");
                billAmount = rs.getDouble("amount");
                billDueDate = rs.getString("due_date");
                billPaidDate = rs.getString("paid_date");
                billPaied = rs.getString("paid").equals("true");
                billTypeId = rs.getInt("bill_type_id");
                billGroupId = rs.getInt("bill_group_id");
                billTypedDescription = rs.getString("bill_type_description");
                billGroupDescription = rs.getString("bill_group_description");
                bills.add(new Bill(id, billDescription, billPaied, billAmount, billDueDate, billPaidDate, billTypeId, billGroupId, billTypedDescription, billGroupDescription));
//                System.out.println(billDescription);
//                System.out.println(billAmount);
//                System.out.println(billDueDate);
//                System.out.println(billPaidDate);
//                System.out.println(billPaied);
//                System.out.println(billTypeId);
//                System.out.println(billGroupId);
//                System.out.println(billTypedDescription);
//                System.out.println(billGroupDescription);
            }
            return bills;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    

    public void insertBill(String description, String dueDate, boolean paid, double amount, String paidDate, int billTypeId)
    {
        String sql = "INSERT INTO bills (description, due_date, paid, amount, paid_date, bill_type_id) values (?, ?, ?, ?, ?, ?)";
        try 
        {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.setString(2, dueDate);
            pstmt.setBoolean(3, paid);
            pstmt.setDouble(4, amount);
            pstmt.setString(5, paidDate);
            pstmt.setInt(6, billTypeId);
            pstmt.executeUpdate();
            System.out.println("Valoare inserata");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateBill(int id, String description, String dueDate, boolean paid, double amount, String paidDate, int billTypeId)
    {
        String sql = "UPDATE bills SET description=?, due_date=?, paid=?, amount=?, paid_date=?, bill_type_id=? WHERE id = ?";
        try {
            PreparedStatement pstmt = this.mConnection.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.setString(2, dueDate);
            System.out.println(paid);
            pstmt.setString(3, paid ? "true" : "false");
            pstmt.setDouble(4, amount);
            pstmt.setString(5, paidDate);
            pstmt.setInt(6, billTypeId);
            pstmt.setInt(7, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteBill(int id)
    {
        String sql = "DELETE FROM bills WHERE id = ?";
        try {
            PreparedStatement pstmt = this.mConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SQLiteHelper app = new SQLiteHelper();
        app.selectAllBills();
        //app.selctBillTypes();
    }
    
    
    
}
