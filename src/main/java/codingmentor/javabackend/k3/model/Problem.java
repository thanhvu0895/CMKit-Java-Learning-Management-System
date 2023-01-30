package codingmentor.javabackend.k3.model;

public class Problem {
	private int id;
	private int assignment_id;
	private String title;
	private Double points;
	private int location;
	private String grader_notes;
	
   /**
    * Repository Functions
    */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public int getAssignment_id() {
		return assignment_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Double getPoints() {
		return points;
	}
	
	public int getLocation() {
		return location;
	}
	
	public String getGrader_notes() {
		return grader_notes;
	}
	
	/* Fluent Style API */
	
	public Problem() {}
	
	public Problem id(int id) {
		this.id = id;
		return this;
    }
	
	public Problem assignment_id(int assignment_id) {
		this.assignment_id = assignment_id;
		return this;
    }
	
	public Problem title(String title) {
		this.title = title;
		return this;
	}
	
	
	public Problem points(Double points) {
		this.points = points;
		return this;
	}
	
	public Problem grader_notes(String grader_notes) {
		this.grader_notes = grader_notes;
		return this;
	}
}
