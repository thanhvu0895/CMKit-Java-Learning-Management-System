package codingmentor.javabackend.k3.model;

public class GradeCategory {
	private int id;
	private String title;
	private int klass_id;
	private int course_id;
	private double weight;
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public int getKlass_id() {
		return klass_id;
	}
	
	public int getCourse_id() {
		return course_id;
	}
	
	public double getWeight() {
		return weight;
	}


	
	/* Fluent Style API */
	public GradeCategory() {}
	
	public GradeCategory id(int id) {
	    this.id = id;
	    return this;
	}
	
	public GradeCategory title(String title) {
	    this.title = title;
	    return this;
	}
	
	public GradeCategory klass_id(int klass_id) {
	    this.klass_id = klass_id;
	    return this;
	}
	
	public GradeCategory course_id(int course_id) {
	    this.course_id = course_id;
	    return this;
	}
	
	public GradeCategory weight(double weight) {
	    this.weight = weight;
	    return this;
	}
}
