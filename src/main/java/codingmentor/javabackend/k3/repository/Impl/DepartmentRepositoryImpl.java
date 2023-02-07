package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.DepartmentMapper;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;

public class DepartmentRepositoryImpl extends AbstractRepository<Department> implements DepartmentRepository{
	private static DepartmentRepository repository = null;
	private final DepartmentMapper mapper;
	
	private DepartmentRepositoryImpl() {
		mapper = new DepartmentMapper();
	}
	 
	public static DepartmentRepository getInstance() {
    	if (repository == null) {
    		repository = new DepartmentRepositoryImpl();
    	}
    	return repository;
    }
   
	/**
	 * close connection to save resources
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
	public List<Department> getDepartments() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM departments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getDepartments: " + statement);
			List<Department> departmentsList = new ArrayList<>();
			while(results.next()) {
				departmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return departmentsList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Department> getDepartmentsByDPUserId(int userId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT D.id, D.title, D.repo_id FROM departments AS D "
					+ "INNER JOIN department_professors AS DP ON DP.department_id = D.id AND DP.user_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getDepartmentsByDPUserId: " + statement);
			List<Department> departmentsList = new ArrayList<>();
			while(results.next()) {
				departmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return departmentsList;
		});
	}
	

	/*
	 * GET ITEM METHOD
	 */
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public Department getDepartmentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM departments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("-- getDepartmentById: " + statement);
		    Department department = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return department;
    	});
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Department getDepartmentByCourseId(int courseId) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT D.id, D.title,D.repo_id FROM "
					+ "departments as D \n"
					+ "	INNER JOIN courses as C \n"
					+ "ON D.id = C.department_id and C.id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, courseId);
		    ResultSet results = statement.executeQuery();
		    System.out.println("-- getDepartmentByCourseId: " + statement);
		    Department department = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return department;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existedByTitle(String title) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 as ONE FROM departments WHERE title = ?  LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 ResultSet results = statement.executeQuery();
			 System.out.println("-- existedByTitle" + statement);
			 Department department = (results.next()) ? new Department() : null;
			 close(connection, statement, results);
			 return department;
		}) != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDepartmentAdmin(int userId, int departmentId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT \r\n"
			 		+ "	 1 as ONE\r\n"
			 		+ "FROM  department_professors as DP \r\n"
			 		+ "INNER JOIN users as U\r\n"
			 		+ "	ON DP.user_id = U.id and U.id = ? and DP.admin = 1\r\n"
			 		+ "INNER JOIN departments as D\r\n"
			 		+ "	ON DP.department_id = D.id AND D.id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, departmentId);
			 ResultSet results = statement.executeQuery();
			 System.out.println("-- isDepartmentAdmin: " +statement);
			 Department department = results.next() ? new Department() : null;
			 close(connection, statement, results);
			 return department;
		}) != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDepartmentProfessor(int userId, int departmentId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT \r\n"
			 		+ "	1 as ONE\r\n"
			 		+ "FROM  department_professors as DP \r\n"
			 		+ "INNER JOIN users as U\r\n"
			 		+ "	ON DP.user_id = U.id and U.id = ? and DP.admin = 1\r\n"
			 		+ "INNER JOIN departments as D\r\n"
			 		+ "	ON DP.department_id = D.id AND D.id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, departmentId);
			 ResultSet results = statement.executeQuery();
			 System.out.println("-- isDepartmentProfessor: " + statement);
			 Department department = results.next() ? new Department() : null;
			 close(connection, statement, results);
			 return department;
		}) != null;
	}

	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertDepartment(String title, int repo_id) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO departments (title, repo_id) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setInt(2, repo_id);
			System.out.println("-- insertDepartment: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Department failed, no rows affected.");
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
	public boolean updateDepartmentTitleById(String title, int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE departments SET title = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 statement.setInt(2, id);
			 System.out.println("-- updateDepartmentTitleById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}
