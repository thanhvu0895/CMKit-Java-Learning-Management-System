package codingmentor.javabackend.k3.repository;


import java.time.LocalDateTime;
import java.util.List;
import codingmentor.javabackend.k3.model.User;

public interface UserRepository {
	/*
	 * GET List 
	 */
	
    /**
	 * Get list of all Users 
	 *
	 * @return a list of all Users if found or null
	 */
    List<User> getUsers();
    
    /**
     * Get list of all Users given Department's id
     * @param Department's id
     * @return
     */
    public List<User> getUsersFromDepartmentId(int departmentId);
    
	/*
	 * GET Item
	 */
	/**
	 * Find User with given id
	 *
	 * @param id: User's id
	 * @return a User object if found or null
	 */
    User findUserById(int id);
    
	/*
	 * GET Item
	 */
	/**
	 * Find User with given id with white listed param
	 *
	 * @param id: User's id
	 * @return a User object if found or null
	 */
    User findUserByIdParamsWhiteListed(int id);
    
    
	/**
	 * Find Single User given User's email
	 *
	 * @param email: User's email
	 * @return an User object if found or null
	 */
    User findUserByEmail(String email);
        
	/**
     * Check if User with given email exists 
     *
     * @param Users's email
     * @return true if User with given email existed or false
     */
    boolean existedByEmail(String email);
    
    /**
   	 * Check if user is department professor
   	 * @param: id: userId
   	 * @return boolean if update successful or else false
   	 */
    boolean isDepartmentProfessor(int id);
    
    /**
   	 * Check if user is department professor by DepartmentId
   	 * @param: id: userId
   	 * @return boolean if update successful or else false
   	 */
    boolean isDepartmentProfessorByDepartmentId(int userId, int departmentId);
    
    
    /*
     * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
     */
    
    
    //POST(INSERT)
    
	/**
	 * Insert new user with given email and admin attribute
	 *
	 * @param user data will insert to database
	 * true if successful or false
	 */
	boolean createUserSendInvite(String email, boolean admin);
    
    // PATCH(UPDATE)
    /**
	 * Update User information given all details
	 *
	 * @return boolean if update successful or else false
	 */
    boolean updateUserEditAdmin(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id);
    
    

    /**
   	 * Update User's preferred name given user id 
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
  	 * Update user's reset_digest 
  	 * @param: User user
  	 * @return boolean if update successful or else false
  	 */
     boolean updateResetDigest(int userid, String reset_digest);
     
   	/**
   	 * Update user's password 
   	 * @return boolean if update successful or else false
   	 */
     boolean updateUserInviteParams(int userid, String first_name, String last_name, String preferred_name, String password);
 
	/**
	 * Update User set_up to true or false
	 * @param: User user
	 * @return boolean if update successful or else false
	 */
      boolean updateSetUpUser(int userid);
      
      
      /**
       * updateResetExpires
       */
      public boolean updateResetExpires(int userid, LocalDateTime reset_expires);
      
      /**
       * Update User's set_up and deleted to True with given id 
       */
      public boolean recoverUser(int id);
      
     /**
 	  * Update deleted param of User object
 	  */
      boolean deleteUser(int id);

}
