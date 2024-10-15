package Projects.Project3;
/**
 * OperatingCosts.java
 * This program calculates the operating cost for data centers and performs various searches and calculations on these cost
 *
 * @author Rikesh Thanju
 * @version 10/09/2024
 * CMSC 255 Section 12:30
 */

public class OperatingCosts {

    /**
     * Splits the string metricNames and returns it as a String array
     *
     * @param metricNames
     * @return String array contianting the individual metric names
     */
    public static String[] getOperatingMetrics(String metricNames){
        //split the string by commas to extract individual metric names and return it
        return metricNames.split(",");
    }

    /**
     * Splits the string dataCenterMetrics into a 2d double array. Each row corresponds to the metrics for a single data center
     *
     * @param dataCenterMetrics
     * @return A 2D double array
     */
    public static double[][] getDataCenters(String dataCenterMetrics){
        //split the dataCenterMetrics string by "<>" to seperate each data center's metrics
        String[] rows = dataCenterMetrics.split("<>");
        //Intialize a 2D array to store metrics for each data center
        double[][] dataCenters = new double[rows.length][];
        for(int i =0; i<rows.length; i++){
            //split each data center metric's by commas
            String[] values = rows[i].split(",");
            dataCenters[i]= new double[values.length];
            //convert each metric to a double and store it in the array
            for(int j = 0; j<values.length; j++){
                dataCenters[i][j] = Double.parseDouble(values[j]);
            }
        }
        return dataCenters;
    }

    /**
     * Computes the annual operation cost for each data center using the provided formula
     * @param dataCenters
     * @return An int array of data center numbers (1-based index) that have an annual operation cost <= 18.5.
     *
     */
    public static int[] findAnnualOperationCost(double[][] dataCenters){
        int[] temp  = new int[dataCenters.length];
        int count = 0;
        //loop thrpugh each data centers metrics
        for(int i =0; i<dataCenters.length; i++){
            double failureRate = dataCenters[i][0];
            double hardware = dataCenters[i][1];
            double maintenance = dataCenters[i][2];
            double power = dataCenters[i][3];
            double area = dataCenters[i][4];
            double climateControl = dataCenters[i][5];
            //calculate the operation cost
            double operationCost = (1+failureRate)*(hardware*maintenance+power+area/climateControl);
            //check if cost is within the limit and add it to the array
            if(operationCost<=18.5){
                temp[count++] = i+1;
            }
        }
        int[] result = new int[count];
        System.arraycopy(temp,0,result,0,count);
        return result;
    }

    /**
     * Finds the 2 highest value metrics for the given data cetner and returns the metric name
     * @param dataCenters
     * @param metrics
     * @param dataCenter
     * @return A string containing the 2 highest metrics in the format "<highest metric> and <second highest metric>"
     */
    public static String searchHighestMetric(double[][] dataCenters, String[] metrics, int dataCenter){
        //get the metrics for the specific data center
        double[] values =dataCenters[dataCenter-1];
        int highestIndex = 0;
        int secondHighestIndex = 0;
        //loop to find the 2 highest metric values
        for(int i =0; i<values.length;i++){
            if(values[i]>values[highestIndex]){
                secondHighestIndex = highestIndex;
                highestIndex = i;
            }
            else if(values[i]>values[secondHighestIndex]){
                secondHighestIndex = i;
            }
        }
        return metrics[highestIndex] + " and " + metrics[secondHighestIndex];
    }

    /**
     * Searches for the data center with the highest value for the given metric
     * @param dataCenters
     * @param metrics
     * @param metric
     * @return The number (1-based index) of the data center with the highest value for the given metric.
     */
    public static int searchHighestDataCenter(double[][] dataCenters, String[] metrics, String metric){

        int metricIndex = 0;
        //loop through metrics array to find the index of the requested metric
        for(int i =0; i<metrics.length; i++){
            if(metrics[i].equals(metric)){
                metricIndex = i;
                break;
            }
        }
        int highestIndex = 0;
        //loop through the data center starting from the second one
        for(int i =1; i<dataCenters.length; i++){
            //compare values
            if(dataCenters[i][metricIndex] > dataCenters[highestIndex][metricIndex]){
                highestIndex = i;
            }
        }
        return highestIndex +1;
    }


    /**
     * main method that accepts 2 strings from the command line, processes  them and prints the results.
     * @param args
     */
    public static void main(String[] args){
        //get the first and second command line arguments as input
        String metricNames = args[0];
        String dataCenterMetrics = args[1];

        String[] metrics = getOperatingMetrics(metricNames);

        double[][] dataCenters = getDataCenters(dataCenterMetrics);

        int[] annualCostCenters = findAnnualOperationCost(dataCenters);

        System.out.println("Data Centers under the annual cost limit are: ");
        System.out.print("[");
        for(int i =0; i<annualCostCenters.length;i++){
            System.out.print(annualCostCenters[i]);
            if(i<annualCostCenters.length-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");

        String highestMetrics = searchHighestMetric(dataCenters,metrics,1);
        System.out.println("The two highest value operating cost metric for Data Center 1 are: " + highestMetrics);
        int highestPowerCenter = searchHighestDataCenter(dataCenters, metrics, "power");
        System.out.println("The Data Center with the highest power is: " + highestPowerCenter);
    }
}


