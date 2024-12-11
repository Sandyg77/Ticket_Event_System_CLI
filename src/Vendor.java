public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final Configuration configuration;
    private int vendorID;

    /**
     * Constructs a Vendor object with the specified ticket pool, configuration, and vendor ID.
     *
     * @param ticketPool The TicketPool object where tickets will be added.
     * @param configuration The Configuration object that provides the simulation parameters.
     * @param vendorID The unique ID for the vendor.
     */

    public Vendor(TicketPool ticketPool, Configuration configuration, int vendorID) {
        this.ticketPool = ticketPool;
        this.configuration = configuration;
        this.vendorID = vendorID;
    }

    /**
     * The run method simulates the vendor's behavior of adding tickets to the ticket pool.
     * */

    @Override
    public void run() {
        for (int i = 1; i <= configuration.getTotalTickets(); i++) {
            // Single Event
            Ticket ticket = new Ticket(i, "Glow Fest", 6000, "Available");
            ticketPool.addTickets(ticket);

            try {
                Thread.sleep(configuration.getTicketReleaseRate() * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            if (i == configuration.getTotalTickets()) {
                System.out.println(">>>Vendor thread with ID: "+ vendorID + " has completed.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
