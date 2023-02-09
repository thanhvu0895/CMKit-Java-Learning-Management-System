package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.GradeCategoryMapper;
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class GradeCategoryRepository extends BaseRepository<GradeCategoryMapper, GradeCategory> {
	
	private static GradeCategoryRepository repository;
	
	public static synchronized GradeCategoryRepository getInstance() {
    	if (repository == null) {
    		repository = new GradeCategoryRepository();
    	}
    	
    	return repository;
    }
	
	private GradeCategoryRepository() {
		super(new GradeCategoryMapper());
	}
	
	
	/*
	 * GET LIST METHOD
	 */
	
	public List<GradeCategory> getGradeCategoriesByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT GC.id, GC.title, GC.klass_id, GC.course_id, GC.weight FROM grade_categories as GC\r\n"
					+ " INNER JOIN courses as C \r\n"
					+ "	ON GC.course_id = C.id and C.id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGradeCategoriesByCourseId: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	
	public List<GradeCategory> getGradeCategoriesUsedByAssignmentsInCourse(int courseId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.id as AssignmentId, GC.id, GC.weight, GC.title, GC.klass_id, GC.course_id FROM assignments as A\r\n"
					+ " LEFT JOIN grade_categories as GC\r\n"
					+ " ON A.grade_category_id = GC.id\r\n"
					+ " where A.course_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGradeCategoriesUsedByAssignmentsInCourse: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	

	public List<GradeCategory> getGradeCategoriesUsedByAssignmentsInKlass(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT A.id as AssignmentId, GC.id, GC.weight, GC.title, GC.klass_id, GC.course_id FROM assignments as A\r\n"
					+ " LEFT JOIN grade_categories as GC\r\n"
					+ " ON A.grade_category_id = GC.id\r\n"
					+ " where A.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGradeCategoriesUsedByAssignmentsInKlass: " + statement);
			List<GradeCategory> gradeCategoryList = new ArrayList<>();
			while(results.next()) {
				gradeCategoryList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return gradeCategoryList;
		});
	}
	
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
			logger.info("-- getGradeCategoriesUsedByAssignmentInCourse: " + statement);
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
	
	public GradeCategory getGradeCategoryById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT* FROM grade_categories WHERE id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    logger.info("-- getGradeCategoryById: " + statement);
		    ResultSet results = statement.executeQuery();
		    GradeCategory gradeCategory = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return gradeCategory;
    	});
	}
	
	public GradeCategory getGradeCategoryByAssignmentId(int assignmentId) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT GC.* FROM grade_categories as GC\r\n"
					+ "	INNER JOIN assignments as A \r\n"
					+ " ON GC.id = A.grade_category_id and A.id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, assignmentId);
		    logger.info("-- getGradeCategoryById: " + statement);
		    ResultSet results = statement.executeQuery();
		    GradeCategory gradeCategory = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return gradeCategory;
    	});
	}	
	
	/*
	 * GET Check True/false METHOD
	 */

	
	public boolean isUsedByAssignment(int gradeCategoryId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 as ONE FROM assignments AS A\r\n"
			 		+ " INNER JOIN grade_categories AS GC\r\n"
			 		+ "	ON A.grade_category_id = GC.id AND GC.id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, gradeCategoryId);
			 ResultSet results = statement.executeQuery();
			 logger.info("-- isUsedByAssignment" + statement);
			 GradeCategory gradeCategory = (results.next()) ? new GradeCategory() : null;
			 close(connection, statement, results);
			 return gradeCategory;
		}) != null;
	}
	

	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)

	
	public boolean insertGradeCategory (String title, int course_id, double weight) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO grade_categories (title, course_id, weight)"
					+ " VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setInt(2, course_id);
			statement.setDouble(3, weight);
			logger.info("-- insertGradeCategory: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}
		
	//PATCH(UPDATE)

	public boolean updateGradeCategoryById(String title, double weight, int gradeCategoryId) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE grade_categories SET title = ?, weight = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 statement.setDouble(2, weight);
			 statement.setInt(3, gradeCategoryId);
			 logger.info("-- updateGradeCategoryById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
	
	
	// deleteProfessor

	public boolean deleteGradeCategory(int gradeCategoryId) {
		return executeUpdate(connection -> {
			 final String query = "\nDELETE FROM grade_categories WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, gradeCategoryId);
			 logger.info("-- deleteGradeCategory: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}


}