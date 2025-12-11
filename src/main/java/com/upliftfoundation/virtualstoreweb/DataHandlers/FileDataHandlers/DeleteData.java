package com.upliftfoundation.virtualstoreweb.DataHandlers.FileDataHandlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.IDeleteData; 

public class DeleteData implements IDeleteData {
    public String tempFile;
    public String filepath;
    String name;
    String description;
    String price;
    UUID ID;

    public void Delete(UUID IDforRemove) {    

      //Add ability to delete objects from file
      File oldFile = new File(filepath);
      File newFile = new File(tempFile);

      try  
        {
        FileWriter fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        Scanner filescanner = new Scanner (new File(filepath));
        filescanner.useDelimiter("{,\n");
        
        while(filescanner.hasNext())
          {
            ID = UUID.fromString(filescanner.next());
            name = filescanner.next();
            price = filescanner.next();
            description = filescanner.next();
            if(!ID.equals(IDforRemove))
            {
              pw.println(ID + ","+ name + "," + price + "," + description);
            }
            filescanner.close();
            pw.flush();
            pw.close ();
            oldFile.delete();
            File dump = new File (filepath);
            newFile.renameTo(dump);
          }
        }
        catch(Exception e) {
            System.out.println("Error");
        }

      }

  }