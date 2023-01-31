package codingmentor.javabackend.k3.model;

public class Contributor {
	private int id;
	private int user_id;
	private int submission_id;
	private boolean feedback_seen;
	
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
	
	
	public boolean isFeedback_seen() {
		return feedback_seen;
	}


	// fluent style api
	public Contributor() {}
	
	public Contributor id(int id) {
		this.id = id;
		return this;
    }
	
	public Contributor user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public Contributor submission_id(int submission_id) {
	    this.submission_id = submission_id;
	    return this;
	}
	
	public Contributor feedback_seen(boolean feedback_seen) {
	    this.feedback_seen = feedback_seen;
	    return this;
	}
}

