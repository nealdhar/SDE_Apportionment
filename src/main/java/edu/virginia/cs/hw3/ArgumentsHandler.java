package edu.virginia.cs.hw3;

import java.util.*;

public class ArgumentsHandler {

    public static final int FILENAME_INDEX = 0;

    private final List<String> arguments;
    private final Configuration config;

    public ArgumentsHandler(List<String> arguments) {
        if (arguments.size() < 1) {
            throw new IllegalArgumentException("Error: No arguments were included at runtime. Arguments expected\n" +
                    "statePopulationFilename");
        }
        this.arguments = arguments;
        this.config = new Configuration();
    }

    public ArgumentsHandler(String[] args) {
        this(Arrays.asList(args));
    }

    public Configuration getConfiguration() {
        setDefaultConfiguration();
        parseOptionalArguments();
        configureStateReader();
        return config;
    }
    private void setDefaultConfiguration() {
        config.setApportionmentStrategy(new HuntingtonHillApportionmentStrategy());
        config.setRepresentatives(435);
        config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
    }
    private void parseOptionalArguments() {
        for (int i = 1; i < arguments.size(); i++) {
            String arg = arguments.get(i);
            if (arg.startsWith("--")) {
                i = parseLongFlags(i, arg.substring(2));
            } else if (arg.startsWith("-")) {
                i = parseShortFlags(i, arg.substring(1));
            } else {
                throw new IllegalArgumentException("Error: Unexpected argument " + arg);
            }
        }
    }

    private int parseLongFlags(int index, String flag) {
        switch (flag) {
            case "reps" -> index = setNumReps(index);
            case "format" -> index = configureFormat(index);
            case "algorithm" -> index = configureStrategy(index);
            default -> throw new IllegalArgumentException("Error: Invalid argument --" + flag);
        }
        return index;
    }

    private int parseShortFlags(int index, String flags) {
        for (int i = 0; i < flags.length(); i++) {
            char c = flags.charAt(i);
            switch (c) {
                case 'r' -> index = setNumReps(index);
                case 'f' -> index = configureFormat(index);
                case 'a' -> index = configureStrategy(index);
                default -> throw new IllegalArgumentException("Error: Invalid argument -" + c);
            }
        }
        return index;
    }

    private int setNumReps(int index) {
        String arg = arguments.get(index + 1);
        try {
            int numReps = Integer.parseInt(arg);
            if (numReps <= 0) {
                throw new IllegalArgumentException("Error: Number of Reps Argument Must Be Positive");
            }
            config.setRepresentatives(numReps);
            return index + 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Positive integer expected for argument " + arg, e);
        }
    }

    private int configureStrategy(int index) {
        if (index + 1 >= arguments.size()) {
            throw new IllegalArgumentException("Error: Missing argument for -a/--algorithm flag");
        }
        String apportionmentName = arguments.get(index + 1);
        ApportionmentStrategyFactory factory = new ApportionmentStrategyFactory();
        ApportionmentStrategy strategy = factory.getApportionmentStrategy(apportionmentName);
        config.setApportionmentStrategy(strategy);
        return index + 1;
    }

    private int configureFormat(int index) {
        if (index + 1 >= arguments.size()) {
            throw new IllegalArgumentException("Error: Missing argument for -f/--format flag");
        }
        String format = arguments.get(index + 1);
        ApportionmentFormatFactory factory = new ApportionmentFormatFactory();
        ApportionmentFormat apportionmentFormat = factory.getApportionmentFormat(format);
        config.setApportionmentFormat(apportionmentFormat);
        return index + 1;
    }

    private void configureStateReader() {
        String filename = arguments.get(FILENAME_INDEX);
        StateReaderFactory factory = new StateReaderFactory();
        StateReader stateReader = factory.getStateReader(filename);
        config.setStateReader(stateReader);
    }
}
