#include <iostream>
#include <vector>
#include <map>
#include <string>

using namespace std;



/* 

This code is a direct translation of it's java counterpart in the sense that 
the logic used by the code is EXACTLY the same.

*/




/*  Usage of Aggregation design in this code :-
  
  Aggregation is used to represent the relationship between the Library (whole) 
  and its Book objects (parts). The Library class contains a collection of Book objects, 
  and it manages and interacts with the Books but doesn't control their lifecycle entirely.
  
 */



class Book {
private:
    string title;
    string author;
    string ISBN;
    bool available;

public:

    //  default constructor in order to make <map> work
    Book(){}

    Book(string title, string author, string ISBN) {
        this -> title = title;
        this -> author = author;
        this -> ISBN = ISBN;
        this -> available = true;
    }

    // getters 
    string getTitle()   { return title;     }
    string getAuthor()  { return author;    }
    string getISBN()    { return ISBN;      }
    bool getAvailable() { return available; }

    //setters
    void setTitle(string newTitle)     { title = newTitle;            }
    void setAuthor(string newAuthor)   { author = newAuthor;          }
    void setISBN(string isbn)          { ISBN = isbn;                 }
    void setAvailable(bool available)  { this->available = available; }
};





class Library {
private:
    string libID;
    // a hashMap that maps ISBN to Book object
    map<string, Book> bookMap;

public:

    Library(string libID) {
        this -> libID = libID ;
    }

    // getters and setters
    string get_LibID()               { return libID;      }
    void set_libID(string new_libID) { libID = new_libID; }

    // add a book object to the HashMap
    void addBook(Book book)          { bookMap[book.getISBN()] = book; }
    void removeBook(string ISBN)     { bookMap.erase(ISBN);            }
    Book findBookByISBN(string ISBN) { return bookMap[ISBN];           }


    // Modifies the output array by appending all the output string to it
    void displayAvailableBooks(vector<string>& output) {
        output.push_back("Books:");
        for (auto pair : bookMap) {
            if (pair.second.getAvailable()) {
                output.push_back(pair.second.getTitle());
                output.push_back(pair.second.getAuthor());
                output.push_back(pair.second.getISBN());
    }}}

};



/* ------------------------ TEST CASES TO TRY ( Directly copy paste ) ------------------------
3
1 3
Let us C
Yashavant P. Kanetkar
101
Harry Potter
JK Rowling
102
Jungle Book
Rudyard Kipling
103
2 1
103
3
----------------------------------------------------------- */

int main() {
    int num_of_operations;
    cin >> num_of_operations;


    int num_of_books_to_add, num_of_books_to_remove, choice;
    string name, author, isbn, dummy; 
    
    // All the output STRING will be added to this vecotr
    vector<string> output;
    Library library("lib_101");

    // Curly braces around each `case` cuz of an error :- 
    // "Transfer of control bypasses initialization"

    while (num_of_operations--) {
        cin >> choice;

        switch (choice) {

            case 1:{
                cin >> num_of_books_to_add;
                output.push_back("Books added:");
                getline(cin, dummy);

                while (num_of_books_to_add-- != 0) {

                    // Consume the newline character
                    getline(cin, name);
                    getline(cin, author);
                    getline(cin, isbn);

                    // Creating Book object and then adding it to the HashMap in Library
                    Book book(name, author, isbn);
                    library.addBook(book);
                    output.push_back(isbn);
                }
                break;
            }

            case 2:{
                cin >> num_of_books_to_remove;
                getline(cin, dummy); 
                output.push_back("Books removed:");

                while (num_of_books_to_remove-- != 0) {

                    getline(cin, isbn);
                    library.removeBook(isbn);
                    output.push_back(isbn);
                }
                break;
            }

            case 3:{
                // vector<string> returnedOutput = library.displayAvailableBooks();
                // output.insert(output.end(), returnedOutput.begin(), returnedOutput.end());

                library.displayAvailableBooks(output);
                
                break;
            }
        }
    }

    for (string str : output) 
        cout << str << endl;
    

    return 0;
}



