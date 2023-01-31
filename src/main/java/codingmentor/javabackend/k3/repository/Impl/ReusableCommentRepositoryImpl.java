package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.ReusableCommentMapper;
import codingmentor.javabackend.k3.model.ReusableComment;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.ReusableCommentRepository;

public class ReusableCommentRepositoryImpl extends AbstractRepository<ReusableComment> implements ReusableCommentRepository{
	private static ReusableCommentRepository repository = null;
	private final ReusableCommentMapper mapper;
	private ReusableCommentRepositoryImpl() {
		mapper = new ReusableCommentMapper();
	}
	 
	public static ReusableCommentRepository getInstance() {
    	if (repository == null) {
    		repository = new ReusableCommentRepositoryImpl();
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
	public List<ReusableComment> getReusableComments() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM reusable_comments";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println("getReusableComments: " + statement);
			List<ReusableComment> reusable_commentsList = new ArrayList<>();
			while(results.next()) {
				reusable_commentsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return reusable_commentsList;
		});
	}
	
	/*
	 * GET ITEM METHOD
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReusableComment getReusableCommentById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM reusable_comments WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println("getReusableCommentById: " + statement);
		    ReusableComment ReusableComment = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return ReusableComment;
    	});
	}
	
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean insertReusableComment(int problem_id, String comment) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO reusable_comments (problem_id, comment) VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, problem_id);
			statement.setString(2, comment);
			System.out.println("insertReusableComment: " + statement);
			int result = statement.executeUpdate();
			close(connection, statement, null);			
			return result;	
		}) != 0;
	}
	//PATCH
	    
}