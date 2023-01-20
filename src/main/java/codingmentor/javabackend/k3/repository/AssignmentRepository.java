package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Assignment;


public interface AssignmentRepository {
    /**
	 * Get list of Assignments
	 *
	 * @return a list of all Assignments if found or null
	 */
    List<Assignment> getAssignments();
    
    /**
     * Get department by Assignment's id
     * @return Assignment if exists and null if not
     */
    Assignment getAssignmentById(int id);
}