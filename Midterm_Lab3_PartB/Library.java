import java.util.ArrayList;

public class Library 
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
