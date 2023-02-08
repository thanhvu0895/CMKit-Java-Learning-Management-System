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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CourseRepositoryImpl extends AbstractRepository<Course> implements CourseRepository{
	private static CourseRepository repository = null;
	private final CourseMapper mapper;
	private final Logger logger;
	
	private CourseRepositoryImpl() {
		mapper = new CourseMapper();
		logger = LogManager.getLogger("codingmentor");
	}
	 
	public static CourseRepository getInstance() {
    	if (repository == null) {
    		repository = new CourseRepositoryImpl();
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
	public List<Course> getCourses() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM courses";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getCourses" + statement);
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
	public List<Course> getCoursesByDepartmentId(int departmentId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM courses where department_id = ? ORDER BY course_code";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, departmentId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getCoursesByDepartmentId: " + statement);
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
	public List<Course> getCoursesWithKlassByDepartmentId(int departmentId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT \r\n"
					+ "	C.id, C.title, C.course_code, C.repo_id, C.department_id, C.active \r\n"
					+ "FROM courses AS C \r\n"
					+ "INNER JOIN klasses as K\r\n"
					+ "	on K.course_id = C.id and department_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, departmentId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getCoursesWithKlassByDepartmentId: " + statement);
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
	public List<Course> getCoursesOrderByCourseCode() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM courses ORDER BY course_code";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getCoursesOrderByCourseCode: " + statement);
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
	public List<Course> getStudentCoursesByUserId(int userId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT C.* from klasses as K\r\n"
					+ "	INNER JOIN students as S\r\n"
					+ "		ON S.klass_id = K.id\r\n"
					+ "LEFT JOIN courses as C\r\n"
					+ "	ON C.id = K.course_id\r\n"
					+ "where S.user_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getStudentCoursesByUserId: " + statement);
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
	public List<Course> getGraderCoursesByUserId(int userId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT C.* from klasses as K\r\n"
					+ "	INNER JOIN graders as G\r\n"
					+ "		ON G.klass_id = K.id\r\n"
					+ "LEFT JOIN courses as C\r\n"
					+ "	ON C.id = K.course_id\r\n"
					+ "where G.user_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGraderCoursesByUserId: " + statement);
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
	public List<Course> getProfessorCoursesByUserId(int userId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT C.* from klasses as K\r\n"
					+ "	INNER JOIN professors as P\r\n"
					+ "		ON P.klass_id = K.id\r\n"
					+ "LEFT JOIN courses as C\r\n"
					+ "	ON C.id = K.course_id\r\n"
					+ "where P.user_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProfessorCoursesByUserId: " + statement);
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
	public List<Course> getProfessorCoursesByUserIdWithStudentsCount(int userId) {
		return executeQuery(connection -> {
			final String query = "\nWITH C as (SELECT K.id as klass_id, C.* from klasses as K\r\n"
					+ "	INNER JOIN professors as P\r\n"
					+ "		ON P.klass_id = K.id\r\n"
					+ "		LEFT JOIN courses as C\r\n"
					+ "		ON C.id = K.course_id\r\n"
					+ "	where P.user_id = ? group by K.id)\r\n"
					+ "SELECT C.id, C.title, C.course_code, C.repo_id, C.department_id, C.active, count(S.id) as student_count FROM C \r\n"
					+ "	LEFT JOIN students as S\r\n"
					+ "	ON S.klass_id = C.klass_id GROUP BY C.klass_id;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProfessorCoursesByUserIdWithStudentsCount: " + statement);
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
	public List<Course> getAdminCourses() {
		return executeQuery(connection -> {
			final String query = "\nSELECT C.* FROM klasses as K\r\n"
					+ "	LEFT JOIN courses as C\r\n"
					+ "    ON K.course_id = C.id group by K.id;";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProfessorCoursesByUserId: " + statement);
			List<Course> coursesList = new ArrayList<>();
			while(results.next()) {
				coursesList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return coursesList;
		});
	}

	/*
	 * GET ITEM METHOD
	 */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Course getCourseById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM courses WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getCourseById: " + statement);
		    Course course = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return course;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Course getCourseByKlassId(int klassId) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT C.* FROM courses as C, klasses as K \r\n"
					+ "where K.course_id = C.id AND K.id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, klassId);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getCourseByKlassId: " + statement);
		    Course course = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return course;
    	});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Course getCourseByGradeCategoryId(int klassId) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT C.* FROM courses as C\r\n"
					+ " INNER JOIN grade_categories as GC \r\n"
					+ " ON GC.course_id = C.id AND GC.id = ? LIMIT 1";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, klassId);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getCourseByGradeCategoryId: " + statement);
		    Course course = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return course;
    	});
	}
	
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertCourse(String title, String course_code, int repo_id, int department_id, boolean active) {
		return executeUpdate(connection -> {
			final String query = "\n INSERT INTO courses (title, course_code, repo_id, department_id, active) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setString(2, course_code);
			statement.setInt(3, repo_id);
			statement.setInt(4, department_id);
			statement.setBoolean(5, active);
			logger.info("-- insertCourse: " + statement);
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

	
	//PATCH(UPDATE)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateCourseById(String title, String courseCode, boolean active, int id){
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE courses SET title = ?, course_code = ?, active = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title);
			 statement.setString(2, courseCode);
			 statement.setBoolean(3, active);
			 statement.setInt(4, id);
			 logger.info("-- updateKlassById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}