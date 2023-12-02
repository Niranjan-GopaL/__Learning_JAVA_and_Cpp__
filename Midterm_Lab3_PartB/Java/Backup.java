package Java;
import java.util.ArrayList;
import java.util.Scanner;


// The relation between the classes 'Book' and 'Library' follows that of aggregation as a book can exist independently.
// The relation between the classes 'Library' and 'Student' also follows that of aggregation as a student can exist independently.
// The relation between the classes 'Library' and 'LibraryTransaction' follows that of composition as a transaction can happen only inside a library.





class Book {
    private int BookID;
    private String title;   
    private String author;
    private int copiesAvailable;

    public Book(){
        copiesAvailable=0;
        BookID=0;
        title="";
        author="";
    }

    public Book(int BookID,String title,String author,int copiesAvailable){
        this.BookID=BookID;
        this.title=title;
        this.author=author;
        this.copiesAvailable=copiesAvailable;
    }

    public int get_BookID()          { return BookID;          }
    public String get_title()        { return title;           }
    public String get_author()       { return author;          }
    public int get_copiesAvailable() { return copiesAvailable; }

    public void set_title(String title)         { this.title=title;                             }
    public void set_author(String author)       { this.author=author;                           }
    public void set_copiesAvailable(int number) {this.copiesAvailable=number;                   }
    public void set_BookID(int BookID)          {this.BookID=BookID;                            }
    public void update_copiesAvailable(int num) {this.copiesAvailable=this.copiesAvailable+num; }

}




class LibraryTransaction
{
    private int TransactionType;
    private int bookID;
    private int studentID;

    public LibraryTransaction(){
        TransactionType=-1;
        bookID=-1;
        studentID=-1;
    }


    public LibraryTransaction(int TransactionType,int bookID,int studentID){
        this.TransactionType=TransactionType;
        this.bookID=bookID;
        this.studentID=studentID;
    }


    //Getters
    public int get_TransactionType(){
        return TransactionType;
    }

    public int get_bookID(){
        return bookID;
    }

    public int get_studentID(){
        return studentID;
    }

    //Setters
    public void set_TransactionType(int type)
    {
        this.TransactionType=type;
    }
    public void set_bookID(int bookID)
    {
        this.bookID=bookID;
    }
    public void set_studentID(int studentID)
    {
        this.studentID=studentID;
    }
}


class Library 
{
    //Attributes of the class 'Library'.
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<LibraryTransaction> transactions;

    //Default constructor for the class 'Library'.
    public Library()
    {
        books=new ArrayList<Book>();
        students=new ArrayList<Student>();
        transactions=new ArrayList<LibraryTransaction>();
    }

    //Getters
    public ArrayList<Book> get_books()
    {
        return books;
    }
    public ArrayList<Student> get_students()
    {
        return students;
    }
    public ArrayList<LibraryTransaction> get_transactions()
    {
        return transactions;
    }

    //Method to find the index of a book with an id of 'id' in the list of the books in the library.
    public int find_bookID(int id)
    {
        int size=books.size();
        for(int i=0;i<size;i++)
        {
            if(id==books.get(i).get_BookID())                      //The given id matches that of the ith(0-indexed) book in the library, so we return i.
            {
                return i;
            }
        }
        //Since the book does not exist in the library, we return the index as -1.
        return -1;
    }

    //Method to find the index of a student with an id of 'id' in the list of students registered in the library.
    public int find_studentID(int id)
    {
        int size=students.size();
        for(int i=0;i<size;i++)
        {
            if(id==students.get(i).get_studentID())               //The given id matches that of the ith(0-indexed) student in the library, so we return i.
            {
                return i;
            }
        }
        //Since the student is not registered with the library, we return the index as -1.
        return -1;
    }

    //Method to add a book to the library. If the book already exists in the library, then the number of copies available are incremented. Else if the book doesn't already exist then the book is simply added to the library.
    public void add_book(Book book)
    {
        int b_index=find_bookID(book.get_BookID());
        if(b_index!=-1)
        {
            books.get(b_index).update_copiesAvailable(book.get_copiesAvailable());
        }
        else
        {
            books.add(book);
        }
    }

    //Method to get the number of copies available of the book with the id of 'bookID' in the list of books in library.
    public int get_number_of_copiesAvailable(int bookID)
    {
        int b_index=find_bookID(bookID);
        if(b_index!=-1)
        {
            return books.get(b_index).get_copiesAvailable();
        }
        //Returning 0 since the book doesn't exist in the library.
        return 0;
    }

