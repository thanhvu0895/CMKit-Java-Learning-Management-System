package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import codingmentor.javabackend.k3.model.Assignment;

public class AssignmentMapper implements RowMapper<Assignment> {

	@Override
	public Assignment map(ResultSet results) throws SQLException {
		ResultSetMetaData rsmd = (ResultSetMetaData) results.getMetaData();
		if (rsmd.getColumnCount() == 14) {
			return new Assignment()
					.id(results.getInt("id"))
					.title(results.getString("title"))
					.klass_id(results.getInt("klass_id"))
					.course_id(results.getInt("course_id"))
					.grade_category_id(results.getObject("grade_category_id") == null ? null : (Integer) results.getObject("grade_category_id"))
					.files_repo_id(results.getInt("files_repo_id"))
					.template_repo_id(results.getInt("template_repo_id"))
					.assignment_type(results.getInt("assignment_type"))
					.permitted_filetypes(results.getString("permitted_filetypes"))
					.description(results.getString("description"))
					.file_limit(results.getInt("file_limit"))
					.file_or_link(results.getInt("file_or_link"))
					.total_points(results.getDouble("total_points"))
					.assigned_graders(results.getString("assigned_graders"));
		}
		
		
		if (rsmd.getColumnCount() == 13) {
			return new Assignment()
					.id(results.getInt("id"))
					.title(results.getString("title"))
					.klass_id(results.getInt("klass_id"))
					.course_id(results.getInt("course_id"))
					.grade_category_id(results.getObject("grade_category_id") == null ? null : (Integer) results.getObject("grade_category_id"))
					.files_repo_id(results.getInt("files_repo_id"))
					.template_repo_id(results.getInt("template_repo_id"))
					.assignment_type(results.getInt("assignment_type"))
					.permitted_filetypes(results.getString("permitted_filetypes"))
					.description(results.getString("description"))
					.file_limit(results.getInt("file_limit"))
					.file_or_link(results.getInt("file_or_link"))
					.total_points(results.getDouble("total_points"));
		}
		
		return new Assignment()
			.id(results.getInt("id"))
			.title(results.getString("title"))
			.klass_id(results.getInt("klass_id"))
			.course_id(results.getInt("course_id"))
			.grade_category_id(results.getObject("grade_category_id") == null ? null : (Integer) results.getObject("grade_category_id"))
			.files_repo_id(results.getInt("files_repo_id"))
			.template_repo_id(results.getInt("template_repo_id"))
			.assignment_type(results.getInt("assignment_type"))
			.permitted_filetypes(results.getString("permitted_filetypes"))
			.description(results.getString("description"))
			.file_limit(results.getInt("file_limit"))
			.file_or_link(results.getInt("file_or_link"));
	}

}
