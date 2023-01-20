package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Course;

public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course map(ResultSet results) throws SQLException {
		return new Course()
				.id(results.getInt("id"))
				.title(results.getString("title"))
				.course_code(results.getString("course_code"))
				.repo_id(results.getInt("repo_id"))
				.department_id(results.getInt("department_id"))
				.active(results.getBoolean("active"));
	}

}