package com.example.shopfashion.Models;
public class Discount{
    public int id_discounts;
    public String name;
    public String description;
    public int id_orders;
    public int getId_discounts() {
        return id_discounts;
    }
    public void setId_discounts(int id_discounts) {
        this.id_discounts = id_discounts;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getId_orders() {
        return id_orders;
    }
    public void setId_orders(int id_orders) {
        this.id_orders = id_orders;
    }
}