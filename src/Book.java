public abstract class Book {
    String author;
    String title;
    String date;
    boolean checkedOut;
    String checkedOutBy; // Stores the Student ID

    String[] book;

    public Book(String author, String title, String date) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.checkedOut = false;
        this.checkedOutBy = "";
        this.book = new String[]{author, title, date, "Available", ""};
    }

    public void updateBookArray() {
        this.book = new String[]{author, title, date, checkedOut ? "Checked Out" : "Available", checkedOutBy};
    }
}