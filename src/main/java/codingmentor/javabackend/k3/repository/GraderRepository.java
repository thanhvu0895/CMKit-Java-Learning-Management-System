package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Grader;


public interface GraderRepository {
    /**
	 * Get list of Graders
	 *
	 * @return a list of all Graders if found or null
	 */
    List<Grader> getGraders();
    
    /**
     * Get department by Grader's id
     * @return Grader if exists and null if not
     */
    Grader getGraderById(int id);
}