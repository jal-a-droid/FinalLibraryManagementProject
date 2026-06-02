import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Create an initial admin account for logging in
        new Account(Constants.DEFAULT_ADMIN_USER, Constants.DEFAULT_ADMIN_PASS, "001");

        // Populate system with dummy data to test
        new Library("Ken", "Barbie", "1999");
        new Library("Neal", "Scythe", "2016");

        // Launch the graphical interface
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }
}