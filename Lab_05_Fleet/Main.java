import java.util.*;


// Base class Vehicle
class Vehicle {
    private int    vehicleID;
    private String brand;
    private double price;

    
    // Constructor
    public Vehicle(int vehicleID, String brand, double price) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.price = price;
    }
    
    // Getters
    public int    getVehicleID() { return vehicleID; }
    public String getBrand()     { return brand;     }
    public double getPrice()     { return price;     }


    // Setters
    public void getVehicleID(int newVehicleID) {  this.vehicleID = newVehicleID; }
    public void getBrand(String newBrand)      {  this.brand = newBrand;     }
    public void getPrice(double newPrice)      {  this.price = newPrice;     }


    // Additional Methods
    public String getAdditionalInfo() {return "";} // Implemented in derived classes}
    public double getRentalCost(int duration) {return price * duration;} // Basic rental cost calculation}
}


class Car extends Vehicle {
    private String type;
    private String fuel;
    private String transmission;

    public Car(int vehicleID, String brand, double price, String type, String fuel, String transmission) {
        super(vehicleID, brand, price);
        this.type = type;
        this.fuel = fuel;
        this.transmission = transmission;
    }

    @Override
    public String getAdditionalInfo() {
        return String.format("Type: %s, Fuel: %s, Transmission: %s", type, fuel, transmission);
    }
}




class Truck extends Vehicle {
    private double cargoCapacity;
    private double truckBedLength;
    private int numOfAxles;
    private double fuelEfficiency;

    public Truck(int vehicleID, String brand, double price, double cargoCapacity, double truckBedLength, int numOfAxles, double fuelEfficiency) {
        super(vehicleID, brand, price);
        this.cargoCapacity = cargoCapacity;
        this.truckBedLength = truckBedLength;
        this.numOfAxles = numOfAxles;
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public String getAdditionalInfo() {
        return String.format("Cargo Capacity: %.2f kg, Bed Length: %.2f m, Axles: %d, Fuel Efficiency: %.2f miles/gallon",
                cargoCapacity, truckBedLength, numOfAxles, fuelEfficiency);
    }

    public double getTotalCargoCapacity() {
        return cargoCapacity;
    }
}


class Bicycle extends Vehicle {
    private String type;
    private String frame;
    private int numOfGears;

    public Bicycle(int vehicleID, String brand, double price, int numOfGears, String type, String frame) {
        super(vehicleID, brand, price);
        this.numOfGears = numOfGears;
        this.type = type;
        this.frame = frame;
    }

    @Override
    public String getAdditionalInfo() {
        return String.format("Type: %s, Frame: %s, Number of Gears: %d", type, frame, numOfGears);
    }
}

class Drone extends Vehicle {
    private double maxAltitude;
    private double flyingTime;
    private int cameraResolution;

    public Drone(int vehicleID, String brand, double price, double maxAltitude, double flyingTime, int cameraResolution) {
        super(vehicleID, brand, price);
        this.maxAltitude = maxAltitude;
        this.flyingTime = flyingTime;
        this.cameraResolution = cameraResolution;
    }

    @Override
    public String getAdditionalInfo() {
        return String.format("Max Altitude: %.2f m, Flying Time: %.2f min, Camera Resolution: %d MP",
                maxAltitude, flyingTime, cameraResolution);
    }
}







// Fleet class managing vehicles and fleet statistics
class Fleet {
    private List<Vehicle> vehicles;

    public Fleet() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public double getTotalValue() {
        double totalValue = 0;
        for (Vehicle vehicle : vehicles) {
            totalValue += vehicle.getPrice();
        }
        return totalValue;
    }

    // Implement other fleet statistics methods
}






// Customer class for managing rental history
class Customer {
    private int customerID;
    private List<Vehicle> rentalHistory;

    public Customer(int customerID) {
        this.customerID = customerID;
        this.rentalHistory = new ArrayList<>();
    }

    // public void rentVehicle(Vehicle vehicle, int duration) {
    //     rentalHistory.add(vehicle);
    //     // Perform rental operations
    // }

    // public String getRentalHistory() {
    //     StringBuilder history = new StringBuilder("Customer " + customerID + " Rental History:\n");
    //     for (Vehicle vehicle : rentalHistory) {
    //         int duration = getRentalDurationForVehicle(vehicle); // Retrieve the rental duration for each vehicle
    //         history.append("- Vehicle ID: ").append(vehicle.getVehicleID())
    //                 .append(", Brand: ").append(vehicle.getBrand())
    //                 .append(", Rental Duration: ").append(duration).append(" days")
    //                 .append(", Rental Cost: ").append(vehicle.getRentalCost(duration)).append("\n");
    //     }
    //     return history.toString();
    // }

    
}


public class Main {
    public static void main(String[] args) {
        
        // Sample usage of the classes
        Fleet fleet = new Fleet();

        Scanner sc = new Scanner(System.in);


        while (true) {

            String command = sc.next();
                switch (sc.next()) {

                    case "ADD_VEHICLE":

                        switch (sc.next()) {
                            case "c":
                                int    vehicleID    = Integer.parseInt(sc.next());
                                String brand        = sc.next();
                                double price        = Double.parseDouble(sc.next());
                                double rentalPrice  = Double.parseDouble(sc.next());
                                String type         = sc.next();
                                String fuel         = sc.next();
                                String transmission = sc.next();
                                break;
                        
                            case "t":
                                break;
                        
                            case "b":
                                break;
                        
                            case "d":
                                break;
                        
                            default:
                                break;
                        }



                        break;

                    case "FLEET_STATISTICS":
                        break;

                    case "CUSTOMER_HISTORY":
                        break;

                    case "RENT":
                        break;

                    case "END":
                        sc.close();
                        break;
                }            
            
        }

        
    }
}
