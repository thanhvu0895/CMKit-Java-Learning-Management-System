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
			final String query = "SELECT * FROM assignments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Course> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Course getCourseById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM assignments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Course assignment = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return assignment;
    	});
	}
	    
}