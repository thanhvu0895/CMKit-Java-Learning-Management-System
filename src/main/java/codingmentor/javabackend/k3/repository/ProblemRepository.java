package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Problem;


public interface ProblemRepository {
    /**
	 * Get list of Problems
	 *
	 * @return a list of all Problems if found or null
	 */
    List<Problem> getProblems();
    
    /**
     * Get department by Problem's id
     * @return Problem if exists and null if not
     */
    Problem getProblemById(int id);
}