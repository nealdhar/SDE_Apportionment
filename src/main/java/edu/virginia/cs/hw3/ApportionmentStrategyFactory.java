package edu.virginia.cs.hw3;

import java.util.Arrays;
import java.util.List;

public class ApportionmentStrategyFactory {
    private final List<String> arguments;
    private Configuration config;

    public ApportionmentStrategyFactory(String[] args) {
        this(Arrays.asList(args));
    }

    public ApportionmentStrategyFactory(List<String> arguments) {
        if (arguments.size() < 1) {
            throw new IllegalArgumentException("Error: No arguments were included at runtime. Arguments expected\n" +
                    "statePopulationFilename [number of representatives] [--hamilton]");
        }
        this.arguments = arguments;
    }
    private void setApportionmentStrategy() {
        if (arguments.contains("hamilton")) {
            config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        }
        if (arguments.contains("jefferson")) {
            config.setApportionmentStrategy(new JeffersonApportionmentStrategy());
        }
    }
    public ApportionmentStrategy getApportionmentStrategy() {
        ApportionmentStrategyFactory factory = new ApportionmentStrategyFactory(arguments);
        factory.setApportionmentStrategy();
        ApportionmentStrategy apportionmentStrategy = factory.getApportionmentStrategy();
        return apportionmentStrategy ;
    }
}
