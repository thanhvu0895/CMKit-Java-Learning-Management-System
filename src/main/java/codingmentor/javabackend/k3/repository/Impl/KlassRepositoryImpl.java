package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.KlassMapper;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;

public class KlassRepositoryImpl extends AbstractRepository<Klass> implements KlassRepository{
	private static KlassRepository repository = null;
	private final KlassMapper mapper;
	private KlassRepositoryImpl() {
		mapper = new KlassMapper();
	}
	 
	public static KlassRepository getInstance() {
    	if (repository == null) {
    		repository = new KlassRepositoryImpl();
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
	public List<Klass> getKlasss() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM klasss";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<Klass> klasssList = new ArrayList<>();
			while(results.next()) {
				klasssList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return klasssList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Klass getKlassById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM klasss WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    Klass klass = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return klass;
    	});
	}
	    
}