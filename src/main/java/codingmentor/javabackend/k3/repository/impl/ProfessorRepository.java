package codingmentor.javabackend.k3.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.ProfessorMapper;
import codingmentor.javabackend.k3.model.Professor;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class ProfessorRepository extends BaseRepository<ProfessorMapper, Professor> {
	
	private static ProfessorRepository repository;
	
	public static synchronized ProfessorRepository getInstance() {
    	if (repository == null) {
    		repository = new ProfessorRepository();
    	}
    	
    	return repository;
    }
	
	private ProfessorRepository() {
		super(new ProfessorMapper());
	}
	   
	/*
	 * GET LIST METHOD
	 */

	public List<Professor> getProfessors() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM professors";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProfessors: " + statement);
			List<Professor> professorsList = new ArrayList<>();
			while(results.next()) {
				professorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return professorsList;
		});
	}

	public List<Professor> getProfessorsByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT P.id, P.user_id, P.klass_id from professors as P\r\n"
					+ " INNER JOIN users AS U\r\n"
					+ " ON P.user_id = U.id and P.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getProfessorsByKlassId: " + statement);
			List<Professor> professorsList = new ArrayList<>();
			while(results.next()) {
				professorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return professorsList;
		});
	}
	
	
	
	/*
	 * GET ITEM METHOD
	 */

	public Professor getProfessorById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM professors WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getProfessorById: " + statement);
		    Professor professor = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return professor;
    	});
	}
	
	
	/*
	 * GET Check True/false METHOD
	 */
    /**
	 * @category CHECK
     */
	
	public boolean isProfessorByUserId(int userId, int klassId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 as ONE FROM professors WHERE user_id = ? and klass_id = ? LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, klassId);
			 ResultSet results = statement.executeQuery();
			 logger.info("-- isProfessorByUserId: " + statement);
			 Professor professor = (results.next()) ? new Professor() : null;
			 close(connection, statement, results);
			 return professor;
		}) != null;
	}
	/*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)

	public int insertProfessor (int user_id, int klass_id) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO professors (user_id, klass_id) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user_id);
			statement.setInt(2, klass_id);
			logger.info("-- insertProfessor: " + statement);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating Department Professor failed, no rows affected.");
			}
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }
            
			close(connection, statement, generatedKeys);
			return 0;
		});
	}

	// DELETE

	public boolean deleteProfessor(int professorId) {
		return executeUpdate(connection -> {
			 final String query = "\n DELETE FROM professors WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, professorId);
			 logger.info("-- deleteProfessor: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}