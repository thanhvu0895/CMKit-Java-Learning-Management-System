package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.RegradeRequest;

public class RegradeRequestMapper implements RowMapper<RegradeRequest> {

	@Override
	public RegradeRequest map(ResultSet results) throws SQLException { 
		return new RegradeRequest()
			.id(results.getInt("id"))
			.submission_id(results.getInt("submission_id"))
			.reason(results.getString("reason"))
			.closed(results.getBoolean("closed"))
			.response(results.getString("response"))
			.requested_by_id(results.getInt("requested_by_id"))
			.closed_by_id(results.getInt("closed_by_id"));
	}
}