package org.example;

public class Product {
    private int id;
    private String name;
    private int price;
    private int amount;

    public Product(){
        this.id = -1;
        this.name = null;
        this.price = -1;
        this.amount = -1;
    }

    public Product(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String toString(){
        return "Product: [" + this.id + "," + this.name + "," + this.price + "," + this.amount + "]";
    }
}
