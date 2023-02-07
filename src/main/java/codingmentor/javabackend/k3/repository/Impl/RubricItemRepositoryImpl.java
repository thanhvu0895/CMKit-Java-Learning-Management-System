package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.RubricItemMapper;
import codingmentor.javabackend.k3.model.RubricItem;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.RubricItemRepository;

public class RubricItemRepositoryImpl extends AbstractRepository<RubricItem> implements RubricItemRepository{
	private static RubricItemRepository repository = null;
	private final RubricItemMapper mapper;
	private RubricItemRepositoryImpl() {
		mapper = new RubricItemMapper();
	}
	 
	public static RubricItemRepository getInstance() {
    	if (repository == null) {
    		repository = new RubricItemRepositoryImpl();
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
	public List<RubricItem> getRubricItems() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM rubric_items";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getRubricItems: " + statement);
			List<RubricItem> rubricItemsList = new ArrayList<>();
			while(results.next()) {
				rubricItemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return rubricItemsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RubricItem> getRubricItemsByProblemId(int problem_id) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM rubric_items where problem_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getRubricItemsByAssignmentId: " + statement);
			List<RubricItem> rubricItemsList = new ArrayList<>();
			while(results.next()) {
				rubricItemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return rubricItemsList;
		});
	}
	
	@Override
	public List<RubricItem> getRubricItemsByProblemIdOrderByLocationAsc(int problem_id) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM rubric_items where problem_id = ? ORDER BY location ASC";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getRubricItemsByAssignmentId: " + statement);
			List<RubricItem> rubricItemsList = new ArrayList<>();
			while(results.next()) {
				rubricItemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return rubricItemsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RubricItem getRubricItemById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM rubric_items WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("-- getRubricItemById: " + statement);
		    RubricItem rubricItem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return rubricItem;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RubricItem getMaxRubricItemByProblemId(int problem_id) {
		return executeQuerySingle(connection -> {
			final String query = " \nSELECT * FROM rubric_items WHERE location = ( \nSELECT MAX(location) FROM rubric_items where problem_id = ?) and problem_id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, problem_id);
		    statement.setInt(2, problem_id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("-- getMaxRubricItemByProblemId: " + statement);
		    RubricItem rubricitem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return rubricitem;
    	});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RubricItem getRubricItemByLocationAndProblemId(int location, int problem_id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM rubric_items WHERE location = ? and problem_id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, location);
		    statement.setInt(2, problem_id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("-- getRubricItemByLocationAndProblemId: " + statement);
		    RubricItem rubricItem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return rubricItem;
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
	public boolean insertRubricItem(int problem_id, String title, double points, int location) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO rubric_items (problem_id, title, points, location) VALUES (?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			statement.setString(2, title);
			statement.setDouble(3, points);
			statement.setInt(4, location);
			System.out.println("-- insertRubricItem: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}

	//PATCH
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateRubricItemById(String title, double points, int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE rubric_items SET title = ?, points = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 statement.setDouble(2, points);
			 statement.setInt(3, id);
			 System.out.println("-- updateRubricItemsById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateRubricItemLocationById(int location, int rubricItemId) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE rubric_items SET location = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, location);
			 statement.setInt(2, rubricItemId);
			 System.out.println("-- updateRubricItemLocationById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}	
}