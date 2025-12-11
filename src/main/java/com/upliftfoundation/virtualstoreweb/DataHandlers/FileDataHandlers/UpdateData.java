package com.upliftfoundation.virtualstoreweb.DataHandlers.FileDataHandlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.IUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;

public class UpdateData implements IUpdateData{
    //Add ability to update existing objects from file and resave back to file
    String tempFile;
    public String filepath;
    String name;
    String description;
    String price;
    UUID ID;
    Scanner fileScanner;
    public StoreItem Update(StoreItem item){
        Scanner fileScanner = new Scanner(System.in);
        String tempFile = "temporary.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        try 
        {
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            fileScanner = new Scanner(new File(filepath));
            fileScanner.useDelimiter("[,\n]");

            while(fileScanner.hasNext())
            {
                name = fileScanner.next();
                description = fileScanner.next();
                price = fileScanner.next();
                ID = UUID.fromString(fileScanner.next());
                //possible problem in code. Creating new UUID here?
                UUID testID = item.getID();
                if(ID.equals(testID))
                {
                    item.setID(testID);
                    pw.println(item.getName() + "," + item.getDescription() + "," + item.getPrice() + "," + item.getID());
                }
                else
                {
                    pw.println("Did not update");//pw.println(Name + "," + Description + "," + Price + "," + ID);
                }
            }
            fileScanner.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
            }
        catch(Exception e)
        {
            System.out.println("Error");
        } 
        return item;
        }
}