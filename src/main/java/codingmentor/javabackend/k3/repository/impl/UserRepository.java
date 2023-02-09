package codingmentor.javabackend.k3.repository.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.mapper.UserMapper;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.BaseRepository;

public class UserRepository extends BaseRepository<UserMapper, User> {
	private PBKDF2Hasher hasher;
	private static UserRepository repository;
	
	public static synchronized UserRepository getInstance() {
    	if (repository == null) {
    		repository = new UserRepository();
    	}
    	
    	return repository;
    }
	
    private UserRepository() {
    	super(new UserMapper());
    	hasher =  new PBKDF2Hasher();
    }
    

	/*
	 * GET List 
	 */

	public List<User> getUsers() {
		return executeQuery(connection -> {
			final String query = "\nSELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			logger.info("-- getUsers: " + statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}

	public List<User> getUsersFromDepartmentId(int departmentId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT \r\n"
					+ "	U.id, U.email, U.admin, U.first_name, U.last_name, U.preferred_name, U.set_up, U.disabled, U.deleted \r\n"
					+ "FROM users as U\r\n"
					+ "INNER JOIN department_professors as DP \r\n"
					+ "	ON DP.user_id = U.id\r\n"
					+ "INNER JOIN departments as D\r\n"
					+ "	ON DP.department_id = D.id and D.id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, departmentId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getUsersFromDepartmentId: " + statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}
    
	
	public List<User> getUsersFromKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT U.id, U.email, U.admin, U.first_name, U.last_name, U.preferred_name, U.set_up, U.disabled, U.deleted from professors as P"
					+ "	INNER JOIN users AS U"
					+ " ON P.user_id = U.id and P.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getUsersFromKlassId: " + statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}

	public List<User> getStudentUsersByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT U.id, U.email, U.admin, U.first_name, U.last_name, U.preferred_name, U.set_up, U.disabled, U.deleted from students as S"
					+ "	INNER JOIN users AS U"
					+ " ON S.user_id = U.id and S.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getStudentUsersByKlassId: " + statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}
	
	public List<User> getGraderUsersByKlassId(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT U.id, U.email, U.admin, U.first_name, U.last_name, U.preferred_name, U.set_up, U.disabled, U.deleted FROM graders as G\r\n"
					+ " INNER JOIN users as U\r\n"
					+ "	ON U.id = G.user_id AND G.klass_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGraderUsersByKlassId: " + statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}
	
	public List<User> getGraderUsersByKlassIdWithAssignedAssignmentCount(int klassId) {
		return executeQuery(connection -> {
			final String query = "\nSELECT T1.*, ifnull(T2.assigned_assignments, 0) as assigned_assignments\r\n"
					+ "FROM (\nSELECT U.id, U.email, U.admin, U.first_name, U.last_name, U.preferred_name, U.set_up, U.disabled, U.deleted \r\n"
					+ "	from graders as G INNER JOIN users as U ON U.id = G.user_id AND G.klass_id = ?) AS T1\r\n"
					+ "LEFT JOIN (\nSELECT A.user_id, count(user_id) as assigned_assignments\r\n"
					+ "	from (\nSELECT AG.* FROM assigned_graders as AG INNER JOIN assigneds as S ON AG.assigned_id = S.id) as A group by user_id) AS T2\r\n"
					+ "ON T1.id = T2.user_id;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, klassId);
			ResultSet results = statement.executeQuery();
			logger.info("-- getGraderUsersByKlassIdWithAssignedAssignmentCount: " + statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}
	

	/*
	 * GET Item
	 */
	
	public User findUserById(int id) {
		return executeQuerySingle(connection -> {

    		// Query to find user by email
    		final String query = "\nSELECT * FROM users WHERE id = ?  LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            logger.info("-- findUserById: " + statement);
            User user = (results.next()) ? mapper.map(results) : null;
            close(connection, statement, results);
            return user;
    	});
	}
	

    public User findUserByEmail(String email) { 
		return executeQuerySingle(connection -> {
	
			// Query to find user by email
			final String query = "\nSELECT * FROM users WHERE email = ? LIMIT 1;";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, email);
	        ResultSet results = statement.executeQuery();
	        logger.info("-- findUserByEmail: " + statement);
	        User user = (results.next()) ? mapper.map(results) : null;
	        close(connection, statement, results);
	        return user;
	    });
    }
 
	/*
	 * GET Check True/false METHOD
	 */
    
	public boolean existedByEmail(String email) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 AS ONE FROM users WHERE email = ?  LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, email);
			 ResultSet results = statement.executeQuery();
			 logger.info("existedByEmail: " + statement);
			 User user = (results.next()) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	}
    
	public boolean isDepartmentProfessor(int userId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 AS one FROM department_professors WHERE user_id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 ResultSet results = statement.executeQuery();
			 logger.info("isDepartmentProfessor: " + statement);
			 User user = (results.next()) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	}
	
	public boolean isDepartmentProfessorByDepartmentId(int userId, int departmentId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 AS one FROM department_professors WHERE user_id = ? and department_id = ? LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, departmentId);
			 ResultSet results = statement.executeQuery();
			 logger.info("isDepartmentProfessorByDepartmentId: " + statement);
			 User user = (results.next()) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	 }

