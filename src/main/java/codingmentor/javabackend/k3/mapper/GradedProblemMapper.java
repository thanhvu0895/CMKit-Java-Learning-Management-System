package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.GradedProblem;

public class GradedProblemMapper implements RowMapper<GradedProblem> {

	@Override
	public GradedProblem map(ResultSet results) throws SQLException { 
		return new GradedProblem()
			.id(results.getInt("id"))
			.problem_id(results.getInt("problem_id"))
			.submission_id(results.getInt("submission_id"))
			.comments(results.getString("comments"))
			.point_adjustment(results.getDouble("point_adjustment"))
			.grader_id(results.getInt("grader_id"));
	}
}