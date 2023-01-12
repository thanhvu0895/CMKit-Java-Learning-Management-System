package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.User;

public class UserMapper implements RowMapper<User> {
	@Override
	public User map(ResultSet results)  throws SQLException {
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
				.disabled(results.getBoolean("disabled"));
	}
}
