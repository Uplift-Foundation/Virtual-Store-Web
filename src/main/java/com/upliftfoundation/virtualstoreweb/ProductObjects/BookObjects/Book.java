package com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

//import jakarta.persistence.MappedSuperclass;

import com.upliftfoundation.virtualstoreweb.ITextFormatter;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.WeightItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public class Book extends WeightItem 
{
    protected String cover; 
    protected String author;

    public Book(String name, double price, String author,String cover, String description, double weight) {
        //inherited ame, price, description, weight, itemNum
        super(name, price, description, weight);
        this.author = author;
        this.cover = cover;
    }

    public Book() {
        super();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String GetData(ITextFormatter userTextFormatter) {
        this.textFormatter = userTextFormatter;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return "Title: " + this.getName() +  ", Price: $" + df.format(this.textFormatter.getPrice()) + ", Author: " + author + ", Cover: " + cover + ", Description: " + this.getDescription() + ", Weight: " + weight + ", Item Number: " + getID();
    }

    public String determineCondition(){
        Random rand = new Random();
        int upperBound = 365;
        int timeInStore = rand.nextInt(upperBound); 
        if (timeInStore >= 300)
        {
            return "This book is in okay condition.";
        }
        else if (timeInStore >100 && timeInStore < 300) {
            return "This book is in good condition.";
        }
        else {
            return "This book is in fantastic condition";
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getAuthor() + "," + this.getCover() + "," + this.getDescription() + "," + this.getWeight();
    }
}
