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
    
	/*
	 * GET LIST METHOD
	 */
    
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Submission> getSubmissions() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM submissions";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getSubmissions: " + statement);
			List<Submission> submissionsList = new ArrayList<>();
			while(results.next()) {
				submissionsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return submissionsList;
		});
	}
	
	/*
	 * GET LIST METHOD
	 */
    
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Submission> getSubmissionsByAssignedId(int assignedId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM submissions where assigned.id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignedId);
			ResultSet results = statement.executeQuery();
			System.out.println("getSubmissions: " + statement);
			List<Submission> submissionsList = new ArrayList<>();
			while(results.next()) {
				submissionsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return submissionsList;
		});
	}


	/*
	 * GET ITEM METHOD
	 */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Submission getSubmissionById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM submissions WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getSubmissionById: " + statement);
		    Submission submission = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return submission;
    	});
	}

	
	
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertSubmission(int assigned_id, double percent_modifier) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO `Submissions` (`user_id`, `klass_id`) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, assigned_id);
			statement.setDouble(2, percent_modifier);
			System.out.println("insertSubmission: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Submission failed, no rows affected.");
			}
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
            
			close(connection, statement, generatedKeys);
			return 0;
		});
	}
	
	//PATCH
	 public boolean updateCachedGradeNull (int assignedId) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE submissions SET cached_grade = NULL where assigned_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignedId);
			System.out.println("updateCachedGrade: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);
			return result;
		}) != 0;
	 }
	
}