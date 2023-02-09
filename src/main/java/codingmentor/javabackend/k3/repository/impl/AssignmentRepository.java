package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.AssignmentMapper;
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class AssignmentRepository extends BaseRepository<AssignmentMapper, Assignment> {
	
	private AssignmentRepository() {
		super(new AssignmentMapper());
	}
	
	private static AssignmentRepository repository;
	
	public static synchronized AssignmentRepository getInstance() {
    	if (repository == null) {
    		repository = new AssignmentRepository();
    	}
    	
    	return repository;
    }
	 
	/*
	 * GET LIST METHOD
	 */

	public List<Assignment> getAssignments() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM assignments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignments(): " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	
	public List<Assignment> getAssignmentsByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "\nWITH A AS (\nSELECT * FROM assignments where course_id = ?)\r\n"
					+ "\r\n"
					+ "\nSELECT a.id, a.title, a.klass_id, a.course_id, a.grade_category_id, a.files_repo_id, a.template_repo_id, a.assignment_type, a.permitted_filetypes, a.description, a.file_limit, a.file_or_link, ifnull(sum(points),0) as total_points\r\n"
					+ "	FROM A a\r\n"
					+ "    left join problems P\r\n"
					+ "    on a.id = P.assignment_id\r\n"
					+ "    group by a.id ;";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, courseId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignmentsByCourseId: " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	
	public List<Assignment> getAssignmentsByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nWITH A AS (\nSELECT * FROM assignments where klass_id = ?)\r\n"
					+ "\r\n"
					+ "\nSELECT a.id, a.title, a.klass_id, a.course_id, a.grade_category_id, a.files_repo_id, a.template_repo_id, a.assignment_type, a.permitted_filetypes, a.description, a.file_limit, a.file_or_link, ifnull(sum(points),0) as total_points\r\n"
					+ "	FROM A a\r\n"
					+ "    left join problems P\r\n"
					+ "    on a.id = P.assignment_id\r\n"
					+ "    group by a.id;";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignmentsByKlassId: " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	
	public List<Assignment> getAssignmentsWithGradersListByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nWITH TABLE1 AS (\r\n"
					+ "WITH A AS (\nSELECT * FROM assignments where klass_id = ?)\r\n"
					+ "\nSELECT a.id, a.title, a.klass_id, a.course_id, a.grade_category_id, a.files_repo_id, a.template_repo_id, a.assignment_type, a.permitted_filetypes, a.description, a.file_limit, a.file_or_link, ifnull(sum(points),0) as total_points\r\n"
					+ "	FROM A a\r\n"
					+ "    left join problems P\r\n"
					+ "    on a.id = P.assignment_id\r\n"
					+ "    group by a.id  /*TOTAL POINTS to ASSIGNMENT IN KLASS*/\r\n"
					+ "),\r\n"
					+ "\r\n"
					+ "TABLE2 AS (\r\n"
					+ "WITH A AS (\nSELECT * FROM assignments where klass_id = ?)\r\n"
					+ "\nSELECT a.id, GROUP_CONCAT(U.email SEPARATOR ', ') as assigned_graders\r\n"
					+ "	FROM A a\r\n"
					+ "    LEFT JOIN assigneds as AG\r\n"
					+ "		ON AG.assignment_id = a.id\r\n"
					+ "	LEFT JOIN assigned_graders as ASG\r\n"
					+ "		ON ASG.assigned_id = AG.id\r\n"
					+ "	LEFT JOIN users as U\r\n"
					+ "		ON ASG.user_id = U.id group by a.id  \r\n"
					+ ")\r\n"
					+ "\r\n"
					+ "\nSELECT TABLE1.*, TABLE2.assigned_graders from TABLE1\r\n"
					+ " LEFT JOIN TABLE2\r\n"
					+ "	ON TABLE1.id = TABLE2.id;";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, klassId);
		    statement.setInt(2, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignmentsWithGradersListByKlassId: " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	
	public List<Assignment> getAssignmentsWithGradersListByCourseId(int courseId) {
		return executeQuery(connection -> {
			final String query = "\n WITH TABLE1 AS (\r\n"
					+ "WITH A AS (\nSELECT * FROM assignments where course_id = ?)\r\n"
					+ "\nSELECT a.id, a.title, a.klass_id, a.course_id, a.grade_category_id, a.files_repo_id, a.template_repo_id, a.assignment_type, a.permitted_filetypes, a.description, a.file_limit, a.file_or_link, ifnull(sum(points),0) as total_points\r\n"
					+ "	FROM A a\r\n"
					+ "    left join problems P\r\n"
					+ "    on a.id = P.assignment_id\r\n"
					+ "    group by a.id  /*TOTAL POINTS to ASSIGNMENT IN KLASS*/\r\n"
					+ "),\r\n"
					+ "\r\n"
					+ "TABLE2 AS (\r\n"
					+ "WITH A AS (\nSELECT * FROM assignments where course_id = ?)\r\n"
					+ "\nSELECT a.id, GROUP_CONCAT(U.email SEPARATOR ', ') as assigned_graders\r\n"
					+ "	FROM A a\r\n"
					+ "    LEFT JOIN assigneds as AG\r\n"
					+ "		ON AG.assignment_id = a.id\r\n"
					+ "	LEFT JOIN assigned_graders as ASG\r\n"
					+ "		ON ASG.assigned_id = AG.id\r\n"
					+ "	LEFT JOIN users as U\r\n"
					+ "		ON ASG.user_id = U.id group by a.id  \r\n"
					+ ")\r\n"
					+ "\r\n"
					+ "\nSELECT TABLE1.*, TABLE2.assigned_graders from TABLE1\r\n"
					+ " LEFT JOIN TABLE2\r\n"
					+ "	ON TABLE1.id = TABLE2.id;";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, courseId);
		    statement.setInt(2, courseId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignmentsWithGradersListByCourseId: " + statement);
			List<Assignment> assignmentsList = new ArrayList<>();
			while(results.next()) {
				assignmentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return assignmentsList;
		});
	}
	
	public List<Assignment> getAssignedAssignmentsByUserInKlassOrderByDueDate(int userId, int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT AM.id, AM.title, AM.klass_id, AM.course_id, AM.grade_category_id, AM.files_repo_id, AM.template_repo_id, AM.assignment_type, AM.permitted_filetypes, AM.description, AM.file_limit, AM.file_or_link FROM assigneds AS A\r\n"
					+ "	LEFT JOIN assigned_graders as AG\r\n"
					+ "		ON AG.assigned_id = A.id\r\n"
					+ "	LEFT JOIN assignments as AM\r\n"
					+ "		ON AM.id = A.assignment_id\r\n"
					+ "WHERE A.klass_id = ? and AG.user_id = ? ORDER BY A.due_date DESC;";
			PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, klassId);
		    statement.setInt(2, userId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getAssignedAssignmentsByUserInKlassOrderByDueDate: " + statement);
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
	
	public Assignment getAssignmentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT id, title, klass_id, course_id, grade_category_id, files_repo_id, template_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link FROM assignments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getAssignmentById: " + statement);
		    Assignment assignment = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return assignment;
    	});
	}

	public Assignment getAssignmentByIdWithTotalPoint(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT a.id, a.title, a.klass_id, a.course_id, a.grade_category_id, a.files_repo_id, a.template_repo_id, a.assignment_type, a.permitted_filetypes, a.description, a.file_limit, a.file_or_link, ifnull(sum(points),0) AS total_points\r\n"
					+ "	FROM assignments as a\r\n"
					+ "		LEFT JOIN problems P\r\n"
					+ "		ON a.id = P.assignment_id\r\n"
					+ "WHERE a.id = ?\r\n"
					+ "group by a.id LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getAssignmentById: " + statement);
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
	
	public int insertAssignment(String title, int course_id, Integer grade_category_id, int files_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO assignments"
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
			logger.info(" --insertAssignment: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Assignment failed, no rows affected.");
			}
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
            
			close(connection, statement, generatedKeys);
			return 0;
		});
	}
	
	public int insertAssignmentKlass(String title, int klass_id, Integer grade_category_id, int files_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link) {
		return executeUpdate(connection -> {
			final String query = "\n INSERT INTO assignments"
					+ " (title, klass_id, grade_category_id, files_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setInt(2, klass_id);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, files_repo_id);
			statement.setInt(5, assignment_type);
			statement.setString(6, permitted_filetypes);
			statement.setString(7, description);
			statement.setInt(8, file_limit);
			statement.setInt(9, file_or_link);
			logger.info("-- insertAssignmentKlass: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating AssignmentKlass failed, no rows affected.");
			}
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
            
			close(connection, statement, generatedKeys);
			return 0;
		});
	}

	public int insertStudentRepoAssignmentKlass(String title, int klass_id, Integer grade_category_id, int files_repo_id, int template_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit, int file_or_link) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO assignments"
					+ " (title, klass_id, grade_category_id, files_repo_id, template_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setInt(2, klass_id);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, files_repo_id);
			statement.setInt(5, template_repo_id);
			statement.setInt(6, assignment_type);
			statement.setString(7, permitted_filetypes);
			statement.setString(8, description);
			statement.setInt(9, file_limit);
			statement.setInt(10, file_or_link);
			logger.info("-- insertStudentRepoAssignmentKlass: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating StudentRepoAssignment failed, no rows affected.");
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
	
	public int insertStudentRepoAssignment(String title, int course_id, Integer grade_category_id, 
			int files_repo_id, int template_repo_id, int assignment_type, String permitted_filetypes, String description, int file_limit,int file_or_link) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO assignments"
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
			logger.info("-- insertStudentRepoAssignment: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating StudentRepoAssignment failed, no rows affected.");
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
	public boolean updateStudentFileAssignmentById(String title, String description, Integer grade_category_id,
			int file_or_link, String permitted_filetypes, int file_limit, int assignmentId) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE assignments SET title = ?, description= ?, grade_category_id = ?, file_or_link = ?, permitted_filetypes = ?, file_limit = ?  WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, file_or_link);
			statement.setString(5, permitted_filetypes);
			statement.setInt(6, file_limit);
			statement.setInt(7, assignmentId);
			 logger.info("-- updateStudentFileAssignmentById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updateAssignmentById(String title, String description, Integer grade_category_id, int assignmentId) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE assignments SET title = ?, description= ?, grade_category_id = ? WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setObject(3, grade_category_id, Types.INTEGER);
			statement.setInt(4, assignmentId);
			 logger.info("-- updateStudentFileAssignmentById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}