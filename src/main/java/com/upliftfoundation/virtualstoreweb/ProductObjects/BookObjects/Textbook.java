package com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name = "textbook")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Textbook extends Book 
{
    @Transient
    protected List<String> authors = new ArrayList<String>();

    public Textbook(String name, double price, String author, String cover, String description, List<String> authors, double weight) {
        ////inherited name, price, description, weight, itemNum, author, cover
        super(name, price, author, cover, description, weight);
        this.authors = authors;
    }
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    public String GetData() {

        return "Title: " + this.getName() + ", Price: $" + this.textFormatter.getPrice() + ", Authors: " + String.join(",", authors) + ", Cover: " + cover + ", Weight: " + weight + ", Item Number: " + getID();
        
    }
    @Override 
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getAuthor() + "," + this.getCover() + "," +this.getDescription() + "," + this.getAuthors() + "," + this.getWeight();
    }
}
