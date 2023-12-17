import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class Vehicle{
    private int vehicleID;
    private String brand;
    private double price;
    private double rentalCost;

    public Vehicle(int vehicleID, String brand, double price, double rentalCost){
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.price = price;
        this.rentalCost = rentalCost;
    }

    public String getBrand(){ return brand;}
    public int getVehicleID(){ return vehicleID;}
    public double getPrice(){ return price;}
    public double getRentalCost(){return rentalCost;}


    public void  setVehicleID(int newVehicleID)    { vehicleID = newVehicleID;   }
    public void  setBrand(String newBrand)         { brand = newBrand;           }
    public void  setPrice(double newPrice)         { price = newPrice;           }
    public void  setRentalCost(double newRentalCost)  { rentalCost = newRentalCost; }
}


class Car extends Vehicle {

    private String carType;
    private String fuel;
    private String transmission;

    public Car(int vehicleID, String brand ,double price, double rentalCost,String carType, String fuel, String transmission){
        super(vehicleID, brand, price, rentalCost);
        this.carType = carType;
        this.fuel = fuel;
        this.transmission = transmission;
    }

        // Getters and Setters
    public String getType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}


class Truck extends Vehicle{

    private double cargoCapacity;
    private double bedLength;
    private int    noOfAxles;
    private double fuelEfficiency;

    public Truck(int vehicleID, String brand ,double price, double rentalCost,double cargoCapacity, double bedLength, int noOfAxles, double fuelEfficiency ){
        super(vehicleID, brand, price, rentalCost);
        this.cargoCapacity = cargoCapacity ;
        this.bedLength = bedLength ;
        this.noOfAxles = noOfAxles ;
        this.fuelEfficiency = fuelEfficiency ;
    }


    // Getters and Setters
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public double getBedLength() {
        return bedLength;
    }

    public void setBedLength(double bedLength) {
        this.bedLength = bedLength;
    }

    public int getNoOfAxles() {
        return noOfAxles;
    }

    public void setNoOfAxles(int noOfAxles) {
        this.noOfAxles = noOfAxles;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }
}


class Drone extends Vehicle{

    private double flightTime;
    private double maxAltitude;
    private int mp;

    public Drone(int vehicleID, String brand ,double price, double rentalCost, double flightTime, double maxAltitude, int mp ){
        super(vehicleID, brand, price, rentalCost);
        this.flightTime = flightTime;
        this.maxAltitude = maxAltitude;
        this.mp = mp;
    }
        // Getters and Setters
    public double getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(double flightTime) {
        this.flightTime = flightTime;
    }

    public double getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(double maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }
}


class Bicycle extends Vehicle{

    private String bicycleType;
    private String frame;
    private int noOfGears;
    

    public Bicycle(int vehicleID, String brand ,double price, double rentalCost, String bicycleType, String frame, int noOfGears ){
        super(vehicleID, brand, price, rentalCost);
        this.bicycleType = bicycleType;
        this.frame = frame;
        this.noOfGears = noOfGears;
    }

    // Getters and Setters
    public String getType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public int getNoOfGears() {
        return noOfGears;
    }

    public void setNoOfGears(int noOfGears) {
        this.noOfGears = noOfGears;
    }

}




class RentalHistory{

    private int    vehicleID;
    private String brand;
    private int    RentalDuration;
    private int    rentalCost;

    RentalHistory() {}

    public RentalHistory(int vehicleID,String brand, int RentalDuration,int rentalCost)
    {
        this.vehicleID      = vehicleID;
        this.brand          = brand;
        this.RentalDuration = RentalDuration;
        this.rentalCost     = rentalCost;
    }

    public void display(){
        System.out.println("- Vehicle ID: "+this.vehicleID+", brand: "+this.brand+", Rental Duration: "+this.RentalDuration+" days, Rental Cost: "+this.rentalCost);
    }
}


class Customer {

    private int CustomerID;
    private ArrayList<RentalHistory> listOfRentedVehicles;

    public Customer() { this.CustomerID = 0; }


    public Customer(int CustomerID) { 
        this.CustomerID = CustomerID; 
        listOfRentedVehicles = new ArrayList<>();
    }
    
    public int getCustomerID() { return this.CustomerID; }

