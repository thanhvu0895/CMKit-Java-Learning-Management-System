package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.PastContributor;

public class PastContributorMapper implements RowMapper<PastContributor> {

	@Override
	public PastContributor map(ResultSet results) throws SQLException { 
		return new PastContributor()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.submission_id(results.getInt("submission_id"));
	}
}