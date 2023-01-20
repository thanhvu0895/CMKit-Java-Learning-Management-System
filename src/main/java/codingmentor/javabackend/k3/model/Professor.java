package codingmentor.javabackend.k3.model;

public class Professor {
	private int id;
	private int user_id;
	private int klass_id;
	
	public int getId() {
		return id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public int getKlass_id() {
		return klass_id;
	}

	public Professor() {}
	
	/* Fluen Style API */
	public Professor id(int id) {
	    this.id = id;
	    return this;
	}
	
	public Professor user_id(int user_id) {
	    this.user_id = user_id;
	    return this;
	}
	
	public Professor klass_id(int klass_id) {
	    this.klass_id = klass_id;
	    return this;
	}	
}
