package codingmentor.javabackend.k3.model;

public class PastKlass {
	private String name;
	private String code;
	private String semester;
	private int section;
	private double grade;
	
	public PastKlass(String name, String code, String semester, int section, double grade) {
		super();
		this.name = name;
		this.code = code;
		this.semester = semester;
		this.section = section;
		this.grade = grade;
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

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
}
