package codingmentor.javabackend.k3.model;

public class Courses {
	private String title;
	private String course_code;
	private int repo_id;
	private int department_id;
	private boolean active;
	
	public Courses() {}
	
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
}
