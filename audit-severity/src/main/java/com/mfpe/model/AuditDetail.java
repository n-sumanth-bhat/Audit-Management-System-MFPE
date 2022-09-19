package com.mfpe.model;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AuditDetail {

	private String auditType;
	private Date auditDate;
	private List<AuditQuestion> auditQuestions;
}
