package com.example.semen.espressoexample;

import java.util.Random;

public class Item {
    private long id;
    private String name;
    private int ten;
    private static int count = 0;

    public Item() {
        this.id = count;
        this.name = "Item " + id;
        this.ten = new Random().nextInt(1000);
        count++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTen() {
        return ten;
    }

    // getters, setters, hashcode, equals
}