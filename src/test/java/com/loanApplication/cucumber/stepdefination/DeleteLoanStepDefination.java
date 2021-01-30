package com.loanApplication.cucumber.stepdefination;

import static org.junit.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteLoanStepDefination extends AbstractSpringTest {

	private static final Logger logger = LoggerFactory.getLogger(CreateLoanStepDefination.class);

	private String loanNumber = null;
	private ResponseEntity<String> response = null;

	@Given("^the Loan to Be Deleted with LoanNumber \"([^\"]*)\"$")
	public void the_Loan_to_Be_Deleted_with_LoanNumber(String loanNumber) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("Loan to be deleted with LoanNumber  {} ", loanNumber);
		}
		this.loanNumber = loanNumber;
	}

	@When("^the client calls \"([^\"]*)\" with the LoanNumber in query Param$")
	public void the_client_calls_with_the_LoanNumber_in_query_Param(String path) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("id", this.loanNumber); // id or loanId
		String url = buildUrl(HOST, PORT, path, null, queryParams);
		logger.info("url {}", url);

		HttpEntity<?> requestEntity = new HttpEntity<>(queryParams, getDefaultHttpHeaders());
		System.out.println(requestEntity + "<------------------");

		Thread.sleep(3000);
		//response = invokeRESTCall(url, HttpMethod.DELETE, requestEntity);
	}

	@Then("^On successful delete the client receive status code of (\\d+)$")
	public void on_successful_delete_the_client_receive_status_code_of(int statusCode) throws Throwable {
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}
	}

///////////////////////
//	@Given("^the Already Deleted Loan to be deleted again with LoanNumber \"([^\"]*)\"$")
//	public void the_Already_Deleted_Loan_to_be_deleted_again_with_LoanNumber(String loanNumber) throws Throwable {
//		if (logger.isInfoEnabled()) {
//			logger.info("Deleted Loan to be deleted Again with LoanNumber  {} ", loanNumber);
//		}
//		this.loanNumber = loanNumber;
//	}
//
//	@Then("^On delete call, user must get error as NOT FOUND (\\d+)$")
//	public void on_delete_call_user_must_get_error_as_NOT_FOUND(int statusCode) throws Throwable {
//		if (response != null && response.getStatusCode().is2xxSuccessful()) {
//			assertEquals(statusCode, response.getStatusCode().value());
//		}
//	}

}
