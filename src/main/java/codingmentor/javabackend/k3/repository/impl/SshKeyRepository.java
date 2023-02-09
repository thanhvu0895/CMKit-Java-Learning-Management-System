package codingmentor.javabackend.k3.repository.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.SshKeyMapper;
import codingmentor.javabackend.k3.model.SshKey;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class SshKeyRepository extends BaseRepository<SshKeyMapper, SshKey> {
	private static SshKeyRepository repository;
	
	public static synchronized SshKeyRepository getInstance() {
    	if (repository == null) {
    		repository = new SshKeyRepository();
    	}
    	
    	return repository;
    }
	
	private SshKeyRepository() {
		super(new SshKeyMapper());
	}
	 
    
	/*
	 * GET LIST METHOD
	 */
	
	public List<SshKey> getSshKeys() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM ssh_keys";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getSshKeys: " + statement);
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

	public SshKey getSshKeyById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "\nSELECT * FROM ssh_keys WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    logger.info("-- getSshKeyById: " + statement);
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