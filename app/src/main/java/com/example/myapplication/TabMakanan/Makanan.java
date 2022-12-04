package com.example.myapplication.TabMakanan;

public class Makanan {
    private String key;
    private String name;
    private String desc;
    private int price;
    private String brand;

    public Makanan() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Makanan(String name, int price, String brand, String desc) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.desc = desc;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public static Makanan convertMakanan(Makanan makanan){
        return new Makanan(makanan.getName(), makanan.getPrice(), makanan.getBrand(), makanan.getDesc());
    }

}
