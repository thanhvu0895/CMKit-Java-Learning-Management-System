package codingmentor.javabackend.k3.model;
import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    public String toBooleanValueString(boolean bool) {
        return bool ? "1" : "0";
    }
    
    public boolean intToBooleanValue(int boolInt) {
        return (boolInt == 1) ? true : false;
    }
}



