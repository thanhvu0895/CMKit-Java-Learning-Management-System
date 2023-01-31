package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.AssignmentMapper;
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.AssignmentRepository;

public class AssignmentRepositoryImpl extends AbstractRepository<Assignment> implements AssignmentRepository{
	private static AssignmentRepository repository = null;
	private final AssignmentMapper mapper;
	private AssignmentRepositoryImpl() {
		mapper = new AssignmentMapper();
	}
	 
	public static AssignmentRepository getInstance() {
    	if (repository == null) {
    		repository = new AssignmentRepositoryImpl();
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
	public List<Assignment> getAssignments() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM assignments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getAssignments(): " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
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
	public List<Assignment> getAssignmentsByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM assignments where course_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			System.out.println("getAssignmentsByCourseId: " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assignment getAssignmentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM assignments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getAssignmentById: " + statement);
		    Assignment assignment = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return assignment;
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
	public int insertAssignment(String title, int course_id, Integer grade_category_id, int files_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO assignments"
					+ " (title, course_id, grade_category_id, files_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setInt(2, course_id);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, files_repo_id);
			statement.setInt(5, assignment_type);
			statement.setString(6, permitted_filetypes);
			statement.setString(7, description);
			statement.setInt(8, file_limit);
			statement.setInt(9, file_or_link);
			System.out.println("insertAssignment: " + statement);
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
	
	// PATCH(UPDATE)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertStudentRepoAssignment(String title, int course_id, Integer grade_category_id, 
			int files_repo_id, int template_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit,int file_or_link) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO assignments"
					+ " (title, course_id, grade_category_id, files_repo_id, template_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setInt(2, course_id);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, files_repo_id);
			statement.setInt(5, template_repo_id);
			statement.setInt(6, assignment_type);
			statement.setString(7, permitted_filetypes);
			statement.setString(8, description);
			statement.setInt(9, file_limit);
			statement.setInt(10, file_or_link);
			System.out.println("insertStudentRepoAssignment: " + statement);
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
	public boolean updateStudentFileAssignmentById(String title, String description, Integer grade_category_id,
			int file_or_link, String permitted_filetypes, int file_limit, int assignmentId) {
		return executeUpdate(connection -> {
			final String query = "UPDATE assignments SET title = ?, description= ?, grade_category_id = ?, file_or_link = ?, permitted_filetypes = ?, file_limit = ?  WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, file_or_link);
			statement.setString(5, permitted_filetypes);
			statement.setInt(6, file_limit);
			statement.setInt(7, assignmentId);
			 System.out.println("updateStudentFileAssignmentById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAssignmentById(String title, String description, Integer grade_category_id, int assignmentId) {
		return executeUpdate(connection -> {
			final String query = "UPDATE assignments SET title = ?, description= ?, grade_category_id = ? WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, assignmentId);
			 System.out.println("updateStudentFileAssignmentById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
	
	// PATCH(UPDATE)
	
	
	
}