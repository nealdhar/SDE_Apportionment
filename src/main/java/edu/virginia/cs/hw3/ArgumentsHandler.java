package edu.virginia.cs.hw3;

import java.util.*;

public class ArgumentsHandler {

    public static final int FILENAME_INDEX = 0;
    private final List<String> arguments;
    private Configuration config;

    public ArgumentsHandler(List<String> arguments) {
        if (arguments.size() < 1) {
            throw new IllegalArgumentException("Error: No arguments were included at runtime. Arguments expected\n" +
                    "statePopulationFilename [number of representatives] [--hamilton]");
        }
        this.arguments = arguments;
    }

    public ArgumentsHandler(String[] args) {
        this(Arrays.asList(args));
    }

    public Configuration getConfiguration() {
        setDefaultConfiguration();
        configureStateReader();
        readCommandLineFlags();
        return config;
    }

    private void setDefaultConfiguration() {
        config = new Configuration();
        config.setApportionmentStrategy(new HuntingtonHillApportionmentStrategy());
        config.setRepresentatives(435);
        config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
    }

    public void readCommandLineFlags() {
        for (int i = 0; i < arguments.size(); i++) {
            String flagged_argument = arguments.get(i + 1);

            if (arguments.get(i).startsWith("--")) {
                String long_flag = arguments.get(i);

                if (long_flag.equals("--algorithm")) {
                    String apportionmentName = flagged_argument;
                    configureStrategy(apportionmentName);
                }
                if (long_flag.equals("--format")) {
                    String format = flagged_argument;
                    configureFormat(format);
                }
                if (long_flag.equals("--reps")) {
                    String num_Reps = flagged_argument;
                    setNumReps(num_Reps);
                }
            }

            if (arguments.get(i).startsWith("-")) {
                String short_flag = arguments.get(i);
                char[] short_flag_list = short_flag.toCharArray();

                for (int j = 1; j < short_flag_list.length; j++) {
                    if (short_flag_list[j] == 'a') {
                        String apportionmentName = flagged_argument;
                        configureStrategy(apportionmentName);
                        i++;
                    }
                    if (short_flag_list[j] == 'f') {
                        String format = flagged_argument;
                        configureFormat(format);
                        i++;
                    }
                    if (short_flag_list[j] == 'r') {
                        String num_Reps = flagged_argument;
                        setNumReps(num_Reps);
                        i++;
                    }
                }
            }

        }
    }

    private int setNumReps(String arg) {
        int numReps = Integer.parseInt(arg);
        return numReps;

    }

    private void configureStateReader() {
        String filename = arguments.get(FILENAME_INDEX);
        StateReaderFactory factory = new StateReaderFactory();
        StateReader stateReader = factory.getStateReader(filename);
        config.setStateReader(stateReader);
    }

    private void configureStrategy(String apportionmentName) {
        ApportionmentStrategyFactory factory = new ApportionmentStrategyFactory();
        ApportionmentStrategy strategy = factory.getApportionmentStrategy(apportionmentName);
        config.setApportionmentStrategy(strategy);
    }
    private void configureFormat(String format) {
        ApportionmentFormatFactory factory = new ApportionmentFormatFactory();
        ApportionmentFormat apportionmentFormat = factory.getApportionmentFormat(format);
        config.setApportionmentFormat(apportionmentFormat);
    }
}