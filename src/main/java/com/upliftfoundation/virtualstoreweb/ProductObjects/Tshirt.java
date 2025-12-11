package com.upliftfoundation.virtualstoreweb.ProductObjects;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.upliftfoundation.virtualstoreweb.ITextFormatter;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.FileUploadItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "tshirt")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tshirt extends FileUploadItem
{
    private double width;
    private double length;      
    private String size;
    private String color;

    public Tshirt(String name, double price, String color, String description, double weight, double width, double length,
    String size) {
        super(name, price, description, weight);
        this.setWidth(width);
        this.setLength(length);
        this.setSize(size);
        this.setColor(color);
    }
    
    public Tshirt(){
        super();
    }
    
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public String GetData(ITextFormatter userTextFormatter)
    {
        this.textFormatter = userTextFormatter;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return "Tshirt: " + this.getName()  +  ", Price: $" + df.format(this.textFormatter.getPrice()) + ", Color: " + color + ", Size: "+ size + ", Length: " + length + ", Width: " + width + ", Description: " + this.getDescription() +  ", Weight: "+ weight + " pounds, Item Number: " + getID();
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getColor() + "," + this.getWidth() + "," + this.getLength() + "," + this.getSize() + "," + this.getDescription() + "," + this.getWeight();
    }
}
