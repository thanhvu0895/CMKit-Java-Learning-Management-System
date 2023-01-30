package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.GradedProblem;


public interface GradedProblemRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of GradedProblems
	 * @category LIST
	 * @return a list of all GradedProblems if found or null
	 */
    List<GradedProblem> getGradedProblems();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by GradedProblem's id
     * @return GradedProblem if exists and null if not
     */
    GradedProblem getGradedProblemById(int id);
    
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
	 */
    
    /**
	 * @category PATCH
     */
    
    /**
	 * @category DELETE
     */
}