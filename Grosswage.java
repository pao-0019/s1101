package com.mycompany.motorph;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grosswage extends Calculation {
    private final String employeeID; // Instance field for employee ID
    private String employeeName; // Instance field for employee name
    private int targetMonth; // Instance field for the target month
    double gross; // Instance field for gross wage
    private double hourly; // Instance field for hourly rate
    private double hours; // Instance field for total hours worked

    // Declare decimalFormat for formatting output
    private static final DecimalFormat grossWageDecimalFormat = new DecimalFormat("#.##");
     private static final Scanner scanner = new Scanner(System.in); // Single Scanner instance
     
     // Constructor for employee ID
    public Grosswage(String employeeID) {
        this.employeeID = employeeID; // Initialize employeeID
    }
    
     // Constructor for employee details
    public Grosswage(String empId, String firstName, String lastName, double hourlyRate) {
        this.employeeID = empId;
        this.employeeName = firstName + " " + lastName;
        this.hourly = hourlyRate; // Initialize hourly rate
    }
    

    @Override
    public double calculate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.print("Enter Month: ");
        targetMonth = sc.nextInt(); // Set targetMonth from user input
        System.out.println("-------------------------");

        // Validate month input
        if (targetMonth < 1 || targetMonth > 12) {
            System.out.println("Invalid month. Please enter a value between 1 and 12.");
            return 0; // or handle as needed
        }

        List<Employee> employees = EmployeeModelFromFile.getEmployeeModelList(); // Get the list of employees

        // Find the employee by ID
        Employee employee = findEmployeeById(employeeID, employees);
        if (employee == null) {
            System.out.println("Employee ID " + employeeID + " not found.");
            return 0; // Exit if employee is not found
        }

        // Retrieve the hourly rate
        hourly = employee.getHourlyRate(); // Set hourly rate
        if (hourly <= 0) {
            System.out.println("Invalid hourly rate for Employee ID " + employeeID + ": " + hourly);
            return 0; // Exit if hourly rate is invalid
        }

        System.out.println("Hourly Rate: " + hourly);

        // Calculate total hours worked
        hours = calculateTotalHours(targetMonth, employeeID); // Set total hours worked

        // Calculate gross wage
        gross = calculateGrossWage(hours); // Set gross wage

        // Call printGross to display the results
        printGross();

        return gross; // Return the gross wage
    }

    private Employee findEmployeeById(String employeeId, List<Employee> employees) {
        for (var employee : employees) {
            if (employee.getEmployeeNumber().equals(employeeId)) {
                return employee; // Return the found employee
            }
        }
        return null; // Return null if not found
    }

    private double calculateTotalHours(int targetMonth, String employeeID) {
        double totalHours = 0;
        List<Integer> years = new ArrayList<>();
        // Allow user to input years
        System.out.print("Enter Year(s) (comma-separated): ");
        Scanner sc = new Scanner(System.in);
        String yearInput = sc.next();
        String[] yearStrings = yearInput.split(",");
        for (String yearStr : yearStrings) {
            years.add(Integer.parseInt(yearStr.trim())); // Add each year to the list
        }

        // Loop through the years to calculate total hours
        for (int year : years) {
            totalHours += AttendanceRecord.calculateTotalHoursAndPrint(year, targetMonth, employeeID);
        }
        return totalHours; // Return the total hours calculated
    }

    public double calculateGrossWage(double totalHours) {
        return totalHours * hourly; // Calculate gross wage
    }

    public void printGross() {
        System.out.println("""
                ------------------------------------------           
                Employee ID: %s
                Name: %s
                Hourly Rate: %.2f
                Total Hours: %.2f
                Gross Wage: %s
                ------------------------------------------
                """.formatted(
                        employeeID,
                        employeeName,
                        hourly,
                        hours,
                        decimalFormat.format(gross)
                ));
    }

    // Getters
    public String getEmployeeID() {
        return employeeID; // Return the employee ID
    }

    public double getHourly() {
        return hourly; // Return the hourly rate
    }

    public int getTargetMonth() {
        return targetMonth; // Return the target month
    }

    public double getGross() {
        return gross; // Return the gross wage
    }

    public double getHours() {
        return hours; // Return the total hours worked
    }
}