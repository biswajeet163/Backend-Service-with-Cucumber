package com.loanApplication.junit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.loanApplication.controller.LoanController;
import com.loanApplication.exception.InvalidExpiredTokenException;
import com.loanApplication.exception.LoanDoesNotExistException;
import com.loanApplication.feign.FeignForSecurity;
import com.loanApplication.model.AuthResponse;
import com.loanApplication.model.Loan;
import com.loanApplication.service.LoanService;

import feign.FeignException.FeignClientException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class LoanControllerTest {

	@InjectMocks
	LoanController loanController;

	@Mock
	LoanService loanService;

	@Mock
	FeignForSecurity feignForSecurity;

	@Test
	void testGetLoan() throws InvalidExpiredTokenException {
		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		List<Loan> loanList = new ArrayList<>();
		loanList.add(loan); 

		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);

		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.getAllLoanListAdmin()).thenReturn(loanList); // For loan List
		assertEquals(HttpStatus.OK, loanController.getLoan("token", "admin_Deepak").getStatusCode());
		assertEquals(HttpStatus.OK, loanController.getLoan("token", "user_Deepak").getStatusCode());

	}

	@Test
	void testGetLoanException() throws Exception {
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.getLoan("token", "admin_Deepak"), "");

	}

	@Test
	void testGetLoanByLoanNumber() throws InvalidExpiredTokenException {

		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		List<Loan> loanList = new ArrayList<>();
		loanList.add(loan);

		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);

		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.getLoanByLoanNumberAdmin("1")).thenReturn(loanList); // For loan List
		assertEquals(HttpStatus.OK, loanController.getLoanByLoanNumber("token", "admin_Deepak", "1").getStatusCode());
		assertEquals(HttpStatus.OK,loanController.getLoanByLoanNumber("token", "user_Deepak", "1").getStatusCode());

	}
 
	@Test
	void testGetLoanByLoanNumberException() throws Exception {
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class,
				() -> loanController.getLoanByLoanNumber("token", "user_Deepak", "1"), "");
	}

	@Test
	void testGetLoanByFirstName() throws InvalidExpiredTokenException {
		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		List<Loan> loanList = new ArrayList<>();
		loanList.add(loan);

		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);

		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.getLoanByFirstName("Deepak")).thenReturn(loanList); // For loan List
		assertEquals(HttpStatus.OK, loanController.getLoanByFirstName("token", "Deepak").getStatusCode() );

	}

	@Test
	void testGetLoanByFirstNameException() throws Exception {
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.getLoanByFirstName("token", "Deepak"),
				"");

	}

	@Test
	void testGetLoanByLastName() throws InvalidExpiredTokenException {
		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		List<Loan> loanList = new ArrayList<>();
		loanList.add(loan);

		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);

		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.getLoanByLastName("Roy")).thenReturn(loanList); // For loan List
		assertEquals( HttpStatus.OK, loanController.getLoanByLastName("token", "Roy").getStatusCode());

	}

	@Test
	void testGetLoanByLastNameException() throws Exception {
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.getLoanByLastName("token", "Roy"), "");
	}

	@Test
	void testOriginateNewLoan() throws InvalidExpiredTokenException {
		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());

		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);
		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.addNewLoanItem(loan)).thenReturn(loan); // For loan List
		assertEquals(HttpStatus.OK, loanController.originateNewLoan("token", loan).getStatusCode());
	}

	@Test
	void testOriginateNewLoanException() throws Exception {
		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.originateNewLoan("token", loan), "");
	}

	@Test
	void testDeleteLoan() throws LoanDoesNotExistException, InvalidExpiredTokenException {

		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);
		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.deleteLoanItemById("1")).thenReturn("Deleted"); // For loan List
		assertEquals(HttpStatus.OK, loanController.deleteLoan("token", "1").getStatusCode());
	}

	@Test
	void testDeleteLoanException() throws Exception {
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.deleteLoan("token", "1"), "");
	}

	@Test
	void testUpdateLoan() throws LoanDoesNotExistException, InvalidExpiredTokenException {

		Loan newLoan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		AuthResponse authres = new AuthResponse();
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);
		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.updateLoanItem(newLoan)).thenReturn(newLoan); // For loan List
		assertEquals(HttpStatus.OK, loanController.updateLoan("token", newLoan).getStatusCode() );
	}

	@Test
	void testUpdateLoanException() throws Exception {
		Loan newLoan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.updateLoan("token", newLoan), "");
	}

	@Test
	void testGetHello() throws InvalidExpiredTokenException {
		Loan loan = new Loan("Biswa", "Car", "short", 10, new Date(), new Date());
		List<Loan> loanList = new ArrayList<>();
		loanList.add(loan);

		AuthResponse authres = new AuthResponse();

		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<AuthResponse>(authres, HttpStatus.OK);

		when(feignForSecurity.validate("token")).thenReturn(authResponse); // for Auth

		when(loanService.test()).thenReturn(loanList); // For loan List
		assertEquals(HttpStatus.OK, loanController.getHello("token").getStatusCode());

	}

	@Test
	void testGetHelloException() throws Exception {
		when(feignForSecurity.validate("token")).thenThrow(FeignClientException.class);
		assertThrows(InvalidExpiredTokenException.class, () -> loanController.getHello("token"), "");
	}

}
