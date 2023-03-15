package edu.virginia.cs.hw3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RelativeBenefitFormat extends ApportionmentFormat {
    private Apportionment apportionment;

    @Override
    public String getApportionmentString(Apportionment apportionment) {
        this.apportionment = apportionment;
        return getBenefitApportionmentString();
    }

    private int getTotalPopulation() {
        int totalPopulation = 0;
        for (State state : new ArrayList<>(apportionment.getStateSet())) {
            totalPopulation += state.getPopulation();
        }
        return totalPopulation;
    }
    private String getBenefitApportionmentString() {
        return apportionment.getStateSet().stream()
                .sorted((s1, s2) -> {
                    double relativeBenefit1 = getRelativeBenefit(s1);
                    double relativeBenefit2 = getRelativeBenefit(s2);
                    return Double.compare(relativeBenefit2, relativeBenefit1);
                })
                .map(this::getRelativeBenefitStringForState)
                .collect(Collectors.joining("\n"));
    }

    protected double getDivisor() {
        int totalPopulation = getTotalPopulation();
        int totalNumRepresentatives = apportionment.getAllocatedRepresentatives();
        return (double) totalPopulation /totalNumRepresentatives;
    }

    private double getRelativeBenefit(State state) {
        double divisor =  getDivisor();
        int finalRepresentativeValue = apportionment.getRepresentativesForState(state);
        double rawRepresentativeValue = state.getPopulation() / divisor;
        return finalRepresentativeValue - rawRepresentativeValue;
    }

    //https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html

    private String getRelativeBenefitStringForState(State state) {
        DecimalFormat decimalFormat = new DecimalFormat("+0.000; -0.000");
        String stateName = state.getName();
        int finalRepresentativeValue = apportionment.getRepresentativesForState(state);
        double relativeBenefit = getRelativeBenefit(state);
        return stateName + " - " + finalRepresentativeValue
                +" - " + decimalFormat.format(relativeBenefit);
    }


}
