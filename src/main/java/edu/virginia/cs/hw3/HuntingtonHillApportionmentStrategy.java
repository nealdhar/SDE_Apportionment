package edu.virginia.cs.hw3;

import java.util.*;

public class HuntingtonHillApportionmentStrategy extends ApportionmentStrategy {
    private Apportionment apportionment;

    // huntingtonHill method is from homework 1C

    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        return getApportionmentIntValue();
    }

    private Apportionment getApportionmentIntValue() {
        apportionment = new Apportionment();
        apportionment = assignOneRepresentative();
        assignRemainingRepsByPriority();
        return apportionment;
    }
    private Apportionment assignOneRepresentative() {
        for(State state : stateList) {
            apportionment.addRepresentativesToState(state, 1);
        }
        return apportionment;
    }
    private void assignRemainingRepsByPriority() {
        while (getRepsLeftToAllocate() > 0)  {
            State highestPriorityState = getHighestPriority();
            apportionment.addRepresentativesToState(highestPriorityState, 1);
        }
    }
    private State getHighestPriority() {
        double highestPriority = 0.0;
        State highestPriorityState = null;
        for (State state : stateList) {
            int n = apportionment.getRepresentativesForState(state);
            double priority = state.getPopulation() / (Math.sqrt(n * (n + 1)));
            if (priority > highestPriority) {
                highestPriorityState = state;
                highestPriority = priority;
            }
        }
        return highestPriorityState;
    }

    private int getRepsLeftToAllocate() {
        int allocatedRepresentatives = apportionment.getAllocatedRepresentatives();
        return targetRepresentatives - allocatedRepresentatives;
    }

}