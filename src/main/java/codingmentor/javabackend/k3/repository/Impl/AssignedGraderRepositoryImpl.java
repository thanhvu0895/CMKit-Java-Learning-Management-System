package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.AssignedGraderMapper;
import codingmentor.javabackend.k3.model.AssignedGrader;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.AssignedGraderRepository;

public class AssignedGraderRepositoryImpl extends AbstractRepository<AssignedGrader> implements AssignedGraderRepository{
	private static AssignedGraderRepository repository = null;
	private final AssignedGraderMapper mapper;
	
	private AssignedGraderRepositoryImpl() {
		mapper = new AssignedGraderMapper();
	}
	 
	public static AssignedGraderRepository getInstance() {
    	if (repository == null) {
    		repository = new AssignedGraderRepositoryImpl();
    	}
    	return repository;
    }
   
	/**
	 * close connection to save resources
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
	public List<AssignedGrader> getAssignedGraders() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM assigned_graders";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getAssignedGraders: " + statement);
			List<AssignedGrader> assigned_gradersList = new ArrayList<>();
			while(results.next()) {
				assigned_gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assigned_gradersList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<AssignedGrader> getAssignedGradersByAssignedId(int assignedId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM assigned_graders where assigned_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignedId);
			ResultSet results = statement.executeQuery();
			System.out.println("getAssignedGraders: " + statement);
			List<AssignedGrader> assigned_gradersList = new ArrayList<>();
			while(results.next()) {
				assigned_gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assigned_gradersList;
		});
	}	

	/*
	 * GET ITEM METHOD
	 */
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public AssignedGrader getAssignedGraderById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM assigned_graders WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getAssignedGraderById: " + statement);
		    AssignedGrader department = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return department;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
	

	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean insertAssignedGrader(int user_id , int assigned_id) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO `assigned_graders` (`user_id`, `assigned_id`) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, user_id);
			statement.setInt(2, assigned_id);
			System.out.println("insertAssignedGrader: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}
	
	//PATCH(UPDATE)
	
	
	//DELETE
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAssignedGrader(int assignedGraderId) {
		return executeUpdate(connection -> {
			 final String query = "DELETE FROM assigned_graders WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, assignedGraderId);
			 System.out.println("deleteAssignedGrader: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
		
	}
	
}
