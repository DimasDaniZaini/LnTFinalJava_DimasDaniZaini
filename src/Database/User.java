package Database;

public class User {

    private final String username, password, message, expire;

    public User(String username, String password, String message, String expire) {
        this.username = username;
        this.password = password;
        this.message= message;
        this.expire= expire;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpire() {
        return expire;
    }

    public String getMessage() {
        return message;
    }

}
