package com.planidear.appconvert.model;

public class Stock  {
    String name, age, color, photo;
    Double wine_price;
    public Stock(){}

    public Stock(String name, String age, String color, Double wine_price, String photo) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.wine_price = wine_price;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getwine_price() {
        return wine_price;
    }

    public void setwine_price(Double wine_price) {
        this.wine_price = wine_price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

