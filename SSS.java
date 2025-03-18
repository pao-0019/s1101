/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angeliquerivera
 */
public class SSS extends Calculation {

     private double sssDeduction; // Instance variable for SSS deduction
     private final Grosswage grosswage; // Instance of Grosswage

    private static final String XLSX_FILE_PATH = "src/main/resources/SSSCont.xlsx"; // Update to your Excel file path
    private static final List<SSSRecord> sssDeductionRecords;

    
    // CONSTRUCTOR
    public SSS (Grosswage grosswage) {
        this.grosswage = grosswage;
    }

    // INITIALIZE
    static {
        List<SSSRecord> records = loadSssDeductions();
        if (records == null) {
            throw new RuntimeException("Failed to load SSS deductions.");
        }
        sssDeductionRecords = records;
    }
    

    @Override
    public double calculate() {
        double gross = grosswage.calculate();

        // Iterates through every compensation range to get the proper contribution.
        for (SSSRecord record: sssDeductionRecords) {
            double[] range = parseSssCompensationRange(record.getCompensationRange());
            if (gross > range[0] && gross <= range[1]) {
                sssDeduction = record.getContribution();
                break;  // Assuming that only one range should match, you can modify as needed
            }
        }
        return sssDeduction;
    }

    // LOADS THE SSS CONTRIBUTION FROM AN EXCEL FILE AND SAVES IT AS NEW OBJECT IN OBJECT ARRAY LIST
    private static List<SSSRecord> loadSssDeductions() {
        List<SSSRecord> deductionRecord = new ArrayList<>();

        // Tries to read the Excel file and load data from it before closing.
        try (FileInputStream fis = new FileInputStream(XLSX_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Skip the header row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String compensationRange = row.getCell(0).getStringCellValue(); // Assuming compensation range is in the first column
                    double contribution = row.getCell(1).getNumericCellValue(); // Assuming contribution is in the second column

                    SSSRecord deductionRecordItem = new SSSRecord(compensationRange, contribution);
                    deductionRecord.add(deductionRecordItem);
                }
            }
        } catch (IOException e) {
            handleException(e);
        }

        return deductionRecord;
    }
// Helper method to get cell value as String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }  
    // PARSES SSS CONTRIBUTION RANGE TO USE IN SSS CALCULATION
    private static double[] parseSssCompensationRange(String compensationRange) {
        // Remove any extra spaces
        compensationRange = compensationRange.trim();

        // Split the range by hyphen
        String[] rangeParts = compensationRange.split("-");

        // Checks if the compensation range is in the correct format.
        if (rangeParts.length != 2) {
            throw new IllegalArgumentException("Invalid compensation range format: " + compensationRange);
        }

        try {
            double start = Double.parseDouble(rangeParts[0].trim());
            double end = Double.parseDouble(rangeParts[1].trim());

            return new double[]{start, end};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric format in compensation range: " + compensationRange, e);
        }
    }

    private static void handleException(Exception e) {
        e.printStackTrace();
    }

    /**
     * @return the compensationRange
     */
    public double getSssDeduction() {
        return sssDeduction; // Return the SSS deduction
    }

    
}
