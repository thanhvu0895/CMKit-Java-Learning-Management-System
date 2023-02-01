package codingmentor.javabackend.k3.repository;

import java.util.List;
import codingmentor.javabackend.k3.model.RubricItem;


public interface RubricItemRepository {
	
	/*
	 * GET LIST METHOD
	 */
	
    /**
     * @category LIST
	 * Get list of RubricItems
	 *
	 * @return a list of all RubricItems if found or null
	 */
    List<RubricItem> getRubricItems();
    
    /**
     * @category LIST
	 * Get list of RubricItems By Problem Id 
	 *
	 * @return a list of all RubricItems if found or null
	 */
    List<RubricItem> getRubricItemsByProblemId(int problem_id);
    
    /**
     * @category LIST
	 * Get list of RubricItems By Problem Id order by location asc
	 *
	 * @return a list of all RubricItems if found or null
	 */
    List<RubricItem> getRubricItemsByProblemIdOrderByLocationAsc(int problem_id);
    
    
	/*
	 * GET ITEM METHOD
	 */
    /**
     * @category ITEM
     * Get department by RubricItem's id
     * @return RubricItem if exists and null if not
     */
    RubricItem getRubricItemById(int id);
    
    
    /**
  	 * @category ITEM
     * Get Rubric Item by location and Problem's id
     * @return Rubric Item if exists and null if not
     */
    RubricItem getRubricItemByLocationAndProblemId(int location, int problem_id);
    
    /**
  	 * @category ITEM
     * Get Rubric Item with Max Location By Problem id
     * @return Rubric Item if exists and null if not
     */
    RubricItem getMaxRubricItemByProblemId(int problem_id);
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /**
	 * @category CHECK
     */
    
    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * @category POST
     * Add a new RubricItem to DB
     * @return newly inserted Professor id if inserted and -1 otherwise
     */
    boolean insertRubricItem(int problem_id, String title, double points, int location);
    
    /**
     * @category PATCH
     * Update title and points of Rubric Item by id
     * @return
     */
    boolean updateRubricItemById(String title, double points, int id);

    /**
     * @category PATCH
     * Update location of Rubric Item by id
     * @return
     */
    boolean updateRubricItemLocationById (int location, int rubricItemId);
    
    /**
	 * @category DELETE
     */
}