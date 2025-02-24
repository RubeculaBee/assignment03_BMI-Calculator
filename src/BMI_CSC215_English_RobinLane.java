/*********************************************************************************
 *
 * File: BMI_CSC215_English_RobinLane
 * By: Robin Lane
 * Date: 02-20-2025
 *
 * Description: A BMI Calculator. The program will ask for the users name, weight
 *              and height in imperial units, calculate their BMI, and then display
 *              it. Then it will ask for their low weight and high weight, and
 *              display a range of weights they could have, as well as what
 *              their BMI would be at those weights.
 *
 *********************************************************************************/

import java.util.Scanner;

import java.util.Date;
import java.text.SimpleDateFormat;

public class BMI_CSC215_English_RobinLane
{
    static String name;
    static int heightIn;
    static double weightLb;

    static double lowWeightLb;
    static double highWeightLb;

    static Scanner input = new Scanner(System.in);

    /* Main method only calls each section method in this order:
     *
     * Welcome Message Method
     * User Input Method One
     * Initial Summary Method
     * User Input Method Two
     * Weight Range Method
     * Goodbye Message Method
     *
     * This keeps the program simple and readable
     */
    public static void main(String[] args)
    {
        displayWelcome();
        System.out.print("\n");

        getPrimaryInput();
        System.out.print("\n");

        displaySummary();
        System.out.print("\n");

        getSecondaryInput();
        System.out.print("\n");
    }

    //Prints desired welcome banner
    static void displayWelcome()
    {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("-- Welcome to:");
        System.out.println("--            BODY MASS INDEX (BMI) Computation, CSC 215, English version");
        System.out.println("--                                                                   by Robin Lane");
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    //Gathers basic user information and stores in class variables
    static void getPrimaryInput()
    {
        System.out.print("Please enter your full name: ");
        name = input.nextLine();

        System.out.printf("Please enter height in feet and inches for %s: ", name);
        heightIn += input.nextInt() * 12; //height in feet is converted to inches
        heightIn += input.nextInt();

        System.out.printf("Please enter weight in pounds for %s: ", name);
        weightLb = input.nextDouble();
    }


    //Method split into three sub-methods. First returns the time, second calculates BMI, third returns weight status.
    //Then this method prints all of that information
    static void displaySummary()
    {
        double BMI = getEngBMI(heightIn, weightLb);

        System.out.printf("-- SUMMARY REPORT for %S\n", name);
        System.out.printf("-- Date and Time:      %s\n", getDateTime());
        System.out.printf("-- BMI:                %.6f (or %.1f if rounded)\n", BMI, BMI);
        System.out.printf("-- Weight Status:      %s\n", getWeightStatus(BMI));
    }

    //Returns the current date and time formatted as desired
    static String getDateTime()
    {
        Date currentDateTime = new Date(); //Stores that current time

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy"); //Creates a Format Pattern for the date (month word) (single digit day) (comma) (4 digit year)
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a"); //Creates a Format Pattern for the time (single digit hours) (double digit minutes) (double digit seconds) (am/pm)

        return dateFormat.format(currentDateTime) + " at " + timeFormat.format(currentDateTime); //returns date and time seperated by the word "at" as a string
    }

    //BMI calculation for imperial units (inches/pounds)
    static double getEngBMI(int heightIn, double weightLb)
    {
        return (weightLb / (heightIn * heightIn)) * 703;
    }

    //Returns a weight category for a given BMI
    static String getWeightStatus(double BMI)
    {
        String status;

        if(BMI < 18.5)
            status = "Underweight";
        else if(BMI >= 18.5 && BMI < 25)
            status = "Healthy Weight";
        else if (BMI >= 25 && BMI < 30)
            status = "Overweight";
        else
            status = "Obesity";

        return status;
    }

    //Gathers the users low weight and high weight
    static void getSecondaryInput()
    {
        System.out.printf("Please enter a LOW weight in pounds for %s: ", name);
        lowWeightLb = input.nextDouble();

        System.out.printf("Please enter a HIGH weight in pounds for %s: ", name);
        highWeightLb = input.nextDouble();
    }
}
