package com.example.shopfashion.Models;


public class Product{

    private int id_products;
    private String name;
    private String description;
    private String price;
    private String images;
    private int id_brands;
    private int id_categories;
    private int id_colors;

    public int getId_products() {
        return id_products;
    }
    public void setId_products(int id_products) {
        this.id_products = id_products;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public int getId_brands() {
        return id_brands;
    }
    public void setId_brands(int id_brands) {
        this.id_brands = id_brands;
    }
    public int getId_categories() {
        return id_categories;
    }
    public void setId_categories(int id_categories) {
        this.id_categories = id_categories;
    }
    public int getId_colors() {
        return id_colors;
    }
    public void setId_colors(int id_colors) {
        this.id_colors = id_colors;
    }
}
