package codingmentor.javabackend.k3.model;
import java.io.Serializable;
import java.util.Date;


/**
 * @author Thanh Vu
 *
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String email;
    private boolean admin;
    private String first_name;
    private String last_name;
    private String preferred_name;
    private String password_digest;
    private String reset_digest;
    private Date reset_expires;
    private boolean set_up;
    private boolean disabled;
    
    public String getFull_name() {
    	String full_name = first_name + " " + last_name;
    	if (!preferred_name.isEmpty()) {
    		full_name = first_name + " (" + preferred_name + ") " + last_name;
    	}
		return full_name;
	}
    
    
	public String getPreferred_first_name() {
		if (!this.set_up) {
    		return this.email;
    	} 
    	return (!preferred_name.isEmpty()) ? this.preferred_name : this.first_name;
	}



	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getPreferred_name() {
		return preferred_name;
	}

	public String getPassword_digest() {
		return password_digest;
	}

	public String getReset_digest() {
		return reset_digest;
	}

	public Date getReset_expires() {
		return reset_expires;
	}

	public boolean isSet_up() {
		return set_up;
	}

	public boolean isDisabled() {
		return disabled;
	}

    // fluent style api
	public User() {
	}
	
    public User id(int id) {
        this.id = id;
        return this;
    }
	
    public User email(String email) {
        this.email = email;
        return this;
    }
    
    public User admin(boolean admin) {
        this.admin = admin;
        return this;
    }
    
    public User first_name(String first_name) {
        this.first_name = first_name;
        return this;
    }
    
    public User last_name(String last_name) {
        this.last_name = last_name;
        return this;
    }
    
    public User preferred_name(String preferred_name) {
        this.preferred_name= preferred_name;
        return this;
    }
    
    
    public User password_digest(String password_digest) {
        this.password_digest= password_digest;
        return this;
    }
    
    public User reset_digest(String reset_digest) {
        this.reset_digest= reset_digest;
        return this;
    }
    
    public User set_up(boolean set_up) {
        this.set_up= set_up;
        return this;
    }
    
    public User disabled(boolean disabled) {
        this.disabled= disabled;
        return this;
    }
}



