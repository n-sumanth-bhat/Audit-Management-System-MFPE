package com.mfpe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.repository.ProjectManagerRepo;

@Service
public class ProjectManagerDetailsService implements UserDetailsService {

	@Autowired
	private ProjectManagerRepo projectManagerRepo;


	// this method returns the User object based on the username...
	// whose password will get checked with the password we provided in this User
	// object..
	// if match --> authenticated , if not match --> user not authenticated

	@Override
	public ProjectManagerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// it gets the ProjectManager using ProjectManagerService and
		// from there it gets the ProjecManagerDetails from ProjectManager using
		// ProjectManagerDetailsService...
		// which is then used in SecurityConfig
		ProjectManagerDetails projectManagerDetails = null;
		projectManagerDetails = getProjectManagerByUserName(username);
		if (projectManagerDetails != null) {
			projectManagerDetails = new ProjectManagerDetails(getProjectManagerByUserName(username));
		}
		

		return projectManagerDetails;
	}

	/**
	 * 
	 * @param username
	 * @return projectManager
	 * @throws ProjectManagerNotFoundException
	 */
	public ProjectManagerDetails getProjectManagerByUserName(String username) throws ProjectManagerNotFoundException {
		ProjectManagerDetails projectManagerDetails = null;
		projectManagerDetails = projectManagerRepo.getProjectManagerByUserName(username);
		if (projectManagerDetails == null) {
			throw new ProjectManagerNotFoundException("Given Project-Manager-Details does not exist in our Database!!");
		}
		return projectManagerDetails;
	}

}
