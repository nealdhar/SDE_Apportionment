package edu.virginia.cs.hw3;

public class StateReaderFactory {
    private Configuration config;

    private void setStateReaderFromFilename(String filename) {
        if (filename.toLowerCase().endsWith(".csv")) {
            setStateReaderFromFilename(filename);
        }
        if (filename.toLowerCase().endsWith(".xlsx")) {
            setStateReaderFromFilename(filename);
        } else {
            throw new IllegalArgumentException("Error: invalid file type. The system currently supports:\n" +
                    "\t.csv, .xlsx");
        }
    }



    private static StateReader getStateReader(String filename){
        StateReaderFactory factory = new StateReaderFactory();
        factory.setStateReaderFromFilename(filename);
        StateReader stateReader = factory.getStateReader(filename);
    return stateReader;
    }

}
