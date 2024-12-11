import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logs {

    private final int logID;
    private Date date;      // Date of the log entry
    private String message;


    /**
     * Constructs a new log entry with a unique ID, date, and message.
     *
     * @param logID The unique identifier for the log entry.
     * @param date The date the log entry was created.
     * @param message The message or content of the log entry.
     */

    public Logs(int logID, Date date, String message) {
        this.logID = logID;
        this.date = date;
        this.message = message;
    }


    /**
     * Saves the current log entry to a file named "Logs.txt".
     * The log entry contains the log ID, date, and message.
     * If the file doesn't exist, it will be created. Each new log entry
     * is appended to the file.
     */
    public void saveLog() {
        String logEntry = String.format("LogID: %d, Date: %s, Message: %s",
                logID, date != null ? date.toString() : "N/A", message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Logs.txt", true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error while saving log: " + e.getMessage());
        }
    }
}
