package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.RubricItemMapper;
import codingmentor.javabackend.k3.model.RubricItem;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.RubricItemRepository;

public class RubricItemRepositoryImpl extends AbstractRepository<RubricItem> implements RubricItemRepository{
	private static RubricItemRepository repository = null;
	private final RubricItemMapper mapper;
	private RubricItemRepositoryImpl() {
		mapper = new RubricItemMapper();
	}
	 
	public static RubricItemRepository getInstance() {
    	if (repository == null) {
    		repository = new RubricItemRepositoryImpl();
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
	public List<RubricItem> getRubricItems() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM rubricItems";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<RubricItem> rubricItemsList = new ArrayList<>();
			while(results.next()) {
				rubricItemsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return rubricItemsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RubricItem getRubricItemById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM rubricItems WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    RubricItem rubricItem = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return rubricItem;
    	});
	}
	    
}