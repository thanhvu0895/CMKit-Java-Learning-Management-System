package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.CourseMapper;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.CourseRepository;

public class CourseRepositoryImpl extends AbstractRepository<Course> implements CourseRepository{
	private static CourseRepository repository = null;
	private final CourseMapper mapper;
	private CourseRepositoryImpl() {
		mapper = new CourseMapper();
	}
	 
	public static CourseRepository getInstance() {
    	if (repository == null) {
    		repository = new CourseRepositoryImpl();
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
	public List<Course> getCourses() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM courses";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Course> coursesList = new ArrayList<>();
			while(results.next()) {
				coursesList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return coursesList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Course getCourseById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM courses WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Course course = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return course;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Course> getCourseByDepartmentId(int departmentId) {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM courses where department_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, departmentId);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Course> coursesList = new ArrayList<>();
			while(results.next()) {
				coursesList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return coursesList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertCourse(String title, String course_code, int repo_id, int department_id, boolean active) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO courses (title, course_code, repo_id, department_id, active) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setString(2, course_code);
			statement.setInt(3, repo_id);
			statement.setInt(4, department_id);
			statement.setBoolean(5, active);
			System.out.println(statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Course failed, no rows affected.");
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