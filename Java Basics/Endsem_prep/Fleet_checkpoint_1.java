import java.util.Scanner;
import java.util.ArrayList;


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


}

/*
ADD_VEHICLE c 1 Toyota 20000 200 Sedan Electric Automatic
ADD_VEHICLE t 2 Ford 25000 500 100 25 2 7
ADD_VEHICLE b 3 Trek 500 10 MTB Carbon_Fibre 8
ADD_VEHICLE d 4 DJI 1000 30 1500 60 7
 */

public class Fleet_checkpoint_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

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
                        vehicles.add(newCar);
                        System.out.println("CAR ADDED");
                    }
                    else if( type.equals("t") ){
                        double cargoCapacity  = Double.parseDouble(line[6]);
                        double bedLength = Double.parseDouble(line[7]);
                        int    noOfAxles = Integer.parseInt(line[8]);
                        double fuelEfficiency = Double.parseDouble(line[9]);
                        
                        Truck newTruck = new Truck(id, brand, price, rentalCost, cargoCapacity, bedLength, noOfAxles, fuelEfficiency);
                        vehicles.add(newTruck);
                        System.out.println("TRUCK ADDED");
                    }
                    else if( type.equals("b") ){
                        String bicycleString    = line[6];
                        String frame = line[7];
                        int noOfGears  = Integer.parseInt(line[8]);

                        Bicycle newBicycle = new Bicycle(id, brand, price, rentalCost,  bicycleString, frame, noOfGears);
                        vehicles.add(newBicycle);
                        System.out.println("BICYCLE ADDED");
                    }
                    else if( type.equals("d") ){
                        double flightTime =  Double.parseDouble(line[6]);
                        double maxAltitude = Double.parseDouble(line[7]);
                        int mp = Integer.parseInt(line[8]);
                        Drone newDrone = new Drone(id, brand, price, rentalCost, flightTime, maxAltitude, mp);
                        vehicles.add(newDrone);
                        System.out.println("DRONE ADDED");
                    }

            } else if( line[0].equals("END") )
               break;
        }

        int i=0;
        for (Vehicle vehicle : vehicles) {
            System.out.println(i);
            i++;
        }


    }
}