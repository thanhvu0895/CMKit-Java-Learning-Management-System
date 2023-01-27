package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Klass;

public class KlassMapper implements RowMapper<Klass> {

	@Override
	public Klass map(ResultSet results) throws SQLException {
		return new Klass()
			.id(results.getInt("id"))
			.course_id(results.getInt("course_id"))
			.repo_id(results.getInt("repo_id"))
			.semester(results.getString("semester"))
			.section(results.getObject("section") == null ? null : (Integer) results.getObject("section"))
			.start_date(results.getDate("start_date").toLocalDate())
			.end_date(results.getDate("end_date").toLocalDate());
	}
}