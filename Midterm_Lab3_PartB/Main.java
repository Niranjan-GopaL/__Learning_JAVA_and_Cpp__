import java.util.ArrayList;
import java.util.Scanner;




class Book {

    private int    bookID;
    private String title;   
    private String author;
    private int    availableCopies;

    public Book(){}

    public Book(int bookID,String title,String author,int availableCopies){
        this.bookID  = bookID;
        this.title   = title;
        this.author  = author;
        this.availableCopies = availableCopies;
    }

    public int    getBookID()          { return bookID;          }
    public String getTitle()           { return title;           }
    public String getAuthor()          { return author;          }
    public int    getCopiesAvailable() { return availableCopies; }

    public void setTitle(String newTitle)         { this.title  = newTitle;                          }
    public void setAuthor(String newAuthor)       { this.author = newAuthor;                         }
    public void setBookID(int newBookID)          { this.bookID = newBookID;                         }
    public void setCopiesAvailable(int newNumber) { this.availableCopies = newNumber;                }
    public void updateCopiesAvailable(int num)    { this.availableCopies = this.availableCopies+num; }

}




class LibraryTransaction {

    private int transactionType;
    private int bookID;
    private int studentID;

    public LibraryTransaction(int transactionType, int bookID, int studentID) {
        this.bookID    = bookID;
        this.studentID = studentID;
        this.transactionType = transactionType;
    }

    // Getters and Setters
    public int getTransactionType() { return transactionType; }
    public int getBookID()          { return bookID;          }
    public int getStudentID()       { return studentID;       }

    
    public void setTransactionType(int type) { this.transactionType = type; }
    public void setBookID(int bookID)        { this.bookID = bookID;        }
    public void setStudentID(int studentID)  { this.studentID = studentID;  }
}






class Library {

    private ArrayList<Book>  books;
    private ArrayList<Student> students;
    private ArrayList<LibraryTransaction> transactions;


    public Library() {
        transactions = new ArrayList<LibraryTransaction>();
        books        = new ArrayList<Book>();
        students     = new ArrayList<Student>();
    }


    public ArrayList<Book>    get_books()                   { return books;        }
    public ArrayList<Student> get_students()                { return students;     }
    public ArrayList<LibraryTransaction> get_transactions() { return transactions; }


    public int find_bookID(int givenBookID){
        for(int bookIdx = 0; bookIdx<books.size(); bookIdx++)
            if(givenBookID == books.get(bookIdx).getBookID())                      
                return bookIdx;
        return -1;
    }


    public int findStudentId(int givenStudentId){
        for(int studentIdx=0; studentIdx<students.size(); studentIdx++)
            if(givenStudentId==students.get(studentIdx).getStudentID())
                return studentIdx;
        return -1;
    }


    public void add_book(Book givenBook){
        int givenBookIdx = find_bookID(givenBook.getBookID());
        if(givenBookIdx!=-1)
            books.get(givenBookIdx).updateCopiesAvailable(givenBook.getCopiesAvailable());
        else
            books.add(givenBook);
        
    }


    public int getNumberOfCopiesAvailable(int bookID){
        int givenBookIdx=find_bookID(bookID);
        if(givenBookIdx!=-1)
            return books.get(givenBookIdx).getCopiesAvailable();
        return 0;
    }



    public void addStudent(Student newStudent){
        if( findStudentId(newStudent.getStudentID()) == -1){
            for(int studentIdx = 0; studentIdx < students.size(); studentIdx++){
                if(newStudent.getStudentID() < students.get(studentIdx).getStudentID()){
                    students.add(studentIdx,newStudent);           
                    return;
                }
        }}

        students.add(newStudent);                     
    }


    public void addTransaction(int transactionType,int bookID,int studentID){

        LibraryTransaction transaction = new LibraryTransaction(transactionType,bookID,studentID);

        int bookIndex = find_bookID(bookID);
        int studentIndex = findStudentId(studentID);
        if(bookIndex==-1 || studentIndex==-1)                            
            return;


        if(transactionType==1){
            if(books.get(bookIndex).getCopiesAvailable()>=1){
                int borrow_success=students.get(studentIndex).addBookBorrow(books.get(bookIndex));       
                if(borrow_success==1)
                    books.get(bookIndex).updateCopiesAvailable(-1);                  
            }
        }
        else if(transactionType==2)               
        {
            books.get(bookIndex).updateCopiesAvailable(1);                      
            students.get(studentIndex).return_book(books.get(bookIndex));                 
        }
        
        transactions.add(transaction);                                             
    }

   
    public void Output(){
        int size=students.size();
        for(int i=0;i<size;i++)
        {
            System.out.println("Books borrowed by student " + students.get(i).getStudentID() + ":");
            ArrayList<Book> borrowed_books=students.get(i).getBooksBorrowedByStudent();
            int bb_size=borrowed_books.size();

            if(bb_size==0){
                System.out.println("No books borrowed.");
                continue;
            }

            for(int j=0;j<bb_size;j++)
                System.out.println("Book ID: " + borrowed_books.get(j).getBookID() + ", Title: \"" + borrowed_books.get(j).getTitle() + "\", Author: \"" + borrowed_books.get(j).getAuthor() + "\"");
            
        }
        
    }   
}






