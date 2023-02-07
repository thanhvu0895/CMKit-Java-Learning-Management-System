package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Course;


public interface CourseRepository {
	/*
	 * GET LIST METHOD
	 */
	
	/**
	 * Get list of Courses
     * @category LIST
	 * @return a list of all Courses if found or null
	 */
    List<Course> getCourses();
    
    /**
	 * Get list of Courses Order by Course Code
     * @category LIST
	 * @return a list of all Courses if found or null
	 */
    List<Course> getCoursesOrderByCourseCode();
    
    /**
     * @category LIST
     * Get Courses by Department's id
     * @return Course if exists and null if not
     */
    List<Course> getCoursesByDepartmentId(int departmentId);
    
    /**
     * @category LIST
     * Get Courses that include klasses by Department's id
     * @return Course if exists and null if not
     */
    List<Course> getCoursesWithKlassByDepartmentId(int departmentId);
    
    /**
     * @category LIST
     * Get Student Courses by theird user id
     * @return Course if exists and null if not
     */
    List<Course> getStudentCoursesByUserId(int userId);
    
    /**
     * @category LIST
     * Get Grader Courses by theird user id
     * @return Course if exists and null if not
     */
    List<Course> getGraderCoursesByUserId(int userId);
    
    /**
     * @category LIST
     * Get Grader Courses by theird user id
     * @return Course if exists and null if not
     */
    List<Course> getProfessorCoursesByUserId(int userId);
    
    List<Course> getProfessorCoursesByUserIdWithStudentsCount(int userId);
    
    
    /**
     * @category LIST
     * Get Admin Courses by theird user id
     * @return Course if exists and null if not
     */
    List<Course> getAdminCourses();    
    
	/*
	 * GET ITEM METHOD
	 */
    
    /**
     * @category ITEM
     * Get course by Course's id
     * @return Course if exists and null if not
     */
    Course getCourseById(int id);
    
    /**
     * @category ITEM
     * Get course by Klass's id
     */
    Course getCourseByKlassId(int klassId);
    
    
    /**
     * @category ITEM
     * Get course by GradeCategory's id
     */
    Course getCourseByGradeCategoryId(int klassId);
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
     * @category CHECK
     */
    
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	// POST(INSERT INTO)
        
    /**
     * @category POST
     * Add a new Course to DB
     * @return newly inserted Course id if inserted and -1 otherwise
     */
    int insertCourse (String title, String course_code, int repo_id, int department_id, boolean active);
    
    
    //PATCH(UPDATE)
    /** Update Course's title, Course Code, active status given Course id
     * @category PATCH
     * @return true if updated, and false if update was unsuccessful
     */
    public boolean updateCourseById(String title, String courseCode, boolean active, int id);
    
    /**
	 * @category DELETE
     */
}