    //Method to register a student with the given id, name. The student is registered only if there doesn't already exist a student with the given id. The students are registered in the library in ascending order of their id's.
    public void add_student(Student student)
    {
        int studentID=student.get_studentID();
        int s_index=find_studentID(studentID);
        if(s_index==-1)
        {
            int size=students.size();
            for(int i=0;i<size;i++)
            {
                if(studentID<students.get(i).get_studentID())
                {
                    // location=i;
                    students.add(i,student);           //Adds student at the index of the list which has a student with a greater id value.
                    return;
                }
            }
            students.add(student);                     //Adds the student at the end of the list since there is no student with an id value greater than the given student.
        }
    }

    //Method to add transaction to the list of transactions in the library.
    public void add_transaction(int TransactionType,int BookID,int studentID)
    {
        LibraryTransaction transaction=new LibraryTransaction(TransactionType,BookID,studentID);

        int b_index=find_bookID(BookID);
        int s_index=find_studentID(studentID);
        if(b_index==-1 || s_index==-1)                             //Returning if either a book with the given bookID doesn't exist or if there is no student with the given id that is registered with the library.
        {
            return;
        }

        if(TransactionType==1)                    //Borrowing a book.
        {
            if(books.get(b_index).get_copiesAvailable()>=1)
            {
                int borrow_success=students.get(s_index).add_borrowedBook(books.get(b_index));        //Adding the book to the list of the borrowed books of the student.
                if(borrow_success==1)
                {
                    books.get(b_index).update_copiesAvailable(-1);                     //Decrementing the number of available copies of the book by 1 only if the student hasn't already borrowed 3 or more books.
                }
            }
        }
        else if(TransactionType==2)               //Returning a book.
        {
            books.get(b_index).update_copiesAvailable(1);                         //Incrementing the number of available copies of the book by 1.
            students.get(s_index).return_book(books.get(b_index));                    //Removing the book from the list of borrowed books of the student.
        }
        
        transactions.add(transaction);                                                //Adding the transaction to the list of transactions of the library.
    }

    //Displaying all the books borrowed by every student registered with the library.
    public void display_students_borrowed_book()
    {
        int size=students.size();
        for(int i=0;i<size;i++)
        {
            System.out.println("Books borrowed by student " + students.get(i).get_studentID() + ":");
            ArrayList<Book> borrowed_books=students.get(i).getBooksBorrowedByStudent();
            int bb_size=borrowed_books.size();
            if(bb_size==0)
            {
                System.out.println("No books borrowed.");
                continue;
            }
            for(int j=0;j<bb_size;j++)
            {
                System.out.println("Book ID: " + borrowed_books.get(j).get_BookID()
                                    + ", Title: \"" + borrowed_books.get(j).get_title()
                                    + "\", Author: \"" + borrowed_books.get(j).get_author() + "\"");
            }
        }
        
    }
}

class Student 
{
    //Attributes of the 'class Student'.
    private int studentID;
    private String name;
    private ArrayList<Book> borrowedBooks;

    //Default constructor for the class 'Student'.
    public Student()
    {
        studentID=-1;
        name="";
        borrowedBooks=new ArrayList<Book>();
    }

    //Parameterized constructor for the class 'Student'.
    public Student(int studentID,String name)
    {
        this.studentID=studentID;
        this.name=name;
        this.borrowedBooks=new ArrayList<Book>();
    }

    //Getters
    public int get_studentID()
    {
        return studentID;
    }
    public String get_name()
    {
        return name;
    }
    public ArrayList<Book> getBooksBorrowedByStudent()
    {
        return borrowedBooks;
    }

    //Setters
    public void set_name(String name)
    {
        this.name=name;
    }
    public void set_studentID(int studentID)
    {
        this.studentID=studentID;
    }

    //Method to add a book to the list of the borrowed books. The maximum number of books that can be borrowed by any student is 3. This method returns 1 to indicate that the book was successfully borrowed and returns 0 to indicate that the book was not borrowed due to the maximum borrow capacity already being 3. The borrowed books are added in ascending order of their id's.
    public int add_borrowedBook(Book book)
    {
        int size=borrowedBooks.size();
        if(size<3)
        {
            int bookid=book.get_BookID();
            for(int i=0;i<size;i++)
            {
                if(bookid<borrowedBooks.get(i).get_BookID())
                {
                    borrowedBooks.add(i,book);                     //Book is added at the location of the list which has a book with a greater id value.
                    return 1;
                }
            }
            borrowedBooks.add(book);                               //Book is added to the end of the list since there is no book with an id greater than the given book.
            return 1;
        }
        return 0;
    }

    //Method to return a book. This removes the book from the list of borrowed books of the student.
    public void return_book(Book book)
    {
        int bookID=book.get_BookID();
        int size=borrowedBooks.size();
        int remove_index=-1;

        //Finding the index of the book to be removed in the list of the borrowed books.
        for(int i=0;i<size;i++)      
        {
            if(borrowedBooks.get(i).get_BookID()==bookID)
            {
                remove_index=i;
                break;
            }
        }

        //Removing the book from the list of the borrowed books only if the book exists in the list.
        if(remove_index!=-1)
        {
            borrowedBooks.remove(remove_index);
        }
        return;
    }
}


