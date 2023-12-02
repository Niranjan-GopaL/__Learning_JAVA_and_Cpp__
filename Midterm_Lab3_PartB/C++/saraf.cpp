#include<iostream>
#include<string>
#include<vector>
#include<sstream>
#include<unordered_map>
#include<fstream>


using namespace std;


//class book
class Book{
    private:
    int bookId ;
    string title;
    string author;
    int copiesAvailable;

    public:
    
    Book( int bookId, string title,string author,int copiesAvailable){
        this->bookId = bookId;
        this->author = author;
        this->title = title;
        this->copiesAvailable = copiesAvailable;
    }

    int getBookId(){
        return this->bookId;
    }
    string getTitle(){
        return this->title;
    }
    string getAuthor(){
        return this->author;
    }
    int getCopiesAvailable(){
        return this->copiesAvailable;
    }
    
    void setTitle(string title){
        this->title = title;
    }
    void setAuthor(string author){
        this->author= author ;
    }
    void setCopiesAvailable(int copiesAvailable){
        this->copiesAvailable = copiesAvailable;
    }   
};




class Student {
    private :

    int StudentId;
    string name;
    vector<Book*> borrowedBooks;

    public:
    Student(){}


    Student(int StudentId,string name){
        this->StudentId = StudentId;
        this->name = name;
    }


    void addBooks(Book *b){
        borrowedBooks.push_back(b);
    }


    void deleteBook(Book *b){
        for(auto it = borrowedBooks.begin();it != borrowedBooks.end();it++){
            if((*it)->getBookId() == b->getBookId()){
                borrowedBooks.erase(it);
                break;
            }
        }
    }


    vector<Book*> getBorrowedBooks() { return this->borrowedBooks; }
    string getName()                 { return this->name;          }
    int getStudentId()               { return this->StudentId ;     } 

};


//transaction class just to keep record of transaction
class Transaction {
    private :

    int *transactionType;
    Book *book;
    Student *student;

    public:

    Transaction(int *TransactionType,Book *book,Student *student){
        this->transactionType = TransactionType;
        this->student = student;
        this->book =book;
    }
    
    int* getTransactionType(){
        return this->transactionType;
    }
    Book* getBook(){
        return this->book;
    }
    Student* getStudent(){
        return this->student;
    }
};
//library class
class Library {
    private:

    vector<Student*> students;
    vector<Book*> books;
    vector<Transaction> transactions;

    void borrowBook(Book *book,Student *student){
        if( book->getCopiesAvailable() > 0){
            student->addBooks(book);
            book->setCopiesAvailable(book->getCopiesAvailable()-1);
        }
    }
    //to return and make changes required l=like copies+1 and remove book from student
    void returnBook(Book *book,Student *student){
        vector<Book*> b = student->getBorrowedBooks();
        for(auto i= b.begin(); i!=b.end();i++){
            if((*i)->getBookId() == book->getBookId()){
                student->deleteBook(book);
                book->setCopiesAvailable(book->getCopiesAvailable()+1);
                break;
            }
        }
    }
    
    //to add a newStudent
    public:

    void addStudent(Student student){
        Student *s = new Student(student.getStudentId(),student.getName());
        students.push_back(s);  
    }
    //to add a new book
    void addBooks(Book book){
        Book *b = new Book(book.getBookId(),book.getTitle(),book.getAuthor(),book.getCopiesAvailable());
        books.push_back(b);
    }
    //to borrow and make changes required like copies-1 and add book to student
    
    //transaction record
    void transaction(int transactionType,int bookID,int studentId){
        //getting the object with bookId and student Id
        Book *b = nullptr ;
        for(auto it = books.begin();it != books.end();it++){
            if((*it)->getBookId() == bookID){
                b = *it;
                break;
            }
        }
        Student *s = nullptr;
        for(auto it = students.begin();it != students.end();it++){
            if((*it)->getStudentId() == studentId ){
                s = *it;
                break;
            }
        }
        
        Transaction *t = new Transaction(&transactionType, b,s);
        //to borrow book
        if(*(t->getTransactionType()) == 1){
            borrowBook(t->getBook(), t->getStudent());
        }
        //to return a book
        else if(*(t->getTransactionType()) == 2){
            returnBook(t->getBook(),t->getStudent());
        }
        transactions.push_back(*t);
        
    }

    //to return a list of books
    
    vector<Book*> getBorrowedByStudent(Student student) {
        Student *s;
            for(auto it = students.begin();it != students.end();it++){
                if((*it)->getStudentId() == student.getStudentId() ){
                    s = *it;
                    break;
                }
            }
        //return a list of books borrowed by a student
        return s->getBorrowedBooks();
    }

