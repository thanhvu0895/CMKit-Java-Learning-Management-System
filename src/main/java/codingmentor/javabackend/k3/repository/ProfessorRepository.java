package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Professor;


public interface ProfessorRepository {
    /**
	 * Get list of Professors
	 *
	 * @return a list of all Professors if found or null
	 */
    List<Professor> getProfessors();
    
    /**
     * Get department by Professor's id
     * @return Professor if exists and null if not
     */
    Professor getProfessorById(int id);
}