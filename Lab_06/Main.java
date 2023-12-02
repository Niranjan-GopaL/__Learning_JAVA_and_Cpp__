import java.util.ArrayList;
import java.util.Scanner;




interface Drawable {
    void draw();
}

//  DrawableCircle that "is a" Circle and "is a" Drawable
class DrawableCircle extends Circle implements Drawable {

    public DrawableCircle(double diameter) {
        super(diameter);
    }

    public void draw() {
        System.out.println("Drawing a circle with diameter " + getMajorAxis());
    }
}



class Ellipse {

    private double majorAxis;
    private double minorAxis;

    Ellipse( double majorAxis, double minorAxis ){
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
    }
    
    // getters and setters
    public double getMajorAxis() { return this.majorAxis; }
    public double getMinorAxis() { return this.minorAxis; }

    public void setMajorAxis( double newMajorAxis ) { this.majorAxis = newMajorAxis; }
    public void setMinorAxis( double newMinorAxis ) { this.minorAxis = newMinorAxis; }
    

    public double computeArea()  { return Math.PI * majorAxis * minorAxis / 4; }

    
    // Since the computation is complex, there are 3 approximations given. Each better than the previous one.
    public double computePerimeter() {
        double a = majorAxis/2;
        double b = minorAxis/2;

        double approx = 2 * Math.PI * Math.sqrt( (a*a + b*b) /2  ) ; 

        double ramanujan_approx =  Math.PI * ( 3* (a + b) -Math.sqrt( (3*a + b) * ( a + 3*b ) )  ) ;

        double h = Math.pow((a - b), 2) / Math.pow((a + b), 2);
        double ramanujan_approx_better_verison = Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));

        int key = 0; double answer;
        switch (key) {
            case 0:
                answer = approx;
                break;
                
            case 1:
                answer = ramanujan_approx;
                break;
        
            case 2:
                answer = ramanujan_approx_better_verison;
                break;
        
            default:
                answer = -1;
                break;
        }


        return answer;
    }


    public double getEccentricity() {
        double a = majorAxis / 2;
        double b = minorAxis / 2;
        double e =  Math.sqrt(Math.abs(a * a - b * b)) / a;
        return e;
    }


    public double getLatusRectum() {
            double l =  2 * (minorAxis * minorAxis) / majorAxis;
            return l;
    }

    // Latex code generator functions
    // These methodes are for illustrating the Reusability aspect of the Design,
    //  since they can be reused in the Circle class also
    public String getLatexEquationForArea() {
        return "$$" + "\\text{Area} = \\pi \\text{a} \\text{b}" + " = " +  " \\pi " + majorAxis/2 + " " + minorAxis/2 + " = " + computeArea() +  "$$";
    }


    public String getLatexEquationForPerimeter() {
        String firstLine = "$$" + "\\text{Perimeter} = \\pi \\times (\\text{a} + \\text{b}) \\times (1 + \\frac{3h}{10 + \\sqrt{4 - 3h}})" + "$$\n";
        String seecondLine = "$$" + "\\implies " + computePerimeter() + "$$" ; 
        return firstLine + seecondLine ;
    }

    public String getLatexEquationForEccentricity() {
        String firstLine = "$$" + "e = \\frac{\\sqrt{|a^2 - b^2|}}{a}" + "$$";
        String seondLine = "$$" + "\\implies  " + computePerimeter() + "$$";
        return firstLine + seondLine;
    }

    public String getLatexEquationForLatusRectum() {
        String firstLine = "$$" + "\\text{Latus Rectum} = \\frac{2b^2}{a}" + "$$";
        String secondLine = "$$" + "\\implies " + getLatusRectum() + "$$";
        return firstLine + secondLine ;
    }

    public String getLatexEllipseEquationForEllipse() {
        double a = majorAxis / 2;
        double b = minorAxis / 2;
        String eq =  String.format("\\frac{x^2}{%.2f} + \\frac{y^2}{%.2f} = 1", a * a, b * b);
        return "$$" + eq + "$$";
    }
}
    





class Circle extends Ellipse {

    public Circle(double diameter) {
        super(diameter, diameter);
    }

    @Override
    public double computeArea() {
        return Math.PI * Math.pow(super.getMajorAxis() / 2, 2);
    }
    
    @Override
    public double computePerimeter() {
        return Math.PI * super.getMajorAxis();
    }

    //  other methodes implemented in Ellipse can be reused
    //  getLatexEquation functions, LatusRectum, Eccentricity computation etc
}









public class Main {


    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        float a = sc.nextFloat();
        float b = sc.nextFloat();


        Ellipse ellipse = new Ellipse(2*a, 2*b);
        Ellipse circle  = new Circle(2*a);

        ArrayList<String> output = new ArrayList<>();

        int operations = 4;

        while(operations-- != 0) {


            String op = sc.next();

            switch (op) {
            
                case "AREA_ELLIPSE":
                    double area = ellipse.computeArea();
                    output.add(String.format("%.2f", area));
                    break;
                    
                    
                    case "PERIMETER_ELLIPSE":
                    double perimeter = ellipse.computePerimeter();
                    output.add(String.format("%.2f", perimeter));
                    break;
                    
                    
                    case "PERIMETER_CIRCLE":
                    double perimeter_c = circle.computePerimeter();
                    output.add(String.format("%.2f", perimeter_c));
                    break;
                    
                    
                    case "AREA_CIRCLE":
                    double area_c = circle.computeArea();
                    output.add(String.format("%.2f", area_c));
                    break;
            
                default:
                    break;
            }
        }

        for (String string : output)
            System.out.println(string);

        sc.close();

    }

    
}
