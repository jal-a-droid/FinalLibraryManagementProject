import java.util.ArrayList;

public class Account {
    String username;
    String userID;
    String password;
    ArrayList<String> account;
    public Account(String user, String ID){
        this.username = user;
        this.userID = ID; //reference back to my Lua code to see how to create a random ID number
        account = new ArrayList<String>();
    }

    public void deleteAccount(){

    }

    public void setPassword(ArrayList<String> account, String password){
        account.add(4, password);
    }
}
