package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.User;

public interface UserRepository {
	/**
	 * Insert data of user to database
	 *
	 * @param user data will insert to database
	 * true if successful or false
	 */
	boolean createUserSendInvite(String email, boolean admin);
    
	/**
     * Check if user exists by email 
     *
     * @param email: users's email
     * @return true if user existed or false
     */
    boolean existedByEmail(String email);
    
	/**
	 * Find user by email
	 *
	 * @param email: user's email
	 * @return a user instance if found or null
	 */
    User findUserByEmail(String email);
    
	/**
	 * Find user by email
	 *
	 * @param id: user's id
	 * @return a user instance if found or null
	 */
    User findUserById(int id);
    
    /**
	 * Find list of users
	 *
	 * @return a list of all users if found or null
	 */
    List<User> getUsers();
    
    /**
	 * Update users
	 *
	 * @return boolean if update successful or else false
	 */
    boolean updateUserEditAdmin(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id);
    
    /**
   	 * Delete users
   	 * @param: id: userId
   	 * @return boolean if update successful or else false
   	 */
    boolean deleteUser(int id);
    
    /**
   	 * Update user's preferred name by id 
   	 * @param: id: userId
   	 * @return boolean if update successful or else false
   	 */
    boolean updatePreferredNameById(String preferred_name, int id);
    
    /**
   	 * Update user's password 
   	 * @param: User user
   	 * @return boolean if update successful or else false
   	 */
     boolean updatePassword(String new_password, User user);
     
      
  	/**
  	 * Update user's password 
  	 * @param: User user
  	 * @return boolean if update successful or else false
  	 */
     boolean updateResetDigest(int userid, String reset_digest);
     
   	/**
   	 * Update user's password 
   	 * @param: User user
   	 * @return boolean if update successful or else false
   	 */
     boolean updateUserInviteParams(int userid, String first_name, String last_name, String preferred_name, String password);
     
 		/**
    	 * Update user's password 
    	 * @param: User user
    	 * @return boolean if update successful or else false
    	 */
      boolean updateSetUpUser(int userid);
}
