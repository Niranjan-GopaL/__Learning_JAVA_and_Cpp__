#include <bits/stdc++.h>
using namespace std;



class MenuItem{
public:
    string name;
    double calories;

    void print(){
        cout << name << "( " << calories << " cal )" << "\n";
    }
};




// normal implementaion
class DrinkItem{

public:
    string name;
    double calories;
    double ounces;

    double cal_per_ounce(){
        return calories/ounces * 1.23 ;
    }

    void print(){
        cout << name << "( " << calories << " cal )"  << "\n";
    }

};


// using inheritance the code becomes a lot more maintainable and strucutred
// the derived class will have all the non-private properties of a base class and can add on top of it 


// using inheritance

class Drink_Item : public MenuItem{

public:
    double ounces;

    double cal_per_ounce(){
        return calories/ounces * 1.23 ;
    }
};




int main() {

    MenuItem fish;

    fish.calories = 400;
    fish.name = "fish";

    fish.print();

    
    Drink_Item mango_juice;
    mango_juice.name = "Mango juice";
    mango_juice.calories = 400;

    mango_juice.print();

    cout << "Calories per ounce" << mango_juice.cal_per_ounce()  << "\n";


    // <----------------------------------->
    // this works fine !
    // pointer to BASE CLASS OBJECT
    // can store DERIEVED CLASS OBJECT
    // this is an example of polymorphism
    // <----------------------------------->

    MenuItem* ptr_to_menu_obj;
    ptr_to_menu_obj = &mango_juice;

    ptr_to_menu_obj -> print() ;


    return 0;
}

