package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.AssignmentMapper;
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.AssignmentRepository;

public class AssignmentRepositoryImpl extends AbstractRepository<Assignment> implements AssignmentRepository{
	private static AssignmentRepository repository = null;
	private final AssignmentMapper mapper;
	private AssignmentRepositoryImpl() {
		mapper = new AssignmentMapper();
	}
	 
	public static AssignmentRepository getInstance() {
    	if (repository == null) {
    		repository = new AssignmentRepositoryImpl();
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
	public List<Assignment> getAssignments() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM assignments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assignment getAssignmentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM assignments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Assignment assignment = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return assignment;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Assignment> getAssignmentsByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM assignments where course_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	    
}