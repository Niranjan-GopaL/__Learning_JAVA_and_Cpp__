import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int bookID;
    private String title;
    private String author;
    private int availableCopies;

    public Book() {}

    public Book(int bookID, String title, String author, int availableCopies) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public int getBookID() { return bookID; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getCopiesAvailable() { return availableCopies; }

    public void setTitle(String newTitle) { this.title = newTitle; }
    public void setAuthor(String newAuthor) { this.author = newAuthor; }
    public void setBookID(int newBookID) { this.bookID = newBookID; }
    public void setCopiesAvailable(int newNumber) { this.availableCopies = newNumber; }
    public void updateCopiesAvailable(int num) { this.availableCopies += num; }
}

class LibraryTransaction {
    private int transactionType;
    private int bookID;
    private int studentID;

    public LibraryTransaction(int transactionType, int bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.transactionType = transactionType;
    }

    public int getTransactionType() { return transactionType; }
    public int getBookID() { return bookID; }
    public int getStudentID() { return studentID; }

    public void setTransactionType(int type) { this.transactionType = type; }
    public void setBookID(int bookID) { this.bookID = bookID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
}

class Library {
    private ArrayList<Book> booksList;
    private ArrayList<Student> studentsList;
    private ArrayList<LibraryTransaction> transactionsList;

    public Library() {
        transactionsList = new ArrayList<>();
        booksList = new ArrayList<>();
        studentsList = new ArrayList<>();
    }

    public ArrayList<Book> getBooksList() { return booksList; }
    public ArrayList<Student> getStudentsList() { return studentsList; }
    public ArrayList<LibraryTransaction> getTransactionsList() { return transactionsList; }

    public int findBookIndex(int givenBookID) {
        for (int bookIndex = 0; bookIndex < booksList.size(); bookIndex++)
            if (givenBookID == booksList.get(bookIndex).getBookID())
                return bookIndex;
        return -1;
    }

    public int findStudentIndex(int givenStudentID) {
        for (int studentIndex = 0; studentIndex < studentsList.size(); studentIndex++)
            if (givenStudentID == studentsList.get(studentIndex).getStudentID())
                return studentIndex;
        return -1;
    }

    public void addBookToLibrary(Book givenBook) {
        int givenBookIdx = findBookIndex(givenBook.getBookID());
        if (givenBookIdx != -1)
            booksList.get(givenBookIdx).updateCopiesAvailable(givenBook.getCopiesAvailable());
        else
            booksList.add(givenBook);
    }

    public int getAvailableCopies(int bookID) {
        int givenBookIdx = findBookIndex(bookID);
        if (givenBookIdx != -1)
            return booksList.get(givenBookIdx).getCopiesAvailable();
        return 0;
    }

    public void addStudentToLibrary(Student newStudent) {
        if (findStudentIndex(newStudent.getStudentID()) == -1) {
            for (int studentIdx = 0; studentIdx < studentsList.size(); studentIdx++) {
                if (newStudent.getStudentID() < studentsList.get(studentIdx).getStudentID()) {
                    studentsList.add(studentIdx, newStudent);
                    return;
                }
            }
        }
        studentsList.add(newStudent);
    }

    public void addTransaction(int transactionType, int bookID, int studentID) {
        LibraryTransaction transaction = new LibraryTransaction(transactionType, bookID, studentID);
        int bookIndex = findBookIndex(bookID);
        int studentIndex = findStudentIndex(studentID);
        if (bookIndex == -1 || studentIndex == -1) {
            return;
        }
        if (transactionType == 1) {
            if (booksList.get(bookIndex).getCopiesAvailable() >= 1) {
                int borrowSuccess = studentsList.get(studentIndex).addBorrowedBook(booksList.get(bookIndex));
                if (borrowSuccess == 1) {
                    booksList.get(bookIndex).updateCopiesAvailable(-1);
                }
            }
        } else if (transactionType == 
