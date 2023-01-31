package codingmentor.javabackend.k3.model;

public class RegradeRequest {
	private int id;
	private int submission_id;
	private String reason;
	private boolean closed;
	private String response;
	private int requested_by_id;
	private int closed_by_id;

   /**
    * Repository Functions
    */
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}

	public int getSubmission_id() {
		return submission_id;
	}
	
	public String getReason() {
		return reason;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public String getResponse() {
		return response;
	}
	
	public int getRequested_by_id() {
		return requested_by_id;
	}
	
	public int getClosed_by_id() {
		return closed_by_id;
	}
	
	
	// fluent style api
	public RegradeRequest() {}
	
	public RegradeRequest id(int id) {
		this.id = id;
		return this;
    }
	
	public RegradeRequest submission_id(int submission_id) {
	    this.submission_id = submission_id;
	    return this;
	}
	
	public RegradeRequest reason(String reason) {
	    this.reason = reason;
	    return this;
	}
	
	public RegradeRequest closed(boolean closed) {
	    this.closed = closed;
	    return this;
	}
	
	public RegradeRequest response(String response) {
	    this.response = response;
	    return this;
	}
	
	public RegradeRequest requested_by_id(int requested_by_id) {
	    this.requested_by_id = requested_by_id;
	    return this;
	}
	
	public RegradeRequest closed_by_id(int closed_by_id) {
	    this.closed_by_id = closed_by_id;
	    return this;
	}
	
}
