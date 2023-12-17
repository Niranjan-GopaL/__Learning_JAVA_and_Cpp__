#include <iostream>
using namespace std;

class custom_exception : public exception {
  virtual const char* what() const noexcept {
    return "Custom Exception";
  }
};

void myfunction2() {
  throw 5.6;
}

void myfunction1() {
  myfunction2();
}

int main() {
  string word = "four";

    try {

        myfunction1();
        // word.at(4); <-- generate out_of_range error
        // int a[1000000000000000000000000000000000000]; <--- bad_alloc error

    }
    catch (out_of_range& e) {
    cout << "Out of Range: " << e.what() << endl;
    }
    catch (bad_alloc& e) {
    cout << "Bad Alloc: " << e.what() << endl;
    }

    // See: https://en.cppreference.com/w/cpp/error/exception
    // Because exceptions like bad_alloc are ALSO of type 'exception' if this 
    // catch block were first, those exceptions would be caught by this 
    // catch block!  So we need to be mindful of this when ordering our 
    // catch blocks.
    catch (exception& e) {
    cout << "Exception: " << e.what() << endl;
    }
    catch (int code) {
    cout << "Error Code: " << code << endl;
    }

    // The ... is a special "catch all" catch block case that allows to catch
    // ANY type of exception.
    catch (...) {
        cout << "Default catch" << endl;
    }

  return 0;
}
