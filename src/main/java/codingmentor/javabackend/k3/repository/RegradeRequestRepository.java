package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.RegradeRequest;


public interface RegradeRequestRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of RegradeRequests
	 * @category LIST
	 * @return a list of all RegradeRequests if found or null
	 */
    List<RegradeRequest> getRegradeRequests();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by RegradeRequest's id
     * @return RegradeRequest if exists and null if not
     */
    RegradeRequest getRegradeRequestById(int id);
    
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