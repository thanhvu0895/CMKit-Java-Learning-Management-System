package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.ReusableComment;

public class ReusableCommentMapper implements RowMapper<ReusableComment> {

	@Override
	public ReusableComment map(ResultSet results) throws SQLException {
		return new ReusableComment()
			.id(results.getInt("id"))
			.problem_id(results.getInt("problem_id"))
			.comment(results.getString("comment"));
	}

}