package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import codingmentor.javabackend.k3.model.Grader;

public class GraderMapper implements RowMapper<Grader> {
	
	@Override
	public Grader map(ResultSet results) throws SQLException {
		ResultSetMetaData rsmd = (ResultSetMetaData) results.getMetaData();
		if (rsmd.getColumnCount() == 5) {
			return new Grader()
				.id(results.getInt("id"))
				.user_id(results.getInt("user_id"))
				.klass_id(results.getInt("klass_id"))
				.notify_grader_assigned(results.getBoolean("notify_grader_assigned"))
				.assignedStatus(results.getObject("assigned_status") == null ? null : ((Long) results.getObject("assigned_status")).intValue());
		}
		
		return new Grader()
			.id(results.getInt("id"))
			.user_id(results.getInt("user_id"))
			.klass_id(results.getInt("klass_id"))
			.notify_grader_assigned(results.getBoolean("notify_grader_assigned"));
	}
}