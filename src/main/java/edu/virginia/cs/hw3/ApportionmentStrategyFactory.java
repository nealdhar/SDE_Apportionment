package edu.virginia.cs.hw3;

import java.util.Arrays;
import java.util.List;

public class ApportionmentStrategyFactory {

    public ApportionmentStrategy getApportionmentStrategy(String apportionmentStrategy) {
        if (apportionmentStrategy.equals("hamilton")) {
            return new HamiltonApportionmentStrategy();
        }
        if (apportionmentStrategy.equals("jefferson")) {
            return new JeffersonApportionmentStrategy();
        } else {
            return new HuntingtonHillApportionmentStrategy();
        }
    }
}