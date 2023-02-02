package codingmentor.javabackend.k3.model;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;


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
    private LocalDateTime reset_expires;
    private boolean set_up;
    private boolean disabled;
    private boolean deleted;
    private int assigned_assignments;
    
    /**
     * Repository Functions
     */
    private PBKDF2Hasher hasher = new PBKDF2Hasher();  
	private UserRepository userRepository =  UserRepositoryImpl.getInstance();
	private DepartmentRepository departmentRepository =  DepartmentRepositoryImpl.getInstance();
	
	public List<Department> getDepartments() {
    	return departmentRepository.getDepartmentsByUserId(this.id);
	}
	
	public List<Department> getDepartmentsCheckAdmin() {
		
    	return isAdmin() ? departmentRepository.getDepartments() : departmentRepository.getDepartmentsByUserId(this.id);
	}
	
    public boolean isDepartmentProfessor() throws NoSuchAlgorithmException {
    	return userRepository.isDepartmentProfessor(this.id);
    }
        
	public boolean validateResetToken(String token) throws NoSuchAlgorithmException {
    	return (this.reset_digest != null) && LocalDateTime.now().isBefore(reset_expires) && this.reset_digest.equals(RandomUtils.SHA256Base64(token));
    }
    
	public User filterParams() {
		return new User()
			.id(this.id)
			.email(this.email)
			.admin(this.admin)
			.first_name(this.first_name)
			.last_name(this.last_name)
			.preferred_name(this.preferred_name)
			.set_up(this.set_up)
			.deleted(this.deleted)
			.disabled(this.disabled);
	}
	/**
	 * Authenticate User's password
	 */
	
	public boolean authenticate(String password) {
		return hasher.checkPassword(password.toCharArray(), this.password_digest);	
	}
	
	/**
     * 
     * @param token stored in user's password_digest
     * @return true if token is valid and false if token is invalid
     * @throws NoSuchAlgorithmException
     */
    public boolean validateInviteToken(String token) throws NoSuchAlgorithmException {
    	return (this.reset_digest != null) && this.reset_digest.equals(RandomUtils.SHA256Base64(token));
    }
    
    //#----- Name methods ------ #
    /**
     * @return Preferred first name of user
     */
	public String getPreferred_first_name() {
		if (!this.set_up) {
    		return this.email;
    	} 
    	return (this.preferred_name != null && !this.preferred_name.isEmpty()) ? this.preferred_name : this.first_name;
	}
	
    /**
     * @return Preferred first name and last name of user
     */
	public String getFull_preferred_name() {
		if (!this.set_up) {
    		return this.email;
    	} 
    	return getPreferred_first_name() + " " + last_name;
	}
	
	/**
	 * @return  Official first/last of user
	 */
    public String getFull_real_name() {
    	if (!this.set_up) {
    		return this.email;
    	} 
    	return first_name + " " + last_name;
	}
	
	
	/**
	 * @return  Official first/last name plus nickname
	 */
    public String getFull_name() {
    	if (!this.set_up) {
    		return this.email;
    	} 
    	String full_name = first_name + " " + last_name;
    	if (this.preferred_name != null && !preferred_name.isEmpty()) {
    		full_name = first_name + " (" + preferred_name + ") " + last_name;
    	}
		return full_name;
	}
	
	/**
	 * @return Official name in reverse
	 */

	public String getFull_real_name_reverse() {
		if (!this.set_up) {
    		return this.email;
    	} 
		
		return last_name+ ", " + first_name ;
	}
	
	/*
	 * Getters, Setters
	 */

    public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public int getId() {
		return id;
	}

	public int getAssigned_assignments() {
		return assigned_assignments;
	}

	public String getEmail() {
		return email.toLowerCase();
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

	public LocalDateTime getReset_expires() {
		return reset_expires;
	}

	public boolean isSet_up() {
		return set_up;
	}

	public boolean isDisabled() {
		return disabled;
	}

    public void setPassword_digest(String password_digest) {
		this.password_digest = password_digest;
	}
    

	// fluent style api
	public User() {
	}
	
    public User id(int id) {
        this.id = id;
        return this;
    }
    
    public User assigned_assignments(int assigned_assignments) {
        this.assigned_assignments = assigned_assignments;
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
    
    public User reset_expires(LocalDateTime reset_expires) {
        this.reset_expires = reset_expires;
        return this;
    }
    
    public User disabled(boolean disabled) {
        this.disabled= disabled;
        return this;
    }
    
    public User deleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}



