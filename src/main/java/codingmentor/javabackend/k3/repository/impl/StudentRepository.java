package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.StudentMapper;
import codingmentor.javabackend.k3.model.Student;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class StudentRepository extends BaseRepository<StudentMapper, Student> {
	private static StudentRepository repository;
	
	public static synchronized StudentRepository getInstance() {
    	if (repository == null) {
    		repository = new StudentRepository();
    	}
    	
    	return repository;
    }
	
	private StudentRepository() {
		super(new StudentMapper());
	}
	 

    
	/*
	 * GET LIST METHOD
	 */

	public List<Student> getStudents() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM students";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getStudents: " + statement);
			List<Student> studentsList = new ArrayList<>();
			while(results.next()) {
				studentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return studentsList;
		});
	}
	
	public List<Student> getStudentsByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM students WHERE klass_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getStudentsByKlassId: " + statement);
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

	public Student getStudentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM students WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getStudentById: " + statement);
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
				logger.info("-- insertStudent: " + statement);
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