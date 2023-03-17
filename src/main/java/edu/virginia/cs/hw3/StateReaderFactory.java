package edu.virginia.cs.hw3;

public class StateReaderFactory {
    public StateReader getStateReader(String filename) {
        if (filename.toLowerCase().endsWith(".csv")) {
            return new CSVStateReader(filename);
        }
        if (filename.toLowerCase().endsWith(".xlsx")) {
            return new ExcelStateReader(filename);
        } else {
            throw new IllegalArgumentException("Error: invalid file type. The system currently supports:\n" +
                    "\t.csv, .xlsx");
        }
    }

}
