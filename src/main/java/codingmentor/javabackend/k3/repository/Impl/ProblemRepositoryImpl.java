package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.ProblemMapper;
import codingmentor.javabackend.k3.model.Problem;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.ProblemRepository;

public class ProblemRepositoryImpl extends AbstractRepository<Problem> implements ProblemRepository{
	private static ProblemRepository repository = null;
	private final ProblemMapper mapper;
	private ProblemRepositoryImpl() {
		mapper = new ProblemMapper();
	}
	 
	public static ProblemRepository getInstance() {
    	if (repository == null) {
    		repository = new ProblemRepositoryImpl();
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
	public List<Problem> getProblems() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM problems";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getProblems: " + statement);
			List<Problem> problemsList = new ArrayList<>();
			while(results.next()) {
				problemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return problemsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Problem getProblemById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM problems WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getProblemById: " + statement);
		    Problem problem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return problem;
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
	public int insertProblem (int assignment_id, String title, Double points, int location, String grader_notes) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO problems (assignment_id, title, points, location, grader_notes) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, assignment_id);
			statement.setString(2, title);
			statement.setDouble(3, points);
			statement.setInt(4, location);
			statement.setString(5, grader_notes);
			System.out.println("insertProblemrepository: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Problem failed, no rows affected.");
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
	    
}