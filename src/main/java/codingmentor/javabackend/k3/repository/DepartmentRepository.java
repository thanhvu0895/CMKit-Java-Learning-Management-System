package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Department;


public interface DepartmentRepository {
    /**
	 * Find list of departments
	 *
	 * @return a list of all departments if found or null
	 */
    List<Department> getDepartments();
    
    /**
     * Implement getDeparmentById 1/20/23
     * @return Department if exists and null if not
     */
    Department getDepartmentById(int id);
}