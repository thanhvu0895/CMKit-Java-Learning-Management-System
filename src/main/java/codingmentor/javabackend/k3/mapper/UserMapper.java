package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import codingmentor.javabackend.k3.model.User;

public class UserMapper implements RowMapper<User> {
	
	@Override
	public User map(ResultSet results)  throws SQLException {
		ResultSetMetaData rsmd = (ResultSetMetaData) results.getMetaData();
		if (rsmd.getColumnCount() <= 9) {
			return new User()
				.id(results.getInt("id"))		
				.email(results.getString("email"))
				.admin(results.getBoolean("admin"))
				.first_name(results.getString("first_name"))
				.last_name(results.getString("last_name"))
				.preferred_name(results.getString("preferred_name"))
				.set_up(results.getBoolean("set_up"))
				.disabled(results.getBoolean("disabled"))
				.deleted(results.getBoolean("deleted"));
		}
		
		if (results.getTimestamp("reset_expires") == null) {
			return new User()
				.id(results.getInt("id"))		
				.email(results.getString("email"))
				.admin(results.getBoolean("admin"))
				.first_name(results.getString("first_name"))
				.last_name(results.getString("last_name"))
				.preferred_name(results.getString("preferred_name"))
				.password_digest(results.getString("password_digest"))
				.reset_digest(results.getString("reset_digest"))
				.set_up(results.getBoolean("set_up"))
				.disabled(results.getBoolean("disabled"))
				.deleted(results.getBoolean("deleted"));
		}
		return new User()
				.id(results.getInt("id"))		
				.email(results.getString("email"))
				.admin(results.getBoolean("admin"))
				.first_name(results.getString("first_name"))
				.last_name(results.getString("last_name"))
				.preferred_name(results.getString("preferred_name"))
				.password_digest(results.getString("password_digest"))
				.reset_expires(results.getTimestamp("reset_expires").toLocalDateTime())
				.reset_digest(results.getString("reset_digest"))
				.set_up(results.getBoolean("set_up"))
				.disabled(results.getBoolean("disabled"))
				.deleted(results.getBoolean("deleted"));
	}
}
