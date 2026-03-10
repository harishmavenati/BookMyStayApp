import java.util.HashMap;
import java.util.Map;

/**
 * HotelBookingApp demonstrates centralized room inventory management
 * using a HashMap. Room characteristics are modeled separately from
 * availability to maintain clear separation of concerns.
 *
 * @author YourName
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Hotel Inventory System ===\n");

        // Create room objects (domain model)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types with availability
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suiteRoom.getRoomType(), 2);

        // Display room details
        singleRoom.displayRoomDetails();
        doubleRoom.displayRoomDetails();
        suiteRoom.displayRoomDetails();

        // Display inventory state
        System.out.println("\n=== Current Room Availability ===");
        inventory.displayInventory();

        // Update inventory example
        System.out.println("\nBooking one Single Room...\n");
        inventory.updateAvailability("Single Room", -1);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}

/**
 * Abstract base class representing a hotel room.
 * Defines common attributes shared by all room types.
 */
abstract class Room {

    protected int beds;
    protected int size;
    protected double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public abstract String getRoomType();

    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqm");
        System.out.println("Price: $" + price);
        System.out.println("----------------------------");
    }
}

/**
 * Represents a Single Room.
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 20, 80);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

/**
 * Represents a Double Room.
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 35, 150);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

/**
 * Represents a Suite Room.
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 60, 300);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

/**
 * RoomInventory manages availability of rooms using a HashMap.
 * It acts as the single source of truth for room availability.
 */
class RoomInventory {

    private Map<String, Integer> availability;

    /**
     * Constructor initializes the inventory structure.
     */
    public RoomInventory() {
        availability = new HashMap<>();
    }

    /**
     * Registers a room type with its available count.
     */
    public void registerRoom(String roomType, int count) {
        availability.put(roomType, count);
    }

    /**
     * Returns the availability of a given room type.
     */
    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability using a controlled method.
     * Positive value increases rooms, negative value decreases.
     */
    public void updateAvailability(String roomType, int change) {

        int current = availability.getOrDefault(roomType, 0);
        availability.put(roomType, current + change);
    }

    /**
     * Displays the current inventory state.
     */
    public void displayInventory() {

        for (Map.Entry<String, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}
