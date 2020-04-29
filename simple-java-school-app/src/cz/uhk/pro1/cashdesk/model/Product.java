package cz.uhk.pro1.cashdesk.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private String id;
    private String unit;
    private double unitPrice;

    public Product(String name, String id, String unit, double unitPrice) {
        this.name = name;
        this.id = id;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return id + "   " + name + "   " + unitPrice + " Kč/" + unit;
    }

}
