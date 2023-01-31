package codingmentor.javabackend.k3.model;

public class ContributorInvite {
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
	public ContributorInvite() {}
	
	public ContributorInvite id(int id) {
		this.id = id;
		return this;
    }
	
	public ContributorInvite user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public ContributorInvite submission_id(int submission_id) {
	    this.submission_id = submission_id;
	    return this;
	}
}
