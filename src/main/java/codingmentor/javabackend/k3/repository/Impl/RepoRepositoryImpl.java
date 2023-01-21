package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.RepoMapper;
import codingmentor.javabackend.k3.model.Repo;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;

public class RepoRepositoryImpl extends AbstractRepository<Repo> implements RepoRepository{
	private static RepoRepository repository = null;
	private final RepoMapper mapper;
	private RepoRepositoryImpl() {
		mapper = new RepoMapper();
	}
	 
	public static RepoRepository getInstance() {
    	if (repository == null) {
    		repository = new RepoRepositoryImpl();
    	}
    	return repository;
    }
   
	/**
	 * close connection to save resources
	 * @param connection
	 * @param ps
	 * @param rs
	 */
    private void close (Connection connection, Statement ps, ResultSet rs) {
    	try {
    		if (rs != null) {
    			rs.close();
    		}
    		if (ps != null) {
    			ps.close();
    		}
    		
    		if (connection != null) {
    			connection.close();
    		}

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Repo> getRepos() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM repos";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Repo> reposList = new ArrayList<>();
			while(results.next()) {
				reposList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return reposList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Repo getRepoById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM repos WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet result = statement.executeQuery();
		    System.out.println(statement);
		    Repo repo = (result.next()) ? mapper.map(result) : null;
		    close(connection, statement, result);
		    return repo;
    	});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertRepo() {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO repos () VALUES ();";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			System.out.println(statement);
			ResultSet rs  = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating Repo failed, no rows affected.");
			}
			ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
			close(connection, statement, generatedKeys);
			return 0;
		});
	}
}