package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.RubricItem;


public interface RubricItemRepository {
    /**
	 * Get list of RubricItems
	 *
	 * @return a list of all RubricItems if found or null
	 */
    List<RubricItem> getRubricItems();
    
    /**
     * Get department by RubricItem's id
     * @return RubricItem if exists and null if not
     */
    RubricItem getRubricItemById(int id);
}