package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.DepartmentProfessorMapper;
import codingmentor.javabackend.k3.model.DepartmentProfessor;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.DepartmentProfessorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DepartmentProfessorRepositoryImpl extends AbstractRepository<DepartmentProfessor> implements DepartmentProfessorRepository{
	private static DepartmentProfessorRepository repository = null;
	private final DepartmentProfessorMapper mapper;
	private final Logger logger;
	
	private DepartmentProfessorRepositoryImpl() {
		mapper = new DepartmentProfessorMapper();
		logger = LogManager.getLogger("codingmentor");
	}
	 
	public static DepartmentProfessorRepository getInstance() {
    	if (repository == null) {
    		repository = new DepartmentProfessorRepositoryImpl();
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
	public List<DepartmentProfessor> getDepartmentProfessors() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM department_professors";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getDepartmentProfessors: " + statement);
			List<DepartmentProfessor> departmentProfessorsList = new ArrayList<>();
			while(results.next()) {
				departmentProfessorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return departmentProfessorsList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override   
	public List<DepartmentProfessor>  getDepartmentProfessorsByDepartmentId(int departmentId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM department_professors where department_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, departmentId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getDepartmentProfessorsByDepartmentId: " + statement);
			List<DepartmentProfessor> departmentProfessorsList = new ArrayList<>();
			while(results.next()) {
				departmentProfessorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return departmentProfessorsList;
		});
	}
	
	
	/*
	 * GET ITEM METHOD
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DepartmentProfessor getDepartmentProfessorById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM department_professors WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getDepartmentProfessorById: " + statement);
		    DepartmentProfessor departmentProfessor = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return departmentProfessor;
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
	public int insertDepartmentProfessor(int user_id, int department_id, boolean admin) {
		return executeUpdate(connection -> {
			final String query = "\n INSERT INTO department_professors (`user_id`, `department_id`, `admin`) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user_id);
			statement.setInt(2, department_id);
			statement.setBoolean(3, admin);
			logger.info("-- insertDepartmentProfessor: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Department Professor failed, no rows affected.");
			}
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
            
			close(connection, statement, generatedKeys);
			return 0;
		});
	}
	
	//PATCH(UPDATE)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAdminByDepartmentProfessorId(boolean admin, int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE department_professors SET admin = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setBoolean(1, admin);
			 statement.setInt(2, id);
			 logger.info("-- updateAdminByDepartmentProfessorId: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	// deleteProfessor
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteDepartmentProfessor(int departmentProfessorId) {
		return executeUpdate(connection -> {
			 final String query = "\n DELETE FROM department_professors WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, departmentProfessorId);
			 logger.info("-- deleteDepartmentProfessor: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
		
	}

}