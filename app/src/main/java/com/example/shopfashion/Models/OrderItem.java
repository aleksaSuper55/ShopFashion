package com.example.shopfashion.Models;

public class OrderItem{
    private int quantity;
    private int id_orders;
    private int id_products;
    private int id_order_item;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_orders() {
        return id_orders;
    }

    public void setId_orders(int id_orders) {
        this.id_orders = id_orders;
    }

    public int getId_products() {
        return id_products;
    }

    public void setId_products(int id_products) {
        this.id_products = id_products;
    }

    public int getId_order_item() {
        return id_order_item;
    }

    public void setId_order_item(int id_order_item) {
        this.id_order_item = id_order_item;
    }
}
