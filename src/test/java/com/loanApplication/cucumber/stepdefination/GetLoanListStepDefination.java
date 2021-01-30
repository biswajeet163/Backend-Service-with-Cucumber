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

public class GetLoanListStepDefination extends AbstractSpringTest {

	private static final Logger logger = LoggerFactory.getLogger(CreateLoanStepDefination.class);

	private String firstName = null;
	private ResponseEntity<String> response = null;

	@Given("^Get loan List by Member First Name \"([^\"]*)\"$")
	public void get_loan_List_by_Member_First_Name(String firstName) throws Throwable {
		if (logger.isInfoEnabled()) {
			logger.info("Getting Loan List Based on first Name  {} ", firstName);
		}
		this.firstName = firstName;
	}

	@When("^the client calls GET \"([^\"]*)\" with the Member First Name in query Param$")
	public void the_client_calls_GET_with_the_Member_First_Name_in_query_Param(String path) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("firstname", this.firstName); // id or loanId
		String url = buildUrl(HOST, PORT, path, null, queryParams); 
		logger.info("url {}", url);

		HttpEntity<?> requestEntity = new HttpEntity<>(queryParams, getDefaultHttpHeaders());
		System.out.println(requestEntity + "<------------------");
		response = invokeRESTCall(url, HttpMethod.GET, requestEntity);

	}

	@Then("^the Get client receives status code of (\\d+)$")
	public void the_Get_client_receives_status_code_of(int statusCode) throws Throwable {
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}
	}

}
