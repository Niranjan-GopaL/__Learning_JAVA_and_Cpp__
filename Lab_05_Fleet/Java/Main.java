import java.util.ArrayList;
import java.util.Scanner;


class Vehicle{

    private int    vehicleID;
    private int    rentalCost;
    private String brand;
    private double price;

    public Vehicle() {}

    public Vehicle(int vehicleID, String brand, double price, int rentalCost) {

        this.vehicleID  = vehicleID;
        this.brand      = brand;
        this.price      = price;
        this.rentalCost = rentalCost;

    }
    
    //  getters and setters
    public int    getVehicleID()   { return vehicleID;  }
    public String getBrand()       { return brand;      }
    public double getPrice()       { return price;      }
    public int    getRentalCost()  { return rentalCost; }

    public void  setVehicleID(int newVehicleID)    { vehicleID = newVehicleID;   }
    public void  setBrand(String newBrand)         { brand = newBrand;           }
    public void  setPrice(double newPrice)         { price = newPrice;           }
    public void  setRentalCost(int newRentalCost)  { rentalCost = newRentalCost; }

}




class Car extends Vehicle{

    private String fuel;
    private String type;
    private String transmission;

    public Car(int vehicleID, String brand, double price, int rentalCost, String transmission, String fuel, String type){

        // inherits the attributes of parent class Vehicle
        super(vehicleID, brand, price, rentalCost);

        // specific to car
        this.fuel = fuel;
        this.type = type;
        this.transmission = transmission;

    }

    // getters and setters
    public String getTransmission() { return transmission; }
    public String getfuel()         { return fuel;         }
    public String getType()         { return type;         }

    public void setTransmission(String newTransmission) { transmission = newTransmission; }
    public void setfuel(String newfuel)                 { fuel = newfuel;                 }
    public void setType(String newType)                 { type = newType;                 }

}





class Truck extends Vehicle{

    private double cargoCapacity;
    private double bedLength;
    private int    noOfAxles;
    private double fuelEfficiency;

    public Truck(int vehicleID, String brand, double price, int rentalCost, double cargoCapacity, double bedLength, int noOfAxles, double fuelEfficiency){

        super(vehicleID, brand, price, rentalCost);
        this.bedLength      = bedLength;
        this.cargoCapacity  = cargoCapacity;
        this.fuelEfficiency = fuelEfficiency;
        this.noOfAxles      = noOfAxles;

    }

    //  getters and setters
    public double getCargoCapacity()  { return cargoCapacity;  }
    public double getBedLength()      { return bedLength;      }
    public double getfuelEfficiency() { return fuelEfficiency; }
    public int    getNoOfAxles()      { return noOfAxles;      }

    public void  setCargoCapacity(double newCargoCapacity)   {  cargoCapacity = newCargoCapacity;   }
    public void  setBedLength(double newBedLength)           {  bedLength = newBedLength;           }
    public void  setfuelEfficiency(double newFuelEfficiency) {  fuelEfficiency = newFuelEfficiency; }
    public void  setNoOfAxles(int newNoOfAxles)              {  noOfAxles = newNoOfAxles;           }
}





class Bicycle extends Vehicle{

    private String type;
    private String frame;
    private int    noOfGears;

    public Bicycle(int vehicleID, String brand, double price, int rentalCost, String type, String frame, int noOfGears) {

        super(vehicleID, brand, price, rentalCost);
        this.frame = frame;
        this.noOfGears = noOfGears;
        this.type = type;

    }

    //  getters and setters
    public String getType()      { return this.type;      }
    public String getFrame()     { return this.frame;     }
    public int    getNoOfGears() { return this.noOfGears; }

    public void setType(String newType)        { type = newType;           }
    public void setFrame(String newFrame)      { frame = newFrame;         }
    public void setNoOfGears(int newNoOfGears) { noOfGears = newNoOfGears; }
}



class Drone extends Vehicle{
    
    private double maxAltitude;
    private double flyingTime;
    private int    cameraResolution;

    public Drone(int vehicleID, String brand, double price, int rentalCost, double maxAltitude, double flyingTime, int cameraResolution)
    {
        super(vehicleID, brand, price, rentalCost);
        this.cameraResolution = cameraResolution;
        this.maxAltitude      = maxAltitude;
        this.flyingTime       = flyingTime;
    }

   
    //  getters and setters
    public double getMaxAltitude()      { return maxAltitude;      }
    public double getFlyingTime()       { return flyingTime;       }
    public int    getCameraResolution() { return cameraResolution; }

    public void setMaxAltitude(double newMaxAltitude)        { maxAltitude = newMaxAltitude;           }
    public void setFlyingTime(double newFlyingTime)          { flyingTime = newFlyingTime;             }
    public void setCameraResolution(int newCameraResolution) { cameraResolution = newCameraResolution; }
   

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

