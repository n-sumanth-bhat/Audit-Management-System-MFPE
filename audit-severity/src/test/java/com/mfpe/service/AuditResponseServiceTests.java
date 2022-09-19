package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditDetail;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.repository.AuditResponseRepo;

@SpringBootTest
class AuditResponseServiceTests {

	@Mock
	AuditResponseRepo auditResponseRepo;

	@InjectMocks
	AuditResponseServiceImpl auditResponseServiceImpl;

	AuditResponse auditResponse = new AuditResponse(1, "ManagerName", "ProjectName", new Date(),
			"ProjectExecutionStatus", "RemedialActionDuration");
	List<AuditQuestion> questions = new ArrayList<>();
	Date date = new Date();
	List<AuditQuestion> questionResponseList = new ArrayList<>();
	AuditRequest auditRequest = new AuditRequest("ProjectName", "ManagerName",
			new AuditDetail("Internal", date, questionResponseList));

	@Test
	public void contextLoads() throws Exception {
		assertNotNull(auditResponseServiceImpl);
	}

	@Test
	public void testGetAuditResponseInternal3Nos() {

		List<AuditBenchmark> benchmarkList = new ArrayList<>();
		benchmarkList.add(new AuditBenchmark(1, "Internal", 3));
		benchmarkList.add(new AuditBenchmark(2, "SOX", 1));

		List<AuditQuestion> questions = new ArrayList<>();
		questions.add(
				new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
		questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
				"Internal", "YES"));
		questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
				"NO"));
		questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"Internal", "NO"));
		auditResponse = new AuditResponse(0, null, null, null, "GREEN", "No action needed");

		assertEquals(auditResponse, auditResponseServiceImpl.getAuditResponse(benchmarkList, "Internal", questions));
	}

	@Test
	public void testGetAuditResponseInternal4Nos() {

		List<AuditBenchmark> benchmarkList = new ArrayList<>();
		benchmarkList.add(new AuditBenchmark(1, "Internal", 3));
		benchmarkList.add(new AuditBenchmark(2, "SOX", 1));

		List<AuditQuestion> questions = new ArrayList<>();
		questions.add(
				new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
		questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
				"Internal", "NO"));
		questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
				"NO"));
		questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"Internal", "NO"));
		auditResponse = new AuditResponse(0, null, null, null, "RED", "Action to be taken in 2 weeks");

		assertEquals(auditResponse, auditResponseServiceImpl.getAuditResponse(benchmarkList, "Internal", questions));
	}

	@Test
	public void testGetAuditResponseSox1Nos() {

		List<AuditBenchmark> benchmarkList = new ArrayList<>();
		benchmarkList.add(new AuditBenchmark(1, "Internal", 3));
		benchmarkList.add(new AuditBenchmark(2, "SOX", 1));

		List<AuditQuestion> questions = new ArrayList<>();
		questions.add(new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "SOX", "YES"));
		questions.add(
				new AuditQuestion(2, "Have all Change requests been approved by the application owner?", "SOX", "NO"));
		questions.add(new AuditQuestion(3,
				"For a major change, was there a database backup taken before and after PROD move?", "SOX", "YES"));
		questions.add(new AuditQuestion(4,
				"Has the application owner approval obtained while adding a user to the system?", "SOX", "YES"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"SOX", "YES"));
		auditResponse = new AuditResponse(0, null, null, null, "GREEN", "No action needed");

		assertEquals(auditResponse, auditResponseServiceImpl.getAuditResponse(benchmarkList, "SOX", questions));
	}

	@Test
	public void testGetAuditResponseSox3Nos() {

		List<AuditBenchmark> benchmarkList = new ArrayList<>();
		benchmarkList.add(new AuditBenchmark(1, "Internal", 3));
		benchmarkList.add(new AuditBenchmark(2, "SOX", 1));

		List<AuditQuestion> questions = new ArrayList<>();
		questions.add(new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "SOX", "YES"));
		questions.add(
				new AuditQuestion(2, "Have all Change requests been approved by the application owner?", "SOX", "NO"));
		questions.add(new AuditQuestion(3,
				"For a major change, was there a database backup taken before and after PROD move?", "SOX", "YES"));
		questions.add(new AuditQuestion(4,
				"Has the application owner approval obtained while adding a user to the system?", "SOX", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"SOX", "NO"));
		auditResponse = new AuditResponse(0, null, null, null, "RED", "Action to be taken in 1 week");

		assertEquals(auditResponse, auditResponseServiceImpl.getAuditResponse(benchmarkList, "SOX", questions));
	}

	@Test
	public void testSaveAuditResponse() {
		questionResponseList.add(new AuditQuestion(1, "question", "Internal", "YES"));
		when(auditResponseRepo.save(auditResponse)).thenReturn(auditResponse);
		assertEquals(auditResponse, auditResponseServiceImpl.saveAuditResponse(auditResponse, auditRequest));
	}

	@Test
	public void testCountNos() {
		questions.add(
				new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
		questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
				"Internal", "YES"));
		questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
				"NO"));
		questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"Internal", "NO"));
		int count = 3;
		assertEquals(count, auditResponseServiceImpl.countNos(questions));
	}

	@Test
	public void testCreateAuditResponseInternalGreenStatus() {
		questions.add(
				new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
		questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
				"Internal", "YES"));
		questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
				"NO"));
		questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"Internal", "NO"));

		AuditResponse internalGreenAuditResponse = new AuditResponse(0, null, null, null, "GREEN", "No action needed");
		assertEquals(internalGreenAuditResponse, auditResponseServiceImpl.createAuditResponse(3, questions));

	}

	@Test
	public void testCreateAuditResponseInternalGreenStatusWithHighValue() {
		questions.add(
				new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
		questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
				"Internal", "YES"));
		questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
				"NO"));
		questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"Internal", "NO"));

		AuditResponse internalGreenAuditResponse = new AuditResponse(0, null, null, null, "GREEN", "No action needed");
		assertEquals(internalGreenAuditResponse, auditResponseServiceImpl.createAuditResponse(4, questions));

	}

	@Test
	public void testCreateAuditResponseInternalRedStatus() {
		questions.add(
				new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
		questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
				"Internal", "NO"));
		questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
				"NO"));
		questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"Internal", "NO"));

		AuditResponse internalGreenAuditResponse = new AuditResponse(0, null, null, null, "RED",
				"Action to be taken in 2 weeks");
		assertEquals(internalGreenAuditResponse, auditResponseServiceImpl.createAuditResponse(3, questions));

	}

	@Test
	public void testCreateAuditResponseSoxGreenStatus() {
		questions.add(new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "SOX", "YES"));
		questions.add(
				new AuditQuestion(2, "Have all Change requests been approved by the application owner?", "SOX", "YES"));
		questions.add(new AuditQuestion(3,
				"For a major change, was there a database backup taken before and after PROD move?", "SOX", "YES"));
		questions.add(new AuditQuestion(4,
				"Has the application owner approval obtained while adding a user to the system?", "SOX", "YES"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"SOX", "NO"));

		AuditResponse internalGreenAuditResponse = new AuditResponse(0, null, null, null, "GREEN", "No action needed");
		assertEquals(internalGreenAuditResponse, auditResponseServiceImpl.createAuditResponse(1, questions));

	}

	@Test
	public void testCreateAuditResponseSoxRedStatus() {
		questions.add(new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "SOX", "YES"));
		questions.add(
				new AuditQuestion(2, "Have all Change requests been approved by the application owner?", "SOX", "YES"));
		questions.add(new AuditQuestion(3,
				"For a major change, was there a database backup taken before and after PROD move?", "SOX", "NO"));
		questions.add(new AuditQuestion(4,
				"Has the application owner approval obtained while adding a user to the system?", "SOX", "NO"));
		questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
				"SOX", "NO"));

		AuditResponse internalGreenAuditResponse = new AuditResponse(0, null, null, null, "RED",
				"Action to be taken in 1 week");
		assertEquals(internalGreenAuditResponse, auditResponseServiceImpl.createAuditResponse(1, questions));

	}

}
