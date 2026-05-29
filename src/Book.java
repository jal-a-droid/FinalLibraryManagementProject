public abstract class Book {
    String author;
    String title;
    String date;
    boolean checkedOut;

    String[] book = {};


    public Book(String author, String title, String date){
        this.author = author;
        this.title = title;
        this.date = date;
        this.book = new String[]{author, title, date};

        checkedOut = false;
    }


}