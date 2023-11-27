import java.util.ArrayList;

public class Student 
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
