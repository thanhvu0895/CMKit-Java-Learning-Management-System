package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.PastContributor;


public interface PastContributorRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of PastContributors
	 * @category LIST
	 * @return a list of all PastContributors if found or null
	 */
    List<PastContributor> getPastContributors();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by PastContributor's id
     * @return PastContributor if exists and null if not
     */
    PastContributor getPastContributorById(int id);
    
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