    public ArrayList<RentalHistory> getListOfRentedVehicles() {
        return this.listOfRentedVehicles;
    }

}




class FleetManagement{   

    private ArrayList<Vehicle>  listOfVehicles  ;
    private ArrayList<Customer> listOfCustomers ;

    public FleetManagement(){
        listOfVehicles  = new ArrayList<>();
        listOfCustomers = new ArrayList<>();
    }

    ArrayList<Vehicle> getListOfVehicles() { return this.listOfVehicles; }


    public Customer getCustomer(int CustomerID) {

        for (Customer customer : listOfCustomers) 
            if(customer.getCustomerID() == CustomerID)
                return customer;
        
        return new Customer();
    }


    public Vehicle getVehicle(int vehicleID){

        for (Vehicle vehicle : listOfVehicles)
            if(vehicle.getVehicleID() == vehicleID)
                return vehicle;

        return new Vehicle();
    }



    public void addCar(Car car) {
        this.listOfVehicles.add(car);
        System.out.printf(
            "Car - ID: %d, brand: %s, price: %.2f, Rental Cost: %d/day, type: %s, fuel: %s, transmission: %s\n",
            car.getVehicleID(), car.getBrand(), car.getPrice(), car.getRentalCost(), car.getType(), car.getfuel(), car.getTransmission()
        );
    }

    public void addTruck(Truck truck) {
        this.listOfVehicles.add(truck);
        System.out.printf(
            "Truck - ID: %d, brand: %s, price: %.2f, Rental Cost: %d/day, Cargo Capacity: %.2f kg, Bed Length: %.2f m, Axles: %d, Mileage: %.2f miles/gallon\n",
            truck.getVehicleID(), truck.getBrand(), truck.getPrice(), truck.getRentalCost(), truck.getCargoCapacity(), 
            truck.getBedLength(), truck.getNoOfAxles(), truck.getfuelEfficiency()
        );
    }

    public void addDrone(Drone drone) {
        this.listOfVehicles.add(drone);
        System.out.printf(
            "Drone - ID: %d, brand: %s, price: %.2f, Rental Cost: %d/day, Max Altitude: %.2f m, Flight time: %.2f min, Camera Resolution: %d MP\n",
            drone.getVehicleID(), drone.getBrand(), drone.getPrice(), drone.getRentalCost(), drone.getMaxAltitude(), 
            drone.getFlyingTime(), drone.getCameraResolution()
        );
    }


    public void addBicycle(Bicycle newBicycle){
        this.listOfVehicles.add(newBicycle);
        System.out.printf(
            "Bicycle - ID: %d, brand: %s, price: %.2f, Rental Cost: %d/day, type: %s, frame: %s, Gears: %d\n",
            newBicycle.getVehicleID(), newBicycle.getBrand(), newBicycle.getPrice(), newBicycle.getRentalCost(),
            newBicycle.getType(), newBicycle.getFrame(), newBicycle.getNoOfGears()
        );
    }




    public void FLEET_STATISTICS() {

        double amount = 0.0, cargoCapacity = 0.0;

        for(Vehicle vehicle : this.listOfVehicles) {
            amount += vehicle.getPrice();
            if (vehicle instanceof Truck)
                cargoCapacity += ((Truck)vehicle).getCargoCapacity();

        }

        System.out.printf("Total Value of All Vehicles: %.2f\n", amount);
        System.out.printf("Total Cargo Capacity of Trucks: %.2f kg\n", cargoCapacity);
    }
    

    public void addCustomer(Customer newCustomer){

        this.listOfCustomers.add(newCustomer);
        int CustomerID = newCustomer.getCustomerID();
        System.out.println("Customer "+CustomerID+" added");

    }



