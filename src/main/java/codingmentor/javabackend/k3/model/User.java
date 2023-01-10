package codingmentor.javabackend.k3.model;
import java.io.Serializable;
import java.util.Date;


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
    
    public User() {	
    }
    
	public User(String email, String password_digest) {
		this.email = email;
		this.password_digest = password_digest;
	}
	
	public User(String email, String password_digest, String first_name) {
		this.email = email;
		this.password_digest = password_digest;
		this.first_name = first_name;
	}
	
	public User(String email, boolean admin, String first_name, String last_name, String password_digest,
			boolean set_up) {
		this.email = email;
		this.admin = admin;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password_digest = password_digest;
		this.set_up = set_up;
	}

	
	public String getLast_name() {
		return last_name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isSet_up() {
		return set_up;
	}

	public boolean isDisabled() {
		return disabled;
	}


	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword_digest() {
		return password_digest;
	}
	
	public void setPassword_digest(String password_digest) {
		this.password_digest = password_digest;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

    // fluent style api
    public User email(String email) {
        this.email = email;
        return this;
    }
    
    public User first_name(String first_name) {
        this.first_name = first_name;
        return this;
    }
    
    public User admin(boolean admin) {
        this.admin = admin;
        return this;
    }
    
    public User password_digest(String password_digest) {
        this.password_digest= password_digest;
        return this;
    }
}



