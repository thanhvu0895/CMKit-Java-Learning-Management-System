package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Student;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student map(ResultSet results) throws SQLException {
		return new Student()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.klass_id(results.getInt("klass_id"))
			.notify_assignment_assigned(results.getBoolean("notify_assignment_assigned"))
			.notify_graded(results.getBoolean("notify_graded"))
			.notify_contributor_invite(results.getBoolean("notify_contributor_invite"))
			.notify_extension(results.getBoolean("notify_extension"))
			.notify_regrade_response(results.getBoolean("notify_regrade_response"));
	}
}