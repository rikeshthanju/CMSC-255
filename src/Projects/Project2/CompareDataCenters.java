package Projects.Project2;
import java.util.Scanner;

/**
 * CompareDataCenters.java
 * This program compares construction parameters of data centers from various firms, such as constructions costs, IT load, and operating costs
 * It performs calculations and comparisons to help the Virtual Cloud Unlimited (VCU) to make decisions on data center construction
 *
 * @author Rikesh Thanju
 * @version 09/19/2024
 * CMSC 255
 */

public class CompareDataCenters {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] constructionFirms = {"Turner" , "Holder","HITT","DPR","Fortis","Mortenson","JE Dunn","Clune"};
        double[] constructionCosts = {442.2,584.8,420.7,726.5,574.1,717.0,763.4,527.9};
        double[] IT_LOAD = {168.4,307.2,21.8,271.0,50.5,117.7,199.6,289.1};
        double[] operatingCost = {19.0,26.4,11.1,24.5,12.7,16.2,20.7,25.5};
        System.out.printf("The average construction cost in dollars: $%.2fM%n",calcAvg(constructionCosts));
        System.out.printf("The average IT Load in megawatts is: %.2fMW%n",calcAvg(IT_LOAD));
        System.out.printf("The highest construction cost in dollars: $%.2fM%n",findHighValue(constructionCosts));
        System.out.printf("The least operating cost in dollars is: $%.2fM%n",findLeastValue(operatingCost));
        String[] result = findHighestTwo(constructionFirms,constructionCosts);
        System.out.println("The two construction firms with the highest construction cost are:");
        for(String firm: result){
            System.out.println(firm);
        }
        String[] result2 = findLeastTwo(constructionFirms,IT_LOAD);
        System.out.println("The two construction firms with the lowest IT Load are:" );
        for(String firm:result2){
            System.out.println(firm);
        }
        //asking user the name of the construction firm that they want to search for
        System.out.println("Enter a construction firm:");
        String userFirm = input.nextLine().trim();
        if(findConstructionFirm(constructionFirms,userFirm)){
            System.out.println(userFirm +" is a construction firm in the array.");
        }
        else{
            System.out.println(userFirm + " is not a construction firm in the array.");
        }





    }

    /**
     * Calcualtes the average from all the values in the double array passed into the method
     * @param values the double array
     * @return the average value as a double
     */
    public static double calcAvg(double[] values){
        //adds the value in the array and divides it by the number of elements to find the average
        double total = 0.0;
        for (double value : values) {
            total += value;

        }
        return total/values.length;

    }

    /**
     *Searches the double array passed into the method to find the largest value
     * @param values the double array
     * @return the largest value in the array
     */
    public static double findHighValue(double[] values){
        double max = 0;
        for (double value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;

    }

    /**
     * searches the double array passed into the method to find the lowest value
     * @param values the double array
     * @return the lowest value in the array
     */
    public static double findLeastValue(double[] values){
        double min = values[0];
        for (double value : values) {
            if (value < min) {
                min = value;
            }
        }
        return min;

    }

    /**
     * Searches through the double array to find the 2 largest value firms
     * @param names the array of construction firms
     * @param values the double array
     * @return A string array containing the 2 construction firms with the largest values
     */
    public static String[] findHighestTwo(String[] names, double[] values){
        int companyIndex1 = -1;
        int companyIndex2= -1;
        for(int i=0; i<values.length;i++){
            if(companyIndex1 ==-1||values[i]>values[companyIndex1]){
                companyIndex2 = companyIndex1;
                companyIndex1=i;
            }
            else if(companyIndex2 == -1 || values[i]>values[companyIndex2]){
                companyIndex2=i;
            }
        }
        //assigning the new array with the 2 highest value firms
        String[] result = new String[2];
        result[0] = names[companyIndex1];
        result[1] = names[companyIndex2];
        return result;

    }

    /**
     * Searches through the double array to find the 2 smallest value firms
     * @param names the array of construction firms
     * @param values the double array of values
     * @return A string array containing the 2 construction firms with the lowest values
     */
    public static String[] findLeastTwo(String[] names, double[] values){
        int companyIndex1 = -1;
        int companyIndex2= -1;
        for(int i=0; i<values.length;i++){
            if(companyIndex1 ==-1||values[i]<values[companyIndex1]){
                companyIndex2 = companyIndex1;
                companyIndex1=i;
            }
            else if(companyIndex2 == -1 || values[i]<values[companyIndex2]){
                companyIndex2=i;
            }
        }
        //assigning the new array with the 2 lowest values firms
        String[] result = new String[2];
        result[0] = names[companyIndex1];
        result[1] = names[companyIndex2];
        return result;

    }

    /**
     * whether a construction firm that the user inputted is a valid construction firm within the array
     * @param names the array of construction firms
     * @param constructionFirm the construction firm that the user inputted to search for in the array
     * @return true if the construction firm is in the array, false otherwise
     */
    public static boolean findConstructionFirm(String[] names, String constructionFirm){
        boolean match = false;
        for(int i =0; i<names.length;i++){
            if(constructionFirm.equalsIgnoreCase(names[i])){
                match = true;
            }
        }
        return match;

    }

}
