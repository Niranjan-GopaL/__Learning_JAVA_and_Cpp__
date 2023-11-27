public class LibraryTransaction
{
    //Attributes of the class 'LibraryTransaction'.
    private int TransactionType;
    private int bookID;
    private int studentID;

    //Default constructor for the class 'LibraryTransaction'.
    public LibraryTransaction()
    {
        TransactionType=-1;
        bookID=-1;
        studentID=-1;
    }

    //Parameterized constructor for the class 'LibraryTransaction'.
    public LibraryTransaction(int TransactionType,int bookID,int studentID)
    {
        this.TransactionType=TransactionType;
        this.bookID=bookID;
        this.studentID=studentID;
    }

    //Getters
    public int get_TransactionType()
    {
        return TransactionType;
    }
    public int get_bookID()
    {
        return bookID;
    }
    public int get_studentID()
    {
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
