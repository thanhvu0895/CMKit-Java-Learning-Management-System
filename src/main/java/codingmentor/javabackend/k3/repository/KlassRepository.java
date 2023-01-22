package codingmentor.javabackend.k3.repository;

import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.model.Klass;


public interface KlassRepository {
    /**
	 * Get list of Klasss
	 *
	 * @return a list of all Klasss if found or null
	 */
	public List<Klass> getklasses();
    
    /**
     * Get Klass by Klass's id
     * @return Klass if exists and null if not
     */
    Klass getKlassById(int id);
    
    
    /**
     * 
     * @param courseIdsList
     * @return
     */
    public List<Klass> getKlassFromCourseIdList (ArrayList<String> courseIdsList);
}