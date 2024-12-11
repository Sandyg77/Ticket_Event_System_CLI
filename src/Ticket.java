public class Ticket {
    private int ticketID;
    private static int  ticketCounter = 1;
    private String ticketEventName;
    private double ticketPrice;
    private String ticketStatus;


    /**
     * Constructs a new Ticket object with a unique ID, event name, price, and status.
     * The status is initialized as "Available" by default.
     *
     * @param ticketID The ID of the ticket.
     * @param ticketEventName The name of the event the ticket is for.
     * @param ticketPrice The price of the ticket.
     * @param ticketStatus The current status of the ticket (e.g., "Available").
     */

    public Ticket(int ticketID, String ticketEventName, double ticketPrice, String ticketStatus) {
        this.ticketID = generateUniqueTicketID();
        this.ticketEventName = ticketEventName;
        this.ticketPrice = ticketPrice;
        this.ticketStatus = "Available";
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketEventName() {
        return ticketEventName;
    }

    public void setTicketEventName(String ticketEventName) {
        this.ticketEventName = ticketEventName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }



    private static synchronized int generateUniqueTicketID() {
        return ticketCounter++;
    }

    /**
     * Returns a string representation of the ticket, including ticket ID,
     * event name, and ticket price.
     *
     * @return A string representation of the ticket.
     */
    @Override
    public String toString() {
        return "Ticket ID: " + ticketID + " Event Name: " + ticketEventName +
                "Ticket Price: " + ticketPrice;
    }
}
