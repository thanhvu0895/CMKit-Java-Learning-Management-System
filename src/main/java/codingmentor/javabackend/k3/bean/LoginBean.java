package codingmentor.javabackend.k3.bean;
import java.io.Serializable;


public class LoginBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String email;
    private String password_digest;
	
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
}



