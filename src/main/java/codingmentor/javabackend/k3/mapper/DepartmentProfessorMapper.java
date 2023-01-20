package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.DepartmentProfessor;

public class DepartmentProfessorMapper implements RowMapper<DepartmentProfessor> {

	@Override
	public DepartmentProfessor map(ResultSet results) throws SQLException {
		return new DepartmentProfessor()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.department_id(results.getInt("department_id"))
			.admin(results.getBoolean("admin"));
	}
}
