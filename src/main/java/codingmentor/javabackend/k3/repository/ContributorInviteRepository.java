package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.ContributorInvite;


public interface ContributorInviteRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of ContributorInvites
	 * @category LIST
	 * @return a list of all ContributorInvites if found or null
	 */
    List<ContributorInvite> getContributorInvites();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by ContributorInvite's id
     * @return ContributorInvite if exists and null if not
     */
    ContributorInvite getContributorInviteById(int id);
    
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