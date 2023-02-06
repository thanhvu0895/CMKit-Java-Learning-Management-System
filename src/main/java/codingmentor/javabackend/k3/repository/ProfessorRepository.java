package codingmentor.javabackend.k3.repository;
import java.util.List;

import codingmentor.javabackend.k3.model.Professor;


public interface ProfessorRepository {
	/*
	 * GET LIST METHOD
	 */
	
	/**
	 * @category LIST
	 * Get list of Professors
	 *
	 * @return a list of all Professors if found or null
	 */
    List<Professor> getProfessors();
    
	/**
	 * @category LIST 
	 * Get list of Professors by Klass's id
	 *
	 * @return a list of all Professors if found or null
	 */
    List<Professor> getProfessorsByKlassId(int klassId);
    
    
	/*
	 * GET ITEM METHOD
	 */
    
    /**
	 * @category ITEM
     * Get Professor by Professor's id
     * @return Professor if exists and null if not
     */
    Professor getProfessorById(int id);
    
    /**
	 * @category ITEM
     * Get Professor by User id
     * @return Professor if exists and null if not
     */
    Professor getProfessorByUserId(int userId);
    
    
	/*
	 * GET Check True/false METHOD
	 */
    /**
	 * @category CHECK
     */
    boolean isProfessorByUserId(int userId, int klassId);
    
    /*
	 * POST(CREATE) PUT(REPLACE) PATCH(UPDATE) METHODS
	 */
	
	//POST(INSERT INTO)
    
    /**
	 * @category POST
     * Add a new Professor to DB
     * @return newly inserted Professor id if inserted and -1 otherwise
     */
    int insertProfessor (int user_id, int klass_id);
    
    /**
     * @category PATCH
	 */
    
    /**
	 * @category DELETE
     */
    
	/**
	 * @category DELETE
	 *  Remove Professor with given id
	 */
    boolean deleteProfessor(int professorId);    
}