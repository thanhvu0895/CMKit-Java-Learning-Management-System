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

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Department> getDepartments() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM departments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
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
	public Department getDepartmentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM departments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Department department = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return department;
    	});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertDepartment(String title, int repo_id) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO departments (title, repo_id) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setInt(2, repo_id);
			System.out.println(statement);
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existedByTitle(String title) {
		return executeQuerySingle(connection -> {
			 final String query = "SELECT title FROM departments WHERE title = ?  LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 ResultSet results = statement.executeQuery();
			 System.out.println(statement);
			 Department department = (results.next() && results.getString("title").equals(title)) ? new Department() : null;
			 close(connection, statement, results);
			 return department;
		}) != null;
	}
	
}
