package com.mfpe.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Component
@Getter
@Setter
@Entity
@Table(name = "project_manager_details")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManagerDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique = true)
	private String username;
	private String password;
	private String projectName;

	public ProjectManagerDetails(ProjectManagerDetails projectManagerdetails) {
		this.id = projectManagerdetails.getId();
		this.name = projectManagerdetails.getName();
		this.username = projectManagerdetails.getUsername();
		this.password = new BCryptPasswordEncoder(10).encode(projectManagerdetails.getPassword());
		this.projectName = projectManagerdetails.getProjectName();
	}

	public String getName() {
		return this.name;
	}

	public String getProjectName() {
		return this.projectName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
