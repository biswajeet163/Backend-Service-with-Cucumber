package com.loanApplication.cucumber.stepdefination;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanApplication.LoanServiceApplication;

@SpringBootTest(classes = LoanServiceApplication.class)//, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@DirtiesContext
public abstract class AbstractSpringTest {
	
	@Autowired(required = false)
	private TestRestTemplate restTemplate;
	protected ObjectMapper mapper = new ObjectMapper();
	protected static final String HOST = "localhost";
	protected static final String PORT = "8100";
	
	public static String headersToken="";

	public TestRestTemplate getRestTemplate() {
		return restTemplate != null ? restTemplate : new TestRestTemplate();
	}

	public void setRestTemplate(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}// ...

	public ResponseEntity<String> invokeRESTCall(String url, HttpMethod method, HttpEntity<?> requestEntity) {
		return getRestTemplate().exchange(url, method, requestEntity, String.class);
	}
	
	public void setJWTToken(String token) {
		
		this.headersToken = token;
		//System.out.println("Set Token>>>>>  "+ this.headersToken);
	}

	public HttpHeaders getDefaultHttpHeaders() {
		System.out.println("\nAvailable Token-> "+ this.headersToken+"\n");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", this.headersToken);
		headers.setContentType(MediaType.APPLICATION_JSON); 
		return headers; 
	}// ...

	public String buildUrl(String host, String port, String path, Map<String, String> uriVariables,
			MultiValueMap<String, String> queryParams) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path).host(host).port(port).scheme("http");
		if (queryParams != null && !queryParams.isEmpty())
			builder.queryParams(queryParams);
		UriComponents uriComponent = uriVariables != null && !uriVariables.isEmpty()
				? builder.buildAndExpand(uriVariables)
				: builder.build();

		return uriComponent.toUri().toString(); 
	}
 
	public String buildUrl(String host, String port, String path) {
		return buildUrl(host, port, path, null, null);
	}

	public String buildUrl(String host, String port, String path, Map<String, String> uriVariables) {
		return buildUrl(host, port, path, uriVariables, null);
	}

}