	public boolean isKlassStudentByKlassId(int userId, int klassId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 AS one FROM students WHERE user_id = ? and klass_id = ? LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, klassId);
			 ResultSet results = statement.executeQuery();
			 logger.info("-- isKlassStudentByKlassId: " + statement);
			 User user = (results.next()) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	}

	public boolean isKlassGraderByKlassId(int userId, int klassId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 AS one FROM graders WHERE user_id = ? and klass_id = ? LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, klassId);
			 ResultSet results = statement.executeQuery();
			 logger.info("-- isKlassGraderByKlassId: " + statement);
			 User user = (results.next()) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	}

	public boolean isKlassProfessorByKlassId(int userId, int klassId) {
		return executeQuerySingle(connection -> {
			 final String query = "\nSELECT 1 AS one FROM professors WHERE user_id = ? and klass_id = ? LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, klassId);
			 ResultSet results = statement.executeQuery();
			 logger.info("-- isKlassProfessorByKlassId: " + statement);
			 User user = (results.next()) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	}
	
    
    /*
     * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
     */
    
  	/*
  	 * # IMPORTANT: Accounts get a random password when they are first created. #
  	 * # This password cannot actually be used as the set_up boolean is false, and
  	 * # using a default password would be dumb # if set_up ever stopped working
  	 */
	
	
	//POST(CREATE)

	public boolean createUserSendInvite(String email, boolean admin) {
		return executeUpdate(connection -> {
			final String query = "\nINSERT INTO users(email, admin, password_digest)"
					+ "VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setBoolean(2, admin);
			String password_digest = hasher.hash(RandomUtils.unique64().toCharArray());
			statement.setString(3, password_digest);
			logger.info("-- createUserSendInvite: " + statement);
			int result = statement.executeUpdate();
    		close(connection, statement, null);			
			return result;
		}) != 0;
	}
	
	public boolean updateUserEditAdmin(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id) {
		return executeUpdate(connection -> {
			final String query = "\nUPDATE users SET first_name = ?, last_name = ?, preferred_name = ?, admin = ?, disabled = ? WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, first_name);
			statement.setString(2, last_name);
			statement.setString(3, preferred_name);
			statement.setBoolean(4, admin);
			statement.setBoolean(5, disabled);
			statement.setInt(6, id);
			logger.info("-- updateUserEditAdmin: " + statement);
			int result = statement.executeUpdate();		 
			close(connection, statement, null);
			return result;
		}) != 0;
	}

	public boolean updatePreferredNameById(String preferred_name, int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET preferred_name = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, preferred_name);
			 statement.setInt(2, id);
			 logger.info("-- updatePreferredNameById: " +statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updatePassword(String new_password, User user) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET password_digest = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 String password_digest = hasher.hash(new_password.toCharArray());
			 statement.setString(1, password_digest);
			 statement.setInt(2, user.getId());
			 logger.info("-- updatePassword: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updateResetDigest(int userid, String reset_digest) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET reset_digest = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, reset_digest);
			 statement.setInt(2, userid);
			 logger.info("-- updateResetDigest: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updateResetExpires(int userid, LocalDateTime reset_expires) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET reset_expires = ?, deleted = 0 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setTimestamp(1, Timestamp.valueOf(reset_expires));
			 statement.setInt(2, userid);
			 logger.info("-- updateResetExpires: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updateUserInviteParams(int userid, String first_name, String last_name, String preferred_name, String password) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET first_name = ?, last_name = ?, preferred_name = ?, password_digest = ? WHERE id = ?;";
			 
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, first_name);
			 statement.setString(2, last_name);
			 statement.setString(3, preferred_name);
			 String password_digest = hasher.hash(password.toCharArray());
			 statement.setString(4, password_digest);
			 statement.setInt(5, userid);
			 logger.info("-- updateUserInviteParams: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean updateSetUpUser(int userid) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET set_up = 1 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userid);
			 logger.info("-- updateSetUpUser: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean deleteUser(int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET deleted = 1, set_up = 0 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, id);
			 logger.info("-- deleteUser: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

	public boolean recoverUser(int id) {
		return executeUpdate(connection -> {
			 final String query = "\nUPDATE users SET deleted = 0 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, id);
			 logger.info("-- recoverUser: " + statement);
			 int result = statement.executeUpdate();
			 close(connection, statement, null);
			 return result;
		}) != 0;
	}

// DEPRECATED: NOT USE ANYMORE	
//	@Override
//	public List<User> getUserFromIdList (ArrayList<String> userIdsList) {
//		return executeQuery(connection -> {
//			String query = "\nSELECT users.* FROM users WHERE users.id IN (";
//			
//			if (userIdsList.size() == 0) {
//				return null;
//			}
//			
//			for (int i = 0; i < userIdsList.size(); i++) {
//				query += (i == 0 ? "" :",") + "?";
//			}
//			query += ");";
//			
//			logger.info(query);
//			PreparedStatement statement = connection.prepareStatement(query);
//			
//			for (int i = 0; i < userIdsList.size(); i++) {
//				statement.setInt(i + 1, Integer.parseInt(userIdsList.get(i)));
//			}
//			
//			ResultSet results = statement.executeQuery();
//			logger.info(statement);
//			 
//			List<User> usersList = new ArrayList<>();
//			while(results.next()) {
//				usersList.add(mapper.map(results));
//			}
//			close(connection, statement, results);
//			return usersList;
//		 });
//	 }
}
