#include <bits/stdc++.h>
using namespace std;



class Book {

    public:

        string title;
        string author;
        string ISBN;
        bool   available;

        // these are const becuase good coding practise
        // these are nevver to be changed
        Book(const string& title, const string& author, const string& isbn){
            setTitle(title);
            setAuthor(author);
            setISBN(isbn);
            setAvailable(true);
        }

        void setTitle(const string  &newTitle)  { title = newTitle ; }
        void setAuthor(const string &newAuthor) { author = newAuthor;}
        void setISBN(const string   &newISBN)   { ISBN = newISBN;    }
        void setAvailable(bool      status)     { available = status;}

        string getTitle()  { return title;    }
        string getAuthor() { return author;   }
        string getISBN()   { return ISBN;     }
        bool   isThere()   { return available;}

        void displayInfo() {
            cout << "Title: " << title << ", Author: " << author << ", ISBN: " << ISBN << "\n";
        }
};




class Library{
    private:
        vector<Book>books;
    public:
        void addBook(Book &book){
            for (auto it = books.begin(); it != books.end(); ++it) {
                if (it->getISBN() == book.getISBN()) {
                    it->setAvailable(true);
                    return;
                }
            }
            books.push_back(book);
        }
        void removeBook(Book &book){
            for (auto it = books.begin(); it != books.end(); ++it) {
                if (it->getISBN() == book.getISBN()) {
                    it->setAvailable(false);
                    return;
                }
            }
        }

        void displayAvailableBooks(){
            bool hasAvailableBooks = false;
            cout << "Available Books:\n";
            for (Book& book : books) {
                if (book.isThere()) {
                    cout << book.getTitle() << " by " << book.getAuthor() << " (ISBN: " << book.getISBN() << ")"<<"\n";
                    hasAvailableBooks = true;
                }
            }
            cout<<"\n";
            if (!hasAvailableBooks) {
                cout << "No available books in the library."<<"\n";
            }
        }
};



int main(){
    Book book1("The Great Gatsby", "F. Scott Fitzgerald", "isbn001");
    Book book2("To Kill a Mockingbird", "Harper Lee", "isbn002");
    Book book3("1984", "George Orwell", "isbn003");

    Library library; //creating 

    library.addBook(book1);
    library.addBook(book2);
    library.addBook(book3);
    library.displayAvailableBooks();
    library.removeBook(book2);
    library.displayAvailableBooks();
    library.addBook(book2);
    library.displayAvailableBooks();

    return 0;
}






