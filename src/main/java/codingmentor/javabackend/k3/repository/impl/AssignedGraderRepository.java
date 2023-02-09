package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.AssignedGraderMapper;
import codingmentor.javabackend.k3.model.AssignedGrader;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class AssignedGraderRepository extends BaseRepository<AssignedGraderMapper, AssignedGrader> {
	
	private static AssignedGraderRepository repository;
	
	public static synchronized AssignedGraderRepository getInstance() {
    	if (repository == null) {
    		repository = new AssignedGraderRepository();
    	}
    	
    	return repository;
    }
	
	private AssignedGraderRepository() {
		super(new AssignedGraderMapper());
	}

	 /** @category LIST */
	public List<AssignedGrader> getAssignedGraders() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM assigned_graders";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignedGraders: " + statement);
			List<AssignedGrader> assigned_gradersList = new ArrayList<>();
			while(results.next()) {
				assigned_gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assigned_gradersList;
		});
	}
	
	/** @category LIST */
	public List<AssignedGrader> getAssignedGradersByAssignedId(int assignedId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM assigned_graders where assigned_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignedId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignedGraders: " + statement);
			List<AssignedGrader> assigned_gradersList = new ArrayList<>();
			while(results.next()) {
				assigned_gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assigned_gradersList;
		});
	}	

	/** @category ITEM */
	public AssignedGrader getAssignedGraderById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM assigned_graders WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getAssignedGraderById: " + statement);
		    AssignedGrader department = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return department;
    	});
	}
	
	/** @category POST */
	public boolean insertAssignedGrader(int user_id , int assigned_id) {
		return executeUpdate(connection -> {
			final String query = "\n INSERT INTO `assigned_graders` (`user_id`, `assigned_id`) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, user_id);
			statement.setInt(2, assigned_id);
			logger.info("-- insertAssignedGrader: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}

	/** @category DELETE*/
	public boolean deleteAssignedGrader(int assignedGraderId) {
		return executeUpdate(connection -> {
			 final String query = "\nDELETE FROM assigned_graders WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, assignedGraderId);
			 logger.info("-- deleteAssignedGrader: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}