    public void vehicleRental(int CustomerID, int vehicleID, int duration){

        for (Customer customer: listOfCustomers){

            if (customer.getCustomerID() == CustomerID){
                
                for (Vehicle vehicle: listOfVehicles){
                    if(vehicle.getVehicleID() == vehicleID) {

                        int rentalCost = vehicle.getRentalCost() * duration;

                        System.out.println("Vehicle " + vehicleID + " Rented for " + duration + " days by customer " + CustomerID+". Rental Cost: " + rentalCost);

                        RentalHistory rentalHistory = new RentalHistory(vehicleID, vehicle.getBrand(), duration, rentalCost);
                        ArrayList<RentalHistory> CustomerRentalHistory = customer.getListOfRentedVehicles();

                        CustomerRentalHistory.add(rentalHistory);
                
                    }}
        }}
    }

    
    public static void main(String[] args) {        
        
        Scanner sc = new Scanner(System.in);
        FleetManagement fleetManagement = new FleetManagement();
        int CustomerCount = 1; String input;

        while (true){

            input = sc.nextLine();
            String[] inputStrings = input.split(" ");


            if( inputStrings[0].equals("END") )
                break;

            else if( inputStrings[0].equals("ADD_VEHICLE") ){

                if ( inputStrings[1].equals("c") )
                {
                    // vehicle parameters
                    int    vehicleID    = Integer.parseInt(inputStrings[2]);
                    String brand        = inputStrings[3];
                    double price        = Double.parseDouble(inputStrings[4]);
                    int    rentalCost   = Integer.parseInt(inputStrings[5]);

                    // car specifc parameters
                    String type         = inputStrings[6];
                    String fuel         = inputStrings[7];
                    String transmission = inputStrings[8];

                    // new car object
                    Car newCar = new Car(vehicleID, brand, price, rentalCost, transmission, fuel, type);
                    
                    // adding new car to fleet
                    fleetManagement.addCar(newCar);
                }

                else if (inputStrings[1].equals("t"))
                {
                    // vehicle parameters
                    int vehicleID        = Integer.parseInt(inputStrings[2]);
                    String brand         = inputStrings[3];
                    double price         = Double.parseDouble(inputStrings[4]);
                    int rentalCost       = Integer.parseInt(inputStrings[5]);

                    // truck specific properties
                    double cargoCapacity = Double.parseDouble(inputStrings[6]);
                    double bedLength     = Double.parseDouble(inputStrings[7]);
                    int noOfAxles        = Integer.parseInt(inputStrings[8]);
                    double Mileage       = Double.parseDouble(inputStrings[9]);

                    // new Truck object
                    Truck newTruck = new Truck(vehicleID, brand, price, rentalCost, cargoCapacity, bedLength, noOfAxles, Mileage);
                    
                    // adding new Truck to fleet
                    fleetManagement.addTruck(newTruck);
                }

                else if (inputStrings[1].equals("b"))
                {
                    // vehicle parameters
                    int vechicleID  = Integer.parseInt(inputStrings[2]);
                    String brand   = inputStrings[3];
                    double price   = Double.parseDouble(inputStrings[4]);
                    int rentalCost = Integer.parseInt(inputStrings[5]);

                    // bicycle specific properties
                    String type    = inputStrings[6];
                    String frame   = inputStrings[7] + " " + inputStrings[8];
                    int Gears      = Integer.parseInt(inputStrings[9]);

                    // new Bicycle object
                    Bicycle newBicycle = new Bicycle(vechicleID, brand, price, rentalCost, type, frame, Gears);
                    
                    // adding new Bicycle to fleet
                    fleetManagement.addBicycle(newBicycle);
                }

                else if (inputStrings[1].equals("d"))
                {
                    // vehicle parameters
                    int vehicleID  = Integer.parseInt(inputStrings[2]);
                    String brand   = inputStrings[3];
                    double price   = Double.parseDouble(inputStrings[4]);
                    int rentalCost = Integer.parseInt(inputStrings[5]);
                    
                    // drone specific parameters
                    double maxAltitude   = Double.parseDouble(inputStrings[6]);
                    double flyingTime    = Double.parseDouble(inputStrings[7]);
                    int cameraResolution = Integer.parseInt(inputStrings[8]);

                    //  new drone object
                    Drone newDrone = new Drone(vehicleID, brand, price, rentalCost, maxAltitude, flyingTime, cameraResolution);

                    // adding new drone to fleet
                    fleetManagement.addDrone(newDrone);
                }
            }



            else if (inputStrings[0].equals("ADD_CUSTOMER")){

                // new customer created
                // customer id is assigned based on the order of creation of customer object
                // hence a counter is used
                Customer newCustomer = new Customer(CustomerCount);
                fleetManagement.addCustomer(newCustomer);


                CustomerCount += 1;

            }

            else if (inputStrings[0].equals("CUSTOMER_HISTORY")){

                int CustomerID     = Integer.parseInt(inputStrings[1]);
                Customer customer  = fleetManagement.getCustomer(CustomerID);

                ArrayList<RentalHistory> listOfRentedVehicles = customer.getListOfRentedVehicles();

                System.out.println("Customer " + CustomerID + " Rental History:");
                for( RentalHistory rentalHistory : listOfRentedVehicles)
                    rentalHistory.display();

            }

            else if (inputStrings[0].equals("RENT")){

                int CustomerID   = Integer.parseInt(inputStrings[1]);
                int vehicleID    = Integer.parseInt(inputStrings[2]);
                int NumberofDays = Integer.parseInt(inputStrings[3]);

                fleetManagement.vehicleRental(CustomerID,vehicleID,NumberofDays);

            }

            // prints the cost of the fleet 
            // and cargo capacity
            else if (inputStrings[0].equals("FLEET_STATISTICS"))
                fleetManagement.FLEET_STATISTICS();

        }
        sc.close();
    }
}

