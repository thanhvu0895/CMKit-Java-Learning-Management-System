package codingmentor.javabackend.k3.model;

public class DepartmentProfessor {
	private int id;
	private int user_id;
	private int department_id;
	private boolean admin;	

	public int getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public boolean isAdmin() {
		return admin;
	}
	
	/* Fluen Style API */
	public DepartmentProfessor() {}
	
	public DepartmentProfessor id(int id) {
	    this.id = id;
	    return this;
	}
	
	public DepartmentProfessor user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public DepartmentProfessor department_id(int department_id) {
	    this.department_id = department_id;
	    return this;
	}
	
	public DepartmentProfessor admin(boolean admin) {
	    this.admin = admin;
	    return this;
	}
}
