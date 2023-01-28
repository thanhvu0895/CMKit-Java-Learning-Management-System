package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Course;


public interface CourseRepository {
	/*
	 * GET LIST METHOD
	 */
	
	/**
	 * Get list of Courses
	 *
	 * @return a list of all Courses if found or null
	 */
    List<Course> getCourses();
    
    /**
	 * Get list of Courses Order by Course Code
	 *
	 * @return a list of all Courses if found or null
	 */
    List<Course> getCoursesOrderByCourseCode();
    
    /**
     * Get Courses by Department's id
     * @return Course if exists and null if not
     */
    List<Course> getCoursesByDepartmentId(int departmentId);
    
    /**
     * Get Courses that include klasses by Department's id
     * @return Course if exists and null if not
     */
    List<Course> getCoursesWithKlassByDepartmentId(int departmentId);
    
	/*
	 * GET ITEM METHOD
	 */
    
    /**
     * Get course by Course's id
     * @return Course if exists and null if not
     */
    Course getCourseById(int id);
    
    /**
     * Get course by Klass's id
     */
    Course getCourseByKlassId(int klassId);
    
    
    /**
     * Get course by GradeCategory's id
     */
    Course getCourseByGradeCategoryId(int klassId);
    
	/*
	 * GET Check True/false METHOD
	 */
    
    
    
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	// POST(INSERT INTO)
        
    /**
     * Add a new Course to DB
     * @return newly inserted Course id if inserted and -1 otherwise
     */
    int insertCourse (String title, String course_code, int repo_id, int department_id, boolean active);
    
    
    //PATCH(UPDATE)
    /** Update Course's title, Course Code, active status given Course id
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateCourseById(String title, String courseCode, boolean active, int id);
    
}