// Superclass
class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

// Subclasses
class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

// Subclasses
class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}


public class Polymorphism {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape square = new Square();

        circle.draw(); // Output: Drawing a circle
        square.draw(); // Output: Drawing a square
    }
}
