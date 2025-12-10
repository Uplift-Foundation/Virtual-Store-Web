package com.upliftfoundation.virtualstoreweb.ProductObjects;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import com.upliftfoundation.virtualstoreweb.ITextFormatter;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.FileUploadItem;

@Entity
@Table(name = "drawing")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Drawing extends FileUploadItem{
    private Integer width;
    private Integer height;      
    private String paper;
    private String color;

    public Drawing(String name, double price, String description, double weight, Integer width, Integer height,
    String paper, String color) {
        super(name, price, description, weight);
        this.setWidth(width);
        this.setHeight(height);
        this.setPaper(paper);
        this.setColor(color);
    }
    public Drawing(){
        super();
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getPaper() {
        return paper;
    }
    public void setPaper(String paper) {
        this.paper = paper;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
   public String GetData(ITextFormatter userTextFormatter)
    {
        this.textFormatter = userTextFormatter;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return "Sticker: " + this.getName()  +  ", Price: $" + df.format(this.textFormatter.getPrice()) + ", Color: " + color + ", Paper: "+ paper + ", Width: " + width + ",Height: " + height + ", Description: " + this.getDescription() +  ", Weight: "+ weight + " pounds, Item Number: " + getID();
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getColor() + "," + this.getPaper() + "," + this.getWidth() + "," + this.getHeight() + "," + this.getDescription() + "," + this.getWeight();
    }

    
}

