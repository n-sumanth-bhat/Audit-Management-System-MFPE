package com.mfpe.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditType;

@FeignClient(url = "${ms.checklist}", name = "auditChecklist")
public interface AuditCheckListFeign {

	@RequestMapping(value = "/auditchecklistquestions", method = RequestMethod.POST)
	public List<AuditQuestion> auditCheckListQuestions(@RequestHeader("Authorization") String jwt,
			@RequestBody AuditType auditType);

}
