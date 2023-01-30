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
    int insertAssignment(String title, int course_id, Integer grade_category_id, int files_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link);
    
    
    /**
     * Insert into Asssignment when assignment type is Student Repo
     * for student pushing and pulling to the repository
     * @return new Assignment
     */
    int insertStudentRepoAssignment(String title, int course_id, Integer grade_category_id, int files_repo_id, int template_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link);
    
    //PATCH(UPDATE)
    /** Update Assignment's with Assignment'type being student files
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateStudentFileAssignmentById(String title, String description, Integer grade_category_id, int file_or_link,  String permitted_filetypes,  int file_limit, int assignmentId);
    
    /** Update other Assignment types
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateAssignmentById(String title, String description, Integer grade_category_id, int assignmentId);
    
    
    
}