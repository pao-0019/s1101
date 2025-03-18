package com.mycompany.motorph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PAOLA A. RODRIGUEZ
 */
public class SSSRecord {
        private String compensationRange; // Compensation range (e.g., "0-1000")
        private double contribution; // Contribution amount for the range

   // Constructor
    public SSSRecord(String compensationRange, double contribution) {
        if (compensationRange == null || compensationRange.trim().isEmpty()) {
            throw new IllegalArgumentException("Compensation range cannot be null or empty.");
        }
        if (contribution < 0) {
            throw new IllegalArgumentException("Contribution must be non-negative.");
        }
        this.compensationRange = compensationRange;
        this.contribution = contribution;
}
// Getter for compensationRange
    public String getCompensationRange() {
        return compensationRange;
    }

    // Getter for contribution
    public double getContribution() {
        return contribution;
    }
    @Override
    public String toString() {
        return "SSSRecord{" +
                "compensationRange='" + compensationRange + '\'' +
                ", contribution=" + contribution +
                '}';
 }
}