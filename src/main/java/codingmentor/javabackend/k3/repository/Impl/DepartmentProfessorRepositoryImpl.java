package codingmentor.javabackend.k3.repository.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.DepartmentProfessorMapper;
import codingmentor.javabackend.k3.model.DepartmentProfessor;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.DepartmentProfessorRepository;

public class DepartmentProfessorRepositoryImpl extends AbstractRepository<DepartmentProfessor> implements DepartmentProfessorRepository{
	private static DepartmentProfessorRepository repository = null;
	private final DepartmentProfessorMapper mapper;
	private DepartmentProfessorRepositoryImpl() {
		mapper = new DepartmentProfessorMapper();
	}
	 
	public static DepartmentProfessorRepository getInstance() {
    	if (repository == null) {
    		repository = new DepartmentProfessorRepositoryImpl();
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
	public List<DepartmentProfessor> getDepartmentProfessors() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM department_professors";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<DepartmentProfessor> departmentProfessorsList = new ArrayList<>();
			while(results.next()) {
				departmentProfessorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return departmentProfessorsList;
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DepartmentProfessor getDepartmentProfessorById(int id) {
		return executeQuerySingle(connection -> {
			final String query = "SELECT * FROM department_professors WHERE id = ? LIMIT 1;";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet results = statement.executeQuery();
		    System.out.println(statement);
		    DepartmentProfessor departmentProfessor = (results.next()) ? mapper.map(results) : null;
		    close(connection, statement, results);
		    return departmentProfessor;
    	});
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override   
	public List<DepartmentProfessor>  getDepartmentProfessorsByDepartmentId(int id) {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM department_professors where department_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<DepartmentProfessor> departmentProfessorsList = new ArrayList<>();
			while(results.next()) {
				departmentProfessorsList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return departmentProfessorsList;
		});
	}
	
	
	@Override
	public boolean updateAdminByDepartmentProfessorId(boolean admin, int id) {
		return executeUpdate(connection -> {
			 final String query = "UPDATE department_professors SET admin = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setBoolean(1, admin);
			 statement.setInt(2, id);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}
}