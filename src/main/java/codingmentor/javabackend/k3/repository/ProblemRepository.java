package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Problem;


public interface ProblemRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Problems
	 * @category LIST
	 * @return a list of all Problems if found or null
	 */
    List<Problem> getProblems();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by Problem's id
     * @return Problem if exists and null if not
     */
    Problem getProblemById(int id);
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
     * @category CHECK
	 */
    
    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * @category POST
     * Insert a new Problemrepository to DB
     * @return newly inserted Department id if inserted and -1 otherwise
     */
    int insertProblem (int assignment_id, String title, Double points, int location, String grader_notes);
    
    /**
	 * @category PATCH
     */
    
    /**
	 * @category DELETE
     */
}