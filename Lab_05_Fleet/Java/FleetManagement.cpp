#include <bits/stdc++.h>
using namespace std;



class Vehicle {
    private:
        int vehicleID;
        string brand;
        double price;
        int rentalCost;

    public:
        Vehicle() {}

        // virtual calss definition to make dynamic_cast work
        // it was otherwise giving a strange error
        virtual ~Vehicle() {}

        Vehicle(int vehicleID, string brand, double price, int rentalCost) {
            this->vehicleID = vehicleID;
            this->brand = brand;
            this->price = price;
            this->rentalCost = rentalCost;
        }

        int getVehicleID()  { return this->vehicleID; }
        string getBrand()   { return this->brand; }
        double getPrice()   { return this->price; }
        int getRentalCost() { return this->rentalCost; }
};




class Car : public Vehicle {
    private:
        string Transmission;
        string Fuel;
        string type;

    public:
        Car(int vehicleID, string brand, double price, int rentalCost, string Transmission, string Fuel, string type)
            : Vehicle(vehicleID, brand, price, rentalCost), Transmission(Transmission), Fuel(Fuel), type(type) {}

        string getTransmission() { return this->Transmission; }
        string getFuel() { return this->Fuel; }
        string getType() { return this->type; }
};





class Truck : public Vehicle {
    private:
        double CargoCapacity;
        double BedLength;
        int NoOfAxles;
        double FuelEfficiency;

    public:
        Truck(int vehicleID, string brand, double price, int rentalCost, double CargoCapacity, double BedLength, int NoOfAxles, double FuelEfficiency)
            : Vehicle(vehicleID, brand, price, rentalCost), CargoCapacity(CargoCapacity), BedLength(BedLength), NoOfAxles(NoOfAxles), FuelEfficiency(FuelEfficiency) {}

        double getCargoCapacity() { return this->CargoCapacity; }
        double getBedLength() { return this->BedLength; }
        double getFuelEfficiency() { return this->FuelEfficiency; }
        int getNoOfAxles() { return this->NoOfAxles; }
};



class Bicycle : public Vehicle {
    private:
        string type;
        string frame;
        int noOfGears;

    public:
        Bicycle(int vehicleID, string brand, double price, int rentalCost, string type, string frame, int noOfGears)
            : Vehicle(vehicleID, brand, price, rentalCost), type(type), frame(frame), noOfGears(noOfGears) {}

        string getType() { return this->type; }
        string getFrame() { return this->frame; }
        int getNoOfGears() { return this->noOfGears; }
};



class Drone : public Vehicle {
    private:
        double MaxAltitude;
        double FlyingTime;
        int CameraResolution;

    public:
        Drone(int vehicleID, string brand, double price, int rentalCost, double MaxAltitude, double FlyingTime, int CameraResolution)
            : Vehicle(vehicleID, brand, price, rentalCost), MaxAltitude(MaxAltitude), FlyingTime(FlyingTime), CameraResolution(CameraResolution) {}

        double getMaxAltitude() { return this->MaxAltitude; }
        double getFlyingTime() { return this->FlyingTime; }
        int getCameraResolution() { return this->CameraResolution; }
};



class RentalHistory {
    private:
        int vehicleID;
        string brand;
        int RentalDuration;
        int rentalCost;

    public:
        RentalHistory() {}

        RentalHistory(int vehicleID, string brand, int RentalDuration, int rentalCost)
            : vehicleID(vehicleID), brand(brand), RentalDuration(RentalDuration), rentalCost(rentalCost) {}

        void display() {
            cout << "- Vehicle ID: " << this->vehicleID << ", Brand: " << this->brand << ", Rental Duration: " << this->RentalDuration << " days, Rental Cost: " << this->rentalCost << endl;
        }
};



class Customer {
    private:
        int CustomerID;
        vector<RentalHistory*> listOfRentedVehicles;

    public:
        Customer() : CustomerID(0) {}

        Customer(int CustomerID) : CustomerID(CustomerID) {}

        int getCustomerID() { return this->CustomerID; }

        vector<RentalHistory*>& getListOfRentedVehicles() {
            return this->listOfRentedVehicles;
        }

        void customer_rental_histroy_update(RentalHistory *rs){
            listOfRentedVehicles.push_back(rs);
        }
};




class FleetManagement {
private:
    vector<Vehicle*> listOfVehicles;
    vector<Customer*> listOfCustomers;

public:
    vector<Vehicle*> getListOfVehicles() { return listOfVehicles; }

    Customer* getCustomer(int CustomerID) {
        for (Customer* customer : listOfCustomers)
            if (customer->getCustomerID() == CustomerID)
                return customer;

        return new Customer(); // Return a new customer object if not found
    }

