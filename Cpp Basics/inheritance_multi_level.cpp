#include <bits/stdc++.h>
using namespace std;





/*
            PRETTY STRAIGHT FORWARD

       [X] 
        ↑
        |    
        |
       [Y] <----- inherits X
        ↑
        |    
        |
       [Z] <------ inherits Y; so by extension inherits [BOTH X AND Y]

example below

*/



class MenuItem{

public:
    string name;
    double calories;

    void print(){
        cout << name << "( " << calories << " cal )" << "\n";
    }

};



class Drink_Item : public MenuItem{

public:
    double ounces;

    double cal_per_ounce(){
        return calories/ounces * 1.23 ;
    }

};



class Hot_Drink_Item : public Drink_Item{

public:
    double temperature;


    void serving_instructions(){
        cout << "Instruction 1" << "\n";
        cout << "Instruction 2" << "\n";
        cout << "Instruction 3" << "\n";
        cout << "Instruction 4" << "\n";
        cout << "server at " << temperature << " temp" << "\n";
    }


};






/*


            X           Y
            |           |
            |           |
            |___________|
                  |
                  |
                  |
                  Z  <-------- can inherit multiple base classes.
                               can cause a lot of ambiguity.


*/


class BaseClass1{

    public:
        int data1;
        int data1_;

        void function1(){
            cout << "function of base class 1";
        }

        void function2(){
            cout << "function of base class 1";
        }
};


class BaseClass2{

    public:
        int data2;
        int data2_;

        void function1(){
            cout << "function of base class 2" << "\n";
        }

        
        void function2(){
            cout << "function of base class 2" << "\n";
        }

};



class Derived: public BaseClass1, public BaseClass2 {

    public:

        void function_derived(){
            cout << "function of derived class" << "\n";
        }


        // this would over ride the implementaion of the 2 base classes
        void function1(){
            cout << "function of Derived class over rode the Base class 1 and Base class 2 implementation" << "\n";
        }

};


// if all ---> base1,  base2,  derived class has the same function, 
// 
//      => FUNCTION OVER RIDING HAPPENS !
//           derived class over-rides the base classes' implementaion.

void solve1(){
    
    Derived derived_obj;
    // function over-riding
    derived_obj.function1();

}



// if all ---> base1 and  base2 class has the same function, 
// 
//      => ERROR !
//         calling that function from the derived class creates AMBIGUITY on WHICH function to use.



void solve2(){
    Derived derived_obj;

    // this is ambigious
    // derived_obj.function2();

    // how to reomove ambiguity
    derived_obj.BaseClass1::function2();
    derived_obj.BaseClass2::function2();

}






/*

Another problem that can occur with multiple level inheritance is the [diamond problem]


                                  X [Common Base Class]
                                  |  
                                  |  
                         .________|__________.
                         |                   |
                         |                   |
                         |                   |
          [Base Class 1] A                   B [Base Class 2]
                         |                   |
                         |                   |
                         .___________________.
                                  |  
                                  |  
                                  |  
                                  Y [Common Derived Class]


*/


// So multi level inheritance is generally not seen as a language feature






int main(){


    solve1();

    solve2();


    return 0;
}
