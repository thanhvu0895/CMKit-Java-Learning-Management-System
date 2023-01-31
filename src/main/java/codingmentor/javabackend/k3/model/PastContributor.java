package codingmentor.javabackend.k3.model;

public class PastContributor {
	private int id;
	private int user_id;
	private int submission_id;
	
	/**
     * Repository Functions
     */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public int getSubmission_id() {
		return submission_id;
	}

	// fluent style api
	public PastContributor() {}
	
	public PastContributor id(int id) {
		this.id = id;
		return this;
    }
	
	public PastContributor user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public PastContributor submission_id(int submission_id) {
	    this.submission_id = submission_id;
	    return this;
	}
}
