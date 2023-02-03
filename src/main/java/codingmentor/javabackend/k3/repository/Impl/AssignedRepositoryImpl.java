package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.AssignedMapper;
import codingmentor.javabackend.k3.model.Assigned;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.AssignedRepository;

public class AssignedRepositoryImpl extends AbstractRepository<Assigned> implements AssignedRepository{
	private static AssignedRepository repository = null;
	private final AssignedMapper mapper;
	private AssignedRepositoryImpl() {
		mapper = new AssignedMapper();
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
	public List<Assigned> getAssigneds() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM assigneds";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getAssigneds: " + statement);
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
			final String query = "SELECT A.id as AssignmentId, AG.* FROM assignments as A\r\n"
					+ "	LEFT JOIN assigneds as AG\r\n"
					+ "	ON A.id = AG.assignment_id\r\n"
					+ " where A.klass_id = ?;\r\n";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			System.out.println("getAssignedsByAssignmentsInKlass: " + statement);
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
	
	/*
	 * GET ITEM METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assigned getAssignedById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM assigneds WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getAssignedById: " + statement);
		    Assigned Assigned = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return Assigned;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assigned getAssignedByAssignmentId(int assignmentId) {
		// TODO Auto-generated method stub
		return null;
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
	public int insertAssigned(int assignment_id, int klass_id, LocalDateTime due_date, boolean allow_late_submissions, int max_contributors, int repo_id, boolean limit_resubmissions, boolean allow_resubmissions) {
		return executeUpdate(connection -> {
			final String query = " INSERT INTO `assigneds` (`assignment_id`, `klass_id`, `due_date`, `allow_late_submissions`, `max_contributors`, `repo_id`, `limit_resubmissions`, `allow_resubmissions`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 statement.setInt(1, assignment_id); 
			 statement.setInt(2, klass_id); 
			 statement.setTimestamp(3, Timestamp.from(due_date.toInstant(ZoneOffset.of("-05:00"))));
			 statement.setBoolean(4, allow_late_submissions); 
			 statement.setInt(5, max_contributors); 
			 statement.setInt(6, repo_id); 
			 statement.setBoolean(7, limit_resubmissions); 
			 statement.setBoolean(8, allow_resubmissions); 
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
	
	//PATCH
	    
}