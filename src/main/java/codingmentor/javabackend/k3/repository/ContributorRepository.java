package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Contributor;


public interface ContributorRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Contributors
	 * @category LIST
	 * @return a list of all Contributors if found or null
	 */
    List<Contributor> getContributors();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by Contributor's id
     * @return Contributor if exists and null if not
     */
    Contributor getContributorById(int id);
    
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