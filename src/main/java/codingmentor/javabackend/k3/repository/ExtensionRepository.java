package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Extension;


public interface ExtensionRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Extensions
	 * @category LIST
	 * @return a list of all Extensions if found or null
	 */
    List<Extension> getExtensions();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by Extension's id
     * @return Extension if exists and null if not
     */
    Extension getExtensionById(int id);
    
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