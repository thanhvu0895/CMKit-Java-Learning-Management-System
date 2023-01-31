package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Problem;

public class ProblemMapper implements RowMapper<Problem> {

	@Override
	public Problem map(ResultSet results) throws SQLException { 
		return new Problem()
			.id(results.getInt("id"))
			.assignment_id(results.getInt("assignment_id"))
			.title(results.getString("title"))
			.points(results.getDouble("points"))
			.location(results.getInt("location"))
			.grader_notes(results.getString("grader_notes"));
	}
}