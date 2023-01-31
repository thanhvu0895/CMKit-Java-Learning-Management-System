package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Department;


public interface DepartmentRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * @category LIST 
	 * Get list of Departments
	 *
	 * @return a list of all Departments if found or null
	 */
    List<Department> getDepartments();
    
    /**
	 * @category LIST
     * Get list of Department from Department Professor's user id
     * @param userId
     * @return
     */
    public List<Department> getDepartmentsByUserId(int userId);
    
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * @category ITEM
     * Get department by Department's id
     * @return Department if exists and null if not
     */
    Department getDepartmentById(int id);
    
    /**
     * @category ITEM 
     * Get Department by Course's id
     * @return Department if exists and null if not
     */
    Department getDepartmentByCourseId(int courseId);
    
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
     * @category CHECK
     * @return true if user is Admin of this department, otherwise false
     */
    public boolean isDepartmentAdmin(int userId, int departmentId);
    
    /**
     * @category CHECK
     * @return true if user is Professor of this department, otherwise false
     */
    public boolean isDepartmentProfessor(int userId, int departmentId);
    
    /**Check if Department already exists by Department's title
     * @category CHECK 
     * @param title
     * @return
     */
    public boolean existedByTitle(String title);
    
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * @category POST
     * Add a new Department to DB
     * @return newly inserted department id if inserted and -1 otherwise
     */
    int insertDepartment (String title, int repo_id);
    
	//PATCH(UPDATE)
    /** Update Department's title given department id and new title
     * @category PATCH
     * @param title
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateDepartmentTitleById(String title, int id);
    
    /**
	 * @category DELETE
     */
}