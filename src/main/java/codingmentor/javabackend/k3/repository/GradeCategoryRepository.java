package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.GradeCategory;


public interface GradeCategoryRepository {
	
	/*
	 * GET LIST METHOD
	 */
    
    /**
	 * @category LIST
     * Get list of gradeCategory from Course id
     * @param userId
     * @return
     */
    List<GradeCategory> getGradeCategoriesByCourseId(int courseId);
 
    /**
 	 * @category LIST
      * Get list of gradeCategory from Klass's id
      * @param userId
      * @return
      */
    List<GradeCategory> getGradeCategoriesByKlassId(int klassId);
    
    /**
	 * @category ITEM
     * Get list of GradeCategory from user id
     * @param userId
     * @return
     */
    List<GradeCategory> getGradeCategoriesUsedByAssignmentInCourse(int courseId);
    
	/*
	 * GET ITEM METHOD
	 */
    /**
	 * @category ITEM
     * Get GradeCategory by GradeCategory's id
     * @return GradeCategory if exists and null if not
     */
    GradeCategory getGradeCategoryById(int id);
    
    /**
	 * @category ITEM
     * Get GradeCategory by GradeCategory's id
     * @return GradeCategory if exists and null if not
     */
    GradeCategory getGradeCategoryByAssignmentId(int assignmentId);
    
 
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
	 * @category CHECK
     * if current GradeCategory is used by a course 
     * @param userId
     * @param gradeCategoryId
     * @return true if user is Admin of this gradeCategory, otherwise false
     */
    boolean isUsedByAssignment(int gradeCategoryId);
 
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
	 * @category POST
     * Add a new GradeCategory to DB
     * @return newly inserted gradeCategory id if inserted and -1 otherwise
     */
    boolean insertGradeCategory (String title, int course_id, double weight);
    
	//PATCH
    /**
     * 
	 * @category PATCH
     * @return
     */
    boolean updateGradeCategoryById(String title, double weight, int gradeCategoryId);
    
    //DELETE
	/**
	 * @category DELETE
	 *  Remove Professor with given id
	 */
    boolean deleteGradeCategory(int gradeCategoryId);    
}