package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Grader;


public interface GraderRepository {
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Graders
	 * @category LIST
	 * @return a list of all Graders if found or null
	 */
    List<Grader> getGraders();
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by Grader's id
     * @return Grader if exists and null if not
     */
    Grader getGraderById(int id);
    
	/*
	 * GET Check True/false METHOD
	 */
    /**
     * @category CHECK
     */
    
    /*
     * 
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
    
    //POST(INSERT INTO)
	/**
     * @category POST
	 */
    int insertGrader (int user_id, int klass_id);
    
    
    /**
     * @category PATCH
	 */
	
	
    
    /**
	 * @category DELETE
     */
}