    public void addRentalHistory(RentalHistory rentalHistory){
        this.listOfRentedVehicles.add(rentalHistory);
        System.out.println("THE SIZE OF RENTAL HISTORY LIST IS ----> " + listOfRentedVehicles.size());
    }

    public ArrayList<RentalHistory> getListOfRentedVehicles() {
        return this.listOfRentedVehicles;
    }

    public void displayRentalHistory(){
        System.out.println("Customer " + CustomerID + " Rental History:");
        for( RentalHistory rentalHistory : listOfRentedVehicles)
            rentalHistory.display();
    }

}


class FleetManagement {

    private HashMap<Integer, Vehicle> mapOfVehicles; // Assuming String key for Vehicle
    private HashMap<Integer, Customer> mapOfCustomers; // Assuming String key for Customer

    public FleetManagement() {
        mapOfVehicles = new HashMap<>();
        mapOfCustomers = new HashMap<>();
    }

    Vehicle getVehicle(int vechicleID){ return mapOfVehicles.get(vechicleID); }
    Customer getCustomer(int customerID){ return mapOfCustomers.get(customerID); }


    public void addCar(Car car) {
        System.out.println(car.getVehicleID());
        this.mapOfVehicles.put(car.getVehicleID(), car);
        System.out.printf(
            "Car - ID: %d, brand: %s, price: %.2f, Rental Cost: %.0f/day, type: %s, fuel: %s, transmission: %s\n",
            car.getVehicleID(), car.getBrand(), car.getPrice(), car.getRentalCost(), car.getType(), car.getFuel(), car.getTransmission()
        );
    }

    public void addTruck(Truck truck) {
        this.mapOfVehicles.put(truck.getVehicleID(),truck );
        System.out.printf(
            "Truck - ID: %d, brand: %s, price: %.2f, Rental Cost: %.0f/day, Cargo Capacity: %.2f kg, Bed Length: %.2f m, Axles: %d, Mileage: %.2f miles/gallon\n",
            truck.getVehicleID(), truck.getBrand(), truck.getPrice(), truck.getRentalCost(), truck.getCargoCapacity(), 
            truck.getBedLength(), truck.getNoOfAxles(), truck.getFuelEfficiency()
        );
    }

    public void addDrone(Drone drone) {
        this.mapOfVehicles.put(drone.getVehicleID(), drone);
        System.out.printf(
            "Drone - ID: %d, brand: %s, price: %.2f, Rental Cost: %.0f/day, Max Altitude: %.2f m, Flight time: %.2f min, Camera Resolution: %d MP\n",
            drone.getVehicleID(), drone.getBrand(), drone.getPrice(), drone.getRentalCost(), drone.getMaxAltitude(), 
            drone.getFlightTime(), drone.getMp()
        );
    }


    public void addBicycle(Bicycle newBicycle){
        this.mapOfVehicles.put(newBicycle.getVehicleID(), newBicycle);
        System.out.printf(
            "Bicycle - ID: %d, brand: %s, price: %.2f, Rental Cost: %.0f/day, type: %s, frame: %s, Gears: %d\n",
            newBicycle.getVehicleID(), newBicycle.getBrand(), newBicycle.getPrice(), newBicycle.getRentalCost(),
            newBicycle.getType(), newBicycle.getFrame(), newBicycle.getNoOfGears()
        );
    }


    public void FLEET_STATISTICS() {

        double amount = 0.0, cargoCapacity = 0.0;

        for(Vehicle vehicle : mapOfVehicles.values()) {
            amount += vehicle.getPrice();
            if (vehicle instanceof Truck)
                cargoCapacity += ((Truck)vehicle).getCargoCapacity();

        }

        System.out.printf("Total Value of All Vehicles: %.2f\n", amount);
        System.out.printf("Total Cargo Capacity of Trucks: %.2f kg\n", cargoCapacity);
    }

    public void addCustomer(Customer newCustomer){

        int CustomerID = newCustomer.getCustomerID();
        this.mapOfCustomers.put(CustomerID, newCustomer);
        System.out.println("Customer "+CustomerID+" added");

    }

