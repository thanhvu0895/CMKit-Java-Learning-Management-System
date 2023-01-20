package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.SshKey;

public class SshKeyMapper implements RowMapper<SshKey> {

	@Override
	public SshKey map(ResultSet results) throws SQLException {
		return new SshKey()
			.id(results.getInt("id"))
			.key(results.getString("key"))
			.user_id(results.getInt("user_id"))
			.label(results.getString("label"));
	}

}