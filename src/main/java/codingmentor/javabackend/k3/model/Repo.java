package codingmentor.javabackend.k3.model;

public class Repo {
	int id;

	public int getId() {
		return id;
	}

	public Repo() {}
	
	/* Fluen Style API */
	
	public Repo id(int id) {
	    this.id = id;
	    return this;
	}
}
