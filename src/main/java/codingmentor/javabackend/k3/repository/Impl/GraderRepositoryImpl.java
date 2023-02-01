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
    
	/*
	 * GET LIST METHOD
	 */
    	
    /**
	 * {@inheritDoc}
	 */
    
	@Override
	public List<Grader> getGradersByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM graders WHERE klass_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			System.out.println("getGradersByKlassId: " + statement);
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
	public List<Grader> getGraders() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM graders";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getGraders: " + statement);
			List<Grader> gradersList = new ArrayList<>();
			while(results.next()) {
				gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradersList;
		});
	}

	/*
	 * GET ITEM METHOD
	 */
	
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
		    System.out.println("getGraderById: " + statement);
		    Grader grader = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return grader;
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
	public int insertGrader (int user_id, int klass_id) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO `graders` (`user_id`, `klass_id`) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user_id);
			statement.setInt(2, klass_id);
			System.out.println("insertGrader: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Grader failed, no rows affected.");
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