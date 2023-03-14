package edu.virginia.cs.hw3;

import java.util.Arrays;
import java.util.List;

public class ApportionmentStrategyFactory {
    private Configuration config;


    private void setApportionmentStrategy(String apportionmentName) {
        if (apportionmentName.equals("hamilton")) {
            config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        }
        if (apportionmentName.contains("jefferson")) {
            config.setApportionmentStrategy(new JeffersonApportionmentStrategy());
        }
    }
    public ApportionmentStrategy getApportionmentStrategy(String apportionmentName) {
        ApportionmentStrategyFactory factory = new ApportionmentStrategyFactory();
        factory.setApportionmentStrategy(apportionmentName);
        ApportionmentStrategy apportionmentStrategy = factory.getApportionmentStrategy(apportionmentName);
        return apportionmentStrategy ;
    }
}
