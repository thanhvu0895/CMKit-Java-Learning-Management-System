package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import codingmentor.javabackend.k3.model.Course;

public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course map(ResultSet results) throws SQLException {
		ResultSetMetaData rsmd = (ResultSetMetaData) results.getMetaData();
		
		if (rsmd.getColumnCount() == 7) {
			return new Course()
					.id(results.getInt("id"))
					.title(results.getString("title"))
					.course_code(results.getString("course_code"))
					.repo_id(results.getInt("repo_id"))
					.department_id(results.getInt("department_id"))
					.active(results.getBoolean("active"))
					.student_count(results.getInt("student_count"));
		}
		return new Course()
				.id(results.getInt("id"))
				.title(results.getString("title"))
				.course_code(results.getString("course_code"))
				.repo_id(results.getInt("repo_id"))
				.department_id(results.getInt("department_id"))
				.active(results.getBoolean("active"));
	}

}