    void display(){
        int n = students.size();
        //bubble sorting to sort student vector and books borrowed by them
        if(n > 1){
            for(int i = 0 ; i < n-1;i++){
                for(int j = 0 ; j < n-i-1;j++){
                    if(students[j]->getStudentId() > students[j+1]->getStudentId()){
                        Student *s = students[j];
                        students[j] = students[j+1];
                        students[j+1] = s;
                    }
                }
            }
        }
        for(int i = 0 ; i< n; i++){
            vector<Book*> b = getBorrowedByStudent(*students[i]);
            int nb = b.size();

            if(nb > 1){
                for(int i = 0 ; i < nb-1;i++){
                    for(int j = 0 ; j < nb-i-1;j++){
                        if(b[j]->getBookId() > b[j+1]->getBookId()){
                            Book* bo = b[j];
                            b[j] = b[j+1];
                            b[j+1] = bo;
                        }
                    }
                }
            }
            cout << "Books borrowed by student " << students[i]->getStudentId() << ":" << endl;;
            if(nb == 0){
                cout << "No books borrowed." << endl;
            }
            else{
                for(auto it = b.begin(); it != b.end();it++){
                    Book *b = *it;
                    cout << "Book ID: " << b->getBookId() << ", Title: " << b->getTitle()<<", Author: "<<b->getAuthor() << endl;;
                }
            }
        }    
    }          
};

int main(){
    
        //Library object
        Library library;
        //input 
        int N,M,Q;
        //ifstream inputFile("1.in");
        cin >> N >> M >> Q;
        cin.ignore();
        vector<Student> stud;
        //to add new books
        for(int i = 0 ; i < N ; i++){
            int bookId = 0;
            string title;
            string author;
            int quantity = 0;
            string s;
            string token;
            getline(cin, s);

            stringstream ss(s);
            string t;
            int j = 0;
            while(getline(ss,t,'"')){
                if(j == 0){
                    string idf = t;
                    string s_book = idf.substr(0,idf.length()-1);
                    bookId = stoi(s_book);
                    //cout << bookId ;
                }
                if(j == 1){
                    title = '"' + t + '"';
                    //cout << title;
                }
                if(j == 3){
                    author = '"' + t + '"';
                    //cout << author;
                }
                if(j == 4){
                    string idf = t;
                    string s_book = idf.substr(1,idf.length());
                    quantity = stoi(s_book);
                    //cout << quantity << endl;
                }
                if(j == 5){
                    //cout << "me " <<endl;
                    break;
                }
                j++;

            }

            // // Splitting the input string by double quotes
            // std::string st[5];
            // for (int i = 0; i < 5; ++i) {
            //     std::getline(ss, st[i], '\"');
            //     // cout << token << endl;
                
            // }

            // // Splitting the first and last parts by space
            // std::stringstream firstStream(st[0]);
            // std::stringstream lastStream(st[4]);

            // std::string firstToken;
            // std::string lastToken;

            // firstStream >> firstToken;
            // lastStream >> lastToken;
            // // cout <<  lastToken << endl;

            // // Converting string to integer
            // int bookId = std::stoi(firstToken);
            // int quantity = std::stoi(lastToken);
            
            // // Creating title and author strings
            //     std::string title = "\"" + st[1] + "\"";
            //     std::string author = "\"" + st[3] + "\"";
            // cout << bookId << " " << title << " " << author << " " << quantity << endl; 
            Book book(bookId,title,author,quantity);
            library.addBooks(book);
            
            }
            
        
        
        //to add new student
        for(int i = 0 ; i < M ; i++){
            int studentId = 0;
            string name;
            string s;
            getline(cin, s);

            stringstream ss(s);
            string t;
            int j = 0;
            while(getline(ss,t,'"')){
                if(j == 0){
                    string idf = t;
                    string s_book = idf.substr(0,idf.length()-1);
                    studentId = stoi(s_book);
                    //cout << bookId ;
                }
                if(j == 1){
                    name = '"' + t + '"';
                    //cout << title;
                }
                if(j == 2){
                    break;
                }
                j++;
            }
            //cin >> studentId >> name;
            Student student(studentId,name);
            stud.push_back(student);
            library.addStudent(student);
        
        }
        //to have various transactions
        for(int i = 0 ; i < Q ; i++ ){
            int typ;
            int bookId ;
            int studentId;
            cin >> typ >> bookId >> studentId ;
            library.transaction(typ, bookId, studentId);
        }
        //inputFile.close();       
        //to display the required output
        //library.display();
        int n = stud.size();
        //bubble sorting to sort student vector and books borrowed by them
        //if(n > 1){
            for(int i = 0 ; i < n-1;i++){
                for(int j = 0 ; j < n-i-1;j++){
                    if(stud[j].getStudentId() > stud[j+1].getStudentId()){
                        Student s = stud[j];
                        stud[j] = stud[j+1];
                        stud[j+1] = s;
                    }
                }
            }
        //}
        for(int i = 0 ; i< n; i++){
            vector<Book*> b = library.getBorrowedByStudent(stud[i]);
            int nb = b.size();

            //if(nb > 1){
                for(int i = 0 ; i < nb-1;i++){
                    for(int j = 0 ; j < nb-i-1;j++){
                        if(b[j]->getBookId() > b[j+1]->getBookId()){
                            Book* bo = b[j];
                            b[j] = b[j+1];
                            b[j+1] = bo;
                        }
                    }
                }
            //}
            cout << "Books borrowed by student " << stud[i].getStudentId() << ":" << endl;;
            if(nb == 0){
                cout << "No books borrowed." << endl;
            }
            else{
                for(auto it = b.begin(); it != b.end();it++){
                    Book *b = *it;
                    cout << "Book ID: " << b->getBookId() << ", Title: " << b->getTitle()<<", Author: "<<b->getAuthor() << endl;;
                }
            }
            

        } 
        cout << endl;
        return 0;
}
    