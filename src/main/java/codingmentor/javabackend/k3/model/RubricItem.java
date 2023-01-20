package codingmentor.javabackend.k3.model;

public class RubricItem {
	private int id;
	private int problem_id;
	private String title;
	private double points;
	private int location;

	public int getId() {
		return id;
	}

	public int getProblem_id() {
		return problem_id;
	}

	public String getTitle() {
		return title;
	}

	public double getPoints() {
		return points;
	}

	public int getLocation() {
		return location;
	}
	
	
	public RubricItem() {}
	
	
	/* Fluent Style API */
	
	public RubricItem id(int id) {
	    this.id = id;
	    return this;
	}
	
	public RubricItem problem_id(int problem_id) {
	    this.problem_id = problem_id;
	    return this;
	}
	
	public RubricItem title(String title) {
	    this.title = title;
	    return this;
	}
	
	public RubricItem points(double points) {
	    this.points = points;
	    return this;
	}
	
	public RubricItem location(int location) {
	    this.location = location;
	    return this;
	}
}
