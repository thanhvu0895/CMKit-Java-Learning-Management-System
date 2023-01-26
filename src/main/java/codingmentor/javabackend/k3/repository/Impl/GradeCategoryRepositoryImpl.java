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
	public List<GradeCategory> getGradeCategories() {
		return null;
//		return executeQuery(connection -> {
//			final String query = "SELECT * FROM gradeCategory";
//			PreparedStatement statement = connection.prepareStatement(query);
//			ResultSet results = statement.executeQuery();
//			System.out.println("getGradeCategory: " + statement);
//			List<GradeCategory> gradeCategoryList = new ArrayList<>();
//			while(results.next()) {
//				gradeCategoryList.add(mapper.map(results));
//			}
//			close(connection, statement, results);
//			return gradeCategoryList;
//		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GradeCategory> getGradeCategoriesByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "SELECT GC.id, GC.title, GC.klass_id, GC.course_id, GC.weight FROM grade_categories as GC\r\n"
					+ " INNER JOIN courses as C \r\n"
					+ "	ON GC.course_id = C.id and C.id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			System.out.println("getGradeCategoriesByCourseId: " + statement);
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
		return null;
//		return executeQuerySingle(connection -> {
//			final String query = "SELECT * FROM gradeCategory WHERE id = ? LIMIT 1;";
//		    PreparedStatement statement = connection.prepareStatement(query);
//		    statement.setInt(1, id);
//		    ResultSet results = statement.executeQuery();
//		    System.out.println("getGradeCategoryById: " + statement);
//		    GradeCategory gradeCategory = (results.next()) ? mapper.map(results) : null;
//		    close(connection, statement, results);
//		    return gradeCategory;
//    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GradeCategory getGradeCategoryByCourseId(int courseId) {
		return null;
//		return executeQuerySingle(connection -> {
//			final String query = "select D.id, D.title,D.repo_id FROM "
//					+ "gradeCategory as D \n"
//					+ "	INNER JOIN courses as C \n"
//					+ "ON D.id = C.gradeCategory_id and C.id = ?;";
//		    PreparedStatement statement = connection.prepareStatement(query);
//		    statement.setInt(1, courseId);
//		    ResultSet results = statement.executeQuery();
//		    System.out.println("getGradeCategoryByCourseId: " + statement);
//		    GradeCategory gradeCategory = (results.next()) ? mapper.map(results) : null;
//		    close(connection, statement, results);
//		    return gradeCategory;
//    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existedByTitle(String title) {
		return false;
//		return executeQuerySingle(connection -> {
//			 final String query = "SELECT 1 as ONE FROM gradeCategory WHERE title = ?  LIMIT 1;";
//			 PreparedStatement statement = connection.prepareStatement(query);
//			 statement.setString(1, title);
//			 ResultSet results = statement.executeQuery();
//			 System.out.println("existedByTitle" + statement);
//			 GradeCategory gradeCategory = (results.next()) ? new GradeCategory() : null;
//			 close(connection, statement, results);
//			 return gradeCategory;
//		}) != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGradeCategoryAdmin(int userId, int gradeCategoryId) {
		return false;
//		return executeQuerySingle(connection -> {
//			 final String query = "SELECT \r\n"
//			 		+ "	 1 as ONE\r\n"
//			 		+ "FROM  gradeCategory_professors as DP \r\n"
//			 		+ "INNER JOIN users as U\r\n"
//			 		+ "	ON DP.user_id = U.id and U.id = ? and DP.admin = 1\r\n"
//			 		+ "INNER JOIN gradeCategory as D\r\n"
//			 		+ "	ON DP.gradeCategory_id = D.id AND D.id = ?;";
//			 PreparedStatement statement = connection.prepareStatement(query);
//			 statement.setInt(1, userId);
//			 statement.setInt(2, gradeCategoryId);
//			 ResultSet results = statement.executeQuery();
//			 System.out.println("isGradeCategoryAdmin: " +statement);
//			 GradeCategory gradeCategory = results.next() ? new GradeCategory() : null;
//			 close(connection, statement, results);
//			 return gradeCategory;
//		}) != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGradeCategoryProfessor(int userId, int gradeCategoryId) {
		return false;
//		return executeQuerySingle(connection -> {
//			 final String query = "SELECT \r\n"
//			 		+ "	1 as ONE\r\n"
//			 		+ "FROM  gradeCategory_professors as DP \r\n"
//			 		+ "INNER JOIN users as U\r\n"
//			 		+ "	ON DP.user_id = U.id and U.id = ? and DP.admin = 1\r\n"
//			 		+ "INNER JOIN gradeCategory as D\r\n"
//			 		+ "	ON DP.gradeCategory_id = D.id AND D.id = ?;";
//			 PreparedStatement statement = connection.prepareStatement(query);
//			 statement.setInt(1, userId);
//			 statement.setInt(2, gradeCategoryId);
//			 ResultSet results = statement.executeQuery();
//			 System.out.println("isGradeCategoryProfessor: " + statement);
//			 GradeCategory gradeCategory = results.next() ? new GradeCategory() : null;
//			 close(connection, statement, results);
//			 return gradeCategory;
//		}) != null;
	}

	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertGradeCategory (String title, int klass_id, int course_id, double weight) {
		return -1;
//		return executeUpdate(connection -> {
//			final String query = "INSERT INTO gradeCategory (title, repo_id) VALUES (?, ?);";
//			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			statement.setString(1, title);
//			statement.setInt(2, repo_id);
//			System.out.println("insertGradeCategory: " + statement);
//			ResultSet rs = statement.getGeneratedKeys();
//			rs.next();
//			int affectedRows = statement.executeUpdate();
//			
//			if (affectedRows == 0) {
//				throw new SQLException("Creating GradeCategory failed, no rows affected.");
//			}
//			
//			ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//               return generatedKeys.getInt(1);
//            }
//            
//			close(connection, statement, generatedKeys);
//			return 0;
//		});
	}
	
	
	//PATCH(UPDATE)
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateGradeCategoryTitleById(String title, int id) {
		return false;
//		return executeUpdate(connection -> {
//			 final String query = "UPDATE gradeCategory SET title = ? WHERE id = ?;";
//			 PreparedStatement statement = connection.prepareStatement(query);
//			 statement.setString(1, title);
//			 statement.setInt(2, id);
//			 System.out.println("updateGradeCategoryTitleById: " + statement);
//			 int result = statement.executeUpdate();
//			 close(connection, statement, null);
//			 return result;
//		}) != 0;
	}
}