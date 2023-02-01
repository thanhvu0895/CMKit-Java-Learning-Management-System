package codingmentor.javabackend.k3.repository;


import java.time.LocalDateTime;
import java.util.List;
import codingmentor.javabackend.k3.model.User;

public interface UserRepository {
	/*
	 * GET List  @category
	 */
	
    /**
	 * Get list of all Users 
	 * @category LIST
	 * @return a list of all Users if found or null
	 */
    List<User> getUsers();
    
    /**
     * @category LIST
     * Get list of all Users given Department's id
     * @param Department's id
     * @return
     */
    public List<User> getUsersFromDepartmentId(int departmentId);
    
    /**
     * @category LIST
     * Get list of Klass Professor's User Details Admin given Klass's id
     * @param Department's id
     * @return
     */
    public List<User> getUsersFromKlassId(int klassId);
    
    /**
     * @category LIST
     * Get list of Klass Professor's User Details Admin given Klass's id
     * @param Department's id
     * @return
     */
    public List<User> getGraderUsersByKlassId(int klassId);
    
	/*
	 * GET Item
	 */
	/**
	 * Find User with given id
	 * @category ITEM
	 * @param id: User's id
	 * @return a User object if found or null
	 */
    User findUserById(int id);
    
	/**
	 * Find Single User given User's email
	 * @category ITEM
	 * @param email: User's email
	 * @return an User object if found or null
	 */
    User findUserByEmail(String email);
     
    
	/*
	 * GET Check True/false METHOD
	 */
    
	/**
     * Check if User with given email exists 
     * @category CHECK
     * @param Users's email
     * @return true if User with given email existed or false
     */
    boolean existedByEmail(String email);
    
    /**
     * @category CHECK
   	 * Check if user is department professor
   	 * @param: id: userId
   	 * @return boolean if update successful or else false
   	 */
    boolean isDepartmentProfessor(int id);
    
    /**
     * @category CHECK
   	 * Check if user is department professor by DepartmentId
   	 * @return boolean if update successful or else false
   	 */
    boolean isDepartmentProfessorByDepartmentId(int userId, int departmentId);
    
    /**
     * @category CHECK
   	 * Check if user is Klass professor by KlassId
   	 * @return boolean if update successful or else false
   	 */
    boolean isKlassProfessorByKlassId(int userId, int klassId);
    
    /*
     * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
     */
    
    
    //POST(INSERT)
    
	/**
	 * Insert new user with given email and admin attribute
	 * @category POST
	 * @param user data will insert to database
	 * true if successful or false
	 */
	boolean createUserSendInvite(String email, boolean admin);
    
    // PATCH(UPDATE)
    /**
	 * Update User information given all details
	 * @category PATCH
	 * @return boolean if update successful or else false
	 */
    boolean updateUserEditAdmin(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id);
    
 
    /**
     * @category PATCH
   	 * Update User's preferred name given user id 
   	 * @param: id: userId
   	 * @return boolean if update successful or else false
   	 */
    boolean updatePreferredNameById(String preferred_name, int id);
    
    /**
     * @category PATCH
   	 * Update user's password 
   	 * @param: User user
   	 * @return boolean if update successful or else false
   	 */
     boolean updatePassword(String new_password, User user);

  	/**
  	 * @category PATCH
  	 * Update user's reset_digest 
  	 * @param: User user
  	 * @return boolean if update successful or else false
  	 */
     boolean updateResetDigest(int userid, String reset_digest);
     
   	/**
   	 * @category PATCH
   	 * Update user's password 
   	 * @return boolean if update successful or else false
   	 */
     boolean updateUserInviteParams(int userid, String first_name, String last_name, String preferred_name, String password);
 
	/**
	 * @category PATCH
	 * Update User set_up to true or false
	 * @param: User user
	 * @return boolean if update successful or else false
	 */
      boolean updateSetUpUser(int userid);
      
      
      /**
       * @category PATCH
       * updateResetExpires
       */
      public boolean updateResetExpires(int userid, LocalDateTime reset_expires);
      
      /**
       * @category PATCH
       * Update User's set_up and deleted to True with given id ă
       */
      public boolean recoverUser(int id);

     //DELETE METHOD
     /**
      * @category DELETE
 	  * Update deleted param of User object
 	  */
      boolean deleteUser(int id);

}
