package edu.virginia.cs.hw3;

public class StateReaderFactory {
    private Configuration config;

    private void setStateReaderFromFilename(String filename) {
        if (filename.toLowerCase().endsWith(".csv")) {
            setConfigurationToCSVReader(filename);
        }
        if (filename.toLowerCase().endsWith(".xlsx")) {
            setConfigurationToXLSXReader(filename);
        } else {
            throw new IllegalArgumentException("Error: invalid file type. The system currently supports:\n" +
                    "\t.csv, .xlsx");
        }
    }
    private void setConfigurationToCSVReader(String filename) {

        config.setStateReader(new CSVStateReader(filename));
    }

    private void setConfigurationToXLSXReader(String filename) {
        config.setStateReader(new ExcelStateReader(filename));
    }
    private static StateReader getStateReader(String filename){
        StateReaderFactory factory = new StateReaderFactory();
        StateReader stateReader = factory.getStateReader(filename);

    return stateReader;
    }

}
