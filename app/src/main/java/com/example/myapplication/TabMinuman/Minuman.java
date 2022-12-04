package com.example.myapplication.TabMinuman;

import com.example.myapplication.TabMakanan.Makanan;

public class Minuman {
    private String key;
    private String name;
    private int price;
    private String brand;

    public Minuman() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Minuman(String name, int price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static Minuman convertMinuman(Minuman minuman){
        return new Minuman(minuman.getName(), minuman.getPrice(), minuman.getBrand());
    }

}

