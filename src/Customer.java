import java.util.Random;

public class Customer implements Runnable {

    private final TicketPool ticketPool;
    private final int noOfTickets;
    private final int retrievalRate;
    private int customerID; // Unique ID for each customer

    /**
     * Constructor for creating a customer that will buy a random number of tickets.
     *
     * @param ticketPool The ticket pool from which the customer will buy tickets.
     * @param retrievalRate The rate at which the customer retrieves tickets in seconds.
     * @param customerID The unique identifier for the customer.
     */
    public Customer(TicketPool ticketPool, int retrievalRate, int customerID) {
        Random rand = new Random();
        this.ticketPool = ticketPool;
        // Takes a random number as the ticket quantity purchased by the consumer
        this.noOfTickets = rand.nextInt(10);
        this.retrievalRate = retrievalRate;
        this.customerID = customerID;
    }

    /**
     * The entry point for the customer thread. This method simulates the customer buying
     * a random number of tickets from the ticket pool at a set retrieval rate.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    @Override
    public void run() {
        for (int i = 1; i <= noOfTickets; i++) {
            Ticket ticket = ticketPool.buyTicket();
            try {
                Thread.sleep(retrievalRate * 1000L); // Delay in seconds
            } catch (InterruptedException e) {
                System.out.println("Customer thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            if (i == noOfTickets) System.out.println(">>>Customer thread with ID: " + customerID +" has completed.");
        }
    }
}



