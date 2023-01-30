package codingmentor.javabackend.k3.model;

public class GradedProblem {
	private int id;
	private int problem_id;
	private int submission_id;
	private String comments;
	private double point_adjustment;
	private int grader_id;
	
	/**
     * Repository Functions
     */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}

	public int getProblem_id() {
		return problem_id;
	}

	public int getSubmission_id() {
		return submission_id;
	}

	public String getComments() {
		return comments;
	}

	public double getPoint_adjustment() {
		return point_adjustment;
	}

	public int getGrader_id() {
		return grader_id;
	}
	
	// fluent style api
	public GradedProblem() {}
	
	public GradedProblem id(int id) {
		this.id = id;
		return this;
    }
	
	public GradedProblem problem_id(int problem_id) {
	    this.problem_id = problem_id;
	    return this;
	}
	
	public GradedProblem submission_id(int submission_id) {
	    this.submission_id = submission_id;
	    return this;
	}
	
	public GradedProblem comments(String comments) {
	    this.comments = comments;
	    return this;
	}
	
	public GradedProblem point_adjustment(double point_adjustment) {
	    this.point_adjustment = point_adjustment;
	    return this;
	}
	
	public GradedProblem grader_id(int grader_id) {
	    this.grader_id = grader_id;
	    return this;
	}
}

