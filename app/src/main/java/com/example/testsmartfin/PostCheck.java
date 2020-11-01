package com.example.testsmartfin;

public class PostCheck {
    String name;
    double price;
    double size;
    public PostCheck(String name, double price, double size){
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

}
