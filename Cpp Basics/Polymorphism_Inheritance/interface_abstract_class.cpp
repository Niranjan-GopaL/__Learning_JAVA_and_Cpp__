#include <bits/stdc++.h>
using namespace std;


class Shape{

// pure virtual member function
public:

    //  the `= 0` makes this WHOLE CLASS an `abstract class`; 
    // `abstract class` is a class that has at least 1 pure virtual function
    virtual double area() = 0;
    virtual void print() = 0;

    virtual ~Shape() {};
};


class Sq: public Shape{

    public:
        double side;

        Sq(double side): side(side) {};
        void print(){ cout << "side is " << side << "\n"; }
        double area(){ return side * side ; }

};



class Tri: public Shape{

    public:
        double b;
        double h;

        Tri(double b, double h): b(b), h(h) {};
        void print(){ cout << "b = " << b << " h = " << h << "\n";}
        double area(){ return 0.5 * b * h ; }

};



int main(){
    Shape *shapes[] = 
    {
        new Sq(2),
        new Sq(3),
        new Sq(4),
        new Tri(3,4),
        new Tri(6,8),
    };

    

    int c; cin >> c;
    vector<Shape*> a;
    int s; 
     while(c--  ){
        cin >> s;
        a.push_back(new Sq(s));     
     }

    Shape *shape1, *shape2, *shape3;



    for( int i= 0; i<5; i++){
        cout << "Area of the shape " << i << " " << shapes[i]->area() << "\n";
        shapes[i]->print();
    }

    //  you can't create an object of an abstract class 

    // Shape shape_1; <---------- this gives error
    // Shape *rect, *tri; <------ this is the TRUE POWER  

    /* 
                But the true power of abstract class is :-

                    -> you can group many similar things unde an `abstract class`
                    -> you can have pointers, references, etc of this class
                        -> which can be dynamically casted to derived class's
    */

    return 0;
}