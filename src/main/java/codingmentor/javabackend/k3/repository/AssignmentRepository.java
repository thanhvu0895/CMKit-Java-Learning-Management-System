package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Assignment;


public interface AssignmentRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Assignments
	 *
	 * @return a list of all Assignments if found or null
	 */
    List<Assignment> getAssignments();
    
    /**
	 * Get list of Assignments by CourseId
	 *
	 * @return a list of all Assignments if found or null
	 */
    List<Assignment> getAssignmentsByCourseId(int courseId);

    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * Get department by Assignment's id
     * @return Assignment if exists and null if not
     */
    Assignment getAssignmentById(int id);
    
	/*
	 * GET Check True/false METHOD
	 */
    
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	//POST(INSERT INTO)
    /**
     * Insert into Assignment when assignment type does not require creating a 
     * repository (like coding project repository) for pushing and pulling
     * @return new Assignment
     */
    int insertAssignment(String title, int course_id, int grade_category_id, int files_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link);
    
    
    /**
     * Insert into Asssignment when assignment type is Student Repo
     * for student pushing and pulling to the repository
     * @return new Assignment
     */
    int insertStudentRepoAssignment(String title, int course_id, int grade_category_id, int files_repo_id, int template_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link);
    
    
    
    
}