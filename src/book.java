public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable = true;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ") - " + (isAvailable ? "Available" : "Borrowed");
    }
}
