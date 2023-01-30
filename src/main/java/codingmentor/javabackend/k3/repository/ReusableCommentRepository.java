package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.ReusableComment;


public interface ReusableCommentRepository {
	
	/*
	 * GET LIST METHOD
	 */
	
    /**
     * @category LIST
	 * Get list of ReusableComments
	 *
	 * @return a list of all ReusableComments if found or null
	 */
    List<ReusableComment> getReusableComments();
    
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * @category ITEM
     * Get department by ReusableComment's id
     * @return ReusableComment if exists and null if not
     */
    ReusableComment getReusableCommentById(int id);
    
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
     * Add a new ReusableComment to DB
     * @return newly inserted Professor id if inserted and -1 otherwise
     */
    boolean insertReusableComment(int problem_id, String comment);
    
    /**
     * @category PATCH
	 */
    
    /**
	 * @category DELETE
     */
}