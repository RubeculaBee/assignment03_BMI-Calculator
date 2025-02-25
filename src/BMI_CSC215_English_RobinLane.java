/*********************************************************************************
 *
 * File: BMI_CSC215_English_RobinLane
 * By: Robin Lane
 * Date: 02-24-2025
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

import java.util.ArrayList;

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

        displayWeightList();
        System.out.print("\n\n");

        displayGoodbye();
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

    //Method Split into sub-methods. First generate a weight list, then BMI list, then weight status list, then display it all to the screen in the correct format
    static void displayWeightList()
    {
        ArrayList<Double> weightList = getWeightList(lowWeightLb, highWeightLb);
        ArrayList<Double> BMIList = getEngBMIList(weightList);
        ArrayList<String> weightStatusList = getWeightStatusList(weightList);

        System.out.print("-------------------------------------------------------\n");
        System.out.printf("|  %-11s|  %-11s|  %-23s|\n", "WEIGHT", "BMI", "WEIGHT STATUS");
        System.out.print(" ----------------------------------------------------- \n");

        //The first and last members of the list are printed separately so as to include the "(LOW)" and "(HIGH)" tags
        //The escape code \033[30;103m sets the background colour to bright yellow and the text colour to black
        System.out.printf("|  %-11.2f|  %-11.5f|  %-16s\033[30;103m(LOW)\033[0m  |\n", weightList.getFirst(), BMIList.getFirst(), weightStatusList.getFirst());
        for(int i = 1; i < weightList.size()-1; i++)
            System.out.printf("|  %-11.2f|  %-11.5f|  %-23s|\n", weightList.get(i), BMIList.get(i), weightStatusList.get(i));
        System.out.printf("|  %-11.2f|  %-11.5f|  %-15s\033[30;103m(HIGH)\033[0m  |\n", weightList.getLast(), BMIList.getLast(), weightStatusList.getLast());
        System.out.print("-------------------------------------------------------\n");
    }

    //Returns an ArrayList that is a range of weights between the given low and high weights, including the current weight.
    static ArrayList<Double> getWeightList(double lowWeightLb, double highWeightLb)
    {
        ArrayList<Double> weightList = new ArrayList<Double>();

        for(double i=lowWeightLb; i<highWeightLb; i+=5.5) //each entry in the list increments by 5.5, as shown in desired output
            weightList.add(i);
        weightList.add(highWeightLb); //loop always stops before the highWeight, so it is then added at the end. This accounts for if the high weight not being in the 5.5 step of the other weights

        for(int i=0; i<weightList.size(); i++)
        {
            if(weightList.get(i) == weightLb) //if the current weight is already in the list, stop looping
                break;

            if(weightList.get(i) > weightLb) //if suddenly you skip past the current weight without finding it, add the current weight right before the one higher than it and stop looping
            {
                weightList.add(i, weightLb);
                break;
            }
        }

        return weightList;
    }

    //Takes in an ArrayList of weights and returns an ArrayList of BMIs
    static ArrayList<Double> getEngBMIList(ArrayList<Double> weightList)
    {
        ArrayList<Double> BMIList = new ArrayList<Double>();

        for(double weight: weightList)
            BMIList.add(getEngBMI(heightIn, weight));

        return BMIList;
    }

    //Takes in a Weight List and returns a list of Weight Statuses based on those weights.
    static ArrayList<String> getWeightStatusList(ArrayList<Double> weightList)
    {
        //This method takes in a weight list instead of a BMI list so that when creating the weight status list the method knows which weight is the current weight
        ArrayList<Double> BMIList = getEngBMIList(weightList); //generate a BMI List

        ArrayList<String> weightStatusList = new ArrayList<String>();

        for(int i=0; i<weightList.size(); i++)
        {
            if(weightList.get(i) == weightLb) //if the currently indexed weight is the same as the users current weight, append " (this)" to the weight status entry
                weightStatusList.add(getWeightStatus(BMIList.get(i)) + " (this)");
            else
                weightStatusList.add(getWeightStatus(BMIList.get(i))); //if not, just append the weight status as normal
        }

        return weightStatusList;
    }

    static void displayGoodbye()
    {
        System.out.print("The SFSU Mashouf Wellness Center is at 755 Font Blvd.\n\n");

        System.out.print("----------------------------------------------------------------------------------------------\n");
        System.out.printf("-- Thank you for using my program, %s!\n", name);
        System.out.print("-- Goodbye!\n");
        System.out.print("----------------------------------------------------------------------------------------------\n");
    }
}
