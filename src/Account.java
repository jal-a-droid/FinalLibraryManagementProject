import java.util.ArrayList;

public class Account {
    String username;
    String userID;
    String password;
    ArrayList<String> accountDetails;

    static ArrayList<Account> systemAccounts = new ArrayList<>();

    public Account(String user, String password, String ID) {
        this.username = user;
        this.password = password;
        this.userID = ID;
        this.accountDetails = new ArrayList<>();
        systemAccounts.add(this);
    }

    public static Account authenticate(String user, String pass) {
        for (Account acc : systemAccounts) {
            if (acc.username.equals(user) && acc.password.equals(pass)) {
                return acc;
            }
        }
        return null;
    }

    public void deleteAccount() {
        systemAccounts.remove(this);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}