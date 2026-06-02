import java.util.ArrayList;

public class Library extends Book {
    // Changed to store Library objects so we can easily track state and modify data
    static ArrayList<Library> libraryList = new ArrayList<>();

    public Library(String author, String title, String date) {
        super(author, title, date);
        libraryList.add(this);
    }

    public static void readAll() {
        for (Library lib : libraryList) {
            for (String s : lib.book) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public static int find(String titleSearch) {
        for (int i = 0; i < libraryList.size(); i++) {
            if (libraryList.get(i).title.equalsIgnoreCase(titleSearch)) {
                return i;
            }
        }
        return -1; // -1 indicates not found instead of a massive number
    }

    public static void modify(int index, String newAuthor, String newTitle, String newDate) {
        if (index >= 0 && index < libraryList.size()) {
            Library b = libraryList.get(index);
            b.author = newAuthor;
            b.title = newTitle;
            b.date = newDate;
            b.updateBookArray();
        }
    }

    public static boolean checkOutBook(String title, String studentName, String studentID) {
        int index = find(title);
        if (index != -1) {
            Library b = libraryList.get(index);
            if (!b.checkedOut) {
                b.checkedOut = true;
                b.checkedOutBy = studentName + " (" + studentID + ")";
                b.updateBookArray();
                return true;
            }
        }
        return false;
    }

    public static boolean checkInBook(String title) {
        int index = find(title);
        if (index != -1) {
            Library b = libraryList.get(index);
            if (b.checkedOut) {
                b.checkedOut = false;
                b.checkedOutBy = "";
                b.updateBookArray();
                return true;
            }
        }
        return false;
    }

    public static void read(int index) {
        if (index >= 0 && index < libraryList.size()) {
            for (String found : libraryList.get(index).book) {
                System.out.print(found + " ");
            }
            System.out.println();
        }
    }

    public static void destroy(int index) {
        if (index >= 0 && index < libraryList.size()) {
            libraryList.remove(index);
        }
    }
}