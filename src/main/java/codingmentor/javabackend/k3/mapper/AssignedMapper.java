package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Assigned;

public class AssignedMapper implements RowMapper<Assigned> {

	@Override
	public Assigned map(ResultSet results) throws SQLException { 
		return new Assigned()
			.id(results.getInt("id"))
			.assignment_id(results.getInt("assignment_id"))
			.klass_id(results.getInt("klass_id"))
			.due_date(results.getTimestamp("due_date").toLocalDateTime())
			.allow_late_submissions(results.getBoolean("allow_late_submissions"))
			.max_contributors(results.getInt("max_contributors"))
			.max_points_override(results.getDouble("max_points_override"))
			.point_value_scale(results.getDouble("point_value_scale"))
			.repo_id(results.getInt("repo_id"))
			.limit_resubmissions(results.getBoolean("limit_resubmissions"))
			.resubmission_limit(results.getInt("resubmission_limit"))
			.allow_resubmissions(results.getInt("allow_resubmissions"))
			.hide_grades(results.getBoolean("hide_grades"));
	}
}