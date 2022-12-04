package com.example.myapplication.DataModel;

import com.example.myapplication.TabMakanan.Makanan;
import com.example.myapplication.TabMinuman.Minuman;

import java.util.ArrayList;

public class Model {
    public static ArrayList<Minuman> minumanArrayList = new ArrayList<>();
    public static ArrayList<Makanan> makananArrayList = new ArrayList<>();

    public static Minuman currentMinuman;
    public static Makanan currentMakanan;
}
