package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.GradeCategory;


public interface GradeCategoryRepository {
	
	/*
	 * GET LIST METHOD
	 */
    /**
	 * 
	 * Get list of GradeCategories
	 *
	 * @return a list of all GradeCategories if found or null
	 */
    List<GradeCategory> getGradeCategories();
    
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
    
    /**
     * Get GradeCategory by Course's id
     * @return GradeCategory if exists and null if not
     */
    GradeCategory getGradeCategoryByCourseId(int courseId);
    
    
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
    
    /**
     * 
     * @param userId
     * @param gradeCategoryId
     * @return true if user is Professor of this gradeCategory, otherwise false
     */
    public boolean isGradeCategoryProfessor(int userId, int gradeCategoryId);
    
    /**Check if GradeCategory already exists by GradeCategory's title
     * 
     * @param title
     * @return
     */
    public boolean existedByTitle(String title);
    
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * Add a new GradeCategory to DB
     * @return newly inserted gradeCategory id if inserted and -1 otherwise
     */
    boolean insertGradeCategory (String title, int course_id, double weight);
    
	//PATCH(UPDATE)
    /** Update GradeCategory's title given gradeCategory id and new title
     * 
     * @param title
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateGradeCategoryTitleById(String title, int id);

}