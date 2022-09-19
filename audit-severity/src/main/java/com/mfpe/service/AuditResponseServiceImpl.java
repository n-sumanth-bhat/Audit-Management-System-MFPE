package com.mfpe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.repository.AuditResponseRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuditResponseServiceImpl implements AuditResponseService {

	@Autowired
	private AuditResponseRepo auditResponseRepo;

	/**
	 * 
	 * @param acceptableNo
	 * @param questions
	 * @return auditResponse
	 */
	public AuditResponse createAuditResponse(int acceptableNo, List<AuditQuestion> questions) {

		String auditType = questions.get(0).getAuditType();
		int actualNo = countNos(questions);
		AuditResponse auditResponse = new AuditResponse();

		log.info(String.format("Audit-type : %s, Actual-Nos : %d, Acceptable Nos : %d", auditType, actualNo,
				acceptableNo));

		if (actualNo <= acceptableNo && auditType.equalsIgnoreCase("Internal")) {
			auditResponse.setProjectExecutionStatus("GREEN");
			auditResponse.setRemedialActionDuration("No action needed");
		} else if (actualNo > acceptableNo && auditType.equalsIgnoreCase("Internal")) {
			auditResponse.setProjectExecutionStatus("RED");
			auditResponse.setRemedialActionDuration("Action to be taken in 2 weeks");
		} else if (actualNo <= acceptableNo && auditType.equalsIgnoreCase("SOX")) {
			auditResponse.setProjectExecutionStatus("GREEN");
			auditResponse.setRemedialActionDuration("No action needed");
		} else {
			auditResponse.setProjectExecutionStatus("RED");
			auditResponse.setRemedialActionDuration("Action to be taken in 1 week");
		}

		return auditResponse;
	}

	/**
	 * 
	 * @param questions
	 * @return count
	 */
	public int countNos(List<AuditQuestion> questions) {

		int count = 0;
		for (AuditQuestion q : questions) {
			if (q.getResponse().equalsIgnoreCase("No")) {
				count++;
			}
		}
		return count;
	}

	@Override
	public AuditResponse getAuditResponse(List<AuditBenchmark> benchmarkList, String auditType,
			List<AuditQuestion> questionResponses) {
		// TODO Auto-generated method stub
		int acceptableNo = 0;
		for (AuditBenchmark ab : benchmarkList) {
			if (ab.getAuditType().equalsIgnoreCase(auditType)) {
				acceptableNo = ab.getBenchmarkNoAnswers();
			}
		}

		AuditResponse auditResponse = createAuditResponse(acceptableNo, questionResponses);
		return auditResponse;
	}

	@Override
	public AuditResponse saveAuditResponse(AuditResponse auditResponse, AuditRequest auditRequest) {

		auditResponse.setProjectName(auditRequest.getProjectName());
		auditResponse.setManagerName(auditRequest.getManagerName());
		auditResponse.setCreationDateTime(new Date());
		log.info("Saving Audit-Response in DB :: " + auditResponse);

		return auditResponseRepo.save(auditResponse);
	}

}
