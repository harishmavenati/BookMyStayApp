import java.util.HashMap;
import java.util.Map;

/**
 * HotelBookingApp demonstrates read-only room search functionality.
 * Guests can view available rooms without modifying the system state.
 *
 * This example reinforces:
 * - Read-only access
 * - Separation of concerns
 * - Defensive programming
 *
 * @author YourName
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Hotel Room Search ===\n");

        // Domain objects (room descriptions)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Centralized inventory
        RoomInventory inventory = new RoomInventory();

        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 0); // unavailable
        inventory.registerRoom(suiteRoom.getRoomType(), 2);

        // Search service (read-only access)
        SearchService searchService = new SearchService(inventory);

        // Guest performs search
        searchService.displayAvailableRooms(singleRoom, doubleRoom, suiteRoom);

        System.out.println("\nSearch completed. System state unchanged.");
    }
}

/**
 * Abstract representation of a hotel room.
 * Defines shared properties for all room types.
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
    }
}

/**
 * Single room implementation.
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
 * Double room implementation.
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
 * Suite room implementation.
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
 * Centralized inventory for managing room availability.
 * Acts as the single source of truth.
 */
class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
    }

    public void registerRoom(String roomType, int count) {
        availability.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}

/**
 * SearchService provides read-only access to room availability.
 * It filters and displays only rooms that are currently available.
 */
class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays available rooms without modifying inventory state.
     */
    public void displayAvailableRooms(Room... rooms) {

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Defensive programming: filter unavailable rooms
            if (available > 0) {

                room.displayRoomDetails();
                System.out.println("Available: " + available);
                System.out.println("-----------------------------");

            }
        }
    }
}