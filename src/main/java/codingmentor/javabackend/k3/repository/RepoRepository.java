package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Repo;


public interface RepoRepository {
    /**
	 * Get list of Repos
	 *
	 * @return a list of all Repos if found or null
	 */
    List<Repo> getRepos();
    
    /**
     * Get department by Repo's id
     * @return Repo if exists and null if not
     */
    Repo getRepoById(int id);
}