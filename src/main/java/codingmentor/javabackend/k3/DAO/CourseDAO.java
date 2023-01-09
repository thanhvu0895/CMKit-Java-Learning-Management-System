package codingmentor.javabackend.k3.DAO;

public class CourseDAO {
	private String name;
	private String code;
	private String semester;
	private int section;
	private double progress;
	
	public CourseDAO(String name, String code, String semester, int section, double progress) {
		super();
		this.name = name;
		this.code = code;
		this.semester = semester;
		this.section = section;
		this.progress = progress;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	
}
