package edu.virginia.cs.hw3;

import java.util.*;

public class HamiltonApportionmentStrategy extends ApportionmentStrategy{
    private double divisor;
    private DecimalApportionment decimalApportionment;
    private Apportionment apportionment;

    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        divisor = getDivisor();
        decimalApportionment = getDecimalApportionment();
        return getIntegerApportionment();
    }

    private Apportionment getIntegerApportionment() {
        apportionment = getFirstPassApportionment();
        executeSecondPassApportionment();
        return apportionment;
    }
    private DecimalApportionment getDecimalApportionment() {
        DecimalApportionment decimalApportionment = new DecimalApportionment();
        for (State state : stateList) {
            double decimalRepresentatives = state.getPopulation() / divisor;
            decimalApportionment.setDecimalApportionmentForState(state, decimalRepresentatives);
        }
        return decimalApportionment;
    }

    private Apportionment getFirstPassApportionment() {
        return decimalApportionment.getRoundedDownApportionment();
    }

    private void executeSecondPassApportionment() {
        int repsLeftToAllocate = getRepsLeftToAllocate();
        Map<State, Double> remainderMap = decimalApportionment.getRemainderMap();
        remainderMap.entrySet().stream()
                .sorted((e1, e2) -> (Double.compare(e2.getValue(), e1.getValue())))
                .limit(repsLeftToAllocate)
                .map(Map.Entry::getKey)
                .forEach(state -> apportionment.addRepresentativesToState(state, 1));
    }

    private int getRepsLeftToAllocate() {
        int allocatedRepresentatives = apportionment.getAllocatedRepresentatives();
        return targetRepresentatives - allocatedRepresentatives;
    }


}
