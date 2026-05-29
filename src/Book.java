import java.util.ArrayList;
//arraylists can be multidimensional right
//what if the arraylist has a dimension for author, for title, and for date
//and you can sort by a specific one??
//you could put them in order with like a for loop because
//i think you can get the object containing a value form its value or something
//idk i have to ask mr bludland
//this is just temporary, we can always change the structure;
// we don't have time to think about everthing right now
public abstract class Book { //I think this will eventually have to be abstract
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
//is this thing on i think my crap broke or whatever
//jalen can yo see this