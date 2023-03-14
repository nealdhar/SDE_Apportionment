package edu.virginia.cs.hw3;

import java.util.Arrays;
import java.util.List;

public class ApportionmentStrategyFactory {
    private Configuration config;


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