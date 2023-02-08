package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.StudentMapper;
import codingmentor.javabackend.k3.model.Student;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.StudentRepository;

public class StudentRepositoryImpl extends AbstractRepository<Student> implements StudentRepository{
	private static StudentRepository repository = null;
	private final StudentMapper mapper;
	private StudentRepositoryImpl() {
		mapper = new StudentMapper();
	}
	 
	public static StudentRepository getInstance() {
    	if (repository == null) {
    		repository = new StudentRepositoryImpl();
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
	public List<Student> getStudents() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM students";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getStudents: " + statement);
			List<Student> studentsList = new ArrayList<>();
			while(results.next()) {
				studentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return studentsList;
		});
	}
	
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Student> getStudentsByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM students WHERE klass_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getStudentsByKlassId: " + statement);
			List<Student> studentsList = new ArrayList<>();
			while(results.next()) {
				studentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return studentsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Student getStudentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM students WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("-- getStudentById: " + statement);
		    Student student = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return student;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	 public int insertStudent (int user_id, int klass_id) {
		 return executeUpdate(connection -> {
				final String query = "\nINSERT INTO `students` (`user_id`, `klass_id`) VALUES (?, ?);";
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, user_id);
				statement.setInt(2, klass_id);
				System.out.println("-- insertStudent: " + statement);
				ResultSet rs = statement.getGeneratedKeys();
				rs.next();
				int affectedRows = statement.executeUpdate();
				
				if (affectedRows == 0) {
					throw new SQLException("Creating Student failed, no rows affected.");
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