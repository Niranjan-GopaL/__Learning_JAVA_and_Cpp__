#include <iostream>
using namespace std;

// Base class
class Shape {
public:
    virtual void draw() {
        cout << "Drawing a shape" << endl;
    }
};

// Derived classes
class Circle : public Shape {
public:
    void draw() override {
        cout << "Drawing a circle" << endl;
    }
};

class Square : public Shape {
public:
    void draw() override {
        cout << "Drawing a square" << endl;
    }
};

// Usage
int main() {
    Shape* circle = new Circle();
    Shape* square = new Square();

    circle->draw(); // Output: Drawing a circle
    square->draw(); // Output: Drawing a square

    delete circle;
    delete square;
    return 0;
}


//  Methode Overloading (compile-time overloading)
class Calculator {
public:
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
};


// Operator overloadig
class Complex {
public:
    int real, imag;

    Complex operator+(const Complex& obj) {
        Complex temp;
        temp.real = real + obj.real;
        temp.imag = imag + obj.imag;
        return temp;
    }
};



// Static Polymorphism ( Template Polymorphism )
// to create code that works FOR MULTIPLE DATA TYPES
template <typename T>
T add(T a, T b) {
    return a + b;
}