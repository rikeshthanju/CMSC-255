package Projects.Project1;

/*
 *ConstructionCosts
 *
 *Program that calculates how much it would cost to construct a data center based on user input
 *
 *@author Rikesh Thanju
 *@version 09/10/2024
 *CMSC 255 Section 12:30
 */
import java.util.Scanner;
public class ConstructionCosts{
    public static void main(String[] args){
        //creating scanner
        Scanner input = new Scanner(System.in);

        //creating loop continuation condition variable
        boolean condition = true;
        while(condition){
            //creation and initialization of the varialbes I will need
            String solarPanels;
            int smallServers = 0;
            int medServers = 0;
            int largeServers = 0;
            int clients=0;
            double totalCost = 0;
            double materials=0;
            double wages=0;
            double solarCost;
            int remainingClients=0;
            int serverCost=0;
            //asking user for square footage of data center
            System.out.println("Enter the data centers square footage:");
            int sqft = input.nextInt();
            //asking user for how many floors are in the data center
            System.out.println("Enter the number of stories:");
            int stories = input.nextInt();
            input.nextLine();
            //asking the user if they have an amount of clients or do they want us to create a random number
            System.out.println("Would you like to randomly generate a number of clients (1) or enter it yourself (2)?");
            int clientChoice = input.nextInt();
            input.nextLine();
            //if statement using the user input to either generate #of clients of take an input of the #of clients
            if(clientChoice ==1){
                clients = (int)(Math.random()*(10000000-100000+1))+100000;
                System.out.println(clients);
            }
            else if(clientChoice==2){
                System.out.println("Enter a number of clients between 100,000 and 10,000,000:");
                clients = input.nextInt();
                input.nextLine();
                if(clients<100000||clients>10000000){
                    System.out.println("Incorrect Input");
                    continue;
                }
            }
            else{
                System.out.println("Incorrect Input");
                continue;
            }

            //asking the user if the data center will have solar panels

            while(true){
                System.out.println("Will solar panels be used?");
                solarPanels = input.nextLine().trim();
                if(solarPanels.equalsIgnoreCase("yes") || solarPanels.equalsIgnoreCase("no")){
                    break;
                }
                else{
                    System.out.println("Incorrect Input: Please enter 'yes' or 'no'");
                }
            }


            //calculate how many large/medium/small servers the data center will need to house all the clients
            largeServers = clients/50000;
            remainingClients = clients%50000;
            medServers = remainingClients/10000;
            remainingClients = remainingClients%10000;
            smallServers = remainingClients/5000;
			/*when there are more than 5000 clients but less than
			  10000 clients left over another medium server will be added but
			  if there are less than 5000 but more than 0 then another small server will be added
			*/
            if(remainingClients>5000 && remainingClients<10000){
                medServers++;
                smallServers--;
            }
            if(remainingClients<5000 && remainingClients>0){
                smallServers++;
            }
            remainingClients = remainingClients%5000;







            //calculating the total server cost with all the servers
            serverCost= (largeServers * 125000) + (medServers*40000) + (smallServers*25000);
            //material calculations
            materials += 30* sqft * stories;
            //wages for the labor calculations
            wages += 100 * Math.sqrt(sqft);
            //calculating the cost of the solar panels and the total cost of the whole data center with solar panels and without solar panels based on the users inputs
            totalCost = materials + wages + serverCost;
            if(solarPanels.equalsIgnoreCase("yes")){
                solarCost = 15 * sqft;
                totalCost+= solarCost;
            }

            //printing out all the information on the cost of the data center and how many servers
            System.out.printf("Your data center will cost $%,.2f%n",totalCost);
            System.out.printf("To serve %,d clients this data center will need:%n", clients);
            if(largeServers>0){
                System.out.println(largeServers + " large server(s)");
            }
            if(medServers>0){
                System.out.println(medServers + " medium server(s)");
            }
            if(smallServers>0){
                System.out.println(smallServers + " small server(s)");
            }

            //ask the user whether they want to calculate the cost of another data center or end the program.
            System.out.println("Would you like to calculate the cost of another data center? Enter yes to continue");
            String redo = input.nextLine().trim();
            if(!redo.equalsIgnoreCase("yes")){
                condition=false;
                break;
            }
        }


    }

}