     Vehicle* getVehicle(int vehicleID) {
        for (Vehicle* vehicle : listOfVehicles)
            if (vehicle->getVehicleID() == vehicleID)
                return vehicle;

        return new Vehicle(); // Return a new vehicle object if not found
    }

    void addCar(Car* car) {
        listOfVehicles.push_back(car);

        cout << fixed << setprecision(2);
        cout << "Car - ID: " << car->getVehicleID() << ", Brand: " << car->getBrand()
                  << ", Price: " << car->getPrice() << ", Rental Cost: " << car->getRentalCost()
                  << "/day, Type: " << car->getType() << ", Fuel: " << car->getFuel()
                  << ", Transmission: " << car->getTransmission() << endl;
    }

    void addTruck(Truck* truck) {
        listOfVehicles.push_back(truck);
        cout << fixed << setprecision(2);
        cout << "Truck - ID: " << truck->getVehicleID() << ", Brand: " << truck->getBrand()
                  << ", Price: " << truck->getPrice() << ", Rental Cost: " << truck->getRentalCost()
                  << "/day, Cargo Capacity: " << truck->getCargoCapacity() << " kg, Bed Length: "
                  << truck->getBedLength() << " m, Axles: " << truck->getNoOfAxles()
                  << ", Mileage: " << truck->getFuelEfficiency() << " miles/gallon" << endl;
    }

    void addDrone(Drone* drone) {
        listOfVehicles.push_back(drone);
        cout << "Drone - ID: " << drone->getVehicleID() << ", Brand: " << drone->getBrand()
                  << ", Price: " << drone->getPrice() << ", Rental Cost: " << drone->getRentalCost()
                  << "/day, Max Altitude: " << drone->getMaxAltitude() << " m, Flight time: "
                  << drone->getFlyingTime() << " min, Camera Resolution: " << drone->getCameraResolution()
                  << " MP" << endl;
    }

    void addBicycle(Bicycle* bicycle) {
        listOfVehicles.push_back(bicycle);
        cout << "Bicycle - ID: " << bicycle->getVehicleID() << ", Brand: " << bicycle->getBrand()
                  << ", Price: " << bicycle->getPrice() << ", Rental Cost: " << bicycle->getRentalCost()
                  << "/day, Type: " << bicycle->getType() << ", Frame: " << bicycle->getFrame()
                  << ", Gears: " << bicycle->getNoOfGears() << endl;
    }
    

    void FLEET_STATISTICS() {
        double amount = 0.0;
        double cargoCapacity = 0.0;

        for (Vehicle* vehicle : listOfVehicles) {
            amount += vehicle->getPrice();
            if (Truck* truck = dynamic_cast<Truck*>(vehicle)) {
                cargoCapacity += truck->getCargoCapacity();
            }        
        }

        cout << "Total Value of All Vehicles: " << amount << endl;
        cout << "Total Cargo Capacity of Trucks: " << cargoCapacity << " kg" << endl;
    }




    void addCustomer(Customer* newCustomer) {
        listOfCustomers.push_back(newCustomer);
        int CustomerID = newCustomer->getCustomerID();
        cout << "Customer " << CustomerID << " added" << endl;
    }


    // void displayVehicle(){

    //     cout << "\n\n------------------------------- output ----------------------\n\n";
    //     for (Vehicle* vehicle : listOfVehicles) {
    //         cout << "VEHICLE id " << vehicle->getVehicleID()  << "\n" ;
    //     }
    //     cout << "\n\n------------------------------- output ----------------------\n\n";
        
    // }

    // void displayCustomer(){

    //     cout << "\n\n------------------------------- output ----------------------\n\n";
    //     for (Customer* customer : listOfCustomers) {
    //         cout << "CUSTOMER id " << customer->getCustomerID() << "\n" ;
    //     }
    //     cout << "\n\n------------------------------- output ----------------------\n\n";
        
    // }


    void vehicleRental(int CustomerID, int vehicleID, int duration) {

        

        for (Customer* customer : listOfCustomers) {

            if (customer->getCustomerID() == CustomerID) {

                for (Vehicle* vehicle : listOfVehicles) {
                    
                    if (vehicle->getVehicleID() == vehicleID) {

                        int rentalCost = vehicle->getRentalCost() * duration;
                        cout << "Vehicle " << vehicleID << " Rented for " << duration << " days by customer " << CustomerID << ". Rental Cost: " << rentalCost << endl;

                        RentalHistory* rentalHistory = new RentalHistory(vehicleID, vehicle->getBrand(), duration, rentalCost); 
                        customer -> customer_rental_histroy_update(rentalHistory);
                        
                        // vector<RentalHistory*> CustomerRentalHistory = customer->getListOfRentedVehicles();
                        // cout << CustomerRentalHistory.size() << "THIS IS THE SIZE OF customer rental";
                        // CustomerRentalHistory.push_back(rentalHistory);

                    }}
            }}

        // displayCustomer();

    }
};



