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
    
    
    /**
	 * Get list of Problems by Assignment Id
	 * @category LIST
	 * @return a list of all Problems if found or null
	 */
    List<Problem> getProblemsByAssignmentId(int assignment_id);
    
    
    /**
	 * Get list of Problems by Assignment Id
	 * @category LIST
	 * @return a list of all Problems if found or null
	 */
    List<Problem> getProblemsByAssignmentIdOrderByLocationAsc(int assignment_id);
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get Problem by Problem's id
     * @return Problem if exists and null if not
     */
    Problem getProblemById(int id);
    
    /**
  	 * @category ITEM
     * Get Problem by Problem and Assignment's id
     * @return Problem if exists and null if not
     */
     Problem getProblemByLocationAndAssignmentId(int location, int assignment_id);
     
     /**
   	 * @category ITEM
	 * Get Problem by Problem and Assignment's id
	 * @return Problem if exists and null if not
     */
      Problem getMaxProblemByAssignmentId(int assignment_id);
    
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
    
    //PATCH(UPDATE)
    /**
	 * @category PATCH
     */
    boolean updateProblemLocationById (int location, int problem_id);
    
    /**
	 * @category PATCH
     */
    boolean updateProblemById (String title, double points, String grader_notes, int problem_id);
    
    
    /**
	 * @category DELETE
     */
}