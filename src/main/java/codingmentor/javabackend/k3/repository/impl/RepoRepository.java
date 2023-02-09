package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.RepoMapper;
import codingmentor.javabackend.k3.model.Repo;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class RepoRepository extends BaseRepository<RepoMapper, Repo>{
	
	private static RepoRepository repository;
	
	public static synchronized RepoRepository getInstance() {
    	if (repository == null) {
    		repository = new RepoRepository();
    	}
    	
    	return repository;
    }
	
	private RepoRepository() {
		super(new RepoMapper());
	}
	 
	/*
	 * GET LIST METHOD
	 */
    
	public List<Repo> getRepos() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM repos";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("getRepos:" + statement);
			List<Repo> reposList = new ArrayList<>();
			while(results.next()) {
				reposList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return reposList;
		});
	}

	
	/*
	 * GET ITEM METHOD
	 */

	public Repo getRepoById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM repos WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet result = statement.executeQuery();
		    logger.info("-- getRepoById: " + statement);
		    Repo repo = (result.next()) ? mapper.map(result) : null;
		    close(connection, statement, result);
		    return repo;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)

	public int insertRepo() {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO repos () VALUES ();";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			logger.info("-- insertRepo(): " + statement);
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