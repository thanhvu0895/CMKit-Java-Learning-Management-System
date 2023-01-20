package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Grader;

public class GraderMapper implements RowMapper<Grader> {

	@Override
	public Grader map(ResultSet results) throws SQLException { 
		return new Grader()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.klass_id(results.getInt("klass_id"))
			.notify_grader_assigned(results.getBoolean("notify_grader_assigned"));
	}
}