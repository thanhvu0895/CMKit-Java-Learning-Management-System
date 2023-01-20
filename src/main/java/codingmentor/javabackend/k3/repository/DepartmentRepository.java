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
}