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
     * Get department by Course's id
     * @return Course if exists and null if not
     */
    Course getCourseById(int id);
}