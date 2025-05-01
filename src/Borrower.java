class Borrower {
    private String name;
    private int StudentId;





    public Borrower(String name, int StudentId) {
        this.name = name;
        this.StudentId = StudentId;
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return StudentId;
    }

    import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private String name;
    private int studentId;
    private List<Book> borrowedBooks = new ArrayList<>();

    private static List<Borrower> allBorrowers = new ArrayList<>();

    public Borrower(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrow();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public static void addBorrower(Borrower b) {
        allBorrowers.add(b);
    }

    public static List<Borrower> getAllBorrowers() {
        return allBorrowers;
    }

    public int getStudentId() {
        return studentId;
    }

    public String toString() {
        return name + " (ID: " + studentId + ")";
    }
}

}