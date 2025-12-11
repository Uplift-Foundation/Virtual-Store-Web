package com.upliftfoundation.virtualstoreweb.DataHandlers.FileDataHandlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.ICreateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;

public class CreateData implements ICreateData{
    //ability to create new objects and save to file
    public String filepath;
    public UUID Create(StoreItem item)
    {
        try 
        {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(item.toString());
            pw.flush();
            pw.close();
            return item.getID();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
