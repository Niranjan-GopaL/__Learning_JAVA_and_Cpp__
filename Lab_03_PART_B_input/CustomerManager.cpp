#include <bits/stdc++.h>
using namespace std;


/*

-> OOP is a set of ideas and principles that constitute a paradigm



*/






// fn recieves POINTER 
// callee passes ADDRESS
// POINTER = ADDRESS
void print_int(int* int_ptr)        { cout << *int_ptr;  }
void print_float(float* float_ptr)  { cout << *float_ptr;}
void print_char(char* char_ptr)     { cout << *char_ptr; }


// we need to caste the void pointer to required type
// therefore it's normally seen with somthing else (char type)
void print_any(void* void_ptr,char type){
    
    switch (type){
        case 'i':
            cout << *((int*)void_ptr) << '\n';
            break;
        
        case 'f':
            cout << *((float*)void_ptr) << '\n';
            break;
        
        case 'c':
            cout << *((char*)void_ptr) << '\n';
            break;
        
        default:
            break;
    }


}



int main(){

    // This is enough to get by

    int n = 10;
    cout << n << '\n';  // 10
    cout << &n << '\n'; // F953A43

    // lvalue is a POINTER
    // therefore we can give rvalue an ADDRESS
    int * ptr_n = &n;

    cout <<  ptr_n << '\n'; // F953A43
    
    // THIS DOES NOT STORE THE Address of the pointer variable 
    cout << *ptr_n << '\n'; 
    // this is dereferencing
    

    // THIS STORES address of a pointer
    int *(*ptr_to_ptr_n) = &ptr_n;
    cout << ptr_to_ptr_n << '\n'; 

    // <---------------------------- END OF CHAPTER 1 ---------------------------->



    int n1 = 10;
    int *ptr_n1 = &n;
    *ptr_n1 = 1000;


    // gives error;
    // uninitialised ptr WAS NOT ASSIGNED AN ADDRESS
    // IT'S TRYING TO DEREFERNCE AN ADDRESS THAT was not initialsed
    // int *uninitialised_ptr;
    // /*uninitialised_ptr = 23;


    // SOLUTION.
    int v;
    int *uninitialised_ptr = &v;
    *uninitialised_ptr = 23;
    cout << "v = " << v << '\n';


    // Problems where POINTERS ARE EXTENSIVELY USED :-
    // 1. pass variable by reference to a fn,
    //    and fn can return multiple values back to the callee  
    // 2. Dynamic arrays
    // 3. Object arrays


    // <---------------------------- END OF CHAPTER 2 ---------------------------->


    // IMPORTANT!!

    // -> int pointer holds AN INTERGER
    // -> <DATATYPE> pointer holds <DATATYPE> variable
    // -> void pointer can hold ANY DATA TYPE VARIABLE ( struct, objects, etc )
    //      -> you can't directly derefernce void pointers


    int number_int = 2;
    float number_float = 2.0;
    char letter = 'c';

    print_char(&letter);
    print_float(&number_float);
    print_int(&number_int);
    print_any(&number_int,'i');
    print_any(&number_float,'f');
    print_any(&letter,'c');
}