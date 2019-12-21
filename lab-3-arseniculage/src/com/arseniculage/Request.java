package com.arseniculage;
import java.util.AbstractQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class Request {

    private Queue<RequestPart> parts;

    public Request() {};

    public void addPart(Instrument instrument, int time) {
        RequestPart part = new RequestPart(instrument, time);
        this.parts.offer(part);
    }
    public RequestPart getPart(){
        return this.parts.poll();
    }


    public Queue<RequestPart> getParts() { return this.parts; }

}