package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.AssignedGrader;


public interface AssignedGraderRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of AssignedGraders
	 * @category LIST
	 * @return a list of all AssignedGraders if found or null
	 */
    List<AssignedGrader> getAssignedGraders();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by AssignedGrader's id
     * @return AssignedGrader if exists and null if not
     */
    AssignedGrader getAssignedGraderById(int id);
    
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