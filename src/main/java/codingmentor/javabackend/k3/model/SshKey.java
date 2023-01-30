package codingmentor.javabackend.k3.model;

public class SshKey {
	private int id;
	private String key;
	private int user_id;
	private String label;
	
   /**
    * Repository Functions
    */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public String getKey() {
		return key;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public String getLabel() {
		return label;
	}

	public SshKey() {}
	
	/* Fluen Style API */
	public SshKey id(int id) {
	    this.id = id;
	    return this;
	}
	
	public SshKey key(String key) {
	    this.key = key;
	    return this;
	}
	
	public SshKey user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public SshKey label(String label) {
	    this.label = label;
	    return this;
	}
}
