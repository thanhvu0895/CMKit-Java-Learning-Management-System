package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Student;


public interface StudentRepository {
    /**
	 * Get list of Students
	 *
	 * @return a list of all Students if found or null
	 */
    List<Student> getStudents();
    
    /**
     * Get department by Student's id
     * @return Student if exists and null if not
     */
    Student getStudentById(int id);
}