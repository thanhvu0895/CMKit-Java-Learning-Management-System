package codingmentor.javabackend.k3.repository.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.RubricItemMapper;
import codingmentor.javabackend.k3.model.RubricItem;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class RubricItemRepository extends BaseRepository<RubricItemMapper, RubricItem> {
	private static RubricItemRepository repository;
	
	public static synchronized RubricItemRepository getInstance() {
    	if (repository == null) {
    		repository = new RubricItemRepository();
    	}
    	
    	return repository;
    }
	
	private RubricItemRepository() {
		super(new RubricItemMapper());
	}

	/*
	 * GET LIST METHOD
	 */
    
	public List<RubricItem> getRubricItems() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM rubric_items";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getRubricItems: " + statement);
			List<RubricItem> rubricItemsList = new ArrayList<>();
			while(results.next()) {
				rubricItemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return rubricItemsList;
		});
	}

	public List<RubricItem> getRubricItemsByProblemId(int problem_id) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM rubric_items where problem_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			ResultSet results = statement.executeQuery();
			logger.info("-- getRubricItemsByAssignmentId: " + statement);
			List<RubricItem> rubricItemsList = new ArrayList<>();
			while(results.next()) {
				rubricItemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return rubricItemsList;
		});
	}
	
	public List<RubricItem> getRubricItemsByProblemIdOrderByLocationAsc(int problem_id) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM rubric_items where problem_id = ? ORDER BY location ASC";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			ResultSet results = statement.executeQuery();
			logger.info("-- getRubricItemsByAssignmentId: " + statement);
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

	public RubricItem getRubricItemById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM rubric_items WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getRubricItemById: " + statement);
		    RubricItem rubricItem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return rubricItem;
    	});
	}
	
	public RubricItem getMaxRubricItemByProblemId(int problem_id) {
		return executeQuerySingle(connection -> {
			final String query = " \nSELECT * FROM rubric_items WHERE location = ( \nSELECT MAX(location) FROM rubric_items where problem_id = ?) and problem_id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, problem_id);
		    statement.setInt(2, problem_id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getMaxRubricItemByProblemId: " + statement);
		    RubricItem rubricitem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return rubricitem;
    	});
	}

	public RubricItem getRubricItemByLocationAndProblemId(int location, int problem_id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM rubric_items WHERE location = ? and problem_id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, location);
		    statement.setInt(2, problem_id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getRubricItemByLocationAndProblemId: " + statement);
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

	public boolean insertRubricItem(int problem_id, String title, double points, int location) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO rubric_items (problem_id, title, points, location) VALUES (?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			statement.setString(2, title);
			statement.setDouble(3, points);
			statement.setInt(4, location);
			logger.info("-- insertRubricItem: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}

	//PATCH

	public boolean updateRubricItemById(String title, double points, int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE rubric_items SET title = ?, points = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 statement.setDouble(2, points);
			 statement.setInt(3, id);
			 logger.info("-- updateRubricItemsById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updateRubricItemLocationById(int location, int rubricItemId) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE rubric_items SET location = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, location);
			 statement.setInt(2, rubricItemId);
			 logger.info("-- updateRubricItemLocationById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}	
}