package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Submission;

public class SubmissionMapper implements RowMapper<Submission> {

	@Override
	public Submission map(ResultSet results) throws SQLException {
		return new Submission()
			.id(results.getInt("id"))
			.assigned_id(results.getInt("assigned_id"))
			.repo_id(results.getInt("repo_id"))
			.turned_in(results.getBoolean("turned_in"))
			.turned_in_time(results.getTimestamp("turned_in_time").toLocalDateTime())
			.point_adjustment(results.getDouble("point_adjustment"))
			.percent_modifier(results.getDouble("percent_modifier"))
			.point_override(results.getBoolean("point_override"))
			.blank(results.getBoolean("blank"))
			.professor_uploaded(results.getBoolean("professor_uploaded"))
			.overall_comments(results.getString("overall_comments"))
			.cached_grade(results.getDouble("cached_grade"))
			.notified_graded(results.getBoolean("notified_graded"))
			.hide_rubric(results.getBoolean("hide_rubric"))
			.force_allow_resubmit(results.getBoolean("force_allow_resubmit"));
	}
}