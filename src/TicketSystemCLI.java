import java.util.Date;
import java.util.Scanner;


public class TicketSystemCLI {

/**
 * * Main method serves as the entry point to the system
 * It displays the main menu, handles user input and starts the simulation.
 * */
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n .......... WELCOME TO TICKET WAVE!......... \n");
            System.out.println("\n -----------------Main Menu-----------------\n");
            System.out.println("1. Select CLI Application");
            System.out.println("2. Exit Main Menu.");
            System.out.println("\n>>> Please select an option (1 or 2): ");

            try {
                int mainMenuOption = input.nextInt();

                switch (mainMenuOption) {
                    case 1:
                        configMenu(configuration, input);
                        break;

                    case 2:
                        System.out.println("Exiting Main Menu...");
                        return;
                    default:
                        System.out.println("Invalid option. Please select a valid option (1-2).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");

            }
        }
    }

    /**
     * Displays the configuration menu
     * @param configuration The Configuration object to be modified.
     * @param input The scanner object for user input.
     */

    private static void configMenu(Configuration configuration, Scanner input) {
        while (true) {
            System.out.println("\n---------- Configuration Menu ----------\n");
            System.out.println("1. Enter Configuration Parameters and Start Simulation");
            System.out.println("2. Load Configuration");
            System.out.println("3. Return to Main Menu");
            System.out.println("4. Exit Sub-Menu");
            System.out.println("\n Please select an option (1-4): ");

            try {
                int subMenuOption = input.nextInt();

                switch (subMenuOption) {
                    case 1:
                        enterConfigParameters(configuration, input);
                        return; // Exit the configuration menu after starting the simulation
                    case 2:
                        Configuration.loadConfig(configuration);
                        break;
                    case 3:
                        return;
                    case 4:
                        System.out.println("Exiting Configuration Menu...");
                        System.exit(0); // Terminating the config menu
                        break;
                    default:
                        System.out.println("Invalid option. Please select a valid option (1-4).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    /**
     * @param configuration The Configuration object to be updated.
     *  @param input The scanner object for user input.
     * */

    private static void enterConfigParameters(Configuration configuration, Scanner input) {
        while (true) {
            System.out.println("Enter Maximum Ticket Capacity: ");
            int maxCapacity = input.nextInt();
            if (maxCapacity > 0) {
                configuration.setMaxCapacity(maxCapacity);
                break;
            } else {
                System.out.println("Invalid value. Maximum Ticket Capacity must be positive.");
            }
        }

        while (true) {
            System.out.println("Enter Total Number of Tickets: ");
            int totalTickets = input.nextInt();
            if (totalTickets > 0) {
                configuration.setTotalTickets(totalTickets);
                break;
            } else {
                System.out.println("Invalid value. Total Tickets must be positive!");
            }
        }

        while (true) {
            System.out.println("Enter Ticket Release Rate in Seconds: ");
            int releaseRate = input.nextInt();
            if (releaseRate > 0) {
                configuration.setTicketReleaseRate(releaseRate);
                break;
            } else {
                System.out.println("Invalid value. Ticket Release Rate must be positive!");
            }
        }

        while (true) {
            System.out.println("Enter Customer Retrieval Rate in Seconds: ");
            int retrievalRate = input.nextInt();
            if (retrievalRate > 0) {
                configuration.setCustomerRetrievalRate(retrievalRate);
                break;
            } else {
                System.out.println("Invalid value. Customer Retrieval Rate must be positive!");
            }
        }

        System.out.println("\nConfiguration parameters entered successfully!");
        System.out.println("\nStarting simulation...");
        Configuration.saveConfig(configuration);
        // Log configuration parameters
        Logs configLog = new Logs(1, new Date(),
                "Configuration parameters set: " + configuration.toString());
        configLog.saveLog();

        startSimulation(configuration); // Starting the simulation
    }

     /**Initializes and starts the simulation by creating and starting vendor and customer threads.
      * @param configuration The Configuration object that defines simulation parameters.
      */
    private static void startSimulation(Configuration configuration) {
        TicketPool ticketPool = new TicketPool(configuration.getMaxCapacity());

        // Log simulation start
        Logs simStartLog = new Logs(2, new Date(), "Simulation started");
        simStartLog.saveLog();

        Vendor[] vendors = new Vendor[5];
        Thread[] vendorThreads = new Thread[5];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, configuration, i);
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThreads[i].start();

            Logs vendorLog = new Logs(3 + i, new Date(),
                    "Vendor thread started: Vendor ID-" + i);
            vendorLog.saveLog();
        }

        Customer[] customers = new Customer[5];
        Thread[] customerThreads = new Thread[5];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), i);
            customerThreads[i] = new Thread(customers[i], "Customer ID-" + i);
            customerThreads[i].start();

            Logs customerLog = new Logs(10 + i, new Date(),
                    "Customer thread started: Customer ID-" + i);
            customerLog.saveLog();
        }

        // Wait for all threads to complete
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();
            }
            for (Thread customerThread : customerThreads) {
                customerThread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error while waiting for threads to finish: " + e.getMessage());
        }

        System.out.println("Simulation completed.");
        Logs simEndLog = new Logs(20, new Date(), "Simulation completed");
        simEndLog.saveLog();
    }
}


