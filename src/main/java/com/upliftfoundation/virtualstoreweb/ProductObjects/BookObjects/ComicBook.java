package com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "comicbook")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ComicBook extends Book 
{
    protected String artist;

    public ComicBook(String name, double price, String author, String cover, String description, String artist, double weight) {
        //inherited name, price, description, weight, itemNum, author, cover
        super(name, price, author, cover, description, weight);
        this.artist = artist;
    }

    public ComicBook() {
        super();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String GetData() {
        return "Title: " + this.getName() + ", Price: $" + this.textFormatter.getPrice() + ", Author: " + author + ", Cover: " + cover + ", Artist: " + artist+ ", Weight: " + weight + ", Item Number: " + getID();
    }

    @Override 
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getAuthor() + "," + this.getCover() + "," +this.getDescription() + "," + this.getArtist() + "," + this.getWeight();
    }
}
