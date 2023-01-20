package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.SshKey;


public interface SshKeyRepository {
    /**
	 * Get list of SshKeys
	 *
	 * @return a list of all SshKeys if found or null
	 */
    List<SshKey> getSshKeys();
    
    /**
     * Get department by SshKey's id
     * @return SshKey if exists and null if not
     */
    SshKey getSshKeyById(int id);
}