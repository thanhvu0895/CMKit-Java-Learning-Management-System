package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.SshKeyMapper;
import codingmentor.javabackend.k3.model.SshKey;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.SshKeyRepository;

public class SshKeyRepositoryImpl extends AbstractRepository<SshKey> implements SshKeyRepository{
	private static SshKeyRepository repository = null;
	private final SshKeyMapper mapper;
	private SshKeyRepositoryImpl() {
		mapper = new SshKeyMapper();
	}
	 
	public static SshKeyRepository getInstance() {
    	if (repository == null) {
    		repository = new SshKeyRepositoryImpl();
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
	public List<SshKey> getSshKeys() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM ssh_keys";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getSshKeys: " + statement);
			List<SshKey> sshKeysList = new ArrayList<>();
			while(results.next()) {
				sshKeysList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return sshKeysList;
		});
	}

	/*
	 * GET ITEM METHOD
	 */
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SshKey getSshKeyById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM ssh_keys WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getSshKeyById: " + statement);
		    SshKey sshKey = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return sshKey;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	
	
	//PATCH
	    
}