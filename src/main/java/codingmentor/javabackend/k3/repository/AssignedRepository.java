package codingmentor.javabackend.k3.repository;

import java.time.LocalDateTime;
import java.util.List;

import codingmentor.javabackend.k3.model.Assigned;


public interface AssignedRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Assigneds
	 * @category LIST
	 * @return a list of all Assigneds if found or null
	 */
    List<Assigned> getAssigneds();
    
    
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Assigneds of assignments in a klass
	 * @category LIST
	 * @return a list of all Assigneds if found or null
	 */
    List<Assigned> getAssignedsByAssignmentsInKlass(int klassId);
    
	/*
	 * GET LIST METHOD
	 */
    /**
	 * Get list of Assigneds of assignments in a Course
	 * @category LIST
	 * @return a list of all Assigneds if found or null
	 */
    List<Assigned> getAssignedsByAssignmentsInCourse(int courseId);
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by Assigned's id
     * @return Assigned if exists and null if not
     */
    Assigned getAssignedById(int id);
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get department by Assigned's id
     * @return Assigned if exists and null if not
     */
    Assigned getAssignedByAssignmentId(int assignmentId);
    
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
    int insertAssigned(int assignment_id, int klass_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, int repo_id, boolean limit_resubmissions, boolean allow_resubmissions);
    
    /**
	 * @category PATCH
     */
    
    /**
	 * @category DELETE
     */
}