public class Backup
{
    public static void main(String args[])
    {
        Library lib=new Library();                         //Creating an object of class Library to manage books and library operations.

        Scanner sc=new Scanner(System.in);                 //Creating an object of Scanner class to get user input.
        
        //Getting user input for the number of books to be added to library, number of students to be registered to the library and the number of transactions taking place in the library respectively.
        int N=sc.nextInt();
        int M=sc.nextInt();
        int Q=sc.nextInt();
        sc.nextLine();                                     //A dummy input to clear the input buffer before taking the next line as string input.

        ArrayList<Book> books=new ArrayList<Book>();       //Creating a array list of books which are going to be added into the library.
        ArrayList<Student> students=new ArrayList<Student>();               //Creating an array list of student which are going to be registered with the library.

        //Getting the information of the 'N' books to be added to the library in the order: BookID "Title" "Author" CopiesAvailable.
        for(int i=0;i<N;i++)
        {
            //Taking the entire line as string input.
            String line=sc.nextLine();
            
            int first_space=line.indexOf(' ');                                                             //Finding the index of the first occurance of the space charcter.
            int bookID=Integer.parseInt(line.substring(0,first_space));                            //Converting from the 0th index of the input to the input to the first occurance of the space character into integer and storing it as bookID.
            line=line.substring(first_space+2);                                                               //Changing the string 'line' to it's substring from the occurance of the first '"' to the end of the string.

            first_space=line.indexOf('"');                                                                 //Finding the first occurance of the '"' in the updated line.
            String title=line.substring(0,first_space);                                            //Taking the substring from the 0th index to the character before the first occurance of '"' and assigning to be the title of the book.
            line=line.substring(first_space+3);                                                               //Changing the string 'line' to it's substring from the occurance of the second '"' to the end of the string.

            first_space=line.indexOf('"');                                                                 //Finding the first occurance of the '"' in the update line. 
            String author=line.substring(0,first_space);                                           //Taking the substring from the 0th index to the character just before the '"' and assigning it to be the author of the book.
            line=line.substring(first_space+2);                                                               //Changing the string 'line' to it's substring from the occurance of ' ' after the '"' to the end of the string.

            int CopiesAvailable=Integer.parseInt(line);                                                       //Simply converting the string 'line' into integer since the updated line would only have the last integer wihtout any extra spaces.

            Book book=new Book(bookID,title,author,CopiesAvailable);                                          //Creating a book with the info given.
            books.add(book);                                                                                  //Adding the book into the list of books in main.
            lib.add_book(book);                                                                               //Adding the same book into the library.
        }

        //Getting the information of the 'M' students to be registered into the library in the order: StudentID "Name".
        for(int i=0;i<M;i++)
        {
            //Taking the entire line as string input.
            String line=sc.nextLine();

            int first_space=line.indexOf(' ');                                                             //Finding the first occurance of the space character.
            int id=Integer.parseInt(line.substring(0,first_space));                                //Converting the substring of line into integer from index 0 to the index of the first occurance of space and assigning it to be the studentID.
            line=line.substring(first_space+2);                                                               //Changing 'line' to it's substring from the index of the character after the first '"' to the last but one character.

            String name=line.substring(0,line.length()-2);                                         //Assigning the updated line to be the name of the Student.
            
            Student student=new Student(id,name);
            students.add(student);                                                                            //Adding the newly created student to the list of the students in main.
            lib.add_student(student);                                                                         //Passing the information of the student onto the library to register the student.
        }

        //Getting the information of the 'Q' transactions being performed in the library.
        for(int i=0;i<Q;i++){
        //Taking the entire line as string input.
            String line=sc.nextLine();

            int first_space=line.indexOf(' ');                                                             //Finding the first occurance of the space in the string. 
            int transactionType=Integer.parseInt(line.substring(0,first_space));                   //Converting the first part of the string into integer and assigning it to be the TransactionType.
            line=line.substring(first_space+1);                                                               //Updating the line to be the substring from the character after first space to the end of the string.

            first_space=line.indexOf(' ');                                                                 //Finding the first occurance of the space in the updated string.
            int BookID=Integer.parseInt(line.substring(0,first_space));                            //Converting the first part of the string into integer and assigning it to be bookID.
            line=line.substring(first_space+1);                                                               //Updating the line to be the substring from the character after the first space to the end of the string.

            int studentID=Integer.parseInt(line);                                                             //Simply converting the entire string to integer as there are no additional space and assigning it to be the studentID.

            lib.add_transaction(transactionType,BookID,studentID);                                            //Adding the transaction to the library.
        }

        //Displaying the list of the borrowed books of all the students registered with the library.
        lib.display_students_borrowed_book();
    
        //Closing the scanner.
        sc.close();
    }
}

