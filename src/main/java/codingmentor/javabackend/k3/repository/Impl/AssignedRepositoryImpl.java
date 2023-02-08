package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import codingmentor.javabackend.k3.mapper.AssignedMapper;
import codingmentor.javabackend.k3.model.Assigned;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.AssignedRepository;

public class AssignedRepositoryImpl extends AbstractRepository<Assigned> implements AssignedRepository{
	private static AssignedRepository repository = null;
	private final AssignedMapper mapper;
	private final Logger logger;
	
	private AssignedRepositoryImpl() {
		mapper = new AssignedMapper();
		logger = LogManager.getLogger("codingmentor");
	}
	 
	public static AssignedRepository getInstance() {
    	if (repository == null) {
    		repository = new AssignedRepositoryImpl();
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
    		logger.error(e.getMessage());
    	}
    }

    
	/*
	 * GET LIST METHOD
	 */

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Assigned> getAssigneds() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM assigneds";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssigneds: " + statement);
			List<Assigned> assignedsList = new ArrayList<>();
			while(results.next()) {
				assignedsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignedsList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Assigned> getAssignedsByAssignmentsInKlass(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.id as AssignmentId, AG.* FROM assignments as A\r\n"
					+ "	LEFT JOIN assigneds as AG\r\n"
					+ "	ON A.id = AG.assignment_id\r\n"
					+ " where A.klass_id = ?;\r\n";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignedsByAssignmentsInKlass: " + statement);
			List<Assigned> assignedsList = new ArrayList<>();
			while(results.next()) {
				if (results.getTimestamp("due_date") == null) {
					assignedsList.add(new Assigned());
				} else {
					assignedsList.add(mapper.map(results));
				}
			}
			close(connection, statement, results);
			return assignedsList;
		});
	}
	
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Assigned> getAssignedsByAssignmentsInCourse(int courseId, int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.id as AssignmentId, AG.* FROM assignments as A\r\n"
					+ "	LEFT JOIN assigneds as AG\r\n"
					+ "	ON A.id = AG.assignment_id and AG.klass_id = ?\r\n"
					+ " where A.course_id = ?;\r\n";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			statement.setInt(2, courseId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignedsByAssignmentsInCourse: " + statement);
			List<Assigned> assignedsList = new ArrayList<>();
			while(results.next()) {
				if (results.getTimestamp("due_date") == null) {
					assignedsList.add(new Assigned());
				} else {
					assignedsList.add(mapper.map(results));
				}
			}
			close(connection, statement, results);
			return assignedsList;
		});
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public List<Assigned> getAssignedsByUserInKlassOrderByDueDate(int userId, int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.* FROM assigneds AS A\r\n"
					+ "	LEFT JOIN assigned_graders as AG\r\n"
					+ "	ON AG.assigned_id = A.id\r\n"
					+ " WHERE A.klass_id = ? and AG.user_id = ? ORDER BY A.due_date DESC;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			statement.setInt(2, userId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignedsByUserInKlassOrderByDueDate: " + statement);
			List<Assigned> assignedsList = new ArrayList<>();
			while(results.next()) {
				assignedsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignedsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assigned getAssignedById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM assigneds WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getAssignedById: " + statement);
		    Assigned Assigned = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return Assigned;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assigned getAssignedByAssignedGraderId(int assignedGraderId) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT A.* FROM assigned_graders as AG \r\n"
					+ "	INNER JOIN assigneds as A\r\n"
					+ "    ON A.id = AG.assigned_id and AG.id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, assignedGraderId);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getAssignedByAssignedGraderId: " + statement);
		    Assigned Assigned = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return Assigned;
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
	public int insertAssigned(int assignment_id, int klass_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, int repo_id, boolean limit_resubmissions, int allow_resubmissions) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO `assigneds` (`assignment_id`, `klass_id`, `due_date`, `allow_late_submissions`, `max_contributors`, `repo_id`, `limit_resubmissions`, `allow_resubmissions`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 statement.setInt(1, assignment_id); 
			 statement.setInt(2, klass_id); 
			 statement.setTimestamp(3, Timestamp.valueOf(due_date));
			 statement.setBoolean(4, allow_late_submissions); 
			 statement.setInt(5, max_contributors); 
			 statement.setInt(6, repo_id); 
			 statement.setBoolean(7, limit_resubmissions); 
			 statement.setInt(8, allow_resubmissions); 
			logger.info("-- insertAssigned: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Assigned failed, no rows affected.");
			}
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
            
			close(connection, statement, generatedKeys);
			return 0;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertAssignedResubmissionLimit(int assignment_id, int klass_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, int repo_id, boolean limit_resubmissions, int resubmission_limit, int allow_resubmissions) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO `assigneds` (`assignment_id`, `klass_id`, `due_date`, `allow_late_submissions`, `max_contributors`, `repo_id`, `limit_resubmissions`, `resubmission_limit`, `allow_resubmissions`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 statement.setInt(1, assignment_id); 
			 statement.setInt(2, klass_id); 
			 statement.setTimestamp(3, Timestamp.valueOf(due_date));
			 statement.setBoolean(4, allow_late_submissions); 
			 statement.setInt(5, max_contributors); 
			 statement.setInt(6, repo_id); 
			 statement.setBoolean(7, limit_resubmissions);
			 statement.setInt(8, resubmission_limit);
			 statement.setInt(9, allow_resubmissions); 
			logger.info("-- insertAssignedResubmissionLimit: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Assigned failed, no rows affected.");
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAssigned(int assigned_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, boolean limit_resubmissions, int allow_resubmissions) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE assigneds SET due_date = ?, allow_late_submissions = ?, max_contributors = ?, limit_resubmissions = ?, allow_resubmissions = ?  WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setTimestamp(1, Timestamp.valueOf(due_date));
			statement.setBoolean(2, allow_late_submissions); 
			statement.setInt(3, max_contributors); 
			statement.setBoolean(4, limit_resubmissions); 
			statement.setInt(5, allow_resubmissions); 
			statement.setInt(6, assigned_id); 
			logger.info("-- updateAssigned: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);
			return result;
		}) != 0;
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAssignedResubmissionLimit(int assigned_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, boolean limit_resubmissions, int resubmission_limit, int allow_resubmissions) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE assigneds SET due_date = ?, allow_late_submissions = ?, max_contributors = ?, limit_resubmissions = ?, resubmission_limit = ?, allow_resubmissions = ?  WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setTimestamp(1, Timestamp.valueOf(due_date));
			statement.setBoolean(2, allow_late_submissions); 
			statement.setInt(3, max_contributors);  
			statement.setBoolean(4, limit_resubmissions);
			statement.setInt(5, resubmission_limit);
			statement.setInt(6, allow_resubmissions); 
			statement.setInt(7, assigned_id); 
			logger.info("-- updateAssignedResubmissionLimit: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);
			return result;
		}) != 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAssignedAdjustment(int assignedId, Double max_points_override, Double point_value_scale) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE assigneds SET max_points_override = ?, point_value_scale = ?  WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setObject(1, max_points_override, Types.DOUBLE);
			statement.setObject(2, point_value_scale, Types.DOUBLE);
			statement.setInt(3, assignedId);  
			logger.info("-- updateAssignedAdjustment: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);
			return result;
		}) != 0;
	}	    
}