package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Repo;


public interface RepoRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
     * @category LIST
	 * Get list of Repos
	 *
	 * @return a list of all Repos if found or null
	 */
    List<Repo> getRepos();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * @category ITEM
     * Get department by Repo's id
     * @return Repo if exists and null if not
     */
    Repo getRepoById(int id);
    
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
     * Add a new Repo to DB
     * @return id of newly added repo or if failed return -1
     */
    int insertRepo ();
    
    /**
     * @category PATCH
	 */
    
    /**
	 * @category DELETE
     */
}