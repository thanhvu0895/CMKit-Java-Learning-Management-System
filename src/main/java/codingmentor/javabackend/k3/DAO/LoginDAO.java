package codingmentor.javabackend.k3.DAO;
import java.io.Serializable;


public class LoginDAO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String email;
    private String password_digest;
    private String first_name;
	
	public LoginDAO(String email, String password_digest) {
		super();
		this.email = email;
		this.password_digest = password_digest;
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
}



