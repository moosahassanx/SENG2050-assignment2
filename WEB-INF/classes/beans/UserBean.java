package beans;

import java.io.Serializable;

public class UserBean implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String username;

    // constructors
    public UserBean() { }


    public UserBean(final String u) {
        this.username = u;
    }

    // mutator
    public void setUsername(final String u) {
        this.username = u;
    }

    // accessor
    public String getUsername(){
        return username;
    }
}