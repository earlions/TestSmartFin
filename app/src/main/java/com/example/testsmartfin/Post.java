package com.example.testsmartfin;

public class Post {
    String name;
    String city;
    float price;
    String image;
    public Post(String name, String city, float price, String image){
        this.name = name;
        this.city = city;
        this.price = price;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
