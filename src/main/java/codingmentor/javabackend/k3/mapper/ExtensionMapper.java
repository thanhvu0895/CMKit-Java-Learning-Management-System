package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Extension;

public class ExtensionMapper implements RowMapper<Extension> {

	@Override
	public Extension map(ResultSet results) throws SQLException { 
		return new Extension()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.allow_late_submissions(results.getBoolean("allow_late_submissions"))
			.new_deadline(results.getTimestamp("new_deadline").toLocalDateTime())
			.use_deadline_as_due_date(results.getBoolean("use_deadline_as_due_date"))
			.limit_resubmissions(results.getBoolean("limit_resubmissions"))
			.resubmission_limit(results.getInt("resubmission_limit"))
			.allow_resubmissions(results.getInt("allow_resubmissions"))
			.assigned_id(results.getInt("assigned_id"));
	}
}