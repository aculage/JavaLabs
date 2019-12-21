package com.arseniculage;
import java.util.*;

public class Request {

    private Queue<RequestPart> parts;

    public Request() {
        this.parts = new ArrayDeque<RequestPart>();
    };

    public void addPart(Instrument instrument, int time) {
        RequestPart part = new RequestPart(instrument, time);
        this.parts.offer(part);
    }
    public RequestPart getPart(){
        return this.parts.poll();
    }


    public Queue<RequestPart> getParts() { return this.parts; }

}