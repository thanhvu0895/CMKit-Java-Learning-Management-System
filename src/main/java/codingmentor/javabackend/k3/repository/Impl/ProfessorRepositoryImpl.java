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

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Professor> getProfessors() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM professors";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
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
	@Override
	public Professor getProfessorById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM professors WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Professor professor = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return professor;
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
	public int insertProfessor (int user_id, int klass_id) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO professors (user_id, klass_id) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, user_id);
			statement.setInt(2, klass_id);
			System.out.println("insertProfessor: " + statement);
			int result = statement.executeUpdate();
    		close(connection, statement, null);			
			return result;
		});
	}
}