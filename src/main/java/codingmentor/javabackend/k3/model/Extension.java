package codingmentor.javabackend.k3.model;

import java.time.LocalDateTime;

public class Extension {
	private int id;
	private boolean allow_late_submissions;
	private LocalDateTime new_deadline;
	private boolean use_deadline_as_due_date;
	private boolean limit_resubmissions;
	private int resubmission_limit;
	private int allow_resubmissions;
	private int user_id;
	private int assigned_id;

	/**
     * Repository Functions
     */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}


	public boolean isAllow_late_submissions() {
		return allow_late_submissions;
	}


	public LocalDateTime getNew_deadline() {
		return new_deadline;
	}


	public boolean isUse_deadline_as_due_date() {
		return use_deadline_as_due_date;
	}


	public boolean getLimit_resubmissions() {
		return limit_resubmissions;
	}


	public int getResubmission_limit() {
		return resubmission_limit;
	}


	public int getAllow_resubmissions() {
		return allow_resubmissions;
	}


	public int getUser_id() {
		return user_id;
	}


	public int getAssigned_id() {
		return assigned_id;
	}
	
	
	// fluent style api
	public Extension() {}
	
	public Extension id(int id) {
		this.id = id;
		return this;
    }
	
	public Extension allow_late_submissions(boolean allow_late_submissions) {
	    this.allow_late_submissions = allow_late_submissions;
	    return this;
	}
	
	public Extension new_deadline(LocalDateTime new_deadline) {
	    this.new_deadline = new_deadline;
	    return this;
	}
	
	public Extension use_deadline_as_due_date(boolean use_deadline_as_due_date) {
	    this.use_deadline_as_due_date = use_deadline_as_due_date;
	    return this;
	}
	
	public Extension limit_resubmissions(boolean limit_resubmissions) {
	    this.limit_resubmissions = limit_resubmissions;
	    return this;
	}
	
	public Extension resubmission_limit(int resubmission_limit) {
	    this.resubmission_limit = resubmission_limit;
	    return this;
	}
	
	public Extension allow_resubmissions(int allow_resubmissions) {
	    this.allow_resubmissions = allow_resubmissions;
	    return this;
	}
	
	public Extension user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public Extension assigned_id(int assigned_id) {
	    this.assigned_id = assigned_id;
	    return this;
	}
}
