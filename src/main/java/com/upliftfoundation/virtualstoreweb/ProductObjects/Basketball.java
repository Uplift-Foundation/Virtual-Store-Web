package com.upliftfoundation.virtualstoreweb.ProductObjects;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.upliftfoundation.virtualstoreweb.ITextFormatter;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.WeightItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "basketball")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Basketball extends WeightItem
{
    private String date;
    private int section;
    private int seat;

    public Basketball(String name, double price, int section, int seat, String description, String date, double weight) {
        super(name, price, description, weight);
        this.date = date;
        this.section = section;
        this.seat = seat;
    }
    public Basketball() {
        super();
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String GetData(ITextFormatter userTextFormatter){
        this.textFormatter = userTextFormatter;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return "Match: " + this.getName() + ", Price: $" + df.format(this.textFormatter.getPrice()) + ", Date: " + date + ", Section: " + section + ", Seat: " + seat + ", Weight: " + weight + ", Item Number: " + getID();
    }

    public String WinnerOfMatch(){
        String[] parts = this.getName().split("vs");
        String team1 = parts[0];
        String team2 = parts[1];
        List<String> list = new ArrayList<>();
        list.add(team1);
        list.add(team2);
        Random rand = new Random();
        int randomitem = rand.nextInt(list.size());
        String randomElement = list.get(randomitem);
        String winner = randomElement;
        return "The winner of the match is: " + winner;
    }
    
    public String SeatsSoldForMatch(){
        Random rand = new Random();
        int upperBound = 30001;
        int seats = rand.nextInt(upperBound);
        return "The amount of seats sold for the match is" + seats;
    }

    @Override 
    public String toString() {
        //String name, double price, int section, int seat, String description, String date, double weight
        return this.getClass().getSimpleName() + "," + this.getID() + "," + this.getName() + "," + String.valueOf(this.getPrice()) + "," + String.valueOf(this.getSection()) + "," + String.valueOf(this.getSeat()) + "," +this.getDescription() + "," + String.valueOf(this.getDate()) + "," + this.getWeight();
    }
}