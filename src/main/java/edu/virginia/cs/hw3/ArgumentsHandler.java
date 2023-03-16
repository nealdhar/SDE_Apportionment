package edu.virginia.cs.hw3;

import java.util.*;
import java.io.*;

public class ArgumentsHandler {

    public static final int FILENAME_INDEX = 0;
    public static final int REPRESENTATIVES_INDEX = 1;
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


    private void setDefaultConfiguration() {
        config = new Configuration();
        config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        config.setRepresentatives(435);
        config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
    }


    private void checkForRepresentativeCount() {
        if (arguments.size() < 2) {
            return;
        }
        try {
            int representativeCount = Integer.parseInt(arguments.get(REPRESENTATIVES_INDEX));
            if (representativeCount <= 0) {
                throw new IllegalArgumentException("Error: Invalid representative count : " + representativeCount + " - number must be positive");
            }
            config.setRepresentatives(representativeCount);
        } catch (NumberFormatException ignored) {
        }
    }


    private boolean filenameExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    private void setConfigurationToCSVReader(String filename) {
        config.setStateReader(new CSVStateReader(filename));
    }

    private void setConfigurationToXLSXReader(String filename) {
        config.setStateReader(new ExcelStateReader(filename));
    }

    private void readFileType(String filename) {
        StateReaderFactory fileReaderFactory = new StateReaderFactory();
        StateReader fileReader = fileReaderFactory.getStateReader(filename);
    }

    private void readCommandLine() {
        for (int i = 0; i < arguments.size(); i++) {

            if (arguments.get(i).startsWith("--")) {
                String long_flag = arguments.get(i);
                if (long_flag.equals("--algorithm")) {
                }
                if (long_flag.equals("--reps")) {
                }
                if (long_flag.equals("--format")) {
                }

            }
            if (arguments.get(i).startsWith("-")) {
                String short_flag = arguments.get(i);
                char[] short_flag_list = short_flag.toCharArray();
                for (int j = 1; j < short_flag_list.length; j++) {
                    if (short_flag_list[j] == 'r') {

                    }
                    if (short_flag_list[j] == 'f') {

                    }
                    if (short_flag_list[j] == 'a') {

                    }
                }
            }

        }
    }
}