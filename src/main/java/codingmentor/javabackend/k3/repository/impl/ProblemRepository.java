package codingmentor.javabackend.k3.repository.impl;import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;import codingmentor.javabackend.k3.mapper.ProblemMapper;
import codingmentor.javabackend.k3.model.Problem;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class ProblemRepository extends BaseRepository<ProblemMapper, Problem> {
	private static ProblemRepository repository;
	
	public static synchronized ProblemRepository getInstance() {
    	if (repository == null) {
    		repository = new ProblemRepository();
    	}
    	
    	return repository;
    }
	
	private ProblemRepository() {
		super(new ProblemMapper());
	}
	 
	/*
	 * GET LIST METHOD
	 */
	 
	public List<Problem> getProblems() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM problems";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProblems: " + statement);
			List<Problem> problemsList = new ArrayList<>();
			while(results.next()) {
				problemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return problemsList;
		});
	}
	
	public List<Problem> getProblemsByAssignmentId(int assignment_id) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM problems WHERE assignment_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignment_id);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProblemsByAssignmentId: " + statement);
			List<Problem> problemsList = new ArrayList<>();
			while(results.next()) {
				problemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return problemsList;
		});
	}
	
	public List<Problem> getProblemsByAssignmentIdOrderByLocationAsc(int assignment_id) {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM problems WHERE assignment_id = ? ORDER BY location ASC";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, assignment_id);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProblemsByAssignmentId: " + statement);
			List<Problem> problemsList = new ArrayList<>();
			while(results.next()) {
				problemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return problemsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	
	public Problem getProblemById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM problems WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getProblemById: " + statement);
		    Problem problem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return problem;
    	});
	}
	
	public Problem getMaxProblemByAssignmentId(int assignment_id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM problems WHERE location = ( \nSELECT MAX(location) FROM problems where assignment_id = ?) and assignemnt_id = ?;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, assignment_id);
		    statement.setInt(2, assignment_id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getMaxProblemByAssignmentId: " + statement);
		    Problem problem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return problem;
    	});
	}
	
	public Problem getProblemByLocationAndAssignmentId(int location, int assignment_id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM problems WHERE location = ? and assignment_id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, location);
		    statement.setInt(2, assignment_id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getProblemByLocationAndAssignmentId: " + statement);
		    Problem problem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return problem;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	public int insertProblem (int assignment_id, String title, Double points, int location, String grader_notes) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO problems (assignment_id, title, points, location, grader_notes) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, assignment_id);
			statement.setString(2, title);
			statement.setDouble(3, points);
			statement.setInt(4, location);
			statement.setString(5, grader_notes);
			logger.info("-- insertProblemrepository: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Problem failed, no rows affected.");
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
	public boolean updateProblemLocationById(int location, int problem_id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE problems SET location = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, location);
			 statement.setInt(2, problem_id);
			 logger.info("-- updateProblemLocationById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}	

	public boolean updateProblemById(String title, double points, String grader_notes, int problem_id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE problems SET title = ?, points = ?, grader_notes = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, title );
			 statement.setDouble(2, points);
			 statement.setString(3, grader_notes);
			 statement.setInt(4, problem_id);
			 logger.info("-- updateProblemById: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}