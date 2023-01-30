package codingmentor.javabackend.k3.model;

public class ReusableComment {
	private int id;
	private int problem_id;
	private String comment;
   
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
		
	public String getComment() {
		return comment;
	}
	
	// fluent style api
	public ReusableComment() {}
	
	public ReusableComment id(int id) {
		this.id = id;
		return this;
    }
	
	public ReusableComment problem_id(int problem_id) {
		this.problem_id = problem_id;
		return this;
    }
	
	public ReusableComment comment(String comment) {
		this.comment = comment;
		return this;
    }
}