class Student 
{
    private int studentID;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public Student()
    {
        studentID=-1;
        name="";
        borrowedBooks=new ArrayList<Book>();
    }


    public Student(int studentID,String name){
        this.studentID=studentID;
        this.name=name;
        this.borrowedBooks=new ArrayList<Book>();
    }


    public int getStudentID() { return studentID; }
    public String getName()   { return name;      }
    public ArrayList<Book> getBooksBorrowedByStudent() { return borrowedBooks; }


    public void setName(String newNmae)        { this.name = newNmae; }
    public void setStudentID(int newStudentId) { this.studentID = newStudentId; }
    

    public int addBookBorrow(Book bookToBorrow){
        if(borrowedBooks.size()<3){
            int bookid=bookToBorrow.getBookID();

            for(int i=0;i<borrowedBooks.size();i++){
                if(bookid<borrowedBooks.get(i).getBookID()){
                    borrowedBooks.add(i,bookToBorrow);                     
                    return 1;
            }}
            
            borrowedBooks.add(bookToBorrow);                               

            return 1;
        }

        return 0;
    }



    public void return_book(Book bookToreturn){

        int bookID=bookToreturn.getBookID();
        int indexToRemove=-1;
       
        for(int i=0;i<borrowedBooks.size();i++){
            if(borrowedBooks.get(i).getBookID()==bookID){
                indexToRemove=i;
                break;
            }
        }

        
        if(indexToRemove != -1)
            borrowedBooks.remove(indexToRemove);
        
        return;
    }
}




public class Main
{
    public static void main(String args[])
    {
        Library library01 = new Library();                        

        
        Scanner sc = new Scanner(System.in);                
        

        int numBooks = sc.nextInt();
        int numStudents = sc.nextInt();
        int numTransactions = sc.nextInt();
        sc.nextLine();                                   

        ArrayList<Book> books = new ArrayList<Book>();     
        ArrayList<Student> students = new ArrayList<Student>();         

       
        for(int i=0;i<numBooks;i++){
            
            String line=sc.nextLine();
            
            int first_space = line.indexOf(' ');                                                    
            int bookID = Integer.parseInt(line.substring(0,first_space));                           
            line = line.substring(first_space+2);                                                  

            first_space = line.indexOf('"');                                                     
            String title = line.substring(0,first_space);                                        
            line = line.substring(first_space+3);                                                 

            first_space = line.indexOf('"');                                                      
            String author = line.substring(0,first_space);                                        
            line = line.substring(first_space+2);                                                              

            int CopiesAvailable = Integer.parseInt(line);                                                     

            Book book = new Book(bookID,title,author,CopiesAvailable);                                      
            books.add(book);                                                                             
            library01.add_book(book);                                                                          
        }

        
        for(int i=0;i<numStudents;i++) {
            String line=sc.nextLine();

            int first_space=line.indexOf(' ');                                                 
            int id=Integer.parseInt(line.substring(0,first_space));                            
            line=line.substring(first_space+2);                                                

            String name=line.substring(0,line.length()-2);                                     
            
            Student student=new Student(id,name);
            students.add(student);                                                                      
            library01.addStudent(student);                                                                  
        }


        for(int i=0;i<numTransactions;i++){
            String line=sc.nextLine();

            int first_space=line.indexOf(' ');                                               
            int transactionType=Integer.parseInt(line.substring(0,first_space));             
            line=line.substring(first_space+1);                                              

            first_space=line.indexOf(' ');                                                   
            int bookID=Integer.parseInt(line.substring(0,first_space));                      
            line=line.substring(first_space+1);                                              

            int studentID=Integer.parseInt(line);                                            

            library01.addTransaction(transactionType,bookID,studentID);                            
        }

        library01.Output();
    
        sc.close();
    }
}