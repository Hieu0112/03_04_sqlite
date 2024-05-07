package com.example.a03_04_sqlite.model;

public class item {
    private int id;
    private String name;
    private double price;
    private String dateUpdate;
    private Category category;

    public item() {
    }

    public item(int id, String name, double price, String dateUpdate, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateUpdate = dateUpdate;
        this.category = category;
    }

    public item(String name, double price, String dateUpdate, Category category) {
        this.name = name;
        this.price = price;
        this.dateUpdate = dateUpdate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
