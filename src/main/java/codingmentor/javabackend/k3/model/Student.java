package codingmentor.javabackend.k3.model;

public class Student {
	private int id;
	private int user_id;
	private int klass_id;
	private boolean notify_assignment_assigned;
	private boolean notify_graded;
	private boolean notify_contributor_invite;
	private boolean notify_extension;
	private boolean notify_regrade_response;
   
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
	
	public int getKlass_id() {
		return klass_id;
	}
	
	public boolean isNotify_assignment_assigned() {
		return notify_assignment_assigned;
	}
	
	public boolean isNotify_graded() {
		return notify_graded;
	}
	
	public boolean isNotify_contributor_invite() {
		return notify_contributor_invite;
	}
	
	public boolean isNotify_extension() {
		return notify_extension;
	}
	
	public boolean isNotify_regrade_response() {
		return notify_regrade_response;
	}

	public Student() {}
	
	/* Fluen Style API */
	public Student id(int id) {
	    this.id = id;
	    return this;
	}
	
	public Student user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public Student klass_id(int klass_id) {
	    this.klass_id = klass_id;
	    return this;
	}
	
	public Student notify_assignment_assigned(boolean notify_assignment_assigned) {
	    this.notify_assignment_assigned = notify_assignment_assigned;
	    return this;
	}
	
	public Student notify_graded(boolean notify_graded) {
	    this.notify_graded = notify_graded;
	    return this;
	}

	public Student notify_contributor_invite(boolean notify_contributor_invite) {
	    this.notify_contributor_invite = notify_contributor_invite;
	    return this;
	}
	
	public Student notify_extension(boolean notify_extension) {
	    this.notify_extension = notify_extension;
	    return this;
	}
	
	public Student notify_regrade_response(boolean notify_regrade_response) {
	    this.notify_regrade_response = notify_regrade_response;
	    return this;
	}
}
