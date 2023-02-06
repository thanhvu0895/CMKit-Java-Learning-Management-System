package codingmentor.javabackend.k3.model;

public class AssignedGrader {
	private int id;
	private int user_id;
	private int assigned_id;
	
   /**
    * Repository Functions
    */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}
	
	public int getAssigned_id() {
		return assigned_id;
	}

	// fluent style api
	public AssignedGrader() {}
	
	public AssignedGrader id(int id) {
		this.id = id;
		return this;
    }
	
	public AssignedGrader user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public AssignedGrader assigned_id(int assigned_id) {
	    this.assigned_id = assigned_id;
	    return this;
	}
	
}

