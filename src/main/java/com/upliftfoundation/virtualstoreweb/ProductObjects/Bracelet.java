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
@Table(name = "bracelet")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Bracelet extends FileUploadItem
{
    private String color;
    private String material;
    private double length;

    public Bracelet(String name, double price, String color, String material, double length, String description, double weight) {
        super(name, price, description, weight);
        this.color = color;
        this.setMaterial(material);
        this.setLength(length);
    }
    
    public Bracelet(){
        super();
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        return "Bracelet: " + this.getName()  +  ", Price: $" + df.format(this.textFormatter.getPrice()) + ", Color: " + color + ", Material: "+ material + ", Length: " + length + ", Description: " + this.getDescription() +  ", Weight: "+ weight + " pounds, Item Number: " + getID();
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getColor() + "," + this.getMaterial() + "," + this.getLength() + "," + this.getDescription() + "," + this.getWeight();
    }
}
