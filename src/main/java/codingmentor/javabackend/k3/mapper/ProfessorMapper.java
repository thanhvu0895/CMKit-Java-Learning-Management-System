package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Professor;

public class ProfessorMapper implements RowMapper<Professor> {

	@Override
	public Professor map(ResultSet results) throws SQLException {
		return new Professor()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.klass_id(results.getInt("klass_id"));
	}
}