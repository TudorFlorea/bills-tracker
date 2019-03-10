/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tudorflorea.billstracker.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Tudor
 */
public class CsvWriter {
    
    private StringBuilder mCsvStringBuilder;
    private File mCvsFile;
    private PrintWriter mPrintWriter;
    
    public CsvWriter()
    {
        mCsvStringBuilder = new StringBuilder();
    }
    
    public void appedCsvLine(String line)
    {
        mCsvStringBuilder.append(line);
    }
    
    public void appendValue(String value, boolean isLast)
    {
        mCsvStringBuilder.append(value);
        if(isLast) {
            mCsvStringBuilder.append("\n");
        } else {
            mCsvStringBuilder.append(",");
        }
    }
    
    public void save()
    {
        try {

            mCvsFile = new File("bills.csv");
            mPrintWriter = new PrintWriter(mCvsFile);
            
            mPrintWriter.write(mCsvStringBuilder.toString());

            System.out.println("exported!");

        } catch (FileNotFoundException e) {
          System.out.println(e.getMessage());
        }
    }
    
}
