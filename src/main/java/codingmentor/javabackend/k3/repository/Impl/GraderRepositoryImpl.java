package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.GraderMapper;
import codingmentor.javabackend.k3.model.Grader;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.GraderRepository;

public class GraderRepositoryImpl extends AbstractRepository<Grader> implements GraderRepository{
	private static GraderRepository repository = null;
	private final GraderMapper mapper;
	private GraderRepositoryImpl() {
		mapper = new GraderMapper();
	}
	 
	public static GraderRepository getInstance() {
    	if (repository == null) {
    		repository = new GraderRepositoryImpl();
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
	public List<Grader> getGraders() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM graders";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Grader> gradersList = new ArrayList<>();
			while(results.next()) {
				gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradersList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grader getGraderById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM graders WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Grader grader = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return grader;
    	});
	}
	    
}