package codingmentor.javabackend.k3.model;

public class Grader {
	
	private int id;
	private int user_id;
	private int klass_id;
	private boolean notify_grader_assigned;
	
	public int getId() {
		return id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public int getKlass_id() {
		return klass_id;
	}
	
	public boolean isNotify_grader_assigned() {
		return notify_grader_assigned;
	}

	public Grader() {}
	
	/* Fluen Style API */
	public Grader id(int id) {
	    this.id = id;
	    return this;
	}
	
	public Grader user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public Grader klass_id(int klass_id) {
	    this.klass_id = klass_id;
	    return this;
	}
	
	public Grader notify_grader_assigned(boolean notify_grader_assigned) {
	    this.notify_grader_assigned = notify_grader_assigned;
	    return this;
	}
}
