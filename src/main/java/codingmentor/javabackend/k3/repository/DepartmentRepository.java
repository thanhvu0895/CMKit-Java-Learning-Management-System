package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Department;


public interface DepartmentRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * 
	 * Get list of Departments
	 *
	 * @return a list of all Departments if found or null
	 */
    List<Department> getDepartments();
    
    /**
     * 
     * Get list of department from user id
     * @param userId
     * @return
     */
    public List<Department> getDepartmentsByUserId(int userId);
    
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * Get department by Department's id
     * @return Department if exists and null if not
     */
    Department getDepartmentById(int id);
    
    /**
     * Get Department by Course's id
     * @return Department if exists and null if not
     */
    Department getDepartmentByCourseId(int courseId);
    
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
     * if an user is department's admin
     * @param userId
     * @param departmentId
     * @return true if user is Admin of this department, otherwise false
     */
    public boolean isDepartmentAdmin(int userId, int departmentId);
    
    /**
     * 
     * @param userId
     * @param departmentId
     * @return true if user is Professor of this department, otherwise false
     */
    public boolean isDepartmentProfessor(int userId, int departmentId);
    
    /**Check if Department already exists by Department's title
     * 
     * @param title
     * @return
     */
    public boolean existedByTitle(String title);
    
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * Add a new Department to DB
     * @return newly inserted department id if inserted and -1 otherwise
     */
    int insertDepartment (String title, int repo_id);
    
	//PATCH(UPDATE)
    /** Update Department's title given department id and new title
     * 
     * @param title
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateDepartmentTitleById(String title, int id);

}