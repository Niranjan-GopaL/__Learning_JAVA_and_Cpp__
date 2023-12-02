import java.util.Scanner;


public class Lab {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();
        String[] splitArray = userInput.split(" ");

        // took only four as there are only 4 different queries which the questions has.
        for (int i = 0; i < 4; i++) {
            String nextUserInput = myScanner.nextLine();
            double majorAxes = Double.parseDouble(splitArray[0]);
            double minorAxes = Double.parseDouble(splitArray[1]);
            Ellipse ellipse = new Ellipse(majorAxes, minorAxes);
            Circle circle = new Circle(2 * majorAxes);

            if ("AREA_ELLIPSE".equals(nextUserInput)) {
                System.out.printf("%.2f\n", ellipse.areaEllipse());
            }
            if ("PERIMETER_ELLIPSE".equals(nextUserInput)) {
                System.out.printf("%.2f\n", ellipse.perimeterEllipse());
            }
            if ("AREA_CIRCLE".equals(nextUserInput)) {
                System.out.printf("%.2f\n", circle.areaEllipse());
            }
            if ("PERIMETER_CIRCLE".equals(nextUserInput)) {
                System.out.printf("%.2f\n", circle.perimeterEllipse());
            }
        }
    }
}


/**
 * Ellipse class is calculating area and perimeter of ellipse based on the given values .
 */
class Ellipse{
    private double majorAxes;
    private double minorAxes;

    /**
     *Initializing
     * @param majorAxes major axes of the ellipse
     * @param minorAxes minor axes of the ellipse
     */
    Ellipse(double majorAxes,double minorAxes){
        this.majorAxes = majorAxes;
        this.minorAxes = minorAxes;
    }

    //setters are not required for major and minor axes of ellipse as they both are fixed once give values and already initialized in constructor

    //getters

    /**
     *
     * @return majoraxes of ellipse
     */
    public double getMajorAxes(){
        return this.majorAxes;
    }

    /**
     *
     * @return minoraxes of ellipse
     */
    public double getMinorAxes(){
        return this.minorAxes;
    }


    /**
     * Area of ellipse is pi*majoraxes*minoraxes
     * @return area of ellipse
     */
    public double areaEllipse(){
        return Math.PI * this.majorAxes*this.minorAxes;
    }

    /**
     * Perimeter of ellipse is pi*sqrt(2*(a^2+b^2))
     * @return perimeter of ellipse
     */
    public double perimeterEllipse() {
        return 2 * Math.PI * Math.sqrt((Math.pow(this.majorAxes, 2) + Math.pow(this.minorAxes, 2)) / 2);
    }

}

/**
 * The class Circle is child of parent class Ellipse.It will be overiding
 */
class Circle extends Ellipse implements Drawable {
    private double diameter;
    // diameter --> 2 * majorAxes (assumption in questions)
    Circle(double diameter) {
        super(diameter / 2, diameter / 2);
        this.diameter = diameter;

    }

    // Override area method for Circle using specialized formula
    @Override
    public double areaEllipse() {
        return Math.PI * Math.pow(getMajorAxes(), 2);
    }

    // Override perimeter method for Circle using specialized formula
    @Override
    public double perimeterEllipse() {
        return 2 * Math.PI * getMajorAxes();
    }

    // Implement the draw() method from the Drawable interface and my this will work only when diameter is greater than 10 units(my assumption)
    public void draw() {
        if (this.diameter>10){
            System.out.println("Drawing a circle with radius " + getMajorAxes());
        }
        else{
            System.out.println("Can't Draw the circle as radius is less than 10");
        }
    }
}


// Define the Drawable interface with a draw() method
interface Drawable {
    void draw();
}