package codingmentor.javabackend.k3.model;

import java.util.List;

import codingmentor.javabackend.k3.repository.impl.AssignmentRepository;

public class Course {
	private int id;
	private String title;
	private String course_code;
	private int repo_id;
	private int department_id;
	private boolean active;
	private int student_count;
	
	
   /**
    * Repository Functions
    */
	private AssignmentRepository assignmentRepository =  AssignmentRepository.getInstance();
	
	
	public List<Assignment> getAssignments() {
		return assignmentRepository.getAssignmentsByCourseId(this.id);
	}
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public int getStudent_count() {
		return student_count;
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
	
	public Course student_count(int student_count) {
	    this.student_count = student_count;
	    return this;
	}
}
