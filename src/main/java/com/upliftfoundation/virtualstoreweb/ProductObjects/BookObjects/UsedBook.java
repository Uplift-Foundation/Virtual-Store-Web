package com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "usedbook")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UsedBook extends Book 
{
    protected String condition;
    
    public UsedBook(String name, double price, String author, String cover, String description, String condition, double weight) {    
        super(name, price, author, cover, description, weight);
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    public String GetData() {
        return "Title: " + this.getName() + ", Price: $" + this.getPrice() + ", Author: " + author + ", Cover: " + cover + ", Condition: " + condition + ", Weight: " + weight + ", Item Number: " + getID();
    }

    @Override 
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getAuthor() + "," + this.getCover() + "," +this.getDescription() + "," + this.getCondition() + "," + this.getWeight();
    }   
}
