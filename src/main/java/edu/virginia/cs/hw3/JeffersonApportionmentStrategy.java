package edu.virginia.cs.hw3;


import java.util.List;

public class JeffersonApportionmentStrategy extends ApportionmentStrategy {
    private List<State> stateList;
    private int targetRepresentatives;
    private double divisor;
    private DecimalApportionment decimalApportionment;
    private Apportionment apportionment;

    // methods from HamiltonAlgorithm
    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        divisor = changeDivisor();
        return apportionment;
    }
    protected double getDivisor() {
        int totalPopulation = getTotalPopulation();
        return (double) totalPopulation / targetRepresentatives;
    }
    protected void initializeFields(List<State> stateList, int representatives) {
        this.stateList = stateList;
        targetRepresentatives = representatives;
    }

    protected double changeDivisor() {
        boolean NoRemainingReps = false;
        divisor = getDivisor();
        while (!NoRemainingReps) {
            decimalApportionment = getDecimalApportionment(divisor);
            apportionment = getFirstPassApportionment();
            int allocatedReps = apportionment.getAllocatedRepresentatives();
            if (allocatedReps == targetRepresentatives) {
                break;
            } else if (allocatedReps < targetRepresentatives) {
                divisor -= 0.1;
            } else {
                divisor += 0.1;
            }
        }
        return divisor;
    }

    protected DecimalApportionment getDecimalApportionment(double divisor) {
        DecimalApportionment decimalApportionment = new DecimalApportionment();
        for (State state : stateList) {
            double decimalRepresentatives = state.getPopulation() / divisor;
            decimalApportionment.setDecimalApportionmentForState(state, decimalRepresentatives);
        }
        return decimalApportionment;
    }

    protected Apportionment getFirstPassApportionment() {
        return decimalApportionment.getRoundedDownApportionment();
    }

    protected int getTotalPopulation() {
        int totalPopulation = 0;
        for (State state : stateList) {
            totalPopulation += state.getPopulation();
        }
        return totalPopulation;
    }
}