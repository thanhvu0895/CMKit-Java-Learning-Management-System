package codingmentor.javabackend.k3.model;
import java.time.LocalDate;

import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.ProfessorRepository;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.ProfessorRepositoryImpl;


public class Klass {
	private int id;
	private int course_id;
	private int repo_id;
	private String semester;
	private Integer section;
	private LocalDate start_date;
	private LocalDate end_date;
	
   /**
    * Repository Functions
    */
	private ProfessorRepository professorRepository =  ProfessorRepositoryImpl.getInstance();
	private DepartmentRepository departmentRepository =  DepartmentRepositoryImpl.getInstance();
	
	/*
	 * OTHER FUNCTIONS
	 */
	
	public boolean isKlassAdmin(User current_user) {
		Department department = departmentRepository.getDepartmentByCourseId(this.course_id);
		return professorRepository.isProfessorByUserId(current_user.getId()) || departmentRepository.isDepartmentAdmin(current_user.getId(), department.getId());
	}
	
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}
	
	public int getCourse_id() {
		return course_id;
	}

	public int getRepo_id() {
		return repo_id;
	}

	public String getSemester() {
		return semester;
	}

	public Integer getSection() {
		return section;
	}

	
	public LocalDate getStart_date() {
		return start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	/* Fluen Style API */
	public Klass() {}
	
	public Klass id(int id) {
	    this.id= id;
	    return this;
	}
	
	public Klass course_id(int course_id) {
	    this.course_id= course_id;
	    return this;
	}
	
	public Klass repo_id(int repo_id) {
	    this.repo_id = repo_id;
	    return this;
	}
	
	public Klass semester(String semester) {
	    this.semester = semester;
	    return this;
	}
	
	public Klass section(Integer section) {
	    this.section = section;
	    return this;
	}
	
	public Klass start_date(LocalDate start_date) {
	    this.start_date = start_date;
	    return this;
	}
	
	public Klass end_date(LocalDate end_date) {
	    this.end_date = end_date;
	    return this;
	}	
}
