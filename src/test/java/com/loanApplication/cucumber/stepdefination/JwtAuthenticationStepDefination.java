package com.loanApplication.cucumber.stepdefination;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

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

public class JwtAuthenticationStepDefination extends AbstractSpringTest {

	private static final Logger logger = LoggerFactory.getLogger(CreateLoanStepDefination.class);

	private String username = null;
	private String password = null;
	private String token = null;

	private ResponseEntity<String> response = null;

	@Given("^the UserCredential with Username \"([^\"]*)\" and  Password  \"([^\"]*)\"$")
	public void the_UserCredential_with_Username_and_Password(String username, String password) throws Throwable {

		System.out.println("\n\nPost Login=================================================================");
		if (logger.isInfoEnabled()) {
			logger.info("UserCredentials with UserName {} and Password {}", username, password);
		}
		this.username = username;
		this.password = password;

	}

	@When("^the client calls \"([^\"]*)\" with USerCredentials$")
	public void the_client_calls_with_USerCredentials(String path) throws Throwable {

		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}
		String url = buildUrl(HOST, "9192", path);
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("username", this.username);
		requestMap.put("password", this.password);
		HttpEntity<?> requestEntity = new HttpEntity<>(requestMap, getDefaultHttpHeaders());
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.POST, requestEntity);

		String token = response.getBody().split(",")[1].split(":")[1].replaceAll("\"", "");
		this.token = token;
		super.setJWTToken(token);
		System.out.println("Login set token -> " + token + "\n\n\n");

	}

	@Then("^the client receives status code  (\\d+)$")
	public void the_client_receives_status_code(int statusCode) throws Throwable {
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}
	}

	@Then("^the response contains Username \"([^\"]*)\"$")
	public void the_response_contains_Username(String userName) throws Throwable {

		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			String responseBody = response.getBody();
			com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
			Map<String, String> responseMap = mapper.readValue(responseBody, Map.class);
			System.out.println("***************** Recieved *******************");
			System.out.println(responseMap );
			System.out.println("**********************************************");
			assertEquals(userName, responseMap.get("username"));
		}

	}

	//////////////////////////////////////////////////////////////////// Validate

	@When("^the client calls \"([^\"]*)\" with the generated TOKEN$")
	public void the_client_calls_with_the_generated_TOKEN(String path) throws Throwable {
		System.out.println(
				"\n\nValidating===============================================================================");
		if (logger.isInfoEnabled()) {
			logger.info("Validating the token  ");
		}

		if (logger.isInfoEnabled()) {
			logger.info("path {}", path);
		}

		// MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		// queryParams.add("clientType", this.clientType); // id or loanId
		String url = buildUrl(HOST, "9192", path);
		logger.info("url {}", url);

		HttpEntity<?> requestEntity = new HttpEntity<>(null, getDefaultHttpHeaders());
		System.out.println("**************** Sending ********************");
		System.out.println(requestEntity );
		System.out.println("**********************************************");
		response = invokeRESTCall(url, HttpMethod.GET, requestEntity);

	}

	@Then("^the receive status code is  (\\d+)$")
	public void the_receive_status_code_is(int statusCode) throws Throwable {

		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			assertEquals(statusCode, response.getStatusCode().value());
		}

	}

}
