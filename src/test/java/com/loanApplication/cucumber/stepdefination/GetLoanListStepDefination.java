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
	private String lastName = null;
	private String loanNumber = null;
	private String clientType = null;

	private ResponseEntity<String> response = null;

	@Given("^Get loan List using query Param of clientType as \"([^\"]*)\"$")
	public void get_loan_List_using_query_Param_of_clientType_as(String clientType) throws Throwable {
		System.out.println("\n\nLoan_List===============================================================================");
		if (logger.isInfoEnabled()) {
			logger.info("Getting Loan List clientType as {}", clientType);
		}
		this.clientType = clientType;
	}

	@When("^the client calls GET \"([^\"]*)\" with the clientType in query Param$")
	public void the_client_calls_GET_with_the_clientType_in_query_Param(String path) throws Throwable {
		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("clientType", this.clientType); // id or loanId
		String url = buildUrl(HOST, PORT, path, null, queryParams);
		logger.info("url {}", url);

		HttpEntity<?> requestEntity = new HttpEntity<>(queryParams, getDefaultHttpHeaders());
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.GET, requestEntity);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("^Get loan List by Member First Name \"([^\"]*)\"$")
	public void get_loan_List_by_Member_First_Name(String firstName) throws Throwable {
		System.out.println("\n\nFirst_Name=============================================================================");
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
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.GET, requestEntity);

	}

	@Then("^the Get client receives status code of (\\d+)$")
	public void the_Get_client_receives_status_code_of(int statusCode) throws Throwable {
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("^Get loan List by Member Last Name \"([^\"]*)\"$")
	public void get_loan_List_by_Member_Last_Name(String lastName) throws Throwable {
		System.out.println("\n\nLast_Name==============================================================================");
		if (logger.isInfoEnabled()) {
			logger.info("Getting Loan List Based on Last Name  {} ", lastName);
		}
		this.lastName = lastName;
	}

	@When("^the client calls GET \"([^\"]*)\" with the Member Last Name in query Param$")
	public void the_client_calls_GET_with_the_Member_Last_Name_in_query_Param(String path) throws Throwable {
		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("lastname", this.lastName); // id or loanId
		String url = buildUrl(HOST, PORT, path, null, queryParams);
		logger.info("url {}", url);

		HttpEntity<?> requestEntity = new HttpEntity<>(queryParams, getDefaultHttpHeaders());
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.GET, requestEntity);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("^Get loan Item by Loan Number \"([^\"]*)\" and the query Param of clientType as \"([^\"]*)\"$")
	public void get_loan_Item_by_Loan_Number_and_the_query_Param_of_clientType_as(String loanNumber, String clientType)
			throws Throwable {
		System.out.println("\n\nLoan_Number============================================================================");
		if (logger.isInfoEnabled()) {
			logger.info("Getting Loan Item Based on Loan Number {} , clientType as {}", loanNumber, clientType);
		}
		this.loanNumber = loanNumber;
		this.clientType = clientType;
	}

	@When("^the client calls GET \"([^\"]*)\" with the Loan Number in query Param$")
	public void the_client_calls_GET_with_the_Loan_Number_in_query_Param(String path) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("loannumber", this.loanNumber); // id or loanId
		queryParams.add("clientType", this.clientType); // id or loanId
		String url = buildUrl(HOST, PORT, path, null, queryParams);
		logger.info("url {}", url);

		HttpEntity<?> requestEntity = new HttpEntity<>(queryParams, getDefaultHttpHeaders());
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.GET, requestEntity);

	}

}
