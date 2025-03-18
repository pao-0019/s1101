/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

/**
 *
 * @author angeliquerivera
 */
public class WithholdingTax extends Calculation {
     private double tax; // Instance variable for tax
    private double taxableIncome; // Instance variable for taxable income
    private double afterTax; // Instance variable for net after tax
    private Grosswage grosswage; // Instance of Grosswage

    // Constructor that accepts an instance of Grosswage
    public WithholdingTax(Grosswage grosswage) {
        this.grosswage = grosswage; // Initialize the Grosswage instance
    }
    
    @Override
    public double calculate() {
        // Initialize other deductions to call their calculate() method.
        Calculation sss = new SSS(grosswage);
        Calculation philhealth = new Philhealth(grosswage);
        Calculation pagibig = new Pagibig(grosswage);
        Calculation latePenalty = new LatePenalty(); // Assuming this does not need Grosswage

        // Declare a temporary variable storing the total deduction.
        double totalDeduction = sss.calculate() + philhealth.calculate() + pagibig.calculate() + latePenalty.calculate();

        // Compute taxable income by getting value of gross wage minus totalDeduction.
        taxableIncome = grosswage.calculate() - totalDeduction; // Use the gross wage from the Grosswage instance

        // Conditional statement to determine tax rate.
        if (taxableIncome <= 20832) {
            tax = 0;
        } else if (taxableIncome > 20832 && taxableIncome <= 33333) {
            tax = (taxableIncome - 20832) * 0.20;
        } else if (taxableIncome > 33333 && taxableIncome <= 66667) {
            tax = 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome > 66667 && taxableIncome <= 166667) {
            tax = 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome > 166667 && taxableIncome <= 666667) {
            tax = 40833.33 + (taxableIncome - 166667) * 0.32;
        } else {
            tax = 200833.33 + (taxableIncome - 666667) * 0.35;
        }

        afterTax = taxableIncome - tax;

        // Return net after tax
        return afterTax;
    }
     // Optional: Getters for tax, taxableIncome, and afterTax if needed
    public double getTax() {
        return tax;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getAfterTax() {
        return afterTax;
 }
}