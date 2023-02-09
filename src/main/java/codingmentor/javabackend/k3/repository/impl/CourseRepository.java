package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.CourseMapper;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class CourseRepository extends BaseRepository<CourseMapper, Course>{
	private static CourseRepository repository;
	
	public static synchronized CourseRepository getInstance() {
    	if (repository == null) {
    		repository = new CourseRepository();
    	}
    	
    	return repository;
    }
	
	
	private CourseRepository() {
		super(new CourseMapper());
	}
	 
	/*
	 * GET LIST METHOD
	 */

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