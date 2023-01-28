package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.GradeCategory;


public interface GradeCategoryRepository {
	
	/*
	 * GET LIST METHOD
	 */
    
    /**
     * 
     * Get list of gradeCategory from user id
     * @param userId
     * @return
     */
    public List<GradeCategory> getGradeCategoriesByCourseId(int courseId);
    
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * Get gradeCategory by GradeCategory's id
     * @return GradeCategory if exists and null if not
     */
    GradeCategory getGradeCategoryById(int id);
    
 
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
     * if current GradeCategory is used by a course 
     * @param userId
     * @param gradeCategoryId
     * @return true if user is Admin of this gradeCategory, otherwise false
     */
    public boolean isUsedByAssignment(int gradeCategoryId);
 
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * Add a new GradeCategory to DB
     * @return newly inserted gradeCategory id if inserted and -1 otherwise
     */
    boolean insertGradeCategory (String title, int course_id, double weight);
    
	//PUT(REPLACE)
    public boolean updateGradeCategoryById(String title, double weight, int gradeCategoryId);
    
    //DELETE
	/**
	 *  Remove Professor with given id
	 */
    boolean deleteGradeCategory(int gradeCategoryId);    
}