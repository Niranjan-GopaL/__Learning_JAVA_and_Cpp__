public class Book 
{
    //Attributes of the class 'Book'.
    private int BookID;
    private String title;   
    private String author;
    private int copiesAvailable;

    //Default constructor of the class 'Book'.
    public Book()
    {
        copiesAvailable=0;
        BookID=0;
        title="";
        author="";
    }

    //Parameterized constructor of the class 'Book'.
    public Book(int BookID,String title,String author,int copiesAvailable)
    {
        this.BookID=BookID;
        this.title=title;
        this.author=author;
        this.copiesAvailable=copiesAvailable;
    }

    //Getters
    public int get_BookID()
    {
        return BookID;
    }
    public String get_title()
    {
        return title;
    }
    public String get_author()
    {
        return author;
    }
    public int get_copiesAvailable()
    {
        return copiesAvailable;
    }

    //Setters
    public void set_title(String title)
    {
        this.title=title;
    }
    public void set_author(String author)
    {
        this.author=author;
    }
    public void set_copiesAvailable(int number)
    {
        this.copiesAvailable=number;
    }
    public void set_BookID(int BookID)
    {
        this.BookID=BookID;
    }

    public void update_copiesAvailable(int num)
    {
        this.copiesAvailable=this.copiesAvailable+num;
    }
}
