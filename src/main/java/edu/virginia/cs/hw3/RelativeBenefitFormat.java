package edu.virginia.cs.hw3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RelativeBenefitFormat extends ApportionmentFormat{
    private Apportionment apportionment;
    private double divisor;
    @Override
    public String getApportionmentString(Apportionment apportionment) {
        this.apportionment = apportionment;
        return getBenefitApportionmentString();
    }

    private double getDivisor() {
        int totalPopulation = getTotalPopulation();
        return (double) totalPopulation / apportionment.getAllocatedRepresentatives();
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
                    double relativeBenefit1 = getRelativeBenefit(s1, getDivisor());
                    double relativeBenefit2 = getRelativeBenefit(s2, getDivisor());
                    return Double.compare(relativeBenefit2, relativeBenefit1);
                })
                .map(this::getRelativeBenefitStringForState)
                .collect(Collectors.joining("\n"));
    }

    private double getRelativeBenefit(State state, double divisor) {
        int finalRepresentativeValue = apportionment.getRepresentativesForState(state);
        double rawRepresentativeValue = state.getPopulation() / divisor;
        return finalRepresentativeValue - rawRepresentativeValue;
    }

    private String getRelativeBenefitStringForState(State state) {
        double divisor = getDivisor();
        DecimalFormat decimalFormat = new DecimalFormat("+#.000; -#.000");
        String stateName = state.getName();
        int finalRepresentativeValue = apportionment.getRepresentativesForState(state);
        double relativeBenefit = getRelativeBenefit(state, divisor);
        return stateName + " - " + finalRepresentativeValue
                +" - " + decimalFormat.format(relativeBenefit);
    }


}
