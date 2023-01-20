package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.model.User;

public class DepartmentMapper  implements RowMapper<Department> {
	@Override
	public Department map(ResultSet results)  throws SQLException {
		return new Department()
				.id(results.getInt("id"))
				.repo_id(results.getInt("repo_id"))
				.title(results.getString("title"));
	}
}
