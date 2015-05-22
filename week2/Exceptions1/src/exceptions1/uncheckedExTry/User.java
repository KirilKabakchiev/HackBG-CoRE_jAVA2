package exceptions1.uncheckedExTry;

public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String _username) {
        if (_username == null) {
            username = "";
        } else {
            username = _username;
        }
    }

    public User(String username) {
        setUsername(username);
    }
    
    public User(){
        setUsername("");
    }

}
