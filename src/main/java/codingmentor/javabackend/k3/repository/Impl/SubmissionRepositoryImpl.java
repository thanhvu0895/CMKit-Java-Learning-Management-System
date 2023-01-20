package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.SubmissionMapper;
import codingmentor.javabackend.k3.model.Submission;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.SubmissionRepository;

public class SubmissionRepositoryImpl extends AbstractRepository<Submission> implements SubmissionRepository{
	private static SubmissionRepository repository = null;
	private final SubmissionMapper mapper;
	private SubmissionRepositoryImpl() {
		mapper = new SubmissionMapper();
	}
	 
	public static SubmissionRepository getInstance() {
    	if (repository == null) {
    		repository = new SubmissionRepositoryImpl();
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
	public List<Submission> getSubmissions() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM submissions";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Submission> submissionsList = new ArrayList<>();
			while(results.next()) {
				submissionsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return submissionsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Submission getSubmissionById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM submissions WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Submission submission = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return submission;
    	});
	}
	    
}