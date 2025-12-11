package com.upliftfoundation.virtualstoreweb.DataHandlers.FileDataHandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;
import java.util.Scanner;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.IReadData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Basketball;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.Book;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.UsedBook;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.ComicBook;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.Textbook;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Computer;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Powerplant;

//checking repo
public class ReadData implements IReadData {

    private String filepath;
    private File file;
    private Scanner fileScanner;
    private UUID id;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    @Override
    public StoreItem Read(UUID ID) {
        // open the file
        this.id = ID;
        try {
            this.file = new File(filepath);
            this.fileScanner = new Scanner(this.file);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // search the file for the id
        while (this.fileScanner != null && this.fileScanner.hasNextLine()) {
            
            // read the line with the id
            String line = this.fileScanner.nextLine();
            
            if(line.contains(id.toString())) 
            { 
                // separate the line by commas into properties
                String[] props = line.split(",");
                // if the first property is a Basketball
                if (props[0].equals("Basketball")) {
                    // create a Basketball object using the properties
                    Basketball storeItemToReturn = new Basketball(props[2], Double.parseDouble(props[3]), Integer.parseInt(props[4]), Integer.parseInt(props[5]), props[6], props[7], Double.parseDouble(props[8]));
                    storeItemToReturn.setID(UUID.fromString(props[1]));
                    // return the object (Computer, Basketball, etc.) at the end
                    return storeItemToReturn;
                }
                else if (props[0].equals("Book")) {
                    Book storeItemToReturn = new Book(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], Double.parseDouble(props[7]));
                    storeItemToReturn.setID(UUID.fromString(props[1]));
                    return storeItemToReturn;
                }
                else if (props[0].equals("ComicBook")) {
                    ComicBook storeItemToReturn = new ComicBook(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], props[7], Double.parseDouble(props[8]));
                    storeItemToReturn.setID(UUID.fromString(props[1]));
                    return storeItemToReturn;
                }
                else if (props[0].equals("Computer")) {
                    Computer storeItemToReturn = new Computer(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], props[7], Double.parseDouble(props[8]));
                    storeItemToReturn.setID(UUID.fromString(props[1]));
                    return storeItemToReturn;
                }
                else if (props[0].equals("Powerplant")){
                    Powerplant storeItemToReturn = new Powerplant(props[2], Double.parseDouble(props[3]), props[4], Double.parseDouble(props[5]));
                    storeItemToReturn.setID(UUID.fromString(props[1]));
                    return storeItemToReturn;
                }
                else if (props[0].equals("Textbook")) 
                {
                    String[] splitLine = line.split(",");
                    String[] props2 = Arrays.copyOfRange(splitLine, 0, 7);
                    String[] secondPart = Arrays.copyOfRange(splitLine, 7, splitLine.length);
                    
                    String[] authorsToRead = Arrays.copyOfRange(secondPart,0, secondPart.length - 1);
                    String weightToRead = secondPart[secondPart.length - 1];
                    
                    List<String> authorsList = new ArrayList<String>(Arrays.asList(authorsToRead));

                    Textbook storeItemToReturn = new Textbook(props2[2], Double.parseDouble(props2[3]), props2[4],props2[5], props2[6], authorsList , Double.parseDouble(weightToRead));
                    storeItemToReturn.setID(UUID.fromString(props[1])); 
                    return storeItemToReturn;
                }
                else if (props[0].equals("UsedBook")) {
                    UsedBook storeItemToReturn = new UsedBook(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], props[7], Double.parseDouble(props[8]));
                    storeItemToReturn.setID(UUID.fromString(props[1]));
                    return storeItemToReturn;
                }
            }
        }
        return null;
    }
  

    @Override
    public List<StoreItem> ReadAll() {

        List<StoreItem> itemsToReturn = new ArrayList<StoreItem>();

        try {
            this.file = new File(filepath);
            this.fileScanner = new Scanner(this.file);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // search the file for the id
        while (this.fileScanner != null && this.fileScanner.hasNextLine()) {
            
            // read the line with the id
            String line = this.fileScanner.nextLine();

            String datatype = line.split(",")[0]; //split in first comma to have data type
            if (datatype.equals("Textbook")) 
            {
                String[] splitLine = line.split(",");
                String[] props = Arrays.copyOfRange(splitLine, 0, 7);
                String[] secondPart = Arrays.copyOfRange(splitLine, 7, splitLine.length);
                
                String[] authorsToRead = Arrays.copyOfRange(secondPart,0, secondPart.length - 1);
                String weightToRead = secondPart[secondPart.length - 1];
                
                List<String> authorsList = new ArrayList<String>(Arrays.asList(authorsToRead));

                Textbook textbooktoReturn = new Textbook(props[2], Double.parseDouble(props[3]), props[4],props[5], props[6], authorsList , Double.parseDouble(weightToRead));
                textbooktoReturn.setID(UUID.fromString(props[1])); 
                itemsToReturn.add(textbooktoReturn);
            }
            else {
                // separate the line by commas into properties
                String[] props = line.split(",");
                // if the first property is a StoreItem
                if (props[0].equals("StoreItem")) {                
                    StoreItem storeItemToReturn = new StoreItem(props[2], Double.parseDouble(props[3]), props[4]);
                    storeItemToReturn.setID(UUID.fromString(props[1]));       
                    itemsToReturn.add(storeItemToReturn);
                }
                //in alphabetical order henceforth
                else if (props[0].equals("Basketball")) {
                    Basketball basketballToReturn = new Basketball(props[2], Double.parseDouble(props[3]), Integer.parseInt(props[4]), Integer.parseInt(props[5]), props[6], props[7], Double.parseDouble(props[8]));
                    basketballToReturn.setID(UUID.fromString(props[1]));
                    itemsToReturn.add(basketballToReturn);
                }
                else if (props[0].equals("Book")) {
                    Book booktoReturn = new Book(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], Double.parseDouble(props[7]));
                    booktoReturn.setID(UUID.fromString(props[1]));
                    itemsToReturn.add(booktoReturn);
                }
                else if (props[0].equals("Powerplant")){
                    Powerplant energyToReturn = new Powerplant(props[2], Double.parseDouble(props[3]), props[4], Double.parseDouble(props[5]));
                    energyToReturn.setID(UUID.fromString(props[1]));       
                    itemsToReturn.add(energyToReturn);
                }
                else if (props[0].equals("ComicBook")) {
                    ComicBook comicBooktoReturn = new ComicBook(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], props[7], Double.parseDouble(props[8]));
                    comicBooktoReturn.setID(UUID.fromString(props[1])); 
                    itemsToReturn.add(comicBooktoReturn);
                }
                else if (props[0].equals("Computer")) {                
                    Computer computerToReturn = new Computer(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], props[7], Double.parseDouble(props[8]));
                    computerToReturn.setID(UUID.fromString(props[1]));       
                    itemsToReturn.add(computerToReturn);
                }
                else if (props[0].equals("UsedBook")) {
                    UsedBook usedBooktoReturn = new UsedBook(props[2], Double.parseDouble(props[3]), props[4], props[5], props[6], props[7], Double.parseDouble(props[8]));
                    usedBooktoReturn.setID(UUID.fromString(props[1]));
                    itemsToReturn.add(usedBooktoReturn);
                }
            }
        }
        return itemsToReturn;
    }
}
