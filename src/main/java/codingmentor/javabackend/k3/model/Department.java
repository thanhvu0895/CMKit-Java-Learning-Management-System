package codingmentor.javabackend.k3.model;
import java.util.List;

import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;

public class Department {
	private int id;
	private String title;
	private int repo_id;
	
   /**
    * Repository Functions
    */
	private DepartmentRepository departmentRepository =  DepartmentRepositoryImpl.getInstance();
	private CourseRepository courseRepository =  CourseRepositoryImpl.getInstance();
	
	public List<Course> getCourses() {
		return courseRepository.getCoursesByDepartmentId(this.id);
	} 
			
	public boolean isDepartmentAdmin(User user) {
		return departmentRepository.isDepartmentAdmin(user.getId(), this.id) || user.isAdmin();
	}
	
	public boolean isDepartmentProfessor(User user) {
		return departmentRepository.isDepartmentProfessor(user.getId(), this.id) || user.isAdmin();
	}
	
	/*
	 * Getters, Setters
	 */
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getRepo_id() {
		return repo_id;
	}

	// fluent style api
	public Department() {}
	
	public Department id(int id) {
		this.id = id;
		return this;
    }
	
	public Department title(String title) {
		this.title = title;
		return this;
	}
	
	public Department repo_id(int repo_id) {
		this.repo_id = repo_id;
		return this;
    }
}
