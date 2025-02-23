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


public class BMI_CSC215_English_RobinLane
{
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
    }

    static void displayWelcome()
    {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("-- Welcome to:");
        System.out.println("--            BODY MASS INDEX (BMI) Computation, CSC 215, English version");
        System.out.println("--                                                                   by Robin Lane");
        System.out.println("----------------------------------------------------------------------------------------------");
    }
}
