package codingmentor.javabackend.k3.repository;
import java.time.LocalDate;
import java.util.List;

import codingmentor.javabackend.k3.model.Klass;


public interface KlassRepository {
	/*
	 * GET LIST METHOD
	 */
	
    /**
	 * Get list of Klasss
	 *
	 * @return a list of all Klasss if found or null
	 */
	public List<Klass> getklasses();
	

    /**
     * 
     * @param department's is
     * @return list of courses 
     */
    public List<Klass> getKlassesFromDepartmentId (int departmentId);
    
	/*
	 * GET ITEM METHOD
	 */
    
    /**
     * Get Klass by Klass's id
     * @return Klass if exists and null if not
     */
    Klass getKlassById(int id);
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    /**
     * Add a new Klass to DB
     * @return newly inserted Klass id if inserted and -1 otherwise
     */
    public int insertKlass (int course_id, int repo_id, String semester, Integer section, LocalDate startDate, LocalDate endDate);

}