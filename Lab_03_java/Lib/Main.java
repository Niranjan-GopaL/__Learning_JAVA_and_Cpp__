import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;




/*  Usage of Aggregation design in this code :-
  
  Aggregation is used to represent the relationship between the Library (whole) 
  and its Book objects (parts). The Library class contains a collection of Book objects, 
  and it manages and interacts with the Books but doesn't control their lifecycle entirely.
  
 */



 
class Book {

    private String  title;
    private String  author;
    private String  ISBN;
    private boolean available;

    public Book(String title, String author, String ISBN) {
        this.title     = title;
        this.author    = author;
        this.ISBN      = ISBN;
        this.available = true; 
    }

    // getters
    public String getTitle()      { return this.title;     }
    public String getAuthor()     { return this.author;    }
    public String getISBN()       { return this.ISBN;      }
    public boolean getAvailable() { return this.available; }
    
    
    // setters
    public void setTitle(String newTitle)       { this.title      = newTitle;  }
    public void setAuthor(String newAuthor)     { this.author     = newAuthor; }
    public void setISBN(String isbn)            { this.ISBN       = isbn;      }
    public void setAvailable(boolean available) { this.available  = available; }

}


// All object of Book type are managed and organised in Class Library
// We use a HashMap for O(1) addition and removal of books
class Library {

    // Use ISBN as the key to map to Book objects --> bookMaps
    private Map<String, Book> bookMap;  

    public Library() {
        this.bookMap = new HashMap<>();
    }

    
    // inserting to a Hashmap, key = isbn
    public void addBook(Book book)          { this.bookMap.put(book.getISBN(), book); }
    // removing from Hashmap
    public void removeBook(String ISBN)     { this.bookMap.remove(ISBN); }
    // In O(1)
    public Book findBookByISBN(String ISBN) { return bookMap.get(ISBN); }


    public ArrayList<String> displayAvailableBooks() {

        ArrayList<String> outputList = new ArrayList<>();

        outputList.add("Books:");
        for (Book book : this.bookMap.values()) 
            if (book.getAvailable()) 
                outputList.add(book.getTitle() + "\n" + book.getAuthor() + "\n" +  book.getISBN());

        return outputList;
    }
}


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
 -----------------------------------------------------------------------*/


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num    = sc.nextInt();

        // all output will be written to this ;
        // finally we'll display all the output together
        ArrayList<String> output = new ArrayList<String>();
        Library library          = new Library();

        while (num-- != 0) {
            
            int choice = sc.nextInt();
            
            switch (choice) {
                
                // Adding books
                case 1:
                    int numOfBooksOfAdded = sc.nextInt();
                    // take the \n from the buffer
                    String __             = sc.nextLine();
                    output.add("Books added:");
                    
                    while (numOfBooksOfAdded-- != 0) {
                        String name   = sc.nextLine();
                        String author = sc.nextLine();
                        String isbn_s = sc.nextLine();
                        Book book1    = new Book(name, author,isbn_s);
                        library.addBook(book1);
                        
                        output.add(isbn_s);
                        System.out.println("REACEHD HERE");
                    }
                    break;
                    



                case 2:
                    int numOfBooksToRemove = sc.nextInt();
                    __                     = sc.nextLine();
                    output.add("Books removed:");
                    
                    while (numOfBooksToRemove-- != 0) {
                        String isbn_ = sc.nextLine();
                        library.removeBook(isbn_);

                        output.add(isbn_);
                        
                        System.out.println("REACEHD HERE");
                    }
                    
                    break;

                    


                case 3:
                ArrayList<String> returned_out = library.displayAvailableBooks();                    
                output.addAll(returned_out);
                        System.out.println("REACEHD HERE");

                
                break;
                
            }
            
        }
        for (String string : output)System.out.println(string);
        sc.close();
     

    }
}


