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
    
    /**
	 * Get list of Assigneds of assignments in a klass
	 * @category LIST
	 * @return a list of all Assigneds if found or null
	 */
    List<Assigned> getAssignedsByAssignmentsInKlass(int klassId);
    
    /**
	 * Get list of Assigneds of assignments in a Course
	 * @category LIST
	 * @return a list of all Assigneds if found or null
	 */
    List<Assigned> getAssignedsByAssignmentsInCourse(int courseId, int klassId);
    
    /**
	 * Get list of Assigneds of user in a Klass
	 * @category LIST
	 * @return a list of all Assigneds if found or null
	 */
    List<Assigned> getAssignedsByUserInKlassOrderByDueDate(int userId, int klassId);
    
    
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
    Assigned getAssignedByAssignedGraderId(int assignedGraderId);
    
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
    int insertAssigned(int assignment_id, int klass_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, int repo_id, boolean limit_resubmissions, int allow_resubmissions);
    
    /**
     * @category POST
	 */
    int insertAssignedResubmissionLimit(int assignment_id, int klass_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, int repo_id, boolean limit_resubmissions, int resubmission_limit, int allow_resubmissions);

       
    //PATCH(UPDATE)
    /** 
     * @category PATCH
     */
    boolean updateAssigned(int assigned_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, boolean limit_resubmissions, int allow_resubmissions);
    
    /** 
     * @category PATCH
     */    
    boolean updateAssignedResubmissionLimit(int assignedId, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, boolean limit_resubmissions, int resubmission_limit, int allow_resubmissions);
    
    /** 
     * @category PATCH
     */    
    boolean updateAssignedAdjustment(int assignedId, Double max_points_override, Double point_value_scale);
    /**
	 * @category DELETE
     */
}