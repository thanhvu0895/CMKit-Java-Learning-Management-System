package codingmentor.javabackend.k3.model;

public class Repo {
	int id;
	
   /**
    * Repository Functions
    */
	
	/*
	 * Getters, Setters
	 */
	
	public int getId() {
		return id;
	}
	
	/* Fluen Style API */
	public Repo() {}

	public Repo id(int id) {
	    this.id = id;
	    return this;
	}
}
