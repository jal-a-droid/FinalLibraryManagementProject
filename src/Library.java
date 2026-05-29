import java.util.ArrayList;

public class Library extends Book {
    static ArrayList<String[]> library = new ArrayList<String[]>();

    public Library(String author, String title, String date) {
        super(author, title, date);
        library.add(book);
    }

    public static void readAll() {
        for (String[] temp : library) {
            for (String s : temp) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public static int find(String search) {
        for (int i = 0; i < library.size(); i++) {
            String[] temp = library.get(i);
            for (String s : temp) {
                if (s.equals(search)) {
                    return i;
                }
            }

        }

        return 100000000;
}

    public static void read(int index){
    String[] temp = library.get(index);
        for (String found : temp) {
        System.out.print(found + " ");
    }
}

    public static void destroy(int index) {
        library.remove(index);
    }
}



