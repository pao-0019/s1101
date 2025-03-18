/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

/**
 *
 * @author angeliquerivera
 */
public class Pagibig extends Calculation {
    private double pagibigDeduction;
    private final Grosswage grosswage;
    
    // Constructor that accepts an instance of Grosswage
    public Pagibig(Grosswage grosswage) {
        this.grosswage = grosswage; // Initialize the instance
    }
    
    @Override
    public double calculate(){
         double gross = grosswage.calculate(); // Get the gross wage from the Grosswage instance
        double pagibig;

        // Conditional statement to calculate the Pagibig deduction based on gross wage range
        if (gross > 1000.00 && gross <= 1500.00) {
            pagibig = gross * 0.03;
        } else {
            pagibig = gross * 0.04;     
        }
        
        // Maximum amount must not exceed 100.
        if (pagibig > 100) {
            pagibig = 100;
        }
         // Store the Pag-Ibig deduction value and return it
        pagibigDeduction = pagibig; // Update the instance variable
        return pagibigDeduction; // Return the Pag-Ibig deduction
    }
    
}