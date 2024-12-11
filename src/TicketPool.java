import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TicketPool is shared with both Vendors and Customers
public class TicketPool {
    private final List<Ticket> ticketList;
    private final int maximumCapacity;  // Maximum capacity is the number of tickets that the list can hold at a given time

    /**
     * Constructs a new TicketPool with a specified maximum capacity.
     * Initializes the ticket list as a synchronized list to allow thread-safe operations.
     *
     * @param maximumCapacity The maximum number of tickets that can be stored in the pool at any given time.
     */

    public TicketPool(int maximumCapacity) {
        this.ticketList = Collections.synchronizedList(new ArrayList<>());
        this.maximumCapacity = maximumCapacity;
    }
    /**
     * Gets the number of remaining tickets in the pool.
     * This method returns the size of the ticket list.
     *
     * @return The number of remaining tickets in the ticket pool.
     */
    public int getRemainingTicketCount() {
        return ticketList.size();
    }

    /**
     * Adds a ticket to the ticket pool.
     * If the pool is full, the method waits until space is available.
     * Once space is available, the ticket is added, and all waiting threads are notified.
     *
     * @param ticket The ticket to be added to the pool.
     */
    public synchronized void addTickets(Ticket ticket) {
        while (ticketList.size() >= maximumCapacity) {
            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        this.ticketList.add(ticket); // Adding the ticket to the synchronized list
        notifyAll();
        System.out.println("\n\n"+ Thread.currentThread().getName()+ " has added a ticket to the Ticket pool.\n" +
                "-----Current size is " + ticketList.size()+ "-----");
        System.out.println(">> Added Ticket ID: " + ticket.getTicketID() + " Event Name: " + ticket.getTicketEventName());
    }

    /**
     * Buys a ticket from the ticket pool.
     * If the pool is empty, the method waits until a ticket is available.
     * Once a ticket is available, it is removed from the pool and returned.
     *
     * @return The ticket that was bought from the pool.
     */

    public synchronized Ticket buyTicket() {
        while (ticketList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketList.removeFirst();
        notifyAll();
        System.out.println("\n\n"+ Thread.currentThread().getName() + " has bought a ticket from the Ticket pool.\n"
        + "-----Current Size is " + ticketList.size() + "-----");
        System.out.println(">> Bought Ticket ID: "+ ticket.getTicketID() + " Event Name: " + ticket.getTicketEventName() + " Price: " +  ticket.getTicketPrice());
        return ticket;
    }

}
