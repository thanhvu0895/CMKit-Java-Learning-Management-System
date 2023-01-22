package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.DepartmentProfessor;


public interface DepartmentProfessorRepository {
    /**
	 * Get list of DepartmentProfessors
	 *
	 * @return a list of all DepartmentProfessors if found or null
	 */
    List<DepartmentProfessor> getDepartmentProfessors();
    
    /**
     * Get department by DepartmentProfessor's id
     * @return DepartmentProfessor if exists and null if not
     */
    DepartmentProfessor getDepartmentProfessorById(int id);
    
    
    /**
     * Get department by DepartmentProfessor's id
     * @return DepartmentProfessor if exists and null if not
     */
    List<DepartmentProfessor>  getDepartmentProfessorsByDepartmentId(int id);

    /**
     * 
     * @param admin
     * @param id
     * @return
     */
    public boolean updateAdminByDepartmentProfessorId(boolean admin, int id);
}