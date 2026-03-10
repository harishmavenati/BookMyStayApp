/**
 * HotelBookingApp is the entry point for the Hotel Booking application.
 *
 * This program demonstrates basic object-oriented design using
 * abstraction, inheritance, polymorphism, and encapsulation.
 *
 * Different room types are modeled using a class hierarchy and
 * room availability is represented using simple variables.
 *
 * @author YourName
 * @version 1.0
 */
public class Main {

    /**
     * Application entry point.
     */
    public static void main(String[] args) {

        // Create room objects (Polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Availability variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        System.out.println("=== Hotel Room Availability ===\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleRoomAvailability);
        System.out.println("----------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailability);
        System.out.println("----------------------------------");

        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailability);
        System.out.println("----------------------------------");

        System.out.println("\nApplication terminated.");
    }
}

/**
 * Abstract class representing a general hotel room.
 * Defines common attributes and behavior for all room types.
 */
abstract class Room {

    protected int numberOfBeds;
    protected int size;
    protected double price;

    /**
     * Constructor for initializing common room properties.
     */
    public Room(int numberOfBeds, int size, double price) {
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.price = price;
    }

    /**
     * Abstract method that subclasses must implement
     * to identify the room type.
     */
    public abstract String getRoomType();

    /**
     * Displays room details.
     */
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + size + " sqm");
        System.out.println("Price per night: $" + price);
    }
}

/**
 * Represents a Single Room.
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 20, 80.0);
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
        super(2, 35, 150.0);
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
        super(3, 60, 300.0);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}
