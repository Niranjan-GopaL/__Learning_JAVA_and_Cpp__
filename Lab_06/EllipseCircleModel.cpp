#include <bits/stdc++.h>
using namespace std;



class Ellipse {

private:
    double majorAxis;
    double minorAxis;

public:

    Ellipse(double majorAxis, double minorAxis) : majorAxis(majorAxis), minorAxis(minorAxis) {}

    // Getters and setters
    double getMajorAxis()  { return this->majorAxis; }
    double getMinorAxis()  { return this->minorAxis; }

    void setMajorAxis(double newMajorAxis) { this->majorAxis = newMajorAxis; }
    void setMinorAxis(double newMinorAxis) { this->minorAxis = newMinorAxis; }


    virtual  double computeArea()  {
        return M_PI * majorAxis * minorAxis / 4;
    }

    
    virtual double computePerimeter() {
        double a = majorAxis / 2;
        double b = minorAxis / 2;

        double approx = 2 * M_PI * sqrt((a * a + b * b) / 2);

        double ramanujan_approx = M_PI * (3 * (a + b) - sqrt((3 * a + b) * (a + 3 * b)));

        double h = pow((a - b), 2) / pow((a + b), 2);
        double ramanujan_approx_better_version = M_PI * (a + b) * (1 + (3 * h) / (10 + sqrt(4 - 3 * h)));

        int key = 0;
        double answer;
        switch (key) {
            case 0:
                answer = approx;
                break;

            case 1:
                answer = ramanujan_approx;
                break;

            case 2:
                answer = ramanujan_approx_better_version;
                break;

            default:
                answer = -1;
                break;
        }

        return answer;
    }

    double getEccentricity() {
        double a = majorAxis / 2;
        double b = minorAxis / 2;
        double e = sqrt(abs(a * a - b * b)) / a;
        return e;
    }

    double getLatusRectum() {
        double l = 2 * (minorAxis * minorAxis) / majorAxis;
        return l;
    }



    // Latex code generator functions
    // These methodes are for illustrating the Reusability aspect of the Design,
    //  since they can be reused in the Circle class also
    string getLatexEquationForArea() {
        stringstream ss;
        ss << "$$\\text{Area} = \\pi \\text{a} \\text{b} = \\pi " << majorAxis / 2 << " " << minorAxis / 2 << " = " << computeArea() << "$$";
        return ss.str();
    }

    string getLatexEquationForPerimeter() {
        stringstream ss;
        ss << "$$\\text{Perimeter} = \\pi \\times (\\text{a} + \\text{b}) \\times (1 + \\frac{3h}{10 + \\sqrt{4 - 3h}})$$\n";
        ss << "$$\\implies " << computePerimeter() << "$$";
        return ss.str();
    }

    string getLatexEquationForEccentricity() {
        stringstream ss;
        ss << "$$e = \\frac{\\sqrt{|a^2 - b^2|}}{a}$$";
        ss << "$$\\implies  " << computePerimeter() << "$$";
        return ss.str();
    }

    string getLatexEquationForLatusRectum() {
        stringstream ss;
        ss << "$$\\text{Latus Rectum} = \\frac{2b^2}{a}$$";
        ss << "$$\\implies " << getLatusRectum() << "$$";
        return ss.str();
    }

    string getLatexEllipseEquationForEllipse() {
        double a = majorAxis / 2;
        double b = minorAxis / 2;
        stringstream ss;
        ss << "$$\\frac{x^2}{" << a * a << "} + \\frac{y^2}{" << b * b << "} = 1$$";
        return ss.str();
    }
};



class Circle : public Ellipse {

public:
    Circle(double diameter) : Ellipse(diameter, diameter) {}

    double computeArea() override {
        return M_PI * pow(getMajorAxis() / 2, 2);
    }

    double computePerimeter() override {
        return M_PI * getMajorAxis();
    }

//  other methodes implemented in Ellipse can be reused
//  getLatexEquation functions, LatusRectum, Eccentricity computation etc

};



class Drawable {

public:
    virtual void draw() = 0; 

};



class DrawableCircle : public Circle, public Drawable {

public:
    DrawableCircle(double diameter) : Circle(diameter) {}

    void draw() {
        cout << "Drawing a circle with diameter " << getMajorAxis() << std::endl;
    }

};




int main() {
    float a, b;
    cin >> a >> b;

    Ellipse ellipse(2 * a, 2 * b);
    Circle circle(2 * a);

    vector<double> output;

    int operations = 4;

    while (operations-- != 0) {
        string op;
        cin >> op;

        if (op == "AREA_ELLIPSE") {
            double area = ellipse.computeArea();
            output.push_back(area);

        } else if (op == "PERIMETER_ELLIPSE") {
            double perimeter = ellipse.computePerimeter();
            output.push_back(perimeter);

        } else if (op == "PERIMETER_CIRCLE") {
            double perimeter_c = circle.computePerimeter();
            output.push_back(perimeter_c);

        } else if (op == "AREA_CIRCLE") {
            double area_c = circle.computeArea();
            output.push_back(area_c);
         
        }
    }


    for (double answer : output) printf("%.2f\n", answer );
    

    return 0;
}