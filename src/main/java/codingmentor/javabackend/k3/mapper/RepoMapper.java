package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.Repo;

public class RepoMapper implements RowMapper<Repo> {

	@Override
	public Repo map(ResultSet results) throws SQLException {
		return new Repo()
			.id(results.getInt("id"));
	}

}