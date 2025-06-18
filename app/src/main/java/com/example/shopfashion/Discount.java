package com.example.shopfashion;
public class Discount {
    private long idDiscounts;  // соответствует id_discounts в БД
    private String name;      // соответствует name в БД (было title)
    private String description;
    private long idOrders;    // новое поле, соответствует id_orders в БД

    // Конструкторы
    public Discount() {}

    public Discount(long idDiscounts, String name, String description, long idOrders) {
        this.idDiscounts = idDiscounts;
        this.name = name;
        this.description = description;
        this.idOrders = idOrders;
    }
    // Геттеры и сеттеры
    public long getIdDiscounts() {
        return idDiscounts;
    }
    public void setIdDiscounts(long idDiscounts) {
        this.idDiscounts = idDiscounts;
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
    public long getIdOrders() {
        return idOrders;
    }
    public void setIdOrders(long idOrders) {
        this.idOrders = idOrders;
    }
}
