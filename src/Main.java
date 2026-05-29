public class Main {
    public static void main(String[] args){
     Library first = new Library("ken","barbie","1999");
     Library second = new Library("niel","scythe","2016");

        Library.readAll();
        Library.destroy(Library.find("1999"));
        System.out.println("\n\n\n\n\n\n\n");
        Library.readAll();
    }
}
