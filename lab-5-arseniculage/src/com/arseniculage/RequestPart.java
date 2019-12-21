package com.arseniculage;

public class RequestPart {
    private Instrument instrument;
    public int time;
    private int startTime = 0;


    public RequestPart(Instrument instrument, int time) {
        this.instrument = instrument;
        this.time = time;
    }

    public Instrument getInstrument() { return this.instrument; }

    public int getTime() { return this.time; }

    public void setStartTime(int time) {
        this.startTime = time;
    }

    public int getStartTime() { return this.startTime; }
}
