package model;

import javafx.util.Pair;

public class Player {
    String name;
    Pair<Integer,Integer> start;
    Pair<Integer,Integer> end;


    String SHIPNAME = "SH";
    int counter = 1;
    public int getCounter()
    {
        return counter;
    }
    public void  updateCounter(int counter)
    {
        this.counter+=1;
    }
    public Player(String name, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        this.name = name+"-"+SHIPNAME;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pair<Integer, Integer> getStart() {
        return start;
    }

    public void setStart(Pair<Integer, Integer> start) {
        this.start = start;
    }

    public Pair<Integer, Integer> getEnd() {
        return end;
    }

    public void setEnd(Pair<Integer, Integer> end) {
        this.end = end;
    }
}
