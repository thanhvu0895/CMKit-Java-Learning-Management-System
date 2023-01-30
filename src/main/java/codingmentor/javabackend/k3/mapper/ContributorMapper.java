package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Contributor;

public class ContributorMapper implements RowMapper<Contributor> {

	@Override
	public Contributor map(ResultSet results) throws SQLException { 
		return new Contributor()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.submission_id(results.getInt("submission_id"))
			.feedback_seen(results.getBoolean("feedback_seen"));
	}
}