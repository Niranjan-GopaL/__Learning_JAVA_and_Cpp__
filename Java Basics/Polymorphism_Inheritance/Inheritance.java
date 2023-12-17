// Superclass
class Vehicle {
    protected String brand;

    public void honk() {
        System.out.println("Tuut, tuut!");
    }
}

// Subclass
class Car extends Vehicle {
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model);
    }
}


// Usage
public class Inheritance {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Corolla");
        myCar.honk();        // Output: Tuut, tuut!
        myCar.displayInfo(); // Output: Brand: Toyota, Model: Corolla
    }
}
