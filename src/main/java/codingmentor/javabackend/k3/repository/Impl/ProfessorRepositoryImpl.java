package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.ProfessorMapper;
import codingmentor.javabackend.k3.model.Professor;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.ProfessorRepository;

public class ProfessorRepositoryImpl extends AbstractRepository<Professor> implements ProfessorRepository{
	private static ProfessorRepository repository = null;
	private final ProfessorMapper mapper;
	private ProfessorRepositoryImpl() {
		mapper = new ProfessorMapper();
	}
	 
	public static ProfessorRepository getInstance() {
    	if (repository == null) {
    		repository = new ProfessorRepositoryImpl();
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
	public List<Professor> getProfessors() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM professors";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getProfessors: " + statement);
			List<Professor> professorsList = new ArrayList<>();
			while(results.next()) {
				professorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return professorsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Professor> getProfessorsByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT P.id, P.user_id, P.klass_id from professors as P\r\n"
					+ " INNER JOIN users AS U\r\n"
					+ " ON P.user_id = U.id and P.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			System.out.println("getProfessorsByKlassId: " + statement);
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Professor getProfessorById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM professors WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getProfessorById: " + statement);
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
	@Override
	public boolean isProfessorByUserId(int userId, int klassId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 as ONE FROM professors WHERE user_id = ? and klass_id = ? LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, klassId);
			 ResultSet results = statement.executeQuery();
			 System.out.println("isProfessorByUserId: " + statement);
			 Professor professor = (results.next()) ? new Professor() : null;
			 close(connection, statement, results);
			 return professor;
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
	public int insertProfessor (int user_id, int klass_id) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO professors (user_id, klass_id) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user_id);
			statement.setInt(2, klass_id);
			System.out.println("insertProfessor: " + statement);
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteProfessor(int professorId) {
		return executeUpdate(connection -> {
			 final String query = "DELETE FROM professors WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, professorId);
			 System.out.println("deleteProfessor: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	@Override
	public Professor getProfessorByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}