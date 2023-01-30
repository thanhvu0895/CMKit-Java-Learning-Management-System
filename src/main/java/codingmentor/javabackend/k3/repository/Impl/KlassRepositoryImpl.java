package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.KlassMapper;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;

public class KlassRepositoryImpl extends AbstractRepository<Klass> implements KlassRepository{
	private static KlassRepository repository = null;
	private final KlassMapper mapper;
	private KlassRepositoryImpl() {
		mapper = new KlassMapper();
	}
	 
	public static KlassRepository getInstance() {
    	if (repository == null) {
    		repository = new KlassRepositoryImpl();
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
	public List<Klass> getklasses() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM klasses";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getklasses(): " + statement);
			List<Klass> klassesList = new ArrayList<>();
			while(results.next()) {
				klassesList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return klassesList;
		});
	}

	
	/*
	 * GET ITEM METHOD
	 */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Klass getKlassById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM klasses WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getKlassById: " + statement);
		    Klass klass = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return klass;
    	});
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Klass> getKlassesFromDepartmentId(int departmentId) {
		return executeQuery(connection -> {
			final String query = "SELECT \r\n"
					+ "	K.id, K.course_id, K.repo_id, K.semester, K.section, K.start_date, K.end_date \r\n"
					+ "FROM klasses as K\r\n"
					+ "INNER JOIN courses as C\r\n"
					+ "	ON C.id = K.course_id\r\n"
					+ "INNER JOIN departments as D\r\n"
					+ "	ON D.id = C.department_id AND D.id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, departmentId);
			ResultSet results = statement.executeQuery();
			System.out.println("getKlassesFromDepartmentId: " + statement);
			List<Klass> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
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
	public int insertKlass (int course_id, int repo_id, String semester, Integer section, LocalDate startDate, LocalDate endDate) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO klasses (course_id, repo_id, semester, section, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, course_id);
			statement.setInt(2, repo_id);
			statement.setString(3, semester);
			statement.setObject(4, section, Types.INTEGER);
			statement.setDate(5, Date.valueOf(startDate));
			statement.setDate(6, Date.valueOf(endDate));
			System.out.println("insertKlass: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Klass failed, no rows affected.");
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
	public boolean updateKlassById(String semester, Integer section, LocalDate startDate, LocalDate endDate, int klassId) {
		return executeUpdate(connection -> {
			 final String query = "UPDATE klasses SET semester = ?, section = ?, start_date = ?, end_date = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, semester);
			 statement.setObject(2, section, Types.INTEGER);
			 statement.setDate(3, Date.valueOf(startDate));
			 statement.setDate(4, Date.valueOf(endDate));
			 statement.setInt(5, klassId);
			 System.out.println("updateKlassById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}