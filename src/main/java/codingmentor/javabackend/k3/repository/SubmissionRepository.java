package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Submission;


public interface SubmissionRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Submissions
	 * @category LIST
	 * @return a list of all Submissions if found or null
	 */
    List<Submission> getSubmissions();
    
    /**
	 * Get list of Submissions by assignedId
	 * @category LIST
	 * @return a list of all Submissions if found or null
	 */
    List<Submission> getSubmissionsByAssignedId(int assignedId);
    
	/*
	 * GET ITEM METHOD
	 */
    
    /**
     * @category ITEM
     * Get department by Submission's id
     * @return Submission if exists and null if not
     */
    Submission getSubmissionById(int id);
    
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
    int insertSubmission (int assigned_id, double percent_modifier);
    /**
     * @category PATCH
	 */
    boolean updateCachedGradeNull (int assignedId);
    /**
	 * @category DELETE
     */
}