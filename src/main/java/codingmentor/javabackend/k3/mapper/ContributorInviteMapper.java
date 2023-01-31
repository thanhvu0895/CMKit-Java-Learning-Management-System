package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.ContributorInvite;

public class ContributorInviteMapper implements RowMapper<ContributorInvite> {

	@Override
	public ContributorInvite map(ResultSet results) throws SQLException { 
		return new ContributorInvite()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.submission_id(results.getInt("submission_id"));
	}
}