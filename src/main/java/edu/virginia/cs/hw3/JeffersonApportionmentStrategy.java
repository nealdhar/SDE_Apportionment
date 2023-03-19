package edu.virginia.cs.hw3;


import java.util.List;

public class JeffersonApportionmentStrategy extends ApportionmentStrategy {
    private double divisor;
    private DecimalApportionment decimalApportionment;
    private Apportionment apportionment;

    // methods from HamiltonAlgorithm
    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        divisor = getDivisor();
        changeDivisor();
        return apportionment;
    }

    protected void changeDivisor() {
        boolean noRemainingReps = false;
        while (!noRemainingReps) {
            decimalApportionment = getDecimalApportionment(divisor);
            apportionment = getFirstPassApportionment();
            int allocatedReps = apportionment.getAllocatedRepresentatives();
            if (allocatedReps == targetRepresentatives) {
                noRemainingReps = true;
            } else if (allocatedReps < targetRepresentatives) {
                divisor -= 0.1;
            } else {
                divisor += 0.1;
            }
        }
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
}