import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {

    private int maxCapacity;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    /**
     * Default constructor for creating a Configuration object.
     * Initializes the object with default values.
     */
    public Configuration() {
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }


    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketReleaseRate(int totalReleaseRate) {
        this.ticketReleaseRate = totalReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }


    /**
     * Saves the current configuration to a JSON file.
     * @param configuration The configuration object to save.
     */
        public static void saveConfig(Configuration configuration) {
            try (FileWriter writer = new FileWriter("config.json");
            ){
                Gson gson = new Gson();
                gson.toJson(configuration, writer);
            } catch (IOException e) {
                System.out.println("Error saving configuration: " + e.getMessage());
            } catch (RuntimeException e){
                System.out.println("A Run Time Error occurred: " + e.getMessage());
            }

        }

    /**
     * Loads configuration from a JSON file.
     *
     * @param configuration The configuration object to load values into.
     */
        public static void loadConfig(Configuration configuration) {
            try(FileReader reader = new FileReader("config.json");
            ) {
                Gson gson = new Gson();
                gson.fromJson(reader, Configuration.class);
                System.out.println("User Configurations loaded successfully!");
            }catch(FileNotFoundException e){
                System.out.println("Configuration file not found. Please check the file path.");
            }catch (IOException e) {
                System.out.println("Error reading the configuration file: " + e.getMessage());
            }catch (RuntimeException e){
                System.out.println("A Run Time Error occurred: " + e.getMessage());
            }
        }


    @Override
    public String toString() {
        return "Configuration{" +
                "maxCapacity=" + maxCapacity +
                ", totalTickets=" + totalTickets +
                ", totalReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                '}';
    }
}
