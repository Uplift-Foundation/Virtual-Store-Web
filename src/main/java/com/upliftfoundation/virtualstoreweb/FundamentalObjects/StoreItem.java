package com.upliftfoundation.virtualstoreweb.FundamentalObjects;

import java.util.ArrayList;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import com.upliftfoundation.virtualstoreweb.ITextFormatter;
import com.upliftfoundation.virtualstoreweb.DataHandlers.ICreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.IDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.IReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.IUpdateData;

@MappedSuperclass
public class StoreItem {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;
    private double price;
    private String name;
    private String description;
    
    @Transient
    private ArrayList<String> keywords = new ArrayList<String>();
    @Transient
    protected ITextFormatter textFormatter;
    @Transient
    public ICreateData createData;
    @Transient
    public IDeleteData deleteData;
    @Transient
    public IReadData readData;
    @Transient
    public IUpdateData updateData;

    public double getPrice() {
        return this.price;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public UUID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StoreItem(String name, double price, String description) {
        this.price = price;
        this.name = name; 
        this.description = description;
        this.ID = UUID.randomUUID();
        this.keywords = new ArrayList<String>();
    }
    
    public StoreItem() {
    }

    public String GetData(ITextFormatter userTextFormatter)
    {
        this.textFormatter = userTextFormatter;
        return this.textFormatter.GetData();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getDescription();
    }
    //dependency injection is that you're depending on another class (or interface), and injecting, passing, the dependency in as parameter
    //hard coded is if you cannot change the value of a variable
}
