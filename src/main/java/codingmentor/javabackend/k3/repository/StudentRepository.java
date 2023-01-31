package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Student;


public interface StudentRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Students
	 * @category LIST
	 * @return a list of all Students if found or null
	 */
    List<Student> getStudents();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * @category ITEM
     * Get Student by Student's id
     * @return Student if exists and null if not
     */
    Student getStudentById(int id);
    
	/*
	 * GET Check True/false METHOD
	 */
    /**
     * @category CHECK
	 */
    

    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * @category POST
	 */
    int insertStudent (int user_id, int klass_id);
    /**
     * @category PATCH
	 */
    
    /**
	 * @category DELETE
     */
}