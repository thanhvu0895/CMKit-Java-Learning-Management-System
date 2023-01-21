package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Course;


public interface CourseRepository {
    /**
	 * Get list of Courses
	 *
	 * @return a list of all Courses if found or null
	 */
    List<Course> getCourses();
    
    /**
     * Get course by Course's id
     * @return Course if exists and null if not
     */
    Course getCourseById(int id);
    
    
    /**
     * Get Courses by Department's id
     * @return Course if exists and null if not
     */
    List<Course> getCourseByDepartmentId(int departmentId);
    
    
    /**
     * Add a new Course to DB
     * @return newly inserted Course id if inserted and -1 otherwise
     */
    int insertCourse (String title, String course_code, int repo_id, int department_id, boolean active);
    
}