package edu.virginia.cs.hw3;

public class StateReaderFactory {
    StateReader CSVStateReader ;
    StateReader XLSXStateReader ;

    StateReader stateReader ;

    private void setStateReaderFromFileName(String fileName) {
        if (fileName.contains(".csv")) {
            stateReader = CSVStateReader;
        }
        if (fileName.contains(".xlsx")) {
            stateReader = XLSXStateReader ;
        }
    }
    private static StateReader getStateReader(String filename){
        StateReaderFactory factory = new StateReaderFactory();
        StateReader stateReader = factory.getStateReader(filename);

    return stateReader;
    }


}
