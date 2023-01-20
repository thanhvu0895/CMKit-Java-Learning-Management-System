package codingmentor.javabackend.k3.model;

public class Department {
	private int id;
	private String title;
	private int repo_id;
	

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getRepo_id() {
		return repo_id;
	}

	// fluent style api
	public Department() {}
	
	public Department id(int id) {
		this.id = id;
		return this;
    }
	
	public Department title(String title) {
		this.title = title;
		return this;
	}
	
	public Department repo_id(int repo_id) {
		this.repo_id = repo_id;
		return this;
    }
}
