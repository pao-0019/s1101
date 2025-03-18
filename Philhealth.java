/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

/**
 *
 * @author angeliquerivera
 */
public class Philhealth extends Calculation {
    
    private double philhealthDeduction;
     private final Grosswage grosswage;
     
     // Constructor 
    public Philhealth(Grosswage grosswage) {
        this.grosswage = grosswage; // Initialize the instance
    }
     
    @Override
    public double calculate() {
         double gross = grosswage.calculate(); 
        double PhilDed;

        // If gross is more than 60,000 limit philhealth deduction to 1,800 max
        if (gross > 60000) { 
            PhilDed = 1800;
        } else {
            PhilDed = (gross * 0.03) / 2; // Calculate PhilHealth deduction
        }

        // To store the Philhealth deduction value and return it
        philhealthDeduction = PhilDed; 
        return philhealthDeduction;
    }

    // Optional: Getter for philhealthDeduction if needed
    public double getPhilhealthDeduction() {
        return philhealthDeduction;
    }
}
