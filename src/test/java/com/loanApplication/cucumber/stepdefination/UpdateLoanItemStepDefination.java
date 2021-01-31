package com.loanApplication.cucumber.stepdefination;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateLoanItemStepDefination extends AbstractSpringTest {

	private static final Logger logger = LoggerFactory.getLogger(CreateLoanStepDefination.class);

	private String memberName = null;
	private String loanNumber;// loanType=null, loanTerm=null, amount=0, originDate=null, endDate=null}
	private ResponseEntity<String> response = null;

	@Given("^the Loan with LoanNumber \"([^\"]*)\" and  MemberName  \"([^\"]*)\" and other detail as well of Loan Item$")
	public void the_Loan_with_LoanNumber_and_MemberName_and_other_detail_as_well_of_Loan_Item(String loanNumber,
			String memberName) throws Throwable {
		System.out.println("\n\nUpdate=================================================================================");
		if (logger.isInfoEnabled()) {
			logger.info("Loan to be Update with existing LoanNumber {} and Modified Loan MemberName {}", loanNumber,
					memberName);
		}
		this.loanNumber = loanNumber;
		this.memberName = memberName;
	}

	@When("^the client calls \"([^\"]*)\" with the given detail$")
	public void the_client_calls_with_the_given_detail(String path) throws Throwable {
		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}
		String url = buildUrl(HOST, PORT, path);
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("memberName", this.memberName);
		requestMap.put("loanNumber", this.loanNumber);
		HttpEntity<?> requestEntity = new HttpEntity<>(requestMap, getDefaultHttpHeaders());
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.PUT, requestEntity);
	}

	@Then("^the client receive status code of (\\d+)$")
	public void the_client_receive_status_code_of(int statusCode) throws Throwable {
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}
	}

	@Then("^the response contains Updated MemberName \"([^\"]*)\"$")
	public void the_response_Updated_contains_MemberName_memberName(String memberName) throws Throwable {
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			String responseBody = response.getBody();
			com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
			Map<String, String> responseMap = mapper.readValue(responseBody, Map.class);
			System.out.println("***************** Recieved *******************");
			System.out.println(responseMap );
			System.out.println("**********************************************");
			assertEquals(memberName, responseMap.get("testUpdateMemberName"));
		}
	}

}
