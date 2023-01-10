package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.User;

public class UserMapper implements RowMapper<User> {
	@Override
	public User map(ResultSet results)  throws SQLException {
		return new User()
				.admin(results.getBoolean("admin"))
				.email(results.getString("email"))
				.first_name(results.getString("first_name"))
				.password_digest(results.getString("password_digest"));
	}
}
