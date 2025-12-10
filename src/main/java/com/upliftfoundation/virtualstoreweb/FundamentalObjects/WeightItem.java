package com.upliftfoundation.virtualstoreweb.FundamentalObjects;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class WeightItem extends StoreItem
{
    public double weight;
    public WeightItem(String name, double price, String description, double weight) {
        super(name, price, description);
        this.weight = weight;
    }

    public WeightItem() {
        super();
    }

    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
