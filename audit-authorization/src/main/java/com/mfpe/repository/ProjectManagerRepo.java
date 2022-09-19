package com.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mfpe.model.ProjectManagerDetails;

@Repository
public interface ProjectManagerRepo extends JpaRepository<ProjectManagerDetails, Integer> {

	@Query(value = "SELECT p FROM ProjectManagerDetails p WHERE p.username = ?1")
	public ProjectManagerDetails getProjectManagerByUserName(String username);
}
