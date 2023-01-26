package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Professor;


public interface ProfessorRepository {
	/*
	 * GET LIST METHOD
	 */
	
	/**
	 * Get list of Professors
	 *
	 * @return a list of all Professors if found or null
	 */
    List<Professor> getProfessors();
    
    
	/*
	 * GET ITEM METHOD
	 */
    
    /**
     * Get department by Professor's id
     * @return Professor if exists and null if not
     */
    Professor getProfessorById(int id);
    
	/*
	 * GET Check True/false METHOD
	 */
    
    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    
    /**
     * Add a new Professor to DB
     * @return newly inserted Professor id if inserted and -1 otherwise
     */
    int insertProfessor (int user_id, int klass_id);
    
}