package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Department;


public interface DepartmentRepository {
    /**
	 * Get list of Departments
	 *
	 * @return a list of all Departments if found or null
	 */
    List<Department> getDepartments();
    
    /**
     * Get department by Department's id
     * @return Department if exists and null if not
     */
    Department getDepartmentById(int id);
    
    /**
     * Add a new Department to DB
     * @return newly inserted department id if inserted and -1 otherwise
     */
    int insertDepartment (String title, int repo_id);
    
    
    /**Check if Department already exists by Department's title
     * 
     * @param title
     * @return
     */
    public boolean existedByTitle(String title);
    
    /**Check if Department already exists by Department's title
     * 
     * @param title
     * @return
     */
    public boolean updateDepartmentTitleById(String title, int id);
    
    public List<Department> getDepartmentsByUserId(int userId);

    public boolean isDepartmentAdmin(int userId, int departmentId);
    
    public boolean isDepartmentProfessor(int userId, int departmentId);
}