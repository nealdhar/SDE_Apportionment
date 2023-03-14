package edu.virginia.cs.hw3;

import java.util.*;

public abstract class ApportionmentStrategy {
    protected List<State> stateList;
    protected int targetRepresentatives;
    public abstract Apportionment getApportionment(List<State> stateList, int representatives);

    protected void initializeFields(List<State> stateList, int representatives) {
        this.stateList = stateList;
        targetRepresentatives = representatives;
    }
    protected double getDivisor() {
        int totalPopulation = getTotalPopulation();
        return (double) totalPopulation / targetRepresentatives;
    }
    protected int getTotalPopulation() {
        int totalPopulation = 0;
        for (State state : stateList) {
            totalPopulation += state.getPopulation();
        }
        return totalPopulation;
    }

}