int main() {

    FleetManagement fleetManagement;
    int CustomerCount = 1;
    string input;

    while (1) {

            getline(cin, input);
            stringstream ss(input);
            vector<string> inputStrings;
            string word;

            while (ss >> word) 
                inputStrings.push_back(word);
            

            if (inputStrings[0] == "END")
                break;

             else if (inputStrings[0] == "ADD_VEHICLE") {

                    if (inputStrings[1] == "c") {
                        int    vehicleID    = stoi(inputStrings[2]);
                        string brand        = inputStrings[3];
                        double price        = stod( inputStrings[4] );
                        int    rentalCost   = stoi( inputStrings[5] );
                        string type         = inputStrings[6];
                        string Fuel         = inputStrings[7];
                        string Transmission = inputStrings[8];

                        // Creating and adding new car to fleet
                        Car* newCar = new Car(vehicleID, brand, price, rentalCost, Transmission, Fuel, type);
                        fleetManagement.addCar(newCar);

                    } else if (inputStrings[1] == "t") {
                        int vehicleID        = stoi(inputStrings[2]);
                        string brand         = inputStrings[3];
                        double price         = stod(inputStrings[4]);
                        int rentalCost       = stoi(inputStrings[5]);
                        double CargoCapacity = stod(inputStrings[6]);
                        double BedLength     = stod(inputStrings[7]);
                        int NoOfAxles        = stoi(inputStrings[8]);
                        double Mileage       = stod(inputStrings[9]);

                        // Creating and adding new truck to fleet
                        Truck* newTruck = new Truck(vehicleID, brand, price, rentalCost, CargoCapacity, BedLength, NoOfAxles, Mileage);
                        fleetManagement.addTruck(newTruck);

                    } else if (inputStrings[1] == "b") {
                        
                        int vehicleID  = stoi(inputStrings[2]);
                        string brand   = inputStrings[3];
                        double price   = stod(inputStrings[4]);
                        int rentalCost = stoi(inputStrings[5]);
                        string type    = inputStrings[6];
                        string frame   = inputStrings[7] + " " + inputStrings[8];
                        int Gears      = stoi(inputStrings[9]);

                        // Creating and adding new bicycle to fleet
                        Bicycle* newBicycle = new Bicycle(vehicleID, brand, price, rentalCost, type, frame, Gears);
                        fleetManagement.addBicycle(newBicycle);

                    } else if (inputStrings[1] == "d") {

                        int vehicleID         = stoi(inputStrings[2]);
                        string brand          = inputStrings[3];
                        double price          = stod(inputStrings[4]);
                        int rentalCost        = stoi(inputStrings[5]);
                        double MaxAltitude    = stod(inputStrings[6]);
                        double FlyingTime     = stod(inputStrings[7]);
                        int CameraResolution  = stoi(inputStrings[8]);

                        // Creating and adding new drone to fleet
                        Drone* newDrone = new Drone(vehicleID, brand, price, rentalCost, MaxAltitude, FlyingTime, CameraResolution);
                        fleetManagement.addDrone(newDrone);
                    }

                } else if (inputStrings[0] == "ADD_CUSTOMER") {
                    // Creating and adding a new customer
                    Customer* newCustomer = new Customer(CustomerCount);
                    fleetManagement.addCustomer(newCustomer);
                    CustomerCount += 1;

                } else if (inputStrings[0] == "CUSTOMER_HISTORY") {
                    int CustomerID = stoi(inputStrings[1]);
                    Customer* customer = fleetManagement.getCustomer(CustomerID);

                    cout << "Customer " << CustomerID << " Rental History:" << endl;

                    vector<RentalHistory*>& listOfRentedVehicles = customer->getListOfRentedVehicles();
                    cout << listOfRentedVehicles.size();

                    for (RentalHistory* rentalHistory : listOfRentedVehicles) 
                        rentalHistory->display();
                    

                } else if (inputStrings[0] == "RENT") {
                    int CustomerID   = stoi(inputStrings[1]);
                    int vehicleID    = stoi(inputStrings[2]);
                    int NumberofDays = stoi(inputStrings[3]);


                    fleetManagement.vehicleRental(CustomerID, vehicleID, NumberofDays);

            } else if (inputStrings[0] == "FLEET_STATISTICS")
                fleetManagement.FLEET_STATISTICS();
            
        }


    return 0;
}