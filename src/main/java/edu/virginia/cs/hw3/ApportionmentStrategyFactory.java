package edu.virginia.cs.hw3;

public class ApportionmentStrategyFactory {

    public ApportionmentStrategy getApportionmentStrategy(String apportionmentName) {
        if (apportionmentName.equals("hamilton")) {
            return new HamiltonApportionmentStrategy();
        }
        if (apportionmentName.equals("jefferson")) {
            return new JeffersonApportionmentStrategy();
        } else {
            return new HuntingtonHillApportionmentStrategy();
        }
    }
}