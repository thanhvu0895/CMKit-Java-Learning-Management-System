package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.AssignedGrader;

public class AssignedGraderMapper implements RowMapper<AssignedGrader> {

	@Override
	public AssignedGrader map(ResultSet results) throws SQLException { 
		return new AssignedGrader()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.assigned_id(results.getInt("assigned_id"));
	}
}