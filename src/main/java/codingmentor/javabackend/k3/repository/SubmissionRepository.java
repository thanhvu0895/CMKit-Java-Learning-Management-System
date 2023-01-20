package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.Submission;


public interface SubmissionRepository {
    /**
	 * Get list of Submissions
	 *
	 * @return a list of all Submissions if found or null
	 */
    List<Submission> getSubmissions();
    
    /**
     * Get department by Submission's id
     * @return Submission if exists and null if not
     */
    Submission getSubmissionById(int id);
}