    public void vehicleRental(int customerID, int vehicleID, int duration){
        Customer c = mapOfCustomers.get(customerID);
        Vehicle v = mapOfVehicles.get(vehicleID);
        
        int rentalCost = (int)v.getRentalCost() * duration;
        System.out.println("Vehicle " + vehicleID + " Rented for " + duration + " days by customer " + customerID+". Rental Cost: " + rentalCost);

        RentalHistory rentalHistory = new RentalHistory(vehicleID, v.getBrand(), duration, rentalCost);
        c.addRentalHistory(rentalHistory);
    }

/*
ADD_VEHICLE c 1 Toyota 20000 200 Sedan Electric Automatic
ADD_VEHICLE t 2 Ford 25000 500 100 25 2 7
ADD_VEHICLE b 3 Trek 500 10 MTB Carbon_Fibre 8
ADD_VEHICLE d 4 DJI 1000 30 1500 60 7



ADD_VEHICLE c 1 Toyota 20000 200 Sedan Electric Automatic
ADD_VEHICLE t 2 Ford 25000 500 100 25 2 7
ADD_VEHICLE b 3 Trek 500 10 MTB Carbon_Fibre 8
ADD_VEHICLE d 4 DJI 1000 30 1500 60 7
FLEET_STATISTICS
ADD_CUSTOMER
ADD_CUSTOMER
END
 */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FleetManagement fleetManagement = new FleetManagement();
        int CustomerCount = 1;

        while (true) {
            String[] line = sc.nextLine().split(" ");
            String command = line[0];

            if (command.equals("ADD_VEHICLE")) {
                String type = line[1];
                
                int id = Integer.parseInt(line[2]);
                String brand = line[3];
                double price = Double.parseDouble(line[4]);
                double rentalCost = Double.parseDouble(line[5]);
                
                    if( type.equals("c") ){
                        String carType = line[6];
                        String fuel = line[7];
                        String transmission = line[8];
                        
                        Car newCar = new Car(id, brand, price, rentalCost,carType,fuel,transmission);

                        fleetManagement.addCar(newCar);                        
                        System.out.println("CAR ADDED");
                    }
                    else if( type.equals("t") ){
                        double cargoCapacity  = Double.parseDouble(line[6]);
                        double bedLength = Double.parseDouble(line[7]);
                        int    noOfAxles = Integer.parseInt(line[8]);
                        double fuelEfficiency = Double.parseDouble(line[9]);
                        
                        Truck newTruck = new Truck(id, brand, price, rentalCost, cargoCapacity, bedLength, noOfAxles, fuelEfficiency);

                        fleetManagement.addTruck(newTruck);                        
                        System.out.println("TRUCK ADDED");
                    }
                    else if( type.equals("b") ){
                        String bicycleString    = line[6];
                        String frame = line[7];
                        int noOfGears  = Integer.parseInt(line[8]);

                        Bicycle newBicycle = new Bicycle(id, brand, price, rentalCost,  bicycleString, frame, noOfGears);

                        fleetManagement.addBicycle(newBicycle);;
                        System.out.println("BICYCLE ADDED");
                    }
                    else if( type.equals("d") ){
                        double flightTime =  Double.parseDouble(line[6]);
                        double maxAltitude = Double.parseDouble(line[7]);
                        int mp = Integer.parseInt(line[8]);

                        Drone newDrone = new Drone(id, brand, price, rentalCost, flightTime, maxAltitude, mp);

                        fleetManagement.addDrone(newDrone);;
                        System.out.println("DRONE ADDED");
                    }
            } 

            else if (line[0].equals("ADD_CUSTOMER")){

                Customer newCustomer = new Customer(CustomerCount);
                fleetManagement.addCustomer(newCustomer);
                CustomerCount += 1;

            }    


            else if (line[0].equals("CUSTOMER_HISTORY")){
                int CustomerID     = Integer.parseInt(line[1]);
                Customer customer  = fleetManagement.getCustomer(CustomerID);
                customer.displayRentalHistory();
            }
                

            else if (line[0].equals("RENT")){

                int CustomerID   = Integer.parseInt(line[1]);
                int vehicleID    = Integer.parseInt(line[2]);
                int NumberofDays = Integer.parseInt(line[3]);

                fleetManagement.vehicleRental(CustomerID,vehicleID,NumberofDays);
            }

            else if (line[0].equals("FLEET_STATISTICS"))
                fleetManagement.FLEET_STATISTICS();

            else if( line[0].equals("END") )
                break;
        }
    }
}