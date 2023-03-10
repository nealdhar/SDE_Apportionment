package edu.virginia.cs.hw3;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelStateReader extends StateReader{
    public static final int NAME_COLUMN_INDEX = 0;
    public static final int POPULATION_COLUMN_INDEX = 1;
    private final String filename;
    private Workbook workbook;
    private Iterator<Row> rowIterator;


    // methods open and close XLSX workbook, method body of getStatesFromWorbook,
    // and method body of addStateFromRow are from homework 1C
    // try and catch body in getStateFromWorkbook is similar to CSVStateReader

    public ExcelStateReader(String filename) {
        if (!filename.toLowerCase().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Error: cannot open non excel file" + filename);
        }
        this.filename = filename;
    }

    @Override
    public void readStates() {
        try {
            openXLSXWorkbook();
            getStatesFromWorkbook();
            closeXLSXWorkbook();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openXLSXWorkbook() {
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            workbook = new XSSFWorkbook(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: File " + filename + " not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeXLSXWorkbook () {
        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getStatesFromWorkbook() throws IOException {
        Sheet firstWorksheet = workbook.getSheetAt(0);
        rowIterator = firstWorksheet.rowIterator();
        if (!rowIterator.hasNext()) {
            throw new RuntimeException("Error: Spreadsheet empty");
        }
        rowIterator.next();
        while(rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            try {
                addStateFromRow(currentRow);
            } catch (IndexOutOfBoundsException | IllegalArgumentException ignored) {
            }
        }
    }

    private void addStateFromRow(Row currentRow) {
        String stateName = currentRow.getCell(NAME_COLUMN_INDEX).getStringCellValue().strip();
        //if (stateName.isEmpty()) continue;
        int statePopulation = (int) currentRow.getCell(POPULATION_COLUMN_INDEX).getNumericCellValue();
        //if (statePopulation <= 0) continue;
        State newState = new State(stateName, statePopulation);
        stateList.add(newState);
    }

}
