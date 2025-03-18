/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author angeliquerivera
 */
public class Netwage extends Calculation {
      private static final DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Define decimalFormat 
      
       private final String employeeID; // Instance field
       private final String employeeName; // Instance field
       private final double gross; // Instance field
       private final double hours; // Instance field

    // Constructor
    public Netwage(String employeeID, String employeeName, double gross, double hours) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.gross = gross;
        this.hours = hours;
    }

   
    @Override
    public double calculate() {
       // Create an instance of Grosswage to pass to deduction classes
        Grosswage grosswage = new Grosswage(employeeID); // Assuming you have a way to get employeeID
        grosswage.calculate(); // Calculate gross wage first

        // Create instances of each deduction class, passing the Grosswage instance
        WithholdingTax withholdingTax = new WithholdingTax(grosswage);
        Calculation sss = new SSS(grosswage);
        Calculation philhealth = new Philhealth(grosswage);
        Calculation pagibig = new Pagibig(grosswage);
        Calculation latePenalty = new LatePenalty(); // Assuming this does not need Grosswage


        // Call the calculate() method of each class and assign their values to temporary variables.
        double sssData = sss.calculate();
        double philhealthData = philhealth.calculate();
        double pagibigData = pagibig.calculate();
        double lateData = latePenalty.calculate();
        double totalDeduction = sssData + philhealthData + pagibigData + lateData;

        double net = gross - totalDeduction;

        // Gets the value of taxableIncome and tax to be used for printing.
         double taxableIncome = withholdingTax.getTaxableIncome();
          double tax = withholdingTax.getTax();

        // This is used to Print the net wage along with other details
        System.out.println("""
                ------------------------------------------
                Employee ID: %s
                Employee Name: %s
                ------------------------------------------
                Total Hours: %s               
                Gross Wage: %s

                SSS Deduction: %s
                Philhealth Deduction: %s
                Pag-Ibig Deduction: %s                       
                Late Deductions: %s

                Total Deductions: %s                                  

                Taxable Income: %s

                Withholding Tax: %s

                Net Wage: %s
                ------------------------------------------
                """.formatted(
                         employeeID,
                        employeeName,
                        hours,
                        decimalFormat.format(gross),
                        decimalFormat.format(sssData),
                        decimalFormat.format(philhealthData),
                        decimalFormat.format(pagibigData),
                        decimalFormat.format(lateData),
                        decimalFormat.format(totalDeduction),
                        decimalFormat.format(taxableIncome),
                        decimalFormat.format(tax),
                        decimalFormat.format(net)
        ));
        return net;  // Return the net wage
    }
}
