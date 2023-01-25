package codingmentor.javabackend.k3.model;

public class Course {
	private int id;
	private String title;
	private String course_code;
	private int repo_id;
	private int department_id;
	private boolean active;
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCourse_code() {
		return course_code;
	}
	
	public int getRepo_id() {
		return repo_id;
	}
	
	public int getDepartment_id() {
		return department_id;
	}
	
	public boolean isActive() {
		return active;
	}
	
	
	/* Fluent Style API */
	public Course() {}
	
	public Course id(int id) {
	    this.id = id;
	    return this;
	}

	public Course course_code(String course_code) {
	    this.course_code = course_code;
	    return this;
	}
	
	public Course title(String title) {
	    this.title = title;
	    return this;
	}
	
	public Course repo_id(int repo_id) {
	    this.repo_id = repo_id;
	    return this;
	}
	
	public Course department_id(int department_id) {
	    this.department_id = department_id;
	    return this;
	}
	
	public Course active(boolean active) {
	    this.active = active;
	    return this;
	}
}
