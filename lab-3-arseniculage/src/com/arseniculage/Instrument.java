package com.arseniculage;

public class Instrument {
    private String name;
    private int count;


    public Instrument(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() { return this.name; }

    public int getCount() { return this.count; }
}
