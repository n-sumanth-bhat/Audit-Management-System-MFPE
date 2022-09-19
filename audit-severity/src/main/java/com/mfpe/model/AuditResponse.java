package com.mfpe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@Table(name = "audit_response")
@AllArgsConstructor
@NoArgsConstructor
public class AuditResponse {

	@Id
	@GeneratedValue
	private int auditId;
	private String managerName;
	private String projectName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDateTime;

	private String projectExecutionStatus;
	private String remedialActionDuration;

	public void setCreationDateTime(Date creationDateTime2) {
		this.creationDateTime = creationDateTime2;
	}

}
