package com.arseniculage;

public class Instrument {
    public String name;
    public int count;


    public Instrument(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() { return this.name; }

    public int getCount() { return this.count; }

    public void setCount(int in) {this.count=in; }
}
