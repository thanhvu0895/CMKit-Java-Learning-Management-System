package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.GradeCategoryMapper;
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.GradeCategoryRepository;

public class GradeCategoryRepositoryImpl extends AbstractRepository<GradeCategory> implements GradeCategoryRepository{
	private static GradeCategoryRepository repository = null;
	private final GradeCategoryMapper mapper;
	private GradeCategoryRepositoryImpl() {
		mapper = new GradeCategoryMapper();
	}
	 
	public static GradeCategoryRepository getInstance() {
    	if (repository == null) {
    		repository = new GradeCategoryRepositoryImpl();
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
	public List<GradeCategory> getGradeCategoriesByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT GC.id, GC.title, GC.klass_id, GC.course_id, GC.weight FROM grade_categories as GC\r\n"
					+ " INNER JOIN courses as C \r\n"
					+ "	ON GC.course_id = C.id and C.id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getGradeCategoriesByCourseId: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GradeCategory> getGradeCategoriesUsedByAssignmentsInCourse(int courseId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.id as AssignmentId, GC.id, GC.weight, GC.title, GC.klass_id, GC.course_id FROM assignments as A\r\n"
					+ " LEFT JOIN grade_categories as GC\r\n"
					+ " ON A.grade_category_id = GC.id\r\n"
					+ " where A.course_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getGradeCategoriesUsedByAssignmentsInCourse: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GradeCategory> getGradeCategoriesUsedByAssignmentsInKlass(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.id as AssignmentId, GC.id, GC.weight, GC.title, GC.klass_id, GC.course_id FROM assignments as A\r\n"
					+ " LEFT JOIN grade_categories as GC\r\n"
					+ " ON A.grade_category_id = GC.id\r\n"
					+ " where A.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getGradeCategoriesUsedByAssignmentsInKlass: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GradeCategory> getGradeCategoriesUsedByAssignmentInCourse(int courseId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT GC.title, GC.id, GC.title, GC.klass_id, GC.course_id, GC.weight FROM grade_categories as GC\r\n"
					+ " INNER JOIN assignments as A\r\n"
					+ "	ON A.grade_category_id = GC.id\r\n"
					+ " INNER JOIN courses as C \r\n"
					+ "	ON GC.course_id = C.id and C.id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			System.out.println("-- getGradeCategoriesUsedByAssignmentInCourse: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public GradeCategory getGradeCategoryById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT* FROM grade_categories WHERE id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    System.out.println("-- getGradeCategoryById: " + statement);
		    ResultSet results = statement.executeQuery();
		    GradeCategory gradeCategory = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return gradeCategory;
    	});
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public GradeCategory getGradeCategoryByAssignmentId(int assignmentId) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT GC.* FROM grade_categories as GC\r\n"
					+ "	INNER JOIN assignments as A \r\n"
					+ " ON GC.id = A.grade_category_id and A.id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, assignmentId);
		    System.out.println("-- getGradeCategoryById: " + statement);
		    ResultSet results = statement.executeQuery();
		    GradeCategory gradeCategory = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return gradeCategory;
    	});
	}	
	
	/*
	 * GET Check True/false METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUsedByAssignment(int gradeCategoryId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 as ONE FROM assignments AS A\r\n"
			 		+ " INNER JOIN grade_categories AS GC\r\n"
			 		+ "	ON A.grade_category_id = GC.id AND GC.id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, gradeCategoryId);
			 ResultSet results = statement.executeQuery();
			 System.out.println("-- isUsedByAssignment" + statement);
			 GradeCategory gradeCategory = (results.next()) ? new GradeCategory() : null;
			 close(connection, statement, results);
			 return gradeCategory;
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
	public boolean insertGradeCategory (String title, int course_id, double weight) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO grade_categories (title, course_id, weight)"
					+ " VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setInt(2, course_id);
			statement.setDouble(3, weight);
			System.out.println("-- insertGradeCategory: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}
		
	//PATCH(UPDATE)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateGradeCategoryById(String title, double weight, int gradeCategoryId) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE grade_categories SET title = ?, weight = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 statement.setDouble(2, weight);
			 statement.setInt(3, gradeCategoryId);
			 System.out.println("-- updateGradeCategoryById: " + statement);
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
	public boolean deleteGradeCategory(int gradeCategoryId) {
		return executeUpdate(connection -> {
			 final String query = "\nDELETE FROM grade_categories WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, gradeCategoryId);
			 System.out.println("-- deleteGradeCategory: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}


}