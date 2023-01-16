package codingmentor.javabackend.k3.model;
import java.util.Date;

public class Klasses {
	private int course_id;
	private int repo_id;
	private String semester;
	private int section;
	private Date start_date;
	private Date end_date;
	
	public Klasses() {}

	public int getCourse_id() {
		return course_id;
	}

	public int getRepo_id() {
		return repo_id;
	}

	public String getSemester() {
		return semester;
	}

	public int getSection() {
		return section;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}
}
