package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Klass;


public interface KlassRepository {
    /**
	 * Get list of Klasss
	 *
	 * @return a list of all Klasss if found or null
	 */
    List<Klass> getKlasss();
    
    /**
     * Get department by Klass's id
     * @return Klass if exists and null if not
     */
    Klass getKlassById(int id);
}