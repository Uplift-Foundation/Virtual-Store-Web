package com.upliftfoundation.virtualstoreweb.ProductObjects; //this is the package

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import com.upliftfoundation.virtualstoreweb.ITextFormatter;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.WeightItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "computer")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Computer extends WeightItem 
{

    private String computerBrand;
    private String releaseDate;
    private String color; 
    //if you were to remove private/public/protected, then the default visibility is anything inside the package. Only package can see
    public Computer(String name, double price, String color, String computerBrand, String description, String releaseDate, double weight) {
        super(name, price, description, weight);
        this.computerBrand = computerBrand;
        this.releaseDate = releaseDate;
        this.color = color;
    }

    public Computer(){
        super();
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getComputerBrand() {
        return computerBrand;
    }

    public void setComputerBrand(String computerBrand) {
        this.computerBrand = computerBrand;
    }
    
    public String GetData(ITextFormatter userTextFormatter)
    {
        this.textFormatter = userTextFormatter;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return "Computer: " + this.getName()  +  ", Price: $" + df.format(this.textFormatter.getPrice()) + ", Color: " + color + ", Computer Brand: "+ computerBrand + ", Description: " + this.getDescription() +  ", Release Date: " + releaseDate + ", Weight: "+ weight + " pounds, Item Number: " + getID();
    }

    public String FreeHeadphones() {
        Random rand = new Random();
        int upperbound = 301;
        int random = rand.nextInt(upperbound);
        int winningNumber = 67;
        if (random == winningNumber)
        {
            return "Congratulations! You have won a pair of free headphones from one of our sponsors. Contact us for more information.";
        }
        else {
            return "Unfortunately, you have not won a pair of a free headphones.";
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + this.getColor() + "," + this.getComputerBrand() + "," + this.getDescription() + "," + this.getReleaseDate() + "," + this.getWeight();
    }
}
