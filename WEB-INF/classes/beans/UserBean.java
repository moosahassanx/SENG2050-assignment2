package beans;

public class UserBean{
    private String username;
    private String password;

    public UserBean() {
    }

    public UserBean(String usernameFeed, String passwordFeed) {
        this.username = usernameFeed;
        this.password = passwordFeed;
    }

    // accessors
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    //mutators
    public void setUsername(String usernameFeed){
        this.username = usernameFeed;
    }

    public void setPassword(String passwordFeed){
        this.password = passwordFeed;
    }
}