package edu.virginia.cs.hw3;

import java.util.*;

public class HuntingtonHillApportionmentStrategy extends ApportionmentStrategy {
    private List<State> stateList;
    private int targetRepresentatives;
    private Apportionment apportionment;

    // huntingtonHill method is from homework 1C

    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        return getApportionmentIntValue();
    }

    private Apportionment getApportionmentIntValue() {
        apportionment = assignOneRepresentative();
        assignRemainingRepsByPriority();
        return apportionment;
    }

    private void initializeFields(List<State> stateList, int representatives) {
        this.stateList = stateList;
        targetRepresentatives = representatives;
        apportionment = new Apportionment();
    }
    private Apportionment assignOneRepresentative() {
        for(State state : stateList) {
            apportionment.addRepresentativesToState(state, 1);
        }
        return apportionment;
    }
    private void assignRemainingRepsByPriority() {
        while (getRepsLeftToAssign() > 0)  {
            State highestPriorityState = getHighestPriority();
            apportionment.addRepresentativesToState(highestPriorityState, 1);
        }
    }
    private State getHighestPriority() {
        double highestPriority = 0.0;
        State highestPriorityState = null;
        for(State state : stateList) {
            int n = apportionment.getRepresentativesForState(state);
            double priority = state.getPopulation() / (Math.sqrt(n * (n + 1)));
            if (priority > highestPriority) {
                highestPriorityState = state;
                highestPriority = priority;
            }
        }
        return highestPriorityState;
    }

    private int getRepsLeftToAssign() {
        int assignedRepresentatives = apportionment.getAllocatedRepresentatives();
        return targetRepresentatives - assignedRepresentatives;
    }

}