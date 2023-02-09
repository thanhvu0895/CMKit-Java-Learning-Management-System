package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.GraderMapper;
import codingmentor.javabackend.k3.model.Grader;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class GraderRepository extends BaseRepository<GraderMapper, Grader> {
	private static GraderRepository repository;
	
	public static synchronized GraderRepository getInstance() {
    	if (repository == null) {
    		repository = new GraderRepository();
    	}
    	
    	return repository;
    }
	
	private GraderRepository() {
		super(new GraderMapper());
	}
    
	/*
	 * GET LIST METHOD
	 */
    	
    /**
	 * {@inheritDoc}
	 */
    

	public List<Grader> getGradersByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM graders WHERE klass_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGradersByKlassId: " + statement);
			List<Grader> gradersList = new ArrayList<>();
			while(results.next()) {
				gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradersList;
		});
	}

	public List<Grader> getGradersByAssignedStatusInKlass(int assignedId, int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT G.id, G.user_id, G.klass_id, G.notify_grader_assigned, AG.id as assigned_status FROM graders as G\r\n"
					+ "	LEFT JOIN assigned_graders AG\r\n"
					+ "	ON AG.user_id = G.user_id and assigned_id = ?\r\n"
					+ " WHERE G.klass_id = ?; ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignedId);
			statement.setInt(2, klassId);;
			ResultSet results = statement.executeQuery();
			logger.info("-- getGradersByAssignedInKlass: " + statement);
			List<Grader> gradersList = new ArrayList<>();
			while(results.next()) {
				gradersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradersList;
		});
	}
	
	public List<Grader> getGraders() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM graders";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGraders: " + statement);
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
	
	public Grader getGraderById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM graders WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getGraderById: " + statement);
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

	public int insertGrader (int user_id, int klass_id) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO `graders` (`user_id`, `klass_id`) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user_id);
			statement.setInt(2, klass_id);
			logger.info("-- insertGrader: " + statement);
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